package com.amazon.device.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.preference.PreferenceManager;
import com.amazon.device.ads.AdError.ErrorCode;
import com.amazon.device.ads.DtbCommonUtils.APIVersion;
import com.facebook.ads.internal.j.e;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.mopub.common.AdType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DTBAdRequest implements DTBAdLoader {
    static JSONArray mRaidArray = null;
    static JSONArray mRaidCustomArray = null;
    static boolean mRaidDefined = false;
    private final String LOG_TAG = DTBAdRequest.class.getSimpleName();
    private volatile AdError adError = null;
    private DTBAdResponse adResponse;
    private final List<DTBAdSize> adSizes = new ArrayList();
    private DTBAdCallback callback;
    private Context context;
    private final Map<String, String> customTargets = new HashMap();
    private boolean isAutoRefresh = false;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private final Runnable mRefreshRunnable = new Runnable() {
        public final void run() {
            DTBAdRequest.this.refreshAd();
        }
    };
    private int refreshDuration = 0;
    private boolean requestHasBeenUsed = false;
    private final Map<String, String> sizeSlotUUIDMap = new HashMap();
    private boolean submitMetrics = true;

    /* access modifiers changed from: protected */
    public String getFullyQuaifiedMoPUBClassName() {
        return "com.mopub.common.MoPub";
    }

    public DTBAdRequest() {
        AdRegistration.register();
        this.context = AdRegistration.getContext();
        if (!mRaidDefined) {
            defineMraid();
        }
    }

    static void resetMraid() {
        mRaidArray = null;
        mRaidDefined = false;
    }

    static void setMRAIDSupportedVersions(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            mRaidCustomArray = new JSONArray();
            for (String put : strArr) {
                mRaidCustomArray.put(put);
            }
        }
    }

    /* access modifiers changed from: protected */
    public String[] dfpCandidateList() {
        return new String[]{"com.google.android.gms.common.GoogleApiAvailability", "com.google.android.gms.common.GoogleApiAvailabilityLight", "com.google.android.gms.common.GooglePlayServicesUtil", "com.google.android.gms.common.GooglePlayServicesUtilLight"};
    }

    /* access modifiers changed from: protected */
    public void defineMraid() {
        String[] dfpCandidateList;
        String stringFieldValue = DtbCommonUtils.getStringFieldValue(getFullyQuaifiedMoPUBClassName(), "SDK_VERSION");
        if (stringFieldValue != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("MOPUB VERSION:");
            sb.append(stringFieldValue);
            DtbLog.debug(sb.toString());
        } else {
            DtbLog.debug("MOPUB VERSION NOT FOUND");
        }
        APIVersion aPIVersion = DtbCommonUtils.getAPIVersion(stringFieldValue);
        Integer num = null;
        APIVersion aPIVersion2 = new APIVersion();
        for (String str : dfpCandidateList()) {
            if (num != null) {
                break;
            }
            num = DtbCommonUtils.getIntegerFieldValue(str, "GOOGLE_PLAY_SERVICES_VERSION_CODE");
        }
        if (num == null) {
            for (char c = 'a'; c <= 'z'; c = (char) (c + 1)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("com.google.android.gms.common.zz");
                sb2.append(c);
                num = DtbCommonUtils.getIntegerFieldValue(sb2.toString(), "GOOGLE_PLAY_SERVICES_VERSION_CODE");
                if (num != null) {
                    break;
                }
            }
        }
        if (num != null) {
            int intValue = num.intValue() / 1000;
            aPIVersion2.minorVersion = (intValue % 1000) / 100;
            aPIVersion2.majorVersion = intValue / 1000;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Google DFP major version:");
            sb3.append(aPIVersion2.majorVersion);
            sb3.append("minor version:");
            sb3.append(aPIVersion2.majorVersion);
            DtbLog.debug(sb3.toString());
        } else {
            DtbLog.debug("Not able to identify Google DFP version");
        }
        mRaidDefined = true;
        switch (AdRegistration.getMRAIDPolicy()) {
            case AUTO_DETECT:
                if (stringFieldValue != null) {
                    useMoPUB(aPIVersion);
                    return;
                }
                if (num != null) {
                    useDFP(aPIVersion2);
                }
                return;
            case DFP:
                if (num != null) {
                    useDFP(aPIVersion2);
                }
                return;
            case MOPUB:
                if (stringFieldValue != null) {
                    useMoPUB(aPIVersion);
                }
                return;
            case CUSTOM:
                mRaidArray = mRaidCustomArray;
                return;
            case NONE:
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void useMoPUB(APIVersion aPIVersion) {
        if (aPIVersion.majorVersion > 0) {
            mRaidArray = new JSONArray();
            mRaidArray.put("1.0");
            if ((aPIVersion.majorVersion >= 3 && aPIVersion.minorVersion >= 3) || aPIVersion.majorVersion > 3) {
                mRaidArray.put("2.0");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void useDFP(APIVersion aPIVersion) {
        if (aPIVersion.majorVersion > 0) {
            mRaidArray = new JSONArray();
            mRaidArray.put("1.0");
            if ((aPIVersion.majorVersion == 7 && aPIVersion.minorVersion >= 8) || aPIVersion.majorVersion > 7) {
                mRaidArray.put("2.0");
            }
            if (aPIVersion.majorVersion >= 15) {
                mRaidArray.put("3.0");
            }
        }
    }

    public void setSizes(DTBAdSize... dTBAdSizeArr) throws IllegalArgumentException {
        this.adSizes.clear();
        String str = this.LOG_TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Setting ");
        sb.append(dTBAdSizeArr.length);
        sb.append(" AdSize(s) to the ad request.");
        DtbLog.info(str, sb.toString());
        int length = dTBAdSizeArr.length;
        int i = 0;
        while (i < length) {
            DTBAdSize dTBAdSize = dTBAdSizeArr[i];
            if (dTBAdSize != null) {
                this.adSizes.add(dTBAdSize);
                i++;
            } else {
                throw new IllegalArgumentException("DTBAdSize cannot be null.");
            }
        }
    }

    public void loadAd(DTBAdCallback dTBAdCallback) {
        this.callback = dTBAdCallback;
        if (this.adSizes.size() <= 0) {
            throw new IllegalArgumentException("Please set atleast one ad size in the request.");
        } else if (this.requestHasBeenUsed) {
            DtbLog.error(this.LOG_TAG, "This ad request object is already used for loading an ad. Please create a new instance to load the Ad.");
        } else {
            this.requestHasBeenUsed = true;
            DtbDeviceRegistration.verifyRegistration();
            for (DTBAdSize dTBAdSize : this.adSizes) {
                Map<String, String> map = this.sizeSlotUUIDMap;
                StringBuilder sb = new StringBuilder();
                sb.append(dTBAdSize.getWidth());
                sb.append(AvidJSONUtil.KEY_X);
                sb.append(dTBAdSize.getHeight());
                map.put(sb.toString(), dTBAdSize.getSlotUUID());
            }
            try {
                if (this.mHandlerThread == null && this.isAutoRefresh && this.refreshDuration > 0) {
                    this.mHandlerThread = new HandlerThread("DtbHandlerThread");
                    this.mHandlerThread.start();
                    this.mHandler = new Handler(this.mHandlerThread.getLooper());
                }
                internalLoadAd();
            } catch (Exception unused) {
                DtbLog.error(this.LOG_TAG, "Unknown exception occured in DTB ad call.");
            }
        }
    }

    public void stop() {
        stopAutoRefresh();
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            DtbLog.debug("Stopping DTB auto refresh");
        }
    }

    private void internalLoadAd() {
        DtbLog.debug("Loading DTB ad.");
        DtbThreadService.getInstance().execute(new Runnable() {
            public final void run() {
                DTBAdRequest.lambda$internalLoadAd$1(DTBAdRequest.this);
            }
        });
        DtbLog.debug("Dispatched the loadAd task on a background thread.");
    }

    public static /* synthetic */ void lambda$internalLoadAd$1(DTBAdRequest dTBAdRequest) {
        DtbLog.info("Fetching DTB ad.");
        try {
            dTBAdRequest.loadAdRequest();
            DtbLog.debug("DTB Ad call is complete");
        } catch (Exception unused) {
            DtbLog.error(dTBAdRequest.LOG_TAG, "Unknown exception in DTB ad call process.");
        }
    }

    private void stopAutoRefresh() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /* access modifiers changed from: private */
    public void refreshAd() {
        if (this.isAutoRefresh && this.refreshDuration > 0) {
            Activity activity = null;
            Context context2 = this.context;
            if (context2 instanceof Activity) {
                activity = (Activity) context2;
                if (activity.isFinishing() || DtbCommonUtils.isActivityDestroyed(activity)) {
                    DtbLog.info("Stopping DTB auto refresh...");
                    stop();
                    return;
                }
            }
            if (activity == null || activity.hasWindowFocus()) {
                internalLoadAd();
            } else {
                DtbLog.debug("Skipping DTB auto refresh...activity not in focus");
                scheduleAdRefreshIfEnabled();
            }
        }
    }

    private void scheduleAdRefreshIfEnabled() {
        if (this.isAutoRefresh && this.refreshDuration > 0) {
            stopAutoRefresh();
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.postDelayed(this.mRefreshRunnable, ((long) this.refreshDuration) * 1000);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addGDPRParameters(Map<String, Object> map) {
        Context context2 = this.context;
        if (context2 != null) {
            addGDPRParametersFromPreferences(map, PreferenceManager.getDefaultSharedPreferences(context2));
        }
    }

    /* access modifiers changed from: protected */
    public void addGDPRParametersFromPreferences(Map<String, Object> map, SharedPreferences sharedPreferences) {
        JSONObject jSONObject = null;
        String string = sharedPreferences.getString("IABConsent_SubjectToGDPR", null);
        String string2 = sharedPreferences.getString("IABConsent_ConsentString", null);
        if (string != null) {
            try {
                jSONObject = new JSONObject();
                jSONObject.put(e.a, string);
            } catch (JSONException unused) {
                DtbLog.error("INVALID JSON formed for GDPR clause");
                return;
            }
        }
        if (string2 != null) {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            jSONObject.put("c", string2);
        }
        if (jSONObject != null) {
            map.put("gdpr", jSONObject);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0259, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x025a, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append("Internal error occurred in ad call: ");
        r2.append(r1.getMessage());
        com.amazon.device.ads.DtbLog.debug(r2.toString());
        r9.adError = new com.amazon.device.ads.AdError(com.amazon.device.ads.AdError.ErrorCode.INTERNAL_ERROR, "Internal error occurred in ad call.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x027e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x027f, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append("Malformed response from ad call: ");
        r2.append(r1.getMessage());
        com.amazon.device.ads.DtbLog.debug(r2.toString());
        r9.adError = new com.amazon.device.ads.AdError(com.amazon.device.ads.AdError.ErrorCode.INTERNAL_ERROR, "Malformed response from ad call.");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0259 A[ExcHandler: Exception (r1v9 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:6:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x02a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadAdRequest() {
        /*
            r9 = this;
            com.amazon.device.ads.DtbMetrics r0 = new com.amazon.device.ads.DtbMetrics
            r0.<init>()
            com.amazon.device.ads.DtbAdvertisingInfo r1 = new com.amazon.device.ads.DtbAdvertisingInfo
            r1.<init>()
            com.amazon.device.ads.DtbAdRequestParamsBuilder r1 = new com.amazon.device.ads.DtbAdRequestParamsBuilder
            r1.<init>()
            android.content.Context r2 = r9.context
            java.util.List<com.amazon.device.ads.DTBAdSize> r3 = r9.adSizes
            java.util.Map<java.lang.String, java.lang.String> r4 = r9.customTargets
            java.util.HashMap r1 = r1.getParams(r2, r3, r4)
            r9.addGDPRParameters(r1)
            r9.addMraidParameters(r1)
            com.amazon.device.ads.DtbSharedPreferences r2 = com.amazon.device.ads.DtbSharedPreferences.getInstance()
            java.lang.String r2 = r2.getAaxHostname()
            java.lang.String r2 = com.amazon.device.ads.DtbDebugProperties.getAaxHostName(r2)
            java.util.List<com.amazon.device.ads.DTBAdSize> r3 = r9.adSizes
            java.util.Iterator r3 = r3.iterator()
        L_0x0031:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0056
            java.lang.Object r4 = r3.next()
            com.amazon.device.ads.DTBAdSize r4 = (com.amazon.device.ads.DTBAdSize) r4
            com.amazon.device.ads.AdType r5 = com.amazon.device.ads.AdType.VIDEO
            com.amazon.device.ads.AdType r4 = r4.getDTBAdType()
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0031
            com.amazon.device.ads.DtbSharedPreferences r2 = com.amazon.device.ads.DtbSharedPreferences.getInstance()
            java.lang.String r2 = r2.getRoute53EnabledCNAME()
            java.lang.String r2 = com.amazon.device.ads.DtbDebugProperties.getRoute53EnabledCNAME(r2)
            goto L_0x0031
        L_0x0056:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r4.<init>()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r4.append(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r2 = "/e/msdk/ads"
            r4.append(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r2 = r4.toString()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r3.<init>(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r2 = com.amazon.device.ads.DtbDebugProperties.getEncodedUrlParams()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            int r2 = r2.length()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r2 <= 0) goto L_0x0082
            r2 = 63
            r3.append(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r2 = com.amazon.device.ads.DtbDebugProperties.getEncodedUrlParams()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r3.append(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
        L_0x0082:
            com.amazon.device.ads.DtbHttpClient r2 = new com.amazon.device.ads.DtbHttpClient     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r2.<init>(r3)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r3 = 1
            boolean r4 = com.amazon.device.ads.DtbDebugProperties.getIsSecure(r3)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r2.setUseSecure(r4)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r4 = "Accept"
            java.lang.String r5 = "application/json"
            r2.addHeader(r4, r5)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r4 = "Content-Type"
            java.lang.String r5 = "application/json"
            r2.addHeader(r4, r5)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r2.setParams(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.DtbMetric r1 = com.amazon.device.ads.DtbMetric.AAX_BID_TIME     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r0.startTimer(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r2.executePOST()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r1 = "Ad call completed."
            com.amazon.device.ads.DtbLog.debug(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r1 = r2.getResponse()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            boolean r1 = com.amazon.device.ads.DtbCommonUtils.isNullOrEmpty(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r1 != 0) goto L_0x0241
            com.amazon.device.ads.DtbMetric r1 = com.amazon.device.ads.DtbMetric.AAX_BID_TIME     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r0.stopTimer(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            org.json.JSONTokener r1 = new org.json.JSONTokener     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r4 = r2.getResponse()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r1.<init>(r4)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.Object r1 = r1.nextValue()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r1 == 0) goto L_0x00e5
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r4.<init>()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r5 = "Bid Response:"
            r4.append(r5)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r4.append(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.DtbLog.debug(r4)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
        L_0x00e5:
            if (r1 == 0) goto L_0x022b
            int r2 = r2.getResponseCode()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r2 != r4) goto L_0x022b
            java.lang.String r2 = "instrPixelURL"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r2 == 0) goto L_0x0100
            java.lang.String r2 = "instrPixelURL"
            java.lang.String r2 = r1.getString(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r0.setInstPxlUrl(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
        L_0x0100:
            java.lang.String r2 = "errorCode"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r2 == 0) goto L_0x01ee
            java.lang.String r2 = "errorCode"
            java.lang.String r2 = r1.getString(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r4 = "200"
            boolean r2 = r2.equals(r4)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r2 == 0) goto L_0x01ee
            java.lang.String r2 = "ads"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r2 == 0) goto L_0x01ee
            java.lang.String r2 = "ads"
            org.json.JSONObject r1 = r1.getJSONObject(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.DTBAdResponse r2 = new com.amazon.device.ads.DTBAdResponse     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r2.<init>()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r9.adResponse = r2     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            int r2 = r1.length()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r2 <= 0) goto L_0x01d7
            java.util.Iterator r2 = r1.keys()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
        L_0x0135:
            boolean r4 = r2.hasNext()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r4 == 0) goto L_0x01c5
            java.lang.Object r4 = r2.next()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            org.json.JSONObject r5 = r1.getJSONObject(r4)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.DTBAdResponse r6 = r9.adResponse     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r7 = "b"
            java.lang.String r7 = r5.getString(r7)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r6.setBidId(r7)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r6 = "v"
            boolean r6 = r5.has(r6)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r6 == 0) goto L_0x0167
            java.lang.String r6 = "v"
            boolean r6 = r5.getBoolean(r6)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r6 == 0) goto L_0x0167
            com.amazon.device.ads.DTBAdResponse r6 = r9.adResponse     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r6.setVideo(r3)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
        L_0x0167:
            java.lang.String r6 = "kvp"
            boolean r6 = r5.has(r6)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r6 == 0) goto L_0x0194
            com.amazon.device.ads.DTBAdResponse r6 = r9.adResponse     // Catch:{ JSONException -> 0x017b, Exception -> 0x0259 }
            java.lang.String r7 = "kvp"
            org.json.JSONObject r7 = r5.getJSONObject(r7)     // Catch:{ JSONException -> 0x017b, Exception -> 0x0259 }
            r6.setKvpDictionary(r7)     // Catch:{ JSONException -> 0x017b, Exception -> 0x0259 }
            goto L_0x0194
        L_0x017b:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r7.<init>()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r8 = "Malformed kvp value from ad response: "
            r7.append(r8)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r6 = r6.getMessage()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r7.append(r6)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r6 = r7.toString()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.DtbLog.debug(r6)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
        L_0x0194:
            java.lang.String r6 = "sz"
            java.lang.String r5 = r5.getString(r6)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdType r6 = com.amazon.device.ads.AdType.DISPLAY     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r7 = "9999x9999"
            boolean r7 = r7.equals(r5)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r7 == 0) goto L_0x01a7
            com.amazon.device.ads.AdType r6 = com.amazon.device.ads.AdType.INTERSTITIAL     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            goto L_0x01b1
        L_0x01a7:
            com.amazon.device.ads.DTBAdResponse r7 = r9.adResponse     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            boolean r7 = r7.isVideo()     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r7 == 0) goto L_0x01b1
            com.amazon.device.ads.AdType r6 = com.amazon.device.ads.AdType.VIDEO     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
        L_0x01b1:
            com.amazon.device.ads.DtbPricePoint r7 = new com.amazon.device.ads.DtbPricePoint     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.util.Map<java.lang.String, java.lang.String> r8 = r9.sizeSlotUUIDMap     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.Object r8 = r8.get(r5)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r7.<init>(r4, r5, r8, r6)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.DTBAdResponse r4 = r9.adResponse     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r4.putPricePoint(r7)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            goto L_0x0135
        L_0x01c5:
            com.amazon.device.ads.AdError r1 = new com.amazon.device.ads.AdError     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError$ErrorCode r2 = com.amazon.device.ads.AdError.ErrorCode.NO_ERROR     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r3 = "Ad loaded successfully."
            r1.<init>(r2, r3)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r9.adError = r1     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r1 = "Ad call response successfully proccessed."
            com.amazon.device.ads.DtbLog.debug(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            goto L_0x02a2
        L_0x01d7:
            java.lang.String r1 = "No pricepoint returned from ad server"
            com.amazon.device.ads.DtbLog.debug(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.DtbMetric r1 = com.amazon.device.ads.DtbMetric.AAX_PUNTED     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r0.incrementMetric(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError r1 = new com.amazon.device.ads.AdError     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError$ErrorCode r2 = com.amazon.device.ads.AdError.ErrorCode.NO_FILL     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r3 = "No pricepoint returned from ad server."
            r1.<init>(r2, r3)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r9.adError = r1     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            goto L_0x02a2
        L_0x01ee:
            java.lang.String r2 = "errorCode"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r2 == 0) goto L_0x0215
            java.lang.String r2 = "errorCode"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r2 = "400"
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            if (r1 == 0) goto L_0x0215
            java.lang.String r1 = "Ad Server punted due to invalid request."
            com.amazon.device.ads.DtbLog.debug(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError r1 = new com.amazon.device.ads.AdError     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError$ErrorCode r2 = com.amazon.device.ads.AdError.ErrorCode.REQUEST_ERROR     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r3 = "Invalid request passed to AdServer."
            r1.<init>(r2, r3)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r9.adError = r1     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            goto L_0x0225
        L_0x0215:
            java.lang.String r1 = "No ad returned from ad server"
            com.amazon.device.ads.DtbLog.debug(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError r1 = new com.amazon.device.ads.AdError     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError$ErrorCode r2 = com.amazon.device.ads.AdError.ErrorCode.NO_FILL     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r3 = "No Ad returned by AdServer."
            r1.<init>(r2, r3)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r9.adError = r1     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
        L_0x0225:
            com.amazon.device.ads.DtbMetric r1 = com.amazon.device.ads.DtbMetric.AAX_PUNTED     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r0.incrementMetric(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            goto L_0x02a2
        L_0x022b:
            java.lang.String r1 = "Ad call did not complete successfully."
            com.amazon.device.ads.DtbLog.debug(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError r1 = new com.amazon.device.ads.AdError     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError$ErrorCode r2 = com.amazon.device.ads.AdError.ErrorCode.NETWORK_ERROR     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r3 = "Ad call did not complete successfully."
            r1.<init>(r2, r3)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r9.adError = r1     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.DtbMetric r1 = com.amazon.device.ads.DtbMetric.AAX_NETWORK_FAILURE     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r0.incrementMetric(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            goto L_0x02a2
        L_0x0241:
            java.lang.String r1 = "No response from Ad call."
            com.amazon.device.ads.DtbLog.debug(r1)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError r1 = new com.amazon.device.ads.AdError     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            com.amazon.device.ads.AdError$ErrorCode r2 = com.amazon.device.ads.AdError.ErrorCode.INTERNAL_ERROR     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r3 = "Response is null."
            r1.<init>(r2, r3)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            r9.adError = r1     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.Exception r1 = new java.lang.Exception     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            java.lang.String r2 = "Response is null"
            r1.<init>(r2)     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
            throw r1     // Catch:{ JSONException -> 0x027e, Exception -> 0x0259 }
        L_0x0259:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Internal error occurred in ad call: "
            r2.append(r3)
            java.lang.String r1 = r1.getMessage()
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.amazon.device.ads.DtbLog.debug(r1)
            com.amazon.device.ads.AdError r1 = new com.amazon.device.ads.AdError
            com.amazon.device.ads.AdError$ErrorCode r2 = com.amazon.device.ads.AdError.ErrorCode.INTERNAL_ERROR
            java.lang.String r3 = "Internal error occurred in ad call."
            r1.<init>(r2, r3)
            r9.adError = r1
            goto L_0x02a2
        L_0x027e:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Malformed response from ad call: "
            r2.append(r3)
            java.lang.String r1 = r1.getMessage()
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.amazon.device.ads.DtbLog.debug(r1)
            com.amazon.device.ads.AdError r1 = new com.amazon.device.ads.AdError
            com.amazon.device.ads.AdError$ErrorCode r2 = com.amazon.device.ads.AdError.ErrorCode.INTERNAL_ERROR
            java.lang.String r3 = "Malformed response from ad call."
            r1.<init>(r2, r3)
            r9.adError = r1
        L_0x02a2:
            com.amazon.device.ads.AdError r1 = r9.adError
            if (r1 != 0) goto L_0x02ab
            java.lang.String r1 = "UNEXPECTED ERROR in ad call !!"
            com.amazon.device.ads.DtbLog.debug(r1)
        L_0x02ab:
            r9.triggerCallBack(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.ads.DTBAdRequest.loadAdRequest():void");
    }

    private void triggerCallBack(DtbMetrics dtbMetrics) {
        scheduleAdRefreshIfEnabled();
        DtbLog.info(this.LOG_TAG, "Forwarding the error handling to view on main thread.");
        DtbThreadService.executeOnMainThread(new Runnable() {
            public final void run() {
                DTBAdRequest.lambda$triggerCallBack$2(DTBAdRequest.this);
            }
        });
        if (this.submitMetrics) {
            Submitter.INSTANCE.submitMetrics(dtbMetrics);
        }
    }

    public static /* synthetic */ void lambda$triggerCallBack$2(DTBAdRequest dTBAdRequest) {
        if (dTBAdRequest.callback == null) {
            DtbLog.error("No callback -DTBAdCallback- provided to loadAd() to handle success or failure.");
        } else if (dTBAdRequest.adError == null || dTBAdRequest.adError.getCode() != ErrorCode.NO_ERROR) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invoking onFailure() callback with errorCode: ");
            sb.append(dTBAdRequest.adError.getCode());
            sb.append("[");
            sb.append(dTBAdRequest.adError.getMessage());
            sb.append("]");
            DtbLog.debug(sb.toString());
            dTBAdRequest.callback.onFailure(dTBAdRequest.adError);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Invoking onSuccess() callback for pricepoints: [");
            sb2.append(dTBAdRequest.adResponse.getDefaultPricePoints());
            sb2.append("]");
            DtbLog.debug(sb2.toString());
            dTBAdRequest.callback.onSuccess(dTBAdRequest.adResponse);
        }
    }

    /* access modifiers changed from: protected */
    public void addMraidParameters(Map<String, Object> map) {
        JSONArray jSONArray = mRaidArray;
        if (jSONArray != null && jSONArray.length() > 0) {
            map.put(AdType.MRAID, mRaidArray);
        }
    }
}

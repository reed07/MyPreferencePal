package com.amazon.device.ads;

import com.brightcove.player.event.AbstractEvent;
import com.facebook.appevents.AppEventsConstants;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONTokener;

class DtbDeviceRegistration {
    private static final String LOG_TAG = "com.amazon.device.ads.DtbDeviceRegistration";
    private static String amznAdId;
    private static String appId;
    private static DtbDeviceRegistration dtbDeviceRegistrationInstance;
    private final DtbMetrics metrics = new DtbMetrics();

    public static void verifyRegistration() {
        if (DtbCommonUtils.isNullOrEmpty(DtbSharedPreferences.getInstance().getAdId())) {
            if (dtbDeviceRegistrationInstance == null) {
                dtbDeviceRegistrationInstance = new DtbDeviceRegistration();
            }
            DtbThreadService.getInstance().execute($$Lambda$DtbDeviceRegistration$EkGC29OSMK3P7G5K9dq1OtmHY.INSTANCE);
        }
    }

    private DtbDeviceRegistration() {
        DtbLog.debug("Running the initialization in background thread.");
        initializeAds();
    }

    /* access modifiers changed from: private */
    public synchronized void retryInitializeAds() {
        if (DtbCommonUtils.isNullOrEmpty(DtbSharedPreferences.getInstance().getAdId())) {
            initializeAds();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x0111 A[Catch:{ JSONException -> 0x0214, Exception -> 0x01da }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0115 A[Catch:{ JSONException -> 0x0214, Exception -> 0x01da }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x012f A[Catch:{ JSONException -> 0x0214, Exception -> 0x01da }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01cb A[Catch:{ JSONException -> 0x0214, Exception -> 0x01da }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void initializeAds() {
        /*
            r12 = this;
            monitor-enter(r12)
            android.content.Context r0 = com.amazon.device.ads.AdRegistration.getContext()     // Catch:{ all -> 0x023e }
            java.lang.String r1 = "connectivity"
            java.lang.Object r0 = r0.getSystemService(r1)     // Catch:{ all -> 0x023e }
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0     // Catch:{ all -> 0x023e }
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()     // Catch:{ all -> 0x023e }
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x001d
            boolean r0 = r0.isConnectedOrConnecting()     // Catch:{ all -> 0x023e }
            if (r0 == 0) goto L_0x001d
            r0 = 1
            goto L_0x001e
        L_0x001d:
            r0 = 0
        L_0x001e:
            if (r0 != 0) goto L_0x0027
            java.lang.String r0 = "Network is not available"
            com.amazon.device.ads.DtbLog.debug(r0)     // Catch:{ all -> 0x023e }
            monitor-exit(r12)
            return
        L_0x0027:
            com.amazon.device.ads.DtbSharedPreferences r0 = com.amazon.device.ads.DtbSharedPreferences.getInstance()     // Catch:{ all -> 0x023e }
            java.lang.String r0 = r0.getAdId()     // Catch:{ all -> 0x023e }
            amznAdId = r0     // Catch:{ all -> 0x023e }
            com.amazon.device.ads.DtbSharedPreferences r0 = com.amazon.device.ads.DtbSharedPreferences.getInstance()     // Catch:{ all -> 0x023e }
            java.lang.String r0 = r0.getAppKey()     // Catch:{ all -> 0x023e }
            appId = r0     // Catch:{ all -> 0x023e }
            boolean r0 = com.amazon.device.ads.DtbCommonUtils.isOnMainThread()     // Catch:{ all -> 0x023e }
            if (r0 == 0) goto L_0x004a
            java.lang.String r0 = LOG_TAG     // Catch:{ all -> 0x023e }
            java.lang.String r1 = "Unable to fetch advertising indentifier information on main thread."
            com.amazon.device.ads.DtbLog.error(r0, r1)     // Catch:{ all -> 0x023e }
            monitor-exit(r12)
            return
        L_0x004a:
            com.amazon.device.ads.DtbAdvertisingInfo r0 = new com.amazon.device.ads.DtbAdvertisingInfo     // Catch:{ all -> 0x023e }
            r0.<init>()     // Catch:{ all -> 0x023e }
            com.amazon.device.ads.DtbDebugProperties.getInstance()     // Catch:{ all -> 0x023e }
            java.lang.String r0 = appId     // Catch:{ all -> 0x023e }
            boolean r0 = r12.registerConfig(r0)     // Catch:{ all -> 0x023e }
            com.amazon.device.ads.DtbSharedPreferences r3 = com.amazon.device.ads.DtbSharedPreferences.getInstance()     // Catch:{ all -> 0x023e }
            java.lang.String r3 = r3.getSisEndpoint()     // Catch:{ all -> 0x023e }
            java.lang.String r4 = "null"
            boolean r4 = r3.startsWith(r4)     // Catch:{ all -> 0x023e }
            if (r4 == 0) goto L_0x006f
            java.lang.String r0 = "SIS is not ready"
            com.amazon.device.ads.DtbLog.debug(r0)     // Catch:{ all -> 0x023e }
            monitor-exit(r12)
            return
        L_0x006f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x023e }
            java.lang.String r5 = com.amazon.device.ads.DtbDebugProperties.getSISUrl(r3)     // Catch:{ all -> 0x023e }
            r4.<init>(r5)     // Catch:{ all -> 0x023e }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x023e }
            com.amazon.device.ads.DtbSharedPreferences r7 = com.amazon.device.ads.DtbSharedPreferences.getInstance()     // Catch:{ all -> 0x023e }
            java.lang.Long r7 = r7.getSisLastCheckIn()     // Catch:{ all -> 0x023e }
            long r7 = r7.longValue()     // Catch:{ all -> 0x023e }
            com.amazon.device.ads.DtbSharedPreferences r9 = com.amazon.device.ads.DtbSharedPreferences.getInstance()     // Catch:{ all -> 0x023e }
            boolean r9 = r9.getIsAdIdChanged()     // Catch:{ all -> 0x023e }
            com.amazon.device.ads.DtbSharedPreferences r10 = com.amazon.device.ads.DtbSharedPreferences.getInstance()     // Catch:{ all -> 0x023e }
            boolean r10 = r10.getIsAdIdNew()     // Catch:{ all -> 0x023e }
            com.amazon.device.ads.DtbSharedPreferences r11 = com.amazon.device.ads.DtbSharedPreferences.getInstance()     // Catch:{ all -> 0x023e }
            r11.saveIsAdIdChanged(r2)     // Catch:{ all -> 0x023e }
            com.amazon.device.ads.DtbSharedPreferences r11 = com.amazon.device.ads.DtbSharedPreferences.getInstance()     // Catch:{ all -> 0x023e }
            r11.saveIsAdIdNew(r2)     // Catch:{ all -> 0x023e }
            if (r9 != 0) goto L_0x00e6
            java.lang.String r9 = amznAdId     // Catch:{ all -> 0x023e }
            if (r9 == 0) goto L_0x00e6
            if (r0 == 0) goto L_0x00af
            goto L_0x00e6
        L_0x00af:
            long r5 = r5 - r7
            r7 = 86400000(0x5265c00, double:4.2687272E-316)
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x00d8
            if (r10 == 0) goto L_0x00ba
            goto L_0x00d8
        L_0x00ba:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x023e }
            r0.<init>()     // Catch:{ all -> 0x023e }
            java.lang.String r1 = "SIS call not required, last registration duration:"
            r0.append(r1)     // Catch:{ all -> 0x023e }
            r0.append(r5)     // Catch:{ all -> 0x023e }
            java.lang.String r1 = ", expiration:"
            r0.append(r1)     // Catch:{ all -> 0x023e }
            r0.append(r7)     // Catch:{ all -> 0x023e }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x023e }
            com.amazon.device.ads.DtbLog.debug(r0)     // Catch:{ all -> 0x023e }
            monitor-exit(r12)
            return
        L_0x00d8:
            java.lang.String r0 = LOG_TAG     // Catch:{ all -> 0x023e }
            java.lang.String r2 = "Trying to update ad id.."
            com.amazon.device.ads.DtbLog.info(r0, r2)     // Catch:{ all -> 0x023e }
            java.lang.String r0 = "/update_dev_info"
            r4.append(r0)     // Catch:{ all -> 0x023e }
            r2 = 1
            goto L_0x00f2
        L_0x00e6:
            java.lang.String r0 = LOG_TAG     // Catch:{ all -> 0x023e }
            java.lang.String r5 = "Trying to register ad id.."
            com.amazon.device.ads.DtbLog.info(r0, r5)     // Catch:{ all -> 0x023e }
            java.lang.String r0 = "/generate_did"
            r4.append(r0)     // Catch:{ all -> 0x023e }
        L_0x00f2:
            java.lang.String r0 = appId     // Catch:{ all -> 0x023e }
            java.util.HashMap r0 = r12.buildSISParams(r0)     // Catch:{ all -> 0x023e }
            r5 = 0
            com.amazon.device.ads.DtbHttpClient r6 = new com.amazon.device.ads.DtbHttpClient     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r6.<init>(r4)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            boolean r4 = com.amazon.device.ads.DtbDebugProperties.getIsSecure(r1)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r6.setUseSecure(r4)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r6.setParams(r0)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r6.enableQueryParams()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            if (r2 == 0) goto L_0x0115
            com.amazon.device.ads.DtbMetric r0 = com.amazon.device.ads.DtbMetric.SIS_LATENCY_UPDATE_DEVICE_INFO     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r5 = r0
            goto L_0x0118
        L_0x0115:
            com.amazon.device.ads.DtbMetric r0 = com.amazon.device.ads.DtbMetric.SIS_LATENCY_REGISTER_EVENT     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r5 = r0
        L_0x0118:
            com.amazon.device.ads.DtbMetrics r0 = r12.metrics     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r0.startTimer(r5)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r6.executePOST()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            com.amazon.device.ads.DtbMetrics r0 = r12.metrics     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r0.stopTimer(r5)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r0 = r6.getResponse()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            boolean r0 = com.amazon.device.ads.DtbCommonUtils.isNullOrEmpty(r0)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            if (r0 != 0) goto L_0x01cb
            org.json.JSONTokener r0 = new org.json.JSONTokener     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r2 = r6.getResponse()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r0.<init>(r2)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.Object r0 = r0.nextValue()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r2 = "rcode"
            boolean r2 = r0.has(r2)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            if (r2 == 0) goto L_0x01a9
            java.lang.String r2 = "rcode"
            int r2 = r0.getInt(r2)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            if (r2 != r1) goto L_0x01a9
            java.lang.String r1 = "msg"
            boolean r1 = r0.has(r1)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            if (r1 == 0) goto L_0x01a9
            java.lang.String r1 = "adId"
            boolean r1 = r0.has(r1)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            if (r1 == 0) goto L_0x01a9
            java.lang.String r1 = "adId"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r2 = "idChanged"
            boolean r2 = r0.has(r2)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            if (r2 == 0) goto L_0x0182
            java.lang.String r2 = "idChanged"
            boolean r0 = r0.getBoolean(r2)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            if (r0 == 0) goto L_0x0182
            java.lang.String r0 = LOG_TAG     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r2 = "ad id has changed, updating.."
            com.amazon.device.ads.DtbLog.info(r0, r2)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            com.amazon.device.ads.DtbMetrics r0 = r12.metrics     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            com.amazon.device.ads.DtbMetric r2 = com.amazon.device.ads.DtbMetric.SIS_COUNTER_IDENTIFIED_DEVICE_CHANGED     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r0.incrementMetric(r2)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
        L_0x0182:
            com.amazon.device.ads.DtbSharedPreferences r0 = com.amazon.device.ads.DtbSharedPreferences.getInstance()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r0.saveAdId(r1)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            com.amazon.device.ads.DtbSharedPreferences r0 = com.amazon.device.ads.DtbSharedPreferences.getInstance()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r0.saveSisLastCheckIn(r1)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r0 = LOG_TAG     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r1 = "ad id is registered or updated successfully."
            com.amazon.device.ads.DtbLog.info(r0, r1)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            if (r5 == 0) goto L_0x01a2
            com.amazon.device.ads.DtbMetrics r0 = r12.metrics     // Catch:{ all -> 0x023e }
            r0.resetMetric(r5)     // Catch:{ all -> 0x023e }
        L_0x01a2:
            java.lang.String r0 = appId     // Catch:{ all -> 0x023e }
            r12.pingSis(r3, r0)     // Catch:{ all -> 0x023e }
            monitor-exit(r12)
            return
        L_0x01a9:
            java.lang.String r1 = LOG_TAG     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r2.<init>()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r3 = "ad id failed registration: "
            r2.append(r3)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            r2.append(r0)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r0 = r2.toString()     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            com.amazon.device.ads.DtbLog.info(r1, r0)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r1 = "ad id failed registration: "
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            throw r0     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
        L_0x01cb:
            java.lang.String r0 = "No response from sis call."
            com.amazon.device.ads.DtbLog.debug(r0)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            java.lang.String r1 = "SIS Response is null"
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
            throw r0     // Catch:{ JSONException -> 0x0214, Exception -> 0x01da }
        L_0x01d8:
            r0 = move-exception
            goto L_0x0236
        L_0x01da:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d8 }
            r1.<init>()     // Catch:{ all -> 0x01d8 }
            java.lang.String r2 = "Exception :"
            r1.append(r2)     // Catch:{ all -> 0x01d8 }
            java.lang.String r2 = com.amazon.device.ads.DtbCommonUtils.exceptionStackToString(r0)     // Catch:{ all -> 0x01d8 }
            r1.append(r2)     // Catch:{ all -> 0x01d8 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x01d8 }
            com.amazon.device.ads.DtbLog.error(r1)     // Catch:{ all -> 0x01d8 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d8 }
            r1.<init>()     // Catch:{ all -> 0x01d8 }
            java.lang.String r2 = "Error registering device for ads:"
            r1.append(r2)     // Catch:{ all -> 0x01d8 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01d8 }
            r1.append(r0)     // Catch:{ all -> 0x01d8 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x01d8 }
            com.amazon.device.ads.DtbLog.error(r0)     // Catch:{ all -> 0x01d8 }
            if (r5 == 0) goto L_0x0212
            com.amazon.device.ads.DtbMetrics r0 = r12.metrics     // Catch:{ all -> 0x023e }
            r0.resetMetric(r5)     // Catch:{ all -> 0x023e }
        L_0x0212:
            monitor-exit(r12)
            return
        L_0x0214:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d8 }
            r1.<init>()     // Catch:{ all -> 0x01d8 }
            java.lang.String r2 = "JSON error parsing return from SIS: "
            r1.append(r2)     // Catch:{ all -> 0x01d8 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x01d8 }
            r1.append(r0)     // Catch:{ all -> 0x01d8 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x01d8 }
            com.amazon.device.ads.DtbLog.error(r0)     // Catch:{ all -> 0x01d8 }
            if (r5 == 0) goto L_0x0234
            com.amazon.device.ads.DtbMetrics r0 = r12.metrics     // Catch:{ all -> 0x023e }
            r0.resetMetric(r5)     // Catch:{ all -> 0x023e }
        L_0x0234:
            monitor-exit(r12)
            return
        L_0x0236:
            if (r5 == 0) goto L_0x023d
            com.amazon.device.ads.DtbMetrics r1 = r12.metrics     // Catch:{ all -> 0x023e }
            r1.resetMetric(r5)     // Catch:{ all -> 0x023e }
        L_0x023d:
            throw r0     // Catch:{ all -> 0x023e }
        L_0x023e:
            r0 = move-exception
            monitor-exit(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.ads.DtbDeviceRegistration.initializeAds():void");
    }

    private void pingSis(String str, String str2) {
        if (System.currentTimeMillis() - DtbSharedPreferences.getInstance().getSisLastPing() >= 2592000000L) {
            String adId = DtbSharedPreferences.getInstance().getAdId();
            if (adId == null || adId.isEmpty()) {
                DtbLog.info("error retrieving ad id, cancelling sis ping");
                return;
            }
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("/ping");
                DtbHttpClient dtbHttpClient = new DtbHttpClient(sb.toString());
                dtbHttpClient.setUseSecure(DtbDebugProperties.getIsSecure(true));
                HashMap hashMap = new HashMap();
                hashMap.put("appId", str2);
                hashMap.put(AbstractEvent.AD_ID, adId);
                dtbHttpClient.setParams(hashMap);
                dtbHttpClient.executeGET();
                if (!DtbCommonUtils.isNullOrEmpty(dtbHttpClient.getResponse())) {
                    JSONObject jSONObject = (JSONObject) new JSONTokener(dtbHttpClient.getResponse()).nextValue();
                    if (!jSONObject.has("rcode") || jSONObject.getInt("rcode") != 1) {
                        String str3 = LOG_TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("sis ping failed failed registration: ");
                        sb2.append(jSONObject.toString());
                        DtbLog.info(str3, sb2.toString());
                        throw new Exception("sis ping failed registration: ");
                    }
                    DtbSharedPreferences.getInstance().saveSisLastPing(System.currentTimeMillis());
                    DtbLog.info(LOG_TAG, "ad id is registered or updated successfully.");
                    return;
                }
                DtbLog.debug("No response from sis ping.");
                throw new Exception("PingSIS Response is null");
            } catch (Exception e) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Error pinging sis: ");
                sb3.append(e.toString());
                DtbLog.error(sb3.toString());
            }
        }
    }

    private boolean registerConfig(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        long longValue = currentTimeMillis - DtbSharedPreferences.getInstance().getConfigLastCheckIn().longValue();
        long configTtl = DtbSharedPreferences.getInstance().getConfigTtl();
        StringBuilder sb = new StringBuilder();
        sb.append("Config last checkin duration: ");
        sb.append(longValue);
        sb.append(", Expiration: ");
        sb.append(configTtl);
        DtbLog.debug(sb.toString());
        boolean z = false;
        if (longValue <= 172800000) {
            DtbLog.debug("No config refresh required");
            return false;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(DtbDebugProperties.getConfigHostName("mads.amazon-adsystem.com"));
        sb2.append("/msdk/getConfig");
        DtbHttpClient dtbHttpClient = new DtbHttpClient(sb2.toString());
        dtbHttpClient.addHeader("Accept", HttpConstants.CONTENT_TYPE_JSON);
        dtbHttpClient.setUseSecure(DtbDebugProperties.getIsSecure(true));
        dtbHttpClient.setParams(buildConfigInfoParams(str));
        try {
            this.metrics.startTimer(DtbMetric.CONFIG_DOWNLOAD_LATENCY);
            dtbHttpClient.executeGET();
            this.metrics.stopTimer(DtbMetric.CONFIG_DOWNLOAD_LATENCY);
            if (!DtbCommonUtils.isNullOrEmpty(dtbHttpClient.getResponse())) {
                JSONObject jSONObject = (JSONObject) new JSONTokener(dtbHttpClient.getResponse()).nextValue();
                if (!jSONObject.has("aaxHostname")) {
                    if (!jSONObject.has("sisURL")) {
                        String str2 = LOG_TAG;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("ad configuration failed load: ");
                        sb3.append(jSONObject.toString());
                        DtbLog.info(str2, sb3.toString());
                        throw new Exception("ad configuration failed load");
                    }
                }
                if (jSONObject.has("aaxHostname")) {
                    DtbSharedPreferences.getInstance().saveAaxHostname(jSONObject.getString("aaxHostname"));
                }
                if (jSONObject.has("sisURL")) {
                    z = DtbSharedPreferences.getInstance().saveSisEndpoint(jSONObject.getString("sisURL"));
                }
                if (jSONObject.has("ttl")) {
                    DtbSharedPreferences.getInstance().saveConfigTtl(DtbCommonUtils.getMilliSeconds(jSONObject.getString("ttl")));
                }
                DtbSharedPreferences.getInstance().saveConfigLastCheckIn(currentTimeMillis);
                DtbLog.info(LOG_TAG, "ad configuration loaded successfully.");
                return z;
            }
            throw new Exception("Config Response is null");
        } catch (Exception e) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Error fetching DTB config: ");
            sb4.append(e.toString());
            DtbLog.error(sb4.toString());
        }
    }

    private static String convertBooleanToFlag(Boolean bool) {
        if (bool == null) {
            return "0";
        }
        return bool.booleanValue() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0";
    }

    private HashMap<String, Object> buildSISParams(String str) {
        HashMap<String, Object> hashMap = new HashMap<>(DtbDeviceData.getDeviceDataInstance().getDeviceParams());
        String idfa = DtbSharedPreferences.getInstance().getIdfa();
        Boolean optOut = DtbSharedPreferences.getInstance().getOptOut();
        if (!DtbCommonUtils.isNullOrEmpty(idfa)) {
            hashMap.put("idfa", idfa);
        } else {
            hashMap.putAll(DtbDeviceData.getDeviceDataInstance().getOptionalParams());
        }
        hashMap.put("oo", convertBooleanToFlag(optOut));
        if (str != null) {
            hashMap.put("appId", str);
        }
        JSONObject paramsJson = DtbPackageNativeData.getPackageNativeDataInstance(AdRegistration.getContext()).getParamsJson();
        if (paramsJson != null) {
            hashMap.put("pkg", paramsJson);
        }
        return hashMap;
    }

    private HashMap<String, Object> buildConfigInfoParams(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("appId", str);
        hashMap.put("sdkVer", DtbCommonUtils.getSDKVersion());
        hashMap.put("fp", "false");
        hashMap.put("testMode", Boolean.toString(AdRegistration.isTestMode()));
        JSONObject paramsJson = DtbDeviceData.getDeviceDataInstance().getParamsJson();
        if (paramsJson != null) {
            hashMap.put("dinfo", paramsJson);
        }
        String adId = DtbSharedPreferences.getInstance().getAdId();
        if (adId != null) {
            hashMap.put(AbstractEvent.AD_ID, adId);
        }
        return hashMap;
    }
}

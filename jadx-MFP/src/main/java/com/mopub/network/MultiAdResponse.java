package com.mopub.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.AdType;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor;
import com.mopub.common.FullAdType;
import com.mopub.common.MoPub;
import com.mopub.common.MoPub.BrowserAgent;
import com.mopub.common.Preconditions;
import com.mopub.common.util.Json;
import com.mopub.common.util.ResponseHeader;
import com.mopub.mobileads.AdTypeTranslator;
import com.mopub.network.AdResponse.Builder;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MultiAdResponse implements Iterator<AdResponse> {
    @Nullable
    private static ServerOverrideListener sServerOverrideListener;
    @NonNull
    private String mFailUrl;
    @NonNull
    private final Iterator<AdResponse> mResponseIterator;

    public interface ServerOverrideListener {
        void onForceExplicitNo(@Nullable String str);

        void onForceGdprApplies();

        void onInvalidateConsent(@Nullable String str);

        void onReacquireConsent(@Nullable String str);
    }

    @NonNull
    public String getFailURL() {
        return this.mFailUrl;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00fc A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MultiAdResponse(@android.support.annotation.NonNull android.content.Context r15, @android.support.annotation.NonNull com.mopub.volley.NetworkResponse r16, @android.support.annotation.NonNull com.mopub.common.AdFormat r17, @android.support.annotation.Nullable java.lang.String r18) throws org.json.JSONException, com.mopub.network.MoPubNetworkError {
        /*
            r14 = this;
            r1 = r14
            r14.<init>()
            java.lang.String r2 = parseStringBody(r16)
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>(r2)
            com.mopub.common.util.ResponseHeader r3 = com.mopub.common.util.ResponseHeader.FAIL_URL
            java.lang.String r3 = r3.getKey()
            java.lang.String r3 = r0.optString(r3)
            r1.mFailUrl = r3
            com.mopub.common.util.ResponseHeader r3 = com.mopub.common.util.ResponseHeader.REQUEST_ID
            java.lang.String r3 = r3.getKey()
            java.lang.String r3 = r0.optString(r3)
            com.mopub.common.util.ResponseHeader r4 = com.mopub.common.util.ResponseHeader.INVALIDATE_CONSENT
            r5 = 0
            boolean r4 = com.mopub.network.HeaderUtils.extractBooleanHeader(r0, r4, r5)
            com.mopub.common.util.ResponseHeader r6 = com.mopub.common.util.ResponseHeader.FORCE_EXPLICIT_NO
            boolean r6 = com.mopub.network.HeaderUtils.extractBooleanHeader(r0, r6, r5)
            com.mopub.common.util.ResponseHeader r7 = com.mopub.common.util.ResponseHeader.REACQUIRE_CONSENT
            boolean r7 = com.mopub.network.HeaderUtils.extractBooleanHeader(r0, r7, r5)
            com.mopub.common.util.ResponseHeader r8 = com.mopub.common.util.ResponseHeader.CONSENT_CHANGE_REASON
            java.lang.String r8 = com.mopub.network.HeaderUtils.extractHeader(r0, r8)
            com.mopub.common.util.ResponseHeader r9 = com.mopub.common.util.ResponseHeader.FORCE_GDPR_APPLIES
            boolean r9 = com.mopub.network.HeaderUtils.extractBooleanHeader(r0, r9, r5)
            com.mopub.network.MultiAdResponse$ServerOverrideListener r10 = sServerOverrideListener
            if (r10 == 0) goto L_0x0062
            if (r9 == 0) goto L_0x004b
            r10.onForceGdprApplies()
        L_0x004b:
            if (r6 == 0) goto L_0x0053
            com.mopub.network.MultiAdResponse$ServerOverrideListener r4 = sServerOverrideListener
            r4.onForceExplicitNo(r8)
            goto L_0x0062
        L_0x0053:
            if (r4 == 0) goto L_0x005b
            com.mopub.network.MultiAdResponse$ServerOverrideListener r4 = sServerOverrideListener
            r4.onInvalidateConsent(r8)
            goto L_0x0062
        L_0x005b:
            if (r7 == 0) goto L_0x0062
            com.mopub.network.MultiAdResponse$ServerOverrideListener r4 = sServerOverrideListener
            r4.onReacquireConsent(r8)
        L_0x0062:
            com.mopub.common.util.ResponseHeader r4 = com.mopub.common.util.ResponseHeader.AD_RESPONSES
            java.lang.String r4 = r4.getKey()
            org.json.JSONArray r10 = r0.getJSONArray(r4)
            r0 = 3
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>(r0)
            r0 = 0
            r13 = r0
            r12 = 0
        L_0x0075:
            int r0 = r10.length()
            if (r12 >= r0) goto L_0x0115
            org.json.JSONObject r0 = r10.getJSONObject(r12)     // Catch:{ JSONException -> 0x00fd, MoPubNetworkError -> 0x00da, Exception -> 0x00c0 }
            r4 = r15
            r5 = r16
            r6 = r0
            r7 = r18
            r8 = r17
            r9 = r3
            com.mopub.network.AdResponse r4 = parseSingleAdResponse(r4, r5, r6, r7, r8, r9)     // Catch:{ JSONException -> 0x00fd, MoPubNetworkError -> 0x00da, Exception -> 0x00c0 }
            java.lang.String r5 = "clear"
            java.lang.String r6 = r4.getAdType()     // Catch:{ JSONException -> 0x00fd, MoPubNetworkError -> 0x00da, Exception -> 0x00c0 }
            boolean r5 = r5.equals(r6)     // Catch:{ JSONException -> 0x00fd, MoPubNetworkError -> 0x00da, Exception -> 0x00c0 }
            if (r5 != 0) goto L_0x009d
            r11.add(r4)     // Catch:{ JSONException -> 0x00fd, MoPubNetworkError -> 0x00da, Exception -> 0x00c0 }
            goto L_0x0111
        L_0x009d:
            java.lang.String r5 = ""
            r1.mFailUrl = r5     // Catch:{ JSONException -> 0x00fd, MoPubNetworkError -> 0x00da, Exception -> 0x00c0 }
            boolean r0 = extractWarmup(r0)     // Catch:{ JSONException -> 0x00be, MoPubNetworkError -> 0x00bb, Exception -> 0x00b8 }
            if (r0 != 0) goto L_0x00aa
            r13 = r4
            goto L_0x0115
        L_0x00aa:
            com.mopub.network.MoPubNetworkError r0 = new com.mopub.network.MoPubNetworkError     // Catch:{ JSONException -> 0x00be, MoPubNetworkError -> 0x00bb, Exception -> 0x00b8 }
            java.lang.String r5 = "Server is preparing this Ad Unit."
            com.mopub.network.MoPubNetworkError$Reason r6 = com.mopub.network.MoPubNetworkError.Reason.WARMING_UP     // Catch:{ JSONException -> 0x00be, MoPubNetworkError -> 0x00bb, Exception -> 0x00b8 }
            java.lang.Integer r7 = r4.getRefreshTimeMillis()     // Catch:{ JSONException -> 0x00be, MoPubNetworkError -> 0x00bb, Exception -> 0x00b8 }
            r0.<init>(r5, r6, r7)     // Catch:{ JSONException -> 0x00be, MoPubNetworkError -> 0x00bb, Exception -> 0x00b8 }
            throw r0     // Catch:{ JSONException -> 0x00be, MoPubNetworkError -> 0x00bb, Exception -> 0x00b8 }
        L_0x00b8:
            r0 = move-exception
            r13 = r4
            goto L_0x00c1
        L_0x00bb:
            r0 = move-exception
            r13 = r4
            goto L_0x00db
        L_0x00be:
            r13 = r4
            goto L_0x00fd
        L_0x00c0:
            r0 = move-exception
        L_0x00c1:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Unexpected error parsing response item. "
            r4.append(r5)
            java.lang.String r0 = r0.getMessage()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.mopub.common.logging.MoPubLog.w(r0)
            goto L_0x0111
        L_0x00da:
            r0 = move-exception
        L_0x00db:
            com.mopub.network.MoPubNetworkError$Reason r4 = r0.getReason()
            com.mopub.network.MoPubNetworkError$Reason r5 = com.mopub.network.MoPubNetworkError.Reason.WARMING_UP
            if (r4 == r5) goto L_0x00fc
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Invalid response item. Error: "
            r4.append(r5)
            com.mopub.network.MoPubNetworkError$Reason r0 = r0.getReason()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.mopub.common.logging.MoPubLog.w(r0)
            goto L_0x0111
        L_0x00fc:
            throw r0
        L_0x00fd:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "Invalid response item. Body: "
            r0.append(r4)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.mopub.common.logging.MoPubLog.w(r0)
        L_0x0111:
            int r12 = r12 + 1
            goto L_0x0075
        L_0x0115:
            java.util.Iterator r0 = r11.iterator()
            r1.mResponseIterator = r0
            java.util.Iterator<com.mopub.network.AdResponse> r0 = r1.mResponseIterator
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto L_0x0139
            r0 = 30000(0x7530, float:4.2039E-41)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            if (r13 == 0) goto L_0x012f
            java.lang.Integer r0 = r13.getRefreshTimeMillis()
        L_0x012f:
            com.mopub.network.MoPubNetworkError r2 = new com.mopub.network.MoPubNetworkError
            com.mopub.network.MoPubNetworkError$Reason r3 = com.mopub.network.MoPubNetworkError.Reason.NO_FILL
            java.lang.String r4 = "No ads found for ad unit."
            r2.<init>(r4, r3, r0)
            throw r2
        L_0x0139:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.network.MultiAdResponse.<init>(android.content.Context, com.mopub.volley.NetworkResponse, com.mopub.common.AdFormat, java.lang.String):void");
    }

    public boolean hasNext() {
        return this.mResponseIterator.hasNext();
    }

    @NonNull
    public AdResponse next() {
        return (AdResponse) this.mResponseIterator.next();
    }

    /* access modifiers changed from: 0000 */
    public boolean isWaterfallFinished() {
        return TextUtils.isEmpty(this.mFailUrl);
    }

    public static void setServerOverrideListener(@NonNull ServerOverrideListener serverOverrideListener) {
        sServerOverrideListener = serverOverrideListener;
    }

    @NonNull
    protected static AdResponse parseSingleAdResponse(@NonNull Context context, @NonNull NetworkResponse networkResponse, @NonNull JSONObject jSONObject, @Nullable String str, @NonNull AdFormat adFormat, @Nullable String str2) throws JSONException, MoPubNetworkError {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(networkResponse);
        Preconditions.checkNotNull(jSONObject);
        Preconditions.checkNotNull(adFormat);
        Builder builder = new Builder();
        String optString = jSONObject.optString(ResponseHeader.CONTENT.getKey());
        JSONObject jSONObject2 = jSONObject.getJSONObject(ResponseHeader.METADATA.getKey());
        builder.setAdUnitId(str);
        builder.setResponseBody(optString);
        String extractHeader = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.AD_TYPE);
        String extractHeader2 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.FULL_AD_TYPE);
        builder.setAdType(extractHeader);
        builder.setFullAdType(extractHeader2);
        builder.setRefreshTimeMilliseconds(extractRefreshTimeMS(jSONObject));
        if (AdType.CLEAR.equals(extractHeader)) {
            return builder.build();
        }
        builder.setDspCreativeId(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.DSP_CREATIVE_ID));
        builder.setNetworkType(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.NETWORK_TYPE));
        String extractHeader3 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.CLICK_TRACKING_URL);
        builder.setClickTrackingUrl(extractHeader3);
        List extractStringArray = HeaderUtils.extractStringArray(jSONObject2, ResponseHeader.IMPRESSION_URLS);
        if (extractStringArray.isEmpty()) {
            extractStringArray.add(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.IMPRESSION_URL));
        }
        builder.setImpressionTrackingUrls(extractStringArray);
        builder.setBeforeLoadUrl(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.BEFORE_LOAD_URL));
        List extractStringArray2 = HeaderUtils.extractStringArray(jSONObject2, ResponseHeader.AFTER_LOAD_URL);
        if (extractStringArray2.isEmpty()) {
            extractStringArray2.add(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.AFTER_LOAD_URL));
        }
        builder.setAfterLoadUrls(extractStringArray2);
        List extractStringArray3 = HeaderUtils.extractStringArray(jSONObject2, ResponseHeader.AFTER_LOAD_SUCCESS_URL);
        if (extractStringArray3.isEmpty()) {
            extractStringArray3.add(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.AFTER_LOAD_SUCCESS_URL));
        }
        builder.setAfterLoadSuccessUrls(extractStringArray3);
        List extractStringArray4 = HeaderUtils.extractStringArray(jSONObject2, ResponseHeader.AFTER_LOAD_FAIL_URL);
        if (extractStringArray4.isEmpty()) {
            extractStringArray4.add(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.AFTER_LOAD_FAIL_URL));
        }
        builder.setAfterLoadFailUrls(extractStringArray4);
        builder.setRequestId(str2);
        builder.setDimensions(HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.WIDTH), HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.HEIGHT));
        builder.setAdTimeoutDelayMilliseconds(HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.AD_TIMEOUT));
        if (AdType.STATIC_NATIVE.equals(extractHeader) || AdType.VIDEO_NATIVE.equals(extractHeader)) {
            try {
                builder.setJsonBody(new JSONObject(optString));
            } catch (JSONException e) {
                throw new MoPubNetworkError("Failed to decode body JSON for native ad format", (Throwable) e, Reason.BAD_BODY);
            }
        }
        builder.setCustomEventClassName(AdTypeTranslator.getCustomEventName(adFormat, extractHeader, extractHeader2, jSONObject2));
        BrowserAgent fromHeader = BrowserAgent.fromHeader(HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.BROWSER_AGENT));
        MoPub.setBrowserAgentFromAdServer(fromHeader);
        builder.setBrowserAgent(fromHeader);
        String extractHeader4 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.CUSTOM_EVENT_DATA);
        if (TextUtils.isEmpty(extractHeader4)) {
            extractHeader4 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.NATIVE_PARAMS);
        }
        try {
            Map jsonStringToMap = Json.jsonStringToMap(extractHeader4);
            try {
                if (!jSONObject2.optString(DataKeys.ADM_KEY).isEmpty()) {
                    jsonStringToMap.put(DataKeys.ADM_KEY, jSONObject2.getString(DataKeys.ADM_KEY));
                }
                if (!TextUtils.isEmpty(extractHeader3)) {
                    jsonStringToMap.put(DataKeys.CLICKTHROUGH_URL_KEY, extractHeader3);
                }
                if (eventDataIsInResponseBody(extractHeader, extractHeader2)) {
                    jsonStringToMap.put(DataKeys.HTML_RESPONSE_BODY_KEY, optString);
                    jsonStringToMap.put(DataKeys.CREATIVE_ORIENTATION_KEY, HeaderUtils.extractHeader(jSONObject2, ResponseHeader.ORIENTATION));
                }
                if (AdType.STATIC_NATIVE.equals(extractHeader) || AdType.VIDEO_NATIVE.equals(extractHeader)) {
                    String extractPercentHeaderString = HeaderUtils.extractPercentHeaderString(jSONObject2, ResponseHeader.IMPRESSION_MIN_VISIBLE_PERCENT);
                    String extractHeader5 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.IMPRESSION_VISIBLE_MS);
                    String extractHeader6 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.IMPRESSION_MIN_VISIBLE_PX);
                    if (!TextUtils.isEmpty(extractPercentHeaderString)) {
                        jsonStringToMap.put(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT, extractPercentHeaderString);
                    }
                    if (!TextUtils.isEmpty(extractHeader5)) {
                        jsonStringToMap.put(DataKeys.IMPRESSION_VISIBLE_MS, extractHeader5);
                    }
                    if (!TextUtils.isEmpty(extractHeader6)) {
                        jsonStringToMap.put(DataKeys.IMPRESSION_MIN_VISIBLE_PX, extractHeader6);
                    }
                }
                if (AdType.VIDEO_NATIVE.equals(extractHeader)) {
                    jsonStringToMap.put(DataKeys.PLAY_VISIBLE_PERCENT, HeaderUtils.extractPercentHeaderString(jSONObject2, ResponseHeader.PLAY_VISIBLE_PERCENT));
                    jsonStringToMap.put(DataKeys.PAUSE_VISIBLE_PERCENT, HeaderUtils.extractPercentHeaderString(jSONObject2, ResponseHeader.PAUSE_VISIBLE_PERCENT));
                    jsonStringToMap.put(DataKeys.MAX_BUFFER_MS, HeaderUtils.extractHeader(jSONObject2, ResponseHeader.MAX_BUFFER_MS));
                }
                String extractHeader7 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.VIDEO_TRACKERS);
                if (!TextUtils.isEmpty(extractHeader7)) {
                    jsonStringToMap.put(DataKeys.VIDEO_TRACKERS_KEY, extractHeader7);
                }
                if (AdType.REWARDED_VIDEO.equals(extractHeader) || ("interstitial".equals(extractHeader) && FullAdType.VAST.equals(extractHeader2))) {
                    jsonStringToMap.put(DataKeys.EXTERNAL_VIDEO_VIEWABILITY_TRACKERS_KEY, HeaderUtils.extractHeader(jSONObject2, ResponseHeader.VIDEO_VIEWABILITY_TRACKERS));
                }
                if (AdFormat.BANNER.equals(adFormat)) {
                    jsonStringToMap.put(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_MS, HeaderUtils.extractHeader(jSONObject2, ResponseHeader.BANNER_IMPRESSION_MIN_VISIBLE_MS));
                    jsonStringToMap.put(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_DIPS, HeaderUtils.extractHeader(jSONObject2, ResponseHeader.BANNER_IMPRESSION_MIN_VISIBLE_DIPS));
                }
                String extractHeader8 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.DISABLE_VIEWABILITY);
                if (!TextUtils.isEmpty(extractHeader8)) {
                    ViewabilityVendor fromKey = ViewabilityVendor.fromKey(extractHeader8);
                    if (fromKey != null) {
                        fromKey.disable();
                    }
                }
                builder.setServerExtras(jsonStringToMap);
                if (AdType.REWARDED_VIDEO.equals(extractHeader) || "custom".equals(extractHeader) || AdType.REWARDED_PLAYABLE.equals(extractHeader)) {
                    String extractHeader9 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.REWARDED_VIDEO_CURRENCY_NAME);
                    String extractHeader10 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.REWARDED_VIDEO_CURRENCY_AMOUNT);
                    String extractHeader11 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.REWARDED_CURRENCIES);
                    String extractHeader12 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.REWARDED_VIDEO_COMPLETION_URL);
                    Integer extractIntegerHeader = HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.REWARDED_DURATION);
                    boolean extractBooleanHeader = HeaderUtils.extractBooleanHeader(jSONObject2, ResponseHeader.SHOULD_REWARD_ON_CLICK, false);
                    builder.setRewardedVideoCurrencyName(extractHeader9);
                    builder.setRewardedVideoCurrencyAmount(extractHeader10);
                    builder.setRewardedCurrencies(extractHeader11);
                    builder.setRewardedVideoCompletionUrl(extractHeader12);
                    builder.setRewardedDuration(extractIntegerHeader);
                    builder.setShouldRewardOnClick(extractBooleanHeader);
                }
                return builder.build();
            } catch (JSONException e2) {
                throw new MoPubNetworkError("Failed to parse ADM for advanced bidding", (Throwable) e2, Reason.BAD_BODY);
            }
        } catch (JSONException e3) {
            throw new MoPubNetworkError("Failed to decode server extras for custom event data.", (Throwable) e3, Reason.BAD_HEADER_DATA);
        }
    }

    @Nullable
    private static Integer extractRefreshTimeMS(@NonNull JSONObject jSONObject) throws JSONException {
        Preconditions.checkNotNull(jSONObject);
        Integer extractIntegerHeader = HeaderUtils.extractIntegerHeader(jSONObject.getJSONObject(ResponseHeader.METADATA.getKey()), ResponseHeader.REFRESH_TIME);
        if (extractIntegerHeader == null) {
            return null;
        }
        return Integer.valueOf(extractIntegerHeader.intValue() * 1000);
    }

    private static boolean extractWarmup(@NonNull JSONObject jSONObject) {
        Preconditions.checkNotNull(jSONObject);
        return HeaderUtils.extractBooleanHeader(jSONObject.optJSONObject(ResponseHeader.METADATA.getKey()), ResponseHeader.WARMUP, false);
    }

    private static String parseStringBody(@NonNull NetworkResponse networkResponse) {
        Preconditions.checkNotNull(networkResponse);
        try {
            return new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
        } catch (UnsupportedEncodingException unused) {
            return new String(networkResponse.data);
        }
    }

    private static boolean eventDataIsInResponseBody(@Nullable String str, @Nullable String str2) {
        return AdType.MRAID.equals(str) || AdType.HTML.equals(str) || ("interstitial".equals(str) && FullAdType.VAST.equals(str2)) || ((AdType.REWARDED_VIDEO.equals(str) && FullAdType.VAST.equals(str2)) || AdType.REWARDED_PLAYABLE.equals(str));
    }
}

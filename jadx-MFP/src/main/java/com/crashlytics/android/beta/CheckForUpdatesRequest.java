package com.crashlytics.android.beta;

import com.myfitnesspal.shared.constants.HttpConstants;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;

class CheckForUpdatesRequest extends AbstractSpiCall {
    private final CheckForUpdatesResponseTransform responseTransform;

    static String createBetaTokenHeaderValueFor(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("3:");
        sb.append(str);
        return sb.toString();
    }

    public CheckForUpdatesRequest(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, CheckForUpdatesResponseTransform checkForUpdatesResponseTransform) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.GET);
        this.responseTransform = checkForUpdatesResponseTransform;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x010b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.crashlytics.android.beta.CheckForUpdatesResponse invoke(java.lang.String r5, java.lang.String r6, com.crashlytics.android.beta.BuildProperties r7) {
        /*
            r4 = this;
            r0 = 0
            java.util.Map r7 = r4.getQueryParamsFor(r7)     // Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
            io.fabric.sdk.android.services.network.HttpRequest r1 = r4.getHttpRequest(r7)     // Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
            io.fabric.sdk.android.services.network.HttpRequest r5 = r4.applyHeadersTo(r1, r5, r6)     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            io.fabric.sdk.android.Logger r6 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r1 = "Beta"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ba }
            r2.<init>()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r3 = "Checking for updates from "
            r2.append(r3)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r3 = r4.getUrl()     // Catch:{ Exception -> 0x00ba }
            r2.append(r3)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00ba }
            r6.d(r1, r2)     // Catch:{ Exception -> 0x00ba }
            io.fabric.sdk.android.Logger r6 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r1 = "Beta"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ba }
            r2.<init>()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r3 = "Checking for updates query params are: "
            r2.append(r3)     // Catch:{ Exception -> 0x00ba }
            r2.append(r7)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r7 = r2.toString()     // Catch:{ Exception -> 0x00ba }
            r6.d(r1, r7)     // Catch:{ Exception -> 0x00ba }
            boolean r6 = r5.ok()     // Catch:{ Exception -> 0x00ba }
            if (r6 == 0) goto L_0x0088
            io.fabric.sdk.android.Logger r6 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r7 = "Beta"
            java.lang.String r1 = "Checking for updates was successful"
            r6.d(r7, r1)     // Catch:{ Exception -> 0x00ba }
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ Exception -> 0x00ba }
            java.lang.String r7 = r5.body()     // Catch:{ Exception -> 0x00ba }
            r6.<init>(r7)     // Catch:{ Exception -> 0x00ba }
            com.crashlytics.android.beta.CheckForUpdatesResponseTransform r7 = r4.responseTransform     // Catch:{ Exception -> 0x00ba }
            com.crashlytics.android.beta.CheckForUpdatesResponse r6 = r7.fromJson(r6)     // Catch:{ Exception -> 0x00ba }
            if (r5 == 0) goto L_0x0087
            java.lang.String r7 = "X-REQUEST-ID"
            java.lang.String r5 = r5.header(r7)
            io.fabric.sdk.android.Logger r7 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r0 = "Fabric"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Checking for updates request ID: "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r7.d(r0, r5)
        L_0x0087:
            return r6
        L_0x0088:
            io.fabric.sdk.android.Logger r6 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r7 = "Beta"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ba }
            r1.<init>()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r2 = "Checking for updates failed. Response code: "
            r1.append(r2)     // Catch:{ Exception -> 0x00ba }
            int r2 = r5.code()     // Catch:{ Exception -> 0x00ba }
            r1.append(r2)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00ba }
            r6.e(r7, r1)     // Catch:{ Exception -> 0x00ba }
            if (r5 == 0) goto L_0x0107
            java.lang.String r6 = "X-REQUEST-ID"
            java.lang.String r5 = r5.header(r6)
            io.fabric.sdk.android.Logger r6 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r7 = "Fabric"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            goto L_0x00f8
        L_0x00ba:
            r6 = move-exception
            goto L_0x00c7
        L_0x00bc:
            r6 = move-exception
            r5 = r1
            goto L_0x0109
        L_0x00bf:
            r6 = move-exception
            r5 = r1
            goto L_0x00c7
        L_0x00c2:
            r6 = move-exception
            r5 = r0
            goto L_0x0109
        L_0x00c5:
            r6 = move-exception
            r5 = r0
        L_0x00c7:
            io.fabric.sdk.android.Logger r7 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x0108 }
            java.lang.String r1 = "Beta"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0108 }
            r2.<init>()     // Catch:{ all -> 0x0108 }
            java.lang.String r3 = "Error while checking for updates from "
            r2.append(r3)     // Catch:{ all -> 0x0108 }
            java.lang.String r3 = r4.getUrl()     // Catch:{ all -> 0x0108 }
            r2.append(r3)     // Catch:{ all -> 0x0108 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0108 }
            r7.e(r1, r2, r6)     // Catch:{ all -> 0x0108 }
            if (r5 == 0) goto L_0x0107
            java.lang.String r6 = "X-REQUEST-ID"
            java.lang.String r5 = r5.header(r6)
            io.fabric.sdk.android.Logger r6 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r7 = "Fabric"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L_0x00f8:
            java.lang.String r2 = "Checking for updates request ID: "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r6.d(r7, r5)
        L_0x0107:
            return r0
        L_0x0108:
            r6 = move-exception
        L_0x0109:
            if (r5 == 0) goto L_0x012b
            java.lang.String r7 = "X-REQUEST-ID"
            java.lang.String r5 = r5.header(r7)
            io.fabric.sdk.android.Logger r7 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Checking for updates request ID: "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r0 = "Fabric"
            r7.d(r0, r5)
        L_0x012b:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.beta.CheckForUpdatesRequest.invoke(java.lang.String, java.lang.String, com.crashlytics.android.beta.BuildProperties):com.crashlytics.android.beta.CheckForUpdatesResponse");
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Crashlytics Android SDK/");
        sb.append(this.kit.getVersion());
        return httpRequest.header("Accept", HttpConstants.CONTENT_TYPE_JSON).header("User-Agent", sb.toString()).header("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa").header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion()).header("X-CRASHLYTICS-API-KEY", str).header("X-CRASHLYTICS-BETA-TOKEN", createBetaTokenHeaderValueFor(str2));
    }

    private Map<String, String> getQueryParamsFor(BuildProperties buildProperties) {
        HashMap hashMap = new HashMap();
        hashMap.put(Http.BUILD_VERSION, buildProperties.versionCode);
        hashMap.put("display_version", buildProperties.versionName);
        hashMap.put("instance", buildProperties.buildId);
        hashMap.put("source", "3");
        return hashMap;
    }
}

package io.fabric.sdk.android.services.settings;

import android.text.TextUtils;
import com.myfitnesspal.shared.constants.HttpConstants;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    /* access modifiers changed from: 0000 */
    public boolean requestWasSuccessful(int i) {
        return i == 200 || i == 201 || i == 202 || i == 203;
    }

    public DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        this(kit, str, str2, httpRequestFactory, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject invoke(io.fabric.sdk.android.services.settings.SettingsRequest r8) {
        /*
            r7 = this;
            r0 = 0
            java.util.Map r1 = r7.getQueryParamsFor(r8)     // Catch:{ HttpRequestException -> 0x0079, all -> 0x0074 }
            io.fabric.sdk.android.services.network.HttpRequest r2 = r7.getHttpRequest(r1)     // Catch:{ HttpRequestException -> 0x0079, all -> 0x0074 }
            io.fabric.sdk.android.services.network.HttpRequest r8 = r7.applyHeadersTo(r2, r8)     // Catch:{ HttpRequestException -> 0x0071, all -> 0x006e }
            io.fabric.sdk.android.Logger r2 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ HttpRequestException -> 0x006c }
            java.lang.String r3 = "Fabric"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ HttpRequestException -> 0x006c }
            r4.<init>()     // Catch:{ HttpRequestException -> 0x006c }
            java.lang.String r5 = "Requesting settings from "
            r4.append(r5)     // Catch:{ HttpRequestException -> 0x006c }
            java.lang.String r5 = r7.getUrl()     // Catch:{ HttpRequestException -> 0x006c }
            r4.append(r5)     // Catch:{ HttpRequestException -> 0x006c }
            java.lang.String r4 = r4.toString()     // Catch:{ HttpRequestException -> 0x006c }
            r2.d(r3, r4)     // Catch:{ HttpRequestException -> 0x006c }
            io.fabric.sdk.android.Logger r2 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ HttpRequestException -> 0x006c }
            java.lang.String r3 = "Fabric"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ HttpRequestException -> 0x006c }
            r4.<init>()     // Catch:{ HttpRequestException -> 0x006c }
            java.lang.String r5 = "Settings query params were: "
            r4.append(r5)     // Catch:{ HttpRequestException -> 0x006c }
            r4.append(r1)     // Catch:{ HttpRequestException -> 0x006c }
            java.lang.String r1 = r4.toString()     // Catch:{ HttpRequestException -> 0x006c }
            r2.d(r3, r1)     // Catch:{ HttpRequestException -> 0x006c }
            org.json.JSONObject r0 = r7.handleResponse(r8)     // Catch:{ HttpRequestException -> 0x006c }
            if (r8 == 0) goto L_0x0094
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Fabric"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
        L_0x0056:
            java.lang.String r4 = "Settings request ID: "
            r3.append(r4)
            java.lang.String r4 = "X-REQUEST-ID"
            java.lang.String r8 = r8.header(r4)
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            r1.d(r2, r8)
            goto L_0x0094
        L_0x006c:
            r1 = move-exception
            goto L_0x007b
        L_0x006e:
            r0 = move-exception
            r8 = r2
            goto L_0x0096
        L_0x0071:
            r1 = move-exception
            r8 = r2
            goto L_0x007b
        L_0x0074:
            r8 = move-exception
            r6 = r0
            r0 = r8
            r8 = r6
            goto L_0x0096
        L_0x0079:
            r1 = move-exception
            r8 = r0
        L_0x007b:
            io.fabric.sdk.android.Logger r2 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x0095 }
            java.lang.String r3 = "Fabric"
            java.lang.String r4 = "Settings request failed."
            r2.e(r3, r4, r1)     // Catch:{ all -> 0x0095 }
            if (r8 == 0) goto L_0x0094
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Fabric"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            goto L_0x0056
        L_0x0094:
            return r0
        L_0x0095:
            r0 = move-exception
        L_0x0096:
            if (r8 == 0) goto L_0x00b8
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Settings request ID: "
            r2.append(r3)
            java.lang.String r3 = "X-REQUEST-ID"
            java.lang.String r8 = r8.header(r3)
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            java.lang.String r2 = "Fabric"
            r1.d(r2, r8)
        L_0x00b8:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.settings.DefaultSettingsSpiCall.invoke(io.fabric.sdk.android.services.settings.SettingsRequest):org.json.JSONObject");
    }

    /* access modifiers changed from: 0000 */
    public JSONObject handleResponse(HttpRequest httpRequest) {
        int code = httpRequest.code();
        StringBuilder sb = new StringBuilder();
        sb.append("Settings result was: ");
        sb.append(code);
        Fabric.getLogger().d("Fabric", sb.toString());
        if (requestWasSuccessful(code)) {
            return getJsonObjectFrom(httpRequest.body());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Failed to retrieve settings from ");
        sb2.append(getUrl());
        Fabric.getLogger().e("Fabric", sb2.toString());
        return null;
    }

    private JSONObject getJsonObjectFrom(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to parse settings JSON from ");
            sb.append(getUrl());
            Fabric.getLogger().d("Fabric", sb.toString(), e);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Settings response ");
            sb2.append(str);
            Fabric.getLogger().d("Fabric", sb2.toString());
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest settingsRequest) {
        HashMap hashMap = new HashMap();
        hashMap.put(Http.BUILD_VERSION, settingsRequest.buildVersion);
        hashMap.put("display_version", settingsRequest.displayVersion);
        hashMap.put("source", Integer.toString(settingsRequest.source));
        if (settingsRequest.iconHash != null) {
            hashMap.put("icon_hash", settingsRequest.iconHash);
        }
        String str = settingsRequest.instanceId;
        if (!CommonUtils.isNullOrEmpty(str)) {
            hashMap.put("instance", str);
        }
        return hashMap;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, SettingsRequest settingsRequest) {
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-API-KEY", settingsRequest.apiKey);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-API-CLIENT-TYPE", "android");
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion());
        applyNonNullHeader(httpRequest, "Accept", HttpConstants.CONTENT_TYPE_JSON);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-DEVICE-MODEL", settingsRequest.deviceModel);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-OS-BUILD-VERSION", settingsRequest.osBuildVersion);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-OS-DISPLAY-VERSION", settingsRequest.osDisplayVersion);
        applyNonNullHeader(httpRequest, "X-CRASHLYTICS-INSTALLATION-ID", settingsRequest.installationId);
        if (TextUtils.isEmpty(settingsRequest.advertisingId)) {
            applyNonNullHeader(httpRequest, "X-CRASHLYTICS-ANDROID-ID", settingsRequest.androidId);
        } else {
            applyNonNullHeader(httpRequest, "X-CRASHLYTICS-ADVERTISING-TOKEN", settingsRequest.advertisingId);
        }
        return httpRequest;
    }

    private void applyNonNullHeader(HttpRequest httpRequest, String str, String str2) {
        if (str2 != null) {
            httpRequest.header(str, str2);
        }
    }
}

package com.brightcove.player.drm;

import android.annotation.TargetApi;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.drm.ExoMediaDrm.KeyRequest;
import com.google.android.exoplayer2.drm.ExoMediaDrm.ProvisionRequest;
import com.google.android.exoplayer2.drm.MediaDrmCallback;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@TargetApi(18)
public class WidevineMediaDrmCallback extends BrightcoveMediaDrmCallback implements MediaDrmCallback {
    private static final String TAG = "WidevineMediaDrmCallback";

    public WidevineMediaDrmCallback(@Nullable String str) {
        super(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.brightcove.player.drm.WidevineMediaDrmCallback create(@android.support.annotation.NonNull java.util.Map<java.lang.String, java.lang.Object> r2, @android.support.annotation.NonNull java.util.Map<java.lang.String, java.lang.Object> r3) {
        /*
            java.lang.String r0 = "key_systems"
            java.lang.Object r3 = r3.get(r0)
            java.util.Map r3 = (java.util.Map) r3
            if (r3 == 0) goto L_0x001d
            java.lang.String r0 = "com.widevine.alpha"
            java.lang.Object r3 = r3.get(r0)
            java.util.Map r3 = (java.util.Map) r3
            if (r3 == 0) goto L_0x001d
            java.lang.String r0 = "license_url"
            java.lang.Object r3 = r3.get(r0)
            java.lang.String r3 = (java.lang.String) r3
            goto L_0x001e
        L_0x001d:
            r3 = 0
        L_0x001e:
            if (r3 != 0) goto L_0x0028
            java.lang.String r3 = "defaultUrl"
            java.lang.Object r3 = r2.get(r3)
            java.lang.String r3 = (java.lang.String) r3
        L_0x0028:
            if (r3 != 0) goto L_0x0051
            java.lang.String r0 = "id"
            java.lang.Object r0 = r2.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x0040
            java.lang.String r0 = TAG
            java.lang.String r1 = "Video ID required for Brightcove Widevine Modular videos."
            android.util.Log.e(r0, r1)
            goto L_0x0051
        L_0x0040:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r1 = "https://wvlic.brightcove.com/proxy/"
            r3.append(r1)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
        L_0x0051:
            com.brightcove.player.drm.WidevineMediaDrmCallback r0 = new com.brightcove.player.drm.WidevineMediaDrmCallback
            r0.<init>(r3)
            r0.setLicenseRequestHeaders(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.drm.WidevineMediaDrmCallback.create(java.util.Map, java.util.Map):com.brightcove.player.drm.WidevineMediaDrmCallback");
    }

    public byte[] executeProvisionRequest(UUID uuid, @NonNull ProvisionRequest provisionRequest) throws IOException {
        return executeProvisionRequest(provisionRequest.getDefaultUrl(), provisionRequest.getData());
    }

    public byte[] executeKeyRequest(UUID uuid, KeyRequest keyRequest) throws Exception {
        return executeKeyRequest(keyRequest.getLicenseServerUrl(), keyRequest.getData());
    }

    /* access modifiers changed from: protected */
    public byte[] executeProvisionRequest(String str, byte[] bArr) throws IOException {
        if (TextUtils.isEmpty(str)) {
            str = this.defaultUrl;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("&signedRequest=");
        sb.append(new String(bArr));
        return DrmUtil.executePost(sb.toString(), null, null);
    }

    /* access modifiers changed from: protected */
    public byte[] executeKeyRequest(String str, byte[] bArr) throws IOException {
        if (TextUtils.isEmpty(str)) {
            str = this.defaultUrl;
        }
        HashMap hashMap = new HashMap(REQUEST_HEADERS);
        Map optionalHeaders = getOptionalHeaders();
        if (optionalHeaders != null) {
            hashMap.putAll(optionalHeaders);
        }
        return DrmUtil.executePost(str, bArr, hashMap);
    }
}

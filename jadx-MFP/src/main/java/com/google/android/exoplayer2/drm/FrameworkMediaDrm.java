package com.google.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.drm.ExoMediaDrm.KeyRequest;
import com.google.android.exoplayer2.drm.ExoMediaDrm.OnEventListener;
import com.google.android.exoplayer2.drm.ExoMediaDrm.OnKeyStatusChangeListener;
import com.google.android.exoplayer2.drm.ExoMediaDrm.ProvisionRequest;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@TargetApi(23)
public final class FrameworkMediaDrm implements ExoMediaDrm<FrameworkMediaCrypto> {
    private static final String CENC_SCHEME_MIME_TYPE = "cenc";
    private final MediaDrm mediaDrm;
    private final UUID uuid;

    public static FrameworkMediaDrm newInstance(UUID uuid2) throws UnsupportedDrmException {
        try {
            return new FrameworkMediaDrm(uuid2);
        } catch (UnsupportedSchemeException e) {
            throw new UnsupportedDrmException(1, e);
        } catch (Exception e2) {
            throw new UnsupportedDrmException(2, e2);
        }
    }

    private FrameworkMediaDrm(UUID uuid2) throws UnsupportedSchemeException {
        Assertions.checkNotNull(uuid2);
        Assertions.checkArgument(!C.COMMON_PSSH_UUID.equals(uuid2), "Use C.CLEARKEY_UUID instead");
        this.uuid = uuid2;
        this.mediaDrm = new MediaDrm(adjustUuid(uuid2));
        if (C.WIDEVINE_UUID.equals(uuid2) && needsForceWidevineL3Workaround()) {
            forceWidevineL3(this.mediaDrm);
        }
    }

    public void setOnEventListener(OnEventListener<? super FrameworkMediaCrypto> onEventListener) {
        this.mediaDrm.setOnEventListener(onEventListener == null ? null : new MediaDrm.OnEventListener(onEventListener) {
            private final /* synthetic */ OnEventListener f$1;

            {
                this.f$1 = r2;
            }

            public final void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
                this.f$1.onEvent(FrameworkMediaDrm.this, bArr, i, i2, bArr2);
            }
        });
    }

    public void setOnKeyStatusChangeListener(OnKeyStatusChangeListener<? super FrameworkMediaCrypto> onKeyStatusChangeListener) {
        if (Util.SDK_INT >= 23) {
            this.mediaDrm.setOnKeyStatusChangeListener(onKeyStatusChangeListener == null ? null : new MediaDrm.OnKeyStatusChangeListener(onKeyStatusChangeListener) {
                private final /* synthetic */ OnKeyStatusChangeListener f$1;

                {
                    this.f$1 = r2;
                }

                public final void onKeyStatusChange(MediaDrm mediaDrm, byte[] bArr, List list, boolean z) {
                    FrameworkMediaDrm.lambda$setOnKeyStatusChangeListener$1(FrameworkMediaDrm.this, this.f$1, mediaDrm, bArr, list, z);
                }
            }, null);
            return;
        }
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<android.media.MediaDrm$KeyStatus>, for r7v0, types: [java.util.List, java.util.List<android.media.MediaDrm$KeyStatus>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$setOnKeyStatusChangeListener$1(com.google.android.exoplayer2.drm.FrameworkMediaDrm r3, com.google.android.exoplayer2.drm.ExoMediaDrm.OnKeyStatusChangeListener r4, android.media.MediaDrm r5, byte[] r6, java.util.List<android.media.MediaDrm.KeyStatus> r7, boolean r8) {
        /*
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r7 = r7.iterator()
        L_0x0009:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x0026
            java.lang.Object r0 = r7.next()
            android.media.MediaDrm$KeyStatus r0 = (android.media.MediaDrm.KeyStatus) r0
            com.google.android.exoplayer2.drm.ExoMediaDrm$KeyStatus r1 = new com.google.android.exoplayer2.drm.ExoMediaDrm$KeyStatus
            int r2 = r0.getStatusCode()
            byte[] r0 = r0.getKeyId()
            r1.<init>(r2, r0)
            r5.add(r1)
            goto L_0x0009
        L_0x0026:
            r4.onKeyStatusChange(r3, r6, r5, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.drm.FrameworkMediaDrm.lambda$setOnKeyStatusChangeListener$1(com.google.android.exoplayer2.drm.FrameworkMediaDrm, com.google.android.exoplayer2.drm.ExoMediaDrm$OnKeyStatusChangeListener, android.media.MediaDrm, byte[], java.util.List, boolean):void");
    }

    public byte[] openSession() throws MediaDrmException {
        return this.mediaDrm.openSession();
    }

    public void closeSession(byte[] bArr) {
        this.mediaDrm.closeSession(bArr);
    }

    public KeyRequest getKeyRequest(byte[] bArr, @Nullable List<SchemeData> list, int i, @Nullable HashMap<String, String> hashMap) throws NotProvisionedException {
        String str;
        byte[] bArr2;
        SchemeData schemeData = null;
        if (list != null) {
            schemeData = getSchemeData(this.uuid, list);
            bArr2 = adjustRequestInitData(this.uuid, schemeData.data);
            str = adjustRequestMimeType(this.uuid, schemeData.mimeType);
        } else {
            bArr2 = null;
            str = null;
        }
        MediaDrm.KeyRequest keyRequest = this.mediaDrm.getKeyRequest(bArr, bArr2, str, i, hashMap);
        byte[] adjustRequestData = adjustRequestData(this.uuid, keyRequest.getData());
        String defaultUrl = keyRequest.getDefaultUrl();
        if (TextUtils.isEmpty(defaultUrl) && schemeData != null && !TextUtils.isEmpty(schemeData.licenseServerUrl)) {
            defaultUrl = schemeData.licenseServerUrl;
        }
        return new KeyRequest(adjustRequestData, defaultUrl);
    }

    public byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) throws NotProvisionedException, DeniedByServerException {
        if (C.CLEARKEY_UUID.equals(this.uuid)) {
            bArr2 = ClearKeyUtil.adjustResponseData(bArr2);
        }
        return this.mediaDrm.provideKeyResponse(bArr, bArr2);
    }

    public ProvisionRequest getProvisionRequest() {
        MediaDrm.ProvisionRequest provisionRequest = this.mediaDrm.getProvisionRequest();
        return new ProvisionRequest(provisionRequest.getData(), provisionRequest.getDefaultUrl());
    }

    public void provideProvisionResponse(byte[] bArr) throws DeniedByServerException {
        this.mediaDrm.provideProvisionResponse(bArr);
    }

    public Map<String, String> queryKeyStatus(byte[] bArr) {
        return this.mediaDrm.queryKeyStatus(bArr);
    }

    public void release() {
        this.mediaDrm.release();
    }

    public void restoreKeys(byte[] bArr, byte[] bArr2) {
        this.mediaDrm.restoreKeys(bArr, bArr2);
    }

    public String getPropertyString(String str) {
        return this.mediaDrm.getPropertyString(str);
    }

    public byte[] getPropertyByteArray(String str) {
        return this.mediaDrm.getPropertyByteArray(str);
    }

    public void setPropertyString(String str, String str2) {
        this.mediaDrm.setPropertyString(str, str2);
    }

    public void setPropertyByteArray(String str, byte[] bArr) {
        this.mediaDrm.setPropertyByteArray(str, bArr);
    }

    public FrameworkMediaCrypto createMediaCrypto(byte[] bArr) throws MediaCryptoException {
        return new FrameworkMediaCrypto(new MediaCrypto(adjustUuid(this.uuid), bArr), Util.SDK_INT < 21 && C.WIDEVINE_UUID.equals(this.uuid) && "L3".equals(getPropertyString("securityLevel")));
    }

    private static SchemeData getSchemeData(UUID uuid2, List<SchemeData> list) {
        boolean z;
        if (!C.WIDEVINE_UUID.equals(uuid2)) {
            return (SchemeData) list.get(0);
        }
        if (Util.SDK_INT >= 28 && list.size() > 1) {
            SchemeData schemeData = (SchemeData) list.get(0);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= list.size()) {
                    z = true;
                    break;
                }
                SchemeData schemeData2 = (SchemeData) list.get(i);
                if (schemeData2.requiresSecureDecryption != schemeData.requiresSecureDecryption || !Util.areEqual(schemeData2.mimeType, schemeData.mimeType) || !Util.areEqual(schemeData2.licenseServerUrl, schemeData.licenseServerUrl) || !PsshAtomUtil.isPsshAtom(schemeData2.data)) {
                    z = false;
                } else {
                    i2 += schemeData2.data.length;
                    i++;
                }
            }
            z = false;
            if (z) {
                byte[] bArr = new byte[i2];
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    SchemeData schemeData3 = (SchemeData) list.get(i4);
                    int length = schemeData3.data.length;
                    System.arraycopy(schemeData3.data, 0, bArr, i3, length);
                    i3 += length;
                }
                return schemeData.copyWithData(bArr);
            }
        }
        for (int i5 = 0; i5 < list.size(); i5++) {
            SchemeData schemeData4 = (SchemeData) list.get(i5);
            int parseVersion = PsshAtomUtil.parseVersion(schemeData4.data);
            if (Util.SDK_INT < 23 && parseVersion == 0) {
                return schemeData4;
            }
            if (Util.SDK_INT >= 23 && parseVersion == 1) {
                return schemeData4;
            }
        }
        return (SchemeData) list.get(0);
    }

    private static UUID adjustUuid(UUID uuid2) {
        return (Util.SDK_INT >= 27 || !C.CLEARKEY_UUID.equals(uuid2)) ? uuid2 : C.COMMON_PSSH_UUID;
    }

    private static byte[] adjustRequestInitData(UUID uuid2, byte[] bArr) {
        if ((Util.SDK_INT < 21 && C.WIDEVINE_UUID.equals(uuid2)) || (C.PLAYREADY_UUID.equals(uuid2) && "Amazon".equals(Util.MANUFACTURER) && ("AFTB".equals(Util.MODEL) || "AFTS".equals(Util.MODEL) || "AFTM".equals(Util.MODEL)))) {
            byte[] parseSchemeSpecificData = PsshAtomUtil.parseSchemeSpecificData(bArr, uuid2);
            if (parseSchemeSpecificData != null) {
                return parseSchemeSpecificData;
            }
        }
        return bArr;
    }

    private static String adjustRequestMimeType(UUID uuid2, String str) {
        return (Util.SDK_INT >= 26 || !C.CLEARKEY_UUID.equals(uuid2) || (!MimeTypes.VIDEO_MP4.equals(str) && !MimeTypes.AUDIO_MP4.equals(str))) ? str : "cenc";
    }

    private static byte[] adjustRequestData(UUID uuid2, byte[] bArr) {
        return C.CLEARKEY_UUID.equals(uuid2) ? ClearKeyUtil.adjustRequestData(bArr) : bArr;
    }

    @SuppressLint({"WrongConstant"})
    private static void forceWidevineL3(MediaDrm mediaDrm2) {
        mediaDrm2.setPropertyString("securityLevel", "L3");
    }

    private static boolean needsForceWidevineL3Workaround() {
        return "ASUS_Z00AD".equals(Util.MODEL);
    }
}

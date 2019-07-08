package com.google.android.gms.internal.ads;

import com.brightcove.player.network.DownloadStatus;
import com.myfitnesspal.feature.externalsync.impl.shealth.constants.SHealthExerciseType.SamsungExercise;
import com.myfitnesspal.shared.constants.SharedConstants.RequestCodes;

public final class zzuo {

    public static final class zza extends zzbrd<zza, C0032zza> implements zzbsn {
        /* access modifiers changed from: private */
        public static final zza zzcar = new zza();
        private static volatile zzbsw<zza> zzcas;

        /* renamed from: com.google.android.gms.internal.ads.zzuo$zza$zza reason: collision with other inner class name */
        public static final class C0032zza extends com.google.android.gms.internal.ads.zzbrd.zza<zza, C0032zza> implements zzbsn {
            private C0032zza() {
                super(zza.zzcar);
            }

            /* synthetic */ C0032zza(zzup zzup) {
                this();
            }
        }

        public enum zzb implements zzbrg {
            UNKNOWN_EVENT_TYPE(0),
            AD_REQUEST(1),
            AD_LOADED(2),
            AD_FAILED_TO_LOAD(3),
            AD_FAILED_TO_LOAD_NO_FILL(4),
            AD_IMPRESSION(5),
            AD_FIRST_CLICK(6),
            AD_SUBSEQUENT_CLICK(7),
            REQUEST_WILL_START(8),
            REQUEST_DID_END(9),
            REQUEST_WILL_UPDATE_SIGNALS(10),
            REQUEST_DID_UPDATE_SIGNALS(11),
            REQUEST_WILL_BUILD_URL(12),
            REQUEST_DID_BUILD_URL(13),
            REQUEST_WILL_MAKE_NETWORK_REQUEST(14),
            REQUEST_DID_RECEIVE_NETWORK_RESPONSE(15),
            REQUEST_WILL_PROCESS_RESPONSE(16),
            REQUEST_DID_PROCESS_RESPONSE(17),
            REQUEST_WILL_RENDER(18),
            REQUEST_DID_RENDER(19),
            REQUEST_WILL_UPDATE_GMS_SIGNALS(1000),
            REQUEST_DID_UPDATE_GMS_SIGNALS(1001),
            REQUEST_FAILED_TO_UPDATE_GMS_SIGNALS(1002),
            REQUEST_FAILED_TO_BUILD_URL(RequestCodes.PICK_FROM_CAMERA),
            REQUEST_FAILED_TO_MAKE_NETWORK_REQUEST(1004),
            REQUEST_FAILED_TO_PROCESS_RESPONSE(DownloadStatus.ERROR_TOO_MANY_REDIRECTS),
            REQUEST_FAILED_TO_UPDATE_SIGNALS(1006),
            BANNER_SIZE_INVALID(10000),
            BANNER_SIZE_VALID(10001),
            ANDROID_WEBVIEW_CRASH(SamsungExercise.ROPE_SKIPPING);
            
            private static final zzbrh<zzb> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            private zzb(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzuq();
            }
        }

        private zza() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzup.zzcaq[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0032zza(null);
                case 3:
                    return zza((zzbsl) zzcar, "\u0001\u0000", (Object[]) null);
                case 4:
                    return zzcar;
                case 5:
                    zzbsw<zza> zzbsw = zzcas;
                    if (zzbsw == null) {
                        synchronized (zza.class) {
                            zzbsw = zzcas;
                            if (zzbsw == null) {
                                zzbsw = new com.google.android.gms.internal.ads.zzbrd.zzb<>(zzcar);
                                zzcas = zzbsw;
                            }
                        }
                    }
                    return zzbsw;
                case 6:
                    return Byte.valueOf(1);
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzbrd.zza(zza.class, zzcar);
        }
    }
}

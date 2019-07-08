package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.view.Surface;

@TargetApi(17)
public final class zzqk extends Surface {
    private static boolean zzbij;
    private static boolean zzbik;
    private final boolean zzatr;
    private final zzqm zzbil;
    private boolean zzbim;

    public static synchronized boolean zzb(Context context) {
        boolean z;
        synchronized (zzqk.class) {
            if (!zzbik) {
                if (zzqe.SDK_INT >= 17) {
                    boolean z2 = false;
                    String eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
                    if (eglQueryString != null && eglQueryString.contains("EGL_EXT_protected_content")) {
                        if (!(zzqe.SDK_INT == 24 && (zzqe.MODEL.startsWith("SM-G950") || zzqe.MODEL.startsWith("SM-G955")) && !context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance"))) {
                            z2 = true;
                        }
                    }
                    zzbij = z2;
                }
                zzbik = true;
            }
            z = zzbij;
        }
        return z;
    }

    public static zzqk zzc(Context context, boolean z) {
        if (zzqe.SDK_INT >= 17) {
            zzpo.checkState(!z || zzb(context));
            return new zzqm().zzk(z);
        }
        throw new UnsupportedOperationException("Unsupported prior to API level 17");
    }

    private zzqk(zzqm zzqm, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.zzbil = zzqm;
        this.zzatr = z;
    }

    public final void release() {
        super.release();
        synchronized (this.zzbil) {
            if (!this.zzbim) {
                this.zzbil.release();
                this.zzbim = true;
            }
        }
    }
}

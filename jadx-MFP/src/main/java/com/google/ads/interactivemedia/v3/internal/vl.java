package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.view.Surface;

@TargetApi(17)
/* compiled from: IMASDK */
public final class vl extends Surface {
    private static int a;
    private static boolean b;
    private final vm c;
    private boolean d;

    public static synchronized boolean a(Context context) {
        int i;
        synchronized (vl.class) {
            if (!b) {
                if (vf.a < 24) {
                    i = 0;
                } else if (vf.a < 26 && ("samsung".equals(vf.c) || "XT1650".equals(vf.d))) {
                    i = 0;
                } else if (vf.a >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) {
                    String eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
                    i = eglQueryString == null ? 0 : !eglQueryString.contains("EGL_EXT_protected_content") ? 0 : eglQueryString.contains("EGL_KHR_surfaceless_context") ? 1 : 2;
                } else {
                    i = 0;
                }
                a = i;
                b = true;
            }
            return a != 0;
        }
    }

    public static vl a(Context context, boolean z) {
        if (vf.a >= 17) {
            int i = 0;
            qi.c(!z || a(context));
            vm vmVar = new vm();
            if (z) {
                i = a;
            }
            return vmVar.a(i);
        }
        throw new UnsupportedOperationException("Unsupported prior to API level 17");
    }

    private vl(vm vmVar, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.c = vmVar;
    }

    public final void release() {
        super.release();
        synchronized (this.c) {
            if (!this.d) {
                this.c.a();
                this.d = true;
            }
        }
    }

    /* synthetic */ vl(vm vmVar, SurfaceTexture surfaceTexture, boolean z, byte b2) {
        this(vmVar, surfaceTexture, z);
    }
}

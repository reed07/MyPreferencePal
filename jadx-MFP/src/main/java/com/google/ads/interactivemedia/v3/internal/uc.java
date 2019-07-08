package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;

@TargetApi(17)
/* compiled from: IMASDK */
public final class uc implements OnFrameAvailableListener, Runnable {
    private static final int[] a = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344};
    private final Handler b;
    private final int[] c;
    private final ue d;
    private EGLDisplay e;
    private EGLContext f;
    private EGLSurface g;
    private SurfaceTexture h;

    public uc(Handler handler) {
        this(handler, null);
    }

    private uc(Handler handler, ue ueVar) {
        this.b = handler;
        this.d = null;
        this.c = new int[1];
    }

    public final void a(int i) {
        int[] iArr;
        EGLSurface eGLSurface;
        int[] iArr2;
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        if (eglGetDisplay != null) {
            int[] iArr3 = new int[2];
            if (EGL14.eglInitialize(eglGetDisplay, iArr3, 0, iArr3, 1)) {
                this.e = eglGetDisplay;
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                int[] iArr4 = new int[1];
                boolean eglChooseConfig = EGL14.eglChooseConfig(this.e, a, 0, eGLConfigArr, 0, 1, iArr4, 0);
                if (!eglChooseConfig || iArr4[0] <= 0 || eGLConfigArr[0] == null) {
                    throw new ud(vf.a("eglChooseConfig failed: success=%b, numConfigs[0]=%d, configs[0]=%s", Boolean.valueOf(eglChooseConfig), Integer.valueOf(iArr4[0]), eGLConfigArr[0]), 0);
                }
                EGLConfig eGLConfig = eGLConfigArr[0];
                EGLDisplay eGLDisplay = this.e;
                if (i == 0) {
                    iArr = new int[]{12440, 2, 12344};
                } else {
                    iArr = new int[]{12440, 2, 12992, 1, 12344};
                }
                EGLContext eglCreateContext = EGL14.eglCreateContext(eGLDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, iArr, 0);
                if (eglCreateContext != null) {
                    this.f = eglCreateContext;
                    EGLDisplay eGLDisplay2 = this.e;
                    EGLContext eGLContext = this.f;
                    if (i == 1) {
                        eGLSurface = EGL14.EGL_NO_SURFACE;
                    } else {
                        if (i == 2) {
                            iArr2 = new int[]{12375, 1, 12374, 1, 12992, 1, 12344};
                        } else {
                            iArr2 = new int[]{12375, 1, 12374, 1, 12344};
                        }
                        eGLSurface = EGL14.eglCreatePbufferSurface(eGLDisplay2, eGLConfig, iArr2, 0);
                        if (eGLSurface == null) {
                            throw new ud("eglCreatePbufferSurface failed", 0);
                        }
                    }
                    if (EGL14.eglMakeCurrent(eGLDisplay2, eGLSurface, eGLSurface, eGLContext)) {
                        this.g = eGLSurface;
                        GLES20.glGenTextures(1, this.c, 0);
                        qi.d();
                        this.h = new SurfaceTexture(this.c[0]);
                        this.h.setOnFrameAvailableListener(this);
                        return;
                    }
                    throw new ud("eglMakeCurrent failed", 0);
                }
                throw new ud("eglCreateContext failed", 0);
            }
            throw new ud("eglInitialize failed", 0);
        }
        throw new ud("eglGetDisplay failed", 0);
    }

    public final void a() {
        this.b.removeCallbacks(this);
        try {
            if (this.h != null) {
                this.h.release();
                GLES20.glDeleteTextures(1, this.c, 0);
            }
        } finally {
            EGLDisplay eGLDisplay = this.e;
            if (eGLDisplay != null && !eGLDisplay.equals(EGL14.EGL_NO_DISPLAY)) {
                EGL14.eglMakeCurrent(this.e, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            }
            EGLSurface eGLSurface = this.g;
            if (eGLSurface != null && !eGLSurface.equals(EGL14.EGL_NO_SURFACE)) {
                EGL14.eglDestroySurface(this.e, this.g);
            }
            EGLContext eGLContext = this.f;
            if (eGLContext != null) {
                EGL14.eglDestroyContext(this.e, eGLContext);
            }
            if (vf.a >= 19) {
                EGL14.eglReleaseThread();
            }
            EGLDisplay eGLDisplay2 = this.e;
            if (eGLDisplay2 != null && !eGLDisplay2.equals(EGL14.EGL_NO_DISPLAY)) {
                EGL14.eglTerminate(this.e);
            }
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
        }
    }

    public final SurfaceTexture b() {
        return (SurfaceTexture) qi.a(this.h);
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.b.post(this);
    }

    public final void run() {
        ue ueVar = this.d;
        if (ueVar != null) {
            ueVar.a();
        }
        SurfaceTexture surfaceTexture = this.h;
        if (surfaceTexture != null) {
            try {
                surfaceTexture.updateTexImage();
            } catch (RuntimeException unused) {
            }
        }
    }
}

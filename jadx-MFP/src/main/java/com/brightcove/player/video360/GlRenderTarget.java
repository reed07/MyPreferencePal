package com.brightcove.player.video360;

import android.annotation.TargetApi;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.support.annotation.RequiresApi;

@TargetApi(17)
@RequiresApi
public class GlRenderTarget {
    private static final int COLOR_SIZE_IN_BIT = 8;
    private static final String TAG = "GlRenderTarget";
    private static final int VERSION_BUFFER_SIZE = 2;
    private EGLConfig config;
    private EGLDisplay display = EGL14.EGL_NO_DISPLAY;
    private EGLContext eglContext = EGL14.EGL_NO_CONTEXT;
    private EGLSurface surface = EGL14.EGL_NO_SURFACE;

    public GlRenderTarget() {
        initialize();
    }

    public boolean hasValidContext() {
        EGLContext eGLContext = this.eglContext;
        return (eGLContext == null || eGLContext == EGL14.EGL_NO_CONTEXT) ? false : true;
    }

    public boolean hasValidSurface() {
        EGLSurface eGLSurface = this.surface;
        return (eGLSurface == null || eGLSurface == EGL14.EGL_NO_SURFACE) ? false : true;
    }

    public void initialize() {
        this.display = EGL14.eglGetDisplay(0);
        if (this.display != EGL14.EGL_NO_DISPLAY) {
            int[] iArr = new int[2];
            if (EGL14.eglInitialize(this.display, iArr, 0, iArr, 1)) {
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                int[] iArr2 = new int[1];
                if (!EGL14.eglChooseConfig(this.display, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344}, 0, eGLConfigArr, 0, eGLConfigArr.length, iArr2, 0)) {
                    throw new OpenGLException("Failed to choose Open GL configuration");
                } else if (iArr2[0] > 0) {
                    this.config = eGLConfigArr[0];
                    this.eglContext = EGL14.eglCreateContext(this.display, this.config, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
                    if (this.eglContext == null) {
                        throw new OpenGLException("Failed to create Open GL context");
                    }
                } else {
                    throw new OpenGLException("No EGL config found for attribute list");
                }
            } else {
                throw new OpenGLException("Failed to initialize Open GL Display!");
            }
        } else {
            throw new OpenGLException("Failed to get Open GL Display!");
        }
    }

    public void release() {
        EGL14.eglDestroySurface(this.display, this.surface);
        EGL14.eglDestroyContext(this.display, this.eglContext);
        this.display = EGL14.EGL_NO_DISPLAY;
        this.surface = EGL14.EGL_NO_SURFACE;
        this.eglContext = EGL14.EGL_NO_CONTEXT;
    }

    public void createRenderSurface(Object obj) {
        if (!hasValidContext()) {
            initialize();
        }
        this.surface = EGL14.eglCreateWindowSurface(this.display, this.config, obj, new int[]{12344}, 0);
        GlUtil.checkGlError("Failed to create Open GL surface");
        EGLSurface eGLSurface = this.surface;
        if (eGLSurface != null && eGLSurface != EGL14.EGL_NO_SURFACE) {
            makeCurrent();
        }
    }

    public void swapBuffers() {
        if (!EGL14.eglSwapBuffers(this.display, this.surface)) {
            GlUtil.checkGlError("Failed to swap Open GL buffers");
        }
    }

    public void makeCurrent() {
        EGLDisplay eGLDisplay = this.display;
        EGLSurface eGLSurface = this.surface;
        if (!EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.eglContext)) {
            GlUtil.checkGlError("Failed to make surface current");
        }
    }
}

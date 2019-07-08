package com.brightcove.player.video360;

import android.annotation.TargetApi;
import android.opengl.EGL14;
import android.opengl.GLUtils;

public class OpenGLException extends RuntimeException {
    public final int errorCode;
    public final String errorDescription;

    @TargetApi(17)
    public OpenGLException(String str) {
        this(str, EGL14.eglGetError());
    }

    @TargetApi(17)
    public OpenGLException(String str, int i) {
        super(str);
        this.errorCode = i;
        this.errorDescription = GLUtils.getEGLErrorString(i);
    }
}

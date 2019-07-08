package com.brightcove.player.video360;

import android.opengl.GLES20;
import android.util.Log;

public class ShaderProgram {
    public static final String TAG = "ShaderProgram";
    private int shaderProgramHandle;

    public ShaderProgram(String str, String str2) {
        this.shaderProgramHandle = createProgram(str, str2);
    }

    public int getShaderHandle() {
        return this.shaderProgramHandle;
    }

    public void release() {
        GLES20.glDeleteProgram(this.shaderProgramHandle);
        this.shaderProgramHandle = -1;
    }

    private static void checkLocation(int i, String str) {
        if (i < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not find location for ");
            sb.append(str);
            throw new RuntimeException(sb.toString());
        }
    }

    public int getAttribute(String str) {
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.shaderProgramHandle, str);
        checkLocation(glGetAttribLocation, str);
        return glGetAttribLocation;
    }

    public int getUniform(String str) {
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.shaderProgramHandle, str);
        checkLocation(glGetUniformLocation, str);
        return glGetUniformLocation;
    }

    private static int createProgram(String str, String str2) {
        int loadShader = loadShader(35633, str);
        int loadShader2 = loadShader(35632, str2);
        int glCreateProgram = GLES20.glCreateProgram();
        GlUtil.checkGlError("glCreateProgram");
        if (glCreateProgram == 0) {
            Log.e(TAG, "Could not create program");
            return 0;
        }
        GLES20.glAttachShader(glCreateProgram, loadShader);
        GlUtil.checkGlError("glAttachShader");
        GLES20.glAttachShader(glCreateProgram, loadShader2);
        GlUtil.checkGlError("glAttachShader");
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            Log.e(TAG, "Could not link program: ");
            Log.e(TAG, GLES20.glGetProgramInfoLog(glCreateProgram));
            GLES20.glDeleteProgram(glCreateProgram);
            glCreateProgram = 0;
        }
        return glCreateProgram;
    }

    private static int loadShader(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        StringBuilder sb = new StringBuilder();
        sb.append("glCreateShader type=");
        sb.append(i);
        GlUtil.checkGlError(sb.toString());
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Could not compile shader ");
        sb2.append(i);
        sb2.append(":");
        Log.e(str2, sb2.toString());
        String str3 = TAG;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(" ");
        sb3.append(GLES20.glGetShaderInfoLog(glCreateShader));
        Log.e(str3, sb3.toString());
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }
}

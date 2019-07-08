package com.brightcove.player.video360;

import android.annotation.TargetApi;
import android.content.Context;
import android.opengl.GLES20;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@TargetApi(17)
public class GlUtil {
    private static final String TAG = "GlUtil";
    private static final int TEXTURE_BUFFER_SIZE = 1;

    private GlUtil() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int generateExternalTexture() {
        /*
            r0 = 1
            int[] r1 = new int[r0]
            r2 = 0
            r3 = -1
            android.opengl.GLES20.glGenTextures(r0, r1, r2)     // Catch:{ RuntimeException -> 0x0033 }
            r4 = r1[r2]     // Catch:{ RuntimeException -> 0x0033 }
            r5 = 33984(0x84c0, float:4.7622E-41)
            android.opengl.GLES20.glActiveTexture(r5)     // Catch:{ RuntimeException -> 0x0031 }
            r5 = 36197(0x8d65, float:5.0723E-41)
            android.opengl.GLES20.glBindTexture(r5, r4)     // Catch:{ RuntimeException -> 0x0031 }
            r6 = 10241(0x2801, float:1.435E-41)
            r7 = 1175979008(0x46180400, float:9729.0)
            android.opengl.GLES20.glTexParameterf(r5, r6, r7)     // Catch:{ RuntimeException -> 0x0031 }
            r6 = 10240(0x2800, float:1.4349E-41)
            android.opengl.GLES20.glTexParameterf(r5, r6, r7)     // Catch:{ RuntimeException -> 0x0031 }
            r6 = 10242(0x2802, float:1.4352E-41)
            r7 = 33071(0x812f, float:4.6342E-41)
            android.opengl.GLES20.glTexParameteri(r5, r6, r7)     // Catch:{ RuntimeException -> 0x0031 }
            r6 = 10243(0x2803, float:1.4354E-41)
            android.opengl.GLES20.glTexParameteri(r5, r6, r7)     // Catch:{ RuntimeException -> 0x0031 }
            return r4
        L_0x0031:
            r5 = move-exception
            goto L_0x0035
        L_0x0033:
            r5 = move-exception
            r4 = -1
        L_0x0035:
            java.lang.String r6 = TAG
            java.lang.String r7 = r5.toString()
            android.util.Log.e(r6, r7, r5)
            if (r4 == r3) goto L_0x0043
            android.opengl.GLES20.glDeleteTextures(r0, r1, r2)
        L_0x0043:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.video360.GlUtil.generateExternalTexture():int");
    }

    public static void checkGlError(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            throw new OpenGLException(str, glGetError);
        }
    }

    public static String readRawTextFile(Context context, int i) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(i)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return sb.toString();
                }
                sb.append(readLine);
                sb.append(10);
            } catch (IOException unused) {
                return null;
            }
        }
    }
}

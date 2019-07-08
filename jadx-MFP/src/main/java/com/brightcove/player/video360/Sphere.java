package com.brightcove.player.video360;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Sphere {
    public static final int FLOAT_SIZE = 4;
    public static final int SHORT_SIZE = 2;
    private ShortBuffer[] mIndices;
    private int[] mNumIndices;
    private int mTotalIndices;
    private FloatBuffer mVertices;

    public int getVerticesStride() {
        return 20;
    }

    public Sphere(int i, float f, float f2, float f3, float f4, int i2) {
        int i3;
        Sphere sphere = this;
        int i4 = i;
        int i5 = i2;
        int i6 = i4 + 1;
        int i7 = i6 * i6;
        if (i7 <= 32767) {
            sphere.mTotalIndices = i4 * i4 * 6;
            float f5 = (float) i4;
            float f6 = 3.1415927f / f5;
            float f7 = 6.2831855f / f5;
            sphere.mVertices = ByteBuffer.allocateDirect(i7 * 5 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            sphere.mIndices = new ShortBuffer[i5];
            sphere.mNumIndices = new int[i5];
            int i8 = ((sphere.mTotalIndices / i5) / 6) * 6;
            int i9 = 0;
            while (true) {
                i3 = i5 - 1;
                if (i9 >= i3) {
                    break;
                }
                sphere.mNumIndices[i9] = i8;
                i9++;
            }
            sphere.mNumIndices[i3] = sphere.mTotalIndices - (i8 * i3);
            for (int i10 = 0; i10 < i5; i10++) {
                sphere.mIndices[i10] = ByteBuffer.allocateDirect(sphere.mNumIndices[i10] * 2).order(ByteOrder.nativeOrder()).asShortBuffer();
            }
            float[] fArr = new float[(i6 * 5)];
            int i11 = 0;
            while (i11 < i6) {
                int i12 = 0;
                while (i12 < i6) {
                    int i13 = i12 * 5;
                    float f8 = (float) i11;
                    double d = (double) (f6 * f8);
                    int i14 = i11;
                    float f9 = (float) i12;
                    int i15 = i6;
                    double d2 = (double) (f7 * f9);
                    float sin = (float) Math.sin(d2);
                    float cos = (float) Math.cos(d);
                    float cos2 = (float) Math.cos(d2);
                    float sin2 = ((float) Math.sin(d)) * f4;
                    fArr[i13 + 0] = f + (sin * sin2);
                    fArr[i13 + 1] = f2 + (sin2 * cos2);
                    fArr[i13 + 2] = f3 + (cos * f4);
                    fArr[i13 + 3] = f9 / f5;
                    fArr[i13 + 4] = (1.0f - f8) / f5;
                    i12++;
                    i11 = i14;
                    i6 = i15;
                    sphere = this;
                    int i16 = i;
                    int i17 = i2;
                }
                int i18 = i6;
                int i19 = i11;
                sphere.mVertices.put(fArr, 0, fArr.length);
                i11 = i19 + 1;
                i6 = i18;
                int i20 = i;
                int i21 = i2;
            }
            int i22 = i6;
            short[] sArr = new short[sphere.max(sphere.mNumIndices)];
            int i23 = i;
            int i24 = 0;
            int i25 = 0;
            int i26 = 0;
            while (i24 < i23) {
                int i27 = i26;
                int i28 = i25;
                int i29 = 0;
                while (i29 < i23) {
                    int i30 = i24 + 1;
                    int i31 = i29 + 1;
                    int[] iArr = sphere.mNumIndices;
                    if (i27 >= iArr[i28]) {
                        sphere.mIndices[i28].put(sArr, 0, iArr[i28]);
                        i28++;
                        i27 = 0;
                    }
                    int i32 = i27 + 1;
                    int i33 = i24 * i22;
                    short s = (short) (i33 + i29);
                    sArr[i27] = s;
                    int i34 = i32 + 1;
                    int i35 = i30 * i22;
                    sArr[i32] = (short) (i29 + i35);
                    int i36 = i34 + 1;
                    short s2 = (short) (i35 + i31);
                    sArr[i34] = s2;
                    int i37 = i36 + 1;
                    sArr[i36] = s;
                    int i38 = i37 + 1;
                    sArr[i37] = s2;
                    i27 = i38 + 1;
                    sArr[i38] = (short) (i33 + i31);
                    i29 = i31;
                }
                i24++;
                i25 = i28;
                i26 = i27;
            }
            sphere.mIndices[i25].put(sArr, 0, sphere.mNumIndices[i25]);
            sphere.mVertices.position(0);
            int i39 = i2;
            for (int i40 = 0; i40 < i39; i40++) {
                sphere.mIndices[i40].position(0);
            }
            return;
        }
        int i41 = i4;
        StringBuilder sb = new StringBuilder();
        sb.append("nSlices ");
        sb.append(i41);
        sb.append(" too big for vertex");
        throw new RuntimeException(sb.toString());
    }

    public FloatBuffer getVertices() {
        return this.mVertices;
    }

    private int max(int[] iArr) {
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (iArr[i2] > i) {
                i = iArr[i2];
            }
        }
        return i;
    }

    public void draw() {
        int i = 0;
        while (true) {
            ShortBuffer[] shortBufferArr = this.mIndices;
            if (i < shortBufferArr.length) {
                GLES20.glDrawElements(4, this.mNumIndices[i], 5123, shortBufferArr[i]);
                i++;
            } else {
                return;
            }
        }
    }
}

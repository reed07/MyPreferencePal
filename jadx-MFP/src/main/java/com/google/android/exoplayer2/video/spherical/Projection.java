package com.google.android.exoplayer2.video.spherical;

import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Projection {
    public static final int DRAW_MODE_TRIANGLES = 0;
    public static final int DRAW_MODE_TRIANGLES_FAN = 2;
    public static final int DRAW_MODE_TRIANGLES_STRIP = 1;
    public static final int POSITION_COORDS_PER_VERTEX = 3;
    public static final int TEXTURE_COORDS_PER_VERTEX = 2;
    public final Mesh leftMesh;
    public final Mesh rightMesh;
    public final boolean singleMesh;
    public final int stereoMode;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DrawMode {
    }

    public static final class Mesh {
        private final SubMesh[] subMeshes;

        public Mesh(SubMesh... subMeshArr) {
            this.subMeshes = subMeshArr;
        }

        public int getSubMeshCount() {
            return this.subMeshes.length;
        }

        public SubMesh getSubMesh(int i) {
            return this.subMeshes[i];
        }
    }

    public static final class SubMesh {
        public static final int VIDEO_TEXTURE_ID = 0;
        public final int mode;
        public final float[] textureCoords;
        public final int textureId;
        public final float[] vertices;

        public SubMesh(int i, float[] fArr, float[] fArr2, int i2) {
            this.textureId = i;
            Assertions.checkArgument(((long) fArr.length) * 2 == ((long) fArr2.length) * 3);
            this.vertices = fArr;
            this.textureCoords = fArr2;
            this.mode = i2;
        }

        public int getVertexCount() {
            return this.vertices.length / 3;
        }
    }

    public static Projection createEquirectangular(int i) {
        return createEquirectangular(50.0f, 36, 72, 180.0f, 360.0f, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0104, code lost:
        if (r3 == 1) goto L_0x0106;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.video.spherical.Projection createEquirectangular(float r29, int r30, int r31, float r32, float r33, int r34) {
        /*
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r32
            r4 = r33
            r5 = 0
            r7 = 1
            int r8 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r8 <= 0) goto L_0x0012
            r8 = 1
            goto L_0x0013
        L_0x0012:
            r8 = 0
        L_0x0013:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r8)
            if (r1 < r7) goto L_0x001a
            r8 = 1
            goto L_0x001b
        L_0x001a:
            r8 = 0
        L_0x001b:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r8)
            if (r2 < r7) goto L_0x0022
            r8 = 1
            goto L_0x0023
        L_0x0022:
            r8 = 0
        L_0x0023:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r8)
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 <= 0) goto L_0x0032
            r8 = 1127481344(0x43340000, float:180.0)
            int r8 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r8 > 0) goto L_0x0032
            r8 = 1
            goto L_0x0033
        L_0x0032:
            r8 = 0
        L_0x0033:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r8)
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x0042
            r5 = 1135869952(0x43b40000, float:360.0)
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x0042
            r5 = 1
            goto L_0x0043
        L_0x0042:
            r5 = 0
        L_0x0043:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r5)
            double r8 = (double) r3
            double r8 = java.lang.Math.toRadians(r8)
            float r3 = (float) r8
            double r4 = (double) r4
            double r4 = java.lang.Math.toRadians(r4)
            float r4 = (float) r4
            float r5 = (float) r1
            float r5 = r3 / r5
            float r8 = (float) r2
            float r8 = r4 / r8
            int r9 = r2 + 1
            int r10 = r9 * 2
            r11 = 2
            int r10 = r10 + r11
            int r10 = r10 * r1
            int r12 = r10 * 3
            float[] r12 = new float[r12]
            int r10 = r10 * 2
            float[] r10 = new float[r10]
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x006b:
            if (r13 >= r1) goto L_0x0166
            float r6 = (float) r13
            float r6 = r6 * r5
            r16 = 1073741824(0x40000000, float:2.0)
            float r17 = r3 / r16
            float r6 = r6 - r17
            int r7 = r13 + 1
            float r11 = (float) r7
            float r11 = r11 * r5
            float r11 = r11 - r17
            r17 = r15
            r15 = r14
            r14 = 0
        L_0x0081:
            if (r14 >= r9) goto L_0x0155
            r18 = r17
            r1 = 2
            r17 = r15
            r15 = 0
        L_0x0089:
            if (r15 >= r1) goto L_0x0136
            if (r15 != 0) goto L_0x0092
            r33 = r6
            r1 = r33
            goto L_0x0095
        L_0x0092:
            r33 = r6
            r1 = r11
        L_0x0095:
            float r6 = (float) r14
            float r6 = r6 * r8
            r19 = 1078530011(0x40490fdb, float:3.1415927)
            float r19 = r6 + r19
            float r20 = r4 / r16
            r21 = r7
            float r7 = r19 - r20
            int r19 = r17 + 1
            r20 = r8
            r22 = r9
            double r8 = (double) r0
            r23 = r3
            double r2 = (double) r7
            double r24 = java.lang.Math.sin(r2)
            double r24 = r24 * r8
            double r0 = (double) r1
            double r26 = java.lang.Math.cos(r0)
            r7 = r13
            r28 = r14
            double r13 = r24 * r26
            float r13 = (float) r13
            float r13 = -r13
            r12[r17] = r13
            int r13 = r19 + 1
            double r24 = java.lang.Math.sin(r0)
            r26 = r15
            double r14 = r8 * r24
            float r14 = (float) r14
            r12[r19] = r14
            int r14 = r13 + 1
            double r2 = java.lang.Math.cos(r2)
            double r8 = r8 * r2
            double r0 = java.lang.Math.cos(r0)
            double r8 = r8 * r0
            float r0 = (float) r8
            r12[r13] = r0
            int r0 = r18 + 1
            float r6 = r6 / r4
            r10[r18] = r6
            int r1 = r0 + 1
            int r13 = r7 + r26
            float r2 = (float) r13
            float r2 = r2 * r5
            float r2 = r2 / r23
            r10[r0] = r2
            if (r28 != 0) goto L_0x00fb
            if (r26 == 0) goto L_0x00f4
            goto L_0x00fb
        L_0x00f4:
            r3 = r26
            r2 = r28
            r0 = r31
            goto L_0x0106
        L_0x00fb:
            r2 = r28
            r0 = r31
            if (r2 != r0) goto L_0x011b
            r3 = r26
            r6 = 1
            if (r3 != r6) goto L_0x011d
        L_0x0106:
            int r6 = r14 + -3
            r8 = 3
            java.lang.System.arraycopy(r12, r6, r12, r14, r8)
            int r14 = r14 + 3
            int r6 = r1 + -2
            r8 = 2
            java.lang.System.arraycopy(r10, r6, r10, r1, r8)
            int r1 = r1 + 2
            r18 = r1
            r17 = r14
            goto L_0x0122
        L_0x011b:
            r3 = r26
        L_0x011d:
            r8 = 2
            r18 = r1
            r17 = r14
        L_0x0122:
            int r15 = r3 + 1
            r6 = r33
            r14 = r2
            r13 = r7
            r8 = r20
            r7 = r21
            r9 = r22
            r3 = r23
            r1 = 2
            r2 = r0
            r0 = r29
            goto L_0x0089
        L_0x0136:
            r0 = r2
            r23 = r3
            r33 = r6
            r21 = r7
            r20 = r8
            r22 = r9
            r7 = r13
            r2 = r14
            r8 = 2
            int r14 = r2 + 1
            r2 = r0
            r15 = r17
            r17 = r18
            r8 = r20
            r7 = r21
            r0 = r29
            r1 = r30
            goto L_0x0081
        L_0x0155:
            r21 = r7
            r20 = r8
            r14 = r15
            r15 = r17
            r13 = r21
            r0 = r29
            r1 = r30
            r7 = 1
            r11 = 2
            goto L_0x006b
        L_0x0166:
            com.google.android.exoplayer2.video.spherical.Projection$SubMesh r0 = new com.google.android.exoplayer2.video.spherical.Projection$SubMesh
            r1 = 1
            r2 = 0
            r0.<init>(r2, r12, r10, r1)
            com.google.android.exoplayer2.video.spherical.Projection r3 = new com.google.android.exoplayer2.video.spherical.Projection
            com.google.android.exoplayer2.video.spherical.Projection$Mesh r4 = new com.google.android.exoplayer2.video.spherical.Projection$Mesh
            com.google.android.exoplayer2.video.spherical.Projection$SubMesh[] r1 = new com.google.android.exoplayer2.video.spherical.Projection.SubMesh[r1]
            r1[r2] = r0
            r4.<init>(r1)
            r0 = r34
            r3.<init>(r4, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.spherical.Projection.createEquirectangular(float, int, int, float, float, int):com.google.android.exoplayer2.video.spherical.Projection");
    }

    public Projection(Mesh mesh, int i) {
        this(mesh, mesh, i);
    }

    public Projection(Mesh mesh, Mesh mesh2, int i) {
        this.leftMesh = mesh;
        this.rightMesh = mesh2;
        this.stereoMode = i;
        this.singleMesh = mesh == mesh2;
    }
}

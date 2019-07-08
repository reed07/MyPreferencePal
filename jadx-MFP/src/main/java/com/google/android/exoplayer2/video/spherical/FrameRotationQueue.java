package com.google.android.exoplayer2.video.spherical;

import android.opengl.Matrix;
import com.google.android.exoplayer2.util.TimedValueQueue;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public final class FrameRotationQueue {
    private final float[] recenterMatrix = new float[16];
    private boolean recenterMatrixComputed;
    private final float[] rotationMatrix = new float[16];
    private final TimedValueQueue<float[]> rotations = new TimedValueQueue<>();

    public void setRotation(long j, float[] fArr) {
        this.rotations.add(j, fArr);
    }

    public void reset() {
        this.rotations.clear();
        this.recenterMatrixComputed = false;
    }

    public boolean pollRotationMatrix(float[] fArr, long j) {
        float[] fArr2 = (float[]) this.rotations.pollFloor(j);
        if (fArr2 == null) {
            return false;
        }
        getRotationMatrixFromAngleAxis(this.rotationMatrix, fArr2);
        if (!this.recenterMatrixComputed) {
            computeRecenterMatrix(this.recenterMatrix, this.rotationMatrix);
            this.recenterMatrixComputed = true;
        }
        Matrix.multiplyMM(fArr, 0, this.recenterMatrix, 0, this.rotationMatrix, 0);
        return true;
    }

    private static void computeRecenterMatrix(float[] fArr, float[] fArr2) {
        Matrix.setIdentityM(fArr, 0);
        float sqrt = (float) Math.sqrt((double) ((fArr2[10] * fArr2[10]) + (fArr2[8] * fArr2[8])));
        fArr[0] = fArr2[10] / sqrt;
        fArr[2] = fArr2[8] / sqrt;
        fArr[8] = (-fArr2[8]) / sqrt;
        fArr[10] = fArr2[10] / sqrt;
    }

    private static void getRotationMatrixFromAngleAxis(float[] fArr, float[] fArr2) {
        float f = fArr2[0];
        float f2 = -fArr2[1];
        float f3 = -fArr2[2];
        float length = Matrix.length(f, f2, f3);
        if (length != BitmapDescriptorFactory.HUE_RED) {
            Matrix.setRotateM(fArr, 0, (float) Math.toDegrees((double) length), f / length, f2 / length, f3 / length);
            return;
        }
        Matrix.setIdentityM(fArr, 0);
    }
}

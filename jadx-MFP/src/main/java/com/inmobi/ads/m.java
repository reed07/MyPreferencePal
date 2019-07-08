package com.inmobi.ads;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: AnimationController */
final class m {

    /* compiled from: AnimationController */
    static class a extends Animation {
        private final float a = BitmapDescriptorFactory.HUE_RED;
        private final float b = 90.0f;
        private final float c;
        private final float d;
        private final float e;
        private final boolean f;
        private Camera g;

        public a(float f2, float f3) {
            this.c = f2;
            this.d = f3;
            this.e = BitmapDescriptorFactory.HUE_RED;
            this.f = true;
        }

        public final void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.g = new Camera();
        }

        /* access modifiers changed from: protected */
        public final void applyTransformation(float f2, Transformation transformation) {
            float f3 = this.a;
            float f4 = f3 + ((this.b - f3) * f2);
            float f5 = this.c;
            float f6 = this.d;
            Camera camera = this.g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f) {
                camera.translate(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, this.e * f2);
            } else {
                camera.translate(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, this.e * (1.0f - f2));
            }
            camera.rotateX(f4);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f5, -f6);
            matrix.postTranslate(f5, f6);
        }
    }

    /* compiled from: AnimationController */
    static class b extends Animation {
        private final float a = BitmapDescriptorFactory.HUE_RED;
        private final float b = 90.0f;
        private final float c;
        private final float d;
        private final float e;
        private final boolean f;
        private Camera g;

        public b(float f2, float f3) {
            this.c = f2;
            this.d = f3;
            this.e = BitmapDescriptorFactory.HUE_RED;
            this.f = true;
        }

        public final void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.g = new Camera();
        }

        /* access modifiers changed from: protected */
        public final void applyTransformation(float f2, Transformation transformation) {
            float f3 = this.a;
            float f4 = f3 + ((this.b - f3) * f2);
            float f5 = this.c;
            float f6 = this.d;
            Camera camera = this.g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f) {
                camera.translate(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, this.e * f2);
            } else {
                camera.translate(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, this.e * (1.0f - f2));
            }
            camera.rotateY(f4);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f5, -f6);
            matrix.postTranslate(f5, f6);
        }
    }
}

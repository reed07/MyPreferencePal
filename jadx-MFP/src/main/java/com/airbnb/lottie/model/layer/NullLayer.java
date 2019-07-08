package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class NullLayer extends BaseLayer {
    /* access modifiers changed from: 0000 */
    public void drawLayer(Canvas canvas, Matrix matrix, int i) {
    }

    NullLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
    }

    public void getBounds(RectF rectF, Matrix matrix) {
        super.getBounds(rectF, matrix);
        rectF.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
    }
}

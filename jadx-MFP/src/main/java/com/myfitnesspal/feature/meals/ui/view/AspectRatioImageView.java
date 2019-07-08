package com.myfitnesspal.feature.meals.ui.view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

public class AspectRatioImageView extends AppCompatImageView {
    private float aspectRatio = 1.0f;

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAspectRatio(float f) {
        this.aspectRatio = f;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, (int) (((float) size) * this.aspectRatio));
    }
}

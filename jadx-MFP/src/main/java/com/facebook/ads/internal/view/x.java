package com.facebook.ads.internal.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class x extends ImageView {
    @Nullable
    private ScaleType a;

    public x(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        int size2 = MeasureSpec.getSize(i);
        if (MeasureSpec.getMode(i) == 1073741824 && MeasureSpec.getMode(i2) == 1073741824) {
            int min = Math.min(size2, size);
            setMeasuredDimension(min, min);
        } else if (MeasureSpec.getMode(i) == 1073741824) {
            if (size > 0) {
                size2 = Math.min(size2, size);
            }
            setMeasuredDimension(size2, size2);
        } else if (MeasureSpec.getMode(i2) == 1073741824) {
            if (size2 > 0) {
                size = Math.min(size2, size);
            }
            setMeasuredDimension(size, size);
        } else {
            super.onMeasure(i, i2);
        }
        ScaleType scaleType = this.a;
        if (scaleType != null) {
            super.setScaleType(scaleType);
        }
    }

    public void setScaleType(ScaleType scaleType) {
        this.a = scaleType;
    }
}

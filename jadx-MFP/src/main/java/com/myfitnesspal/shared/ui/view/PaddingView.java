package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;

public final class PaddingView extends View {
    private int width;

    public PaddingView(Context context, int i) {
        super(context);
        setWidth(i);
    }

    public void setWidth(int i) {
        if (i != this.width) {
            this.width = i;
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(this.width, 1073741824), MeasureSpec.makeMeasureSpec(1, 1073741824));
    }
}

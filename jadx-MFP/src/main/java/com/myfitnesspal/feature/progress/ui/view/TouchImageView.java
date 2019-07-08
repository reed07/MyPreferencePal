package com.myfitnesspal.feature.progress.ui.view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View.OnTouchListener;

public class TouchImageView extends AppCompatImageView {
    private OnTouchListener lastOnTouchListener;

    public TouchImageView(Context context) {
        super(context);
    }

    public TouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TouchImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.lastOnTouchListener = onTouchListener;
        super.setOnTouchListener(onTouchListener);
    }

    public OnTouchListener getOnTouchListener() {
        return this.lastOnTouchListener;
    }
}

package com.myfitnesspal.feature.home.util;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;

public class InternalURLSpan extends ClickableSpan {
    int color;
    OnClickListener mListener;

    public InternalURLSpan(OnClickListener onClickListener, int i) {
        this.mListener = onClickListener;
        this.color = i;
    }

    public void onClick(View view) {
        this.mListener.onClick(view);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
        textPaint.setColor(this.color);
    }
}

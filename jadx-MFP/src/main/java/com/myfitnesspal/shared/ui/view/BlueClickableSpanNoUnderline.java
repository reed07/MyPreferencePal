package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.myfitnesspal.android.R;

public class BlueClickableSpanNoUnderline extends ClickableSpan {
    Context context;

    public void onClick(View view) {
    }

    public BlueClickableSpanNoUnderline(Context context2) {
        this.context = context2;
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
        textPaint.setColor(this.context.getResources().getColor(R.color.blue));
    }

    public Context getContext() {
        return this.context;
    }
}

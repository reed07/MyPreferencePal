package com.myfitnesspal.feature.registration.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.myfitnesspal.android.R;

public class PageIndicatorBar extends LinearLayout {
    private Context context;

    public PageIndicatorBar(Context context2) {
        super(context2);
        this.context = context2;
    }

    public PageIndicatorBar(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        this.context = context2;
    }

    public void setProgressAndMax(int i, int i2) {
        int i3;
        int round = Math.round(TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        LinearLayout linearLayout = new LinearLayout(this.context);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        for (int i4 = 1; i4 <= i2; i4++) {
            LayoutParams layoutParams2 = new LayoutParams(-1, Math.round((float) round), 1.0f);
            LinearLayout linearLayout2 = new LinearLayout(this.context);
            linearLayout2.setLayoutParams(layoutParams2);
            if (i4 <= i) {
                i3 = getResources().getColor(R.color.material_sign_up_pager_indicator_green);
            } else {
                i3 = getResources().getColor(R.color.material_sign_up_pager_indicator_gray);
            }
            linearLayout2.setBackgroundColor(i3);
            linearLayout.addView(linearLayout2);
            if (i4 != i2) {
                LayoutParams layoutParams3 = new LayoutParams(round, round);
                LinearLayout linearLayout3 = new LinearLayout(this.context);
                linearLayout3.setLayoutParams(layoutParams3);
                linearLayout.addView(linearLayout3);
            }
        }
        addView(linearLayout);
    }
}

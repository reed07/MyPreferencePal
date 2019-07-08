package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CustomTabLayout extends TabLayout {
    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setTapEnabled(boolean z) {
        LinearLayout linearLayout = (LinearLayout) getChildAt(0);
        if (linearLayout != null) {
            linearLayout.setEnabled(z);
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                linearLayout.getChildAt(i).setClickable(z);
            }
        }
    }
}

package com.shinobicontrols.charts;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.List;

class ax extends LinearLayout {
    private final float a = getResources().getDisplayMetrics().density;

    public ax(Context context) {
        super(context);
        setOrientation(0);
    }

    /* access modifiers changed from: 0000 */
    public void a(List<av> list, int i) {
        removeAllViews();
        if (i != 0) {
            int min = Math.min(list.size(), i);
            a(min);
            for (int i2 = 0; i2 < list.size(); i2++) {
                av avVar = (av) list.get(i2);
                View childAt = getChildAt(i2 % min);
                if (childAt instanceof aw) {
                    ((aw) childAt).a(avVar);
                }
            }
        }
    }

    private void a(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            aw awVar = new aw(getContext());
            awVar.setLayoutParams(new LayoutParams(-2, -2));
            addView(awVar);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(LegendStyle legendStyle) {
        int a2 = at.a(this.a, legendStyle.getSymbolLabelGap() / 2.0f);
        int i = 0;
        while (i < getChildCount()) {
            View childAt = getChildAt(i);
            if (childAt instanceof aw) {
                aw awVar = (aw) childAt;
                awVar.a(legendStyle);
                a(awVar, i > 0 ? a2 : 0, i < getChildCount() + -1 ? a2 : 0);
            }
            i++;
        }
    }

    private void a(aw awVar, int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) awVar.getLayoutParams();
        layoutParams.leftMargin = i;
        layoutParams.rightMargin = i2;
    }
}

package com.shinobicontrols.charts;

import android.content.Context;
import android.graphics.Paint.FontMetrics;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.shinobicontrols.charts.Legend.SymbolAlignment;

class aw extends LinearLayout {
    private final LinearLayout a;
    private final LinearLayout b;
    private final float c = getResources().getDisplayMetrics().density;

    public aw(Context context) {
        super(context);
        setOrientation(0);
        this.a = new LinearLayout(context);
        this.a.setLayoutParams(new LayoutParams(-2, -2));
        this.a.setOrientation(1);
        this.b = new LinearLayout(context);
        this.b.setLayoutParams(new LayoutParams(-2, -2));
        this.b.setOrientation(1);
    }

    /* access modifiers changed from: 0000 */
    public void a(av avVar) {
        ay b2 = avVar.b();
        b2.setLayoutParams(new LayoutParams(0, 0));
        this.a.addView(b2);
        TextView a2 = avVar.a();
        a2.setLayoutParams(new LayoutParams(-2, -2));
        this.b.addView(a2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.b.measure(i, i2);
        int i3 = 0;
        if (this.b.getChildAt(0) != null) {
            FontMetrics fontMetrics = ((TextView) this.b.getChildAt(0)).getPaint().getFontMetrics();
            int ceil = ((int) Math.ceil((double) (fontMetrics.bottom - fontMetrics.top))) + ((TextView) this.b.getChildAt(0)).getPaddingTop() + ((TextView) this.b.getChildAt(0)).getPaddingBottom();
            if (this.a.getVisibility() == 0) {
                for (int i4 = 0; i4 < this.a.getChildCount(); i4++) {
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.a.getChildAt(i4).getLayoutParams();
                    marginLayoutParams.height = ceil;
                    MarginLayoutParams marginLayoutParams2 = (MarginLayoutParams) this.b.getChildAt(i4).getLayoutParams();
                    double measuredHeight = (((double) this.b.getChildAt(i4).getMeasuredHeight()) - ((double) ceil)) / 2.0d;
                    int floor = (int) Math.floor(measuredHeight);
                    int i5 = marginLayoutParams2.topMargin + floor;
                    int ceil2 = marginLayoutParams2.bottomMargin + ((int) Math.ceil(measuredHeight));
                    if (i5 < 0) {
                        i5 = 0;
                    }
                    if (ceil2 < 0) {
                        ceil2 = 0;
                    }
                    marginLayoutParams.topMargin = i5;
                    marginLayoutParams.bottomMargin = ceil2;
                }
                this.a.measure(i, i2);
                i3 = at.b(this.a);
            }
            setMeasuredDimension(View.resolveSize(i3 + at.b(this.b), i), View.resolveSize(at.a(this.b), i2));
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(LegendStyle legendStyle) {
        a(legendStyle.areSymbolsShown());
        int a2 = at.a(this.c, legendStyle.getRowVerticalMargin() / 2.0f);
        a(legendStyle.getSymbolAlignment(), legendStyle.getSymbolLabelGap());
        int a3 = at.a(this.c, legendStyle.getSymbolWidth());
        int childCount = this.a.getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = this.a.getChildAt(i);
            a(childAt, legendStyle.getSymbolCornerRadius());
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            layoutParams.width = a3;
            layoutParams.topMargin = i > 0 ? a2 : 0;
            layoutParams.bottomMargin = i < childCount + -1 ? a2 : 0;
            i++;
        }
        int childCount2 = this.b.getChildCount();
        int i2 = 0;
        while (i2 < childCount2) {
            View childAt2 = this.b.getChildAt(i2);
            a(childAt2, legendStyle.getTypeface(), legendStyle.getTextSize(), legendStyle.getTextColor(), legendStyle.getTextAlignment());
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            layoutParams2.gravity = legendStyle.getTextAlignment();
            layoutParams2.topMargin = i2 > 0 ? a2 : 0;
            layoutParams2.bottomMargin = i2 < childCount2 + -1 ? a2 : 0;
            i2++;
        }
        a(legendStyle.getSymbolAlignment());
    }

    private void a(boolean z) {
        this.a.setVisibility(z ? 0 : 8);
    }

    private void a(SymbolAlignment symbolAlignment, float f) {
        int a2 = at.a(this.c, f / 2.0f);
        if (symbolAlignment == SymbolAlignment.LEFT) {
            ((LayoutParams) this.a.getLayoutParams()).setMargins(0, 0, a2, 0);
            ((LayoutParams) this.b.getLayoutParams()).setMargins(a2, 0, 0, 0);
            return;
        }
        ((LayoutParams) this.b.getLayoutParams()).setMargins(0, 0, a2, 0);
        ((LayoutParams) this.a.getLayoutParams()).setMargins(a2, 0, 0, 0);
    }

    private void a(View view, Typeface typeface, float f, int i, int i2) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setTypeface(typeface);
            textView.setTextSize(2, f);
            textView.setTextColor(i);
            textView.setGravity(i2);
        }
    }

    private void a(View view, float f) {
        if (view instanceof ay) {
            Drawable a2 = ((ay) view).a();
            if (a2 instanceof GradientDrawable) {
                ((GradientDrawable) a2).setCornerRadius(f);
            }
        }
    }

    private void a(SymbolAlignment symbolAlignment) {
        if (this.a.getVisibility() != 0) {
            addView(this.b);
        } else if (symbolAlignment == SymbolAlignment.LEFT) {
            addView(this.a);
            addView(this.b);
        } else {
            addView(this.b);
            addView(this.a);
        }
    }
}

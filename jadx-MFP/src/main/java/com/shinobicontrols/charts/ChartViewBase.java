package com.shinobicontrols.charts;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.FrameLayout;

abstract class ChartViewBase extends FrameLayout {
    private final v a;

    /* access modifiers changed from: 0000 */
    public abstract v a(Context context, AttributeSet attributeSet, int i);

    ChartViewBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = a(getContext(), attributeSet, i);
        addView(this.a);
    }

    public final ShinobiChart getShinobiChart() {
        return this.a;
    }

    public final void onCreate(Bundle bundle) {
        this.a.a(bundle);
    }

    public void onResume() {
        this.a.c();
    }

    public void onPause() {
        this.a.d();
    }

    public final void onDestroy() {
        this.a.e();
    }
}

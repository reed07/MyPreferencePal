package com.facebook.ads.internal.t;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public abstract class f extends RelativeLayout {
    public f(Context context) {
        super(context);
    }

    public f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public f(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @RequiresApi
    public f(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public abstract View getAdContentsView();
}

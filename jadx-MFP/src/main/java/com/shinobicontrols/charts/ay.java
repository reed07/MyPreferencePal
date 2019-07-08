package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;

class ay extends View {
    private final Drawable a;

    @SuppressLint({"NewApi", "ViewConstructor"})
    public ay(Context context, Drawable drawable) {
        super(context);
        this.a = drawable;
        if (VERSION.SDK_INT >= 16) {
            setBackground(this.a);
        } else {
            setBackgroundDrawable(this.a);
        }
    }

    /* access modifiers changed from: 0000 */
    public Drawable a() {
        return this.a;
    }
}

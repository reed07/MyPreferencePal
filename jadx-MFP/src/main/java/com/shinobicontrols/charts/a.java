package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;

class a {
    @SuppressLint({"NewApi"})
    static void a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    static bw a(Context context, v vVar) {
        if (VERSION.SDK_INT >= 14) {
            return new bx(context, vVar);
        }
        return new by(context, vVar);
    }
}

package com.facebook.ads.internal.view.i.b;

import android.view.MotionEvent;
import android.view.View;
import com.facebook.ads.internal.o.d;

public class u extends d {
    private final View a;
    private final MotionEvent b;

    public u(View view, MotionEvent motionEvent) {
        this.a = view;
        this.b = motionEvent;
    }

    public MotionEvent a() {
        return this.b;
    }
}

package com.facebook.ads.internal.view.g;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.component.g;

class e extends g {
    private final ImageView b;

    public e(Context context) {
        super(context);
        this.b = new ImageView(context);
        this.b.setAdjustViewBounds(true);
        addView(this.b, new LayoutParams(-2, -1));
    }

    public void a(String str) {
        d dVar = new d(this.b);
        dVar.a();
        dVar.a(str);
    }
}

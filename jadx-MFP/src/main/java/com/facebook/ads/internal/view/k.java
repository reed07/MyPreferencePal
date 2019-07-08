package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.internal.t.f;
import com.facebook.ads.internal.w.b.j;
import com.facebook.ads.internal.w.b.x;

public class k extends f {
    private static final int a = ((int) (x.b * 1.0f));
    private final ImageView b;

    public k(Context context) {
        super(context);
        this.b = new x(context);
        this.b.setScaleType(ScaleType.CENTER_CROP);
        j.a(this.b, j.INTERNAL_AD_MEDIA);
        addView(this.b, new LayoutParams(-1, -1));
        x.a((View) this.b, -2130706433);
        int i = a;
        setPadding(i, i, i, i);
    }

    public View getAdContentsView() {
        return this.b;
    }

    public ImageView getImageCardView() {
        return this.b;
    }
}

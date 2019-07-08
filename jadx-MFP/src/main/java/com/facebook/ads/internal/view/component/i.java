package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class i extends LinearLayout {
    private static final int a = ((int) (x.b * 16.0f));
    private static final int b = ((int) (x.b * 14.0f));
    private static final int c = ColorUtils.setAlphaComponent(-1, 77);
    private final CircularProgressView d;
    private final TextView e;

    public i(Context context) {
        super(context);
        setOrientation(0);
        setGravity(16);
        this.d = new CircularProgressView(context);
        CircularProgressView circularProgressView = this.d;
        int i = a;
        circularProgressView.setPadding(i, i, i, i);
        this.d.setProgress(BitmapDescriptorFactory.HUE_RED);
        a(c, -1);
        this.e = new TextView(context);
        a(false, -1, b);
        addView(this.d);
        addView(this.e);
    }

    public void a(int i, int i2) {
        this.d.a(i, i2);
    }

    public void a(boolean z, int i, int i2) {
        x.a(this.e, z, i2);
        this.e.setTextColor(i);
    }

    public void setProgress(int i) {
        this.d.setProgressWithAnimation((float) i);
    }

    public void setText(String str) {
        this.e.setText(str);
    }
}

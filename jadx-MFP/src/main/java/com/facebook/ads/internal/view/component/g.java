package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class g extends RelativeLayout {
    protected int a = ((int) (x.b * 4.0f));
    private final Path b = new Path();
    private final RectF c = new RectF();

    public g(Context context) {
        super(context);
        x.a((View) this, 0);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.c.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) getWidth(), (float) getHeight());
        this.b.reset();
        Path path = this.b;
        RectF rectF = this.c;
        int i = this.a;
        path.addRoundRect(rectF, (float) i, (float) i, Direction.CW);
        canvas.clipPath(this.b);
        super.onDraw(canvas);
    }

    public void setCornerRadius(int i) {
        this.a = (int) (((float) i) * x.b);
    }
}

package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.widget.ImageView;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class f extends ImageView {
    private static final int a = ((int) (x.b * 8.0f));
    private static final float[] b;
    private final Path c = new Path();
    private final RectF d = new RectF();
    private float[] e = b;
    private boolean f = false;

    static {
        int i = a;
        b = new float[]{(float) i, (float) i, (float) i, (float) i, (float) i, (float) i, (float) i, (float) i};
    }

    public f(Context context) {
        super(context);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    private float[] getRadiiForCircularImage() {
        float min = (float) (Math.min(getWidth(), getHeight()) / 2);
        return new float[]{min, min, min, min, min, min, min, min};
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.d.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) getWidth(), (float) getHeight());
        this.c.reset();
        this.c.addRoundRect(this.d, this.f ? getRadiiForCircularImage() : this.e, Direction.CW);
        canvas.clipPath(this.c);
        super.onDraw(canvas);
    }

    public void setFullCircleCorners(boolean z) {
        this.f = z;
    }

    public void setRadius(int i) {
        float f2 = (float) ((int) (((float) i) * x.b));
        this.e = new float[]{f2, f2, f2, f2, f2, f2, f2, f2};
    }

    public void setRadius(float[] fArr) {
        this.e = fArr;
    }
}

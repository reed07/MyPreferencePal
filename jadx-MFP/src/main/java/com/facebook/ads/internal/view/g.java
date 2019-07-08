package com.facebook.ads.internal.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.w.b.x;

public class g extends View {
    private Paint a;
    private Paint b;
    private Paint c;
    private int d;
    private boolean e;

    public g(Context context) {
        this(context, 60, true);
    }

    public g(Context context, int i, boolean z) {
        super(context);
        this.d = i;
        this.e = z;
        if (z) {
            this.a = new Paint();
            this.a.setColor(-3355444);
            this.a.setStyle(Style.STROKE);
            this.a.setStrokeWidth(3.0f);
            this.a.setAntiAlias(true);
            this.b = new Paint();
            this.b.setColor(-1287371708);
            this.b.setStyle(Style.FILL);
            this.b.setAntiAlias(true);
            this.c = new Paint();
            this.c.setColor(-1);
            this.c.setStyle(Style.STROKE);
            this.c.setStrokeWidth(6.0f);
            this.c.setAntiAlias(true);
        }
        float f = x.b;
        int i2 = this.d;
        LayoutParams layoutParams = new LayoutParams((int) (((float) i2) * f), (int) (((float) i2) * f));
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.e) {
            if (canvas.isHardwareAccelerated() && VERSION.SDK_INT < 17) {
                setLayerType(1, null);
            }
            int min = Math.min(canvas.getWidth(), canvas.getHeight());
            int i = min / 2;
            int i2 = (i * 2) / 3;
            float f = (float) i;
            canvas.drawCircle(f, f, (float) i2, this.a);
            canvas.drawCircle(f, f, (float) (i2 - 2), this.b);
            int i3 = min / 3;
            float f2 = (float) i3;
            float f3 = (float) (i3 * 2);
            Canvas canvas2 = canvas;
            float f4 = f2;
            float f5 = f3;
            canvas2.drawLine(f2, f4, f3, f5, this.c);
            canvas2.drawLine(f3, f4, f2, f5, this.c);
        }
        super.onDraw(canvas);
    }
}

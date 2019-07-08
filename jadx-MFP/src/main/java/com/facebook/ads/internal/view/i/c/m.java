package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.Button;
import com.facebook.ads.internal.w.b.x;

public class m extends Button {
    private final Path a;
    private final Path b;
    private final Paint c;
    private final Path d;
    private boolean e;

    public m(Context context) {
        this(context, false);
    }

    public m(Context context, final boolean z) {
        super(context);
        this.e = false;
        this.a = new Path();
        this.b = new Path();
        this.d = new Path();
        this.c = new Paint() {
            {
                setStyle(Style.FILL_AND_STROKE);
                setStrokeCap(Cap.ROUND);
                setStrokeWidth(3.0f);
                setAntiAlias(true);
                setColor(z ? -1 : -10066330);
            }
        };
        setClickable(true);
        x.a((View) this, 0);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Path path;
        if (canvas.isHardwareAccelerated() && VERSION.SDK_INT < 17) {
            setLayerType(1, null);
        }
        float max = ((float) Math.max(canvas.getWidth(), canvas.getHeight())) / 100.0f;
        if (this.e) {
            this.d.rewind();
            float f = 26.5f * max;
            float f2 = 15.5f * max;
            this.d.moveTo(f, f2);
            this.d.lineTo(f, 84.5f * max);
            this.d.lineTo(90.0f * max, max * 50.0f);
            this.d.lineTo(f, f2);
            this.d.close();
            path = this.d;
        } else {
            this.a.rewind();
            float f3 = 29.0f * max;
            float f4 = 21.0f * max;
            this.a.moveTo(f3, f4);
            float f5 = 79.0f * max;
            this.a.lineTo(f3, f5);
            float f6 = 45.0f * max;
            this.a.lineTo(f6, f5);
            this.a.lineTo(f6, f4);
            this.a.lineTo(f3, f4);
            this.a.close();
            this.b.rewind();
            float f7 = 55.0f * max;
            this.b.moveTo(f7, f4);
            this.b.lineTo(f7, f5);
            float f8 = max * 71.0f;
            this.b.lineTo(f8, f5);
            this.b.lineTo(f8, f4);
            this.b.lineTo(f7, f4);
            this.b.close();
            canvas.drawPath(this.a, this.c);
            path = this.b;
        }
        canvas.drawPath(path, this.c);
        super.onDraw(canvas);
    }

    public void setChecked(boolean z) {
        this.e = z;
        refreshDrawableState();
        invalidate();
    }
}

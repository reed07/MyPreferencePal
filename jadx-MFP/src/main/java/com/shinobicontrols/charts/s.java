package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.shinobicontrols.charts.Annotation.Position;

@SuppressLint({"ViewConstructor"})
class s extends ViewGroup {
    final w a;
    private final Paint b = new Paint();
    private Bitmap c;

    s(Context context, w wVar) {
        super(context);
        setWillNotDraw(false);
        this.a = wVar;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        this.a.a.l.a(MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE), Position.BEHIND_DATA);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.a.a.l.a(this.a.b.left, this.a.b.top, this.a.b.right, this.a.b.bottom, Position.BEHIND_DATA);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.b.setColor(this.a.a.getStyle().getPlotAreaBackgroundColor());
        canvas.drawRect(this.a.b, this.b);
        this.a.a(canvas);
        canvas.clipRect(this.a.b);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, null, this.a.b, this.b);
            this.c = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Bitmap bitmap) {
        this.c = bitmap;
    }
}

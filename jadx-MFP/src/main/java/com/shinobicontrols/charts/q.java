package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.Annotation.Position;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;

@SuppressLint({"ViewConstructor"})
class q extends ViewGroup {
    final w a;
    private final bn b;
    private final cy c;
    private float d = BitmapDescriptorFactory.HUE_RED;
    private float e = BitmapDescriptorFactory.HUE_RED;

    q(Context context, w wVar) {
        super(context);
        setWillNotDraw(false);
        this.a = wVar;
        this.b = new bn(wVar.a);
        this.c = new cy(wVar.a, this.b);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        motionEvent.offsetLocation(this.d, this.e);
        return this.c.a(motionEvent) || super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE);
        if (this.a.a.h != null) {
            this.a.a.h.a(makeMeasureSpec, makeMeasureSpec2);
        }
        this.a.a.l.a(makeMeasureSpec, makeMeasureSpec2, Position.IN_FRONT_OF_DATA);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.a.a.h != null) {
            this.a.a.h.a(this.a.b.left, this.a.b.top, this.a.b.right, this.a.b.bottom);
        }
        this.a.a.l.a(this.a.b.left, this.a.b.top, this.a.b.right, this.a.b.bottom, Position.IN_FRONT_OF_DATA);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.clipRect(this.a.b);
        this.a.b(canvas);
        this.a.c(canvas);
    }

    /* access modifiers changed from: 0000 */
    public void a(OnGestureListener onGestureListener) {
        this.b.a(onGestureListener);
    }

    /* access modifiers changed from: 0000 */
    public void b(OnGestureListener onGestureListener) {
        this.b.b(onGestureListener);
    }

    /* access modifiers changed from: 0000 */
    public void c(OnGestureListener onGestureListener) {
        this.b.c(onGestureListener);
    }

    /* access modifiers changed from: 0000 */
    public void d(OnGestureListener onGestureListener) {
        this.b.d(onGestureListener);
    }

    /* access modifiers changed from: 0000 */
    public void a(float f) {
        this.d = f;
    }

    /* access modifiers changed from: 0000 */
    public void b(float f) {
        this.e = f;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.c.a();
    }
}

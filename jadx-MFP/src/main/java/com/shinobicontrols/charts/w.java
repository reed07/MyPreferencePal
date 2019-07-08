package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.shinobicontrols.charts.Axis.Position;
import com.shinobicontrols.charts.ShinobiChart.OnGestureListener;

@SuppressLint({"ViewConstructor"})
class w extends ViewGroup {
    final v a;
    final Rect b = new Rect();
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    private bw i;
    private q j;
    private s k;

    w(Context context, v vVar) {
        super(context);
        this.a = vVar;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        a(measuredWidth, measuredHeight);
        this.g = (measuredWidth - this.e) - this.f;
        this.h = (measuredHeight - this.d) - this.c;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.j.measure(makeMeasureSpec, makeMeasureSpec2);
        this.k.measure(makeMeasureSpec, makeMeasureSpec2);
    }

    private void a(int i2, int i3) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        if (i2 > 0) {
            for (Axis<?, ?> axis : this.a.e.a) {
                a(axis, makeMeasureSpec, makeMeasureSpec2);
                axis.d(i2);
                axis.a(true);
            }
        }
        if (i3 > 0) {
            for (Axis<?, ?> axis2 : this.a.f.a) {
                a(axis2, makeMeasureSpec, makeMeasureSpec2);
                axis2.d(i3);
                axis2.a(true);
            }
        }
        a(this.a.e);
        a(this.a.f);
        k();
    }

    private void a(Axis<?, ?> axis, int i2, int i3) {
        Title t = axis.t();
        if (t != null && t.getVisibility() != 8) {
            measureChildWithMargins(t, i2, 0, i3, 0);
        }
    }

    private static void a(h hVar) {
        int i2;
        if (hVar.b()) {
            Axis a2 = hVar.a();
            int i3 = 0;
            if (a2.d == Position.REVERSE) {
                i3 = 0 - a2.o;
                i2 = 0;
            } else {
                i2 = a2.o + 0;
            }
            for (int i4 = 1; i4 < hVar.a.length; i4++) {
                Axis<?, ?> axis = hVar.a[i4];
                if (axis.d == Position.REVERSE) {
                    axis.h = i3;
                    i3 -= axis.o;
                } else {
                    axis.h = i2;
                    i2 += axis.o;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (i2 <= i4 && i3 <= i5) {
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            this.b.set(0, 0, i6, i7);
            this.b.left += this.e;
            this.b.top += this.d;
            this.b.right -= this.f;
            this.b.bottom -= this.c;
            if (!this.b.isEmpty()) {
                n();
                this.k.layout(0, 0, i6, i7);
                at.b(this.i.a(), this.b);
                this.j.a((float) (-this.e));
                this.j.b((float) (-this.d));
                if (this.a.f()) {
                    a(i2, i3, i4, i5);
                }
                f();
                this.j.layout(0, 0, i6, i7);
            }
        }
    }

    private void n() {
        Crosshair crosshair = this.a.h;
        if (crosshair != null && crosshair.isActive()) {
            crosshair.g();
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (!this.b.isEmpty()) {
            super.dispatchDraw(canvas);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(ChartStyle chartStyle) {
        this.k.setBackgroundColor(chartStyle.getCanvasBackgroundColor());
        a.a((View) this, (Drawable) null);
        a.a((View) this.j, (Drawable) null);
        this.i.setBackgroundColor(chartStyle.getPlotAreaBackgroundColor());
        this.i.a(chartStyle.d());
        this.i.a(chartStyle.e());
        for (Axis v : this.a.getAllXAxes()) {
            v.v();
        }
        for (Axis v2 : this.a.getAllYAxes()) {
            v2.v();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.k = new s(getContext(), this);
        this.k.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.k);
        this.i = o();
        this.i.setLayoutParams(new MarginLayoutParams(-1, -1));
        addView(this.i.a());
        this.j = new q(getContext(), this);
        this.j.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.j);
    }

    private bw o() {
        return a.a(getContext(), this.a);
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        if (this.a.l() || this.a.f()) {
            invalidate();
            requestLayout();
            Crosshair crosshair = this.a.h;
            if (crosshair != null && crosshair.isActive()) {
                crosshair.c();
            }
            this.a.l.a();
        }
    }

    public void invalidate() {
        super.invalidate();
        c();
        d();
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        s sVar = this.k;
        if (sVar != null) {
            sVar.invalidate();
        }
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        q qVar = this.j;
        if (qVar != null) {
            qVar.invalidate();
        }
    }

    public void e() {
        this.i.f();
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        this.i.e();
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        q qVar = this.j;
        if (qVar != null) {
            qVar.forceLayout();
        }
    }

    /* access modifiers changed from: 0000 */
    public void h() {
        s sVar = this.k;
        if (sVar != null) {
            sVar.forceLayout();
        }
    }

    /* access modifiers changed from: 0000 */
    public void i() {
        this.i.c();
    }

    /* access modifiers changed from: 0000 */
    public void j() {
        this.i.b();
    }

    /* access modifiers changed from: 0000 */
    public void k() {
        this.c = i.a(this.a.e, Position.NORMAL);
        this.d = i.a(this.a.e, Position.REVERSE);
        this.e = i.a(this.a.f, Position.NORMAL);
        this.f = i.a(this.a.f, Position.REVERSE);
    }

    /* access modifiers changed from: 0000 */
    public void a(Canvas canvas) {
        for (Axis<?, ?> a2 : this.a.e.a) {
            a2.a(canvas, this.b);
        }
        for (Axis<?, ?> a3 : this.a.f.a) {
            a3.a(canvas, this.b);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Canvas canvas) {
        for (Series series : this.a.c) {
            if (!series.y) {
                series.a(canvas, this.b);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(Canvas canvas) {
        Crosshair crosshair = this.a.h;
        if (crosshair != null && !this.a.l() && crosshair.a == a.SHOWN) {
            crosshair.b(canvas, this.b);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(OnGestureListener onGestureListener) {
        this.j.a(onGestureListener);
    }

    /* access modifiers changed from: 0000 */
    public void b(OnGestureListener onGestureListener) {
        this.j.b(onGestureListener);
    }

    /* access modifiers changed from: 0000 */
    public void c(OnGestureListener onGestureListener) {
        this.j.c(onGestureListener);
    }

    /* access modifiers changed from: 0000 */
    public void d(OnGestureListener onGestureListener) {
        this.j.d(onGestureListener);
    }

    private void a(int i2, int i3, int i4, int i5) {
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        for (Axis<?, ?> a2 : this.a.e.a) {
            a2.a(this.b, i6, i7);
        }
        for (Axis<?, ?> a3 : this.a.f.a) {
            a3.a(this.b, i6, i7);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Tooltip tooltip) {
        this.j.addView(tooltip);
    }

    /* access modifiers changed from: 0000 */
    public void b(Tooltip tooltip) {
        this.j.removeView(tooltip);
    }

    /* access modifiers changed from: 0000 */
    public void a(View view, Annotation.Position position) {
        if (position == Annotation.Position.BEHIND_DATA) {
            this.k.addView(view);
        } else {
            this.j.addView(view);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(View view, Annotation.Position position) {
        if (position == Annotation.Position.BEHIND_DATA) {
            this.k.removeView(view);
        } else {
            this.j.removeView(view);
        }
    }

    /* access modifiers changed from: 0000 */
    public void l() {
        this.i.b_();
    }

    public void a(Bitmap bitmap) {
        this.k.a(bitmap);
    }

    /* access modifiers changed from: 0000 */
    public void m() {
        this.j.a();
    }
}

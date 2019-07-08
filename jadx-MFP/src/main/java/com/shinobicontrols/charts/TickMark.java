package com.shinobicontrols.charts;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;

public final class TickMark {
    private final PointF A = new PointF();
    double a;
    boolean b = false;
    boolean c = false;
    boolean d;
    boolean e;
    String f;
    final Rect g = new Rect();
    private final Axis<?, ?> h;
    private int i;
    private int j;
    private Typeface k;
    private float l;
    private double m = Double.NEGATIVE_INFINITY;
    private final Paint n = new Paint();
    private final TextPaint o;
    private final Paint p = new Paint();
    private final Paint q = new Paint();
    private int r;
    private int s;
    private int t;
    private int u;
    private final Point v = new Point();
    private final Rect w = new Rect();
    private final Path x = new Path();
    private final ChartUtils y = new ChartUtils();
    private final PointF z = new PointF();

    public enum ClippingMode {
        NEITHER_PERSIST,
        TICKS_AND_LABELS_PERSIST,
        TICKS_PERSIST
    }

    public enum Orientation {
        HORIZONTAL(0),
        VERTICAL(1),
        DIAGONAL(2);
        
        private final int a;

        private Orientation(int i) {
            this.a = i;
        }

        public int getXmlValue() {
            return this.a;
        }
    }

    TickMark(Axis<?, ?> axis) {
        this.h = axis;
        this.o = new TextPaint();
        this.o.setTextAlign(Align.CENTER);
        this.o.setAntiAlias(true);
    }

    /* access modifiers changed from: 0000 */
    public void a(Canvas canvas, dg dgVar, int i2, c cVar) {
        int b2 = (int) (this.h.b(this.a, (double) cVar.A, cVar.B) + 0.5d);
        boolean z2 = this.a <= this.h.i.b;
        a(canvas, cVar, b2, z2);
        if (z2) {
            a(canvas, cVar, b2);
        }
        if (z2 && this.b) {
            Axis<?, ?> axis = this.h;
            if (!(axis instanceof CategoryAxis) || this.a <= axis.j.b) {
                b(canvas, cVar, b2);
            }
        }
        if (this.b && cVar.f) {
            Axis<?, ?> axis2 = this.h;
            if (!(axis2 instanceof CategoryAxis) || (this.a > axis2.j.a && this.a <= this.h.j.b)) {
                dgVar.a(i2, this.z, this, this.A, cVar);
                if ((this.h.a() && this.A.x > this.z.x) || (!this.h.a() && this.A.y < this.z.y)) {
                    a(canvas, cVar);
                }
            }
        }
        this.h.b.a(this, this.h);
        a(canvas, z2, cVar);
    }

    private void a(Canvas canvas, c cVar, int i2, boolean z2) {
        this.h.n.a(this.g, cVar, i2, this.b);
        if (z2 && this.r != cVar.n) {
            this.r = cVar.n;
            this.n.setColor(this.r);
        }
    }

    private void a(Canvas canvas, c cVar, int i2) {
        this.h.n.a(this.v, cVar, i2);
        if (this.i != cVar.p) {
            this.o.setColor(cVar.p);
            this.i = cVar.p;
        }
        if (this.j != cVar.q) {
            this.o.setShadowLayer(1.0f, 1.0f, 1.0f, cVar.q);
            this.j = cVar.q;
        }
        if (this.k != cVar.r) {
            this.o.setTypeface(cVar.r);
            this.k = cVar.r;
        }
        if (this.l != cVar.s) {
            this.o.setTextSize(cVar.s);
            this.l = cVar.s;
        }
        double d2 = this.a;
        if (d2 != this.m) {
            this.f = this.h.a(d2);
            this.f = Axis.a(this.f) ? " " : this.f.trim();
            this.m = this.a;
        }
    }

    private void b(Canvas canvas, c cVar, int i2) {
        this.p.setStrokeWidth((float) at.a(this.h.a, cVar.i));
        this.h.n.c(this.x, cVar, i2, this.p);
        this.p.setStyle(Style.STROKE);
        if (this.s != cVar.o) {
            this.s = cVar.o;
            this.p.setColor(this.s);
        }
        if (cVar.u) {
            this.p.setPathEffect(cVar.v);
        }
    }

    private void a(Canvas canvas, c cVar) {
        if (this.t != cVar.w) {
            this.t = cVar.w;
        }
        if (this.u != cVar.x) {
            this.u = cVar.x;
        }
        if (this.c) {
            this.q.setColor(this.u);
        } else {
            this.q.setColor(this.t);
        }
        this.h.n.c(this.w, cVar, this.z, this.A);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.m = Double.NEGATIVE_INFINITY;
    }

    private void a(Canvas canvas, boolean z2, c cVar) {
        Canvas canvas2 = canvas;
        c cVar2 = cVar;
        Rect a2 = this.y.a(this.v.x, this.v.y, this.h.x != null ? this.h.x : this.h.w, this.h.getStyle().getTickStyle().getLabelTextSize(), this.h.getStyle().getTickStyle().getLabelTypeface(), this.h.b);
        if (z2) {
            if (!this.h.b.a(canvas, this, a2, this.g, this.h)) {
                if (this.e) {
                    canvas2.drawRect(this.g, this.n);
                }
                if (this.d) {
                    if (cVar2.t != Orientation.HORIZONTAL) {
                        canvas.save();
                        canvas2.rotate(cVar2.t == Orientation.DIAGONAL ? -45.0f : -90.0f, (float) this.v.x, (float) this.v.y);
                    }
                    ChartUtils.drawText(canvas2, this.f, this.v.x, this.v.y, this.o);
                    if (cVar2.t != Orientation.HORIZONTAL) {
                        canvas.restore();
                    }
                }
            }
        }
        if (z2 && this.b && cVar2.e) {
            Axis<?, ?> axis = this.h;
            if (!(axis instanceof CategoryAxis) || this.a <= axis.j.b) {
                canvas2.drawPath(this.x, this.p);
            }
        }
        if (this.b && cVar2.f) {
            Axis<?, ?> axis2 = this.h;
            if ((axis2 instanceof CategoryAxis) && (this.a <= axis2.j.a || this.a > this.h.j.b)) {
                return;
            }
            if ((this.h.a() && this.A.x > this.z.x) || (!this.h.a() && this.A.y < this.z.y)) {
                canvas2.drawRect(this.w, this.q);
            }
        }
    }

    public boolean isMajor() {
        return this.b;
    }

    public Object getValue() {
        return this.h.transformInternalValueToUser(this.a);
    }

    public boolean isLabelShown() {
        return this.d;
    }

    public void setLabelShown(boolean z2) {
        this.d = z2;
    }

    public boolean isLineShown() {
        return this.e;
    }

    public void setLineShown(boolean z2) {
        this.e = z2;
    }

    public String getLabelText() {
        return this.f;
    }

    public void setLabelText(String str) {
        this.f = str;
    }

    public Point getLabelCenter() {
        return this.v;
    }

    public Paint getLinePaint() {
        return this.n;
    }

    public TextPaint getLabelPaint() {
        return this.o;
    }
}

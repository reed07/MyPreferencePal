package com.shinobicontrols.charts;

import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class Annotation {
    private Object a;
    private Object b;
    private final Axis<?, ?> c;
    private final Axis<?, ?> d;
    private Range<?> e;
    private Range<?> f;
    private final View g;
    private Position h = Position.IN_FRONT_OF_DATA;
    private AnnotationStyle i;
    private final al j = new al();
    private final e k;

    public enum Position {
        IN_FRONT_OF_DATA,
        BEHIND_DATA
    }

    Annotation(View view, Axis<?, ?> axis, Axis<?, ?> axis2, e eVar) {
        if (view == null) {
            throw new IllegalArgumentException("Annotation cannot have a null View.");
        } else if (axis == null) {
            throw new IllegalArgumentException(view.getContext().getString(R.string.AnnotationCannotHaveNullX));
        } else if (axis2 == null) {
            throw new IllegalArgumentException(view.getContext().getString(R.string.AnnotationCannotHaveNullY));
        } else if (axis != axis2) {
            this.g = view;
            this.c = axis;
            this.d = axis2;
            this.k = eVar;
        } else {
            throw new IllegalArgumentException(view.getContext().getString(R.string.AnnotationCannotHaveSameXY));
        }
    }

    public Object getXValue() {
        return this.a;
    }

    public void setXValue(Object obj) {
        this.a = obj;
    }

    public Object getYValue() {
        return this.b;
    }

    public void setYValue(Object obj) {
        this.b = obj;
    }

    public Axis<?, ?> getXAxis() {
        return this.c;
    }

    public Axis<?, ?> getYAxis() {
        return this.d;
    }

    public Range<?> getXRange() {
        return this.e;
    }

    public void setXRange(Range<?> range) {
        this.e = range;
    }

    public Range<?> getYRange() {
        return this.f;
    }

    public void setYRange(Range<?> range) {
        this.f = range;
    }

    public View getView() {
        return this.g;
    }

    public Position getPosition() {
        return this.h;
    }

    public void setPosition(Position position) {
        this.h = position;
        a();
    }

    public AnnotationStyle getStyle() {
        return this.i;
    }

    public void setStyle(AnnotationStyle annotationStyle) {
        this.i = annotationStyle;
    }

    /* access modifiers changed from: 0000 */
    public final void a() {
        this.j.a(new d(this));
    }

    /* access modifiers changed from: 0000 */
    public am a(a aVar) {
        return this.j.a(d.a, (a) aVar);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3) {
        this.g.measure(i2, i3);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3, int i4, int i5) {
        LayoutParams layoutParams = this.g.getLayoutParams();
        if (layoutParams.width != -1) {
            if (layoutParams.width == 0) {
                Range<?> range = this.e;
                if (range != null) {
                    i2 = ((int) this.c.f(this.c.translatePoint(range.getMinimum()))) - this.c.b.a.left;
                    i4 = ((int) this.c.f(this.c.translatePoint(this.e.getMaximum()))) - this.c.b.a.left;
                }
            }
            Object obj = this.a;
            if (obj != null) {
                double f2 = this.c.f(this.c.translatePoint(obj)) - ((double) this.c.b.a.left);
                int measuredWidth = this.g.getMeasuredWidth();
                int i6 = (int) (f2 - (((double) measuredWidth) / 2.0d));
                int i7 = i6;
                i4 = measuredWidth + i6;
                i2 = i7;
            } else {
                throw new NullPointerException(this.g.getContext().getString(R.string.AnnotationCannotConvertNullX));
            }
        }
        if (layoutParams.height != -1) {
            if (layoutParams.height == 0) {
                Range<?> range2 = this.f;
                if (range2 != null) {
                    i3 = ((int) this.d.f(this.d.translatePoint(range2.getMaximum()))) - this.d.b.a.top;
                    i5 = ((int) this.d.f(this.d.translatePoint(this.f.getMinimum()))) - this.d.b.a.top;
                }
            }
            Object obj2 = this.b;
            if (obj2 != null) {
                double f3 = this.d.f(this.d.translatePoint(obj2)) - ((double) this.d.b.a.top);
                int measuredHeight = this.g.getMeasuredHeight();
                int i8 = (int) (f3 - (((double) measuredHeight) / 2.0d));
                int i9 = i8;
                i5 = measuredHeight + i8;
                i3 = i9;
            } else {
                throw new NullPointerException(this.g.getContext().getString(R.string.AnnotationCannotConvertNullY));
            }
        }
        this.g.layout(i2, i3, i4, i5);
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.k.a(this);
    }
}

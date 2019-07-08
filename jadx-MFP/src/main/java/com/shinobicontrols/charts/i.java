package com.shinobicontrols.charts;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.Axis.Orientation;
import com.shinobicontrols.charts.Title.Position;

abstract class i {
    private static i b = new i(null) {
        /* access modifiers changed from: 0000 */
        public double a(double d, int i, int i2) {
            return 0.0d;
        }

        /* access modifiers changed from: 0000 */
        public double a(double d, Rect rect) {
            return 0.0d;
        }

        /* access modifiers changed from: 0000 */
        public int a(Position position) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Point point, c cVar, int i) {
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, Title title, Rect rect2) {
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, Rect rect2, float f, int i, float f2) {
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, c cVar, int i, boolean z) {
        }

        /* access modifiers changed from: 0000 */
        public void a(c cVar) {
        }

        /* access modifiers changed from: 0000 */
        public void c(Path path, c cVar, int i, Paint paint) {
        }

        /* access modifiers changed from: 0000 */
        public void c(Rect rect, c cVar, PointF pointF, PointF pointF2) {
        }
    };
    protected Axis<?, ?> a;

    private static class a extends i {
        protected a(Axis<?, ?> axis) {
            super(axis);
        }

        /* access modifiers changed from: 0000 */
        public int a(Position position) {
            return position.a();
        }

        /* access modifiers changed from: 0000 */
        public void a(c cVar) {
            float f = cVar.l + cVar.H + cVar.z;
            cVar.j = (float) a(0, cVar.i / 2.0f);
            cVar.K.left = a(cVar.a.left, (-cVar.h) / 2.0f);
            cVar.K.right = a(cVar.a.left, cVar.h / 2.0f);
            cVar.K.top = (int) (((float) a(cVar.a.bottom, (f - cVar.m) - cVar.g)) + cVar.I);
            cVar.K.bottom = (int) (((float) a(cVar.a.bottom, f - cVar.m)) + cVar.I);
            cVar.L.bottom = (int) (((float) a(cVar.a.bottom, (f - cVar.m) - (cVar.g * 0.5f))) + cVar.I);
            float a = ((float) a(0, cVar.l + cVar.H + cVar.z)) + cVar.I;
            float f2 = (-((float) cVar.y.x)) / 2.0f;
            float f3 = ((float) cVar.y.x) + f2;
            float f4 = ((float) cVar.y.y) + a;
            if (cVar.J) {
                a -= cVar.z;
                f4 -= cVar.z;
            }
            cVar.M.x = (int) (((float) cVar.a.left) + ((f2 + f3) / 2.0f));
            cVar.M.y = (int) (((float) cVar.a.bottom) + ((a + f4) / 2.0f));
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, Rect rect2, float f, int i, float f2) {
            rect.left = rect2.left;
            rect.right = rect2.right;
            rect.top = rect2.bottom + i;
            rect.bottom = a(rect2.bottom + i, f);
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, c cVar, int i, boolean z) {
            rect.left = cVar.K.left + i;
            rect.right = i + cVar.K.right;
            rect.top = cVar.K.top;
            if (z) {
                rect.bottom = cVar.K.bottom;
            } else {
                rect.bottom = cVar.L.bottom;
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(Point point, c cVar, int i) {
            point.x = i + cVar.M.x;
            point.y = cVar.M.y;
        }

        /* access modifiers changed from: 0000 */
        public double a(double d, int i, int i2) {
            return a(d, i, this.a.i.a, this.a.i.b());
        }

        /* access modifiers changed from: 0000 */
        public double a(double d, Rect rect) {
            return a(d, rect, this.a.i.a, this.a.i.b());
        }

        /* access modifiers changed from: 0000 */
        public void c(Path path, c cVar, int i, Paint paint) {
            a(path, cVar, i, paint);
        }

        /* access modifiers changed from: 0000 */
        public void c(Rect rect, c cVar, PointF pointF, PointF pointF2) {
            a(rect, cVar, pointF, pointF2);
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, Title title, Rect rect2) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) title.getLayoutParams();
            int measuredHeight = marginLayoutParams.topMargin + title.getMeasuredHeight() + marginLayoutParams.bottomMargin;
            rect2.left = rect.left;
            rect2.top = ((rect.bottom + i2) + i) - measuredHeight;
            rect2.right = rect.right;
            rect2.bottom = rect.bottom + i2 + i;
        }
    }

    private static class b extends i {
        protected b(Axis<?, ?> axis) {
            super(axis);
        }

        /* access modifiers changed from: 0000 */
        public int a(Position position) {
            return position.b();
        }

        /* access modifiers changed from: 0000 */
        public void a(c cVar) {
            float f = -(cVar.l + cVar.H + (-cVar.z));
            cVar.j = (float) a(0, cVar.i / 2.0f);
            cVar.K.left = (int) (((float) a(cVar.a.left, cVar.m + f)) - cVar.I);
            cVar.L.left = (int) (((float) a(cVar.a.left, (cVar.m + f) + (cVar.g * 0.5f))) - cVar.I);
            cVar.K.right = (int) (((float) a(cVar.a.left, (f + cVar.m) + cVar.g)) - cVar.I);
            cVar.K.top = a(cVar.a.top, (-cVar.h) / 2.0f);
            cVar.K.bottom = a(cVar.a.top, cVar.h / 2.0f);
            float a = (((float) a(0, -((cVar.l + cVar.H) + (-cVar.z)))) - cVar.I) - ((float) cVar.y.x);
            float f2 = (-((float) cVar.y.y)) / 2.0f;
            float f3 = ((float) cVar.y.x) + a;
            float f4 = ((float) cVar.y.y) + f2;
            if (cVar.J) {
                a -= cVar.z;
                f3 -= cVar.z;
            }
            cVar.M.x = (int) (((float) cVar.a.left) + ((a + f3) / 2.0f));
            cVar.M.y = (int) (((float) cVar.a.top) + ((f2 + f4) / 2.0f));
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, Rect rect2, float f, int i, float f2) {
            rect.left = a(rect2.left - i, -f);
            rect.right = rect2.left - i;
            rect.top = a(rect2.top, -f2);
            rect.bottom = a(rect2.bottom, f);
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, c cVar, int i, boolean z) {
            if (z) {
                rect.left = cVar.K.left;
            } else {
                rect.left = cVar.L.left;
            }
            rect.right = cVar.K.right;
            rect.top = cVar.K.top + i;
            rect.bottom = i + cVar.K.bottom;
        }

        /* access modifiers changed from: 0000 */
        public void a(Point point, c cVar, int i) {
            point.x = cVar.M.x;
            point.y = i + cVar.M.y;
        }

        /* access modifiers changed from: 0000 */
        public double a(double d, int i, int i2) {
            return b(d, i2, this.a.i.a, this.a.i.b());
        }

        /* access modifiers changed from: 0000 */
        public double a(double d, Rect rect) {
            return b(d, rect, this.a.i.a, this.a.i.b());
        }

        /* access modifiers changed from: 0000 */
        public void c(Path path, c cVar, int i, Paint paint) {
            b(path, cVar, i, paint);
        }

        /* access modifiers changed from: 0000 */
        public void c(Rect rect, c cVar, PointF pointF, PointF pointF2) {
            b(rect, cVar, pointF, pointF2);
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, Title title, Rect rect2) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) title.getLayoutParams();
            int measuredWidth = marginLayoutParams.leftMargin + title.getMeasuredWidth() + marginLayoutParams.rightMargin;
            rect2.left = (rect.left - i2) - i;
            rect2.top = rect.top;
            rect2.right = ((rect.left - i2) - i) + measuredWidth;
            rect2.bottom = rect.bottom;
        }
    }

    private static class c extends i {
        protected c(Axis<?, ?> axis) {
            super(axis);
        }

        /* access modifiers changed from: 0000 */
        public int a(Position position) {
            return position.a();
        }

        /* access modifiers changed from: 0000 */
        public void a(c cVar) {
            float f = ((-cVar.l) - cVar.H) + cVar.z;
            cVar.j = (float) a(0, cVar.i / 2.0f);
            cVar.K.left = a(cVar.a.left, (-cVar.h) / 2.0f);
            cVar.K.right = a(cVar.a.left, cVar.h / 2.0f);
            cVar.K.top = (int) (((float) a(cVar.a.top, cVar.m + f)) + cVar.I);
            cVar.L.top = (int) (((float) a(cVar.a.top, cVar.m + f + (cVar.g * 0.5f))) + cVar.I);
            cVar.K.bottom = (int) (((float) a(cVar.a.top, f + cVar.m + cVar.g)) + cVar.I);
            float f2 = (-((float) cVar.y.x)) / 2.0f;
            float a = (((float) a(0, ((-cVar.l) - cVar.H) + cVar.z)) + cVar.I) - ((float) cVar.y.y);
            float f3 = ((float) cVar.y.x) + f2;
            float f4 = ((float) cVar.y.y) + a;
            if (cVar.J) {
                a -= cVar.z;
                f4 -= cVar.z;
            }
            cVar.M.x = (int) (((float) cVar.a.left) + ((f2 + f3) / 2.0f));
            cVar.M.y = (int) (((float) cVar.a.top) + ((a + f4) / 2.0f));
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, Rect rect2, float f, int i, float f2) {
            rect.left = rect2.left;
            rect.right = rect2.right;
            rect.top = a(rect2.top + i, -f);
            rect.bottom = rect2.top + i;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, c cVar, int i, boolean z) {
            rect.left = cVar.K.left + i;
            rect.right = i + cVar.K.right;
            if (z) {
                rect.top = cVar.K.top;
            } else {
                rect.top = cVar.L.top;
            }
            rect.bottom = cVar.K.bottom;
        }

        /* access modifiers changed from: 0000 */
        public void a(Point point, c cVar, int i) {
            point.x = i + cVar.M.x;
            point.y = cVar.M.y;
        }

        /* access modifiers changed from: 0000 */
        public double a(double d, int i, int i2) {
            return a(d, i, this.a.i.a, this.a.i.b());
        }

        /* access modifiers changed from: 0000 */
        public double a(double d, Rect rect) {
            return a(d, rect, this.a.i.a, this.a.i.b());
        }

        /* access modifiers changed from: 0000 */
        public void c(Path path, c cVar, int i, Paint paint) {
            a(path, cVar, i, paint);
        }

        /* access modifiers changed from: 0000 */
        public void c(Rect rect, c cVar, PointF pointF, PointF pointF2) {
            a(rect, cVar, pointF, pointF2);
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, Title title, Rect rect2) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) title.getLayoutParams();
            int measuredHeight = marginLayoutParams.topMargin + title.getMeasuredHeight() + marginLayoutParams.bottomMargin;
            rect2.left = rect.left;
            rect2.top = (rect.top + i2) - i;
            rect2.right = rect.right;
            rect2.bottom = ((rect.top + i2) - i) + measuredHeight;
        }
    }

    private static class d extends i {
        protected d(Axis<?, ?> axis) {
            super(axis);
        }

        /* access modifiers changed from: 0000 */
        public int a(Position position) {
            return position.b();
        }

        /* access modifiers changed from: 0000 */
        public void a(c cVar) {
            float f = (cVar.l + cVar.H) - cVar.z;
            cVar.j = (float) a(0, cVar.i / 2.0f);
            cVar.K.left = (int) (((float) a(cVar.a.right, (f - cVar.m) - cVar.g)) - cVar.I);
            cVar.K.right = (int) (((float) a(cVar.a.right, f - cVar.m)) - cVar.I);
            cVar.L.right = (int) (((float) a(cVar.a.right, (f - cVar.m) - (cVar.g * 0.5f))) - cVar.I);
            cVar.K.top = a(cVar.a.top, (-cVar.h) / 2.0f);
            cVar.K.bottom = a(cVar.a.top, cVar.h / 2.0f);
            float a = ((float) a(0, (cVar.l + cVar.H) - cVar.z)) - cVar.I;
            float f2 = (-((float) cVar.y.y)) / 2.0f;
            float f3 = ((float) cVar.y.x) + a;
            float f4 = ((float) cVar.y.y) + f2;
            if (cVar.J) {
                a -= cVar.z;
                f3 -= cVar.z;
            }
            cVar.M.x = (int) (((float) cVar.a.right) + ((a + f3) / 2.0f));
            cVar.M.y = (int) (((float) cVar.a.top) + ((f2 + f4) / 2.0f));
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, Rect rect2, float f, int i, float f2) {
            rect.left = rect2.right - i;
            rect.right = a(rect2.right - i, f);
            rect.top = a(rect2.top, -f2);
            rect.bottom = a(rect2.bottom, f);
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, c cVar, int i, boolean z) {
            rect.left = cVar.K.left;
            if (z) {
                rect.right = cVar.K.right;
            } else {
                rect.right = cVar.L.right;
            }
            rect.top = cVar.K.top + i;
            rect.bottom = i + cVar.K.bottom;
        }

        /* access modifiers changed from: 0000 */
        public void a(Point point, c cVar, int i) {
            point.x = cVar.M.x;
            point.y = i + cVar.M.y;
        }

        /* access modifiers changed from: 0000 */
        public double a(double d, int i, int i2) {
            return b(d, i2, this.a.i.a, this.a.i.b());
        }

        /* access modifiers changed from: 0000 */
        public double a(double d, Rect rect) {
            return b(d, rect, this.a.i.a, this.a.i.b());
        }

        /* access modifiers changed from: 0000 */
        public void c(Path path, c cVar, int i, Paint paint) {
            b(path, cVar, i, paint);
        }

        /* access modifiers changed from: 0000 */
        public void c(Rect rect, c cVar, PointF pointF, PointF pointF2) {
            b(rect, cVar, pointF, pointF2);
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, Title title, Rect rect2) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) title.getLayoutParams();
            rect2.left = ((rect.right - i2) + i) - ((marginLayoutParams.leftMargin + title.getMeasuredWidth()) + marginLayoutParams.rightMargin);
            rect2.top = rect.top;
            rect2.right = (rect.right - i2) + i;
            rect2.bottom = rect.bottom;
        }
    }

    static double a(double d2, int i, double d3, double d4) {
        return ((d2 - d3) * ((double) i)) / d4;
    }

    static double b(double d2, int i, double d3, double d4) {
        double d5 = (double) i;
        return d5 - (((d2 - d3) * d5) / d4);
    }

    /* access modifiers changed from: 0000 */
    public abstract double a(double d2, int i, int i2);

    /* access modifiers changed from: 0000 */
    public abstract double a(double d2, Rect rect);

    /* access modifiers changed from: 0000 */
    public abstract int a(Position position);

    /* access modifiers changed from: 0000 */
    public abstract void a(Point point, c cVar, int i);

    /* access modifiers changed from: 0000 */
    public abstract void a(Rect rect, int i, int i2, Title title, Rect rect2);

    /* access modifiers changed from: 0000 */
    public abstract void a(Rect rect, Rect rect2, float f, int i, float f2);

    /* access modifiers changed from: 0000 */
    public abstract void a(Rect rect, c cVar, int i, boolean z);

    /* access modifiers changed from: 0000 */
    public abstract void a(c cVar);

    /* access modifiers changed from: 0000 */
    public abstract void c(Path path, c cVar, int i, Paint paint);

    /* access modifiers changed from: 0000 */
    public abstract void c(Rect rect, c cVar, PointF pointF, PointF pointF2);

    protected i(Axis<?, ?> axis) {
        this.a = axis;
    }

    static i a(Axis<?, ?> axis) {
        i iVar;
        if (axis == null || axis.c == null || axis.d == null) {
            return b;
        }
        if (axis.c == Orientation.HORIZONTAL) {
            if (axis.d == Axis.Position.NORMAL) {
                iVar = new a(axis);
            } else if (axis.d == Axis.Position.REVERSE) {
                iVar = new c(axis);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Axis Position invalid:");
                sb.append(axis.d);
                throw new AssertionError(sb.toString());
            }
        } else if (axis.c != Orientation.VERTICAL) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Axis Orientation invalid:");
            sb2.append(axis.c);
            throw new AssertionError(sb2.toString());
        } else if (axis.d == Axis.Position.NORMAL) {
            iVar = new b(axis);
        } else if (axis.d == Axis.Position.REVERSE) {
            iVar = new d(axis);
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Axis Position invalid:");
            sb3.append(axis.d);
            throw new AssertionError(sb3.toString());
        }
        return iVar;
    }

    /* access modifiers changed from: 0000 */
    public int a(int i, float f) {
        return at.a(this.a.a, i, f);
    }

    /* access modifiers changed from: 0000 */
    public void a(Path path, c cVar, int i, Paint paint) {
        path.reset();
        int i2 = i + cVar.a.left;
        float f = (float) i2;
        path.moveTo(f, (float) cVar.a.top);
        path.lineTo(f, (float) cVar.a.bottom);
        float f2 = (float) (i2 - cVar.a.left);
        if (f2 < cVar.j) {
            float f3 = cVar.j - f2;
            paint.setStrokeWidth(paint.getStrokeWidth() - f3);
            path.offset(f3 / 2.0f, BitmapDescriptorFactory.HUE_RED);
        }
        float f4 = (float) (cVar.a.right - i2);
        if (f4 < cVar.j) {
            float f5 = cVar.j - f4;
            paint.setStrokeWidth(paint.getStrokeWidth() - f5);
            path.offset((-f5) / 2.0f, BitmapDescriptorFactory.HUE_RED);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Path path, c cVar, int i, Paint paint) {
        path.reset();
        int i2 = i + cVar.a.top;
        float f = (float) i2;
        path.moveTo((float) cVar.a.left, f);
        path.lineTo((float) cVar.a.right, f);
        float f2 = (float) (cVar.a.bottom - i2);
        if (f2 < cVar.j) {
            float f3 = cVar.j - f2;
            paint.setStrokeWidth(paint.getStrokeWidth() - f3);
            path.offset(BitmapDescriptorFactory.HUE_RED, (-f3) / 2.0f);
        }
        float f4 = (float) (i2 - cVar.a.top);
        if (f4 < cVar.j) {
            float f5 = cVar.j - f4;
            paint.setStrokeWidth(paint.getStrokeWidth() - f5);
            path.offset(BitmapDescriptorFactory.HUE_RED, f5 / 2.0f);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Rect rect, c cVar, PointF pointF, PointF pointF2) {
        rect.left = (int) pointF.x;
        rect.right = (int) pointF2.x;
        rect.top = cVar.a.top;
        rect.bottom = a(cVar.a.bottom, cVar.I);
    }

    /* access modifiers changed from: 0000 */
    public void b(Rect rect, c cVar, PointF pointF, PointF pointF2) {
        rect.left = cVar.a.left;
        rect.right = cVar.a.right;
        rect.top = (int) pointF2.y;
        rect.bottom = (int) pointF.y;
    }

    static double a(double d2, Rect rect, double d3, double d4) {
        return d3 + ((d2 * d4) / ((double) (rect.right - rect.left)));
    }

    static double b(double d2, Rect rect, double d3, double d4) {
        return d3 + (d4 * (1.0d - (d2 / ((double) (rect.bottom - rect.top)))));
    }

    static int a(h hVar, Axis.Position position) {
        int i = 0;
        for (Axis<?, ?> axis : hVar.a) {
            if (position == axis.d) {
                i += axis.o;
            }
        }
        return i;
    }
}

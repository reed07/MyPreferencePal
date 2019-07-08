package com.shinobicontrols.charts;

import android.graphics.Paint;
import android.graphics.Point;

public final class PieDonutSlice extends InternalDataPoint {
    String m;
    float n;
    float o;
    float p;
    float q;
    Point r;
    Point s;
    Paint t = new Paint();
    Paint u = new Paint();

    PieDonutSlice(double d, double d2) {
        super(d, d2);
    }

    public float getCenterAngle() {
        return (this.n + this.o) / 2.0f;
    }

    public Point getLabelCenter() {
        return this.s;
    }

    public String getLabelText() {
        return this.m;
    }

    public void setLabelText(String str) {
        this.m = str;
    }

    public double getY() {
        return this.b;
    }

    public int getCenterX() {
        return this.r.x;
    }

    public int getCenterY() {
        return this.r.y;
    }

    public Paint getLabelPaint() {
        return this.t;
    }

    public Paint getLabelBackgroundPaint() {
        return this.u;
    }
}

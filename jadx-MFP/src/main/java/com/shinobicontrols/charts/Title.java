package com.shinobicontrols.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class Title extends TextView {
    private final boolean a;
    private Orientation b = Orientation.HORIZONTAL;
    private final float c = getResources().getDisplayMetrics().density;

    public enum CentersOn {
        CANVAS,
        CHART,
        PLOTTING_AREA
    }

    public enum Orientation {
        HORIZONTAL(0),
        VERTICAL(1);
        
        private final int a;

        private Orientation(int i) {
            this.a = i;
        }

        public int getXmlValue() {
            return this.a;
        }
    }

    public enum Position {
        BOTTOM_OR_LEFT(0, 3, 80),
        CENTER(1, 1, 16),
        TOP_OR_RIGHT(2, 5, 48);
        
        private final int a;
        private final int b;
        private final int c;

        private Position(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }

        public int getXmlValue() {
            return this.a;
        }

        /* access modifiers changed from: 0000 */
        public int a() {
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public int b() {
            return this.c;
        }
    }

    Title(Context context) {
        super(context);
        int gravity = getGravity();
        if (!Gravity.isVertical(gravity) || (gravity & 112) != 80) {
            this.a = true;
            return;
        }
        setGravity((gravity & 7) | 48);
        this.a = false;
    }

    /* access modifiers changed from: 0000 */
    public void a(Orientation orientation) {
        this.b = orientation;
        invalidate();
        requestLayout();
    }

    /* access modifiers changed from: 0000 */
    public void a(TitleStyle titleStyle) {
        setTextSize(2, ((Float) titleStyle.e.a).floatValue());
        setTypeface((Typeface) titleStyle.d.a);
        setTextColor(((Integer) titleStyle.h.a).intValue());
        if (titleStyle.getBackgroundColor() == 0) {
            a.a((View) this, (Drawable) null);
        } else {
            setBackgroundColor(((Integer) titleStyle.c.a).intValue());
        }
        int a2 = at.a(this.c, ((Float) titleStyle.i.a).floatValue());
        setPadding(a2, a2, a2, a2);
        int a3 = at.a(this.c, ((Float) titleStyle.j.a).floatValue());
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
        marginLayoutParams.topMargin = a3;
        marginLayoutParams.bottomMargin = a3;
        marginLayoutParams.leftMargin = a3;
        marginLayoutParams.rightMargin = a3;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.b == Orientation.HORIZONTAL) {
            super.onDraw(canvas);
            return;
        }
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.drawableState = getDrawableState();
        canvas.save();
        if (!this.a) {
            canvas.translate((float) getWidth(), BitmapDescriptorFactory.HUE_RED);
            canvas.rotate(90.0f);
        } else {
            canvas.translate(BitmapDescriptorFactory.HUE_RED, (float) getHeight());
            canvas.rotate(-90.0f);
        }
        canvas.translate((float) getCompoundPaddingLeft(), (float) getExtendedPaddingTop());
        getLayout().draw(canvas);
        canvas.restore();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.b == Orientation.HORIZONTAL) {
            super.onMeasure(i, i2);
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        super.onMeasure(i2, i);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }
}

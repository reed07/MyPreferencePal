package com.shinobicontrols.charts;

import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PointF;
import android.graphics.Typeface;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

class ar {
    private final Paint a = new Paint();
    private float b;
    private float c;
    private String d;
    private float e;
    private Typeface f;

    ar() {
    }

    /* access modifiers changed from: 0000 */
    public void a(PointF pointF, String str, float f2, Typeface typeface, v vVar) {
        if (!str.equals(this.d) || f2 != this.e || !typeface.equals(this.f)) {
            this.a.setTextSize(vVar.getResources().getDisplayMetrics().scaledDensity * f2);
            this.a.setTypeface(typeface);
            FontMetrics fontMetrics = this.a.getFontMetrics();
            pointF.x = a(str, this.a);
            pointF.y = (float) Math.ceil((double) (fontMetrics.bottom - fontMetrics.top));
            this.d = str;
            this.e = f2;
            this.f = typeface;
            this.b = pointF.x;
            this.c = pointF.y;
            return;
        }
        pointF.x = this.b;
        pointF.y = this.c;
    }

    /* access modifiers changed from: 0000 */
    public void b(PointF pointF, String str, float f2, Typeface typeface, v vVar) {
        a(pointF, str, f2, typeface, vVar);
        pointF.y *= (float) a(str);
    }

    private float a(String str, Paint paint) {
        float f2;
        String[] split;
        if (str.contains("\n")) {
            f2 = BitmapDescriptorFactory.HUE_RED;
            for (String str2 : str.split("\n")) {
                if (paint.measureText(str2) > f2) {
                    f2 = paint.measureText(str2);
                }
            }
        } else {
            f2 = paint.measureText(str);
        }
        return (float) Math.ceil((double) (f2 + 2.0f));
    }

    private int a(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        return str.split("\n", -1).length;
    }
}

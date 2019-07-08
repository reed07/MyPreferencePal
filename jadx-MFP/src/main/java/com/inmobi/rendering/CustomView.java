package com.inmobi.rendering;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class CustomView extends View {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private int f;
    private int g;
    private Paint h;
    private Path i;
    private RectF j;

    private CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, float f2, int i2) {
        this(context);
        this.f = i2;
        this.a = f2;
        this.g = 15;
        this.h = new Paint(1);
        this.j = new RectF();
        this.i = new Path();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.onDraw(canvas);
        this.h.reset();
        switch (this.f) {
            case 0:
                float f2 = this.a;
                float f3 = (50.0f * f2) / 2.0f;
                float f4 = (f2 * 30.0f) / 2.0f;
                float f5 = f4 / 3.0f;
                float f6 = f3 - f5;
                float f7 = f3 + f5;
                this.h.setAntiAlias(true);
                this.h.setColor(-16777216);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawCircle(f3, f3, f4, this.h);
                this.h.setColor(-1);
                this.h.setStyle(Style.STROKE);
                Canvas canvas3 = canvas;
                float f8 = f6;
                float f9 = f7;
                canvas3.drawLine(f8, f6, f9, f7, this.h);
                canvas3.drawLine(f8, f7, f9, f6, this.h);
                canvas2.drawCircle(f3, f3, f4, this.h);
                return;
            case 1:
                float f10 = (this.a * 50.0f) / 2.0f;
                this.h.setAntiAlias(true);
                this.h.setColor(0);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawCircle(f10, f10, f10, this.h);
                return;
            case 2:
                this.h.setAntiAlias(true);
                this.h.setColor(-1);
                this.h.setStrokeWidth(5.0f);
                this.h.setStyle(Style.STROKE);
                Canvas canvas4 = canvas;
                canvas4.drawLine(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((((float) this.g) * this.a) / 2.0f) + ((float) (getWidth() / 2)), ((((float) this.g) * this.a) / 2.0f) + ((float) (getHeight() / 2)), this.h);
                Canvas canvas5 = canvas;
                canvas5.drawLine(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((((float) this.g) * this.a) / 2.0f) + ((float) (getHeight() / 2)), ((((float) this.g) * this.a) / 2.0f) + ((float) (getWidth() / 2)), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f), this.h);
                break;
            case 3:
                float f11 = this.a;
                float f12 = (50.0f * f11) / 2.0f;
                float f13 = (f11 * 30.0f) / 2.0f;
                this.i.reset();
                this.h.setAntiAlias(true);
                this.h.setColor(-16777216);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawCircle(f12, f12, f13, this.h);
                this.h.setColor(-1);
                this.h.setStyle(Style.STROKE);
                canvas2.drawCircle(f12, f12, f13, this.h);
                this.j.set(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                canvas.drawArc(this.j, BitmapDescriptorFactory.HUE_RED, 270.0f, false, this.h);
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - (this.a * 2.0f));
                Path path = this.i;
                float width = (float) (getWidth() / 2);
                float f14 = (float) this.g;
                float f15 = this.a;
                path.lineTo((width + ((f14 * f15) / 2.0f)) - (f15 * 2.0f), (float) (getHeight() / 2));
                Path path2 = this.i;
                float width2 = (float) (getWidth() / 2);
                float f16 = (float) this.g;
                float f17 = this.a;
                path2.lineTo(width2 + ((f16 * f17) / 2.0f) + (f17 * 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - (this.a * 2.0f));
                this.i.close();
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawPath(this.i, this.h);
                return;
            case 4:
                this.i.reset();
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(-16777216);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawPath(this.i, this.h);
                return;
            case 5:
                this.i.reset();
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(-16777216);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawPath(this.i, this.h);
                return;
            case 6:
                this.i.reset();
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(-12303292);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawPath(this.i, this.h);
                return;
            case 7:
                b(canvas);
                float f18 = this.e;
                this.b = f18 / 3.0f;
                this.c = f18 / 3.0f;
                this.h.setStyle(Style.FILL);
                Path path3 = this.i;
                float f19 = this.d;
                path3.moveTo(this.b + f19, f19);
                Path path4 = this.i;
                float f20 = this.d;
                path4.lineTo(f20 - this.b, f20 - this.c);
                Path path5 = this.i;
                float f21 = this.d;
                path5.lineTo(f21 - this.b, f21 + this.c);
                Path path6 = this.i;
                float f22 = this.d;
                path6.lineTo(this.b + f22, f22);
                canvas2.drawPath(this.i, this.h);
                return;
            case 8:
                b(canvas);
                float f23 = this.e;
                this.b = f23 / 4.0f;
                this.c = f23 / 3.0f;
                float f24 = this.d;
                float f25 = this.b;
                float f26 = f24 - f25;
                float f27 = this.c;
                canvas.drawLine(f26, f24 - f27, f24 - f25, f24 + f27, this.h);
                float f28 = this.d;
                float f29 = this.b;
                float f30 = f28 + f29;
                float f31 = this.c;
                canvas.drawLine(f30, f28 - f31, f28 + f29, f28 + f31, this.h);
                return;
            case 9:
                a(canvas);
                float f32 = this.d;
                float f33 = this.a;
                float f34 = f32 - (f33 * 10.0f);
                float f35 = this.c;
                RectF rectF = new RectF(f34, (f32 - f35) - (f33 * 2.0f), (14.0f * f33) + f32, f32 + f35 + (f33 * 2.0f));
                float f36 = this.d;
                float f37 = this.a;
                float f38 = f36 - (10.0f * f37);
                float f39 = this.c;
                RectF rectF2 = new RectF(f38, (f36 - f39) - (f37 * 4.0f), (18.0f * f37) + f36, f36 + f39 + (f37 * 4.0f));
                this.h.setColor(-1);
                this.h.setStrokeWidth(4.0f);
                this.h.setStyle(Style.STROKE);
                Canvas canvas6 = canvas;
                canvas6.drawArc(rectF, -45.0f, 90.0f, false, this.h);
                canvas6.drawArc(rectF2, -45.0f, 90.0f, false, this.h);
                canvas2.drawPath(this.i, this.h);
                canvas2.drawPath(this.i, this.h);
                return;
            case 11:
                a(canvas);
                this.h.setColor(-1);
                this.h.setStrokeWidth(4.0f);
                this.h.setStyle(Style.STROKE);
                Path path7 = this.i;
                float f40 = this.d;
                path7.moveTo((this.a * 10.0f) + f40, f40 - this.c);
                Path path8 = this.i;
                float f41 = this.d;
                path8.lineTo((this.a * 18.0f) + f41, f41 + this.c);
                Path path9 = this.i;
                float f42 = this.d;
                path9.moveTo((this.a * 18.0f) + f42, f42 - this.c);
                Path path10 = this.i;
                float f43 = this.d;
                path10.lineTo((this.a * 10.0f) + f43, f43 + this.c);
                canvas2.drawPath(this.i, this.h);
                return;
            case 12:
                float f44 = this.a;
                this.d = (50.0f * f44) / 2.0f;
                this.b = f44 * 3.0f;
                this.c = f44 * 3.0f;
                this.h.setStyle(Style.STROKE);
                this.h.setStrokeWidth(4.0f);
                this.h.setColor(-1);
                Path path11 = this.i;
                float f45 = this.d;
                path11.moveTo(f45 - this.b, (f45 - this.c) - (this.a * 5.0f));
                Path path12 = this.i;
                float f46 = this.d;
                path12.lineTo(f46 - this.b, f46 - this.c);
                Path path13 = this.i;
                float f47 = this.d;
                path13.lineTo((f47 - this.b) - (this.a * 5.0f), f47 - this.c);
                Path path14 = this.i;
                float f48 = this.d;
                path14.moveTo(this.b + f48, (f48 - this.c) - (this.a * 5.0f));
                Path path15 = this.i;
                float f49 = this.d;
                path15.lineTo(this.b + f49, f49 - this.c);
                Path path16 = this.i;
                float f50 = this.d;
                path16.lineTo(this.b + f50 + (this.a * 5.0f), f50 - this.c);
                Path path17 = this.i;
                float f51 = this.d;
                path17.moveTo(f51 - this.b, f51 + this.c + (this.a * 5.0f));
                Path path18 = this.i;
                float f52 = this.d;
                path18.lineTo(f52 - this.b, f52 + this.c);
                Path path19 = this.i;
                float f53 = this.d;
                path19.lineTo((f53 - this.b) - (this.a * 5.0f), f53 + this.c);
                Path path20 = this.i;
                float f54 = this.d;
                path20.moveTo(this.b + f54, f54 + this.c + (this.a * 5.0f));
                Path path21 = this.i;
                float f55 = this.d;
                path21.lineTo(this.b + f55, f55 + this.c);
                Path path22 = this.i;
                float f56 = this.d;
                path22.lineTo(this.b + f56 + (this.a * 5.0f), f56 + this.c);
                canvas2.drawPath(this.i, this.h);
                return;
        }
    }

    private void a(Canvas canvas) {
        float f2 = this.a;
        this.d = ((30.0f * f2) / 2.0f) - (f2 * 5.0f);
        this.b = f2 * 5.0f;
        this.c = f2 * 5.0f;
        this.h.setStyle(Style.FILL);
        this.h.setColor(-1);
        this.h.setStrokeWidth(4.0f);
        this.h.setAntiAlias(true);
        Path path = this.i;
        float f3 = this.d;
        path.moveTo(f3 - this.b, f3 - this.c);
        Path path2 = this.i;
        float f4 = this.d;
        path2.lineTo(f4, f4 - this.c);
        Path path3 = this.i;
        float f5 = this.d;
        float f6 = this.a;
        path3.lineTo((f6 * 6.0f) + f5, (f5 - this.c) - (f6 * 4.0f));
        Path path4 = this.i;
        float f7 = this.d;
        float f8 = this.a;
        path4.lineTo((6.0f * f8) + f7, f7 + this.c + (f8 * 4.0f));
        Path path5 = this.i;
        float f9 = this.d;
        path5.lineTo(f9, this.c + f9);
        Path path6 = this.i;
        float f10 = this.d;
        path6.lineTo(f10 - this.b, f10 + this.c);
        Path path7 = this.i;
        float f11 = this.d;
        path7.lineTo(f11 - this.b, f11 - this.c);
        canvas.drawPath(this.i, this.h);
    }

    private void b(Canvas canvas) {
        float f2 = this.a;
        this.e = 25.0f * f2;
        this.d = f2 * 30.0f;
        this.h.setAntiAlias(true);
        this.h.setColor(-1);
        this.h.setStrokeWidth(7.0f);
        this.h.setStyle(Style.STROKE);
        float f3 = this.d;
        canvas.drawCircle(f3, f3, this.e, this.h);
    }

    public void setSwitchInt(int i2) {
        this.f = i2;
    }
}

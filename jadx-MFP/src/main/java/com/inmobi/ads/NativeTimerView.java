package com.inmobi.ads;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.inmobi.commons.core.utilities.b.c;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class NativeTimerView extends View {
    long a;
    long b;
    ValueAnimator c;
    private Bitmap d;
    private Canvas e;
    private RectF f;
    private RectF g;
    private Rect h;
    private Paint i;
    private Paint j;
    private Paint k;
    private Paint l;
    private Paint m;
    private float n;
    private b o;

    public static class a implements AnimatorUpdateListener {
        public WeakReference<NativeTimerView> a;

        public a(NativeTimerView nativeTimerView) {
            this.a = new WeakReference<>(nativeTimerView);
        }

        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            NativeTimerView nativeTimerView = (NativeTimerView) this.a.get();
            if (nativeTimerView != null) {
                int visibility = nativeTimerView.getVisibility();
                if (visibility != 4 && visibility != 8) {
                    nativeTimerView.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                } else if (((double) ((Float) valueAnimator.getAnimatedValue()).floatValue()) >= 1.0d) {
                    nativeTimerView.b();
                }
            }
        }
    }

    interface b {
        void a();
    }

    public NativeTimerView(Context context) {
        this(context, null);
    }

    public NativeTimerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NativeTimerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = 0;
        this.i = new Paint();
        this.i.setAntiAlias(true);
        this.i.setColor(-723724);
        this.m = new Paint();
        this.m.setAntiAlias(true);
        this.m.setColor(-16777216);
        this.m.setTextAlign(Align.CENTER);
        this.m.setAntiAlias(true);
        this.h = new Rect();
        this.j = new Paint();
        this.j.setAntiAlias(true);
        this.j.setColor(-16777216);
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setColor(0);
        this.k.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.l = new Paint();
        this.l.setStyle(Style.STROKE);
        this.l.setAntiAlias(true);
        this.l.setColor(-16777216);
    }

    public void setTimerEventsListener(b bVar) {
        this.o = bVar;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i2);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        if (!(i2 == i4 && i3 == i5)) {
            this.d = Bitmap.createBitmap(i2, i3, Config.ARGB_8888);
            this.d.eraseColor(0);
            this.e = new Canvas(this.d);
        }
        super.onSizeChanged(i2, i3, i4, i5);
        float a2 = (float) c.a((int) (((float) getWidth()) * 4.0f * 0.007f));
        float a3 = (float) c.a((int) (((float) getWidth()) * 14.0f * 0.007f));
        float a4 = (float) c.a((int) (((float) getWidth()) * 5.0f * 0.007f));
        float a5 = (float) c.a((int) (((float) getWidth()) * 1.5f * 0.007f));
        this.f = new RectF(a4, a4, ((float) getWidth()) - a4, ((float) getHeight()) - a4);
        this.g = new RectF(this.f.left + a2, this.f.top + a2, this.f.right - a2, this.f.bottom - a2);
        this.l.setStrokeWidth(a5);
        this.m.setTextSize(a3);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.e.drawColor(0, Mode.CLEAR);
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        int min = Math.min(width, height);
        int a2 = c.a((int) (((float) getWidth()) * 7.0f * 0.007f));
        float f2 = (float) width;
        float f3 = (float) height;
        canvas.drawCircle(f2, f3, (float) min, this.i);
        canvas.drawCircle(f2, f3, (float) (min - a2), this.l);
        ValueAnimator valueAnimator = this.c;
        if (valueAnimator != null) {
            int currentPlayTime = (int) (this.a - (valueAnimator.getCurrentPlayTime() / 1000));
            if (((double) ((Float) this.c.getAnimatedValue()).floatValue()) >= 1.0d) {
                currentPlayTime = 0;
            }
            Paint paint = this.m;
            Rect rect = this.h;
            String valueOf = String.valueOf(currentPlayTime);
            paint.getTextBounds(valueOf, 0, valueOf.length(), rect);
            canvas.drawText(valueOf, (float) (getWidth() / 2), ((float) (getHeight() / 2)) + (((paint.descent() - paint.ascent()) / 2.0f) - paint.descent()), paint);
            if (((double) ((Float) this.c.getAnimatedValue()).floatValue()) >= 1.0d) {
                b();
            }
        }
        float f4 = this.n;
        if (f4 > BitmapDescriptorFactory.HUE_RED) {
            this.e.drawArc(this.f, 270.0f, f4, true, this.j);
            this.e.drawOval(this.g, this.k);
        }
        canvas.drawBitmap(this.d, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, null);
    }

    public void setTimerValue(long j2) {
        this.a = j2;
    }

    public final void a() {
        this.c = ValueAnimator.ofFloat(new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f});
        this.c.setDuration(TimeUnit.SECONDS.toMillis(this.a));
        this.c.setInterpolator(new LinearInterpolator());
        this.c.addUpdateListener(new a(this));
        this.c.start();
    }

    /* access modifiers changed from: 0000 */
    public final void a(float f2) {
        this.n = f2 * 360.0f;
        invalidate();
    }

    /* access modifiers changed from: private */
    public void b() {
        b bVar = this.o;
        if (bVar != null) {
            bVar.a();
            this.c.cancel();
            this.c = null;
        }
    }
}

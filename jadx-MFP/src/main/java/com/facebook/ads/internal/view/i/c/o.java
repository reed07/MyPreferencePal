package com.facebook.ads.internal.view.i.c;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.a.b;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.p;
import com.facebook.ads.internal.w.b.x;
import java.util.concurrent.atomic.AtomicInteger;

public class o extends RelativeLayout implements b {
    private static final int a = ((int) (x.b * 6.0f));
    private ObjectAnimator b;
    private AtomicInteger c;
    private ProgressBar d;
    /* access modifiers changed from: private */
    @Nullable
    public a e;
    private f f;
    private f g;
    private f h;
    private f i;

    public o(Context context) {
        this(context, a, -12549889);
    }

    public o(Context context, int i2, int i3) {
        super(context);
        this.f = new p() {
            public void a(com.facebook.ads.internal.view.i.b.o oVar) {
                if (o.this.e != null) {
                    o oVar2 = o.this;
                    o.a(oVar2, oVar2.e.getDuration(), o.this.e.getCurrentPositionInMillis());
                }
            }
        };
        this.g = new j() {
            public void a(i iVar) {
                o.this.b();
            }
        };
        this.h = new l() {
            public void a(k kVar) {
                if (o.this.e != null) {
                    o oVar = o.this;
                    o.a(oVar, oVar.e.getDuration(), o.this.e.getCurrentPositionInMillis());
                }
            }
        };
        this.i = new d() {
            public void a(c cVar) {
                if (o.this.e != null) {
                    o.c(o.this);
                }
            }
        };
        this.c = new AtomicInteger(-1);
        this.d = new ProgressBar(context, null, 16842872);
        this.d.setLayoutParams(new LayoutParams(-1, i2));
        setProgressBarColor(i3);
        this.d.setMax(10000);
        addView(this.d);
    }

    static /* synthetic */ void a(o oVar, int i2, int i3) {
        oVar.b();
        if (oVar.c.get() < i3 && i2 > i3) {
            oVar.b = ObjectAnimator.ofInt(oVar.d, "progress", new int[]{(i3 * 10000) / i2, (Math.min(i3 + Callback.DEFAULT_SWIPE_ANIMATION_DURATION, i2) * 10000) / i2});
            oVar.b.setDuration((long) Math.min(Callback.DEFAULT_SWIPE_ANIMATION_DURATION, i2 - i3));
            oVar.b.setInterpolator(new LinearInterpolator());
            oVar.b.start();
            oVar.c.set(i3);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        ObjectAnimator objectAnimator = this.b;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.b.setTarget(null);
            this.b = null;
            this.d.clearAnimation();
        }
    }

    static /* synthetic */ void c(o oVar) {
        oVar.b();
        oVar.b = ObjectAnimator.ofInt(oVar.d, "progress", new int[]{0, 0});
        oVar.b.setDuration(0);
        oVar.b.setInterpolator(new LinearInterpolator());
        oVar.b.start();
        oVar.c.set(0);
    }

    public void a() {
        b();
        this.d = null;
        this.e = null;
    }

    public void a(a aVar) {
        this.e = aVar;
        aVar.getEventBus().a((T[]) new f[]{this.g, this.h, this.f, this.i});
    }

    public void b(a aVar) {
        aVar.getEventBus().b((T[]) new f[]{this.f, this.h, this.g, this.i});
        this.e = null;
    }

    public void setProgressBarColor(int i2) {
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new ColorDrawable(0), new ColorDrawable(0), new ScaleDrawable(new ColorDrawable(i2), 8388611, 1.0f, -1.0f)});
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        this.d.setProgressDrawable(layerDrawable);
    }
}

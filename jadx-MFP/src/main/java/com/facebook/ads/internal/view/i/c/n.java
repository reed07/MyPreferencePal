package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.a.b;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.o;
import com.facebook.ads.internal.view.i.b.p;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class n extends View implements b {
    private final Paint a = new Paint();
    private final Rect b;
    /* access modifiers changed from: private */
    public float c;
    private final p d = new p() {
        public void a(o oVar) {
            if (n.this.f != null) {
                int duration = n.this.f.getDuration();
                if (duration > 0) {
                    n nVar = n.this;
                    nVar.c = ((float) nVar.f.getCurrentPositionInMillis()) / ((float) duration);
                } else {
                    n.this.c = BitmapDescriptorFactory.HUE_RED;
                }
                n.this.postInvalidate();
            }
        }
    };
    private final d e = new d() {
        public void a(c cVar) {
            if (n.this.f != null) {
                n.this.c = BitmapDescriptorFactory.HUE_RED;
                n.this.postInvalidate();
            }
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public a f;

    public n(Context context) {
        super(context);
        this.a.setStyle(Style.FILL);
        this.a.setColor(-9528840);
        this.b = new Rect();
    }

    public void a(a aVar) {
        this.f = aVar;
        aVar.getEventBus().a((T[]) new f[]{this.d, this.e});
    }

    public void b(a aVar) {
        aVar.getEventBus().b((T[]) new f[]{this.e, this.d});
        this.f = null;
    }

    public void draw(Canvas canvas) {
        this.b.set(0, 0, (int) (((float) getWidth()) * this.c), getHeight());
        canvas.drawRect(this.b, this.a);
        super.draw(canvas);
    }
}

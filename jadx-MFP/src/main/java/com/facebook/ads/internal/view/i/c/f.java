package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.a.b;
import com.facebook.ads.internal.view.i.b.w;
import com.facebook.ads.internal.view.i.b.x;
import com.facebook.ads.internal.w.c.c;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class f extends ImageView implements b {
    private static final int a = ((int) (Resources.getSystem().getDisplayMetrics().density * 4.0f));
    private final Paint b = new Paint();
    /* access modifiers changed from: private */
    @Nullable
    public a c;
    private final x d = new x() {
        public void a(w wVar) {
            f.this.a();
        }
    };

    public f(Context context) {
        super(context);
        this.b.setColor(-1728053248);
        setColorFilter(-1);
        int i = a;
        setPadding(i, i, i, i);
        c();
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                a aVar;
                float f;
                if (f.this.c != null) {
                    if (f.this.b()) {
                        aVar = f.this.c;
                        f = 1.0f;
                    } else {
                        aVar = f.this.c;
                        f = BitmapDescriptorFactory.HUE_RED;
                    }
                    aVar.setVolume(f);
                    f.this.a();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean b() {
        a aVar = this.c;
        return aVar != null && aVar.getVolume() == BitmapDescriptorFactory.HUE_RED;
    }

    private void c() {
        setImageBitmap(c.a(com.facebook.ads.internal.w.c.b.SOUND_ON));
    }

    public final void a() {
        if (this.c != null) {
            if (b()) {
                setImageBitmap(c.a(com.facebook.ads.internal.w.c.b.SOUND_OFF));
            } else {
                c();
            }
        }
    }

    public void a(a aVar) {
        this.c = aVar;
        a aVar2 = this.c;
        if (aVar2 != null) {
            aVar2.getEventBus().a(this.d);
        }
    }

    public void b(a aVar) {
        a aVar2 = this.c;
        if (aVar2 != null) {
            aVar2.getEventBus().b(this.d);
        }
        this.c = null;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        canvas.drawCircle((float) width, (float) height, (float) Math.min(width, height), this.b);
        super.onDraw(canvas);
    }
}

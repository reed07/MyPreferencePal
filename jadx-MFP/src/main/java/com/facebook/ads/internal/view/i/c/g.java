package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.i.a.c;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.w.b.x;

public class g extends c {
    private final ImageView a;
    private final f<k> b = new f<k>() {
        public Class<k> a() {
            return k.class;
        }

        public void a(k kVar) {
            g.this.setVisibility(8);
        }
    };
    private final f<com.facebook.ads.internal.view.i.b.c> c = new f<com.facebook.ads.internal.view.i.b.c>() {
        public Class<com.facebook.ads.internal.view.i.b.c> a() {
            return com.facebook.ads.internal.view.i.b.c.class;
        }

        public void a(com.facebook.ads.internal.view.i.b.c cVar) {
            g.this.setVisibility(0);
        }
    };

    public g(Context context) {
        super(context);
        this.a = new ImageView(context);
        this.a.setScaleType(ScaleType.FIT_CENTER);
        x.a((View) this.a, -16777216);
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.a);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a((T[]) new f[]{this.b, this.c});
        }
    }

    public void a(@Nullable String str, @Nullable e eVar) {
        if (str == null) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        d a2 = new d(this.a).a();
        if (eVar != null) {
            a2.a(eVar);
        }
        a2.a(str);
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b((T[]) new f[]{this.c, this.b});
        }
        super.b();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.a.layout(0, 0, i3 - i, i4 - i2);
    }

    public void setImage(@Nullable String str) {
        a(str, null);
    }
}

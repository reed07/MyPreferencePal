package com.facebook.ads;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.ads.internal.t.a;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.t.k;
import com.facebook.ads.internal.view.h.b;
import com.facebook.ads.internal.view.h.c;
import com.facebook.ads.internal.w.b.x;

public class BannerTemplateLayout extends NativeAdLayout {
    private static final int a = ((int) (x.b * 280.0f));
    private static final int b = ((int) (x.b * 375.0f));
    private final c c;

    BannerTemplateLayout(Context context, NativeBannerAd nativeBannerAd, j jVar) {
        super(context);
        MediaView mediaView = new MediaView(getContext());
        AdOptionsView adOptionsView = new AdOptionsView(getContext(), nativeBannerAd, this);
        adOptionsView.setIconColor(jVar.c());
        k a2 = nativeBannerAd.a().a();
        if (a2 == k.HEIGHT_50) {
            b bVar = new b(context, nativeBannerAd.f(), jVar, a2, mediaView, adOptionsView);
            this.c = bVar;
            setMinWidth(a);
        } else {
            a aVar = new a(context, nativeBannerAd.f(), adOptionsView, null, mediaView, a2, jVar);
            this.c = aVar;
            setMinWidth(a);
            setMaxWidth(b);
        }
        x.a((View) this, jVar.b());
        nativeBannerAd.registerViewForInteraction(this, mediaView, this.c.getViewsForInteraction());
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        addView(this.c.getView(), layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c.a();
    }
}

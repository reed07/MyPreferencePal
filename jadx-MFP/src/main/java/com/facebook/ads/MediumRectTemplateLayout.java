package com.facebook.ads;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.ads.internal.t.a;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.w.b.x;
import java.util.List;

public class MediumRectTemplateLayout extends NativeAdLayout {
    private static final int a = ((int) (x.b * 280.0f));
    private static final int b = ((int) (x.b * 375.0f));
    private final a c;

    MediumRectTemplateLayout(Context context, NativeAd nativeAd, j jVar) {
        super(context);
        MediaView mediaView = new MediaView(getContext());
        MediaView mediaView2 = new MediaView(getContext());
        AdOptionsView adOptionsView = new AdOptionsView(getContext(), nativeAd, this);
        adOptionsView.setIconColor(jVar.c());
        a aVar = new a(context, nativeAd.f(), adOptionsView, mediaView2, mediaView, nativeAd.e().a(), jVar);
        this.c = aVar;
        setMinWidth(a);
        setMaxWidth(b);
        x.a((View) this, jVar.b());
        nativeAd.registerViewForInteraction((View) this, mediaView2, mediaView, (List<View>) this.c.getViewsForInteraction());
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        addView(this.c, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c.a();
    }
}

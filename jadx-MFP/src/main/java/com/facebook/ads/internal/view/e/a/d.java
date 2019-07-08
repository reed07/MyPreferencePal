package com.facebook.ads.internal.view.e.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.i.b;
import com.facebook.ads.internal.view.i.c.d.a;
import com.facebook.ads.internal.view.i.c.f;
import com.facebook.ads.internal.view.i.c.g;
import com.facebook.ads.internal.view.i.c.l;
import com.facebook.ads.internal.view.y;
import com.facebook.ads.internal.w.b.x;
import java.util.Map;

public class d extends FrameLayout {
    private static final int a = ((int) (x.b * 16.0f));
    private final c b;
    private y c;
    private f d;
    /* access modifiers changed from: private */
    public l e;
    private g f;
    @Nullable
    private b g;

    public d(Context context, c cVar) {
        super(context);
        this.b = cVar;
        setUpView(context);
    }

    private void setUpPlugins(Context context) {
        this.c.d();
        this.f = new g(context);
        this.c.a((com.facebook.ads.internal.view.i.a.b) this.f);
        this.d = new f(context);
        this.c.a((com.facebook.ads.internal.view.i.a.b) new com.facebook.ads.internal.view.i.c.b(context));
        this.c.a((com.facebook.ads.internal.view.i.a.b) this.d);
        this.e = new l(context, true);
        this.c.a((com.facebook.ads.internal.view.i.a.b) this.e);
        this.c.a((com.facebook.ads.internal.view.i.a.b) new com.facebook.ads.internal.view.i.c.d(this.e, a.FADE_OUT_ON_PLAY, true, true));
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        int i = a;
        layoutParams.setMargins(i, i, i, i);
        this.d.setLayoutParams(layoutParams);
        this.c.addView(this.d);
    }

    private void setUpVideo(Context context) {
        this.c = new y(context);
        this.c.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        x.a((View) this.c);
        addView(this.c);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                d.this.e.performClick();
            }
        });
    }

    private void setUpView(Context context) {
        setUpVideo(context);
        setUpPlugins(context);
    }

    public void a() {
        this.c.a(true);
    }

    public void a(com.facebook.ads.internal.o.f fVar) {
        this.c.getEventBus().a(fVar);
    }

    public void a(c cVar, String str, Map<String, String> map) {
        c();
        b bVar = new b(getContext(), cVar, (com.facebook.ads.internal.view.i.a) this.c, str, map);
        this.g = bVar;
    }

    public void a(com.facebook.ads.internal.view.i.a.a aVar) {
        this.c.a(aVar);
    }

    public boolean b() {
        return this.c.j();
    }

    public void c() {
        b bVar = this.g;
        if (bVar != null) {
            bVar.a();
            this.g = null;
        }
    }

    @VisibleForTesting
    public com.facebook.ads.internal.view.i.a getSimpleVideoView() {
        return this.c;
    }

    public float getVolume() {
        return this.c.getVolume();
    }

    public void setPlaceholderUrl(String str) {
        this.f.setImage(str);
    }

    public void setVideoURI(String str) {
        this.c.setVideoURI(str);
    }

    public void setVolume(float f2) {
        this.c.setVolume(f2);
        this.d.a();
    }
}

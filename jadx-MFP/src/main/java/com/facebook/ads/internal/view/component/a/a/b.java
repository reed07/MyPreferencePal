package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.b.n;
import com.facebook.ads.internal.view.i.b.w;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class b extends com.facebook.ads.internal.view.component.a.c {
    private static final int c = ((int) (x.b * 1.0f));
    private static final int d = ((int) (x.b * 4.0f));
    private static final int e = ((int) (x.b * 6.0f));
    private com.facebook.ads.internal.view.x f;
    private com.facebook.ads.internal.view.e.a.d g;
    private RelativeLayout h;
    private final String i;
    private final Paint j;
    private boolean k;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.view.e.a.a l;
    private final Path m = new Path();
    private final RectF n = new RectF();
    /* access modifiers changed from: private */
    public boolean o;
    /* access modifiers changed from: private */
    public boolean p;
    private a q;
    private final com.facebook.ads.internal.view.i.b.x r = new com.facebook.ads.internal.view.i.b.x() {
        public void a(w wVar) {
            b.this.l.c().a(b.this.getVideoView().getVolume());
        }
    };
    private final com.facebook.ads.internal.view.i.b.d s = new com.facebook.ads.internal.view.i.b.d() {
        public void a(com.facebook.ads.internal.view.i.b.c cVar) {
            b.this.l.d().a(((Integer) b.this.getTag(-1593835536)).intValue());
        }
    };
    private final l t = new l() {
        public void a(k kVar) {
            b.this.l.e().a(b.this);
        }
    };
    private final j u = new j() {
        public void a(i iVar) {
            b.this.l.e().b(b.this);
        }
    };
    private final n v = new n() {
        public void a(m mVar) {
            b.this.p = true;
            b.b(b.this);
        }
    };

    public interface a {
        void a();
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$b reason: collision with other inner class name */
    private static class C0017b implements com.facebook.ads.internal.view.c.e {
        final WeakReference<b> a;

        private C0017b(b bVar) {
            this.a = new WeakReference<>(bVar);
        }

        public void a(boolean z) {
            b bVar = (b) this.a.get();
            if (bVar != null) {
                bVar.o = z;
                b.b(bVar);
            }
        }
    }

    public interface c {
        void a(int i);
    }

    public interface d {
        void a(View view);

        void b(View view);
    }

    public interface e {
        float a();

        void a(float f);
    }

    b(com.facebook.ads.internal.view.component.a.e eVar, h hVar, boolean z, String str, com.facebook.ads.internal.view.e.a.a aVar) {
        super(eVar, hVar, z);
        this.l = aVar;
        this.i = str;
        setGravity(17);
        int i2 = c;
        setPadding(i2, 0, i2, i2);
        x.a((View) this, 0);
        setUpView(getContext());
        this.j = new Paint();
        this.j.setColor(-16777216);
        this.j.setStyle(Style.FILL);
        this.j.setAlpha(16);
        this.j.setAntiAlias(true);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    private void a(View view) {
        view.setLayoutParams(new LayoutParams(-1, -2));
        x.a(view);
    }

    static /* synthetic */ void b(b bVar) {
        if (bVar.q != null) {
            if ((bVar.f() && bVar.p) || (!bVar.f() && bVar.o)) {
                bVar.q.a();
            }
        }
    }

    private void setUpView(Context context) {
        setUpImageView(context);
        setUpVideoView(context);
        setUpMediaContainer(context);
        this.h.addView(this.f);
        this.h.addView(this.g);
        a(context);
    }

    /* access modifiers changed from: protected */
    public abstract void a(Context context);

    public void a(com.facebook.ads.internal.adapters.b.i iVar, Map<String, String> map) {
        getCtaButton().a(iVar, this.i, map);
    }

    public void a(String str, String str2) {
        getTitleDescContainer().a(str, str2, null, true, false);
    }

    public void a(Map<String, String> map) {
        this.g.c();
        if (f()) {
            this.g.a(getAdEventManager(), this.i, map);
        }
    }

    public boolean a() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return false;
    }

    public boolean f() {
        return this.k;
    }

    public boolean g() {
        return f() && this.g.b();
    }

    /* access modifiers changed from: protected */
    public final RelativeLayout getMediaContainer() {
        return this.h;
    }

    public final com.facebook.ads.internal.view.e.a.d getVideoView() {
        return this.g;
    }

    public void h() {
        if (f()) {
            j();
            this.g.a(com.facebook.ads.internal.view.i.a.a.AUTO_STARTED);
        }
    }

    public void i() {
        if (f()) {
            this.g.a();
        }
    }

    public void j() {
        float a2 = this.l.c().a();
        if (f() && a2 != this.g.getVolume()) {
            this.g.setVolume(a2);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.m.reset();
        this.n.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) getWidth(), (float) getHeight());
        Path path = this.m;
        RectF rectF = this.n;
        int i2 = e;
        path.addRoundRect(rectF, (float) i2, (float) i2, Direction.CW);
        canvas.drawPath(this.m, this.j);
        this.n.set((float) c, BitmapDescriptorFactory.HUE_RED, (float) (getWidth() - c), (float) (getHeight() - c));
        Path path2 = this.m;
        RectF rectF2 = this.n;
        int i3 = d;
        path2.addRoundRect(rectF2, (float) i3, (float) i3, Direction.CW);
        canvas.clipPath(this.m);
        super.onDraw(canvas);
    }

    public void setImageUrl(String str) {
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        new com.facebook.ads.internal.view.c.d((ImageView) this.f).a().a((com.facebook.ads.internal.view.c.e) new C0017b()).a(str);
    }

    public void setIsVideo(boolean z) {
        this.k = z;
    }

    public void setOnAssetsLoadedListener(a aVar) {
        this.q = aVar;
    }

    /* access modifiers changed from: protected */
    public void setUpImageView(Context context) {
        this.f = new com.facebook.ads.internal.view.x(context);
        a((View) this.f);
    }

    /* access modifiers changed from: protected */
    public void setUpMediaContainer(Context context) {
        this.h = new RelativeLayout(context);
        a((View) this.h);
    }

    /* access modifiers changed from: protected */
    public void setUpVideoView(Context context) {
        this.g = new com.facebook.ads.internal.view.e.a.d(context, getAdEventManager());
        a((View) this.g);
    }

    public void setVideoPlaceholderUrl(String str) {
        this.g.setPlaceholderUrl(str);
    }

    public void setVideoUrl(String str) {
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.g.setVideoURI(str);
        this.g.a((f) this.r);
        this.g.a((f) this.s);
        this.g.a((f) this.t);
        this.g.a((f) this.u);
        this.g.a((f) this.v);
    }
}

package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.ads.internal.adapters.b.n;
import com.facebook.ads.internal.adapters.b.o;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.component.j;
import com.facebook.ads.internal.view.i;
import com.facebook.ads.internal.w.b.e;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class b extends RelativeLayout implements com.facebook.ads.internal.view.c.a.d, com.facebook.ads.internal.w.b.e.a {
    private static final int a = ((int) (x.b * 64.0f));
    private static final LayoutParams b = new LayoutParams(-1, -1);
    private static final int c = ((int) (x.b * 16.0f));
    private static final int d = ((int) (x.b * 12.0f));
    private static final int e = ((int) (x.b * 10.0f));
    private static final float f = ((float) ((int) (x.b * 4.0f)));
    private final o g;
    private final n h;
    private final com.facebook.ads.internal.adapters.b.b i;
    private final com.facebook.ads.internal.s.c j;
    private final i k;
    /* access modifiers changed from: private */
    public final AtomicBoolean l = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final e m;
    private final e n;
    private final boolean o;
    /* access modifiers changed from: private */
    public WeakReference<com.facebook.ads.internal.view.c.a> p;
    private com.facebook.ads.internal.view.c.a.b q;
    private com.facebook.ads.internal.view.component.c r;
    private a s;
    private RelativeLayout t;
    /* access modifiers changed from: private */
    public boolean u = false;
    private Toast v;
    /* access modifiers changed from: private */
    @Nullable
    public c w;

    private static class a implements OnClickListener {
        final WeakReference<b> a;

        a(b bVar) {
            this.a = new WeakReference<>(bVar);
        }

        public void onClick(View view) {
            if (this.a.get() != null) {
                b.g((b) this.a.get());
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f.b$b reason: collision with other inner class name */
    private static class C0019b implements OnTouchListener {
        final WeakReference<com.facebook.ads.internal.view.c.a> a;
        final com.facebook.ads.internal.s.c b;
        final o c;

        private C0019b(com.facebook.ads.internal.view.c.a aVar, com.facebook.ads.internal.s.c cVar, o oVar) {
            this.a = new WeakReference<>(aVar);
            this.b = cVar;
            this.c = oVar;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.a.get() != null && motionEvent.getAction() == 1) {
                HashMap hashMap = new HashMap();
                ((com.facebook.ads.internal.view.c.a) this.a.get()).getViewabilityChecker().a((Map<String, String>) hashMap);
                hashMap.put("touch", k.a(((com.facebook.ads.internal.view.c.a) this.a.get()).getTouchDataRecorder().e()));
                this.b.d(this.c.g(), hashMap);
            }
            return false;
        }
    }

    public interface c {
        void a();

        void a(com.facebook.ads.internal.x.a aVar, w wVar);

        void a(boolean z);

        void b();

        void c();

        void d();
    }

    private class d {
        private d() {
        }

        @JavascriptInterface
        public void onCTAClick() {
            b.g(b.this);
        }
    }

    public b(Context context, o oVar, com.facebook.ads.internal.s.c cVar, C0012a aVar, c cVar2, boolean z, boolean z2) {
        super(context);
        this.g = oVar;
        this.h = oVar.f().j();
        this.i = oVar.e();
        this.j = cVar;
        this.w = cVar2;
        this.k = new i(context, aVar, com.facebook.ads.internal.view.i.a.CROSS);
        this.o = z2;
        this.m = new e(z ? this.h.c() : 0, this);
        this.n = new e(this.h.h() ? 2 : 0, new com.facebook.ads.internal.w.b.e.a() {
            public void a() {
                b.this.g();
            }

            public void a(int i) {
            }
        });
        this.k.a(this.i.a(), true);
        this.k.setShowPageDetails(false);
        this.k.a(this.g.b(), this.g.g(), this.h.c());
        this.k.setToolbarListener(new com.facebook.ads.internal.view.i.b() {
            public void a() {
                if (b.this.w != null) {
                    b.this.w.c();
                }
            }
        });
        x.a((View) this.k);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.k.setLayoutParams(layoutParams);
        this.s = new a(getContext(), this.g);
        setLayoutParams(b);
        x.a((View) this, this.i.a().d(true));
        addView(this.s, b);
        x.a((View) this, -14473425);
        setLayoutParams(b);
    }

    private static TextView a(ViewGroup viewGroup) {
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof TextView) {
                return (TextView) childAt;
            }
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt);
            }
        }
        return null;
    }

    private void b(int i2) {
        Toast toast = this.v;
        if (toast != null) {
            toast.setGravity(49, 0, a);
            String valueOf = String.valueOf(i2);
            TextView a2 = a((ViewGroup) this.v.getView());
            if (a2 != null) {
                a2.setText(this.h.e().replace("[secs]", valueOf));
                a2.setGravity(17);
            }
        }
    }

    static /* synthetic */ void f(b bVar) {
        Toast toast = bVar.v;
        if (toast == null || toast.getView().getWindowVisibility() != 0) {
            bVar.v = Toast.makeText(bVar.getContext(), bVar.h.e(), 1);
            bVar.b(bVar.m.e());
            bVar.v.show();
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        c cVar = this.w;
        if (cVar != null) {
            cVar.a();
        }
        this.t = new RelativeLayout(getContext());
        x.a((View) this.t);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        int i2 = c;
        int i3 = d;
        layoutParams.setMargins(i2, i3, i2, i3);
        layoutParams.addRule(12);
        this.t.setLayoutParams(layoutParams);
        com.facebook.ads.internal.view.component.c cVar2 = new com.facebook.ads.internal.view.component.c(getContext(), true, false, this.i.a());
        cVar2.setButtonColor(452984831);
        cVar2.setText(this.g.d().b());
        cVar2.getBackground().setAlpha(0);
        x.a((View) cVar2);
        cVar2.setOnClickListener(new a(this));
        cVar2.setTextSize(14.0f);
        cVar2.setIncludeFontPadding(false);
        int i4 = e;
        cVar2.setPadding(i4, i4, i4, i4);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(11);
        cVar2.setLayoutParams(layoutParams2);
        if (!this.o) {
            cVar2.setVisibility(8);
        }
        this.r = cVar2;
        com.facebook.ads.internal.view.component.c cVar3 = this.r;
        j jVar = new j(getContext(), this.g.e().a(), true, 16, 14, 0);
        x.a((View) jVar);
        jVar.a(this.g.c().a(), this.g.c().b(), null, false, true);
        TextView descriptionTextView = jVar.getDescriptionTextView();
        descriptionTextView.setAlpha(0.8f);
        descriptionTextView.setMaxLines(1);
        descriptionTextView.setEllipsize(TruncateAt.END);
        TextView titleTextView = jVar.getTitleTextView();
        titleTextView.setMaxLines(1);
        titleTextView.setEllipsize(TruncateAt.END);
        LayoutParams layoutParams3 = new LayoutParams(-1, -2);
        layoutParams3.addRule(0, cVar3.getId());
        layoutParams3.setMargins(0, 0, c, 0);
        jVar.setLayoutParams(layoutParams3);
        LayoutParams layoutParams4 = (LayoutParams) this.r.getLayoutParams();
        layoutParams4.addRule(6, jVar.getId());
        layoutParams4.addRule(8, jVar.getId());
        this.q = new com.facebook.ads.internal.view.c.a.c() {
            public void a(int i, @Nullable String str) {
                b.this.u = true;
                if (b.this.p.get() != null) {
                    ((com.facebook.ads.internal.view.c.a) b.this.p.get()).setVisibility(4);
                }
                if (b.this.w != null) {
                    b.this.w.d();
                }
            }

            public void b() {
                if (b.this.l.compareAndSet(false, true) && b.this.p.get() != null && b.this.w != null) {
                    com.facebook.ads.internal.view.c.a aVar = (com.facebook.ads.internal.view.c.a) b.this.p.get();
                    b.this.w.a(aVar.getViewabilityChecker(), aVar.getTouchDataRecorder());
                    b.this.m.a();
                }
            }
        };
        com.facebook.ads.internal.view.c.a aVar = new com.facebook.ads.internal.view.c.a(getContext(), new WeakReference(this.q), 10);
        aVar.setLogMultipleImpressions(false);
        aVar.setWaitForAssetsToLoad(true);
        aVar.setCheckAssetsByJavascriptBridge(false);
        aVar.setWebViewTimeoutInMillis(this.h.g());
        aVar.setRequestId(this.g.a());
        WebSettings settings = aVar.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
        }
        this.p = new WeakReference<>(aVar);
        aVar.loadUrl(getMarkupUrl());
        aVar.setOnTouchListener(new C0019b(aVar, this.j, this.g));
        aVar.addJavascriptInterface(new d(), "FbPlayableAd");
        aVar.setCornerRadius(f);
        x.a((View) this, -14473425);
        LayoutParams layoutParams5 = new LayoutParams(-1, -1);
        int i5 = c;
        layoutParams5.setMargins(i5, 0, i5, 0);
        layoutParams5.addRule(3, this.k.getId());
        layoutParams5.addRule(2, this.t.getId());
        aVar.setLayoutParams(layoutParams5);
        aVar.setVisibility(4);
        aVar.setOnAssetsLoadedListener(this);
        this.t.addView(jVar);
        this.t.addView(this.r);
        addView(this.k);
        addView(aVar);
        addView(this.t);
        this.k.setVisibility(4);
        aVar.setVisibility(4);
        aVar.setTranslationY(50.0f);
        this.t.setVisibility(4);
        this.t.setTranslationY(200.0f);
    }

    static /* synthetic */ void g(b bVar) {
        boolean z = !bVar.o && !bVar.m.d();
        c cVar = bVar.w;
        if (cVar != null) {
            cVar.a(z);
        }
        if (z) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    b.f(b.this);
                }
            });
        }
    }

    private String getMarkupUrl() {
        return !TextUtils.isEmpty(this.h.j()) ? this.h.j() : this.h.a();
    }

    public void a() {
        c cVar = this.w;
        if (cVar != null) {
            cVar.b();
        }
        this.k.a(true);
        if (!this.o) {
            x.a((ViewGroup) this, 500);
            this.r.setVisibility(0);
        }
    }

    public void a(int i2) {
        this.k.setProgress((1.0f - (((float) i2) / ((float) this.h.c()))) * 100.0f);
        b(i2);
    }

    public void b() {
        if (!this.u && this.p.get() != null) {
            com.facebook.ads.internal.view.c.a adWebView = getAdWebView();
            if (adWebView != null) {
                x.a((ViewGroup) this);
                adWebView.setVisibility(0);
                x.b(this.s);
                this.k.setVisibility(0);
                this.t.setVisibility(0);
                adWebView.animate().setStartDelay(100).setDuration(300).translationYBy(-50.0f);
                this.t.animate().setStartDelay(100).setDuration(300).translationYBy(-200.0f);
            }
        }
    }

    public void c() {
        if (this.h.h()) {
            this.n.a();
            return;
        }
        removeAllViews();
        g();
    }

    public void d() {
        e eVar;
        if (!this.n.d()) {
            eVar = this.n;
        } else if (!this.m.c()) {
            eVar = this.m;
        } else {
            return;
        }
        eVar.a();
    }

    public void e() {
        this.n.b();
        this.m.b();
    }

    public void f() {
        this.n.b();
        this.m.b();
        this.k.setToolbarListener(null);
        WeakReference<com.facebook.ads.internal.view.c.a> weakReference = this.p;
        com.facebook.ads.internal.view.c.a aVar = weakReference != null ? (com.facebook.ads.internal.view.c.a) weakReference.get() : null;
        if (aVar != null) {
            aVar.removeJavascriptInterface("FbPlayableAd");
        }
        this.w = null;
        this.v = null;
    }

    public com.facebook.ads.internal.view.c.a getAdWebView() {
        WeakReference<com.facebook.ads.internal.view.c.a> weakReference = this.p;
        if (weakReference != null) {
            return (com.facebook.ads.internal.view.c.a) weakReference.get();
        }
        return null;
    }
}

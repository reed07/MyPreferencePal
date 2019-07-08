package com.facebook.ads.internal.view.g;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.n;
import com.facebook.ads.internal.adapters.b.o;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.view.component.j;
import com.facebook.ads.internal.view.i.b.aa;
import com.facebook.ads.internal.w.b.p;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.e.e;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class c {
    /* access modifiers changed from: private */
    public static final String a = "c";
    private static final int b = ((int) (x.b * 4.0f));
    private static final int c = ((int) (x.b * 72.0f));
    private static final int d = ((int) (x.b * 8.0f));
    private com.facebook.ads.internal.view.component.a e;
    /* access modifiers changed from: private */
    public final Context f;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.s.c g;
    /* access modifiers changed from: private */
    public final q h;
    private final String i;
    private final h j;
    private final com.facebook.ads.internal.x.a k;
    private final w l;
    private Executor m = p.a;
    /* access modifiers changed from: private */
    @Nullable
    public C0012a n;
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.internal.view.c.a o;
    @Nullable
    private com.facebook.ads.internal.view.c.a.b p;

    public enum a {
        SCREENSHOTS,
        MARKUP,
        PLAYABLE,
        INFO
    }

    private static class b implements com.facebook.ads.internal.view.f.b.c {
        final WeakReference<c> a;

        private b(c cVar) {
            this.a = new WeakReference<>(cVar);
        }

        public void a() {
        }

        public void a(com.facebook.ads.internal.x.a aVar, w wVar) {
        }

        public void a(boolean z) {
            if (this.a.get() != null) {
                ((c) this.a.get()).g().performClick();
            }
        }

        public void b() {
        }

        public void c() {
            if (this.a.get() != null) {
                c.a((c) this.a.get());
            }
        }

        public void d() {
            c();
        }
    }

    public c(Context context, com.facebook.ads.internal.s.c cVar, q qVar, C0012a aVar, com.facebook.ads.internal.x.a aVar2, w wVar) {
        this.f = context;
        this.g = cVar;
        this.h = qVar;
        this.n = aVar;
        this.i = com.facebook.ads.internal.o.c.a(this.h.k().b());
        this.j = this.h.i().a();
        this.k = aVar2;
        this.l = wVar;
    }

    static /* synthetic */ void a(c cVar) {
        C0012a aVar = cVar.n;
        if (aVar != null) {
            aVar.a(aa.REWARDED_VIDEO_END_ACTIVITY.a());
        }
    }

    /* access modifiers changed from: private */
    public com.facebook.ads.internal.view.component.a g() {
        com.facebook.ads.internal.view.component.a aVar = this.e;
        if (aVar != null) {
            return aVar;
        }
        com.facebook.ads.internal.view.component.a aVar2 = new com.facebook.ads.internal.view.component.a(this.f, true, false, aa.REWARDED_VIDEO_AD_CLICK.a(), this.j, this.g, this.n, this.k, this.l);
        this.e = aVar2;
        this.e.a(this.h.h(), this.h.a(), new HashMap());
        return this.e;
    }

    public boolean a() {
        return b() == a.MARKUP;
    }

    public a b() {
        n j2 = this.h.j().j();
        return (j2 == null || !j2.i()) ? !this.h.k().d().isEmpty() ? a.SCREENSHOTS : !TextUtils.isEmpty(this.i) ? a.MARKUP : a.INFO : a.PLAYABLE;
    }

    public Pair<a, View> c() {
        a b2 = b();
        switch (b2) {
            case MARKUP:
                this.p = new com.facebook.ads.internal.view.c.a.c() {
                    public void a() {
                        if (c.this.o != null && !TextUtils.isEmpty(c.this.h.k().c())) {
                            c.this.o.post(new Runnable() {
                                public void run() {
                                    if (c.this.o == null || c.this.o.c()) {
                                        Log.w(c.a, "Webview already destroyed, cannot activate");
                                        return;
                                    }
                                    com.facebook.ads.internal.view.c.a f = c.this.o;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("javascript:");
                                    sb.append(c.this.h.k().c());
                                    f.loadUrl(sb.toString());
                                }
                            });
                        }
                    }

                    public void a(String str, Map<String, String> map) {
                        Uri parse = Uri.parse(str);
                        if (!"fbad".equals(parse.getScheme()) || !parse.getAuthority().equals("close")) {
                            if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority()) && c.this.n != null) {
                                c.this.n.a(aa.REWARDED_VIDEO_AD_CLICK.a());
                            }
                            com.facebook.ads.internal.a.b a2 = com.facebook.ads.internal.a.c.a(c.this.f, c.this.g, c.this.h.a(), parse, map);
                            if (a2 != null) {
                                try {
                                    a2.a();
                                } catch (Exception e) {
                                    Log.e(c.a, "Error executing action", e);
                                }
                            }
                            return;
                        }
                        c.a(c.this);
                    }
                };
                this.o = new com.facebook.ads.internal.view.c.a(this.f, new WeakReference(this.p), 1);
                this.o.loadDataWithBaseURL(com.facebook.ads.internal.w.e.b.a(), this.i, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                return new Pair<>(b2, this.o);
            case SCREENSHOTS:
                RecyclerView recyclerView = new RecyclerView(this.f);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.f, 0, false));
                recyclerView.setAdapter(new d(this.h.k().d(), b));
                return new Pair<>(b2, recyclerView);
            case PLAYABLE:
                com.facebook.ads.internal.view.f.b bVar = new com.facebook.ads.internal.view.f.b(this.f, o.a(this.h), this.g, this.n, new b(), false, false);
                return new Pair<>(b2, bVar);
            default:
                j jVar = new j(this.f, this.j, true, false, false);
                jVar.a(this.h.g().a(), this.h.g().c(), null, false, true);
                jVar.setAlignment(17);
                com.facebook.ads.internal.view.component.a g2 = g();
                f fVar = new f(this.f);
                x.a((View) fVar, 0);
                fVar.setRadius(50);
                new d((ImageView) fVar).a().a(this.h.f().b());
                LinearLayout linearLayout = new LinearLayout(this.f);
                linearLayout.setOrientation(1);
                linearLayout.setGravity(17);
                int i2 = c;
                linearLayout.addView(fVar, new LayoutParams(i2, i2));
                LayoutParams layoutParams = new LayoutParams(-2, -2);
                int i3 = d;
                layoutParams.setMargins(0, i3, 0, i3);
                linearLayout.addView(jVar, layoutParams);
                linearLayout.addView(g2, layoutParams);
                return new Pair<>(b2, linearLayout);
        }
    }

    public void d() {
        String a2 = this.h.k().a();
        if (!TextUtils.isEmpty(a2)) {
            e eVar = new e(this.f, new HashMap());
            eVar.a((com.facebook.ads.internal.w.e.e.a) new com.facebook.ads.internal.w.e.e.a() {
                public void a() {
                    if (c.this.n != null) {
                        c.this.n.a(aa.REWARD_SERVER_FAILED.a());
                    }
                }

                public void a(com.facebook.ads.internal.w.e.f fVar) {
                    C0012a aVar;
                    aa aaVar;
                    if (c.this.n != null) {
                        if (fVar == null || !fVar.a()) {
                            aVar = c.this.n;
                            aaVar = aa.REWARD_SERVER_FAILED;
                        } else {
                            aVar = c.this.n;
                            aaVar = aa.REWARD_SERVER_SUCCESS;
                        }
                        aVar.a(aaVar.a());
                    }
                }
            });
            eVar.executeOnExecutor(this.m, new String[]{a2});
        }
    }

    public void e() {
        com.facebook.ads.internal.view.c.a aVar = this.o;
        if (aVar != null) {
            aVar.destroy();
            this.o = null;
            this.p = null;
        }
    }
}

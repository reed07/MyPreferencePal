package com.facebook.ads.internal.t;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.i.a.b;
import com.facebook.ads.internal.view.i.c.g;
import com.facebook.ads.internal.view.i.c.h;
import com.facebook.ads.internal.view.i.d.d;
import com.facebook.ads.internal.view.p;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.x.a.C0023a;
import java.util.concurrent.atomic.AtomicBoolean;

public class c {
    private static final String a = "c";
    private final g b;
    private final com.facebook.ads.internal.x.a c;
    private final C0023a d;
    private final View e;
    private final com.facebook.ads.internal.view.i.d.a f = new com.facebook.ads.internal.view.i.d.a() {
        public void a() {
            c.this.n.set(true);
            if (c.this.h != null) {
                c.this.h.a(c.this.m.get());
            }
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public p g;
    /* access modifiers changed from: private */
    @Nullable
    public a h;
    private Context i;
    private boolean j;
    /* access modifiers changed from: private */
    public boolean k;
    /* access modifiers changed from: private */
    public boolean l;
    /* access modifiers changed from: private */
    public final AtomicBoolean m = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final AtomicBoolean n = new AtomicBoolean(false);
    private l o = l.DEFAULT;

    public interface a {
        void a(boolean z);
    }

    public c(Context context, View view) {
        this.i = context;
        this.e = view;
        this.b = new g(context);
        this.d = new C0023a() {
            public void a() {
                if (c.this.g != null) {
                    if (!c.this.l && (c.this.k || c.g(c.this))) {
                        c.a(c.this, com.facebook.ads.internal.view.i.a.a.AUTO_STARTED);
                    }
                    c.this.k = false;
                    c.this.l = false;
                }
            }

            public void b() {
                if (c.this.g != null) {
                    c.this.g.e();
                }
            }
        };
        this.c = new com.facebook.ads.internal.x.a(this.e, 50, true, this.d);
        g();
    }

    static /* synthetic */ void a(c cVar, com.facebook.ads.internal.view.i.a.a aVar) {
        p pVar = cVar.g;
        if (pVar != null) {
            pVar.a(aVar);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.e(a, "MediaViewVideo is null; unable to find it.");
        }
    }

    private void g() {
        float f2 = x.b;
        int i2 = (int) (2.0f * f2);
        int i3 = (int) (f2 * 25.0f);
        h hVar = new h(this.i);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        hVar.setPadding(i2, i3, i3, i2);
        hVar.setLayoutParams(layoutParams);
        int i4 = 0;
        while (true) {
            if (i4 >= ((ViewGroup) this.e).getChildCount()) {
                break;
            }
            View childAt = ((ViewGroup) this.e).getChildAt(0);
            if (childAt instanceof p) {
                this.g = (p) childAt;
                break;
            }
            i4++;
        }
        p pVar = this.g;
        if (pVar != null) {
            pVar.a((b) this.b);
            this.g.a((b) hVar);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.e(a, "Unable to find MediaViewVideo child.");
        }
        this.c.a(0);
        this.c.b((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    static /* synthetic */ boolean g(c cVar) {
        p pVar = cVar.g;
        return (pVar == null || pVar.getState() == d.PLAYBACK_COMPLETED || cVar.o != l.ON) ? false : true;
    }

    private void h() {
        if (this.e.getVisibility() != 0 || !this.j || !this.e.hasWindowFocus()) {
            p pVar = this.g;
            if (pVar != null && pVar.getState() == d.PAUSED) {
                this.l = true;
            }
            this.c.c();
            return;
        }
        this.c.a();
    }

    public void a() {
        this.o = l.DEFAULT;
        p pVar = this.g;
        if (pVar != null) {
            ((com.facebook.ads.internal.view.i.d) pVar.getVideoView()).setViewImplInflationListener(null);
        }
    }

    public void a(e eVar) {
        a(eVar, (a) null);
    }

    public void a(e eVar, @Nullable a aVar) {
        this.k = false;
        this.l = false;
        this.h = aVar;
        p pVar = this.g;
        if (pVar != null) {
            ((com.facebook.ads.internal.view.i.d) pVar.getVideoView()).setViewImplInflationListener(this.f);
        }
        this.b.a((eVar == null || eVar.j() == null) ? null : eVar.j().a(), new e() {
            public void a(boolean z) {
                c.this.m.set(z);
                if (c.this.n.get() && c.this.h != null) {
                    c.this.h.a(z);
                }
            }
        });
        this.o = eVar.t();
        this.c.a();
    }

    public void b() {
        p pVar = this.g;
        if (pVar != null) {
            pVar.getVideoView().setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (c.this.g != null && motionEvent.getAction() == 1) {
                        c.this.g.a();
                    }
                    return true;
                }
            });
        }
    }

    public void c() {
        this.j = true;
        h();
    }

    public void d() {
        this.j = false;
        h();
    }

    public void e() {
        h();
    }

    public void f() {
        h();
    }
}

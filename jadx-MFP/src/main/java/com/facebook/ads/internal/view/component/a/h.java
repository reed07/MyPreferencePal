package com.facebook.ads.internal.view.component.a;

import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.facebook.ads.internal.a.i;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.b.f;
import com.facebook.ads.internal.view.component.a.C0016a;
import com.facebook.ads.internal.view.i.b;
import com.facebook.ads.internal.view.u;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.x.a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

class h extends l {
    private static final int a = ((int) (x.b * 4.0f));
    /* access modifiers changed from: private */
    public final o b;
    @Nullable
    private final m c;
    private final FrameLayout d;
    /* access modifiers changed from: private */
    public final c e;
    /* access modifiers changed from: private */
    public final String f;
    /* access modifiers changed from: private */
    public final a g;
    /* access modifiers changed from: private */
    public final w h;
    private final String i;
    private final f j;
    private final com.facebook.ads.internal.view.i.c.f k;
    private final int l;
    /* access modifiers changed from: private */
    public boolean m = true;
    private boolean n;
    /* access modifiers changed from: private */
    public WeakReference<u> o;

    public h(e eVar, com.facebook.ads.internal.adapters.b.h hVar, int i2, int i3, i iVar, boolean z) {
        final i iVar2 = iVar;
        super(eVar.a());
        boolean z2 = true;
        this.e = eVar.b();
        this.k = (com.facebook.ads.internal.view.i.c.f) eVar.i();
        this.g = eVar.e();
        this.f = eVar.g().c();
        this.h = eVar.f();
        this.i = eVar.g().a().d();
        this.l = eVar.k();
        x.a((View) this, 0);
        LinearLayout linearLayout = new LinearLayout(eVar.a());
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        addView(linearLayout, layoutParams);
        com.facebook.ads.internal.view.i iVar3 = new com.facebook.ads.internal.view.i(eVar.a(), eVar.c(), com.facebook.ads.internal.view.i.a.CROSS);
        iVar3.setCloseButtonStyle(com.facebook.ads.internal.view.i.a.DOWN_ARROW);
        iVar3.a(eVar.g().a(), eVar.g().c(), 0);
        iVar3.a(hVar, true);
        iVar3.b(false);
        iVar3.setToolbarListener(new b() {
            public void a() {
                if (h.this.o.get() != null) {
                    ((u) h.this.o.get()).a();
                    if (h.this.b.getVisibility() != 0) {
                        HashMap hashMap = new HashMap();
                        h.this.g.a((Map<String, String>) hashMap);
                        hashMap.put("touch", k.a(h.this.h.e()));
                        h.this.e.r(h.this.f, hashMap);
                    }
                }
            }
        });
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        this.d = new FrameLayout(eVar.a());
        linearLayout.addView(this.d, layoutParams2);
        x.a((View) this.d, -433903825);
        LayoutParams layoutParams3 = new LayoutParams(-1, -2);
        if (eVar.k() == 1) {
            this.d.addView(iVar3, layoutParams3);
            this.d.setVisibility(4);
        }
        final FrameLayout frameLayout = new FrameLayout(eVar.a());
        LayoutParams layoutParams4 = new LayoutParams(-1, a);
        final com.facebook.ads.internal.view.b.b bVar = new com.facebook.ads.internal.view.b.b(eVar.a(), null, 16842872);
        frameLayout.addView(bVar, layoutParams2);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, -1);
        this.j = new f(eVar.a(), new f.a() {
            public void a(int i) {
                if (h.this.m) {
                    bVar.setProgress(i);
                }
            }

            public void a(String str) {
                h.this.m = true;
                frameLayout.setVisibility(0);
            }

            public void b(String str) {
            }

            public void c(String str) {
                bVar.setProgress(100);
                h.this.m = false;
                frameLayout.setVisibility(8);
            }
        });
        this.j.addView(frameLayout, layoutParams4);
        linearLayout.addView(this.j, layoutParams5);
        layoutParams.gravity = 17;
        o oVar = new o(eVar, hVar, i2, i3, new C0016a() {
            public void a() {
                h.this.a(iVar2);
            }
        });
        this.b = oVar;
        this.c = this.b.a.getSwipeUpCtaButton();
        LayoutParams layoutParams6 = new LayoutParams(-1, -1);
        if (eVar.k() == 1) {
            z2 = false;
        }
        layoutParams6.setMargins(0, z2 ? 0 : com.facebook.ads.internal.view.i.a, 0, 0);
        addView(this.b, layoutParams6);
        if (z) {
            a(iVar2);
        }
    }

    /* access modifiers changed from: private */
    public void a(i iVar) {
        if (this.b.getVisibility() == 0) {
            HashMap hashMap = new HashMap();
            this.g.a((Map<String, String>) hashMap);
            hashMap.put("touch", k.a(this.h.e()));
            iVar.a(hashMap);
            this.b.setVisibility(4);
            this.j.loadUrl(iVar.c().toString());
            iVar.a();
        }
    }

    private boolean a(MotionEvent motionEvent, @Nullable View view) {
        boolean z = false;
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        boolean z2 = motionEvent.getX() >= ((float) iArr[0]) && motionEvent.getX() <= ((float) (iArr[0] + view.getWidth()));
        boolean z3 = motionEvent.getY() >= ((float) iArr[1]) && motionEvent.getY() <= ((float) (iArr[1] + view.getHeight()));
        boolean z4 = this.b.getVisibility() == 0;
        if (z2 && z3 && z4) {
            z = true;
        }
        return z;
    }

    public void a() {
        if (this.b.getVisibility() == 0) {
            m mVar = this.c;
            if (mVar != null) {
                mVar.performClick();
            }
            HashMap hashMap = new HashMap();
            this.g.a((Map<String, String>) hashMap);
            hashMap.put("touch", k.a(this.h.e()));
            this.e.q(this.f, hashMap);
        }
        this.d.setVisibility(0);
        ((u) getParent()).b();
    }

    public void a(MotionEvent motionEvent) {
        boolean a2 = a(motionEvent, (View) this.k);
        if (!a2) {
            this.h.a(motionEvent, this, this);
        }
        if (motionEvent.getAction() == 0) {
            this.n = a(motionEvent, (View) this.c);
        } else if (motionEvent.getAction() == 1) {
            if (a2) {
                this.k.performClick();
                return;
            }
            m mVar = this.c;
            if (mVar == null || !a(motionEvent, (View) mVar) || !this.n) {
                HashMap hashMap = new HashMap();
                this.g.a((Map<String, String>) hashMap);
                hashMap.put("touch", k.a(this.h.e()));
                this.e.d(this.f, hashMap);
            } else {
                this.c.performClick();
            }
        }
    }

    public void a(l lVar) {
        this.b.a(lVar.a().b(), lVar.a().c(), this.i, false, false);
    }

    public void b() {
        this.d.setVisibility(4);
    }

    public boolean c() {
        if (this.l != 1 || this.o.get() == null) {
            if (this.l != 2 || !this.j.canGoBack()) {
                return false;
            }
            this.j.goBack();
            return true;
        } else if (((u) this.o.get()).c()) {
            return false;
        } else {
            if (this.j.canGoBack()) {
                this.j.goBack();
            } else if (this.o.get() != null) {
                ((u) this.o.get()).a();
            }
            return true;
        }
    }

    public boolean d() {
        return this.b.getVisibility() != 0;
    }

    public void e() {
        this.j.onPause();
    }

    public void f() {
        this.j.onResume();
    }

    public void g() {
        this.j.destroy();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent() instanceof u) {
            this.o = new WeakReference<>((u) getParent());
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}

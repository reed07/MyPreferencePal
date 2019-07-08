package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: IMASDK */
public final class ac implements r {
    private static ac a = new ac();
    private static Handler b = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public static Handler c = null;
    /* access modifiers changed from: private */
    public static final Runnable j = new ae();
    /* access modifiers changed from: private */
    public static final Runnable k = new af();
    private List<ag> d = new ArrayList();
    private int e;
    private s f = new s();
    private ab g = new ab();
    /* access modifiers changed from: private */
    public al h = new al(new ak());
    private double i;

    ac() {
    }

    public static ac a() {
        return a;
    }

    public final void b() {
        if (c == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            c = handler;
            handler.post(j);
            c.postDelayed(k, 200);
        }
    }

    public final void c() {
        Handler handler = c;
        if (handler != null) {
            handler.removeCallbacks(k);
            c = null;
        }
        this.d.clear();
        b.post(new ad(this));
    }

    public final void d() {
        Handler handler = c;
        if (handler != null) {
            handler.removeCallbacks(k);
            c = null;
        }
    }

    /* access modifiers changed from: private */
    public final void h() {
        this.e = 0;
        this.i = ho.f();
        this.g.c();
        double f2 = ho.f();
        q a2 = this.f.a();
        if (this.g.b().size() > 0) {
            this.h.b(a2.a(null), this.g.b(), f2);
        }
        if (this.g.a().size() > 0) {
            JSONObject a3 = a2.a(null);
            a(null, a2, a3, ah.PARENT_VIEW);
            z.a(a3);
            this.h.a(a3, this.g.a(), f2);
        } else {
            this.h.b();
        }
        this.g.d();
        ho.f();
        double d2 = this.i;
        if (this.d.size() > 0) {
            for (ag a4 : this.d) {
                a4.a();
            }
        }
    }

    public final void a(View view, q qVar, JSONObject jSONObject) {
        boolean z;
        if (ho.d(view)) {
            ah c2 = this.g.c(view);
            if (c2 != ah.UNDERLYING_VIEW) {
                JSONObject a2 = qVar.a(view);
                z.a(jSONObject, a2);
                String a3 = this.g.a(view);
                if (a3 != null) {
                    z.a(a2, a3);
                    this.g.e();
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    ArrayList b2 = this.g.b(view);
                    if (b2 != null) {
                        z.a(a2, (List<String>) b2);
                    }
                    a(view, qVar, a2, c2);
                }
                this.e++;
            }
        }
    }

    private final void a(View view, q qVar, JSONObject jSONObject, ah ahVar) {
        qVar.a(view, jSONObject, this, ahVar == ah.PARENT_VIEW);
    }
}

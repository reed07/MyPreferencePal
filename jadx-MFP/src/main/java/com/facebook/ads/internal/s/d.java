package com.facebook.ads.internal.s;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.s.a.C0008a;
import com.facebook.ads.internal.w.b.o;
import com.facebook.ads.internal.w.e.e;
import java.util.Map;

@UiThread
public class d implements c {
    private static final String a = "d";
    private static double b = 0.0d;
    private static String c = null;
    private static volatile boolean d = false;
    @Nullable
    @SuppressLint({"StaticFieldLeak"})
    private static c i;
    /* access modifiers changed from: private */
    public final b e;
    private final com.facebook.ads.internal.j.d f;
    /* access modifiers changed from: private */
    public final Context g;
    @Nullable
    private e h;

    private d(Context context) {
        this.g = context.getApplicationContext();
        this.f = new com.facebook.ads.internal.j.d(context);
        this.e = new b(context, new i(context, this.f));
        this.e.b();
        b(context);
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (d.class) {
            if (i == null) {
                i = new d(context.getApplicationContext());
            }
            cVar = i;
        }
        return cVar;
    }

    private void a(final a aVar) {
        if (!aVar.g()) {
            String str = a;
            StringBuilder sb = new StringBuilder();
            sb.append("Attempting to log an invalid ");
            sb.append(aVar.i());
            sb.append(" event.");
            Log.e(str, sb.toString());
            return;
        }
        e eVar = this.h;
        if (eVar != null) {
            eVar.a(aVar);
        }
        this.f.a(aVar.a(), aVar.h().c, aVar.i().toString(), aVar.b(), aVar.c(), aVar.d(), aVar.e(), new a<String>() {
            public void a(int i, String str) {
                super.a(i, str);
            }

            public void a(String str) {
                super.a(str);
                if (com.facebook.ads.internal.r.a.z(d.this.g)) {
                    com.facebook.ads.internal.p.a.a(d.this.g, aVar.i().toString(), str);
                }
                if (aVar.f()) {
                    d.this.e.a();
                } else {
                    d.this.e.b();
                }
            }
        });
    }

    private static synchronized void b(Context context) {
        synchronized (d.class) {
            if (!d) {
                com.facebook.ads.internal.n.a.b(context);
                o.a();
                b = o.b();
                c = o.c();
                d = true;
            }
        }
    }

    public void a(String str) {
        new e(this.g).execute(new String[]{str});
    }

    public void a(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.IMMEDIATE).a(g.IMPRESSION).a(true).a());
        }
    }

    public void a(String str, Map<String, String> map, String str2, f fVar) {
        a(new C0008a().a(str).a(b).b(c).a(map).a(fVar).a(g.a(str2)).a(true).a());
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(f.DEFERRED).a(g.SHOW_AD_CALLED).a(true).a());
        }
    }

    public void b(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.IMMEDIATE).a(g.INVALIDATION).a(false).a());
        }
    }

    public void c(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.IMMEDIATE).a(g.OPEN_LINK).a(true).a());
        }
    }

    public void d(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.DEFERRED).a(g.OFF_TARGET_CLICK).a(true).a());
        }
    }

    public void e(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.IMMEDIATE).a(g.VIDEO).a(true).a());
        }
    }

    public void f(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.DEFERRED).a(g.NATIVE_VIEW).a(false).a());
        }
    }

    public void g(String str, Map<String, String> map) {
        a(new C0008a().a(str).a(b).b(c).a(map).a(f.DEFERRED).a(g.BROWSER_SESSION).a(false).a());
    }

    public void h(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.IMMEDIATE).a(g.STORE).a(true).a());
        }
    }

    public void i(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.DEFERRED).a(g.CLICK_GUARD).a(true).a());
        }
    }

    public void j(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.DEFERRED).a(g.TWO_STEP).a(true).a());
        }
    }

    public void k(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.DEFERRED).a(g.TWO_STEP_CANCEL).a(true).a());
        }
    }

    public void l(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.DEFERRED).a(g.CLOSE).a(true).a());
        }
    }

    public void m(String str, Map<String, String> map) {
        a(new C0008a().a(str).a(b).b(c).a(map).a(f.IMMEDIATE).a(g.USER_RETURN).a(true).a());
    }

    public void n(String str, Map<String, String> map) {
        a(new C0008a().a(str).a(b).b(c).a(map).a(f.DEFERRED).a(g.AD_REPORTING).a(false).a());
    }

    public void o(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.DEFERRED).a(g.PREVIEW_IMPRESSION).a(true).a());
        }
    }

    public void p(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.DEFERRED).a(g.AD_SELECTION).a(true).a());
        }
    }

    public void q(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.IMMEDIATE).a(g.SWIPE_TO_CLICK).a(true).a());
        }
    }

    public void r(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new C0008a().a(str).a(b).b(c).a(map).a(f.IMMEDIATE).a(g.WATCH_AND_X_MINIMIZED).a(true).a());
        }
    }
}

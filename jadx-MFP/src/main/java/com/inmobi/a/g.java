package com.inmobi.a;

import android.content.Context;
import com.inmobi.a.p.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CarbWorker */
public class g {
    private static final String a = "g";
    private a b;
    /* access modifiers changed from: private */
    public boolean c = false;
    /* access modifiers changed from: private */
    public a d = new a();
    private e e = new e();

    g() {
    }

    public final synchronized void a(a aVar) {
        this.b = aVar;
        if (!this.c) {
            long b2 = this.d.a.b("carb_last_update_ts", 0);
            boolean z = b2 == 0 ? true : System.currentTimeMillis() - b2 >= ((long) (this.b.d * 1000));
            if (z) {
                this.c = true;
                new Thread(new Runnable() {
                    public final void run() {
                        c a2 = g.a(g.this);
                        if (!a2.a) {
                            g.this.d.a.a("carb_last_update_ts", System.currentTimeMillis());
                            if (!(a2.d == 0)) {
                                g.a(g.this, a2, g.a((List) a2.b));
                            }
                        }
                        g.this.c = false;
                    }
                }).start();
            }
        }
    }

    private static boolean a(String str) {
        Context b2 = com.inmobi.commons.a.a.b();
        boolean z = false;
        if (b2 == null) {
            return false;
        }
        try {
            b2.getPackageManager().getPackageInfo(str, 256);
            z = true;
        } catch (Exception unused) {
        }
        return z;
    }

    static /* synthetic */ c a(g gVar) {
        b bVar = new b(gVar.b.b, gVar.b.e, gVar.b.f, o.a().d());
        bVar.v = gVar.b.h;
        bVar.r = gVar.b.g * 1000;
        bVar.s = gVar.b.g * 1000;
        return e.a(bVar);
    }

    static /* synthetic */ List a(List list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!a(((d) list.get(i)).a)) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    static /* synthetic */ void a(g gVar, c cVar, List list) {
        f fVar = new f(gVar.b.c, gVar.b.e, gVar.b.f, o.a().d(), list, cVar);
        fVar.r = gVar.b.g * 1000;
        fVar.s = gVar.b.g * 1000;
        e.a(fVar);
    }
}

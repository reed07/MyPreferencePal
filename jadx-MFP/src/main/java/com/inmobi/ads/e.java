package com.inmobi.ads;

import android.os.SystemClock;
import com.inmobi.a.n;
import com.inmobi.commons.core.network.a.C0047a;
import com.inmobi.commons.core.network.d;

/* compiled from: AdNetworkClient */
final class e implements C0047a {
    private static final String a = "e";
    private f b;
    private a c;
    private long d = 0;

    /* compiled from: AdNetworkClient */
    public interface a {
        void a(g gVar);

        void b(g gVar);
    }

    public e(f fVar, a aVar) {
        this.b = fVar;
        this.c = aVar;
    }

    public final void a() {
        this.d = SystemClock.elapsedRealtime();
        new com.inmobi.commons.core.network.a(this.b, this).a();
    }

    public final void a(d dVar) {
        g gVar = new g(this.b, dVar);
        try {
            n.a().a(this.b.g());
            n.a().b(dVar.c());
            n.a().c(SystemClock.elapsedRealtime() - this.d);
            this.c.a(gVar);
        } catch (Exception e) {
            new StringBuilder("Handling ad fetch success encountered an unexpected error: ").append(e.getMessage());
        }
    }

    public final void b(d dVar) {
        g gVar = new g(this.b, dVar);
        new StringBuilder("Ad fetch failed:").append(gVar.a.b.b);
        try {
            n.a().a(this.b.g());
            n.a().b(dVar.c());
            this.c.b(gVar);
        } catch (Exception e) {
            new StringBuilder("Handling ad fetch failed encountered an unexpected error: ").append(e.getMessage());
        }
    }
}

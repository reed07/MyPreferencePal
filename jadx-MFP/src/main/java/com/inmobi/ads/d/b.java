package com.inmobi.ads.d;

import com.inmobi.b.a;
import com.inmobi.commons.core.f.b;
import com.inmobi.commons.core.utilities.b.e;

/* compiled from: TRCCollectorImpl */
public final class b implements a {
    private final a a;

    public b(a aVar) {
        this.a = aVar;
    }

    public final void a(com.inmobi.commons.core.f.b bVar) {
        a aVar = this.a;
        if (aVar.c.b(bVar.j).h) {
            aVar.a.execute(new Runnable(bVar) {
                final /* synthetic */ b a;

                {
                    this.a = r2;
                }

                public final void run() {
                    e.c();
                    if (e.d() == 0) {
                        a.e;
                        return;
                    }
                    a.a(a.this, this.a);
                    a.a(a.this, this.a.j);
                }
            });
        }
    }
}

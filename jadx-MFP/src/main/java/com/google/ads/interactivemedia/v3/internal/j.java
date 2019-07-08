package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* compiled from: IMASDK */
public final class j {
    private static j a = new j();
    private final ArrayList<e> b = new ArrayList<>();
    private final ArrayList<e> c = new ArrayList<>();

    private j() {
    }

    public static j a() {
        return a;
    }

    public final void a(e eVar) {
        this.b.add(eVar);
    }

    public final void b(e eVar) {
        boolean d = d();
        this.c.add(eVar);
        if (!d) {
            g.a().b();
        }
    }

    public final void c(e eVar) {
        boolean d = d();
        this.b.remove(eVar);
        this.c.remove(eVar);
        if (d && !d()) {
            g.a().c();
        }
    }

    public final Collection<e> b() {
        return Collections.unmodifiableCollection(this.b);
    }

    public final Collection<e> c() {
        return Collections.unmodifiableCollection(this.c);
    }

    private final boolean d() {
        return this.c.size() > 0;
    }
}

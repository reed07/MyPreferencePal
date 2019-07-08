package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: IMASDK */
public final class uf<T> {
    private final CopyOnWriteArrayList<uh<T>> a = new CopyOnWriteArrayList<>();

    public final void a(Handler handler, T t) {
        qi.b((handler == null || t == null) ? false : true);
        a(t);
        this.a.add(new uh(handler, t));
    }

    public final void a(T t) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            uh uhVar = (uh) it.next();
            if (uhVar.b == t) {
                uhVar.a();
                this.a.remove(uhVar);
            }
        }
    }

    public final void a(ug<T> ugVar) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((uh) it.next()).a(ugVar);
        }
    }
}

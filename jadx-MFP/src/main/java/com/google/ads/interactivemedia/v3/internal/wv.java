package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public final class wv {
    private yj a = yj.a;
    private xi b = xi.DEFAULT;
    private wn c = wg.IDENTITY;
    private final Map<Type, ww<?>> d = new HashMap();
    private final List<xl> e = new ArrayList();
    private final List<xl> f = new ArrayList();
    private boolean g = false;
    private int h = 2;
    private int i = 2;
    private boolean j = false;
    private boolean k = false;
    private boolean l = true;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;

    public final wv a(we weVar) {
        this.a = this.a.a(weVar, true, false);
        return this;
    }

    public final wv a(Type type, Object obj) {
        boolean z = obj instanceof xg;
        tt.a(z || (obj instanceof wy) || (obj instanceof ww) || (obj instanceof xj));
        if (obj instanceof ww) {
            this.d.put(type, (ww) obj);
        }
        if (z || (obj instanceof wy)) {
            this.e.add(zy.a(abt.a(type), obj));
        }
        if (obj instanceof xj) {
            this.e.add(aac.a(abt.a(type), (xj) obj));
        }
        return this;
    }

    public final wv a(xl xlVar) {
        this.e.add(xlVar);
        return this;
    }

    public final wo a() {
        ArrayList arrayList = new ArrayList(this.e.size() + this.f.size() + 3);
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(this.f);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        int i2 = this.h;
        int i3 = this.i;
        if (!(i2 == 2 || i3 == 2)) {
            wd wdVar = new wd(Date.class, i2, i3);
            wd wdVar2 = new wd(Timestamp.class, i2, i3);
            wd wdVar3 = new wd(java.sql.Date.class, i2, i3);
            arrayList.add(aac.a(Date.class, (xj<TT>) wdVar));
            arrayList.add(aac.a(Timestamp.class, (xj<TT>) wdVar2));
            arrayList.add(aac.a(java.sql.Date.class, (xj<TT>) wdVar3));
        }
        wo woVar = new wo(this.a, this.c, this.d, false, false, false, this.l, false, false, false, this.b, null, this.h, this.i, this.e, this.f, arrayList);
        return woVar;
    }
}

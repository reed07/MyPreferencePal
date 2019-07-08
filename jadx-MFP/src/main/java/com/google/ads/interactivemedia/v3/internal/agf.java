package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.Map.Entry;

/* compiled from: IMASDK */
final class agf implements Serializable {
    private static final long serialVersionUID = 0;
    private final Object[] a;
    private final Object[] b;

    agf(age<?, ?> age) {
        this.a = new Object[age.size()];
        this.b = new Object[age.size()];
        agt a2 = age.entrySet().iterator();
        int i = 0;
        while (a2.hasNext()) {
            Entry entry = (Entry) a2.next();
            this.a[i] = entry.getKey();
            this.b[i] = entry.getValue();
            i++;
        }
    }

    /* access modifiers changed from: 0000 */
    public final Object readResolve() {
        agj agj = new agj(this.a.length);
        int i = 0;
        while (true) {
            Object[] objArr = this.a;
            if (i >= objArr.length) {
                return agj.a();
            }
            agj.b(objArr[i], this.b[i]);
            i++;
        }
    }
}

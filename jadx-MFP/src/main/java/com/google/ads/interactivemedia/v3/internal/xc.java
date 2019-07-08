package com.google.ads.interactivemedia.v3.internal;

import java.util.Map.Entry;
import java.util.Set;

/* compiled from: IMASDK */
public final class xc extends wz {
    private final yo<String, wz> a = new yo<>();

    public final void a(String str, wz wzVar) {
        if (wzVar == null) {
            wzVar = xb.a;
        }
        this.a.put(str, wzVar);
    }

    public final Set<Entry<String, wz>> h() {
        return this.a.entrySet();
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof xc) && ((xc) obj).a.equals(this.a));
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}

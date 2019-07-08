package com.google.ads.interactivemedia.v3.internal;

import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
final class jj implements ji {
    jj() {
    }

    public final List<jf> a(String str, boolean z) throws jm {
        List a = jl.a(str, z);
        if (a.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.singletonList((jf) a.get(0));
    }

    public final jf a() throws jm {
        return jl.a();
    }
}

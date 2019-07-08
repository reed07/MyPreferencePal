package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.abu;
import com.google.ads.interactivemedia.v3.internal.abw;
import com.google.ads.interactivemedia.v3.internal.abx;
import com.google.ads.interactivemedia.v3.internal.xj;
import java.io.IOException;

/* compiled from: IMASDK */
final class ag extends xj<af> {
    ag() {
    }

    public final void write(abx abx, af afVar) throws IOException {
        if (afVar == null) {
            abx.f();
        } else {
            abx.b(afVar.getName());
        }
    }

    public final af read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return new af(abu.h());
        }
        abu.j();
        return null;
    }
}

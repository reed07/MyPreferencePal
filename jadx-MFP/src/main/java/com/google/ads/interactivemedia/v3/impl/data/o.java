package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.agb;
import java.util.Collection;
import java.util.List;

/* compiled from: IMASDK */
final class o extends ac {
    private agb<ad> obstructions;

    o() {
    }

    public final ac obstructions(List<ad> list) {
        this.obstructions = agb.a((Collection<? extends E>) list);
        return this;
    }

    public final ab build() {
        String str = "";
        if (this.obstructions == null) {
            str = String.valueOf(str).concat(" obstructions");
        }
        if (str.isEmpty()) {
            return new n(this.obstructions, null);
        }
        String str2 = "Missing required properties:";
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}

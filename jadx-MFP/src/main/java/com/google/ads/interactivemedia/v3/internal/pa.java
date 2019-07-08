package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.util.List;

/* compiled from: IMASDK */
public final class pa extends oy {
    private final String e;
    private final ox f;
    private final ph g;

    public pa(long j, bs bsVar, String str, pg pgVar, List<ou> list, String str2, long j2) {
        ox oxVar;
        pg pgVar2 = pgVar;
        super(j, bsVar, str, pgVar, list, 0);
        Uri.parse(str);
        ph phVar = null;
        if (pgVar2.e <= 0) {
            oxVar = null;
        } else {
            oxVar = new ox(null, pgVar2.d, pgVar2.e);
        }
        this.f = oxVar;
        this.e = str2;
        if (this.f == null) {
            ox oxVar2 = new ox(null, 0, -1);
            phVar = new ph(oxVar2);
        }
        this.g = phVar;
    }

    public final ox d() {
        return this.f;
    }

    public final ok e() {
        return this.g;
    }

    public final String f() {
        return this.e;
    }
}

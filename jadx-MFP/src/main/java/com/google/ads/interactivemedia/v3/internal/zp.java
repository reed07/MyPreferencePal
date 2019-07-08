package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.ArrayList;

/* compiled from: IMASDK */
public final class zp extends xj<Object> {
    public static final xl a = new zq();
    private final wo b;

    zp(wo woVar) {
        this.b = woVar;
    }

    public final Object read(abu abu) throws IOException {
        int ordinal = abu.f().ordinal();
        if (ordinal == 0) {
            ArrayList arrayList = new ArrayList();
            abu.a();
            while (abu.e()) {
                arrayList.add(read(abu));
            }
            abu.b();
            return arrayList;
        } else if (ordinal != 2) {
            switch (ordinal) {
                case 5:
                    return abu.h();
                case 6:
                    return Double.valueOf(abu.k());
                case 7:
                    return Boolean.valueOf(abu.i());
                case 8:
                    abu.j();
                    return null;
                default:
                    throw new IllegalStateException();
            }
        } else {
            yo yoVar = new yo();
            abu.c();
            while (abu.e()) {
                yoVar.put(abu.g(), read(abu));
            }
            abu.d();
            return yoVar;
        }
    }

    public final void write(abx abx, Object obj) throws IOException {
        if (obj == null) {
            abx.f();
            return;
        }
        xj a2 = this.b.a(obj.getClass());
        if (a2 instanceof zp) {
            abx.d();
            abx.e();
            return;
        }
        a2.write(abx, obj);
    }
}

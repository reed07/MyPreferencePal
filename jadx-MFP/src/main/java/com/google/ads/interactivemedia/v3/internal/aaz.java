package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: IMASDK */
final class aaz extends xj<wz> {
    aaz() {
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final wz read(abu abu) throws IOException {
        int ordinal = abu.f().ordinal();
        if (ordinal == 0) {
            wx wxVar = new wx();
            abu.a();
            while (abu.e()) {
                wxVar.a(read(abu));
            }
            abu.b();
            return wxVar;
        } else if (ordinal != 2) {
            switch (ordinal) {
                case 5:
                    return new xe(abu.h());
                case 6:
                    return new xe((Number) new yn(abu.h()));
                case 7:
                    return new xe(Boolean.valueOf(abu.i()));
                case 8:
                    abu.j();
                    return xb.a;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            xc xcVar = new xc();
            abu.c();
            while (abu.e()) {
                xcVar.a(abu.g(), read(abu));
            }
            abu.d();
            return xcVar;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final void write(abx abx, wz wzVar) throws IOException {
        if (wzVar == null || (wzVar instanceof xb)) {
            abx.f();
        } else if (wzVar instanceof xe) {
            xe g = wzVar.g();
            if (g.i()) {
                abx.a(g.a());
            } else if (g.h()) {
                abx.a(g.f());
            } else {
                abx.b(g.b());
            }
        } else {
            boolean z = wzVar instanceof wx;
            if (z) {
                abx.b();
                if (z) {
                    Iterator it = ((wx) wzVar).iterator();
                    while (it.hasNext()) {
                        write(abx, (wz) it.next());
                    }
                    abx.c();
                    return;
                }
                StringBuilder sb = new StringBuilder("Not a JSON Array: ");
                sb.append(wzVar);
                throw new IllegalStateException(sb.toString());
            }
            boolean z2 = wzVar instanceof xc;
            if (z2) {
                abx.d();
                if (z2) {
                    for (Entry entry : ((xc) wzVar).h()) {
                        abx.a((String) entry.getKey());
                        write(abx, (wz) entry.getValue());
                    }
                    abx.e();
                    return;
                }
                StringBuilder sb2 = new StringBuilder("Not a JSON Object: ");
                sb2.append(wzVar);
                throw new IllegalStateException(sb2.toString());
            }
            StringBuilder sb3 = new StringBuilder("Couldn't write ");
            sb3.append(wzVar.getClass());
            throw new IllegalArgumentException(sb3.toString());
        }
    }
}

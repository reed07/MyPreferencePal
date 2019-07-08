package com.inmobi.a;

import com.inmobi.commons.core.utilities.b.b;
import com.inmobi.commons.core.utilities.b.h;

/* compiled from: SessionManager */
public class n {
    private static final String g = "n";
    private static n h;
    private static final Object i = new Object();
    long a = 0;
    long b = 0;
    long c = 0;
    long d = 0;
    long e = 0;
    long f = 0;

    public static n a() {
        n nVar = h;
        if (nVar == null) {
            synchronized (i) {
                nVar = h;
                if (nVar == null) {
                    nVar = new n();
                    h = nVar;
                }
            }
        }
        return nVar;
    }

    private n() {
    }

    static h b() {
        if (!o.a().a.a.b()) {
            return null;
        }
        return h.a();
    }

    public final void a(long j) {
        switch (b.a()) {
            case 0:
                this.c += j;
                return;
            case 1:
                this.a += j;
                break;
        }
    }

    public final void b(long j) {
        switch (b.a()) {
            case 0:
                this.d += j;
                return;
            case 1:
                this.b += j;
                break;
        }
    }

    public final void c(long j) {
        this.e += j;
    }
}

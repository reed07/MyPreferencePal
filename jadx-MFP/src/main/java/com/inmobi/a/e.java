package com.inmobi.a;

import android.os.SystemClock;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.e.f;
import com.inmobi.commons.core.network.d;

/* compiled from: CarbNetworkClient */
public class e {
    private static final String a = "e";

    static c a(b bVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        d a2 = new com.inmobi.commons.core.network.e(bVar).a();
        c cVar = new c(a2);
        try {
            n.a().a(bVar.g());
            n.a().b(a2.c());
            n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
        } catch (Exception e) {
            new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
        }
        boolean z = cVar.a;
        return cVar;
    }

    static boolean a(f fVar) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i > fVar.a) {
                break;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            d a2 = new com.inmobi.commons.core.network.e(fVar).a();
            try {
                n.a().a(fVar.g());
                n.a().b(a2.c());
                n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
            } catch (Exception e) {
                new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
            }
            if (!a2.a()) {
                z = true;
                break;
            }
            i++;
            if (i > fVar.a) {
                break;
            }
            try {
                Thread.sleep((long) (fVar.b * 1000));
            } catch (InterruptedException unused) {
            }
        }
        if (!z) {
            try {
                b.a().a(new f("signals", "CarbUploadDiscarded"));
            } catch (Exception e2) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e2.getMessage());
                sb.append(")");
            }
        }
        return z;
    }
}

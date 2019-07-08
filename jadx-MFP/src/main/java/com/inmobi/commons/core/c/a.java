package com.inmobi.commons.core.c;

import com.inmobi.commons.core.b.c;
import com.inmobi.commons.core.network.e;
import com.inmobi.commons.core.utilities.uid.d;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: EventSubmitter */
public class a {
    /* access modifiers changed from: private */
    public static final String a = "a";
    private static volatile a b;
    private static ScheduledExecutorService c;
    private static final Object d = new Object();

    public static a a() {
        a aVar = b;
        if (aVar == null) {
            synchronized (d) {
                aVar = b;
                if (aVar == null) {
                    aVar = new a();
                    b = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
        c = Executors.newSingleThreadScheduledExecutor();
    }

    public final void a(c cVar, String str, int i, int i2, long j, d dVar, b bVar) {
        c cVar2 = cVar;
        int i3 = i;
        int i4 = i2;
        if (!com.inmobi.commons.core.utilities.d.a() || !com.inmobi.commons.a.a.g()) {
            bVar.a(cVar2, false);
            return;
        }
        final com.inmobi.commons.core.network.c cVar3 = new com.inmobi.commons.core.network.c(HttpConstants.METHOD_POST, str, false, dVar);
        HashMap hashMap = new HashMap();
        hashMap.put("payload", cVar2.b);
        cVar3.c(hashMap);
        int i5 = i3 - i4;
        if (i5 > 0) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("X-im-retry-count", String.valueOf(i5));
            cVar3.a((Map<String, String>) hashMap2);
        }
        cVar3.u = false;
        cVar3.A = false;
        long j2 = i4 != i3 ? j : 0;
        ScheduledExecutorService scheduledExecutorService = c;
        final int i6 = i2;
        final c cVar4 = cVar;
        final String str2 = str;
        final int i7 = i;
        final long j3 = j;
        final d dVar2 = dVar;
        final b bVar2 = bVar;
        AnonymousClass1 r0 = new Runnable() {
            public final void run() {
                com.inmobi.commons.core.network.d a2 = new e(cVar3).a();
                if (!a2.a()) {
                    bVar2.a(cVar4);
                } else if (i6 > 1) {
                    a.a;
                    StringBuilder sb = new StringBuilder("Unable to send trc events to server: ");
                    sb.append(a2.b());
                    sb.append(". Will retry");
                    a.this.a(cVar4, str2, i7, i6 - 1, j3, dVar2, bVar2);
                } else {
                    bVar2.a(cVar4, true);
                }
            }
        };
        scheduledExecutorService.schedule(r0, j2, TimeUnit.SECONDS);
    }
}

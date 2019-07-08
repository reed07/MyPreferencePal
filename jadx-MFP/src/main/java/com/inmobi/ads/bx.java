package com.inmobi.ads;

import android.os.SystemClock;
import android.text.TextUtils;
import com.inmobi.a.n;
import com.inmobi.ads.c.C0043c;
import com.inmobi.ads.c.k;
import com.inmobi.ads.cache.d;
import com.inmobi.commons.core.a.a;
import com.inmobi.commons.core.network.NetworkError;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: VastResponse */
public final class bx implements by {
    List<bv> a;
    String b;
    String c;
    List<NativeTracker> d;
    List<bt> e;
    int f;
    private String g;
    private bt h;
    private k i;
    private bv j;

    private static boolean a(double d2, double d3, double d4) {
        return d4 > d2 && d4 <= d3;
    }

    public bx(k kVar) {
        this.j = null;
        this.a = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.i = kVar;
        this.f = 0;
    }

    private bx(List<NativeTracker> list, k kVar) {
        this(kVar);
        if (list.size() != 0) {
            this.d = new ArrayList(list);
        }
    }

    public bx(String str, String str2, String str3, List<NativeTracker> list, List<bt> list2, k kVar) {
        this(list, kVar);
        if (list2.size() != 0) {
            this.e = new ArrayList(list2);
        }
        this.g = str;
        this.a.add(new bv(str));
        this.b = str2;
        this.c = str3;
    }

    public final String a() {
        return this.c;
    }

    public final String b() {
        bv bvVar;
        int i2;
        String str = this.g;
        if (str != null) {
            return str;
        }
        d.a();
        List d2 = d.d();
        bv bvVar2 = null;
        if (!d2.isEmpty()) {
            Iterator it = this.a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                bvVar = (bv) it.next();
                if (d2.contains(bvVar.a)) {
                    break;
                }
            }
        }
        bvVar = null;
        if (bvVar != null) {
            this.j = bvVar;
            this.g = bvVar.a;
            return this.g;
        }
        double d3 = (((double) this.i.b) * 2.0d) / 1048576.0d;
        double d4 = 1.0d;
        double d5 = (((double) this.i.c) * 1.0d) / 1048576.0d;
        for (bv bvVar3 : this.a) {
            String[] split = this.b.split(":");
            try {
                i2 = (Integer.parseInt(split[1]) * 60) + Integer.parseInt(split[2]);
            } catch (ArrayIndexOutOfBoundsException e2) {
                i2 = 0;
                a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
            double d6 = ((((double) bvVar3.b) * d4) * ((double) i2)) / 8192.0d;
            bvVar3.c = d6;
            double d7 = d6;
            bv bvVar4 = bvVar3;
            if (a(0.0d, d3, d7)) {
                bvVar = a(bvVar, bvVar4, d7);
                d4 = 1.0d;
            } else {
                if (a(d3, d5, d7)) {
                    bvVar2 = b(bvVar2, bvVar4, d7);
                }
                d4 = 1.0d;
            }
        }
        a(bvVar, bvVar2);
        if (TextUtils.isEmpty(this.g)) {
            C0043c cVar = this.i.d;
            if (cVar.a || this.a.size() == 0) {
                return this.g;
            }
            CountDownLatch countDownLatch = new CountDownLatch(this.a.size());
            try {
                a(cVar, countDownLatch);
                countDownLatch.await((long) cVar.b, TimeUnit.MILLISECONDS);
                for (bv bvVar5 : this.a) {
                    double d8 = bvVar5.c;
                    double d9 = d8;
                    if (a(0.0d, d3, d8)) {
                        bvVar = a(bvVar, bvVar5, d9);
                    } else {
                        if (a(d3, d5, d9)) {
                            bvVar2 = b(bvVar2, bvVar5, d9);
                        }
                    }
                }
            } catch (Exception e3) {
                new StringBuilder("SDK encountered an unexpected error in getting vast header response; ").append(e3.getMessage());
                a.a().a(new com.inmobi.commons.core.e.a(e3));
                for (bv bvVar6 : this.a) {
                    double d10 = bvVar6.c;
                    double d11 = d10;
                    if (a(0.0d, d3, d10)) {
                        bvVar = a(bvVar, bvVar6, d11);
                    } else {
                        if (a(d3, d5, d11)) {
                            bvVar2 = b(bvVar2, bvVar6, d11);
                        }
                    }
                }
            } catch (Throwable th) {
                for (bv bvVar7 : this.a) {
                    double d12 = bvVar7.c;
                    double d13 = d12;
                    if (a(0.0d, d3, d12)) {
                        bvVar = a(bvVar, bvVar7, d13);
                    } else {
                        if (a(d3, d5, d13)) {
                            bvVar2 = b(bvVar2, bvVar7, d13);
                        }
                    }
                }
                a(bvVar, bvVar2);
                throw th;
            }
            a(bvVar, bvVar2);
        }
        return this.g;
    }

    private void a(C0043c cVar, CountDownLatch countDownLatch) {
        for (bv bwVar : this.a) {
            bw bwVar2 = new bw(bwVar, cVar.b, countDownLatch);
            bwVar2.c = SystemClock.elapsedRealtime();
            bw.d.execute(new Runnable() {
                public final void run() {
                    bw bwVar;
                    try {
                        com.inmobi.commons.core.network.d a2 = new f(bw.this.a).a();
                        if (a2 == null) {
                            return;
                        }
                        if (a2.a()) {
                            bw.this.a(a2);
                            return;
                        }
                        bwVar = bw.this;
                        try {
                            n.a().a(bwVar.a.g());
                            n.a().b(a2.c());
                            n.a().c(SystemClock.elapsedRealtime() - bwVar.c);
                            if (bwVar.b.get() != null) {
                                ((bv) bwVar.b.get()).c = (((double) a2.c) * 1.0d) / 1048576.0d;
                            }
                            bwVar.a();
                        } catch (Exception e) {
                            new StringBuilder("Handling Vast Media Header Request success encountered an unexpected error: ").append(e.getMessage());
                            a.a().a(new com.inmobi.commons.core.e.a(e));
                            bwVar.a();
                        }
                    } catch (Exception e2) {
                        bw.e;
                        new StringBuilder("Network request failed with unexpected error: ").append(e2.getMessage());
                        NetworkError networkError = new NetworkError(ErrorCode.UNKNOWN_ERROR, "Network request failed with unknown error");
                        com.inmobi.commons.core.network.d dVar = new com.inmobi.commons.core.network.d();
                        dVar.b = networkError;
                        bw.this.a(dVar);
                    } catch (Throwable th) {
                        bwVar.a();
                        throw th;
                    }
                }
            });
        }
    }

    private void a(bv bvVar, bv bvVar2) {
        if (bvVar != null) {
            this.j = bvVar;
            this.g = bvVar.a;
            return;
        }
        if (bvVar2 != null) {
            this.j = bvVar2;
            this.g = bvVar2.a;
        }
    }

    public final List<bv> c() {
        return this.a;
    }

    public final List<NativeTracker> d() {
        return this.d;
    }

    public final List<bt> e() {
        return this.e;
    }

    public final void a(bt btVar) {
        this.h = btVar;
    }

    public final bt f() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    public final void a(NativeTracker nativeTracker) {
        this.d.add(nativeTracker);
    }

    private static bv a(bv bvVar, bv bvVar2, double d2) {
        return (bvVar != null && d2 <= bvVar.c) ? bvVar : bvVar2;
    }

    private static bv b(bv bvVar, bv bvVar2, double d2) {
        return (bvVar != null && d2 >= bvVar.c) ? bvVar : bvVar2;
    }
}

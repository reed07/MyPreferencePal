package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;

/* compiled from: IMASDK */
public final class rb {
    public static final int a = vf.h("GA94");

    public static void a(long j, ut utVar, gc[] gcVarArr) {
        while (true) {
            boolean z = true;
            if (utVar.b() > 1) {
                int a2 = a(utVar);
                int a3 = a(utVar);
                int d = utVar.d() + a3;
                if (a3 == -1 || a3 > utVar.b()) {
                    Log.w("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                    d = utVar.c();
                } else if (a2 == 4 && a3 >= 8) {
                    int e = utVar.e();
                    int f = utVar.f();
                    int l = f == 49 ? utVar.l() : 0;
                    int e2 = utVar.e();
                    if (f == 47) {
                        utVar.d(1);
                    }
                    boolean z2 = e == 181 && (f == 49 || f == 47) && e2 == 3;
                    if (f == 49) {
                        if (l != a) {
                            z = false;
                        }
                        z2 &= z;
                    }
                    if (z2) {
                        b(j, utVar, gcVarArr);
                    }
                }
                utVar.c(d);
            } else {
                return;
            }
        }
    }

    public static void b(long j, ut utVar, gc[] gcVarArr) {
        int e = utVar.e();
        if ((e & 64) != 0) {
            int i = e & 31;
            utVar.d(1);
            int i2 = i * 3;
            int d = utVar.d();
            for (gc gcVar : gcVarArr) {
                utVar.c(d);
                gcVar.a(utVar, i2);
                gcVar.a(j, 1, i2, 0, null);
            }
        }
    }

    private static int a(ut utVar) {
        int i = 0;
        while (utVar.b() != 0) {
            int e = utVar.e();
            i += e;
            if (e != 255) {
                return i;
            }
        }
        return -1;
    }
}

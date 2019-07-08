package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.WindowManager;

/* compiled from: IMASDK */
public final class vq {
    private final WindowManager a;
    private final vs b;
    private final vr c;
    private long d;
    private long e;
    private long f;
    private long g;
    private long h;
    private boolean i;
    private long j;
    private long k;
    private long l;

    public vq() {
        this(null);
    }

    public vq(Context context) {
        vr vrVar = null;
        if (context != null) {
            context = context.getApplicationContext();
            this.a = (WindowManager) context.getSystemService("window");
        } else {
            this.a = null;
        }
        if (this.a != null) {
            if (vf.a >= 17) {
                DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
                if (displayManager != null) {
                    vrVar = new vr(this, displayManager);
                }
            }
            this.c = vrVar;
            this.b = vs.a();
        } else {
            this.c = null;
            this.b = null;
        }
        this.d = -9223372036854775807L;
        this.e = -9223372036854775807L;
    }

    public final void a() {
        this.i = false;
        if (this.a != null) {
            this.b.b();
            vr vrVar = this.c;
            if (vrVar != null) {
                vrVar.a();
            }
            c();
        }
    }

    public final void b() {
        if (this.a != null) {
            vr vrVar = this.c;
            if (vrVar != null) {
                vrVar.b();
            }
            this.b.c();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(long r11, long r13) {
        /*
            r10 = this;
            r0 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r11
            boolean r2 = r10.i
            if (r2 == 0) goto L_0x0042
            long r2 = r10.f
            int r4 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0019
            long r2 = r10.l
            r4 = 1
            long r2 = r2 + r4
            r10.l = r2
            long r2 = r10.h
            r10.g = r2
        L_0x0019:
            long r2 = r10.l
            r4 = 6
            r6 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 < 0) goto L_0x003a
            long r4 = r10.k
            long r4 = r0 - r4
            long r4 = r4 / r2
            long r2 = r10.g
            long r2 = r2 + r4
            boolean r4 = r10.b(r2, r13)
            if (r4 == 0) goto L_0x0033
            r10.i = r6
            goto L_0x0042
        L_0x0033:
            long r4 = r10.j
            long r4 = r4 + r2
            long r6 = r10.k
            long r4 = r4 - r6
            goto L_0x0044
        L_0x003a:
            boolean r2 = r10.b(r0, r13)
            if (r2 == 0) goto L_0x0042
            r10.i = r6
        L_0x0042:
            r4 = r13
            r2 = r0
        L_0x0044:
            boolean r6 = r10.i
            if (r6 != 0) goto L_0x0053
            r10.k = r0
            r10.j = r13
            r13 = 0
            r10.l = r13
            r13 = 1
            r10.i = r13
        L_0x0053:
            r10.f = r11
            r10.h = r2
            com.google.ads.interactivemedia.v3.internal.vs r11 = r10.b
            if (r11 == 0) goto L_0x008e
            long r12 = r10.d
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r14 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r14 != 0) goto L_0x0067
            goto L_0x008e
        L_0x0067:
            long r11 = r11.a
            int r13 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r13 != 0) goto L_0x006e
            return r4
        L_0x006e:
            long r13 = r10.d
            long r0 = r4 - r11
            long r0 = r0 / r13
            long r0 = r0 * r13
            long r11 = r11 + r0
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 > 0) goto L_0x007d
            long r13 = r11 - r13
            goto L_0x0081
        L_0x007d:
            long r13 = r13 + r11
            r8 = r11
            r11 = r13
            r13 = r8
        L_0x0081:
            long r0 = r11 - r4
            long r4 = r4 - r13
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0089
            goto L_0x008a
        L_0x0089:
            r11 = r13
        L_0x008a:
            long r13 = r10.e
            long r11 = r11 - r13
            return r11
        L_0x008e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.vq.a(long, long):long");
    }

    /* access modifiers changed from: private */
    public final void c() {
        Display defaultDisplay = this.a.getDefaultDisplay();
        if (defaultDisplay != null) {
            this.d = (long) (1.0E9d / ((double) defaultDisplay.getRefreshRate()));
            this.e = (this.d * 80) / 100;
        }
    }

    private final boolean b(long j2, long j3) {
        return Math.abs((j3 - this.j) - (j2 - this.k)) > 20000000;
    }
}

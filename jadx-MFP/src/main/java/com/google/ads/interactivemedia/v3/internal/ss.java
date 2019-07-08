package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Handler;
import android.util.SparseArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.myfitnesspal.shared.model.v1.Country;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: IMASDK */
public final class ss implements sh, tz {
    public static final Map<String, int[]> a;
    public static final long[] b = {5700000, 3400000, 1900000, 1000000, 400000};
    public static final long[] c = {169000, 129000, 114000, 102000, 87000};
    public static final long[] d = {2100000, 1300000, 950000, 700000, 400000};
    public static final long[] e = {6900000, 4300000, 2700000, 1600000, 450000};
    private final Context f;
    private final SparseArray<Long> g;
    private final uf<si> h;
    private final ux i;
    private final ua j;
    private int k;
    private long l;
    private long m;
    private int n;
    private long o;
    private long p;
    private long q;
    private long r;

    @Deprecated
    public ss() {
        this(null, new SparseArray(), 2000, ua.a, false);
    }

    public final tz b() {
        return this;
    }

    public final void c() {
    }

    private ss(Context context, SparseArray<Long> sparseArray, int i2, ua uaVar, boolean z) {
        Context context2;
        int i3;
        if (context == null) {
            context2 = null;
        } else {
            context2 = context.getApplicationContext();
        }
        this.f = context2;
        this.g = sparseArray;
        this.h = new uf<>();
        this.i = new ux(i2);
        this.j = uaVar;
        if (context == null) {
            i3 = 0;
        } else {
            i3 = vf.a(context);
        }
        this.n = i3;
        this.q = a(this.n);
        if (context != null && z) {
            su.a(context).a(this);
        }
    }

    public final synchronized long a() {
        return this.q;
    }

    public final void a(Handler handler, si siVar) {
        this.h.a(handler, siVar);
    }

    public final void a(si siVar) {
        this.h.a(siVar);
    }

    public final synchronized void a(boolean z) {
        if (z) {
            if (this.k == 0) {
                this.l = this.j.a();
            }
            this.k++;
        }
    }

    public final synchronized void a(boolean z, int i2) {
        if (z) {
            this.m += (long) i2;
        }
    }

    public final synchronized void b(boolean z) {
        if (z) {
            qi.c(this.k > 0);
            long a2 = this.j.a();
            int i2 = (int) (a2 - this.l);
            long j2 = (long) i2;
            this.o += j2;
            this.p += this.m;
            if (i2 > 0) {
                this.i.a((int) Math.sqrt((double) this.m), (float) ((this.m * 8000) / j2));
                if (this.o >= AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS || this.p >= 524288) {
                    this.q = (long) this.i.a(0.5f);
                }
                a(i2, this.m, this.q);
                this.l = a2;
                this.m = 0;
            }
            this.k--;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0052, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void d() {
        /*
            r10 = this;
            monitor-enter(r10)
            android.content.Context r0 = r10.f     // Catch:{ all -> 0x0053 }
            r1 = 0
            if (r0 != 0) goto L_0x0008
            r0 = 0
            goto L_0x000e
        L_0x0008:
            android.content.Context r0 = r10.f     // Catch:{ all -> 0x0053 }
            int r0 = com.google.ads.interactivemedia.v3.internal.vf.a(r0)     // Catch:{ all -> 0x0053 }
        L_0x000e:
            int r2 = r10.n     // Catch:{ all -> 0x0053 }
            if (r2 != r0) goto L_0x0014
            monitor-exit(r10)
            return
        L_0x0014:
            r10.n = r0     // Catch:{ all -> 0x0053 }
            r2 = 1
            if (r0 == r2) goto L_0x0051
            if (r0 == 0) goto L_0x0051
            r2 = 8
            if (r0 != r2) goto L_0x0020
            goto L_0x0051
        L_0x0020:
            long r2 = r10.a(r0)     // Catch:{ all -> 0x0053 }
            r10.q = r2     // Catch:{ all -> 0x0053 }
            com.google.ads.interactivemedia.v3.internal.ua r0 = r10.j     // Catch:{ all -> 0x0053 }
            long r2 = r0.a()     // Catch:{ all -> 0x0053 }
            int r0 = r10.k     // Catch:{ all -> 0x0053 }
            if (r0 <= 0) goto L_0x0037
            long r0 = r10.l     // Catch:{ all -> 0x0053 }
            long r0 = r2 - r0
            int r1 = (int) r0     // Catch:{ all -> 0x0053 }
            r5 = r1
            goto L_0x0038
        L_0x0037:
            r5 = 0
        L_0x0038:
            long r6 = r10.m     // Catch:{ all -> 0x0053 }
            long r8 = r10.q     // Catch:{ all -> 0x0053 }
            r4 = r10
            r4.a(r5, r6, r8)     // Catch:{ all -> 0x0053 }
            r10.l = r2     // Catch:{ all -> 0x0053 }
            r0 = 0
            r10.m = r0     // Catch:{ all -> 0x0053 }
            r10.p = r0     // Catch:{ all -> 0x0053 }
            r10.o = r0     // Catch:{ all -> 0x0053 }
            com.google.ads.interactivemedia.v3.internal.ux r0 = r10.i     // Catch:{ all -> 0x0053 }
            r0.a()     // Catch:{ all -> 0x0053 }
            monitor-exit(r10)
            return
        L_0x0051:
            monitor-exit(r10)
            return
        L_0x0053:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ss.d():void");
    }

    private final void a(int i2, long j2, long j3) {
        if (i2 != 0 || j2 != 0 || j3 != this.r) {
            this.r = j3;
            uf<si> ufVar = this.h;
            tk tkVar = new tk(i2, j2, j3);
            ufVar.a((ug<T>) tkVar);
        }
    }

    private final long a(int i2) {
        Long l2 = (Long) this.g.get(i2);
        if (l2 == null) {
            l2 = (Long) this.g.get(0);
        }
        if (l2 == null) {
            l2 = Long.valueOf(1000000);
        }
        return l2.longValue();
    }

    /* synthetic */ ss(Context context, SparseArray sparseArray, int i2, ua uaVar, boolean z, byte b2) {
        this(context, sparseArray, i2, uaVar, z);
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("AD", new int[]{1, 0, 0, 0});
        hashMap.put("AE", new int[]{1, 3, 4, 4});
        hashMap.put("AF", new int[]{4, 4, 3, 2});
        hashMap.put("AG", new int[]{3, 2, 1, 2});
        hashMap.put("AI", new int[]{1, 0, 0, 2});
        hashMap.put("AL", new int[]{1, 1, 1, 1});
        hashMap.put("AM", new int[]{2, 2, 4, 3});
        hashMap.put("AO", new int[]{2, 4, 2, 0});
        hashMap.put("AR", new int[]{2, 3, 2, 3});
        hashMap.put("AS", new int[]{3, 4, 4, 1});
        hashMap.put("AT", new int[]{0, 1, 0, 0});
        hashMap.put("AU", new int[]{0, 3, 0, 0});
        hashMap.put("AW", new int[]{1, 1, 0, 4});
        hashMap.put("AX", new int[]{0, 1, 0, 0});
        hashMap.put("AZ", new int[]{3, 3, 2, 2});
        hashMap.put("BA", new int[]{1, 1, 1, 2});
        hashMap.put("BB", new int[]{0, 1, 0, 0});
        hashMap.put("BD", new int[]{2, 1, 3, 2});
        hashMap.put("BE", new int[]{0, 0, 0, 0});
        hashMap.put("BF", new int[]{4, 4, 4, 1});
        hashMap.put("BG", new int[]{0, 0, 0, 1});
        hashMap.put("BH", new int[]{2, 1, 3, 4});
        hashMap.put("BI", new int[]{4, 3, 4, 4});
        hashMap.put("BJ", new int[]{4, 3, 4, 3});
        hashMap.put("BL", new int[]{1, 0, 1, 2});
        hashMap.put("BM", new int[]{1, 0, 0, 0});
        hashMap.put("BN", new int[]{4, 3, 3, 3});
        hashMap.put("BO", new int[]{2, 2, 1, 2});
        hashMap.put("BQ", new int[]{1, 1, 2, 4});
        hashMap.put("BR", new int[]{2, 3, 2, 2});
        hashMap.put("BS", new int[]{1, 1, 0, 2});
        hashMap.put("BT", new int[]{3, 0, 2, 1});
        hashMap.put("BW", new int[]{4, 4, 2, 3});
        hashMap.put("BY", new int[]{1, 1, 1, 1});
        hashMap.put("BZ", new int[]{2, 3, 3, 1});
        hashMap.put(Country.CANADA_SHORT, new int[]{0, 2, 2, 3});
        hashMap.put("CD", new int[]{4, 4, 2, 1});
        hashMap.put("CF", new int[]{4, 4, 3, 3});
        hashMap.put("CG", new int[]{4, 4, 4, 4});
        hashMap.put("CH", new int[]{0, 0, 0, 0});
        hashMap.put("CI", new int[]{4, 4, 4, 4});
        hashMap.put("CK", new int[]{2, 4, 2, 0});
        hashMap.put("CL", new int[]{2, 2, 2, 3});
        hashMap.put("CM", new int[]{3, 4, 3, 1});
        hashMap.put(Country.TRADITIONAL_CHINESE_SHORT, new int[]{2, 0, 1, 2});
        hashMap.put("CO", new int[]{2, 3, 2, 1});
        hashMap.put("CR", new int[]{2, 2, 4, 4});
        hashMap.put("CU", new int[]{4, 4, 4, 1});
        hashMap.put("CV", new int[]{2, 2, 2, 4});
        hashMap.put("CW", new int[]{1, 1, 0, 0});
        hashMap.put("CX", new int[]{1, 2, 2, 2});
        hashMap.put("CY", new int[]{1, 1, 0, 0});
        hashMap.put("CZ", new int[]{0, 1, 0, 0});
        hashMap.put("DE", new int[]{0, 2, 2, 2});
        hashMap.put("DJ", new int[]{3, 4, 4, 0});
        hashMap.put("DK", new int[]{0, 0, 0, 0});
        hashMap.put("DM", new int[]{2, 0, 3, 4});
        hashMap.put("DO", new int[]{3, 3, 4, 4});
        hashMap.put("DZ", new int[]{3, 3, 4, 4});
        hashMap.put("EC", new int[]{2, 3, 3, 1});
        hashMap.put("EE", new int[]{0, 0, 0, 0});
        hashMap.put("EG", new int[]{3, 3, 1, 1});
        hashMap.put("EH", new int[]{2, 0, 2, 3});
        hashMap.put("ER", new int[]{4, 2, 2, 2});
        hashMap.put("ES", new int[]{0, 0, 1, 1});
        hashMap.put("ET", new int[]{4, 4, 4, 0});
        hashMap.put("FI", new int[]{0, 0, 1, 0});
        hashMap.put("FJ", new int[]{3, 2, 3, 3});
        hashMap.put("FK", new int[]{3, 4, 2, 1});
        hashMap.put("FM", new int[]{4, 2, 4, 0});
        hashMap.put("FO", new int[]{0, 0, 0, 1});
        hashMap.put("FR", new int[]{1, 0, 2, 1});
        hashMap.put("GA", new int[]{3, 3, 2, 1});
        hashMap.put("GB", new int[]{0, 1, 3, 2});
        hashMap.put("GD", new int[]{2, 0, 3, 0});
        hashMap.put("GE", new int[]{1, 1, 0, 3});
        hashMap.put("GF", new int[]{1, 2, 4, 4});
        hashMap.put("GG", new int[]{0, 1, 0, 0});
        hashMap.put("GH", new int[]{3, 2, 2, 2});
        hashMap.put("GI", new int[]{0, 0, 0, 1});
        hashMap.put("GL", new int[]{2, 4, 1, 4});
        hashMap.put("GM", new int[]{4, 3, 3, 0});
        hashMap.put("GN", new int[]{4, 4, 3, 4});
        hashMap.put("GP", new int[]{2, 2, 1, 3});
        hashMap.put("GQ", new int[]{4, 4, 3, 1});
        hashMap.put("GR", new int[]{1, 1, 0, 1});
        hashMap.put("GT", new int[]{3, 2, 3, 4});
        hashMap.put("GU", new int[]{1, 0, 4, 4});
        hashMap.put("GW", new int[]{4, 4, 4, 0});
        hashMap.put("GY", new int[]{3, 4, 1, 0});
        hashMap.put("HK", new int[]{0, 2, 3, 4});
        hashMap.put("HN", new int[]{3, 3, 2, 2});
        hashMap.put("HR", new int[]{1, 0, 0, 2});
        hashMap.put("HT", new int[]{3, 3, 3, 3});
        hashMap.put("HU", new int[]{0, 0, 1, 0});
        hashMap.put("ID", new int[]{2, 3, 3, 4});
        hashMap.put("IE", new int[]{0, 0, 1, 1});
        hashMap.put("IL", new int[]{0, 1, 1, 3});
        hashMap.put("IM", new int[]{0, 1, 0, 1});
        hashMap.put("IN", new int[]{2, 3, 3, 4});
        hashMap.put("IO", new int[]{4, 2, 2, 2});
        hashMap.put("IQ", new int[]{3, 3, 4, 3});
        hashMap.put("IR", new int[]{3, 2, 4, 4});
        hashMap.put("IS", new int[]{0, 0, 0, 0});
        hashMap.put("IT", new int[]{1, 0, 1, 3});
        hashMap.put("JE", new int[]{0, 0, 0, 1});
        hashMap.put("JM", new int[]{3, 3, 3, 2});
        hashMap.put("JO", new int[]{1, 1, 1, 2});
        hashMap.put(Country.JAPANESE_SHORT, new int[]{0, 1, 1, 2});
        hashMap.put("KE", new int[]{3, 3, 3, 3});
        hashMap.put(ExpandedProductParsedResult.KILOGRAM, new int[]{2, 2, 3, 3});
        hashMap.put("KH", new int[]{1, 0, 4, 4});
        hashMap.put("KI", new int[]{4, 4, 4, 4});
        hashMap.put("KM", new int[]{4, 4, 2, 2});
        hashMap.put("KN", new int[]{1, 0, 1, 3});
        hashMap.put("KP", new int[]{1, 2, 2, 2});
        hashMap.put(Country.KOREA_SOUTH_SHORT, new int[]{0, 4, 0, 2});
        hashMap.put("KW", new int[]{1, 2, 1, 2});
        hashMap.put("KY", new int[]{1, 1, 0, 2});
        hashMap.put("KZ", new int[]{1, 2, 2, 3});
        hashMap.put("LA", new int[]{3, 2, 2, 2});
        hashMap.put(ExpandedProductParsedResult.POUND, new int[]{3, 2, 0, 0});
        hashMap.put("LC", new int[]{2, 2, 1, 0});
        hashMap.put("LI", new int[]{0, 0, 1, 2});
        hashMap.put("LK", new int[]{1, 1, 2, 2});
        hashMap.put("LR", new int[]{3, 4, 3, 1});
        hashMap.put("LS", new int[]{3, 3, 2, 0});
        hashMap.put("LT", new int[]{0, 0, 0, 1});
        hashMap.put("LU", new int[]{0, 0, 1, 0});
        hashMap.put("LV", new int[]{0, 0, 0, 0});
        hashMap.put("LY", new int[]{4, 4, 4, 4});
        hashMap.put("MA", new int[]{2, 1, 2, 2});
        hashMap.put("MC", new int[]{1, 0, 1, 0});
        hashMap.put("MD", new int[]{1, 1, 0, 0});
        hashMap.put("ME", new int[]{1, 2, 2, 3});
        hashMap.put("MF", new int[]{1, 4, 3, 3});
        hashMap.put("MG", new int[]{3, 4, 1, 2});
        hashMap.put("MH", new int[]{4, 0, 2, 3});
        hashMap.put("MK", new int[]{1, 0, 0, 1});
        hashMap.put("ML", new int[]{4, 4, 4, 4});
        hashMap.put("MM", new int[]{2, 3, 1, 2});
        hashMap.put("MN", new int[]{2, 2, 2, 4});
        hashMap.put("MO", new int[]{0, 1, 4, 4});
        hashMap.put("MP", new int[]{0, 0, 4, 4});
        hashMap.put("MQ", new int[]{1, 1, 1, 3});
        hashMap.put("MR", new int[]{4, 2, 4, 2});
        hashMap.put("MS", new int[]{1, 2, 1, 2});
        hashMap.put("MT", new int[]{0, 0, 0, 0});
        hashMap.put("MU", new int[]{2, 2, 4, 4});
        hashMap.put("MV", new int[]{4, 2, 0, 1});
        hashMap.put("MW", new int[]{3, 2, 1, 1});
        hashMap.put("MX", new int[]{2, 4, 3, 1});
        hashMap.put("MY", new int[]{2, 3, 3, 3});
        hashMap.put("MZ", new int[]{3, 3, 2, 4});
        hashMap.put("NA", new int[]{4, 2, 1, 1});
        hashMap.put("NC", new int[]{2, 1, 3, 3});
        hashMap.put("NE", new int[]{4, 4, 4, 4});
        hashMap.put("NF", new int[]{0, 2, 2, 2});
        hashMap.put("NG", new int[]{3, 4, 2, 2});
        hashMap.put("NI", new int[]{3, 4, 3, 3});
        hashMap.put("NL", new int[]{0, 1, 3, 2});
        hashMap.put("NO", new int[]{0, 0, 1, 0});
        hashMap.put("NP", new int[]{2, 3, 2, 2});
        hashMap.put("NR", new int[]{4, 3, 4, 1});
        hashMap.put("NU", new int[]{4, 2, 2, 2});
        hashMap.put("NZ", new int[]{0, 0, 0, 1});
        hashMap.put("OM", new int[]{2, 2, 1, 3});
        hashMap.put("PA", new int[]{1, 3, 2, 3});
        hashMap.put("PE", new int[]{2, 2, 4, 4});
        hashMap.put("PF", new int[]{2, 2, 0, 1});
        hashMap.put("PG", new int[]{4, 4, 4, 4});
        hashMap.put("PH", new int[]{3, 0, 4, 4});
        hashMap.put("PK", new int[]{3, 3, 3, 3});
        hashMap.put("PL", new int[]{1, 0, 1, 3});
        hashMap.put("PM", new int[]{0, 2, 2, 3});
        hashMap.put("PR", new int[]{2, 3, 4, 3});
        hashMap.put("PS", new int[]{2, 3, 0, 4});
        hashMap.put("PT", new int[]{1, 1, 1, 1});
        hashMap.put("PW", new int[]{3, 2, 3, 0});
        hashMap.put("PY", new int[]{2, 1, 3, 3});
        hashMap.put("QA", new int[]{2, 3, 1, 2});
        hashMap.put("RE", new int[]{1, 1, 2, 2});
        hashMap.put("RO", new int[]{0, 1, 1, 3});
        hashMap.put("RS", new int[]{1, 1, 0, 0});
        hashMap.put("RU", new int[]{0, 1, 1, 1});
        hashMap.put("RW", new int[]{3, 4, 3, 1});
        hashMap.put("SA", new int[]{3, 2, 2, 3});
        hashMap.put("SB", new int[]{4, 4, 3, 0});
        hashMap.put("SC", new int[]{4, 2, 0, 1});
        hashMap.put("SD", new int[]{3, 4, 4, 4});
        hashMap.put("SE", new int[]{0, 0, 0, 0});
        hashMap.put("SG", new int[]{1, 2, 3, 3});
        hashMap.put("SH", new int[]{4, 2, 2, 2});
        hashMap.put("SI", new int[]{0, 1, 0, 0});
        hashMap.put("SJ", new int[]{3, 2, 0, 2});
        hashMap.put("SK", new int[]{0, 1, 0, 1});
        hashMap.put("SL", new int[]{4, 3, 2, 4});
        hashMap.put("SM", new int[]{1, 0, 1, 1});
        hashMap.put("SN", new int[]{4, 4, 4, 2});
        hashMap.put("SO", new int[]{4, 4, 4, 3});
        hashMap.put("SR", new int[]{3, 2, 2, 3});
        hashMap.put("SS", new int[]{4, 3, 4, 2});
        hashMap.put("ST", new int[]{3, 2, 2, 2});
        hashMap.put("SV", new int[]{2, 3, 2, 3});
        hashMap.put("SX", new int[]{2, 4, 2, 0});
        hashMap.put("SY", new int[]{4, 4, 2, 0});
        hashMap.put("SZ", new int[]{3, 4, 1, 1});
        hashMap.put("TC", new int[]{2, 1, 2, 1});
        hashMap.put("TD", new int[]{4, 4, 4, 3});
        hashMap.put("TG", new int[]{3, 2, 2, 0});
        hashMap.put("TH", new int[]{1, 3, 4, 4});
        hashMap.put("TJ", new int[]{4, 4, 4, 4});
        hashMap.put("TL", new int[]{4, 2, 4, 4});
        hashMap.put("TM", new int[]{4, 1, 3, 3});
        hashMap.put("TN", new int[]{2, 2, 1, 2});
        hashMap.put("TO", new int[]{2, 3, 3, 1});
        hashMap.put("TR", new int[]{1, 2, 0, 2});
        hashMap.put("TT", new int[]{2, 1, 1, 0});
        hashMap.put("TV", new int[]{4, 2, 2, 4});
        hashMap.put(Country.SIMPLIFIED_CHINESE_SHORT, new int[]{0, 0, 0, 1});
        hashMap.put("TZ", new int[]{3, 3, 3, 2});
        hashMap.put("UA", new int[]{0, 2, 1, 3});
        hashMap.put("UG", new int[]{4, 3, 2, 2});
        hashMap.put(Country.UNITED_STATES_SHORT, new int[]{0, 1, 3, 3});
        hashMap.put("UY", new int[]{2, 1, 2, 2});
        hashMap.put("UZ", new int[]{4, 3, 2, 4});
        hashMap.put("VA", new int[]{1, 2, 2, 2});
        hashMap.put("VC", new int[]{2, 0, 3, 2});
        hashMap.put("VE", new int[]{3, 4, 4, 3});
        hashMap.put("VG", new int[]{3, 1, 3, 4});
        hashMap.put("VI", new int[]{1, 0, 2, 4});
        hashMap.put("VN", new int[]{0, 2, 4, 4});
        hashMap.put("VU", new int[]{4, 1, 3, 2});
        hashMap.put("WS", new int[]{3, 2, 3, 0});
        hashMap.put("XK", new int[]{1, 2, 1, 0});
        hashMap.put("YE", new int[]{4, 4, 4, 2});
        hashMap.put("YT", new int[]{3, 1, 1, 2});
        hashMap.put("ZA", new int[]{2, 3, 1, 2});
        hashMap.put("ZM", new int[]{3, 3, 3, 1});
        hashMap.put("ZW", new int[]{3, 3, 2, 1});
        a = Collections.unmodifiableMap(hashMap);
    }
}

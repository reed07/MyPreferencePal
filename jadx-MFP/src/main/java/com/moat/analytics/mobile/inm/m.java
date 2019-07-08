package com.moat.analytics.mobile.inm;

import android.util.Log;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;

class m extends Exception {
    private static final Long a = Long.valueOf(DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
    private static Long b;
    private static Exception c = null;

    m(String str) {
        super(str);
    }

    static String a(String str, Exception exc) {
        if (exc instanceof m) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" failed: ");
            sb.append(exc.getMessage());
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(" failed unexpectedly");
        return sb2.toString();
    }

    static void a() {
        Exception exc = c;
        if (exc != null) {
            b(exc);
            c = null;
        }
    }

    static void a(Exception exc) {
        if (w.a().b) {
            Log.e("MoatException", Log.getStackTraceString(exc));
        } else {
            b(exc);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x00dd */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(java.lang.Exception r12) {
        /*
            com.moat.analytics.mobile.inm.w r0 = com.moat.analytics.mobile.inm.w.a()     // Catch:{ Exception -> 0x0193 }
            com.moat.analytics.mobile.inm.w$d r0 = r0.a     // Catch:{ Exception -> 0x0193 }
            com.moat.analytics.mobile.inm.w$d r1 = com.moat.analytics.mobile.inm.w.d.ON     // Catch:{ Exception -> 0x0193 }
            if (r0 != r1) goto L_0x0191
            com.moat.analytics.mobile.inm.w r0 = com.moat.analytics.mobile.inm.w.a()     // Catch:{ Exception -> 0x0193 }
            int r0 = r0.e     // Catch:{ Exception -> 0x0193 }
            if (r0 != 0) goto L_0x0013
            return
        L_0x0013:
            r1 = 100
            if (r0 >= r1) goto L_0x0024
            double r1 = (double) r0     // Catch:{ Exception -> 0x0193 }
            r3 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r1 = r1 / r3
            double r3 = java.lang.Math.random()     // Catch:{ Exception -> 0x0193 }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0024
            return
        L_0x0024:
            java.lang.String r1 = ""
            java.lang.String r2 = ""
            java.lang.String r3 = ""
            java.lang.String r4 = ""
            java.lang.String r5 = "https://px.moatads.com/pixel.gif?e=0&i=MOATSDK1&ac=1"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0193 }
            r6.<init>(r5)     // Catch:{ Exception -> 0x0193 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0193 }
            java.lang.String r7 = "&zt="
            r5.<init>(r7)     // Catch:{ Exception -> 0x0193 }
            boolean r7 = r12 instanceof com.moat.analytics.mobile.inm.m     // Catch:{ Exception -> 0x0193 }
            r8 = 1
            r9 = 0
            if (r7 == 0) goto L_0x0042
            r7 = 1
            goto L_0x0043
        L_0x0042:
            r7 = 0
        L_0x0043:
            r5.append(r7)     // Catch:{ Exception -> 0x0193 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0193 }
            r6.append(r5)     // Catch:{ Exception -> 0x0193 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0193 }
            java.lang.String r7 = "&zr="
            r5.<init>(r7)     // Catch:{ Exception -> 0x0193 }
            r5.append(r0)     // Catch:{ Exception -> 0x0193 }
            java.lang.String r0 = r5.toString()     // Catch:{ Exception -> 0x0193 }
            r6.append(r0)     // Catch:{ Exception -> 0x0193 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = "&zm="
            r0.<init>(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = r12.getMessage()     // Catch:{ Exception -> 0x00b1 }
            if (r5 != 0) goto L_0x006e
            java.lang.String r5 = "null"
            goto L_0x0082
        L_0x006e:
            java.lang.String r5 = r12.getMessage()     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r7 = "UTF-8"
            byte[] r5 = r5.getBytes(r7)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = android.util.Base64.encodeToString(r5, r9)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r7 = "UTF-8"
            java.lang.String r5 = java.net.URLEncoder.encode(r5, r7)     // Catch:{ Exception -> 0x00b1 }
        L_0x0082:
            r0.append(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00b1 }
            r6.append(r0)     // Catch:{ Exception -> 0x00b1 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = "&k="
            r0.<init>(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r12 = android.util.Log.getStackTraceString(r12)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = "UTF-8"
            byte[] r12 = r12.getBytes(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r12 = android.util.Base64.encodeToString(r12, r9)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = "UTF-8"
            java.lang.String r12 = java.net.URLEncoder.encode(r12, r5)     // Catch:{ Exception -> 0x00b1 }
            r0.append(r12)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r12 = r0.toString()     // Catch:{ Exception -> 0x00b1 }
            r6.append(r12)     // Catch:{ Exception -> 0x00b1 }
        L_0x00b1:
            java.lang.String r1 = "INM"
            java.lang.String r12 = "&zMoatMMAKv=c334ae83accfebb8da23104450c896463c9cfab7"
            r6.append(r12)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r3 = "2.5.0"
            com.moat.analytics.mobile.inm.s$a r12 = com.moat.analytics.mobile.inm.s.d()     // Catch:{ Exception -> 0x00dd }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00dd }
            java.lang.String r5 = "&zMoatMMAKan="
            r0.<init>(r5)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r5 = r12.a()     // Catch:{ Exception -> 0x00dd }
            r0.append(r5)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00dd }
            r6.append(r0)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r2 = r12.b()     // Catch:{ Exception -> 0x00dd }
            int r12 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00dd }
            java.lang.String r4 = java.lang.Integer.toString(r12)     // Catch:{ Exception -> 0x00dd }
        L_0x00dd:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0193 }
            java.lang.String r0 = "&d=Android:"
            r12.<init>(r0)     // Catch:{ Exception -> 0x0193 }
            r12.append(r1)     // Catch:{ Exception -> 0x0193 }
            java.lang.String r0 = ":"
            r12.append(r0)     // Catch:{ Exception -> 0x0193 }
            r12.append(r2)     // Catch:{ Exception -> 0x0193 }
            java.lang.String r0 = ":-"
            r12.append(r0)     // Catch:{ Exception -> 0x0193 }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x0193 }
            r6.append(r12)     // Catch:{ Exception -> 0x0193 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0193 }
            java.lang.String r0 = "&bo="
            r12.<init>(r0)     // Catch:{ Exception -> 0x0193 }
            r12.append(r3)     // Catch:{ Exception -> 0x0193 }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x0193 }
            r6.append(r12)     // Catch:{ Exception -> 0x0193 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0193 }
            java.lang.String r0 = "&bd="
            r12.<init>(r0)     // Catch:{ Exception -> 0x0193 }
            r12.append(r4)     // Catch:{ Exception -> 0x0193 }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x0193 }
            r6.append(r12)     // Catch:{ Exception -> 0x0193 }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0193 }
            java.lang.Long r12 = java.lang.Long.valueOf(r0)     // Catch:{ Exception -> 0x0193 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0193 }
            java.lang.String r1 = "&t="
            r0.<init>(r1)     // Catch:{ Exception -> 0x0193 }
            r0.append(r12)     // Catch:{ Exception -> 0x0193 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0193 }
            r6.append(r0)     // Catch:{ Exception -> 0x0193 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0193 }
            java.lang.String r1 = "&de="
            r0.<init>(r1)     // Catch:{ Exception -> 0x0193 }
            java.util.Locale r1 = java.util.Locale.ROOT     // Catch:{ Exception -> 0x0193 }
            java.lang.String r2 = "%.0f"
            java.lang.Object[] r3 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x0193 }
            double r4 = java.lang.Math.random()     // Catch:{ Exception -> 0x0193 }
            r7 = 4621819117588971520(0x4024000000000000, double:10.0)
            r10 = 4622945017495814144(0x4028000000000000, double:12.0)
            double r7 = java.lang.Math.pow(r7, r10)     // Catch:{ Exception -> 0x0193 }
            double r4 = r4 * r7
            double r4 = java.lang.Math.floor(r4)     // Catch:{ Exception -> 0x0193 }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0193 }
            r3[r9] = r4     // Catch:{ Exception -> 0x0193 }
            java.lang.String r1 = java.lang.String.format(r1, r2, r3)     // Catch:{ Exception -> 0x0193 }
            r0.append(r1)     // Catch:{ Exception -> 0x0193 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0193 }
            r6.append(r0)     // Catch:{ Exception -> 0x0193 }
            java.lang.String r0 = "&cs=0"
            r6.append(r0)     // Catch:{ Exception -> 0x0193 }
            java.lang.Long r0 = b     // Catch:{ Exception -> 0x0193 }
            if (r0 == 0) goto L_0x0187
            long r0 = r12.longValue()     // Catch:{ Exception -> 0x0193 }
            java.lang.Long r2 = b     // Catch:{ Exception -> 0x0193 }
            long r2 = r2.longValue()     // Catch:{ Exception -> 0x0193 }
            long r0 = r0 - r2
            java.lang.Long r2 = a     // Catch:{ Exception -> 0x0193 }
            long r2 = r2.longValue()     // Catch:{ Exception -> 0x0193 }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x0190
        L_0x0187:
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x0193 }
            com.moat.analytics.mobile.inm.q.b(r0)     // Catch:{ Exception -> 0x0193 }
            b = r12     // Catch:{ Exception -> 0x0193 }
        L_0x0190:
            return
        L_0x0191:
            c = r12     // Catch:{ Exception -> 0x0193 }
        L_0x0193:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.inm.m.b(java.lang.Exception):void");
    }
}

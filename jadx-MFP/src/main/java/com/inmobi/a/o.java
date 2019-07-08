package com.inmobi.a;

import android.content.Context;
import android.location.Criteria;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.inmobi.commons.core.configs.a;
import com.inmobi.commons.core.configs.b;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.utilities.b.h;
import com.inmobi.commons.core.utilities.f;
import com.inmobi.commons.core.utilities.uid.d;
import java.util.HashMap;

/* compiled from: SignalsComponent */
public class o implements c {
    private static final String b = "o";
    private static final Object c = new Object();
    private static volatile o d;
    public p a = new p();
    private h e;
    private g f;
    private boolean g = false;

    public static o a() {
        o oVar = d;
        if (oVar == null) {
            synchronized (c) {
                oVar = d;
                if (oVar == null) {
                    oVar = new o();
                    d = oVar;
                }
            }
        }
        return oVar;
    }

    private o() {
        b.a().a((a) this.a, (c) this);
        h.a().a(this.a.a.b());
        m.a();
        m.a(this.a.a.a());
        com.inmobi.commons.core.e.b.a().a("signals", this.a.c);
    }

    public final void a(a aVar) {
        this.a = (p) aVar;
        m.a();
        m.a(this.a.a.a());
        h.a().a(this.a.a.b());
        com.inmobi.commons.core.e.b.a().a("signals", this.a.c);
    }

    public final synchronized void b() {
        if (!this.g) {
            this.g = true;
            f();
            m a2 = m.a();
            try {
                if (m.a && m.c() && a2.g()) {
                    if (a2.b != null) {
                        Criteria criteria = new Criteria();
                        criteria.setBearingAccuracy(2);
                        criteria.setPowerRequirement(2);
                        criteria.setCostAllowed(false);
                        String bestProvider = a2.b.getBestProvider(criteria, true);
                        if (bestProvider != null) {
                            a2.b.requestSingleUpdate(bestProvider, a2, a2.c.getLooper());
                        }
                    }
                    if (!m.b() && f.a("signals")) {
                        Context b2 = com.inmobi.commons.a.a.b();
                        try {
                            if (a2.d == null) {
                                a2.d = new Builder(b2).addConnectionCallbacks(new ConnectionCallbacks() {
                                    public final void onConnected(@Nullable Bundle bundle) {
                                        m.e;
                                        m.h = true;
                                    }

                                    public final void onConnectionSuspended(int i) {
                                        m.h = false;
                                        m.e;
                                    }
                                }).addOnConnectionFailedListener(new OnConnectionFailedListener() {
                                    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                                        m.h = false;
                                    }
                                }).addApi(LocationServices.API).build();
                                a2.d.connect();
                                return;
                            }
                            a2.d.connect();
                        } catch (Exception e2) {
                            StringBuilder sb = new StringBuilder("Error in connecting to GooglePlayServices API : (");
                            sb.append(e2.getMessage());
                            sb.append(")");
                        }
                    }
                }
            } catch (Exception e3) {
                new StringBuilder("SDK encountered unexpected error in initializing location collection; ").append(e3.getMessage());
            }
        }
    }

    public final synchronized void c() {
        if (this.g) {
            this.g = false;
            n a2 = n.a();
            if (a().a.a.b()) {
                h.a().c = System.currentTimeMillis();
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("sessionId", h.a().a);
                    hashMap.put("totalNetworkTime", Long.valueOf(a2.e));
                    hashMap.put("sessionDuration", Long.valueOf(SystemClock.elapsedRealtime() - a2.f));
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("signals", "SDKSessionEnded", hashMap);
                } catch (Exception e2) {
                    StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                    sb.append(e2.getMessage());
                    sb.append(")");
                }
            }
            if (this.e != null) {
                h hVar = this.e;
                hVar.a.b = true;
                hVar.a.sendEmptyMessageDelayed(2, (long) (a().a.a.c * 1000));
            }
            m a3 = m.a();
            if (m.a && m.c()) {
                if (a3.b != null) {
                    a3.b.removeUpdates(a3);
                }
                if (a3.d != null) {
                    a3.d.disconnect();
                }
            }
            a3.d = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final d d() {
        return new d(this.a.p.a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void f() {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.g     // Catch:{ all -> 0x008e }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r7)
            return
        L_0x0007:
            com.inmobi.a.p r0 = r7.a     // Catch:{ all -> 0x008e }
            com.inmobi.a.p$b r0 = r0.a     // Catch:{ all -> 0x008e }
            boolean r0 = r0.a     // Catch:{ all -> 0x008e }
            if (r0 == 0) goto L_0x008c
            com.inmobi.a.n r0 = com.inmobi.a.n.a()     // Catch:{ all -> 0x008e }
            com.inmobi.a.o r1 = a()     // Catch:{ all -> 0x008e }
            com.inmobi.a.p r1 = r1.a     // Catch:{ all -> 0x008e }
            com.inmobi.a.p$b r1 = r1.a     // Catch:{ all -> 0x008e }
            boolean r1 = r1.b()     // Catch:{ all -> 0x008e }
            if (r1 == 0) goto L_0x007c
            java.util.UUID r1 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x008e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x008e }
            com.inmobi.commons.core.utilities.b.h r2 = com.inmobi.commons.core.utilities.b.h.a()     // Catch:{ all -> 0x008e }
            r2.a = r1     // Catch:{ all -> 0x008e }
            com.inmobi.commons.core.utilities.b.h r2 = com.inmobi.commons.core.utilities.b.h.a()     // Catch:{ all -> 0x008e }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x008e }
            r2.b = r3     // Catch:{ all -> 0x008e }
            com.inmobi.commons.core.utilities.b.h r2 = com.inmobi.commons.core.utilities.b.h.a()     // Catch:{ all -> 0x008e }
            r3 = 0
            r2.c = r3     // Catch:{ all -> 0x008e }
            long r5 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x008e }
            r0.f = r5     // Catch:{ all -> 0x008e }
            r0.a = r3     // Catch:{ all -> 0x008e }
            r0.b = r3     // Catch:{ all -> 0x008e }
            r0.c = r3     // Catch:{ all -> 0x008e }
            r0.d = r3     // Catch:{ all -> 0x008e }
            r0.e = r3     // Catch:{ all -> 0x008e }
            r0.f = r3     // Catch:{ all -> 0x008e }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x008e }
            r0.<init>()     // Catch:{ all -> 0x008e }
            java.lang.String r2 = "sessionId"
            r0.put(r2, r1)     // Catch:{ all -> 0x008e }
            com.inmobi.commons.core.e.b.a()     // Catch:{ Exception -> 0x0068 }
            java.lang.String r1 = "signals"
            java.lang.String r2 = "SDKSessionStarted"
            com.inmobi.commons.core.e.b.a(r1, r2, r0)     // Catch:{ Exception -> 0x0068 }
            goto L_0x007c
        L_0x0068:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x008e }
            java.lang.String r2 = "Error in submitting telemetry event : ("
            r1.<init>(r2)     // Catch:{ all -> 0x008e }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x008e }
            r1.append(r0)     // Catch:{ all -> 0x008e }
            java.lang.String r0 = ")"
            r1.append(r0)     // Catch:{ all -> 0x008e }
        L_0x007c:
            com.inmobi.a.h r0 = r7.e     // Catch:{ all -> 0x008e }
            if (r0 != 0) goto L_0x0087
            com.inmobi.a.h r0 = new com.inmobi.a.h     // Catch:{ all -> 0x008e }
            r0.<init>()     // Catch:{ all -> 0x008e }
            r7.e = r0     // Catch:{ all -> 0x008e }
        L_0x0087:
            com.inmobi.a.h r0 = r7.e     // Catch:{ all -> 0x008e }
            r0.a()     // Catch:{ all -> 0x008e }
        L_0x008c:
            monitor-exit(r7)
            return
        L_0x008e:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.a.o.f():void");
    }

    public final void e() {
        if (this.g && this.a.b.a) {
            if (this.f == null) {
                this.f = new g();
            }
            this.f.a(this.a.b);
        }
    }
}

package com.inmobi.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.inmobi.a.b.b;
import com.inmobi.commons.core.e.f;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.network.e;
import com.inmobi.commons.core.utilities.b.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: IceCollector */
class h {
    /* access modifiers changed from: private */
    public static final String b = "h";
    a a;

    /* compiled from: IceCollector */
    static class a extends Handler {
        private List<l> a = new ArrayList();
        /* access modifiers changed from: private */
        public boolean b;

        a(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    h.b;
                    sendEmptyMessage(3);
                    return;
                case 2:
                    h.b;
                    removeMessages(3);
                    sendEmptyMessage(4);
                    return;
                case 3:
                    h.b;
                    if (this.b) {
                        sendEmptyMessage(2);
                        return;
                    }
                    l lVar = new l();
                    lVar.a = b.a();
                    lVar.c = m.a().e();
                    if (this.a != null) {
                        if ((lVar.a == null && lVar.b == null) ? false : true) {
                            this.a.add(lVar);
                            if (this.a.size() > o.a().a.a.d) {
                                try {
                                    com.inmobi.commons.core.e.b.a().a(new f("signals", "SampleSizeExceeded"));
                                } catch (Exception e) {
                                    h.b;
                                    StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                                    sb.append(e.getMessage());
                                    sb.append(")");
                                }
                                while (this.a.size() > o.a().a.a.d) {
                                    this.a.remove(0);
                                }
                            }
                        }
                    }
                    sendEmptyMessageDelayed(3, (long) (o.a().a.a.b * 1000));
                    return;
                case 4:
                    k kVar = new k();
                    kVar.a = m.a().d();
                    kVar.c = this.a;
                    n.a();
                    kVar.b = n.b();
                    p.b bVar = o.a().a.a;
                    j jVar = new j(bVar.e, bVar.f, bVar.g, o.a().d(), kVar);
                    new Thread(new Runnable() {
                        public final void run() {
                            int i = 0;
                            while (i <= i.this.b.a) {
                                i.a;
                                long elapsedRealtime = SystemClock.elapsedRealtime();
                                d a2 = new e(i.this.b).a();
                                try {
                                    n.a().a(i.this.b.g());
                                    n.a().b(a2.c());
                                    n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
                                } catch (Exception e) {
                                    i.a;
                                    new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
                                }
                                if (a2.a()) {
                                    i.a;
                                    i++;
                                    if (i > i.this.b.a) {
                                        try {
                                            com.inmobi.commons.core.e.b.a().a(new f("signals", "RetryCountExceeded"));
                                            return;
                                        } catch (Exception e2) {
                                            i.a;
                                            StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                                            sb.append(e2.getMessage());
                                            sb.append(")");
                                            return;
                                        }
                                    } else {
                                        try {
                                            Thread.sleep((long) (i.this.b.b * 1000));
                                        } catch (InterruptedException unused) {
                                            i.a;
                                        }
                                    }
                                } else {
                                    i.a;
                                    try {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("url", i.this.b.q);
                                        hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - 0));
                                        hashMap.put("payloadSize", Long.valueOf(i.this.b.g() + a2.c()));
                                        com.inmobi.commons.core.e.b.a();
                                        com.inmobi.commons.core.e.b.a("signals", "NICElatency", hashMap);
                                        HashMap hashMap2 = new HashMap();
                                        hashMap2.put("sessionId", h.a().a);
                                        n a3 = n.a();
                                        hashMap2.put("totalWifiSentBytes", Long.valueOf(a3.a));
                                        hashMap2.put("totalWifiReceivedBytes", Long.valueOf(a3.b));
                                        hashMap2.put("totalCarrierSentBytes", Long.valueOf(a3.c));
                                        hashMap2.put("totalCarrierReceivedBytes", Long.valueOf(a3.d));
                                        hashMap2.put("totalNetworkTime", Long.valueOf(a3.e));
                                        com.inmobi.commons.core.e.b.a();
                                        com.inmobi.commons.core.e.b.a("signals", "SDKNetworkStats", hashMap2);
                                        return;
                                    } catch (Exception e3) {
                                        i.a;
                                        StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                                        sb2.append(e3.getMessage());
                                        sb2.append(")");
                                        return;
                                    }
                                }
                            }
                        }
                    }).start();
                    this.a = new ArrayList();
                    break;
            }
        }
    }

    h() {
        HandlerThread handlerThread = new HandlerThread("DataCollectionHandler");
        handlerThread.start();
        this.a = new a(handlerThread.getLooper());
    }

    public final synchronized void a() {
        this.a.b = false;
        if (!this.a.hasMessages(3)) {
            this.a.removeMessages(2);
            this.a.sendEmptyMessage(1);
        }
    }
}

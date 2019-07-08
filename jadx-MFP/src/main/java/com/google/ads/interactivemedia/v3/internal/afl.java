package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: IMASDK */
public class afl extends afj {
    /* access modifiers changed from: private */
    public static AdvertisingIdClient c;
    /* access modifiers changed from: private */
    public static CountDownLatch d = new CountDownLatch(1);
    /* access modifiers changed from: private */
    public static volatile boolean e;
    private boolean f;

    /* compiled from: IMASDK */
    class a {
        private String a;
        private boolean b;

        public a(afl afl, String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public final String a() {
            return this.a;
        }

        public final boolean b() {
            return this.b;
        }
    }

    /* compiled from: IMASDK */
    static final class b implements Runnable {
        private Context a;

        public b(Context context) {
            this.a = context.getApplicationContext();
            if (this.a == null) {
                this.a = context;
            }
        }

        public final void run() {
            synchronized (afl.class) {
                try {
                    if (afl.c == null) {
                        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.a);
                        advertisingIdClient.start();
                        afl.c = advertisingIdClient;
                    }
                    afl.d.countDown();
                } catch (GooglePlayServicesNotAvailableException unused) {
                    try {
                        afl.e = true;
                        afl.d.countDown();
                    } catch (Throwable th) {
                        afl.d.countDown();
                        throw th;
                    }
                } catch (IOException unused2) {
                    afl.d.countDown();
                } catch (GooglePlayServicesRepairableException unused3) {
                    afl.d.countDown();
                }
            }
        }
    }

    public static afl a(String str, Context context) {
        return a(str, context, true);
    }

    private static afl a(String str, Context context, boolean z) {
        afo afo = new afo();
        a(str, context, afo);
        synchronized (afl.class) {
            if (c == null) {
                new Thread(new b(context)).start();
            }
        }
        return new afl(context, afo, new afp(239), true);
    }

    private afl(Context context, afo afo, afp afp, boolean z) {
        super(context, afo, afp);
        this.f = z;
    }

    private final a c() throws IOException {
        try {
            if (!d.await(2, TimeUnit.SECONDS)) {
                return new a(this, null, false);
            }
            synchronized (afl.class) {
                if (c == null) {
                    a aVar = new a(this, null, false);
                    return aVar;
                }
                Info info = c.getInfo();
                return new a(this, a(info.getId()), info.isLimitAdTrackingEnabled());
            }
        } catch (InterruptedException unused) {
            return new a(this, null, false);
        }
    }

    /* access modifiers changed from: protected */
    public final void b(Context context) {
        super.b(context);
        try {
            if (!e) {
                if (this.f) {
                    a c2 = c();
                    String a2 = c2.a();
                    if (a2 != null) {
                        a(28, c2.b() ? 1 : 0);
                        a(26, 5);
                        a(24, a2);
                    }
                    return;
                }
            }
            a(24, d(context));
        } catch (IOException unused) {
        } catch (afk unused2) {
        }
    }
}

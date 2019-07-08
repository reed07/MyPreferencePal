package io.uacf.core.app;

import com.uacf.core.util.Strings;
import io.uacf.core.interfaces.UacfDeviceIdProvider;
import java.util.concurrent.CountDownLatch;

public abstract class BackgroundFetchDeviceIdProvider implements UacfDeviceIdProvider {
    protected static String deviceId;
    protected static CountDownLatch latch;

    /* access modifiers changed from: protected */
    public abstract String fetchDeviceId();

    protected BackgroundFetchDeviceIdProvider() {
        backgroundDeviceIdFetch();
    }

    public String get() {
        if (latch.getCount() > 0) {
            try {
                latch.await();
            } catch (InterruptedException unused) {
            }
        }
        return deviceId;
    }

    /* access modifiers changed from: protected */
    public void backgroundDeviceIdFetch() {
        if (Strings.isEmpty(deviceId) && latch == null) {
            latch = new CountDownLatch(1);
            new Thread(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
                    if (com.uacf.core.util.Strings.isEmpty(io.uacf.core.app.BackgroundFetchDeviceIdProvider.deviceId) == false) goto L_0x003d;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
                    io.uacf.core.app.BackgroundFetchDeviceIdProvider.deviceId = java.util.UUID.randomUUID().toString();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
                    io.uacf.core.app.BackgroundFetchDeviceIdProvider.latch.countDown();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:3:0x000e, code lost:
                    if (com.uacf.core.util.Strings.isEmpty(io.uacf.core.app.BackgroundFetchDeviceIdProvider.deviceId) != false) goto L_0x0033;
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r2 = this;
                        io.uacf.core.app.BackgroundFetchDeviceIdProvider r0 = io.uacf.core.app.BackgroundFetchDeviceIdProvider.this     // Catch:{ Exception -> 0x002a, all -> 0x0011 }
                        java.lang.String r0 = r0.fetchDeviceId()     // Catch:{ Exception -> 0x002a, all -> 0x0011 }
                        io.uacf.core.app.BackgroundFetchDeviceIdProvider.deviceId = r0     // Catch:{ Exception -> 0x002a, all -> 0x0011 }
                        java.lang.String r0 = io.uacf.core.app.BackgroundFetchDeviceIdProvider.deviceId
                        boolean r0 = com.uacf.core.util.Strings.isEmpty(r0)
                        if (r0 == 0) goto L_0x003d
                        goto L_0x0033
                    L_0x0011:
                        r0 = move-exception
                        java.lang.String r1 = io.uacf.core.app.BackgroundFetchDeviceIdProvider.deviceId
                        boolean r1 = com.uacf.core.util.Strings.isEmpty(r1)
                        if (r1 == 0) goto L_0x0024
                        java.util.UUID r1 = java.util.UUID.randomUUID()
                        java.lang.String r1 = r1.toString()
                        io.uacf.core.app.BackgroundFetchDeviceIdProvider.deviceId = r1
                    L_0x0024:
                        java.util.concurrent.CountDownLatch r1 = io.uacf.core.app.BackgroundFetchDeviceIdProvider.latch
                        r1.countDown()
                        throw r0
                    L_0x002a:
                        java.lang.String r0 = io.uacf.core.app.BackgroundFetchDeviceIdProvider.deviceId
                        boolean r0 = com.uacf.core.util.Strings.isEmpty(r0)
                        if (r0 == 0) goto L_0x003d
                    L_0x0033:
                        java.util.UUID r0 = java.util.UUID.randomUUID()
                        java.lang.String r0 = r0.toString()
                        io.uacf.core.app.BackgroundFetchDeviceIdProvider.deviceId = r0
                    L_0x003d:
                        java.util.concurrent.CountDownLatch r0 = io.uacf.core.app.BackgroundFetchDeviceIdProvider.latch
                        r0.countDown()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.uacf.core.app.BackgroundFetchDeviceIdProvider.AnonymousClass1.run():void");
                }
            }).start();
        }
    }
}

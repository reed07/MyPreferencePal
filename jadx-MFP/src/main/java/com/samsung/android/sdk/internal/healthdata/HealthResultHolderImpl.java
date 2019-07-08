package com.samsung.android.sdk.internal.healthdata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.samsung.android.sdk.healthdata.HealthResultHolder;
import com.samsung.android.sdk.healthdata.HealthResultHolder.BaseResult;
import com.samsung.android.sdk.healthdata.HealthResultHolder.ResultListener;
import java.util.concurrent.CountDownLatch;

public class HealthResultHolderImpl<T extends BaseResult> implements HealthResultHolder<T>, RemoteResultListener<T> {
    private final Object a;
    private final a<T> b;
    private final CountDownLatch c;
    private ResultListener<T> d;
    private volatile T e;
    private volatile boolean f;
    private boolean g;
    private boolean h;

    static class a<T extends BaseResult> extends Handler {
        public a() {
            this(Looper.getMainLooper());
        }

        public a(Looper looper) {
            super(looper);
        }

        public final void a(ResultListener<T> resultListener, T t) {
            sendMessage(obtainMessage(1, new Pair(resultListener, t)));
        }

        public final void handleMessage(Message message) {
            if (message.what != 1) {
                Log.d("Health.ResultHolder", "No default handler");
                return;
            }
            Pair pair = (Pair) message.obj;
            ResultListener resultListener = (ResultListener) pair.first;
            BaseResult baseResult = (BaseResult) pair.second;
            if (resultListener != null) {
                resultListener.onResult(baseResult);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void cancelResult() throws RemoteException {
    }

    HealthResultHolderImpl() {
        this.a = new Object();
        this.c = new CountDownLatch(1);
        this.b = new a<>();
    }

    HealthResultHolderImpl(Looper looper) {
        this.a = new Object();
        this.c = new CountDownLatch(1);
        this.b = new a<>(looper);
    }

    public static <T extends BaseResult> HealthResultHolderImpl<T> createAndSetResult(T t, Looper looper) {
        HealthResultHolderImpl<T> healthResultHolderImpl = new HealthResultHolderImpl<>(looper);
        healthResultHolderImpl.setResult(t);
        return healthResultHolderImpl;
    }

    public final boolean isReady() {
        return this.c.getCount() == 0;
    }

    private T a() {
        T t;
        synchronized (this.a) {
            c();
            b();
            t = this.e;
            clearStatus();
        }
        return t;
    }

    public final T await() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            b();
            try {
                this.c.await();
            } catch (InterruptedException unused) {
                synchronized (this.a) {
                    if (!isReady()) {
                        this.h = true;
                    }
                }
            }
            c();
            return a();
        }
        throw new IllegalStateException("await() must not be called on the main thread");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultListener(com.samsung.android.sdk.healthdata.HealthResultHolder.ResultListener<T> r4) {
        /*
            r3 = this;
            r3.b()
            java.lang.Object r0 = r3.a
            monitor-enter(r0)
            boolean r1 = r3.isCanceled()     // Catch:{ all -> 0x0022 }
            if (r1 == 0) goto L_0x000e
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return
        L_0x000e:
            boolean r1 = r3.isReady()     // Catch:{ all -> 0x0022 }
            if (r1 == 0) goto L_0x001e
            com.samsung.android.sdk.internal.healthdata.HealthResultHolderImpl$a<T> r1 = r3.b     // Catch:{ all -> 0x0022 }
            com.samsung.android.sdk.healthdata.HealthResultHolder$BaseResult r2 = r3.a()     // Catch:{ all -> 0x0022 }
            r1.a(r4, r2)     // Catch:{ all -> 0x0022 }
            goto L_0x0020
        L_0x001e:
            r3.d = r4     // Catch:{ all -> 0x0022 }
        L_0x0020:
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return
        L_0x0022:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.internal.healthdata.HealthResultHolderImpl.setResultListener(com.samsung.android.sdk.healthdata.HealthResultHolder$ResultListener):void");
    }

    public final boolean isCanceled() {
        boolean z;
        synchronized (this.a) {
            z = this.g;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0023, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void cancel() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.a
            monitor-enter(r0)
            boolean r1 = r3.g     // Catch:{ all -> 0x0024 }
            if (r1 != 0) goto L_0x0022
            boolean r1 = r3.f     // Catch:{ all -> 0x0024 }
            if (r1 == 0) goto L_0x000c
            goto L_0x0022
        L_0x000c:
            r3.cancelResult()     // Catch:{ RemoteException -> 0x0010 }
            goto L_0x001a
        L_0x0010:
            r1 = move-exception
            java.lang.String r2 = "Health.ResultHolder"
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0024 }
            android.util.Log.d(r2, r1)     // Catch:{ all -> 0x0024 }
        L_0x001a:
            r1 = 0
            r3.d = r1     // Catch:{ all -> 0x0024 }
            r1 = 1
            r3.g = r1     // Catch:{ all -> 0x0024 }
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return
        L_0x0024:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.internal.healthdata.HealthResultHolderImpl.cancel():void");
    }

    /* access modifiers changed from: protected */
    public void clearStatus() {
        this.f = true;
        this.e = null;
        this.d = null;
    }

    public void onReceiveHealthResult(int i, T t) {
        setResult(t);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResult(T r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.a
            monitor-enter(r0)
            boolean r1 = r3.h     // Catch:{ all -> 0x003b }
            if (r1 != 0) goto L_0x0039
            boolean r1 = r3.g     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x000c
            goto L_0x0039
        L_0x000c:
            boolean r1 = r3.isReady()     // Catch:{ all -> 0x003b }
            if (r1 != 0) goto L_0x0031
            r3.b()     // Catch:{ all -> 0x003b }
            r3.e = r4     // Catch:{ all -> 0x003b }
            java.util.concurrent.CountDownLatch r4 = r3.c     // Catch:{ all -> 0x003b }
            r4.countDown()     // Catch:{ all -> 0x003b }
            com.samsung.android.sdk.healthdata.HealthResultHolder$ResultListener<T> r4 = r3.d     // Catch:{ all -> 0x003b }
            if (r4 == 0) goto L_0x002f
            boolean r4 = r3.g     // Catch:{ all -> 0x003b }
            if (r4 != 0) goto L_0x002f
            com.samsung.android.sdk.internal.healthdata.HealthResultHolderImpl$a<T> r4 = r3.b     // Catch:{ all -> 0x003b }
            com.samsung.android.sdk.healthdata.HealthResultHolder$ResultListener<T> r1 = r3.d     // Catch:{ all -> 0x003b }
            com.samsung.android.sdk.healthdata.HealthResultHolder$BaseResult r2 = r3.a()     // Catch:{ all -> 0x003b }
            r4.a(r1, r2)     // Catch:{ all -> 0x003b }
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x0031:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x003b }
            java.lang.String r1 = "Result have been set already"
            r4.<init>(r1)     // Catch:{ all -> 0x003b }
            throw r4     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x003b:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.internal.healthdata.HealthResultHolderImpl.setResult(com.samsung.android.sdk.healthdata.HealthResultHolder$BaseResult):void");
    }

    private void b() {
        if (this.f) {
            throw new IllegalStateException("Result has already been processed");
        }
    }

    private void c() {
        if (!isReady()) {
            throw new IllegalStateException("Result is not ready");
        }
    }
}

package io.requery.reactivex;

import io.requery.query.Result;
import io.requery.util.CloseableIterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class QuerySubscription<T> implements Subscription {
    private final AtomicBoolean canceled;
    private final AtomicLong emitted;
    private final AtomicLong requested;
    private final Result<T> result;
    private final Subscriber<? super T> subscriber;

    public void request(long j) {
        if (j == Long.MAX_VALUE) {
            try {
                if (this.requested.compareAndSet(0, Long.MAX_VALUE)) {
                    requestAll();
                    return;
                }
            } catch (Throwable th) {
                this.subscriber.onError(th);
                return;
            }
        }
        if (j > 0 && add(this.requested, j) == 0) {
            requestN(j);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        if (r0 != null) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        if (r1 != null) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        r0.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void requestAll() {
        /*
            r4 = this;
            io.requery.query.Result<T> r0 = r4.result
            io.requery.util.CloseableIterator r0 = r0.iterator()
        L_0x0006:
            r1 = 0
            java.util.concurrent.atomic.AtomicBoolean r2 = r4.canceled     // Catch:{ Throwable -> 0x0031 }
            boolean r2 = r2.get()     // Catch:{ Throwable -> 0x0031 }
            if (r2 != 0) goto L_0x0029
            boolean r2 = r0.hasNext()     // Catch:{ Throwable -> 0x0031 }
            if (r2 == 0) goto L_0x0024
            org.reactivestreams.Subscriber<? super T> r2 = r4.subscriber     // Catch:{ Throwable -> 0x0031 }
            java.lang.Object r3 = r0.next()     // Catch:{ Throwable -> 0x0031 }
            r2.onNext(r3)     // Catch:{ Throwable -> 0x0031 }
            java.util.concurrent.atomic.AtomicLong r2 = r4.emitted     // Catch:{ Throwable -> 0x0031 }
            r2.incrementAndGet()     // Catch:{ Throwable -> 0x0031 }
            goto L_0x0006
        L_0x0024:
            org.reactivestreams.Subscriber<? super T> r2 = r4.subscriber     // Catch:{ Throwable -> 0x0031 }
            r2.onComplete()     // Catch:{ Throwable -> 0x0031 }
        L_0x0029:
            if (r0 == 0) goto L_0x002e
            r0.close()
        L_0x002e:
            return
        L_0x002f:
            r2 = move-exception
            goto L_0x0033
        L_0x0031:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x002f }
        L_0x0033:
            if (r0 == 0) goto L_0x0043
            if (r1 == 0) goto L_0x0040
            r0.close()     // Catch:{ Throwable -> 0x003b }
            goto L_0x0043
        L_0x003b:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x0043
        L_0x0040:
            r0.close()
        L_0x0043:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.requery.reactivex.QuerySubscription.requestAll():void");
    }

    private void requestN(long j) {
        Throwable th;
        long j2 = j;
        while (true) {
            long j3 = 0;
            if (j2 > 0) {
                CloseableIterator it = this.result.iterator(this.emitted.intValue(), (int) j);
                while (true) {
                    if (this.canceled.get() || !it.hasNext()) {
                        break;
                    }
                    long j4 = 1 + j3;
                    if (j3 >= j2) {
                        j3 = j4;
                        break;
                    } else {
                        this.subscriber.onNext(it.next());
                        j3 = j4;
                    }
                }
                this.emitted.addAndGet(j3);
                if (this.canceled.get() || j3 >= j2) {
                    try {
                        j2 = this.requested.addAndGet(-j2);
                        if (it != null) {
                            it.close();
                        }
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                } else {
                    this.subscriber.onComplete();
                    if (it != null) {
                        it.close();
                        return;
                    }
                    return;
                }
            } else {
                return;
            }
        }
        throw th;
    }

    public void cancel() {
        this.canceled.compareAndSet(false, true);
    }

    private static long add(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j2 = atomicLong.get();
            j3 = Long.MAX_VALUE;
            if (j2 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            long j4 = j2 + j;
            if (j4 >= 0) {
                j3 = j4;
            }
        } while (!atomicLong.compareAndSet(j2, j3));
        return j2;
    }
}

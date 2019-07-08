package com.uacf.sync.engine;

import android.content.Context;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Holder;
import com.uacf.core.util.Ln;
import com.uacf.sync.engine.UacfSchedulerEngine.Callbacks;
import io.uacf.core.util.ContextUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class UacfSchedulerEngineBase<TScheduleGroup> extends SimpleAsyncServiceBase implements UacfSchedulerEngine<TScheduleGroup> {
    private static final int[] RETRY_DURATIONS = {0, 500, 1000, 2000};
    private static final String WAKE_LOCK_NAME = "SyncServiceWakeLock";
    private Context context;
    protected final UacfSchedulerEngineDelegate<TScheduleGroup> delegate;
    private SerialExecutor executor = new SerialExecutor<>();
    /* access modifiers changed from: private */
    public AtomicBoolean isIdle = new AtomicBoolean(true);
    private final Object schedulerLoopMonitor = new Object();
    /* access modifiers changed from: private */
    public final UacfWakeLockWrapper wakeLock;

    private class SerialExecutor extends SimpleAsyncServiceBase {
        /* access modifiers changed from: private */
        public Future<?> activeTask;
        /* access modifiers changed from: private */
        public LinkedList<SyncDescriptor> queue;

        /* access modifiers changed from: protected */
        public int getMaxThreads() {
            return 1;
        }

        private SerialExecutor() {
            this.queue = new LinkedList<>();
            this.activeTask = null;
        }

        public SyncDescriptor enqueue(TScheduleGroup tschedulegroup, Callbacks<TScheduleGroup> callbacks) {
            Ln.d("enqueuing sync operation of type=%s", tschedulegroup);
            synchronized (this) {
                Iterator it = this.queue.iterator();
                while (it.hasNext()) {
                    SyncDescriptor syncDescriptor = (SyncDescriptor) it.next();
                    if (syncDescriptor.type == tschedulegroup && syncDescriptor.yielded == null) {
                        if (callbacks != null) {
                            synchronized (syncDescriptor.monitor) {
                                syncDescriptor.callbacks.add(callbacks);
                            }
                        }
                        Ln.d("requested type=%s was de-duplicated against syncId=%s", tschedulegroup, syncDescriptor.id);
                        return syncDescriptor;
                    }
                }
                SyncDescriptor syncDescriptor2 = new SyncDescriptor<>();
                syncDescriptor2.type = tschedulegroup;
                syncDescriptor2.id = UUID.randomUUID().toString();
                syncDescriptor2.latch = new CountDownLatch(1);
                syncDescriptor2.callbacks = new ArrayList();
                if (callbacks != null) {
                    syncDescriptor2.callbacks.add(callbacks);
                }
                Ln.d("requested type=%s was added with syncId=%s", tschedulegroup, syncDescriptor2.id);
                this.queue.add(syncDescriptor2);
                if (this.queue.size() == 1) {
                    next();
                }
                UacfSchedulerEngineBase.this.delegate.onSyncEnqueued(new UacfScheduleEnqueuedInfo(syncDescriptor2.id, tschedulegroup));
                return syncDescriptor2;
            }
        }

        public void enqueueYielded(SyncDescriptor syncDescriptor) {
            synchronized (syncDescriptor.monitor) {
                if (syncDescriptor.yielded == null || syncDescriptor.yielded.size() == 0) {
                    throw new IllegalArgumentException("descriptor does not have a yielded set!");
                }
            }
            synchronized (this) {
                this.queue.add(syncDescriptor);
                if (this.queue.size() == 1) {
                    next();
                }
            }
        }

        public synchronized void abort() {
            if (this.activeTask != null) {
                this.activeTask.cancel(true);
                this.activeTask = null;
            }
            this.queue.clear();
        }

        /* access modifiers changed from: private */
        public synchronized void next() {
            if (this.activeTask != null) {
                Ln.d("next() called, but we're already processing! bailing...", new Object[0]);
                return;
            }
            UacfSchedulerEngineBase.this.wakeLock.acquire();
            this.activeTask = async(new Runnable() {
                public void run() {
                    SyncDescriptor syncDescriptor;
                    try {
                        Ln.d("next() called, checking if there's work to process...", new Object[0]);
                        synchronized (SerialExecutor.this) {
                            syncDescriptor = SerialExecutor.this.queue.size() > 0 ? (SyncDescriptor) SerialExecutor.this.queue.remove() : null;
                        }
                        if (syncDescriptor != null) {
                            Ln.d("work found! processing will begin now", new Object[0]);
                            UacfSchedulerEngineBase.this.isIdle.set(false);
                            UacfSchedulerEngineBase.this.process(syncDescriptor);
                        }
                    } catch (Exception e) {
                        Ln.e("unexpected exception running process()", new Object[0]);
                        Ln.e(e);
                    } catch (Throwable th) {
                        synchronized (SerialExecutor.this) {
                            SerialExecutor.this.activeTask = null;
                            if (SerialExecutor.this.queue.size() > 0) {
                                SerialExecutor.this.next();
                            } else {
                                UacfSchedulerEngineBase.this.isIdle.set(true);
                                UacfSchedulerEngineBase.this.wakeLock.release();
                            }
                            throw th;
                        }
                    }
                    synchronized (SerialExecutor.this) {
                        SerialExecutor.this.activeTask = null;
                        if (SerialExecutor.this.queue.size() > 0) {
                            SerialExecutor.this.next();
                        } else {
                            UacfSchedulerEngineBase.this.isIdle.set(true);
                            UacfSchedulerEngineBase.this.wakeLock.release();
                        }
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        public String getThreadName() {
            StringBuilder sb = new StringBuilder();
            sb.append(UacfSchedulerEngineBase.this.getThreadName());
            sb.append(".SerialExecutor");
            return sb.toString();
        }
    }

    private class SyncDescriptor {
        List<Callbacks<TScheduleGroup>> callbacks;
        String id;
        CountDownLatch latch;
        final Object monitor;
        TScheduleGroup type;
        List<UacfScheduleOp> yielded;

        private SyncDescriptor() {
            this.monitor = new Object();
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 3;
    }

    public UacfSchedulerEngineBase(Context context2, UacfSchedulerEngineDelegate<TScheduleGroup> uacfSchedulerEngineDelegate) {
        if (uacfSchedulerEngineDelegate != null) {
            this.context = ContextUtil.getApplicationContextSafe(context2);
            this.delegate = uacfSchedulerEngineDelegate;
            this.wakeLock = new UacfWakeLockWrapper(context2, WAKE_LOCK_NAME);
            return;
        }
        throw new IllegalArgumentException("Delegate may not be null");
    }

    public void abortAndClearQueue() {
        this.executor.abort();
    }

    public boolean isIdle() {
        return this.isIdle.get();
    }

    public String enqueue(TScheduleGroup tschedulegroup) {
        return this.executor.enqueue(tschedulegroup, null).id;
    }

    public String enqueue(TScheduleGroup tschedulegroup, Callbacks<TScheduleGroup> callbacks) {
        return this.executor.enqueue(tschedulegroup, callbacks).id;
    }

    public UacfScheduleFinishedInfo<TScheduleGroup> enqueueAndWait(TScheduleGroup tschedulegroup) {
        final Holder holder = new Holder();
        try {
            this.executor.enqueue(tschedulegroup, new Callbacks<TScheduleGroup>() {
                public void onProgress(UacfScheduleProgressInfo<TScheduleGroup> uacfScheduleProgressInfo) {
                }

                public void onCompleted(UacfScheduleFinishedInfo<TScheduleGroup> uacfScheduleFinishedInfo) {
                    holder.setValue(uacfScheduleFinishedInfo);
                }
            }).latch.await();
            return (UacfScheduleFinishedInfo) holder.getValue();
        } catch (InterruptedException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return getClass().getSimpleName();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01ab, code lost:
        r5 = "sync for type=%s completed. success=%s. yielded=%s";
        r7 = new java.lang.Object[3];
        r7[0] = r2.type;
        r7[1] = java.lang.Boolean.valueOf(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01be, code lost:
        if (r2.yielded != null) goto L_0x01c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01c0, code lost:
        r18 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01c3, code lost:
        r18 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01c5, code lost:
        r7[2] = java.lang.Boolean.valueOf(r18);
        com.uacf.core.util.Ln.d(r5, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01ce, code lost:
        if (r0 != false) goto L_0x01d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01d2, code lost:
        monitor-enter(r2.monitor);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        r0 = r2.callbacks;
        r2.callbacks = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:?, code lost:
        r5 = new com.uacf.sync.engine.UacfScheduleFinishedInfo(r6, r2.type, r4, r9, r10, r11);
        r1.delegate.onSyncFinished(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01eb, code lost:
        if (r0 != null) goto L_0x01ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01ed, code lost:
        r0 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01f5, code lost:
        if (r0.hasNext() != false) goto L_0x01f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01f7, code lost:
        ((com.uacf.sync.engine.UacfSchedulerEngine.Callbacks) r0.next()).onCompleted(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:?, code lost:
        r2.latch.countDown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x020b, code lost:
        r2.latch.countDown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0210, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0211, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0213, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x022f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x02e2, code lost:
        r2.latch.countDown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x02e7, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0 = new com.uacf.sync.engine.UacfSchedulerEngineBase.AnonymousClass2(r1);
        com.uacf.core.util.Ln.d("sync with id=%s has %d operations. outer loop starting..", r2.id, java.lang.Integer.valueOf(r13.size()));
        r12 = r13.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0060, code lost:
        r15 = null;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0066, code lost:
        if (r12.hasNext() == false) goto L_0x0153;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0068, code lost:
        r7 = (com.uacf.sync.engine.UacfScheduleOp) r12.next();
        r16 = r7.getClass().getSimpleName();
        r5 = new java.lang.Object[r10];
        r5[r11] = r16;
        com.uacf.core.util.Ln.d("running inner sync loop for SyncOp=%s", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0081, code lost:
        r5 = false;
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0083, code lost:
        if (r5 != false) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r20 = r12;
        java.lang.Thread.sleep((long) RETRY_DURATIONS[r9]);
        r15 = r7.sync(new com.uacf.sync.engine.UacfScheduleContext(r1.context), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a6, code lost:
        switch(r15.getStatus()) {
            case com.uacf.sync.engine.UacfScheduleOp.Status.Completed :com.uacf.sync.engine.UacfScheduleOp$Status: goto L_0x00e4;
            case com.uacf.sync.engine.UacfScheduleOp.Status.Pending :com.uacf.sync.engine.UacfScheduleOp$Status: goto L_0x00d7;
            case com.uacf.sync.engine.UacfScheduleOp.Status.Yield :com.uacf.sync.engine.UacfScheduleOp$Status: goto L_0x00bf;
            case com.uacf.sync.engine.UacfScheduleOp.Status.Retry :com.uacf.sync.engine.UacfScheduleOp$Status: goto L_0x00aa;
            default: goto L_0x00a9;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00aa, code lost:
        r9 = r9 + 1;
        com.uacf.core.util.Ln.d("SyncOp=%s failed! retry count=%d", r16, java.lang.Integer.valueOf(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00bf, code lost:
        com.uacf.core.util.Ln.d("SyncOp=%s yieled control", r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ca, code lost:
        r11 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r4.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cf, code lost:
        r14 = r11;
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d2, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        com.uacf.core.util.Ln.d("SyncOp=%s finished one step, but still has data pending!", r16);
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e4, code lost:
        com.uacf.core.util.Ln.d("SyncOp=%s completed!", r16);
        r14 = r14 + 1;
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f7, code lost:
        if (r9 < (RETRY_DURATIONS.length - 1)) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f9, code lost:
        com.uacf.core.util.Ln.d("SyncOp=%s failed and all retries exhausted!", r16);
        r7.onRetriesExhausted();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010b, code lost:
        if (r15.getDelayMillis() <= 0) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010d, code lost:
        java.lang.Thread.sleep((long) r15.getDelayMillis());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0115, code lost:
        r12 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x011b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011c, code lost:
        r11 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011d, code lost:
        r9 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        com.uacf.core.util.Ln.e(r0);
        com.uacf.core.util.Ln.e("SyncOp=%s failed with UNEXPECTED EXCEPTION %s", r16, r0.getClass().getSimpleName());
        r1.delegate.onSyncFailed(new com.uacf.sync.engine.UacfScheduleFailedInfo(r6, r2.type, r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0142, code lost:
        r15 = r9;
        r14 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0145, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0146, code lost:
        r15 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0147, code lost:
        r14 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x014a, code lost:
        r20 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x014c, code lost:
        r12 = r20;
        r10 = 1;
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0153, code lost:
        if (r13 != null) goto L_0x0155;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0159, code lost:
        if (r14 == r13.size()) goto L_0x015b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x015b, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x015d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0160, code lost:
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0161, code lost:
        if (r15 == null) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0163, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0165, code lost:
        r0 = r15.getException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0169, code lost:
        if (r9 == false) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x016b, code lost:
        if (r0 != null) goto L_0x016d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x016d, code lost:
        r7 = r0.getStatusCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0172, code lost:
        r7 = 557;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0174, code lost:
        if (r0 != null) goto L_0x0176;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0176, code lost:
        r4 = r0.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x017b, code lost:
        r4 = "UNKNOWN ERROR";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x017d, code lost:
        if (r0 == null) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x017f, code lost:
        r11 = r4;
        r10 = r7;
        r4 = new com.uacf.sync.engine.UacfScheduleException.UacfScheduleFailedException(r7, r4);
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0189, code lost:
        r11 = r4;
        r10 = r7;
        r4 = r0;
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0192, code lost:
        if (r4.size() > 0) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0196, code lost:
        monitor-enter(r2.monitor);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        r2.yielded = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:?, code lost:
        r1.executor.enqueueYielded(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x019f, code lost:
        r4 = r0;
        r0 = false;
        r10 = 0;
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01a7, code lost:
        r4 = r0;
        r0 = true;
        r10 = 0;
        r11 = null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:64:0x0155, B:146:0x0227] */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01c0 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x01c3 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01d0 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0227 A[SYNTHETIC, Splitter:B:146:0x0227] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0235 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0237 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x023d A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0262 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x0297 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0298 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x02a5 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0155 A[SYNTHETIC, Splitter:B:64:0x0155] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0163 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0165 A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x016b A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x018e A[Catch:{ all -> 0x022f, all -> 0x015d }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:123:0x0204=Splitter:B:123:0x0204, B:205:0x02dc=Splitter:B:205:0x02dc} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void process(com.uacf.sync.engine.UacfSchedulerEngineBase.SyncDescriptor r22) {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            java.lang.Object r3 = r1.schedulerLoopMonitor
            monitor-enter(r3)
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x02e8 }
            r4.<init>()     // Catch:{ all -> 0x02e8 }
            java.lang.String r6 = r2.id     // Catch:{ all -> 0x02e8 }
            r8 = 2
            r9 = 0
            r10 = 1
            r11 = 0
            com.uacf.sync.engine.UacfSchedulerEngineDelegate<TScheduleGroup> r0 = r1.delegate     // Catch:{ all -> 0x0221 }
            com.uacf.sync.engine.UacfScheduleStartedInfo r12 = new com.uacf.sync.engine.UacfScheduleStartedInfo     // Catch:{ all -> 0x0221 }
            java.lang.String r13 = r2.id     // Catch:{ all -> 0x0221 }
            TScheduleGroup r14 = r2.type     // Catch:{ all -> 0x0221 }
            r12.<init>(r13, r14)     // Catch:{ all -> 0x0221 }
            r0.onSyncStarted(r12)     // Catch:{ all -> 0x0221 }
            java.lang.String r0 = "processing sync with id=%s"
            java.lang.Object[] r12 = new java.lang.Object[r10]     // Catch:{ all -> 0x0221 }
            java.lang.String r13 = r2.id     // Catch:{ all -> 0x0221 }
            r12[r11] = r13     // Catch:{ all -> 0x0221 }
            com.uacf.core.util.Ln.d(r0, r12)     // Catch:{ all -> 0x0221 }
            java.lang.Object r12 = r2.monitor     // Catch:{ all -> 0x0221 }
            monitor-enter(r12)     // Catch:{ all -> 0x0221 }
            java.util.List<com.uacf.sync.engine.UacfScheduleOp> r0 = r2.yielded     // Catch:{ all -> 0x0218 }
            if (r0 == 0) goto L_0x0036
            java.util.List<com.uacf.sync.engine.UacfScheduleOp> r0 = r2.yielded     // Catch:{ all -> 0x021f }
            r13 = r0
            goto L_0x003f
        L_0x0036:
            com.uacf.sync.engine.UacfSchedulerEngineDelegate<TScheduleGroup> r0 = r1.delegate     // Catch:{ all -> 0x0218 }
            TScheduleGroup r13 = r2.type     // Catch:{ all -> 0x0218 }
            java.util.List r0 = r0.getSyncOpsForType(r13)     // Catch:{ all -> 0x0218 }
            r13 = r0
        L_0x003f:
            r2.yielded = r9     // Catch:{ all -> 0x0215 }
            monitor-exit(r12)     // Catch:{ all -> 0x0215 }
            com.uacf.sync.engine.UacfSchedulerEngineBase$2 r0 = new com.uacf.sync.engine.UacfSchedulerEngineBase$2     // Catch:{ all -> 0x0213 }
            r0.<init>(r2)     // Catch:{ all -> 0x0213 }
            java.lang.String r12 = "sync with id=%s has %d operations. outer loop starting.."
            java.lang.Object[] r14 = new java.lang.Object[r8]     // Catch:{ all -> 0x0213 }
            java.lang.String r15 = r2.id     // Catch:{ all -> 0x0213 }
            r14[r11] = r15     // Catch:{ all -> 0x0213 }
            int r15 = r13.size()     // Catch:{ all -> 0x0213 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x0213 }
            r14[r10] = r15     // Catch:{ all -> 0x0213 }
            com.uacf.core.util.Ln.d(r12, r14)     // Catch:{ all -> 0x0213 }
            java.util.Iterator r12 = r13.iterator()     // Catch:{ all -> 0x0213 }
            r15 = r9
            r14 = 0
        L_0x0062:
            boolean r16 = r12.hasNext()     // Catch:{ all -> 0x0211 }
            if (r16 == 0) goto L_0x0153
            java.lang.Object r16 = r12.next()     // Catch:{ all -> 0x0211 }
            r7 = r16
            com.uacf.sync.engine.UacfScheduleOp r7 = (com.uacf.sync.engine.UacfScheduleOp) r7     // Catch:{ all -> 0x0211 }
            java.lang.Class r16 = r7.getClass()     // Catch:{ all -> 0x0211 }
            java.lang.String r16 = r16.getSimpleName()     // Catch:{ all -> 0x0211 }
            java.lang.String r9 = "running inner sync loop for SyncOp=%s"
            java.lang.Object[] r5 = new java.lang.Object[r10]     // Catch:{ all -> 0x0211 }
            r5[r11] = r16     // Catch:{ all -> 0x0211 }
            com.uacf.core.util.Ln.d(r9, r5)     // Catch:{ all -> 0x0211 }
            r5 = 0
            r9 = 0
        L_0x0083:
            if (r5 != 0) goto L_0x014a
            int[] r18 = RETRY_DURATIONS     // Catch:{ Exception -> 0x011b }
            r10 = r18[r9]     // Catch:{ Exception -> 0x011b }
            r20 = r12
            long r11 = (long) r10     // Catch:{ Exception -> 0x011b }
            java.lang.Thread.sleep(r11)     // Catch:{ Exception -> 0x011b }
            com.uacf.sync.engine.UacfScheduleContext r10 = new com.uacf.sync.engine.UacfScheduleContext     // Catch:{ Exception -> 0x011b }
            android.content.Context r11 = r1.context     // Catch:{ Exception -> 0x011b }
            r10.<init>(r11)     // Catch:{ Exception -> 0x011b }
            com.uacf.sync.engine.UacfScheduleOp$Result r15 = r7.sync(r10, r0)     // Catch:{ Exception -> 0x011b }
            int[] r10 = com.uacf.sync.engine.UacfSchedulerEngineBase.AnonymousClass3.$SwitchMap$com$uacf$sync$engine$UacfScheduleOp$Status     // Catch:{ Exception -> 0x011b }
            com.uacf.sync.engine.UacfScheduleOp$Status r11 = r15.getStatus()     // Catch:{ Exception -> 0x011b }
            int r11 = r11.ordinal()     // Catch:{ Exception -> 0x011b }
            r10 = r10[r11]     // Catch:{ Exception -> 0x011b }
            switch(r10) {
                case 1: goto L_0x00e4;
                case 2: goto L_0x00d7;
                case 3: goto L_0x00bf;
                case 4: goto L_0x00aa;
                default: goto L_0x00a9;
            }     // Catch:{ Exception -> 0x011b }
        L_0x00a9:
            goto L_0x00f2
        L_0x00aa:
            int r9 = r9 + 1
            java.lang.String r10 = "SyncOp=%s failed! retry count=%d"
            java.lang.Object[] r11 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x011b }
            r12 = 0
            r11[r12] = r16     // Catch:{ Exception -> 0x011b }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x011b }
            r19 = 1
            r11[r19] = r12     // Catch:{ Exception -> 0x011b }
            com.uacf.core.util.Ln.d(r10, r11)     // Catch:{ Exception -> 0x011b }
            goto L_0x00f2
        L_0x00bf:
            java.lang.String r5 = "SyncOp=%s yieled control"
            r10 = 1
            java.lang.Object[] r11 = new java.lang.Object[r10]     // Catch:{ Exception -> 0x011b }
            r10 = 0
            r11[r10] = r16     // Catch:{ Exception -> 0x011b }
            com.uacf.core.util.Ln.d(r5, r11)     // Catch:{ Exception -> 0x011b }
            int r11 = r14 + 1
            r4.add(r7)     // Catch:{ Exception -> 0x00d5, all -> 0x00d2 }
            r14 = r11
            r5 = 1
            goto L_0x00f2
        L_0x00d2:
            r0 = move-exception
            goto L_0x0147
        L_0x00d5:
            r0 = move-exception
            goto L_0x011d
        L_0x00d7:
            java.lang.String r9 = "SyncOp=%s finished one step, but still has data pending!"
            r10 = 1
            java.lang.Object[] r11 = new java.lang.Object[r10]     // Catch:{ Exception -> 0x011b }
            r10 = 0
            r11[r10] = r16     // Catch:{ Exception -> 0x011b }
            com.uacf.core.util.Ln.d(r9, r11)     // Catch:{ Exception -> 0x011b }
            r9 = 0
            goto L_0x00f2
        L_0x00e4:
            java.lang.String r5 = "SyncOp=%s completed!"
            r10 = 1
            java.lang.Object[] r11 = new java.lang.Object[r10]     // Catch:{ Exception -> 0x011b }
            r10 = 0
            r11[r10] = r16     // Catch:{ Exception -> 0x011b }
            com.uacf.core.util.Ln.d(r5, r11)     // Catch:{ Exception -> 0x011b }
            int r14 = r14 + 1
            r5 = 1
        L_0x00f2:
            int[] r10 = RETRY_DURATIONS     // Catch:{ Exception -> 0x011b }
            int r10 = r10.length     // Catch:{ Exception -> 0x011b }
            r11 = 1
            int r10 = r10 - r11
            if (r9 < r10) goto L_0x0107
            java.lang.String r5 = "SyncOp=%s failed and all retries exhausted!"
            java.lang.Object[] r9 = new java.lang.Object[r11]     // Catch:{ Exception -> 0x011b }
            r10 = 0
            r9[r10] = r16     // Catch:{ Exception -> 0x011b }
            com.uacf.core.util.Ln.d(r5, r9)     // Catch:{ Exception -> 0x011b }
            r7.onRetriesExhausted()     // Catch:{ Exception -> 0x011b }
            goto L_0x014c
        L_0x0107:
            int r10 = r15.getDelayMillis()     // Catch:{ Exception -> 0x011b }
            if (r10 <= 0) goto L_0x0115
            int r10 = r15.getDelayMillis()     // Catch:{ Exception -> 0x011b }
            long r10 = (long) r10     // Catch:{ Exception -> 0x011b }
            java.lang.Thread.sleep(r10)     // Catch:{ Exception -> 0x011b }
        L_0x0115:
            r12 = r20
            r10 = 1
            r11 = 0
            goto L_0x0083
        L_0x011b:
            r0 = move-exception
            r11 = r14
        L_0x011d:
            r9 = r15
            com.uacf.core.util.Ln.e(r0)     // Catch:{ all -> 0x0145 }
            java.lang.String r5 = "SyncOp=%s failed with UNEXPECTED EXCEPTION %s"
            java.lang.Object[] r7 = new java.lang.Object[r8]     // Catch:{ all -> 0x0145 }
            r10 = 0
            r7[r10] = r16     // Catch:{ all -> 0x0145 }
            java.lang.Class r10 = r0.getClass()     // Catch:{ all -> 0x0145 }
            java.lang.String r10 = r10.getSimpleName()     // Catch:{ all -> 0x0145 }
            r12 = 1
            r7[r12] = r10     // Catch:{ all -> 0x0145 }
            com.uacf.core.util.Ln.e(r5, r7)     // Catch:{ all -> 0x0145 }
            com.uacf.sync.engine.UacfSchedulerEngineDelegate<TScheduleGroup> r5 = r1.delegate     // Catch:{ all -> 0x0145 }
            com.uacf.sync.engine.UacfScheduleFailedInfo r7 = new com.uacf.sync.engine.UacfScheduleFailedInfo     // Catch:{ all -> 0x0145 }
            TScheduleGroup r10 = r2.type     // Catch:{ all -> 0x0145 }
            r7.<init>(r6, r10, r0)     // Catch:{ all -> 0x0145 }
            r5.onSyncFailed(r7)     // Catch:{ all -> 0x0145 }
            r15 = r9
            r14 = r11
            goto L_0x0153
        L_0x0145:
            r0 = move-exception
            r15 = r9
        L_0x0147:
            r14 = r11
            goto L_0x0225
        L_0x014a:
            r20 = r12
        L_0x014c:
            r12 = r20
            r9 = 0
            r10 = 1
            r11 = 0
            goto L_0x0062
        L_0x0153:
            if (r13 == 0) goto L_0x0160
            int r0 = r13.size()     // Catch:{ all -> 0x015d }
            if (r14 != r0) goto L_0x0160
            r9 = 1
            goto L_0x0161
        L_0x015d:
            r0 = move-exception
            goto L_0x020b
        L_0x0160:
            r9 = 0
        L_0x0161:
            if (r15 != 0) goto L_0x0165
            r0 = 0
            goto L_0x0169
        L_0x0165:
            com.uacf.sync.engine.UacfScheduleException r0 = r15.getException()     // Catch:{ all -> 0x015d }
        L_0x0169:
            if (r9 != 0) goto L_0x018e
            if (r0 == 0) goto L_0x0172
            int r7 = r0.getStatusCode()     // Catch:{ all -> 0x015d }
            goto L_0x0174
        L_0x0172:
            r7 = 557(0x22d, float:7.8E-43)
        L_0x0174:
            if (r0 == 0) goto L_0x017b
            java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x015d }
            goto L_0x017d
        L_0x017b:
            java.lang.String r4 = "UNKNOWN ERROR"
        L_0x017d:
            if (r0 != 0) goto L_0x0189
            com.uacf.sync.engine.UacfScheduleException$UacfScheduleFailedException r0 = new com.uacf.sync.engine.UacfScheduleException$UacfScheduleFailedException     // Catch:{ all -> 0x015d }
            r0.<init>(r7, r4)     // Catch:{ all -> 0x015d }
            r11 = r4
            r10 = r7
            r4 = r0
            r0 = 1
            goto L_0x01ab
        L_0x0189:
            r11 = r4
            r10 = r7
            r4 = r0
            r0 = 1
            goto L_0x01ab
        L_0x018e:
            int r5 = r4.size()     // Catch:{ all -> 0x015d }
            if (r5 <= 0) goto L_0x01a7
            java.lang.Object r5 = r2.monitor     // Catch:{ all -> 0x015d }
            monitor-enter(r5)     // Catch:{ all -> 0x015d }
            r2.yielded = r4     // Catch:{ all -> 0x01a4 }
            monitor-exit(r5)     // Catch:{ all -> 0x01a4 }
            com.uacf.sync.engine.UacfSchedulerEngineBase$SerialExecutor<> r4 = r1.executor     // Catch:{ all -> 0x015d }
            r4.enqueueYielded(r2)     // Catch:{ all -> 0x015d }
            r4 = r0
            r0 = 0
            r10 = 0
            r11 = 0
            goto L_0x01ab
        L_0x01a4:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x01a4 }
            throw r0     // Catch:{ all -> 0x015d }
        L_0x01a7:
            r4 = r0
            r0 = 1
            r10 = 0
            r11 = 0
        L_0x01ab:
            java.lang.String r5 = "sync for type=%s completed. success=%s. yielded=%s"
            r7 = 3
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x015d }
            TScheduleGroup r12 = r2.type     // Catch:{ all -> 0x015d }
            r13 = 0
            r7[r13] = r12     // Catch:{ all -> 0x015d }
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r9)     // Catch:{ all -> 0x015d }
            r13 = 1
            r7[r13] = r12     // Catch:{ all -> 0x015d }
            java.util.List<com.uacf.sync.engine.UacfScheduleOp> r12 = r2.yielded     // Catch:{ all -> 0x015d }
            if (r12 == 0) goto L_0x01c3
            r18 = 1
            goto L_0x01c5
        L_0x01c3:
            r18 = 0
        L_0x01c5:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r18)     // Catch:{ all -> 0x015d }
            r7[r8] = r12     // Catch:{ all -> 0x015d }
            com.uacf.core.util.Ln.d(r5, r7)     // Catch:{ all -> 0x015d }
            if (r0 == 0) goto L_0x0204
            java.lang.Object r5 = r2.monitor     // Catch:{ all -> 0x015d }
            monitor-enter(r5)     // Catch:{ all -> 0x015d }
            java.util.List<com.uacf.sync.engine.UacfSchedulerEngine$Callbacks<TScheduleGroup>> r0 = r2.callbacks     // Catch:{ all -> 0x0201 }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x0201 }
            r7.<init>()     // Catch:{ all -> 0x0201 }
            r2.callbacks = r7     // Catch:{ all -> 0x0201 }
            monitor-exit(r5)     // Catch:{ all -> 0x0201 }
            com.uacf.sync.engine.UacfScheduleFinishedInfo r12 = new com.uacf.sync.engine.UacfScheduleFinishedInfo     // Catch:{ all -> 0x015d }
            TScheduleGroup r7 = r2.type     // Catch:{ all -> 0x015d }
            r5 = r12
            r8 = r4
            r5.<init>(r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x015d }
            com.uacf.sync.engine.UacfSchedulerEngineDelegate<TScheduleGroup> r4 = r1.delegate     // Catch:{ all -> 0x015d }
            r4.onSyncFinished(r12)     // Catch:{ all -> 0x015d }
            if (r0 == 0) goto L_0x0204
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x015d }
        L_0x01f1:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x015d }
            if (r4 == 0) goto L_0x0204
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x015d }
            com.uacf.sync.engine.UacfSchedulerEngine$Callbacks r4 = (com.uacf.sync.engine.UacfSchedulerEngine.Callbacks) r4     // Catch:{ all -> 0x015d }
            r4.onCompleted(r12)     // Catch:{ all -> 0x015d }
            goto L_0x01f1
        L_0x0201:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0201 }
            throw r0     // Catch:{ all -> 0x015d }
        L_0x0204:
            java.util.concurrent.CountDownLatch r0 = r2.latch     // Catch:{ all -> 0x02e8 }
            r0.countDown()     // Catch:{ all -> 0x02e8 }
            monitor-exit(r3)     // Catch:{ all -> 0x02e8 }
            return
        L_0x020b:
            java.util.concurrent.CountDownLatch r2 = r2.latch     // Catch:{ all -> 0x02e8 }
            r2.countDown()     // Catch:{ all -> 0x02e8 }
            throw r0     // Catch:{ all -> 0x02e8 }
        L_0x0211:
            r0 = move-exception
            goto L_0x0225
        L_0x0213:
            r0 = move-exception
            goto L_0x0223
        L_0x0215:
            r0 = move-exception
            r9 = r13
            goto L_0x021a
        L_0x0218:
            r0 = move-exception
            r9 = 0
        L_0x021a:
            monitor-exit(r12)     // Catch:{ all -> 0x021f }
            throw r0     // Catch:{ all -> 0x021c }
        L_0x021c:
            r0 = move-exception
            r13 = r9
            goto L_0x0223
        L_0x021f:
            r0 = move-exception
            goto L_0x021a
        L_0x0221:
            r0 = move-exception
            r13 = 0
        L_0x0223:
            r14 = 0
            r15 = 0
        L_0x0225:
            if (r13 == 0) goto L_0x0232
            int r5 = r13.size()     // Catch:{ all -> 0x022f }
            if (r14 != r5) goto L_0x0232
            r9 = 1
            goto L_0x0233
        L_0x022f:
            r0 = move-exception
            goto L_0x02e2
        L_0x0232:
            r9 = 0
        L_0x0233:
            if (r15 != 0) goto L_0x0237
            r5 = 0
            goto L_0x023b
        L_0x0237:
            com.uacf.sync.engine.UacfScheduleException r5 = r15.getException()     // Catch:{ all -> 0x022f }
        L_0x023b:
            if (r9 != 0) goto L_0x0262
            if (r5 == 0) goto L_0x0244
            int r7 = r5.getStatusCode()     // Catch:{ all -> 0x022f }
            goto L_0x0246
        L_0x0244:
            r7 = 557(0x22d, float:7.8E-43)
        L_0x0246:
            if (r5 == 0) goto L_0x024d
            java.lang.String r4 = r5.getMessage()     // Catch:{ all -> 0x022f }
            goto L_0x024f
        L_0x024d:
            java.lang.String r4 = "UNKNOWN ERROR"
        L_0x024f:
            if (r5 != 0) goto L_0x025c
            com.uacf.sync.engine.UacfScheduleException$UacfScheduleFailedException r5 = new com.uacf.sync.engine.UacfScheduleException$UacfScheduleFailedException     // Catch:{ all -> 0x022f }
            r5.<init>(r7, r4)     // Catch:{ all -> 0x022f }
            r17 = r4
            r10 = r5
            r11 = r7
            r4 = 1
            goto L_0x0281
        L_0x025c:
            r17 = r4
            r10 = r5
            r11 = r7
            r4 = 1
            goto L_0x0281
        L_0x0262:
            int r7 = r4.size()     // Catch:{ all -> 0x022f }
            if (r7 <= 0) goto L_0x027c
            java.lang.Object r7 = r2.monitor     // Catch:{ all -> 0x022f }
            monitor-enter(r7)     // Catch:{ all -> 0x022f }
            r2.yielded = r4     // Catch:{ all -> 0x0279 }
            monitor-exit(r7)     // Catch:{ all -> 0x0279 }
            com.uacf.sync.engine.UacfSchedulerEngineBase$SerialExecutor<> r4 = r1.executor     // Catch:{ all -> 0x022f }
            r4.enqueueYielded(r2)     // Catch:{ all -> 0x022f }
            r10 = r5
            r4 = 0
            r11 = 0
            r17 = 0
            goto L_0x0281
        L_0x0279:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0279 }
            throw r0     // Catch:{ all -> 0x022f }
        L_0x027c:
            r10 = r5
            r4 = 1
            r11 = 0
            r17 = 0
        L_0x0281:
            java.lang.String r5 = "sync for type=%s completed. success=%s. yielded=%s"
            r7 = 3
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x022f }
            TScheduleGroup r12 = r2.type     // Catch:{ all -> 0x022f }
            r13 = 0
            r7[r13] = r12     // Catch:{ all -> 0x022f }
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r9)     // Catch:{ all -> 0x022f }
            r18 = 1
            r7[r18] = r12     // Catch:{ all -> 0x022f }
            java.util.List<com.uacf.sync.engine.UacfScheduleOp> r12 = r2.yielded     // Catch:{ all -> 0x022f }
            if (r12 == 0) goto L_0x0298
            goto L_0x029a
        L_0x0298:
            r18 = 0
        L_0x029a:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r18)     // Catch:{ all -> 0x022f }
            r7[r8] = r12     // Catch:{ all -> 0x022f }
            com.uacf.core.util.Ln.d(r5, r7)     // Catch:{ all -> 0x022f }
            if (r4 == 0) goto L_0x02dc
            java.lang.Object r4 = r2.monitor     // Catch:{ all -> 0x022f }
            monitor-enter(r4)     // Catch:{ all -> 0x022f }
            java.util.List<com.uacf.sync.engine.UacfSchedulerEngine$Callbacks<TScheduleGroup>> r12 = r2.callbacks     // Catch:{ all -> 0x02d9 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x02d9 }
            r5.<init>()     // Catch:{ all -> 0x02d9 }
            r2.callbacks = r5     // Catch:{ all -> 0x02d9 }
            monitor-exit(r4)     // Catch:{ all -> 0x02d9 }
            com.uacf.sync.engine.UacfScheduleFinishedInfo r4 = new com.uacf.sync.engine.UacfScheduleFinishedInfo     // Catch:{ all -> 0x022f }
            TScheduleGroup r7 = r2.type     // Catch:{ all -> 0x022f }
            r5 = r4
            r8 = r10
            r10 = r11
            r11 = r17
            r5.<init>(r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x022f }
            com.uacf.sync.engine.UacfSchedulerEngineDelegate<TScheduleGroup> r5 = r1.delegate     // Catch:{ all -> 0x022f }
            r5.onSyncFinished(r4)     // Catch:{ all -> 0x022f }
            if (r12 == 0) goto L_0x02dc
            java.util.Iterator r5 = r12.iterator()     // Catch:{ all -> 0x022f }
        L_0x02c9:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x022f }
            if (r6 == 0) goto L_0x02dc
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x022f }
            com.uacf.sync.engine.UacfSchedulerEngine$Callbacks r6 = (com.uacf.sync.engine.UacfSchedulerEngine.Callbacks) r6     // Catch:{ all -> 0x022f }
            r6.onCompleted(r4)     // Catch:{ all -> 0x022f }
            goto L_0x02c9
        L_0x02d9:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x02d9 }
            throw r0     // Catch:{ all -> 0x022f }
        L_0x02dc:
            java.util.concurrent.CountDownLatch r2 = r2.latch     // Catch:{ all -> 0x02e8 }
            r2.countDown()     // Catch:{ all -> 0x02e8 }
            throw r0     // Catch:{ all -> 0x02e8 }
        L_0x02e2:
            java.util.concurrent.CountDownLatch r2 = r2.latch     // Catch:{ all -> 0x02e8 }
            r2.countDown()     // Catch:{ all -> 0x02e8 }
            throw r0     // Catch:{ all -> 0x02e8 }
        L_0x02e8:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x02e8 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.sync.engine.UacfSchedulerEngineBase.process(com.uacf.sync.engine.UacfSchedulerEngineBase$SyncDescriptor):void");
    }
}

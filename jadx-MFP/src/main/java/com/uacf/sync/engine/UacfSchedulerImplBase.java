package com.uacf.sync.engine;

import com.uacf.core.util.Debouncer;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UacfSchedulerImplBase<TScheduleGroup> implements UacfScheduler<TScheduleGroup> {
    private Debouncer<TScheduleGroup> debouncer;
    protected final UacfSchedulerDelegate<TScheduleGroup> delegate;
    private boolean forcePeriodicSchedule;
    /* access modifiers changed from: private */
    public final Object internalStateLock = new Object();
    /* access modifiers changed from: private */
    public long lastPeriodicScheduleTime = 0;
    /* access modifiers changed from: private */
    public Set<TScheduleGroup> pending = new HashSet();

    public UacfSchedulerImplBase(UacfSchedulerDelegate<TScheduleGroup> uacfSchedulerDelegate) {
        this.delegate = uacfSchedulerDelegate;
        createDebouncer();
    }

    public void debounceDefaultSync() {
        debounceSyncTypes(this.delegate.getDefaultSyncTypes());
    }

    public void debounceSyncTypes(TScheduleGroup... tschedulegroupArr) {
        synchronized (this.internalStateLock) {
            this.pending.addAll(Arrays.asList(tschedulegroupArr));
        }
        Ln.d("DEBOUNCE: called with types %s, new pending list is %s", Strings.toString(tschedulegroupArr), Strings.toString(this.pending));
        this.debouncer.call();
    }

    public void schedulePeriodicSyncIfNecessary() {
        long periodicSyncTime = this.delegate.getPeriodicSyncTime();
        if (periodicSyncTime >= 0) {
            synchronized (this.internalStateLock) {
                long currentTimeMillis = System.currentTimeMillis() - this.lastPeriodicScheduleTime;
                Ln.d("DEBOUNCE: schedulePeriodicSyncIfNecessary elapsed=%d forcePeriodicSchedule=%s", Long.valueOf(currentTimeMillis), Boolean.valueOf(this.forcePeriodicSchedule));
                if (currentTimeMillis > periodicSyncTime || this.forcePeriodicSchedule) {
                    Ln.e("DEBOUNCE: schedulePeriodicSyncIfNecessary calling debounceDefaultSync()!", new Object[0]);
                    this.forcePeriodicSchedule = false;
                    debounceDefaultSync();
                }
            }
        }
    }

    private void createDebouncer() {
        this.debouncer = new Debouncer<TScheduleGroup>(this.delegate.getSyncDebounceTimeout()) {
            /* access modifiers changed from: protected */
            public void onDebounced(TScheduleGroup tschedulegroup) {
                Set access$100;
                Ln.e("onDebounced() called", new Object[0]);
                HashSet hashSet = new HashSet();
                synchronized (UacfSchedulerImplBase.this.internalStateLock) {
                    access$100 = UacfSchedulerImplBase.this.pending;
                    UacfSchedulerImplBase.this.pending = new HashSet();
                }
                for (Object next : access$100) {
                    if (UacfSchedulerImplBase.this.delegate.requiresReschedule(next)) {
                        hashSet.add(next);
                    } else {
                        if (UacfSchedulerImplBase.this.delegate.shouldResetLastPeriodicSyncTime(next)) {
                            UacfSchedulerImplBase.this.lastPeriodicScheduleTime = System.currentTimeMillis();
                        }
                        UacfSchedulerImplBase.this.delegate.getSyncEngine().enqueue(next);
                    }
                }
                synchronized (UacfSchedulerImplBase.this.internalStateLock) {
                    UacfSchedulerImplBase.this.pending.addAll(hashSet);
                }
            }
        };
    }
}

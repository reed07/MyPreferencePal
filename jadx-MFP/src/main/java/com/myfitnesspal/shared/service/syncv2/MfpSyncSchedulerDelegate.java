package com.myfitnesspal.shared.service.syncv2;

import com.myfitnesspal.shared.service.session.Session;
import com.uacf.sync.engine.UacfSchedulerDelegate;
import dagger.Lazy;

public class MfpSyncSchedulerDelegate implements UacfSchedulerDelegate<SyncType> {
    private static final long ACTIVITY_RESUME_SYNC_PERIOD_MILLIS = 900000;
    private static final int SYNC_DEBOUNCE_TIMEOUT_MILLIS = 5000;
    final Lazy<Session> session;
    private final Lazy<SyncService> syncService;

    public long getPeriodicSyncTime() {
        return ACTIVITY_RESUME_SYNC_PERIOD_MILLIS;
    }

    public int getSyncDebounceTimeout() {
        return 5000;
    }

    public MfpSyncSchedulerDelegate(Lazy<Session> lazy, Lazy<SyncService> lazy2) {
        this.session = lazy;
        this.syncService = lazy2;
    }

    public boolean shouldResetLastPeriodicSyncTime(SyncType syncType) {
        return syncType == SyncType.Incremental;
    }

    public boolean requiresReschedule(SyncType syncType) {
        return !((Session) this.session.get()).getUser().isLoggedIn() && syncType.requiresThatUserIsLoggedIn();
    }

    public SyncService getSyncEngine() {
        return (SyncService) this.syncService.get();
    }

    public SyncType[] getDefaultSyncTypes() {
        return new SyncType[]{SyncType.Incremental};
    }
}

package com.myfitnesspal.shared.service.syncv2;

import android.content.Context;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.uacf.sync.engine.UacfSchedulerEngineBase;
import com.uacf.sync.engine.UacfSchedulerEngineDelegate;
import dagger.Lazy;
import java.util.Date;

public class SyncServiceImpl extends UacfSchedulerEngineBase<SyncType> implements SyncService {
    private final Lazy<Bus> messageBus;

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "SyncServiceImpl.SerialExecutor";
    }

    public SyncServiceImpl(Context context, UacfSchedulerEngineDelegate<SyncType> uacfSchedulerEngineDelegate, Lazy<Bus> lazy) {
        super(context, uacfSchedulerEngineDelegate);
        this.messageBus = lazy;
        ((Bus) this.messageBus.get()).register(this);
    }

    @Subscribe
    public void onStartSyncEvent(StartSyncEvent startSyncEvent) {
        enqueue(startSyncEvent.getScheduleType());
    }

    public Date getLastSyncDate() {
        return ((MfpSyncEngineDelegate) this.delegate).getLastSyncDate();
    }
}

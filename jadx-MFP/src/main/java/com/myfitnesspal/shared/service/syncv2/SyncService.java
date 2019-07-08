package com.myfitnesspal.shared.service.syncv2;

import com.uacf.sync.engine.UacfSchedulerEngine;
import java.util.Date;

public interface SyncService extends UacfSchedulerEngine<SyncType> {
    Date getLastSyncDate();
}

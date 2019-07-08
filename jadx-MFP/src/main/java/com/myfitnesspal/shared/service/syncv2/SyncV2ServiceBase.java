package com.myfitnesspal.shared.service.syncv2;

import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Function2;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;

public abstract class SyncV2ServiceBase<T> extends SimpleAsyncServiceBase implements SyncItemHandler<T> {
    public void publishUnsyncedItems(Function2<Integer, Integer> function2) throws UacfScheduleException {
    }
}

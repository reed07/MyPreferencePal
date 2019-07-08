package com.uacf.sync.provider.sdk.model;

import com.uacf.core.util.Function2;
import com.uacf.sync.engine.UacfScheduleException;
import java.util.List;

public interface SyncItemHandler<T> {
    void consumeSyncItems(List<SyncItem<T>> list);

    Class<?> getSyncItemClass();

    String getSyncResourceName();

    void publishUnsyncedItems(Function2<Integer, Integer> function2) throws UacfScheduleException;
}

package com.myfitnesspal.shared.service.syncv2;

import com.uacf.sync.provider.sdk.model.SyncMode;
import java.util.Collection;

public interface SyncUtil {

    public static final class FinalSyncState {
        public static final int JUST_COMPLETED = 2;
        public static final int PENDING = 1;
    }

    Collection<String> getAllSyncV2EnabledResources();

    String getResourceNameForRollout(String str);

    Collection<String> getResourcesWithFinalState(int i);

    String getRolloutForResourceName(String str);

    Collection<String> getSyncV2ResourcesWhoseRolloutsAreOn();

    boolean hasInitialSyncV2Completed();

    void migrateDataForSyncV2();

    void migrateRemindersData();

    void migrateResourceState(int i, int i2);

    void migrateStepsData();

    void purgeStateForV2ImportMode(SyncMode syncMode);

    void removeFinalSyncStateForResources(Collection<String> collection);

    void setFinalSyncStateForResources(Collection<String> collection, int i);

    void setInitialSyncV2Completed(boolean z);

    void setUserRefreshed();

    boolean userRefreshRequired();
}

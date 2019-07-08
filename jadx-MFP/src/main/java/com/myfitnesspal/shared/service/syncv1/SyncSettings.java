package com.myfitnesspal.shared.service.syncv1;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.constants.SharedConstants.Settings.Sync;
import javax.inject.Inject;
import javax.inject.Named;

public final class SyncSettings {
    private final SharedPreferences prefs;

    @Inject
    public SyncSettings(@Named("sync-settings") SharedPreferences sharedPreferences) {
        this.prefs = sharedPreferences;
    }

    public long getMasterIdOfMostRecentThirdPartyAccountAddOrDelete() {
        return this.prefs.getLong(Sync.MASTER_ID_FOR_MOST_RECENT_THIRD_PARTY_ADD_OR_DELETE, 0);
    }

    public void setMasterIdOfMostRecentThirdPartyAccountAddOrDelete(long j) {
        this.prefs.edit().putLong(Sync.MASTER_ID_FOR_MOST_RECENT_THIRD_PARTY_ADD_OR_DELETE, j).commit();
    }
}

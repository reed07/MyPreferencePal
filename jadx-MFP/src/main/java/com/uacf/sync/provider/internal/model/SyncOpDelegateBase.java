package com.uacf.sync.provider.internal.model;

import com.uacf.sync.provider.sdk.model.SyncModeImport;

public abstract class SyncOpDelegateBase implements SyncOpDelegate {
    public void onBeforeFetch() {
    }

    public void onBeforePublish() {
    }

    public void onImportComplete() {
    }

    public void onPrepareToSync() {
    }

    public void onSyncTokenExpired() {
    }

    public void expireSyncToken() {
        getPrefs().edit().putString(new SyncModeSync().getTokenPrefsKey(), "s1p69aR4A3Q2gb80bFH-Plg0ZHAqOttsLYe5TLBSS6vz86pR9Gm7UurHnU72NSKboJVKf2-aIgBmuEEAJOtPmh7RrsLyxx4gwzdRqmNgOrk").apply();
    }

    public void removeSyncToken() {
        getPrefs().edit().remove(new SyncModeSync().getTokenPrefsKey()).apply();
    }

    public void purgeStateForImportModes(SyncModeImport... syncModeImportArr) {
        for (SyncModeImport syncModeImport : syncModeImportArr) {
            getPrefs().edit().putBoolean(syncModeImport.getFinishedPrefsKey(), false).putString(syncModeImport.getTokenPrefsKey(), null).apply();
        }
    }
}

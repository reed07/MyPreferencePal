package com.uacf.sync.provider.internal.model;

import android.content.SharedPreferences;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import com.uacf.sync.provider.sdk.model.SyncMode;
import java.util.Collection;

public interface SyncOpDelegate {
    SyncItemHandler getHandlerForResource(String str);

    Collection<SyncMode> getOrderedListOfImportModes();

    SharedPreferences getPrefs();

    Collection<String> getSupportedResourceNames();

    void onBeforeFetch();

    void onBeforePublish();

    void onImportComplete();

    void onPrepareToSync();

    void onSyncTokenExpired();
}

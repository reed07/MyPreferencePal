package com.myfitnesspal.shared.service.syncv2.ops;

import android.content.SharedPreferences;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.sync.provider.internal.model.SyncOpDelegateBase;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import com.uacf.sync.provider.sdk.model.SyncMode;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;

public class MfpSyncV2OpDelegate extends SyncOpDelegateBase {
    @Inject
    Map<String, SyncItemHandler> mapOfResourceNameToHandler;
    @Inject
    @Named("sync-v2-settings")
    KeyedSharedPreferences prefs;
    private Collection<String> resourcesToSyncWithV2;
    @Inject
    Lazy<SyncUtil> syncUtil;

    public MfpSyncV2OpDelegate() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void onPrepareToSync() {
        if (this.resourcesToSyncWithV2 == null) {
            this.resourcesToSyncWithV2 = ((SyncUtil) this.syncUtil.get()).getAllSyncV2EnabledResources();
        }
    }

    public void onImportComplete() {
        ((SyncUtil) this.syncUtil.get()).setInitialSyncV2Completed(true);
    }

    public Collection<String> getSupportedResourceNames() {
        return this.resourcesToSyncWithV2;
    }

    public SyncItemHandler getHandlerForResource(String str) {
        return (SyncItemHandler) this.mapOfResourceNameToHandler.get(str);
    }

    public Collection<SyncMode> getOrderedListOfImportModes() {
        ArrayList arrayList = new ArrayList();
        Collection supportedResourceNames = getSupportedResourceNames();
        arrayList.add(MfpSyncV2Modes.ImportDefaultSet);
        if (supportedResourceNames.contains("exercise")) {
            arrayList.add(MfpSyncV2Modes.ImportExercisesAndExerciseEntries);
        }
        if (supportedResourceNames.contains("image")) {
            arrayList.add(MfpSyncV2Modes.ImportImagesAndImageAssociations);
        }
        if (supportedResourceNames.contains("notification")) {
            arrayList.add(MfpSyncV2Modes.ImportNotifications);
        }
        return arrayList;
    }

    public SharedPreferences getPrefs() {
        return this.prefs;
    }
}

package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import java.util.Map;
import javax.inject.Provider;

public final class MfpSyncV2OpDelegate_MembersInjector implements MembersInjector<MfpSyncV2OpDelegate> {
    private final Provider<Map<String, SyncItemHandler>> mapOfResourceNameToHandlerProvider;
    private final Provider<KeyedSharedPreferences> prefsProvider;
    private final Provider<SyncUtil> syncUtilProvider;

    public MfpSyncV2OpDelegate_MembersInjector(Provider<Map<String, SyncItemHandler>> provider, Provider<SyncUtil> provider2, Provider<KeyedSharedPreferences> provider3) {
        this.mapOfResourceNameToHandlerProvider = provider;
        this.syncUtilProvider = provider2;
        this.prefsProvider = provider3;
    }

    public static MembersInjector<MfpSyncV2OpDelegate> create(Provider<Map<String, SyncItemHandler>> provider, Provider<SyncUtil> provider2, Provider<KeyedSharedPreferences> provider3) {
        return new MfpSyncV2OpDelegate_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(MfpSyncV2OpDelegate mfpSyncV2OpDelegate) {
        injectMapOfResourceNameToHandler(mfpSyncV2OpDelegate, (Map) this.mapOfResourceNameToHandlerProvider.get());
        injectSyncUtil(mfpSyncV2OpDelegate, DoubleCheck.lazy(this.syncUtilProvider));
        injectPrefs(mfpSyncV2OpDelegate, (KeyedSharedPreferences) this.prefsProvider.get());
    }

    public static void injectMapOfResourceNameToHandler(MfpSyncV2OpDelegate mfpSyncV2OpDelegate, Map<String, SyncItemHandler> map) {
        mfpSyncV2OpDelegate.mapOfResourceNameToHandler = map;
    }

    public static void injectSyncUtil(MfpSyncV2OpDelegate mfpSyncV2OpDelegate, Lazy<SyncUtil> lazy) {
        mfpSyncV2OpDelegate.syncUtil = lazy;
    }

    public static void injectPrefs(MfpSyncV2OpDelegate mfpSyncV2OpDelegate, KeyedSharedPreferences keyedSharedPreferences) {
        mfpSyncV2OpDelegate.prefs = keyedSharedPreferences;
    }
}

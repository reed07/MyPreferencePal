package com.myfitnesspal.shared.service.syncv1;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SyncSettings_Factory implements Factory<SyncSettings> {
    private final Provider<SharedPreferences> prefsProvider;

    public SyncSettings_Factory(Provider<SharedPreferences> provider) {
        this.prefsProvider = provider;
    }

    public SyncSettings get() {
        return provideInstance(this.prefsProvider);
    }

    public static SyncSettings provideInstance(Provider<SharedPreferences> provider) {
        return new SyncSettings((SharedPreferences) provider.get());
    }

    public static SyncSettings_Factory create(Provider<SharedPreferences> provider) {
        return new SyncSettings_Factory(provider);
    }

    public static SyncSettings newSyncSettings(SharedPreferences sharedPreferences) {
        return new SyncSettings(sharedPreferences);
    }
}

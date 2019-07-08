package com.myfitnesspal.feature.settings.model;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ABTestSettings_Factory implements Factory<ABTestSettings> {
    private final Provider<Context> contextProvider;
    private final Provider<SharedPreferences> prefsProvider;

    public ABTestSettings_Factory(Provider<Context> provider, Provider<SharedPreferences> provider2) {
        this.contextProvider = provider;
        this.prefsProvider = provider2;
    }

    public ABTestSettings get() {
        return provideInstance(this.contextProvider, this.prefsProvider);
    }

    public static ABTestSettings provideInstance(Provider<Context> provider, Provider<SharedPreferences> provider2) {
        return new ABTestSettings((Context) provider.get(), (SharedPreferences) provider2.get());
    }

    public static ABTestSettings_Factory create(Provider<Context> provider, Provider<SharedPreferences> provider2) {
        return new ABTestSettings_Factory(provider, provider2);
    }

    public static ABTestSettings newABTestSettings(Context context, SharedPreferences sharedPreferences) {
        return new ABTestSettings(context, sharedPreferences);
    }
}

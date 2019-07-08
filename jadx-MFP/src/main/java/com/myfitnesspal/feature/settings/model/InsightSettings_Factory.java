package com.myfitnesspal.feature.settings.model;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InsightSettings_Factory implements Factory<InsightSettings> {
    private final Provider<SharedPreferences> prefsProvider;
    private final Provider<Session> sessionProvider;

    public InsightSettings_Factory(Provider<SharedPreferences> provider, Provider<Session> provider2) {
        this.prefsProvider = provider;
        this.sessionProvider = provider2;
    }

    public InsightSettings get() {
        return provideInstance(this.prefsProvider, this.sessionProvider);
    }

    public static InsightSettings provideInstance(Provider<SharedPreferences> provider, Provider<Session> provider2) {
        return new InsightSettings((SharedPreferences) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static InsightSettings_Factory create(Provider<SharedPreferences> provider, Provider<Session> provider2) {
        return new InsightSettings_Factory(provider, provider2);
    }

    public static InsightSettings newInsightSettings(SharedPreferences sharedPreferences, Lazy<Session> lazy) {
        return new InsightSettings(sharedPreferences, lazy);
    }
}

package com.myfitnesspal.feature.settings.model;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class XPromoSettings_Factory implements Factory<XPromoSettings> {
    private final Provider<SharedPreferences> prefsProvider;
    private final Provider<Session> sessionProvider;

    public XPromoSettings_Factory(Provider<SharedPreferences> provider, Provider<Session> provider2) {
        this.prefsProvider = provider;
        this.sessionProvider = provider2;
    }

    public XPromoSettings get() {
        return provideInstance(this.prefsProvider, this.sessionProvider);
    }

    public static XPromoSettings provideInstance(Provider<SharedPreferences> provider, Provider<Session> provider2) {
        return new XPromoSettings((SharedPreferences) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static XPromoSettings_Factory create(Provider<SharedPreferences> provider, Provider<Session> provider2) {
        return new XPromoSettings_Factory(provider, provider2);
    }

    public static XPromoSettings newXPromoSettings(SharedPreferences sharedPreferences, Lazy<Session> lazy) {
        return new XPromoSettings(sharedPreferences, lazy);
    }
}

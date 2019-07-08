package com.myfitnesspal.feature.settings.model;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AdsSettings_Factory implements Factory<AdsSettings> {
    private final Provider<String> facebookAppIdProvider;
    private final Provider<SharedPreferences> prefsProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UacfConfigurationUtil> uacfConfigurationUtilProvider;

    public AdsSettings_Factory(Provider<SharedPreferences> provider, Provider<String> provider2, Provider<UacfConfigurationUtil> provider3, Provider<Session> provider4) {
        this.prefsProvider = provider;
        this.facebookAppIdProvider = provider2;
        this.uacfConfigurationUtilProvider = provider3;
        this.sessionProvider = provider4;
    }

    public AdsSettings get() {
        return provideInstance(this.prefsProvider, this.facebookAppIdProvider, this.uacfConfigurationUtilProvider, this.sessionProvider);
    }

    public static AdsSettings provideInstance(Provider<SharedPreferences> provider, Provider<String> provider2, Provider<UacfConfigurationUtil> provider3, Provider<Session> provider4) {
        return new AdsSettings((SharedPreferences) provider.get(), (String) provider2.get(), DoubleCheck.lazy(provider3), (Session) provider4.get());
    }

    public static AdsSettings_Factory create(Provider<SharedPreferences> provider, Provider<String> provider2, Provider<UacfConfigurationUtil> provider3, Provider<Session> provider4) {
        return new AdsSettings_Factory(provider, provider2, provider3, provider4);
    }

    public static AdsSettings newAdsSettings(SharedPreferences sharedPreferences, String str, Lazy<UacfConfigurationUtil> lazy, Session session) {
        return new AdsSettings(sharedPreferences, str, lazy, session);
    }
}

package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ServiceModule_ProvidesLoginModelFactory implements Factory<LoginModel> {
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final ServiceModule module;
    private final Provider<SharedPreferences> prefsProvider;
    private final Provider<Session> sessionProvider;

    public ServiceModule_ProvidesLoginModelFactory(ServiceModule serviceModule, Provider<SharedPreferences> provider, Provider<Session> provider2, Provider<GlobalSettingsService> provider3) {
        this.module = serviceModule;
        this.prefsProvider = provider;
        this.sessionProvider = provider2;
        this.globalSettingsServiceProvider = provider3;
    }

    public LoginModel get() {
        return provideInstance(this.module, this.prefsProvider, this.sessionProvider, this.globalSettingsServiceProvider);
    }

    public static LoginModel provideInstance(ServiceModule serviceModule, Provider<SharedPreferences> provider, Provider<Session> provider2, Provider<GlobalSettingsService> provider3) {
        return proxyProvidesLoginModel(serviceModule, (SharedPreferences) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ServiceModule_ProvidesLoginModelFactory create(ServiceModule serviceModule, Provider<SharedPreferences> provider, Provider<Session> provider2, Provider<GlobalSettingsService> provider3) {
        return new ServiceModule_ProvidesLoginModelFactory(serviceModule, provider, provider2, provider3);
    }

    public static LoginModel proxyProvidesLoginModel(ServiceModule serviceModule, SharedPreferences sharedPreferences, Lazy<Session> lazy, Lazy<GlobalSettingsService> lazy2) {
        return (LoginModel) Preconditions.checkNotNull(serviceModule.providesLoginModel(sharedPreferences, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}

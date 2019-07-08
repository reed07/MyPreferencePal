package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.service.facebook.FacebookService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.thirdparty.ThirdPartyService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFacebookServiceFactory implements Factory<FacebookService> {
    private final Provider<LoginModel> loginModelProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<ThirdPartyService> thirdPartyServiceProvider;

    public ApplicationModule_ProvidesFacebookServiceFactory(ApplicationModule applicationModule, Provider<ThirdPartyService> provider, Provider<LoginModel> provider2, Provider<Session> provider3) {
        this.module = applicationModule;
        this.thirdPartyServiceProvider = provider;
        this.loginModelProvider = provider2;
        this.sessionProvider = provider3;
    }

    public FacebookService get() {
        return provideInstance(this.module, this.thirdPartyServiceProvider, this.loginModelProvider, this.sessionProvider);
    }

    public static FacebookService provideInstance(ApplicationModule applicationModule, Provider<ThirdPartyService> provider, Provider<LoginModel> provider2, Provider<Session> provider3) {
        return proxyProvidesFacebookService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvidesFacebookServiceFactory create(ApplicationModule applicationModule, Provider<ThirdPartyService> provider, Provider<LoginModel> provider2, Provider<Session> provider3) {
        return new ApplicationModule_ProvidesFacebookServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static FacebookService proxyProvidesFacebookService(ApplicationModule applicationModule, Lazy<ThirdPartyService> lazy, Lazy<LoginModel> lazy2, Lazy<Session> lazy3) {
        return (FacebookService) Preconditions.checkNotNull(applicationModule.providesFacebookService(lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}

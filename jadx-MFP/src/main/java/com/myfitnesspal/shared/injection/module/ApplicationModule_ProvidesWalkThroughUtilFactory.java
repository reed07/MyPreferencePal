package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtil;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesWalkThroughUtilFactory implements Factory<WalkthroughUtil> {
    private final Provider<Bus> busProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public ApplicationModule_ProvidesWalkThroughUtilFactory(ApplicationModule applicationModule, Provider<Bus> provider, Provider<Session> provider2, Provider<LocalizedStringsUtil> provider3, Provider<UserEnergyService> provider4) {
        this.module = applicationModule;
        this.busProvider = provider;
        this.sessionProvider = provider2;
        this.localizedStringsUtilProvider = provider3;
        this.userEnergyServiceProvider = provider4;
    }

    public WalkthroughUtil get() {
        return provideInstance(this.module, this.busProvider, this.sessionProvider, this.localizedStringsUtilProvider, this.userEnergyServiceProvider);
    }

    public static WalkthroughUtil provideInstance(ApplicationModule applicationModule, Provider<Bus> provider, Provider<Session> provider2, Provider<LocalizedStringsUtil> provider3, Provider<UserEnergyService> provider4) {
        return proxyProvidesWalkThroughUtil(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static ApplicationModule_ProvidesWalkThroughUtilFactory create(ApplicationModule applicationModule, Provider<Bus> provider, Provider<Session> provider2, Provider<LocalizedStringsUtil> provider3, Provider<UserEnergyService> provider4) {
        ApplicationModule_ProvidesWalkThroughUtilFactory applicationModule_ProvidesWalkThroughUtilFactory = new ApplicationModule_ProvidesWalkThroughUtilFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesWalkThroughUtilFactory;
    }

    public static WalkthroughUtil proxyProvidesWalkThroughUtil(ApplicationModule applicationModule, Lazy<Bus> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<UserEnergyService> lazy4) {
        return (WalkthroughUtil) Preconditions.checkNotNull(applicationModule.providesWalkThroughUtil(lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}

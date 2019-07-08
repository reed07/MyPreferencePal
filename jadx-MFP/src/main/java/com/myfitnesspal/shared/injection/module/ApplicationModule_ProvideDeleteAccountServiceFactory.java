package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideDeleteAccountServiceFactory implements Factory<DeleteAccountService> {
    private final Provider<MfpV2Api> mfpV2ApiProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvideDeleteAccountServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<Session> provider2) {
        this.module = applicationModule;
        this.mfpV2ApiProvider = provider;
        this.sessionProvider = provider2;
    }

    public DeleteAccountService get() {
        return provideInstance(this.module, this.mfpV2ApiProvider, this.sessionProvider);
    }

    public static DeleteAccountService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<Session> provider2) {
        return proxyProvideDeleteAccountService(applicationModule, provider, DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvideDeleteAccountServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<Session> provider2) {
        return new ApplicationModule_ProvideDeleteAccountServiceFactory(applicationModule, provider, provider2);
    }

    public static DeleteAccountService proxyProvideDeleteAccountService(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Lazy<Session> lazy) {
        return (DeleteAccountService) Preconditions.checkNotNull(applicationModule.provideDeleteAccountService(provider, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}

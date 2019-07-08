package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.shared.api.v1.MfpActionApi;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesMessageServiceFactory implements Factory<MessageService> {
    private final Provider<BackgroundJobHelper> backgroundJobHelperProvider;
    private final Provider<MfpActionApi> mfpActionApiProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesMessageServiceFactory(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Provider<BackgroundJobHelper> provider2) {
        this.module = applicationModule;
        this.mfpActionApiProvider = provider;
        this.backgroundJobHelperProvider = provider2;
    }

    public MessageService get() {
        return provideInstance(this.module, this.mfpActionApiProvider, this.backgroundJobHelperProvider);
    }

    public static MessageService provideInstance(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Provider<BackgroundJobHelper> provider2) {
        return proxyProvidesMessageService(applicationModule, provider, (BackgroundJobHelper) provider2.get());
    }

    public static ApplicationModule_ProvidesMessageServiceFactory create(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Provider<BackgroundJobHelper> provider2) {
        return new ApplicationModule_ProvidesMessageServiceFactory(applicationModule, provider, provider2);
    }

    public static MessageService proxyProvidesMessageService(ApplicationModule applicationModule, Provider<MfpActionApi> provider, BackgroundJobHelper backgroundJobHelper) {
        return (MessageService) Preconditions.checkNotNull(applicationModule.providesMessageService(provider, backgroundJobHelper), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesUserPropertiesServiceFactory implements Factory<UserPropertiesService> {
    private final Provider<MfpInformationApi> apiProvider;
    private final ApplicationModule module;
    private final Provider<MfpV2Api> userPropertiesV2ApiProvider;

    public ApplicationModule_ProvidesUserPropertiesServiceFactory(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2) {
        this.module = applicationModule;
        this.apiProvider = provider;
        this.userPropertiesV2ApiProvider = provider2;
    }

    public UserPropertiesService get() {
        return provideInstance(this.module, this.apiProvider, this.userPropertiesV2ApiProvider);
    }

    public static UserPropertiesService provideInstance(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2) {
        return proxyProvidesUserPropertiesService(applicationModule, provider, provider2);
    }

    public static ApplicationModule_ProvidesUserPropertiesServiceFactory create(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2) {
        return new ApplicationModule_ProvidesUserPropertiesServiceFactory(applicationModule, provider, provider2);
    }

    public static UserPropertiesService proxyProvidesUserPropertiesService(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2) {
        return (UserPropertiesService) Preconditions.checkNotNull(applicationModule.providesUserPropertiesService(provider, provider2), "Cannot return null from a non-@Nullable @Provides method");
    }
}

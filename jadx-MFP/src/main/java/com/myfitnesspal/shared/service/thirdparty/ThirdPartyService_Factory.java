package com.myfitnesspal.shared.service.thirdparty;

import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ThirdPartyService_Factory implements Factory<ThirdPartyService> {
    private final Provider<MfpInformationApi> apiProvider;

    public ThirdPartyService_Factory(Provider<MfpInformationApi> provider) {
        this.apiProvider = provider;
    }

    public ThirdPartyService get() {
        return provideInstance(this.apiProvider);
    }

    public static ThirdPartyService provideInstance(Provider<MfpInformationApi> provider) {
        return new ThirdPartyService(provider);
    }

    public static ThirdPartyService_Factory create(Provider<MfpInformationApi> provider) {
        return new ThirdPartyService_Factory(provider);
    }

    public static ThirdPartyService newThirdPartyService(Provider<MfpInformationApi> provider) {
        return new ThirdPartyService(provider);
    }
}

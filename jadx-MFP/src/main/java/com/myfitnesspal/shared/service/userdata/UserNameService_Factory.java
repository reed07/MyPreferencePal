package com.myfitnesspal.shared.service.userdata;

import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserNameService_Factory implements Factory<UserNameService> {
    private final Provider<MfpInformationApi> apiProvider;

    public UserNameService_Factory(Provider<MfpInformationApi> provider) {
        this.apiProvider = provider;
    }

    public UserNameService get() {
        return provideInstance(this.apiProvider);
    }

    public static UserNameService provideInstance(Provider<MfpInformationApi> provider) {
        return new UserNameService(provider);
    }

    public static UserNameService_Factory create(Provider<MfpInformationApi> provider) {
        return new UserNameService_Factory(provider);
    }

    public static UserNameService newUserNameService(Provider<MfpInformationApi> provider) {
        return new UserNameService(provider);
    }
}

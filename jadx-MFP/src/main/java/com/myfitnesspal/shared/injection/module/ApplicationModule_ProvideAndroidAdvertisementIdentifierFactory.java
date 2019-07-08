package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.ads.AndroidAdvertisementIdentifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideAndroidAdvertisementIdentifierFactory implements Factory<AndroidAdvertisementIdentifier> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideAndroidAdvertisementIdentifierFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public AndroidAdvertisementIdentifier get() {
        return provideInstance(this.module);
    }

    public static AndroidAdvertisementIdentifier provideInstance(ApplicationModule applicationModule) {
        return proxyProvideAndroidAdvertisementIdentifier(applicationModule);
    }

    public static ApplicationModule_ProvideAndroidAdvertisementIdentifierFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideAndroidAdvertisementIdentifierFactory(applicationModule);
    }

    public static AndroidAdvertisementIdentifier proxyProvideAndroidAdvertisementIdentifier(ApplicationModule applicationModule) {
        return (AndroidAdvertisementIdentifier) Preconditions.checkNotNull(applicationModule.provideAndroidAdvertisementIdentifier(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

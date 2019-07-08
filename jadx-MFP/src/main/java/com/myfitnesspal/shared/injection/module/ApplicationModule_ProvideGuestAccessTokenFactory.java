package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideGuestAccessTokenFactory implements Factory<String> {
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<MfpApiSettings> mfpApiSettingsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideGuestAccessTokenFactory(ApplicationModule applicationModule, Provider<MfpApiSettings> provider, Provider<AppIndexerBot> provider2) {
        this.module = applicationModule;
        this.mfpApiSettingsProvider = provider;
        this.appIndexerBotProvider = provider2;
    }

    public String get() {
        return provideInstance(this.module, this.mfpApiSettingsProvider, this.appIndexerBotProvider);
    }

    public static String provideInstance(ApplicationModule applicationModule, Provider<MfpApiSettings> provider, Provider<AppIndexerBot> provider2) {
        return proxyProvideGuestAccessToken(applicationModule, DoubleCheck.lazy(provider), (AppIndexerBot) provider2.get());
    }

    public static ApplicationModule_ProvideGuestAccessTokenFactory create(ApplicationModule applicationModule, Provider<MfpApiSettings> provider, Provider<AppIndexerBot> provider2) {
        return new ApplicationModule_ProvideGuestAccessTokenFactory(applicationModule, provider, provider2);
    }

    public static String proxyProvideGuestAccessToken(ApplicationModule applicationModule, Lazy<MfpApiSettings> lazy, AppIndexerBot appIndexerBot) {
        return (String) Preconditions.checkNotNull(applicationModule.provideGuestAccessToken(lazy, appIndexerBot), "Cannot return null from a non-@Nullable @Provides method");
    }
}

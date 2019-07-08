package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideClientIdFactory implements Factory<String> {
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideClientIdFactory(ApplicationModule applicationModule, Provider<AppIndexerBot> provider) {
        this.module = applicationModule;
        this.appIndexerBotProvider = provider;
    }

    public String get() {
        return provideInstance(this.module, this.appIndexerBotProvider);
    }

    public static String provideInstance(ApplicationModule applicationModule, Provider<AppIndexerBot> provider) {
        return proxyProvideClientId(applicationModule, (AppIndexerBot) provider.get());
    }

    public static ApplicationModule_ProvideClientIdFactory create(ApplicationModule applicationModule, Provider<AppIndexerBot> provider) {
        return new ApplicationModule_ProvideClientIdFactory(applicationModule, provider);
    }

    public static String proxyProvideClientId(ApplicationModule applicationModule, AppIndexerBot appIndexerBot) {
        return (String) Preconditions.checkNotNull(applicationModule.provideClientId(appIndexerBot), "Cannot return null from a non-@Nullable @Provides method");
    }
}

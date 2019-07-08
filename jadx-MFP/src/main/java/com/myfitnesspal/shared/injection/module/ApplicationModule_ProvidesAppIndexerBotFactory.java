package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesAppIndexerBotFactory implements Factory<AppIndexerBot> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesAppIndexerBotFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public AppIndexerBot get() {
        return provideInstance(this.module);
    }

    public static AppIndexerBot provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesAppIndexerBot(applicationModule);
    }

    public static ApplicationModule_ProvidesAppIndexerBotFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesAppIndexerBotFactory(applicationModule);
    }

    public static AppIndexerBot proxyProvidesAppIndexerBot(ApplicationModule applicationModule) {
        return (AppIndexerBot) Preconditions.checkNotNull(applicationModule.providesAppIndexerBot(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

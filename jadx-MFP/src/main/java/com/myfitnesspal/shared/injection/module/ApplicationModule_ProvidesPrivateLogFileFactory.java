package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesPrivateLogFileFactory implements Factory<File> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesPrivateLogFileFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public File get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static File provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvidesPrivateLogFile(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvidesPrivateLogFileFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesPrivateLogFileFactory(applicationModule, provider);
    }

    public static File proxyProvidesPrivateLogFile(ApplicationModule applicationModule, Context context) {
        return (File) Preconditions.checkNotNull(applicationModule.providesPrivateLogFile(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

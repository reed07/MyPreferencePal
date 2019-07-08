package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesBackgroundServiceHelperFactory implements Factory<BackgroundJobHelper> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesBackgroundServiceHelperFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public BackgroundJobHelper get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static BackgroundJobHelper provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvidesBackgroundServiceHelper(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvidesBackgroundServiceHelperFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesBackgroundServiceHelperFactory(applicationModule, provider);
    }

    public static BackgroundJobHelper proxyProvidesBackgroundServiceHelper(ApplicationModule applicationModule, Context context) {
        return (BackgroundJobHelper) Preconditions.checkNotNull(applicationModule.providesBackgroundServiceHelper(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

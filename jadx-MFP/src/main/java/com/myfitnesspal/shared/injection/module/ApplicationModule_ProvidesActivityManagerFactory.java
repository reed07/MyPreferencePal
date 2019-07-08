package com.myfitnesspal.shared.injection.module;

import android.app.ActivityManager;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesActivityManagerFactory implements Factory<ActivityManager> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesActivityManagerFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public ActivityManager get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static ActivityManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvidesActivityManager(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvidesActivityManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesActivityManagerFactory(applicationModule, provider);
    }

    public static ActivityManager proxyProvidesActivityManager(ApplicationModule applicationModule, Context context) {
        return (ActivityManager) Preconditions.checkNotNull(applicationModule.providesActivityManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

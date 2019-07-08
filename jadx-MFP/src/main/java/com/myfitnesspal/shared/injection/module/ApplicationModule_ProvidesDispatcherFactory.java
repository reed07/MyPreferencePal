package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.framework.deeplink.Dispatcher;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesDispatcherFactory implements Factory<Dispatcher> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesDispatcherFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public Dispatcher get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static Dispatcher provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvidesDispatcher(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvidesDispatcherFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesDispatcherFactory(applicationModule, provider);
    }

    public static Dispatcher proxyProvidesDispatcher(ApplicationModule applicationModule, Context context) {
        return (Dispatcher) Preconditions.checkNotNull(applicationModule.providesDispatcher(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.uacf.core.logging.LogConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ServiceModule_ProvidesLnConfigFactory implements Factory<LogConfig> {
    private final Provider<Context> contextProvider;
    private final ServiceModule module;

    public ServiceModule_ProvidesLnConfigFactory(ServiceModule serviceModule, Provider<Context> provider) {
        this.module = serviceModule;
        this.contextProvider = provider;
    }

    public LogConfig get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static LogConfig provideInstance(ServiceModule serviceModule, Provider<Context> provider) {
        return proxyProvidesLnConfig(serviceModule, (Context) provider.get());
    }

    public static ServiceModule_ProvidesLnConfigFactory create(ServiceModule serviceModule, Provider<Context> provider) {
        return new ServiceModule_ProvidesLnConfigFactory(serviceModule, provider);
    }

    public static LogConfig proxyProvidesLnConfig(ServiceModule serviceModule, Context context) {
        return (LogConfig) Preconditions.checkNotNull(serviceModule.providesLnConfig(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

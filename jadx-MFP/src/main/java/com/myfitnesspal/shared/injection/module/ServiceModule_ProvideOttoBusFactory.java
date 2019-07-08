package com.myfitnesspal.shared.injection.module;

import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ServiceModule_ProvideOttoBusFactory implements Factory<Bus> {
    private final ServiceModule module;

    public ServiceModule_ProvideOttoBusFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public Bus get() {
        return provideInstance(this.module);
    }

    public static Bus provideInstance(ServiceModule serviceModule) {
        return proxyProvideOttoBus(serviceModule);
    }

    public static ServiceModule_ProvideOttoBusFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideOttoBusFactory(serviceModule);
    }

    public static Bus proxyProvideOttoBus(ServiceModule serviceModule) {
        return (Bus) Preconditions.checkNotNull(serviceModule.provideOttoBus(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

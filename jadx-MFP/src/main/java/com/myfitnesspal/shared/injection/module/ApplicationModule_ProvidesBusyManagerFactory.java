package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.ui.activity.busymanager.BusyManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesBusyManagerFactory implements Factory<BusyManager> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesBusyManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public BusyManager get() {
        return provideInstance(this.module);
    }

    public static BusyManager provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesBusyManager(applicationModule);
    }

    public static ApplicationModule_ProvidesBusyManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesBusyManagerFactory(applicationModule);
    }

    public static BusyManager proxyProvidesBusyManager(ApplicationModule applicationModule) {
        return (BusyManager) Preconditions.checkNotNull(applicationModule.providesBusyManager(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

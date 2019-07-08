package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.uacf.core.util.DeviceInfo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesDeviceInfoFactory implements Factory<DeviceInfo> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesDeviceInfoFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public DeviceInfo get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static DeviceInfo provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvidesDeviceInfo(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvidesDeviceInfoFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesDeviceInfoFactory(applicationModule, provider);
    }

    public static DeviceInfo proxyProvidesDeviceInfo(ApplicationModule applicationModule, Context context) {
        return (DeviceInfo) Preconditions.checkNotNull(applicationModule.providesDeviceInfo(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

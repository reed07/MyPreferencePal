package com.myfitnesspal.shared.factory;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DeviceUuidFactory_Factory implements Factory<DeviceUuidFactory> {
    private final Provider<Context> contextProvider;

    public DeviceUuidFactory_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public DeviceUuidFactory get() {
        return provideInstance(this.contextProvider);
    }

    public static DeviceUuidFactory provideInstance(Provider<Context> provider) {
        return new DeviceUuidFactory((Context) provider.get());
    }

    public static DeviceUuidFactory_Factory create(Provider<Context> provider) {
        return new DeviceUuidFactory_Factory(provider);
    }

    public static DeviceUuidFactory newDeviceUuidFactory(Context context) {
        return new DeviceUuidFactory(context);
    }
}

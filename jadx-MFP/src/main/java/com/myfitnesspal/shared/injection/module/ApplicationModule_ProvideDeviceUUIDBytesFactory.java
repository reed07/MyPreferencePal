package com.myfitnesspal.shared.injection.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class ApplicationModule_ProvideDeviceUUIDBytesFactory implements Factory<byte[]> {
    private final ApplicationModule module;
    private final Provider<UUID> uuidProvider;

    public ApplicationModule_ProvideDeviceUUIDBytesFactory(ApplicationModule applicationModule, Provider<UUID> provider) {
        this.module = applicationModule;
        this.uuidProvider = provider;
    }

    public byte[] get() {
        return provideInstance(this.module, this.uuidProvider);
    }

    public static byte[] provideInstance(ApplicationModule applicationModule, Provider<UUID> provider) {
        return proxyProvideDeviceUUIDBytes(applicationModule, (UUID) provider.get());
    }

    public static ApplicationModule_ProvideDeviceUUIDBytesFactory create(ApplicationModule applicationModule, Provider<UUID> provider) {
        return new ApplicationModule_ProvideDeviceUUIDBytesFactory(applicationModule, provider);
    }

    public static byte[] proxyProvideDeviceUUIDBytes(ApplicationModule applicationModule, UUID uuid) {
        return (byte[]) Preconditions.checkNotNull(applicationModule.provideDeviceUUIDBytes(uuid), "Cannot return null from a non-@Nullable @Provides method");
    }
}

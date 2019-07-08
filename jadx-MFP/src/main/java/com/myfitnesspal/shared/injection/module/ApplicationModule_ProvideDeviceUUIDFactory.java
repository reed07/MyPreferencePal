package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class ApplicationModule_ProvideDeviceUUIDFactory implements Factory<UUID> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideDeviceUUIDFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public UUID get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static UUID provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvideDeviceUUID(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvideDeviceUUIDFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvideDeviceUUIDFactory(applicationModule, provider);
    }

    public static UUID proxyProvideDeviceUUID(ApplicationModule applicationModule, Context context) {
        return (UUID) Preconditions.checkNotNull(applicationModule.provideDeviceUUID(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

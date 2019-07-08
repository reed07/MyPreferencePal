package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.syncv1.packets.PacketFactory;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesPacketFactoryFactory implements Factory<PacketFactory> {
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesPacketFactoryFactory(ApplicationModule applicationModule, Provider<DbConnectionManager> provider) {
        this.module = applicationModule;
        this.dbConnectionManagerProvider = provider;
    }

    public PacketFactory get() {
        return provideInstance(this.module, this.dbConnectionManagerProvider);
    }

    public static PacketFactory provideInstance(ApplicationModule applicationModule, Provider<DbConnectionManager> provider) {
        return proxyProvidesPacketFactory(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesPacketFactoryFactory create(ApplicationModule applicationModule, Provider<DbConnectionManager> provider) {
        return new ApplicationModule_ProvidesPacketFactoryFactory(applicationModule, provider);
    }

    public static PacketFactory proxyProvidesPacketFactory(ApplicationModule applicationModule, Lazy<DbConnectionManager> lazy) {
        return (PacketFactory) Preconditions.checkNotNull(applicationModule.providesPacketFactory(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}

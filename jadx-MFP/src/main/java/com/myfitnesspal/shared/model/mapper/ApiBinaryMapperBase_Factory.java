package com.myfitnesspal.shared.model.mapper;

import com.myfitnesspal.shared.service.syncv1.packets.PacketFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ApiBinaryMapperBase_Factory implements Factory<ApiBinaryMapperBase> {
    private final Provider<PacketFactory> packetFactoryProvider;

    public ApiBinaryMapperBase_Factory(Provider<PacketFactory> provider) {
        this.packetFactoryProvider = provider;
    }

    public ApiBinaryMapperBase get() {
        return provideInstance(this.packetFactoryProvider);
    }

    public static ApiBinaryMapperBase provideInstance(Provider<PacketFactory> provider) {
        return new ApiBinaryMapperBase((PacketFactory) provider.get());
    }

    public static ApiBinaryMapperBase_Factory create(Provider<PacketFactory> provider) {
        return new ApiBinaryMapperBase_Factory(provider);
    }

    public static ApiBinaryMapperBase newApiBinaryMapperBase(PacketFactory packetFactory) {
        return new ApiBinaryMapperBase(packetFactory);
    }
}

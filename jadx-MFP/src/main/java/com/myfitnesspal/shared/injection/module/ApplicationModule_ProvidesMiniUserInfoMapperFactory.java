package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesMiniUserInfoMapperFactory implements Factory<MiniUserInfoMapper> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesMiniUserInfoMapperFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public MiniUserInfoMapper get() {
        return provideInstance(this.module);
    }

    public static MiniUserInfoMapper provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesMiniUserInfoMapper(applicationModule);
    }

    public static ApplicationModule_ProvidesMiniUserInfoMapperFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesMiniUserInfoMapperFactory(applicationModule);
    }

    public static MiniUserInfoMapper proxyProvidesMiniUserInfoMapper(ApplicationModule applicationModule) {
        return (MiniUserInfoMapper) Preconditions.checkNotNull(applicationModule.providesMiniUserInfoMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.diary.service.DiaryDayCache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesDiaryDayCacheFactory implements Factory<DiaryDayCache> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesDiaryDayCacheFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public DiaryDayCache get() {
        return provideInstance(this.module);
    }

    public static DiaryDayCache provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesDiaryDayCache(applicationModule);
    }

    public static ApplicationModule_ProvidesDiaryDayCacheFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesDiaryDayCacheFactory(applicationModule);
    }

    public static DiaryDayCache proxyProvidesDiaryDayCache(ApplicationModule applicationModule) {
        return (DiaryDayCache) Preconditions.checkNotNull(applicationModule.providesDiaryDayCache(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

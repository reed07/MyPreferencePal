package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.DiaryNoteMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesDiaryNoteMapperFactory implements Factory<DiaryNoteMapper> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesDiaryNoteMapperFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public DiaryNoteMapper get() {
        return provideInstance(this.module);
    }

    public static DiaryNoteMapper provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesDiaryNoteMapper(applicationModule);
    }

    public static ApplicationModule_ProvidesDiaryNoteMapperFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesDiaryNoteMapperFactory(applicationModule);
    }

    public static DiaryNoteMapper proxyProvidesDiaryNoteMapper(ApplicationModule applicationModule) {
        return (DiaryNoteMapper) Preconditions.checkNotNull(applicationModule.providesDiaryNoteMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable;
import com.myfitnesspal.shared.db.table.StepsTable;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class ApplicationModule_ProvideStepsServiceFactory implements Factory<StepService> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<UUID> deviceIdProvider;
    private final Provider<ExerciseEntriesTable> exerciseEntriesTableProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<StepsTable> stepsTableProvider;

    public ApplicationModule_ProvideStepsServiceFactory(ApplicationModule applicationModule, Provider<StepsTable> provider, Provider<ExerciseEntriesTable> provider2, Provider<AnalyticsService> provider3, Provider<AppGalleryService> provider4, Provider<Session> provider5, Provider<UUID> provider6) {
        this.module = applicationModule;
        this.stepsTableProvider = provider;
        this.exerciseEntriesTableProvider = provider2;
        this.analyticsServiceProvider = provider3;
        this.appGalleryServiceProvider = provider4;
        this.sessionProvider = provider5;
        this.deviceIdProvider = provider6;
    }

    public StepService get() {
        return provideInstance(this.module, this.stepsTableProvider, this.exerciseEntriesTableProvider, this.analyticsServiceProvider, this.appGalleryServiceProvider, this.sessionProvider, this.deviceIdProvider);
    }

    public static StepService provideInstance(ApplicationModule applicationModule, Provider<StepsTable> provider, Provider<ExerciseEntriesTable> provider2, Provider<AnalyticsService> provider3, Provider<AppGalleryService> provider4, Provider<Session> provider5, Provider<UUID> provider6) {
        return proxyProvideStepsService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), (UUID) provider6.get());
    }

    public static ApplicationModule_ProvideStepsServiceFactory create(ApplicationModule applicationModule, Provider<StepsTable> provider, Provider<ExerciseEntriesTable> provider2, Provider<AnalyticsService> provider3, Provider<AppGalleryService> provider4, Provider<Session> provider5, Provider<UUID> provider6) {
        ApplicationModule_ProvideStepsServiceFactory applicationModule_ProvideStepsServiceFactory = new ApplicationModule_ProvideStepsServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6);
        return applicationModule_ProvideStepsServiceFactory;
    }

    public static StepService proxyProvideStepsService(ApplicationModule applicationModule, Lazy<StepsTable> lazy, Lazy<ExerciseEntriesTable> lazy2, Lazy<AnalyticsService> lazy3, Lazy<AppGalleryService> lazy4, Lazy<Session> lazy5, UUID uuid) {
        return (StepService) Preconditions.checkNotNull(applicationModule.provideStepsService(lazy, lazy2, lazy3, lazy4, lazy5, uuid), "Cannot return null from a non-@Nullable @Provides method");
    }
}

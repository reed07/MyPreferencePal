package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesProgressServiceFactory implements Factory<ProgressService> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<UserSummaryService> userSummaryServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public ApplicationModule_ProvidesProgressServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<UserSummaryService> provider3, Provider<UserWeightService> provider4, Provider<MeasurementsService> provider5, Provider<ConfigService> provider6) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.userSummaryServiceProvider = provider3;
        this.userWeightServiceProvider = provider4;
        this.measurementsServiceProvider = provider5;
        this.configServiceProvider = provider6;
    }

    public ProgressService get() {
        return provideInstance(this.module, this.contextProvider, this.sessionProvider, this.userSummaryServiceProvider, this.userWeightServiceProvider, this.measurementsServiceProvider, this.configServiceProvider);
    }

    public static ProgressService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<UserSummaryService> provider3, Provider<UserWeightService> provider4, Provider<MeasurementsService> provider5, Provider<ConfigService> provider6) {
        return proxyProvidesProgressService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static ApplicationModule_ProvidesProgressServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<UserSummaryService> provider3, Provider<UserWeightService> provider4, Provider<MeasurementsService> provider5, Provider<ConfigService> provider6) {
        ApplicationModule_ProvidesProgressServiceFactory applicationModule_ProvidesProgressServiceFactory = new ApplicationModule_ProvidesProgressServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6);
        return applicationModule_ProvidesProgressServiceFactory;
    }

    public static ProgressService proxyProvidesProgressService(ApplicationModule applicationModule, Context context, Lazy<Session> lazy, Lazy<UserSummaryService> lazy2, Lazy<UserWeightService> lazy3, Lazy<MeasurementsService> lazy4, Lazy<ConfigService> lazy5) {
        return (ProgressService) Preconditions.checkNotNull(applicationModule.providesProgressService(context, lazy, lazy2, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.video.util.VideoAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideVideoAnalyticsHelperFactory implements Factory<VideoAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideVideoAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public VideoAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static VideoAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideVideoAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideVideoAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideVideoAnalyticsHelperFactory(applicationModule, provider);
    }

    public static VideoAnalyticsHelper proxyProvideVideoAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (VideoAnalyticsHelper) Preconditions.checkNotNull(applicationModule.provideVideoAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}

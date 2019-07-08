package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesImageUploadServiceFactory implements Factory<ImageUploadService> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ImageService> imageUploadServiceProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesImageUploadServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ImageService> provider3, Provider<AnalyticsService> provider4) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.imageUploadServiceProvider = provider3;
        this.analyticsServiceProvider = provider4;
    }

    public ImageUploadService get() {
        return provideInstance(this.module, this.contextProvider, this.sessionProvider, this.imageUploadServiceProvider, this.analyticsServiceProvider);
    }

    public static ImageUploadService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ImageService> provider3, Provider<AnalyticsService> provider4) {
        return proxyProvidesImageUploadService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static ApplicationModule_ProvidesImageUploadServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ImageService> provider3, Provider<AnalyticsService> provider4) {
        ApplicationModule_ProvidesImageUploadServiceFactory applicationModule_ProvidesImageUploadServiceFactory = new ApplicationModule_ProvidesImageUploadServiceFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesImageUploadServiceFactory;
    }

    public static ImageUploadService proxyProvidesImageUploadService(ApplicationModule applicationModule, Context context, Lazy<Session> lazy, Lazy<ImageService> lazy2, Lazy<AnalyticsService> lazy3) {
        return (ImageUploadService) Preconditions.checkNotNull(applicationModule.providesImageUploadService(context, lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}

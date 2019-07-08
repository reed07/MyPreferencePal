package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.nutrition.service.ChartRendererFactory;
import com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesChartRendererFactoryFactory implements Factory<ChartRendererFactory> {
    private final Provider<Context> contextProvider;
    private final Provider<CoreChartRendererBaseConstructorArgs> coreChartRendererBaseConstructorArgsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesChartRendererFactoryFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<CoreChartRendererBaseConstructorArgs> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.coreChartRendererBaseConstructorArgsProvider = provider2;
    }

    public ChartRendererFactory get() {
        return provideInstance(this.module, this.contextProvider, this.coreChartRendererBaseConstructorArgsProvider);
    }

    public static ChartRendererFactory provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<CoreChartRendererBaseConstructorArgs> provider2) {
        return proxyProvidesChartRendererFactory(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidesChartRendererFactoryFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<CoreChartRendererBaseConstructorArgs> provider2) {
        return new ApplicationModule_ProvidesChartRendererFactoryFactory(applicationModule, provider, provider2);
    }

    public static ChartRendererFactory proxyProvidesChartRendererFactory(ApplicationModule applicationModule, Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        return (ChartRendererFactory) Preconditions.checkNotNull(applicationModule.providesChartRendererFactory(context, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}

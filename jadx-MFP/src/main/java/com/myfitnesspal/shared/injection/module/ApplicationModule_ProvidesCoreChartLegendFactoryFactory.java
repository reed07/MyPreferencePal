package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.nutrition.service.ChartLegendFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesCoreChartLegendFactoryFactory implements Factory<ChartLegendFactory> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesCoreChartLegendFactoryFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public ChartLegendFactory get() {
        return provideInstance(this.module);
    }

    public static ChartLegendFactory provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesCoreChartLegendFactory(applicationModule);
    }

    public static ApplicationModule_ProvidesCoreChartLegendFactoryFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesCoreChartLegendFactoryFactory(applicationModule);
    }

    public static ChartLegendFactory proxyProvidesCoreChartLegendFactory(ApplicationModule applicationModule) {
        return (ChartLegendFactory) Preconditions.checkNotNull(applicationModule.providesCoreChartLegendFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

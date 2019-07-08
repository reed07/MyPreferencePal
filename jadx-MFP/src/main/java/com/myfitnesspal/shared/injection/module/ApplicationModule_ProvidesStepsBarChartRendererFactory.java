package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.progress.ui.chart.StepsBarChartRenderer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesStepsBarChartRendererFactory implements Factory<StepsBarChartRenderer> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesStepsBarChartRendererFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public StepsBarChartRenderer get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static StepsBarChartRenderer provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvidesStepsBarChartRenderer(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvidesStepsBarChartRendererFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesStepsBarChartRendererFactory(applicationModule, provider);
    }

    public static StepsBarChartRenderer proxyProvidesStepsBarChartRenderer(ApplicationModule applicationModule, Context context) {
        return (StepsBarChartRenderer) Preconditions.checkNotNull(applicationModule.providesStepsBarChartRenderer(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

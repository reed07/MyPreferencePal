package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.progress.ui.chart.MeasurementLineChartRenderer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesMeasurementLineChartRendererFactory implements Factory<MeasurementLineChartRenderer> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesMeasurementLineChartRendererFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public MeasurementLineChartRenderer get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static MeasurementLineChartRenderer provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvidesMeasurementLineChartRenderer(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvidesMeasurementLineChartRendererFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesMeasurementLineChartRendererFactory(applicationModule, provider);
    }

    public static MeasurementLineChartRenderer proxyProvidesMeasurementLineChartRenderer(ApplicationModule applicationModule, Context context) {
        return (MeasurementLineChartRenderer) Preconditions.checkNotNull(applicationModule.providesMeasurementLineChartRenderer(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

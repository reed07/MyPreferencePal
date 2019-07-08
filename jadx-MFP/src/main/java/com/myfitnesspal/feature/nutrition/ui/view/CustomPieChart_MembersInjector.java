package com.myfitnesspal.feature.nutrition.ui.view;

import com.myfitnesspal.feature.nutrition.service.ChartLegendFactory;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CustomPieChart_MembersInjector implements MembersInjector<CustomPieChart> {
    private final Provider<ChartLegendFactory> coreChartLegendFactoryProvider;
    private final Provider<Bus> messageBusProvider;

    public CustomPieChart_MembersInjector(Provider<ChartLegendFactory> provider, Provider<Bus> provider2) {
        this.coreChartLegendFactoryProvider = provider;
        this.messageBusProvider = provider2;
    }

    public static MembersInjector<CustomPieChart> create(Provider<ChartLegendFactory> provider, Provider<Bus> provider2) {
        return new CustomPieChart_MembersInjector(provider, provider2);
    }

    public void injectMembers(CustomPieChart customPieChart) {
        injectCoreChartLegendFactory(customPieChart, DoubleCheck.lazy(this.coreChartLegendFactoryProvider));
        injectMessageBus(customPieChart, DoubleCheck.lazy(this.messageBusProvider));
    }

    public static void injectCoreChartLegendFactory(CustomPieChart customPieChart, Lazy<ChartLegendFactory> lazy) {
        customPieChart.coreChartLegendFactory = lazy;
    }

    public static void injectMessageBus(CustomPieChart customPieChart, Lazy<Bus> lazy) {
        customPieChart.messageBus = lazy;
    }
}

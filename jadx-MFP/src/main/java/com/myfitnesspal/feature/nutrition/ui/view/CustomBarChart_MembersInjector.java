package com.myfitnesspal.feature.nutrition.ui.view;

import com.myfitnesspal.feature.nutrition.service.ChartLegendFactory;
import com.myfitnesspal.shared.service.session.Session;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CustomBarChart_MembersInjector implements MembersInjector<CustomBarChart> {
    private final Provider<ChartLegendFactory> coreChartLegendFactoryProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<Session> sessionProvider;

    public CustomBarChart_MembersInjector(Provider<ChartLegendFactory> provider, Provider<Session> provider2, Provider<Bus> provider3) {
        this.coreChartLegendFactoryProvider = provider;
        this.sessionProvider = provider2;
        this.messageBusProvider = provider3;
    }

    public static MembersInjector<CustomBarChart> create(Provider<ChartLegendFactory> provider, Provider<Session> provider2, Provider<Bus> provider3) {
        return new CustomBarChart_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(CustomBarChart customBarChart) {
        injectCoreChartLegendFactory(customBarChart, DoubleCheck.lazy(this.coreChartLegendFactoryProvider));
        injectSession(customBarChart, DoubleCheck.lazy(this.sessionProvider));
        injectMessageBus(customBarChart, DoubleCheck.lazy(this.messageBusProvider));
    }

    public static void injectCoreChartLegendFactory(CustomBarChart customBarChart, Lazy<ChartLegendFactory> lazy) {
        customBarChart.coreChartLegendFactory = lazy;
    }

    public static void injectSession(CustomBarChart customBarChart, Lazy<Session> lazy) {
        customBarChart.session = lazy;
    }

    public static void injectMessageBus(CustomBarChart customBarChart, Lazy<Bus> lazy) {
        customBarChart.messageBus = lazy;
    }
}

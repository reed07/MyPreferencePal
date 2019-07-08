package com.myfitnesspal.shared.util;

import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class UpdateWeightProxy_MembersInjector implements MembersInjector<UpdateWeightProxy> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final Provider<ProgressAnalytics> progressAnalyticsProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserHeightService> userHeightServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public UpdateWeightProxy_MembersInjector(Provider<ProgressAnalytics> provider, Provider<MeasurementsService> provider2, Provider<UserWeightService> provider3, Provider<UserHeightService> provider4, Provider<ConfigService> provider5, Provider<Session> provider6) {
        this.progressAnalyticsProvider = provider;
        this.measurementsServiceProvider = provider2;
        this.userWeightServiceProvider = provider3;
        this.userHeightServiceProvider = provider4;
        this.configServiceProvider = provider5;
        this.sessionProvider = provider6;
    }

    public static MembersInjector<UpdateWeightProxy> create(Provider<ProgressAnalytics> provider, Provider<MeasurementsService> provider2, Provider<UserWeightService> provider3, Provider<UserHeightService> provider4, Provider<ConfigService> provider5, Provider<Session> provider6) {
        UpdateWeightProxy_MembersInjector updateWeightProxy_MembersInjector = new UpdateWeightProxy_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return updateWeightProxy_MembersInjector;
    }

    public void injectMembers(UpdateWeightProxy updateWeightProxy) {
        injectProgressAnalytics(updateWeightProxy, DoubleCheck.lazy(this.progressAnalyticsProvider));
        injectMeasurementsService(updateWeightProxy, DoubleCheck.lazy(this.measurementsServiceProvider));
        injectUserWeightService(updateWeightProxy, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectUserHeightService(updateWeightProxy, DoubleCheck.lazy(this.userHeightServiceProvider));
        injectConfigService(updateWeightProxy, DoubleCheck.lazy(this.configServiceProvider));
        injectSession(updateWeightProxy, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectProgressAnalytics(UpdateWeightProxy updateWeightProxy, Lazy<ProgressAnalytics> lazy) {
        updateWeightProxy.progressAnalytics = lazy;
    }

    public static void injectMeasurementsService(UpdateWeightProxy updateWeightProxy, Lazy<MeasurementsService> lazy) {
        updateWeightProxy.measurementsService = lazy;
    }

    public static void injectUserWeightService(UpdateWeightProxy updateWeightProxy, Lazy<UserWeightService> lazy) {
        updateWeightProxy.userWeightService = lazy;
    }

    public static void injectUserHeightService(UpdateWeightProxy updateWeightProxy, Lazy<UserHeightService> lazy) {
        updateWeightProxy.userHeightService = lazy;
    }

    public static void injectConfigService(UpdateWeightProxy updateWeightProxy, Lazy<ConfigService> lazy) {
        updateWeightProxy.configService = lazy;
    }

    public static void injectSession(UpdateWeightProxy updateWeightProxy, Lazy<Session> lazy) {
        updateWeightProxy.session = lazy;
    }
}

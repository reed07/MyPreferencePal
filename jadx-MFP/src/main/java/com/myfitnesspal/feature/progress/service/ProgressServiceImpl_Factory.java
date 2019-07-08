package com.myfitnesspal.feature.progress.service;

import android.content.Context;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ProgressServiceImpl_Factory implements Factory<ProgressServiceImpl> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserSummaryService> userSummaryServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public ProgressServiceImpl_Factory(Provider<Context> provider, Provider<Session> provider2, Provider<UserSummaryService> provider3, Provider<UserWeightService> provider4, Provider<MeasurementsService> provider5, Provider<ConfigService> provider6) {
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.userSummaryServiceProvider = provider3;
        this.userWeightServiceProvider = provider4;
        this.measurementsServiceProvider = provider5;
        this.configServiceProvider = provider6;
    }

    public ProgressServiceImpl get() {
        return provideInstance(this.contextProvider, this.sessionProvider, this.userSummaryServiceProvider, this.userWeightServiceProvider, this.measurementsServiceProvider, this.configServiceProvider);
    }

    public static ProgressServiceImpl provideInstance(Provider<Context> provider, Provider<Session> provider2, Provider<UserSummaryService> provider3, Provider<UserWeightService> provider4, Provider<MeasurementsService> provider5, Provider<ConfigService> provider6) {
        ProgressServiceImpl progressServiceImpl = new ProgressServiceImpl((Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
        return progressServiceImpl;
    }

    public static ProgressServiceImpl_Factory create(Provider<Context> provider, Provider<Session> provider2, Provider<UserSummaryService> provider3, Provider<UserWeightService> provider4, Provider<MeasurementsService> provider5, Provider<ConfigService> provider6) {
        ProgressServiceImpl_Factory progressServiceImpl_Factory = new ProgressServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6);
        return progressServiceImpl_Factory;
    }

    public static ProgressServiceImpl newProgressServiceImpl(Context context, Lazy<Session> lazy, Lazy<UserSummaryService> lazy2, Lazy<UserWeightService> lazy3, Lazy<MeasurementsService> lazy4, Lazy<ConfigService> lazy5) {
        ProgressServiceImpl progressServiceImpl = new ProgressServiceImpl(context, lazy, lazy2, lazy3, lazy4, lazy5);
        return progressServiceImpl;
    }
}

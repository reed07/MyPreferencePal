package com.myfitnesspal.shared.service.userdata;

import android.content.Context;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.session.UserV2Service;
import com.myfitnesspal.shared.validation.Validator;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserWeightService_Factory implements Factory<UserWeightService> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserV2Service> userV2ServiceProvider;
    private final Provider<Validator> validatorProvider;

    public UserWeightService_Factory(Provider<Context> provider, Provider<Session> provider2, Provider<MeasurementsService> provider3, Provider<ConfigService> provider4, Provider<UserV2Service> provider5, Provider<Validator> provider6) {
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.measurementsServiceProvider = provider3;
        this.configServiceProvider = provider4;
        this.userV2ServiceProvider = provider5;
        this.validatorProvider = provider6;
    }

    public UserWeightService get() {
        return provideInstance(this.contextProvider, this.sessionProvider, this.measurementsServiceProvider, this.configServiceProvider, this.userV2ServiceProvider, this.validatorProvider);
    }

    public static UserWeightService provideInstance(Provider<Context> provider, Provider<Session> provider2, Provider<MeasurementsService> provider3, Provider<ConfigService> provider4, Provider<UserV2Service> provider5, Provider<Validator> provider6) {
        UserWeightService userWeightService = new UserWeightService((Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), (Validator) provider6.get());
        return userWeightService;
    }

    public static UserWeightService_Factory create(Provider<Context> provider, Provider<Session> provider2, Provider<MeasurementsService> provider3, Provider<ConfigService> provider4, Provider<UserV2Service> provider5, Provider<Validator> provider6) {
        UserWeightService_Factory userWeightService_Factory = new UserWeightService_Factory(provider, provider2, provider3, provider4, provider5, provider6);
        return userWeightService_Factory;
    }

    public static UserWeightService newUserWeightService(Context context, Lazy<Session> lazy, Lazy<MeasurementsService> lazy2, Lazy<ConfigService> lazy3, Lazy<UserV2Service> lazy4, Validator validator) {
        UserWeightService userWeightService = new UserWeightService(context, lazy, lazy2, lazy3, lazy4, validator);
        return userWeightService;
    }
}

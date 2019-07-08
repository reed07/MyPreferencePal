package com.myfitnesspal.shared.notification;

import android.content.Context;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MfpNotificationHandler_Factory implements Factory<MfpNotificationHandler> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ApiJsonMapper> apiJsonMapperProvider;
    private final Provider<BackgroundJobHelper> backgroundJobHelperProvider;
    private final Provider<Context> contextProvider;

    public MfpNotificationHandler_Factory(Provider<AnalyticsService> provider, Provider<BackgroundJobHelper> provider2, Provider<ApiJsonMapper> provider3, Provider<Context> provider4) {
        this.analyticsServiceProvider = provider;
        this.backgroundJobHelperProvider = provider2;
        this.apiJsonMapperProvider = provider3;
        this.contextProvider = provider4;
    }

    public MfpNotificationHandler get() {
        return provideInstance(this.analyticsServiceProvider, this.backgroundJobHelperProvider, this.apiJsonMapperProvider, this.contextProvider);
    }

    public static MfpNotificationHandler provideInstance(Provider<AnalyticsService> provider, Provider<BackgroundJobHelper> provider2, Provider<ApiJsonMapper> provider3, Provider<Context> provider4) {
        return new MfpNotificationHandler(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), (Context) provider4.get());
    }

    public static MfpNotificationHandler_Factory create(Provider<AnalyticsService> provider, Provider<BackgroundJobHelper> provider2, Provider<ApiJsonMapper> provider3, Provider<Context> provider4) {
        return new MfpNotificationHandler_Factory(provider, provider2, provider3, provider4);
    }

    public static MfpNotificationHandler newMfpNotificationHandler(Lazy<AnalyticsService> lazy, Lazy<BackgroundJobHelper> lazy2, Lazy<ApiJsonMapper> lazy3, Context context) {
        return new MfpNotificationHandler(lazy, lazy2, lazy3, context);
    }
}

package com.myfitnesspal.shared.notification.service;

import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MfpNotificationActionService_MembersInjector implements MembersInjector<MfpNotificationActionService> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<MfpV2Api> apiProvider;

    public MfpNotificationActionService_MembersInjector(Provider<MfpV2Api> provider, Provider<AnalyticsService> provider2) {
        this.apiProvider = provider;
        this.analyticsServiceProvider = provider2;
    }

    public static MembersInjector<MfpNotificationActionService> create(Provider<MfpV2Api> provider, Provider<AnalyticsService> provider2) {
        return new MfpNotificationActionService_MembersInjector(provider, provider2);
    }

    public void injectMembers(MfpNotificationActionService mfpNotificationActionService) {
        injectApiProvider(mfpNotificationActionService, this.apiProvider);
        injectAnalyticsService(mfpNotificationActionService, DoubleCheck.lazy(this.analyticsServiceProvider));
    }

    public static void injectApiProvider(MfpNotificationActionService mfpNotificationActionService, Provider<MfpV2Api> provider) {
        mfpNotificationActionService.apiProvider = provider;
    }

    public static void injectAnalyticsService(MfpNotificationActionService mfpNotificationActionService, Lazy<AnalyticsService> lazy) {
        mfpNotificationActionService.analyticsService = lazy;
    }
}

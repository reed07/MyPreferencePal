package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitSubscriptionService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ExternalSyncModule_ProvidesGoogleFitSubscriptionServiceFactory implements Factory<GoogleFitSubscriptionService> {
    private final ExternalSyncModule module;

    public ExternalSyncModule_ProvidesGoogleFitSubscriptionServiceFactory(ExternalSyncModule externalSyncModule) {
        this.module = externalSyncModule;
    }

    public GoogleFitSubscriptionService get() {
        return provideInstance(this.module);
    }

    public static GoogleFitSubscriptionService provideInstance(ExternalSyncModule externalSyncModule) {
        return proxyProvidesGoogleFitSubscriptionService(externalSyncModule);
    }

    public static ExternalSyncModule_ProvidesGoogleFitSubscriptionServiceFactory create(ExternalSyncModule externalSyncModule) {
        return new ExternalSyncModule_ProvidesGoogleFitSubscriptionServiceFactory(externalSyncModule);
    }

    public static GoogleFitSubscriptionService proxyProvidesGoogleFitSubscriptionService(ExternalSyncModule externalSyncModule) {
        return (GoogleFitSubscriptionService) Preconditions.checkNotNull(externalSyncModule.providesGoogleFitSubscriptionService(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

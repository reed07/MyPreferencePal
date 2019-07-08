package com.myfitnesspal.shared.uacf;

import com.myfitnesspal.shared.service.analytics.AmplitudeService;
import com.myfitnesspal.shared.uacf.UacfSharedLibrary.UacfThumbprintAnalyticsCallback;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UacfSharedLibrary_UacfThumbprintAnalyticsCallback_MembersInjector implements MembersInjector<UacfThumbprintAnalyticsCallback> {
    private final Provider<AmplitudeService> amplitudeServiceProvider;

    public UacfSharedLibrary_UacfThumbprintAnalyticsCallback_MembersInjector(Provider<AmplitudeService> provider) {
        this.amplitudeServiceProvider = provider;
    }

    public static MembersInjector<UacfThumbprintAnalyticsCallback> create(Provider<AmplitudeService> provider) {
        return new UacfSharedLibrary_UacfThumbprintAnalyticsCallback_MembersInjector(provider);
    }

    public void injectMembers(UacfThumbprintAnalyticsCallback uacfThumbprintAnalyticsCallback) {
        injectAmplitudeService(uacfThumbprintAnalyticsCallback, (AmplitudeService) this.amplitudeServiceProvider.get());
    }

    public static void injectAmplitudeService(UacfThumbprintAnalyticsCallback uacfThumbprintAnalyticsCallback, AmplitudeService amplitudeService) {
        uacfThumbprintAnalyticsCallback.amplitudeService = amplitudeService;
    }
}

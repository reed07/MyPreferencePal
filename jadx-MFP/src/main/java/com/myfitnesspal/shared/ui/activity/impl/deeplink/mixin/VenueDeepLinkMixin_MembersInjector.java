package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class VenueDeepLinkMixin_MembersInjector implements MembersInjector<VenueDeepLinkMixin> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<VenueService> venueServiceProvider;

    public VenueDeepLinkMixin_MembersInjector(Provider<AnalyticsService> provider, Provider<VenueService> provider2) {
        this.analyticsProvider = provider;
        this.venueServiceProvider = provider2;
    }

    public static MembersInjector<VenueDeepLinkMixin> create(Provider<AnalyticsService> provider, Provider<VenueService> provider2) {
        return new VenueDeepLinkMixin_MembersInjector(provider, provider2);
    }

    public void injectMembers(VenueDeepLinkMixin venueDeepLinkMixin) {
        DeepLinkMixinBase_MembersInjector.injectAnalytics(venueDeepLinkMixin, DoubleCheck.lazy(this.analyticsProvider));
        injectVenueService(venueDeepLinkMixin, DoubleCheck.lazy(this.venueServiceProvider));
    }

    public static void injectVenueService(VenueDeepLinkMixin venueDeepLinkMixin, Lazy<VenueService> lazy) {
        venueDeepLinkMixin.venueService = lazy;
    }
}

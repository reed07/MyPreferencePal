package com.myfitnesspal.feature.restaurantlogging.ui.view;

import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class VenuesListView_MembersInjector implements MembersInjector<VenuesListView> {
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<UserDistanceService> userDistanceServiceProvider;

    public VenuesListView_MembersInjector(Provider<UserDistanceService> provider, Provider<NavigationHelper> provider2) {
        this.userDistanceServiceProvider = provider;
        this.navigationHelperProvider = provider2;
    }

    public static MembersInjector<VenuesListView> create(Provider<UserDistanceService> provider, Provider<NavigationHelper> provider2) {
        return new VenuesListView_MembersInjector(provider, provider2);
    }

    public void injectMembers(VenuesListView venuesListView) {
        injectUserDistanceService(venuesListView, DoubleCheck.lazy(this.userDistanceServiceProvider));
        injectNavigationHelper(venuesListView, DoubleCheck.lazy(this.navigationHelperProvider));
    }

    public static void injectUserDistanceService(VenuesListView venuesListView, Lazy<UserDistanceService> lazy) {
        venuesListView.userDistanceService = lazy;
    }

    public static void injectNavigationHelper(VenuesListView venuesListView, Lazy<NavigationHelper> lazy) {
        venuesListView.navigationHelper = lazy;
    }
}

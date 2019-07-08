package com.myfitnesspal.shared.ui.dialog.impl;

import android.content.pm.PackageManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ImageChooserDialogFragment_MembersInjector implements MembersInjector<ImageChooserDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<PackageManager> packageManagerProvider;
    private final Provider<Session> sessionProvider;

    public ImageChooserDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<PackageManager> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.packageManagerProvider = provider5;
    }

    public static MembersInjector<ImageChooserDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<PackageManager> provider5) {
        ImageChooserDialogFragment_MembersInjector imageChooserDialogFragment_MembersInjector = new ImageChooserDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return imageChooserDialogFragment_MembersInjector;
    }

    public void injectMembers(ImageChooserDialogFragment imageChooserDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(imageChooserDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(imageChooserDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(imageChooserDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(imageChooserDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectPackageManager(imageChooserDialogFragment, (PackageManager) this.packageManagerProvider.get());
    }

    public static void injectPackageManager(ImageChooserDialogFragment imageChooserDialogFragment, PackageManager packageManager) {
        imageChooserDialogFragment.packageManager = packageManager;
    }
}

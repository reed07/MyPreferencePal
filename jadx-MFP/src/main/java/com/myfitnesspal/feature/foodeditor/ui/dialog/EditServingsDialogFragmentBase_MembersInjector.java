package com.myfitnesspal.feature.foodeditor.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class EditServingsDialogFragmentBase_MembersInjector implements MembersInjector<EditServingsDialogFragmentBase> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public EditServingsDialogFragmentBase_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
    }

    public static MembersInjector<EditServingsDialogFragmentBase> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        return new EditServingsDialogFragmentBase_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(EditServingsDialogFragmentBase editServingsDialogFragmentBase) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(editServingsDialogFragmentBase, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(editServingsDialogFragmentBase, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(editServingsDialogFragmentBase, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(editServingsDialogFragmentBase, DoubleCheck.lazy(this.analyticsServiceProvider));
    }
}

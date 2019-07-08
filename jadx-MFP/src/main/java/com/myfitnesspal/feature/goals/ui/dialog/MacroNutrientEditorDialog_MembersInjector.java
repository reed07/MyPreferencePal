package com.myfitnesspal.feature.goals.ui.dialog;

import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MacroNutrientEditorDialog_MembersInjector implements MembersInjector<MacroNutrientEditorDialog> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<NutrientGoalsUtil> nguProvider;
    private final Provider<Session> sessionProvider;

    public MacroNutrientEditorDialog_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<NutrientGoalsUtil> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.nguProvider = provider5;
    }

    public static MembersInjector<MacroNutrientEditorDialog> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<NutrientGoalsUtil> provider5) {
        MacroNutrientEditorDialog_MembersInjector macroNutrientEditorDialog_MembersInjector = new MacroNutrientEditorDialog_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return macroNutrientEditorDialog_MembersInjector;
    }

    public void injectMembers(MacroNutrientEditorDialog macroNutrientEditorDialog) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(macroNutrientEditorDialog, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(macroNutrientEditorDialog, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(macroNutrientEditorDialog, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(macroNutrientEditorDialog, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectNgu(macroNutrientEditorDialog, DoubleCheck.lazy(this.nguProvider));
    }

    public static void injectNgu(MacroNutrientEditorDialog macroNutrientEditorDialog, Lazy<NutrientGoalsUtil> lazy) {
        macroNutrientEditorDialog.ngu = lazy;
    }
}

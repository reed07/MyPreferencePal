package com.myfitnesspal.feature.recipes.ui.dialog;

import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CreateRecipeDialogFragment_MembersInjector implements MembersInjector<CreateRecipeDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<Session> sessionProvider;

    public CreateRecipeDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ConfigService> provider5, Provider<RecipesAnalyticsHelper> provider6) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.configServiceProvider = provider5;
        this.recipesAnalyticsHelperProvider = provider6;
    }

    public static MembersInjector<CreateRecipeDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ConfigService> provider5, Provider<RecipesAnalyticsHelper> provider6) {
        CreateRecipeDialogFragment_MembersInjector createRecipeDialogFragment_MembersInjector = new CreateRecipeDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return createRecipeDialogFragment_MembersInjector;
    }

    public void injectMembers(CreateRecipeDialogFragment createRecipeDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(createRecipeDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(createRecipeDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(createRecipeDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(createRecipeDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectConfigService(createRecipeDialogFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectRecipesAnalyticsHelper(createRecipeDialogFragment, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
    }

    public static void injectConfigService(CreateRecipeDialogFragment createRecipeDialogFragment, Lazy<ConfigService> lazy) {
        createRecipeDialogFragment.configService = lazy;
    }

    public static void injectRecipesAnalyticsHelper(CreateRecipeDialogFragment createRecipeDialogFragment, Lazy<RecipesAnalyticsHelper> lazy) {
        createRecipeDialogFragment.recipesAnalyticsHelper = lazy;
    }
}

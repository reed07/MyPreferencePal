package com.myfitnesspal.feature.debug.ui.fragment;

import com.myfitnesspal.feature.achievementinterstitialad.service.StoredAchievementEvents;
import com.myfitnesspal.feature.debug.util.DebugSettingsService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AdvancedDebuggingFragment_MembersInjector implements MembersInjector<AdvancedDebuggingFragment> {
    private final Provider<AdsSettings> adsSettingsProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<AuthTokenProvider> authTokenProvider;
    private final Provider<DebugSettingsService> debugSettingsServiceProvider;
    private final Provider<FoodSearchActivityFactory> foodSearchActivityFactoryProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MfpApiSettings> mfpApiSettingsProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<ProductService> productServiceProvider;
    private final Provider<RequestInterceptor> requestInterceptorProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SignUpService> signUpServiceProvider;
    private final Provider<StoredAchievementEvents> storedAchievementEventsProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;

    public AdvancedDebuggingFragment_MembersInjector(Provider<ApiUrlProvider> provider, Provider<MfpApiSettings> provider2, Provider<AppSettings> provider3, Provider<AdsSettings> provider4, Provider<RequestInterceptor> provider5, Provider<NavigationHelper> provider6, Provider<SignUpService> provider7, Provider<Session> provider8, Provider<LocalSettingsService> provider9, Provider<AuthTokenProvider> provider10, Provider<UserApplicationSettingsService> provider11, Provider<GlobalSettingsService> provider12, Provider<ProductService> provider13, Provider<FoodSearchActivityFactory> provider14, Provider<DebugSettingsService> provider15, Provider<StoredAchievementEvents> provider16) {
        this.apiUrlProvider = provider;
        this.mfpApiSettingsProvider = provider2;
        this.appSettingsProvider = provider3;
        this.adsSettingsProvider = provider4;
        this.requestInterceptorProvider = provider5;
        this.navigationHelperProvider = provider6;
        this.signUpServiceProvider = provider7;
        this.sessionProvider = provider8;
        this.localSettingsServiceProvider = provider9;
        this.authTokenProvider = provider10;
        this.userApplicationSettingsServiceProvider = provider11;
        this.globalSettingsServiceProvider = provider12;
        this.productServiceProvider = provider13;
        this.foodSearchActivityFactoryProvider = provider14;
        this.debugSettingsServiceProvider = provider15;
        this.storedAchievementEventsProvider = provider16;
    }

    public static MembersInjector<AdvancedDebuggingFragment> create(Provider<ApiUrlProvider> provider, Provider<MfpApiSettings> provider2, Provider<AppSettings> provider3, Provider<AdsSettings> provider4, Provider<RequestInterceptor> provider5, Provider<NavigationHelper> provider6, Provider<SignUpService> provider7, Provider<Session> provider8, Provider<LocalSettingsService> provider9, Provider<AuthTokenProvider> provider10, Provider<UserApplicationSettingsService> provider11, Provider<GlobalSettingsService> provider12, Provider<ProductService> provider13, Provider<FoodSearchActivityFactory> provider14, Provider<DebugSettingsService> provider15, Provider<StoredAchievementEvents> provider16) {
        AdvancedDebuggingFragment_MembersInjector advancedDebuggingFragment_MembersInjector = new AdvancedDebuggingFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16);
        return advancedDebuggingFragment_MembersInjector;
    }

    public void injectMembers(AdvancedDebuggingFragment advancedDebuggingFragment) {
        injectApiUrlProvider(advancedDebuggingFragment, (ApiUrlProvider) this.apiUrlProvider.get());
        injectMfpApiSettings(advancedDebuggingFragment, (MfpApiSettings) this.mfpApiSettingsProvider.get());
        injectAppSettings(advancedDebuggingFragment, (AppSettings) this.appSettingsProvider.get());
        injectAdsSettings(advancedDebuggingFragment, (AdsSettings) this.adsSettingsProvider.get());
        injectRequestInterceptor(advancedDebuggingFragment, (RequestInterceptor) this.requestInterceptorProvider.get());
        injectNavigationHelper(advancedDebuggingFragment, (NavigationHelper) this.navigationHelperProvider.get());
        injectSignUpService(advancedDebuggingFragment, (SignUpService) this.signUpServiceProvider.get());
        injectSession(advancedDebuggingFragment, (Session) this.sessionProvider.get());
        injectLocalSettingsService(advancedDebuggingFragment, (LocalSettingsService) this.localSettingsServiceProvider.get());
        injectAuthTokenProvider(advancedDebuggingFragment, (AuthTokenProvider) this.authTokenProvider.get());
        injectUserApplicationSettingsService(advancedDebuggingFragment, (UserApplicationSettingsService) this.userApplicationSettingsServiceProvider.get());
        injectGlobalSettingsService(advancedDebuggingFragment, (GlobalSettingsService) this.globalSettingsServiceProvider.get());
        injectProductService(advancedDebuggingFragment, (ProductService) this.productServiceProvider.get());
        injectFoodSearchActivityFactory(advancedDebuggingFragment, (FoodSearchActivityFactory) this.foodSearchActivityFactoryProvider.get());
        injectDebugSettingsService(advancedDebuggingFragment, (DebugSettingsService) this.debugSettingsServiceProvider.get());
        injectStoredAchievementEvents(advancedDebuggingFragment, (StoredAchievementEvents) this.storedAchievementEventsProvider.get());
    }

    public static void injectApiUrlProvider(AdvancedDebuggingFragment advancedDebuggingFragment, ApiUrlProvider apiUrlProvider2) {
        advancedDebuggingFragment.apiUrlProvider = apiUrlProvider2;
    }

    public static void injectMfpApiSettings(AdvancedDebuggingFragment advancedDebuggingFragment, MfpApiSettings mfpApiSettings) {
        advancedDebuggingFragment.mfpApiSettings = mfpApiSettings;
    }

    public static void injectAppSettings(AdvancedDebuggingFragment advancedDebuggingFragment, AppSettings appSettings) {
        advancedDebuggingFragment.appSettings = appSettings;
    }

    public static void injectAdsSettings(AdvancedDebuggingFragment advancedDebuggingFragment, AdsSettings adsSettings) {
        advancedDebuggingFragment.adsSettings = adsSettings;
    }

    public static void injectRequestInterceptor(AdvancedDebuggingFragment advancedDebuggingFragment, RequestInterceptor requestInterceptor) {
        advancedDebuggingFragment.requestInterceptor = requestInterceptor;
    }

    public static void injectNavigationHelper(AdvancedDebuggingFragment advancedDebuggingFragment, NavigationHelper navigationHelper) {
        advancedDebuggingFragment.navigationHelper = navigationHelper;
    }

    public static void injectSignUpService(AdvancedDebuggingFragment advancedDebuggingFragment, SignUpService signUpService) {
        advancedDebuggingFragment.signUpService = signUpService;
    }

    public static void injectSession(AdvancedDebuggingFragment advancedDebuggingFragment, Session session) {
        advancedDebuggingFragment.session = session;
    }

    public static void injectLocalSettingsService(AdvancedDebuggingFragment advancedDebuggingFragment, LocalSettingsService localSettingsService) {
        advancedDebuggingFragment.localSettingsService = localSettingsService;
    }

    public static void injectAuthTokenProvider(AdvancedDebuggingFragment advancedDebuggingFragment, AuthTokenProvider authTokenProvider2) {
        advancedDebuggingFragment.authTokenProvider = authTokenProvider2;
    }

    public static void injectUserApplicationSettingsService(AdvancedDebuggingFragment advancedDebuggingFragment, UserApplicationSettingsService userApplicationSettingsService) {
        advancedDebuggingFragment.userApplicationSettingsService = userApplicationSettingsService;
    }

    public static void injectGlobalSettingsService(AdvancedDebuggingFragment advancedDebuggingFragment, GlobalSettingsService globalSettingsService) {
        advancedDebuggingFragment.globalSettingsService = globalSettingsService;
    }

    public static void injectProductService(AdvancedDebuggingFragment advancedDebuggingFragment, ProductService productService) {
        advancedDebuggingFragment.productService = productService;
    }

    public static void injectFoodSearchActivityFactory(AdvancedDebuggingFragment advancedDebuggingFragment, FoodSearchActivityFactory foodSearchActivityFactory) {
        advancedDebuggingFragment.foodSearchActivityFactory = foodSearchActivityFactory;
    }

    public static void injectDebugSettingsService(AdvancedDebuggingFragment advancedDebuggingFragment, DebugSettingsService debugSettingsService) {
        advancedDebuggingFragment.debugSettingsService = debugSettingsService;
    }

    public static void injectStoredAchievementEvents(AdvancedDebuggingFragment advancedDebuggingFragment, StoredAchievementEvents storedAchievementEvents) {
        advancedDebuggingFragment.storedAchievementEvents = storedAchievementEvents;
    }
}

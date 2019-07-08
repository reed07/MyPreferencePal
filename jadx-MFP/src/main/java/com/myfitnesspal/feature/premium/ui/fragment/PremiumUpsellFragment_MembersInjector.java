package com.myfitnesspal.feature.premium.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.service.UpsellService;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class PremiumUpsellFragment_MembersInjector implements MembersInjector<PremiumUpsellFragment> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<GeoLocationService> geoLocationServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalSettingsService> localSettingsProvider;
    private final Provider<PaymentAnalyticsHelper> paymentAnalyticsProvider;
    private final Provider<PaymentService> paymentServiceProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<ProductService> productServiceProvider;
    private final Provider<UpsellService> upsellServiceProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;

    public PremiumUpsellFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LocalSettingsService> provider3, Provider<UpsellService> provider4, Provider<ProductService> provider5, Provider<PaymentService> provider6, Provider<GeoLocationService> provider7, Provider<ApiUrlProvider> provider8, Provider<AnalyticsService> provider9, Provider<ConfigService> provider10, Provider<PaymentAnalyticsHelper> provider11, Provider<PremiumService> provider12, Provider<UserApplicationSettingsService> provider13) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.localSettingsProvider = provider3;
        this.upsellServiceProvider = provider4;
        this.productServiceProvider = provider5;
        this.paymentServiceProvider = provider6;
        this.geoLocationServiceProvider = provider7;
        this.apiUrlProvider = provider8;
        this.analyticsProvider = provider9;
        this.configServiceProvider = provider10;
        this.paymentAnalyticsProvider = provider11;
        this.premiumServiceProvider = provider12;
        this.userApplicationSettingsServiceProvider = provider13;
    }

    public static MembersInjector<PremiumUpsellFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LocalSettingsService> provider3, Provider<UpsellService> provider4, Provider<ProductService> provider5, Provider<PaymentService> provider6, Provider<GeoLocationService> provider7, Provider<ApiUrlProvider> provider8, Provider<AnalyticsService> provider9, Provider<ConfigService> provider10, Provider<PaymentAnalyticsHelper> provider11, Provider<PremiumService> provider12, Provider<UserApplicationSettingsService> provider13) {
        PremiumUpsellFragment_MembersInjector premiumUpsellFragment_MembersInjector = new PremiumUpsellFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
        return premiumUpsellFragment_MembersInjector;
    }

    public void injectMembers(PremiumUpsellFragment premiumUpsellFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(premiumUpsellFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(premiumUpsellFragment, (Glide) this.glideProvider.get());
        injectLocalSettings(premiumUpsellFragment, DoubleCheck.lazy(this.localSettingsProvider));
        injectUpsellService(premiumUpsellFragment, DoubleCheck.lazy(this.upsellServiceProvider));
        injectProductService(premiumUpsellFragment, DoubleCheck.lazy(this.productServiceProvider));
        injectPaymentService(premiumUpsellFragment, DoubleCheck.lazy(this.paymentServiceProvider));
        injectGeoLocationService(premiumUpsellFragment, DoubleCheck.lazy(this.geoLocationServiceProvider));
        injectApiUrlProvider(premiumUpsellFragment, DoubleCheck.lazy(this.apiUrlProvider));
        injectAnalytics(premiumUpsellFragment, DoubleCheck.lazy(this.analyticsProvider));
        injectConfigService(premiumUpsellFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectPaymentAnalytics(premiumUpsellFragment, DoubleCheck.lazy(this.paymentAnalyticsProvider));
        injectPremiumService(premiumUpsellFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectUserApplicationSettingsService(premiumUpsellFragment, DoubleCheck.lazy(this.userApplicationSettingsServiceProvider));
    }

    public static void injectLocalSettings(PremiumUpsellFragment premiumUpsellFragment, Lazy<LocalSettingsService> lazy) {
        premiumUpsellFragment.localSettings = lazy;
    }

    public static void injectUpsellService(PremiumUpsellFragment premiumUpsellFragment, Lazy<UpsellService> lazy) {
        premiumUpsellFragment.upsellService = lazy;
    }

    public static void injectProductService(PremiumUpsellFragment premiumUpsellFragment, Lazy<ProductService> lazy) {
        premiumUpsellFragment.productService = lazy;
    }

    public static void injectPaymentService(PremiumUpsellFragment premiumUpsellFragment, Lazy<PaymentService> lazy) {
        premiumUpsellFragment.paymentService = lazy;
    }

    public static void injectGeoLocationService(PremiumUpsellFragment premiumUpsellFragment, Lazy<GeoLocationService> lazy) {
        premiumUpsellFragment.geoLocationService = lazy;
    }

    public static void injectApiUrlProvider(PremiumUpsellFragment premiumUpsellFragment, Lazy<ApiUrlProvider> lazy) {
        premiumUpsellFragment.apiUrlProvider = lazy;
    }

    public static void injectAnalytics(PremiumUpsellFragment premiumUpsellFragment, Lazy<AnalyticsService> lazy) {
        premiumUpsellFragment.analytics = lazy;
    }

    public static void injectConfigService(PremiumUpsellFragment premiumUpsellFragment, Lazy<ConfigService> lazy) {
        premiumUpsellFragment.configService = lazy;
    }

    public static void injectPaymentAnalytics(PremiumUpsellFragment premiumUpsellFragment, Lazy<PaymentAnalyticsHelper> lazy) {
        premiumUpsellFragment.paymentAnalytics = lazy;
    }

    public static void injectPremiumService(PremiumUpsellFragment premiumUpsellFragment, Lazy<PremiumService> lazy) {
        premiumUpsellFragment.premiumService = lazy;
    }

    public static void injectUserApplicationSettingsService(PremiumUpsellFragment premiumUpsellFragment, Lazy<UserApplicationSettingsService> lazy) {
        premiumUpsellFragment.userApplicationSettingsService = lazy;
    }
}

package com.myfitnesspal.feature.premium.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.service.UpsellService;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class PremiumUpsellWebFragment_MembersInjector implements MembersInjector<PremiumUpsellWebFragment> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<MfpApiSettings> apiSettingsProvider;
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

    public PremiumUpsellWebFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LocalSettingsService> provider3, Provider<UpsellService> provider4, Provider<ProductService> provider5, Provider<PaymentService> provider6, Provider<GeoLocationService> provider7, Provider<ApiUrlProvider> provider8, Provider<AnalyticsService> provider9, Provider<ConfigService> provider10, Provider<PaymentAnalyticsHelper> provider11, Provider<PremiumService> provider12, Provider<UserApplicationSettingsService> provider13, Provider<MfpApiSettings> provider14) {
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
        this.apiSettingsProvider = provider14;
    }

    public static MembersInjector<PremiumUpsellWebFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LocalSettingsService> provider3, Provider<UpsellService> provider4, Provider<ProductService> provider5, Provider<PaymentService> provider6, Provider<GeoLocationService> provider7, Provider<ApiUrlProvider> provider8, Provider<AnalyticsService> provider9, Provider<ConfigService> provider10, Provider<PaymentAnalyticsHelper> provider11, Provider<PremiumService> provider12, Provider<UserApplicationSettingsService> provider13, Provider<MfpApiSettings> provider14) {
        PremiumUpsellWebFragment_MembersInjector premiumUpsellWebFragment_MembersInjector = new PremiumUpsellWebFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
        return premiumUpsellWebFragment_MembersInjector;
    }

    public void injectMembers(PremiumUpsellWebFragment premiumUpsellWebFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(premiumUpsellWebFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(premiumUpsellWebFragment, (Glide) this.glideProvider.get());
        PremiumUpsellFragment_MembersInjector.injectLocalSettings(premiumUpsellWebFragment, DoubleCheck.lazy(this.localSettingsProvider));
        PremiumUpsellFragment_MembersInjector.injectUpsellService(premiumUpsellWebFragment, DoubleCheck.lazy(this.upsellServiceProvider));
        PremiumUpsellFragment_MembersInjector.injectProductService(premiumUpsellWebFragment, DoubleCheck.lazy(this.productServiceProvider));
        PremiumUpsellFragment_MembersInjector.injectPaymentService(premiumUpsellWebFragment, DoubleCheck.lazy(this.paymentServiceProvider));
        PremiumUpsellFragment_MembersInjector.injectGeoLocationService(premiumUpsellWebFragment, DoubleCheck.lazy(this.geoLocationServiceProvider));
        PremiumUpsellFragment_MembersInjector.injectApiUrlProvider(premiumUpsellWebFragment, DoubleCheck.lazy(this.apiUrlProvider));
        PremiumUpsellFragment_MembersInjector.injectAnalytics(premiumUpsellWebFragment, DoubleCheck.lazy(this.analyticsProvider));
        PremiumUpsellFragment_MembersInjector.injectConfigService(premiumUpsellWebFragment, DoubleCheck.lazy(this.configServiceProvider));
        PremiumUpsellFragment_MembersInjector.injectPaymentAnalytics(premiumUpsellWebFragment, DoubleCheck.lazy(this.paymentAnalyticsProvider));
        PremiumUpsellFragment_MembersInjector.injectPremiumService(premiumUpsellWebFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        PremiumUpsellFragment_MembersInjector.injectUserApplicationSettingsService(premiumUpsellWebFragment, DoubleCheck.lazy(this.userApplicationSettingsServiceProvider));
        injectApiSettings(premiumUpsellWebFragment, (MfpApiSettings) this.apiSettingsProvider.get());
    }

    public static void injectApiSettings(PremiumUpsellWebFragment premiumUpsellWebFragment, MfpApiSettings mfpApiSettings) {
        premiumUpsellWebFragment.apiSettings = mfpApiSettings;
    }
}

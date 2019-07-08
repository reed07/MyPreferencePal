package com.myfitnesspal.feature.premium.service;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ProductServiceImpl_Factory implements Factory<ProductServiceImpl> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<MfpApiSettings> apiSettingsProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<GeoLocationService> geoLocationServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<SharedPreferences> prefsProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;

    public ProductServiceImpl_Factory(Provider<SharedPreferences> provider, Provider<PremiumService> provider2, Provider<GeoLocationService> provider3, Provider<MfpV2Api> provider4, Provider<Session> provider5, Provider<ConfigService> provider6, Provider<MfpApiSettings> provider7, Provider<LocalSettingsService> provider8, Provider<UserApplicationSettingsService> provider9) {
        this.prefsProvider = provider;
        this.premiumServiceProvider = provider2;
        this.geoLocationServiceProvider = provider3;
        this.apiProvider = provider4;
        this.sessionProvider = provider5;
        this.configServiceProvider = provider6;
        this.apiSettingsProvider = provider7;
        this.localSettingsServiceProvider = provider8;
        this.userApplicationSettingsServiceProvider = provider9;
    }

    public ProductServiceImpl get() {
        return provideInstance(this.prefsProvider, this.premiumServiceProvider, this.geoLocationServiceProvider, this.apiProvider, this.sessionProvider, this.configServiceProvider, this.apiSettingsProvider, this.localSettingsServiceProvider, this.userApplicationSettingsServiceProvider);
    }

    public static ProductServiceImpl provideInstance(Provider<SharedPreferences> provider, Provider<PremiumService> provider2, Provider<GeoLocationService> provider3, Provider<MfpV2Api> provider4, Provider<Session> provider5, Provider<ConfigService> provider6, Provider<MfpApiSettings> provider7, Provider<LocalSettingsService> provider8, Provider<UserApplicationSettingsService> provider9) {
        ProductServiceImpl productServiceImpl = new ProductServiceImpl((SharedPreferences) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), provider4, DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), (MfpApiSettings) provider7.get(), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9));
        return productServiceImpl;
    }

    public static ProductServiceImpl_Factory create(Provider<SharedPreferences> provider, Provider<PremiumService> provider2, Provider<GeoLocationService> provider3, Provider<MfpV2Api> provider4, Provider<Session> provider5, Provider<ConfigService> provider6, Provider<MfpApiSettings> provider7, Provider<LocalSettingsService> provider8, Provider<UserApplicationSettingsService> provider9) {
        ProductServiceImpl_Factory productServiceImpl_Factory = new ProductServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
        return productServiceImpl_Factory;
    }

    public static ProductServiceImpl newProductServiceImpl(SharedPreferences sharedPreferences, Lazy<PremiumService> lazy, Lazy<GeoLocationService> lazy2, Provider<MfpV2Api> provider, Lazy<Session> lazy3, Lazy<ConfigService> lazy4, MfpApiSettings mfpApiSettings, Lazy<LocalSettingsService> lazy5, Lazy<UserApplicationSettingsService> lazy6) {
        ProductServiceImpl productServiceImpl = new ProductServiceImpl(sharedPreferences, lazy, lazy2, provider, lazy3, lazy4, mfpApiSettings, lazy5, lazy6);
        return productServiceImpl;
    }
}

package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidePaymentAnalyticsHelperFactory implements Factory<PaymentAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<GeoLocationService> geoLocationServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final ApplicationModule module;
    private final Provider<PaymentService> paymentServiceProvider;
    private final Provider<ProductService> productServiceProvider;

    public ApplicationModule_ProvidePaymentAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<AppSettings> provider3, Provider<AnalyticsService> provider4, Provider<GeoLocationService> provider5, Provider<CountryService> provider6, Provider<PaymentService> provider7, Provider<ProductService> provider8, Provider<LocalSettingsService> provider9) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.configServiceProvider = provider2;
        this.appSettingsProvider = provider3;
        this.analyticsProvider = provider4;
        this.geoLocationServiceProvider = provider5;
        this.countryServiceProvider = provider6;
        this.paymentServiceProvider = provider7;
        this.productServiceProvider = provider8;
        this.localSettingsServiceProvider = provider9;
    }

    public PaymentAnalyticsHelper get() {
        return provideInstance(this.module, this.contextProvider, this.configServiceProvider, this.appSettingsProvider, this.analyticsProvider, this.geoLocationServiceProvider, this.countryServiceProvider, this.paymentServiceProvider, this.productServiceProvider, this.localSettingsServiceProvider);
    }

    public static PaymentAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<AppSettings> provider3, Provider<AnalyticsService> provider4, Provider<GeoLocationService> provider5, Provider<CountryService> provider6, Provider<PaymentService> provider7, Provider<ProductService> provider8, Provider<LocalSettingsService> provider9) {
        return proxyProvidePaymentAnalyticsHelper(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9));
    }

    public static ApplicationModule_ProvidePaymentAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<AppSettings> provider3, Provider<AnalyticsService> provider4, Provider<GeoLocationService> provider5, Provider<CountryService> provider6, Provider<PaymentService> provider7, Provider<ProductService> provider8, Provider<LocalSettingsService> provider9) {
        ApplicationModule_ProvidePaymentAnalyticsHelperFactory applicationModule_ProvidePaymentAnalyticsHelperFactory = new ApplicationModule_ProvidePaymentAnalyticsHelperFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
        return applicationModule_ProvidePaymentAnalyticsHelperFactory;
    }

    public static PaymentAnalyticsHelper proxyProvidePaymentAnalyticsHelper(ApplicationModule applicationModule, Context context, Lazy<ConfigService> lazy, Lazy<AppSettings> lazy2, Lazy<AnalyticsService> lazy3, Lazy<GeoLocationService> lazy4, Lazy<CountryService> lazy5, Lazy<PaymentService> lazy6, Lazy<ProductService> lazy7, Lazy<LocalSettingsService> lazy8) {
        return (PaymentAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providePaymentAnalyticsHelper(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8), "Cannot return null from a non-@Nullable @Provides method");
    }
}

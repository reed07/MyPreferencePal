package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ServiceModule_ProvidesPremiumServiceFactory implements Factory<PremiumService> {
    private final Provider<Bus> busProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<PremiumFeatureOverrides> featureOverridesProvider;
    private final Provider<GlobalSettingsService> globalSettingsProvider;
    private final ServiceModule module;
    private final Provider<ProductService> productServiceProvider;
    private final Provider<SubscriptionService> subscriptionServiceProvider;

    public ServiceModule_ProvidesPremiumServiceFactory(ServiceModule serviceModule, Provider<ConfigService> provider, Provider<SubscriptionService> provider2, Provider<ProductService> provider3, Provider<PremiumFeatureOverrides> provider4, Provider<GlobalSettingsService> provider5, Provider<Bus> provider6) {
        this.module = serviceModule;
        this.configServiceProvider = provider;
        this.subscriptionServiceProvider = provider2;
        this.productServiceProvider = provider3;
        this.featureOverridesProvider = provider4;
        this.globalSettingsProvider = provider5;
        this.busProvider = provider6;
    }

    public PremiumService get() {
        return provideInstance(this.module, this.configServiceProvider, this.subscriptionServiceProvider, this.productServiceProvider, this.featureOverridesProvider, this.globalSettingsProvider, this.busProvider);
    }

    public static PremiumService provideInstance(ServiceModule serviceModule, Provider<ConfigService> provider, Provider<SubscriptionService> provider2, Provider<ProductService> provider3, Provider<PremiumFeatureOverrides> provider4, Provider<GlobalSettingsService> provider5, Provider<Bus> provider6) {
        return proxyProvidesPremiumService(serviceModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static ServiceModule_ProvidesPremiumServiceFactory create(ServiceModule serviceModule, Provider<ConfigService> provider, Provider<SubscriptionService> provider2, Provider<ProductService> provider3, Provider<PremiumFeatureOverrides> provider4, Provider<GlobalSettingsService> provider5, Provider<Bus> provider6) {
        ServiceModule_ProvidesPremiumServiceFactory serviceModule_ProvidesPremiumServiceFactory = new ServiceModule_ProvidesPremiumServiceFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6);
        return serviceModule_ProvidesPremiumServiceFactory;
    }

    public static PremiumService proxyProvidesPremiumService(ServiceModule serviceModule, Lazy<ConfigService> lazy, Lazy<SubscriptionService> lazy2, Lazy<ProductService> lazy3, Lazy<PremiumFeatureOverrides> lazy4, Lazy<GlobalSettingsService> lazy5, Lazy<Bus> lazy6) {
        return (PremiumService) Preconditions.checkNotNull(serviceModule.providesPremiumService(lazy, lazy2, lazy3, lazy4, lazy5, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideAdUnitServiceFactory implements Factory<AdUnitService> {
    private final Provider<AdsSettings> adSettingsProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<UacfConfigurationUtil> uacfConfigurationUtilProvider;

    public ApplicationModule_ProvideAdUnitServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<AdsSettings> provider2, Provider<UacfConfigurationUtil> provider3) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.adSettingsProvider = provider2;
        this.uacfConfigurationUtilProvider = provider3;
    }

    public AdUnitService get() {
        return provideInstance(this.module, this.contextProvider, this.adSettingsProvider, this.uacfConfigurationUtilProvider);
    }

    public static AdUnitService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<AdsSettings> provider2, Provider<UacfConfigurationUtil> provider3) {
        return proxyProvideAdUnitService(applicationModule, (Context) provider.get(), (AdsSettings) provider2.get(), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvideAdUnitServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<AdsSettings> provider2, Provider<UacfConfigurationUtil> provider3) {
        return new ApplicationModule_ProvideAdUnitServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static AdUnitService proxyProvideAdUnitService(ApplicationModule applicationModule, Context context, AdsSettings adsSettings, Lazy<UacfConfigurationUtil> lazy) {
        return (AdUnitService) Preconditions.checkNotNull(applicationModule.provideAdUnitService(context, adsSettings, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}

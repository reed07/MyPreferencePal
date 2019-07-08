package com.myfitnesspal.shared.service.ads;

import android.content.Context;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AdUnitServiceImpl_Factory implements Factory<AdUnitServiceImpl> {
    private final Provider<AdsSettings> adSettingsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<UacfConfigurationUtil> uacfConfigurationUtilProvider;

    public AdUnitServiceImpl_Factory(Provider<Context> provider, Provider<AdsSettings> provider2, Provider<UacfConfigurationUtil> provider3) {
        this.contextProvider = provider;
        this.adSettingsProvider = provider2;
        this.uacfConfigurationUtilProvider = provider3;
    }

    public AdUnitServiceImpl get() {
        return provideInstance(this.contextProvider, this.adSettingsProvider, this.uacfConfigurationUtilProvider);
    }

    public static AdUnitServiceImpl provideInstance(Provider<Context> provider, Provider<AdsSettings> provider2, Provider<UacfConfigurationUtil> provider3) {
        return new AdUnitServiceImpl((Context) provider.get(), (AdsSettings) provider2.get(), DoubleCheck.lazy(provider3));
    }

    public static AdUnitServiceImpl_Factory create(Provider<Context> provider, Provider<AdsSettings> provider2, Provider<UacfConfigurationUtil> provider3) {
        return new AdUnitServiceImpl_Factory(provider, provider2, provider3);
    }

    public static AdUnitServiceImpl newAdUnitServiceImpl(Context context, AdsSettings adsSettings, Lazy<UacfConfigurationUtil> lazy) {
        return new AdUnitServiceImpl(context, adsSettings, lazy);
    }
}

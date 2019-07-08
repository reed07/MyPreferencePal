package com.myfitnesspal.feature.nutrition.service;

import android.content.Context;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NutritionDetailsServiceImpl_Factory implements Factory<NutritionDetailsServiceImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<Boolean> isPremiumAvailableProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;

    public NutritionDetailsServiceImpl_Factory(Provider<Context> provider, Provider<LocalSettingsService> provider2, Provider<Boolean> provider3) {
        this.contextProvider = provider;
        this.localSettingsServiceProvider = provider2;
        this.isPremiumAvailableProvider = provider3;
    }

    public NutritionDetailsServiceImpl get() {
        return provideInstance(this.contextProvider, this.localSettingsServiceProvider, this.isPremiumAvailableProvider);
    }

    public static NutritionDetailsServiceImpl provideInstance(Provider<Context> provider, Provider<LocalSettingsService> provider2, Provider<Boolean> provider3) {
        return new NutritionDetailsServiceImpl((Context) provider.get(), DoubleCheck.lazy(provider2), ((Boolean) provider3.get()).booleanValue());
    }

    public static NutritionDetailsServiceImpl_Factory create(Provider<Context> provider, Provider<LocalSettingsService> provider2, Provider<Boolean> provider3) {
        return new NutritionDetailsServiceImpl_Factory(provider, provider2, provider3);
    }

    public static NutritionDetailsServiceImpl newNutritionDetailsServiceImpl(Context context, Lazy<LocalSettingsService> lazy, boolean z) {
        return new NutritionDetailsServiceImpl(context, lazy, z);
    }
}

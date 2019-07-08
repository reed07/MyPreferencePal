package com.myfitnesspal.shared.service.install;

import android.content.Context;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.util.ResourceUtils;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CountryServiceImpl_Factory implements Factory<CountryServiceImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<GlobalSettingsService> globalSettingsProvider;
    private final Provider<ResourceUtils> resourceUtilsProvider;

    public CountryServiceImpl_Factory(Provider<Context> provider, Provider<ResourceUtils> provider2, Provider<GlobalSettingsService> provider3) {
        this.contextProvider = provider;
        this.resourceUtilsProvider = provider2;
        this.globalSettingsProvider = provider3;
    }

    public CountryServiceImpl get() {
        return provideInstance(this.contextProvider, this.resourceUtilsProvider, this.globalSettingsProvider);
    }

    public static CountryServiceImpl provideInstance(Provider<Context> provider, Provider<ResourceUtils> provider2, Provider<GlobalSettingsService> provider3) {
        return new CountryServiceImpl((Context) provider.get(), (ResourceUtils) provider2.get(), DoubleCheck.lazy(provider3));
    }

    public static CountryServiceImpl_Factory create(Provider<Context> provider, Provider<ResourceUtils> provider2, Provider<GlobalSettingsService> provider3) {
        return new CountryServiceImpl_Factory(provider, provider2, provider3);
    }

    public static CountryServiceImpl newCountryServiceImpl(Context context, ResourceUtils resourceUtils, Lazy<GlobalSettingsService> lazy) {
        return new CountryServiceImpl(context, resourceUtils, lazy);
    }
}

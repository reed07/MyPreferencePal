package com.myfitnesspal.shared.deeplink;

import android.content.Context;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DeepLinkManagerImpl_Factory implements Factory<DeepLinkManagerImpl> {
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;

    public DeepLinkManagerImpl_Factory(Provider<Context> provider, Provider<AppSettings> provider2, Provider<NavigationHelper> provider3) {
        this.contextProvider = provider;
        this.appSettingsProvider = provider2;
        this.navigationHelperProvider = provider3;
    }

    public DeepLinkManagerImpl get() {
        return provideInstance(this.contextProvider, this.appSettingsProvider, this.navigationHelperProvider);
    }

    public static DeepLinkManagerImpl provideInstance(Provider<Context> provider, Provider<AppSettings> provider2, Provider<NavigationHelper> provider3) {
        return new DeepLinkManagerImpl((Context) provider.get(), (AppSettings) provider2.get(), (NavigationHelper) provider3.get());
    }

    public static DeepLinkManagerImpl_Factory create(Provider<Context> provider, Provider<AppSettings> provider2, Provider<NavigationHelper> provider3) {
        return new DeepLinkManagerImpl_Factory(provider, provider2, provider3);
    }

    public static DeepLinkManagerImpl newDeepLinkManagerImpl(Context context, AppSettings appSettings, NavigationHelper navigationHelper) {
        return new DeepLinkManagerImpl(context, appSettings, navigationHelper);
    }
}

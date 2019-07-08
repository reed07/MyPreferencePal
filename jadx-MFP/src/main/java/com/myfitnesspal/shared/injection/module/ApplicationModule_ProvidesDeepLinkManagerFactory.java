package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesDeepLinkManagerFactory implements Factory<DeepLinkManager> {
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<NavigationHelper> navigationHelperProvider;

    public ApplicationModule_ProvidesDeepLinkManagerFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<NavigationHelper> provider3) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.appSettingsProvider = provider2;
        this.navigationHelperProvider = provider3;
    }

    public DeepLinkManager get() {
        return provideInstance(this.module, this.contextProvider, this.appSettingsProvider, this.navigationHelperProvider);
    }

    public static DeepLinkManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<NavigationHelper> provider3) {
        return proxyProvidesDeepLinkManager(applicationModule, (Context) provider.get(), (AppSettings) provider2.get(), (NavigationHelper) provider3.get());
    }

    public static ApplicationModule_ProvidesDeepLinkManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<NavigationHelper> provider3) {
        return new ApplicationModule_ProvidesDeepLinkManagerFactory(applicationModule, provider, provider2, provider3);
    }

    public static DeepLinkManager proxyProvidesDeepLinkManager(ApplicationModule applicationModule, Context context, AppSettings appSettings, NavigationHelper navigationHelper) {
        return (DeepLinkManager) Preconditions.checkNotNull(applicationModule.providesDeepLinkManager(context, appSettings, navigationHelper), "Cannot return null from a non-@Nullable @Provides method");
    }
}

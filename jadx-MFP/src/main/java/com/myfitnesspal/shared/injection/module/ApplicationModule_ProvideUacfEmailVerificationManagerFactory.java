package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import com.myfitnesspal.shared.uacf.UacfEmailVerificationManager;
import com.myfitnesspal.shared.uacf.UacfRolloutUtil;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.identity.sdk.UacfIdentitySdk;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.uacf.thumbprint.ui.sdk.ClientEmailVerificationStatus;
import javax.inject.Provider;

public final class ApplicationModule_ProvideUacfEmailVerificationManagerFactory implements Factory<UacfEmailVerificationManager> {
    private final Provider<UacfConfigurationUtil> configurationUtilProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<UacfIdentitySdk> identitySdkProvider;
    private final ApplicationModule module;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<ClientEmailVerificationStatus> refreshClientEmailVerificationStatusProvider;
    private final Provider<UacfRolloutUtil> rolloutUtilProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public ApplicationModule_ProvideUacfEmailVerificationManagerFactory(ApplicationModule applicationModule, Provider<Session> provider, Provider<UacfIdentitySdk> provider2, Provider<UacfRolloutUtil> provider3, Provider<UacfConfigurationUtil> provider4, Provider<SharedPreferences> provider5, Provider<ClientEmailVerificationStatus> provider6, Provider<CountryService> provider7, Provider<NavigationHelper> provider8) {
        this.module = applicationModule;
        this.sessionProvider = provider;
        this.identitySdkProvider = provider2;
        this.rolloutUtilProvider = provider3;
        this.configurationUtilProvider = provider4;
        this.sharedPreferencesProvider = provider5;
        this.refreshClientEmailVerificationStatusProvider = provider6;
        this.countryServiceProvider = provider7;
        this.navigationHelperProvider = provider8;
    }

    public UacfEmailVerificationManager get() {
        return provideInstance(this.module, this.sessionProvider, this.identitySdkProvider, this.rolloutUtilProvider, this.configurationUtilProvider, this.sharedPreferencesProvider, this.refreshClientEmailVerificationStatusProvider, this.countryServiceProvider, this.navigationHelperProvider);
    }

    public static UacfEmailVerificationManager provideInstance(ApplicationModule applicationModule, Provider<Session> provider, Provider<UacfIdentitySdk> provider2, Provider<UacfRolloutUtil> provider3, Provider<UacfConfigurationUtil> provider4, Provider<SharedPreferences> provider5, Provider<ClientEmailVerificationStatus> provider6, Provider<CountryService> provider7, Provider<NavigationHelper> provider8) {
        return proxyProvideUacfEmailVerificationManager(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), (SharedPreferences) provider5.get(), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8));
    }

    public static ApplicationModule_ProvideUacfEmailVerificationManagerFactory create(ApplicationModule applicationModule, Provider<Session> provider, Provider<UacfIdentitySdk> provider2, Provider<UacfRolloutUtil> provider3, Provider<UacfConfigurationUtil> provider4, Provider<SharedPreferences> provider5, Provider<ClientEmailVerificationStatus> provider6, Provider<CountryService> provider7, Provider<NavigationHelper> provider8) {
        ApplicationModule_ProvideUacfEmailVerificationManagerFactory applicationModule_ProvideUacfEmailVerificationManagerFactory = new ApplicationModule_ProvideUacfEmailVerificationManagerFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return applicationModule_ProvideUacfEmailVerificationManagerFactory;
    }

    public static UacfEmailVerificationManager proxyProvideUacfEmailVerificationManager(ApplicationModule applicationModule, Lazy<Session> lazy, Lazy<UacfIdentitySdk> lazy2, Lazy<UacfRolloutUtil> lazy3, Lazy<UacfConfigurationUtil> lazy4, SharedPreferences sharedPreferences, Lazy<ClientEmailVerificationStatus> lazy5, Lazy<CountryService> lazy6, Lazy<NavigationHelper> lazy7) {
        return (UacfEmailVerificationManager) Preconditions.checkNotNull(applicationModule.provideUacfEmailVerificationManager(lazy, lazy2, lazy3, lazy4, sharedPreferences, lazy5, lazy6, lazy7), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.myfitnesspal.shared.injection.module;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideUserAgentFactory implements Factory<UserAgentProvider> {
    private final Provider<String> apiClientIdProvider;
    private final Provider<Application> appProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<TelephonyManager> telephonyManagerProvider;

    public ApplicationModule_ProvideUserAgentFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Application> provider2, Provider<TelephonyManager> provider3, Provider<String> provider4) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.appProvider = provider2;
        this.telephonyManagerProvider = provider3;
        this.apiClientIdProvider = provider4;
    }

    public UserAgentProvider get() {
        return provideInstance(this.module, this.contextProvider, this.appProvider, this.telephonyManagerProvider, this.apiClientIdProvider);
    }

    public static UserAgentProvider provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Application> provider2, Provider<TelephonyManager> provider3, Provider<String> provider4) {
        return proxyProvideUserAgent(applicationModule, (Context) provider.get(), (Application) provider2.get(), (TelephonyManager) provider3.get(), (String) provider4.get());
    }

    public static ApplicationModule_ProvideUserAgentFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Application> provider2, Provider<TelephonyManager> provider3, Provider<String> provider4) {
        ApplicationModule_ProvideUserAgentFactory applicationModule_ProvideUserAgentFactory = new ApplicationModule_ProvideUserAgentFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvideUserAgentFactory;
    }

    public static UserAgentProvider proxyProvideUserAgent(ApplicationModule applicationModule, Context context, Application application, TelephonyManager telephonyManager, String str) {
        return (UserAgentProvider) Preconditions.checkNotNull(applicationModule.provideUserAgent(context, application, telephonyManager, str), "Cannot return null from a non-@Nullable @Provides method");
    }
}

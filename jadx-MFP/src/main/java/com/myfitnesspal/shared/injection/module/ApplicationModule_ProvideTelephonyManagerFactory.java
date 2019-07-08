package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.telephony.TelephonyManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideTelephonyManagerFactory implements Factory<TelephonyManager> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideTelephonyManagerFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public TelephonyManager get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static TelephonyManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvideTelephonyManager(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvideTelephonyManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvideTelephonyManagerFactory(applicationModule, provider);
    }

    public static TelephonyManager proxyProvideTelephonyManager(ApplicationModule applicationModule, Context context) {
        return (TelephonyManager) Preconditions.checkNotNull(applicationModule.provideTelephonyManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

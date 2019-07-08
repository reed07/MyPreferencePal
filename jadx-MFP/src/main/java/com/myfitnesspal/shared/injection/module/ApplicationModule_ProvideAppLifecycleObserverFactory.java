package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.ads.AdIdConsentCompliant;
import com.myfitnesspal.shared.service.lifecycle.AppLifecycleObserver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideAppLifecycleObserverFactory implements Factory<AppLifecycleObserver> {
    private final Provider<AdIdConsentCompliant> adIdConsentCompliantProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideAppLifecycleObserverFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<AdIdConsentCompliant> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.adIdConsentCompliantProvider = provider2;
    }

    public AppLifecycleObserver get() {
        return provideInstance(this.module, this.contextProvider, this.adIdConsentCompliantProvider);
    }

    public static AppLifecycleObserver provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<AdIdConsentCompliant> provider2) {
        return proxyProvideAppLifecycleObserver(applicationModule, (Context) provider.get(), (AdIdConsentCompliant) provider2.get());
    }

    public static ApplicationModule_ProvideAppLifecycleObserverFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<AdIdConsentCompliant> provider2) {
        return new ApplicationModule_ProvideAppLifecycleObserverFactory(applicationModule, provider, provider2);
    }

    public static AppLifecycleObserver proxyProvideAppLifecycleObserver(ApplicationModule applicationModule, Context context, AdIdConsentCompliant adIdConsentCompliant) {
        return (AppLifecycleObserver) Preconditions.checkNotNull(applicationModule.provideAppLifecycleObserver(context, adIdConsentCompliant), "Cannot return null from a non-@Nullable @Provides method");
    }
}

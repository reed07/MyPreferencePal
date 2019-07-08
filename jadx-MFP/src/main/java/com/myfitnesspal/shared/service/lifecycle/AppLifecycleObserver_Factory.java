package com.myfitnesspal.shared.service.lifecycle;

import android.content.Context;
import com.myfitnesspal.shared.service.ads.AdIdConsentCompliant;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AppLifecycleObserver_Factory implements Factory<AppLifecycleObserver> {
    private final Provider<AdIdConsentCompliant> adIdConsentCompliantProvider;
    private final Provider<Context> contextProvider;

    public AppLifecycleObserver_Factory(Provider<Context> provider, Provider<AdIdConsentCompliant> provider2) {
        this.contextProvider = provider;
        this.adIdConsentCompliantProvider = provider2;
    }

    public AppLifecycleObserver get() {
        return provideInstance(this.contextProvider, this.adIdConsentCompliantProvider);
    }

    public static AppLifecycleObserver provideInstance(Provider<Context> provider, Provider<AdIdConsentCompliant> provider2) {
        return new AppLifecycleObserver((Context) provider.get(), (AdIdConsentCompliant) provider2.get());
    }

    public static AppLifecycleObserver_Factory create(Provider<Context> provider, Provider<AdIdConsentCompliant> provider2) {
        return new AppLifecycleObserver_Factory(provider, provider2);
    }

    public static AppLifecycleObserver newAppLifecycleObserver(Context context, AdIdConsentCompliant adIdConsentCompliant) {
        return new AppLifecycleObserver(context, adIdConsentCompliant);
    }
}

package com.myfitnesspal.app;

import com.myfitnesspal.shared.service.lifecycle.AppLifecycleObserver;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MyFitnessPalApp_MembersInjector implements MembersInjector<MyFitnessPalApp> {
    private final Provider<AppLifecycleObserver> appLifecycleObserverProvider;

    public MyFitnessPalApp_MembersInjector(Provider<AppLifecycleObserver> provider) {
        this.appLifecycleObserverProvider = provider;
    }

    public static MembersInjector<MyFitnessPalApp> create(Provider<AppLifecycleObserver> provider) {
        return new MyFitnessPalApp_MembersInjector(provider);
    }

    public void injectMembers(MyFitnessPalApp myFitnessPalApp) {
        injectAppLifecycleObserver(myFitnessPalApp, (AppLifecycleObserver) this.appLifecycleObserverProvider.get());
    }

    public static void injectAppLifecycleObserver(MyFitnessPalApp myFitnessPalApp, AppLifecycleObserver appLifecycleObserver) {
        myFitnessPalApp.appLifecycleObserver = appLifecycleObserver;
    }
}

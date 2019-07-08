package com.myfitnesspal.feature.home.service;

import android.content.Context;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AppRatingService_Factory implements Factory<AppRatingService> {
    private final Provider<Context> contextProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<Session> sessionProvider;

    public AppRatingService_Factory(Provider<Context> provider, Provider<Session> provider2, Provider<LocalSettingsService> provider3, Provider<GlobalSettingsService> provider4) {
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.localSettingsServiceProvider = provider3;
        this.globalSettingsServiceProvider = provider4;
    }

    public AppRatingService get() {
        return provideInstance(this.contextProvider, this.sessionProvider, this.localSettingsServiceProvider, this.globalSettingsServiceProvider);
    }

    public static AppRatingService provideInstance(Provider<Context> provider, Provider<Session> provider2, Provider<LocalSettingsService> provider3, Provider<GlobalSettingsService> provider4) {
        return new AppRatingService((Context) provider.get(), (Session) provider2.get(), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static AppRatingService_Factory create(Provider<Context> provider, Provider<Session> provider2, Provider<LocalSettingsService> provider3, Provider<GlobalSettingsService> provider4) {
        return new AppRatingService_Factory(provider, provider2, provider3, provider4);
    }

    public static AppRatingService newAppRatingService(Context context, Session session, Lazy<LocalSettingsService> lazy, Lazy<GlobalSettingsService> lazy2) {
        return new AppRatingService(context, session, lazy, lazy2);
    }
}

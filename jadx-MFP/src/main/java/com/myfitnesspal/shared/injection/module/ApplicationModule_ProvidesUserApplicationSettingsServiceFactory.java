package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesUserApplicationSettingsServiceFactory implements Factory<UserApplicationSettingsService> {
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<SQLiteDatabaseWrapper> mainDatabaseProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<MfpV2Api> userApiProvider;

    public ApplicationModule_ProvidesUserApplicationSettingsServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<SQLiteDatabaseWrapper> provider2, Provider<LocalSettingsService> provider3, Provider<Session> provider4) {
        this.module = applicationModule;
        this.userApiProvider = provider;
        this.mainDatabaseProvider = provider2;
        this.localSettingsServiceProvider = provider3;
        this.sessionProvider = provider4;
    }

    public UserApplicationSettingsService get() {
        return provideInstance(this.module, this.userApiProvider, this.mainDatabaseProvider, this.localSettingsServiceProvider, this.sessionProvider);
    }

    public static UserApplicationSettingsService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<SQLiteDatabaseWrapper> provider2, Provider<LocalSettingsService> provider3, Provider<Session> provider4) {
        return proxyProvidesUserApplicationSettingsService(applicationModule, provider, DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), (Session) provider4.get());
    }

    public static ApplicationModule_ProvidesUserApplicationSettingsServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<SQLiteDatabaseWrapper> provider2, Provider<LocalSettingsService> provider3, Provider<Session> provider4) {
        ApplicationModule_ProvidesUserApplicationSettingsServiceFactory applicationModule_ProvidesUserApplicationSettingsServiceFactory = new ApplicationModule_ProvidesUserApplicationSettingsServiceFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesUserApplicationSettingsServiceFactory;
    }

    public static UserApplicationSettingsService proxyProvidesUserApplicationSettingsService(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Lazy<SQLiteDatabaseWrapper> lazy, Lazy<LocalSettingsService> lazy2, Session session) {
        return (UserApplicationSettingsService) Preconditions.checkNotNull(applicationModule.providesUserApplicationSettingsService(provider, lazy, lazy2, session), "Cannot return null from a non-@Nullable @Provides method");
    }
}

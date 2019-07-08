package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.GlobalAppPreferenceMigrationUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideGlobalAppPreferenceMigrationUtilFactory implements Factory<GlobalAppPreferenceMigrationUtil> {
    private final Provider<Context> contextProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvideGlobalAppPreferenceMigrationUtilFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<LocalSettingsService> provider2, Provider<GlobalSettingsService> provider3, Provider<Session> provider4) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.localSettingsServiceProvider = provider2;
        this.globalSettingsServiceProvider = provider3;
        this.sessionProvider = provider4;
    }

    public GlobalAppPreferenceMigrationUtil get() {
        return provideInstance(this.module, this.contextProvider, this.localSettingsServiceProvider, this.globalSettingsServiceProvider, this.sessionProvider);
    }

    public static GlobalAppPreferenceMigrationUtil provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<LocalSettingsService> provider2, Provider<GlobalSettingsService> provider3, Provider<Session> provider4) {
        return proxyProvideGlobalAppPreferenceMigrationUtil(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static ApplicationModule_ProvideGlobalAppPreferenceMigrationUtilFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<LocalSettingsService> provider2, Provider<GlobalSettingsService> provider3, Provider<Session> provider4) {
        ApplicationModule_ProvideGlobalAppPreferenceMigrationUtilFactory applicationModule_ProvideGlobalAppPreferenceMigrationUtilFactory = new ApplicationModule_ProvideGlobalAppPreferenceMigrationUtilFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvideGlobalAppPreferenceMigrationUtilFactory;
    }

    public static GlobalAppPreferenceMigrationUtil proxyProvideGlobalAppPreferenceMigrationUtil(ApplicationModule applicationModule, Context context, Lazy<LocalSettingsService> lazy, Lazy<GlobalSettingsService> lazy2, Lazy<Session> lazy3) {
        return (GlobalAppPreferenceMigrationUtil) Preconditions.checkNotNull(applicationModule.provideGlobalAppPreferenceMigrationUtil(context, lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}

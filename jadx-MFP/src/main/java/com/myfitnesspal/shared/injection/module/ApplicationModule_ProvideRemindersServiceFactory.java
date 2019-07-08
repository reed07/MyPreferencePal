package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.RemindersTable;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideRemindersServiceFactory implements Factory<RemindersService> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final ApplicationModule module;
    private final Provider<RemindersTable> remindersTableProvider;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvideRemindersServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<RemindersTable> provider2, Provider<ConfigService> provider3, Provider<Session> provider4, Provider<LocalizedStringsUtil> provider5, Provider<DbConnectionManager> provider6) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.remindersTableProvider = provider2;
        this.configServiceProvider = provider3;
        this.sessionProvider = provider4;
        this.localizedStringsUtilProvider = provider5;
        this.dbConnectionManagerProvider = provider6;
    }

    public RemindersService get() {
        return provideInstance(this.module, this.contextProvider, this.remindersTableProvider, this.configServiceProvider, this.sessionProvider, this.localizedStringsUtilProvider, this.dbConnectionManagerProvider);
    }

    public static RemindersService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<RemindersTable> provider2, Provider<ConfigService> provider3, Provider<Session> provider4, Provider<LocalizedStringsUtil> provider5, Provider<DbConnectionManager> provider6) {
        return proxyProvideRemindersService(applicationModule, (Context) provider.get(), (RemindersTable) provider2.get(), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static ApplicationModule_ProvideRemindersServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<RemindersTable> provider2, Provider<ConfigService> provider3, Provider<Session> provider4, Provider<LocalizedStringsUtil> provider5, Provider<DbConnectionManager> provider6) {
        ApplicationModule_ProvideRemindersServiceFactory applicationModule_ProvideRemindersServiceFactory = new ApplicationModule_ProvideRemindersServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6);
        return applicationModule_ProvideRemindersServiceFactory;
    }

    public static RemindersService proxyProvideRemindersService(ApplicationModule applicationModule, Context context, RemindersTable remindersTable, Lazy<ConfigService> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<DbConnectionManager> lazy4) {
        return (RemindersService) Preconditions.checkNotNull(applicationModule.provideRemindersService(context, remindersTable, lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}

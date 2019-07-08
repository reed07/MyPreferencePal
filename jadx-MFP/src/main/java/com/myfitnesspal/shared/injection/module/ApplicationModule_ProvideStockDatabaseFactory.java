package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideStockDatabaseFactory implements Factory<SQLiteDatabaseWrapper> {
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<DbConnectionManager> connectionManagerProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideStockDatabaseFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<DbConnectionManager> provider2, Provider<AppSettings> provider3) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.connectionManagerProvider = provider2;
        this.appSettingsProvider = provider3;
    }

    public SQLiteDatabaseWrapper get() {
        return provideInstance(this.module, this.contextProvider, this.connectionManagerProvider, this.appSettingsProvider);
    }

    public static SQLiteDatabaseWrapper provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<DbConnectionManager> provider2, Provider<AppSettings> provider3) {
        return proxyProvideStockDatabase(applicationModule, (Context) provider.get(), (DbConnectionManager) provider2.get(), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvideStockDatabaseFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<DbConnectionManager> provider2, Provider<AppSettings> provider3) {
        return new ApplicationModule_ProvideStockDatabaseFactory(applicationModule, provider, provider2, provider3);
    }

    public static SQLiteDatabaseWrapper proxyProvideStockDatabase(ApplicationModule applicationModule, Context context, DbConnectionManager dbConnectionManager, Lazy<AppSettings> lazy) {
        return (SQLiteDatabaseWrapper) Preconditions.checkNotNull(applicationModule.provideStockDatabase(context, dbConnectionManager, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}

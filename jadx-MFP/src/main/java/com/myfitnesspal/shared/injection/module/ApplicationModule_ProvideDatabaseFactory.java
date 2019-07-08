package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideDatabaseFactory implements Factory<SQLiteDatabaseWrapper> {
    private final Provider<DbConnectionManager> connectionManagerProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideDatabaseFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<DbConnectionManager> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.connectionManagerProvider = provider2;
    }

    public SQLiteDatabaseWrapper get() {
        return provideInstance(this.module, this.contextProvider, this.connectionManagerProvider);
    }

    public static SQLiteDatabaseWrapper provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<DbConnectionManager> provider2) {
        return proxyProvideDatabase(applicationModule, (Context) provider.get(), (DbConnectionManager) provider2.get());
    }

    public static ApplicationModule_ProvideDatabaseFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<DbConnectionManager> provider2) {
        return new ApplicationModule_ProvideDatabaseFactory(applicationModule, provider, provider2);
    }

    public static SQLiteDatabaseWrapper proxyProvideDatabase(ApplicationModule applicationModule, Context context, DbConnectionManager dbConnectionManager) {
        return (SQLiteDatabaseWrapper) Preconditions.checkNotNull(applicationModule.provideDatabase(context, dbConnectionManager), "Cannot return null from a non-@Nullable @Provides method");
    }
}

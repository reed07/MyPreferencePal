package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesSyncUtilFactory implements Factory<SyncUtil> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<ExerciseEntryMapper> mapperProvider;
    private final SyncModule module;
    private final Provider<KeyedSharedPreferences> prefsProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SQLiteDatabaseWrapper> stockDatabaseProvider;

    public SyncModule_ProvidesSyncUtilFactory(SyncModule syncModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<KeyedSharedPreferences> provider3, Provider<SQLiteDatabaseWrapper> provider4, Provider<SQLiteDatabaseWrapper> provider5, Provider<ExerciseEntryMapper> provider6, Provider<LocalSettingsService> provider7, Provider<Session> provider8) {
        this.module = syncModule;
        this.contextProvider = provider;
        this.configServiceProvider = provider2;
        this.prefsProvider = provider3;
        this.databaseProvider = provider4;
        this.stockDatabaseProvider = provider5;
        this.mapperProvider = provider6;
        this.localSettingsServiceProvider = provider7;
        this.sessionProvider = provider8;
    }

    public SyncUtil get() {
        return provideInstance(this.module, this.contextProvider, this.configServiceProvider, this.prefsProvider, this.databaseProvider, this.stockDatabaseProvider, this.mapperProvider, this.localSettingsServiceProvider, this.sessionProvider);
    }

    public static SyncUtil provideInstance(SyncModule syncModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<KeyedSharedPreferences> provider3, Provider<SQLiteDatabaseWrapper> provider4, Provider<SQLiteDatabaseWrapper> provider5, Provider<ExerciseEntryMapper> provider6, Provider<LocalSettingsService> provider7, Provider<Session> provider8) {
        return proxyProvidesSyncUtil(syncModule, (Context) provider.get(), DoubleCheck.lazy(provider2), (KeyedSharedPreferences) provider3.get(), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8));
    }

    public static SyncModule_ProvidesSyncUtilFactory create(SyncModule syncModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<KeyedSharedPreferences> provider3, Provider<SQLiteDatabaseWrapper> provider4, Provider<SQLiteDatabaseWrapper> provider5, Provider<ExerciseEntryMapper> provider6, Provider<LocalSettingsService> provider7, Provider<Session> provider8) {
        SyncModule_ProvidesSyncUtilFactory syncModule_ProvidesSyncUtilFactory = new SyncModule_ProvidesSyncUtilFactory(syncModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return syncModule_ProvidesSyncUtilFactory;
    }

    public static SyncUtil proxyProvidesSyncUtil(SyncModule syncModule, Context context, Lazy<ConfigService> lazy, KeyedSharedPreferences keyedSharedPreferences, Lazy<SQLiteDatabaseWrapper> lazy2, Lazy<SQLiteDatabaseWrapper> lazy3, Lazy<ExerciseEntryMapper> lazy4, Lazy<LocalSettingsService> lazy5, Lazy<Session> lazy6) {
        return (SyncUtil) Preconditions.checkNotNull(syncModule.providesSyncUtil(context, lazy, keyedSharedPreferences, lazy2, lazy3, lazy4, lazy5, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }
}

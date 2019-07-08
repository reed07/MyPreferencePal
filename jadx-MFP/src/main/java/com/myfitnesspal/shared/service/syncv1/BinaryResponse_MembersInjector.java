package com.myfitnesspal.shared.service.syncv1;

import android.content.Context;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class BinaryResponse_MembersInjector implements MembersInjector<BinaryResponse> {
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncSettings> syncSettingsProvider;

    public BinaryResponse_MembersInjector(Provider<Context> provider, Provider<AppSettings> provider2, Provider<SyncSettings> provider3, Provider<Session> provider4, Provider<DbConnectionManager> provider5) {
        this.contextProvider = provider;
        this.appSettingsProvider = provider2;
        this.syncSettingsProvider = provider3;
        this.sessionProvider = provider4;
        this.dbConnectionManagerProvider = provider5;
    }

    public static MembersInjector<BinaryResponse> create(Provider<Context> provider, Provider<AppSettings> provider2, Provider<SyncSettings> provider3, Provider<Session> provider4, Provider<DbConnectionManager> provider5) {
        BinaryResponse_MembersInjector binaryResponse_MembersInjector = new BinaryResponse_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return binaryResponse_MembersInjector;
    }

    public void injectMembers(BinaryResponse binaryResponse) {
        injectContext(binaryResponse, (Context) this.contextProvider.get());
        injectAppSettings(binaryResponse, (AppSettings) this.appSettingsProvider.get());
        injectSyncSettings(binaryResponse, (SyncSettings) this.syncSettingsProvider.get());
        injectSession(binaryResponse, DoubleCheck.lazy(this.sessionProvider));
        injectDbConnectionManager(binaryResponse, DoubleCheck.lazy(this.dbConnectionManagerProvider));
    }

    public static void injectContext(BinaryResponse binaryResponse, Context context) {
        binaryResponse.context = context;
    }

    public static void injectAppSettings(BinaryResponse binaryResponse, AppSettings appSettings) {
        binaryResponse.appSettings = appSettings;
    }

    public static void injectSyncSettings(BinaryResponse binaryResponse, SyncSettings syncSettings) {
        binaryResponse.syncSettings = syncSettings;
    }

    public static void injectSession(BinaryResponse binaryResponse, Lazy<Session> lazy) {
        binaryResponse.session = lazy;
    }

    public static void injectDbConnectionManager(BinaryResponse binaryResponse, Lazy<DbConnectionManager> lazy) {
        binaryResponse.dbConnectionManager = lazy;
    }
}

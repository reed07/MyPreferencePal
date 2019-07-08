package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.session.UserImpl;
import com.myfitnesspal.shared.service.session.UserV2Service;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SessionModule_ProvidesUserServiceFactory implements Factory<UserV2Service> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final SessionModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<UserImpl> userProvider;

    public SessionModule_ProvidesUserServiceFactory(SessionModule sessionModule, Provider<Context> provider, Provider<UserImpl> provider2, Provider<DbConnectionManager> provider3, Provider<MfpV2Api> provider4, Provider<Session> provider5) {
        this.module = sessionModule;
        this.contextProvider = provider;
        this.userProvider = provider2;
        this.dbConnectionManagerProvider = provider3;
        this.apiProvider = provider4;
        this.sessionProvider = provider5;
    }

    public UserV2Service get() {
        return provideInstance(this.module, this.contextProvider, this.userProvider, this.dbConnectionManagerProvider, this.apiProvider, this.sessionProvider);
    }

    public static UserV2Service provideInstance(SessionModule sessionModule, Provider<Context> provider, Provider<UserImpl> provider2, Provider<DbConnectionManager> provider3, Provider<MfpV2Api> provider4, Provider<Session> provider5) {
        return proxyProvidesUserService(sessionModule, (Context) provider.get(), DoubleCheck.lazy(provider2), (DbConnectionManager) provider3.get(), provider4, (Session) provider5.get());
    }

    public static SessionModule_ProvidesUserServiceFactory create(SessionModule sessionModule, Provider<Context> provider, Provider<UserImpl> provider2, Provider<DbConnectionManager> provider3, Provider<MfpV2Api> provider4, Provider<Session> provider5) {
        SessionModule_ProvidesUserServiceFactory sessionModule_ProvidesUserServiceFactory = new SessionModule_ProvidesUserServiceFactory(sessionModule, provider, provider2, provider3, provider4, provider5);
        return sessionModule_ProvidesUserServiceFactory;
    }

    public static UserV2Service proxyProvidesUserService(SessionModule sessionModule, Context context, Lazy<UserImpl> lazy, DbConnectionManager dbConnectionManager, Provider<MfpV2Api> provider, Session session) {
        return (UserV2Service) Preconditions.checkNotNull(sessionModule.providesUserService(context, lazy, dbConnectionManager, provider, session), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.session.UserImpl;
import com.myfitnesspal.shared.service.session.UserV2Service;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SessionModule_ProvideUserImplFactory implements Factory<UserImpl> {
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final SessionModule module;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<UserV2Service> userServiceProvider;

    public SessionModule_ProvideUserImplFactory(SessionModule sessionModule, Provider<AppIndexerBot> provider, Provider<UserV2Service> provider2, Provider<LoginModel> provider3, Provider<PremiumService> provider4, Provider<DbConnectionManager> provider5) {
        this.module = sessionModule;
        this.appIndexerBotProvider = provider;
        this.userServiceProvider = provider2;
        this.loginModelProvider = provider3;
        this.premiumServiceProvider = provider4;
        this.dbConnectionManagerProvider = provider5;
    }

    public UserImpl get() {
        return provideInstance(this.module, this.appIndexerBotProvider, this.userServiceProvider, this.loginModelProvider, this.premiumServiceProvider, this.dbConnectionManagerProvider);
    }

    public static UserImpl provideInstance(SessionModule sessionModule, Provider<AppIndexerBot> provider, Provider<UserV2Service> provider2, Provider<LoginModel> provider3, Provider<PremiumService> provider4, Provider<DbConnectionManager> provider5) {
        return proxyProvideUserImpl(sessionModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), (DbConnectionManager) provider5.get());
    }

    public static SessionModule_ProvideUserImplFactory create(SessionModule sessionModule, Provider<AppIndexerBot> provider, Provider<UserV2Service> provider2, Provider<LoginModel> provider3, Provider<PremiumService> provider4, Provider<DbConnectionManager> provider5) {
        SessionModule_ProvideUserImplFactory sessionModule_ProvideUserImplFactory = new SessionModule_ProvideUserImplFactory(sessionModule, provider, provider2, provider3, provider4, provider5);
        return sessionModule_ProvideUserImplFactory;
    }

    public static UserImpl proxyProvideUserImpl(SessionModule sessionModule, Lazy<AppIndexerBot> lazy, Lazy<UserV2Service> lazy2, Lazy<LoginModel> lazy3, Lazy<PremiumService> lazy4, DbConnectionManager dbConnectionManager) {
        return (UserImpl) Preconditions.checkNotNull(sessionModule.provideUserImpl(lazy, lazy2, lazy3, lazy4, dbConnectionManager), "Cannot return null from a non-@Nullable @Provides method");
    }
}

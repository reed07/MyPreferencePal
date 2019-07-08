package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.ops.AuthTokenOp;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesAuthTokenOpFactory implements Factory<AuthTokenOp> {
    private final Provider<AuthTokenProvider> authTokensProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final SyncModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<SignUpModel> signUpModelProvider;

    public SyncModule_ProvidesAuthTokenOpFactory(SyncModule syncModule, Provider<AuthTokenProvider> provider, Provider<SignUpModel> provider2, Provider<LoginModel> provider3, Provider<Session> provider4) {
        this.module = syncModule;
        this.authTokensProvider = provider;
        this.signUpModelProvider = provider2;
        this.loginModelProvider = provider3;
        this.sessionProvider = provider4;
    }

    public AuthTokenOp get() {
        return provideInstance(this.module, this.authTokensProvider, this.signUpModelProvider, this.loginModelProvider, this.sessionProvider);
    }

    public static AuthTokenOp provideInstance(SyncModule syncModule, Provider<AuthTokenProvider> provider, Provider<SignUpModel> provider2, Provider<LoginModel> provider3, Provider<Session> provider4) {
        return proxyProvidesAuthTokenOp(syncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static SyncModule_ProvidesAuthTokenOpFactory create(SyncModule syncModule, Provider<AuthTokenProvider> provider, Provider<SignUpModel> provider2, Provider<LoginModel> provider3, Provider<Session> provider4) {
        SyncModule_ProvidesAuthTokenOpFactory syncModule_ProvidesAuthTokenOpFactory = new SyncModule_ProvidesAuthTokenOpFactory(syncModule, provider, provider2, provider3, provider4);
        return syncModule_ProvidesAuthTokenOpFactory;
    }

    public static AuthTokenOp proxyProvidesAuthTokenOp(SyncModule syncModule, Lazy<AuthTokenProvider> lazy, Lazy<SignUpModel> lazy2, Lazy<LoginModel> lazy3, Lazy<Session> lazy4) {
        return (AuthTokenOp) Preconditions.checkNotNull(syncModule.providesAuthTokenOp(lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.auth.LegacyAuthTokenStore;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class SessionModule_ProvideAuthTokenProviderFactory implements Factory<AuthTokenProvider> {
    private final Provider<MfpAnalyticsService> analyticsProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<MfpV2Api> authApiProvider;
    private final Provider<String> clientIdProvider;
    private final Provider<Context> contextProvider;
    private final Provider<UUID> deviceIdProvider;
    private final Provider<LegacyAuthTokenStore> legacyAuthTokenStoreProvider;
    private final SessionModule module;
    private final Provider<Session> sessionProvider;

    public SessionModule_ProvideAuthTokenProviderFactory(SessionModule sessionModule, Provider<Context> provider, Provider<MfpAnalyticsService> provider2, Provider<Session> provider3, Provider<AppIndexerBot> provider4, Provider<MfpV2Api> provider5, Provider<LegacyAuthTokenStore> provider6, Provider<String> provider7, Provider<UUID> provider8) {
        this.module = sessionModule;
        this.contextProvider = provider;
        this.analyticsProvider = provider2;
        this.sessionProvider = provider3;
        this.appIndexerBotProvider = provider4;
        this.authApiProvider = provider5;
        this.legacyAuthTokenStoreProvider = provider6;
        this.clientIdProvider = provider7;
        this.deviceIdProvider = provider8;
    }

    public AuthTokenProvider get() {
        return provideInstance(this.module, this.contextProvider, this.analyticsProvider, this.sessionProvider, this.appIndexerBotProvider, this.authApiProvider, this.legacyAuthTokenStoreProvider, this.clientIdProvider, this.deviceIdProvider);
    }

    public static AuthTokenProvider provideInstance(SessionModule sessionModule, Provider<Context> provider, Provider<MfpAnalyticsService> provider2, Provider<Session> provider3, Provider<AppIndexerBot> provider4, Provider<MfpV2Api> provider5, Provider<LegacyAuthTokenStore> provider6, Provider<String> provider7, Provider<UUID> provider8) {
        return proxyProvideAuthTokenProvider(sessionModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), (AppIndexerBot) provider4.get(), provider5, (LegacyAuthTokenStore) provider6.get(), (String) provider7.get(), (UUID) provider8.get());
    }

    public static SessionModule_ProvideAuthTokenProviderFactory create(SessionModule sessionModule, Provider<Context> provider, Provider<MfpAnalyticsService> provider2, Provider<Session> provider3, Provider<AppIndexerBot> provider4, Provider<MfpV2Api> provider5, Provider<LegacyAuthTokenStore> provider6, Provider<String> provider7, Provider<UUID> provider8) {
        SessionModule_ProvideAuthTokenProviderFactory sessionModule_ProvideAuthTokenProviderFactory = new SessionModule_ProvideAuthTokenProviderFactory(sessionModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return sessionModule_ProvideAuthTokenProviderFactory;
    }

    public static AuthTokenProvider proxyProvideAuthTokenProvider(SessionModule sessionModule, Context context, Lazy<MfpAnalyticsService> lazy, Lazy<Session> lazy2, AppIndexerBot appIndexerBot, Provider<MfpV2Api> provider, LegacyAuthTokenStore legacyAuthTokenStore, String str, UUID uuid) {
        return (AuthTokenProvider) Preconditions.checkNotNull(sessionModule.provideAuthTokenProvider(context, lazy, lazy2, appIndexerBot, provider, legacyAuthTokenStore, str, uuid), "Cannot return null from a non-@Nullable @Provides method");
    }
}

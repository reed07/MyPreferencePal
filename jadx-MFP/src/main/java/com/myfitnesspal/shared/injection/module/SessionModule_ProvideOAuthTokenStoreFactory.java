package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.api.auth.LegacyAuthTokenStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SessionModule_ProvideOAuthTokenStoreFactory implements Factory<LegacyAuthTokenStore> {
    private final SessionModule module;
    private final Provider<SharedPreferences> prefsProvider;

    public SessionModule_ProvideOAuthTokenStoreFactory(SessionModule sessionModule, Provider<SharedPreferences> provider) {
        this.module = sessionModule;
        this.prefsProvider = provider;
    }

    public LegacyAuthTokenStore get() {
        return provideInstance(this.module, this.prefsProvider);
    }

    public static LegacyAuthTokenStore provideInstance(SessionModule sessionModule, Provider<SharedPreferences> provider) {
        return proxyProvideOAuthTokenStore(sessionModule, (SharedPreferences) provider.get());
    }

    public static SessionModule_ProvideOAuthTokenStoreFactory create(SessionModule sessionModule, Provider<SharedPreferences> provider) {
        return new SessionModule_ProvideOAuthTokenStoreFactory(sessionModule, provider);
    }

    public static LegacyAuthTokenStore proxyProvideOAuthTokenStore(SessionModule sessionModule, SharedPreferences sharedPreferences) {
        return (LegacyAuthTokenStore) Preconditions.checkNotNull(sessionModule.provideOAuthTokenStore(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}

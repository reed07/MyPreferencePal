package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.session.UserV2Service;
import com.myfitnesspal.shared.service.syncv2.ops.UserV2Op;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesUserV2SyncOpFactory implements Factory<UserV2Op> {
    private final SyncModule module;
    private final Provider<KeyedSharedPreferences> prefsProvider;
    private final Provider<UserV2Service> userServiceProvider;

    public SyncModule_ProvidesUserV2SyncOpFactory(SyncModule syncModule, Provider<UserV2Service> provider, Provider<KeyedSharedPreferences> provider2) {
        this.module = syncModule;
        this.userServiceProvider = provider;
        this.prefsProvider = provider2;
    }

    public UserV2Op get() {
        return provideInstance(this.module, this.userServiceProvider, this.prefsProvider);
    }

    public static UserV2Op provideInstance(SyncModule syncModule, Provider<UserV2Service> provider, Provider<KeyedSharedPreferences> provider2) {
        return proxyProvidesUserV2SyncOp(syncModule, DoubleCheck.lazy(provider), (KeyedSharedPreferences) provider2.get());
    }

    public static SyncModule_ProvidesUserV2SyncOpFactory create(SyncModule syncModule, Provider<UserV2Service> provider, Provider<KeyedSharedPreferences> provider2) {
        return new SyncModule_ProvidesUserV2SyncOpFactory(syncModule, provider, provider2);
    }

    public static UserV2Op proxyProvidesUserV2SyncOp(SyncModule syncModule, Lazy<UserV2Service> lazy, KeyedSharedPreferences keyedSharedPreferences) {
        return (UserV2Op) Preconditions.checkNotNull(syncModule.providesUserV2SyncOp(lazy, keyedSharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}

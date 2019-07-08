package com.myfitnesspal.shared.service.userdata;

import android.content.Context;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserHeightService_Factory implements Factory<UserHeightService> {
    private final Provider<Context> contextProvider;
    private final Provider<Session> sessionProvider;

    public UserHeightService_Factory(Provider<Context> provider, Provider<Session> provider2) {
        this.contextProvider = provider;
        this.sessionProvider = provider2;
    }

    public UserHeightService get() {
        return provideInstance(this.contextProvider, this.sessionProvider);
    }

    public static UserHeightService provideInstance(Provider<Context> provider, Provider<Session> provider2) {
        return new UserHeightService((Context) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static UserHeightService_Factory create(Provider<Context> provider, Provider<Session> provider2) {
        return new UserHeightService_Factory(provider, provider2);
    }

    public static UserHeightService newUserHeightService(Context context, Lazy<Session> lazy) {
        return new UserHeightService(context, lazy);
    }
}

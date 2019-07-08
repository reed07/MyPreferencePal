package com.myfitnesspal.shared.service.userdata;

import android.content.Context;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserDistanceService_Factory implements Factory<UserDistanceService> {
    private final Provider<Context> contextProvider;
    private final Provider<Session> sessionProvider;

    public UserDistanceService_Factory(Provider<Context> provider, Provider<Session> provider2) {
        this.contextProvider = provider;
        this.sessionProvider = provider2;
    }

    public UserDistanceService get() {
        return provideInstance(this.contextProvider, this.sessionProvider);
    }

    public static UserDistanceService provideInstance(Provider<Context> provider, Provider<Session> provider2) {
        return new UserDistanceService((Context) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static UserDistanceService_Factory create(Provider<Context> provider, Provider<Session> provider2) {
        return new UserDistanceService_Factory(provider, provider2);
    }

    public static UserDistanceService newUserDistanceService(Context context, Lazy<Session> lazy) {
        return new UserDistanceService(context, lazy);
    }
}

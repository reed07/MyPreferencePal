package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ServiceModule_ProvidesSubscriptionServiceDbAdapterFactory implements Factory<SubscriptionServiceDbAdapter> {
    private final Provider<Context> contextProvider;
    private final ServiceModule module;
    private final Provider<Session> sessionProvider;

    public ServiceModule_ProvidesSubscriptionServiceDbAdapterFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<Session> provider2) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.sessionProvider = provider2;
    }

    public SubscriptionServiceDbAdapter get() {
        return provideInstance(this.module, this.contextProvider, this.sessionProvider);
    }

    public static SubscriptionServiceDbAdapter provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<Session> provider2) {
        return proxyProvidesSubscriptionServiceDbAdapter(serviceModule, (Context) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static ServiceModule_ProvidesSubscriptionServiceDbAdapterFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<Session> provider2) {
        return new ServiceModule_ProvidesSubscriptionServiceDbAdapterFactory(serviceModule, provider, provider2);
    }

    public static SubscriptionServiceDbAdapter proxyProvidesSubscriptionServiceDbAdapter(ServiceModule serviceModule, Context context, Lazy<Session> lazy) {
        return (SubscriptionServiceDbAdapter) Preconditions.checkNotNull(serviceModule.providesSubscriptionServiceDbAdapter(context, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}

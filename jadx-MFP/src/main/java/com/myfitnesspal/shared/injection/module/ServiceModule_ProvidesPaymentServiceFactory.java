package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.payments.service.PaymentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ServiceModule_ProvidesPaymentServiceFactory implements Factory<PaymentService> {
    private final Provider<Context> contextProvider;
    private final ServiceModule module;

    public ServiceModule_ProvidesPaymentServiceFactory(ServiceModule serviceModule, Provider<Context> provider) {
        this.module = serviceModule;
        this.contextProvider = provider;
    }

    public PaymentService get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static PaymentService provideInstance(ServiceModule serviceModule, Provider<Context> provider) {
        return proxyProvidesPaymentService(serviceModule, (Context) provider.get());
    }

    public static ServiceModule_ProvidesPaymentServiceFactory create(ServiceModule serviceModule, Provider<Context> provider) {
        return new ServiceModule_ProvidesPaymentServiceFactory(serviceModule, provider);
    }

    public static PaymentService proxyProvidesPaymentService(ServiceModule serviceModule, Context context) {
        return (PaymentService) Preconditions.checkNotNull(serviceModule.providesPaymentService(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

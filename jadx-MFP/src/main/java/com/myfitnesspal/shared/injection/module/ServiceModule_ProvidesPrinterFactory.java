package com.myfitnesspal.shared.injection.module;

import com.uacf.core.logging.Printer;
import com.uacf.core.logging.PrivateFilePrinter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ServiceModule_ProvidesPrinterFactory implements Factory<Printer> {
    private final ServiceModule module;
    private final Provider<PrivateFilePrinter> privateFilePrinterProvider;

    public ServiceModule_ProvidesPrinterFactory(ServiceModule serviceModule, Provider<PrivateFilePrinter> provider) {
        this.module = serviceModule;
        this.privateFilePrinterProvider = provider;
    }

    public Printer get() {
        return provideInstance(this.module, this.privateFilePrinterProvider);
    }

    public static Printer provideInstance(ServiceModule serviceModule, Provider<PrivateFilePrinter> provider) {
        return proxyProvidesPrinter(serviceModule, (PrivateFilePrinter) provider.get());
    }

    public static ServiceModule_ProvidesPrinterFactory create(ServiceModule serviceModule, Provider<PrivateFilePrinter> provider) {
        return new ServiceModule_ProvidesPrinterFactory(serviceModule, provider);
    }

    public static Printer proxyProvidesPrinter(ServiceModule serviceModule, PrivateFilePrinter privateFilePrinter) {
        return (Printer) Preconditions.checkNotNull(serviceModule.providesPrinter(privateFilePrinter), "Cannot return null from a non-@Nullable @Provides method");
    }
}

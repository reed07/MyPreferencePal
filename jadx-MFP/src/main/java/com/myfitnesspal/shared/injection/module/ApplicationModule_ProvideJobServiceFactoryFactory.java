package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.notification.JobServiceFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideJobServiceFactoryFactory implements Factory<JobServiceFactory> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideJobServiceFactoryFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public JobServiceFactory get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static JobServiceFactory provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvideJobServiceFactory(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvideJobServiceFactoryFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvideJobServiceFactoryFactory(applicationModule, provider);
    }

    public static JobServiceFactory proxyProvideJobServiceFactory(ApplicationModule applicationModule, Context context) {
        return (JobServiceFactory) Preconditions.checkNotNull(applicationModule.provideJobServiceFactory(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

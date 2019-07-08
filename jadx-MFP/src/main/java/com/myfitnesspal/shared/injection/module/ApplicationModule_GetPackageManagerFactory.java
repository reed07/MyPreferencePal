package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_GetPackageManagerFactory implements Factory<PackageManager> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_GetPackageManagerFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public PackageManager get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static PackageManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyGetPackageManager(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_GetPackageManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_GetPackageManagerFactory(applicationModule, provider);
    }

    public static PackageManager proxyGetPackageManager(ApplicationModule applicationModule, Context context) {
        return (PackageManager) Preconditions.checkNotNull(applicationModule.getPackageManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

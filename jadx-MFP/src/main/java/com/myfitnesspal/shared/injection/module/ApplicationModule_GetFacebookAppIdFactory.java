package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_GetFacebookAppIdFactory implements Factory<String> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_GetFacebookAppIdFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public String get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static String provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyGetFacebookAppId(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_GetFacebookAppIdFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_GetFacebookAppIdFactory(applicationModule, provider);
    }

    public static String proxyGetFacebookAppId(ApplicationModule applicationModule, Context context) {
        return (String) Preconditions.checkNotNull(applicationModule.getFacebookAppId(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

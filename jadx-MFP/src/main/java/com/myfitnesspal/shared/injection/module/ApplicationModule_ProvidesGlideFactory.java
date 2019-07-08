package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesGlideFactory implements Factory<Glide> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<ApiUrlProvider> urlProvider;

    public ApplicationModule_ProvidesGlideFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<ApiUrlProvider> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.urlProvider = provider2;
    }

    public Glide get() {
        return provideInstance(this.module, this.contextProvider, this.urlProvider);
    }

    public static Glide provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<ApiUrlProvider> provider2) {
        return proxyProvidesGlide(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidesGlideFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<ApiUrlProvider> provider2) {
        return new ApplicationModule_ProvidesGlideFactory(applicationModule, provider, provider2);
    }

    public static Glide proxyProvidesGlide(ApplicationModule applicationModule, Context context, Lazy<ApiUrlProvider> lazy) {
        return (Glide) Preconditions.checkNotNull(applicationModule.providesGlide(context, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}

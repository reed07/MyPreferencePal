package com.myfitnesspal.shared.injection.module;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public final class ApplicationModule_ProvideRetrofitInterfaceFactory implements Factory<Retrofit> {
    private final Provider<Gson> gsonProvider;
    private final ApplicationModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public ApplicationModule_ProvideRetrofitInterfaceFactory(ApplicationModule applicationModule, Provider<Gson> provider, Provider<OkHttpClient> provider2) {
        this.module = applicationModule;
        this.gsonProvider = provider;
        this.okHttpClientProvider = provider2;
    }

    public Retrofit get() {
        return provideInstance(this.module, this.gsonProvider, this.okHttpClientProvider);
    }

    public static Retrofit provideInstance(ApplicationModule applicationModule, Provider<Gson> provider, Provider<OkHttpClient> provider2) {
        return proxyProvideRetrofitInterface(applicationModule, (Gson) provider.get(), (OkHttpClient) provider2.get());
    }

    public static ApplicationModule_ProvideRetrofitInterfaceFactory create(ApplicationModule applicationModule, Provider<Gson> provider, Provider<OkHttpClient> provider2) {
        return new ApplicationModule_ProvideRetrofitInterfaceFactory(applicationModule, provider, provider2);
    }

    public static Retrofit proxyProvideRetrofitInterface(ApplicationModule applicationModule, Gson gson, OkHttpClient okHttpClient) {
        return (Retrofit) Preconditions.checkNotNull(applicationModule.provideRetrofitInterface(gson, okHttpClient), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesPremiumApiErrorUtilFactory implements Factory<PremiumApiErrorUtil> {
    private final Provider<Context> contextProvider;
    private final Provider<Bus> messageBusProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesPremiumApiErrorUtilFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Bus> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.messageBusProvider = provider2;
    }

    public PremiumApiErrorUtil get() {
        return provideInstance(this.module, this.contextProvider, this.messageBusProvider);
    }

    public static PremiumApiErrorUtil provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Bus> provider2) {
        return proxyProvidesPremiumApiErrorUtil(applicationModule, (Context) provider.get(), (Bus) provider2.get());
    }

    public static ApplicationModule_ProvidesPremiumApiErrorUtilFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Bus> provider2) {
        return new ApplicationModule_ProvidesPremiumApiErrorUtilFactory(applicationModule, provider, provider2);
    }

    public static PremiumApiErrorUtil proxyProvidesPremiumApiErrorUtil(ApplicationModule applicationModule, Context context, Bus bus) {
        return (PremiumApiErrorUtil) Preconditions.checkNotNull(applicationModule.providesPremiumApiErrorUtil(context, bus), "Cannot return null from a non-@Nullable @Provides method");
    }
}

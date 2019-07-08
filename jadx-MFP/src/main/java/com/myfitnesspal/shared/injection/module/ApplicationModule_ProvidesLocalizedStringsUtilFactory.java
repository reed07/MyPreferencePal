package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.ResourceUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesLocalizedStringsUtilFactory implements Factory<LocalizedStringsUtil> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<ResourceUtils> resourceUtilsProvider;

    public ApplicationModule_ProvidesLocalizedStringsUtilFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<ResourceUtils> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.resourceUtilsProvider = provider2;
    }

    public LocalizedStringsUtil get() {
        return provideInstance(this.module, this.contextProvider, this.resourceUtilsProvider);
    }

    public static LocalizedStringsUtil provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<ResourceUtils> provider2) {
        return proxyProvidesLocalizedStringsUtil(applicationModule, (Context) provider.get(), (ResourceUtils) provider2.get());
    }

    public static ApplicationModule_ProvidesLocalizedStringsUtilFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<ResourceUtils> provider2) {
        return new ApplicationModule_ProvidesLocalizedStringsUtilFactory(applicationModule, provider, provider2);
    }

    public static LocalizedStringsUtil proxyProvidesLocalizedStringsUtil(ApplicationModule applicationModule, Context context, ResourceUtils resourceUtils) {
        return (LocalizedStringsUtil) Preconditions.checkNotNull(applicationModule.providesLocalizedStringsUtil(context, resourceUtils), "Cannot return null from a non-@Nullable @Provides method");
    }
}

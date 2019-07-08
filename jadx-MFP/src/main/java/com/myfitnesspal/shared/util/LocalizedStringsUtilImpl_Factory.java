package com.myfitnesspal.shared.util;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LocalizedStringsUtilImpl_Factory implements Factory<LocalizedStringsUtilImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<ResourceUtils> resourceUtilsProvider;

    public LocalizedStringsUtilImpl_Factory(Provider<Context> provider, Provider<ResourceUtils> provider2) {
        this.contextProvider = provider;
        this.resourceUtilsProvider = provider2;
    }

    public LocalizedStringsUtilImpl get() {
        return provideInstance(this.contextProvider, this.resourceUtilsProvider);
    }

    public static LocalizedStringsUtilImpl provideInstance(Provider<Context> provider, Provider<ResourceUtils> provider2) {
        return new LocalizedStringsUtilImpl((Context) provider.get(), (ResourceUtils) provider2.get());
    }

    public static LocalizedStringsUtilImpl_Factory create(Provider<Context> provider, Provider<ResourceUtils> provider2) {
        return new LocalizedStringsUtilImpl_Factory(provider, provider2);
    }

    public static LocalizedStringsUtilImpl newLocalizedStringsUtilImpl(Context context, ResourceUtils resourceUtils) {
        return new LocalizedStringsUtilImpl(context, resourceUtils);
    }
}

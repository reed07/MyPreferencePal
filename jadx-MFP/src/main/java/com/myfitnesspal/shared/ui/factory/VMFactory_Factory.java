package com.myfitnesspal.shared.ui.factory;

import android.arch.lifecycle.ViewModel;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;

public final class VMFactory_Factory implements Factory<VMFactory> {
    private final Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> creatorsProvider;

    public VMFactory_Factory(Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> provider) {
        this.creatorsProvider = provider;
    }

    public VMFactory get() {
        return provideInstance(this.creatorsProvider);
    }

    public static VMFactory provideInstance(Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> provider) {
        return new VMFactory((Map) provider.get());
    }

    public static VMFactory_Factory create(Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> provider) {
        return new VMFactory_Factory(provider);
    }

    public static VMFactory newVMFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> map) {
        return new VMFactory(map);
    }
}

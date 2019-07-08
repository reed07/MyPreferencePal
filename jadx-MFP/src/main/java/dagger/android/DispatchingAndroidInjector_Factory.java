package dagger.android;

import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;

public final class DispatchingAndroidInjector_Factory<T> implements Factory<DispatchingAndroidInjector<T>> {
    private final Provider<Map<Class<? extends T>, Provider<AndroidInjector.Factory<? extends T>>>> injectorFactoriesWithClassKeysProvider;
    private final Provider<Map<String, Provider<AndroidInjector.Factory<? extends T>>>> injectorFactoriesWithStringKeysProvider;

    public DispatchingAndroidInjector_Factory(Provider<Map<Class<? extends T>, Provider<AndroidInjector.Factory<? extends T>>>> provider, Provider<Map<String, Provider<AndroidInjector.Factory<? extends T>>>> provider2) {
        this.injectorFactoriesWithClassKeysProvider = provider;
        this.injectorFactoriesWithStringKeysProvider = provider2;
    }

    public DispatchingAndroidInjector<T> get() {
        return provideInstance(this.injectorFactoriesWithClassKeysProvider, this.injectorFactoriesWithStringKeysProvider);
    }

    public static <T> DispatchingAndroidInjector<T> provideInstance(Provider<Map<Class<? extends T>, Provider<AndroidInjector.Factory<? extends T>>>> provider, Provider<Map<String, Provider<AndroidInjector.Factory<? extends T>>>> provider2) {
        return new DispatchingAndroidInjector<>((Map) provider.get(), (Map) provider2.get());
    }

    public static <T> DispatchingAndroidInjector_Factory<T> create(Provider<Map<Class<? extends T>, Provider<AndroidInjector.Factory<? extends T>>>> provider, Provider<Map<String, Provider<AndroidInjector.Factory<? extends T>>>> provider2) {
        return new DispatchingAndroidInjector_Factory<>(provider, provider2);
    }

    public static <T> DispatchingAndroidInjector<T> newDispatchingAndroidInjector(Map<Class<? extends T>, Provider<AndroidInjector.Factory<? extends T>>> map, Map<String, Provider<AndroidInjector.Factory<? extends T>>> map2) {
        return new DispatchingAndroidInjector<>(map, map2);
    }
}

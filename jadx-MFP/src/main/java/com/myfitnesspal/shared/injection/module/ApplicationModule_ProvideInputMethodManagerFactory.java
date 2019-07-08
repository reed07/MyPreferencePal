package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideInputMethodManagerFactory implements Factory<InputMethodManager> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideInputMethodManagerFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public InputMethodManager get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static InputMethodManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvideInputMethodManager(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvideInputMethodManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvideInputMethodManagerFactory(applicationModule, provider);
    }

    public static InputMethodManager proxyProvideInputMethodManager(ApplicationModule applicationModule, Context context) {
        return (InputMethodManager) Preconditions.checkNotNull(applicationModule.provideInputMethodManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}

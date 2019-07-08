package com.myfitnesspal.shared.util;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InputMethodManagerHelper_Factory implements Factory<InputMethodManagerHelper> {
    private final Provider<Context> contextProvider;

    public InputMethodManagerHelper_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public InputMethodManagerHelper get() {
        return provideInstance(this.contextProvider);
    }

    public static InputMethodManagerHelper provideInstance(Provider<Context> provider) {
        return new InputMethodManagerHelper((Context) provider.get());
    }

    public static InputMethodManagerHelper_Factory create(Provider<Context> provider) {
        return new InputMethodManagerHelper_Factory(provider);
    }

    public static InputMethodManagerHelper newInputMethodManagerHelper(Context context) {
        return new InputMethodManagerHelper(context);
    }
}

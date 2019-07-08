package com.myfitnesspal.feature.registration.util;

import android.content.Context;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PasswordResetHelper_Factory implements Factory<PasswordResetHelper> {
    private final Provider<Context> contextProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;

    public PasswordResetHelper_Factory(Provider<Context> provider, Provider<NavigationHelper> provider2) {
        this.contextProvider = provider;
        this.navigationHelperProvider = provider2;
    }

    public PasswordResetHelper get() {
        return provideInstance(this.contextProvider, this.navigationHelperProvider);
    }

    public static PasswordResetHelper provideInstance(Provider<Context> provider, Provider<NavigationHelper> provider2) {
        return new PasswordResetHelper((Context) provider.get(), (NavigationHelper) provider2.get());
    }

    public static PasswordResetHelper_Factory create(Provider<Context> provider, Provider<NavigationHelper> provider2) {
        return new PasswordResetHelper_Factory(provider, provider2);
    }

    public static PasswordResetHelper newPasswordResetHelper(Context context, NavigationHelper navigationHelper) {
        return new PasswordResetHelper(context, navigationHelper);
    }
}

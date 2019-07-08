package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesAdvancedDebuggingUtilFactory implements Factory<AdvancedDebuggingUtil> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<NavigationHelper> navigationHelperProvider;

    public ApplicationModule_ProvidesAdvancedDebuggingUtilFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<NavigationHelper> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.navigationHelperProvider = provider2;
    }

    public AdvancedDebuggingUtil get() {
        return provideInstance(this.module, this.contextProvider, this.navigationHelperProvider);
    }

    public static AdvancedDebuggingUtil provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<NavigationHelper> provider2) {
        return proxyProvidesAdvancedDebuggingUtil(applicationModule, (Context) provider.get(), (NavigationHelper) provider2.get());
    }

    public static ApplicationModule_ProvidesAdvancedDebuggingUtilFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<NavigationHelper> provider2) {
        return new ApplicationModule_ProvidesAdvancedDebuggingUtilFactory(applicationModule, provider, provider2);
    }

    public static AdvancedDebuggingUtil proxyProvidesAdvancedDebuggingUtil(ApplicationModule applicationModule, Context context, NavigationHelper navigationHelper) {
        return (AdvancedDebuggingUtil) Preconditions.checkNotNull(applicationModule.providesAdvancedDebuggingUtil(context, navigationHelper), "Cannot return null from a non-@Nullable @Provides method");
    }
}

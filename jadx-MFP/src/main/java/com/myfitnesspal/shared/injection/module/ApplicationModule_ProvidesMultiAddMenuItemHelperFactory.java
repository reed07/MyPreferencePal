package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.restaurantlogging.util.MultiAddMenuItemHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesMultiAddMenuItemHelperFactory implements Factory<MultiAddMenuItemHelper> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesMultiAddMenuItemHelperFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public MultiAddMenuItemHelper get() {
        return provideInstance(this.module);
    }

    public static MultiAddMenuItemHelper provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesMultiAddMenuItemHelper(applicationModule);
    }

    public static ApplicationModule_ProvidesMultiAddMenuItemHelperFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesMultiAddMenuItemHelperFactory(applicationModule);
    }

    public static MultiAddMenuItemHelper proxyProvidesMultiAddMenuItemHelper(ApplicationModule applicationModule) {
        return (MultiAddMenuItemHelper) Preconditions.checkNotNull(applicationModule.providesMultiAddMenuItemHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

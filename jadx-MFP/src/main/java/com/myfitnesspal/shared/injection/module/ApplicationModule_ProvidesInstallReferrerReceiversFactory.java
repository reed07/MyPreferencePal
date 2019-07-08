package com.myfitnesspal.shared.injection.module;

import android.content.BroadcastReceiver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;

public final class ApplicationModule_ProvidesInstallReferrerReceiversFactory implements Factory<List<BroadcastReceiver>> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesInstallReferrerReceiversFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public List<BroadcastReceiver> get() {
        return provideInstance(this.module);
    }

    public static List<BroadcastReceiver> provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesInstallReferrerReceivers(applicationModule);
    }

    public static ApplicationModule_ProvidesInstallReferrerReceiversFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesInstallReferrerReceiversFactory(applicationModule);
    }

    public static List<BroadcastReceiver> proxyProvidesInstallReferrerReceivers(ApplicationModule applicationModule) {
        return (List) Preconditions.checkNotNull(applicationModule.providesInstallReferrerReceivers(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

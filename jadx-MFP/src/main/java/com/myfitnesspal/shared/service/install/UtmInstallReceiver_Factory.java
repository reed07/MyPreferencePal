package com.myfitnesspal.shared.service.install;

import com.myfitnesspal.feature.registration.service.InstallManager;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UtmInstallReceiver_Factory implements Factory<UtmInstallReceiver> {
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<InstallManager> installManagerProvider;

    public UtmInstallReceiver_Factory(Provider<InstallManager> provider, Provider<DeepLinkManager> provider2) {
        this.installManagerProvider = provider;
        this.deepLinkManagerProvider = provider2;
    }

    public UtmInstallReceiver get() {
        return provideInstance(this.installManagerProvider, this.deepLinkManagerProvider);
    }

    public static UtmInstallReceiver provideInstance(Provider<InstallManager> provider, Provider<DeepLinkManager> provider2) {
        UtmInstallReceiver utmInstallReceiver = new UtmInstallReceiver();
        UtmInstallReceiver_MembersInjector.injectInstallManager(utmInstallReceiver, (InstallManager) provider.get());
        UtmInstallReceiver_MembersInjector.injectDeepLinkManager(utmInstallReceiver, (DeepLinkManager) provider2.get());
        return utmInstallReceiver;
    }

    public static UtmInstallReceiver_Factory create(Provider<InstallManager> provider, Provider<DeepLinkManager> provider2) {
        return new UtmInstallReceiver_Factory(provider, provider2);
    }

    public static UtmInstallReceiver newUtmInstallReceiver() {
        return new UtmInstallReceiver();
    }
}

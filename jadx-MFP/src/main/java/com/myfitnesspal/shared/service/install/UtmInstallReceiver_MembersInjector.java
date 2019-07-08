package com.myfitnesspal.shared.service.install;

import com.myfitnesspal.feature.registration.service.InstallManager;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UtmInstallReceiver_MembersInjector implements MembersInjector<UtmInstallReceiver> {
    private final Provider<DeepLinkManager> deepLinkManagerProvider;
    private final Provider<InstallManager> installManagerProvider;

    public UtmInstallReceiver_MembersInjector(Provider<InstallManager> provider, Provider<DeepLinkManager> provider2) {
        this.installManagerProvider = provider;
        this.deepLinkManagerProvider = provider2;
    }

    public static MembersInjector<UtmInstallReceiver> create(Provider<InstallManager> provider, Provider<DeepLinkManager> provider2) {
        return new UtmInstallReceiver_MembersInjector(provider, provider2);
    }

    public void injectMembers(UtmInstallReceiver utmInstallReceiver) {
        injectInstallManager(utmInstallReceiver, (InstallManager) this.installManagerProvider.get());
        injectDeepLinkManager(utmInstallReceiver, (DeepLinkManager) this.deepLinkManagerProvider.get());
    }

    public static void injectInstallManager(UtmInstallReceiver utmInstallReceiver, InstallManager installManager) {
        utmInstallReceiver.installManager = installManager;
    }

    public static void injectDeepLinkManager(UtmInstallReceiver utmInstallReceiver, DeepLinkManager deepLinkManager) {
        utmInstallReceiver.deepLinkManager = deepLinkManager;
    }
}

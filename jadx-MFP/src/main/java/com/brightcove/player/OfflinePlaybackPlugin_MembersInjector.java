package com.brightcove.player;

import com.brightcove.player.drm.LicenseManagerFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class OfflinePlaybackPlugin_MembersInjector implements MembersInjector<OfflinePlaybackPlugin> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<LicenseManagerFactory> licenseManagerFactoryProvider;

    public OfflinePlaybackPlugin_MembersInjector(Provider<LicenseManagerFactory> provider) {
        this.licenseManagerFactoryProvider = provider;
    }

    public static MembersInjector<OfflinePlaybackPlugin> create(Provider<LicenseManagerFactory> provider) {
        return new OfflinePlaybackPlugin_MembersInjector(provider);
    }

    public void injectMembers(OfflinePlaybackPlugin offlinePlaybackPlugin) {
        if (offlinePlaybackPlugin != null) {
            offlinePlaybackPlugin.licenseManagerFactory = (LicenseManagerFactory) this.licenseManagerFactoryProvider.get();
            return;
        }
        throw new NullPointerException("Cannot inject members into a null reference");
    }

    public static void injectLicenseManagerFactory(OfflinePlaybackPlugin offlinePlaybackPlugin, Provider<LicenseManagerFactory> provider) {
        offlinePlaybackPlugin.licenseManagerFactory = (LicenseManagerFactory) provider.get();
    }
}

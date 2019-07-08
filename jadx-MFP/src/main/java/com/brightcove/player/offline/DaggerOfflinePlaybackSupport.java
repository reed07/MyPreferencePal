package com.brightcove.player.offline;

import com.brightcove.player.OfflinePlaybackPlugin;
import com.brightcove.player.OfflinePlaybackPlugin_MembersInjector;
import com.brightcove.player.drm.LicenseManagerFactory;
import com.brightcove.player.drm.OfflineLicenseManagerFactory;
import com.brightcove.player.drm.OfflineLicenseManagerFactory_ProvideLicenseManagerFactoryFactory;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerOfflinePlaybackSupport implements OfflinePlaybackSupport {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private MembersInjector<OfflinePlaybackPlugin> offlinePlaybackPluginMembersInjector;
    private Provider<LicenseManagerFactory> provideLicenseManagerFactoryProvider;

    public static final class Builder {
        private Builder() {
        }

        public OfflinePlaybackSupport build() {
            return new DaggerOfflinePlaybackSupport(this);
        }

        @Deprecated
        public Builder offlineLicenseManagerFactory(OfflineLicenseManagerFactory offlineLicenseManagerFactory) {
            Preconditions.checkNotNull(offlineLicenseManagerFactory);
            return this;
        }
    }

    private DaggerOfflinePlaybackSupport(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static OfflinePlaybackSupport create() {
        return new Builder().build();
    }

    private void initialize(Builder builder) {
        this.provideLicenseManagerFactoryProvider = DoubleCheck.provider(OfflineLicenseManagerFactory_ProvideLicenseManagerFactoryFactory.create());
        this.offlinePlaybackPluginMembersInjector = OfflinePlaybackPlugin_MembersInjector.create(this.provideLicenseManagerFactoryProvider);
    }

    public void inject(OfflinePlaybackPlugin offlinePlaybackPlugin) {
        this.offlinePlaybackPluginMembersInjector.injectMembers(offlinePlaybackPlugin);
    }
}

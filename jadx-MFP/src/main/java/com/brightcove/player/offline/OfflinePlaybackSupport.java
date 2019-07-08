package com.brightcove.player.offline;

import com.brightcove.player.OfflinePlaybackPlugin.Modules;
import com.brightcove.player.drm.OfflineLicenseManagerFactory;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {OfflineLicenseManagerFactory.class})
public interface OfflinePlaybackSupport extends Modules {
}

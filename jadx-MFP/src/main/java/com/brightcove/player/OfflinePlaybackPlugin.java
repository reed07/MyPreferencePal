package com.brightcove.player;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.drm.LicenseManager;
import com.brightcove.player.drm.LicenseManagerFactory;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;

public final class OfflinePlaybackPlugin implements LicenseManagerFactory {
    private final AtomicBoolean initialized = new AtomicBoolean();
    @Nullable
    @Inject
    protected LicenseManagerFactory licenseManagerFactory;

    private static class LazySingleton {
        public static final OfflinePlaybackPlugin INSTANCE = new OfflinePlaybackPlugin();

        private LazySingleton() {
        }
    }

    public interface Modules {
        void inject(@NonNull OfflinePlaybackPlugin offlinePlaybackPlugin);
    }

    @Singleton
    public static OfflinePlaybackPlugin getInstance() {
        return LazySingleton.INSTANCE;
    }

    @NonNull
    public LicenseManager createLicenseManager(@NonNull Video video, @NonNull Source source) {
        LicenseManagerFactory licenseManagerFactory2 = this.licenseManagerFactory;
        if (licenseManagerFactory2 != null) {
            return licenseManagerFactory2.createLicenseManager(video, source);
        }
        throw new IllegalStateException("Plugin is not initialized with a license manager factory!");
    }

    public OfflinePlaybackPlugin initialize(@NonNull Modules modules) {
        if (!this.initialized.get()) {
            this.initialized.set(true);
            modules.inject(this);
            return this;
        }
        throw new IllegalStateException("Plugin has been initialized already!");
    }
}

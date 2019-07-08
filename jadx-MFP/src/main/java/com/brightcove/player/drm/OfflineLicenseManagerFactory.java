package com.brightcove.player.drm;

import android.support.annotation.NonNull;
import com.brightcove.player.Constants;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.UnsupportedDrmException;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class OfflineLicenseManagerFactory implements LicenseManagerFactory {
    @Singleton
    @NonNull
    @Provides
    public static LicenseManagerFactory provideLicenseManagerFactory() {
        return new OfflineLicenseManagerFactory();
    }

    public LicenseManager createLicenseManager(@NonNull Video video, @NonNull Source source) {
        try {
            return new OfflineLicenseManager(FrameworkMediaDrm.newInstance(Constants.WIDEVINE_UUID), WidevineMediaDrmCallback.create(video.getProperties(), source.getProperties()), null);
        } catch (UnsupportedDrmException e) {
            throw new IllegalStateException("Failed to created offline license helper", e);
        }
    }
}

package com.brightcove.player.drm;

import android.support.annotation.NonNull;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;

public interface LicenseManagerFactory {
    @NonNull
    LicenseManager createLicenseManager(@NonNull Video video, @NonNull Source source);
}

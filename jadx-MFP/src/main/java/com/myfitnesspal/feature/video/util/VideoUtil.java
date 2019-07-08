package com.myfitnesspal.feature.video.util;

import android.support.annotation.NonNull;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.constants.Constants.ABTest.NewsFeedVideo;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.uacf.core.util.Strings;
import dagger.Lazy;

public class VideoUtil {
    public static boolean isVideoAutoPlayOn(@NonNull Lazy<UserApplicationSettingsService> lazy, @NonNull Lazy<ConfigService> lazy2) {
        if (((UserApplicationSettingsService) lazy.get()).isAutoplayNewsfeedVideoSettingExists()) {
            return ((UserApplicationSettingsService) lazy.get()).isAutoplayNewsfeedVideosEnabled();
        }
        return Strings.equals(NewsFeedVideo.AUTO_PLAY, ((ConfigService) lazy2.get()).getVariantIfLanguageSupported(NewsFeedVideo.NAME));
    }
}

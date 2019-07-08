package com.myfitnesspal.feature.video.model;

import com.brightcove.player.model.Video;
import com.uacf.core.util.Strings;
import java.util.concurrent.TimeUnit;

public class VideoGalleryItem implements VideoGalleryAdapterItem {
    private Video video;

    public VideoGalleryItem(Video video2) {
        this.video = video2;
    }

    public Video getVideo() {
        return this.video;
    }

    public String getName() {
        return this.video.getName();
    }

    public String getImageUrl() {
        return Strings.toString(this.video.getStillImageUri());
    }

    public String getDuration() {
        long duration = (long) (this.video.getDuration() > 0 ? this.video.getDuration() : 0);
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(hours);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(minutes);
        StringBuilder sb = new StringBuilder();
        if (hours > 0) {
            sb.append(String.format("%02d", new Object[]{Long.valueOf(hours)}));
            sb.append(":");
        }
        sb.append(String.format("%02d", new Object[]{Long.valueOf(minutes)}));
        sb.append(":");
        sb.append(String.format("%02d", new Object[]{Long.valueOf(seconds)}));
        return sb.toString();
    }
}

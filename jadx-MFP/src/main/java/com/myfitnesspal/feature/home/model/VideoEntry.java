package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryData;

public class VideoEntry implements MfpNewsFeedActivityEntryData {
    @Expose
    String description;
    @Expose
    String playlistId;
    @Expose
    String thumbnail;
    @Expose
    String title;
    @Expose
    String videoId;
    @Expose
    String videoUrl;

    public String getText() {
        return null;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }
}

package com.myfitnesspal.feature.video.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.model.Playlist;
import com.brightcove.player.model.Video;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoGalleryDetailsItem implements VideoGalleryAdapterItem {
    private static final String DESCRIPTION_PROPERTY = "description";
    private static final String ECONOMICS_PROPERTY = "economics";
    private static final String ECONOMICS_PROPERTY_VALUE = "AD_SUPPORTED";
    public static final String SPONSOR_IMAGE_PROPERTY = "sponsor_image";
    public static final String SPONSOR_LINK_PROPERTY = "sponsor_link";
    public static final String SPONSOR_PROPERTY = "sponsor";
    public static final String SPONSOR_URL_PATH = "http://media.api.ua.com/ua-media/image/upload/";
    private Map<String, String> descriptionProperty = new HashMap();
    private Playlist playlist;
    private String playlistName;
    private Video video;
    private List<Video> videosList = new ArrayList();

    public VideoGalleryDetailsItem(@NonNull Playlist playlist2, @NonNull String str) {
        this.playlist = playlist2;
        parsePlaylist(str);
    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    public List<Video> getVideos() {
        return this.videosList;
    }

    public void setActiveVideo(@NonNull String str) {
        for (Video video2 : this.videosList) {
            if (Strings.equals(video2.getId(), str)) {
                this.video = video2;
            }
        }
    }

    public String getVideoName() {
        Video video2 = this.video;
        return video2 != null ? video2.getName() : "";
    }

    public boolean isAdSupported() {
        Video video2 = this.video;
        return video2 != null && Strings.equals(video2.getStringProperty("economics"), "AD_SUPPORTED");
    }

    private void parsePlaylist(@NonNull String str) {
        this.playlistName = this.playlist.getStringProperty("name");
        List<Video> videos = this.playlist.getVideos();
        if (CollectionUtils.notEmpty((Collection<?>) videos)) {
            for (Video video2 : videos) {
                if (video2 != null) {
                    if (Strings.equals(video2.getId(), str)) {
                        this.video = video2;
                    }
                    this.videosList.add(video2);
                }
            }
        }
        this.descriptionProperty = parseDescriptionProperty();
    }

    private Map<String, String> parseDescriptionProperty() {
        try {
            return (Map) new Gson().fromJson(this.playlist.getStringProperty("description"), new TypeToken<Map<String, String>>() {
            }.getType());
        } catch (Exception unused) {
            return new HashMap();
        }
    }

    public String getStringProperty(@NonNull String str) {
        return getStringProperty(this.descriptionProperty, str);
    }

    @Nullable
    private String getStringProperty(@NonNull Map<String, String> map, @NonNull String str) {
        if (!CollectionUtils.notEmpty(map) || !map.containsKey(str)) {
            return null;
        }
        return (String) map.get(str);
    }
}

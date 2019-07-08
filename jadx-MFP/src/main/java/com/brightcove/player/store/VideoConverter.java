package com.brightcove.player.store;

import com.brightcove.player.model.Video;
import com.brightcove.player.util.Convert.Lazy;
import io.requery.Converter;

public class VideoConverter implements Converter<Video, String> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<Video> getMappedType() {
        return Video.class;
    }

    public Class<String> getPersistedType() {
        return String.class;
    }

    public String convertToPersisted(Video video) {
        if (video == null) {
            return null;
        }
        return Lazy.UTC_GSON.toJson((Object) video);
    }

    public Video convertToMapped(Class<? extends Video> cls, String str) {
        if (str == null) {
            return null;
        }
        return ((Video) Lazy.UTC_GSON.fromJson(str, cls)).fixProperties();
    }
}

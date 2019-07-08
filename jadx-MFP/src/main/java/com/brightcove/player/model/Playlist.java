package com.brightcove.player.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.brightcove.player.util.ErrorUtil;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Playlist extends MetadataObject implements Parcelable {
    public static Creator<Playlist> CREATOR = new Creator<Playlist>() {
        public Playlist createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            if (readInt == 2) {
                return (Playlist) parcel.readSerializable();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown parcel type: ");
            sb.append(readInt);
            throw new IllegalArgumentException(sb.toString());
        }

        public Playlist[] newArray(int i) {
            return new Playlist[i];
        }
    };
    public static final Playlist EMPTY_PLAYLIST = new Playlist(Collections.emptyMap(), Collections.emptyList());
    public static final int PARCEL_OBJECT_TYPE = 2;
    private List<Video> videos;

    public static final class Fields {
        public static final String NAME = "name";
        public static final String SHORT_DESCRIPTION = "shortDescription";
    }

    @SuppressLint({"WrongConstant"})
    public int describeContents() {
        return 2;
    }

    public Playlist(Map<String, Object> map) {
        super(map);
    }

    public Playlist(Map<String, Object> map, List<Video> list) {
        super(map);
        if (list != null) {
            this.videos = list;
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.VIDEOS_REQUIRED));
    }

    public List<Video> getVideos() {
        return this.videos;
    }

    public Integer getCount() {
        return Integer.valueOf(this.videos.size());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist{");
        if (this.properties.get("name") != null) {
            sb.append("name: ");
            sb.append(this.properties.get("name"));
        }
        if (this.properties.get("shortDescription") != null) {
            sb.append(" shortDescription: ");
            sb.append(this.properties.get("shortDescription"));
        }
        sb.append(" videos: ");
        List<Video> list = this.videos;
        sb.append(list != null ? list.size() : 0);
        sb.append("}");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(describeContents());
        parcel.writeSerializable(this);
    }
}

package com.brightcove.player.event;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MediaEvent extends AbstractEvent<Parcelable> implements Parcelable {
    public static Creator<MediaEvent> CREATOR = new Creator<MediaEvent>() {
        public MediaEvent createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            if (readInt == 0) {
                String readString = parcel.readString();
                HashMap hashMap = new HashMap();
                parcel.readMap(hashMap, ClassLoader.getSystemClassLoader());
                return new MediaEvent(readString, hashMap);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown parcel type: ");
            sb.append(readInt);
            throw new IllegalArgumentException(sb.toString());
        }

        public MediaEvent[] newArray(int i) {
            return new MediaEvent[i];
        }
    };
    public static final int PARCEL_OBJECT_TYPE = 0;
    private final Map<String, Parcelable> properties;

    public int describeContents() {
        return 0;
    }

    public MediaEvent(String str) {
        this(str, Collections.emptyMap());
    }

    public MediaEvent(String str, Map<String, Parcelable> map) {
        super(str);
        this.properties = map;
    }

    public static MediaEvent create(@NonNull Event event) {
        HashMap hashMap = new HashMap();
        for (Entry entry : event.getProperties().entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Parcelable) {
                hashMap.put(entry.getKey(), (Parcelable) value);
            }
        }
        return new MediaEvent(event.getType(), hashMap);
    }

    @NonNull
    public Map<String, Parcelable> getProperties() {
        return Collections.unmodifiableMap(this.properties);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(describeContents());
        parcel.writeString(this.type);
        parcel.writeMap(this.properties);
    }
}

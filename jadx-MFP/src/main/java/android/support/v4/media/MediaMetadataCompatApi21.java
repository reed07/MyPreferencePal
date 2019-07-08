package android.support.v4.media;

import android.media.MediaMetadata;
import android.os.Parcel;
import android.support.annotation.RequiresApi;

@RequiresApi
class MediaMetadataCompatApi21 {

    public static class Builder {
        private Builder() {
        }
    }

    public static void writeToParcel(Object obj, Parcel parcel, int i) {
        ((MediaMetadata) obj).writeToParcel(parcel, i);
    }

    private MediaMetadataCompatApi21() {
    }
}

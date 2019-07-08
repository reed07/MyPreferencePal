package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;
import java.util.Date;

public final class VideoObjectBuilder extends IndexableBuilder<VideoObjectBuilder> {
    VideoObjectBuilder() {
        super("VideoObject");
    }

    public final VideoObjectBuilder setAuthor(@NonNull PersonBuilder personBuilder) {
        return (VideoObjectBuilder) put("author", (S[]) new PersonBuilder[]{personBuilder});
    }

    public final VideoObjectBuilder setDuration(long j) {
        return (VideoObjectBuilder) put("duration", j);
    }

    public final VideoObjectBuilder setDurationWatched(long j) {
        return (VideoObjectBuilder) put("durationWatched", j);
    }

    public final VideoObjectBuilder setUploadDate(@NonNull Date date) {
        return (VideoObjectBuilder) put("uploadDate", date.getTime());
    }

    public final VideoObjectBuilder setSeriesName(@NonNull String str) {
        return (VideoObjectBuilder) put("seriesName", str);
    }

    public final VideoObjectBuilder setLocationCreated(@NonNull PlaceBuilder placeBuilder) {
        return (VideoObjectBuilder) put("locationCreated", (S[]) new PlaceBuilder[]{placeBuilder});
    }
}

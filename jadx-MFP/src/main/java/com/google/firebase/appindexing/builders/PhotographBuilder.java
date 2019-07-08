package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;
import java.util.Date;

public final class PhotographBuilder extends IndexableBuilder<PhotographBuilder> {
    PhotographBuilder() {
        super("Photograph");
    }

    public final PhotographBuilder setLocationCreated(@NonNull PlaceBuilder placeBuilder) {
        return (PhotographBuilder) put("locationCreated", (S[]) new PlaceBuilder[]{placeBuilder});
    }

    public final PhotographBuilder setDateCreated(@NonNull Date date) {
        return (PhotographBuilder) put("dateCreated", date.getTime());
    }
}

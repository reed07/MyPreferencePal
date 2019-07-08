package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;

public final class PlaceBuilder extends IndexableBuilder<PlaceBuilder> {
    PlaceBuilder() {
        super("Place");
    }

    public final PlaceBuilder setGeo(@NonNull GeoShapeBuilder geoShapeBuilder) {
        return (PlaceBuilder) put("geo", (S[]) new GeoShapeBuilder[]{geoShapeBuilder});
    }
}

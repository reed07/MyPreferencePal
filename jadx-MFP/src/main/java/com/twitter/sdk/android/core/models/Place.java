package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Place {

    public static class BoundingBox {
        @SerializedName("coordinates")
        public final List<List<List<Double>>> coordinates;
        @SerializedName("type")
        public final String type;

        private BoundingBox() {
            this(null, null);
        }

        public BoundingBox(List<List<List<Double>>> list, String str) {
            this.coordinates = ModelUtils.getSafeList(list);
            this.type = str;
        }
    }
}

package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserEntities {

    public static class UrlEntities {
        @SerializedName("urls")
        public final List<UrlEntity> urls;

        private UrlEntities() {
            this(null);
        }

        public UrlEntities(List<UrlEntity> list) {
            this.urls = ModelUtils.getSafeList(list);
        }
    }
}

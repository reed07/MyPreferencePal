package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.MediaEntity.Sizes;
import java.util.List;

public class Configuration {
    @SerializedName("dm_text_character_limit")
    public final int dmTextCharacterLimit;
    @SerializedName("non_username_paths")
    public final List<String> nonUsernamePaths;
    @SerializedName("photo_size_limit")
    public final long photoSizeLimit;
    @SerializedName("photo_sizes")
    public final Sizes photoSizes;
    @SerializedName("short_url_length_https")
    public final int shortUrlLengthHttps;

    private Configuration() {
        this(0, null, 0, null, 0);
    }

    public Configuration(int i, List<String> list, long j, Sizes sizes, int i2) {
        this.dmTextCharacterLimit = i;
        this.nonUsernamePaths = ModelUtils.getSafeList(list);
        this.photoSizeLimit = j;
        this.photoSizes = sizes;
        this.shortUrlLengthHttps = i2;
    }
}

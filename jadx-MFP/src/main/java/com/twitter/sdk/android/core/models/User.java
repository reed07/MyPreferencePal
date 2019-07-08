package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class User implements Identifiable, Serializable {
    private static final long serialVersionUID = 4663450696842173958L;
    @SerializedName("profile_image_url_https")
    public final String profileImageUrlHttps;
}

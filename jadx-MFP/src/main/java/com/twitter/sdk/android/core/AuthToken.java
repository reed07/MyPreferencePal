package com.twitter.sdk.android.core;

import com.google.gson.annotations.SerializedName;

public abstract class AuthToken {
    @SerializedName("created_at")
    protected final long createdAt;

    public AuthToken() {
        this(System.currentTimeMillis());
    }

    protected AuthToken(long j) {
        this.createdAt = j;
    }
}

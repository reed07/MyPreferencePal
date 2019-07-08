package com.twitter.sdk.android.core.internal.oauth;

import com.google.gson.annotations.SerializedName;

class GuestTokenResponse {
    @SerializedName("guest_token")
    public final String guestToken;
}

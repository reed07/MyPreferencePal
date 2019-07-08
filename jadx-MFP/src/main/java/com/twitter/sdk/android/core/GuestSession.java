package com.twitter.sdk.android.core;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import com.twitter.sdk.android.core.internal.persistence.SerializationStrategy;

public class GuestSession extends Session<GuestAuthToken> {

    public static class Serializer implements SerializationStrategy<GuestSession> {
        private final Gson gson = new GsonBuilder().registerTypeAdapter(GuestAuthToken.class, new AuthTokenAdapter()).create();

        public String serialize(GuestSession guestSession) {
            if (!(guestSession == null || guestSession.getAuthToken() == null)) {
                try {
                    return this.gson.toJson((Object) guestSession);
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to serialize session ");
                    sb.append(e.getMessage());
                    Twitter.getLogger().d("Twitter", sb.toString());
                }
            }
            return "";
        }

        public GuestSession deserialize(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    return (GuestSession) this.gson.fromJson(str, GuestSession.class);
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to deserialize session ");
                    sb.append(e.getMessage());
                    Twitter.getLogger().d("Twitter", sb.toString());
                }
            }
            return null;
        }
    }

    public GuestSession(GuestAuthToken guestAuthToken) {
        super(guestAuthToken, 0);
    }
}

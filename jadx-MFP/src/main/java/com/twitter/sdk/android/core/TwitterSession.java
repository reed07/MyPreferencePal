package com.twitter.sdk.android.core;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.internal.persistence.SerializationStrategy;

public class TwitterSession extends Session<TwitterAuthToken> {
    @SerializedName("user_name")
    private final String userName;

    static class Serializer implements SerializationStrategy<TwitterSession> {
        private final Gson gson = new Gson();

        public String serialize(TwitterSession twitterSession) {
            if (!(twitterSession == null || twitterSession.getAuthToken() == null)) {
                try {
                    return this.gson.toJson((Object) twitterSession);
                } catch (Exception e) {
                    Twitter.getLogger().d("Twitter", e.getMessage());
                }
            }
            return "";
        }

        public TwitterSession deserialize(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    return (TwitterSession) this.gson.fromJson(str, TwitterSession.class);
                } catch (Exception e) {
                    Twitter.getLogger().d("Twitter", e.getMessage());
                }
            }
            return null;
        }
    }

    public TwitterSession(TwitterAuthToken twitterAuthToken, long j, String str) {
        super(twitterAuthToken, j);
        this.userName = str;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        TwitterSession twitterSession = (TwitterSession) obj;
        String str = this.userName;
        if (str != null) {
            z = str.equals(twitterSession.userName);
        } else if (twitterSession.userName != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.userName;
        return hashCode + (str != null ? str.hashCode() : 0);
    }
}

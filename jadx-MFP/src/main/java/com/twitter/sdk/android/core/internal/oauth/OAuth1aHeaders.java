package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import java.util.Map;

public class OAuth1aHeaders {
    public String getAuthorizationHeader(TwitterAuthConfig twitterAuthConfig, TwitterAuthToken twitterAuthToken, String str, String str2, String str3, Map<String, String> map) {
        return getOAuth1aParameters(twitterAuthConfig, twitterAuthToken, str, str2, str3, map).getAuthorizationHeader();
    }

    /* access modifiers changed from: 0000 */
    public OAuth1aParameters getOAuth1aParameters(TwitterAuthConfig twitterAuthConfig, TwitterAuthToken twitterAuthToken, String str, String str2, String str3, Map<String, String> map) {
        OAuth1aParameters oAuth1aParameters = new OAuth1aParameters(twitterAuthConfig, twitterAuthToken, str, str2, str3, map);
        return oAuth1aParameters;
    }
}

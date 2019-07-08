package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.TwitterApi;
import com.twitter.sdk.android.core.internal.network.UrlUtils;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class OAuth2Service extends OAuthService {
    OAuth2Api api = ((OAuth2Api) getRetrofit().create(OAuth2Api.class));

    interface OAuth2Api {
        @FormUrlEncoded
        @POST("/oauth2/token")
        @Headers({"Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
        Call<OAuth2Token> getAppAuthToken(@Header("Authorization") String str, @Field("grant_type") String str2);

        @POST("/1.1/guest/activate.json")
        Call<GuestTokenResponse> getGuestToken(@Header("Authorization") String str);
    }

    public OAuth2Service(TwitterCore twitterCore, TwitterApi twitterApi) {
        super(twitterCore, twitterApi);
    }

    public void requestGuestAuthToken(final Callback<GuestAuthToken> callback) {
        requestAppAuthToken(new Callback<OAuth2Token>() {
            public void success(Result<OAuth2Token> result) {
                final OAuth2Token oAuth2Token = (OAuth2Token) result.data;
                OAuth2Service.this.requestGuestToken(new Callback<GuestTokenResponse>() {
                    public void success(Result<GuestTokenResponse> result) {
                        callback.success(new Result(new GuestAuthToken(oAuth2Token.getTokenType(), oAuth2Token.getAccessToken(), ((GuestTokenResponse) result.data).guestToken), null));
                    }

                    public void failure(TwitterException twitterException) {
                        Twitter.getLogger().e("Twitter", "Your app may not allow guest auth. Please talk to us regarding upgrading your consumer key.", twitterException);
                        callback.failure(twitterException);
                    }
                }, oAuth2Token);
            }

            public void failure(TwitterException twitterException) {
                Twitter.getLogger().e("Twitter", "Failed to get app auth token", twitterException);
                Callback callback = callback;
                if (callback != null) {
                    callback.failure(twitterException);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void requestAppAuthToken(Callback<OAuth2Token> callback) {
        this.api.getAppAuthToken(getAuthHeader(), "client_credentials").enqueue(callback);
    }

    /* access modifiers changed from: 0000 */
    public void requestGuestToken(Callback<GuestTokenResponse> callback, OAuth2Token oAuth2Token) {
        this.api.getGuestToken(getAuthorizationHeader(oAuth2Token)).enqueue(callback);
    }

    private String getAuthorizationHeader(OAuth2Token oAuth2Token) {
        StringBuilder sb = new StringBuilder();
        sb.append("Bearer ");
        sb.append(oAuth2Token.getAccessToken());
        return sb.toString();
    }

    private String getAuthHeader() {
        TwitterAuthConfig authConfig = getTwitterCore().getAuthConfig();
        StringBuilder sb = new StringBuilder();
        sb.append(UrlUtils.percentEncode(authConfig.getConsumerKey()));
        sb.append(":");
        sb.append(UrlUtils.percentEncode(authConfig.getConsumerSecret()));
        ByteString encodeUtf8 = ByteString.encodeUtf8(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Basic ");
        sb2.append(encodeUtf8.base64());
        return sb2.toString();
    }
}

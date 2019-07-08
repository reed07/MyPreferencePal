package com.twitter.sdk.android.core;

import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.twitter.sdk.android.core.models.ApiError;
import com.twitter.sdk.android.core.models.ApiErrors;
import com.twitter.sdk.android.core.models.SafeListAdapter;
import com.twitter.sdk.android.core.models.SafeMapAdapter;
import retrofit2.Response;

public class TwitterApiException extends TwitterException {
    private final ApiError apiError;
    private final int code;
    private final Response response;
    private final TwitterRateLimit twitterRateLimit;

    public TwitterApiException(Response response2) {
        this(response2, readApiError(response2), readApiRateLimit(response2), response2.code());
    }

    TwitterApiException(Response response2, ApiError apiError2, TwitterRateLimit twitterRateLimit2, int i) {
        super(createExceptionMessage(i));
        this.apiError = apiError2;
        this.twitterRateLimit = twitterRateLimit2;
        this.code = i;
        this.response = response2;
    }

    public static TwitterRateLimit readApiRateLimit(Response response2) {
        return new TwitterRateLimit(response2.headers());
    }

    public static ApiError readApiError(Response response2) {
        try {
            String readUtf8 = response2.errorBody().source().buffer().clone().readUtf8();
            if (!TextUtils.isEmpty(readUtf8)) {
                return parseApiError(readUtf8);
            }
        } catch (Exception e) {
            Twitter.getLogger().e("Twitter", "Unexpected response", e);
        }
        return null;
    }

    static ApiError parseApiError(String str) {
        try {
            ApiErrors apiErrors = (ApiErrors) new GsonBuilder().registerTypeAdapterFactory(new SafeListAdapter()).registerTypeAdapterFactory(new SafeMapAdapter()).create().fromJson(str, ApiErrors.class);
            if (!apiErrors.errors.isEmpty()) {
                return (ApiError) apiErrors.errors.get(0);
            }
        } catch (JsonSyntaxException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid json: ");
            sb.append(str);
            Twitter.getLogger().e("Twitter", sb.toString(), e);
        }
        return null;
    }

    static String createExceptionMessage(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP request failed, Status: ");
        sb.append(i);
        return sb.toString();
    }
}

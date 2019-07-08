package com.twitter.sdk.android.core;

import retrofit2.Call;
import retrofit2.Response;

public abstract class Callback<T> implements retrofit2.Callback<T> {
    public abstract void failure(TwitterException twitterException);

    public abstract void success(Result<T> result);

    public final void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            success(new Result(response.body(), response));
        } else {
            failure(new TwitterApiException(response));
        }
    }

    public final void onFailure(Call<T> call, Throwable th) {
        failure(new TwitterException("Request Failure", th));
    }
}

package com.twitter.sdk.android.core;

import retrofit2.Response;

public class Result<T> {
    public final T data;
    public final Response response;

    public Result(T t, Response response2) {
        this.data = t;
        this.response = response2;
    }
}

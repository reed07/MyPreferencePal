package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AccountService {
    @GET("/1.1/account/verify_credentials.json")
    Call<User> verifyCredentials(@Query("include_entities") Boolean bool, @Query("skip_status") Boolean bool2, @Query("include_email") Boolean bool3);
}

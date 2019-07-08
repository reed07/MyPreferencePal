package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.models.Configuration;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ConfigurationService {
    @GET("/1.1/help/configuration.json")
    Call<Configuration> configuration();
}

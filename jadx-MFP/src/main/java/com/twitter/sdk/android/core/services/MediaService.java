package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.models.Media;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MediaService {
    @POST("https://upload.twitter.com/1.1/media/upload.json")
    @Multipart
    Call<Media> upload(@Part("media") RequestBody requestBody, @Part("media_data") RequestBody requestBody2, @Part("additional_owners") RequestBody requestBody3);
}

package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.models.TwitterCollection;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CollectionService {
    @GET("/1.1/collections/entries.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<TwitterCollection> collection(@Query("id") String str, @Query("count") Integer num, @Query("max_position") Long l, @Query("min_position") Long l2);
}

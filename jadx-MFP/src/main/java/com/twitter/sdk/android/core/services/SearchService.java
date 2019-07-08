package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.services.params.Geocode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("/1.1/search/tweets.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<Search> tweets(@Query("q") String str, @Query(encoded = true, value = "geocode") Geocode geocode, @Query("lang") String str2, @Query("locale") String str3, @Query("result_type") String str4, @Query("count") Integer num, @Query("until") String str5, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("include_entities") Boolean bool);
}

package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.models.Tweet;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ListService {
    @GET("/1.1/lists/statuses.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<List<Tweet>> statuses(@Query("list_id") Long l, @Query("slug") String str, @Query("owner_screen_name") String str2, @Query("owner_id") Long l2, @Query("since_id") Long l3, @Query("max_id") Long l4, @Query("count") Integer num, @Query("include_entities") Boolean bool, @Query("include_rts") Boolean bool2);
}

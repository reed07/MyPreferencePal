package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.models.Tweet;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StatusesService {
    @FormUrlEncoded
    @POST("/1.1/statuses/destroy/{id}.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<Tweet> destroy(@Path("id") Long l, @Field("trim_user") Boolean bool);

    @GET("/1.1/statuses/home_timeline.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<List<Tweet>> homeTimeline(@Query("count") Integer num, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("trim_user") Boolean bool, @Query("exclude_replies") Boolean bool2, @Query("contributor_details") Boolean bool3, @Query("include_entities") Boolean bool4);

    @GET("/1.1/statuses/lookup.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<List<Tweet>> lookup(@Query("id") String str, @Query("include_entities") Boolean bool, @Query("trim_user") Boolean bool2, @Query("map") Boolean bool3);

    @GET("/1.1/statuses/mentions_timeline.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<List<Tweet>> mentionsTimeline(@Query("count") Integer num, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("trim_user") Boolean bool, @Query("contributor_details") Boolean bool2, @Query("include_entities") Boolean bool3);

    @FormUrlEncoded
    @POST("/1.1/statuses/retweet/{id}.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<Tweet> retweet(@Path("id") Long l, @Field("trim_user") Boolean bool);

    @GET("/1.1/statuses/retweets_of_me.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<List<Tweet>> retweetsOfMe(@Query("count") Integer num, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("trim_user") Boolean bool, @Query("include_entities") Boolean bool2, @Query("include_user_entities") Boolean bool3);

    @GET("/1.1/statuses/show.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<Tweet> show(@Query("id") Long l, @Query("trim_user") Boolean bool, @Query("include_my_retweet") Boolean bool2, @Query("include_entities") Boolean bool3);

    @FormUrlEncoded
    @POST("/1.1/statuses/unretweet/{id}.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<Tweet> unretweet(@Path("id") Long l, @Field("trim_user") Boolean bool);

    @FormUrlEncoded
    @POST("/1.1/statuses/update.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<Tweet> update(@Field("status") String str, @Field("in_reply_to_status_id") Long l, @Field("possibly_sensitive") Boolean bool, @Field("lat") Double d, @Field("long") Double d2, @Field("place_id") String str2, @Field("display_coordinates") Boolean bool2, @Field("trim_user") Boolean bool3, @Field("media_ids") String str3);

    @GET("/1.1/statuses/user_timeline.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<List<Tweet>> userTimeline(@Query("user_id") Long l, @Query("screen_name") String str, @Query("count") Integer num, @Query("since_id") Long l2, @Query("max_id") Long l3, @Query("trim_user") Boolean bool, @Query("exclude_replies") Boolean bool2, @Query("contributor_details") Boolean bool3, @Query("include_rts") Boolean bool4);
}

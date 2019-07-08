package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TweetEntities {
    static final TweetEntities EMPTY;
    @SerializedName("hashtags")
    public final List<HashtagEntity> hashtags;
    @SerializedName("media")
    public final List<MediaEntity> media;
    @SerializedName("symbols")
    public final List<SymbolEntity> symbols;
    @SerializedName("urls")
    public final List<UrlEntity> urls;
    @SerializedName("user_mentions")
    public final List<MentionEntity> userMentions;

    static {
        TweetEntities tweetEntities = new TweetEntities(null, null, null, null, null);
        EMPTY = tweetEntities;
    }

    private TweetEntities() {
        this(null, null, null, null, null);
    }

    public TweetEntities(List<UrlEntity> list, List<MentionEntity> list2, List<MediaEntity> list3, List<HashtagEntity> list4, List<SymbolEntity> list5) {
        this.urls = ModelUtils.getSafeList(list);
        this.userMentions = ModelUtils.getSafeList(list2);
        this.media = ModelUtils.getSafeList(list3);
        this.hashtags = ModelUtils.getSafeList(list4);
        this.symbols = ModelUtils.getSafeList(list5);
    }
}

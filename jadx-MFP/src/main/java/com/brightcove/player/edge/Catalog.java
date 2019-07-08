package com.brightcove.player.edge;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.network.HttpRequestConfig;
import com.brightcove.player.network.HttpRequestConfig.Builder;
import java.util.HashMap;
import java.util.Map;

@ListensFor(events = {})
@Emits(events = {"error", "account", "videoDownloadStarted", "videoDownloadCompleted", "videoDownloadCancelled", "videoDownloadFailed"})
public class Catalog extends AbstractComponent {
    public static final String DEFAULT_EDGE_BASE_URL = "https://edge.api.brightcove.com/playback/v1";
    private String account;
    private String baseURL;
    private String policy;

    public Catalog(EventEmitter eventEmitter, String str, String str2) {
        this(eventEmitter, str, str2, DEFAULT_EDGE_BASE_URL);
    }

    public Catalog(EventEmitter eventEmitter, String str, String str2, String str3) {
        super(eventEmitter, Catalog.class);
        this.account = str;
        this.policy = str2;
        this.baseURL = str3;
        HashMap hashMap = new HashMap();
        hashMap.put("value", str);
        eventEmitter.emit("account", hashMap);
    }

    public void findPlaylistByID(@NonNull String str, @NonNull HttpRequestConfig httpRequestConfig, @NonNull PlaylistListener playlistListener) {
        GetPlaylistTask getPlaylistTask = new GetPlaylistTask(this.eventEmitter, this.baseURL, httpRequestConfig, this.account, this.policy);
        getPlaylistTask.getById(str, playlistListener);
    }

    public void findPlaylistByID(@NonNull String str, @NonNull PlaylistListener playlistListener) {
        findPlaylistByID(str, new Builder().build(), playlistListener);
    }

    @Deprecated
    public void findPlaylistByID(@NonNull String str, @Nullable Map<String, String> map, @NonNull PlaylistListener playlistListener) {
        findPlaylistByID(str, new Builder().addRequestHeaders(map).build(), playlistListener);
    }

    @Deprecated
    public void findPlaylistByID(@NonNull String str, @Nullable Map<String, String> map, @Nullable Map<String, String> map2, @NonNull PlaylistListener playlistListener) {
        findPlaylistByID(str, new Builder().addRequestHeaders(map).addQueryParameters(map2).build(), playlistListener);
    }

    public void findPlaylistByReferenceID(@NonNull String str, @NonNull HttpRequestConfig httpRequestConfig, @NonNull PlaylistListener playlistListener) {
        GetPlaylistTask getPlaylistTask = new GetPlaylistTask(this.eventEmitter, this.baseURL, httpRequestConfig, this.account, this.policy);
        getPlaylistTask.getByReferenceId(str, playlistListener);
    }

    public void findPlaylistByReferenceID(@NonNull String str, @NonNull PlaylistListener playlistListener) {
        findPlaylistByReferenceID(str, new Builder().build(), playlistListener);
    }

    @Deprecated
    public void findPlaylistByReferenceID(@NonNull String str, @Nullable Map<String, String> map, @NonNull PlaylistListener playlistListener) {
        findPlaylistByReferenceID(str, new Builder().addRequestHeaders(map).build(), playlistListener);
    }

    @Deprecated
    public void findPlaylistByReferenceID(@NonNull String str, @Nullable Map<String, String> map, @Nullable Map<String, String> map2, @NonNull PlaylistListener playlistListener) {
        findPlaylistByReferenceID(str, new Builder().addRequestHeaders(map).addQueryParameters(map2).build(), playlistListener);
    }

    public void findVideoByID(@NonNull String str, @NonNull HttpRequestConfig httpRequestConfig, @NonNull VideoListener videoListener) {
        GetVideoTask getVideoTask = new GetVideoTask(this.eventEmitter, this.baseURL, httpRequestConfig, this.account, this.policy);
        getVideoTask.getById(str, videoListener);
    }

    public void findVideoByID(@NonNull String str, @NonNull VideoListener videoListener) {
        findVideoByID(str, new Builder().build(), videoListener);
    }

    @Deprecated
    public void findVideoByID(@NonNull String str, @Nullable Map<String, String> map, @NonNull VideoListener videoListener) {
        findVideoByID(str, new Builder().addRequestHeaders(map).build(), videoListener);
    }

    @Deprecated
    public void findVideoByID(@NonNull String str, @Nullable Map<String, String> map, @Nullable Map<String, String> map2, @NonNull VideoListener videoListener) {
        findVideoByID(str, new Builder().addRequestHeaders(map).addQueryParameters(map2).build(), videoListener);
    }

    public void findVideoByReferenceID(@NonNull String str, @NonNull HttpRequestConfig httpRequestConfig, @NonNull VideoListener videoListener) {
        GetVideoTask getVideoTask = new GetVideoTask(this.eventEmitter, this.baseURL, httpRequestConfig, this.account, this.policy);
        getVideoTask.getByReferenceId(str, videoListener);
    }

    public void findVideoByReferenceID(@NonNull String str, @NonNull VideoListener videoListener) {
        findVideoByReferenceID(str, new Builder().build(), videoListener);
    }

    @Deprecated
    public void findVideoByReferenceID(@NonNull String str, @Nullable Map<String, String> map, @NonNull VideoListener videoListener) {
        findVideoByReferenceID(str, new Builder().addRequestHeaders(map).build(), videoListener);
    }

    @Deprecated
    public void findVideoByReferenceID(@NonNull String str, @Nullable Map<String, String> map, @Nullable Map<String, String> map2, @NonNull VideoListener videoListener) {
        findVideoByReferenceID(str, new Builder().addRequestHeaders(map).addQueryParameters(map2).build(), videoListener);
    }
}

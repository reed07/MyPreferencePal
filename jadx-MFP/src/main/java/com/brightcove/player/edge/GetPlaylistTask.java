package com.brightcove.player.edge;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.model.Playlist;
import com.brightcove.player.model.Video;
import com.brightcove.player.network.HttpRequestConfig;
import com.brightcove.player.network.HttpRequestConfig.Builder;
import com.brightcove.player.util.ErrorUtil;
import com.brightcove.player.util.EventEmitterUtil;
import com.facebook.internal.NativeProtocol;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@ListensFor(events = {})
@Emits(events = {})
public class GetPlaylistTask extends EdgeTask implements Component {
    private PlaylistListener playlistListener;
    private long startResponseTimeMs;

    @Deprecated
    public GetPlaylistTask(@NonNull EventEmitter eventEmitter, @NonNull String str, @Nullable Map<String, String> map, @NonNull String str2, @NonNull String str3) {
        this(eventEmitter, str, new Builder().addRequestHeaders(map).build(), str2, str3);
    }

    @Deprecated
    public GetPlaylistTask(@NonNull EventEmitter eventEmitter, @NonNull String str, @Nullable Map<String, String> map, @Nullable Map<String, String> map2, @NonNull String str2, @NonNull String str3) {
        this(eventEmitter, str, new Builder().addRequestHeaders(map).addQueryParameters(map2).build(), str2, str3);
    }

    public GetPlaylistTask(@NonNull EventEmitter eventEmitter, @NonNull String str, @NonNull HttpRequestConfig httpRequestConfig, @NonNull String str2, @NonNull String str3) {
        super(eventEmitter, str, httpRequestConfig, str2, str3);
    }

    public void getById(String str, PlaylistListener playlistListener2) {
        this.playlistListener = playlistListener2;
        try {
            URI createURI = createURI("accounts", this.account, "playlists", str);
            execute(new URI[]{createURI});
            emitAnalyticsCatalogRequest(createURI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void getByReferenceId(String str, PlaylistListener playlistListener2) {
        this.playlistListener = playlistListener2;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("ref:");
            sb.append(str);
            URI createURI = createURI("accounts", this.account, "playlists", sb.toString());
            execute(new URI[]{createURI});
            emitAnalyticsCatalogRequest(createURI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void emitAnalyticsCatalogRequest(URI uri) {
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.CATALOG_URL, uri);
        this.eventEmitter.emit(EventType.ANALYTICS_CATALOG_REQUEST, hashMap);
        this.startResponseTimeMs = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.CATALOG_URL, this.uri);
        hashMap.put(AbstractEvent.RESPONSE_TIME_MS, Long.valueOf(System.currentTimeMillis() - this.startResponseTimeMs));
        this.eventEmitter.emit(EventType.ANALYTICS_CATALOG_RESPONSE, hashMap);
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(NativeProtocol.BRIDGE_ARG_ERROR_CODE)) {
                    hashMap.clear();
                    VideoParser.parseJSONProperties(jSONObject, hashMap, Collections.emptySet());
                    this.eventEmitter.emit("error", hashMap);
                    if (!jSONObject.isNull("message")) {
                        this.playlistListener.onError(jSONObject.getString("message"));
                    }
                } else {
                    Playlist buildPlaylistFromJSON = VideoParser.buildPlaylistFromJSON(jSONObject, this.eventEmitter);
                    configureAuthorizationTokenToPlaylistVideos(buildPlaylistFromJSON);
                    this.playlistListener.onPlaylist(buildPlaylistFromJSON);
                }
            } catch (JSONException e) {
                String format = String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.MEDIA_REQUEST_INVALID_JSON), new Object[]{this.uri});
                this.errors.add(format);
                EventEmitterUtil.emitError(this.eventEmitter, format, e);
            } catch (IllegalArgumentException e2) {
                String format2 = String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.MEDIA_REQUEST_NO_JSON), new Object[]{this.uri});
                this.errors.add(format2);
                EventEmitterUtil.emitError(this.eventEmitter, format2, e2);
            } catch (VideoParseException e3) {
                EventEmitterUtil.emitError(this.eventEmitter, "", e3);
            }
        } else {
            List list = this.errors;
            StringBuilder sb = new StringBuilder();
            sb.append("No data was found that matched your request: ");
            sb.append(this.uri);
            list.add(sb.toString());
        }
        if (!this.errors.isEmpty()) {
            for (String onError : this.errors) {
                this.playlistListener.onError(onError);
            }
        }
    }

    private void configureAuthorizationTokenToPlaylistVideos(@NonNull Playlist playlist) {
        BrightcoveTokenAuthorizer brightcoveTokenAuthorizer = new BrightcoveTokenAuthorizer();
        for (Video configure : playlist.getVideos()) {
            brightcoveTokenAuthorizer.configure(configure, this.httpRequestConfig.getBrightcoveAuthorizationToken());
        }
    }
}

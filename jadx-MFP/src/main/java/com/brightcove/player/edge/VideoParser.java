package com.brightcove.player.edge;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.brightcove.player.captioning.BrightcoveCaptionFormat;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.model.CuePoint;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Playlist;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.SourceAwareMetadataObject;
import com.brightcove.player.model.SourceCollection;
import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.Fields;
import com.brightcove.player.model.Video.ProjectionFormat;
import com.brightcove.player.util.ErrorUtil;
import com.brightcove.player.util.EventEmitterUtil;
import com.brightcove.player.util.StringUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class VideoParser {
    private static final String ACCOUNT_ID = "account_id";
    private static final String AVG_BITRATE = "avg_bitrate";
    private static final String CAPTIONS = "captions";
    private static final String CONTAINER = "container";
    private static final String CUE_POINTS = "cue_points";
    private static final String CUSTOM_FIELDS = "custom_fields";
    private static final String DASH = "application/dash+xml";
    private static final String DEFAULT = "default";
    private static final String DESCRIPTION = "description";
    private static final String DURATION = "duration";
    private static final String EN = "en";
    private static final String FLV = "flv";
    private static final String HLS_APPLE = "application/vnd.apple.mpegurl";
    private static final String HLS_X = "application/x-mpegURL";
    private static final String ID = "id";
    private static final String IN_BAND_METADATA_TRACK_DISPATCH_TYPE = "in_band_metadata_track_dispatch_type";
    private static final String KEY_SYSTEMS = "key_systems";
    private static final String KIND = "kind";
    private static final String LICENSE_URL = "license_url";
    private static final String M2TS = "m2ts";
    private static final String M4F = "m4f";
    private static final String MIME_TYPE = "mime_type";
    private static final String MP4 = "mp4";
    private static final String NAME = "name";
    private static final String OFFLINE_ENABLED = "offline_enabled";
    private static final String POSTER = "poster";
    private static final String POSTER_SOURCES = "poster_sources";
    private static final String PROFILES = "profiles";
    private static final String PROJECTION_FORMAT = "projection";
    private static final String REFERENCE_ID = "reference_id";
    private static final String SOURCES = "sources";
    private static final String SRC = "src";
    private static final String SRCLANG = "srclang";
    private static final String TAG = "VideoParser";
    private static final String TEXT_TRACKS = "text_tracks";
    private static final String TEXT_UNKNOWN = "text/unknown";
    private static final String THUMBNAIL = "thumbnail";
    private static final String THUMBNAIL_SOURCES = "thumbnail_sources";
    private static final String TIME = "time";
    private static final String TYPE = "type";
    private static final String VIDEOS = "videos";
    private static final String WVM = "wvm";

    public static Playlist buildPlaylistFromJSON(JSONObject jSONObject, EventEmitter eventEmitter) throws JSONException, VideoParseException {
        Map buildPlaylistProperties = buildPlaylistProperties(jSONObject);
        ArrayList arrayList = new ArrayList();
        if (!jSONObject.isNull("videos")) {
            JSONArray jSONArray = jSONObject.getJSONArray("videos");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2 != null) {
                    arrayList.add(buildVideoFromJSON(jSONObject2, eventEmitter));
                }
            }
        }
        return new Playlist(buildPlaylistProperties, arrayList);
    }

    public static Video buildVideoFromJSON(JSONObject jSONObject, EventEmitter eventEmitter) throws JSONException, VideoParseException {
        Video video;
        Map buildVideoProperties = buildVideoProperties(jSONObject, eventEmitter);
        Set buildVideoSourceCollections = buildVideoSourceCollections(jSONObject);
        List buildVideoCuePoints = buildVideoCuePoints(jSONObject, eventEmitter);
        if (buildVideoCuePoints != null) {
            video = new Video(buildVideoProperties, buildVideoSourceCollections, buildVideoCuePoints);
        } else {
            video = new Video(buildVideoProperties, buildVideoSourceCollections);
        }
        verifyVideoRequiredProperties(video, jSONObject);
        return video;
    }

    private static void verifyVideoRequiredProperties(Video video, JSONObject jSONObject) throws VideoParseException {
        if (video == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to create a video from the json object ");
            sb.append(jSONObject.toString());
            throw new VideoParseException(sb.toString());
        } else if (video.getSourceCollections().isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to create any video source collections from the json object ");
            sb2.append(jSONObject.toString());
            throw new VideoParseException(sb2.toString());
        } else if (!video.getProperties().isEmpty()) {
            loop0:
            for (DeliveryType deliveryType : video.getSourceCollections().keySet()) {
                SourceCollection sourceCollection = (SourceCollection) video.getSourceCollections().get(deliveryType);
                if (sourceCollection == null || sourceCollection.getSources() == null || sourceCollection.getSources().isEmpty()) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Failed creating a SourceCollection with delivery type ");
                    sb3.append(deliveryType);
                    throw new VideoParseException(sb3.toString());
                }
                Iterator it = sourceCollection.getSources().iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object obj = ((Source) it.next()).getProperties().get("key_systems");
                        if (obj instanceof Map) {
                            Map map = (Map) obj;
                            if (!map.isEmpty()) {
                                for (Object next : map.keySet()) {
                                    Object obj2 = map.get(next);
                                    if (obj2 instanceof Map) {
                                        Object obj3 = ((Map) obj2).get("license_url");
                                        if (obj3 == null || TextUtils.isEmpty(obj3.toString())) {
                                        }
                                    }
                                }
                                continue;
                            } else {
                                throw new VideoParseException("A key_systems property was found but it is empty");
                            }
                        }
                    }
                }
                throw new VideoParseException(String.format("A license url for key system %s was not found", new Object[]{next}));
            }
            if (!TextUtils.isEmpty(video.getId())) {
                Object obj4 = video.getProperties().get(Fields.PUBLISHER_ID);
                if (obj4 == null || TextUtils.isEmpty(obj4.toString())) {
                    throw new VideoParseException("A valid account id was not found");
                }
                Object obj5 = video.getProperties().get("duration");
                if (obj5 == null || TextUtils.isEmpty(obj5.toString())) {
                    throw new VideoParseException("A duration value was not found");
                }
                return;
            }
            throw new VideoParseException("A valid video id was not found");
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Failed to create any video properties from the json object ");
            sb4.append(jSONObject.toString());
            throw new VideoParseException(sb4.toString());
        }
    }

    private static Map<String, Object> buildPlaylistProperties(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        if (!jSONObject.isNull("id")) {
            hashMap.put("id", Long.valueOf(jSONObject.getLong("id")));
        }
        if (!jSONObject.isNull("reference_id")) {
            hashMap.put("reference_id", jSONObject.getString("reference_id"));
        }
        if (!jSONObject.isNull("name")) {
            hashMap.put("name", jSONObject.getString("name"));
        }
        if (!jSONObject.isNull("description")) {
            hashMap.put("description", jSONObject.getString("description"));
        }
        HashSet hashSet = new HashSet();
        hashSet.add("id");
        hashSet.add("reference_id");
        hashSet.add("name");
        hashSet.add("description");
        parseJSONProperties(jSONObject, hashMap, hashSet);
        return hashMap;
    }

    private static Map<String, Object> buildVideoProperties(JSONObject jSONObject, EventEmitter eventEmitter) throws JSONException {
        HashMap hashMap = new HashMap();
        if (!jSONObject.isNull(ACCOUNT_ID)) {
            hashMap.put(Fields.PUBLISHER_ID, jSONObject.getString(ACCOUNT_ID));
        }
        if (!jSONObject.isNull("id")) {
            hashMap.put("id", jSONObject.getString("id"));
        }
        if (!jSONObject.isNull("duration")) {
            hashMap.put("duration", Integer.valueOf(jSONObject.getInt("duration")));
        }
        if (!jSONObject.isNull(PROJECTION_FORMAT)) {
            readProjectionFormat(hashMap, jSONObject, eventEmitter);
        }
        if (!jSONObject.isNull(OFFLINE_ENABLED)) {
            hashMap.put(Fields.ODRM_PLAYBACK_ALLOWED, jSONObject.getString(OFFLINE_ENABLED));
        }
        URI findURISource = findURISource(jSONObject, POSTER, eventEmitter);
        if (findURISource != null) {
            hashMap.put(Fields.STILL_IMAGE_URI, findURISource);
        }
        URI findURISource2 = findURISource(jSONObject, "thumbnail", eventEmitter);
        if (findURISource2 != null) {
            hashMap.put("thumbnail", findURISource2);
        }
        if (!jSONObject.isNull(TEXT_TRACKS)) {
            JSONArray jSONArray = jSONObject.getJSONArray(TEXT_TRACKS);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2.getString(KIND).equals(CAPTIONS) && !jSONObject2.isNull(SRC)) {
                    String str = TEXT_UNKNOWN;
                    if (!jSONObject2.isNull(MIME_TYPE)) {
                        str = jSONObject2.getString(MIME_TYPE);
                    }
                    String str2 = EN;
                    if (!jSONObject2.isNull(SRCLANG)) {
                        str2 = jSONObject2.getString(SRCLANG);
                    }
                    arrayList.add(Pair.create(Uri.parse(jSONObject2.getString(SRC)), BrightcoveCaptionFormat.builder().type(str).language(str2).hasInBandMetadataTrackDispatchType(!jSONObject2.isNull(IN_BAND_METADATA_TRACK_DISPATCH_TYPE)).isDefault(!jSONObject2.isNull("default") ? jSONObject2.getBoolean("default") : false).build()));
                }
            }
            hashMap.put(Fields.CAPTION_SOURCES, arrayList);
        }
        if (!jSONObject.isNull(CUSTOM_FIELDS)) {
            JSONObject jSONObject3 = jSONObject.getJSONObject(CUSTOM_FIELDS);
            HashMap hashMap2 = new HashMap();
            if (jSONObject3 != null && jSONObject3.length() > 0) {
                Iterator keys = jSONObject3.keys();
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    if (str3 != null) {
                        hashMap2.put(str3, jSONObject3.getString(str3));
                    }
                }
                if (hashMap2.size() > 0) {
                    hashMap.put("customFields", hashMap2);
                }
            }
        }
        Set findSourceListFromJson = findSourceListFromJson(jSONObject, POSTER_SOURCES);
        Set findSourceListFromJson2 = findSourceListFromJson(jSONObject, Fields.POSTER_SOURCES);
        if (findSourceListFromJson.isEmpty()) {
            findSourceListFromJson = findSourceListFromJson2;
        }
        Set findSourceListFromJson3 = findSourceListFromJson(jSONObject, THUMBNAIL_SOURCES);
        Set findSourceListFromJson4 = findSourceListFromJson(jSONObject, Fields.THUMBNAIL_SOURCES);
        if (findSourceListFromJson3.isEmpty()) {
            findSourceListFromJson3 = findSourceListFromJson4;
        }
        hashMap.put(Fields.POSTER_SOURCES, findSourceListFromJson);
        hashMap.put(Fields.THUMBNAIL_SOURCES, findSourceListFromJson3);
        HashSet hashSet = new HashSet();
        hashSet.add(ACCOUNT_ID);
        hashSet.add("cue_points");
        hashSet.add(CUSTOM_FIELDS);
        hashSet.add("duration");
        hashSet.add("id");
        hashSet.add(POSTER);
        hashSet.add("thumbnail");
        hashSet.add(SOURCES);
        hashSet.add(POSTER_SOURCES);
        hashSet.add(Fields.POSTER_SOURCES);
        hashSet.add(THUMBNAIL_SOURCES);
        hashSet.add(Fields.THUMBNAIL_SOURCES);
        hashSet.add(TEXT_TRACKS);
        hashSet.add(PROJECTION_FORMAT);
        hashSet.add(OFFLINE_ENABLED);
        parseJSONProperties(jSONObject, hashMap, hashSet);
        return hashMap;
    }

    @Nullable
    private static URI findURISource(@NonNull JSONObject jSONObject, String str, EventEmitter eventEmitter) throws JSONException {
        if (!jSONObject.isNull(str)) {
            String string = jSONObject.getString(str);
            try {
                return new URI(string);
            } catch (URISyntaxException e) {
                EventEmitterUtil.emitError(eventEmitter, String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.INVALID_URI), new Object[]{string}), e);
            }
        }
        return null;
    }

    @NonNull
    private static Set<URI> findSourceListFromJson(@NonNull JSONObject jSONObject, String str) throws JSONException {
        HashSet hashSet = new HashSet();
        if (!jSONObject.isNull(str)) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    hashSet.add(new URI(jSONArray.getJSONObject(i).getString(SRC)));
                }
            } catch (URISyntaxException e) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("There was a problem trying to read the source list named ");
                sb.append(str);
                Log.w(str2, sb.toString(), e);
            }
        }
        return hashSet;
    }

    private static void readProjectionFormat(Map<String, Object> map, JSONObject jSONObject, EventEmitter eventEmitter) throws JSONException {
        String string = jSONObject.getString(PROJECTION_FORMAT);
        ProjectionFormat parse = ProjectionFormat.parse(string);
        if (parse == null) {
            parse = ProjectionFormat.NORMAL;
            String format = String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.PROJECTION_FORMAT_NOT_SUPPORTED), new Object[]{string});
            HashMap hashMap = new HashMap();
            hashMap.put("message", format);
            eventEmitter.emit("error", hashMap);
        }
        map.put("projectionFormat", parse);
    }

    private static Set<SourceCollection> buildVideoSourceCollections(JSONObject jSONObject) throws JSONException {
        HashSet hashSet = new HashSet();
        Set<Source> buildSourcesFromJSON = buildSourcesFromJSON(jSONObject);
        if (!buildSourcesFromJSON.isEmpty()) {
            HashMap hashMap = new HashMap();
            for (Source source : buildSourcesFromJSON) {
                DeliveryType deliveryType = source.getDeliveryType();
                Set set = (Set) hashMap.get(deliveryType);
                if (set == null) {
                    set = new HashSet();
                    hashMap.put(deliveryType, set);
                }
                set.add(source);
            }
            for (DeliveryType deliveryType2 : hashMap.keySet()) {
                hashSet.add(new SourceCollection((Set) hashMap.get(deliveryType2), deliveryType2));
            }
        }
        return hashSet;
    }

    private static Set<Source> buildSourcesFromJSON(JSONObject jSONObject) throws JSONException {
        HashSet hashSet = new HashSet();
        JSONArray optJSONArray = jSONObject.optJSONArray(SOURCES);
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                Source buildSourceFromJSON = buildSourceFromJSON(optJSONArray.getJSONObject(i));
                if (buildSourceFromJSON != null) {
                    hashSet.add(buildSourceFromJSON);
                }
            }
        }
        return hashSet;
    }

    private static Source buildSourceFromJSON(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        if (!jSONObject.isNull(SRC)) {
            hashMap.put("url", jSONObject.getString(SRC));
            if (jSONObject.isNull("profiles") || (!jSONObject.getString("profiles").startsWith("urn:hbbtv") && !jSONObject.getString("profiles").startsWith("urn:dvb"))) {
                if (!jSONObject.isNull("key_systems")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("key_systems");
                    HashMap hashMap2 = new HashMap();
                    parseJSONProperties(jSONObject2, hashMap2, Collections.emptySet());
                    for (Entry key : hashMap2.entrySet()) {
                        if (((String) key.getKey()).startsWith("com.apple.fps")) {
                            Log.w(TAG, "Ignoring FairPlay source.");
                            return null;
                        }
                    }
                }
                if (!jSONObject.isNull(CONTAINER)) {
                    String string = jSONObject.getString(CONTAINER);
                    if (string.equalsIgnoreCase(MP4)) {
                        hashMap.put(SourceAwareMetadataObject.Fields.DELIVERY_TYPE, DeliveryType.MP4);
                    } else if (string.equalsIgnoreCase(M2TS)) {
                        hashMap.put(SourceAwareMetadataObject.Fields.DELIVERY_TYPE, DeliveryType.HLS);
                    } else if (string.equalsIgnoreCase(FLV)) {
                        hashMap.put(SourceAwareMetadataObject.Fields.DELIVERY_TYPE, DeliveryType.FLV);
                    } else if (string.equalsIgnoreCase(WVM)) {
                        hashMap.put(SourceAwareMetadataObject.Fields.DELIVERY_TYPE, DeliveryType.WVM);
                    } else if (string.equalsIgnoreCase(M4F)) {
                        hashMap.put(SourceAwareMetadataObject.Fields.DELIVERY_TYPE, DeliveryType.DASH);
                    } else {
                        hashMap.put(SourceAwareMetadataObject.Fields.DELIVERY_TYPE, DeliveryType.UNKNOWN);
                    }
                }
                if (!jSONObject.isNull("type")) {
                    String string2 = jSONObject.getString("type");
                    if (string2.equalsIgnoreCase("application/dash+xml")) {
                        hashMap.put(SourceAwareMetadataObject.Fields.DELIVERY_TYPE, DeliveryType.DASH);
                    } else if (string2.equalsIgnoreCase("application/x-mpegURL") || string2.equalsIgnoreCase(HLS_APPLE)) {
                        hashMap.put(SourceAwareMetadataObject.Fields.DELIVERY_TYPE, DeliveryType.HLS);
                    }
                }
                if (!jSONObject.isNull(AVG_BITRATE)) {
                    hashMap.put(Source.Fields.BIT_RATE, Integer.valueOf(jSONObject.getInt(AVG_BITRATE)));
                }
                HashSet hashSet = new HashSet(4);
                hashSet.add(SRC);
                hashSet.add(CONTAINER);
                hashSet.add("type");
                hashSet.add(AVG_BITRATE);
                parseJSONProperties(jSONObject, hashMap, hashSet);
                return new Source((Map<String, Object>) hashMap);
            }
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring profiles: ");
            sb.append(jSONObject.getString("profiles"));
            Log.w(str, sb.toString());
            return null;
        }
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Ignoring source without a src: ");
        sb2.append(jSONObject);
        Log.w(str2, sb2.toString());
        return null;
    }

    private static List<CuePoint> buildVideoCuePoints(JSONObject jSONObject, EventEmitter eventEmitter) throws JSONException {
        if (jSONObject.isNull("cue_points")) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = jSONObject.getJSONArray("cue_points");
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(buildCuePointFromJSON(jSONArray.getJSONObject(i), eventEmitter));
        }
        return arrayList;
    }

    private static CuePoint buildCuePointFromJSON(JSONObject jSONObject, EventEmitter eventEmitter) throws JSONException {
        if (jSONObject.isNull("time")) {
            EventEmitterUtil.emitError(eventEmitter, String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.MISSING_CUE_POINT_PROPERTY), new Object[]{"time"}));
            return null;
        } else if (jSONObject.isNull("type")) {
            EventEmitterUtil.emitError(eventEmitter, String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.MISSING_CUE_POINT_PROPERTY), new Object[]{"type"}));
            return null;
        } else {
            Iterator keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            String str = null;
            int i = 0;
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                char c = 65535;
                int hashCode = str2.hashCode();
                if (hashCode != 3560141) {
                    if (hashCode == 3575610 && str2.equals("type")) {
                        c = 1;
                    }
                } else if (str2.equals("time")) {
                    c = 0;
                }
                switch (c) {
                    case 0:
                        i = (int) (jSONObject.getDouble("time") * 1000.0d);
                        break;
                    case 1:
                        str = jSONObject.getString("type");
                        break;
                    default:
                        hashMap.put(str2, jSONObject.get(str2).toString());
                        break;
                }
            }
            HashSet hashSet = new HashSet(2);
            hashSet.add("time");
            hashSet.add("type");
            parseJSONProperties(jSONObject, hashMap, hashSet);
            return new CuePoint(i, str, (Map<String, Object>) hashMap);
        }
    }

    static void parseJSONProperties(JSONObject jSONObject, Map<String, Object> map, Set<String> set) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (!StringUtil.isEmpty(str) && !jSONObject.isNull(str) && !set.contains(str)) {
                map.put(str, parseObject(jSONObject.get(str)));
            }
        }
    }

    private static Object parseObject(Object obj) throws JSONException {
        if (obj instanceof JSONObject) {
            HashMap hashMap = new HashMap();
            parseJSONProperties((JSONObject) obj, hashMap, Collections.emptySet());
            return hashMap;
        } else if (!(obj instanceof JSONArray)) {
            return obj;
        } else {
            JSONArray jSONArray = (JSONArray) obj;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(parseObject(jSONArray.get(i)));
            }
            return arrayList;
        }
    }
}

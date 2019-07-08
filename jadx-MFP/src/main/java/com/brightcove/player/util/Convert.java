package com.brightcove.player.util;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.brightcove.player.captioning.BrightcoveCaptionFormat;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.SourceAwareMetadataObject;
import com.brightcove.player.model.SourceCollection;
import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.CanSetDownloadIdentifier;
import com.brightcove.player.model.Video.CanSetLicenseExpiryDate;
import com.brightcove.player.model.Video.CanSetLicenseKeySetId;
import com.brightcove.player.model.Video.Fields;
import com.brightcove.player.model.Video.ProjectionFormat;
import com.brightcove.player.model.VideoFields;
import com.google.common.base.Ascii;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

public final class Convert {
    private static final char[] HEX_LOOKUP = "0123456789ABCDEF".toCharArray();
    /* access modifiers changed from: private */
    public static final String TAG = "Convert";

    public static class Lazy {
        static Type BRIGHTCOVE_CAPTION_MAP_TYPE = new TypeToken<Pair<Uri, BrightcoveCaptionFormat>>() {
        }.getType();
        /* access modifiers changed from: private */
        public static final Gson PROPERTIES_GSON = new GsonBuilder().enableComplexMapKeySerialization().registerTypeAdapter(URI.class, new JavaUriAdapter()).registerTypeAdapter(Uri.class, new UriAdapter()).registerTypeAdapter(BRIGHTCOVE_CAPTION_MAP_TYPE, new CaptionSourcesAdapter()).registerTypeAdapter(BrightcoveCaptionFormat.class, new BrightcoveCaptionFormatAdapter()).registerTypeAdapter(STRING_OBJECT_MAP_TYPE, new PropertiesMapAdapter()).create();
        static Type SOURCES_HASH_TYPE = new TypeToken<HashSet<URI>>() {
        }.getType();
        static Type SOURCE_COLLECTION_MAP_TYPE = new TypeToken<LinkedHashMap<DeliveryType, SourceCollection>>() {
        }.getType();
        static Type STRING_OBJECT_MAP_TYPE = new TypeToken<Map<String, Object>>() {
        }.getType();
        public static final Gson UTC_GSON = new GsonBuilder().enableComplexMapKeySerialization().registerTypeHierarchyAdapter(Uri.class, new UriAdapter()).registerTypeAdapter(Video.class, new VideoAdapter()).registerTypeAdapter(Date.class, new UtcDateAdapter()).registerTypeAdapter(Source.class, new SourceAdapter()).registerTypeAdapter(SourceCollection.class, new SourceCollectionAdapter()).create();

        private static class BrightcoveCaptionFormatAdapter implements JsonDeserializer<BrightcoveCaptionFormat> {
            private BrightcoveCaptionFormatAdapter() {
            }

            public BrightcoveCaptionFormat deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                if (!jsonElement.isJsonObject()) {
                    return null;
                }
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                String asString = asJsonObject.get("language").getAsString();
                String asString2 = asJsonObject.get("type").getAsString();
                boolean asBoolean = asJsonObject.get("hasInBandMetadataTrackDispatchType").getAsBoolean();
                return BrightcoveCaptionFormat.builder().language(asString).type(asString2).hasInBandMetadataTrackDispatchType(asBoolean).isDefault(asJsonObject.get("isDefault").getAsBoolean()).build();
            }
        }

        private static class CaptionSourcesAdapter implements JsonDeserializer<Pair<Uri, BrightcoveCaptionFormat>> {
            private CaptionSourcesAdapter() {
            }

            public Pair<Uri, BrightcoveCaptionFormat> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                if (!jsonElement.isJsonObject()) {
                    return null;
                }
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                return new Pair<>((Uri) jsonDeserializationContext.deserialize(asJsonObject.get("first"), Uri.class), (BrightcoveCaptionFormat) jsonDeserializationContext.deserialize(asJsonObject.get("second"), BrightcoveCaptionFormat.class));
            }
        }

        private static class JavaUriAdapter implements JsonDeserializer<URI> {
            private JavaUriAdapter() {
            }

            public URI deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                if (jsonElement.isJsonObject()) {
                    JsonElement jsonElement2 = jsonElement.getAsJsonObject().get("src");
                    if (jsonElement2 != null) {
                        String asString = jsonElement2.getAsString();
                        if (!TextUtils.isEmpty(asString)) {
                            try {
                                return new URI(asString);
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                return null;
            }
        }

        private static class PropertiesMapAdapter implements JsonDeserializer<Map<String, Object>>, JsonSerializer<Map<String, Object>> {
            private PropertiesMapAdapter() {
            }

            public Map<String, Object> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                ProjectionFormat projectionFormat;
                if (jsonElement.isJsonObject()) {
                    JsonObject asJsonObject = jsonElement.getAsJsonObject();
                    if (asJsonObject.has(Fields.CAPTION_SOURCES) || asJsonObject.has("projectionFormat")) {
                        HashMap hashMap = new HashMap();
                        for (Entry entry : asJsonObject.entrySet()) {
                            String str = (String) entry.getKey();
                            char c = 65535;
                            switch (str.hashCode()) {
                                case -1992146644:
                                    if (str.equals(Fields.THUMBNAIL_SOURCES)) {
                                        c = 6;
                                        break;
                                    }
                                    break;
                                case -1992012396:
                                    if (str.equals("duration")) {
                                        c = 21;
                                        break;
                                    }
                                    break;
                                case -1858576348:
                                    if (str.equals("published_at")) {
                                        c = 3;
                                        break;
                                    }
                                    break;
                                case -1761228046:
                                    if (str.equals(Fields.CAPTION_SOURCES)) {
                                        c = 0;
                                        break;
                                    }
                                    break;
                                case -1747792199:
                                    if (str.equals(Fields.LONG_DESCRIPTION)) {
                                        c = 18;
                                        break;
                                    }
                                    break;
                                case -1724546052:
                                    if (str.equals("description")) {
                                        c = 17;
                                        break;
                                    }
                                    break;
                                case -1547328826:
                                    if (str.equals("poster_sources")) {
                                        c = 9;
                                        break;
                                    }
                                    break;
                                case -953780442:
                                    if (str.equals("projectionFormat")) {
                                        c = 1;
                                        break;
                                    }
                                    break;
                                case -885554659:
                                    if (str.equals(Fields.ODRM_PLAYBACK_ALLOWED)) {
                                        c = 19;
                                        break;
                                    }
                                    break;
                                case -502535537:
                                    if (str.equals("reference_id")) {
                                        c = 16;
                                        break;
                                    }
                                    break;
                                case -295464393:
                                    if (str.equals("updated_at")) {
                                        c = 2;
                                        break;
                                    }
                                    break;
                                case -279439957:
                                    if (str.equals(Fields.POSTER_SOURCES)) {
                                        c = 8;
                                        break;
                                    }
                                    break;
                                case -257774363:
                                    if (str.equals("offline_enabled")) {
                                        c = 20;
                                        break;
                                    }
                                    break;
                                case 3355:
                                    if (str.equals("id")) {
                                        c = 13;
                                        break;
                                    }
                                    break;
                                case 3373707:
                                    if (str.equals("name")) {
                                        c = 12;
                                        break;
                                    }
                                    break;
                                case 3552281:
                                    if (str.equals("tags")) {
                                        c = 10;
                                        break;
                                    }
                                    break;
                                case 107016440:
                                    if (str.equals(Fields.PUBLISHER_ID)) {
                                        c = 14;
                                        break;
                                    }
                                    break;
                                case 530612185:
                                    if (str.equals(Fields.STILL_IMAGE_URI)) {
                                        c = 5;
                                        break;
                                    }
                                    break;
                                case 1193338725:
                                    if (str.equals("thumbnail_sources")) {
                                        c = 7;
                                        break;
                                    }
                                    break;
                                case 1330532588:
                                    if (str.equals(Fields.THUMBNAIL)) {
                                        c = 15;
                                        break;
                                    }
                                    break;
                                case 1369680106:
                                    if (str.equals("created_at")) {
                                        c = 4;
                                        break;
                                    }
                                    break;
                                case 1558986526:
                                    if (str.equals(EdgeTask.ECONOMICS)) {
                                        c = 11;
                                        break;
                                    }
                                    break;
                            }
                            switch (c) {
                                case 0:
                                    JsonArray asJsonArray = ((JsonElement) entry.getValue()).getAsJsonArray();
                                    ArrayList arrayList = new ArrayList();
                                    for (int i = 0; i < asJsonArray.size(); i++) {
                                        Pair pair = (Pair) jsonDeserializationContext.deserialize(asJsonArray.get(i), Lazy.BRIGHTCOVE_CAPTION_MAP_TYPE);
                                        if (pair != null) {
                                            arrayList.add(pair);
                                        }
                                    }
                                    hashMap.put(Fields.CAPTION_SOURCES, arrayList);
                                    break;
                                case 1:
                                    if (((JsonElement) entry.getValue()).getAsString().equals(ProjectionFormat.EQUIRECTANGULAR.name)) {
                                        projectionFormat = ProjectionFormat.EQUIRECTANGULAR;
                                    } else {
                                        projectionFormat = ProjectionFormat.NORMAL;
                                    }
                                    hashMap.put(entry.getKey(), projectionFormat);
                                    break;
                                case 2:
                                case 3:
                                case 4:
                                    try {
                                        hashMap.put(str, UtcDateAdapter.newFormatter().parse(((JsonElement) entry.getValue()).getAsString()));
                                        break;
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                        break;
                                    }
                                case 5:
                                    try {
                                        hashMap.put(str, new URI(((JsonElement) entry.getValue()).getAsString()));
                                        break;
                                    } catch (URISyntaxException e2) {
                                        e2.printStackTrace();
                                        break;
                                    }
                                case 6:
                                    hashMap.put(Fields.THUMBNAIL_SOURCES, new HashSet(convertToUris((String[]) jsonDeserializationContext.deserialize((JsonElement) entry.getValue(), String[].class))));
                                    break;
                                case 7:
                                    hashMap.put(Fields.THUMBNAIL_SOURCES, (Set) jsonDeserializationContext.deserialize((JsonElement) entry.getValue(), Lazy.SOURCES_HASH_TYPE));
                                    break;
                                case 8:
                                    hashMap.put(Fields.POSTER_SOURCES, new HashSet(convertToUris((String[]) jsonDeserializationContext.deserialize((JsonElement) entry.getValue(), String[].class))));
                                    break;
                                case 9:
                                    hashMap.put(Fields.POSTER_SOURCES, (Set) jsonDeserializationContext.deserialize((JsonElement) entry.getValue(), Lazy.SOURCES_HASH_TYPE));
                                    break;
                                case 10:
                                    hashMap.put(str, (List) jsonDeserializationContext.deserialize((JsonElement) entry.getValue(), ArrayList.class));
                                    break;
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                case 18:
                                    hashMap.put(str, ((JsonElement) entry.getValue()).getAsString());
                                    break;
                                case 19:
                                case 20:
                                    hashMap.put(str, Boolean.valueOf(((JsonElement) entry.getValue()).getAsBoolean()));
                                    break;
                                case 21:
                                    hashMap.put(str, Integer.valueOf(((JsonElement) entry.getValue()).getAsInt()));
                                    break;
                            }
                        }
                        return hashMap;
                    }
                }
                return (Map) jsonDeserializationContext.deserialize(jsonElement, LinkedTreeMap.class);
            }

            private List<URI> convertToUris(String[] strArr) {
                ArrayList arrayList = new ArrayList();
                if (strArr != null) {
                    for (String str : strArr) {
                        if (str != null) {
                            try {
                                arrayList.add(new URI(str));
                            } catch (URISyntaxException e) {
                                String access$1000 = Convert.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("Error parsing URI: ");
                                sb.append(e);
                                Log.e(access$1000, sb.toString());
                            }
                        }
                    }
                }
                return arrayList;
            }

            public JsonElement serialize(Map<String, Object> map, Type type, JsonSerializationContext jsonSerializationContext) {
                return jsonSerializationContext.serialize(map, LinkedTreeMap.class);
            }
        }

        private static class SourceAdapter implements JsonDeserializer<Source> {
            private SourceAdapter() {
            }

            public Source deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject asJsonObject = jsonElement.getAsJsonObject().get("properties").getAsJsonObject();
                HashMap hashMap = new HashMap();
                for (Entry entry : asJsonObject.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str.equals(SourceAwareMetadataObject.Fields.DELIVERY_TYPE)) {
                        hashMap.put(entry.getKey(), DeliveryType.getDeliveryTypeByName(((JsonElement) entry.getValue()).getAsString()));
                    } else if (str.equals(Source.Fields.KEY_SYSTEMS)) {
                        hashMap.put(str, (Map) jsonDeserializationContext.deserialize((JsonElement) entry.getValue(), HashMap.class));
                    } else {
                        try {
                            JsonElement jsonElement2 = (JsonElement) entry.getValue();
                            if (!jsonElement2.isJsonObject() && !jsonElement2.isJsonArray()) {
                                hashMap.put(entry.getKey(), ((JsonElement) entry.getValue()).getAsString());
                            }
                        } catch (ClassCastException e) {
                            Log.w(Convert.TAG, "Deserialized value is not a String.", e);
                        } catch (IllegalStateException e2) {
                            Log.w(Convert.TAG, "Deserialized value is an Array instead of a String.", e2);
                        }
                    }
                }
                return new Source((Map<String, Object>) hashMap);
            }
        }

        private static class SourceCollectionAdapter implements JsonDeserializer<SourceCollection> {
            private SourceCollectionAdapter() {
            }

            public SourceCollection deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                HashSet hashSet;
                HashMap hashMap = new HashMap();
                if (jsonElement.isJsonObject()) {
                    hashSet = new HashSet(Arrays.asList((Source[]) jsonDeserializationContext.deserialize(jsonElement.getAsJsonObject().get("sources"), Source[].class)));
                    for (Entry entry : jsonElement.getAsJsonObject().get("properties").getAsJsonObject().entrySet()) {
                        if (((String) entry.getKey()).equals(SourceAwareMetadataObject.Fields.DELIVERY_TYPE)) {
                            hashMap.put(entry.getKey(), DeliveryType.getDeliveryTypeByName(((JsonElement) entry.getValue()).getAsString()));
                        } else {
                            try {
                                hashMap.put(entry.getKey(), ((JsonElement) entry.getValue()).getAsString());
                            } catch (ClassCastException e) {
                                Log.w(Convert.TAG, "Deserialized value is not a String.", e);
                            } catch (IllegalStateException e2) {
                                Log.w(Convert.TAG, "Deserialized value is an Array instead of a String.", e2);
                            }
                        }
                    }
                } else {
                    hashSet = null;
                }
                if (hashSet != null) {
                    return new SourceCollection((Map<String, Object>) hashMap, (Set<Source>) hashSet);
                }
                return new SourceCollection(hashMap);
            }
        }

        private static class UriAdapter implements JsonDeserializer<Uri>, JsonSerializer<Uri> {
            private UriAdapter() {
            }

            public Uri deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                if (jsonElement.isJsonObject()) {
                    JsonElement jsonElement2 = jsonElement.getAsJsonObject().get("uriString");
                    if (jsonElement2 != null) {
                        String asString = jsonElement2.getAsString();
                        if (!TextUtils.isEmpty(asString)) {
                            return Uri.parse(asString);
                        }
                    }
                }
                return null;
            }

            public JsonElement serialize(Uri uri, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(uri.toString());
            }
        }

        private static class UtcDateAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {
            private UtcDateAdapter() {
            }

            public static SimpleDateFormat newFormatter() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return simpleDateFormat;
            }

            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                try {
                    return newFormatter().parse(jsonElement.getAsString());
                } catch (ParseException e) {
                    throw new JsonParseException((Throwable) e);
                }
            }

            public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(newFormatter().format(date));
            }
        }

        private static class VideoAdapter implements JsonDeserializer<Video> {
            private VideoAdapter() {
            }

            @CanSetDownloadIdentifier
            @CanSetLicenseExpiryDate
            @CanSetLicenseKeySetId
            public Video deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                List list = (List) jsonDeserializationContext.deserialize(asJsonObject.get(VideoFields.CUE_POINTS), ArrayList.class);
                Video video = new Video((Map) Lazy.PROPERTIES_GSON.fromJson(asJsonObject.get("properties"), Lazy.STRING_OBJECT_MAP_TYPE), new HashSet(((Map) jsonDeserializationContext.deserialize(asJsonObject.get("sourceCollectionMap"), Lazy.SOURCE_COLLECTION_MAP_TYPE)).values()), list);
                Date date = (Date) jsonDeserializationContext.deserialize(asJsonObject.get("licenseExpiryDate"), Date.class);
                byte[] bArr = (byte[]) jsonDeserializationContext.deserialize(asJsonObject.get("licenseKeySetId"), byte[].class);
                UUID uuid = (UUID) jsonDeserializationContext.deserialize(asJsonObject.get("downloadId"), UUID.class);
                video.setLicenseExpiryDate(date);
                video.setOfflinePlaybackLicenseKey(bArr);
                video.setDownloadId(uuid);
                return video;
            }
        }
    }

    private Convert() {
    }

    @Nullable
    public static String toString(@Nullable Object obj, @Nullable String str) {
        if (obj == null) {
            return str;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return obj.toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    @NonNull
    public static String toString(@Nullable Object obj) {
        return toString(obj, "");
    }

    public static boolean toBoolean(@Nullable Object obj) {
        boolean z = obj != null;
        if (!z) {
            return z;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue() != 0.0d;
        }
        return Boolean.parseBoolean(toString(obj, "false"));
    }

    public static long toLong(@Nullable Object obj, long j) {
        if (obj == null) {
            return j;
        }
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        try {
            return Long.parseLong(toString(obj, String.valueOf(j)));
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static long toLong(@Nullable Object obj) {
        return toLong(obj, 0);
    }

    public static int toInt(@Nullable Object obj, int i) {
        if (obj == null) {
            return i;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        try {
            return Integer.parseInt(toString(obj, String.valueOf(i)));
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static int toInt(@Nullable Object obj) {
        return toInt(obj, 0);
    }

    @NonNull
    public static <T> Set<T> toSet(@Nullable T[] tArr) {
        HashSet hashSet = new HashSet();
        if (tArr != null) {
            hashSet.addAll(Arrays.asList(tArr));
        }
        return hashSet;
    }

    @NonNull
    public static <T> List<T> toList(@Nullable Set<T> set) {
        ArrayList arrayList = new ArrayList();
        if (set != null) {
            arrayList.addAll(set);
        }
        return arrayList;
    }

    @NonNull
    public static <T> Set<T> toSet(@Nullable Collection<T> collection) {
        HashSet hashSet = new HashSet();
        if (collection != null) {
            hashSet.addAll(collection);
        }
        return hashSet;
    }

    @NonNull
    public static long[] toPrimitiveLongArray(@Nullable Collection<? extends Number> collection) {
        int i = 0;
        if (collection == null) {
            return new long[0];
        }
        long[] jArr = new long[collection.size()];
        for (Number number : collection) {
            if (number != null) {
                int i2 = i + 1;
                jArr[i] = number.longValue();
                i = i2;
            }
        }
        return jArr;
    }

    @NonNull
    public static Long[] toLongArray(@Nullable Collection<? extends Number> collection) {
        int i = 0;
        if (collection == null) {
            return new Long[0];
        }
        Long[] lArr = new Long[collection.size()];
        for (Number longValue : collection) {
            int i2 = i + 1;
            lArr[i] = Long.valueOf(longValue.longValue());
            i = i2;
        }
        return lArr;
    }

    @NonNull
    public static Long[] toLongArray(@Nullable long[] jArr) {
        int i = 0;
        if (jArr == null) {
            return new Long[0];
        }
        Long[] lArr = new Long[jArr.length];
        int length = jArr.length;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            lArr[i2] = Long.valueOf(jArr[i]);
            i++;
            i2 = i3;
        }
        return lArr;
    }

    @Nullable
    public static URI toURI(@Nullable Object obj) {
        if (obj instanceof URI) {
            return (URI) obj;
        }
        if (obj == null) {
            return null;
        }
        return URI.create(toString(obj));
    }

    @NonNull
    public static String toHexString(@Nullable byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        char[] cArr = new char[(bArr.length * 2)];
        int i = 0;
        for (byte b : bArr) {
            byte b2 = b & 255;
            int i2 = i + 1;
            char[] cArr2 = HEX_LOOKUP;
            cArr[i] = cArr2[b2 >>> 4];
            i = i2 + 1;
            cArr[i2] = cArr2[b2 & Ascii.SI];
        }
        return new String(cArr);
    }

    @NonNull
    public static String toJsonString(@Nullable Object obj) {
        return Lazy.UTC_GSON.toJson(obj);
    }
}

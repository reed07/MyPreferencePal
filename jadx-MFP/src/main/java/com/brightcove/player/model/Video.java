package com.brightcove.player.model;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.brightcove.player.util.Convert;
import com.brightcove.player.util.ErrorUtil;
import com.brightcove.player.util.ReflectionUtil;
import com.google.gson.annotations.Expose;
import com.mopub.common.Constants;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class Video extends MetadataObject implements Parcelable {
    public static Creator<Video> CREATOR = new Creator<Video>() {
        public Video createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            if (readInt == 1) {
                return (Video) parcel.readSerializable();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown parcel type: ");
            sb.append(readInt);
            throw new IllegalArgumentException(sb.toString());
        }

        public Video[] newArray(int i) {
            return new Video[i];
        }
    };
    private static final Date OWNED_DATE = new Date(100000000000000L);
    public static final int PARCEL_OBJECT_TYPE = 1;
    private List<CuePoint> cuePoints;
    private UUID downloadId;
    private Date licenseExpiryDate;
    private byte[] licenseKeySetId;
    @Expose
    private Map<DeliveryType, SourceCollection> sourceCollectionMap;

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CanSetDownloadIdentifier {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CanSetLicenseExpiryDate {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CanSetLicenseKeySetId {
    }

    public static class Fields {
        public static final String CAPTION_SOURCES = "captionSources";
        public static final String CONTENT_ID = "contentId";
        public static final String CUSTOM_FIELDS = "customFields";
        public static final String DESCRIPTION = "description";
        public static final String DURATION = "duration";
        public static final String HEADERS = "headers";
        public static final String ID = "id";
        public static final String LONG_DESCRIPTION = "long_description";
        public static final String NAME = "name";
        public static final String ODRM_PLAYBACK_ALLOWED = "odrmPlaybackAllowed";
        public static final String POSTER_SOURCES = "posterSources";
        public static final String PROJECTION_FORMAT = "projectionFormat";
        public static final String PUBLISHER_ID = "pubId";
        public static final String REFERENCE_ID = "reference_id";
        public static final String SHORT_DESCRIPTION = "shortDescription";
        public static final String STILL_IMAGE_URI = "stillImageUri";
        public static final String THUMBNAIL = "thumbnail";
        public static final String THUMBNAIL_SOURCES = "thumbnailSources";
        public static final String WIDEVINE_HEADERS = "widevineHeaders";
    }

    public enum ProjectionFormat {
        NORMAL("normal"),
        EQUIRECTANGULAR("equirectangular");
        
        public final String name;

        private ProjectionFormat(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }

        @Nullable
        public static ProjectionFormat parse(@Nullable Object obj, @Nullable ProjectionFormat projectionFormat) {
            ProjectionFormat[] values;
            if (obj instanceof ProjectionFormat) {
                return (ProjectionFormat) obj;
            }
            if (obj != null) {
                String obj2 = obj.toString();
                for (ProjectionFormat projectionFormat2 : values()) {
                    if (obj2.equalsIgnoreCase(projectionFormat2.name)) {
                        return projectionFormat2;
                    }
                }
            }
            return projectionFormat;
        }

        @Nullable
        public static ProjectionFormat parse(@Nullable Object obj) {
            return parse(obj, null);
        }
    }

    @SuppressLint({"WrongConstant"})
    public int describeContents() {
        return 1;
    }

    public Video(Map<String, Object> map) {
        super(map);
    }

    public Video(Map<String, Object> map, Set<SourceCollection> set) {
        this(map);
        if (set != null) {
            HashMap hashMap = new HashMap();
            for (SourceCollection sourceCollection : set) {
                if (!hashMap.containsKey(sourceCollection.getDeliveryType())) {
                    hashMap.put(sourceCollection.getDeliveryType(), sourceCollection);
                } else {
                    throw new IllegalStateException(ErrorUtil.getMessage(ErrorUtil.DUPLICATE_DELIVERY_TYPES));
                }
            }
            this.sourceCollectionMap = hashMap;
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.SOURCE_COLLECTIONS_REQUIRED));
    }

    public Video(Map<String, Object> map, Set<SourceCollection> set, List<CuePoint> list) {
        this(map, set);
        if (list != null) {
            this.cuePoints = list;
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.CUE_POINTS_REQUIRED));
    }

    public Video fixProperties() {
        this.properties.put(Fields.THUMBNAIL, Convert.toURI(this.properties.get(Fields.THUMBNAIL)));
        this.properties.put(Fields.STILL_IMAGE_URI, getStillImageUri());
        if (this.properties.containsKey("duration")) {
            this.properties.put("duration", Integer.valueOf(Convert.toInt(this.properties.get("duration"))));
        }
        return this;
    }

    @NonNull
    private static DeliveryType detectDeliveryType(@NonNull String str) {
        DeliveryType deliveryType = DeliveryType.UNKNOWN;
        String lowerCase = str.split("[?]")[0].toLowerCase(Locale.getDefault());
        if (lowerCase.endsWith(".mp4")) {
            return DeliveryType.MP4;
        }
        if (lowerCase.endsWith(".m3u") || lowerCase.endsWith(".m3u8")) {
            return DeliveryType.HLS;
        }
        if (lowerCase.endsWith(".mpd")) {
            return DeliveryType.DASH;
        }
        return deliveryType;
    }

    public static Video createVideo(@NonNull String str) {
        return createVideo(str, null, null);
    }

    public static Video createVideo(@NonNull String str, ProjectionFormat projectionFormat) {
        return createVideo(str, null, projectionFormat);
    }

    public static Video createVideo(@NonNull String str, DeliveryType deliveryType) {
        return createVideo(str, deliveryType, null);
    }

    public static Video createVideo(@NonNull String str, @Nullable DeliveryType deliveryType, @Nullable ProjectionFormat projectionFormat) {
        if (str != null) {
            if (deliveryType == null) {
                deliveryType = detectDeliveryType(str);
            }
            if (projectionFormat == null) {
                projectionFormat = ProjectionFormat.NORMAL;
            }
            HashSet hashSet = new HashSet();
            hashSet.add(new SourceCollection(new Source(str, deliveryType), deliveryType));
            HashMap hashMap = new HashMap();
            hashMap.put("projectionFormat", projectionFormat);
            return new Video(hashMap, hashSet, new ArrayList());
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.URI_REQUIRED));
    }

    public List<CuePoint> getCuePoints() {
        return this.cuePoints;
    }

    public Map<DeliveryType, SourceCollection> getSourceCollections() {
        return this.sourceCollectionMap;
    }

    @Nullable
    public SourceCollection getSourceCollectionByDeliveryType(DeliveryType deliveryType) {
        return (SourceCollection) this.sourceCollectionMap.get(deliveryType);
    }

    @Nullable
    public Source findHighQualitySource(@NonNull DeliveryType deliveryType) {
        Source source = null;
        if (this.sourceCollectionMap.containsKey(deliveryType)) {
            for (Source source2 : ((SourceCollection) this.sourceCollectionMap.get(deliveryType)).getSources()) {
                if (source == null || source2.getBitRate().intValue() > source.getBitRate().intValue()) {
                    source = source2;
                }
            }
        }
        return source;
    }

    @Nullable
    public Source findLowQualitySource(@NonNull DeliveryType deliveryType) {
        Source source = null;
        if (this.sourceCollectionMap.containsKey(deliveryType)) {
            for (Source source2 : ((SourceCollection) this.sourceCollectionMap.get(deliveryType)).getSources()) {
                if (source == null || source2.getBitRate().intValue() < source.getBitRate().intValue()) {
                    source = source2;
                }
            }
        }
        return source;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Video{");
        if (this.properties.get("name") != null) {
            sb.append("name: \"");
            sb.append(this.properties.get("name"));
            sb.append("\"");
        }
        if (this.properties.get("shortDescription") != null) {
            sb.append(", shortDescription: \"");
            sb.append(this.properties.get("shortDescription"));
            sb.append("\"");
        }
        sb.append(", sourceCollections: ");
        Map<DeliveryType, SourceCollection> map = this.sourceCollectionMap;
        int i = 0;
        sb.append(map != null ? map.size() : 0);
        sb.append(", cuePoints: ");
        List<CuePoint> list = this.cuePoints;
        if (list != null) {
            i = list.size();
        }
        sb.append(i);
        if (this.properties.get("customFields") != null) {
            sb.append(", custom fields: \"");
            sb.append(this.properties.get("customFields"));
            sb.append("\"");
        }
        sb.append("}");
        return sb.toString();
    }

    @NonNull
    public String getId() {
        return this.properties.containsKey("id") ? this.properties.get("id").toString() : "";
    }

    @NonNull
    public String getReferenceId() {
        return this.properties.containsKey("reference_id") ? this.properties.get("reference_id").toString() : "";
    }

    public int getDuration() {
        if (this.properties.containsKey("duration")) {
            try {
                return Integer.parseInt(this.properties.get("duration").toString());
            } catch (NumberFormatException unused) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid number value seen for duration in Video: ");
                sb.append(this.properties.get("duration"));
                Log.e(str, sb.toString());
            }
        }
        return 0;
    }

    @NonNull
    public ProjectionFormat getProjectionFormat() {
        return ProjectionFormat.parse(this.properties.get("projectionFormat"), ProjectionFormat.NORMAL);
    }

    public boolean isVideo360() {
        return getProjectionFormat() != ProjectionFormat.NORMAL;
    }

    public boolean isClearContent() {
        Map<DeliveryType, SourceCollection> map = this.sourceCollectionMap;
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                if (DeliveryType.DASH.equals(entry.getKey())) {
                    for (Source hasKeySystem : ((SourceCollection) entry.getValue()).getSources()) {
                        if (hasKeySystem.hasKeySystem(com.brightcove.player.model.Source.Fields.WIDEVINE_KEY_SYSTEM)) {
                            return false;
                        }
                    }
                    continue;
                }
            }
        }
        return true;
    }

    public final boolean isOfflinePlaybackAllowed() {
        boolean z = Convert.toBoolean(this.properties.get(Fields.ODRM_PLAYBACK_ALLOWED));
        if (!z) {
            return z;
        }
        Source findHighQualitySource = findHighQualitySource(DeliveryType.DASH);
        if (findHighQualitySource == null) {
            return false;
        }
        return !findHighQualitySource.hasKeySystem(com.brightcove.player.model.Source.Fields.WIDEVINE_KEY_SYSTEM) || VERSION.SDK_INT >= 21;
    }

    @NonNull
    public String getName() {
        String stringProperty = getStringProperty(this.properties, "name");
        return stringProperty == null ? "" : stringProperty;
    }

    @NonNull
    public String getDescription() {
        String stringProperty = getStringProperty(this.properties, "description");
        if (stringProperty == null) {
            stringProperty = getStringProperty(this.properties, "shortDescription");
        }
        return stringProperty == null ? "" : stringProperty;
    }

    @Nullable
    public String getLongDescription() {
        String stringProperty = getStringProperty(this.properties, Fields.LONG_DESCRIPTION);
        return stringProperty == null ? "" : stringProperty;
    }

    @Nullable
    public URI getStillImageUri() {
        return Convert.toURI(this.properties.get(Fields.STILL_IMAGE_URI));
    }

    @Nullable
    public URI getPosterImage() {
        URI findPreferredURI = findPreferredURI(getPosterSources());
        return findPreferredURI == null ? getStillImageUri() : findPreferredURI;
    }

    @Nullable
    public URI getThumbnail() {
        URI findPreferredURI = findPreferredURI(getThumbnailSources());
        return findPreferredURI == null ? Convert.toURI(this.properties.get(Fields.THUMBNAIL)) : findPreferredURI;
    }

    @NonNull
    public Set<URI> getPosterSources() {
        Set<URI> set = (Set) this.properties.get(Fields.POSTER_SOURCES);
        return set == null ? new HashSet() : set;
    }

    @NonNull
    public Set<URI> getThumbnailSources() {
        Set<URI> set = (Set) this.properties.get(Fields.THUMBNAIL_SOURCES);
        return set == null ? new HashSet() : set;
    }

    @Nullable
    private URI findPreferredURI(@NonNull Collection<URI> collection) {
        URI uri = null;
        if (!collection.isEmpty()) {
            for (URI uri2 : collection) {
                if (uri == null || uri2.toString().startsWith(Constants.HTTPS)) {
                    uri = uri2;
                }
            }
        }
        return uri;
    }

    public boolean isOwned() {
        Date licenseExpiryDate2 = getLicenseExpiryDate();
        return licenseExpiryDate2 != null && licenseExpiryDate2.after(OWNED_DATE);
    }

    public boolean isRented() {
        Date licenseExpiryDate2 = getLicenseExpiryDate();
        return licenseExpiryDate2 != null && licenseExpiryDate2.before(OWNED_DATE);
    }

    @Nullable
    public UUID getDownloadId() {
        return this.downloadId;
    }

    public void setDownloadId(@Nullable UUID uuid) {
        ReflectionUtil.assertCallerAnnotation(CanSetDownloadIdentifier.class, "Not permitted to set download identifier");
        this.downloadId = uuid;
    }

    @Nullable
    public byte[] getOfflinePlaybackLicenseKey() {
        return this.licenseKeySetId;
    }

    public void setOfflinePlaybackLicenseKey(@Nullable byte[] bArr) {
        ReflectionUtil.assertCallerAnnotation(CanSetLicenseKeySetId.class, "Not permitted to set offline playback license key set identifier");
        this.licenseKeySetId = bArr;
    }

    @Nullable
    public Date getLicenseExpiryDate() {
        return this.licenseExpiryDate;
    }

    public void setLicenseExpiryDate(@Nullable Date date) {
        ReflectionUtil.assertCallerAnnotation(CanSetLicenseExpiryDate.class, "Not permitted to set license expiry date");
        this.licenseExpiryDate = date;
    }

    public boolean isOfflineCopy() {
        Map<DeliveryType, SourceCollection> map = this.sourceCollectionMap;
        if (map != null) {
            for (SourceCollection sources : map.values()) {
                Iterator it = sources.getSources().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!((Source) it.next()).isLocal()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(describeContents());
        parcel.writeSerializable(this);
    }
}

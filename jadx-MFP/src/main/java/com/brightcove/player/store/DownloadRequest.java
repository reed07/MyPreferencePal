package com.brightcove.player.store;

import android.net.Uri;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import io.requery.CascadeAction;
import io.requery.Persistable;
import io.requery.ReferentialAction;
import io.requery.meta.Attribute;
import io.requery.meta.AttributeBuilder;
import io.requery.meta.Cardinality;
import io.requery.meta.QueryAttribute;
import io.requery.meta.QueryExpression;
import io.requery.meta.Type;
import io.requery.meta.TypeBuilder;
import io.requery.proxy.BooleanProperty;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.IntProperty;
import io.requery.proxy.LongProperty;
import io.requery.proxy.PreInsertListener;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.util.Map;

public class DownloadRequest extends AbstractDownloadRequest implements Persistable {
    public static final Type<DownloadRequest> $TYPE = new TypeBuilder(DownloadRequest.class, "DownloadRequest").setBaseType(AbstractDownloadRequest.class).setCacheable(true).setImmutable(false).setReadOnly(false).setStateless(false).setView(false).setFactory(new Supplier<DownloadRequest>() {
        public DownloadRequest get() {
            return new DownloadRequest();
        }
    }).setProxyProvider(new Function<DownloadRequest, EntityProxy<DownloadRequest>>() {
        public EntityProxy<DownloadRequest> apply(DownloadRequest downloadRequest) {
            return downloadRequest.$proxy;
        }
    }).addAttribute(ALLOWED_OVER_MOBILE).addAttribute(ALLOWED_OVER_METERED).addAttribute(REQUEST_SET).addAttribute(ALLOWED_OVER_WIFI).addAttribute(REASON_CODE).addAttribute(VISIBLE_IN_DOWNLOADS_UI).addAttribute(STATUS_CODE).addAttribute(ALLOWED_OVER_BLUETOOTH).addAttribute(UPDATE_TIME).addAttribute(LOCAL_URI).addAttribute(MIME_TYPE).addAttribute(DOWNLOAD_ID).addAttribute(ESTIMATED_SIZE).addAttribute(HEADERS).addAttribute(NOTIFICATION_VISIBILITY).addAttribute(DESCRIPTION).addAttribute(TITLE).addAttribute(ALLOW_SCANNING_BY_MEDIA_SCANNER).addAttribute(ACTUAL_SIZE).addAttribute(CREATE_TIME).addAttribute(REMOTE_URI).addAttribute(ALLOWED_OVER_ROAMING).addAttribute(KEY).addAttribute(BYTES_DOWNLOADED).addExpression(REQUEST_SET_ID).build();
    public static final QueryAttribute<DownloadRequest, Long> ACTUAL_SIZE = new AttributeBuilder("actualSize", Long.TYPE).setProperty(new LongProperty<DownloadRequest>() {
        public Long get(DownloadRequest downloadRequest) {
            return Long.valueOf(downloadRequest.actualSize);
        }

        public void set(DownloadRequest downloadRequest, Long l) {
            downloadRequest.actualSize = l.longValue();
        }

        public long getLong(DownloadRequest downloadRequest) {
            return downloadRequest.actualSize;
        }

        public void setLong(DownloadRequest downloadRequest, long j) {
            downloadRequest.actualSize = j;
        }
    }).setPropertyName("actualSize").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$actualSize_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$actualSize_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Boolean> ALLOWED_OVER_BLUETOOTH = new AttributeBuilder("allowedOverBluetooth", Boolean.TYPE).setProperty(new BooleanProperty<DownloadRequest>() {
        public Boolean get(DownloadRequest downloadRequest) {
            return Boolean.valueOf(downloadRequest.allowedOverBluetooth);
        }

        public void set(DownloadRequest downloadRequest, Boolean bool) {
            downloadRequest.allowedOverBluetooth = bool.booleanValue();
        }

        public boolean getBoolean(DownloadRequest downloadRequest) {
            return downloadRequest.allowedOverBluetooth;
        }

        public void setBoolean(DownloadRequest downloadRequest, boolean z) {
            downloadRequest.allowedOverBluetooth = z;
        }
    }).setPropertyName("allowedOverBluetooth").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$allowedOverBluetooth_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$allowedOverBluetooth_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Boolean> ALLOWED_OVER_METERED = new AttributeBuilder("allowedOverMetered", Boolean.TYPE).setProperty(new BooleanProperty<DownloadRequest>() {
        public Boolean get(DownloadRequest downloadRequest) {
            return Boolean.valueOf(downloadRequest.allowedOverMetered);
        }

        public void set(DownloadRequest downloadRequest, Boolean bool) {
            downloadRequest.allowedOverMetered = bool.booleanValue();
        }

        public boolean getBoolean(DownloadRequest downloadRequest) {
            return downloadRequest.allowedOverMetered;
        }

        public void setBoolean(DownloadRequest downloadRequest, boolean z) {
            downloadRequest.allowedOverMetered = z;
        }
    }).setPropertyName("allowedOverMetered").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$allowedOverMetered_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$allowedOverMetered_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Boolean> ALLOWED_OVER_MOBILE = new AttributeBuilder("allowedOverMobile", Boolean.TYPE).setProperty(new BooleanProperty<DownloadRequest>() {
        public Boolean get(DownloadRequest downloadRequest) {
            return Boolean.valueOf(downloadRequest.allowedOverMobile);
        }

        public void set(DownloadRequest downloadRequest, Boolean bool) {
            downloadRequest.allowedOverMobile = bool.booleanValue();
        }

        public boolean getBoolean(DownloadRequest downloadRequest) {
            return downloadRequest.allowedOverMobile;
        }

        public void setBoolean(DownloadRequest downloadRequest, boolean z) {
            downloadRequest.allowedOverMobile = z;
        }
    }).setPropertyName("allowedOverMobile").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$allowedOverMobile_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$allowedOverMobile_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Boolean> ALLOWED_OVER_ROAMING = new AttributeBuilder("allowedOverRoaming", Boolean.TYPE).setProperty(new BooleanProperty<DownloadRequest>() {
        public Boolean get(DownloadRequest downloadRequest) {
            return Boolean.valueOf(downloadRequest.allowedOverRoaming);
        }

        public void set(DownloadRequest downloadRequest, Boolean bool) {
            downloadRequest.allowedOverRoaming = bool.booleanValue();
        }

        public boolean getBoolean(DownloadRequest downloadRequest) {
            return downloadRequest.allowedOverRoaming;
        }

        public void setBoolean(DownloadRequest downloadRequest, boolean z) {
            downloadRequest.allowedOverRoaming = z;
        }
    }).setPropertyName("allowedOverRoaming").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$allowedOverRoaming_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$allowedOverRoaming_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Boolean> ALLOWED_OVER_WIFI = new AttributeBuilder("allowedOverWifi", Boolean.TYPE).setProperty(new BooleanProperty<DownloadRequest>() {
        public Boolean get(DownloadRequest downloadRequest) {
            return Boolean.valueOf(downloadRequest.allowedOverWifi);
        }

        public void set(DownloadRequest downloadRequest, Boolean bool) {
            downloadRequest.allowedOverWifi = bool.booleanValue();
        }

        public boolean getBoolean(DownloadRequest downloadRequest) {
            return downloadRequest.allowedOverWifi;
        }

        public void setBoolean(DownloadRequest downloadRequest, boolean z) {
            downloadRequest.allowedOverWifi = z;
        }
    }).setPropertyName("allowedOverWifi").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$allowedOverWifi_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$allowedOverWifi_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Boolean> ALLOW_SCANNING_BY_MEDIA_SCANNER = new AttributeBuilder("allowScanningByMediaScanner", Boolean.TYPE).setProperty(new BooleanProperty<DownloadRequest>() {
        public Boolean get(DownloadRequest downloadRequest) {
            return Boolean.valueOf(downloadRequest.allowScanningByMediaScanner);
        }

        public void set(DownloadRequest downloadRequest, Boolean bool) {
            downloadRequest.allowScanningByMediaScanner = bool.booleanValue();
        }

        public boolean getBoolean(DownloadRequest downloadRequest) {
            return downloadRequest.allowScanningByMediaScanner;
        }

        public void setBoolean(DownloadRequest downloadRequest, boolean z) {
            downloadRequest.allowScanningByMediaScanner = z;
        }
    }).setPropertyName("allowScanningByMediaScanner").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$allowScanningByMediaScanner_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$allowScanningByMediaScanner_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Long> BYTES_DOWNLOADED = new AttributeBuilder("bytesDownloaded", Long.TYPE).setProperty(new LongProperty<DownloadRequest>() {
        public Long get(DownloadRequest downloadRequest) {
            return Long.valueOf(downloadRequest.bytesDownloaded);
        }

        public void set(DownloadRequest downloadRequest, Long l) {
            downloadRequest.bytesDownloaded = l.longValue();
        }

        public long getLong(DownloadRequest downloadRequest) {
            return downloadRequest.bytesDownloaded;
        }

        public void setLong(DownloadRequest downloadRequest, long j) {
            downloadRequest.bytesDownloaded = j;
        }
    }).setPropertyName("bytesDownloaded").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$bytesDownloaded_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$bytesDownloaded_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Long> CREATE_TIME = new AttributeBuilder("createTime", Long.TYPE).setProperty(new LongProperty<DownloadRequest>() {
        public Long get(DownloadRequest downloadRequest) {
            return Long.valueOf(downloadRequest.createTime);
        }

        public void set(DownloadRequest downloadRequest, Long l) {
            downloadRequest.createTime = l.longValue();
        }

        public long getLong(DownloadRequest downloadRequest) {
            return downloadRequest.createTime;
        }

        public void setLong(DownloadRequest downloadRequest, long j) {
            downloadRequest.createTime = j;
        }
    }).setPropertyName("createTime").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$createTime_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$createTime_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, String> DESCRIPTION = new AttributeBuilder("description", String.class).setProperty(new Property<DownloadRequest, String>() {
        public String get(DownloadRequest downloadRequest) {
            return downloadRequest.description;
        }

        public void set(DownloadRequest downloadRequest, String str) {
            downloadRequest.description = str;
        }
    }).setPropertyName("description").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$description_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$description_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Long> DOWNLOAD_ID = new AttributeBuilder("downloadId", Long.class).setProperty(new Property<DownloadRequest, Long>() {
        public Long get(DownloadRequest downloadRequest) {
            return downloadRequest.downloadId;
        }

        public void set(DownloadRequest downloadRequest, Long l) {
            downloadRequest.downloadId = l;
        }
    }).setPropertyName("downloadId").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$downloadId_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$downloadId_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(true).build();
    public static final QueryAttribute<DownloadRequest, Long> ESTIMATED_SIZE = new AttributeBuilder("estimatedSize", Long.TYPE).setProperty(new LongProperty<DownloadRequest>() {
        public Long get(DownloadRequest downloadRequest) {
            return Long.valueOf(downloadRequest.estimatedSize);
        }

        public void set(DownloadRequest downloadRequest, Long l) {
            downloadRequest.estimatedSize = l.longValue();
        }

        public long getLong(DownloadRequest downloadRequest) {
            return downloadRequest.estimatedSize;
        }

        public void setLong(DownloadRequest downloadRequest, long j) {
            downloadRequest.estimatedSize = j;
        }
    }).setPropertyName("estimatedSize").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$estimatedSize_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$estimatedSize_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Map<String, String>> HEADERS = new AttributeBuilder("headers", Map.class).setProperty(new Property<DownloadRequest, Map<String, String>>() {
        public Map<String, String> get(DownloadRequest downloadRequest) {
            return downloadRequest.headers;
        }

        public void set(DownloadRequest downloadRequest, Map<String, String> map) {
            downloadRequest.headers = map;
        }
    }).setPropertyName("headers").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$headers_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$headers_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Long> KEY = new AttributeBuilder(IpcUtil.KEY_CODE, Long.class).setProperty(new Property<DownloadRequest, Long>() {
        public Long get(DownloadRequest downloadRequest) {
            return downloadRequest.key;
        }

        public void set(DownloadRequest downloadRequest, Long l) {
            downloadRequest.key = l;
        }
    }).setPropertyName(IpcUtil.KEY_CODE).setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$key_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$key_state = propertyState;
        }
    }).setKey(true).setGenerated(true).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Uri> LOCAL_URI = new AttributeBuilder("localUri", Uri.class).setProperty(new Property<DownloadRequest, Uri>() {
        public Uri get(DownloadRequest downloadRequest) {
            return downloadRequest.localUri;
        }

        public void set(DownloadRequest downloadRequest, Uri uri) {
            downloadRequest.localUri = uri;
        }
    }).setPropertyName("localUri").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$localUri_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$localUri_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, String> MIME_TYPE = new AttributeBuilder("mimeType", String.class).setProperty(new Property<DownloadRequest, String>() {
        public String get(DownloadRequest downloadRequest) {
            return downloadRequest.mimeType;
        }

        public void set(DownloadRequest downloadRequest, String str) {
            downloadRequest.mimeType = str;
        }
    }).setPropertyName("mimeType").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$mimeType_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$mimeType_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Integer> NOTIFICATION_VISIBILITY = new AttributeBuilder("notificationVisibility", Integer.TYPE).setProperty(new IntProperty<DownloadRequest>() {
        public Integer get(DownloadRequest downloadRequest) {
            return Integer.valueOf(downloadRequest.notificationVisibility);
        }

        public void set(DownloadRequest downloadRequest, Integer num) {
            downloadRequest.notificationVisibility = num.intValue();
        }

        public int getInt(DownloadRequest downloadRequest) {
            return downloadRequest.notificationVisibility;
        }

        public void setInt(DownloadRequest downloadRequest, int i) {
            downloadRequest.notificationVisibility = i;
        }
    }).setPropertyName("notificationVisibility").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$notificationVisibility_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$notificationVisibility_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Integer> REASON_CODE = new AttributeBuilder("reasonCode", Integer.TYPE).setProperty(new IntProperty<DownloadRequest>() {
        public Integer get(DownloadRequest downloadRequest) {
            return Integer.valueOf(downloadRequest.reasonCode);
        }

        public void set(DownloadRequest downloadRequest, Integer num) {
            downloadRequest.reasonCode = num.intValue();
        }

        public int getInt(DownloadRequest downloadRequest) {
            return downloadRequest.reasonCode;
        }

        public void setInt(DownloadRequest downloadRequest, int i) {
            downloadRequest.reasonCode = i;
        }
    }).setPropertyName("reasonCode").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$reasonCode_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$reasonCode_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Uri> REMOTE_URI = new AttributeBuilder("remoteUri", Uri.class).setProperty(new Property<DownloadRequest, Uri>() {
        public Uri get(DownloadRequest downloadRequest) {
            return downloadRequest.remoteUri;
        }

        public void set(DownloadRequest downloadRequest, Uri uri) {
            downloadRequest.remoteUri = uri;
        }
    }).setPropertyName("remoteUri").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$remoteUri_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$remoteUri_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, DownloadRequestSet> REQUEST_SET = new AttributeBuilder("requestSet", DownloadRequestSet.class).setProperty(new Property<DownloadRequest, DownloadRequestSet>() {
        public DownloadRequestSet get(DownloadRequest downloadRequest) {
            return downloadRequest.requestSet;
        }

        public void set(DownloadRequest downloadRequest, DownloadRequestSet downloadRequestSet) {
            downloadRequest.requestSet = downloadRequestSet;
        }
    }).setPropertyName("requestSet").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$requestSet_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$requestSet_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).setForeignKey(true).setReferencedClass(DownloadRequestSet.class).setReferencedAttribute(new Supplier<Attribute>() {
        public Attribute get() {
            return DownloadRequestSet.KEY;
        }
    }).setDeleteAction(ReferentialAction.CASCADE).setUpdateAction(ReferentialAction.CASCADE).setCascadeAction(CascadeAction.SAVE).setCardinality(Cardinality.MANY_TO_ONE).setMappedAttribute(new Supplier<Attribute>() {
        public Attribute get() {
            return DownloadRequestSet.DOWNLOAD_REQUESTS;
        }
    }).build();
    public static final QueryExpression<Long> REQUEST_SET_ID = new AttributeBuilder("requestSet", Long.class).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).setForeignKey(true).setReferencedClass(DownloadRequestSet.class).setReferencedAttribute(new Supplier<Attribute>() {
        public Attribute get() {
            return DownloadRequestSet.KEY;
        }
    }).setDeleteAction(ReferentialAction.CASCADE).setUpdateAction(ReferentialAction.CASCADE).setCascadeAction(CascadeAction.SAVE).setMappedAttribute(new Supplier<Attribute>() {
        public Attribute get() {
            return DownloadRequestSet.DOWNLOAD_REQUESTS;
        }
    }).build();
    public static final QueryAttribute<DownloadRequest, Integer> STATUS_CODE = new AttributeBuilder("statusCode", Integer.TYPE).setProperty(new IntProperty<DownloadRequest>() {
        public Integer get(DownloadRequest downloadRequest) {
            return Integer.valueOf(downloadRequest.statusCode);
        }

        public void set(DownloadRequest downloadRequest, Integer num) {
            downloadRequest.statusCode = num.intValue();
        }

        public int getInt(DownloadRequest downloadRequest) {
            return downloadRequest.statusCode;
        }

        public void setInt(DownloadRequest downloadRequest, int i) {
            downloadRequest.statusCode = i;
        }
    }).setPropertyName("statusCode").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$statusCode_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$statusCode_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, String> TITLE = new AttributeBuilder("title", String.class).setProperty(new Property<DownloadRequest, String>() {
        public String get(DownloadRequest downloadRequest) {
            return downloadRequest.title;
        }

        public void set(DownloadRequest downloadRequest, String str) {
            downloadRequest.title = str;
        }
    }).setPropertyName("title").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$title_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$title_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Long> UPDATE_TIME = new AttributeBuilder("updateTime", Long.TYPE).setProperty(new LongProperty<DownloadRequest>() {
        public Long get(DownloadRequest downloadRequest) {
            return Long.valueOf(downloadRequest.updateTime);
        }

        public void set(DownloadRequest downloadRequest, Long l) {
            downloadRequest.updateTime = l.longValue();
        }

        public long getLong(DownloadRequest downloadRequest) {
            return downloadRequest.updateTime;
        }

        public void setLong(DownloadRequest downloadRequest, long j) {
            downloadRequest.updateTime = j;
        }
    }).setPropertyName("updateTime").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$updateTime_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$updateTime_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequest, Boolean> VISIBLE_IN_DOWNLOADS_UI = new AttributeBuilder("visibleInDownloadsUi", Boolean.TYPE).setProperty(new BooleanProperty<DownloadRequest>() {
        public Boolean get(DownloadRequest downloadRequest) {
            return Boolean.valueOf(downloadRequest.visibleInDownloadsUi);
        }

        public void set(DownloadRequest downloadRequest, Boolean bool) {
            downloadRequest.visibleInDownloadsUi = bool.booleanValue();
        }

        public boolean getBoolean(DownloadRequest downloadRequest) {
            return downloadRequest.visibleInDownloadsUi;
        }

        public void setBoolean(DownloadRequest downloadRequest, boolean z) {
            downloadRequest.visibleInDownloadsUi = z;
        }
    }).setPropertyName("visibleInDownloadsUi").setPropertyState(new Property<DownloadRequest, PropertyState>() {
        public PropertyState get(DownloadRequest downloadRequest) {
            return downloadRequest.$visibleInDownloadsUi_state;
        }

        public void set(DownloadRequest downloadRequest, PropertyState propertyState) {
            downloadRequest.$visibleInDownloadsUi_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    /* access modifiers changed from: private */
    public PropertyState $actualSize_state;
    /* access modifiers changed from: private */
    public PropertyState $allowScanningByMediaScanner_state;
    /* access modifiers changed from: private */
    public PropertyState $allowedOverBluetooth_state;
    /* access modifiers changed from: private */
    public PropertyState $allowedOverMetered_state;
    /* access modifiers changed from: private */
    public PropertyState $allowedOverMobile_state;
    /* access modifiers changed from: private */
    public PropertyState $allowedOverRoaming_state;
    /* access modifiers changed from: private */
    public PropertyState $allowedOverWifi_state;
    /* access modifiers changed from: private */
    public PropertyState $bytesDownloaded_state;
    /* access modifiers changed from: private */
    public PropertyState $createTime_state;
    /* access modifiers changed from: private */
    public PropertyState $description_state;
    /* access modifiers changed from: private */
    public PropertyState $downloadId_state;
    /* access modifiers changed from: private */
    public PropertyState $estimatedSize_state;
    /* access modifiers changed from: private */
    public PropertyState $headers_state;
    /* access modifiers changed from: private */
    public PropertyState $key_state;
    /* access modifiers changed from: private */
    public PropertyState $localUri_state;
    /* access modifiers changed from: private */
    public PropertyState $mimeType_state;
    /* access modifiers changed from: private */
    public PropertyState $notificationVisibility_state;
    /* access modifiers changed from: private */
    public final transient EntityProxy<DownloadRequest> $proxy = new EntityProxy<>(this, $TYPE);
    /* access modifiers changed from: private */
    public PropertyState $reasonCode_state;
    /* access modifiers changed from: private */
    public PropertyState $remoteUri_state;
    /* access modifiers changed from: private */
    public PropertyState $requestSet_state;
    /* access modifiers changed from: private */
    public PropertyState $statusCode_state;
    /* access modifiers changed from: private */
    public PropertyState $title_state;
    /* access modifiers changed from: private */
    public PropertyState $updateTime_state;
    /* access modifiers changed from: private */
    public PropertyState $visibleInDownloadsUi_state;

    public DownloadRequest() {
        this.$proxy.modifyListeners().addPreInsertListener(new PreInsertListener<DownloadRequest>() {
            public void preInsert(DownloadRequest downloadRequest) {
                DownloadRequest.this.onBeforeInsert();
            }
        });
    }

    public Long getKey() {
        return (Long) this.$proxy.get(KEY);
    }

    public DownloadRequestSet getRequestSet() {
        return (DownloadRequestSet) this.$proxy.get(REQUEST_SET);
    }

    public void setRequestSet(DownloadRequestSet downloadRequestSet) {
        this.$proxy.set(REQUEST_SET, downloadRequestSet);
    }

    public Long getDownloadId() {
        return (Long) this.$proxy.get(DOWNLOAD_ID);
    }

    public void setDownloadId(Long l) {
        this.$proxy.set(DOWNLOAD_ID, l);
    }

    public Uri getLocalUri() {
        return (Uri) this.$proxy.get(LOCAL_URI);
    }

    public void setLocalUri(Uri uri) {
        this.$proxy.set(LOCAL_URI, uri);
    }

    public String getMimeType() {
        return (String) this.$proxy.get(MIME_TYPE);
    }

    public void setMimeType(String str) {
        this.$proxy.set(MIME_TYPE, str);
    }

    public Map<String, String> getHeaders() {
        return (Map) this.$proxy.get(HEADERS);
    }

    public void setHeaders(Map<String, String> map) {
        this.$proxy.set(HEADERS, map);
    }

    public String getTitle() {
        return (String) this.$proxy.get(TITLE);
    }

    public void setTitle(String str) {
        this.$proxy.set(TITLE, str);
    }

    public String getDescription() {
        return (String) this.$proxy.get(DESCRIPTION);
    }

    public void setDescription(String str) {
        this.$proxy.set(DESCRIPTION, str);
    }

    public Uri getRemoteUri() {
        return (Uri) this.$proxy.get(REMOTE_URI);
    }

    public void setRemoteUri(Uri uri) {
        this.$proxy.set(REMOTE_URI, uri);
    }

    public boolean isAllowScanningByMediaScanner() {
        return ((Boolean) this.$proxy.get(ALLOW_SCANNING_BY_MEDIA_SCANNER)).booleanValue();
    }

    public void setAllowScanningByMediaScanner(boolean z) {
        this.$proxy.set(ALLOW_SCANNING_BY_MEDIA_SCANNER, Boolean.valueOf(z));
    }

    public boolean isAllowedOverMobile() {
        return ((Boolean) this.$proxy.get(ALLOWED_OVER_MOBILE)).booleanValue();
    }

    public void setAllowedOverMobile(boolean z) {
        this.$proxy.set(ALLOWED_OVER_MOBILE, Boolean.valueOf(z));
    }

    public boolean isAllowedOverWifi() {
        return ((Boolean) this.$proxy.get(ALLOWED_OVER_WIFI)).booleanValue();
    }

    public void setAllowedOverWifi(boolean z) {
        this.$proxy.set(ALLOWED_OVER_WIFI, Boolean.valueOf(z));
    }

    public boolean isAllowedOverBluetooth() {
        return ((Boolean) this.$proxy.get(ALLOWED_OVER_BLUETOOTH)).booleanValue();
    }

    public void setAllowedOverBluetooth(boolean z) {
        this.$proxy.set(ALLOWED_OVER_BLUETOOTH, Boolean.valueOf(z));
    }

    public boolean isAllowedOverRoaming() {
        return ((Boolean) this.$proxy.get(ALLOWED_OVER_ROAMING)).booleanValue();
    }

    public void setAllowedOverRoaming(boolean z) {
        this.$proxy.set(ALLOWED_OVER_ROAMING, Boolean.valueOf(z));
    }

    public boolean isAllowedOverMetered() {
        return ((Boolean) this.$proxy.get(ALLOWED_OVER_METERED)).booleanValue();
    }

    public void setAllowedOverMetered(boolean z) {
        this.$proxy.set(ALLOWED_OVER_METERED, Boolean.valueOf(z));
    }

    public boolean isVisibleInDownloadsUi() {
        return ((Boolean) this.$proxy.get(VISIBLE_IN_DOWNLOADS_UI)).booleanValue();
    }

    public void setVisibleInDownloadsUi(boolean z) {
        this.$proxy.set(VISIBLE_IN_DOWNLOADS_UI, Boolean.valueOf(z));
    }

    public int getNotificationVisibility() {
        return ((Integer) this.$proxy.get(NOTIFICATION_VISIBILITY)).intValue();
    }

    public void setNotificationVisibility(int i) {
        this.$proxy.set(NOTIFICATION_VISIBILITY, Integer.valueOf(i));
    }

    public int getStatusCode() {
        return ((Integer) this.$proxy.get(STATUS_CODE)).intValue();
    }

    public void setStatusCode(int i) {
        this.$proxy.set(STATUS_CODE, Integer.valueOf(i));
    }

    public int getReasonCode() {
        return ((Integer) this.$proxy.get(REASON_CODE)).intValue();
    }

    public void setReasonCode(int i) {
        this.$proxy.set(REASON_CODE, Integer.valueOf(i));
    }

    public long getBytesDownloaded() {
        return ((Long) this.$proxy.get(BYTES_DOWNLOADED)).longValue();
    }

    public void setBytesDownloaded(long j) {
        this.$proxy.set(BYTES_DOWNLOADED, Long.valueOf(j));
    }

    public long getActualSize() {
        return ((Long) this.$proxy.get(ACTUAL_SIZE)).longValue();
    }

    public void setActualSize(long j) {
        this.$proxy.set(ACTUAL_SIZE, Long.valueOf(j));
    }

    public long getEstimatedSize() {
        return ((Long) this.$proxy.get(ESTIMATED_SIZE)).longValue();
    }

    public void setEstimatedSize(long j) {
        this.$proxy.set(ESTIMATED_SIZE, Long.valueOf(j));
    }

    public long getCreateTime() {
        return ((Long) this.$proxy.get(CREATE_TIME)).longValue();
    }

    public void setCreateTime(long j) {
        this.$proxy.set(CREATE_TIME, Long.valueOf(j));
    }

    public long getUpdateTime() {
        return ((Long) this.$proxy.get(UPDATE_TIME)).longValue();
    }

    public void setUpdateTime(long j) {
        this.$proxy.set(UPDATE_TIME, Long.valueOf(j));
    }

    public boolean equals(Object obj) {
        return (obj instanceof DownloadRequest) && ((DownloadRequest) obj).$proxy.equals(this.$proxy);
    }

    public int hashCode() {
        return this.$proxy.hashCode();
    }

    public String toString() {
        return this.$proxy.toString();
    }
}

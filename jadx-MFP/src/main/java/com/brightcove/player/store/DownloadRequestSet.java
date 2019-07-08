package com.brightcove.player.store;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import io.requery.CascadeAction;
import io.requery.Persistable;
import io.requery.meta.Attribute;
import io.requery.meta.AttributeBuilder;
import io.requery.meta.Cardinality;
import io.requery.meta.QueryAttribute;
import io.requery.meta.SetAttributeBuilder;
import io.requery.meta.Type;
import io.requery.meta.TypeBuilder;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.IntProperty;
import io.requery.proxy.LongProperty;
import io.requery.proxy.PreInsertListener;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.util.Set;

public class DownloadRequestSet extends AbstractDownloadRequestSet implements Persistable {
    public static final Type<DownloadRequestSet> $TYPE = new TypeBuilder(DownloadRequestSet.class, "DownloadRequestSet").setBaseType(AbstractDownloadRequestSet.class).setCacheable(true).setImmutable(false).setReadOnly(false).setStateless(false).setView(false).setFactory(new Supplier<DownloadRequestSet>() {
        public DownloadRequestSet get() {
            return new DownloadRequestSet();
        }
    }).setProxyProvider(new Function<DownloadRequestSet, EntityProxy<DownloadRequestSet>>() {
        public EntityProxy<DownloadRequestSet> apply(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$proxy;
        }
    }).addAttribute(ESTIMATED_SIZE).addAttribute(NOTIFICATION_VISIBILITY).addAttribute(DOWNLOAD_REQUESTS).addAttribute(REASON_CODE).addAttribute(TITLE).addAttribute(STATUS_CODE).addAttribute(ACTUAL_SIZE).addAttribute(CREATE_TIME).addAttribute(UPDATE_TIME).addAttribute(KEY).addAttribute(BYTES_DOWNLOADED).addAttribute(OFFLINE_VIDEO).build();
    public static final QueryAttribute<DownloadRequestSet, Long> ACTUAL_SIZE = new AttributeBuilder("actualSize", Long.TYPE).setProperty(new LongProperty<DownloadRequestSet>() {
        public Long get(DownloadRequestSet downloadRequestSet) {
            return Long.valueOf(downloadRequestSet.actualSize);
        }

        public void set(DownloadRequestSet downloadRequestSet, Long l) {
            downloadRequestSet.actualSize = l.longValue();
        }

        public long getLong(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.actualSize;
        }

        public void setLong(DownloadRequestSet downloadRequestSet, long j) {
            downloadRequestSet.actualSize = j;
        }
    }).setPropertyName("actualSize").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$actualSize_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$actualSize_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequestSet, Long> BYTES_DOWNLOADED = new AttributeBuilder("bytesDownloaded", Long.TYPE).setProperty(new LongProperty<DownloadRequestSet>() {
        public Long get(DownloadRequestSet downloadRequestSet) {
            return Long.valueOf(downloadRequestSet.bytesDownloaded);
        }

        public void set(DownloadRequestSet downloadRequestSet, Long l) {
            downloadRequestSet.bytesDownloaded = l.longValue();
        }

        public long getLong(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.bytesDownloaded;
        }

        public void setLong(DownloadRequestSet downloadRequestSet, long j) {
            downloadRequestSet.bytesDownloaded = j;
        }
    }).setPropertyName("bytesDownloaded").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$bytesDownloaded_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$bytesDownloaded_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequestSet, Long> CREATE_TIME = new AttributeBuilder("createTime", Long.TYPE).setProperty(new LongProperty<DownloadRequestSet>() {
        public Long get(DownloadRequestSet downloadRequestSet) {
            return Long.valueOf(downloadRequestSet.createTime);
        }

        public void set(DownloadRequestSet downloadRequestSet, Long l) {
            downloadRequestSet.createTime = l.longValue();
        }

        public long getLong(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.createTime;
        }

        public void setLong(DownloadRequestSet downloadRequestSet, long j) {
            downloadRequestSet.createTime = j;
        }
    }).setPropertyName("createTime").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$createTime_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$createTime_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final Attribute<DownloadRequestSet, Set<DownloadRequest>> DOWNLOAD_REQUESTS = new SetAttributeBuilder("downloadRequests", Set.class, DownloadRequest.class).setProperty(new Property<DownloadRequestSet, Set<DownloadRequest>>() {
        public Set<DownloadRequest> get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.downloadRequests;
        }

        public void set(DownloadRequestSet downloadRequestSet, Set<DownloadRequest> set) {
            downloadRequestSet.downloadRequests = set;
        }
    }).setPropertyName("downloadRequests").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$downloadRequests_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$downloadRequests_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).setCascadeAction(CascadeAction.SAVE, CascadeAction.DELETE).setCardinality(Cardinality.ONE_TO_MANY).setMappedAttribute(new Supplier<Attribute>() {
        public Attribute get() {
            return DownloadRequest.REQUEST_SET;
        }
    }).build();
    public static final QueryAttribute<DownloadRequestSet, Long> ESTIMATED_SIZE = new AttributeBuilder("estimatedSize", Long.TYPE).setProperty(new LongProperty<DownloadRequestSet>() {
        public Long get(DownloadRequestSet downloadRequestSet) {
            return Long.valueOf(downloadRequestSet.estimatedSize);
        }

        public void set(DownloadRequestSet downloadRequestSet, Long l) {
            downloadRequestSet.estimatedSize = l.longValue();
        }

        public long getLong(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.estimatedSize;
        }

        public void setLong(DownloadRequestSet downloadRequestSet, long j) {
            downloadRequestSet.estimatedSize = j;
        }
    }).setPropertyName("estimatedSize").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$estimatedSize_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$estimatedSize_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequestSet, Long> KEY = new AttributeBuilder(IpcUtil.KEY_CODE, Long.class).setProperty(new Property<DownloadRequestSet, Long>() {
        public Long get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.key;
        }

        public void set(DownloadRequestSet downloadRequestSet, Long l) {
            downloadRequestSet.key = l;
        }
    }).setPropertyName(IpcUtil.KEY_CODE).setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$key_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$key_state = propertyState;
        }
    }).setKey(true).setGenerated(true).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<DownloadRequestSet, Integer> NOTIFICATION_VISIBILITY = new AttributeBuilder("notificationVisibility", Integer.TYPE).setProperty(new IntProperty<DownloadRequestSet>() {
        public Integer get(DownloadRequestSet downloadRequestSet) {
            return Integer.valueOf(downloadRequestSet.notificationVisibility);
        }

        public void set(DownloadRequestSet downloadRequestSet, Integer num) {
            downloadRequestSet.notificationVisibility = num.intValue();
        }

        public int getInt(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.notificationVisibility;
        }

        public void setInt(DownloadRequestSet downloadRequestSet, int i) {
            downloadRequestSet.notificationVisibility = i;
        }
    }).setPropertyName("notificationVisibility").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$notificationVisibility_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$notificationVisibility_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final Attribute<DownloadRequestSet, OfflineVideo> OFFLINE_VIDEO = new AttributeBuilder("offlineVideo", OfflineVideo.class).setProperty(new Property<DownloadRequestSet, OfflineVideo>() {
        public OfflineVideo get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.offlineVideo;
        }

        public void set(DownloadRequestSet downloadRequestSet, OfflineVideo offlineVideo) {
            downloadRequestSet.offlineVideo = offlineVideo;
        }
    }).setPropertyName("offlineVideo").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$offlineVideo_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$offlineVideo_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(true).setCascadeAction(CascadeAction.SAVE).setCardinality(Cardinality.ONE_TO_ONE).setMappedAttribute(new Supplier<Attribute>() {
        public Attribute get() {
            return OfflineVideo.DOWNLOAD_REQUEST_SET;
        }
    }).build();
    public static final QueryAttribute<DownloadRequestSet, Integer> REASON_CODE = new AttributeBuilder("reasonCode", Integer.TYPE).setProperty(new IntProperty<DownloadRequestSet>() {
        public Integer get(DownloadRequestSet downloadRequestSet) {
            return Integer.valueOf(downloadRequestSet.reasonCode);
        }

        public void set(DownloadRequestSet downloadRequestSet, Integer num) {
            downloadRequestSet.reasonCode = num.intValue();
        }

        public int getInt(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.reasonCode;
        }

        public void setInt(DownloadRequestSet downloadRequestSet, int i) {
            downloadRequestSet.reasonCode = i;
        }
    }).setPropertyName("reasonCode").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$reasonCode_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$reasonCode_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequestSet, Integer> STATUS_CODE = new AttributeBuilder("statusCode", Integer.TYPE).setProperty(new IntProperty<DownloadRequestSet>() {
        public Integer get(DownloadRequestSet downloadRequestSet) {
            return Integer.valueOf(downloadRequestSet.statusCode);
        }

        public void set(DownloadRequestSet downloadRequestSet, Integer num) {
            downloadRequestSet.statusCode = num.intValue();
        }

        public int getInt(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.statusCode;
        }

        public void setInt(DownloadRequestSet downloadRequestSet, int i) {
            downloadRequestSet.statusCode = i;
        }
    }).setPropertyName("statusCode").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$statusCode_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$statusCode_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    public static final QueryAttribute<DownloadRequestSet, String> TITLE = new AttributeBuilder("title", String.class).setProperty(new Property<DownloadRequestSet, String>() {
        public String get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.title;
        }

        public void set(DownloadRequestSet downloadRequestSet, String str) {
            downloadRequestSet.title = str;
        }
    }).setPropertyName("title").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$title_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$title_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<DownloadRequestSet, Long> UPDATE_TIME = new AttributeBuilder("updateTime", Long.TYPE).setProperty(new LongProperty<DownloadRequestSet>() {
        public Long get(DownloadRequestSet downloadRequestSet) {
            return Long.valueOf(downloadRequestSet.updateTime);
        }

        public void set(DownloadRequestSet downloadRequestSet, Long l) {
            downloadRequestSet.updateTime = l.longValue();
        }

        public long getLong(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.updateTime;
        }

        public void setLong(DownloadRequestSet downloadRequestSet, long j) {
            downloadRequestSet.updateTime = j;
        }
    }).setPropertyName("updateTime").setPropertyState(new Property<DownloadRequestSet, PropertyState>() {
        public PropertyState get(DownloadRequestSet downloadRequestSet) {
            return downloadRequestSet.$updateTime_state;
        }

        public void set(DownloadRequestSet downloadRequestSet, PropertyState propertyState) {
            downloadRequestSet.$updateTime_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(false).build();
    /* access modifiers changed from: private */
    public PropertyState $actualSize_state;
    /* access modifiers changed from: private */
    public PropertyState $bytesDownloaded_state;
    /* access modifiers changed from: private */
    public PropertyState $createTime_state;
    /* access modifiers changed from: private */
    public PropertyState $downloadRequests_state;
    /* access modifiers changed from: private */
    public PropertyState $estimatedSize_state;
    /* access modifiers changed from: private */
    public PropertyState $key_state;
    /* access modifiers changed from: private */
    public PropertyState $notificationVisibility_state;
    /* access modifiers changed from: private */
    public PropertyState $offlineVideo_state;
    /* access modifiers changed from: private */
    public final transient EntityProxy<DownloadRequestSet> $proxy = new EntityProxy<>(this, $TYPE);
    /* access modifiers changed from: private */
    public PropertyState $reasonCode_state;
    /* access modifiers changed from: private */
    public PropertyState $statusCode_state;
    /* access modifiers changed from: private */
    public PropertyState $title_state;
    /* access modifiers changed from: private */
    public PropertyState $updateTime_state;

    public DownloadRequestSet() {
        this.$proxy.modifyListeners().addPreInsertListener(new PreInsertListener<DownloadRequestSet>() {
            public void preInsert(DownloadRequestSet downloadRequestSet) {
                DownloadRequestSet.this.onBeforeInsert();
            }
        });
    }

    public Long getKey() {
        return (Long) this.$proxy.get(KEY);
    }

    public String getTitle() {
        return (String) this.$proxy.get(TITLE);
    }

    public void setTitle(String str) {
        this.$proxy.set(TITLE, str);
    }

    public OfflineVideo getOfflineVideo() {
        return (OfflineVideo) this.$proxy.get(OFFLINE_VIDEO);
    }

    public Set<DownloadRequest> getDownloadRequests() {
        return (Set) this.$proxy.get(DOWNLOAD_REQUESTS);
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
        return (obj instanceof DownloadRequestSet) && ((DownloadRequestSet) obj).$proxy.equals(this.$proxy);
    }

    public int hashCode() {
        return this.$proxy.hashCode();
    }

    public String toString() {
        return this.$proxy.toString();
    }
}

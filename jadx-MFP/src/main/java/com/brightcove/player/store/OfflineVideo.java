package com.brightcove.player.store;

import com.brightcove.player.model.Video;
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
import io.requery.proxy.EntityProxy;
import io.requery.proxy.PreInsertListener;
import io.requery.proxy.PreUpdateListener;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.io.File;
import java.util.UUID;

public class OfflineVideo extends AbstractOfflineVideo implements Persistable {
    public static final Type<OfflineVideo> $TYPE = new TypeBuilder(OfflineVideo.class, "OfflineVideo").setBaseType(AbstractOfflineVideo.class).setCacheable(true).setImmutable(false).setReadOnly(false).setStateless(false).setView(false).setFactory(new Supplier<OfflineVideo>() {
        public OfflineVideo get() {
            return new OfflineVideo();
        }
    }).setProxyProvider(new Function<OfflineVideo, EntityProxy<OfflineVideo>>() {
        public EntityProxy<OfflineVideo> apply(OfflineVideo offlineVideo) {
            return offlineVideo.$proxy;
        }
    }).addAttribute(DOWNLOAD_REQUEST_SET).addAttribute(DOWNLOAD_DIRECTORY).addAttribute(VIDEO).addAttribute(VIDEO_ID).addAttribute(KEY).addExpression(DOWNLOAD_REQUEST_SET_ID).build();
    public static final QueryAttribute<OfflineVideo, File> DOWNLOAD_DIRECTORY = new AttributeBuilder("downloadDirectory", File.class).setProperty(new Property<OfflineVideo, File>() {
        public File get(OfflineVideo offlineVideo) {
            return offlineVideo.downloadDirectory;
        }

        public void set(OfflineVideo offlineVideo, File file) {
            offlineVideo.downloadDirectory = file;
        }
    }).setPropertyName("downloadDirectory").setPropertyState(new Property<OfflineVideo, PropertyState>() {
        public PropertyState get(OfflineVideo offlineVideo) {
            return offlineVideo.$downloadDirectory_state;
        }

        public void set(OfflineVideo offlineVideo, PropertyState propertyState) {
            offlineVideo.$downloadDirectory_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).setConverter(new FileConverter()).build();
    public static final QueryAttribute<OfflineVideo, DownloadRequestSet> DOWNLOAD_REQUEST_SET = new AttributeBuilder("downloadRequestSet", DownloadRequestSet.class).setProperty(new Property<OfflineVideo, DownloadRequestSet>() {
        public DownloadRequestSet get(OfflineVideo offlineVideo) {
            return offlineVideo.downloadRequestSet;
        }

        public void set(OfflineVideo offlineVideo, DownloadRequestSet downloadRequestSet) {
            offlineVideo.downloadRequestSet = downloadRequestSet;
        }
    }).setPropertyName("downloadRequestSet").setPropertyState(new Property<OfflineVideo, PropertyState>() {
        public PropertyState get(OfflineVideo offlineVideo) {
            return offlineVideo.$downloadRequestSet_state;
        }

        public void set(OfflineVideo offlineVideo, PropertyState propertyState) {
            offlineVideo.$downloadRequestSet_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).setForeignKey(true).setReferencedClass(DownloadRequestSet.class).setReferencedAttribute(new Supplier<Attribute>() {
        public Attribute get() {
            return DownloadRequestSet.KEY;
        }
    }).setDeleteAction(ReferentialAction.CASCADE).setUpdateAction(ReferentialAction.CASCADE).setCascadeAction(CascadeAction.SAVE, CascadeAction.DELETE).setCardinality(Cardinality.ONE_TO_ONE).setMappedAttribute(new Supplier<Attribute>() {
        public Attribute get() {
            return DownloadRequestSet.OFFLINE_VIDEO;
        }
    }).build();
    public static final QueryExpression<Long> DOWNLOAD_REQUEST_SET_ID = new AttributeBuilder("downloadRequestSet", Long.class).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).setForeignKey(true).setReferencedClass(DownloadRequestSet.class).setReferencedAttribute(new Supplier<Attribute>() {
        public Attribute get() {
            return DownloadRequestSet.KEY;
        }
    }).setDeleteAction(ReferentialAction.CASCADE).setUpdateAction(ReferentialAction.CASCADE).setCascadeAction(CascadeAction.SAVE, CascadeAction.DELETE).setMappedAttribute(new Supplier<Attribute>() {
        public Attribute get() {
            return DownloadRequestSet.OFFLINE_VIDEO;
        }
    }).build();
    public static final QueryAttribute<OfflineVideo, UUID> KEY = new AttributeBuilder(IpcUtil.KEY_CODE, UUID.class).setProperty(new Property<OfflineVideo, UUID>() {
        public UUID get(OfflineVideo offlineVideo) {
            return offlineVideo.key;
        }

        public void set(OfflineVideo offlineVideo, UUID uuid) {
            offlineVideo.key = uuid;
        }
    }).setPropertyName(IpcUtil.KEY_CODE).setPropertyState(new Property<OfflineVideo, PropertyState>() {
        public PropertyState get(OfflineVideo offlineVideo) {
            return offlineVideo.$key_state;
        }

        public void set(OfflineVideo offlineVideo, PropertyState propertyState) {
            offlineVideo.$key_state = propertyState;
        }
    }).setKey(true).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).build();
    public static final QueryAttribute<OfflineVideo, Video> VIDEO = new AttributeBuilder("video", Video.class).setProperty(new Property<OfflineVideo, Video>() {
        public Video get(OfflineVideo offlineVideo) {
            return offlineVideo.video;
        }

        public void set(OfflineVideo offlineVideo, Video video) {
            offlineVideo.video = video;
        }
    }).setPropertyName("video").setPropertyState(new Property<OfflineVideo, PropertyState>() {
        public PropertyState get(OfflineVideo offlineVideo) {
            return offlineVideo.$video_state;
        }

        public void set(OfflineVideo offlineVideo, PropertyState propertyState) {
            offlineVideo.$video_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(true).setUnique(false).setConverter(new VideoConverter()).build();
    public static final QueryAttribute<OfflineVideo, String> VIDEO_ID = new AttributeBuilder("videoId", String.class).setProperty(new Property<OfflineVideo, String>() {
        public String get(OfflineVideo offlineVideo) {
            return offlineVideo.videoId;
        }

        public void set(OfflineVideo offlineVideo, String str) {
            offlineVideo.videoId = str;
        }
    }).setPropertyName("videoId").setPropertyState(new Property<OfflineVideo, PropertyState>() {
        public PropertyState get(OfflineVideo offlineVideo) {
            return offlineVideo.$videoId_state;
        }

        public void set(OfflineVideo offlineVideo, PropertyState propertyState) {
            offlineVideo.$videoId_state = propertyState;
        }
    }).setGenerated(false).setLazy(false).setNullable(false).setUnique(true).build();
    /* access modifiers changed from: private */
    public PropertyState $downloadDirectory_state;
    /* access modifiers changed from: private */
    public PropertyState $downloadRequestSet_state;
    /* access modifiers changed from: private */
    public PropertyState $key_state;
    /* access modifiers changed from: private */
    public final transient EntityProxy<OfflineVideo> $proxy = new EntityProxy<>(this, $TYPE);
    /* access modifiers changed from: private */
    public PropertyState $videoId_state;
    /* access modifiers changed from: private */
    public PropertyState $video_state;

    public OfflineVideo() {
        this.$proxy.modifyListeners().addPreInsertListener(new PreInsertListener<OfflineVideo>() {
            public void preInsert(OfflineVideo offlineVideo) {
                OfflineVideo.this.onBeforeInsert();
            }
        });
        this.$proxy.modifyListeners().addPreUpdateListener(new PreUpdateListener<OfflineVideo>() {
            public void preUpdate(OfflineVideo offlineVideo) {
                OfflineVideo.this.onBeforeUpdate();
            }
        });
    }

    public UUID getKey() {
        return (UUID) this.$proxy.get(KEY);
    }

    public void setKey(UUID uuid) {
        this.$proxy.set(KEY, uuid);
    }

    public File getDownloadDirectory() {
        return (File) this.$proxy.get(DOWNLOAD_DIRECTORY);
    }

    public void setDownloadDirectory(File file) {
        this.$proxy.set(DOWNLOAD_DIRECTORY, file);
    }

    public Video getVideo() {
        return (Video) this.$proxy.get(VIDEO);
    }

    public void setVideo(Video video) {
        this.$proxy.set(VIDEO, video);
    }

    public DownloadRequestSet getDownloadRequestSet() {
        return (DownloadRequestSet) this.$proxy.get(DOWNLOAD_REQUEST_SET);
    }

    public void setDownloadRequestSet(DownloadRequestSet downloadRequestSet) {
        this.$proxy.set(DOWNLOAD_REQUEST_SET, downloadRequestSet);
    }

    public String getVideoId() {
        return (String) this.$proxy.get(VIDEO_ID);
    }

    public void setVideoId(String str) {
        this.$proxy.set(VIDEO_ID, str);
    }

    public boolean equals(Object obj) {
        return (obj instanceof OfflineVideo) && ((OfflineVideo) obj).$proxy.equals(this.$proxy);
    }

    public int hashCode() {
        return this.$proxy.hashCode();
    }

    public String toString() {
        return this.$proxy.toString();
    }
}

package com.brightcove.player.offline;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.brightcove.player.edge.OfflineStoreManager;
import com.brightcove.player.edge.OfflineStoreManager.CanChangeDownloadIdentifier;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Video;
import com.brightcove.player.network.DownloadManager;
import com.brightcove.player.network.DownloadManager.Listener;
import com.brightcove.player.network.DownloadManager.Request;
import com.brightcove.player.network.DownloadStatus;
import com.brightcove.player.store.DownloadRequestSet;
import com.brightcove.player.store.OfflineVideo;
import com.brightcove.player.util.FileUtil;
import com.brightcove.player.util.FileUtil.StrictMode;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class MediaDownloadable {
    public static final String AUDIO_LANGUAGES = "AUDIO_LANGUAGES";
    public static final String AUDIO_LANGUAGE_ROLES = "AUDIO_LANGUAGE_ROLES";
    public static final String CAPTIONS = "CAPTIONS";
    public static final String DEFAULT_MPD_NAME = "master.mpd";
    private static final ConcurrentHashMap<DeliveryType, Constructor<? extends MediaDownloadable>> DOWNLOADABLE_MAP = new ConcurrentHashMap<>();
    private static final String NO_MEDIA_FILE = ".nomedia";
    private static final String TAG = "com.brightcove.player.offline.MediaDownloadable";
    public static final String VIDEO_RENDITIONS = "VIDEO_RENDITIONS";
    protected final Bundle configurationBundle = new Bundle();
    protected final Context context;
    protected final DownloadEventListener downloadEventListener;
    protected final DownloadManager downloadManager;
    /* access modifiers changed from: private */
    public Long downloadRequestSetKey;
    protected long estimatedSize;
    private final Listener listener = new Listener() {
        public void onChanged(@Nullable DownloadStatus downloadStatus) {
            if (downloadStatus.getCode() == -4) {
                MediaDownloadable.this.onMediaDownloadPaused(downloadStatus);
            } else {
                MediaDownloadable.this.onMediaDownloadProgress(downloadStatus);
            }
        }

        public void onCompleted(@Nullable DownloadStatus downloadStatus) {
            MediaDownloadable.this.onMediaDownloadComplete(downloadStatus);
        }

        public void onFailed(@Nullable DownloadStatus downloadStatus) {
            MediaDownloadable.this.onMediaDownloadFailed(downloadStatus);
        }

        public void onCancelled() {
            MediaDownloadable.this.onMediaDownloadCancelled();
        }

        public void onDeleted() {
            MediaDownloadable.this.onMediaDownloadDeleted();
        }
    };
    protected final RequestConfig requestConfig;
    /* access modifiers changed from: private */
    public final OfflineStoreManager storeManager;
    protected final Video video;

    public interface DownloadEventListener {
        void onDownloadCanceled(@NonNull Video video);

        void onDownloadCompleted(@NonNull Video video, @NonNull DownloadStatus downloadStatus);

        void onDownloadDeleted(@NonNull Video video);

        void onDownloadFailed(@NonNull Video video, @NonNull DownloadStatus downloadStatus);

        void onDownloadPaused(@NonNull Video video, @NonNull DownloadStatus downloadStatus);

        void onDownloadProgress(@NonNull Video video, @NonNull DownloadStatus downloadStatus);

        void onDownloadRequested(@NonNull Video video);

        void onDownloadStarted(@NonNull Video video, long j, @NonNull Map<String, Serializable> map);
    }

    public interface MediaFormatListener {
        void onResult(MediaDownloadable mediaDownloadable, Bundle bundle);
    }

    public interface OnVideoSizeCallback {
        void onVideoSizeEstimated(long j);
    }

    public abstract void getMediaFormatTracksAvailable(@NonNull MediaFormatListener mediaFormatListener);

    /* access modifiers changed from: protected */
    @NonNull
    public abstract HashMap<String, Serializable> getMediaProperties();

    public MediaDownloadable(@NonNull Context context2, @NonNull Video video2, @Nullable DownloadEventListener downloadEventListener2, @Nullable RequestConfig requestConfig2) {
        this.context = context2.getApplicationContext();
        this.video = video2;
        this.downloadEventListener = downloadEventListener2;
        if (requestConfig2 == null) {
            requestConfig2 = new RequestConfig();
        }
        this.requestConfig = requestConfig2;
        this.requestConfig.setTitle(video2.getName());
        this.storeManager = OfflineStoreManager.getInstance(context2);
        this.downloadManager = DownloadManager.getInstance(context2);
        OfflineVideo findOfflineVideo = this.storeManager.findOfflineVideo(video2.getId());
        if (findOfflineVideo != null) {
            setupDownloadRequestSet(findOfflineVideo.getDownloadRequestSet());
        }
    }

    /* access modifiers changed from: private */
    public void setupDownloadRequestSet(DownloadRequestSet downloadRequestSet) {
        if (downloadRequestSet != null) {
            this.downloadRequestSetKey = downloadRequestSet.getKey();
            this.estimatedSize = downloadRequestSet.getEstimatedSize();
            int statusCode = downloadRequestSet.getStatusCode();
            if (statusCode != 8 && statusCode != 16 && statusCode != 0) {
                this.downloadManager.addListener(this.downloadRequestSetKey, this.listener);
            }
        }
    }

    public static void registerDownloadable(@NonNull DeliveryType deliveryType, @NonNull Class<? extends MediaDownloadable> cls) {
        try {
            DOWNLOADABLE_MAP.put(deliveryType, cls.getConstructor(new Class[]{Context.class, Video.class, DownloadEventListener.class, RequestConfig.class}));
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "Failed to register media downloadable", e);
        }
    }

    @Nullable
    public static MediaDownloadable create(@NonNull Context context2, @NonNull Video video2, @Nullable DownloadEventListener downloadEventListener2, @Nullable RequestConfig requestConfig2) {
        if (video2.isOfflinePlaybackAllowed()) {
            Map sourceCollections = video2.getSourceCollections();
            for (Entry entry : DOWNLOADABLE_MAP.entrySet()) {
                if (sourceCollections.containsKey(entry.getKey())) {
                    try {
                        return (MediaDownloadable) ((Constructor) entry.getValue()).newInstance(new Object[]{context2, video2, downloadEventListener2, requestConfig2});
                    } catch (Exception e) {
                        Log.e(TAG, "Failed to createLicenseManager media downloadable", e);
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    public static MediaDownloadable create(@NonNull Context context2, @NonNull String str, @Nullable DownloadEventListener downloadEventListener2, @Nullable RequestConfig requestConfig2) {
        return create(context2, Video.createVideo(str), downloadEventListener2, requestConfig2);
    }

    public void setVideoBitrate(int i) {
        this.requestConfig.setVideoBitrate(i);
    }

    private void deleteDownloadDirectory() {
        OfflineVideo findOfflineVideo = this.storeManager.findOfflineVideo(this.video.getId());
        if (findOfflineVideo != null) {
            UUID key = findOfflineVideo.getKey();
            File downloadDirectory = findOfflineVideo.getDownloadDirectory();
            if (downloadDirectory != null && key != null) {
                String absolutePath = downloadDirectory.getAbsolutePath();
                if (absolutePath.endsWith(key.toString()) && !FileUtil.delete(downloadDirectory)) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to delete directory: ");
                    sb.append(absolutePath);
                    Log.w(str, sb.toString());
                }
            }
        }
    }

    @NonNull
    public File getDownloadDirectory() {
        String id = this.video.getId();
        OfflineVideo findOfflineVideo = this.storeManager.findOfflineVideo(id);
        if (findOfflineVideo == null) {
            OfflineVideo offlineVideo = new OfflineVideo();
            offlineVideo.setVideoId(id);
            findOfflineVideo = (OfflineVideo) this.storeManager.saveEntity(offlineVideo);
        }
        File downloadDirectory = findOfflineVideo.getDownloadDirectory();
        if (downloadDirectory != null) {
            return downloadDirectory;
        }
        File downloadPath = this.requestConfig.getDownloadPath();
        if (downloadPath == null) {
            downloadPath = this.context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        }
        if (downloadPath.isDirectory() || downloadPath.mkdirs()) {
            File file = new File(downloadPath, NO_MEDIA_FILE);
            if (!file.isFile()) {
                try {
                    file.createNewFile();
                } catch (IOException unused) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to createLicenseManager file: ");
                    sb.append(file.getAbsolutePath());
                    Log.w(str, sb.toString());
                }
            }
        } else {
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Download path is not valid:");
            sb2.append(downloadPath.getAbsolutePath());
            Log.w(str2, sb2.toString());
        }
        String uuid = findOfflineVideo.getKey().toString();
        return !downloadPath.getAbsolutePath().endsWith(uuid) ? new File(downloadPath, uuid) : downloadPath;
    }

    public long getEstimatedSize() {
        return this.estimatedSize;
    }

    public void estimatedSize(OnVideoSizeCallback onVideoSizeCallback) {
        onVideoSizeCallback.onVideoSizeEstimated(this.estimatedSize);
    }

    public Bundle getConfigurationBundle() {
        Bundle bundle = new Bundle();
        bundle.putAll(this.configurationBundle);
        return bundle;
    }

    public void setConfigurationBundle(Bundle bundle) {
        if (bundle != null) {
            this.configurationBundle.clear();
            this.configurationBundle.putAll(bundle);
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public void enqueueDownloadRequest(@NonNull final Request... requestArr) {
        if (this.downloadRequestSetKey == null) {
            this.downloadManager.createDownloadRequestSet(this.requestConfig, this.estimatedSize, this.listener).subscribe((Consumer<? super T>) new Consumer<DownloadRequestSet>() {
                public void accept(DownloadRequestSet downloadRequestSet) throws Exception {
                    MediaDownloadable.this.storeManager.saveOfflineVideo(MediaDownloadable.this.video, MediaDownloadable.this.getDownloadDirectory(), downloadRequestSet);
                    MediaDownloadable.this.onMediaDownloadStarted();
                    MediaDownloadable.this.downloadManager.enqueueDownload(downloadRequestSet, requestArr).subscribe();
                    if (MediaDownloadable.this.downloadRequestSetKey == null) {
                        MediaDownloadable.this.setupDownloadRequestSet(downloadRequestSet);
                    }
                }
            });
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Downloads have been enqueued already for media: ");
        sb.append(this.video.getName());
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void onMediaDownloadRequested() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Processing request to download media: ");
        sb.append(this.video.getName());
        Log.v(str, sb.toString());
        DownloadEventListener downloadEventListener2 = this.downloadEventListener;
        if (downloadEventListener2 != null) {
            downloadEventListener2.onDownloadRequested(this.video);
        }
    }

    /* access modifiers changed from: protected */
    public void onMediaDownloadStarted() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Started to download media: ");
        sb.append(this.video.getName());
        Log.d(str, sb.toString());
        DownloadEventListener downloadEventListener2 = this.downloadEventListener;
        if (downloadEventListener2 != null) {
            downloadEventListener2.onDownloadStarted(this.video, this.estimatedSize, getMediaProperties());
        }
    }

    /* access modifiers changed from: protected */
    public void onMediaDownloadPaused(@NonNull DownloadStatus downloadStatus) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Paused download of media: ");
        sb.append(this.video.getName());
        Log.v(str, sb.toString());
        DownloadEventListener downloadEventListener2 = this.downloadEventListener;
        if (downloadEventListener2 != null) {
            downloadEventListener2.onDownloadPaused(this.video, downloadStatus);
        }
    }

    /* access modifiers changed from: protected */
    public void onMediaDownloadProgress(@NonNull DownloadStatus downloadStatus) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Download in progress for media: ");
        sb.append(this.video.getName());
        Log.v(str, sb.toString());
        DownloadEventListener downloadEventListener2 = this.downloadEventListener;
        if (downloadEventListener2 != null) {
            downloadEventListener2.onDownloadProgress(this.video, downloadStatus);
        }
    }

    /* access modifiers changed from: protected */
    public void onMediaDownloadComplete(@NonNull DownloadStatus downloadStatus) {
        this.storeManager.saveOfflineVideo(this.video);
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Finished downloading media: ");
        sb.append(this.video.getName());
        Log.d(str, sb.toString());
        this.downloadManager.removeListener(this.listener);
        DownloadEventListener downloadEventListener2 = this.downloadEventListener;
        if (downloadEventListener2 != null) {
            downloadEventListener2.onDownloadCompleted(this.video, downloadStatus);
        }
    }

    /* access modifiers changed from: protected */
    public void onMediaDownloadFailed(@NonNull DownloadStatus downloadStatus) {
        Log.w(TAG, String.format("Failed to download media[%s], reason[%d]", new Object[]{this.video.getName(), Integer.valueOf(downloadStatus.getReason())}));
        this.downloadManager.removeListener(this.listener);
        DownloadEventListener downloadEventListener2 = this.downloadEventListener;
        if (downloadEventListener2 != null) {
            downloadEventListener2.onDownloadFailed(this.video, downloadStatus);
        }
    }

    /* access modifiers changed from: protected */
    @CanChangeDownloadIdentifier
    public void onMediaDownloadCancelled() {
        Log.d(TAG, String.format("Cancelled download of media[%s]", new Object[]{this.video.getName()}));
        this.downloadManager.removeListener(this.listener);
        deleteDownloadDirectory();
        DownloadEventListener downloadEventListener2 = this.downloadEventListener;
        if (downloadEventListener2 != null) {
            downloadEventListener2.onDownloadCanceled(this.video);
        }
        this.storeManager.changeDownloadIdentifier(this.video);
    }

    /* access modifiers changed from: protected */
    @CanChangeDownloadIdentifier
    public void onMediaDownloadDeleted() {
        Log.d(TAG, String.format("Deleted downloaded media[%s]", new Object[]{this.video.getName()}));
        this.downloadManager.removeListener(this.listener);
        deleteDownloadDirectory();
        DownloadEventListener downloadEventListener2 = this.downloadEventListener;
        if (downloadEventListener2 != null) {
            downloadEventListener2.onDownloadDeleted(this.video);
        }
        this.storeManager.changeDownloadIdentifier(this.video);
    }

    public boolean requestDownload() {
        onMediaDownloadRequested();
        try {
            StrictMode.makeReadWriteDirectory(getDownloadDirectory());
            return true;
        } catch (IllegalArgumentException e) {
            Log.w(TAG, "Download path is not valid", e);
            return false;
        }
    }

    public int pauseDownload() {
        return this.downloadManager.pauseDownload(this.downloadRequestSetKey).getCode();
    }

    public int resumeDownload() {
        return this.downloadManager.resumeDownload(this.downloadRequestSetKey).getCode();
    }

    private boolean removeDownload(boolean z) {
        String id = this.video.getId();
        OfflineVideo findOfflineVideo = this.storeManager.findOfflineVideo(id);
        if (findOfflineVideo != null) {
            DownloadRequestSet downloadRequestSet = findOfflineVideo.getDownloadRequestSet();
            if (downloadRequestSet != null && (z || downloadRequestSet.getStatusCode() != 8)) {
                this.downloadManager.addListener(downloadRequestSet.getKey(), this.listener);
                this.downloadManager.deleteDownload(downloadRequestSet);
                if (z) {
                    this.storeManager.deleteOfflineVideo(id);
                }
                return true;
            }
        }
        return false;
    }

    public boolean cancelDownload() {
        return removeDownload(false);
    }

    public boolean deleteDownload() {
        return removeDownload(true);
    }

    public DownloadStatus getDownloadStatus() {
        DownloadRequestSet downloadRequestSet;
        OfflineVideo findOfflineVideo = this.storeManager.findOfflineVideo(this.video.getId());
        if (findOfflineVideo == null) {
            downloadRequestSet = null;
        } else {
            downloadRequestSet = findOfflineVideo.getDownloadRequestSet();
        }
        return this.downloadManager.getDownloadStatus(downloadRequestSet);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public Request createDownloadRequest(@NonNull Uri uri, @NonNull Uri uri2) {
        return new Request(uri).setLocalUri(uri2).setNotificationVisibility(2).setAllowedOverMobile(this.requestConfig.isMobileDownloadAllowed()).setAllowedOverWifi(this.requestConfig.isWifiDownloadAllowed()).setAllowedOverBluetooth(this.requestConfig.isBluetoothDownloadAllowed()).setAllowedOverRoaming(this.requestConfig.isRoamingDownloadAllowed()).setAllowedOverMetered(this.requestConfig.isMeteredDownloadAllowed()).setVisibleInDownloadsUi(false);
    }
}

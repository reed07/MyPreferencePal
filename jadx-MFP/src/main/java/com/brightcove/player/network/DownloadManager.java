package com.brightcove.player.network;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.brightcove.player.data.Optional;
import com.brightcove.player.edge.OfflineStoreManager;
import com.brightcove.player.network.IDownloadManager.IRequest;
import com.brightcove.player.offline.R;
import com.brightcove.player.offline.RequestConfig;
import com.brightcove.player.store.DownloadRequest;
import com.brightcove.player.store.DownloadRequestSet;
import com.brightcove.player.util.Convert;
import com.brightcove.player.util.FileUtil.StrictMode;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.exception.FileDownloadHttpException;
import com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException;
import com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException;
import io.reactivex.Observable;
import io.reactivex.Scheduler.Worker;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class DownloadManager implements IDownloadManager {
    private static final int BATCH_SIZE = 20;
    private static final int NOTIFICATION_ID = 1000;
    private static final int STATUS_DOWNLOADING_REPORT_FREQUENCY_MS = 250;
    /* access modifiers changed from: private */
    public static final String TAG = "DownloadManager";
    private static volatile DownloadManager instance;
    private final com.brightcove.player.network.ConnectivityMonitor.Listener connectivityListener = new com.brightcove.player.network.ConnectivityMonitor.Listener() {
        public void onConnectivityChanged(boolean z, @Nullable NetworkInfo networkInfo) {
            if (z) {
                DownloadManager.this.queueNextBatch();
                return;
            }
            try {
                FileDownloader.getImpl().pauseAll();
            } catch (IllegalStateException unused) {
                Log.w(DownloadManager.TAG, "Failed to attempt pausing any active download.");
            }
        }
    };
    private final WeakReference<Context> contextReference;
    /* access modifiers changed from: private */
    public ConcurrentSkipListSet<Long> currentDownloadsQueued = new ConcurrentSkipListSet<>();
    public final FileDownloadLargeFileListener downloadListener = new FileDownloadLargeFileListener() {
        /* access modifiers changed from: protected */
        public void pending(BaseDownloadTask baseDownloadTask, long j, long j2) {
            com.brightcove.player.logging.Log.v(DownloadManager.TAG, "Waiting to start request # %d: path [%s], bytesDownloaded = %d, totalBytes = %d", Long.valueOf((long) baseDownloadTask.getId()), baseDownloadTask.getPath(), Long.valueOf(j), Long.valueOf(j2));
        }

        /* access modifiers changed from: protected */
        public void progress(BaseDownloadTask baseDownloadTask, long j, long j2) {
            long id = (long) baseDownloadTask.getId();
            com.brightcove.player.logging.Log.v(DownloadManager.TAG, "Downloading request # %d: path [%s], bytesDownloaded = %d, totalBytes = %d", Long.valueOf(id), baseDownloadTask.getPath(), Long.valueOf(j), Long.valueOf(j2));
            onDownloadRequestStateChanged(Long.valueOf(id), 2, 0, j, j2);
        }

        /* access modifiers changed from: protected */
        public void paused(BaseDownloadTask baseDownloadTask, long j, long j2) {
            long id = (long) baseDownloadTask.getId();
            com.brightcove.player.logging.Log.v(DownloadManager.TAG, "Paused request # %d: path [%s], bytesDownloaded = %d, totalBytes = %d", Long.valueOf(id), baseDownloadTask.getPath(), Long.valueOf(j), Long.valueOf(j2));
            onDownloadRequestStateChanged(Long.valueOf(id), -1, 4, j, j2);
        }

        /* access modifiers changed from: protected */
        public void completed(BaseDownloadTask baseDownloadTask) {
            long j;
            long j2;
            int i;
            int i2;
            String path = baseDownloadTask.getPath();
            long id = (long) baseDownloadTask.getId();
            if (StrictMode.isFile(path)) {
                long largeFileSoFarBytes = baseDownloadTask.getLargeFileSoFarBytes();
                long largeFileTotalBytes = baseDownloadTask.getLargeFileTotalBytes();
                com.brightcove.player.logging.Log.v(DownloadManager.TAG, "Finished download of [%s], bytesDownloaded = %d, totalBytes = %d", path, Long.valueOf(largeFileSoFarBytes), Long.valueOf(largeFileTotalBytes));
                j2 = largeFileSoFarBytes;
                j = largeFileTotalBytes;
                i2 = 8;
                i = 0;
            } else {
                j2 = 0;
                j = 0;
                i2 = -1;
                i = 1001;
            }
            onDownloadRequestStateChanged(Long.valueOf(id), i2, i, j2, j);
        }

        /* access modifiers changed from: protected */
        public void error(BaseDownloadTask baseDownloadTask, Throwable th) {
            long id = (long) baseDownloadTask.getId();
            long largeFileSoFarBytes = baseDownloadTask.getLargeFileSoFarBytes();
            long largeFileTotalBytes = baseDownloadTask.getLargeFileTotalBytes();
            com.brightcove.player.logging.Log.v(DownloadManager.TAG, "Failed to download request # %d: path [%s], bytesDownloaded = %d, totalBytes = %d", th, Long.valueOf(id), baseDownloadTask.getPath(), Long.valueOf(largeFileSoFarBytes), Long.valueOf(largeFileTotalBytes));
            if (th instanceof FileDownloadNetworkPolicyException) {
                onDownloadRequestStateChanged(Long.valueOf(id), -1, 2, largeFileSoFarBytes, largeFileTotalBytes);
            } else if (th instanceof FileDownloadOutOfSpaceException) {
                onDownloadRequestStateChanged(Long.valueOf(id), 16, 1006, largeFileSoFarBytes, largeFileTotalBytes);
            } else if (th instanceof FileDownloadHttpException) {
                onDownloadRequestStateChanged(Long.valueOf(id), 16, 1002, largeFileSoFarBytes, largeFileTotalBytes);
            } else if ((th instanceof FileDownloadGiveUpRetryException) || (th instanceof IOException)) {
                onDownloadRequestStateChanged(Long.valueOf(id), -1, 1, largeFileSoFarBytes, largeFileTotalBytes);
            } else if (th instanceof NullPointerException) {
                onDownloadRequestStateChanged(Long.valueOf(id), -1, 1000, largeFileSoFarBytes, largeFileTotalBytes);
            } else {
                onDownloadRequestStateChanged(Long.valueOf(id), 16, 1000, largeFileSoFarBytes, largeFileTotalBytes);
            }
        }

        /* access modifiers changed from: protected */
        public void warn(BaseDownloadTask baseDownloadTask) {
            com.brightcove.player.logging.Log.w(DownloadManager.TAG, "Downloading already downloaded [%s]", baseDownloadTask.getPath());
        }

        private void onDownloadRequestStateChanged(@NonNull Long l, int i, int i2, long j, long j2) {
            Worker access$700 = DownloadManager.this.downloadQueueWorker;
            final int i3 = i;
            final Long l2 = l;
            final int i4 = i2;
            final long j3 = j;
            final long j4 = j2;
            AnonymousClass1 r0 = new Runnable() {
                public void run() {
                    boolean z;
                    synchronized (DownloadManager.this.requestSetLock) {
                        boolean z2 = i3 == 8;
                        DownloadRequest updateDownloadRequestStatusByDownloadId = DownloadManager.this.storeManager.updateDownloadRequestStatusByDownloadId(l2, i3, i4, j3, j4, !z2);
                        DownloadRequestSet downloadRequestSet = null;
                        if (!(updateDownloadRequestStatusByDownloadId == null || updateDownloadRequestStatusByDownloadId.getRequestSet() == null)) {
                            Long key = updateDownloadRequestStatusByDownloadId.getRequestSet().getKey();
                            DownloadRequestSet findDownloadRequestSetByKey = DownloadManager.this.storeManager.findDownloadRequestSetByKey(key);
                            if (z2) {
                                boolean isDownloadCompleted = DownloadManager.this.storeManager.isDownloadCompleted(key);
                                DownloadManager.this.currentDownloadsQueued.remove(l2);
                                z = isDownloadCompleted;
                            } else {
                                z = false;
                            }
                            if (z2 && findDownloadRequestSetByKey != null) {
                                DownloadManager.this.storeManager.updateDownloadRequestSetStatus(findDownloadRequestSetByKey, i3, i4, updateDownloadRequestStatusByDownloadId, z);
                            }
                            downloadRequestSet = findDownloadRequestSetByKey;
                        }
                        DownloadManager.this.reportStatusChange(downloadRequestSet);
                        if ((i3 == 8 || i3 == 16) && DownloadManager.this.currentDownloadsQueued.size() == 0 && DownloadManager.this.isNextBatchReadyToBeQueued.getAndSet(false)) {
                            DownloadManager.this.queueNextBatch();
                        }
                    }
                }
            };
            access$700.schedule(r0);
        }
    };
    /* access modifiers changed from: private */
    public final Worker downloadQueueWorker = Schedulers.single().createWorker();
    /* access modifiers changed from: private */
    public AtomicBoolean isNextBatchReadyToBeQueued;
    private long lastTimeStatusDownloadingWasReported;
    private final WeakHashMap<Listener, Long> listenerMap = new WeakHashMap<>();
    private final Runnable queueBatchTask = new Runnable() {
        public void run() {
            ConnectivityMonitor instance = ConnectivityMonitor.getInstance(DownloadManager.this.getContext());
            if (instance.isConnected()) {
                boolean z = !instance.isWifiConnection();
                List findDownloadsToBeQueued = DownloadManager.this.storeManager.findDownloadsToBeQueued(20, z);
                int i = 0;
                while (i == 0 && findDownloadsToBeQueued.size() > 0) {
                    i = DownloadManager.this.addDownloadRequestToQueue(findDownloadsToBeQueued);
                    if (i == 0) {
                        findDownloadsToBeQueued = DownloadManager.this.storeManager.findDownloadsToBeQueued(20, z);
                    }
                }
                DownloadManager.this.isNextBatchReadyToBeQueued.set(true);
            }
        }
    };
    /* access modifiers changed from: private */
    public final Object requestSetLock = new Object();
    /* access modifiers changed from: private */
    public final OfflineStoreManager storeManager;

    public interface Listener {
        void onCancelled();

        void onChanged(@Nullable DownloadStatus downloadStatus);

        void onCompleted(@Nullable DownloadStatus downloadStatus);

        void onDeleted();

        void onFailed(@Nullable DownloadStatus downloadStatus);
    }

    public static class Request implements IRequest {
        boolean allowScanningByMediaScanner;
        private boolean allowedOverBluetooth;
        private boolean allowedOverMetered;
        private boolean allowedOverMobile;
        private boolean allowedOverRoaming;
        private boolean allowedOverWifi;
        private String description;
        long estimatedSize;
        private Map<String, String> headers;
        private Uri localUri;
        private String mimeType;
        int notificationVisibility;
        private Uri remoteUri;
        private String title;
        private boolean visibleInDownloadsUi;

        public Request(Uri uri) {
            this.remoteUri = uri;
        }

        public Request(Uri uri, Uri uri2) {
            this(uri);
            this.localUri = uri2;
        }

        @NonNull
        public Uri getRemoteUri() {
            return this.remoteUri;
        }

        public Request setRemoteUri(@NonNull Uri uri) {
            this.remoteUri = uri;
            return this;
        }

        @Nullable
        public Uri getLocalUri() {
            return this.localUri;
        }

        public Request setLocalUri(@NonNull Uri uri) {
            this.localUri = uri;
            return this;
        }

        @Nullable
        public String getMimeType() {
            return this.mimeType;
        }

        public Request setMimeType(String str) {
            this.mimeType = str;
            return this;
        }

        @Nullable
        public Map<String, String> getHeaders() {
            return this.headers;
        }

        public Request setHeaders(Map<String, String> map) {
            this.headers = map;
            return this;
        }

        public long getEstimatedSize() {
            return this.estimatedSize;
        }

        public Request setEstimatedSize(long j) {
            this.estimatedSize = j;
            return this;
        }

        @Nullable
        public String getTitle() {
            return this.title;
        }

        public Request setTitle(String str) {
            this.title = str;
            return this;
        }

        @Nullable
        public String getDescription() {
            return this.description;
        }

        public Request setDescription(String str) {
            this.description = str;
            return this;
        }

        public boolean isAllowScanningByMediaScanner() {
            return this.allowScanningByMediaScanner;
        }

        public Request setAllowScanningByMediaScanner(boolean z) {
            this.allowScanningByMediaScanner = z;
            return this;
        }

        public boolean isAllowedOverMobile() {
            return this.allowedOverMobile;
        }

        public Request setAllowedOverMobile(boolean z) {
            this.allowedOverMobile = z;
            return this;
        }

        public boolean isAllowedOverWifi() {
            return this.allowedOverWifi;
        }

        public Request setAllowedOverWifi(boolean z) {
            this.allowedOverWifi = z;
            return this;
        }

        public boolean isAllowedOverBluetooth() {
            return this.allowedOverBluetooth;
        }

        public Request setAllowedOverBluetooth(boolean z) {
            this.allowedOverBluetooth = z;
            return this;
        }

        public boolean isAllowedOverRoaming() {
            return this.allowedOverRoaming;
        }

        public Request setAllowedOverRoaming(boolean z) {
            this.allowedOverRoaming = z;
            return this;
        }

        public boolean isAllowedOverMetered() {
            return this.allowedOverMetered;
        }

        public Request setAllowedOverMetered(boolean z) {
            this.allowedOverMetered = z;
            return this;
        }

        public boolean isVisibleInDownloadsUi() {
            return this.visibleInDownloadsUi;
        }

        public Request setVisibleInDownloadsUi(boolean z) {
            this.visibleInDownloadsUi = z;
            return this;
        }

        public int getNotificationVisibility() {
            return this.notificationVisibility;
        }

        public Request setNotificationVisibility(int i) {
            this.notificationVisibility = i;
            return this;
        }
    }

    /* access modifiers changed from: private */
    public void reportStatusChange(@Nullable DownloadRequestSet downloadRequestSet) {
        if (downloadRequestSet != null) {
            Long key = downloadRequestSet.getKey();
            DownloadStatus downloadStatus = getDownloadStatus(downloadRequestSet);
            int code = downloadStatus.getCode();
            if (code != -4) {
                postStatusNotification(downloadRequestSet, downloadStatus);
                if (code == 8) {
                    onDownloadCompleted(key.longValue(), downloadStatus);
                } else if (code == 16) {
                    onDownloadFailed(key.longValue(), downloadStatus);
                } else if (code == 2) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.lastTimeStatusDownloadingWasReported > 250) {
                        this.lastTimeStatusDownloadingWasReported = currentTimeMillis;
                        onDownloadChanged(key.longValue(), downloadStatus);
                    }
                } else {
                    onDownloadChanged(key.longValue(), downloadStatus);
                }
            }
        }
    }

    private DownloadManager(@NonNull Context context) {
        Context applicationContext = context.getApplicationContext();
        this.contextReference = new WeakReference<>(applicationContext);
        this.storeManager = OfflineStoreManager.getInstance(context);
        this.isNextBatchReadyToBeQueued = new AtomicBoolean(true);
        FileDownloader.setup(context);
        if (VERSION.SDK_INT >= 26) {
            createNotificationChannel();
        }
        this.downloadQueueWorker.schedule(new Runnable() {
            public void run() {
                DownloadManager.this.searchInvalidStatus();
                DownloadManager.this.queueNextBatch();
            }
        });
        ConnectivityMonitor.getInstance(applicationContext).addListener(this.connectivityListener);
    }

    @NonNull
    public static DownloadManager getInstance(@NonNull Context context) {
        if (instance == null) {
            synchronized (DownloadManager.class) {
                if (instance == null) {
                    instance = new DownloadManager(context);
                }
            }
        }
        return instance;
    }

    public boolean addListener(@NonNull Long l, @NonNull Listener listener) {
        boolean z;
        synchronized (this.listenerMap) {
            if (l != null) {
                if (!this.listenerMap.containsKey(listener)) {
                    this.listenerMap.put(listener, l);
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public boolean removeListener(@NonNull Listener listener) {
        boolean z;
        synchronized (this.listenerMap) {
            z = this.listenerMap.remove(listener) != null;
        }
        return z;
    }

    @NonNull
    private HashMap<Listener, Long> getListeners() {
        HashMap<Listener, Long> hashMap;
        synchronized (this.listenerMap) {
            hashMap = new HashMap<>(this.listenerMap);
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public void searchInvalidStatus() {
        byte b;
        FileDownloader impl = FileDownloader.getImpl();
        List<DownloadRequest> findCurrentDownloadBatchInProgress = this.storeManager.findCurrentDownloadBatchInProgress(500);
        while (findCurrentDownloadBatchInProgress.size() > 0) {
            for (DownloadRequest downloadRequest : findCurrentDownloadBatchInProgress) {
                if (impl.isServiceConnected()) {
                    b = impl.getStatus(Convert.toInt(downloadRequest.getDownloadId()), Convert.toString(downloadRequest.getLocalUri()));
                    impl.clear(Convert.toInt(downloadRequest.getDownloadId()), Convert.toString(downloadRequest.getLocalUri()));
                } else {
                    b = 0;
                }
                int statusCode = downloadRequest.getStatusCode();
                if (b == 0 && (statusCode == 1 || statusCode == -4 || statusCode == 2)) {
                    this.storeManager.updateDownloadRequestStatusByDownloadId(downloadRequest.getDownloadId(), -1, 1, 0, 0, true);
                }
            }
            findCurrentDownloadBatchInProgress = this.storeManager.findCurrentDownloadBatchInProgress(500);
        }
    }

    /* access modifiers changed from: private */
    public Context getContext() {
        WeakReference<Context> weakReference = this.contextReference;
        Context context = weakReference == null ? null : (Context) weakReference.get();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("No application context!");
    }

    @NonNull
    public Observable<DownloadRequestSet> createDownloadRequestSet(@Nullable RequestConfig requestConfig, long j, @NonNull Listener listener) {
        final RequestConfig requestConfig2 = requestConfig;
        final long j2 = j;
        final Listener listener2 = listener;
        AnonymousClass4 r0 = new Callable<DownloadRequestSet>() {
            public DownloadRequestSet call() throws Exception {
                DownloadRequestSet createDownloadRequestSet = DownloadManager.this.storeManager.createDownloadRequestSet(requestConfig2, j2);
                DownloadManager.this.addListener(createDownloadRequestSet.getKey(), listener2);
                return createDownloadRequestSet;
            }
        };
        return Observable.fromCallable(r0).subscribeOn(Schedulers.io());
    }

    @NonNull
    public Observable<DownloadRequestSet> enqueueDownload(@NonNull final DownloadRequestSet downloadRequestSet, @NonNull final IRequest... iRequestArr) {
        return Observable.fromCallable(new Callable<DownloadRequestSet>() {
            public DownloadRequestSet call() throws Exception {
                DownloadRequestSet addDownloadRequests = DownloadManager.this.storeManager.addDownloadRequests(downloadRequestSet, iRequestArr);
                DownloadManager.this.queueNextBatch();
                return addDownloadRequests;
            }
        }).subscribeOn(Schedulers.io());
    }

    @NonNull
    public DownloadStatus pauseDownload(@Nullable DownloadRequestSet downloadRequestSet) {
        return downloadRequestSet == null ? new DownloadStatus() : pauseDownload(downloadRequestSet.getKey());
    }

    @NonNull
    public DownloadStatus pauseDownload(@NonNull final Long l) {
        return (DownloadStatus) Observable.fromCallable(new Callable<DownloadStatus>() {
            public DownloadStatus call() throws Exception {
                DownloadManager.this.downloadQueueWorker.schedule(new Runnable() {
                    public void run() {
                        DownloadManager.this.storeManager.findDownloadRequestSetByKey(l);
                        DownloadManager.this.removeDownloadRequestFromQueue(DownloadManager.this.storeManager.pauseDownloadRequestSet(l), true);
                        DownloadStatus downloadStatus = DownloadManager.this.getDownloadStatus(l);
                        if (downloadStatus.code == -4) {
                            DownloadManager.this.cancelStatusNotification(l.longValue());
                            DownloadManager.this.onDownloadChanged(l.longValue(), downloadStatus);
                            return;
                        }
                        String access$100 = DownloadManager.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Tried to pause but got status:");
                        sb.append(downloadStatus.code);
                        com.brightcove.player.logging.Log.w(access$100, sb.toString(), new Object[0]);
                    }
                });
                return DownloadManager.this.getDownloadStatus(l);
            }
        }).subscribeOn(Schedulers.io()).blockingFirst();
    }

    @NonNull
    public DownloadStatus resumeDownload(@Nullable DownloadRequestSet downloadRequestSet) {
        return downloadRequestSet == null ? new DownloadStatus() : resumeDownload(downloadRequestSet.getKey());
    }

    @NonNull
    public DownloadStatus resumeDownload(@NonNull final Long l) {
        Optional optional = (Optional) Observable.fromCallable(new Callable<Optional<DownloadRequestSet>>() {
            public Optional<DownloadRequestSet> call() throws Exception {
                return new Optional<>(DownloadManager.this.storeManager.resumeDownloadRequestSet(l));
            }
        }).subscribeOn(Schedulers.io()).blockingFirst();
        if (!optional.isPresent()) {
            return new DownloadStatus();
        }
        DownloadRequestSet downloadRequestSet = (DownloadRequestSet) optional.get();
        DownloadStatus downloadStatus = getDownloadStatus(downloadRequestSet);
        postStatusNotification(downloadRequestSet, downloadStatus);
        onDownloadChanged(l.longValue(), downloadStatus);
        queueNextBatch();
        return downloadStatus;
    }

    public boolean deleteDownload(@NonNull Long l) {
        return deleteDownload(this.storeManager.findDownloadRequestSetByKey(l));
    }

    public boolean deleteDownload(@Nullable DownloadRequestSet downloadRequestSet) {
        boolean booleanValue;
        boolean z = false;
        if (downloadRequestSet == null) {
            return false;
        }
        synchronized (this.requestSetLock) {
            final Long key = downloadRequestSet.getKey();
            if (downloadRequestSet.getStatusCode() == 8) {
                z = true;
            }
            booleanValue = ((Boolean) Observable.fromCallable(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    DownloadManager.this.removeDownloadRequestFromQueue(DownloadManager.this.storeManager.markRequestSetForRemoval(key), false);
                    return Boolean.valueOf(DownloadManager.this.storeManager.deleteDownloadRequestSet(key));
                }
            }).subscribeOn(Schedulers.io()).blockingFirst()).booleanValue();
            if (booleanValue) {
                cancelStatusNotification(key.longValue());
                if (z) {
                    onDownloadDeleted(key.longValue());
                } else {
                    onDownloadCancelled(key.longValue());
                }
            }
        }
        return booleanValue;
    }

    @NonNull
    public DownloadStatus getDownloadStatus(@NonNull Long l) {
        return getDownloadStatus(this.storeManager.findDownloadRequestSetByKey(l));
    }

    @NonNull
    public DownloadStatus getDownloadStatus(@Nullable final DownloadRequestSet downloadRequestSet) {
        return (DownloadStatus) Observable.fromCallable(new Callable<DownloadStatus>() {
            public DownloadStatus call() throws Exception {
                DownloadStatus downloadStatus = new DownloadStatus();
                DownloadRequestSet downloadRequestSet = (DownloadRequestSet) DownloadManager.this.storeManager.refreshEntity(downloadRequestSet);
                if (downloadRequestSet != null) {
                    downloadStatus.setCode(downloadRequestSet.getStatusCode());
                    downloadStatus.setReason(downloadRequestSet.getReasonCode());
                    downloadStatus.bytesDownloaded = downloadRequestSet.getBytesDownloaded();
                    downloadStatus.actualSize = downloadRequestSet.getActualSize();
                    downloadStatus.estimatedSize = downloadRequestSet.getEstimatedSize();
                    downloadStatus.time = downloadRequestSet.getModifiedTime();
                }
                return downloadStatus;
            }
        }).subscribeOn(Schedulers.io()).blockingSingle();
    }

    /* access modifiers changed from: private */
    public void queueNextBatch() {
        this.downloadQueueWorker.schedule(this.queueBatchTask);
    }

    /* access modifiers changed from: private */
    public int addDownloadRequestToQueue(@NonNull Collection<DownloadRequest> collection) {
        FileDownloader impl = FileDownloader.getImpl();
        int i = 0;
        for (DownloadRequest downloadRequest : collection) {
            DownloadRequestSet downloadRequestSet = (DownloadRequestSet) this.storeManager.refreshEntity(downloadRequest.getRequestSet());
            if (downloadRequestSet == null || !downloadRequestSet.isMarkedForDeletion()) {
                File file = new File(Convert.toString(downloadRequest.getLocalUri()).replace("file://", ""));
                File file2 = new File(file.getParent());
                if (!file2.isDirectory() && !file2.mkdirs()) {
                    com.brightcove.player.logging.Log.w(TAG, "Failed to create path: %s", file2.getAbsolutePath());
                }
                BaseDownloadTask callbackProgressTimes = impl.create(downloadRequest.getRemoteUri().toString()).setCallbackProgressTimes(0);
                callbackProgressTimes.setPath(file.getAbsolutePath());
                if (!downloadRequest.isAllowedOverMobile()) {
                    callbackProgressTimes.setWifiRequired(true);
                }
                Map headers = downloadRequest.getHeaders();
                if (headers != null) {
                    for (Entry entry : headers.entrySet()) {
                        callbackProgressTimes.addHeader((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                callbackProgressTimes.setSyncCallback(true);
                callbackProgressTimes.setListener(this.downloadListener);
                long start = (long) callbackProgressTimes.start();
                if (this.storeManager.updateDownloadId(downloadRequest.getKey(), Long.valueOf(start))) {
                    i++;
                    this.currentDownloadsQueued.add(Long.valueOf(start));
                } else {
                    com.brightcove.player.logging.Log.v(TAG, "Download request #%d was removed while queuing", downloadRequest.getKey());
                    callbackProgressTimes.pause();
                }
            }
        }
        return i;
    }

    /* access modifiers changed from: private */
    public void removeDownloadRequestFromQueue(Collection<DownloadRequest> collection, boolean z) {
        FileDownloader impl = FileDownloader.getImpl();
        this.currentDownloadsQueued.clear();
        for (DownloadRequest downloadRequest : collection) {
            long j = Convert.toLong(downloadRequest.getDownloadId());
            String replace = Convert.toString(downloadRequest.getLocalUri()).replace("file://", "");
            int i = (int) j;
            byte status = impl.getStatus(i, replace);
            if (!(status == 0 || status == -3)) {
                impl.clear(i, replace);
                if (z) {
                    this.storeManager.updateDownloadRequestStatusByDownloadId(Long.valueOf(j), -1, 4, 0, 0, false);
                }
            }
        }
        queueNextBatch();
    }

    /* access modifiers changed from: private */
    public void cancelStatusNotification(long j) {
        ((NotificationManager) getContext().getSystemService("notification")).cancel((int) (j + 1000));
    }

    /* access modifiers changed from: private */
    public void onDownloadChanged(long j, @NonNull DownloadStatus downloadStatus) {
        for (Entry entry : getListeners().entrySet()) {
            if (((Long) entry.getValue()).longValue() == j) {
                Listener listener = (Listener) entry.getKey();
                if (listener != null) {
                    listener.onChanged(downloadStatus);
                }
            }
        }
    }

    private void onDownloadCompleted(long j, @NonNull DownloadStatus downloadStatus) {
        for (Entry entry : getListeners().entrySet()) {
            if (((Long) entry.getValue()).longValue() == j) {
                Listener listener = (Listener) entry.getKey();
                if (listener != null) {
                    listener.onCompleted(downloadStatus);
                }
            }
        }
    }

    private void onDownloadCancelled(long j) {
        for (Entry entry : getListeners().entrySet()) {
            if (((Long) entry.getValue()).longValue() == j) {
                Listener listener = (Listener) entry.getKey();
                if (listener != null) {
                    listener.onCancelled();
                }
            }
        }
    }

    private void onDownloadDeleted(long j) {
        for (Entry entry : getListeners().entrySet()) {
            if (((Long) entry.getValue()).longValue() == j) {
                Listener listener = (Listener) entry.getKey();
                if (listener != null) {
                    listener.onDeleted();
                }
            }
        }
    }

    private void onDownloadFailed(long j, @NonNull DownloadStatus downloadStatus) {
        for (Entry entry : getListeners().entrySet()) {
            if (((Long) entry.getValue()).longValue() == j) {
                Listener listener = (Listener) entry.getKey();
                if (listener != null) {
                    listener.onFailed(downloadStatus);
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void postStatusNotification(@NonNull DownloadRequestSet downloadRequestSet, @NonNull DownloadStatus downloadStatus) {
        Context context = getContext();
        int code = downloadStatus.getCode();
        int notificationVisibility = downloadRequestSet.getNotificationVisibility();
        if (notificationVisibility != 2 && (notificationVisibility != 3 || code == 8)) {
            Resources resources = context.getResources();
            Builder contentText = new Builder(context).setContentTitle(downloadRequestSet.getTitle()).setSmallIcon(17301634).setContentText(resources.getString(downloadStatus.getStatusMessage()));
            if (VERSION.SDK_INT >= 21) {
                contentText.setCategory("progress");
            }
            boolean z = false;
            boolean z2 = code == 2 || code == -1 || code == 1;
            if (z2) {
                contentText.setOngoing(true).setSmallIcon(17301633);
                double progress = downloadStatus.getProgress();
                int i = (int) progress;
                if (Double.isNaN(progress) || progress > 100.0d) {
                    z = true;
                }
                contentText.setProgress(100, i, z);
            } else if (code == 4 || code == 16) {
                contentText.setSubText(resources.getString(downloadStatus.getReasonMessage()));
            }
            if (VERSION.SDK_INT >= 26) {
                contentText.setChannelId(context.getString(R.string.offline_playback_notification_channel_id));
            }
            int longValue = (int) (downloadRequestSet.getKey().longValue() + 1000);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (!z2) {
                notificationManager.cancel(longValue);
            }
            notificationManager.notify(longValue, contentText.build());
        }
    }

    @TargetApi(26)
    private void createNotificationChannel() {
        Context context = getContext();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(new NotificationChannel(context.getString(R.string.offline_playback_notification_channel_id), context.getString(R.string.offline_playback_notification_channel_name), 2));
        }
    }
}

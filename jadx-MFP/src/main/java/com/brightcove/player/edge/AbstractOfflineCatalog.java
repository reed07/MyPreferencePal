package com.brightcove.player.edge;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import com.brightcove.player.analytics.Analytics.Fields;
import com.brightcove.player.data.Optional;
import com.brightcove.player.drm.CustomerRightsToken;
import com.brightcove.player.drm.CustomerRightsTokenConfig;
import com.brightcove.player.drm.LicenseManager;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventEmitterImpl;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.exception.EntityNotFoundException;
import com.brightcove.player.exception.InvalidDownloadPathException;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;
import com.brightcove.player.network.DownloadStatus;
import com.brightcove.player.offline.DownloadFileCreator;
import com.brightcove.player.offline.ExternalFileCreator;
import com.brightcove.player.offline.MediaDownloadable;
import com.brightcove.player.offline.MediaDownloadable.DownloadEventListener;
import com.brightcove.player.offline.MediaDownloadable.MediaFormatListener;
import com.brightcove.player.offline.MediaDownloadable.OnVideoSizeCallback;
import com.brightcove.player.offline.RequestConfig;
import com.brightcove.player.store.OfflineVideo;
import com.brightcove.player.util.FileUtil;
import io.reactivex.Observable;
import io.reactivex.Scheduler.Worker;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class AbstractOfflineCatalog extends Catalog {
    /* access modifiers changed from: private */
    public static final String TAG = "AbstractOfflineCatalog";
    private static final int TIMEOUT = 30;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final DownloadEventListener downloadEventListener;
    private final Set<DownloadEventListener> downloadEventListenerSet;
    @NonNull
    private DownloadFileCreator downloadFileCreator;
    private final HandlerThread fallbackHandlerThread;
    /* access modifiers changed from: private */
    public final Handler fallbackOfflineHandler;
    /* access modifiers changed from: private */
    public final Map<String, MediaDownloadable> mediaDownloadableCache;
    private final HandlerThread offlineHandlerThread;
    /* access modifiers changed from: private */
    public final RequestConfig requestConfig;
    /* access modifiers changed from: private */
    public final OfflineStoreManager storeManager;

    private abstract class LicenseOperation implements Runnable {
        private final EventEmitter emitter;
        private final Map<String, Object> properties = new HashMap();
        Source source;
        private final String successEvent;
        final Video video;

        /* access modifiers changed from: protected */
        @Nullable
        public abstract byte[] submitRequest(@NonNull LicenseManager licenseManager) throws Exception;

        public LicenseOperation(String str, Video video2, EventListener eventListener) {
            this.successEvent = str;
            this.video = video2;
            this.emitter = new EventEmitterImpl();
            this.emitter.on(EventType.ANY, eventListener);
            this.properties.put("video", video2);
        }

        private void emitEvent(@NonNull String str) {
            this.emitter.emit(str, this.properties);
        }

        /* JADX WARNING: Removed duplicated region for block: B:39:0x00af  */
        /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
        @com.brightcove.player.model.Video.CanSetLicenseExpiryDate
        @com.brightcove.player.model.Video.CanSetLicenseKeySetId
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r10 = this;
                com.brightcove.player.model.Video r0 = r10.video
                boolean r0 = r0.isOfflinePlaybackAllowed()
                if (r0 == 0) goto L_0x00b3
                com.brightcove.player.model.Video r0 = r10.video
                com.brightcove.player.model.DeliveryType r1 = com.brightcove.player.model.DeliveryType.DASH
                com.brightcove.player.model.Source r0 = r0.findHighQualitySource(r1)
                r10.source = r0
                com.brightcove.player.model.Source r0 = r10.source
                if (r0 != 0) goto L_0x0021
                com.brightcove.player.event.EventEmitter r0 = r10.emitter
                java.lang.String r1 = "odrmSourceNotFound"
                java.util.Map<java.lang.String, java.lang.Object> r2 = r10.properties
                r0.emit(r1, r2)
                goto L_0x00b8
            L_0x0021:
                r0 = 0
                com.brightcove.player.OfflinePlaybackPlugin r1 = com.brightcove.player.OfflinePlaybackPlugin.getInstance()     // Catch:{ Exception -> 0x0096, all -> 0x0091 }
                com.brightcove.player.model.Video r2 = r10.video     // Catch:{ Exception -> 0x0096, all -> 0x0091 }
                com.brightcove.player.model.Source r3 = r10.source     // Catch:{ Exception -> 0x0096, all -> 0x0091 }
                com.brightcove.player.drm.LicenseManager r1 = r1.createLicenseManager(r2, r3)     // Catch:{ Exception -> 0x0096, all -> 0x0091 }
                byte[] r2 = r10.submitRequest(r1)     // Catch:{ Exception -> 0x008f }
                java.lang.String r3 = "odrmLicenseReleased"
                java.lang.String r4 = r10.successEvent     // Catch:{ Exception -> 0x008f }
                boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x008f }
                r4 = 1
                r5 = 0
                if (r3 == 0) goto L_0x0043
                if (r2 != 0) goto L_0x0041
                goto L_0x0047
            L_0x0041:
                r4 = 0
                goto L_0x0047
            L_0x0043:
                if (r2 == 0) goto L_0x0046
                goto L_0x0047
            L_0x0046:
                r4 = 0
            L_0x0047:
                if (r4 == 0) goto L_0x0082
                com.brightcove.player.model.Video r3 = r10.video     // Catch:{ Exception -> 0x008f }
                r3.setOfflinePlaybackLicenseKey(r2)     // Catch:{ Exception -> 0x008f }
                if (r2 != 0) goto L_0x0056
                com.brightcove.player.model.Video r2 = r10.video     // Catch:{ Exception -> 0x008f }
                r2.setLicenseExpiryDate(r0)     // Catch:{ Exception -> 0x008f }
                goto L_0x0077
            L_0x0056:
                android.util.Pair r0 = r1.getRemainingLicenseDuration(r2)     // Catch:{ Exception -> 0x008f }
                java.util.Date r2 = new java.util.Date     // Catch:{ Exception -> 0x008f }
                long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x008f }
                java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x008f }
                java.lang.Object r0 = r0.first     // Catch:{ Exception -> 0x008f }
                java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ Exception -> 0x008f }
                long r7 = r0.longValue()     // Catch:{ Exception -> 0x008f }
                long r7 = r3.toMillis(r7)     // Catch:{ Exception -> 0x008f }
                long r5 = r5 + r7
                r2.<init>(r5)     // Catch:{ Exception -> 0x008f }
                com.brightcove.player.model.Video r0 = r10.video     // Catch:{ Exception -> 0x008f }
                r0.setLicenseExpiryDate(r2)     // Catch:{ Exception -> 0x008f }
            L_0x0077:
                com.brightcove.player.edge.AbstractOfflineCatalog r0 = com.brightcove.player.edge.AbstractOfflineCatalog.this     // Catch:{ Exception -> 0x008f }
                com.brightcove.player.edge.OfflineStoreManager r0 = r0.storeManager     // Catch:{ Exception -> 0x008f }
                com.brightcove.player.model.Video r2 = r10.video     // Catch:{ Exception -> 0x008f }
                r0.saveOfflineLicense(r2)     // Catch:{ Exception -> 0x008f }
            L_0x0082:
                if (r4 == 0) goto L_0x0087
                java.lang.String r0 = r10.successEvent     // Catch:{ Exception -> 0x008f }
                goto L_0x0089
            L_0x0087:
                java.lang.String r0 = "odrmLicenseError"
            L_0x0089:
                r10.emitEvent(r0)     // Catch:{ Exception -> 0x008f }
                if (r1 == 0) goto L_0x00b8
                goto L_0x00a8
            L_0x008f:
                r0 = move-exception
                goto L_0x009a
            L_0x0091:
                r1 = move-exception
                r9 = r1
                r1 = r0
                r0 = r9
                goto L_0x00ad
            L_0x0096:
                r1 = move-exception
                r9 = r1
                r1 = r0
                r0 = r9
            L_0x009a:
                java.util.Map<java.lang.String, java.lang.Object> r2 = r10.properties     // Catch:{ all -> 0x00ac }
                java.lang.String r3 = "error"
                r2.put(r3, r0)     // Catch:{ all -> 0x00ac }
                java.lang.String r0 = "odrmLicenseError"
                r10.emitEvent(r0)     // Catch:{ all -> 0x00ac }
                if (r1 == 0) goto L_0x00b8
            L_0x00a8:
                r1.releaseResources()
                goto L_0x00b8
            L_0x00ac:
                r0 = move-exception
            L_0x00ad:
                if (r1 == 0) goto L_0x00b2
                r1.releaseResources()
            L_0x00b2:
                throw r0
            L_0x00b3:
                java.lang.String r0 = "odrmPlaybackNotAllowed"
                r10.emitEvent(r0)
            L_0x00b8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.edge.AbstractOfflineCatalog.LicenseOperation.run():void");
        }
    }

    private class NewLicenseOperation extends LicenseOperation {
        private final CustomerRightsToken customerRightsToken;

        public NewLicenseOperation(Video video, CustomerRightsToken customerRightsToken2, @NonNull EventListener eventListener) {
            super(EventType.ODRM_LICENSE_ACQUIRED, video, eventListener);
            this.customerRightsToken = customerRightsToken2;
        }

        /* access modifiers changed from: protected */
        @Nullable
        public byte[] submitRequest(@NonNull LicenseManager licenseManager) throws Exception {
            return licenseManager.downloadLicense(this.source.getUrl(), this.customerRightsToken);
        }
    }

    private class ReleaseLicenseOperation extends LicenseOperation {
        public ReleaseLicenseOperation(Video video, EventListener eventListener) {
            super(EventType.ODRM_LICENSE_RELEASED, video, eventListener);
        }

        /* access modifiers changed from: protected */
        @Nullable
        public byte[] submitRequest(@NonNull LicenseManager licenseManager) throws Exception {
            licenseManager.releaseLicense(this.video.getOfflinePlaybackLicenseKey());
            return null;
        }
    }

    private class RenewLicenseOperation extends LicenseOperation {
        public RenewLicenseOperation(Video video, EventListener eventListener) {
            super(EventType.ODRM_LICENSE_RENEWED, video, eventListener);
        }

        /* access modifiers changed from: protected */
        @Nullable
        public byte[] submitRequest(@NonNull LicenseManager licenseManager) throws Exception {
            return licenseManager.renewLicense(this.video.getOfflinePlaybackLicenseKey());
        }
    }

    @Deprecated
    public void renewLicense(@NonNull Video video, @NonNull EventListener eventListener) {
    }

    public AbstractOfflineCatalog(Context context2, EventEmitter eventEmitter, String str, String str2, String str3) {
        this(context2, eventEmitter, str, str2, str3, new ExternalFileCreator());
    }

    public AbstractOfflineCatalog(Context context2, EventEmitter eventEmitter, String str, String str2, String str3, @NonNull DownloadFileCreator downloadFileCreator2) {
        super(eventEmitter, str, str2, str3);
        this.mediaDownloadableCache = new ConcurrentHashMap();
        this.offlineHandlerThread = new HandlerThread("OfflinePlaybackBackground");
        this.fallbackHandlerThread = new HandlerThread("fallback");
        this.requestConfig = new RequestConfig();
        this.downloadEventListenerSet = new HashSet();
        this.downloadEventListener = new DownloadEventListener() {
            public void onDownloadRequested(@NonNull final Video video) {
                AbstractOfflineCatalog.this.getDownloadEventListeners().subscribe((Consumer<? super T>) new Consumer<DownloadEventListener>() {
                    public void accept(DownloadEventListener downloadEventListener) throws Exception {
                        downloadEventListener.onDownloadRequested(video);
                    }
                });
            }

            public void onDownloadStarted(@NonNull Video video, long j, @NonNull Map<String, Serializable> map) {
                AbstractOfflineCatalog.this.reportDownloadStarted(video, map);
                Observable access$000 = AbstractOfflineCatalog.this.getDownloadEventListeners();
                final Video video2 = video;
                final long j2 = j;
                final Map<String, Serializable> map2 = map;
                AnonymousClass2 r1 = new Consumer<DownloadEventListener>() {
                    public void accept(DownloadEventListener downloadEventListener) throws Exception {
                        downloadEventListener.onDownloadStarted(video2, j2, map2);
                    }
                };
                access$000.subscribe((Consumer<? super T>) r1);
            }

            public void onDownloadPaused(@NonNull final Video video, @NonNull final DownloadStatus downloadStatus) {
                AbstractOfflineCatalog.this.getDownloadEventListeners().subscribe((Consumer<? super T>) new Consumer<DownloadEventListener>() {
                    public void accept(DownloadEventListener downloadEventListener) throws Exception {
                        downloadEventListener.onDownloadPaused(video, downloadStatus);
                    }
                });
            }

            public void onDownloadProgress(@NonNull final Video video, @NonNull final DownloadStatus downloadStatus) {
                AbstractOfflineCatalog.this.getDownloadEventListeners().subscribe((Consumer<? super T>) new Consumer<DownloadEventListener>() {
                    public void accept(DownloadEventListener downloadEventListener) throws Exception {
                        downloadEventListener.onDownloadProgress(video, downloadStatus);
                    }
                });
            }

            public void onDownloadCompleted(@NonNull final Video video, @NonNull final DownloadStatus downloadStatus) {
                AbstractOfflineCatalog.this.mediaDownloadableCache.remove(video.getId());
                final Worker createWorker = Schedulers.io().createWorker();
                createWorker.schedule(new Runnable() {
                    public void run() {
                        AbstractOfflineCatalog.this.reportDownloadCompleted(video, downloadStatus);
                        AbstractOfflineCatalog.this.getDownloadEventListeners().subscribe((Consumer<? super T>) new Consumer<DownloadEventListener>() {
                            public void accept(DownloadEventListener downloadEventListener) throws Exception {
                                downloadEventListener.onDownloadCompleted(video, downloadStatus);
                            }
                        });
                        createWorker.dispose();
                    }
                });
            }

            public void onDownloadCanceled(@NonNull final Video video) {
                AbstractOfflineCatalog.this.mediaDownloadableCache.remove(video.getId());
                final Worker createWorker = Schedulers.io().createWorker();
                createWorker.schedule(new Runnable() {
                    public void run() {
                        AbstractOfflineCatalog.this.storeManager.updateDownloadRequestIdList(video.getId(), null, 0);
                        AbstractOfflineCatalog.this.reportDownloadCancelled(video);
                        AbstractOfflineCatalog.this.getDownloadEventListeners().subscribe((Consumer<? super T>) new Consumer<DownloadEventListener>() {
                            public void accept(DownloadEventListener downloadEventListener) throws Exception {
                                downloadEventListener.onDownloadCanceled(video);
                            }
                        });
                        createWorker.dispose();
                    }
                });
            }

            public void onDownloadDeleted(@NonNull final Video video) {
                AbstractOfflineCatalog.this.mediaDownloadableCache.remove(video.getId());
                AbstractOfflineCatalog.this.getDownloadEventListeners().subscribe((Consumer<? super T>) new Consumer<DownloadEventListener>() {
                    public void accept(DownloadEventListener downloadEventListener) throws Exception {
                        downloadEventListener.onDownloadDeleted(video);
                    }
                });
            }

            public void onDownloadFailed(@NonNull final Video video, @NonNull final DownloadStatus downloadStatus) {
                AbstractOfflineCatalog.this.mediaDownloadableCache.remove(video.getId());
                AbstractOfflineCatalog.this.reportDownloadFailed(video, downloadStatus);
                AbstractOfflineCatalog.this.getDownloadEventListeners().subscribe((Consumer<? super T>) new Consumer<DownloadEventListener>() {
                    public void accept(DownloadEventListener downloadEventListener) throws Exception {
                        downloadEventListener.onDownloadFailed(video, downloadStatus);
                    }
                });
            }
        };
        this.context = context2.getApplicationContext();
        this.downloadFileCreator = downloadFileCreator2;
        this.storeManager = OfflineStoreManager.getInstance(context2);
        this.offlineHandlerThread.start();
        this.fallbackHandlerThread.start();
        this.fallbackOfflineHandler = new Handler(this.fallbackHandlerThread.getLooper());
        try {
            internalUpdateDownloadPath();
        } catch (InvalidDownloadPathException e) {
            Log.e(TAG, "Path set is invalid", e);
        }
    }

    @Nullable
    public File getDownloadPath() {
        return this.downloadFileCreator.getDownloadsFolder(this.context);
    }

    public void setDownloadPath(@NonNull final File file) throws InvalidDownloadPathException {
        setDownloadPath((DownloadFileCreator) new DownloadFileCreator() {
            public File getDownloadsFolder(@NonNull Context context) {
                return file;
            }
        });
    }

    public void setDownloadPath(@NonNull DownloadFileCreator downloadFileCreator2) throws InvalidDownloadPathException {
        this.downloadFileCreator = downloadFileCreator2;
        internalUpdateDownloadPath();
    }

    private void internalUpdateDownloadPath() throws InvalidDownloadPathException {
        File downloadsFolder = this.downloadFileCreator.getDownloadsFolder(this.context);
        if (downloadsFolder == null) {
            throw new InvalidDownloadPathException("null");
        } else if (isDownloadPathValid()) {
            this.requestConfig.setDownloadPath(downloadsFolder);
        } else {
            throw new InvalidDownloadPathException(downloadsFolder.getAbsolutePath());
        }
    }

    public boolean isVideoDownloadable(@Nullable Video video) {
        return video != null && video.isOfflinePlaybackAllowed() && isDownloadPathValid();
    }

    public boolean isDownloadPathValid() {
        return FileUtil.isFileValid(this.downloadFileCreator.getDownloadsFolder(this.context));
    }

    public boolean isMobileDownloadAllowed() {
        return this.requestConfig.isMobileDownloadAllowed();
    }

    public void setMobileDownloadAllowed(boolean z) {
        this.requestConfig.setMobileDownloadAllowed(z);
    }

    public boolean isRoamingDownloadAllowed() {
        return this.requestConfig.isRoamingDownloadAllowed();
    }

    public void setRoamingDownloadAllowed(boolean z) {
        this.requestConfig.setRoamingDownloadAllowed(z);
    }

    public boolean isMeteredDownloadAllowed() {
        return this.requestConfig.isMeteredDownloadAllowed();
    }

    public void setMeteredDownloadAllowed(boolean z) {
        this.requestConfig.setMeteredDownloadAllowed(z);
    }

    public void setVideoBitrate(int i) {
        this.requestConfig.setVideoBitrate(i);
    }

    /* access modifiers changed from: private */
    public Observable<DownloadEventListener> getDownloadEventListeners() {
        DownloadEventListener[] downloadEventListenerArr;
        synchronized (this.downloadEventListenerSet) {
            downloadEventListenerArr = (DownloadEventListener[]) this.downloadEventListenerSet.toArray(new DownloadEventListener[0]);
        }
        return Observable.fromArray(downloadEventListenerArr).observeOn(AndroidSchedulers.mainThread());
    }

    public void addDownloadEventListener(@NonNull DownloadEventListener downloadEventListener2) {
        synchronized (this.downloadEventListenerSet) {
            this.downloadEventListenerSet.add(downloadEventListener2);
        }
    }

    public void removeDownloadEventListener(@NonNull DownloadEventListener downloadEventListener2) {
        synchronized (this.downloadEventListenerSet) {
            this.downloadEventListenerSet.remove(downloadEventListener2);
        }
    }

    public long estimateSize(Video video) {
        MediaDownloadable createMediaDownloadable = createMediaDownloadable(video, false);
        if (createMediaDownloadable == null) {
            return 0;
        }
        return createMediaDownloadable.getEstimatedSize();
    }

    public void estimateSize(@NonNull final Video video, @NonNull final OnVideoSizeCallback onVideoSizeCallback) throws InvalidDownloadPathException {
        internalUpdateDownloadPath();
        Single.fromCallable(new Callable<Optional<MediaDownloadable>>() {
            public Optional<MediaDownloadable> call() {
                return Optional.from(AbstractOfflineCatalog.this.createMediaDownloadable(video, false));
            }
        }).flatMap(new Function<Optional<MediaDownloadable>, SingleSource<MediaDownloadable>>() {
            public SingleSource<MediaDownloadable> apply(Optional<MediaDownloadable> optional) {
                if (optional.isPresent()) {
                    return Single.just(optional.get());
                }
                return Single.error((Throwable) new EntityNotFoundException("Error creating media downloadable"));
            }
        }).map(new Function<MediaDownloadable, Long>() {
            /* access modifiers changed from: private */
            public CountDownLatch countDownLatch = new CountDownLatch(1);
            /* access modifiers changed from: private */
            public volatile Long mSize = Long.valueOf(0);

            public Long apply(final MediaDownloadable mediaDownloadable) throws Exception {
                AbstractOfflineCatalog.this.fallbackOfflineHandler.post(new Runnable() {
                    public void run() {
                        mediaDownloadable.estimatedSize(new OnVideoSizeCallback() {
                            public void onVideoSizeEstimated(long j) {
                                AnonymousClass4.this.mSize = Long.valueOf(j);
                                AnonymousClass4.this.countDownLatch.countDown();
                            }
                        });
                    }
                });
                this.countDownLatch.await(30, TimeUnit.SECONDS);
                return this.mSize;
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<Long>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(Long l) {
                onVideoSizeCallback.onVideoSizeEstimated(l.longValue());
            }

            public void onError(Throwable th) {
                onVideoSizeCallback.onVideoSizeEstimated(0);
            }
        });
    }

    public void getMediaFormatTracksAvailable(@NonNull final Video video, @NonNull final MediaFormatListener mediaFormatListener) throws InvalidDownloadPathException {
        internalUpdateDownloadPath();
        Single.fromCallable(new Callable<Optional<MediaDownloadable>>() {
            public Optional<MediaDownloadable> call() throws Exception {
                return Optional.from(AbstractOfflineCatalog.this.createMediaDownloadable(video, true));
            }
        }).flatMap(new Function<Optional<MediaDownloadable>, SingleSource<MediaDownloadable>>() {
            public SingleSource<MediaDownloadable> apply(Optional<MediaDownloadable> optional) throws Exception {
                if (optional.isPresent()) {
                    return Single.just(optional.get());
                }
                return Single.error((Throwable) new EntityNotFoundException("Error creating media downloadable"));
            }
        }).flatMap(new Function<MediaDownloadable, SingleSource<Pair<MediaDownloadable, Bundle>>>() {
            /* access modifiers changed from: private */
            public CountDownLatch countDownLatch = new CountDownLatch(1);
            /* access modifiers changed from: private */
            public volatile Pair<MediaDownloadable, Bundle> result;

            public SingleSource<Pair<MediaDownloadable, Bundle>> apply(final MediaDownloadable mediaDownloadable) throws Exception {
                AbstractOfflineCatalog.this.fallbackOfflineHandler.post(new Runnable() {
                    public void run() {
                        mediaDownloadable.getMediaFormatTracksAvailable(new MediaFormatListener() {
                            public void onResult(MediaDownloadable mediaDownloadable, Bundle bundle) {
                                AnonymousClass8.this.result = new Pair(mediaDownloadable, bundle);
                                AnonymousClass8.this.countDownLatch.countDown();
                            }
                        });
                    }
                });
                this.countDownLatch.await(30, TimeUnit.SECONDS);
                if (this.result != null) {
                    return Single.just(this.result);
                }
                return Single.error((Throwable) new Exception());
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<Pair<MediaDownloadable, Bundle>>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(Pair<MediaDownloadable, Bundle> pair) {
                mediaFormatListener.onResult((MediaDownloadable) pair.first, (Bundle) pair.second);
            }

            public void onError(Throwable th) {
                String access$1100 = AbstractOfflineCatalog.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Error getMediaFormatTracksAvailable: ");
                sb.append(th);
                Log.e(access$1100, sb.toString());
            }
        });
    }

    public DownloadStatus downloadVideo(@NonNull Video video) {
        DownloadStatus downloadStatus;
        File downloadsFolder = this.downloadFileCreator.getDownloadsFolder(this.context);
        if (downloadsFolder == null || !isVideoDownloadable(video)) {
            DownloadStatus createForInvalidDownloadsFile = DownloadStatus.createForInvalidDownloadsFile();
            this.downloadEventListener.onDownloadFailed(video, createForInvalidDownloadsFile);
            return createForInvalidDownloadsFile;
        }
        this.requestConfig.setDownloadPath(downloadsFolder);
        MediaDownloadable createMediaDownloadable = createMediaDownloadable(video, false);
        if (createMediaDownloadable != null) {
            downloadStatus = getVideoDownloadStatus(createMediaDownloadable);
            if (downloadStatus.getCode() == 0 && createMediaDownloadable.requestDownload()) {
                this.mediaDownloadableCache.put(video.getId(), createMediaDownloadable);
                downloadStatus = getVideoDownloadStatus(createMediaDownloadable);
            }
        } else {
            downloadStatus = new DownloadStatus();
        }
        return downloadStatus;
    }

    public void downloadVideo(@NonNull final Video video, @NonNull final OfflineCallback<DownloadStatus> offlineCallback) {
        Single.fromCallable(new Callable<DownloadStatus>() {
            public DownloadStatus call() {
                return AbstractOfflineCatalog.this.downloadVideo(video);
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<DownloadStatus>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(DownloadStatus downloadStatus) {
                offlineCallback.onSuccess(downloadStatus);
            }

            public void onError(Throwable th) {
                offlineCallback.onFailure(th);
            }
        });
    }

    public boolean cancelVideoDownload(@NonNull Video video) {
        return cancelVideoDownload(video.getId());
    }

    public void cancelVideoDownload(@NonNull Video video, @NonNull OfflineCallback<Boolean> offlineCallback) {
        cancelVideoDownload(video.getId(), offlineCallback);
    }

    public boolean cancelVideoDownload(@NonNull String str) {
        final MediaDownloadable createMediaDownloadable = createMediaDownloadable(str);
        return createMediaDownloadable != null && ((Boolean) Observable.fromCallable(new Callable<Boolean>() {
            public Boolean call() {
                return Boolean.valueOf(createMediaDownloadable.cancelDownload());
            }
        }).subscribeOn(Schedulers.io()).blockingSingle()).booleanValue();
    }

    public void cancelVideoDownload(@NonNull final String str, @NonNull final OfflineCallback<Boolean> offlineCallback) {
        Single.fromCallable(new Callable<Boolean>() {
            public Boolean call() {
                return Boolean.valueOf(AbstractOfflineCatalog.this.cancelVideoDownload(str));
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<Boolean>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(Boolean bool) {
                offlineCallback.onSuccess(bool);
            }

            public void onError(Throwable th) {
                offlineCallback.onFailure(th);
            }
        });
    }

    public int pauseVideoDownload(Video video) {
        return pauseVideoDownload(video.getId());
    }

    public void pauseVideoDownload(@NonNull Video video, @NonNull OfflineCallback<Integer> offlineCallback) {
        pauseVideoDownload(video.getId(), offlineCallback);
    }

    public int pauseVideoDownload(@NonNull String str) {
        MediaDownloadable createMediaDownloadable = createMediaDownloadable(str);
        if (createMediaDownloadable != null) {
            return createMediaDownloadable.pauseDownload();
        }
        return 0;
    }

    public void pauseVideoDownload(@NonNull final String str, @NonNull final OfflineCallback<Integer> offlineCallback) {
        Single.fromCallable(new Callable<Integer>() {
            public Integer call() {
                return Integer.valueOf(AbstractOfflineCatalog.this.pauseVideoDownload(str));
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<Integer>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(Integer num) {
                offlineCallback.onSuccess(num);
            }

            public void onError(Throwable th) {
                offlineCallback.onFailure(th);
            }
        });
    }

    public int resumeVideoDownload(@NonNull Video video) {
        return resumeVideoDownload(video.getId());
    }

    public void resumeVideoDownload(@NonNull Video video, @NonNull OfflineCallback<Integer> offlineCallback) {
        resumeVideoDownload(video.getId(), offlineCallback);
    }

    public int resumeVideoDownload(@NonNull String str) {
        MediaDownloadable createMediaDownloadable = createMediaDownloadable(str);
        if (createMediaDownloadable != null) {
            return createMediaDownloadable.resumeDownload();
        }
        return 0;
    }

    public void resumeVideoDownload(@NonNull final String str, @NonNull final OfflineCallback<Integer> offlineCallback) {
        Single.fromCallable(new Callable<Integer>() {
            public Integer call() {
                return Integer.valueOf(AbstractOfflineCatalog.this.resumeVideoDownload(str));
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<Integer>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(Integer num) {
                offlineCallback.onSuccess(num);
            }

            public void onError(Throwable th) {
                offlineCallback.onFailure(th);
            }
        });
    }

    public boolean deleteVideo(@NonNull Video video) {
        return deleteVideo(video.getId());
    }

    public void deleteVideo(@NonNull Video video, @NonNull OfflineCallback<Boolean> offlineCallback) {
        deleteVideo(video.getId(), offlineCallback);
    }

    public boolean deleteVideo(@NonNull final String str) {
        return ((Boolean) Observable.fromCallable(new Callable<Boolean>() {
            public Boolean call() {
                MediaDownloadable access$1400 = AbstractOfflineCatalog.this.createMediaDownloadable(str);
                return Boolean.valueOf(access$1400 != null && access$1400.deleteDownload());
            }
        }).subscribeOn(Schedulers.io()).blockingSingle()).booleanValue();
    }

    public void deleteVideo(@NonNull final String str, @NonNull final OfflineCallback<Boolean> offlineCallback) {
        Single.fromCallable(new Callable<Boolean>() {
            public Boolean call() {
                return Boolean.valueOf(AbstractOfflineCatalog.this.deleteVideo(str));
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<Boolean>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(Boolean bool) {
                offlineCallback.onSuccess(bool);
            }

            public void onError(Throwable th) {
                offlineCallback.onFailure(th);
            }
        });
    }

    public DownloadStatus getVideoDownloadStatus(@NonNull Video video) {
        return getVideoDownloadStatus(video.getId());
    }

    public void getVideoDownloadStatus(@NonNull Video video, @NonNull OfflineCallback<DownloadStatus> offlineCallback) {
        getVideoDownloadStatus(video.getId(), offlineCallback);
    }

    @NonNull
    public DownloadStatus getVideoDownloadStatus(@NonNull String str) {
        MediaDownloadable createMediaDownloadable = createMediaDownloadable(str);
        if (createMediaDownloadable == null) {
            return new DownloadStatus();
        }
        if (!this.mediaDownloadableCache.containsKey(str)) {
            this.mediaDownloadableCache.put(str, createMediaDownloadable);
        }
        return getVideoDownloadStatus(createMediaDownloadable);
    }

    public void getVideoDownloadStatus(@NonNull final String str, @NonNull final OfflineCallback<DownloadStatus> offlineCallback) {
        Single.fromCallable(new Callable<Optional<DownloadStatus>>() {
            public Optional<DownloadStatus> call() {
                return Optional.from(AbstractOfflineCatalog.this.getVideoDownloadStatus(str));
            }
        }).flatMap(new Function<Optional<DownloadStatus>, SingleSource<DownloadStatus>>() {
            public SingleSource<DownloadStatus> apply(Optional<DownloadStatus> optional) {
                if (optional.isPresent()) {
                    return Single.just(optional.get());
                }
                StringBuilder sb = new StringBuilder();
                sb.append("DownloadStatus from videoId[ ");
                sb.append(str);
                sb.append("] was not found");
                return Single.error((Throwable) new EntityNotFoundException(sb.toString()));
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<DownloadStatus>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(DownloadStatus downloadStatus) {
                offlineCallback.onSuccess(downloadStatus);
            }

            public void onError(Throwable th) {
                offlineCallback.onFailure(th);
            }
        });
    }

    @NonNull
    private DownloadStatus getVideoDownloadStatus(@NonNull final MediaDownloadable mediaDownloadable) {
        return (DownloadStatus) Observable.fromCallable(new Callable<DownloadStatus>() {
            public DownloadStatus call() {
                return mediaDownloadable.getDownloadStatus();
            }
        }).subscribeOn(Schedulers.io()).blockingSingle();
    }

    @NonNull
    public List<Video> findAllQueuedVideoDownload() {
        return OfflineStoreManager.toVideoList(this.storeManager.findAllOfflineVideo());
    }

    public void findAllQueuedVideoDownload(@NonNull final OfflineCallback<List<Video>> offlineCallback) {
        Single.fromCallable(new Callable<List<Video>>() {
            public List<Video> call() {
                return AbstractOfflineCatalog.this.findAllQueuedVideoDownload();
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<List<Video>>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(List<Video> list) {
                offlineCallback.onSuccess(list);
            }

            public void onError(Throwable th) {
                offlineCallback.onFailure(th);
            }
        });
    }

    @NonNull
    public List<Video> findAllVideoDownload(int i) {
        return OfflineStoreManager.toVideoList(this.storeManager.findAllOfflineVideo(i));
    }

    public void findAllVideoDownload(final int i, @NonNull final OfflineCallback<List<Video>> offlineCallback) {
        Single.fromCallable(new Callable<List<Video>>() {
            public List<Video> call() {
                return AbstractOfflineCatalog.this.findAllVideoDownload(i);
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<List<Video>>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(List<Video> list) {
                offlineCallback.onSuccess(list);
            }

            public void onError(Throwable th) {
                offlineCallback.onFailure(th);
            }
        });
    }

    @Nullable
    public Video findOfflineVideoById(String str) {
        OfflineVideo findOfflineVideo = this.storeManager.findOfflineVideo(str);
        if (findOfflineVideo == null) {
            return null;
        }
        return findOfflineVideo.getVideo();
    }

    public void findOfflineVideoById(final String str, @NonNull final OfflineCallback<Video> offlineCallback) {
        Single.fromCallable(new Callable<Optional<Video>>() {
            public Optional<Video> call() {
                return Optional.from(AbstractOfflineCatalog.this.findOfflineVideoById(str));
            }
        }).subscribeOn(AndroidSchedulers.from(this.offlineHandlerThread.getLooper())).observeOn(AndroidSchedulers.mainThread()).subscribe((SingleObserver<? super T>) new SingleObserver<Optional<Video>>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onSuccess(Optional<Video> optional) {
                offlineCallback.onSuccess(optional.isPresent() ? (Video) optional.get() : null);
            }

            public void onError(Throwable th) {
                offlineCallback.onFailure(th);
            }
        });
    }

    /* access modifiers changed from: private */
    public void reportDownloadStarted(@NonNull Video video, @NonNull Map<String, Serializable> map) {
        HashMap hashMap = new HashMap();
        map.put("video", video);
        hashMap.put(Fields.DOWNLOAD_ID, video.getDownloadId());
        hashMap.put(Fields.DOWNLOAD_REQUEST_TIME, Long.valueOf(System.currentTimeMillis()));
        for (Entry entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        this.eventEmitter.emit(EventType.VIDEO_DOWNLOAD_STARTED, hashMap);
    }

    /* access modifiers changed from: private */
    public void reportDownloadCancelled(@NonNull Video video) {
        HashMap hashMap = new HashMap();
        hashMap.put("video", video);
        hashMap.put(Fields.DOWNLOAD_ID, video.getDownloadId());
        hashMap.put(Fields.DOWNLOAD_CANCEL_TIME, Long.valueOf(System.currentTimeMillis()));
        this.eventEmitter.emit(EventType.VIDEO_DOWNLOAD_CANCELLED, hashMap);
    }

    /* access modifiers changed from: private */
    public void reportDownloadCompleted(@NonNull Video video, @NonNull DownloadStatus downloadStatus) {
        HashMap hashMap = new HashMap();
        hashMap.put("video", video);
        hashMap.put(Fields.DOWNLOAD_ID, video.getDownloadId());
        hashMap.put(Fields.DOWNLOAD_COMPLETION_TIME, Long.valueOf(downloadStatus.getTime()));
        hashMap.put(Fields.DOWNLOAD_SIZE, Long.valueOf(downloadStatus.getActualSize()));
        this.eventEmitter.emit(EventType.VIDEO_DOWNLOAD_COMPLETED, hashMap);
    }

    /* access modifiers changed from: private */
    public void reportDownloadFailed(@NonNull Video video, @NonNull DownloadStatus downloadStatus) {
        HashMap hashMap = new HashMap();
        hashMap.put("video", video);
        hashMap.put(Fields.DOWNLOAD_ID, video.getDownloadId());
        hashMap.put(Fields.DOWNLOAD_FAILURE_TIME, Long.valueOf(downloadStatus.getTime()));
        hashMap.put("errorCode", Integer.valueOf(downloadStatus.getReason()));
        this.eventEmitter.emit(EventType.VIDEO_DOWNLOAD_FAILED, hashMap);
    }

    /* access modifiers changed from: private */
    @Nullable
    public MediaDownloadable createMediaDownloadable(final String str) {
        MediaDownloadable mediaDownloadable = (MediaDownloadable) this.mediaDownloadableCache.get(str);
        if (mediaDownloadable != null) {
            return mediaDownloadable;
        }
        Optional optional = (Optional) Observable.fromCallable(new Callable<Optional<MediaDownloadable>>() {
            public Optional<MediaDownloadable> call() throws Exception {
                Video video;
                OfflineVideo findOfflineVideo = AbstractOfflineCatalog.this.storeManager.findOfflineVideo(str);
                if (findOfflineVideo == null) {
                    video = null;
                } else {
                    video = findOfflineVideo.getVideo();
                }
                if (video == null) {
                    return Optional.empty();
                }
                return new Optional<>(MediaDownloadable.create(AbstractOfflineCatalog.this.context, video, AbstractOfflineCatalog.this.downloadEventListener, AbstractOfflineCatalog.this.requestConfig.copy()));
            }
        }).subscribeOn(Schedulers.io()).blockingSingle();
        return optional.isPresent() ? (MediaDownloadable) optional.get() : null;
    }

    /* access modifiers changed from: private */
    @Nullable
    public MediaDownloadable createMediaDownloadable(@NonNull Video video, boolean z) {
        String id = video.getId();
        MediaDownloadable createMediaDownloadable = createMediaDownloadable(id);
        if (createMediaDownloadable == null) {
            createMediaDownloadable = MediaDownloadable.create(this.context, video, this.downloadEventListener, this.requestConfig.copy());
            if (createMediaDownloadable != null && z) {
                this.mediaDownloadableCache.put(id, createMediaDownloadable);
            }
        }
        return createMediaDownloadable;
    }

    public void requestRentalLicense(@NonNull Video video, @NonNull Date date, long j, @NonNull EventListener eventListener) {
        new Thread(new NewLicenseOperation(video, CustomerRightsToken.createRentalRightsToken(date, Long.valueOf(j)), eventListener)).start();
    }

    public void requestRentalLicense(@NonNull Video video, @NonNull Date date, long j, @NonNull EventListener eventListener, @NonNull CustomerRightsTokenConfig customerRightsTokenConfig) {
        new Thread(new NewLicenseOperation(video, CustomerRightsToken.createRentalRightsToken(date, Long.valueOf(j), customerRightsTokenConfig), eventListener)).start();
    }

    public void requestPurchaseLicense(@NonNull Video video, @NonNull EventListener eventListener) {
        new Thread(new NewLicenseOperation(video, CustomerRightsToken.createPurchaseRightsToken(), eventListener)).start();
    }

    public void requestPurchaseLicense(@NonNull Video video, @NonNull EventListener eventListener, @NonNull CustomerRightsTokenConfig customerRightsTokenConfig) {
        new Thread(new NewLicenseOperation(video, CustomerRightsToken.createPurchaseRightsToken(customerRightsTokenConfig), eventListener)).start();
    }

    public void releaseLicense(@NonNull Video video, @NonNull EventListener eventListener) {
        new Thread(new ReleaseLicenseOperation(video, eventListener)).start();
    }

    public void terminate() {
        if (VERSION.SDK_INT >= 18) {
            this.fallbackHandlerThread.quitSafely();
            this.offlineHandlerThread.quitSafely();
            return;
        }
        this.fallbackHandlerThread.quit();
        this.offlineHandlerThread.quit();
    }
}

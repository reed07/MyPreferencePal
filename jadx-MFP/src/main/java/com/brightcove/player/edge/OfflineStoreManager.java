package com.brightcove.player.edge;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.logging.Log;
import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.CanSetDownloadIdentifier;
import com.brightcove.player.model.Video.CanSetLicenseExpiryDate;
import com.brightcove.player.model.Video.CanSetLicenseKeySetId;
import com.brightcove.player.network.DownloadStatus;
import com.brightcove.player.network.IDownloadManager.IRequest;
import com.brightcove.player.offline.RequestConfig;
import com.brightcove.player.store.BaseStore;
import com.brightcove.player.store.DownloadRequest;
import com.brightcove.player.store.DownloadRequestSet;
import com.brightcove.player.store.IdentifiableEntity;
import com.brightcove.player.store.Models;
import com.brightcove.player.store.OfflineVideo;
import com.brightcove.player.util.Convert;
import com.brightcove.player.util.ReflectionUtil;
import io.requery.BlockingEntityStore;
import io.requery.Persistable;
import io.requery.PersistenceException;
import io.requery.TransactionIsolation;
import io.requery.meta.QueryAttribute;
import io.requery.query.Condition;
import io.requery.query.DistinctSelection;
import io.requery.query.Expression;
import io.requery.query.Limit;
import io.requery.query.LogicalCondition;
import io.requery.query.Result;
import io.requery.query.Scalar;
import io.requery.query.Tuple;
import io.requery.query.Update;
import io.requery.query.WhereAndOr;
import io.requery.reactivex.ReactiveResult;
import io.requery.reactivex.ReactiveScalar;
import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

public class OfflineStoreManager extends BaseStore {
    private static volatile OfflineStoreManager INSTANCE = null;
    private static final int STORE_VERSION = 1;
    /* access modifiers changed from: private */
    public static final String TAG = "OfflineStoreManager";
    private static final String TOTAL_BYTES_DOWNLOADED = "totalBytesDownloaded";
    private static final String TOTAL_SIZE = "totalSize";

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CanChangeDownloadIdentifier {
    }

    public static OfflineStoreManager getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            synchronized (OfflineStoreManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OfflineStoreManager(context);
                }
            }
        }
        return INSTANCE;
    }

    private OfflineStoreManager(@NonNull Context context) {
        super(context, Models.DEFAULT, getDefaultDatabaseName(context, Models.DEFAULT), 1);
    }

    @Nullable
    public <E extends IdentifiableEntity> E refreshEntity(@Nullable E e) {
        if (e == null) {
            return null;
        }
        return (IdentifiableEntity) ((Result) this.dataStore.toBlocking().select(e.getClass(), new QueryAttribute[0]).where(e.getIdentityCondition()).get()).firstOrNull();
    }

    public <T extends IdentifiableEntity> T saveEntity(@NonNull T t) {
        if (t.getKey() == null) {
            return (IdentifiableEntity) this.dataStore.toBlocking().insert(t);
        }
        return (IdentifiableEntity) this.dataStore.toBlocking().update(t);
    }

    @Nullable
    public OfflineVideo updateDownloadRequestIdList(@NonNull String str, @Nullable List<Long> list, long j) {
        final OfflineVideo findOfflineVideo = findOfflineVideo(str);
        if (findOfflineVideo == null) {
            return findOfflineVideo;
        }
        BlockingEntityStore blocking = this.dataStore.toBlocking();
        final List<Long> list2 = list;
        final long j2 = j;
        final BlockingEntityStore blockingEntityStore = blocking;
        AnonymousClass1 r0 = new Callable<OfflineVideo>() {
            public OfflineVideo call() throws Exception {
                try {
                    Collection findDownloadRequestSets = OfflineStoreManager.this.findDownloadRequestSets(Convert.toLongArray((Collection<? extends Number>) list2));
                    DownloadRequestSet downloadRequestSet = findDownloadRequestSets.isEmpty() ? null : (DownloadRequestSet) findDownloadRequestSets.iterator().next();
                    if (downloadRequestSet != null) {
                        downloadRequestSet.setEstimatedSize(j2);
                        downloadRequestSet = (DownloadRequestSet) blockingEntityStore.update(downloadRequestSet);
                    }
                    findOfflineVideo.setDownloadRequestSet(downloadRequestSet);
                    return (OfflineVideo) blockingEntityStore.update(findOfflineVideo);
                } catch (PersistenceException e) {
                    Log.v(OfflineStoreManager.TAG, "Failed to update download request list, Perhaps the request has been cancelled or deleted already!", e, new Object[0]);
                    return null;
                }
            }
        };
        return (OfflineVideo) blocking.runInTransaction(r0, TransactionIsolation.SERIALIZABLE);
    }

    @CanSetDownloadIdentifier
    public OfflineVideo saveOfflineVideo(@NonNull Video video, @NonNull File file, @NonNull DownloadRequestSet downloadRequestSet) {
        String id = video.getId();
        if (!TextUtils.isEmpty(id)) {
            OfflineVideo findOfflineVideo = findOfflineVideo(id);
            if (findOfflineVideo == null) {
                findOfflineVideo = new OfflineVideo();
                findOfflineVideo.setVideoId(id);
                findOfflineVideo.setVideo(video);
            } else {
                if (TextUtils.isEmpty(findOfflineVideo.getVideoId())) {
                    findOfflineVideo.setVideoId(id);
                }
                if (findOfflineVideo.getVideo() == null) {
                    findOfflineVideo.setVideo(video);
                }
            }
            findOfflineVideo.setVideo(video);
            findOfflineVideo.setDownloadDirectory(file);
            findOfflineVideo.setDownloadRequestSet(downloadRequestSet);
            return (OfflineVideo) saveEntity(findOfflineVideo);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Video id[");
        sb.append(id);
        sb.append("] is invalid");
        throw new IllegalArgumentException(sb.toString());
    }

    @CanSetLicenseExpiryDate
    @CanSetLicenseKeySetId
    @NonNull
    public OfflineVideo saveOfflineVideo(Video video) {
        String id = video.getId();
        OfflineVideo findOfflineVideo = findOfflineVideo(id);
        if (findOfflineVideo == null) {
            findOfflineVideo = new OfflineVideo();
            findOfflineVideo.setDownloadRequestSet(null);
            findOfflineVideo.setVideoId(id);
        } else {
            Video video2 = findOfflineVideo.getVideo();
            if (video2 != null) {
                video.setOfflinePlaybackLicenseKey(video2.getOfflinePlaybackLicenseKey());
                video.setLicenseExpiryDate(video2.getLicenseExpiryDate());
            }
        }
        findOfflineVideo.setVideo(video);
        return (OfflineVideo) saveEntity(findOfflineVideo);
    }

    @CanSetLicenseExpiryDate
    @CanSetLicenseKeySetId
    @NonNull
    public OfflineVideo saveOfflineLicense(@NonNull Video video) {
        String id = video.getId();
        OfflineVideo findOfflineVideo = findOfflineVideo(id);
        if (findOfflineVideo == null) {
            findOfflineVideo = new OfflineVideo();
            findOfflineVideo.setDownloadRequestSet(null);
            findOfflineVideo.setVideoId(id);
            findOfflineVideo.setVideo(video);
        } else {
            Video video2 = findOfflineVideo.getVideo();
            if (video2 == null) {
                findOfflineVideo.setVideo(video);
            } else {
                video2.setOfflinePlaybackLicenseKey(video.getOfflinePlaybackLicenseKey());
                video2.setLicenseExpiryDate(video.getLicenseExpiryDate());
                findOfflineVideo.setVideo(video2);
            }
        }
        return (OfflineVideo) saveEntity(findOfflineVideo);
    }

    @Nullable
    public OfflineVideo findOfflineVideo(@NonNull String str) {
        return (OfflineVideo) ((Result) this.dataStore.toBlocking().select(OfflineVideo.class, new QueryAttribute[0]).where((Condition) OfflineVideo.VIDEO_ID.eq((Object) str)).limit(1).get()).firstOrNull();
    }

    @NonNull
    public List<OfflineVideo> findAllOfflineVideo() {
        return ((ReactiveResult) this.dataStore.select(OfflineVideo.class, new QueryAttribute[0]).get()).toList();
    }

    @NonNull
    public List<OfflineVideo> findAllOfflineVideo(int i) {
        return ((ReactiveResult) this.dataStore.select(OfflineVideo.class, new QueryAttribute[0]).join(DownloadRequestSet.class).on((Condition) OfflineVideo.DOWNLOAD_REQUEST_SET_ID.eq((Expression) DownloadRequestSet.KEY)).where((Condition) DownloadRequestSet.STATUS_CODE.eq((Object) Integer.valueOf(i))).get()).toList();
    }

    public boolean deleteOfflineVideo(@NonNull String str) {
        return ((Integer) ((ReactiveScalar) this.dataStore.delete(OfflineVideo.class).where((Condition) OfflineVideo.VIDEO_ID.eq((Object) str)).get()).value()).intValue() > 0;
    }

    @NonNull
    public DownloadRequestSet createDownloadRequestSet(@Nullable RequestConfig requestConfig, long j) {
        DownloadRequestSet downloadRequestSet = new DownloadRequestSet();
        downloadRequestSet.setStatusCode(-1);
        if (requestConfig != null) {
            downloadRequestSet.setTitle(requestConfig.getTitle());
        }
        downloadRequestSet.setEstimatedSize(j);
        return (DownloadRequestSet) saveEntity(downloadRequestSet);
    }

    @NonNull
    public DownloadRequestSet addDownloadRequests(@NonNull final DownloadRequestSet downloadRequestSet, @NonNull final IRequest... iRequestArr) {
        final BlockingEntityStore blocking = this.dataStore.toBlocking();
        return (DownloadRequestSet) blocking.runInTransaction(new Callable<DownloadRequestSet>() {
            public DownloadRequestSet call() throws Exception {
                for (IRequest access$100 : iRequestArr) {
                    DownloadRequest access$1002 = OfflineStoreManager.createDownloadRequest(access$100);
                    access$1002.setStatusCode(-1);
                    access$1002.setRequestSet(downloadRequestSet);
                    blocking.insert(access$1002);
                }
                downloadRequestSet.setStatusCode(1);
                return (DownloadRequestSet) blocking.update(downloadRequestSet);
            }
        }, TransactionIsolation.SERIALIZABLE);
    }

    @Nullable
    public DownloadRequestSet findDownloadRequestSetByKey(@NonNull Long l) {
        return (DownloadRequestSet) this.dataStore.toBlocking().findByKey(DownloadRequestSet.class, l);
    }

    @Nullable
    public Uri findOfflineAssetUri(@Nullable Uri uri) {
        String str = uri.toString().split("\\?")[0];
        DownloadRequest downloadRequest = (DownloadRequest) ((ReactiveResult) ((WhereAndOr) this.dataStore.select(DownloadRequest.class, new QueryAttribute[0]).where(DownloadRequest.REMOTE_URI.substr(1, str.length()).eq((Object) Uri.parse(str))).and((Condition) DownloadRequest.STATUS_CODE.eq((Object) Integer.valueOf(8)))).limit(1).get()).firstOrNull();
        if (downloadRequest == null) {
            return null;
        }
        return downloadRequest.getLocalUri();
    }

    public Collection<DownloadRequestSet> findDownloadRequestSets(@NonNull Long... lArr) {
        HashMap hashMap = new HashMap();
        int length = lArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 500;
            if (i2 > length) {
                i2 = length;
            }
            for (DownloadRequestSet downloadRequestSet : ((ReactiveResult) ((DistinctSelection) this.dataStore.select(DownloadRequestSet.class, new QueryAttribute[0]).distinct()).join(DownloadRequest.class).on((Condition) DownloadRequestSet.KEY.eq((Expression) DownloadRequest.REQUEST_SET_ID)).where((Condition) DownloadRequest.KEY.in(Convert.toSet((T[]) Arrays.copyOfRange(lArr, i, i2)))).get()).toList()) {
                hashMap.put(downloadRequestSet.getKey(), downloadRequestSet);
            }
            i = i2;
        }
        return hashMap.values();
    }

    public boolean deleteDownloadRequestSet(@NonNull final Long l) {
        final BlockingEntityStore blocking = this.dataStore.toBlocking();
        return ((Boolean) blocking.runInTransaction(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                if (((Integer) ((Scalar) blocking.update(OfflineVideo.class).set(OfflineVideo.DOWNLOAD_REQUEST_SET_ID, null).where((Condition) OfflineVideo.DOWNLOAD_REQUEST_SET_ID.eq((Object) l)).get()).value()).intValue() > 0) {
                    Log.v(OfflineStoreManager.TAG, "Removed all references to download request set #%d", l);
                }
                boolean z = ((Integer) ((Scalar) blocking.delete(DownloadRequestSet.class).where((Condition) DownloadRequestSet.KEY.eq((Object) l)).get()).value()).intValue() > 0;
                if (z) {
                    Log.v(OfflineStoreManager.TAG, "Deleted download request set #%d", l);
                }
                return Boolean.valueOf(z);
            }
        }, TransactionIsolation.SERIALIZABLE)).booleanValue();
    }

    @Nullable
    public DownloadRequest updateDownloadRequestStatusByDownloadId(@NonNull Long l, int i, int i2, long j, long j2, boolean z) {
        BlockingEntityStore blocking = this.dataStore.toBlocking();
        final BlockingEntityStore blockingEntityStore = blocking;
        final Long l2 = l;
        final int i3 = i;
        final int i4 = i2;
        final long j3 = j;
        final long j4 = j2;
        final boolean z2 = z;
        AnonymousClass4 r0 = new Callable<DownloadRequest>() {
            public DownloadRequest call() throws Exception {
                boolean z = false;
                DownloadRequest downloadRequest = (DownloadRequest) ((Result) blockingEntityStore.select(DownloadRequest.class, new QueryAttribute[0]).where((Condition) DownloadRequest.DOWNLOAD_ID.eq((Object) l2)).limit(1).get()).firstOrNull();
                if (!(downloadRequest == null || (downloadRequest.getStatusCode() == i3 && downloadRequest.getReasonCode() == i4 && downloadRequest.getBytesDownloaded() == j3 && downloadRequest.getActualSize() == j4))) {
                    Long key = downloadRequest.getKey();
                    DownloadRequestSet requestSet = downloadRequest.getRequestSet();
                    Update update = blockingEntityStore.update(DownloadRequest.class).set(DownloadRequest.STATUS_CODE, Integer.valueOf(i3)).set(DownloadRequest.REASON_CODE, Integer.valueOf(i4)).set(DownloadRequest.BYTES_DOWNLOADED, Long.valueOf(Math.max(0, j3))).set(DownloadRequest.ACTUAL_SIZE, Long.valueOf(Math.max(0, j4))).set(DownloadRequest.UPDATE_TIME, Long.valueOf(System.currentTimeMillis()));
                    QueryAttribute<DownloadRequest, Long> queryAttribute = DownloadRequest.DOWNLOAD_ID;
                    int i = i3;
                    if (((Integer) ((Scalar) update.set(queryAttribute, (i == -1 || i == 16 || i == 8) ? null : downloadRequest.getDownloadId()).where((Condition) DownloadRequest.KEY.eq((Object) key)).get()).value()).intValue() > 0) {
                        z = true;
                    }
                    if (z) {
                        downloadRequest = (DownloadRequest) OfflineStoreManager.this.refreshEntity(downloadRequest);
                    }
                    if (z && requestSet != null && z2) {
                        z = OfflineStoreManager.updateDownloadRequestSetStatus(blockingEntityStore, requestSet, i3, i4, downloadRequest, true);
                    }
                }
                if (z) {
                    return downloadRequest;
                }
                return null;
            }
        };
        return (DownloadRequest) blocking.runInTransaction(r0, TransactionIsolation.SERIALIZABLE);
    }

    public boolean updateDownloadRequestSetStatus(DownloadRequestSet downloadRequestSet, int i, int i2, DownloadRequest downloadRequest, boolean z) {
        BlockingEntityStore blocking = this.dataStore.toBlocking();
        final BlockingEntityStore blockingEntityStore = blocking;
        final DownloadRequestSet downloadRequestSet2 = downloadRequestSet;
        final int i3 = i;
        final int i4 = i2;
        final DownloadRequest downloadRequest2 = downloadRequest;
        final boolean z2 = z;
        AnonymousClass5 r1 = new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return Boolean.valueOf(OfflineStoreManager.updateDownloadRequestSetStatus(blockingEntityStore, downloadRequestSet2, i3, i4, downloadRequest2, z2));
            }
        };
        return ((Boolean) blocking.runInTransaction(r1, TransactionIsolation.SERIALIZABLE)).booleanValue();
    }

    /* access modifiers changed from: private */
    public static boolean updateDownloadRequestSetStatus(@NonNull BlockingEntityStore<Persistable> blockingEntityStore, DownloadRequestSet downloadRequestSet, int i, int i2, DownloadRequest downloadRequest, boolean z) {
        long j;
        long j2;
        BlockingEntityStore<Persistable> blockingEntityStore2 = blockingEntityStore;
        int i3 = i;
        Long key = downloadRequest.getKey();
        DownloadRequestSet downloadRequestSet2 = (DownloadRequestSet) blockingEntityStore2.findByKey(DownloadRequestSet.class, downloadRequestSet.getKey());
        if (downloadRequestSet2 != null) {
            int statusCode = downloadRequestSet2.getStatusCode();
            if (!(statusCode == -2 || statusCode == -3 || statusCode == -4 || statusCode == 8 || statusCode == 16)) {
                Long key2 = downloadRequestSet2.getKey();
                int reasonCode = downloadRequestSet2.getReasonCode();
                boolean z2 = i3 == 8;
                if (z || !z2 || !(statusCode == 2 || statusCode == 1)) {
                    if (i3 == 16 || i3 == 2 || isAllOtherDownloadRequestsInState(blockingEntityStore2, key2, key, i3)) {
                        reasonCode = i2;
                    } else if ((i3 == 8 || i3 == -1) && countOfDownloadRequestsInState(blockingEntityStore2, key2, 2) == 0) {
                        i3 = 1;
                        reasonCode = 0;
                    } else {
                        i3 = statusCode;
                    }
                    Tuple tuple = (Tuple) ((Result) blockingEntityStore2.select(DownloadRequest.BYTES_DOWNLOADED.sum().as(TOTAL_BYTES_DOWNLOADED), DownloadRequest.ACTUAL_SIZE.sum().as(TOTAL_SIZE)).where((Condition) DownloadRequest.REQUEST_SET_ID.eq((Object) key2)).get()).first();
                    j2 = ((Long) tuple.get(TOTAL_BYTES_DOWNLOADED)).longValue();
                    j = ((Long) tuple.get(TOTAL_SIZE)).longValue();
                } else {
                    j = downloadRequestSet2.getActualSize() + downloadRequest.getActualSize();
                    reasonCode = 0;
                    j2 = downloadRequestSet2.getBytesDownloaded() + downloadRequest.getBytesDownloaded();
                    i3 = 2;
                }
                boolean z3 = ((Integer) ((Scalar) ((WhereAndOr) blockingEntityStore2.update(DownloadRequestSet.class).set(DownloadRequestSet.STATUS_CODE, Integer.valueOf(i3)).set(DownloadRequestSet.REASON_CODE, Integer.valueOf(reasonCode)).set(DownloadRequestSet.BYTES_DOWNLOADED, Long.valueOf(j2)).set(DownloadRequestSet.ACTUAL_SIZE, Long.valueOf(j)).set(DownloadRequestSet.UPDATE_TIME, Long.valueOf(System.currentTimeMillis())).where((Condition) DownloadRequestSet.KEY.eq((Object) key2)).and((Condition) ((LogicalCondition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-2))).or((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-3))))).get()).value()).intValue() > 0;
                DownloadRequestSet downloadRequestSet3 = (DownloadRequestSet) blockingEntityStore2.refresh(downloadRequestSet2);
                if (!z3) {
                    return z3;
                }
                Log.v(TAG, "Updated download request set #%d: status = %d => %d reason = %d bytesDownloaded = %d actualSize = %d estimatedSize = %d", key2, Integer.valueOf(statusCode), Integer.valueOf(i3), Integer.valueOf(reasonCode), Long.valueOf(j2), Long.valueOf(j), Long.valueOf(downloadRequestSet3.getEstimatedSize()));
                return z3;
            }
        }
        return false;
    }

    private static boolean isAllOtherDownloadRequestsInState(BlockingEntityStore<Persistable> blockingEntityStore, @NonNull Long l, @NonNull Long l2, int i) {
        return ((Integer) ((Scalar) ((WhereAndOr) ((WhereAndOr) blockingEntityStore.count(DownloadRequest.class).where((Condition) DownloadRequest.REQUEST_SET_ID.eq((Object) l)).and((Condition) DownloadRequest.KEY.ne(l2))).and((Condition) DownloadRequest.STATUS_CODE.ne(Integer.valueOf(i)))).get()).value()).intValue() == 0;
    }

    private static int countOfDownloadRequestsInState(@NonNull BlockingEntityStore<Persistable> blockingEntityStore, @NonNull Long l, int i) {
        return ((Integer) ((Scalar) ((WhereAndOr) blockingEntityStore.count(DownloadRequest.class).where((Condition) DownloadRequest.REQUEST_SET_ID.eq((Object) l)).and((Condition) DownloadRequest.STATUS_CODE.eq((Object) Integer.valueOf(i)))).get()).value()).intValue();
    }

    /* access modifiers changed from: private */
    public static boolean setDownloadRequestSetStatus(@NonNull BlockingEntityStore<Persistable> blockingEntityStore, @NonNull Long l, int i) {
        boolean z = ((Integer) ((Scalar) blockingEntityStore.update(DownloadRequestSet.class).set(DownloadRequestSet.STATUS_CODE, Integer.valueOf(i)).set(DownloadRequestSet.UPDATE_TIME, Long.valueOf(System.currentTimeMillis())).where((Condition) DownloadRequestSet.KEY.eq((Object) l)).get()).value()).intValue() > 0;
        if (z) {
            Log.v(TAG, "Updated download request set status to [#%d - %s]: %s", l, Integer.valueOf(i), Integer.valueOf(DownloadStatus.toStatusMessage(i)));
        }
        return z;
    }

    @NonNull
    public List<DownloadRequest> pauseDownloadRequestSet(@NonNull final Long l) {
        final BlockingEntityStore blocking = this.dataStore.toBlocking();
        return (List) blocking.runInTransaction(new Callable<List<DownloadRequest>>() {
            public List<DownloadRequest> call() throws Exception {
                int i;
                DownloadRequestSet downloadRequestSet = (DownloadRequestSet) blocking.findByKey(DownloadRequestSet.class, l);
                if (downloadRequestSet == null) {
                    i = 0;
                } else {
                    i = downloadRequestSet.getStatusCode();
                }
                if (i == 8 || i == 0 || i == -3 || i == -2 || i == -1 || !OfflineStoreManager.setDownloadRequestSetStatus(blocking, l, -4)) {
                    return new ArrayList();
                }
                return ((Result) ((WhereAndOr) ((WhereAndOr) ((WhereAndOr) blocking.select(DownloadRequest.class, new QueryAttribute[0]).where((Condition) DownloadRequest.REQUEST_SET_ID.eq((Object) l)).and((Condition) DownloadRequest.DOWNLOAD_ID.notNull())).and((Condition) DownloadRequest.STATUS_CODE.ne(Integer.valueOf(8)))).and((Condition) DownloadRequest.STATUS_CODE.ne(Integer.valueOf(16)))).get()).toList();
            }
        }, TransactionIsolation.SERIALIZABLE);
    }

    @Nullable
    public DownloadRequestSet resumeDownloadRequestSet(@NonNull final Long l) {
        final BlockingEntityStore blocking = this.dataStore.toBlocking();
        return (DownloadRequestSet) blocking.runInTransaction(new Callable<DownloadRequestSet>() {
            public DownloadRequestSet call() throws Exception {
                int i;
                DownloadRequestSet downloadRequestSet = (DownloadRequestSet) blocking.findByKey(DownloadRequestSet.class, l);
                if (downloadRequestSet == null) {
                    i = 0;
                } else {
                    i = downloadRequestSet.getStatusCode();
                }
                if (i == -4 || i == 16) {
                    int intValue = ((Integer) ((ReactiveScalar) ((WhereAndOr) OfflineStoreManager.this.dataStore.update(DownloadRequest.class).set(DownloadRequest.STATUS_CODE, Integer.valueOf(-1)).set(DownloadRequest.REASON_CODE, Integer.valueOf(0)).set(DownloadRequest.DOWNLOAD_ID, null).where((Condition) DownloadRequest.REQUEST_SET_ID.eq((Object) l)).and((Condition) DownloadRequest.STATUS_CODE.ne(Integer.valueOf(8)))).get()).value()).intValue();
                    if (!OfflineStoreManager.setDownloadRequestSetStatus(blocking, l, 1)) {
                        return downloadRequestSet;
                    }
                    DownloadRequestSet downloadRequestSet2 = (DownloadRequestSet) blocking.findByKey(DownloadRequestSet.class, l);
                    Log.v(OfflineStoreManager.TAG, "Marked %d download requests in request set # %d as pending", Integer.valueOf(intValue), l);
                    return downloadRequestSet2;
                }
                Log.w(OfflineStoreManager.TAG, "Cannot resume a download that is neither in paused state nor in failed state!", new Object[0]);
                return downloadRequestSet;
            }
        }, TransactionIsolation.SERIALIZABLE);
    }

    public boolean updateDownloadId(@NonNull Long l, Long l2) {
        if (((Integer) ((Scalar) this.dataStore.toBlocking().update(DownloadRequest.class).set(DownloadRequest.STATUS_CODE, Integer.valueOf(l2 == null ? -1 : 1)).set(DownloadRequest.DOWNLOAD_ID, l2).where((Condition) DownloadRequest.KEY.eq((Object) l)).get()).value()).intValue() > 0) {
            return true;
        }
        return false;
    }

    @Nullable
    @CanSetDownloadIdentifier
    public OfflineVideo changeDownloadIdentifier(@NonNull Video video) {
        ReflectionUtil.assertCallerAnnotation(CanChangeDownloadIdentifier.class, "Not permitted to change download identifier");
        String id = video.getId();
        OfflineVideo findOfflineVideo = findOfflineVideo(id);
        if (findOfflineVideo == null) {
            return findOfflineVideo;
        }
        UUID key = findOfflineVideo.getKey();
        UUID randomUUID = UUID.randomUUID();
        Video video2 = findOfflineVideo.getVideo();
        if (video2 != null) {
            video2.setDownloadId(randomUUID);
        }
        if (((Integer) ((ReactiveScalar) this.dataStore.update(OfflineVideo.class).set(OfflineVideo.KEY, randomUUID).set(OfflineVideo.DOWNLOAD_DIRECTORY, null).set(OfflineVideo.VIDEO, video2).where((Condition) OfflineVideo.KEY.eq((Object) key)).get()).value()).intValue() > 0) {
            video.setDownloadId(randomUUID);
            return findOfflineVideo(id);
        }
        Log.w(TAG, "Failed to the change offline download identifier: oldKey = %s", key);
        return findOfflineVideo;
    }

    @NonNull
    public List<DownloadRequest> markRequestSetForRemoval(@NonNull final Long l) {
        final BlockingEntityStore blocking = this.dataStore.toBlocking();
        final DownloadRequestSet downloadRequestSet = (DownloadRequestSet) blocking.findByKey(DownloadRequestSet.class, l);
        if (downloadRequestSet == null) {
            return new ArrayList();
        }
        return (List) blocking.runInTransaction(new Callable<List<DownloadRequest>>() {
            public List<DownloadRequest> call() throws Exception {
                if (((Integer) ((Scalar) blocking.update(DownloadRequestSet.class).set(DownloadRequestSet.STATUS_CODE, Integer.valueOf(downloadRequestSet.getStatusCode() == 8 ? -3 : -2)).where((Condition) DownloadRequestSet.KEY.eq((Object) l)).get()).value()).intValue() > 0) {
                    Log.v(OfflineStoreManager.TAG, "Download request set#%d marked for removal", l);
                }
                Log.v(OfflineStoreManager.TAG, "Deleted %d download requests from set#%d", Integer.valueOf(((Integer) ((Scalar) blocking.delete(DownloadRequest.class).where((Condition) ((LogicalCondition) DownloadRequest.REQUEST_SET_ID.eq((Object) l)).and((Condition) DownloadRequest.DOWNLOAD_ID.isNull())).get()).value()).intValue()), l);
                return ((Result) blocking.select(DownloadRequest.class, new QueryAttribute[0]).where((Condition) ((LogicalCondition) DownloadRequest.REQUEST_SET_ID.eq((Object) l)).and((Condition) DownloadRequest.DOWNLOAD_ID.notNull())).get()).toList();
            }
        }, TransactionIsolation.SERIALIZABLE);
    }

    @NonNull
    public List<DownloadRequest> findDownloadsToBeQueued(int i, boolean z) {
        WhereAndOr whereAndOr = (WhereAndOr) ((WhereAndOr) ((WhereAndOr) ((WhereAndOr) ((WhereAndOr) ((WhereAndOr) ((WhereAndOr) this.dataStore.toBlocking().select(DownloadRequest.class, new QueryAttribute[0]).join(DownloadRequestSet.class).on((Condition) DownloadRequestSet.KEY.eq((Expression) DownloadRequest.REQUEST_SET_ID)).where((Condition) DownloadRequest.DOWNLOAD_ID.isNull()).and((Condition) DownloadRequest.STATUS_CODE.eq((Object) Integer.valueOf(-1)))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-1)))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-4)))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-3)))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-2)))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(8)))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(16)));
        if (z) {
            whereAndOr = (WhereAndOr) whereAndOr.and((Condition) DownloadRequest.ALLOWED_OVER_MOBILE.eq((Object) Boolean.TRUE));
        }
        return ((Result) ((Limit) whereAndOr.orderBy((Expression) DownloadRequest.CREATE_TIME)).limit(i).get()).toList();
    }

    public boolean isCurrentDownloadBatchInProgress() {
        return ((Integer) ((Scalar) ((WhereAndOr) ((WhereAndOr) ((WhereAndOr) ((WhereAndOr) this.dataStore.toBlocking().count(DownloadRequest.class).join(DownloadRequestSet.class).on((Condition) DownloadRequestSet.KEY.eq((Expression) DownloadRequest.REQUEST_SET_ID)).where((Condition) DownloadRequest.DOWNLOAD_ID.notNull()).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-3)))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-2)))).and((Condition) DownloadRequest.STATUS_CODE.ne(Integer.valueOf(8)))).and((Condition) DownloadRequest.STATUS_CODE.ne(Integer.valueOf(16)))).get()).value()).intValue() > 1;
    }

    public int countDownloadRequestsInState(@NonNull Long l, int i) {
        return ((Integer) ((Scalar) ((WhereAndOr) this.dataStore.toBlocking().count(DownloadRequest.class).where((Condition) DownloadRequest.REQUEST_SET_ID.eq((Object) l)).and((Condition) DownloadRequest.STATUS_CODE.eq((Object) Integer.valueOf(i)))).get()).value()).intValue();
    }

    public boolean isDownloadCompleted(@NonNull Long l) {
        return ((Integer) ((Scalar) ((WhereAndOr) this.dataStore.toBlocking().count(DownloadRequest.class).where((Condition) DownloadRequest.REQUEST_SET_ID.eq((Object) l)).and((Condition) DownloadRequest.STATUS_CODE.ne(Integer.valueOf(8)))).get()).value()).intValue() == 0;
    }

    public List<DownloadRequest> findCurrentDownloadBatchInProgress(int i) {
        return ((Result) ((WhereAndOr) ((WhereAndOr) ((WhereAndOr) ((WhereAndOr) this.dataStore.toBlocking().select(DownloadRequest.class, new QueryAttribute[0]).join(DownloadRequestSet.class).on((Condition) DownloadRequestSet.KEY.eq((Expression) DownloadRequest.REQUEST_SET_ID)).where((Condition) DownloadRequest.DOWNLOAD_ID.notNull()).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-3)))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-2)))).and((Condition) DownloadRequest.STATUS_CODE.ne(Integer.valueOf(8)))).and((Condition) DownloadRequest.STATUS_CODE.ne(Integer.valueOf(16)))).limit(i).get()).toList();
    }

    public List<DownloadRequestSet> findCurrentDownloadRequestSetInProgress() {
        return ((Result) ((WhereAndOr) ((WhereAndOr) ((WhereAndOr) this.dataStore.toBlocking().select(DownloadRequestSet.class, new QueryAttribute[0]).where((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-3))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(-2)))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(8)))).and((Condition) DownloadRequestSet.STATUS_CODE.ne(Integer.valueOf(16)))).get()).toList();
    }

    public static List<Video> toVideoList(@NonNull List<OfflineVideo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (OfflineVideo video : list) {
            arrayList.add(video.getVideo());
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static DownloadRequest createDownloadRequest(IRequest iRequest) {
        DownloadRequest downloadRequest = new DownloadRequest();
        downloadRequest.setRemoteUri(iRequest.getRemoteUri());
        downloadRequest.setLocalUri(iRequest.getLocalUri());
        downloadRequest.setMimeType(iRequest.getMimeType());
        downloadRequest.setTitle(iRequest.getTitle());
        downloadRequest.setDescription(iRequest.getDescription());
        downloadRequest.setAllowedOverMobile(iRequest.isAllowedOverMobile());
        downloadRequest.setAllowedOverWifi(iRequest.isAllowedOverWifi());
        downloadRequest.setAllowedOverBluetooth(iRequest.isAllowedOverBluetooth());
        downloadRequest.setAllowedOverRoaming(iRequest.isAllowedOverRoaming());
        downloadRequest.setAllowedOverMetered(iRequest.isAllowedOverMetered());
        downloadRequest.setNotificationVisibility(iRequest.getNotificationVisibility());
        downloadRequest.setVisibleInDownloadsUi(iRequest.isVisibleInDownloadsUi());
        downloadRequest.setHeaders(iRequest.getHeaders());
        return downloadRequest;
    }
}

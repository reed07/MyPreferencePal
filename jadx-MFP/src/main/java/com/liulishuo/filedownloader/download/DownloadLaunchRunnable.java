package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.IThreadPoolMonitor;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.download.ConnectionProfile.ConnectionProfileBuild;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.exception.FileDownloadHttpException;
import com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException;
import com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException;
import com.liulishuo.filedownloader.exception.FileDownloadSecurityException;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class DownloadLaunchRunnable implements ProcessCallback, Runnable {
    private static final ThreadPoolExecutor DOWNLOAD_EXECUTOR = FileDownloadExecutors.newFixedThreadPool("ConnectionBlock");
    private boolean acceptPartial;
    private final AtomicBoolean alive;
    private final FileDownloadDatabase database;
    private final int defaultConnectionCount;
    private final ArrayList<DownloadRunnable> downloadRunnableList;
    private volatile boolean error;
    private volatile Exception errorException;
    private boolean isChunked;
    private final boolean isForceReDownload;
    private boolean isNeedForceDiscardRange;
    private boolean isResumeAvailableOnDB;
    private boolean isSingleConnection;
    private boolean isTriedFixRangeNotSatisfiable;
    private final boolean isWifiRequired;
    private long lastCallbackBytes;
    private long lastCallbackTimestamp;
    private long lastUpdateBytes;
    private long lastUpdateTimestamp;
    private final FileDownloadModel model;
    private volatile boolean paused;
    private String redirectedUrl;
    private DownloadRunnable singleDownloadRunnable;
    private final DownloadStatusCallback statusCallback;
    private final boolean supportSeek;
    private final IThreadPoolMonitor threadPoolMonitor;
    private final FileDownloadHeader userRequestHeader;
    int validRetryTimes;

    public static class Builder {
        private Integer callbackProgressMaxCount;
        private FileDownloadHeader header;
        private Boolean isForceReDownload;
        private Boolean isWifiRequired;
        private Integer maxRetryTimes;
        private Integer minIntervalMillis;
        private FileDownloadModel model;
        private IThreadPoolMonitor threadPoolMonitor;

        public Builder setModel(FileDownloadModel fileDownloadModel) {
            this.model = fileDownloadModel;
            return this;
        }

        public Builder setHeader(FileDownloadHeader fileDownloadHeader) {
            this.header = fileDownloadHeader;
            return this;
        }

        public Builder setThreadPoolMonitor(IThreadPoolMonitor iThreadPoolMonitor) {
            this.threadPoolMonitor = iThreadPoolMonitor;
            return this;
        }

        public Builder setMinIntervalMillis(Integer num) {
            this.minIntervalMillis = num;
            return this;
        }

        public Builder setCallbackProgressMaxCount(Integer num) {
            this.callbackProgressMaxCount = num;
            return this;
        }

        public Builder setForceReDownload(Boolean bool) {
            this.isForceReDownload = bool;
            return this;
        }

        public Builder setWifiRequired(Boolean bool) {
            this.isWifiRequired = bool;
            return this;
        }

        public Builder setMaxRetryTimes(Integer num) {
            this.maxRetryTimes = num;
            return this;
        }

        public DownloadLaunchRunnable build() {
            FileDownloadModel fileDownloadModel = this.model;
            if (fileDownloadModel != null) {
                IThreadPoolMonitor iThreadPoolMonitor = this.threadPoolMonitor;
                if (iThreadPoolMonitor != null) {
                    Integer num = this.minIntervalMillis;
                    if (!(num == null || this.callbackProgressMaxCount == null || this.isForceReDownload == null || this.isWifiRequired == null || this.maxRetryTimes == null)) {
                        DownloadLaunchRunnable downloadLaunchRunnable = new DownloadLaunchRunnable(fileDownloadModel, this.header, iThreadPoolMonitor, num.intValue(), this.callbackProgressMaxCount.intValue(), this.isForceReDownload.booleanValue(), this.isWifiRequired.booleanValue(), this.maxRetryTimes.intValue());
                        return downloadLaunchRunnable;
                    }
                }
            }
            throw new IllegalArgumentException();
        }
    }

    class DiscardSafely extends Throwable {
        DiscardSafely() {
        }
    }

    class RetryDirectly extends Throwable {
        RetryDirectly() {
        }
    }

    private DownloadLaunchRunnable(FileDownloadModel fileDownloadModel, FileDownloadHeader fileDownloadHeader, IThreadPoolMonitor iThreadPoolMonitor, int i, int i2, boolean z, boolean z2, int i3) {
        this.defaultConnectionCount = 5;
        this.isNeedForceDiscardRange = false;
        this.downloadRunnableList = new ArrayList<>(5);
        this.lastCallbackBytes = 0;
        this.lastCallbackTimestamp = 0;
        this.lastUpdateBytes = 0;
        this.lastUpdateTimestamp = 0;
        this.alive = new AtomicBoolean(true);
        this.paused = false;
        this.isTriedFixRangeNotSatisfiable = false;
        this.model = fileDownloadModel;
        this.userRequestHeader = fileDownloadHeader;
        this.isForceReDownload = z;
        this.isWifiRequired = z2;
        this.database = CustomComponentHolder.getImpl().getDatabaseInstance();
        this.supportSeek = CustomComponentHolder.getImpl().isSupportSeek();
        this.threadPoolMonitor = iThreadPoolMonitor;
        this.validRetryTimes = i3;
        this.statusCallback = new DownloadStatusCallback(fileDownloadModel, i3, i, i2);
    }

    public void pause() {
        this.paused = true;
        DownloadRunnable downloadRunnable = this.singleDownloadRunnable;
        if (downloadRunnable != null) {
            downloadRunnable.pause();
        }
        Iterator it = ((ArrayList) this.downloadRunnableList.clone()).iterator();
        while (it.hasNext()) {
            DownloadRunnable downloadRunnable2 = (DownloadRunnable) it.next();
            if (downloadRunnable2 != null) {
                downloadRunnable2.pause();
            }
        }
    }

    public void pending() {
        inspectTaskModelResumeAvailableOnDB(this.database.findConnectionModel(this.model.getId()));
        this.statusCallback.onPending();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:105:0x01e0 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r10 = this;
            r0 = 10
            r1 = 0
            android.os.Process.setThreadPriority(r0)     // Catch:{ all -> 0x025c }
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r10.model     // Catch:{ all -> 0x025c }
            byte r0 = r0.getStatus()     // Catch:{ all -> 0x025c }
            r2 = -2
            r3 = 1
            if (r0 == r3) goto L_0x008d
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r10.model     // Catch:{ all -> 0x025c }
            byte r0 = r0.getStatus()     // Catch:{ all -> 0x025c }
            if (r0 != r2) goto L_0x0030
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x025c }
            if (r0 == 0) goto L_0x0060
            java.lang.String r0 = "High concurrent cause, start runnable but already paused %d"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ all -> 0x025c }
            com.liulishuo.filedownloader.model.FileDownloadModel r3 = r10.model     // Catch:{ all -> 0x025c }
            int r3 = r3.getId()     // Catch:{ all -> 0x025c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x025c }
            r2[r1] = r3     // Catch:{ all -> 0x025c }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r10, r0, r2)     // Catch:{ all -> 0x025c }
            goto L_0x0060
        L_0x0030:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x025c }
            java.lang.String r2 = "Task[%d] can't start the download runnable, because its status is %d not %d"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x025c }
            com.liulishuo.filedownloader.model.FileDownloadModel r5 = r10.model     // Catch:{ all -> 0x025c }
            int r5 = r5.getId()     // Catch:{ all -> 0x025c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x025c }
            r4[r1] = r5     // Catch:{ all -> 0x025c }
            com.liulishuo.filedownloader.model.FileDownloadModel r5 = r10.model     // Catch:{ all -> 0x025c }
            byte r5 = r5.getStatus()     // Catch:{ all -> 0x025c }
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)     // Catch:{ all -> 0x025c }
            r4[r3] = r5     // Catch:{ all -> 0x025c }
            r5 = 2
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)     // Catch:{ all -> 0x025c }
            r4[r5] = r3     // Catch:{ all -> 0x025c }
            java.lang.String r2 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r2, r4)     // Catch:{ all -> 0x025c }
            r0.<init>(r2)     // Catch:{ all -> 0x025c }
            r10.onError(r0)     // Catch:{ all -> 0x025c }
        L_0x0060:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.discardAllMessage()
            boolean r0 = r10.paused
            if (r0 == 0) goto L_0x006f
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.onPausedDirectly()
            goto L_0x0087
        L_0x006f:
            boolean r0 = r10.error
            if (r0 == 0) goto L_0x007b
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            java.lang.Exception r2 = r10.errorException
            r0.onErrorDirectly(r2)
            goto L_0x0087
        L_0x007b:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback     // Catch:{ IOException -> 0x0081 }
            r0.onCompletedDirectly()     // Catch:{ IOException -> 0x0081 }
            goto L_0x0087
        L_0x0081:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback
            r2.onErrorDirectly(r0)
        L_0x0087:
            java.util.concurrent.atomic.AtomicBoolean r0 = r10.alive
            r0.set(r1)
            return
        L_0x008d:
            boolean r0 = r10.paused     // Catch:{ all -> 0x025c }
            if (r0 != 0) goto L_0x0096
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback     // Catch:{ all -> 0x025c }
            r0.onStartThread()     // Catch:{ all -> 0x025c }
        L_0x0096:
            boolean r0 = r10.paused     // Catch:{ all -> 0x025c }
            if (r0 == 0) goto L_0x00de
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x025c }
            if (r0 == 0) goto L_0x00b1
            java.lang.String r0 = "High concurrent cause, start runnable but already paused %d"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ all -> 0x025c }
            com.liulishuo.filedownloader.model.FileDownloadModel r3 = r10.model     // Catch:{ all -> 0x025c }
            int r3 = r3.getId()     // Catch:{ all -> 0x025c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x025c }
            r2[r1] = r3     // Catch:{ all -> 0x025c }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r10, r0, r2)     // Catch:{ all -> 0x025c }
        L_0x00b1:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.discardAllMessage()
            boolean r0 = r10.paused
            if (r0 == 0) goto L_0x00c0
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.onPausedDirectly()
            goto L_0x00d8
        L_0x00c0:
            boolean r0 = r10.error
            if (r0 == 0) goto L_0x00cc
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            java.lang.Exception r2 = r10.errorException
            r0.onErrorDirectly(r2)
            goto L_0x00d8
        L_0x00cc:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback     // Catch:{ IOException -> 0x00d2 }
            r0.onCompletedDirectly()     // Catch:{ IOException -> 0x00d2 }
            goto L_0x00d8
        L_0x00d2:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback
            r2.onErrorDirectly(r0)
        L_0x00d8:
            java.util.concurrent.atomic.AtomicBoolean r0 = r10.alive
            r0.set(r1)
            return
        L_0x00de:
            r10.checkupBeforeConnect()     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            r10.trialConnect()     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            r10.checkupAfterGetFilename()     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            com.liulishuo.filedownloader.database.FileDownloadDatabase r0 = r10.database     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            com.liulishuo.filedownloader.model.FileDownloadModel r4 = r10.model     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            int r4 = r4.getId()     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            java.util.List r0 = r0.findConnectionModel(r4)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            r10.inspectTaskModelResumeAvailableOnDB(r0)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            boolean r4 = r10.paused     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            if (r4 == 0) goto L_0x012c
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r10.model     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            r0.setStatus(r2)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.discardAllMessage()
            boolean r0 = r10.paused
            if (r0 == 0) goto L_0x010e
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.onPausedDirectly()
            goto L_0x0126
        L_0x010e:
            boolean r0 = r10.error
            if (r0 == 0) goto L_0x011a
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            java.lang.Exception r2 = r10.errorException
            r0.onErrorDirectly(r2)
            goto L_0x0126
        L_0x011a:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback     // Catch:{ IOException -> 0x0120 }
            r0.onCompletedDirectly()     // Catch:{ IOException -> 0x0120 }
            goto L_0x0126
        L_0x0120:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback
            r2.onErrorDirectly(r0)
        L_0x0126:
            java.util.concurrent.atomic.AtomicBoolean r0 = r10.alive
            r0.set(r1)
            return
        L_0x012c:
            com.liulishuo.filedownloader.model.FileDownloadModel r4 = r10.model     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            long r4 = r4.getTotal()     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            com.liulishuo.filedownloader.model.FileDownloadModel r6 = r10.model     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            java.lang.String r6 = r6.getTempFilePath()     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            r10.handlePreAllocate(r4, r6)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            int r6 = r10.calcConnectionCount(r4)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            if (r6 <= 0) goto L_0x01cc
            r7 = 0
            int r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0174
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.discardAllMessage()
            boolean r0 = r10.paused
            if (r0 == 0) goto L_0x0156
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.onPausedDirectly()
            goto L_0x016e
        L_0x0156:
            boolean r0 = r10.error
            if (r0 == 0) goto L_0x0162
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            java.lang.Exception r2 = r10.errorException
            r0.onErrorDirectly(r2)
            goto L_0x016e
        L_0x0162:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback     // Catch:{ IOException -> 0x0168 }
            r0.onCompletedDirectly()     // Catch:{ IOException -> 0x0168 }
            goto L_0x016e
        L_0x0168:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback
            r2.onErrorDirectly(r0)
        L_0x016e:
            java.util.concurrent.atomic.AtomicBoolean r0 = r10.alive
            r0.set(r1)
            return
        L_0x0174:
            boolean r7 = r10.paused     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            if (r7 == 0) goto L_0x01aa
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r10.model     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            r0.setStatus(r2)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.discardAllMessage()
            boolean r0 = r10.paused
            if (r0 == 0) goto L_0x018c
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.onPausedDirectly()
            goto L_0x01a4
        L_0x018c:
            boolean r0 = r10.error
            if (r0 == 0) goto L_0x0198
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            java.lang.Exception r2 = r10.errorException
            r0.onErrorDirectly(r2)
            goto L_0x01a4
        L_0x0198:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback     // Catch:{ IOException -> 0x019e }
            r0.onCompletedDirectly()     // Catch:{ IOException -> 0x019e }
            goto L_0x01a4
        L_0x019e:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback
            r2.onErrorDirectly(r0)
        L_0x01a4:
            java.util.concurrent.atomic.AtomicBoolean r0 = r10.alive
            r0.set(r1)
            return
        L_0x01aa:
            if (r6 != r3) goto L_0x01ae
            r7 = 1
            goto L_0x01af
        L_0x01ae:
            r7 = 0
        L_0x01af:
            r10.isSingleConnection = r7     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            boolean r7 = r10.isSingleConnection     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            if (r7 == 0) goto L_0x01ba
            r10.realDownloadWithSingleConnection(r4)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            goto L_0x022f
        L_0x01ba:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r7 = r10.statusCallback     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            r7.onMultiConnection()     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            boolean r7 = r10.isResumeAvailableOnDB     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            if (r7 == 0) goto L_0x01c8
            r10.realDownloadWithMultiConnectionFromResume(r6, r0)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            goto L_0x022f
        L_0x01c8:
            r10.realDownloadWithMultiConnectionFromBeginning(r4, r6)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            goto L_0x022f
        L_0x01cc:
            java.lang.IllegalAccessException r0 = new java.lang.IllegalAccessException     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            java.lang.String r4 = "invalid connection count %d, the connection count must be larger than 0"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            r5[r1] = r6     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            java.lang.String r4 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r4, r5)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            r0.<init>(r4)     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
            throw r0     // Catch:{ IOException -> 0x0220, IllegalAccessException -> 0x021e, InterruptedException -> 0x021c, IllegalArgumentException -> 0x021a, FileDownloadSecurityException -> 0x0218, FileDownloadGiveUpRetryException -> 0x0216, DiscardSafely -> 0x01e8, RetryDirectly -> 0x01e0 }
        L_0x01e0:
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = r10.model     // Catch:{ all -> 0x025c }
            r4 = 5
            r0.setStatus(r4)     // Catch:{ all -> 0x025c }
            goto L_0x0096
        L_0x01e8:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.discardAllMessage()
            boolean r0 = r10.paused
            if (r0 == 0) goto L_0x01f8
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.onPausedDirectly()
            goto L_0x0210
        L_0x01f8:
            boolean r0 = r10.error
            if (r0 == 0) goto L_0x0204
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            java.lang.Exception r2 = r10.errorException
            r0.onErrorDirectly(r2)
            goto L_0x0210
        L_0x0204:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback     // Catch:{ IOException -> 0x020a }
            r0.onCompletedDirectly()     // Catch:{ IOException -> 0x020a }
            goto L_0x0210
        L_0x020a:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback
            r2.onErrorDirectly(r0)
        L_0x0210:
            java.util.concurrent.atomic.AtomicBoolean r0 = r10.alive
            r0.set(r1)
            return
        L_0x0216:
            r0 = move-exception
            goto L_0x0221
        L_0x0218:
            r0 = move-exception
            goto L_0x0221
        L_0x021a:
            r0 = move-exception
            goto L_0x0221
        L_0x021c:
            r0 = move-exception
            goto L_0x0221
        L_0x021e:
            r0 = move-exception
            goto L_0x0221
        L_0x0220:
            r0 = move-exception
        L_0x0221:
            boolean r4 = r10.isRetry(r0)     // Catch:{ all -> 0x025c }
            if (r4 == 0) goto L_0x022c
            r10.onRetry(r0)     // Catch:{ all -> 0x025c }
            goto L_0x0096
        L_0x022c:
            r10.onError(r0)     // Catch:{ all -> 0x025c }
        L_0x022f:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.discardAllMessage()
            boolean r0 = r10.paused
            if (r0 == 0) goto L_0x023e
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            r0.onPausedDirectly()
            goto L_0x0256
        L_0x023e:
            boolean r0 = r10.error
            if (r0 == 0) goto L_0x024a
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback
            java.lang.Exception r2 = r10.errorException
            r0.onErrorDirectly(r2)
            goto L_0x0256
        L_0x024a:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r0 = r10.statusCallback     // Catch:{ IOException -> 0x0250 }
            r0.onCompletedDirectly()     // Catch:{ IOException -> 0x0250 }
            goto L_0x0256
        L_0x0250:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback
            r2.onErrorDirectly(r0)
        L_0x0256:
            java.util.concurrent.atomic.AtomicBoolean r0 = r10.alive
            r0.set(r1)
            return
        L_0x025c:
            r0 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback
            r2.discardAllMessage()
            boolean r2 = r10.paused
            if (r2 != 0) goto L_0x027f
            boolean r2 = r10.error
            if (r2 == 0) goto L_0x0272
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback
            java.lang.Exception r3 = r10.errorException
            r2.onErrorDirectly(r3)
            goto L_0x0284
        L_0x0272:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback     // Catch:{ IOException -> 0x0278 }
            r2.onCompletedDirectly()     // Catch:{ IOException -> 0x0278 }
            goto L_0x0284
        L_0x0278:
            r2 = move-exception
            com.liulishuo.filedownloader.download.DownloadStatusCallback r3 = r10.statusCallback
            r3.onErrorDirectly(r2)
            goto L_0x0284
        L_0x027f:
            com.liulishuo.filedownloader.download.DownloadStatusCallback r2 = r10.statusCallback
            r2.onPausedDirectly()
        L_0x0284:
            java.util.concurrent.atomic.AtomicBoolean r2 = r10.alive
            r2.set(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.DownloadLaunchRunnable.run():void");
    }

    private int calcConnectionCount(long j) {
        if (!isMultiConnectionAvailable()) {
            return 1;
        }
        if (this.isResumeAvailableOnDB) {
            return this.model.getConnectionCount();
        }
        return CustomComponentHolder.getImpl().determineConnectionCount(this.model.getId(), this.model.getUrl(), this.model.getPath(), j);
    }

    /* JADX INFO: finally extract failed */
    private void trialConnect() throws IOException, RetryDirectly, IllegalAccessException, FileDownloadSecurityException {
        ConnectionProfile connectionProfile;
        FileDownloadConnection fileDownloadConnection = null;
        try {
            if (this.isNeedForceDiscardRange) {
                connectionProfile = ConnectionProfileBuild.buildTrialConnectionProfileNoRange();
            } else {
                connectionProfile = ConnectionProfileBuild.buildTrialConnectionProfile();
            }
            ConnectTask build = new Builder().setDownloadId(this.model.getId()).setUrl(this.model.getUrl()).setEtag(this.model.getETag()).setHeader(this.userRequestHeader).setConnectionProfile(connectionProfile).build();
            FileDownloadConnection connect = build.connect();
            handleTrialConnectResult(build.getRequestHeader(), build, connect);
            if (connect != null) {
                connect.ending();
            }
        } catch (Throwable th) {
            if (fileDownloadConnection != null) {
                fileDownloadConnection.ending();
            }
            throw th;
        }
    }

    private boolean isMultiConnectionAvailable() {
        boolean z = false;
        if (this.isResumeAvailableOnDB && this.model.getConnectionCount() <= 1) {
            return false;
        }
        if (this.acceptPartial && this.supportSeek && !this.isChunked) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public void inspectTaskModelResumeAvailableOnDB(List<ConnectionModel> list) {
        int connectionCount = this.model.getConnectionCount();
        String tempFilePath = this.model.getTempFilePath();
        String targetFilePath = this.model.getTargetFilePath();
        boolean z = false;
        boolean z2 = connectionCount > 1;
        long j = this.isNeedForceDiscardRange ? 0 : (!z2 || this.supportSeek) ? FileDownloadUtils.isBreakpointAvailable(this.model.getId(), this.model) ? !this.supportSeek ? new File(tempFilePath).length() : z2 ? connectionCount != list.size() ? 0 : ConnectionModel.getTotalOffset(list) : this.model.getSoFar() : 0 : 0;
        this.model.setSoFar(j);
        if (j > 0) {
            z = true;
        }
        this.isResumeAvailableOnDB = z;
        if (!this.isResumeAvailableOnDB) {
            this.database.removeConnections(this.model.getId());
            FileDownloadUtils.deleteTaskFiles(targetFilePath, tempFilePath);
        }
    }

    private void handleTrialConnectResult(Map<String, List<String>> map, ConnectTask connectTask, FileDownloadConnection fileDownloadConnection) throws IOException, RetryDirectly, IllegalArgumentException, FileDownloadSecurityException {
        boolean z;
        FileDownloadConnection fileDownloadConnection2 = fileDownloadConnection;
        int id = this.model.getId();
        int responseCode = fileDownloadConnection.getResponseCode();
        this.acceptPartial = FileDownloadUtils.isAcceptRange(responseCode, fileDownloadConnection2);
        boolean z2 = responseCode == 200 || responseCode == 201 || responseCode == 0;
        String eTag = this.model.getETag();
        String findEtag = FileDownloadUtils.findEtag(id, fileDownloadConnection2);
        if (responseCode == 412) {
            z = true;
        } else if (eTag != null && !eTag.equals(findEtag) && (z2 || this.acceptPartial)) {
            z = true;
        } else if (responseCode != 201 || !connectTask.isRangeNotFromBeginning()) {
            if (responseCode == 416) {
                if (this.model.getSoFar() > 0) {
                    z = true;
                } else if (!this.isNeedForceDiscardRange) {
                    this.isNeedForceDiscardRange = true;
                    z = true;
                }
            }
            z = false;
        } else {
            z = true;
        }
        if (z) {
            if (this.isResumeAvailableOnDB) {
                FileDownloadLog.w(this, "there is precondition failed on this request[%d] with old etag[%s]、new etag[%s]、response code is %d", Integer.valueOf(id), eTag, findEtag, Integer.valueOf(responseCode));
            }
            this.database.removeConnections(this.model.getId());
            FileDownloadUtils.deleteTaskFiles(this.model.getTargetFilePath(), this.model.getTempFilePath());
            this.isResumeAvailableOnDB = false;
            if (eTag != null && eTag.equals(findEtag)) {
                FileDownloadLog.w(this, "the old etag[%s] is the same to the new etag[%s], but the response status code is %d not Partial(206), so wo have to start this task from very beginning for task[%d]!", eTag, findEtag, Integer.valueOf(responseCode), Integer.valueOf(id));
                findEtag = null;
            }
            this.model.setSoFar(0);
            this.model.setTotal(0);
            this.model.setETag(findEtag);
            this.model.resetConnectionCount();
            this.database.updateOldEtagOverdue(id, this.model.getETag(), this.model.getSoFar(), this.model.getTotal(), this.model.getConnectionCount());
            throw new RetryDirectly();
        }
        this.redirectedUrl = connectTask.getFinalRedirectedUrl();
        if (this.acceptPartial || z2) {
            long findInstanceLengthForTrial = FileDownloadUtils.findInstanceLengthForTrial(fileDownloadConnection);
            String findFilename = this.model.isPathAsDirectory() ? FileDownloadUtils.findFilename(fileDownloadConnection2, this.model.getUrl()) : null;
            this.isChunked = findInstanceLengthForTrial == -1;
            this.statusCallback.onConnected(this.isResumeAvailableOnDB && this.acceptPartial, findInstanceLengthForTrial, findEtag, findFilename);
            return;
        }
        throw new FileDownloadHttpException(responseCode, map, fileDownloadConnection.getResponseHeaderFields());
    }

    private void realDownloadWithSingleConnection(long j) throws IOException, IllegalAccessException {
        ConnectionProfile connectionProfile;
        if (!this.acceptPartial) {
            this.model.setSoFar(0);
            connectionProfile = ConnectionProfileBuild.buildBeginToEndConnectionProfile(j);
        } else {
            connectionProfile = ConnectionProfileBuild.buildToEndConnectionProfile(this.model.getSoFar(), this.model.getSoFar(), j - this.model.getSoFar());
        }
        this.singleDownloadRunnable = new com.liulishuo.filedownloader.download.DownloadRunnable.Builder().setId(this.model.getId()).setConnectionIndex(Integer.valueOf(-1)).setCallback(this).setUrl(this.model.getUrl()).setEtag(this.model.getETag()).setHeader(this.userRequestHeader).setWifiRequired(this.isWifiRequired).setConnectionModel(connectionProfile).setPath(this.model.getTempFilePath()).build();
        this.model.setConnectionCount(1);
        this.database.updateConnectionCount(this.model.getId(), 1);
        if (this.paused) {
            this.model.setStatus(-2);
            this.singleDownloadRunnable.pause();
            return;
        }
        this.singleDownloadRunnable.run();
    }

    private void realDownloadWithMultiConnectionFromResume(int i, List<ConnectionModel> list) throws InterruptedException {
        if (i <= 1 || list.size() != i) {
            throw new IllegalArgumentException();
        }
        fetchWithMultipleConnection(list, this.model.getTotal());
    }

    private void realDownloadWithMultiConnectionFromBeginning(long j, int i) throws InterruptedException {
        long j2 = j / ((long) i);
        int id = this.model.getId();
        ArrayList arrayList = new ArrayList();
        long j3 = 0;
        int i2 = 0;
        while (i2 < i) {
            long j4 = i2 == i + -1 ? -1 : (j3 + j2) - 1;
            ConnectionModel connectionModel = new ConnectionModel();
            connectionModel.setId(id);
            connectionModel.setIndex(i2);
            connectionModel.setStartOffset(j3);
            connectionModel.setCurrentOffset(j3);
            connectionModel.setEndOffset(j4);
            arrayList.add(connectionModel);
            this.database.insertConnectionModel(connectionModel);
            j3 += j2;
            i2++;
        }
        this.model.setConnectionCount(i);
        this.database.updateConnectionCount(id, i);
        fetchWithMultipleConnection(arrayList, j);
    }

    private void fetchWithMultipleConnection(List<ConnectionModel> list, long j) throws InterruptedException {
        long j2;
        int id = this.model.getId();
        String eTag = this.model.getETag();
        String str = this.redirectedUrl;
        if (str == null) {
            str = this.model.getUrl();
        }
        String tempFilePath = this.model.getTempFilePath();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "fetch data with multiple connection(count: [%d]) for task[%d] totalLength[%d]", Integer.valueOf(list.size()), Integer.valueOf(id), Long.valueOf(j));
        }
        boolean z = this.isResumeAvailableOnDB;
        long j3 = 0;
        long j4 = 0;
        for (ConnectionModel connectionModel : list) {
            if (connectionModel.getEndOffset() == -1) {
                j2 = j - connectionModel.getCurrentOffset();
            } else {
                j2 = (connectionModel.getEndOffset() - connectionModel.getCurrentOffset()) + 1;
            }
            j4 += connectionModel.getCurrentOffset() - connectionModel.getStartOffset();
            if (j2 != j3) {
                DownloadRunnable build = new com.liulishuo.filedownloader.download.DownloadRunnable.Builder().setId(id).setConnectionIndex(Integer.valueOf(connectionModel.getIndex())).setCallback(this).setUrl(str).setEtag(z ? eTag : null).setHeader(this.userRequestHeader).setWifiRequired(this.isWifiRequired).setConnectionModel(ConnectionProfileBuild.buildConnectionProfile(connectionModel.getStartOffset(), connectionModel.getCurrentOffset(), connectionModel.getEndOffset(), j2)).setPath(tempFilePath).build();
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "enable multiple connection: %s", connectionModel);
                }
                if (build != null) {
                    this.downloadRunnableList.add(build);
                } else {
                    throw new IllegalArgumentException("the download runnable must not be null!");
                }
            } else if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "pass connection[%d-%d], because it has been completed", Integer.valueOf(connectionModel.getId()), Integer.valueOf(connectionModel.getIndex()));
            }
            j3 = 0;
        }
        if (j4 != this.model.getSoFar()) {
            FileDownloadLog.w(this, "correct the sofar[%d] from connection table[%d]", Long.valueOf(this.model.getSoFar()), Long.valueOf(j4));
            this.model.setSoFar(j4);
        }
        ArrayList arrayList = new ArrayList(this.downloadRunnableList.size());
        Iterator it = this.downloadRunnableList.iterator();
        while (it.hasNext()) {
            DownloadRunnable downloadRunnable = (DownloadRunnable) it.next();
            if (this.paused) {
                downloadRunnable.pause();
            } else {
                arrayList.add(Executors.callable(downloadRunnable));
            }
        }
        if (this.paused) {
            this.model.setStatus(-2);
            return;
        }
        List<Future> invokeAll = DOWNLOAD_EXECUTOR.invokeAll(arrayList);
        if (FileDownloadLog.NEED_LOG) {
            for (Future future : invokeAll) {
                FileDownloadLog.d(this, "finish sub-task for [%d] %B %B", Integer.valueOf(id), Boolean.valueOf(future.isDone()), Boolean.valueOf(future.isCancelled()));
            }
        }
    }

    private void handlePreAllocate(long j, String str) throws IOException, IllegalAccessException {
        FileDownloadOutputStream fileDownloadOutputStream = null;
        if (j != -1) {
            try {
                fileDownloadOutputStream = FileDownloadUtils.createOutputStream(this.model.getTempFilePath());
                long length = new File(str).length();
                long j2 = j - length;
                long freeSpaceBytes = FileDownloadUtils.getFreeSpaceBytes(str);
                if (freeSpaceBytes < j2) {
                    FileDownloadOutOfSpaceException fileDownloadOutOfSpaceException = new FileDownloadOutOfSpaceException(freeSpaceBytes, j2, length);
                    throw fileDownloadOutOfSpaceException;
                } else if (!FileDownloadProperties.getImpl().fileNonPreAllocation) {
                    fileDownloadOutputStream.setLength(j);
                }
            } catch (Throwable th) {
                if (fileDownloadOutputStream != null) {
                    fileDownloadOutputStream.close();
                }
                throw th;
            }
        }
        if (fileDownloadOutputStream != null) {
            fileDownloadOutputStream.close();
        }
    }

    public void onProgress(long j) {
        if (!this.paused) {
            this.statusCallback.onProgress(j);
        }
    }

    public void onCompleted(DownloadRunnable downloadRunnable, long j, long j2) {
        if (this.paused) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "the task[%d] has already been paused, so pass the completed callback", Integer.valueOf(this.model.getId()));
            }
            return;
        }
        int i = downloadRunnable.connectionIndex;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "the connection has been completed(%d): [%d, %d)  %d", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), Long.valueOf(this.model.getTotal()));
        }
        if (!this.isSingleConnection) {
            synchronized (this.downloadRunnableList) {
                this.downloadRunnableList.remove(downloadRunnable);
            }
        } else if (!(j == 0 || j2 == this.model.getTotal())) {
            FileDownloadLog.e(this, "the single task not completed corrected(%d, %d != %d) for task(%d)", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(this.model.getTotal()), Integer.valueOf(this.model.getId()));
        }
    }

    public boolean isRetry(Exception exc) {
        boolean z = true;
        if (exc instanceof FileDownloadHttpException) {
            int code = ((FileDownloadHttpException) exc).getCode();
            if (this.isSingleConnection && code == 416 && !this.isTriedFixRangeNotSatisfiable) {
                FileDownloadUtils.deleteTaskFiles(this.model.getTargetFilePath(), this.model.getTempFilePath());
                this.isTriedFixRangeNotSatisfiable = true;
                return true;
            }
        }
        if (this.validRetryTimes <= 0 || (exc instanceof FileDownloadGiveUpRetryException)) {
            z = false;
        }
        return z;
    }

    public void onError(Exception exc) {
        this.error = true;
        this.errorException = exc;
        if (this.paused) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "the task[%d] has already been paused, so pass the error callback", Integer.valueOf(this.model.getId()));
            }
            return;
        }
        Iterator it = ((ArrayList) this.downloadRunnableList.clone()).iterator();
        while (it.hasNext()) {
            DownloadRunnable downloadRunnable = (DownloadRunnable) it.next();
            if (downloadRunnable != null) {
                downloadRunnable.discard();
            }
        }
    }

    public void onRetry(Exception exc) {
        if (this.paused) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "the task[%d] has already been paused, so pass the retry callback", Integer.valueOf(this.model.getId()));
            }
            return;
        }
        int i = this.validRetryTimes;
        this.validRetryTimes = i - 1;
        if (i < 0) {
            FileDownloadLog.e(this, "valid retry times is less than 0(%d) for download task(%d)", Integer.valueOf(this.validRetryTimes), Integer.valueOf(this.model.getId()));
        }
        this.statusCallback.onRetry(exc, this.validRetryTimes);
    }

    public void syncProgressFromCache() {
        this.database.updateProgress(this.model.getId(), this.model.getSoFar());
    }

    private void checkupBeforeConnect() throws FileDownloadGiveUpRetryException {
        if (this.isWifiRequired && !FileDownloadUtils.checkPermission("android.permission.ACCESS_NETWORK_STATE")) {
            throw new FileDownloadGiveUpRetryException(FileDownloadUtils.formatString("Task[%d] can't start the download runnable, because this task require wifi, but user application nor current process has %s, so we can't check whether the network type connection.", Integer.valueOf(this.model.getId()), "android.permission.ACCESS_NETWORK_STATE"));
        } else if (this.isWifiRequired && FileDownloadUtils.isNetworkNotOnWifiType()) {
            throw new FileDownloadNetworkPolicyException();
        }
    }

    private void checkupAfterGetFilename() throws RetryDirectly, DiscardSafely {
        int id = this.model.getId();
        if (this.model.isPathAsDirectory()) {
            String targetFilePath = this.model.getTargetFilePath();
            int generateId = FileDownloadUtils.generateId(this.model.getUrl(), targetFilePath);
            if (!FileDownloadHelper.inspectAndInflowDownloaded(id, targetFilePath, this.isForceReDownload, false)) {
                FileDownloadModel find = this.database.find(generateId);
                if (find != null) {
                    if (!FileDownloadHelper.inspectAndInflowDownloading(id, find, this.threadPoolMonitor, false)) {
                        List<ConnectionModel> findConnectionModel = this.database.findConnectionModel(generateId);
                        this.database.remove(generateId);
                        this.database.removeConnections(generateId);
                        FileDownloadUtils.deleteTargetFile(this.model.getTargetFilePath());
                        if (FileDownloadUtils.isBreakpointAvailable(generateId, find)) {
                            this.model.setSoFar(find.getSoFar());
                            this.model.setTotal(find.getTotal());
                            this.model.setETag(find.getETag());
                            this.model.setConnectionCount(find.getConnectionCount());
                            this.database.update(this.model);
                            if (findConnectionModel != null) {
                                for (ConnectionModel connectionModel : findConnectionModel) {
                                    connectionModel.setId(id);
                                    this.database.insertConnectionModel(connectionModel);
                                }
                            }
                            throw new RetryDirectly();
                        }
                    } else {
                        this.database.remove(id);
                        this.database.removeConnections(id);
                        throw new DiscardSafely();
                    }
                }
                if (FileDownloadHelper.inspectAndInflowConflictPath(id, this.model.getSoFar(), this.model.getTempFilePath(), targetFilePath, this.threadPoolMonitor)) {
                    this.database.remove(id);
                    this.database.removeConnections(id);
                    throw new DiscardSafely();
                }
                return;
            }
            this.database.remove(id);
            this.database.removeConnections(id);
            throw new DiscardSafely();
        }
    }

    public int getId() {
        return this.model.getId();
    }

    public boolean isAlive() {
        return this.alive.get() || this.statusCallback.isAlive();
    }

    public String getTempFilePath() {
        return this.model.getTempFilePath();
    }
}

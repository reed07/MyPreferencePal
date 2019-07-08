package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.FinishListener;
import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.BaseDownloadTask.LifeCycleCallback;
import com.liulishuo.filedownloader.IDownloadSpeed.Lookup;
import com.liulishuo.filedownloader.IDownloadSpeed.Monitor;
import com.liulishuo.filedownloader.ITaskHunter.IMessageHandler;
import com.liulishuo.filedownloader.ITaskHunter.IStarter;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshot.IWarnMessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class DownloadTaskHunter implements LifeCycleCallback, ITaskHunter, IMessageHandler, IStarter {
    private String mEtag;
    private boolean mIsLargeFile;
    private boolean mIsResuming;
    private boolean mIsReusedOldFile = false;
    private IFileDownloadMessenger mMessenger;
    private final Object mPauseLock;
    private int mRetryingTimes;
    private long mSoFarBytes;
    private final Lookup mSpeedLookup;
    private final Monitor mSpeedMonitor;
    private volatile byte mStatus = 0;
    private final ICaptureTask mTask;
    private Throwable mThrowable = null;
    private long mTotalBytes;

    interface ICaptureTask {
        ArrayList<FinishListener> getFinishListenerList();

        FileDownloadHeader getHeader();

        IRunningTask getRunningTask();

        void setFileName(String str);
    }

    public boolean updateKeepAhead(MessageSnapshot messageSnapshot) {
        if (!FileDownloadStatus.isKeepAhead(getStatus(), messageSnapshot.getStatus())) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "can't update mStatus change by keep ahead, %d, but the current mStatus is %d, %d", Byte.valueOf(this.mStatus), Byte.valueOf(getStatus()), Integer.valueOf(getId()));
            }
            return false;
        }
        update(messageSnapshot);
        return true;
    }

    public boolean updateKeepFlow(MessageSnapshot messageSnapshot) {
        byte status = getStatus();
        byte status2 = messageSnapshot.getStatus();
        if (-2 == status && FileDownloadStatus.isIng(status2)) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "High concurrent cause, callback pending, but has already be paused %d", Integer.valueOf(getId()));
            }
            return true;
        } else if (!FileDownloadStatus.isKeepFlow(status, status2)) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "can't update mStatus change by keep flow, %d, but the current mStatus is %d, %d", Byte.valueOf(this.mStatus), Byte.valueOf(getStatus()), Integer.valueOf(getId()));
            }
            return false;
        } else {
            update(messageSnapshot);
            return true;
        }
    }

    public boolean updateMoreLikelyCompleted(MessageSnapshot messageSnapshot) {
        if (!FileDownloadStatus.isMoreLikelyCompleted(this.mTask.getRunningTask().getOrigin())) {
            return false;
        }
        update(messageSnapshot);
        return true;
    }

    public boolean updateSameFilePathTaskRunning(MessageSnapshot messageSnapshot) {
        if (!this.mTask.getRunningTask().getOrigin().isPathAsDirectory() || messageSnapshot.getStatus() != -4 || getStatus() != 2) {
            return false;
        }
        update(messageSnapshot);
        return true;
    }

    public IFileDownloadMessenger getMessenger() {
        return this.mMessenger;
    }

    public MessageSnapshot prepareErrorMessage(Throwable th) {
        this.mStatus = -1;
        this.mThrowable = th;
        return MessageSnapshotTaker.catchException(getId(), getSofarBytes(), th);
    }

    private void update(MessageSnapshot messageSnapshot) {
        int i;
        BaseDownloadTask origin = this.mTask.getRunningTask().getOrigin();
        byte status = messageSnapshot.getStatus();
        this.mStatus = status;
        this.mIsLargeFile = messageSnapshot.isLargeFile();
        switch (status) {
            case -4:
                this.mSpeedMonitor.reset();
                int count = FileDownloadList.getImpl().count(origin.getId());
                if (count > 1 || !origin.isPathAsDirectory()) {
                    i = 0;
                } else {
                    i = FileDownloadList.getImpl().count(FileDownloadUtils.generateId(origin.getUrl(), origin.getTargetFilePath()));
                }
                if (count + i <= 1) {
                    byte status2 = FileDownloadServiceProxy.getImpl().getStatus(origin.getId());
                    FileDownloadLog.w(this, "warn, but no mListener to receive, switch to pending %d %d", Integer.valueOf(origin.getId()), Integer.valueOf(status2));
                    if (FileDownloadStatus.isIng(status2)) {
                        this.mStatus = 1;
                        this.mTotalBytes = messageSnapshot.getLargeTotalBytes();
                        this.mSoFarBytes = messageSnapshot.getLargeSofarBytes();
                        this.mSpeedMonitor.start(this.mSoFarBytes);
                        this.mMessenger.notifyPending(((IWarnMessageSnapshot) messageSnapshot).turnToPending());
                        return;
                    }
                }
                FileDownloadList.getImpl().remove(this.mTask.getRunningTask(), messageSnapshot);
                return;
            case -3:
                this.mIsReusedOldFile = messageSnapshot.isReusedDownloadedFile();
                this.mSoFarBytes = messageSnapshot.getLargeTotalBytes();
                this.mTotalBytes = messageSnapshot.getLargeTotalBytes();
                FileDownloadList.getImpl().remove(this.mTask.getRunningTask(), messageSnapshot);
                return;
            case -1:
                this.mThrowable = messageSnapshot.getThrowable();
                this.mSoFarBytes = messageSnapshot.getLargeSofarBytes();
                FileDownloadList.getImpl().remove(this.mTask.getRunningTask(), messageSnapshot);
                return;
            case 1:
                this.mSoFarBytes = messageSnapshot.getLargeSofarBytes();
                this.mTotalBytes = messageSnapshot.getLargeTotalBytes();
                this.mMessenger.notifyPending(messageSnapshot);
                return;
            case 2:
                this.mTotalBytes = messageSnapshot.getLargeTotalBytes();
                this.mIsResuming = messageSnapshot.isResuming();
                this.mEtag = messageSnapshot.getEtag();
                String fileName = messageSnapshot.getFileName();
                if (fileName != null) {
                    if (origin.getFilename() != null) {
                        FileDownloadLog.w(this, "already has mFilename[%s], but assign mFilename[%s] again", origin.getFilename(), fileName);
                    }
                    this.mTask.setFileName(fileName);
                }
                this.mSpeedMonitor.start(this.mSoFarBytes);
                this.mMessenger.notifyConnected(messageSnapshot);
                return;
            case 3:
                this.mSoFarBytes = messageSnapshot.getLargeSofarBytes();
                this.mSpeedMonitor.update(messageSnapshot.getLargeSofarBytes());
                this.mMessenger.notifyProgress(messageSnapshot);
                return;
            case 5:
                this.mSoFarBytes = messageSnapshot.getLargeSofarBytes();
                this.mThrowable = messageSnapshot.getThrowable();
                this.mRetryingTimes = messageSnapshot.getRetryingTimes();
                this.mSpeedMonitor.reset();
                this.mMessenger.notifyRetry(messageSnapshot);
                return;
            case 6:
                this.mMessenger.notifyStarted(messageSnapshot);
                return;
            default:
                return;
        }
    }

    public void onBegin() {
        if (FileDownloadMonitor.isValid()) {
            FileDownloadMonitor.getMonitor().onTaskBegin(this.mTask.getRunningTask().getOrigin());
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "filedownloader:lifecycle:start %s by %d ", toString(), Byte.valueOf(getStatus()));
        }
    }

    public void onIng() {
        if (FileDownloadMonitor.isValid() && getStatus() == 6) {
            FileDownloadMonitor.getMonitor().onTaskStarted(this.mTask.getRunningTask().getOrigin());
        }
    }

    public void onOver() {
        BaseDownloadTask origin = this.mTask.getRunningTask().getOrigin();
        if (FileDownloadMonitor.isValid()) {
            FileDownloadMonitor.getMonitor().onTaskOver(origin);
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "filedownloader:lifecycle:over %s by %d ", toString(), Byte.valueOf(getStatus()));
        }
        this.mSpeedMonitor.end(this.mSoFarBytes);
        if (this.mTask.getFinishListenerList() != null) {
            ArrayList arrayList = (ArrayList) this.mTask.getFinishListenerList().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((FinishListener) arrayList.get(i)).over(origin);
            }
        }
        FileDownloader.getImpl().getLostConnectedHandler().taskWorkFine(this.mTask.getRunningTask());
    }

    DownloadTaskHunter(ICaptureTask iCaptureTask, Object obj) {
        this.mPauseLock = obj;
        this.mTask = iCaptureTask;
        DownloadSpeedMonitor downloadSpeedMonitor = new DownloadSpeedMonitor();
        this.mSpeedMonitor = downloadSpeedMonitor;
        this.mSpeedLookup = downloadSpeedMonitor;
        this.mMessenger = new FileDownloadMessenger(iCaptureTask.getRunningTask(), this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        r0 = r8.mTask.getRunningTask();
        r1 = r0.getOrigin();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.liulishuo.filedownloader.FileDownloadMonitor.isValid() == false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        com.liulishuo.filedownloader.FileDownloadMonitor.getMonitor().onRequestStart(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        if (com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        com.liulishuo.filedownloader.util.FileDownloadLog.v(r8, "call start Url[%s], Path[%s] Listener[%s], Tag[%s]", r1.getUrl(), r1.getPath(), r1.getListener(), r1.getTag());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        prepare();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0069, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006c, code lost:
        com.liulishuo.filedownloader.FileDownloadList.getImpl().add(r0);
        com.liulishuo.filedownloader.FileDownloadList.getImpl().remove(r0, prepareErrorMessage(r1));
        r0 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void intoLaunchPool() {
        /*
            r8 = this;
            java.lang.Object r0 = r8.mPauseLock
            monitor-enter(r0)
            byte r1 = r8.mStatus     // Catch:{ all -> 0x009e }
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x0025
            java.lang.String r1 = "High concurrent cause, this task %d will not input to launch pool, because of the status isn't idle : %d"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x009e }
            int r5 = r8.getId()     // Catch:{ all -> 0x009e }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x009e }
            r2[r4] = r5     // Catch:{ all -> 0x009e }
            byte r4 = r8.mStatus     // Catch:{ all -> 0x009e }
            java.lang.Byte r4 = java.lang.Byte.valueOf(r4)     // Catch:{ all -> 0x009e }
            r2[r3] = r4     // Catch:{ all -> 0x009e }
            com.liulishuo.filedownloader.util.FileDownloadLog.w(r8, r1, r2)     // Catch:{ all -> 0x009e }
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            return
        L_0x0025:
            r1 = 10
            r8.mStatus = r1     // Catch:{ all -> 0x009e }
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            com.liulishuo.filedownloader.DownloadTaskHunter$ICaptureTask r0 = r8.mTask
            com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask r0 = r0.getRunningTask()
            com.liulishuo.filedownloader.BaseDownloadTask r1 = r0.getOrigin()
            boolean r5 = com.liulishuo.filedownloader.FileDownloadMonitor.isValid()
            if (r5 == 0) goto L_0x0041
            com.liulishuo.filedownloader.FileDownloadMonitor$IMonitor r5 = com.liulishuo.filedownloader.FileDownloadMonitor.getMonitor()
            r5.onRequestStart(r1)
        L_0x0041:
            boolean r5 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG
            if (r5 == 0) goto L_0x0066
            java.lang.String r5 = "call start Url[%s], Path[%s] Listener[%s], Tag[%s]"
            r6 = 4
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.String r7 = r1.getUrl()
            r6[r4] = r7
            java.lang.String r7 = r1.getPath()
            r6[r3] = r7
            com.liulishuo.filedownloader.FileDownloadListener r7 = r1.getListener()
            r6[r2] = r7
            r2 = 3
            java.lang.Object r1 = r1.getTag()
            r6[r2] = r1
            com.liulishuo.filedownloader.util.FileDownloadLog.v(r8, r5, r6)
        L_0x0066:
            r8.prepare()     // Catch:{ Throwable -> 0x006b }
            r0 = 1
            goto L_0x007f
        L_0x006b:
            r1 = move-exception
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.getImpl()
            r2.add(r0)
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.getImpl()
            com.liulishuo.filedownloader.message.MessageSnapshot r1 = r8.prepareErrorMessage(r1)
            r2.remove(r0, r1)
            r0 = 0
        L_0x007f:
            if (r0 == 0) goto L_0x0088
            com.liulishuo.filedownloader.FileDownloadTaskLauncher r0 = com.liulishuo.filedownloader.FileDownloadTaskLauncher.getImpl()
            r0.launch(r8)
        L_0x0088:
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG
            if (r0 == 0) goto L_0x009d
            java.lang.String r0 = "the task[%d] has been into the launch pool."
            java.lang.Object[] r1 = new java.lang.Object[r3]
            int r2 = r8.getId()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r4] = r2
            com.liulishuo.filedownloader.util.FileDownloadLog.v(r8, r0, r1)
        L_0x009d:
            return
        L_0x009e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.DownloadTaskHunter.intoLaunchPool():void");
    }

    public boolean pause() {
        if (FileDownloadStatus.isOver(getStatus())) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "High concurrent cause, Already is over, can't pause again, %d %d", Byte.valueOf(getStatus()), Integer.valueOf(this.mTask.getRunningTask().getOrigin().getId()));
            }
            return false;
        }
        this.mStatus = -2;
        IRunningTask runningTask = this.mTask.getRunningTask();
        BaseDownloadTask origin = runningTask.getOrigin();
        FileDownloadTaskLauncher.getImpl().expire(this);
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "the task[%d] has been expired from the launch pool.", Integer.valueOf(getId()));
        }
        if (FileDownloader.getImpl().isServiceConnected()) {
            FileDownloadServiceProxy.getImpl().pause(origin.getId());
        } else if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "request pause the task[%d] to the download service, but the download service isn't connected yet.", Integer.valueOf(origin.getId()));
        }
        FileDownloadList.getImpl().add(runningTask);
        FileDownloadList.getImpl().remove(runningTask, MessageSnapshotTaker.catchPause(origin));
        FileDownloader.getImpl().getLostConnectedHandler().taskWorkFine(runningTask);
        return true;
    }

    public byte getStatus() {
        return this.mStatus;
    }

    public long getSofarBytes() {
        return this.mSoFarBytes;
    }

    public long getTotalBytes() {
        return this.mTotalBytes;
    }

    public Throwable getErrorCause() {
        return this.mThrowable;
    }

    public int getRetryingTimes() {
        return this.mRetryingTimes;
    }

    public boolean isLargeFile() {
        return this.mIsLargeFile;
    }

    public void free() {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "free the task %d, when the status is %d", Integer.valueOf(getId()), Byte.valueOf(this.mStatus));
        }
        this.mStatus = 0;
    }

    private void prepare() throws IOException {
        File file;
        BaseDownloadTask origin = this.mTask.getRunningTask().getOrigin();
        if (origin.getPath() == null) {
            origin.setPath(FileDownloadUtils.getDefaultSaveFilePath(origin.getUrl()));
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "save Path is null to %s", origin.getPath());
            }
        }
        if (origin.isPathAsDirectory()) {
            file = new File(origin.getPath());
        } else {
            String parent = FileDownloadUtils.getParent(origin.getPath());
            if (parent != null) {
                file = new File(parent);
            } else {
                throw new InvalidParameterException(FileDownloadUtils.formatString("the provided mPath[%s] is invalid, can't find its directory", origin.getPath()));
            }
        }
        if (!file.exists() && !file.mkdirs() && !file.exists()) {
            throw new IOException(FileDownloadUtils.formatString("Create parent directory failed, please make sure you have permission to create file or directory on the path: %s", file.getAbsolutePath()));
        }
    }

    private int getId() {
        return this.mTask.getRunningTask().getOrigin().getId();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        com.liulishuo.filedownloader.FileDownloadList.getImpl().add(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007c, code lost:
        if (com.liulishuo.filedownloader.util.FileDownloadHelper.inspectAndInflowDownloaded(r0.getId(), r0.getTargetFilePath(), r0.isForceReDownload(), true) == false) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007f, code lost:
        r0 = com.liulishuo.filedownloader.FileDownloadServiceProxy.getImpl().start(r0.getUrl(), r0.getPath(), r0.isPathAsDirectory(), r0.getCallbackProgressTimes(), r0.getCallbackProgressMinInterval(), r0.getAutoRetryTimes(), r0.isForceReDownload(), r1.mTask.getHeader(), r0.isWifiRequired());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b0, code lost:
        if (r1.mStatus != -2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b2, code lost:
        com.liulishuo.filedownloader.util.FileDownloadLog.w(r1, "High concurrent cause, this task %d will be paused,because of the status is paused, so the pause action must be applied", java.lang.Integer.valueOf(getId()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c3, code lost:
        if (r0 == false) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c5, code lost:
        com.liulishuo.filedownloader.FileDownloadServiceProxy.getImpl().pause(getId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00d0, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d1, code lost:
        if (r0 != false) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d7, code lost:
        if (r7.dispatchTaskStart(r6) != false) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d9, code lost:
        r0 = prepareErrorMessage(new java.lang.RuntimeException("Occur Unknown Error, when request to start maybe some problem in binder, maybe the process was killed in unexpected."));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ec, code lost:
        if (com.liulishuo.filedownloader.FileDownloadList.getImpl().isNotContains(r6) == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ee, code lost:
        r7.taskWorkFine(r6);
        com.liulishuo.filedownloader.FileDownloadList.getImpl().add(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00f8, code lost:
        com.liulishuo.filedownloader.FileDownloadList.getImpl().remove(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0100, code lost:
        r7.taskWorkFine(r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void start() {
        /*
            r18 = this;
            r1 = r18
            byte r0 = r1.mStatus
            r2 = 2
            r3 = 10
            r4 = 0
            r5 = 1
            if (r0 == r3) goto L_0x0025
            java.lang.String r0 = "High concurrent cause, this task %d will not start, because the of status isn't toLaunchPool: %d"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            int r3 = r18.getId()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r4] = r3
            byte r3 = r1.mStatus
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)
            r2[r5] = r3
            com.liulishuo.filedownloader.util.FileDownloadLog.w(r1, r0, r2)
            return
        L_0x0025:
            com.liulishuo.filedownloader.DownloadTaskHunter$ICaptureTask r0 = r1.mTask
            com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask r6 = r0.getRunningTask()
            com.liulishuo.filedownloader.BaseDownloadTask r0 = r6.getOrigin()
            com.liulishuo.filedownloader.FileDownloader r7 = com.liulishuo.filedownloader.FileDownloader.getImpl()
            com.liulishuo.filedownloader.ILostServiceConnectedHandler r7 = r7.getLostConnectedHandler()
            boolean r8 = r7.dispatchTaskStart(r6)     // Catch:{ Throwable -> 0x0107 }
            if (r8 == 0) goto L_0x003e
            return
        L_0x003e:
            java.lang.Object r8 = r1.mPauseLock     // Catch:{ Throwable -> 0x0107 }
            monitor-enter(r8)     // Catch:{ Throwable -> 0x0107 }
            byte r9 = r1.mStatus     // Catch:{ all -> 0x0104 }
            if (r9 == r3) goto L_0x0060
            java.lang.String r0 = "High concurrent cause, this task %d will not start, the status can't assign to toFileDownloadService, because the status isn't toLaunchPool: %d"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0104 }
            int r3 = r18.getId()     // Catch:{ all -> 0x0104 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0104 }
            r2[r4] = r3     // Catch:{ all -> 0x0104 }
            byte r3 = r1.mStatus     // Catch:{ all -> 0x0104 }
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)     // Catch:{ all -> 0x0104 }
            r2[r5] = r3     // Catch:{ all -> 0x0104 }
            com.liulishuo.filedownloader.util.FileDownloadLog.w(r1, r0, r2)     // Catch:{ all -> 0x0104 }
            monitor-exit(r8)     // Catch:{ all -> 0x0104 }
            return
        L_0x0060:
            r2 = 11
            r1.mStatus = r2     // Catch:{ all -> 0x0104 }
            monitor-exit(r8)     // Catch:{ all -> 0x0104 }
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.getImpl()     // Catch:{ Throwable -> 0x0107 }
            r2.add(r6)     // Catch:{ Throwable -> 0x0107 }
            int r2 = r0.getId()     // Catch:{ Throwable -> 0x0107 }
            java.lang.String r3 = r0.getTargetFilePath()     // Catch:{ Throwable -> 0x0107 }
            boolean r8 = r0.isForceReDownload()     // Catch:{ Throwable -> 0x0107 }
            boolean r2 = com.liulishuo.filedownloader.util.FileDownloadHelper.inspectAndInflowDownloaded(r2, r3, r8, r5)     // Catch:{ Throwable -> 0x0107 }
            if (r2 == 0) goto L_0x007f
            return
        L_0x007f:
            com.liulishuo.filedownloader.FileDownloadServiceProxy r8 = com.liulishuo.filedownloader.FileDownloadServiceProxy.getImpl()     // Catch:{ Throwable -> 0x0107 }
            java.lang.String r9 = r0.getUrl()     // Catch:{ Throwable -> 0x0107 }
            java.lang.String r10 = r0.getPath()     // Catch:{ Throwable -> 0x0107 }
            boolean r11 = r0.isPathAsDirectory()     // Catch:{ Throwable -> 0x0107 }
            int r12 = r0.getCallbackProgressTimes()     // Catch:{ Throwable -> 0x0107 }
            int r13 = r0.getCallbackProgressMinInterval()     // Catch:{ Throwable -> 0x0107 }
            int r14 = r0.getAutoRetryTimes()     // Catch:{ Throwable -> 0x0107 }
            boolean r15 = r0.isForceReDownload()     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.DownloadTaskHunter$ICaptureTask r2 = r1.mTask     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.model.FileDownloadHeader r16 = r2.getHeader()     // Catch:{ Throwable -> 0x0107 }
            boolean r17 = r0.isWifiRequired()     // Catch:{ Throwable -> 0x0107 }
            boolean r0 = r8.start(r9, r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ Throwable -> 0x0107 }
            byte r2 = r1.mStatus     // Catch:{ Throwable -> 0x0107 }
            r3 = -2
            if (r2 != r3) goto L_0x00d1
            java.lang.String r2 = "High concurrent cause, this task %d will be paused,because of the status is paused, so the pause action must be applied"
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0107 }
            int r5 = r18.getId()     // Catch:{ Throwable -> 0x0107 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x0107 }
            r3[r4] = r5     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.util.FileDownloadLog.w(r1, r2, r3)     // Catch:{ Throwable -> 0x0107 }
            if (r0 == 0) goto L_0x00d0
            com.liulishuo.filedownloader.FileDownloadServiceProxy r0 = com.liulishuo.filedownloader.FileDownloadServiceProxy.getImpl()     // Catch:{ Throwable -> 0x0107 }
            int r2 = r18.getId()     // Catch:{ Throwable -> 0x0107 }
            r0.pause(r2)     // Catch:{ Throwable -> 0x0107 }
        L_0x00d0:
            return
        L_0x00d1:
            if (r0 != 0) goto L_0x0100
            boolean r0 = r7.dispatchTaskStart(r6)     // Catch:{ Throwable -> 0x0107 }
            if (r0 != 0) goto L_0x0116
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ Throwable -> 0x0107 }
            java.lang.String r2 = "Occur Unknown Error, when request to start maybe some problem in binder, maybe the process was killed in unexpected."
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.message.MessageSnapshot r0 = r1.prepareErrorMessage(r0)     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.getImpl()     // Catch:{ Throwable -> 0x0107 }
            boolean r2 = r2.isNotContains(r6)     // Catch:{ Throwable -> 0x0107 }
            if (r2 == 0) goto L_0x00f8
            r7.taskWorkFine(r6)     // Catch:{ Throwable -> 0x0107 }
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.getImpl()     // Catch:{ Throwable -> 0x0107 }
            r2.add(r6)     // Catch:{ Throwable -> 0x0107 }
        L_0x00f8:
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.getImpl()     // Catch:{ Throwable -> 0x0107 }
            r2.remove(r6, r0)     // Catch:{ Throwable -> 0x0107 }
            goto L_0x0116
        L_0x0100:
            r7.taskWorkFine(r6)     // Catch:{ Throwable -> 0x0107 }
            goto L_0x0116
        L_0x0104:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0104 }
            throw r0     // Catch:{ Throwable -> 0x0107 }
        L_0x0107:
            r0 = move-exception
            r0.printStackTrace()
            com.liulishuo.filedownloader.FileDownloadList r2 = com.liulishuo.filedownloader.FileDownloadList.getImpl()
            com.liulishuo.filedownloader.message.MessageSnapshot r0 = r1.prepareErrorMessage(r0)
            r2.remove(r6, r0)
        L_0x0116:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.DownloadTaskHunter.start():void");
    }
}

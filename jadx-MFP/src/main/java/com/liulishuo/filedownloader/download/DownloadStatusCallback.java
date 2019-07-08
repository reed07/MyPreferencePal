package com.liulishuo.filedownloader.download;

import android.database.sqlite.SQLiteFullException;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

public class DownloadStatusCallback implements Callback {
    private final AtomicLong callbackIncreaseBuffer = new AtomicLong();
    private long callbackMinIntervalBytes;
    private final int callbackProgressMaxCount;
    private final int callbackProgressMinInterval;
    private final FileDownloadDatabase database;
    private Handler handler;
    private HandlerThread handlerThread;
    private volatile boolean handlingMessage = false;
    private final AtomicBoolean isFirstCallback = new AtomicBoolean(true);
    private volatile long lastCallbackTimestamp = 0;
    private final int maxRetryTimes;
    private final FileDownloadModel model;
    private final AtomicBoolean needCallbackProgressToUser = new AtomicBoolean(false);
    private final AtomicBoolean needSetProcess = new AtomicBoolean(false);
    private volatile Thread parkThread;
    private final ProcessParams processParams;

    public static class ProcessParams {
        private Exception exception;
        private boolean isResuming;
        private int retryingTimes;

        /* access modifiers changed from: 0000 */
        public void setResuming(boolean z) {
            this.isResuming = z;
        }

        public boolean isResuming() {
            return this.isResuming;
        }

        /* access modifiers changed from: 0000 */
        public void setException(Exception exc) {
            this.exception = exc;
        }

        /* access modifiers changed from: 0000 */
        public void setRetryingTimes(int i) {
            this.retryingTimes = i;
        }

        public Exception getException() {
            return this.exception;
        }

        public int getRetryingTimes() {
            return this.retryingTimes;
        }
    }

    DownloadStatusCallback(FileDownloadModel fileDownloadModel, int i, int i2, int i3) {
        this.model = fileDownloadModel;
        this.database = CustomComponentHolder.getImpl().getDatabaseInstance();
        int i4 = 5;
        if (i2 >= 5) {
            i4 = i2;
        }
        this.callbackProgressMinInterval = i4;
        this.callbackProgressMaxCount = i3;
        this.processParams = new ProcessParams();
        this.maxRetryTimes = i;
    }

    public boolean isAlive() {
        HandlerThread handlerThread2 = this.handlerThread;
        return handlerThread2 != null && handlerThread2.isAlive();
    }

    /* access modifiers changed from: 0000 */
    public void discardAllMessage() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
            this.handlerThread.quit();
            this.parkThread = Thread.currentThread();
            while (this.handlingMessage) {
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
            }
            this.parkThread = null;
        }
    }

    public void onPending() {
        this.model.setStatus(1);
        this.database.updatePending(this.model.getId());
        onStatusChanged(1);
    }

    /* access modifiers changed from: 0000 */
    public void onStartThread() {
        this.model.setStatus(6);
        onStatusChanged(6);
        this.database.onTaskStart(this.model.getId());
    }

    /* access modifiers changed from: 0000 */
    public void onConnected(boolean z, long j, String str, String str2) throws IllegalArgumentException {
        String eTag = this.model.getETag();
        if (eTag == null || eTag.equals(str)) {
            this.processParams.setResuming(z);
            this.model.setStatus(2);
            this.model.setTotal(j);
            this.model.setETag(str);
            this.model.setFilename(str2);
            this.database.updateConnected(this.model.getId(), j, str, str2);
            onStatusChanged(2);
            this.callbackMinIntervalBytes = calculateCallbackMinIntervalBytes(j, (long) this.callbackProgressMaxCount);
            this.needSetProcess.compareAndSet(false, true);
            return;
        }
        throw new IllegalArgumentException(FileDownloadUtils.formatString("callback onConnected must with precondition succeed, but the etag is changes(%s != %s)", str, eTag));
    }

    /* access modifiers changed from: 0000 */
    public void onMultiConnection() {
        this.handlerThread = new HandlerThread("source-status-callback");
        this.handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper(), this);
    }

    /* access modifiers changed from: 0000 */
    public void onProgress(long j) {
        this.callbackIncreaseBuffer.addAndGet(j);
        this.model.increaseSoFar(j);
        inspectNeedCallbackToUser(SystemClock.elapsedRealtime());
        if (this.handler == null) {
            handleProgress();
        } else if (this.needCallbackProgressToUser.get()) {
            sendMessage(this.handler.obtainMessage(3));
        }
    }

    /* access modifiers changed from: 0000 */
    public void onRetry(Exception exc, int i) {
        this.callbackIncreaseBuffer.set(0);
        Handler handler2 = this.handler;
        if (handler2 == null) {
            handleRetry(exc, i);
        } else {
            sendMessage(handler2.obtainMessage(5, i, 0, exc));
        }
    }

    /* access modifiers changed from: 0000 */
    public void onPausedDirectly() {
        handlePaused();
    }

    /* access modifiers changed from: 0000 */
    public void onErrorDirectly(Exception exc) {
        handleError(exc);
    }

    /* access modifiers changed from: 0000 */
    public void onCompletedDirectly() throws IOException {
        if (!interceptBeforeCompleted()) {
            handleCompleted();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void sendMessage(android.os.Message r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            android.os.HandlerThread r0 = r4.handlerThread     // Catch:{ all -> 0x0045 }
            boolean r0 = r0.isAlive()     // Catch:{ all -> 0x0045 }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0020
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x0045 }
            if (r0 == 0) goto L_0x001e
            java.lang.String r0 = "require callback %d but the host thread of the flow has already dead, what is occurred because of there are several reason can final this flow on different thread."
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0045 }
            int r5 = r5.what     // Catch:{ all -> 0x0045 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0045 }
            r2[r1] = r5     // Catch:{ all -> 0x0045 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r4, r0, r2)     // Catch:{ all -> 0x0045 }
        L_0x001e:
            monitor-exit(r4)
            return
        L_0x0020:
            android.os.Handler r0 = r4.handler     // Catch:{ IllegalStateException -> 0x0026 }
            r0.sendMessage(r5)     // Catch:{ IllegalStateException -> 0x0026 }
            goto L_0x0042
        L_0x0026:
            r0 = move-exception
            android.os.HandlerThread r3 = r4.handlerThread     // Catch:{ all -> 0x0045 }
            boolean r3 = r3.isAlive()     // Catch:{ all -> 0x0045 }
            if (r3 != 0) goto L_0x0044
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x0045 }
            if (r0 == 0) goto L_0x0042
            java.lang.String r0 = "require callback %d but the host thread of the flow has already dead, what is occurred because of there are several reason can final this flow on different thread."
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0045 }
            int r5 = r5.what     // Catch:{ all -> 0x0045 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0045 }
            r2[r1] = r5     // Catch:{ all -> 0x0045 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r4, r0, r2)     // Catch:{ all -> 0x0045 }
        L_0x0042:
            monitor-exit(r4)
            return
        L_0x0044:
            throw r0     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.DownloadStatusCallback.sendMessage(android.os.Message):void");
    }

    private static long calculateCallbackMinIntervalBytes(long j, long j2) {
        if (j2 <= 0) {
            return -1;
        }
        if (j == -1) {
            return 1;
        }
        long j3 = j / j2;
        if (j3 <= 0) {
            j3 = 1;
        }
        return j3;
    }

    private Exception exFiltrate(Exception exc) {
        long j;
        String tempFilePath = this.model.getTempFilePath();
        if ((!this.model.isChunked() && !FileDownloadProperties.getImpl().fileNonPreAllocation) || !(exc instanceof IOException) || !new File(tempFilePath).exists()) {
            return exc;
        }
        long freeSpaceBytes = FileDownloadUtils.getFreeSpaceBytes(tempFilePath);
        if (freeSpaceBytes > 4096) {
            return exc;
        }
        File file = new File(tempFilePath);
        if (!file.exists()) {
            FileDownloadLog.e(this, exc, "Exception with: free space isn't enough, and the target file not exist.", new Object[0]);
            j = 0;
        } else {
            j = file.length();
        }
        if (VERSION.SDK_INT >= 9) {
            FileDownloadOutOfSpaceException fileDownloadOutOfSpaceException = new FileDownloadOutOfSpaceException(freeSpaceBytes, 4096, j, exc);
            return fileDownloadOutOfSpaceException;
        }
        FileDownloadOutOfSpaceException fileDownloadOutOfSpaceException2 = new FileDownloadOutOfSpaceException(freeSpaceBytes, 4096, j);
        return fileDownloadOutOfSpaceException2;
    }

    private void handleSQLiteFullException(SQLiteFullException sQLiteFullException) {
        int id = this.model.getId();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "the data of the task[%d] is dirty, because the SQLite full exception[%s], so remove it from the database directly.", Integer.valueOf(id), sQLiteFullException.toString());
        }
        this.model.setErrMsg(sQLiteFullException.toString());
        this.model.setStatus(-1);
        this.database.remove(id);
        this.database.removeConnections(id);
    }

    private void renameTempFile() throws IOException {
        boolean z;
        String tempFilePath = this.model.getTempFilePath();
        String targetFilePath = this.model.getTargetFilePath();
        File file = new File(tempFilePath);
        try {
            File file2 = new File(targetFilePath);
            if (file2.exists()) {
                long length = file2.length();
                if (file2.delete()) {
                    FileDownloadLog.w(this, "The target file([%s], [%d]) will be replaced with the new downloaded file[%d]", targetFilePath, Long.valueOf(length), Long.valueOf(file.length()));
                } else {
                    throw new IOException(FileDownloadUtils.formatString("Can't delete the old file([%s], [%d]), so can't replace it with the new downloaded one.", targetFilePath, Long.valueOf(length)));
                }
            }
            z = !file.renameTo(file2);
            if (z) {
                try {
                    throw new IOException(FileDownloadUtils.formatString("Can't rename the  temp downloaded file(%s) to the target file(%s)", tempFilePath, targetFilePath));
                } catch (Throwable th) {
                    th = th;
                    if (z && file.exists() && !file.delete()) {
                        FileDownloadLog.w(this, "delete the temp file(%s) failed, on completed downloading.", tempFilePath);
                    }
                    throw th;
                }
            } else if (z && file.exists() && !file.delete()) {
                FileDownloadLog.w(this, "delete the temp file(%s) failed, on completed downloading.", tempFilePath);
            }
        } catch (Throwable th2) {
            th = th2;
            z = true;
            FileDownloadLog.w(this, "delete the temp file(%s) failed, on completed downloading.", tempFilePath);
            throw th;
        }
    }

    public boolean handleMessage(Message message) {
        this.handlingMessage = true;
        int i = message.what;
        if (i == 3) {
            handleProgress();
        } else if (i == 5) {
            try {
                handleRetry((Exception) message.obj, message.arg1);
            } catch (Throwable th) {
                this.handlingMessage = false;
                if (this.parkThread != null) {
                    LockSupport.unpark(this.parkThread);
                }
                throw th;
            }
        }
        this.handlingMessage = false;
        if (this.parkThread != null) {
            LockSupport.unpark(this.parkThread);
        }
        return true;
    }

    private void handleProgress() {
        if (this.model.getSoFar() == this.model.getTotal()) {
            this.database.updateProgress(this.model.getId(), this.model.getSoFar());
            return;
        }
        if (this.needSetProcess.compareAndSet(true, false)) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.i(this, "handleProgress update model's status with progress", new Object[0]);
            }
            this.model.setStatus(3);
        }
        if (this.needCallbackProgressToUser.compareAndSet(true, false)) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.i(this, "handleProgress notify user progress status", new Object[0]);
            }
            onStatusChanged(3);
        }
    }

    private void handleCompleted() throws IOException {
        renameTempFile();
        this.model.setStatus(-3);
        this.database.updateCompleted(this.model.getId(), this.model.getTotal());
        this.database.removeConnections(this.model.getId());
        onStatusChanged(-3);
        if (FileDownloadProperties.getImpl().broadcastCompleted) {
            FileDownloadBroadcastHandler.sendCompletedBroadcast(this.model);
        }
    }

    private boolean interceptBeforeCompleted() {
        if (this.model.isChunked()) {
            FileDownloadModel fileDownloadModel = this.model;
            fileDownloadModel.setTotal(fileDownloadModel.getSoFar());
        } else if (this.model.getSoFar() != this.model.getTotal()) {
            onErrorDirectly(new FileDownloadGiveUpRetryException(FileDownloadUtils.formatString("sofar[%d] not equal total[%d]", Long.valueOf(this.model.getSoFar()), Long.valueOf(this.model.getTotal()))));
            return true;
        }
        return false;
    }

    private void handleRetry(Exception exc, int i) {
        Exception exFiltrate = exFiltrate(exc);
        this.processParams.setException(exFiltrate);
        this.processParams.setRetryingTimes(this.maxRetryTimes - i);
        this.model.setStatus(5);
        this.model.setErrMsg(exFiltrate.toString());
        this.database.updateRetry(this.model.getId(), exFiltrate);
        onStatusChanged(5);
    }

    private void handlePaused() {
        this.model.setStatus(-2);
        this.database.updatePause(this.model.getId(), this.model.getSoFar());
        onStatusChanged(-2);
    }

    private void handleError(Exception exc) {
        Exception exFiltrate = exFiltrate(exc);
        if (exFiltrate instanceof SQLiteFullException) {
            handleSQLiteFullException((SQLiteFullException) exFiltrate);
        } else {
            try {
                this.model.setStatus(-1);
                this.model.setErrMsg(exc.toString());
                this.database.updateError(this.model.getId(), exFiltrate, this.model.getSoFar());
            } catch (SQLiteFullException e) {
                exFiltrate = e;
                handleSQLiteFullException((SQLiteFullException) exFiltrate);
            }
        }
        this.processParams.setException(exFiltrate);
        onStatusChanged(-1);
    }

    private void inspectNeedCallbackToUser(long j) {
        boolean z;
        if (this.isFirstCallback.compareAndSet(true, false)) {
            z = true;
        } else {
            z = this.callbackMinIntervalBytes != -1 && this.callbackIncreaseBuffer.get() >= this.callbackMinIntervalBytes && j - this.lastCallbackTimestamp >= ((long) this.callbackProgressMinInterval);
        }
        if (z && this.needCallbackProgressToUser.compareAndSet(false, true)) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.i(this, "inspectNeedCallbackToUser need callback to user", new Object[0]);
            }
            this.lastCallbackTimestamp = j;
            this.callbackIncreaseBuffer.set(0);
        }
    }

    private void onStatusChanged(byte b) {
        if (b == -2) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "High concurrent cause, Already paused and we don't need to call-back to Task in here, %d", Integer.valueOf(this.model.getId()));
            }
            return;
        }
        MessageSnapshotFlow.getImpl().inflow(MessageSnapshotTaker.take(b, this.model, this.processParams));
    }
}

package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.IThreadPoolMonitor;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.download.CustomComponentHolder;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.List;

class FileDownloadManager implements IThreadPoolMonitor {
    private final FileDownloadDatabase mDatabase;
    private final FileDownloadThreadPool mThreadPool;

    FileDownloadManager() {
        CustomComponentHolder impl = CustomComponentHolder.getImpl();
        this.mDatabase = impl.getDatabaseInstance();
        this.mThreadPool = new FileDownloadThreadPool(impl.getMaxNetworkThreadCount());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0088, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b2, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f8, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0185  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void start(java.lang.String r19, java.lang.String r20, boolean r21, int r22, int r23, int r24, boolean r25, com.liulishuo.filedownloader.model.FileDownloadHeader r26, boolean r27) {
        /*
            r18 = this;
            r7 = r18
            r0 = r19
            r8 = r20
            r9 = r21
            monitor-enter(r18)
            boolean r1 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x01d0 }
            r10 = 2
            r11 = 0
            r12 = 1
            if (r1 == 0) goto L_0x0022
            java.lang.String r1 = "request start the task with url(%s) path(%s) isDirectory(%B)"
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x01d0 }
            r2[r11] = r0     // Catch:{ all -> 0x01d0 }
            r2[r12] = r8     // Catch:{ all -> 0x01d0 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r21)     // Catch:{ all -> 0x01d0 }
            r2[r10] = r3     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r7, r1, r2)     // Catch:{ all -> 0x01d0 }
        L_0x0022:
            int r13 = com.liulishuo.filedownloader.util.FileDownloadUtils.generateId(r19, r20, r21)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.database.FileDownloadDatabase r1 = r7.mDatabase     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.model.FileDownloadModel r1 = r1.find(r13)     // Catch:{ all -> 0x01d0 }
            r2 = 0
            if (r9 != 0) goto L_0x006e
            if (r1 != 0) goto L_0x006e
            java.lang.String r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.getParent(r20)     // Catch:{ all -> 0x01d0 }
            int r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.generateId(r0, r1, r12)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.database.FileDownloadDatabase r3 = r7.mDatabase     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.model.FileDownloadModel r3 = r3.find(r1)     // Catch:{ all -> 0x01d0 }
            if (r3 == 0) goto L_0x006b
            java.lang.String r4 = r3.getTargetFilePath()     // Catch:{ all -> 0x01d0 }
            boolean r4 = r8.equals(r4)     // Catch:{ all -> 0x01d0 }
            if (r4 == 0) goto L_0x006b
            boolean r4 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x01d0 }
            if (r4 == 0) goto L_0x0062
            java.lang.String r4 = "task[%d] find model by dirCaseId[%d]"
            java.lang.Object[] r5 = new java.lang.Object[r10]     // Catch:{ all -> 0x01d0 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x01d0 }
            r5[r11] = r6     // Catch:{ all -> 0x01d0 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x01d0 }
            r5[r12] = r6     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r7, r4, r5)     // Catch:{ all -> 0x01d0 }
        L_0x0062:
            com.liulishuo.filedownloader.database.FileDownloadDatabase r4 = r7.mDatabase     // Catch:{ all -> 0x01d0 }
            java.util.List r1 = r4.findConnectionModel(r1)     // Catch:{ all -> 0x01d0 }
            r15 = r1
            r14 = r3
            goto L_0x0070
        L_0x006b:
            r15 = r2
            r14 = r3
            goto L_0x0070
        L_0x006e:
            r14 = r1
            r15 = r2
        L_0x0070:
            boolean r1 = com.liulishuo.filedownloader.util.FileDownloadHelper.inspectAndInflowDownloading(r13, r14, r7, r12)     // Catch:{ all -> 0x01d0 }
            if (r1 == 0) goto L_0x0089
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x01d0 }
            if (r0 == 0) goto L_0x0087
            java.lang.String r0 = "has already started download %d"
            java.lang.Object[] r1 = new java.lang.Object[r12]     // Catch:{ all -> 0x01d0 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x01d0 }
            r1[r11] = r2     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r7, r0, r1)     // Catch:{ all -> 0x01d0 }
        L_0x0087:
            monitor-exit(r18)
            return
        L_0x0089:
            if (r14 == 0) goto L_0x0093
            java.lang.String r1 = r14.getTargetFilePath()     // Catch:{ all -> 0x01d0 }
            r6 = r25
            r5 = r1
            goto L_0x009a
        L_0x0093:
            java.lang.String r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.getTargetFilePath(r8, r9, r2)     // Catch:{ all -> 0x01d0 }
            r6 = r25
            r5 = r1
        L_0x009a:
            boolean r1 = com.liulishuo.filedownloader.util.FileDownloadHelper.inspectAndInflowDownloaded(r13, r5, r6, r12)     // Catch:{ all -> 0x01d0 }
            if (r1 == 0) goto L_0x00b3
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x01d0 }
            if (r0 == 0) goto L_0x00b1
            java.lang.String r0 = "has already completed downloading %d"
            java.lang.Object[] r1 = new java.lang.Object[r12]     // Catch:{ all -> 0x01d0 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x01d0 }
            r1[r11] = r2     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r7, r0, r1)     // Catch:{ all -> 0x01d0 }
        L_0x00b1:
            monitor-exit(r18)
            return
        L_0x00b3:
            r2 = 0
            if (r14 == 0) goto L_0x00bc
            long r16 = r14.getSoFar()     // Catch:{ all -> 0x01d0 }
            goto L_0x00be
        L_0x00bc:
            r16 = r2
        L_0x00be:
            if (r14 == 0) goto L_0x00c6
            java.lang.String r1 = r14.getTempFilePath()     // Catch:{ all -> 0x01d0 }
            r4 = r1
            goto L_0x00cb
        L_0x00c6:
            java.lang.String r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.getTempPath(r5)     // Catch:{ all -> 0x01d0 }
            r4 = r1
        L_0x00cb:
            r1 = r13
            r2 = r16
            r16 = r5
            r6 = r18
            boolean r1 = com.liulishuo.filedownloader.util.FileDownloadHelper.inspectAndInflowConflictPath(r1, r2, r4, r5, r6)     // Catch:{ all -> 0x01d0 }
            if (r1 == 0) goto L_0x00f9
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x01d0 }
            if (r0 == 0) goto L_0x00eb
            java.lang.String r0 = "there is an another task with the same target-file-path %d %s"
            java.lang.Object[] r1 = new java.lang.Object[r10]     // Catch:{ all -> 0x01d0 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x01d0 }
            r1[r11] = r2     // Catch:{ all -> 0x01d0 }
            r1[r12] = r16     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r7, r0, r1)     // Catch:{ all -> 0x01d0 }
        L_0x00eb:
            if (r14 == 0) goto L_0x00f7
            com.liulishuo.filedownloader.database.FileDownloadDatabase r0 = r7.mDatabase     // Catch:{ all -> 0x01d0 }
            r0.remove(r13)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.database.FileDownloadDatabase r0 = r7.mDatabase     // Catch:{ all -> 0x01d0 }
            r0.removeConnections(r13)     // Catch:{ all -> 0x01d0 }
        L_0x00f7:
            monitor-exit(r18)
            return
        L_0x00f9:
            if (r14 == 0) goto L_0x0165
            byte r1 = r14.getStatus()     // Catch:{ all -> 0x01d0 }
            r2 = -2
            if (r1 == r2) goto L_0x011c
            byte r1 = r14.getStatus()     // Catch:{ all -> 0x01d0 }
            r2 = -1
            if (r1 == r2) goto L_0x011c
            byte r1 = r14.getStatus()     // Catch:{ all -> 0x01d0 }
            if (r1 == r12) goto L_0x011c
            byte r1 = r14.getStatus()     // Catch:{ all -> 0x01d0 }
            r2 = 6
            if (r1 == r2) goto L_0x011c
            byte r1 = r14.getStatus()     // Catch:{ all -> 0x01d0 }
            if (r1 != r10) goto L_0x0165
        L_0x011c:
            int r1 = r14.getId()     // Catch:{ all -> 0x01d0 }
            if (r1 == r13) goto L_0x0155
            com.liulishuo.filedownloader.database.FileDownloadDatabase r0 = r7.mDatabase     // Catch:{ all -> 0x01d0 }
            int r1 = r14.getId()     // Catch:{ all -> 0x01d0 }
            r0.remove(r1)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.database.FileDownloadDatabase r0 = r7.mDatabase     // Catch:{ all -> 0x01d0 }
            int r1 = r14.getId()     // Catch:{ all -> 0x01d0 }
            r0.removeConnections(r1)     // Catch:{ all -> 0x01d0 }
            r14.setId(r13)     // Catch:{ all -> 0x01d0 }
            r14.setPath(r8, r9)     // Catch:{ all -> 0x01d0 }
            if (r15 == 0) goto L_0x0183
            java.util.Iterator r0 = r15.iterator()     // Catch:{ all -> 0x01d0 }
        L_0x0140:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x01d0 }
            if (r1 == 0) goto L_0x0183
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.model.ConnectionModel r1 = (com.liulishuo.filedownloader.model.ConnectionModel) r1     // Catch:{ all -> 0x01d0 }
            r1.setId(r13)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.database.FileDownloadDatabase r2 = r7.mDatabase     // Catch:{ all -> 0x01d0 }
            r2.insertConnectionModel(r1)     // Catch:{ all -> 0x01d0 }
            goto L_0x0140
        L_0x0155:
            java.lang.String r1 = r14.getUrl()     // Catch:{ all -> 0x01d0 }
            boolean r1 = android.text.TextUtils.equals(r0, r1)     // Catch:{ all -> 0x01d0 }
            if (r1 != 0) goto L_0x0163
            r14.setUrl(r0)     // Catch:{ all -> 0x01d0 }
            goto L_0x0183
        L_0x0163:
            r12 = 0
            goto L_0x0183
        L_0x0165:
            if (r14 != 0) goto L_0x016c
            com.liulishuo.filedownloader.model.FileDownloadModel r14 = new com.liulishuo.filedownloader.model.FileDownloadModel     // Catch:{ all -> 0x01d0 }
            r14.<init>()     // Catch:{ all -> 0x01d0 }
        L_0x016c:
            r14.setUrl(r0)     // Catch:{ all -> 0x01d0 }
            r14.setPath(r8, r9)     // Catch:{ all -> 0x01d0 }
            r14.setId(r13)     // Catch:{ all -> 0x01d0 }
            r0 = 0
            r14.setSoFar(r0)     // Catch:{ all -> 0x01d0 }
            r14.setTotal(r0)     // Catch:{ all -> 0x01d0 }
            r14.setStatus(r12)     // Catch:{ all -> 0x01d0 }
            r14.setConnectionCount(r12)     // Catch:{ all -> 0x01d0 }
        L_0x0183:
            if (r12 == 0) goto L_0x018a
            com.liulishuo.filedownloader.database.FileDownloadDatabase r0 = r7.mDatabase     // Catch:{ all -> 0x01d0 }
            r0.update(r14)     // Catch:{ all -> 0x01d0 }
        L_0x018a:
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = new com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder     // Catch:{ all -> 0x01d0 }
            r0.<init>()     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.setModel(r14)     // Catch:{ all -> 0x01d0 }
            r1 = r26
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.setHeader(r1)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.setThreadPoolMonitor(r7)     // Catch:{ all -> 0x01d0 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r23)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.setMinIntervalMillis(r1)     // Catch:{ all -> 0x01d0 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r22)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.setCallbackProgressMaxCount(r1)     // Catch:{ all -> 0x01d0 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r25)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.setForceReDownload(r1)     // Catch:{ all -> 0x01d0 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r27)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.setWifiRequired(r1)     // Catch:{ all -> 0x01d0 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r24)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable$Builder r0 = r0.setMaxRetryTimes(r1)     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable r0 = r0.build()     // Catch:{ all -> 0x01d0 }
            com.liulishuo.filedownloader.services.FileDownloadThreadPool r1 = r7.mThreadPool     // Catch:{ all -> 0x01d0 }
            r1.execute(r0)     // Catch:{ all -> 0x01d0 }
            monitor-exit(r18)
            return
        L_0x01d0:
            r0 = move-exception
            monitor-exit(r18)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.services.FileDownloadManager.start(java.lang.String, java.lang.String, boolean, int, int, int, boolean, com.liulishuo.filedownloader.model.FileDownloadHeader, boolean):void");
    }

    public boolean isDownloading(String str, String str2) {
        return isDownloading(FileDownloadUtils.generateId(str, str2));
    }

    public boolean isDownloading(int i) {
        return isDownloading(this.mDatabase.find(i));
    }

    public boolean pause(int i) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "request pause the task %d", Integer.valueOf(i));
        }
        FileDownloadModel find = this.mDatabase.find(i);
        if (find == null) {
            return false;
        }
        find.setStatus(-2);
        this.mThreadPool.cancel(i);
        return true;
    }

    public void pauseAll() {
        List<Integer> allExactRunningDownloadIds = this.mThreadPool.getAllExactRunningDownloadIds();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "pause all tasks %d", Integer.valueOf(allExactRunningDownloadIds.size()));
        }
        for (Integer intValue : allExactRunningDownloadIds) {
            pause(intValue.intValue());
        }
    }

    public long getSoFar(int i) {
        FileDownloadModel find = this.mDatabase.find(i);
        if (find == null) {
            return 0;
        }
        int connectionCount = find.getConnectionCount();
        if (connectionCount <= 1) {
            return find.getSoFar();
        }
        List findConnectionModel = this.mDatabase.findConnectionModel(i);
        if (findConnectionModel == null || findConnectionModel.size() != connectionCount) {
            return 0;
        }
        return ConnectionModel.getTotalOffset(findConnectionModel);
    }

    public long getTotal(int i) {
        FileDownloadModel find = this.mDatabase.find(i);
        if (find == null) {
            return 0;
        }
        return find.getTotal();
    }

    public byte getStatus(int i) {
        FileDownloadModel find = this.mDatabase.find(i);
        if (find == null) {
            return 0;
        }
        return find.getStatus();
    }

    public boolean isIdle() {
        return this.mThreadPool.exactSize() <= 0;
    }

    public synchronized boolean setMaxNetworkThreadCount(int i) {
        return this.mThreadPool.setMaxNetworkThreadCount(i);
    }

    public boolean isDownloading(FileDownloadModel fileDownloadModel) {
        boolean z = false;
        if (fileDownloadModel == null) {
            return false;
        }
        boolean isInThreadPool = this.mThreadPool.isInThreadPool(fileDownloadModel.getId());
        if (FileDownloadStatus.isOver(fileDownloadModel.getStatus())) {
            if (isInThreadPool) {
                z = true;
            }
        } else if (isInThreadPool) {
            z = true;
        } else {
            FileDownloadLog.e(this, "%d status is[%s](not finish) & but not in the pool", Integer.valueOf(fileDownloadModel.getId()), Byte.valueOf(fileDownloadModel.getStatus()));
        }
        return z;
    }

    public int findRunningTaskIdBySameTempPath(String str, int i) {
        return this.mThreadPool.findRunningTaskIdBySameTempPath(str, i);
    }

    public boolean clearTaskData(int i) {
        if (i == 0) {
            FileDownloadLog.w(this, "The task[%d] id is invalid, can't clear it.", Integer.valueOf(i));
            return false;
        } else if (isDownloading(i)) {
            FileDownloadLog.w(this, "The task[%d] is downloading, can't clear it.", Integer.valueOf(i));
            return false;
        } else {
            this.mDatabase.remove(i);
            this.mDatabase.removeConnections(i);
            return true;
        }
    }

    public void clearAllTaskData() {
        this.mDatabase.clear();
    }
}

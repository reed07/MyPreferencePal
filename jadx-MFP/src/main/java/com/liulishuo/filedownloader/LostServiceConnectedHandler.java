package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ConnectStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LostServiceConnectedHandler extends FileDownloadConnectListener implements ILostServiceConnectedHandler {
    private final ArrayList<IRunningTask> mWaitingList = new ArrayList<>();

    public void connected() {
        IQueuesHandler queuesHandler = FileDownloader.getImpl().getQueuesHandler();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "The downloader service is connected.", new Object[0]);
        }
        synchronized (this.mWaitingList) {
            List<IRunningTask> list = (List) this.mWaitingList.clone();
            this.mWaitingList.clear();
            ArrayList arrayList = new ArrayList(queuesHandler.serialQueueSize());
            for (IRunningTask iRunningTask : list) {
                int attachKey = iRunningTask.getAttachKey();
                if (queuesHandler.contain(attachKey)) {
                    iRunningTask.getOrigin().asInQueueTask().enqueue();
                    if (!arrayList.contains(Integer.valueOf(attachKey))) {
                        arrayList.add(Integer.valueOf(attachKey));
                    }
                } else {
                    iRunningTask.startTaskByRescue();
                }
            }
            queuesHandler.unFreezeSerialQueues(arrayList);
        }
    }

    public void disconnected() {
        if (getConnectStatus() == ConnectStatus.lost) {
            IQueuesHandler queuesHandler = FileDownloader.getImpl().getQueuesHandler();
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "lost the connection to the file download service, and current active task size is %d", Integer.valueOf(FileDownloadList.getImpl().size()));
            }
            if (FileDownloadList.getImpl().size() > 0) {
                synchronized (this.mWaitingList) {
                    FileDownloadList.getImpl().divertAndIgnoreDuplicate(this.mWaitingList);
                    Iterator it = this.mWaitingList.iterator();
                    while (it.hasNext()) {
                        ((IRunningTask) it.next()).free();
                    }
                    queuesHandler.freezeAllSerialQueues();
                }
                try {
                    FileDownloader.getImpl().bindService();
                } catch (IllegalStateException unused) {
                    FileDownloadLog.w(this, "restart service failed, you may need to restart downloading manually when the app comes back to foreground", new Object[0]);
                }
            }
        } else if (FileDownloadList.getImpl().size() > 0) {
            FileDownloadLog.w(this, "file download service has be unbound but the size of active tasks are not empty %d ", Integer.valueOf(FileDownloadList.getImpl().size()));
        }
    }

    public boolean isInWaitingList(IRunningTask iRunningTask) {
        return !this.mWaitingList.isEmpty() && this.mWaitingList.contains(iRunningTask);
    }

    public void taskWorkFine(IRunningTask iRunningTask) {
        if (!this.mWaitingList.isEmpty()) {
            synchronized (this.mWaitingList) {
                this.mWaitingList.remove(iRunningTask);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004e, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTaskStart(com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask r7) {
        /*
            r6 = this;
            com.liulishuo.filedownloader.FileDownloader r0 = com.liulishuo.filedownloader.FileDownloader.getImpl()
            boolean r0 = r0.isServiceConnected()
            r1 = 0
            if (r0 != 0) goto L_0x0054
            java.util.ArrayList<com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask> r0 = r6.mWaitingList
            monitor-enter(r0)
            com.liulishuo.filedownloader.FileDownloader r2 = com.liulishuo.filedownloader.FileDownloader.getImpl()     // Catch:{ all -> 0x0051 }
            boolean r2 = r2.isServiceConnected()     // Catch:{ all -> 0x0051 }
            if (r2 != 0) goto L_0x004f
            boolean r2 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x0051 }
            r3 = 1
            if (r2 == 0) goto L_0x0032
            java.lang.String r2 = "Waiting for connecting with the downloader service... %d"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0051 }
            com.liulishuo.filedownloader.BaseDownloadTask r5 = r7.getOrigin()     // Catch:{ all -> 0x0051 }
            int r5 = r5.getId()     // Catch:{ all -> 0x0051 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0051 }
            r4[r1] = r5     // Catch:{ all -> 0x0051 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r6, r2, r4)     // Catch:{ all -> 0x0051 }
        L_0x0032:
            com.liulishuo.filedownloader.FileDownloadServiceProxy r1 = com.liulishuo.filedownloader.FileDownloadServiceProxy.getImpl()     // Catch:{ all -> 0x0051 }
            android.content.Context r2 = com.liulishuo.filedownloader.util.FileDownloadHelper.getAppContext()     // Catch:{ all -> 0x0051 }
            r1.bindStartByContext(r2)     // Catch:{ all -> 0x0051 }
            java.util.ArrayList<com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask> r1 = r6.mWaitingList     // Catch:{ all -> 0x0051 }
            boolean r1 = r1.contains(r7)     // Catch:{ all -> 0x0051 }
            if (r1 != 0) goto L_0x004d
            r7.free()     // Catch:{ all -> 0x0051 }
            java.util.ArrayList<com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask> r1 = r6.mWaitingList     // Catch:{ all -> 0x0051 }
            r1.add(r7)     // Catch:{ all -> 0x0051 }
        L_0x004d:
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            return r3
        L_0x004f:
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            goto L_0x0054
        L_0x0051:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            throw r7
        L_0x0054:
            r6.taskWorkFine(r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.LostServiceConnectedHandler.dispatchTaskStart(com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask):boolean");
    }
}

package com.liulishuo.filedownloader;

import android.content.Context;
import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.util.List;

public class FileDownloader {
    private static final Object INIT_LOST_CONNECTED_HANDLER_LOCK = new Object();
    private static final Object INIT_QUEUES_HANDLER_LOCK = new Object();
    private ILostServiceConnectedHandler mLostConnectedHandler;
    private IQueuesHandler mQueuesHandler;
    private Runnable pauseAllRunnable;

    private static final class HolderClass {
        /* access modifiers changed from: private */
        public static final FileDownloader INSTANCE = new FileDownloader();

        private HolderClass() {
        }
    }

    public static void setup(Context context) {
        FileDownloadHelper.holdContext(context.getApplicationContext());
    }

    public static FileDownloader getImpl() {
        return HolderClass.INSTANCE;
    }

    public BaseDownloadTask create(String str) {
        return new DownloadTask(str);
    }

    public void pauseAll() {
        FileDownloadTaskLauncher.getImpl().expireAll();
        for (IRunningTask origin : FileDownloadList.getImpl().copy()) {
            origin.getOrigin().pause();
        }
        if (FileDownloadServiceProxy.getImpl().isConnected()) {
            FileDownloadServiceProxy.getImpl().pauseAllTasks();
            return;
        }
        if (this.pauseAllRunnable == null) {
            this.pauseAllRunnable = new Runnable() {
                public void run() {
                    FileDownloadServiceProxy.getImpl().pauseAllTasks();
                }
            };
        }
        FileDownloadServiceProxy.getImpl().bindStartByContext(FileDownloadHelper.getAppContext(), this.pauseAllRunnable);
    }

    public int pause(int i) {
        List<IRunningTask> downloadingList = FileDownloadList.getImpl().getDownloadingList(i);
        if (downloadingList == null || downloadingList.isEmpty()) {
            FileDownloadLog.w(this, "request pause but not exist %d", Integer.valueOf(i));
            return 0;
        }
        for (IRunningTask origin : downloadingList) {
            origin.getOrigin().pause();
        }
        return downloadingList.size();
    }

    public boolean clear(int i, String str) {
        pause(i);
        if (!FileDownloadServiceProxy.getImpl().clearTaskData(i)) {
            return false;
        }
        File file = new File(FileDownloadUtils.getTempPath(str));
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(str);
        if (file2.exists()) {
            file2.delete();
        }
        return true;
    }

    public byte getStatus(int i, String str) {
        byte b;
        IRunningTask iRunningTask = FileDownloadList.getImpl().get(i);
        if (iRunningTask == null) {
            b = FileDownloadServiceProxy.getImpl().getStatus(i);
        } else {
            b = iRunningTask.getOrigin().getStatus();
        }
        if (str == null || b != 0 || !FileDownloadUtils.isFilenameConverted(FileDownloadHelper.getAppContext()) || !new File(str).exists()) {
            return b;
        }
        return -3;
    }

    public void bindService() {
        if (!isServiceConnected()) {
            FileDownloadServiceProxy.getImpl().bindStartByContext(FileDownloadHelper.getAppContext());
        }
    }

    public boolean isServiceConnected() {
        return FileDownloadServiceProxy.getImpl().isConnected();
    }

    public void addServiceConnectListener(FileDownloadConnectListener fileDownloadConnectListener) {
        FileDownloadEventPool.getImpl().addListener("event.service.connect.changed", fileDownloadConnectListener);
    }

    /* access modifiers changed from: 0000 */
    public IQueuesHandler getQueuesHandler() {
        if (this.mQueuesHandler == null) {
            synchronized (INIT_QUEUES_HANDLER_LOCK) {
                if (this.mQueuesHandler == null) {
                    this.mQueuesHandler = new QueuesHandler();
                }
            }
        }
        return this.mQueuesHandler;
    }

    /* access modifiers changed from: 0000 */
    public ILostServiceConnectedHandler getLostConnectedHandler() {
        if (this.mLostConnectedHandler == null) {
            synchronized (INIT_LOST_CONNECTED_HANDLER_LOCK) {
                if (this.mLostConnectedHandler == null) {
                    this.mLostConnectedHandler = new LostServiceConnectedHandler();
                    addServiceConnectListener((FileDownloadConnectListener) this.mLostConnectedHandler);
                }
            }
        }
        return this.mLostConnectedHandler;
    }
}

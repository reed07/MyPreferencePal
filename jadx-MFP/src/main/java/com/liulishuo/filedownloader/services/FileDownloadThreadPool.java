package com.liulishuo.filedownloader.services;

import android.util.SparseArray;
import com.liulishuo.filedownloader.download.DownloadLaunchRunnable;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

class FileDownloadThreadPool {
    private int mIgnoreCheckTimes = 0;
    private int mMaxThreadCount;
    private ThreadPoolExecutor mThreadPool;
    private SparseArray<DownloadLaunchRunnable> runnablePool = new SparseArray<>();
    private final String threadPrefix = "Network";

    FileDownloadThreadPool(int i) {
        this.mThreadPool = FileDownloadExecutors.newDefaultThreadPool(i, "Network");
        this.mMaxThreadCount = i;
    }

    public synchronized boolean setMaxNetworkThreadCount(int i) {
        if (exactSize() > 0) {
            FileDownloadLog.w(this, "Can't change the max network thread count, because the  network thread pool isn't in IDLE, please try again after all running tasks are completed or invoking FileDownloader#pauseAll directly.", new Object[0]);
            return false;
        }
        int validNetworkThreadCount = FileDownloadProperties.getValidNetworkThreadCount(i);
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "change the max network thread count, from %d to %d", Integer.valueOf(this.mMaxThreadCount), Integer.valueOf(validNetworkThreadCount));
        }
        List shutdownNow = this.mThreadPool.shutdownNow();
        this.mThreadPool = FileDownloadExecutors.newDefaultThreadPool(validNetworkThreadCount, "Network");
        if (shutdownNow.size() > 0) {
            FileDownloadLog.w(this, "recreate the network thread pool and discard %d tasks", Integer.valueOf(shutdownNow.size()));
        }
        this.mMaxThreadCount = validNetworkThreadCount;
        return true;
    }

    public void execute(DownloadLaunchRunnable downloadLaunchRunnable) {
        downloadLaunchRunnable.pending();
        synchronized (this) {
            this.runnablePool.put(downloadLaunchRunnable.getId(), downloadLaunchRunnable);
        }
        this.mThreadPool.execute(downloadLaunchRunnable);
        int i = this.mIgnoreCheckTimes;
        if (i >= 600) {
            filterOutNoExist();
            this.mIgnoreCheckTimes = 0;
            return;
        }
        this.mIgnoreCheckTimes = i + 1;
    }

    public void cancel(int i) {
        filterOutNoExist();
        synchronized (this) {
            DownloadLaunchRunnable downloadLaunchRunnable = (DownloadLaunchRunnable) this.runnablePool.get(i);
            if (downloadLaunchRunnable != null) {
                downloadLaunchRunnable.pause();
                boolean remove = this.mThreadPool.remove(downloadLaunchRunnable);
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "successful cancel %d %B", Integer.valueOf(i), Boolean.valueOf(remove));
                }
            }
            this.runnablePool.remove(i);
        }
    }

    private synchronized void filterOutNoExist() {
        SparseArray<DownloadLaunchRunnable> sparseArray = new SparseArray<>();
        int size = this.runnablePool.size();
        for (int i = 0; i < size; i++) {
            int keyAt = this.runnablePool.keyAt(i);
            DownloadLaunchRunnable downloadLaunchRunnable = (DownloadLaunchRunnable) this.runnablePool.get(keyAt);
            if (downloadLaunchRunnable.isAlive()) {
                sparseArray.put(keyAt, downloadLaunchRunnable);
            }
        }
        this.runnablePool = sparseArray;
    }

    public boolean isInThreadPool(int i) {
        DownloadLaunchRunnable downloadLaunchRunnable = (DownloadLaunchRunnable) this.runnablePool.get(i);
        return downloadLaunchRunnable != null && downloadLaunchRunnable.isAlive();
    }

    public int findRunningTaskIdBySameTempPath(String str, int i) {
        if (str == null) {
            return 0;
        }
        int size = this.runnablePool.size();
        for (int i2 = 0; i2 < size; i2++) {
            DownloadLaunchRunnable downloadLaunchRunnable = (DownloadLaunchRunnable) this.runnablePool.valueAt(i2);
            if (downloadLaunchRunnable != null && downloadLaunchRunnable.isAlive() && downloadLaunchRunnable.getId() != i && str.equals(downloadLaunchRunnable.getTempFilePath())) {
                return downloadLaunchRunnable.getId();
            }
        }
        return 0;
    }

    public synchronized int exactSize() {
        filterOutNoExist();
        return this.runnablePool.size();
    }

    public synchronized List<Integer> getAllExactRunningDownloadIds() {
        ArrayList arrayList;
        filterOutNoExist();
        arrayList = new ArrayList();
        for (int i = 0; i < this.runnablePool.size(); i++) {
            arrayList.add(Integer.valueOf(((DownloadLaunchRunnable) this.runnablePool.get(this.runnablePool.keyAt(i))).getId()));
        }
        return arrayList;
    }
}

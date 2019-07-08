package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.ITaskHunter.IStarter;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

class FileDownloadTaskLauncher {
    private final LaunchTaskPool mLaunchTaskPool = new LaunchTaskPool();

    private static class HolderClass {
        /* access modifiers changed from: private */
        public static final FileDownloadTaskLauncher INSTANCE = new FileDownloadTaskLauncher();

        private HolderClass() {
        }

        static {
            MessageSnapshotFlow.getImpl().setReceiver(new MessageSnapshotGate());
        }
    }

    private static class LaunchTaskPool {
        private ThreadPoolExecutor mPool;
        private LinkedBlockingQueue<Runnable> mWorkQueue;

        LaunchTaskPool() {
            init();
        }

        public void asyncExecute(IStarter iStarter) {
            this.mPool.execute(new LaunchTaskRunnable(iStarter));
        }

        public void expire(IStarter iStarter) {
            this.mWorkQueue.remove(iStarter);
        }

        public void expireAll() {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "expire %d tasks", Integer.valueOf(this.mWorkQueue.size()));
            }
            this.mPool.shutdownNow();
            init();
        }

        private void init() {
            this.mWorkQueue = new LinkedBlockingQueue<>();
            this.mPool = FileDownloadExecutors.newDefaultThreadPool(3, this.mWorkQueue, "LauncherTask");
        }
    }

    private static class LaunchTaskRunnable implements Runnable {
        private boolean mExpired = false;
        private final IStarter mTaskStarter;

        LaunchTaskRunnable(IStarter iStarter) {
            this.mTaskStarter = iStarter;
        }

        public void run() {
            if (!this.mExpired) {
                this.mTaskStarter.start();
            }
        }

        public boolean equals(Object obj) {
            return super.equals(obj) || obj == this.mTaskStarter;
        }
    }

    FileDownloadTaskLauncher() {
    }

    public static FileDownloadTaskLauncher getImpl() {
        return HolderClass.INSTANCE;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void launch(IStarter iStarter) {
        this.mLaunchTaskPool.asyncExecute(iStarter);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void expireAll() {
        this.mLaunchTaskPool.expireAll();
    }

    /* access modifiers changed from: 0000 */
    public synchronized void expire(IStarter iStarter) {
        this.mLaunchTaskPool.expire(iStarter);
    }
}

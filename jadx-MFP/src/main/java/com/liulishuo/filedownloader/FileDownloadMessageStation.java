package com.liulishuo.filedownloader;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class FileDownloadMessageStation {
    static int INTERVAL = 10;
    static int SUB_PACKAGE_SIZE = 5;
    private final Executor blockCompletedPool;
    private final ArrayList<IFileDownloadMessenger> disposingList;
    private final Handler handler;
    private final Object queueLock;
    private final LinkedBlockingQueue<IFileDownloadMessenger> waitingQueue;

    private static final class HolderClass {
        /* access modifiers changed from: private */
        public static final FileDownloadMessageStation INSTANCE = new FileDownloadMessageStation();

        private HolderClass() {
        }
    }

    private static class UIHandlerCallback implements Callback {
        private UIHandlerCallback() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ((IFileDownloadMessenger) message.obj).handoverMessage();
            } else if (message.what == 2) {
                dispose((ArrayList) message.obj);
                FileDownloadMessageStation.getImpl().push();
            }
            return true;
        }

        private void dispose(ArrayList<IFileDownloadMessenger> arrayList) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((IFileDownloadMessenger) it.next()).handoverMessage();
            }
            arrayList.clear();
        }
    }

    public static FileDownloadMessageStation getImpl() {
        return HolderClass.INSTANCE;
    }

    private FileDownloadMessageStation() {
        this.blockCompletedPool = FileDownloadExecutors.newDefaultThreadPool(5, "BlockCompleted");
        this.queueLock = new Object();
        this.disposingList = new ArrayList<>();
        this.handler = new Handler(Looper.getMainLooper(), new UIHandlerCallback());
        this.waitingQueue = new LinkedBlockingQueue<>();
    }

    /* access modifiers changed from: 0000 */
    public void requestEnqueue(IFileDownloadMessenger iFileDownloadMessenger) {
        requestEnqueue(iFileDownloadMessenger, false);
    }

    /* access modifiers changed from: 0000 */
    public void requestEnqueue(final IFileDownloadMessenger iFileDownloadMessenger, boolean z) {
        if (iFileDownloadMessenger.handoverDirectly()) {
            iFileDownloadMessenger.handoverMessage();
        } else if (iFileDownloadMessenger.isBlockingCompleted()) {
            this.blockCompletedPool.execute(new Runnable() {
                public void run() {
                    iFileDownloadMessenger.handoverMessage();
                }
            });
        } else {
            if (!isIntervalValid() && !this.waitingQueue.isEmpty()) {
                synchronized (this.queueLock) {
                    if (!this.waitingQueue.isEmpty()) {
                        Iterator it = this.waitingQueue.iterator();
                        while (it.hasNext()) {
                            handoverInUIThread((IFileDownloadMessenger) it.next());
                        }
                    }
                    this.waitingQueue.clear();
                }
            }
            if (!isIntervalValid() || z) {
                handoverInUIThread(iFileDownloadMessenger);
            } else {
                enqueue(iFileDownloadMessenger);
            }
        }
    }

    private void handoverInUIThread(IFileDownloadMessenger iFileDownloadMessenger) {
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(1, iFileDownloadMessenger));
    }

    private void enqueue(IFileDownloadMessenger iFileDownloadMessenger) {
        synchronized (this.queueLock) {
            this.waitingQueue.offer(iFileDownloadMessenger);
        }
        push();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        r0 = r6.handler;
        r0.sendMessageDelayed(r0.obtainMessage(2, r6.disposingList), (long) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0053, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void push() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.queueLock
            monitor-enter(r0)
            java.util.ArrayList<com.liulishuo.filedownloader.IFileDownloadMessenger> r1 = r6.disposingList     // Catch:{ all -> 0x0054 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0054 }
            if (r1 != 0) goto L_0x000d
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            return
        L_0x000d:
            java.util.concurrent.LinkedBlockingQueue<com.liulishuo.filedownloader.IFileDownloadMessenger> r1 = r6.waitingQueue     // Catch:{ all -> 0x0054 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0017
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            return
        L_0x0017:
            boolean r1 = isIntervalValid()     // Catch:{ all -> 0x0054 }
            r2 = 0
            if (r1 != 0) goto L_0x0027
            java.util.concurrent.LinkedBlockingQueue<com.liulishuo.filedownloader.IFileDownloadMessenger> r1 = r6.waitingQueue     // Catch:{ all -> 0x0054 }
            java.util.ArrayList<com.liulishuo.filedownloader.IFileDownloadMessenger> r3 = r6.disposingList     // Catch:{ all -> 0x0054 }
            r1.drainTo(r3)     // Catch:{ all -> 0x0054 }
            r1 = 0
            goto L_0x0045
        L_0x0027:
            int r1 = INTERVAL     // Catch:{ all -> 0x0054 }
            java.util.concurrent.LinkedBlockingQueue<com.liulishuo.filedownloader.IFileDownloadMessenger> r3 = r6.waitingQueue     // Catch:{ all -> 0x0054 }
            int r3 = r3.size()     // Catch:{ all -> 0x0054 }
            int r4 = SUB_PACKAGE_SIZE     // Catch:{ all -> 0x0054 }
            int r3 = java.lang.Math.min(r3, r4)     // Catch:{ all -> 0x0054 }
        L_0x0035:
            if (r2 >= r3) goto L_0x0045
            java.util.ArrayList<com.liulishuo.filedownloader.IFileDownloadMessenger> r4 = r6.disposingList     // Catch:{ all -> 0x0054 }
            java.util.concurrent.LinkedBlockingQueue<com.liulishuo.filedownloader.IFileDownloadMessenger> r5 = r6.waitingQueue     // Catch:{ all -> 0x0054 }
            java.lang.Object r5 = r5.remove()     // Catch:{ all -> 0x0054 }
            r4.add(r5)     // Catch:{ all -> 0x0054 }
            int r2 = r2 + 1
            goto L_0x0035
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            android.os.Handler r0 = r6.handler
            r2 = 2
            java.util.ArrayList<com.liulishuo.filedownloader.IFileDownloadMessenger> r3 = r6.disposingList
            android.os.Message r2 = r0.obtainMessage(r2, r3)
            long r3 = (long) r1
            r0.sendMessageDelayed(r2, r3)
            return
        L_0x0054:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.FileDownloadMessageStation.push():void");
    }

    public static boolean isIntervalValid() {
        return INTERVAL > 0;
    }
}

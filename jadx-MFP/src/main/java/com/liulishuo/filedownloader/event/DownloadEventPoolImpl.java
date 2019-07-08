package com.liulishuo.filedownloader.event;

import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executor;

public class DownloadEventPoolImpl {
    private final HashMap<String, LinkedList<IDownloadListener>> listenersMap = new HashMap<>();
    private final Executor threadPool = FileDownloadExecutors.newDefaultThreadPool(10, "EventPool");

    public boolean addListener(String str, IDownloadListener iDownloadListener) {
        boolean add;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "setListener %s", str);
        }
        if (iDownloadListener != null) {
            LinkedList linkedList = (LinkedList) this.listenersMap.get(str);
            if (linkedList == null) {
                synchronized (str.intern()) {
                    linkedList = (LinkedList) this.listenersMap.get(str);
                    if (linkedList == null) {
                        HashMap<String, LinkedList<IDownloadListener>> hashMap = this.listenersMap;
                        LinkedList linkedList2 = new LinkedList();
                        hashMap.put(str, linkedList2);
                        linkedList = linkedList2;
                    }
                }
            }
            synchronized (str.intern()) {
                add = linkedList.add(iDownloadListener);
            }
            return add;
        }
        throw new IllegalArgumentException("listener must not be null!");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean publish(com.liulishuo.filedownloader.event.IDownloadEvent r6) {
        /*
            r5 = this;
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0013
            java.lang.String r0 = "publish %s"
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = r6.getId()
            r3[r1] = r4
            com.liulishuo.filedownloader.util.FileDownloadLog.v(r5, r0, r3)
        L_0x0013:
            if (r6 == 0) goto L_0x004a
            java.lang.String r0 = r6.getId()
            java.util.HashMap<java.lang.String, java.util.LinkedList<com.liulishuo.filedownloader.event.IDownloadListener>> r3 = r5.listenersMap
            java.lang.Object r3 = r3.get(r0)
            java.util.LinkedList r3 = (java.util.LinkedList) r3
            if (r3 != 0) goto L_0x0046
            java.lang.String r4 = r0.intern()
            monitor-enter(r4)
            java.util.HashMap<java.lang.String, java.util.LinkedList<com.liulishuo.filedownloader.event.IDownloadListener>> r3 = r5.listenersMap     // Catch:{ all -> 0x0043 }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ all -> 0x0043 }
            java.util.LinkedList r3 = (java.util.LinkedList) r3     // Catch:{ all -> 0x0043 }
            if (r3 != 0) goto L_0x0041
            boolean r6 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x0043 }
            if (r6 == 0) goto L_0x003f
            java.lang.String r6 = "No listener for this event %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0043 }
            r2[r1] = r0     // Catch:{ all -> 0x0043 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r5, r6, r2)     // Catch:{ all -> 0x0043 }
        L_0x003f:
            monitor-exit(r4)     // Catch:{ all -> 0x0043 }
            return r1
        L_0x0041:
            monitor-exit(r4)     // Catch:{ all -> 0x0043 }
            goto L_0x0046
        L_0x0043:
            r6 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0043 }
            throw r6
        L_0x0046:
            r5.trigger(r3, r6)
            return r2
        L_0x004a:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "event must not be null!"
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.event.DownloadEventPoolImpl.publish(com.liulishuo.filedownloader.event.IDownloadEvent):boolean");
    }

    public void asyncPublishInNewThread(final IDownloadEvent iDownloadEvent) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "asyncPublishInNewThread %s", iDownloadEvent.getId());
        }
        if (iDownloadEvent != null) {
            this.threadPool.execute(new Runnable() {
                public void run() {
                    DownloadEventPoolImpl.this.publish(iDownloadEvent);
                }
            });
            return;
        }
        throw new IllegalArgumentException("event must not be null!");
    }

    private void trigger(LinkedList<IDownloadListener> linkedList, IDownloadEvent iDownloadEvent) {
        Object[] array;
        for (Object obj : linkedList.toArray()) {
            if (obj != null && ((IDownloadListener) obj).callback(iDownloadEvent)) {
                break;
            }
        }
        if (iDownloadEvent.callback != null) {
            iDownloadEvent.callback.run();
        }
    }
}

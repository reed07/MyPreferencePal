package com.squareup.okhttp;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ConnectionPool {
    private static final ConnectionPool systemDefault;
    private final LinkedList<Connection> connections = new LinkedList<>();
    private final Runnable connectionsCleanupRunnable;
    private Executor executor;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;

    static {
        String property = System.getProperty("http.keepAlive");
        String property2 = System.getProperty("http.keepAliveDuration");
        String property3 = System.getProperty("http.maxConnections");
        long parseLong = property2 != null ? Long.parseLong(property2) : 300000;
        if (property != null && !Boolean.parseBoolean(property)) {
            systemDefault = new ConnectionPool(0, parseLong);
        } else if (property3 != null) {
            systemDefault = new ConnectionPool(Integer.parseInt(property3), parseLong);
        } else {
            systemDefault = new ConnectionPool(5, parseLong);
        }
    }

    public ConnectionPool(int i, long j) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
        this.executor = threadPoolExecutor;
        this.connectionsCleanupRunnable = new Runnable() {
            public void run() {
                ConnectionPool.this.runCleanupUntilPoolIsEmpty();
            }
        };
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = j * 1000 * 1000;
    }

    public static ConnectionPool getDefault() {
        return systemDefault;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0074, code lost:
        r0 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.squareup.okhttp.Connection get(com.squareup.okhttp.Address r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            r0 = 0
            java.util.LinkedList<com.squareup.okhttp.Connection> r1 = r8.connections     // Catch:{ all -> 0x0084 }
            java.util.LinkedList<com.squareup.okhttp.Connection> r2 = r8.connections     // Catch:{ all -> 0x0084 }
            int r2 = r2.size()     // Catch:{ all -> 0x0084 }
            java.util.ListIterator r1 = r1.listIterator(r2)     // Catch:{ all -> 0x0084 }
        L_0x000e:
            boolean r2 = r1.hasPrevious()     // Catch:{ all -> 0x0084 }
            if (r2 == 0) goto L_0x0075
            java.lang.Object r2 = r1.previous()     // Catch:{ all -> 0x0084 }
            com.squareup.okhttp.Connection r2 = (com.squareup.okhttp.Connection) r2     // Catch:{ all -> 0x0084 }
            com.squareup.okhttp.Route r3 = r2.getRoute()     // Catch:{ all -> 0x0084 }
            com.squareup.okhttp.Address r3 = r3.getAddress()     // Catch:{ all -> 0x0084 }
            boolean r3 = r3.equals(r9)     // Catch:{ all -> 0x0084 }
            if (r3 == 0) goto L_0x000e
            boolean r3 = r2.isAlive()     // Catch:{ all -> 0x0084 }
            if (r3 == 0) goto L_0x000e
            long r3 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0084 }
            long r5 = r2.getIdleStartTimeNs()     // Catch:{ all -> 0x0084 }
            long r3 = r3 - r5
            long r5 = r8.keepAliveDurationNs     // Catch:{ all -> 0x0084 }
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x003e
            goto L_0x000e
        L_0x003e:
            r1.remove()     // Catch:{ all -> 0x0084 }
            boolean r3 = r2.isFramed()     // Catch:{ all -> 0x0084 }
            if (r3 != 0) goto L_0x0074
            com.squareup.okhttp.internal.Platform r3 = com.squareup.okhttp.internal.Platform.get()     // Catch:{ SocketException -> 0x0053 }
            java.net.Socket r4 = r2.getSocket()     // Catch:{ SocketException -> 0x0053 }
            r3.tagSocket(r4)     // Catch:{ SocketException -> 0x0053 }
            goto L_0x0074
        L_0x0053:
            r3 = move-exception
            java.net.Socket r2 = r2.getSocket()     // Catch:{ all -> 0x0084 }
            com.squareup.okhttp.internal.Util.closeQuietly(r2)     // Catch:{ all -> 0x0084 }
            com.squareup.okhttp.internal.Platform r2 = com.squareup.okhttp.internal.Platform.get()     // Catch:{ all -> 0x0084 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0084 }
            r4.<init>()     // Catch:{ all -> 0x0084 }
            java.lang.String r5 = "Unable to tagSocket(): "
            r4.append(r5)     // Catch:{ all -> 0x0084 }
            r4.append(r3)     // Catch:{ all -> 0x0084 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x0084 }
            r2.logW(r3)     // Catch:{ all -> 0x0084 }
            goto L_0x000e
        L_0x0074:
            r0 = r2
        L_0x0075:
            if (r0 == 0) goto L_0x0082
            boolean r9 = r0.isFramed()     // Catch:{ all -> 0x0084 }
            if (r9 == 0) goto L_0x0082
            java.util.LinkedList<com.squareup.okhttp.Connection> r9 = r8.connections     // Catch:{ all -> 0x0084 }
            r9.addFirst(r0)     // Catch:{ all -> 0x0084 }
        L_0x0082:
            monitor-exit(r8)
            return r0
        L_0x0084:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.ConnectionPool.get(com.squareup.okhttp.Address):com.squareup.okhttp.Connection");
    }

    /* access modifiers changed from: 0000 */
    public void recycle(Connection connection) {
        if (connection.isFramed() || !connection.clearOwner()) {
            return;
        }
        if (!connection.isAlive()) {
            Util.closeQuietly(connection.getSocket());
            return;
        }
        try {
            Platform.get().untagSocket(connection.getSocket());
            synchronized (this) {
                addConnection(connection);
                connection.incrementRecycleCount();
                connection.resetIdleStartTime();
            }
        } catch (SocketException e) {
            Platform platform = Platform.get();
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to untagSocket(): ");
            sb.append(e);
            platform.logW(sb.toString());
            Util.closeQuietly(connection.getSocket());
        }
    }

    private void addConnection(Connection connection) {
        boolean isEmpty = this.connections.isEmpty();
        this.connections.addFirst(connection);
        if (isEmpty) {
            this.executor.execute(this.connectionsCleanupRunnable);
        } else {
            notifyAll();
        }
    }

    /* access modifiers changed from: 0000 */
    public void share(Connection connection) {
        if (!connection.isFramed()) {
            throw new IllegalArgumentException();
        } else if (connection.isAlive()) {
            synchronized (this) {
                addConnection(connection);
            }
        }
    }

    /* access modifiers changed from: private */
    public void runCleanupUntilPoolIsEmpty() {
        do {
        } while (performCleanup());
    }

    /* access modifiers changed from: 0000 */
    public boolean performCleanup() {
        synchronized (this) {
            if (this.connections.isEmpty()) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            long nanoTime = System.nanoTime();
            long j = this.keepAliveDurationNs;
            ListIterator listIterator = this.connections.listIterator(this.connections.size());
            long j2 = j;
            int i = 0;
            while (listIterator.hasPrevious()) {
                Connection connection = (Connection) listIterator.previous();
                long idleStartTimeNs = (connection.getIdleStartTimeNs() + this.keepAliveDurationNs) - nanoTime;
                if (idleStartTimeNs > 0) {
                    if (connection.isAlive()) {
                        if (connection.isIdle()) {
                            i++;
                            j2 = Math.min(j2, idleStartTimeNs);
                        }
                    }
                }
                listIterator.remove();
                arrayList.add(connection);
            }
            ListIterator listIterator2 = this.connections.listIterator(this.connections.size());
            while (listIterator2.hasPrevious() && i > this.maxIdleConnections) {
                Connection connection2 = (Connection) listIterator2.previous();
                if (connection2.isIdle()) {
                    arrayList.add(connection2);
                    listIterator2.remove();
                    i--;
                }
            }
            if (arrayList.isEmpty()) {
                try {
                    long j3 = j2 / 1000000;
                    Long.signum(j3);
                    wait(j3, (int) (j2 - (1000000 * j3)));
                    return true;
                } catch (InterruptedException unused) {
                    int size = arrayList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        Util.closeQuietly(((Connection) arrayList.get(i2)).getSocket());
                    }
                    return true;
                }
            }
        }
    }
}

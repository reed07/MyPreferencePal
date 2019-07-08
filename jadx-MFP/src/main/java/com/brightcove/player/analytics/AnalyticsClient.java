package com.brightcove.player.analytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import com.brightcove.player.logging.Log;
import com.brightcove.player.network.ConnectivityMonitor;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AnalyticsClient {
    private static final AtomicLong BACKLOG_LIMIT = new AtomicLong(DEFAULT_BACKLOG_LIMIT);
    private static final AtomicInteger BATCH_SIZE = new AtomicInteger(DEFAULT_BATCH_SIZE);
    public static long DEFAULT_BACKLOG_LIMIT = 500000000;
    public static int DEFAULT_BATCH_SIZE = 100;
    public static int DEFAULT_RETRY_LIMIT = 3;
    private static volatile AnalyticsClient INSTANCE = null;
    private static final SparseArray<Boolean> NETWORK_ENTITLEMENTS = new SparseArray<>(ConnectivityMonitor.ALL_NETWORKS.length);
    private static final AtomicInteger RETRY_LIMIT = new AtomicInteger(DEFAULT_RETRY_LIMIT);
    private static final String TAG = "AnalyticsClient";
    private final HashSet<IAnalyticsHandler> handlers = new HashSet<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static AnalyticsClient getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            synchronized (AnalyticsClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AnalyticsClient(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    static {
        for (int put : ConnectivityMonitor.ALL_NETWORKS) {
            NETWORK_ENTITLEMENTS.put(put, Boolean.valueOf(true));
        }
    }

    @SuppressLint({"WrongConstant"})
    public static boolean setNetworkEnabled(int i, boolean z) {
        boolean z2;
        boolean z3;
        synchronized (NETWORK_ENTITLEMENTS) {
            z2 = false;
            if (((Boolean) NETWORK_ENTITLEMENTS.get(i)).booleanValue() != z) {
                if (!z) {
                    z3 = false;
                    int i2 = 0;
                    while (!z3 && i2 < ConnectivityMonitor.ALL_NETWORKS.length) {
                        int i3 = ConnectivityMonitor.ALL_NETWORKS[i2];
                        z3 = i3 != i && ((Boolean) NETWORK_ENTITLEMENTS.get(i3, Boolean.valueOf(true))).booleanValue();
                        i2++;
                    }
                } else {
                    z3 = true;
                }
                if (z3) {
                    NETWORK_ENTITLEMENTS.put(i, Boolean.valueOf(z));
                    if (INSTANCE != null) {
                        z2 = true;
                    }
                } else {
                    Log.w(TAG, "Cannot disable network type [%d] for analytics transmission, because it is currently the only network type enabled", Integer.valueOf(i));
                }
            } else {
                z3 = true;
            }
        }
        if (z2) {
            INSTANCE.onNetworkEntitlementChanged(i, z);
        }
        return z3;
    }

    public static void enableNetwork(int i) {
        setNetworkEnabled(i, true);
    }

    public static void disableNetwork(int i) {
        setNetworkEnabled(i, false);
    }

    public static boolean isNetworkEnabled(int i) {
        boolean booleanValue;
        synchronized (NETWORK_ENTITLEMENTS) {
            booleanValue = ((Boolean) NETWORK_ENTITLEMENTS.get(i, Boolean.valueOf(true))).booleanValue();
        }
        return booleanValue;
    }

    public static SparseArray<Boolean> getNetworkEntitlements() {
        SparseArray<Boolean> clone;
        synchronized (NETWORK_ENTITLEMENTS) {
            clone = NETWORK_ENTITLEMENTS.clone();
        }
        return clone;
    }

    public static long getBacklogLimit() {
        return BACKLOG_LIMIT.get();
    }

    public static void setBacklogLimit(long j) {
        AtomicLong atomicLong = BACKLOG_LIMIT;
        if (j < 1) {
            j = 0;
        }
        atomicLong.set(j);
    }

    public static int getRetryLimit() {
        return RETRY_LIMIT.get();
    }

    public static void setRetryLimit(int i) {
        AtomicInteger atomicInteger = RETRY_LIMIT;
        if (i < 1) {
            i = 0;
        }
        atomicInteger.set(i);
    }

    public static int getBatchSize() {
        return BATCH_SIZE.get();
    }

    public static void setBatchSize(int i) {
        AtomicInteger atomicInteger = BATCH_SIZE;
        if (i < 1) {
            i = 1;
        }
        atomicInteger.set(i);
    }

    private AnalyticsClient(@NonNull Context context) {
        addHandler(DefaultAnalyticsHandler.getInstance(context));
    }

    public AnalyticsClient addHandler(@NonNull IAnalyticsHandler iAnalyticsHandler) {
        this.lock.writeLock().lock();
        try {
            if (this.handlers.add(iAnalyticsHandler)) {
                iAnalyticsHandler.onAttached();
            }
            return this;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public AnalyticsClient removeHandler(@NonNull IAnalyticsHandler iAnalyticsHandler) {
        this.lock.writeLock().lock();
        try {
            if (this.handlers.remove(iAnalyticsHandler)) {
                iAnalyticsHandler.onRemoved();
            }
            return this;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public AnalyticsClient removeAllHandlers() {
        this.lock.writeLock().lock();
        try {
            IAnalyticsHandler[] iAnalyticsHandlerArr = (IAnalyticsHandler[]) this.handlers.toArray(new IAnalyticsHandler[0]);
            this.handlers.clear();
            for (IAnalyticsHandler onRemoved : iAnalyticsHandlerArr) {
                onRemoved.onRemoved();
            }
            return this;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public AnalyticsClient publish(@NonNull AnalyticsEvent analyticsEvent, @NonNull IAnalyticsErrorListener iAnalyticsErrorListener) {
        this.lock.readLock().lock();
        try {
            Iterator it = this.handlers.iterator();
            while (it.hasNext()) {
                ((IAnalyticsHandler) it.next()).onAnalyticsEvent(analyticsEvent, iAnalyticsErrorListener);
            }
            return this;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    private void onNetworkEntitlementChanged(int i, boolean z) {
        this.lock.readLock().lock();
        try {
            Iterator it = this.handlers.iterator();
            while (it.hasNext()) {
                ((IAnalyticsHandler) it.next()).onNetworkEntitlementChanged(i, z);
            }
        } finally {
            this.lock.readLock().unlock();
        }
    }
}

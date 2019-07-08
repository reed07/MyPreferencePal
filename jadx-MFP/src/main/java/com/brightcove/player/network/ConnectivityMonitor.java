package com.brightcove.player.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.brightcove.player.util.Convert;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.WeakHashMap;

public class ConnectivityMonitor {
    public static final int[] ALL_NETWORKS = {0, 1, 6, 7, 9, 17};
    private static volatile SoftReference<ConnectivityMonitor> cachedInstance;
    private final Context context;
    /* access modifiers changed from: private */
    public final WeakHashMap<Listener, ConnectivityMonitor> listenerList = new WeakHashMap<>();
    /* access modifiers changed from: private */
    public final ConnectivityManager manager;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            HashSet<Listener> hashSet;
            NetworkInfo activeNetworkInfo = ConnectivityMonitor.this.manager.getActiveNetworkInfo();
            boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
            synchronized (ConnectivityMonitor.this.listenerList) {
                hashSet = new HashSet<>(ConnectivityMonitor.this.listenerList.keySet());
            }
            for (Listener onConnectivityChanged : hashSet) {
                onConnectivityChanged.onConnectivityChanged(z, activeNetworkInfo);
            }
        }
    };

    public interface Listener {
        void onConnectivityChanged(boolean z, @Nullable NetworkInfo networkInfo);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NetworkType {
    }

    @Nullable
    private static ConnectivityMonitor getCachedInstance() {
        if (cachedInstance == null) {
            return null;
        }
        return (ConnectivityMonitor) cachedInstance.get();
    }

    public static ConnectivityMonitor getInstance(@NonNull Context context2) {
        ConnectivityMonitor cachedInstance2 = getCachedInstance();
        if (cachedInstance2 == null) {
            synchronized (ConnectivityMonitor.class) {
                cachedInstance2 = getCachedInstance();
                if (cachedInstance2 == null) {
                    cachedInstance2 = new ConnectivityMonitor(context2);
                    cachedInstance = new SoftReference<>(cachedInstance2);
                }
            }
        }
        return cachedInstance2;
    }

    public ConnectivityMonitor addListener(@NonNull Listener listener) {
        synchronized (this.listenerList) {
            if (!this.listenerList.containsKey(listener)) {
                this.listenerList.put(listener, this);
                if (this.listenerList.size() == 1) {
                    this.context.registerReceiver(this.receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                }
            }
        }
        return this;
    }

    public ConnectivityMonitor removeListener(@NonNull Listener listener) {
        synchronized (this.listenerList) {
            if (this.listenerList.remove(listener) == this && this.listenerList.size() == 0) {
                this.context.unregisterReceiver(this.receiver);
            }
        }
        return this;
    }

    private ConnectivityMonitor(@NonNull Context context2) {
        this.context = context2.getApplicationContext();
        this.manager = (ConnectivityManager) context2.getSystemService("connectivity");
    }

    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = this.manager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @NonNull
    public String getActiveNetworkName() {
        NetworkInfo activeNetworkInfo = this.manager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "";
        }
        return activeNetworkInfo.getTypeName();
    }

    public boolean isConnected(int... iArr) {
        NetworkInfo activeNetworkInfo = this.manager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            int type = activeNetworkInfo.getType();
            for (int i : iArr) {
                if (i == type) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isConnected(@NonNull SparseArray<Boolean> sparseArray) {
        return isConnected(this.manager.getActiveNetworkInfo(), sparseArray);
    }

    public static boolean isConnected(@Nullable NetworkInfo networkInfo, @NonNull SparseArray<Boolean> sparseArray) {
        return networkInfo != null && Convert.toBoolean(sparseArray.get(networkInfo.getType()));
    }

    public boolean isWifiConnection() {
        return isConnected(1);
    }
}

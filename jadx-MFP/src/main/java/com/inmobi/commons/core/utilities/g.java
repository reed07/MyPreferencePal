package com.inmobi.commons.core.utilities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: SystemBroadcastObserver */
public class g {
    private static final String a = "g";
    private static HashMap<String, CopyOnWriteArrayList<b>> b = new HashMap<>();
    private static HashMap<String, a> c = new HashMap<>();
    private static HashMap<String, NetworkCallback> d = new HashMap<>();
    private static final Object e = new Object();
    private static volatile g f;

    /* compiled from: SystemBroadcastObserver */
    static final class a extends BroadcastReceiver {
        private static final String a = "g$a";

        a() {
        }

        @SuppressLint({"MissingPermission"})
        public final void onReceive(Context context, Intent intent) {
            if (intent != null) {
                try {
                    if (intent.getAction() != null) {
                        boolean z = false;
                        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                            if (connectivityManager != null) {
                                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                                    z = true;
                                }
                            }
                        } else if ("android.os.action.DEVICE_IDLE_MODE_CHANGED".equalsIgnoreCase(intent.getAction())) {
                            PowerManager powerManager = (PowerManager) context.getSystemService("power");
                            if (powerManager != null && VERSION.SDK_INT >= 23 && powerManager.isDeviceIdleMode()) {
                                z = true;
                            }
                        } else if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                            z = true;
                        }
                        g.b(z, intent.getAction());
                        StringBuilder sb = new StringBuilder();
                        sb.append(intent.getAction());
                        sb.append(" Availability:");
                        sb.append(z);
                    }
                } catch (Exception e) {
                    new StringBuilder("SDK encountered unexpected error in SystemBroadReceiver.onReceive handler; ").append(e.getMessage());
                }
            }
        }
    }

    /* compiled from: SystemBroadcastObserver */
    public interface b {
        void a(boolean z);
    }

    public static g a() {
        g gVar = f;
        if (gVar == null) {
            synchronized (e) {
                gVar = f;
                if (gVar == null) {
                    gVar = new g();
                    f = gVar;
                }
            }
        }
        return gVar;
    }

    public final void a(String str, b bVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) b.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.add(bVar);
        } else {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.add(bVar);
        }
        b.put(str, copyOnWriteArrayList);
        if (copyOnWriteArrayList.size() == 1) {
            Context b2 = com.inmobi.commons.a.a.b();
            if (b2 != null) {
                if ("SYSTEM_CONNECTIVITY_CHANGE".equals(str)) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) b2.getSystemService("connectivity");
                    if (connectivityManager != null) {
                        AnonymousClass1 r0 = new NetworkCallback() {
                            public final void onAvailable(Network network) {
                                super.onAvailable(network);
                                g.b(true, "SYSTEM_CONNECTIVITY_CHANGE");
                            }

                            public final void onLost(Network network) {
                                super.onLost(network);
                                g.b(false, "SYSTEM_CONNECTIVITY_CHANGE");
                            }
                        };
                        d.put(str, r0);
                        connectivityManager.registerDefaultNetworkCallback(r0);
                    }
                    return;
                }
                a aVar = new a();
                c.put(str, aVar);
                b2.registerReceiver(aVar, new IntentFilter(str));
            }
        }
    }

    public final void a(b bVar) {
        if (VERSION.SDK_INT < 28) {
            a("android.net.conn.CONNECTIVITY_CHANGE", bVar);
        } else {
            a("SYSTEM_CONNECTIVITY_CHANGE", bVar);
        }
    }

    /* access modifiers changed from: private */
    public static void b(boolean z, String str) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) b.get(str);
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    ((b) it.next()).a(z);
                } catch (Exception e2) {
                    new StringBuilder("SDK encountered unexpected error in SystemBroadcastObserver.onServiceChanged event handler; ").append(e2.getMessage());
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void a(b bVar, String str) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) b.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(bVar);
            if (copyOnWriteArrayList.size() == 0) {
                Context b2 = com.inmobi.commons.a.a.b();
                if (b2 != null) {
                    if ("SYSTEM_CONNECTIVITY_CHANGE".equals(str) && d.get(str) != null) {
                        ConnectivityManager connectivityManager = (ConnectivityManager) b2.getSystemService("connectivity");
                        if (connectivityManager != null) {
                            connectivityManager.unregisterNetworkCallback((NetworkCallback) d.get(str));
                            d.remove(str);
                        }
                    } else if (c.get(str) != null) {
                        b2.unregisterReceiver((BroadcastReceiver) c.get(str));
                        c.remove(str);
                    }
                }
            }
        }
    }
}

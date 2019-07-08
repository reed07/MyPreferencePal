package com.samsung.android.sdk.healthdata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.healthdata.IHealthDataObserver.Stub;
import com.samsung.android.sdk.internal.healthdata.ErrorUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public abstract class HealthDataObserver {
    private static final ArrayList<HealthDataObserver> b = new ArrayList<>();
    /* access modifiers changed from: private */
    public final Handler a;
    private final Stub c = new Stub() {
        public final void onChange(String str) {
            if (HealthDataObserver.this.a != null) {
                HealthDataObserver.this.a.sendMessage(HealthDataObserver.this.a.obtainMessage(0, str));
            } else {
                HealthDataObserver.this.onChange(str);
            }
        }
    };

    static class a extends Handler {
        private final WeakReference<HealthDataObserver> a;

        public a(HealthDataObserver healthDataObserver, Looper looper) {
            super(looper);
            this.a = new WeakReference<>(healthDataObserver);
        }

        public final void handleMessage(Message message) {
            HealthDataObserver healthDataObserver = (HealthDataObserver) this.a.get();
            if (healthDataObserver != null) {
                healthDataObserver.onChange((String) message.obj);
            }
        }
    }

    public abstract void onChange(String str);

    public HealthDataObserver(Handler handler) {
        Looper looper = handler != null ? handler.getLooper() : Looper.myLooper();
        if (looper == null) {
            this.a = null;
        } else {
            this.a = new a(this, looper);
        }
    }

    public static void addObserver(HealthDataStore healthDataStore, String str, HealthDataObserver healthDataObserver) {
        if (!TextUtils.isEmpty(str)) {
            b(healthDataObserver);
            synchronized (b) {
                if (!b.contains(healthDataObserver)) {
                    b.add(healthDataObserver);
                }
                try {
                    IDataWatcher iDataWatcher = HealthDataStore.getInterface(healthDataStore).getIDataWatcher();
                    if (iDataWatcher != null) {
                        iDataWatcher.registerDataObserver2(healthDataStore.a().getPackageName(), str, healthDataObserver.c);
                    } else {
                        throw new IllegalStateException("IDataWatcher is null");
                    }
                } catch (RemoteException e) {
                    String str2 = "Health.Observer";
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(" registration failed: ");
                    sb.append(e.toString());
                    Log.d(str2, sb.toString());
                    throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
                }
            }
            return;
        }
        throw new IllegalArgumentException("Data type is invalid");
    }

    public static void removeObserver(HealthDataStore healthDataStore, HealthDataObserver healthDataObserver) {
        b(healthDataObserver);
        synchronized (b) {
            b.remove(healthDataObserver);
            try {
                IDataWatcher iDataWatcher = HealthDataStore.getInterface(healthDataStore).getIDataWatcher();
                if (iDataWatcher != null) {
                    iDataWatcher.unregisterDataObserver2(healthDataStore.a().getPackageName(), healthDataObserver.c);
                } else {
                    throw new IllegalStateException("IDataWatcher is null");
                }
            } catch (RemoteException e) {
                String str = "Health.Observer";
                StringBuilder sb = new StringBuilder("Object unregistration failed: ");
                sb.append(e.toString());
                Log.d(str, sb.toString());
                throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
            }
        }
    }

    private static void b(HealthDataObserver healthDataObserver) {
        if (healthDataObserver != null) {
            Handler handler = healthDataObserver.a;
            if (handler == null || handler.getLooper() == null) {
                throw new IllegalStateException("This thread has no looper");
            }
            return;
        }
        throw new IllegalArgumentException("Invalid observer instance");
    }
}

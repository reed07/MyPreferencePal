package com.facebook.ads.internal.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;

public class AdsProcessPriorityService extends Service {
    private Messenger a;

    public IBinder onBind(Intent intent) {
        return this.a.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.a = new Messenger(new Handler());
    }
}

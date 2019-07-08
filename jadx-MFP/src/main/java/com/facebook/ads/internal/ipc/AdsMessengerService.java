package com.facebook.ads.internal.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.settings.AdInternalSettings;

@UiThread
public class AdsMessengerService extends Service {
    private Messenger a;
    /* access modifiers changed from: private */
    public boolean b;
    /* access modifiers changed from: private */
    public final ServiceConnection c = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AdsMessengerService.this.b = true;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            AdsMessengerService.this.b = false;
            AdsMessengerService adsMessengerService = AdsMessengerService.this;
            adsMessengerService.unbindService(adsMessengerService.c);
        }
    };

    @VisibleForTesting
    public static class a extends Handler {
        private final a a;

        private a(Context context) {
            this.a = new a(context);
        }

        public void handleMessage(Message message) {
            String string = message.getData().getString("STR_AD_ID_KEY");
            switch (message.what) {
                case 1:
                    com.facebook.ads.internal.e.a.a().a(string, message.replyTo);
                    break;
                case 2:
                    com.facebook.ads.internal.e.a.a().d(string);
                    return;
                default:
                    if (!this.a.a(message)) {
                        super.handleMessage(message);
                        break;
                    }
                    break;
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return this.a.getBinder();
    }

    public void onCreate() {
        AdInternalSettings.d = true;
        com.facebook.ads.internal.n.a.a(this);
        com.facebook.ads.internal.n.a.b(this);
        this.a = new Messenger(new a(getApplicationContext()));
        if (com.facebook.ads.internal.r.a.Y(getApplicationContext())) {
            bindService(new Intent(getApplicationContext(), AdsProcessPriorityService.class), this.c, 1);
        }
    }

    public void onDestroy() {
        com.facebook.ads.internal.e.a.a().b();
        if (this.b) {
            unbindService(this.c);
        }
    }
}

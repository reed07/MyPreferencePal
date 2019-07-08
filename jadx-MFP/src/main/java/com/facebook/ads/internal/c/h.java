package com.facebook.ads.internal.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.ipc.AdsMessengerService;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.w.h.a;
import com.facebook.ads.internal.w.h.b;

public class h extends Handler {
    @Nullable
    Messenger a;
    boolean b;
    boolean c;
    private final Context d;
    private final Messenger e;
    /* access modifiers changed from: private */
    public final b f;
    private ServiceConnection g = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            h.this.a = new Messenger(iBinder);
            h.this.a("Attached.");
            try {
                h.this.a(h.this.a, 1, null);
                if (h.this.c) {
                    h.this.c = false;
                    h.this.f.b();
                }
            } catch (RemoteException unused) {
                h.b(h.this);
            }
            h.this.a("Remote service connected.");
        }

        public void onServiceDisconnected(ComponentName componentName) {
            h.this.a("Disconnected.");
            if (h.this.b) {
                h.b(h.this);
            }
        }
    };

    h(Context context, b bVar) {
        super(Looper.getMainLooper());
        this.d = context;
        this.e = new Messenger(this);
        this.f = bVar;
    }

    static /* synthetic */ void b(h hVar) {
        hVar.a = null;
        hVar.b();
        hVar.f.a(10, AdErrorType.REMOTE_ADS_SERVICE_ERROR, (String) null);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        Context context = this.d;
        this.b = context.bindService(new Intent(context, AdsMessengerService.class), this.g, 1);
        if (this.b) {
            a("Binding.");
            return;
        }
        a.a(this.d, "ipc", b.ab, new Exception("Context.bind() returned false."));
        this.c = false;
        a("Can't bind to service. Use internal.");
        this.f.c();
    }

    /* access modifiers changed from: 0000 */
    public void a(Messenger messenger, int i, @Nullable Bundle bundle) {
        Message obtain = Message.obtain(null, i);
        obtain.replyTo = this.e;
        if (bundle != null) {
            obtain.setData(bundle);
        }
        obtain.getData().putString("STR_AD_ID_KEY", this.f.c);
        messenger.send(obtain);
    }

    public void a(String str) {
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        if (this.b) {
            Messenger messenger = this.a;
            if (messenger != null) {
                try {
                    a(messenger, 2, null);
                } catch (RemoteException unused) {
                }
            }
            this.b = false;
            this.d.unbindService(this.g);
            a("Unbinding.");
        }
    }

    public void handleMessage(Message message) {
        if (message.what == 3) {
            a("Received check alive.");
            return;
        }
        String string = message.getData().getString("STR_AD_ID_KEY");
        StringBuilder sb = new StringBuilder();
        sb.append("Received message ");
        sb.append(message.what);
        sb.append(" for Ad: ");
        sb.append(string);
        a(sb.toString());
        if (this.f.c.equals(string)) {
            this.f.a(message);
        }
    }
}

package com.facebook.ads.internal.q;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.facebook.ads.internal.w.h.b;
import com.facebook.share.internal.MessengerShareContentUtility;

public class a {
    /* access modifiers changed from: private */
    public final Context a;
    private final String b;
    private final String c;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public Messenger e;
    private final ServiceConnection f = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            a.this.d = true;
            a.this.e = new Messenger(iBinder);
            Message obtain = Message.obtain(null, 1);
            obtain.setData(a.a(a.this));
            try {
                a.this.e.send(obtain);
            } catch (RemoteException e) {
                com.facebook.ads.internal.w.h.a.b(a.this.a, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, b.y, e);
            }
            a.this.a.unbindService(this);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            try {
                a.this.a.unbindService(this);
            } catch (IllegalArgumentException unused) {
            }
            a.this.e = null;
            a.this.d = false;
        }
    };

    public a(Context context, String str, String str2) {
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    static /* synthetic */ Bundle a(a aVar) {
        Bundle bundle = new Bundle();
        bundle.putInt("PARAM_PROTOCOL_VERSION", 1);
        bundle.putString("PARAM_AN_UUID", aVar.c);
        bundle.putString("PARAM_REQUEST_ID", aVar.b);
        return bundle;
    }

    public void a() {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.audiencenetwork.AudienceNetworkService");
        try {
            if (!this.a.bindService(intent, this.f, 1)) {
                this.a.unbindService(this.f);
            }
        } catch (Exception e2) {
            com.facebook.ads.internal.w.h.a.b(this.a, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, b.z, e2);
        }
    }
}

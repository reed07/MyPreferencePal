package com.facebook.ads.internal.c;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.c.a.C0003a;
import com.facebook.ads.internal.c.a.c;
import com.facebook.ads.internal.n.a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.UUID;

@UiThread
public abstract class b implements c {
    final Context a;
    final h b;
    final String c = UUID.randomUUID().toString();
    final a d;
    private final Handler e;

    b(Context context) {
        this.a = context;
        a.b(this.a);
        this.d = new a(context, this);
        this.b = new h(context, this);
        this.e = new Handler(Looper.getMainLooper());
    }

    /* access modifiers changed from: 0000 */
    public abstract Message a();

    /* access modifiers changed from: 0000 */
    public void a(int i, @Nullable Bundle bundle) {
        try {
            Messenger messenger = this.b.a;
            if (messenger != null) {
                this.b.a(messenger, i, bundle);
            }
        } catch (RemoteException unused) {
            this.b.b();
            this.b.a("Error during sending command!");
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i, AdErrorType adErrorType, @Nullable String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString("STR_ERROR_MESSAGE_KEY", str);
        } else {
            bundle.putString("STR_ERROR_MESSAGE_KEY", adErrorType.getDefaultErrorMessage());
        }
        bundle.putInt("INT_ERROR_CODE_KEY", adErrorType.getErrorCode());
        a(i, this.c, bundle);
    }

    public void a(int i, String str, @Nullable Bundle bundle) {
        final Message obtain = Message.obtain(null, i);
        obtain.getData().putString("STR_AD_ID_KEY", str);
        if (bundle != null) {
            obtain.getData().putBundle("BUNDLE_EXTRAS_KEY", bundle);
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.e.post(new Runnable() {
                public void run() {
                    b.this.a(obtain);
                }
            });
        } else {
            a(obtain);
        }
    }

    public abstract void a(Message message);

    /* access modifiers changed from: 0000 */
    public boolean a(Context context) {
        if (AdInternalSettings.b) {
            return true;
        }
        if (AdInternalSettings.c) {
            com.facebook.ads.internal.w.h.a.a(this.a, "ipc", com.facebook.ads.internal.w.h.b.ag, new Exception("Multiprocess support is off"));
            return false;
        } else if (!com.facebook.ads.internal.r.a.V(context)) {
            return false;
        } else {
            int i = AdInternalSettings.e;
            AdInternalSettings.e = i + 1;
            if (i <= 0 && com.facebook.ads.internal.r.a.W(context)) {
                return false;
            }
            if (!com.facebook.ads.internal.w.d.a.a(this.a)) {
                int i2 = AdInternalSettings.f;
                AdInternalSettings.f = i2 + 1;
                if (i2 > 0) {
                    if (AdInternalSettings.f == 3) {
                        com.facebook.ads.internal.w.h.a.a(this.a, "ipc", com.facebook.ads.internal.w.h.b.af, new Exception("Marker file not created after 3 requests."));
                    }
                    return false;
                }
            }
            return com.facebook.ads.internal.n.c.b(this.a);
        }
    }

    public void b() {
        try {
            if (this.b.a != null) {
                this.b.a.send(a());
            }
        } catch (RemoteException unused) {
            this.b.b();
            c();
            this.b.a("Error during sending load command!");
        }
    }

    public abstract void c();

    public abstract void d();

    public void e() {
        if (com.facebook.ads.internal.r.a.Z(this.a) && this.d.a != C0003a.DESTROYED) {
            com.facebook.ads.internal.w.h.a.a(this.a, "api", com.facebook.ads.internal.w.h.b.s, new Exception("Destroy was not called."));
            Log.e(AudienceNetworkAds.TAG, "You didn't call destroy() for Ad Object. This may lead to leaking memory. Please, always call destroy() when you don't need this Ad Object any more.");
            d();
        }
    }
}

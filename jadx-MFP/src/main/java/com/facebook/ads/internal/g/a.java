package com.facebook.ads.internal.g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.mopub.common.GpsHelper;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class a {
    public static final String a = "a";
    private final String b;
    private final boolean c;
    private final c d;

    /* renamed from: com.facebook.ads.internal.g.a$a reason: collision with other inner class name */
    private static final class C0005a implements IInterface {
        private IBinder a;

        C0005a(IBinder iBinder) {
            this.a = iBinder;
        }

        public String a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public IBinder asBinder() {
            return this.a;
        }

        public boolean b() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z = true;
                obtain.writeInt(1);
                this.a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    private static final class b implements ServiceConnection {
        private AtomicBoolean a;
        private final BlockingQueue<IBinder> b;

        private b() {
            this.a = new AtomicBoolean(false);
            this.b = new LinkedBlockingDeque();
        }

        public IBinder a() {
            if (!this.a.compareAndSet(true, true)) {
                return (IBinder) this.b.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public enum c {
        SHARED_PREFS,
        FB4A,
        DIRECT,
        REFLECTION,
        SERVICE
    }

    private a(String str, boolean z, c cVar) {
        this.b = str;
        this.c = z;
        this.d = cVar;
    }

    private static a a(Context context) {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null) {
                return new a(advertisingIdInfo.getId(), advertisingIdInfo.isLimitAdTrackingEnabled(), c.DIRECT);
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static a a(Context context, com.facebook.ads.internal.g.c.a aVar) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot get advertising info on main thread.");
        } else if (com.facebook.ads.internal.w.b.a.a() && com.facebook.ads.internal.w.b.a.b("idfa_override")) {
            return new a(com.facebook.ads.internal.w.b.a.a("idfa_override"), false, c.DIRECT);
        } else {
            if (aVar != null && !TextUtils.isEmpty(aVar.b)) {
                return new a(aVar.b, aVar.c, c.FB4A);
            }
            a a2 = a(context);
            if (a2 == null || TextUtils.isEmpty(a2.a())) {
                Method a3 = d.a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
                if (a3 != null) {
                    Object a4 = d.a((Object) null, a3, context);
                    if (a4 != null && ((Integer) a4).intValue() == 0) {
                        Method a5 = d.a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
                        if (a5 != null) {
                            Object a6 = d.a((Object) null, a5, context);
                            if (a6 != null) {
                                Method a7 = d.a(a6.getClass(), "getId", (Class<?>[]) new Class[0]);
                                Method a8 = d.a(a6.getClass(), GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, (Class<?>[]) new Class[0]);
                                if (!(a7 == null || a8 == null)) {
                                    a2 = new a((String) d.a(a6, a7, new Object[0]), ((Boolean) d.a(a6, a8, new Object[0])).booleanValue(), c.REFLECTION);
                                }
                            }
                        }
                    }
                }
                a2 = null;
            }
            if (a2 == null || TextUtils.isEmpty(a2.a())) {
                a2 = b(context);
            }
            return a2;
        }
    }

    private static a b(Context context) {
        b bVar = new b();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, bVar, 1)) {
            try {
                C0005a aVar = new C0005a(bVar.a());
                return new a(aVar.a(), aVar.b(), c.SERVICE);
            } catch (Exception unused) {
            } finally {
                context.unbindService(bVar);
            }
        }
        return null;
    }

    public String a() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }

    public c c() {
        return this.d;
    }
}

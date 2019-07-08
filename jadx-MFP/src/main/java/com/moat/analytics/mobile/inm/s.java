package com.moat.analytics.mobile.inm;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.support.annotation.FloatRange;
import android.telephony.TelephonyManager;
import com.google.android.exoplayer2.util.MimeTypes;
import com.mopub.common.GpsHelper;
import java.lang.ref.WeakReference;

class s {
    /* access modifiers changed from: private */
    public static String a;
    private static a b;
    private static b c;

    static class a {
        /* access modifiers changed from: private */
        public boolean a;
        private String b;
        private String c;
        private String d;

        private a() {
            this.a = false;
            this.b = "_unknown_";
            this.c = "_unknown_";
            this.d = "_unknown_";
            try {
                Context c2 = s.c();
                if (c2 != null) {
                    this.a = true;
                    PackageManager packageManager = c2.getPackageManager();
                    this.c = c2.getPackageName();
                    this.b = packageManager.getApplicationLabel(c2.getApplicationInfo()).toString();
                    this.d = packageManager.getInstallerPackageName(this.c);
                    return;
                }
                p.a(3, "Util", (Object) this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                m.a(e);
            }
        }

        /* access modifiers changed from: 0000 */
        public String a() {
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public String b() {
            return this.c;
        }

        /* access modifiers changed from: 0000 */
        public String c() {
            String str = this.d;
            return str != null ? str : "_unknown_";
        }
    }

    static class b {
        String a;
        String b;
        Integer c;
        boolean d;
        boolean e;
        boolean f;

        private b() {
            this.a = "_unknown_";
            this.b = "_unknown_";
            this.c = Integer.valueOf(-1);
            this.d = false;
            this.e = false;
            this.f = false;
            try {
                Context c2 = s.c();
                if (c2 != null) {
                    this.f = true;
                    TelephonyManager telephonyManager = (TelephonyManager) c2.getSystemService("phone");
                    this.a = telephonyManager.getSimOperatorName();
                    this.b = telephonyManager.getNetworkOperatorName();
                    this.c = Integer.valueOf(telephonyManager.getPhoneType());
                    this.d = s.i();
                    this.e = s.b(c2);
                }
            } catch (Exception e2) {
                m.a(e2);
            }
        }
    }

    s() {
    }

    @FloatRange
    static double a() {
        try {
            AudioManager audioManager = (AudioManager) a.a().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            return ((double) h()) / ((double) audioManager.getStreamMaxVolume(3));
        } catch (Exception e) {
            m.a(e);
            return 0.0d;
        }
    }

    static void a(final Context context) {
        try {
            AsyncTask.execute(new Runnable() {
                public final void run() {
                    String str;
                    String str2;
                    try {
                        Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                        Class cls2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                        Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
                        if (!((Boolean) cls2.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0])).booleanValue()) {
                            s.a = (String) cls2.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
                            StringBuilder sb = new StringBuilder("Retrieved Advertising ID = ");
                            sb.append(s.a);
                            p.a(3, "Util", (Object) this, sb.toString());
                            return;
                        }
                        p.a(3, "Util", (Object) this, "User has limited ad tracking");
                    } catch (ClassNotFoundException e) {
                        e = e;
                        str = "Util";
                        str2 = "ClassNotFoundException while retrieving Advertising ID";
                        p.a(str, (Object) this, str2, e);
                    } catch (NoSuchMethodException e2) {
                        e = e2;
                        str = "Util";
                        str2 = "NoSuchMethodException while retrieving Advertising ID";
                        p.a(str, (Object) this, str2, e);
                    } catch (Exception e3) {
                        m.a(e3);
                    }
                }
            });
        } catch (Exception e) {
            m.a(e);
        }
    }

    static String b() {
        return a;
    }

    static boolean b(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    static Context c() {
        WeakReference<Context> weakReference = ((k) MoatAnalytics.getInstance()).e;
        if (weakReference != null) {
            return (Context) weakReference.get();
        }
        return null;
    }

    static a d() {
        a aVar = b;
        if (aVar == null || !aVar.a) {
            b = new a();
        }
        return b;
    }

    static b e() {
        b bVar = c;
        if (bVar == null || !bVar.f) {
            c = new b();
        }
        return c;
    }

    private static int h() {
        try {
            return ((AudioManager) a.a().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getStreamVolume(3);
        } catch (Exception e) {
            m.a(e);
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static boolean i() {
        Context c2 = c();
        int i = c2 != null ? VERSION.SDK_INT >= 17 ? Global.getInt(c2.getContentResolver(), "adb_enabled", 0) : Secure.getInt(c2.getContentResolver(), "adb_enabled", 0) : 0;
        return i == 1;
    }
}

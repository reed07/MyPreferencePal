package com.moat.analytics.mobile.und;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.support.annotation.FloatRange;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

class s {
    /* access modifiers changed from: private */
    public static String a;

    static class a {
        private boolean a = false;
        private String b = "_unknown_";
        private String c = "_unknown_";

        a() {
        }

        private void c() {
            try {
                Context c2 = s.c();
                if (c2 != null) {
                    PackageManager packageManager = c2.getPackageManager();
                    this.c = c2.getPackageName();
                    this.b = packageManager.getApplicationLabel(c2.getApplicationInfo()).toString();
                    this.a = true;
                    return;
                }
                p.a(3, "Util", (Object) this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                m.a(e);
            }
        }

        /* access modifiers changed from: 0000 */
        public String a() {
            if (this.a) {
                return this.b;
            }
            c();
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public String b() {
            if (this.a) {
                return this.c;
            }
            c();
            return this.c;
        }
    }

    s() {
    }

    @FloatRange
    static double a() {
        try {
            AudioManager audioManager = (AudioManager) a.a().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            return ((double) e()) / ((double) audioManager.getStreamMaxVolume(3));
        } catch (Exception e) {
            m.a(e);
            return 0.0d;
        }
    }

    static void a(final Context context) {
        try {
            AsyncTask.execute(new Runnable() {
                public void run() {
                    String str;
                    String str2;
                    try {
                        Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                        if (!advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            s.a = advertisingIdInfo.getId();
                            str = "Util";
                            StringBuilder sb = new StringBuilder();
                            sb.append("Retrieved Advertising ID = ");
                            sb.append(s.a);
                            str2 = sb.toString();
                        } else {
                            str = "Util";
                            str2 = "User has limited ad tracking";
                        }
                        p.a(3, str, (Object) this, str2);
                    } catch (Exception e) {
                        m.a(e);
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

    static Context c() {
        return (Context) ((k) MoatAnalytics.getInstance()).e.get();
    }

    private static int e() {
        try {
            return ((AudioManager) a.a().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getStreamVolume(3);
        } catch (Exception e) {
            m.a(e);
            return 0;
        }
    }
}

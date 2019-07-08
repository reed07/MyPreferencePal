package com.facebook.ads.internal.w.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import java.io.IOException;
import java.util.concurrent.Executors;
import org.xmlpull.v1.XmlPullParserException;

public class d {
    public static volatile a a = a.NOT_INITIALIZED;
    /* access modifiers changed from: private */
    public static int b = -1;

    enum a {
        NOT_INITIALIZED,
        INITIALIZING,
        INITIALIZED
    }

    public static int a(final Context context) {
        if (a == a.NOT_INITIALIZED) {
            if (!(a == a.INITIALIZED) && a == a.NOT_INITIALIZED) {
                a = a.INITIALIZING;
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    public void run() {
                        if (d.a != a.INITIALIZED) {
                            Context context = context;
                            SharedPreferences sharedPreferences = context.getSharedPreferences(com.facebook.ads.internal.w.f.a.a("FBAdPrefs", context), 0);
                            int i = sharedPreferences.getInt("AppMinSdkVersion", -1);
                            if (i != -1) {
                                d.b = i;
                            } else {
                                int c = VERSION.SDK_INT >= 24 ? d.d(context) : d.b(context);
                                d.b = c;
                                sharedPreferences.edit().putInt("AppMinSdkVersion", c).commit();
                            }
                            d.a = a.INITIALIZED;
                        }
                    }
                });
            }
        }
        return b;
    }

    public static int b(Context context) {
        try {
            XmlResourceParser openXmlResourceParser = context.getAssets().openXmlResourceParser("AndroidManifest.xml");
            while (openXmlResourceParser.next() != 1) {
                if (openXmlResourceParser.getEventType() == 2 && openXmlResourceParser.getName().equals("uses-sdk")) {
                    for (int i = 0; i < openXmlResourceParser.getAttributeCount(); i++) {
                        if (openXmlResourceParser.getAttributeName(i).equals("minSdkVersion")) {
                            return Integer.parseInt(openXmlResourceParser.getAttributeValue(i));
                        }
                    }
                    continue;
                }
            }
            return 0;
        } catch (IOException | XmlPullParserException unused) {
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static int d(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).minSdkVersion;
        } catch (NameNotFoundException unused) {
            return 0;
        }
    }
}

package com.inmobi.commons.core.utilities.uid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.f;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import java.security.MessageDigest;

/* compiled from: UidHelper */
public class c {
    /* access modifiers changed from: private */
    public static final String a = "c";
    private static final Object b = new Object();
    private static c c;
    /* access modifiers changed from: private */
    public static a d;

    public static String d() {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES;
    }

    public static c a() {
        c cVar = c;
        if (cVar == null) {
            synchronized (b) {
                cVar = c;
                if (cVar == null) {
                    cVar = new c();
                    c = cVar;
                }
            }
        }
        return cVar;
    }

    private c() {
    }

    public static void c() {
        try {
            if (f.a("root")) {
                a aVar = d;
                if (aVar != null) {
                    String str = aVar.a;
                    if (str != null) {
                        InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
                        String str2 = a;
                        StringBuilder sb = new StringBuilder("Publisher device Id is ");
                        sb.append(str);
                        Logger.a(internalLogLevel, str2, sb.toString());
                    }
                }
                return;
            }
            String e = e();
            InternalLogLevel internalLogLevel2 = InternalLogLevel.DEBUG;
            String str3 = a;
            StringBuilder sb2 = new StringBuilder("Publisher device Id is ");
            sb2.append(a(e, "SHA-1"));
            Logger.a(internalLogLevel2, str3, sb2.toString());
        } catch (Exception e2) {
            new StringBuilder("SDK encountered an unexpected error attempting to print the publisher test ID; ").append(e2.getMessage());
        }
    }

    static String a(String str, String str2) {
        if (str != null) {
            try {
                if (!"".equals(str.trim())) {
                    MessageDigest instance = MessageDigest.getInstance(str2);
                    instance.update(str.getBytes());
                    byte[] digest = instance.digest();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (byte b2 : digest) {
                        stringBuffer.append(Integer.toString((b2 & 255) + 256, 16).substring(1));
                    }
                    return stringBuffer.toString();
                }
            } catch (Exception e) {
                new StringBuilder("SDK encountered an unexpected error attempting to get digested UID; ").append(e.getMessage());
                return null;
            }
        }
        return "TEST_EMULATOR";
    }

    @SuppressLint({"HardwareIds"})
    static String e() {
        String str = "";
        Context b2 = a.b();
        if (b2 == null) {
            return str;
        }
        try {
            String string = Secure.getString(b2.getContentResolver(), "android_id");
            return string == null ? System.getString(b2.getContentResolver(), "android_id") : string;
        } catch (Exception unused) {
            return "";
        }
    }

    static a f() {
        return d;
    }

    private static boolean j() {
        try {
            AdvertisingIdClient.class.getName();
            return true;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    @Nullable
    public static Boolean g() {
        a();
        a aVar = d;
        if (aVar == null) {
            return null;
        }
        return aVar.b;
    }

    public final void b() {
        try {
            final b bVar = new b();
            a aVar = new a();
            d = aVar;
            aVar.a = bVar.a.c("adv_id");
            d.b = bVar.a.a.contains(Events.LIMIT_AD_TRACKING) ? Boolean.valueOf(bVar.a.b(Events.LIMIT_AD_TRACKING, true)) : null;
            if (f.a("root") && j()) {
                new Thread(new Runnable() {
                    public final void run() {
                        try {
                            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(a.b());
                            String id = advertisingIdInfo.getId();
                            boolean isLimitAdTrackingEnabled = advertisingIdInfo.isLimitAdTrackingEnabled();
                            c.d.a = id;
                            bVar.a.a("adv_id", id);
                            c.d.b = Boolean.valueOf(isLimitAdTrackingEnabled);
                            bVar.a.a(Events.LIMIT_AD_TRACKING, isLimitAdTrackingEnabled);
                        } catch (Exception e) {
                            c.a;
                            new StringBuilder("SDK encountered unexpected error in trying to set the advertising ID ").append(e.getMessage());
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in setting the advertising ID; ").append(e.getMessage());
        }
    }
}

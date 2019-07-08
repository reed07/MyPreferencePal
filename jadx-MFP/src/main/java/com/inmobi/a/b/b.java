package com.inmobi.a.b;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.inmobi.a.o;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.e;

/* compiled from: WifiInfoUtil */
public final class b {
    private static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    private static a a(boolean z, boolean z2) {
        a aVar;
        NoSuchMethodError e;
        Exception e2;
        Context b = a.b();
        String str = null;
        if (b == null) {
            return null;
        }
        try {
            WifiInfo connectionInfo = ((WifiManager) b.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                String bssid = connectionInfo.getBSSID();
                String ssid = connectionInfo.getSSID();
                if (bssid != null) {
                    if (!(z && ssid != null && ssid.endsWith("_nomap"))) {
                        aVar = new a();
                        try {
                            aVar.a = a(bssid);
                            String substring = (ssid == null || !ssid.startsWith("\"") || !ssid.endsWith("\"")) ? ssid : ssid.substring(1, ssid.length() - 1);
                            if (!z2) {
                                str = substring;
                            }
                            aVar.b = str;
                            aVar.c = connectionInfo.getRssi();
                            aVar.d = connectionInfo.getIpAddress();
                        } catch (NoSuchMethodError e3) {
                            e = e3;
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            return aVar;
                        } catch (Exception e4) {
                            e2 = e4;
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                            return aVar;
                        }
                        return aVar;
                    }
                }
            }
            aVar = null;
        } catch (NoSuchMethodError e5) {
            e = e5;
            aVar = null;
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return aVar;
        } catch (Exception e6) {
            e2 = e6;
            aVar = null;
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return aVar;
        }
        return aVar;
    }

    private static long a(String str) {
        String[] split = str.split("\\:");
        byte[] bArr = new byte[6];
        int i = 0;
        while (i < 6) {
            try {
                bArr[i] = (byte) Integer.parseInt(split[i], 16);
                i++;
            } catch (NumberFormatException unused) {
                return 0;
            }
        }
        return ((((long) bArr[0]) & 255) << 40) | ((((long) bArr[3]) & 255) << 16) | (((long) bArr[5]) & 255) | ((((long) bArr[4]) & 255) << 8) | ((((long) bArr[2]) & 255) << 24) | ((((long) bArr[1]) & 255) << 32);
    }

    public static a a() {
        boolean z = false;
        if (a.a() && e.a(a.b(), "signals", "android.permission.ACCESS_WIFI_STATE")) {
            com.inmobi.a.p.b bVar = o.a().a.a;
            if (bVar.l && bVar.a) {
                z = true;
            }
            if (z) {
                int i = o.a().a.a.j;
                return a(!a(i, 2), a(i, 1));
            }
        }
        return null;
    }
}

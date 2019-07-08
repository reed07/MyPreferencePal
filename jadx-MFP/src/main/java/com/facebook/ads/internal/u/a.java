package com.facebook.ads.internal.u;

import com.facebook.ads.internal.protocol.c;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class a {
    private static Map<String, Long> a = new ConcurrentHashMap();
    private static Map<String, Long> b = new ConcurrentHashMap();
    private static Map<String, String> c = new ConcurrentHashMap();

    public static void a(long j, b bVar) {
        a.put(d(bVar), Long.valueOf(j));
    }

    public static void a(String str, b bVar) {
        c.put(d(bVar), str);
    }

    public static boolean a(b bVar) {
        String d = d(bVar);
        boolean z = false;
        if (!b.containsKey(d)) {
            return false;
        }
        long longValue = ((Long) b.get(d)).longValue();
        c c2 = bVar.c();
        long j = -1000;
        if (!a.containsKey(d)) {
            switch (c2) {
                case BANNER:
                    j = 15000;
                    break;
            }
        } else {
            j = ((Long) a.get(d)).longValue();
        }
        if (System.currentTimeMillis() - longValue < j) {
            z = true;
        }
        return z;
    }

    public static void b(b bVar) {
        b.put(d(bVar), Long.valueOf(System.currentTimeMillis()));
    }

    public static String c(b bVar) {
        return (String) c.get(d(bVar));
    }

    private static String d(b bVar) {
        Locale locale = Locale.US;
        String str = "%s:%s:%s:%d:%d:%d";
        Object[] objArr = new Object[6];
        int i = 0;
        objArr[0] = bVar.b();
        objArr[1] = bVar.c();
        objArr[2] = bVar.a();
        objArr[3] = Integer.valueOf(bVar.d() == null ? 0 : bVar.d().a());
        if (bVar.d() != null) {
            i = bVar.d().b();
        }
        objArr[4] = Integer.valueOf(i);
        objArr[5] = Integer.valueOf(bVar.e());
        return String.format(locale, str, objArr);
    }
}

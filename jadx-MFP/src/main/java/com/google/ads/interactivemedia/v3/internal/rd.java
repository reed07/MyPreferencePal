package com.google.ads.interactivemedia.v3.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: IMASDK */
public final class rd {
    private static final Pattern a = Pattern.compile("^NOTE(( |\t).*)?$");

    public static void a(ut utVar) throws ca {
        int d = utVar.d();
        if (!b(utVar)) {
            utVar.c(d);
            String str = "Expected WEBVTT. Got ";
            String valueOf = String.valueOf(utVar.s());
            throw new ca(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    public static boolean b(ut utVar) {
        String s = utVar.s();
        return s != null && s.startsWith("WEBVTT");
    }

    public static long a(String str) throws NumberFormatException {
        String[] b = vf.b(str, "\\.");
        long j = 0;
        for (String parseLong : vf.a(b[0], ":")) {
            j = (j * 60) + Long.parseLong(parseLong);
        }
        long j2 = j * 1000;
        if (b.length == 2) {
            j2 += Long.parseLong(b[1]);
        }
        return j2 * 1000;
    }

    public static Matcher c(ut utVar) {
        String s;
        while (true) {
            String s2 = utVar.s();
            if (s2 == null) {
                return null;
            }
            if (a.matcher(s2).matches()) {
                do {
                    s = utVar.s();
                    if (s == null) {
                        break;
                    }
                } while (!s.isEmpty());
            } else {
                Matcher matcher = rc.a.matcher(s2);
                if (matcher.matches()) {
                    return matcher;
                }
            }
        }
    }
}

package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.js.a;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: IMASDK */
public final class fu {
    private static final Pattern c = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    public int a = -1;
    public int b = -1;

    public final boolean a(js jsVar) {
        for (int i = 0; i < jsVar.a(); i++) {
            a a2 = jsVar.a(i);
            if (a2 instanceof ki) {
                ki kiVar = (ki) a2;
                if ("iTunSMPB".equals(kiVar.a) && a(kiVar.b)) {
                    return true;
                }
            } else if (a2 instanceof kq) {
                kq kqVar = (kq) a2;
                if ("com.apple.iTunes".equals(kqVar.a) && "iTunSMPB".equals(kqVar.b) && a(kqVar.d)) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private final boolean a(String str) {
        Matcher matcher = c.matcher(str);
        if (matcher.find()) {
            try {
                int parseInt = Integer.parseInt(matcher.group(1), 16);
                int parseInt2 = Integer.parseInt(matcher.group(2), 16);
                if (parseInt > 0 || parseInt2 > 0) {
                    this.a = parseInt;
                    this.b = parseInt2;
                    return true;
                }
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    public final boolean a() {
        return (this.a == -1 || this.b == -1) ? false : true;
    }
}

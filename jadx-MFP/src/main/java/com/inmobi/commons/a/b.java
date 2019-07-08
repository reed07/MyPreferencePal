package com.inmobi.commons.a;

import android.content.Context;
import com.inmobi.commons.core.d.c;

/* compiled from: SdkInfo */
public final class b {
    public static String a() {
        char[] charArray;
        String str = "";
        for (char c : "7.2.6".toCharArray()) {
            if (c == '.') {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("T");
                str = sb.toString();
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append((char) ((c - '0') + 65));
                str = sb2.toString();
            }
        }
        StringBuilder sb3 = new StringBuilder("pr-SAND-");
        sb3.append(str);
        sb3.append("-20190208");
        return sb3.toString();
    }

    public static String a(Context context) {
        return c.a(context, "sdk_version_store").c("sdk_version");
    }

    public static void a(Context context, boolean z) {
        c.a(context, "sdk_version_store").a("db_deletion_failed", z);
    }
}

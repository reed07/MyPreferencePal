package com.facebook.ads.internal.w.e;

import android.content.Context;
import android.support.annotation.AnyThread;
import android.text.TextUtils;
import com.facebook.ads.internal.l.b;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.v.a.a;
import java.util.HashSet;
import java.util.Set;

@AnyThread
public class d {
    private static String a;
    private static final Set<String> b = new HashSet(1);
    private static final Set<String> c = new HashSet(2);

    static {
        b.add("1ww8E0AYsR2oX5lndk2hwp2Uosk=\n");
        c.add("toZ2GRnRjC9P5VVUdCpOrFH8lfQ=\n");
        c.add("3lKvjNsfmrn+WmfDhvr2iVh/yRs=\n");
        c.add("B08QtE4yLCdli4rptyqAEczXOeA=\n");
        c.add("XZXI6anZbdKf+taURdnyUH5ipgM=\n");
    }

    public static a a(Context context) {
        a aVar = new a();
        a(context, aVar, true);
        return aVar;
    }

    public static a a(Context context, boolean z) {
        a aVar = new a();
        a(context, aVar, z);
        if (!a()) {
            aVar.b(c);
            aVar.a(b);
        }
        return aVar;
    }

    private static void a(Context context, a aVar, boolean z) {
        if (a()) {
            aVar.c(360000);
            aVar.d(120000);
        } else {
            aVar.c(30000);
        }
        aVar.b(3);
        aVar.a("user-agent", com.facebook.ads.internal.n.d.a(new b(context), context, z));
    }

    public static boolean a() {
        String urlPrefix = AdInternalSettings.getUrlPrefix();
        return !TextUtils.isEmpty(urlPrefix) && urlPrefix.endsWith(".sb");
    }

    public static a b(Context context) {
        return a(context, true);
    }
}

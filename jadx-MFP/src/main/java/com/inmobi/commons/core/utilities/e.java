package com.inmobi.commons.core.utilities;

import android.content.Context;
import com.inmobi.commons.core.e.b;
import java.util.HashMap;

/* compiled from: PermissionUtils */
public final class e {
    public static boolean a(Context context, String str, String str2) {
        try {
            return context.checkCallingOrSelfPermission(str2) == 0;
        } catch (Exception e) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("type", "RuntimeException");
                StringBuilder sb = new StringBuilder();
                sb.append(e.getMessage());
                hashMap.put("message", sb.toString());
                b.a();
                b.a(str, "ExceptionCaught", hashMap);
            } catch (Exception unused) {
                StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                sb2.append(e.getMessage());
                sb2.append(")");
            }
            return false;
        }
    }
}

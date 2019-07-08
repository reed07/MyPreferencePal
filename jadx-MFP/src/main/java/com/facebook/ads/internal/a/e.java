package com.facebook.ads.internal.a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.w.h.b;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;

public class e {

    public interface a {
        d a();

        Collection<String> b();

        String getClientToken();
    }

    public static Collection<String> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.optString(i));
        }
        return hashSet;
    }

    public static boolean a(Context context, a aVar, c cVar) {
        boolean z;
        d a2 = aVar.a();
        if (!(a2 == null || a2 == d.NONE)) {
            Collection b = aVar.b();
            if (b != null && !b.isEmpty()) {
                Iterator it = b.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (a(context, (String) it.next())) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z == (a2 == d.INSTALLED)) {
                    String clientToken = aVar.getClientToken();
                    if (!TextUtils.isEmpty(clientToken)) {
                        cVar.b(clientToken, null);
                        return true;
                    }
                    com.facebook.ads.internal.w.h.a.b(context, "api", b.j, new Exception("Ad is invalidated without token."));
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException | RuntimeException unused) {
            return false;
        }
    }
}

package com.inmobi.ads;

import android.os.Build.VERSION;
import com.inmobi.ads.cache.a;
import com.inmobi.ads.cache.d;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeGifAsset */
class aq extends ak {
    private static final String A = "aq";
    t z;

    aq(String str, String str2, al alVar, String str3, int i, JSONObject jSONObject) {
        this(str, str2, alVar, str3, new LinkedList(), i, jSONObject);
    }

    aq(String str, String str2, al alVar, String str3, List<NativeTracker> list, int i, JSONObject jSONObject) {
        t tVar;
        super(str, str2, "GIF", alVar, list);
        d.a();
        a b = d.b(str3);
        this.e = b == null ? null : b.e;
        if (b != null) {
            try {
                String str4 = b.e;
                if (VERSION.SDK_INT < 28) {
                    tVar = new u(str4);
                } else {
                    tVar = new l(str4);
                }
                this.z = tVar;
            } catch (Exception e) {
                new StringBuilder("Exception in decoding GIF : ").append(e.getMessage());
                this.z = null;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
        if (jSONObject != null) {
            this.i = i;
            this.f = jSONObject;
        }
    }
}

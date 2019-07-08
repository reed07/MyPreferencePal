package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Looper;
import org.json.JSONObject;

/* compiled from: IMASDK */
public class ax {
    private static sh a;
    private final d b;
    private final d c;
    private final boolean d;

    public static cn a(Context context, cl clVar, rz rzVar) {
        bw bwVar = new bw();
        Looper a2 = vf.a();
        Context context2 = context;
        cl clVar2 = clVar;
        rz rzVar2 = rzVar;
        cn cnVar = new cn(context2, clVar2, rzVar2, bwVar, null, a(context), new cv(), a2);
        return cnVar;
    }

    private static synchronized sh a(Context context) {
        sh shVar;
        synchronized (ax.class) {
            if (a == null) {
                a = new st(context).a();
            }
            shVar = a;
        }
        return shVar;
    }

    private ax(d dVar, d dVar2, boolean z) {
        this.b = dVar;
        if (dVar2 == null) {
            this.c = d.NONE;
        } else {
            this.c = dVar2;
        }
        this.d = z;
    }

    public static ax a(d dVar, d dVar2, boolean z) {
        ho.a((Object) dVar, "Impression owner is null");
        ho.a(dVar);
        return new ax(dVar, dVar2, z);
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        z.a(jSONObject, "impressionOwner", this.b);
        z.a(jSONObject, "videoEventsOwner", this.c);
        z.a(jSONObject, "isolateVerificationScripts", Boolean.valueOf(this.d));
        return jSONObject;
    }
}

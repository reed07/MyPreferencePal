package com.facebook.ads.internal.k;

import com.facebook.ads.internal.w.b.o;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.util.HashMap;

public class a extends d {
    public a(String str, String str2) {
        double b = o.b();
        String c = o.c();
        HashMap hashMap = new HashMap();
        hashMap.put(IpcUtil.KEY_CODE, str);
        hashMap.put("value", str2);
        super(b, c, hashMap);
    }

    public String a() {
        return "client_response";
    }
}

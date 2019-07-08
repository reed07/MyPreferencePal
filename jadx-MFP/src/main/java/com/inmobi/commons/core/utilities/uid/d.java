package com.inmobi.commons.core.utilities.uid;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: UidMap */
public class d {
    private Map<String, Boolean> a;

    public d(Map<String, Boolean> map) {
        this.a = map;
    }

    public final Map<String, String> a(String str, boolean z) {
        HashMap hashMap = new HashMap();
        String str2 = null;
        try {
            if (((Boolean) this.a.get("GPID")).booleanValue()) {
                c.a();
                a f = c.f();
                if (f != null) {
                    str2 = f.a;
                    if (str2 != null) {
                        if (z) {
                            str2 = a(str2, str);
                        }
                        hashMap.put("GPID", str2);
                    }
                }
            }
            a(str, z, hashMap, str2);
        } catch (Exception unused) {
            d.class.getSimpleName();
            a(str, z, hashMap, null);
        }
        return hashMap;
    }

    private void a(String str, boolean z, Map<String, String> map, String str2) {
        try {
            if (((Boolean) this.a.get("UM5")).booleanValue() && str2 == null) {
                c.a();
                c.a();
                String a2 = c.a(c.e(), "MD5");
                if (z) {
                    a2 = a(a2, str);
                }
                map.put("UM5", a2);
            }
            if (((Boolean) this.a.get("O1")).booleanValue() && str2 == null) {
                c.a();
                c.a();
                String a3 = c.a(c.e(), "SHA-1");
                if (z) {
                    a3 = a(a3, str);
                }
                map.put("O1", a3);
            }
        } catch (Exception unused) {
            d.class.getSimpleName();
        }
    }

    private static String a(String str, String str2) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bArr = new byte[bytes.length];
            byte[] bytes2 = str2.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                bArr[i] = (byte) (bytes[i] ^ bytes2[i % bytes2.length]);
            }
            return new String(Base64.encode(bArr, 2), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }
}

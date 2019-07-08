package com.facebook.ads.internal.adapters.b;

import java.io.Serializable;

public class i implements Serializable {
    private static final long serialVersionUID = -4041915335826065133L;
    private final String a;
    private final String b;

    i(String str, String str2) {
        this.a = a(str);
        this.b = a(str2);
    }

    private static String a(String str) {
        return "null".equalsIgnoreCase(str) ? "" : str;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}

package com.facebook.ads.internal.protocol;

public class b extends Exception {
    private final AdErrorType a;
    private final String b;

    public b(AdErrorType adErrorType, String str) {
        this(adErrorType, str, null);
    }

    public b(AdErrorType adErrorType, String str, Throwable th) {
        super(str, th);
        this.a = adErrorType;
        this.b = str;
    }

    public AdErrorType a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}

package com.facebook.ads.internal.protocol;

import android.text.TextUtils;

public class a {
    private final AdErrorType a;
    private final String b;

    public a(int i, String str) {
        this(AdErrorType.adErrorTypeFromCode(i), str);
    }

    public a(AdErrorType adErrorType, String str) {
        if (TextUtils.isEmpty(str)) {
            str = adErrorType.getDefaultErrorMessage();
        }
        this.a = adErrorType;
        this.b = str;
    }

    public static a a(AdErrorType adErrorType, String str) {
        return new a(adErrorType, str);
    }

    public static a a(b bVar) {
        return new a(bVar.a(), bVar.b());
    }

    public AdErrorType a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}

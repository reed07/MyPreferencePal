package com.facebook.ads.internal.v.a;

public class k extends l {
    public k(String str, p pVar) {
        super(str, null);
        this.b = j.POST;
        this.a = str;
        this.c = "application/x-www-form-urlencoded;charset=UTF-8";
        if (pVar != null) {
            this.d = pVar.b();
        }
    }

    public k(String str, p pVar, String str2, byte[] bArr) {
        super(str, pVar);
        this.b = j.POST;
        this.c = str2;
        this.d = bArr;
    }
}

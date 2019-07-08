package com.facebook.ads.internal.v.a;

public abstract class l {
    protected String a = "";
    protected j b;
    protected String c;
    protected byte[] d;

    public l(String str, p pVar) {
        if (str != null) {
            this.a = str;
        }
        if (pVar != null) {
            String a2 = pVar.a();
            StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append("?");
            sb.append(a2);
            this.a = sb.toString();
        }
    }

    public String a() {
        return this.a;
    }

    public j b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public byte[] d() {
        return this.d;
    }
}

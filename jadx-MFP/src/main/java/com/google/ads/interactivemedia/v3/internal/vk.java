package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class vk {
    public final int a;
    public final String b;

    public static vk a(ut utVar) {
        String str;
        utVar.d(2);
        int e = utVar.e();
        int i = e >> 1;
        int e2 = ((utVar.e() >> 3) & 31) | ((e & 1) << 5);
        if (i == 4 || i == 5) {
            str = "dvhe";
        } else if (i == 8) {
            str = "hev1";
        } else if (i != 9) {
            return null;
        } else {
            str = "avc3";
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 26);
        sb.append(str);
        sb.append(".0");
        sb.append(i);
        sb.append(".0");
        sb.append(e2);
        return new vk(i, e2, sb.toString());
    }

    private vk(int i, int i2, String str) {
        this.a = i;
        this.b = str;
    }
}

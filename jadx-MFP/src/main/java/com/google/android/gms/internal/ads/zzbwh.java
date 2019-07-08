package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

public final class zzbwh {
    public static final zzbwh zzgda;
    private static final zzbwh zzgdb;
    private static final zzbwh zzgdc;
    private static final zzbwh zzgdd;
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double w;
    private final double zzgcw;
    private final double zzgcx;
    private final double zzgcy;
    private final double zzgcz;

    private zzbwh(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        this.zzgcw = d6;
        this.zzgcx = d7;
        this.w = d8;
        this.a = d2;
        this.b = d3;
        this.c = d4;
        this.d = d5;
        this.zzgcy = d9;
        this.zzgcz = d10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzbwh zzbwh = (zzbwh) obj;
        return Double.compare(zzbwh.a, this.a) == 0 && Double.compare(zzbwh.b, this.b) == 0 && Double.compare(zzbwh.c, this.c) == 0 && Double.compare(zzbwh.d, this.d) == 0 && Double.compare(zzbwh.zzgcy, this.zzgcy) == 0 && Double.compare(zzbwh.zzgcz, this.zzgcz) == 0 && Double.compare(zzbwh.zzgcw, this.zzgcw) == 0 && Double.compare(zzbwh.zzgcx, this.zzgcx) == 0 && Double.compare(zzbwh.w, this.w) == 0;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.zzgcw);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.zzgcx);
        int i2 = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        long doubleToLongBits3 = Double.doubleToLongBits(this.w);
        int i3 = (i2 * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)));
        long doubleToLongBits4 = Double.doubleToLongBits(this.a);
        int i4 = (i3 * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)));
        long doubleToLongBits5 = Double.doubleToLongBits(this.b);
        int i5 = (i4 * 31) + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)));
        long doubleToLongBits6 = Double.doubleToLongBits(this.c);
        int i6 = (i5 * 31) + ((int) (doubleToLongBits6 ^ (doubleToLongBits6 >>> 32)));
        long doubleToLongBits7 = Double.doubleToLongBits(this.d);
        int i7 = (i6 * 31) + ((int) (doubleToLongBits7 ^ (doubleToLongBits7 >>> 32)));
        long doubleToLongBits8 = Double.doubleToLongBits(this.zzgcy);
        int i8 = (i7 * 31) + ((int) (doubleToLongBits8 ^ (doubleToLongBits8 >>> 32)));
        long doubleToLongBits9 = Double.doubleToLongBits(this.zzgcz);
        return (i8 * 31) + ((int) (doubleToLongBits9 ^ (doubleToLongBits9 >>> 32)));
    }

    public final String toString() {
        if (equals(zzgda)) {
            return "Rotate 0°";
        }
        if (equals(zzgdb)) {
            return "Rotate 90°";
        }
        if (equals(zzgdc)) {
            return "Rotate 180°";
        }
        if (equals(zzgdd)) {
            return "Rotate 270°";
        }
        double d2 = this.zzgcw;
        double d3 = this.zzgcx;
        double d4 = this.w;
        double d5 = this.a;
        double d6 = this.b;
        double d7 = this.c;
        double d8 = this.d;
        double d9 = this.zzgcy;
        double d10 = this.zzgcz;
        double d11 = d8;
        StringBuilder sb = new StringBuilder(260);
        sb.append("Matrix{u=");
        sb.append(d2);
        sb.append(", v=");
        sb.append(d3);
        sb.append(", w=");
        sb.append(d4);
        sb.append(", a=");
        sb.append(d5);
        sb.append(", b=");
        sb.append(d6);
        sb.append(", c=");
        sb.append(d7);
        sb.append(", d=");
        sb.append(d11);
        sb.append(", tx=");
        sb.append(d9);
        sb.append(", ty=");
        sb.append(d10);
        sb.append("}");
        return sb.toString();
    }

    public static zzbwh zzq(ByteBuffer byteBuffer) {
        double zzd = zzbb.zzd(byteBuffer);
        double zzd2 = zzbb.zzd(byteBuffer);
        double zze = zzbb.zze(byteBuffer);
        zzbwh zzbwh = new zzbwh(zzd, zzd2, zzbb.zzd(byteBuffer), zzbb.zzd(byteBuffer), zze, zzbb.zze(byteBuffer), zzbb.zze(byteBuffer), zzbb.zzd(byteBuffer), zzbb.zzd(byteBuffer));
        return zzbwh;
    }

    static {
        zzbwh zzbwh = new zzbwh(1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
        zzgda = zzbwh;
        zzbwh zzbwh2 = new zzbwh(0.0d, 1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
        zzgdb = zzbwh2;
        zzbwh zzbwh3 = new zzbwh(-1.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
        zzgdc = zzbwh3;
        zzbwh zzbwh4 = new zzbwh(0.0d, -1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
        zzgdd = zzbwh4;
    }
}

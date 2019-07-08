package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbh extends zzbut<zzbh> {
    private String stackTrace;
    public String zzdh;
    public Long zzdi;
    private String zzdj;
    private String zzdk;
    private Long zzdl;
    private Long zzdm;
    private String zzdn;
    private Long zzdo;
    private String zzdp;

    public zzbh() {
        this.zzdh = null;
        this.zzdi = null;
        this.stackTrace = null;
        this.zzdj = null;
        this.zzdk = null;
        this.zzdl = null;
        this.zzdm = null;
        this.zzdn = null;
        this.zzdo = null;
        this.zzdp = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        String str = this.zzdh;
        if (str != null) {
            zzbur.zzf(1, str);
        }
        Long l = this.zzdi;
        if (l != null) {
            zzbur.zzr(2, l.longValue());
        }
        String str2 = this.stackTrace;
        if (str2 != null) {
            zzbur.zzf(3, str2);
        }
        String str3 = this.zzdj;
        if (str3 != null) {
            zzbur.zzf(4, str3);
        }
        String str4 = this.zzdk;
        if (str4 != null) {
            zzbur.zzf(5, str4);
        }
        Long l2 = this.zzdl;
        if (l2 != null) {
            zzbur.zzr(6, l2.longValue());
        }
        Long l3 = this.zzdm;
        if (l3 != null) {
            zzbur.zzr(7, l3.longValue());
        }
        String str5 = this.zzdn;
        if (str5 != null) {
            zzbur.zzf(8, str5);
        }
        Long l4 = this.zzdo;
        if (l4 != null) {
            zzbur.zzr(9, l4.longValue());
        }
        String str6 = this.zzdp;
        if (str6 != null) {
            zzbur.zzf(10, str6);
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        String str = this.zzdh;
        if (str != null) {
            zzt += zzbur.zzg(1, str);
        }
        Long l = this.zzdi;
        if (l != null) {
            zzt += zzbur.zzm(2, l.longValue());
        }
        String str2 = this.stackTrace;
        if (str2 != null) {
            zzt += zzbur.zzg(3, str2);
        }
        String str3 = this.zzdj;
        if (str3 != null) {
            zzt += zzbur.zzg(4, str3);
        }
        String str4 = this.zzdk;
        if (str4 != null) {
            zzt += zzbur.zzg(5, str4);
        }
        Long l2 = this.zzdl;
        if (l2 != null) {
            zzt += zzbur.zzm(6, l2.longValue());
        }
        Long l3 = this.zzdm;
        if (l3 != null) {
            zzt += zzbur.zzm(7, l3.longValue());
        }
        String str5 = this.zzdn;
        if (str5 != null) {
            zzt += zzbur.zzg(8, str5);
        }
        Long l4 = this.zzdo;
        if (l4 != null) {
            zzt += zzbur.zzm(9, l4.longValue());
        }
        String str6 = this.zzdp;
        return str6 != null ? zzt + zzbur.zzg(10, str6) : zzt;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            switch (zzaku) {
                case 0:
                    return this;
                case 10:
                    this.zzdh = zzbuq.readString();
                    break;
                case 16:
                    this.zzdi = Long.valueOf(zzbuq.zzaln());
                    break;
                case 26:
                    this.stackTrace = zzbuq.readString();
                    break;
                case 34:
                    this.zzdj = zzbuq.readString();
                    break;
                case 42:
                    this.zzdk = zzbuq.readString();
                    break;
                case 48:
                    this.zzdl = Long.valueOf(zzbuq.zzaln());
                    break;
                case 56:
                    this.zzdm = Long.valueOf(zzbuq.zzaln());
                    break;
                case 66:
                    this.zzdn = zzbuq.readString();
                    break;
                case 72:
                    this.zzdo = Long.valueOf(zzbuq.zzaln());
                    break;
                case 82:
                    this.zzdp = zzbuq.readString();
                    break;
                default:
                    if (super.zza(zzbuq, zzaku)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }
}

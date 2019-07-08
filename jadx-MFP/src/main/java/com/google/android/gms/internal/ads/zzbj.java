package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbj extends zzbut<zzbj> {
    public String zzdq;
    private String zzdr;
    private String zzds;
    private String zzdt;
    private String zzdu;
    public String zzdv;

    public final void zza(zzbur zzbur) throws IOException {
        String str = this.zzdq;
        if (str != null) {
            zzbur.zzf(1, str);
        }
        String str2 = this.zzdr;
        if (str2 != null) {
            zzbur.zzf(2, str2);
        }
        String str3 = this.zzds;
        if (str3 != null) {
            zzbur.zzf(3, str3);
        }
        String str4 = this.zzdt;
        if (str4 != null) {
            zzbur.zzf(4, str4);
        }
        String str5 = this.zzdu;
        if (str5 != null) {
            zzbur.zzf(5, str5);
        }
        String str6 = this.zzdv;
        if (str6 != null) {
            zzbur.zzf(6, str6);
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        String str = this.zzdq;
        if (str != null) {
            zzt += zzbur.zzg(1, str);
        }
        String str2 = this.zzdr;
        if (str2 != null) {
            zzt += zzbur.zzg(2, str2);
        }
        String str3 = this.zzds;
        if (str3 != null) {
            zzt += zzbur.zzg(3, str3);
        }
        String str4 = this.zzdt;
        if (str4 != null) {
            zzt += zzbur.zzg(4, str4);
        }
        String str5 = this.zzdu;
        if (str5 != null) {
            zzt += zzbur.zzg(5, str5);
        }
        String str6 = this.zzdv;
        return str6 != null ? zzt + zzbur.zzg(6, str6) : zzt;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 10) {
                this.zzdq = zzbuq.readString();
            } else if (zzaku == 18) {
                this.zzdr = zzbuq.readString();
            } else if (zzaku == 26) {
                this.zzds = zzbuq.readString();
            } else if (zzaku == 34) {
                this.zzdt = zzbuq.readString();
            } else if (zzaku == 42) {
                this.zzdu = zzbuq.readString();
            } else if (zzaku == 50) {
                this.zzdv = zzbuq.readString();
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
    }
}

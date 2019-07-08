package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzbv extends zzbt<Integer, Object> {
    public String zzdq;
    public String zzds;
    public String zzdt;
    public String zzdu;
    public long zzit;

    public zzbv(String str) {
        this();
        zzj(str);
    }

    public zzbv() {
        this.zzdq = "E";
        this.zzit = -1;
        this.zzds = "E";
        this.zzdt = "E";
        this.zzdu = "E";
    }

    /* access modifiers changed from: protected */
    public final void zzj(String str) {
        String str2;
        long j;
        String str3;
        String str4;
        String str5;
        HashMap zzk = zzk(str);
        if (zzk != null) {
            if (zzk.get(Integer.valueOf(0)) == null) {
                str2 = "E";
            } else {
                str2 = (String) zzk.get(Integer.valueOf(0));
            }
            this.zzdq = str2;
            if (zzk.get(Integer.valueOf(1)) == null) {
                j = -1;
            } else {
                j = ((Long) zzk.get(Integer.valueOf(1))).longValue();
            }
            this.zzit = j;
            if (zzk.get(Integer.valueOf(2)) == null) {
                str3 = "E";
            } else {
                str3 = (String) zzk.get(Integer.valueOf(2));
            }
            this.zzds = str3;
            if (zzk.get(Integer.valueOf(3)) == null) {
                str4 = "E";
            } else {
                str4 = (String) zzk.get(Integer.valueOf(3));
            }
            this.zzdt = str4;
            if (zzk.get(Integer.valueOf(4)) == null) {
                str5 = "E";
            } else {
                str5 = (String) zzk.get(Integer.valueOf(4));
            }
            this.zzdu = str5;
        }
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Object> zzv() {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(Integer.valueOf(0), this.zzdq);
        hashMap.put(Integer.valueOf(4), this.zzdu);
        hashMap.put(Integer.valueOf(3), this.zzdt);
        hashMap.put(Integer.valueOf(2), this.zzds);
        hashMap.put(Integer.valueOf(1), Long.valueOf(this.zzit));
        return hashMap;
    }
}

package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzdr extends zzbt<Integer, Long> {
    public Long zzgl;
    public Long zzgn;
    public Long zzgr;
    public Long zzgs;
    public Long zztl;
    public Long zztm;
    public Long zztn;
    public Long zzto;
    public Long zztp;
    public Long zztq;
    public Long zztr;

    public zzdr() {
    }

    public zzdr(String str) {
        zzj(str);
    }

    /* access modifiers changed from: protected */
    public final void zzj(String str) {
        HashMap zzk = zzk(str);
        if (zzk != null) {
            this.zztl = (Long) zzk.get(Integer.valueOf(0));
            this.zztm = (Long) zzk.get(Integer.valueOf(1));
            this.zztn = (Long) zzk.get(Integer.valueOf(2));
            this.zzgn = (Long) zzk.get(Integer.valueOf(3));
            this.zzgl = (Long) zzk.get(Integer.valueOf(4));
            this.zzto = (Long) zzk.get(Integer.valueOf(5));
            this.zztp = (Long) zzk.get(Integer.valueOf(6));
            this.zztq = (Long) zzk.get(Integer.valueOf(7));
            this.zzgs = (Long) zzk.get(Integer.valueOf(8));
            this.zzgr = (Long) zzk.get(Integer.valueOf(9));
            this.zztr = (Long) zzk.get(Integer.valueOf(10));
        }
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Long> zzv() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(Integer.valueOf(0), this.zztl);
        hashMap.put(Integer.valueOf(1), this.zztm);
        hashMap.put(Integer.valueOf(2), this.zztn);
        hashMap.put(Integer.valueOf(3), this.zzgn);
        hashMap.put(Integer.valueOf(4), this.zzgl);
        hashMap.put(Integer.valueOf(5), this.zzto);
        hashMap.put(Integer.valueOf(6), this.zztp);
        hashMap.put(Integer.valueOf(7), this.zztq);
        hashMap.put(Integer.valueOf(8), this.zzgs);
        hashMap.put(Integer.valueOf(9), this.zzgr);
        hashMap.put(Integer.valueOf(10), this.zztr);
        return hashMap;
    }
}

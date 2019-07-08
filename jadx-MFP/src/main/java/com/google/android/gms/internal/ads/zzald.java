package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzark
public final class zzald implements zzakp {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final zzaba zzbln;
    private final zzalg zzbma;
    private final boolean zzbum;
    private final zzakr zzdmn;
    private final boolean zzdms;
    private final boolean zzdmt;
    private final zzasi zzdnh;
    private final long zzdni;
    private boolean zzdnk = false;
    private final String zzdnm;
    private List<zzakx> zzdnn = new ArrayList();
    private zzaku zzdnr;

    public zzald(Context context, zzasi zzasi, zzalg zzalg, zzakr zzakr, boolean z, boolean z2, String str, long j, long j2, zzaba zzaba, boolean z3) {
        this.mContext = context;
        this.zzdnh = zzasi;
        this.zzbma = zzalg;
        this.zzdmn = zzakr;
        this.zzbum = z;
        this.zzdms = z2;
        this.zzdnm = str;
        this.mStartTime = j;
        this.zzdni = j2;
        this.zzbln = zzaba;
        this.zzdmt = z3;
    }

    public final zzakx zzh(List<zzakq> list) {
        Object obj;
        zzakx zzakx;
        zzaxz.zzdn("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzaay zzrg = this.zzbln.zzrg();
        zzwf zzwf = this.zzdnh.zzbst;
        int[] iArr = new int[2];
        if (zzwf.zzckm != null) {
            zzbv.zzlz();
            if (zzakz.zza(this.zzdnm, iArr)) {
                int i = 0;
                int i2 = iArr[0];
                int i3 = iArr[1];
                zzwf[] zzwfArr = zzwf.zzckm;
                int length = zzwfArr.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    zzwf zzwf2 = zzwfArr[i];
                    if (i2 == zzwf2.width && i3 == zzwf2.height) {
                        zzwf = zzwf2;
                        break;
                    }
                    i++;
                }
            }
        }
        Iterator it = list.iterator();
        int i4 = 1;
        while (it.hasNext()) {
            zzakq zzakq = (zzakq) it.next();
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(zzakq.zzdkv);
            zzaxz.zzen(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            Iterator it2 = zzakq.zzdkw.iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                zzaay zzrg2 = this.zzbln.zzrg();
                Object obj2 = this.mLock;
                synchronized (obj2) {
                    try {
                        if (this.zzdnk) {
                            zzakx = new zzakx(-1);
                        } else {
                            Context context = this.mContext;
                            zzalg zzalg = this.zzbma;
                            zzakr zzakr = this.zzdmn;
                            zzwb zzwb = this.zzdnh.zzdwg;
                            zzbbi zzbbi = this.zzdnh.zzbsp;
                            Iterator it3 = it;
                            boolean z = this.zzbum;
                            Iterator it4 = it2;
                            boolean z2 = this.zzdms;
                            zzwb zzwb2 = zzwb;
                            zzacp zzacp = this.zzdnh.zzbti;
                            zzaay zzaay = zzrg;
                            r10 = r10;
                            zzaay zzaay2 = zzrg2;
                            zzaku zzaku = r10;
                            obj = obj2;
                            try {
                                zzaku zzaku2 = new zzaku(context, str2, zzalg, zzakr, zzakq, zzwb2, zzwf, zzbbi, z, z2, zzacp, this.zzdnh.zzbtt, this.zzdnh.zzdwu, this.zzdnh.zzdxp, this.zzdmt);
                                this.zzdnr = zzaku;
                                zzakx zzj = this.zzdnr.zzj(this.mStartTime, this.zzdni);
                                this.zzdnn.add(zzj);
                                if (zzj.zzdna == 0) {
                                    zzaxz.zzdn("Adapter succeeded.");
                                    this.zzbln.zzg("mediation_network_succeed", str2);
                                    if (!arrayList.isEmpty()) {
                                        this.zzbln.zzg("mediation_networks_fail", TextUtils.join(",", arrayList));
                                    }
                                    this.zzbln.zza(zzaay2, "mls");
                                    this.zzbln.zza(zzaay, "ttm");
                                    return zzj;
                                }
                                zzaay zzaay3 = zzaay;
                                zzaay zzaay4 = zzaay2;
                                int i5 = zzj.zzdna;
                                arrayList.add(str2);
                                this.zzbln.zza(zzaay4, "mlf");
                                if (zzj.zzdnc != null) {
                                    zzayh.zzelc.post(new zzale(this, zzj));
                                }
                                it = it3;
                                zzrg = zzaay3;
                                i4 = i5;
                                it2 = it4;
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        obj = obj2;
                        throw th;
                    }
                }
                return zzakx;
            }
        }
        if (!arrayList.isEmpty()) {
            this.zzbln.zzg("mediation_networks_fail", TextUtils.join(",", arrayList));
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcxm)).booleanValue()) {
            return new zzakx(i4);
        }
        return new zzakx(1);
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzdnk = true;
            if (this.zzdnr != null) {
                this.zzdnr.cancel();
            }
        }
    }

    public final List<zzakx> zzui() {
        return this.zzdnn;
    }
}

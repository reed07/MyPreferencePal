package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfe.zza;
import com.google.android.gms.internal.measurement.zzfe.zzb;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzuo;
import com.google.android.gms.internal.measurement.zzya;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

final class zzdv extends zzfm {
    public zzdv(zzfn zzfn) {
        super(zzfn);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return false;
    }

    @WorkerThread
    public final byte[] zzb(@NonNull zzag zzag, @Size String str) {
        Integer num;
        zzfw zzfw;
        Bundle bundle;
        zzg zzg;
        zzfv zzfv;
        zzac zzac;
        long j;
        zzfw zzfw2;
        zzag zzag2 = zzag;
        String str2 = str;
        zzaf();
        this.zzada.zzgf();
        Preconditions.checkNotNull(zzag);
        Preconditions.checkNotEmpty(str);
        if (!zzgv().zze(str2, zzai.zzalh)) {
            zzgt().zzjn().zzg("Generating ScionPayload disabled. packageName", str2);
            return new byte[0];
        } else if ("_iap".equals(zzag2.name) || "_iapx".equals(zzag2.name)) {
            zzfv zzfv2 = new zzfv();
            zzjt().beginTransaction();
            try {
                zzg zzbm = zzjt().zzbm(str2);
                if (zzbm == null) {
                    zzgt().zzjn().zzg("Log and bundle not available. package_name", str2);
                    return new byte[0];
                } else if (!zzbm.isMeasurementEnabled()) {
                    zzgt().zzjn().zzg("Log and bundle disabled. package_name", str2);
                    byte[] bArr = new byte[0];
                    zzjt().endTransaction();
                    return bArr;
                } else {
                    zzfw zzfw3 = new zzfw();
                    zzfv2.zzaxh = new zzfw[]{zzfw3};
                    zzfw3.zzaxj = Integer.valueOf(1);
                    zzfw3.zzaxr = "android";
                    zzfw3.zztt = zzbm.zzal();
                    zzfw3.zzafp = zzbm.zzhg();
                    zzfw3.zzts = zzbm.zzak();
                    long zzhf = zzbm.zzhf();
                    if (zzhf == -2147483648L) {
                        num = null;
                    } else {
                        num = Integer.valueOf((int) zzhf);
                    }
                    zzfw3.zzayd = num;
                    zzfw3.zzaxv = Long.valueOf(zzbm.zzhh());
                    zzfw3.zzafi = zzbm.getGmpAppId();
                    if (TextUtils.isEmpty(zzfw3.zzafi)) {
                        zzfw3.zzawr = zzbm.zzhb();
                    }
                    zzfw3.zzaxz = Long.valueOf(zzbm.zzhi());
                    if (this.zzada.isEnabled() && zzq.zzie() && zzgv().zzas(zzfw3.zztt)) {
                        zzfw3.zzayj = null;
                    }
                    Pair zzbz = zzgu().zzbz(zzbm.zzal());
                    if (zzbm.zzhw() && zzbz != null && !TextUtils.isEmpty((CharSequence) zzbz.first)) {
                        zzfw3.zzaxx = zzr((String) zzbz.first, Long.toString(zzag2.zzaig));
                        zzfw3.zzaxy = (Boolean) zzbz.second;
                    }
                    zzgp().zzcl();
                    zzfw3.zzaxt = Build.MODEL;
                    zzgp().zzcl();
                    zzfw3.zzaxs = VERSION.RELEASE;
                    zzfw3.zzaxu = Integer.valueOf((int) zzgp().zziw());
                    zzfw3.zzahr = zzgp().zzix();
                    try {
                        zzfw3.zzafh = zzr(zzbm.getAppInstanceId(), Long.toString(zzag2.zzaig));
                        zzfw3.zzafk = zzbm.getFirebaseInstanceId();
                        String str3 = zzfw3.zztt;
                        List zzbl = zzjt().zzbl(str3);
                        if (zzgv().zzau(str2)) {
                            Iterator it = zzbl.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    zzfw2 = null;
                                    break;
                                }
                                zzfw2 = (zzfw) it.next();
                                if ("_lte".equals(zzfw2.name)) {
                                    break;
                                }
                            }
                            if (zzfw2 == null || zzfw2.value == null) {
                                zzfw zzfw4 = new zzfw(str3, "auto", "_lte", zzbx().currentTimeMillis(), Long.valueOf(0));
                                zzbl.add(zzfw4);
                                zzjt().zza(zzfw4);
                            }
                        }
                        zzfz[] zzfzArr = new zzfz[zzbl.size()];
                        for (int i = 0; i < zzbl.size(); i++) {
                            zzfz zzfz = new zzfz();
                            zzfzArr[i] = zzfz;
                            zzfz.name = ((zzfw) zzbl.get(i)).name;
                            zzfz.zzayw = Long.valueOf(((zzfw) zzbl.get(i)).zzaum);
                            zzjr().zza(zzfz, ((zzfw) zzbl.get(i)).value);
                        }
                        zzfw3.zzaxl = zzfzArr;
                        Bundle zziy = zzag2.zzahu.zziy();
                        zziy.putLong("_c", 1);
                        zzgt().zzjn().zzby("Marking in-app purchase as real-time");
                        zziy.putLong("_r", 1);
                        zziy.putString("_o", zzag2.origin);
                        if (zzgr().zzcz(zzfw3.zztt)) {
                            zzgr().zza(zziy, "_dbg", (Object) Long.valueOf(1));
                            zzgr().zza(zziy, "_r", (Object) Long.valueOf(1));
                        }
                        zzac zzg2 = zzjt().zzg(str2, zzag2.name);
                        if (zzg2 == null) {
                            bundle = zziy;
                            zzfw = zzfw3;
                            zzfv = zzfv2;
                            zzg = zzbm;
                            zzac zzac2 = new zzac(str, zzag2.name, 0, 0, zzag2.zzaig, 0, null, null, null, null);
                            zzac = zzac2;
                            j = 0;
                        } else {
                            bundle = zziy;
                            zzfw = zzfw3;
                            zzfv = zzfv2;
                            zzg = zzbm;
                            j = zzg2.zzahx;
                            zzac = zzg2.zzae(zzag2.zzaig);
                        }
                        zzjt().zza(zzac);
                        zzab zzab = new zzab(this.zzada, zzag2.origin, str, zzag2.name, zzag2.zzaig, j, bundle);
                        zzft zzft = new zzft();
                        zzfw zzfw5 = zzfw;
                        zzfw5.zzaxk = new zzft[]{zzft};
                        zzft.zzaxd = Long.valueOf(zzab.timestamp);
                        zzft.name = zzab.name;
                        zzft.zzaxe = Long.valueOf(zzab.zzaht);
                        zzft.zzaxc = new zzfu[zzab.zzahu.size()];
                        Iterator it2 = zzab.zzahu.iterator();
                        int i2 = 0;
                        while (it2.hasNext()) {
                            String str4 = (String) it2.next();
                            zzfu zzfu = new zzfu();
                            int i3 = i2 + 1;
                            zzft.zzaxc[i2] = zzfu;
                            zzfu.name = str4;
                            zzjr().zza(zzfu, zzab.zzahu.get(str4));
                            i2 = i3;
                        }
                        zzfw5.zzaym = (zzb) ((zzuo) zzb.zzmp().zzb((zza) ((zzuo) zza.zzmn().zzan(zzac.zzahv).zzda(zzag2.name).zzwo())).zzwo());
                        zzfw5.zzayc = zzjs().zza(zzg.zzal(), (zzft[]) null, zzfw5.zzaxl);
                        zzfw5.zzaxn = zzft.zzaxd;
                        zzfw5.zzaxo = zzft.zzaxd;
                        long zzhe = zzg.zzhe();
                        zzfw5.zzaxq = zzhe != 0 ? Long.valueOf(zzhe) : null;
                        long zzhd = zzg.zzhd();
                        if (zzhd != 0) {
                            zzhe = zzhd;
                        }
                        zzfw5.zzaxp = zzhe != 0 ? Long.valueOf(zzhe) : null;
                        zzg.zzhm();
                        zzfw5.zzaya = Integer.valueOf((int) zzg.zzhj());
                        zzfw5.zzaxw = Long.valueOf(zzgv().zzhh());
                        zzfw5.zzaxm = Long.valueOf(zzbx().currentTimeMillis());
                        zzfw5.zzayb = Boolean.TRUE;
                        zzg zzg3 = zzg;
                        zzg3.zzo(zzfw5.zzaxn.longValue());
                        zzg3.zzp(zzfw5.zzaxo.longValue());
                        zzjt().zza(zzg3);
                        zzjt().setTransactionSuccessful();
                        zzjt().endTransaction();
                        try {
                            byte[] bArr2 = new byte[zzfv.zzvx()];
                            zzya zzk = zzya.zzk(bArr2, 0, bArr2.length);
                            zzfv.zza(zzk);
                            zzk.zzza();
                            return zzjr().zzb(bArr2);
                        } catch (IOException e) {
                            zzgt().zzjg().zze("Data loss. Failed to bundle and serialize. appId", zzas.zzbw(str), e);
                            return null;
                        }
                    } catch (SecurityException e2) {
                        zzgt().zzjn().zzg("app instance id encryption failed", e2.getMessage());
                        byte[] bArr3 = new byte[0];
                        zzjt().endTransaction();
                        return bArr3;
                    }
                }
            } catch (SecurityException e3) {
                zzgt().zzjn().zzg("Resettable device id encryption failed", e3.getMessage());
                return new byte[0];
            } finally {
                zzjt().endTransaction();
            }
        } else {
            zzgt().zzjn().zze("Generating a payload for this event is not available. package_name, event_name", str2, zzag2.name);
            return null;
        }
    }

    private static String zzr(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }
}

package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzg.zza;
import com.google.android.gms.tagmanager.zzdi;
import com.google.android.gms.tagmanager.zzgj;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class zzrg {
    public static zzrk zza(zzl zzl) throws zzro {
        zzm[] zzmArr;
        zzp[] zzpArr = new zzp[zzl.zzou.length];
        for (int i = 0; i < zzl.zzou.length; i++) {
            zza(i, zzl, zzpArr, (Set<Integer>) new HashSet<Integer>(0));
        }
        zzrl zztb = zzrk.zztb();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < zzl.zzox.length; i2++) {
            arrayList.add(zza(zzl.zzox[i2], zzl, zzpArr, i2));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < zzl.zzoy.length; i3++) {
            arrayList2.add(zza(zzl.zzoy[i3], zzl, zzpArr, i3));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < zzl.zzow.length; i4++) {
            zzri zza = zza(zzl.zzow[i4], zzl, zzpArr, i4);
            zztb.zzc(zza);
            arrayList3.add(zza);
        }
        for (zzm zzm : zzl.zzoz) {
            zzrn zzrn = new zzrn();
            for (int valueOf : zzm.zzpj) {
                zzrn.zzd((zzri) arrayList2.get(Integer.valueOf(valueOf).intValue()));
            }
            for (int valueOf2 : zzm.zzpk) {
                zzrn.zze((zzri) arrayList2.get(Integer.valueOf(valueOf2).intValue()));
            }
            for (int valueOf3 : zzm.zzpl) {
                zzrn.zzf((zzri) arrayList.get(Integer.valueOf(valueOf3).intValue()));
            }
            for (int valueOf4 : zzm.zzpn) {
                zzrn.zzfj(zzl.zzou[Integer.valueOf(valueOf4).intValue()].string);
            }
            for (int valueOf5 : zzm.zzpm) {
                zzrn.zzg((zzri) arrayList.get(Integer.valueOf(valueOf5).intValue()));
            }
            for (int valueOf6 : zzm.zzpo) {
                zzrn.zzfk(zzl.zzou[Integer.valueOf(valueOf6).intValue()].string);
            }
            for (int valueOf7 : zzm.zzpp) {
                zzrn.zzh((zzri) arrayList3.get(Integer.valueOf(valueOf7).intValue()));
            }
            for (int valueOf8 : zzm.zzpr) {
                zzrn.zzfl(zzl.zzou[Integer.valueOf(valueOf8).intValue()].string);
            }
            for (int valueOf9 : zzm.zzpq) {
                zzrn.zzi((zzri) arrayList3.get(Integer.valueOf(valueOf9).intValue()));
            }
            for (int valueOf10 : zzm.zzps) {
                zzrn.zzfm(zzl.zzou[Integer.valueOf(valueOf10).intValue()].string);
            }
            zztb.zzb(zzrn.zztg());
        }
        zztb.zzfi(zzl.version);
        zztb.zzah(zzl.zzph);
        return zztb.zztd();
    }

    public static zzp zzk(zzp zzp) {
        zzp zzp2 = new zzp();
        zzp2.type = zzp.type;
        zzp2.zzqr = (int[]) zzp.zzqr.clone();
        if (zzp.zzqs) {
            zzp2.zzqs = zzp.zzqs;
        }
        return zzp2;
    }

    private static zzp zza(int i, zzl zzl, zzp[] zzpArr, Set<Integer> set) throws zzro {
        if (set.contains(Integer.valueOf(i))) {
            String valueOf = String.valueOf(set);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 90);
            sb.append("Value cycle detected.  Current value reference: ");
            sb.append(i);
            sb.append(".  Previous value references: ");
            sb.append(valueOf);
            sb.append(".");
            zzev(sb.toString());
        }
        zzp zzp = (zzp) zza(zzl.zzou, i, "values");
        if (zzpArr[i] != null) {
            return zzpArr[i];
        }
        zzp zzp2 = null;
        set.add(Integer.valueOf(i));
        int i2 = 0;
        switch (zzp.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                zzp2 = zzp;
                break;
            case 2:
                zza zzl2 = zzl(zzp);
                zzp zzk = zzk(zzp);
                zzk.zzqj = new zzp[zzl2.zzpv.length];
                int[] iArr = zzl2.zzpv;
                int length = iArr.length;
                int i3 = 0;
                while (i2 < length) {
                    int i4 = i3 + 1;
                    zzk.zzqj[i3] = zza(iArr[i2], zzl, zzpArr, set);
                    i2++;
                    i3 = i4;
                }
                zzp2 = zzk;
                break;
            case 3:
                zzp2 = zzk(zzp);
                zza zzl3 = zzl(zzp);
                if (zzl3.zzpw.length != zzl3.zzpx.length) {
                    int length2 = zzl3.zzpw.length;
                    int length3 = zzl3.zzpx.length;
                    StringBuilder sb2 = new StringBuilder(58);
                    sb2.append("Uneven map keys (");
                    sb2.append(length2);
                    sb2.append(") and map values (");
                    sb2.append(length3);
                    sb2.append(")");
                    zzev(sb2.toString());
                }
                zzp2.zzqk = new zzp[zzl3.zzpw.length];
                zzp2.zzql = new zzp[zzl3.zzpw.length];
                int[] iArr2 = zzl3.zzpw;
                int length4 = iArr2.length;
                int i5 = 0;
                int i6 = 0;
                while (i5 < length4) {
                    int i7 = i6 + 1;
                    zzp2.zzqk[i6] = zza(iArr2[i5], zzl, zzpArr, set);
                    i5++;
                    i6 = i7;
                }
                int[] iArr3 = zzl3.zzpx;
                int length5 = iArr3.length;
                int i8 = 0;
                while (i2 < length5) {
                    int i9 = i8 + 1;
                    zzp2.zzql[i8] = zza(iArr3[i2], zzl, zzpArr, set);
                    i2++;
                    i8 = i9;
                }
                break;
            case 4:
                zzp2 = zzk(zzp);
                zzp2.zzqm = zzgj.zzc(zza(zzl(zzp).zzqa, zzl, zzpArr, set));
                break;
            case 7:
                zzp2 = zzk(zzp);
                zza zzl4 = zzl(zzp);
                zzp2.zzqq = new zzp[zzl4.zzpz.length];
                int[] iArr4 = zzl4.zzpz;
                int length6 = iArr4.length;
                int i10 = 0;
                while (i2 < length6) {
                    int i11 = i10 + 1;
                    zzp2.zzqq[i10] = zza(iArr4[i2], zzl, zzpArr, set);
                    i2++;
                    i10 = i11;
                }
                break;
        }
        if (zzp2 == null) {
            String valueOf2 = String.valueOf(zzp);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 15);
            sb3.append("Invalid value: ");
            sb3.append(valueOf2);
            zzev(sb3.toString());
        }
        zzpArr[i] = zzp2;
        set.remove(Integer.valueOf(i));
        return zzp2;
    }

    private static zza zzl(zzp zzp) throws zzro {
        if (((zza) zzp.zza(zza.zzpt)) == null) {
            String valueOf = String.valueOf(zzp);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 54);
            sb.append("Expected a ServingValue and didn't get one. Value is: ");
            sb.append(valueOf);
            zzev(sb.toString());
        }
        return (zza) zzp.zza(zza.zzpt);
    }

    private static void zzev(String str) throws zzro {
        zzdi.e(str);
        throw new zzro(str);
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzro {
        if (i < 0 || i >= tArr.length) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 45);
            sb.append("Index out of bounds detected: ");
            sb.append(i);
            sb.append(" in ");
            sb.append(str);
            zzev(sb.toString());
        }
        return tArr[i];
    }

    private static zzri zza(zzh zzh, zzl zzl, zzp[] zzpArr, int i) throws zzro {
        zzrj zzsz = zzri.zzsz();
        for (int valueOf : zzh.zzoe) {
            zzk zzk = (zzk) zza(zzl.zzov, Integer.valueOf(valueOf).intValue(), "properties");
            String str = (String) zza(zzl.zzot, zzk.key, "keys");
            zzp zzp = (zzp) zza(zzpArr, zzk.value, "values");
            if (zzb.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzsz.zzm(zzp);
            } else {
                zzsz.zzb(str, zzp);
            }
        }
        return zzsz.zzta();
    }

    public static void zza(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}

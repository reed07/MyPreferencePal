package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzc.zza;
import com.google.android.gms.internal.measurement.zzc.zza.C0037zza;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.io.IOException;

public final class zzl extends zzyc<zzl> {
    public String version;
    private String[] zzos;
    public String[] zzot;
    public zzp[] zzou;
    public zzk[] zzov;
    public zzh[] zzow;
    public zzh[] zzox;
    public zzh[] zzoy;
    public zzm[] zzoz;
    private String zzpa;
    private String zzpb;
    private String zzpc;
    private zza zzpd;
    private float zzpe;
    private boolean zzpf;
    private String[] zzpg;
    public int zzph;

    public zzl() {
        this.zzos = zzyl.zzcfo;
        this.zzot = zzyl.zzcfo;
        this.zzou = zzp.zzk();
        this.zzov = zzk.zzh();
        this.zzow = zzh.zze();
        this.zzox = zzh.zze();
        this.zzoy = zzh.zze();
        this.zzoz = zzm.zzi();
        this.zzpa = "";
        this.zzpb = "";
        this.zzpc = "0";
        this.version = "";
        this.zzpd = null;
        this.zzpe = BitmapDescriptorFactory.HUE_RED;
        this.zzpf = false;
        this.zzpg = zzyl.zzcfo;
        this.zzph = 0;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzl)) {
            return false;
        }
        zzl zzl = (zzl) obj;
        if (!zzyg.equals((Object[]) this.zzos, (Object[]) zzl.zzos) || !zzyg.equals((Object[]) this.zzot, (Object[]) zzl.zzot) || !zzyg.equals((Object[]) this.zzou, (Object[]) zzl.zzou) || !zzyg.equals((Object[]) this.zzov, (Object[]) zzl.zzov) || !zzyg.equals((Object[]) this.zzow, (Object[]) zzl.zzow) || !zzyg.equals((Object[]) this.zzox, (Object[]) zzl.zzox) || !zzyg.equals((Object[]) this.zzoy, (Object[]) zzl.zzoy) || !zzyg.equals((Object[]) this.zzoz, (Object[]) zzl.zzoz)) {
            return false;
        }
        String str = this.zzpa;
        if (str == null) {
            if (zzl.zzpa != null) {
                return false;
            }
        } else if (!str.equals(zzl.zzpa)) {
            return false;
        }
        String str2 = this.zzpb;
        if (str2 == null) {
            if (zzl.zzpb != null) {
                return false;
            }
        } else if (!str2.equals(zzl.zzpb)) {
            return false;
        }
        String str3 = this.zzpc;
        if (str3 == null) {
            if (zzl.zzpc != null) {
                return false;
            }
        } else if (!str3.equals(zzl.zzpc)) {
            return false;
        }
        String str4 = this.version;
        if (str4 == null) {
            if (zzl.version != null) {
                return false;
            }
        } else if (!str4.equals(zzl.version)) {
            return false;
        }
        zza zza = this.zzpd;
        if (zza == null) {
            if (zzl.zzpd != null) {
                return false;
            }
        } else if (!zza.equals(zzl.zzpd)) {
            return false;
        }
        if (Float.floatToIntBits(this.zzpe) != Float.floatToIntBits(zzl.zzpe) || this.zzpf != zzl.zzpf || !zzyg.equals((Object[]) this.zzpg, (Object[]) zzl.zzpg) || this.zzph != zzl.zzph) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzl.zzcev == null || zzl.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzl.zzcev);
    }

    public final int hashCode() {
        int i;
        int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzyg.hashCode((Object[]) this.zzos)) * 31) + zzyg.hashCode((Object[]) this.zzot)) * 31) + zzyg.hashCode((Object[]) this.zzou)) * 31) + zzyg.hashCode((Object[]) this.zzov)) * 31) + zzyg.hashCode((Object[]) this.zzow)) * 31) + zzyg.hashCode((Object[]) this.zzox)) * 31) + zzyg.hashCode((Object[]) this.zzoy)) * 31) + zzyg.hashCode((Object[]) this.zzoz)) * 31;
        String str = this.zzpa;
        int i2 = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzpb;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzpc;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.version;
        int hashCode5 = hashCode4 + (str4 == null ? 0 : str4.hashCode());
        zza zza = this.zzpd;
        int i3 = hashCode5 * 31;
        if (zza == null) {
            i = 0;
        } else {
            i = zza.hashCode();
        }
        int floatToIntBits = (((((((((i3 + i) * 31) + Float.floatToIntBits(this.zzpe)) * 31) + (this.zzpf ? 1231 : 1237)) * 31) + zzyg.hashCode((Object[]) this.zzpg)) * 31) + this.zzph) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i2 = this.zzcev.hashCode();
        }
        return floatToIntBits + i2;
    }

    public final void zza(zzya zzya) throws IOException {
        String[] strArr = this.zzot;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.zzot;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                if (str != null) {
                    zzya.zzb(1, str);
                }
                i2++;
            }
        }
        zzp[] zzpArr = this.zzou;
        if (zzpArr != null && zzpArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzp[] zzpArr2 = this.zzou;
                if (i3 >= zzpArr2.length) {
                    break;
                }
                zzp zzp = zzpArr2[i3];
                if (zzp != null) {
                    zzya.zza(2, (zzyi) zzp);
                }
                i3++;
            }
        }
        zzk[] zzkArr = this.zzov;
        if (zzkArr != null && zzkArr.length > 0) {
            int i4 = 0;
            while (true) {
                zzk[] zzkArr2 = this.zzov;
                if (i4 >= zzkArr2.length) {
                    break;
                }
                zzk zzk = zzkArr2[i4];
                if (zzk != null) {
                    zzya.zza(3, (zzyi) zzk);
                }
                i4++;
            }
        }
        zzh[] zzhArr = this.zzow;
        if (zzhArr != null && zzhArr.length > 0) {
            int i5 = 0;
            while (true) {
                zzh[] zzhArr2 = this.zzow;
                if (i5 >= zzhArr2.length) {
                    break;
                }
                zzh zzh = zzhArr2[i5];
                if (zzh != null) {
                    zzya.zza(4, (zzyi) zzh);
                }
                i5++;
            }
        }
        zzh[] zzhArr3 = this.zzox;
        if (zzhArr3 != null && zzhArr3.length > 0) {
            int i6 = 0;
            while (true) {
                zzh[] zzhArr4 = this.zzox;
                if (i6 >= zzhArr4.length) {
                    break;
                }
                zzh zzh2 = zzhArr4[i6];
                if (zzh2 != null) {
                    zzya.zza(5, (zzyi) zzh2);
                }
                i6++;
            }
        }
        zzh[] zzhArr5 = this.zzoy;
        if (zzhArr5 != null && zzhArr5.length > 0) {
            int i7 = 0;
            while (true) {
                zzh[] zzhArr6 = this.zzoy;
                if (i7 >= zzhArr6.length) {
                    break;
                }
                zzh zzh3 = zzhArr6[i7];
                if (zzh3 != null) {
                    zzya.zza(6, (zzyi) zzh3);
                }
                i7++;
            }
        }
        zzm[] zzmArr = this.zzoz;
        if (zzmArr != null && zzmArr.length > 0) {
            int i8 = 0;
            while (true) {
                zzm[] zzmArr2 = this.zzoz;
                if (i8 >= zzmArr2.length) {
                    break;
                }
                zzm zzm = zzmArr2[i8];
                if (zzm != null) {
                    zzya.zza(7, (zzyi) zzm);
                }
                i8++;
            }
        }
        String str2 = this.zzpa;
        if (str2 != null && !str2.equals("")) {
            zzya.zzb(9, this.zzpa);
        }
        String str3 = this.zzpb;
        if (str3 != null && !str3.equals("")) {
            zzya.zzb(10, this.zzpb);
        }
        String str4 = this.zzpc;
        if (str4 != null && !str4.equals("0")) {
            zzya.zzb(12, this.zzpc);
        }
        String str5 = this.version;
        if (str5 != null && !str5.equals("")) {
            zzya.zzb(13, this.version);
        }
        zza zza = this.zzpd;
        if (zza != null) {
            zzya.zze(14, zza);
        }
        if (Float.floatToIntBits(this.zzpe) != Float.floatToIntBits(BitmapDescriptorFactory.HUE_RED)) {
            zzya.zza(15, this.zzpe);
        }
        String[] strArr3 = this.zzpg;
        if (strArr3 != null && strArr3.length > 0) {
            int i9 = 0;
            while (true) {
                String[] strArr4 = this.zzpg;
                if (i9 >= strArr4.length) {
                    break;
                }
                String str6 = strArr4[i9];
                if (str6 != null) {
                    zzya.zzb(16, str6);
                }
                i9++;
            }
        }
        int i10 = this.zzph;
        if (i10 != 0) {
            zzya.zzd(17, i10);
        }
        boolean z = this.zzpf;
        if (z) {
            zzya.zzb(18, z);
        }
        String[] strArr5 = this.zzos;
        if (strArr5 != null && strArr5.length > 0) {
            while (true) {
                String[] strArr6 = this.zzos;
                if (i >= strArr6.length) {
                    break;
                }
                String str7 = strArr6[i];
                if (str7 != null) {
                    zzya.zzb(19, str7);
                }
                i++;
            }
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        String[] strArr = this.zzot;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                String[] strArr2 = this.zzot;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                if (str != null) {
                    i4++;
                    i3 += zzya.zzgc(str);
                }
                i2++;
            }
            zzf = zzf + i3 + (i4 * 1);
        }
        zzp[] zzpArr = this.zzou;
        if (zzpArr != null && zzpArr.length > 0) {
            int i5 = zzf;
            int i6 = 0;
            while (true) {
                zzp[] zzpArr2 = this.zzou;
                if (i6 >= zzpArr2.length) {
                    break;
                }
                zzp zzp = zzpArr2[i6];
                if (zzp != null) {
                    i5 += zzya.zzb(2, (zzyi) zzp);
                }
                i6++;
            }
            zzf = i5;
        }
        zzk[] zzkArr = this.zzov;
        if (zzkArr != null && zzkArr.length > 0) {
            int i7 = zzf;
            int i8 = 0;
            while (true) {
                zzk[] zzkArr2 = this.zzov;
                if (i8 >= zzkArr2.length) {
                    break;
                }
                zzk zzk = zzkArr2[i8];
                if (zzk != null) {
                    i7 += zzya.zzb(3, (zzyi) zzk);
                }
                i8++;
            }
            zzf = i7;
        }
        zzh[] zzhArr = this.zzow;
        if (zzhArr != null && zzhArr.length > 0) {
            int i9 = zzf;
            int i10 = 0;
            while (true) {
                zzh[] zzhArr2 = this.zzow;
                if (i10 >= zzhArr2.length) {
                    break;
                }
                zzh zzh = zzhArr2[i10];
                if (zzh != null) {
                    i9 += zzya.zzb(4, (zzyi) zzh);
                }
                i10++;
            }
            zzf = i9;
        }
        zzh[] zzhArr3 = this.zzox;
        if (zzhArr3 != null && zzhArr3.length > 0) {
            int i11 = zzf;
            int i12 = 0;
            while (true) {
                zzh[] zzhArr4 = this.zzox;
                if (i12 >= zzhArr4.length) {
                    break;
                }
                zzh zzh2 = zzhArr4[i12];
                if (zzh2 != null) {
                    i11 += zzya.zzb(5, (zzyi) zzh2);
                }
                i12++;
            }
            zzf = i11;
        }
        zzh[] zzhArr5 = this.zzoy;
        if (zzhArr5 != null && zzhArr5.length > 0) {
            int i13 = zzf;
            int i14 = 0;
            while (true) {
                zzh[] zzhArr6 = this.zzoy;
                if (i14 >= zzhArr6.length) {
                    break;
                }
                zzh zzh3 = zzhArr6[i14];
                if (zzh3 != null) {
                    i13 += zzya.zzb(6, (zzyi) zzh3);
                }
                i14++;
            }
            zzf = i13;
        }
        zzm[] zzmArr = this.zzoz;
        if (zzmArr != null && zzmArr.length > 0) {
            int i15 = zzf;
            int i16 = 0;
            while (true) {
                zzm[] zzmArr2 = this.zzoz;
                if (i16 >= zzmArr2.length) {
                    break;
                }
                zzm zzm = zzmArr2[i16];
                if (zzm != null) {
                    i15 += zzya.zzb(7, (zzyi) zzm);
                }
                i16++;
            }
            zzf = i15;
        }
        String str2 = this.zzpa;
        if (str2 != null && !str2.equals("")) {
            zzf += zzya.zzc(9, this.zzpa);
        }
        String str3 = this.zzpb;
        if (str3 != null && !str3.equals("")) {
            zzf += zzya.zzc(10, this.zzpb);
        }
        String str4 = this.zzpc;
        if (str4 != null && !str4.equals("0")) {
            zzf += zzya.zzc(12, this.zzpc);
        }
        String str5 = this.version;
        if (str5 != null && !str5.equals("")) {
            zzf += zzya.zzc(13, this.version);
        }
        zza zza = this.zzpd;
        if (zza != null) {
            zzf += zztv.zzc(14, (zzvv) zza);
        }
        if (Float.floatToIntBits(this.zzpe) != Float.floatToIntBits(BitmapDescriptorFactory.HUE_RED)) {
            zzf += zzya.zzbd(15) + 4;
        }
        String[] strArr3 = this.zzpg;
        if (strArr3 != null && strArr3.length > 0) {
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            while (true) {
                String[] strArr4 = this.zzpg;
                if (i17 >= strArr4.length) {
                    break;
                }
                String str6 = strArr4[i17];
                if (str6 != null) {
                    i19++;
                    i18 += zzya.zzgc(str6);
                }
                i17++;
            }
            zzf = zzf + i18 + (i19 * 2);
        }
        int i20 = this.zzph;
        if (i20 != 0) {
            zzf += zzya.zzh(17, i20);
        }
        if (this.zzpf) {
            zzf += zzya.zzbd(18) + 1;
        }
        String[] strArr5 = this.zzos;
        if (strArr5 == null || strArr5.length <= 0) {
            return zzf;
        }
        int i21 = 0;
        int i22 = 0;
        while (true) {
            String[] strArr6 = this.zzos;
            if (i >= strArr6.length) {
                return zzf + i21 + (i22 * 2);
            }
            String str7 = strArr6[i];
            if (str7 != null) {
                i22++;
                i21 += zzya.zzgc(str7);
            }
            i++;
        }
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            switch (zzuj) {
                case 0:
                    return this;
                case 10:
                    int zzb = zzyl.zzb(zzxz, 10);
                    String[] strArr = this.zzot;
                    int length = strArr == null ? 0 : strArr.length;
                    String[] strArr2 = new String[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzot, 0, strArr2, 0, length);
                    }
                    while (length < strArr2.length - 1) {
                        strArr2[length] = zzxz.readString();
                        zzxz.zzuj();
                        length++;
                    }
                    strArr2[length] = zzxz.readString();
                    this.zzot = strArr2;
                    break;
                case 18:
                    int zzb2 = zzyl.zzb(zzxz, 18);
                    zzp[] zzpArr = this.zzou;
                    int length2 = zzpArr == null ? 0 : zzpArr.length;
                    zzp[] zzpArr2 = new zzp[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzou, 0, zzpArr2, 0, length2);
                    }
                    while (length2 < zzpArr2.length - 1) {
                        zzpArr2[length2] = new zzp();
                        zzxz.zza((zzyi) zzpArr2[length2]);
                        zzxz.zzuj();
                        length2++;
                    }
                    zzpArr2[length2] = new zzp();
                    zzxz.zza((zzyi) zzpArr2[length2]);
                    this.zzou = zzpArr2;
                    break;
                case 26:
                    int zzb3 = zzyl.zzb(zzxz, 26);
                    zzk[] zzkArr = this.zzov;
                    int length3 = zzkArr == null ? 0 : zzkArr.length;
                    zzk[] zzkArr2 = new zzk[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzov, 0, zzkArr2, 0, length3);
                    }
                    while (length3 < zzkArr2.length - 1) {
                        zzkArr2[length3] = new zzk();
                        zzxz.zza((zzyi) zzkArr2[length3]);
                        zzxz.zzuj();
                        length3++;
                    }
                    zzkArr2[length3] = new zzk();
                    zzxz.zza((zzyi) zzkArr2[length3]);
                    this.zzov = zzkArr2;
                    break;
                case 34:
                    int zzb4 = zzyl.zzb(zzxz, 34);
                    zzh[] zzhArr = this.zzow;
                    int length4 = zzhArr == null ? 0 : zzhArr.length;
                    zzh[] zzhArr2 = new zzh[(zzb4 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzow, 0, zzhArr2, 0, length4);
                    }
                    while (length4 < zzhArr2.length - 1) {
                        zzhArr2[length4] = new zzh();
                        zzxz.zza((zzyi) zzhArr2[length4]);
                        zzxz.zzuj();
                        length4++;
                    }
                    zzhArr2[length4] = new zzh();
                    zzxz.zza((zzyi) zzhArr2[length4]);
                    this.zzow = zzhArr2;
                    break;
                case 42:
                    int zzb5 = zzyl.zzb(zzxz, 42);
                    zzh[] zzhArr3 = this.zzox;
                    int length5 = zzhArr3 == null ? 0 : zzhArr3.length;
                    zzh[] zzhArr4 = new zzh[(zzb5 + length5)];
                    if (length5 != 0) {
                        System.arraycopy(this.zzox, 0, zzhArr4, 0, length5);
                    }
                    while (length5 < zzhArr4.length - 1) {
                        zzhArr4[length5] = new zzh();
                        zzxz.zza((zzyi) zzhArr4[length5]);
                        zzxz.zzuj();
                        length5++;
                    }
                    zzhArr4[length5] = new zzh();
                    zzxz.zza((zzyi) zzhArr4[length5]);
                    this.zzox = zzhArr4;
                    break;
                case 50:
                    int zzb6 = zzyl.zzb(zzxz, 50);
                    zzh[] zzhArr5 = this.zzoy;
                    int length6 = zzhArr5 == null ? 0 : zzhArr5.length;
                    zzh[] zzhArr6 = new zzh[(zzb6 + length6)];
                    if (length6 != 0) {
                        System.arraycopy(this.zzoy, 0, zzhArr6, 0, length6);
                    }
                    while (length6 < zzhArr6.length - 1) {
                        zzhArr6[length6] = new zzh();
                        zzxz.zza((zzyi) zzhArr6[length6]);
                        zzxz.zzuj();
                        length6++;
                    }
                    zzhArr6[length6] = new zzh();
                    zzxz.zza((zzyi) zzhArr6[length6]);
                    this.zzoy = zzhArr6;
                    break;
                case 58:
                    int zzb7 = zzyl.zzb(zzxz, 58);
                    zzm[] zzmArr = this.zzoz;
                    int length7 = zzmArr == null ? 0 : zzmArr.length;
                    zzm[] zzmArr2 = new zzm[(zzb7 + length7)];
                    if (length7 != 0) {
                        System.arraycopy(this.zzoz, 0, zzmArr2, 0, length7);
                    }
                    while (length7 < zzmArr2.length - 1) {
                        zzmArr2[length7] = new zzm();
                        zzxz.zza((zzyi) zzmArr2[length7]);
                        zzxz.zzuj();
                        length7++;
                    }
                    zzmArr2[length7] = new zzm();
                    zzxz.zza((zzyi) zzmArr2[length7]);
                    this.zzoz = zzmArr2;
                    break;
                case 74:
                    this.zzpa = zzxz.readString();
                    break;
                case 82:
                    this.zzpb = zzxz.readString();
                    break;
                case 98:
                    this.zzpc = zzxz.readString();
                    break;
                case 106:
                    this.version = zzxz.readString();
                    break;
                case 114:
                    zza zza = (zza) zzxz.zza(zza.zza());
                    zza zza2 = this.zzpd;
                    if (zza2 != null) {
                        zza = (zza) ((zzuo) ((C0037zza) ((C0037zza) zza2.zzwf()).zza(zza)).zzwo());
                    }
                    this.zzpd = zza;
                    break;
                case 125:
                    this.zzpe = Float.intBitsToFloat(zzxz.zzvd());
                    break;
                case 130:
                    int zzb8 = zzyl.zzb(zzxz, 130);
                    String[] strArr3 = this.zzpg;
                    int length8 = strArr3 == null ? 0 : strArr3.length;
                    String[] strArr4 = new String[(zzb8 + length8)];
                    if (length8 != 0) {
                        System.arraycopy(this.zzpg, 0, strArr4, 0, length8);
                    }
                    while (length8 < strArr4.length - 1) {
                        strArr4[length8] = zzxz.readString();
                        zzxz.zzuj();
                        length8++;
                    }
                    strArr4[length8] = zzxz.readString();
                    this.zzpg = strArr4;
                    break;
                case 136:
                    this.zzph = zzxz.zzvb();
                    break;
                case 144:
                    this.zzpf = zzxz.zzup();
                    break;
                case PacketTypes.UsernameValidationResponse /*154*/:
                    int zzb9 = zzyl.zzb(zzxz, PacketTypes.UsernameValidationResponse);
                    String[] strArr5 = this.zzos;
                    int length9 = strArr5 == null ? 0 : strArr5.length;
                    String[] strArr6 = new String[(zzb9 + length9)];
                    if (length9 != 0) {
                        System.arraycopy(this.zzos, 0, strArr6, 0, length9);
                    }
                    while (length9 < strArr6.length - 1) {
                        strArr6[length9] = zzxz.readString();
                        zzxz.zzuj();
                        length9++;
                    }
                    strArr6[length9] = zzxz.readString();
                    this.zzos = strArr6;
                    break;
                default:
                    if (super.zza(zzxz, zzuj)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }
}

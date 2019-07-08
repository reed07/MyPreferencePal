package com.google.android.gms.internal.ads;

import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

final class zzms implements zzlm, zzlx<zzmj<zzmq>> {
    final int id;
    private final int zzawn;
    private final zzot zzawq;
    private zzln zzawz;
    private final zzkt zzbah;
    private final zzmr zzbax;
    private final long zzbay;
    private final zzpk zzbaz;
    private final zzmt[] zzbba;
    private zzmj<zzmq>[] zzbbb = new zzmj[0];
    private zzla zzbbc = new zzla(this.zzbbb);
    private zznj zzbbd;
    private List<zzni> zzbbe;
    private final zzma zzxk;
    private int zzyr;

    public zzms(int i, zznj zznj, int i2, zzmr zzmr, int i3, zzkt zzkt, long j, zzpk zzpk, zzot zzot) {
        int i4;
        int i5;
        boolean z;
        boolean z2;
        zznm zznm;
        this.id = i;
        this.zzbbd = zznj;
        this.zzyr = i2;
        this.zzbax = zzmr;
        this.zzawn = i3;
        this.zzbah = zzkt;
        this.zzbay = j;
        this.zzbaz = zzpk;
        this.zzawq = zzot;
        this.zzbbe = zznj.zzba(i2).zzbbe;
        List<zzni> list = this.zzbbe;
        int size = list.size();
        SparseIntArray sparseIntArray = new SparseIntArray(size);
        for (int i6 = 0; i6 < size; i6++) {
            sparseIntArray.put(((zzni) list.get(i6)).id, i6);
        }
        int[][] iArr = new int[size][];
        boolean[] zArr = new boolean[size];
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            if (!zArr[i8]) {
                zArr[i8] = true;
                List<zznm> list2 = ((zzni) list.get(i8)).zzbcp;
                int i9 = 0;
                while (true) {
                    if (i9 >= list2.size()) {
                        zznm = null;
                        break;
                    }
                    zznm zznm2 = (zznm) list2.get(i9);
                    if ("urn:mpeg:dash:adaptation-set-switching:2016".equals(zznm2.zzbdi)) {
                        zznm = zznm2;
                        break;
                    }
                    i9++;
                }
                if (zznm == null) {
                    int i10 = i7 + 1;
                    iArr[i7] = new int[]{i8};
                    i7 = i10;
                } else {
                    String[] split = zznm.value.split(",");
                    int[] iArr2 = new int[(split.length + 1)];
                    iArr2[0] = i8;
                    int i11 = 0;
                    while (i11 < split.length) {
                        int i12 = sparseIntArray.get(Integer.parseInt(split[i11]));
                        zArr[i12] = true;
                        i11++;
                        iArr2[i11] = i12;
                    }
                    int i13 = i7 + 1;
                    iArr[i7] = iArr2;
                    i7 = i13;
                }
            }
        }
        if (i7 < size) {
            iArr = (int[][]) Arrays.copyOf(iArr, i7);
        }
        int length = iArr.length;
        boolean[] zArr2 = new boolean[length];
        boolean[] zArr3 = new boolean[length];
        int i14 = length;
        for (int i15 = 0; i15 < length; i15++) {
            int[] iArr3 = iArr[i15];
            int length2 = iArr3.length;
            int i16 = 0;
            while (true) {
                if (i16 >= length2) {
                    z = false;
                    break;
                }
                List<zznp> list3 = ((zzni) list.get(iArr3[i16])).zzbcn;
                for (int i17 = 0; i17 < list3.size(); i17++) {
                    if (!((zznp) list3.get(i17)).zzbdo.isEmpty()) {
                        z = true;
                        break;
                    }
                }
                i16++;
            }
            if (z) {
                zArr2[i15] = true;
                i14++;
            }
            int[] iArr4 = iArr[i15];
            int length3 = iArr4.length;
            int i18 = 0;
            while (true) {
                if (i18 >= length3) {
                    z2 = false;
                    break;
                }
                List<zznm> list4 = ((zzni) list.get(iArr4[i18])).zzbco;
                for (int i19 = 0; i19 < list4.size(); i19++) {
                    if ("urn:scte:dash:cc:cea-608:2015".equals(((zznm) list4.get(i19)).zzbdi)) {
                        z2 = true;
                        break;
                    }
                }
                i18++;
            }
            if (z2) {
                zArr3[i15] = true;
                i14++;
            }
        }
        zzlz[] zzlzArr = new zzlz[i14];
        zzmt[] zzmtArr = new zzmt[i14];
        int i20 = 0;
        int i21 = 0;
        while (i20 < length) {
            int[] iArr5 = iArr[i20];
            ArrayList arrayList = new ArrayList();
            for (int i22 : iArr5) {
                arrayList.addAll(((zzni) list.get(i22)).zzbcn);
            }
            zzfs[] zzfsArr = new zzfs[arrayList.size()];
            for (int i23 = 0; i23 < zzfsArr.length; i23++) {
                zzfsArr[i23] = ((zznp) arrayList.get(i23)).zzaad;
            }
            zzni zzni = (zzni) list.get(iArr5[0]);
            boolean z3 = zArr2[i20];
            boolean z4 = zArr3[i20];
            zzlzArr[i21] = new zzlz(zzfsArr);
            int i24 = i21 + 1;
            zzni zzni2 = zzni;
            int[] iArr6 = iArr5;
            zzmt zzmt = new zzmt(zzni.type, iArr5, i21, true, z3, z4);
            zzmtArr[i21] = zzmt;
            if (z3) {
                int i25 = zzni2.id;
                StringBuilder sb = new StringBuilder(16);
                sb.append(i25);
                sb.append(":emsg");
                zzlzArr[i24] = new zzlz(zzfs.zza(sb.toString(), MimeTypes.APPLICATION_EMSG, null, -1, null));
                i4 = i24 + 1;
                zzmt zzmt2 = new zzmt(4, iArr6, i21, false, false, false);
                zzmtArr[i24] = zzmt2;
            } else {
                i4 = i24;
            }
            if (z4) {
                int i26 = zzni2.id;
                StringBuilder sb2 = new StringBuilder(18);
                sb2.append(i26);
                sb2.append(":cea608");
                zzlzArr[i4] = new zzlz(zzfs.zza(sb2.toString(), MimeTypes.APPLICATION_CEA608, (String) null, -1, 0, (String) null, (zzhp) null));
                i5 = i4 + 1;
                zzmt zzmt3 = new zzmt(3, iArr6, i21, false, false, false);
                zzmtArr[i4] = zzmt3;
            } else {
                i5 = i4;
            }
            i20++;
            i21 = i5;
        }
        Pair create = Pair.create(new zzma(zzlzArr), zzmtArr);
        this.zzxk = (zzma) create.first;
        this.zzbba = (zzmt[]) create.second;
    }

    public final long zzey() {
        return -9223372036854775807L;
    }

    public final void zza(zznj zznj, int i) {
        this.zzbbd = zznj;
        this.zzyr = i;
        this.zzbbe = zznj.zzba(i).zzbbe;
        zzmj<zzmq>[] zzmjArr = this.zzbbb;
        if (zzmjArr != null) {
            for (zzmj<zzmq> zzfy : zzmjArr) {
                ((zzmq) zzfy.zzfy()).zza(zznj, i);
            }
            this.zzawz.zza(this);
        }
    }

    public final void release() {
        for (zzmj<zzmq> release : this.zzbbb) {
            release.release();
        }
    }

    public final void zza(zzln zzln, long j) {
        this.zzawz = zzln;
        zzln.zza(this);
    }

    public final void zzew() throws IOException {
        this.zzbaz.zzev();
    }

    public final zzma zzex() {
        return this.zzxk;
    }

    public final long zza(zzom[] zzomArr, boolean[] zArr, zzlv[] zzlvArr, boolean[] zArr2, long j) {
        zzlv zzlv;
        int i;
        int i2;
        zzom[] zzomArr2 = zzomArr;
        long j2 = j;
        HashMap hashMap = new HashMap();
        char c = 0;
        int i3 = 0;
        while (i3 < zzomArr2.length) {
            if (zzlvArr[i3] instanceof zzmj) {
                zzmj zzmj = (zzmj) zzlvArr[i3];
                if (zzomArr2[i3] == null || !zArr[i3]) {
                    zzmj.release();
                    zzlvArr[i3] = null;
                } else {
                    hashMap.put(Integer.valueOf(this.zzxk.zza(zzomArr2[i3].zzgk())), zzmj);
                }
            }
            if (zzlvArr[i3] != null || zzomArr2[i3] == null) {
                i = i3;
            } else {
                int zza = this.zzxk.zza(zzomArr2[i3].zzgk());
                zzmt zzmt = this.zzbba[zza];
                if (zzmt.zzbbg) {
                    zzom zzom = zzomArr2[i3];
                    int[] iArr = new int[2];
                    boolean z = zzmt.zzbbi;
                    if (z) {
                        iArr[c] = 4;
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    boolean z2 = zzmt.zzbbj;
                    if (z2) {
                        int i4 = i2 + 1;
                        iArr[i2] = 3;
                        i2 = i4;
                    }
                    if (i2 < 2) {
                        iArr = Arrays.copyOf(iArr, i2);
                    }
                    i = i3;
                    zzmj zzmj2 = new zzmj(zzmt.zzwg, iArr, this.zzbax.zza(this.zzbaz, this.zzbbd, this.zzyr, zzmt.zzbbf, zzom, zzmt.zzwg, this.zzbay, z, z2), this, this.zzawq, j, this.zzawn, this.zzbah);
                    hashMap.put(Integer.valueOf(zza), zzmj2);
                    zzlvArr[i] = zzmj2;
                    zArr2[i] = true;
                } else {
                    i = i3;
                }
            }
            i3 = i + 1;
            long j3 = j;
            c = 0;
        }
        for (int i5 = 0; i5 < zzomArr2.length; i5++) {
            if (((zzlvArr[i5] instanceof zzmk) || (zzlvArr[i5] instanceof zzlb)) && (zzomArr2[i5] == null || !zArr[i5])) {
                zza(zzlvArr[i5]);
                zzlvArr[i5] = null;
            }
            if (zzomArr2[i5] != null) {
                zzmt zzmt2 = this.zzbba[this.zzxk.zza(zzomArr2[i5].zzgk())];
                if (!zzmt2.zzbbg) {
                    zzmj<T> zzmj3 = (zzmj) hashMap.get(Integer.valueOf(zzmt2.zzbbh));
                    zzlv zzlv2 = zzlvArr[i5];
                    boolean z3 = zzmj3 == null ? zzlv2 instanceof zzlb : (zzlv2 instanceof zzmk) && ((zzmk) zzlv2).zzbap == zzmj3;
                    if (!z3) {
                        zza(zzlv2);
                        if (zzmj3 == null) {
                            zzlv = new zzlb();
                            long j4 = j;
                        } else {
                            zzlv = zzmj3.zza(j, zzmt2.zzwg);
                        }
                        zzlvArr[i5] = zzlv;
                        zArr2[i5] = true;
                    } else {
                        long j5 = j;
                    }
                } else {
                    long j6 = j;
                }
            } else {
                long j7 = j;
            }
        }
        long j8 = j;
        this.zzbbb = new zzmj[hashMap.size()];
        hashMap.values().toArray(this.zzbbb);
        this.zzbbc = new zzla(this.zzbbb);
        return j8;
    }

    public final void zzaa(long j) {
        for (zzmj<zzmq> zzaf : this.zzbbb) {
            zzaf.zzaf(j);
        }
    }

    public final boolean zzy(long j) {
        return this.zzbbc.zzy(j);
    }

    public final long zzeu() {
        return this.zzbbc.zzeu();
    }

    public final long zzez() {
        long j = Long.MAX_VALUE;
        for (zzmj<zzmq> zzez : this.zzbbb) {
            long zzez2 = zzez.zzez();
            if (zzez2 != Long.MIN_VALUE) {
                j = Math.min(j, zzez2);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public final long zzab(long j) {
        for (zzmj<zzmq> zzag : this.zzbbb) {
            zzag.zzag(j);
        }
        return j;
    }

    private static void zza(zzlv zzlv) {
        if (zzlv instanceof zzmk) {
            ((zzmk) zzlv).release();
        }
    }

    public final /* synthetic */ void zza(zzlw zzlw) {
        this.zzawz.zza(this);
    }
}

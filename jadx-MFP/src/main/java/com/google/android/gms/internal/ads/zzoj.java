package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.Arrays;
import java.util.Map;

public abstract class zzoj extends zzop {
    private int zzaak = 0;
    private final SparseArray<Map<zzma, zzol>> zzbfa = new SparseArray<>();
    private final SparseBooleanArray zzbfb = new SparseBooleanArray();
    private zzok zzbfc;

    /* access modifiers changed from: protected */
    public abstract zzom[] zza(zzga[] zzgaArr, zzma[] zzmaArr, int[][][] iArr) throws zzff;

    public final void zzf(int i, boolean z) {
        if (this.zzbfb.get(i) != z) {
            this.zzbfb.put(i, z);
            invalidate();
        }
    }

    public final zzor zza(zzga[] zzgaArr, zzma zzma) throws zzff {
        int[] iArr;
        zzga[] zzgaArr2 = zzgaArr;
        zzma zzma2 = zzma;
        int[] iArr2 = new int[(zzgaArr2.length + 1)];
        zzlz[][] zzlzArr = new zzlz[(zzgaArr2.length + 1)][];
        int[][][] iArr3 = new int[(zzgaArr2.length + 1)][][];
        for (int i = 0; i < zzlzArr.length; i++) {
            zzlzArr[i] = new zzlz[zzma2.length];
            iArr3[i] = new int[zzma2.length][];
        }
        int[] iArr4 = new int[zzgaArr2.length];
        for (int i2 = 0; i2 < iArr4.length; i2++) {
            iArr4[i2] = zzgaArr2[i2].zzbl();
        }
        for (int i3 = 0; i3 < zzma2.length; i3++) {
            zzlz zzau = zzma2.zzau(i3);
            int length = zzgaArr2.length;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                if (i4 >= zzgaArr2.length) {
                    i4 = length;
                    break;
                }
                zzga zzga = zzgaArr2[i4];
                int i6 = length;
                int i7 = i5;
                for (int i8 = 0; i8 < zzau.length; i8++) {
                    int zzb = zzga.zzb(zzau.zzat(i8)) & 3;
                    if (zzb > i7) {
                        if (zzb == 3) {
                            break;
                        }
                        i6 = i4;
                        i7 = zzb;
                    }
                }
                i4++;
                i5 = i7;
                length = i6;
            }
            if (i4 == zzgaArr2.length) {
                iArr = new int[zzau.length];
            } else {
                zzga zzga2 = zzgaArr2[i4];
                int[] iArr5 = new int[zzau.length];
                for (int i9 = 0; i9 < zzau.length; i9++) {
                    iArr5[i9] = zzga2.zzb(zzau.zzat(i9));
                }
                iArr = iArr5;
            }
            int i10 = iArr2[i4];
            zzlzArr[i4][i10] = zzau;
            iArr3[i4][i10] = iArr;
            iArr2[i4] = iArr2[i4] + 1;
        }
        zzma[] zzmaArr = new zzma[zzgaArr2.length];
        int[] iArr6 = new int[zzgaArr2.length];
        for (int i11 = 0; i11 < zzgaArr2.length; i11++) {
            int i12 = iArr2[i11];
            zzmaArr[i11] = new zzma((zzlz[]) Arrays.copyOf(zzlzArr[i11], i12));
            iArr3[i11] = (int[][]) Arrays.copyOf(iArr3[i11], i12);
            iArr6[i11] = zzgaArr2[i11].getTrackType();
        }
        zzma zzma3 = new zzma((zzlz[]) Arrays.copyOf(zzlzArr[zzgaArr2.length], iArr2[zzgaArr2.length]));
        zzom[] zza = zza(zzgaArr2, zzmaArr, iArr3);
        int i13 = 0;
        while (true) {
            zzol zzol = null;
            if (i13 < zzgaArr2.length) {
                if (this.zzbfb.get(i13)) {
                    zza[i13] = null;
                } else {
                    zzma zzma4 = zzmaArr[i13];
                    Map map = (Map) this.zzbfa.get(i13);
                    if (map != null) {
                        zzol = (zzol) map.get(zzma4);
                    }
                    if (zzol != null) {
                        throw new NoSuchMethodError();
                    }
                }
                i13++;
            } else {
                zzok zzok = new zzok(iArr6, zzmaArr, iArr4, iArr3, zzma3);
                zzgb[] zzgbArr = new zzgb[zzgaArr2.length];
                for (int i14 = 0; i14 < zzgaArr2.length; i14++) {
                    zzgbArr[i14] = zza[i14] != null ? zzgb.zzaaj : null;
                }
                return new zzor(zzma2, new zzoo(zza), zzok, zzgbArr);
            }
        }
    }

    public final void zzd(Object obj) {
        this.zzbfc = (zzok) obj;
    }
}

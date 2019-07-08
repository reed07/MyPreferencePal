package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzjr {
    private static final int[] zzask = {zzqe.zzam("isom"), zzqe.zzam("iso2"), zzqe.zzam("iso3"), zzqe.zzam("iso4"), zzqe.zzam("iso5"), zzqe.zzam("iso6"), zzqe.zzam("avc1"), zzqe.zzam("hvc1"), zzqe.zzam("hev1"), zzqe.zzam("mp41"), zzqe.zzam("mp42"), zzqe.zzam("3g2a"), zzqe.zzam("3g2b"), zzqe.zzam("3gr6"), zzqe.zzam("3gs6"), zzqe.zzam("3ge6"), zzqe.zzam("3gg6"), zzqe.zzam("M4V "), zzqe.zzam("M4A "), zzqe.zzam("f4v "), zzqe.zzam("kddi"), zzqe.zzam("M4VP"), zzqe.zzam("qt  "), zzqe.zzam("MSNV")};

    public static boolean zzd(zzia zzia) throws IOException, InterruptedException {
        return zza(zzia, true);
    }

    public static boolean zze(zzia zzia) throws IOException, InterruptedException {
        return zza(zzia, false);
    }

    private static boolean zza(zzia zzia, boolean z) throws IOException, InterruptedException {
        boolean z2;
        boolean z3;
        zzia zzia2 = zzia;
        long length = zzia.getLength();
        if (length == -1 || length > 4096) {
            length = 4096;
        }
        int i = (int) length;
        zzpx zzpx = new zzpx(64);
        int i2 = 0;
        boolean z4 = false;
        while (true) {
            if (i2 >= i) {
                break;
            }
            zzpx.reset(8);
            zzia2.zza(zzpx.data, 0, 8);
            long zzhd = zzpx.zzhd();
            int readInt = zzpx.readInt();
            int i3 = 16;
            if (zzhd == 1) {
                zzia2.zza(zzpx.data, 8, 8);
                zzpx.zzbk(16);
                zzhd = zzpx.zzhh();
            } else {
                i3 = 8;
            }
            long j = (long) i3;
            if (zzhd >= j) {
                i2 += i3;
                if (readInt != zziv.zzamb) {
                    if (readInt != zziv.zzamk && readInt != zziv.zzamm) {
                        if ((((long) i2) + zzhd) - j >= ((long) i)) {
                            break;
                        }
                        int i4 = (int) (zzhd - j);
                        i2 += i4;
                        if (readInt == zziv.zzala) {
                            if (i4 < 8) {
                                return false;
                            }
                            zzpx.reset(i4);
                            zzia2.zza(zzpx.data, 0, i4);
                            int i5 = i4 / 4;
                            int i6 = 0;
                            while (true) {
                                if (i6 >= i5) {
                                    break;
                                }
                                if (i6 == 1) {
                                    zzpx.zzbl(4);
                                } else {
                                    int readInt2 = zzpx.readInt();
                                    if ((readInt2 >>> 8) != zzqe.zzam("3gp")) {
                                        int[] iArr = zzask;
                                        int length2 = iArr.length;
                                        int i7 = 0;
                                        while (true) {
                                            if (i7 >= length2) {
                                                z3 = false;
                                                break;
                                            } else if (iArr[i7] == readInt2) {
                                                z3 = true;
                                                break;
                                            } else {
                                                i7++;
                                            }
                                        }
                                    } else {
                                        z3 = true;
                                    }
                                    if (z3) {
                                        z4 = true;
                                        break;
                                    }
                                }
                                i6++;
                            }
                            if (!z4) {
                                return false;
                            }
                        } else if (i4 != 0) {
                            zzia2.zzx(i4);
                        }
                    } else {
                        z2 = true;
                    }
                }
            } else {
                return false;
            }
        }
        z2 = false;
        if (!z4 || z != z2) {
            return false;
        }
        return true;
    }
}

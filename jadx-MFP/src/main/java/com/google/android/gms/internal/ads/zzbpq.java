package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.io.IOException;

final class zzbpq {
    static int zza(byte[] bArr, int i, zzbpr zzbpr) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza((int) b, bArr, i2, zzbpr);
        }
        zzbpr.zzfld = b;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzbpr zzbpr) {
        int i3 = i & Statements.GetOwnedFoodIdsDateDescending;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzbpr.zzfld = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzbpr.zzfld = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << Ascii.SO);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzbpr.zzfld = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzbpr.zzfld = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzbpr.zzfld = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzb(byte[] bArr, int i, zzbpr zzbpr) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzbpr.zzfle = j;
            return i2;
        }
        long j2 = j & 127;
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j3 = j2 | (((long) (b & Byte.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j3 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        zzbpr.zzfle = j3;
        return i3;
    }

    static int zzg(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static long zzh(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double zzi(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzh(bArr, i));
    }

    static float zzj(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzg(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzbpr zzbpr) throws zzbrl {
        int zza = zza(bArr, i, zzbpr);
        int i2 = zzbpr.zzfld;
        if (i2 < 0) {
            throw zzbrl.zzand();
        } else if (i2 == 0) {
            zzbpr.zzflf = "";
            return zza;
        } else {
            zzbpr.zzflf = new String(bArr, zza, i2, zzbrf.UTF_8);
            return zza + i2;
        }
    }

    static int zzd(byte[] bArr, int i, zzbpr zzbpr) throws zzbrl {
        int zza = zza(bArr, i, zzbpr);
        int i2 = zzbpr.zzfld;
        if (i2 < 0) {
            throw zzbrl.zzand();
        } else if (i2 == 0) {
            zzbpr.zzflf = "";
            return zza;
        } else {
            zzbpr.zzflf = zzbuc.zzo(bArr, zza, i2);
            return zza + i2;
        }
    }

    static int zze(byte[] bArr, int i, zzbpr zzbpr) throws zzbrl {
        int zza = zza(bArr, i, zzbpr);
        int i2 = zzbpr.zzfld;
        if (i2 < 0) {
            throw zzbrl.zzand();
        } else if (i2 > bArr.length - zza) {
            throw zzbrl.zzanc();
        } else if (i2 == 0) {
            zzbpr.zzflf = zzbpu.zzfli;
            return zza;
        } else {
            zzbpr.zzflf = zzbpu.zzi(bArr, zza, i2);
            return zza + i2;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzbrk<?> zzbrk, zzbpr zzbpr) {
        zzbre zzbre = (zzbre) zzbrk;
        int zza = zza(bArr, i2, zzbpr);
        zzbre.zzfo(zzbpr.zzfld);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzbpr);
            if (i != zzbpr.zzfld) {
                break;
            }
            zza = zza(bArr, zza2, zzbpr);
            zzbre.zzfo(zzbpr.zzfld);
        }
        return zza;
    }

    static int zza(byte[] bArr, int i, zzbrk<?> zzbrk, zzbpr zzbpr) throws IOException {
        zzbre zzbre = (zzbre) zzbrk;
        int zza = zza(bArr, i, zzbpr);
        int i2 = zzbpr.zzfld + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzbpr);
            zzbre.zzfo(zzbpr.zzfld);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzbrl.zzanc();
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzbtv zzbtv, zzbpr zzbpr) throws zzbrl {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 5) {
                switch (i4) {
                    case 0:
                        int zzb = zzb(bArr, i2, zzbpr);
                        zzbtv.zzc(i, Long.valueOf(zzbpr.zzfle));
                        return zzb;
                    case 1:
                        zzbtv.zzc(i, Long.valueOf(zzh(bArr, i2)));
                        return i2 + 8;
                    case 2:
                        int zza = zza(bArr, i2, zzbpr);
                        int i5 = zzbpr.zzfld;
                        if (i5 < 0) {
                            throw zzbrl.zzand();
                        } else if (i5 <= bArr.length - zza) {
                            if (i5 == 0) {
                                zzbtv.zzc(i, zzbpu.zzfli);
                            } else {
                                zzbtv.zzc(i, zzbpu.zzi(bArr, zza, i5));
                            }
                            return zza + i5;
                        } else {
                            throw zzbrl.zzanc();
                        }
                    case 3:
                        zzbtv zzapa = zzbtv.zzapa();
                        int i6 = (i & -8) | 4;
                        int i7 = 0;
                        while (true) {
                            if (i2 < i3) {
                                int zza2 = zza(bArr, i2, zzbpr);
                                int i8 = zzbpr.zzfld;
                                if (i8 != i6) {
                                    i7 = i8;
                                    i2 = zza(i8, bArr, zza2, i3, zzapa, zzbpr);
                                } else {
                                    i7 = i8;
                                    i2 = zza2;
                                }
                            }
                        }
                        if (i2 > i3 || i7 != i6) {
                            throw zzbrl.zzanj();
                        }
                        zzbtv.zzc(i, zzapa);
                        return i2;
                    default:
                        throw zzbrl.zzanf();
                }
            } else {
                zzbtv.zzc(i, Integer.valueOf(zzg(bArr, i2)));
                return i2 + 4;
            }
        } else {
            throw zzbrl.zzanf();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzbpr zzbpr) throws zzbrl {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 5) {
                return i2 + 4;
            }
            switch (i4) {
                case 0:
                    return zzb(bArr, i2, zzbpr);
                case 1:
                    return i2 + 8;
                case 2:
                    return zza(bArr, i2, zzbpr) + zzbpr.zzfld;
                case 3:
                    int i5 = (i & -8) | 4;
                    int i6 = 0;
                    while (i2 < i3) {
                        i2 = zza(bArr, i2, zzbpr);
                        i6 = zzbpr.zzfld;
                        if (i6 != i5) {
                            i2 = zza(i6, bArr, i2, i3, zzbpr);
                        } else if (i2 > i3 && i6 == i5) {
                            return i2;
                        } else {
                            throw zzbrl.zzanj();
                        }
                    }
                    if (i2 > i3) {
                    }
                    throw zzbrl.zzanj();
                default:
                    throw zzbrl.zzanf();
            }
        } else {
            throw zzbrl.zzanf();
        }
    }
}

package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

final class zzts extends zztq {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzbuf;
    private int zzbug;
    private int zzbuh;
    private int zzbui;
    private int zzbuj;

    private zzts(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzbuj = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzbuh = this.pos;
        this.zzbuf = z;
    }

    public final int zzuj() throws IOException {
        if (zzuz()) {
            this.zzbui = 0;
            return 0;
        }
        this.zzbui = zzvb();
        int i = this.zzbui;
        if ((i >>> 3) != 0) {
            return i;
        }
        throw new zzuv("Protocol message contained an invalid tag (zero).");
    }

    public final void zzap(int i) throws zzuv {
        if (this.zzbui != i) {
            throw zzuv.zzwt();
        }
    }

    public final boolean zzaq(int i) throws IOException {
        int zzuj;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.limit - this.pos >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        this.pos = i3 + 1;
                        if (bArr[i3] < 0) {
                            i2++;
                        }
                    }
                    throw zzuv.zzws();
                }
                while (i2 < 10) {
                    if (zzvg() < 0) {
                        i2++;
                    }
                }
                throw zzuv.zzws();
                return true;
            case 1:
                zzau(8);
                return true;
            case 2:
                zzau(zzvb());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzau(4);
                return true;
            default:
                throw zzuv.zzwu();
        }
        do {
            zzuj = zzuj();
            if (zzuj != 0) {
            }
            zzap(((i >>> 3) << 3) | 4);
            return true;
        } while (zzaq(zzuj));
        zzap(((i >>> 3) << 3) | 4);
        return true;
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzve());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzvd());
    }

    public final long zzuk() throws IOException {
        return zzvc();
    }

    public final long zzul() throws IOException {
        return zzvc();
    }

    public final int zzum() throws IOException {
        return zzvb();
    }

    public final long zzun() throws IOException {
        return zzve();
    }

    public final int zzuo() throws IOException {
        return zzvd();
    }

    public final boolean zzup() throws IOException {
        return zzvc() != 0;
    }

    public final String readString() throws IOException {
        int zzvb = zzvb();
        if (zzvb > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzvb <= i - i2) {
                String str = new String(this.buffer, i2, zzvb, zzuq.UTF_8);
                this.pos += zzvb;
                return str;
            }
        }
        if (zzvb == 0) {
            return "";
        }
        if (zzvb < 0) {
            throw zzuv.zzwr();
        }
        throw zzuv.zzwq();
    }

    public final String zzuq() throws IOException {
        int zzvb = zzvb();
        if (zzvb > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzvb <= i - i2) {
                String zzh = zzxl.zzh(this.buffer, i2, zzvb);
                this.pos += zzvb;
                return zzh;
            }
        }
        if (zzvb == 0) {
            return "";
        }
        if (zzvb <= 0) {
            throw zzuv.zzwr();
        }
        throw zzuv.zzwq();
    }

    public final <T extends zzvv> T zza(zzwf<T> zzwf, zzub zzub) throws IOException {
        int zzvb = zzvb();
        if (this.zzbua < this.zzbub) {
            int zzas = zzas(zzvb);
            this.zzbua++;
            T t = (zzvv) zzwf.zza(this, zzub);
            zzap(0);
            this.zzbua--;
            zzat(zzas);
            return t;
        }
        throw zzuv.zzwv();
    }

    public final zzte zzur() throws IOException {
        byte[] bArr;
        int zzvb = zzvb();
        if (zzvb > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzvb <= i - i2) {
                zzte zzb = zzte.zzb(this.buffer, i2, zzvb);
                this.pos += zzvb;
                return zzb;
            }
        }
        if (zzvb == 0) {
            return zzte.zzbts;
        }
        if (zzvb > 0) {
            int i3 = this.limit;
            int i4 = this.pos;
            if (zzvb <= i3 - i4) {
                this.pos = zzvb + i4;
                bArr = Arrays.copyOfRange(this.buffer, i4, this.pos);
                return zzte.zzi(bArr);
            }
        }
        if (zzvb > 0) {
            throw zzuv.zzwq();
        } else if (zzvb == 0) {
            bArr = zzuq.zzbzc;
            return zzte.zzi(bArr);
        } else {
            throw zzuv.zzwr();
        }
    }

    public final int zzus() throws IOException {
        return zzvb();
    }

    public final int zzut() throws IOException {
        return zzvb();
    }

    public final int zzuu() throws IOException {
        return zzvd();
    }

    public final long zzuv() throws IOException {
        return zzve();
    }

    public final int zzuw() throws IOException {
        int zzvb = zzvb();
        return (-(zzvb & 1)) ^ (zzvb >>> 1);
    }

    public final long zzux() throws IOException {
        long zzvc = zzvc();
        return (-(zzvc & 1)) ^ (zzvc >>> 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzvb() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L_0x006d
            byte[] r2 = r5.buffer
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0011
            r5.pos = r3
            return r0
        L_0x0011:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x006d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0022
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x006a
        L_0x0022:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x002f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r1 = r3
            goto L_0x006a
        L_0x002f:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x003d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x006a
        L_0x003d:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x0069
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x006a
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x0069
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x006a
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x0069
            int r1 = r3 + 1
            byte r2 = r2[r3]
            if (r2 < 0) goto L_0x006d
            goto L_0x006a
        L_0x0069:
            r1 = r3
        L_0x006a:
            r5.pos = r1
            return r0
        L_0x006d:
            long r0 = r5.zzuy()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzts.zzvb():int");
    }

    private final long zzvc() throws IOException {
        long j;
        int i = this.pos;
        int i2 = this.limit;
        if (i2 != i) {
            byte[] bArr = this.buffer;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.pos = i3;
                return (long) b;
            } else if (i2 - i3 >= 9) {
                int i4 = i3 + 1;
                byte b2 = b ^ (bArr[i3] << 7);
                if (b2 < 0) {
                    j = (long) (b2 ^ Byte.MIN_VALUE);
                } else {
                    int i5 = i4 + 1;
                    byte b3 = b2 ^ (bArr[i4] << Ascii.SO);
                    if (b3 >= 0) {
                        i4 = i5;
                        j = (long) (b3 ^ 16256);
                    } else {
                        i4 = i5 + 1;
                        byte b4 = b3 ^ (bArr[i5] << Ascii.NAK);
                        if (b4 < 0) {
                            j = (long) (b4 ^ -2080896);
                        } else {
                            long j2 = (long) b4;
                            int i6 = i4 + 1;
                            long j3 = j2 ^ (((long) bArr[i4]) << 28);
                            if (j3 >= 0) {
                                j = j3 ^ 266354560;
                                i4 = i6;
                            } else {
                                i4 = i6 + 1;
                                long j4 = j3 ^ (((long) bArr[i6]) << 35);
                                if (j4 < 0) {
                                    j = j4 ^ -34093383808L;
                                } else {
                                    int i7 = i4 + 1;
                                    long j5 = j4 ^ (((long) bArr[i4]) << 42);
                                    if (j5 >= 0) {
                                        j = j5 ^ 4363953127296L;
                                        i4 = i7;
                                    } else {
                                        i4 = i7 + 1;
                                        long j6 = j5 ^ (((long) bArr[i7]) << 49);
                                        if (j6 < 0) {
                                            j = j6 ^ -558586000294016L;
                                        } else {
                                            int i8 = i4 + 1;
                                            long j7 = (j6 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                i4 = i8 + 1;
                                                if (((long) bArr[i8]) >= 0) {
                                                    j = j7;
                                                }
                                            } else {
                                                i4 = i8;
                                                j = j7;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.pos = i4;
                return j;
            }
        }
        return zzuy();
    }

    /* access modifiers changed from: 0000 */
    public final long zzuy() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzvg = zzvg();
            j |= ((long) (zzvg & Byte.MAX_VALUE)) << i;
            if ((zzvg & 128) == 0) {
                return j;
            }
        }
        throw zzuv.zzws();
    }

    private final int zzvd() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 4) {
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
        }
        throw zzuv.zzwq();
    }

    private final long zzve() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 8) {
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzuv.zzwq();
    }

    public final int zzas(int i) throws zzuv {
        if (i >= 0) {
            int zzva = i + zzva();
            int i2 = this.zzbuj;
            if (zzva <= i2) {
                this.zzbuj = zzva;
                zzvf();
                return i2;
            }
            throw zzuv.zzwq();
        }
        throw zzuv.zzwr();
    }

    private final void zzvf() {
        this.limit += this.zzbug;
        int i = this.limit;
        int i2 = i - this.zzbuh;
        int i3 = this.zzbuj;
        if (i2 > i3) {
            this.zzbug = i2 - i3;
            this.limit = i - this.zzbug;
            return;
        }
        this.zzbug = 0;
    }

    public final void zzat(int i) {
        this.zzbuj = i;
        zzvf();
    }

    public final boolean zzuz() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzva() {
        return this.pos - this.zzbuh;
    }

    private final byte zzvg() throws IOException {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzuv.zzwq();
    }

    public final void zzau(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.limit;
            int i3 = this.pos;
            if (i <= i2 - i3) {
                this.pos = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzuv.zzwr();
        }
        throw zzuv.zzwq();
    }
}

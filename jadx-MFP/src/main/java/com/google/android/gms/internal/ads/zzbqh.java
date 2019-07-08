package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

final class zzbqh extends zzbqf {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzflv;
    private int zzflw;
    private int zzflx;
    private int zzfly;
    private int zzflz;

    private zzbqh(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzflz = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzflx = this.pos;
        this.zzflv = z;
    }

    public final int zzaku() throws IOException {
        if (zzalk()) {
            this.zzfly = 0;
            return 0;
        }
        this.zzfly = zzalm();
        int i = this.zzfly;
        if ((i >>> 3) != 0) {
            return i;
        }
        throw zzbrl.zzanf();
    }

    public final void zzeo(int i) throws zzbrl {
        if (this.zzfly != i) {
            throw zzbrl.zzang();
        }
    }

    public final boolean zzep(int i) throws IOException {
        int zzaku;
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
                    throw zzbrl.zzane();
                }
                while (i2 < 10) {
                    if (zzalr() < 0) {
                        i2++;
                    }
                }
                throw zzbrl.zzane();
                return true;
            case 1:
                zzet(8);
                return true;
            case 2:
                zzet(zzalm());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzet(4);
                return true;
            default:
                throw zzbrl.zzanh();
        }
        do {
            zzaku = zzaku();
            if (zzaku != 0) {
            }
            zzeo(((i >>> 3) << 3) | 4);
            return true;
        } while (zzep(zzaku));
        zzeo(((i >>> 3) << 3) | 4);
        return true;
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzalp());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzalo());
    }

    public final long zzakv() throws IOException {
        return zzaln();
    }

    public final long zzakw() throws IOException {
        return zzaln();
    }

    public final int zzakx() throws IOException {
        return zzalm();
    }

    public final long zzaky() throws IOException {
        return zzalp();
    }

    public final int zzakz() throws IOException {
        return zzalo();
    }

    public final boolean zzala() throws IOException {
        return zzaln() != 0;
    }

    public final String readString() throws IOException {
        int zzalm = zzalm();
        if (zzalm > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzalm <= i - i2) {
                String str = new String(this.buffer, i2, zzalm, zzbrf.UTF_8);
                this.pos += zzalm;
                return str;
            }
        }
        if (zzalm == 0) {
            return "";
        }
        if (zzalm < 0) {
            throw zzbrl.zzand();
        }
        throw zzbrl.zzanc();
    }

    public final String zzalb() throws IOException {
        int zzalm = zzalm();
        if (zzalm > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzalm <= i - i2) {
                String zzo = zzbuc.zzo(this.buffer, i2, zzalm);
                this.pos += zzalm;
                return zzo;
            }
        }
        if (zzalm == 0) {
            return "";
        }
        if (zzalm <= 0) {
            throw zzbrl.zzand();
        }
        throw zzbrl.zzanc();
    }

    public final <T extends zzbsl> T zza(zzbsw<T> zzbsw, zzbqq zzbqq) throws IOException {
        int zzalm = zzalm();
        if (this.zzflq < this.zzflr) {
            int zzer = zzer(zzalm);
            this.zzflq++;
            T t = (zzbsl) zzbsw.zza(this, zzbqq);
            zzeo(0);
            this.zzflq--;
            zzes(zzer);
            return t;
        }
        throw zzbrl.zzani();
    }

    public final zzbpu zzalc() throws IOException {
        byte[] bArr;
        int zzalm = zzalm();
        if (zzalm > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzalm <= i - i2) {
                zzbpu zzi = zzbpu.zzi(this.buffer, i2, zzalm);
                this.pos += zzalm;
                return zzi;
            }
        }
        if (zzalm == 0) {
            return zzbpu.zzfli;
        }
        if (zzalm > 0) {
            int i3 = this.limit;
            int i4 = this.pos;
            if (zzalm <= i3 - i4) {
                this.pos = zzalm + i4;
                bArr = Arrays.copyOfRange(this.buffer, i4, this.pos);
                return zzbpu.zzs(bArr);
            }
        }
        if (zzalm > 0) {
            throw zzbrl.zzanc();
        } else if (zzalm == 0) {
            bArr = zzbrf.zzfqr;
            return zzbpu.zzs(bArr);
        } else {
            throw zzbrl.zzand();
        }
    }

    public final int zzald() throws IOException {
        return zzalm();
    }

    public final int zzale() throws IOException {
        return zzalm();
    }

    public final int zzalf() throws IOException {
        return zzalo();
    }

    public final long zzalg() throws IOException {
        return zzalp();
    }

    public final int zzalh() throws IOException {
        return zzeu(zzalm());
    }

    public final long zzali() throws IOException {
        return zzax(zzaln());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzalm() throws java.io.IOException {
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
            long r0 = r5.zzalj()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbqh.zzalm():int");
    }

    private final long zzaln() throws IOException {
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
        return zzalj();
    }

    /* access modifiers changed from: 0000 */
    public final long zzalj() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzalr = zzalr();
            j |= ((long) (zzalr & Byte.MAX_VALUE)) << i;
            if ((zzalr & 128) == 0) {
                return j;
            }
        }
        throw zzbrl.zzane();
    }

    private final int zzalo() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 4) {
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
        }
        throw zzbrl.zzanc();
    }

    private final long zzalp() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 8) {
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzbrl.zzanc();
    }

    public final int zzer(int i) throws zzbrl {
        if (i >= 0) {
            int zzall = i + zzall();
            int i2 = this.zzflz;
            if (zzall <= i2) {
                this.zzflz = zzall;
                zzalq();
                return i2;
            }
            throw zzbrl.zzanc();
        }
        throw zzbrl.zzand();
    }

    private final void zzalq() {
        this.limit += this.zzflw;
        int i = this.limit;
        int i2 = i - this.zzflx;
        int i3 = this.zzflz;
        if (i2 > i3) {
            this.zzflw = i2 - i3;
            this.limit = i - this.zzflw;
            return;
        }
        this.zzflw = 0;
    }

    public final void zzes(int i) {
        this.zzflz = i;
        zzalq();
    }

    public final boolean zzalk() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzall() {
        return this.pos - this.zzflx;
    }

    private final byte zzalr() throws IOException {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzbrl.zzanc();
    }

    public final void zzet(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.limit;
            int i3 = this.pos;
            if (i <= i2 - i3) {
                this.pos = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzbrl.zzand();
        }
        throw zzbrl.zzanc();
    }
}

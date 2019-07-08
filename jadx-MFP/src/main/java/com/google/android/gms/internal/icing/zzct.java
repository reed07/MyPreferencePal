package com.google.android.gms.internal.icing;

import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzct extends zzcd {
    private static final Logger logger = Logger.getLogger(zzct.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzgn = zzgd.zzdr();
    zzcv zzgo = this;

    static class zza extends zzct {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i2 + 0;
                if ((i2 | 0 | (bArr.length - i3)) >= 0) {
                    this.buffer = bArr;
                    this.offset = 0;
                    this.position = 0;
                    this.limit = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(0), Integer.valueOf(i2)}));
            }
            throw new NullPointerException("buffer");
        }

        public final void zzb(int i, int i2) throws IOException {
            zzp((i << 3) | i2);
        }

        public final void zzc(int i, int i2) throws IOException {
            zzb(i, 0);
            zzo(i2);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzb(i, 0);
            zzp(i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzb(i, 5);
            zzr(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzb(i, 0);
            zzb(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzb(i, 1);
            zzd(j);
        }

        public final void zza(int i, boolean z) throws IOException {
            zzb(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zza(int i, String str) throws IOException {
            zzb(i, 2);
            zzu(str);
        }

        public final void zza(int i, zzce zzce) throws IOException {
            zzb(i, 2);
            zza(zzce);
        }

        public final void zza(zzce zzce) throws IOException {
            zzp(zzce.size());
            zzce.zza((zzcd) this);
        }

        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzp(i2);
            write(bArr, 0, i2);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(int i, zzeq zzeq, zzff zzff) throws IOException {
            zzb(i, 2);
            zzbx zzbx = (zzbx) zzeq;
            int zzag = zzbx.zzag();
            if (zzag == -1) {
                zzag = zzff.zzl(zzbx);
                zzbx.zzg(zzag);
            }
            zzp(zzag);
            zzff.zza(zzeq, this.zzgo);
        }

        public final void zza(int i, zzeq zzeq) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zzb(3, 2);
            zzb(zzeq);
            zzb(1, 4);
        }

        public final void zzb(int i, zzce zzce) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzce);
            zzb(1, 4);
        }

        public final void zzb(zzeq zzeq) throws IOException {
            zzp(zzeq.zzbi());
            zzeq.zzb(this);
        }

        public final void zzc(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzo(int i) throws IOException {
            if (i >= 0) {
                zzp(i);
            } else {
                zzb((long) i);
            }
        }

        public final void zzp(int i) throws IOException {
            if (!zzct.zzgn || zzav() < 10) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & Statements.GetOwnedFoodIdsDateDescending) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            } else {
                while ((i & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    zzgd.zza(bArr3, (long) i4, (byte) ((i & Statements.GetOwnedFoodIdsDateDescending) | 128));
                    i >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zzgd.zza(bArr4, (long) i5, (byte) i);
            }
        }

        public final void zzr(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr4[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzb(long j) throws IOException {
            if (!zzct.zzgn || zzav() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & Statements.GetOwnedFoodIdsDateDescending) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zzgd.zza(bArr3, (long) i3, (byte) ((((int) j) & Statements.GetOwnedFoodIdsDateDescending) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzgd.zza(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zzd(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) j);
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) ((int) (j >> 8));
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) ((int) (j >> 16));
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr4[i4] = (byte) ((int) (j >> 24));
                byte[] bArr5 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr5[i5] = (byte) ((int) (j >> 32));
                byte[] bArr6 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr6[i6] = (byte) ((int) (j >> 40));
                byte[] bArr7 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr7[i7] = (byte) ((int) (j >> 48));
                byte[] bArr8 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr8[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        private final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzu(String str) throws IOException {
            int i = this.position;
            try {
                int zzu = zzu(str.length() * 3);
                int zzu2 = zzu(str.length());
                if (zzu2 == zzu) {
                    this.position = i + zzu2;
                    int zza = zzgf.zza(str, this.buffer, this.position, zzav());
                    this.position = i;
                    zzp((zza - i) - zzu2);
                    this.position = zza;
                    return;
                }
                zzp(zzgf.zza(str));
                this.position = zzgf.zza(str, this.buffer, this.position, zzav());
            } catch (zzgi e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        public final int zzav() {
            return this.limit - this.position;
        }
    }

    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        zzb(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
        }
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static zzct zzb(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzf(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        if ((j & -16384) != 0) {
            i++;
        }
        return i;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzh(boolean z) {
        return 1;
    }

    public static int zzi(long j) {
        return 8;
    }

    private static long zzj(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzu(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzw(int i) {
        return 4;
    }

    public static int zzx(int i) {
        return 4;
    }

    private static int zzz(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzce zzce) throws IOException;

    public abstract void zza(int i, zzeq zzeq) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zza(int i, zzeq zzeq, zzff zzff) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(zzce zzce) throws IOException;

    public abstract int zzav();

    public abstract void zzb(int i, int i2) throws IOException;

    public abstract void zzb(int i, zzce zzce) throws IOException;

    public abstract void zzb(long j) throws IOException;

    public abstract void zzb(zzeq zzeq) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zzb(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzc(byte b) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract void zzd(long j) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzo(int i) throws IOException;

    public abstract void zzp(int i) throws IOException;

    public abstract void zzr(int i) throws IOException;

    public abstract void zzu(String str) throws IOException;

    private zzct() {
    }

    public final void zze(int i, int i2) throws IOException {
        zzd(i, zzz(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzj(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzf(i, Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zzq(int i) throws IOException {
        zzp(zzz(i));
    }

    public final void zzc(long j) throws IOException {
        zzb(zzj(j));
    }

    public final void zza(float f) throws IOException {
        zzr(Float.floatToRawIntBits(f));
    }

    public final void zza(double d) throws IOException {
        zzd(Double.doubleToRawLongBits(d));
    }

    public final void zzg(boolean z) throws IOException {
        zzc(z ? (byte) 1 : 0);
    }

    public static int zzg(int i, int i2) {
        return zzs(i) + zzt(i2);
    }

    public static int zzh(int i, int i2) {
        return zzs(i) + zzu(i2);
    }

    public static int zzi(int i, int i2) {
        return zzs(i) + zzu(zzz(i2));
    }

    public static int zzj(int i, int i2) {
        return zzs(i) + 4;
    }

    public static int zzk(int i, int i2) {
        return zzs(i) + 4;
    }

    public static int zzd(int i, long j) {
        return zzs(i) + zzf(j);
    }

    public static int zze(int i, long j) {
        return zzs(i) + zzf(j);
    }

    public static int zzf(int i, long j) {
        return zzs(i) + zzf(zzj(j));
    }

    public static int zzg(int i, long j) {
        return zzs(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzs(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzs(i) + 4;
    }

    public static int zzb(int i, double d) {
        return zzs(i) + 8;
    }

    public static int zzb(int i, boolean z) {
        return zzs(i) + 1;
    }

    public static int zzl(int i, int i2) {
        return zzs(i) + zzt(i2);
    }

    public static int zzb(int i, String str) {
        return zzs(i) + zzv(str);
    }

    public static int zzc(int i, zzce zzce) {
        int zzs = zzs(i);
        int size = zzce.size();
        return zzs + zzu(size) + size;
    }

    public static int zza(int i, zzdy zzdy) {
        int zzs = zzs(i);
        int zzbi = zzdy.zzbi();
        return zzs + zzu(zzbi) + zzbi;
    }

    static int zzb(int i, zzeq zzeq, zzff zzff) {
        return zzs(i) + zza(zzeq, zzff);
    }

    public static int zzb(int i, zzeq zzeq) {
        return (zzs(1) << 1) + zzh(2, i) + zzs(3) + zzc(zzeq);
    }

    public static int zzd(int i, zzce zzce) {
        return (zzs(1) << 1) + zzh(2, i) + zzc(3, zzce);
    }

    public static int zzb(int i, zzdy zzdy) {
        return (zzs(1) << 1) + zzh(2, i) + zza(3, zzdy);
    }

    public static int zzs(int i) {
        return zzu(i << 3);
    }

    public static int zzt(int i) {
        if (i >= 0) {
            return zzu(i);
        }
        return 10;
    }

    public static int zzv(int i) {
        return zzu(zzz(i));
    }

    public static int zze(long j) {
        return zzf(j);
    }

    public static int zzg(long j) {
        return zzf(zzj(j));
    }

    public static int zzy(int i) {
        return zzt(i);
    }

    public static int zzv(String str) {
        int i;
        try {
            i = zzgf.zza(str);
        } catch (zzgi unused) {
            i = str.getBytes(zzdl.UTF_8).length;
        }
        return zzu(i) + i;
    }

    public static int zza(zzdy zzdy) {
        int zzbi = zzdy.zzbi();
        return zzu(zzbi) + zzbi;
    }

    public static int zzb(zzce zzce) {
        int size = zzce.size();
        return zzu(size) + size;
    }

    public static int zzc(byte[] bArr) {
        int length = bArr.length;
        return zzu(length) + length;
    }

    public static int zzc(zzeq zzeq) {
        int zzbi = zzeq.zzbi();
        return zzu(zzbi) + zzbi;
    }

    static int zza(zzeq zzeq, zzff zzff) {
        zzbx zzbx = (zzbx) zzeq;
        int zzag = zzbx.zzag();
        if (zzag == -1) {
            zzag = zzff.zzl(zzbx);
            zzbx.zzg(zzag);
        }
        return zzu(zzag) + zzag;
    }

    public final void zzaw() {
        if (zzav() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(String str, zzgi zzgi) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzgi);
        byte[] bytes = str.getBytes(zzdl.UTF_8);
        try {
            zzp(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb(e);
        } catch (zzb e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzc(int i, zzeq zzeq, zzff zzff) {
        int zzs = zzs(i) << 1;
        zzbx zzbx = (zzbx) zzeq;
        int zzag = zzbx.zzag();
        if (zzag == -1) {
            zzag = zzff.zzl(zzbx);
            zzbx.zzg(zzag);
        }
        return zzs + zzag;
    }

    @Deprecated
    public static int zzd(zzeq zzeq) {
        return zzeq.zzbi();
    }

    @Deprecated
    public static int zzaa(int i) {
        return zzu(i);
    }
}

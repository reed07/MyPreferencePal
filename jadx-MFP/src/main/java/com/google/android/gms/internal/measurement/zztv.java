package com.google.android.gms.internal.measurement;

import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zztv extends zztd {
    private static final Logger logger = Logger.getLogger(zztv.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzbuo = zzxj.zzyo();
    zztx zzbup = this;

    static class zza extends zztv {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i + i2;
                if ((i | i2 | (bArr.length - i3)) >= 0) {
                    this.buffer = bArr;
                    this.offset = i;
                    this.position = i;
                    this.limit = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
            }
            throw new NullPointerException("buffer");
        }

        public void flush() {
        }

        public final void zzc(int i, int i2) throws IOException {
            zzba((i << 3) | i2);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzc(i, 0);
            zzaz(i2);
        }

        public final void zze(int i, int i2) throws IOException {
            zzc(i, 0);
            zzba(i2);
        }

        public final void zzg(int i, int i2) throws IOException {
            zzc(i, 5);
            zzbc(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzc(i, 0);
            zzat(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzc(i, 1);
            zzav(j);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzc(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zzb(int i, String str) throws IOException {
            zzc(i, 2);
            zzgb(str);
        }

        public final void zza(int i, zzte zzte) throws IOException {
            zzc(i, 2);
            zza(zzte);
        }

        public final void zza(zzte zzte) throws IOException {
            zzba(zzte.size());
            zzte.zza((zztd) this);
        }

        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzba(i2);
            write(bArr, 0, i2);
        }

        public final void zza(int i, zzvv zzvv) throws IOException {
            zzc(i, 2);
            zzb(zzvv);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(int i, zzvv zzvv, zzwl zzwl) throws IOException {
            zzc(i, 2);
            zzsx zzsx = (zzsx) zzvv;
            int zztx = zzsx.zztx();
            if (zztx == -1) {
                zztx = zzwl.zzai(zzsx);
                zzsx.zzai(zztx);
            }
            zzba(zztx);
            zzwl.zza(zzvv, this.zzbup);
        }

        public final void zzb(int i, zzvv zzvv) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzvv);
            zzc(1, 4);
        }

        public final void zzb(int i, zzte zzte) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzte);
            zzc(1, 4);
        }

        public final void zzb(zzvv zzvv) throws IOException {
            zzba(zzvv.zzvx());
            zzvv.zzb(this);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(zzvv zzvv, zzwl zzwl) throws IOException {
            zzsx zzsx = (zzsx) zzvv;
            int zztx = zzsx.zztx();
            if (zztx == -1) {
                zztx = zzwl.zzai(zzsx);
                zzsx.zzai(zztx);
            }
            zzba(zztx);
            zzwl.zza(zzvv, this.zzbup);
        }

        public final void zzc(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzaz(int i) throws IOException {
            if (i >= 0) {
                zzba(i);
            } else {
                zzat((long) i);
            }
        }

        public final void zzba(int i) throws IOException {
            if (!zztv.zzbuo || zzvj() < 10) {
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
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            } else {
                while ((i & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    zzxj.zza(bArr3, (long) i4, (byte) ((i & Statements.GetOwnedFoodIdsDateDescending) | 128));
                    i >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zzxj.zza(bArr4, (long) i5, (byte) i);
            }
        }

        public final void zzbc(int i) throws IOException {
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
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzat(long j) throws IOException {
            if (!zztv.zzbuo || zzvj() < 10) {
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
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zzxj.zza(bArr3, (long) i3, (byte) ((((int) j) & Statements.GetOwnedFoodIdsDateDescending) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzxj.zza(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zzav(long j) throws IOException {
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
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzgb(String str) throws IOException {
            int i = this.position;
            try {
                int zzbf = zzbf(str.length() * 3);
                int zzbf2 = zzbf(str.length());
                if (zzbf2 == zzbf) {
                    this.position = i + zzbf2;
                    int zza = zzxl.zza(str, this.buffer, this.position, zzvj());
                    this.position = i;
                    zzba((zza - i) - zzbf2);
                    this.position = zza;
                    return;
                }
                zzba(zzxl.zza(str));
                this.position = zzxl.zza(str, this.buffer, this.position, zzvj());
            } catch (zzxp e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzc((Throwable) e2);
            }
        }

        public final int zzvj() {
            return this.limit - this.position;
        }

        public final int zzvl() {
            return this.position - this.offset;
        }
    }

    static final class zzb extends zza {
        private final ByteBuffer zzbuq;
        private int zzbur;

        zzb(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzbuq = byteBuffer;
            this.zzbur = byteBuffer.position();
        }

        public final void flush() {
            this.zzbuq.position(this.zzbur + zzvl());
        }
    }

    public static class zzc extends IOException {
        zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzc(String str) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        }

        zzc(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        zzc(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
        }
    }

    static final class zzd extends zztv {
        private final int zzbur;
        private final ByteBuffer zzbus;
        private final ByteBuffer zzbut;

        zzd(ByteBuffer byteBuffer) {
            super();
            this.zzbus = byteBuffer;
            this.zzbut = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzbur = byteBuffer.position();
        }

        public final void zzc(int i, int i2) throws IOException {
            zzba((i << 3) | i2);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzc(i, 0);
            zzaz(i2);
        }

        public final void zze(int i, int i2) throws IOException {
            zzc(i, 0);
            zzba(i2);
        }

        public final void zzg(int i, int i2) throws IOException {
            zzc(i, 5);
            zzbc(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzc(i, 0);
            zzat(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzc(i, 1);
            zzav(j);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzc(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zzb(int i, String str) throws IOException {
            zzc(i, 2);
            zzgb(str);
        }

        public final void zza(int i, zzte zzte) throws IOException {
            zzc(i, 2);
            zza(zzte);
        }

        public final void zza(int i, zzvv zzvv) throws IOException {
            zzc(i, 2);
            zzb(zzvv);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(int i, zzvv zzvv, zzwl zzwl) throws IOException {
            zzc(i, 2);
            zza(zzvv, zzwl);
        }

        public final void zzb(int i, zzvv zzvv) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzvv);
            zzc(1, 4);
        }

        public final void zzb(int i, zzte zzte) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzte);
            zzc(1, 4);
        }

        public final void zzb(zzvv zzvv) throws IOException {
            zzba(zzvv.zzvx());
            zzvv.zzb(this);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(zzvv zzvv, zzwl zzwl) throws IOException {
            zzsx zzsx = (zzsx) zzvv;
            int zztx = zzsx.zztx();
            if (zztx == -1) {
                zztx = zzwl.zzai(zzsx);
                zzsx.zzai(zztx);
            }
            zzba(zztx);
            zzwl.zza(zzvv, this.zzbup);
        }

        public final void zzc(byte b) throws IOException {
            try {
                this.zzbut.put(b);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zza(zzte zzte) throws IOException {
            zzba(zzte.size());
            zzte.zza((zztd) this);
        }

        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzba(i2);
            write(bArr, 0, i2);
        }

        public final void zzaz(int i) throws IOException {
            if (i >= 0) {
                zzba(i);
            } else {
                zzat((long) i);
            }
        }

        public final void zzba(int i) throws IOException {
            while ((i & -128) != 0) {
                this.zzbut.put((byte) ((i & Statements.GetOwnedFoodIdsDateDescending) | 128));
                i >>>= 7;
            }
            try {
                this.zzbut.put((byte) i);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzbc(int i) throws IOException {
            try {
                this.zzbut.putInt(i);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzat(long j) throws IOException {
            while ((-128 & j) != 0) {
                this.zzbut.put((byte) ((((int) j) & Statements.GetOwnedFoodIdsDateDescending) | 128));
                j >>>= 7;
            }
            try {
                this.zzbut.put((byte) ((int) j));
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzav(long j) throws IOException {
            try {
                this.zzbut.putLong(j);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.zzbut.put(bArr, i, i2);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc((Throwable) e);
            } catch (BufferOverflowException e2) {
                throw new zzc((Throwable) e2);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzgb(String str) throws IOException {
            int position = this.zzbut.position();
            try {
                int zzbf = zzbf(str.length() * 3);
                int zzbf2 = zzbf(str.length());
                if (zzbf2 == zzbf) {
                    int position2 = this.zzbut.position() + zzbf2;
                    this.zzbut.position(position2);
                    zzgd(str);
                    int position3 = this.zzbut.position();
                    this.zzbut.position(position);
                    zzba(position3 - position2);
                    this.zzbut.position(position3);
                    return;
                }
                zzba(zzxl.zza(str));
                zzgd(str);
            } catch (zzxp e) {
                this.zzbut.position(position);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc((Throwable) e2);
            }
        }

        public final void flush() {
            this.zzbus.position(this.zzbut.position());
        }

        public final int zzvj() {
            return this.zzbut.remaining();
        }

        private final void zzgd(String str) throws IOException {
            try {
                zzxl.zza(str, this.zzbut);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc((Throwable) e);
            }
        }
    }

    static final class zze extends zztv {
        private final ByteBuffer zzbus;
        private final ByteBuffer zzbut;
        private final long zzbuu;
        private final long zzbuv;
        private final long zzbuw;
        private final long zzbux = (this.zzbuw - 10);
        private long zzbuy = this.zzbuv;

        zze(ByteBuffer byteBuffer) {
            super();
            this.zzbus = byteBuffer;
            this.zzbut = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzbuu = zzxj.zzb(byteBuffer);
            this.zzbuv = this.zzbuu + ((long) byteBuffer.position());
            this.zzbuw = this.zzbuu + ((long) byteBuffer.limit());
        }

        public final void zzc(int i, int i2) throws IOException {
            zzba((i << 3) | i2);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzc(i, 0);
            zzaz(i2);
        }

        public final void zze(int i, int i2) throws IOException {
            zzc(i, 0);
            zzba(i2);
        }

        public final void zzg(int i, int i2) throws IOException {
            zzc(i, 5);
            zzbc(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzc(i, 0);
            zzat(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzc(i, 1);
            zzav(j);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzc(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zzb(int i, String str) throws IOException {
            zzc(i, 2);
            zzgb(str);
        }

        public final void zza(int i, zzte zzte) throws IOException {
            zzc(i, 2);
            zza(zzte);
        }

        public final void zza(int i, zzvv zzvv) throws IOException {
            zzc(i, 2);
            zzb(zzvv);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(int i, zzvv zzvv, zzwl zzwl) throws IOException {
            zzc(i, 2);
            zza(zzvv, zzwl);
        }

        public final void zzb(int i, zzvv zzvv) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzvv);
            zzc(1, 4);
        }

        public final void zzb(int i, zzte zzte) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzte);
            zzc(1, 4);
        }

        public final void zzb(zzvv zzvv) throws IOException {
            zzba(zzvv.zzvx());
            zzvv.zzb(this);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(zzvv zzvv, zzwl zzwl) throws IOException {
            zzsx zzsx = (zzsx) zzvv;
            int zztx = zzsx.zztx();
            if (zztx == -1) {
                zztx = zzwl.zzai(zzsx);
                zzsx.zzai(zztx);
            }
            zzba(zztx);
            zzwl.zza(zzvv, this.zzbup);
        }

        public final void zzc(byte b) throws IOException {
            long j = this.zzbuy;
            if (j < this.zzbuw) {
                this.zzbuy = 1 + j;
                zzxj.zza(j, b);
                return;
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j), Long.valueOf(this.zzbuw), Integer.valueOf(1)}));
        }

        public final void zza(zzte zzte) throws IOException {
            zzba(zzte.size());
            zzte.zza((zztd) this);
        }

        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzba(i2);
            write(bArr, 0, i2);
        }

        public final void zzaz(int i) throws IOException {
            if (i >= 0) {
                zzba(i);
            } else {
                zzat((long) i);
            }
        }

        public final void zzba(int i) throws IOException {
            if (this.zzbuy <= this.zzbux) {
                while ((i & -128) != 0) {
                    long j = this.zzbuy;
                    this.zzbuy = j + 1;
                    zzxj.zza(j, (byte) ((i & Statements.GetOwnedFoodIdsDateDescending) | 128));
                    i >>>= 7;
                }
                long j2 = this.zzbuy;
                this.zzbuy = 1 + j2;
                zzxj.zza(j2, (byte) i);
                return;
            }
            while (true) {
                long j3 = this.zzbuy;
                if (j3 >= this.zzbuw) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j3), Long.valueOf(this.zzbuw), Integer.valueOf(1)}));
                } else if ((i & -128) == 0) {
                    this.zzbuy = 1 + j3;
                    zzxj.zza(j3, (byte) i);
                    return;
                } else {
                    this.zzbuy = j3 + 1;
                    zzxj.zza(j3, (byte) ((i & Statements.GetOwnedFoodIdsDateDescending) | 128));
                    i >>>= 7;
                }
            }
        }

        public final void zzbc(int i) throws IOException {
            this.zzbut.putInt((int) (this.zzbuy - this.zzbuu), i);
            this.zzbuy += 4;
        }

        public final void zzat(long j) throws IOException {
            if (this.zzbuy <= this.zzbux) {
                while ((j & -128) != 0) {
                    long j2 = this.zzbuy;
                    this.zzbuy = j2 + 1;
                    zzxj.zza(j2, (byte) ((((int) j) & Statements.GetOwnedFoodIdsDateDescending) | 128));
                    j >>>= 7;
                }
                long j3 = this.zzbuy;
                this.zzbuy = 1 + j3;
                zzxj.zza(j3, (byte) ((int) j));
                return;
            }
            while (true) {
                long j4 = this.zzbuy;
                if (j4 >= this.zzbuw) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j4), Long.valueOf(this.zzbuw), Integer.valueOf(1)}));
                } else if ((j & -128) == 0) {
                    this.zzbuy = 1 + j4;
                    zzxj.zza(j4, (byte) ((int) j));
                    return;
                } else {
                    this.zzbuy = j4 + 1;
                    zzxj.zza(j4, (byte) ((((int) j) & Statements.GetOwnedFoodIdsDateDescending) | 128));
                    j >>>= 7;
                }
            }
        }

        public final void zzav(long j) throws IOException {
            this.zzbut.putLong((int) (this.zzbuy - this.zzbuu), j);
            this.zzbuy += 8;
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i) {
                long j = (long) i2;
                long j2 = this.zzbuw - j;
                long j3 = this.zzbuy;
                if (j2 >= j3) {
                    zzxj.zza(bArr, (long) i, j3, j);
                    this.zzbuy += j;
                    return;
                }
            }
            if (bArr == null) {
                throw new NullPointerException("value");
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzbuy), Long.valueOf(this.zzbuw), Integer.valueOf(i2)}));
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzgb(String str) throws IOException {
            long j = this.zzbuy;
            try {
                int zzbf = zzbf(str.length() * 3);
                int zzbf2 = zzbf(str.length());
                if (zzbf2 == zzbf) {
                    int i = ((int) (this.zzbuy - this.zzbuu)) + zzbf2;
                    this.zzbut.position(i);
                    zzxl.zza(str, this.zzbut);
                    int position = this.zzbut.position() - i;
                    zzba(position);
                    this.zzbuy += (long) position;
                    return;
                }
                int zza = zzxl.zza(str);
                zzba(zza);
                zzbc(this.zzbuy);
                zzxl.zza(str, this.zzbut);
                this.zzbuy += (long) zza;
            } catch (zzxp e) {
                this.zzbuy = j;
                zzbc(this.zzbuy);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc((Throwable) e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzc((Throwable) e3);
            }
        }

        public final void flush() {
            this.zzbus.position((int) (this.zzbuy - this.zzbuu));
        }

        public final int zzvj() {
            return (int) (this.zzbuw - this.zzbuy);
        }

        private final void zzbc(long j) {
            this.zzbut.position((int) (j - this.zzbuu));
        }
    }

    public static int zzax(long j) {
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

    public static int zzaz(long j) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzba(long j) {
        return 8;
    }

    private static long zzbb(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzbf(int i) {
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

    public static int zzbh(int i) {
        return 4;
    }

    public static int zzbi(int i) {
        return 4;
    }

    private static int zzbk(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static int zzc(double d) {
        return 8;
    }

    public static zztv zzj(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzt(boolean z) {
        return 1;
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzte zzte) throws IOException;

    public abstract void zza(int i, zzvv zzvv) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zza(int i, zzvv zzvv, zzwl zzwl) throws IOException;

    public abstract void zza(zzte zzte) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zza(zzvv zzvv, zzwl zzwl) throws IOException;

    public abstract void zzat(long j) throws IOException;

    public abstract void zzav(long j) throws IOException;

    public abstract void zzaz(int i) throws IOException;

    public abstract void zzb(int i, zzte zzte) throws IOException;

    public abstract void zzb(int i, zzvv zzvv) throws IOException;

    public abstract void zzb(int i, String str) throws IOException;

    public abstract void zzb(int i, boolean z) throws IOException;

    public abstract void zzb(zzvv zzvv) throws IOException;

    public abstract void zzba(int i) throws IOException;

    public abstract void zzbc(int i) throws IOException;

    public abstract void zzc(byte b) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract void zze(int i, int i2) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zze(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzg(int i, int i2) throws IOException;

    public abstract void zzgb(String str) throws IOException;

    public abstract int zzvj();

    public static zztv zza(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzb(byteBuffer);
        }
        if (!byteBuffer.isDirect() || byteBuffer.isReadOnly()) {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        } else if (zzxj.zzyp()) {
            return new zze(byteBuffer);
        } else {
            return new zzd(byteBuffer);
        }
    }

    private zztv() {
    }

    public final void zzf(int i, int i2) throws IOException {
        zze(i, zzbk(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzbb(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzg(i, Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zzbb(int i) throws IOException {
        zzba(zzbk(i));
    }

    public final void zzau(long j) throws IOException {
        zzat(zzbb(j));
    }

    public final void zza(float f) throws IOException {
        zzbc(Float.floatToRawIntBits(f));
    }

    public final void zzb(double d) throws IOException {
        zzav(Double.doubleToRawLongBits(d));
    }

    public final void zzs(boolean z) throws IOException {
        zzc(z ? (byte) 1 : 0);
    }

    public static int zzh(int i, int i2) {
        return zzbd(i) + zzbe(i2);
    }

    public static int zzi(int i, int i2) {
        return zzbd(i) + zzbf(i2);
    }

    public static int zzj(int i, int i2) {
        return zzbd(i) + zzbf(zzbk(i2));
    }

    public static int zzk(int i, int i2) {
        return zzbd(i) + 4;
    }

    public static int zzl(int i, int i2) {
        return zzbd(i) + 4;
    }

    public static int zzd(int i, long j) {
        return zzbd(i) + zzax(j);
    }

    public static int zze(int i, long j) {
        return zzbd(i) + zzax(j);
    }

    public static int zzf(int i, long j) {
        return zzbd(i) + zzax(zzbb(j));
    }

    public static int zzg(int i, long j) {
        return zzbd(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzbd(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzbd(i) + 4;
    }

    public static int zzb(int i, double d) {
        return zzbd(i) + 8;
    }

    public static int zzc(int i, boolean z) {
        return zzbd(i) + 1;
    }

    public static int zzm(int i, int i2) {
        return zzbd(i) + zzbe(i2);
    }

    public static int zzc(int i, String str) {
        return zzbd(i) + zzgc(str);
    }

    public static int zzc(int i, zzte zzte) {
        int zzbd = zzbd(i);
        int size = zzte.size();
        return zzbd + zzbf(size) + size;
    }

    public static int zza(int i, zzvc zzvc) {
        int zzbd = zzbd(i);
        int zzvx = zzvc.zzvx();
        return zzbd + zzbf(zzvx) + zzvx;
    }

    public static int zzc(int i, zzvv zzvv) {
        return zzbd(i) + zzc(zzvv);
    }

    static int zzb(int i, zzvv zzvv, zzwl zzwl) {
        return zzbd(i) + zzb(zzvv, zzwl);
    }

    public static int zzd(int i, zzvv zzvv) {
        return (zzbd(1) << 1) + zzi(2, i) + zzc(3, zzvv);
    }

    public static int zzd(int i, zzte zzte) {
        return (zzbd(1) << 1) + zzi(2, i) + zzc(3, zzte);
    }

    public static int zzb(int i, zzvc zzvc) {
        return (zzbd(1) << 1) + zzi(2, i) + zza(3, zzvc);
    }

    public static int zzbd(int i) {
        return zzbf(i << 3);
    }

    public static int zzbe(int i) {
        if (i >= 0) {
            return zzbf(i);
        }
        return 10;
    }

    public static int zzbg(int i) {
        return zzbf(zzbk(i));
    }

    public static int zzaw(long j) {
        return zzax(j);
    }

    public static int zzay(long j) {
        return zzax(zzbb(j));
    }

    public static int zzbj(int i) {
        return zzbe(i);
    }

    public static int zzgc(String str) {
        int i;
        try {
            i = zzxl.zza(str);
        } catch (zzxp unused) {
            i = str.getBytes(zzuq.UTF_8).length;
        }
        return zzbf(i) + i;
    }

    public static int zza(zzvc zzvc) {
        int zzvx = zzvc.zzvx();
        return zzbf(zzvx) + zzvx;
    }

    public static int zzb(zzte zzte) {
        int size = zzte.size();
        return zzbf(size) + size;
    }

    public static int zzk(byte[] bArr) {
        int length = bArr.length;
        return zzbf(length) + length;
    }

    public static int zzc(zzvv zzvv) {
        int zzvx = zzvv.zzvx();
        return zzbf(zzvx) + zzvx;
    }

    static int zzb(zzvv zzvv, zzwl zzwl) {
        zzsx zzsx = (zzsx) zzvv;
        int zztx = zzsx.zztx();
        if (zztx == -1) {
            zztx = zzwl.zzai(zzsx);
            zzsx.zzai(zztx);
        }
        return zzbf(zztx) + zztx;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(String str, zzxp zzxp) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzxp);
        byte[] bytes = str.getBytes(zzuq.UTF_8);
        try {
            zzba(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzc((Throwable) e);
        } catch (zzc e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzc(int i, zzvv zzvv, zzwl zzwl) {
        int zzbd = zzbd(i) << 1;
        zzsx zzsx = (zzsx) zzvv;
        int zztx = zzsx.zztx();
        if (zztx == -1) {
            zztx = zzwl.zzai(zzsx);
            zzsx.zzai(zztx);
        }
        return zzbd + zztx;
    }

    @Deprecated
    public static int zzd(zzvv zzvv) {
        return zzvv.zzvx();
    }

    @Deprecated
    public static int zzbl(int i) {
        return zzbf(i);
    }
}

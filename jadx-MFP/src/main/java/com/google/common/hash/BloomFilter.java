package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    /* access modifiers changed from: private */
    public final LockFreeBitArray bits;
    /* access modifiers changed from: private */
    public final Funnel<? super T> funnel;
    /* access modifiers changed from: private */
    public final int numHashFunctions;
    /* access modifiers changed from: private */
    public final Strategy strategy;

    private static class SerialForm<T> implements Serializable {
        private static final long serialVersionUID = 1;
        final long[] data;
        final Funnel<? super T> funnel;
        final int numHashFunctions;
        final Strategy strategy;

        SerialForm(BloomFilter<T> bloomFilter) {
            this.data = LockFreeBitArray.toPlainArray(bloomFilter.bits.data);
            this.numHashFunctions = bloomFilter.numHashFunctions;
            this.funnel = bloomFilter.funnel;
            this.strategy = bloomFilter.strategy;
        }

        /* access modifiers changed from: 0000 */
        public Object readResolve() {
            BloomFilter bloomFilter = new BloomFilter(new LockFreeBitArray(this.data), this.numHashFunctions, this.funnel, this.strategy);
            return bloomFilter;
        }
    }

    interface Strategy extends Serializable {
        <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, LockFreeBitArray lockFreeBitArray);

        int ordinal();

        <T> boolean put(T t, Funnel<? super T> funnel, int i, LockFreeBitArray lockFreeBitArray);
    }

    private BloomFilter(LockFreeBitArray lockFreeBitArray, int i, Funnel<? super T> funnel2, Strategy strategy2) {
        boolean z = true;
        Preconditions.checkArgument(i > 0, "numHashFunctions (%s) must be > 0", i);
        if (i > 255) {
            z = false;
        }
        Preconditions.checkArgument(z, "numHashFunctions (%s) must be <= 255", i);
        this.bits = (LockFreeBitArray) Preconditions.checkNotNull(lockFreeBitArray);
        this.numHashFunctions = i;
        this.funnel = (Funnel) Preconditions.checkNotNull(funnel2);
        this.strategy = (Strategy) Preconditions.checkNotNull(strategy2);
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<>(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
    }

    public boolean mightContain(T t) {
        return this.strategy.mightContain(t, this.funnel, this.numHashFunctions, this.bits);
    }

    @Deprecated
    public boolean apply(T t) {
        return mightContain(t);
    }

    @CanIgnoreReturnValue
    public boolean put(T t) {
        return this.strategy.put(t, this.funnel, this.numHashFunctions, this.bits);
    }

    public double expectedFpp() {
        return Math.pow(((double) this.bits.bitCount()) / ((double) bitSize()), (double) this.numHashFunctions);
    }

    public long approximateElementCount() {
        double bitSize = (double) this.bits.bitSize();
        return DoubleMath.roundToLong(((-Math.log1p(-(((double) this.bits.bitCount()) / bitSize))) * bitSize) / ((double) this.numHashFunctions), RoundingMode.HALF_UP);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public long bitSize() {
        return this.bits.bitSize();
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=com.google.common.hash.BloomFilter<T>, code=com.google.common.hash.BloomFilter, for r6v0, types: [com.google.common.hash.BloomFilter<T>, com.google.common.hash.BloomFilter, java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isCompatible(com.google.common.hash.BloomFilter r6) {
        /*
            r5 = this;
            com.google.common.base.Preconditions.checkNotNull(r6)
            if (r5 == r6) goto L_0x002d
            int r0 = r5.numHashFunctions
            int r1 = r6.numHashFunctions
            if (r0 != r1) goto L_0x002d
            long r0 = r5.bitSize()
            long r2 = r6.bitSize()
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x002d
            com.google.common.hash.BloomFilter$Strategy r0 = r5.strategy
            com.google.common.hash.BloomFilter$Strategy r1 = r6.strategy
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x002d
            com.google.common.hash.Funnel<? super T> r0 = r5.funnel
            com.google.common.hash.Funnel<? super T> r6 = r6.funnel
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x002d
            r6 = 1
            goto L_0x002e
        L_0x002d:
            r6 = 0
        L_0x002e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.hash.BloomFilter.isCompatible(com.google.common.hash.BloomFilter):boolean");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=com.google.common.hash.BloomFilter<T>, code=com.google.common.hash.BloomFilter, for r14v0, types: [com.google.common.hash.BloomFilter<T>, com.google.common.hash.BloomFilter, java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void putAll(com.google.common.hash.BloomFilter r14) {
        /*
            r13 = this;
            com.google.common.base.Preconditions.checkNotNull(r14)
            r0 = 1
            r1 = 0
            if (r13 == r14) goto L_0x0009
            r2 = 1
            goto L_0x000a
        L_0x0009:
            r2 = 0
        L_0x000a:
            java.lang.String r3 = "Cannot combine a BloomFilter with itself."
            com.google.common.base.Preconditions.checkArgument(r2, r3)
            int r2 = r13.numHashFunctions
            int r3 = r14.numHashFunctions
            if (r2 != r3) goto L_0x0017
            r2 = 1
            goto L_0x0018
        L_0x0017:
            r2 = 0
        L_0x0018:
            java.lang.String r3 = "BloomFilters must have the same number of hash functions (%s != %s)"
            int r4 = r13.numHashFunctions
            int r5 = r14.numHashFunctions
            com.google.common.base.Preconditions.checkArgument(r2, r3, r4, r5)
            long r2 = r13.bitSize()
            long r4 = r14.bitSize()
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x002f
            r7 = 1
            goto L_0x0030
        L_0x002f:
            r7 = 0
        L_0x0030:
            java.lang.String r8 = "BloomFilters must have the same size underlying bit arrays (%s != %s)"
            long r9 = r13.bitSize()
            long r11 = r14.bitSize()
            com.google.common.base.Preconditions.checkArgument(r7, r8, r9, r11)
            com.google.common.hash.BloomFilter$Strategy r0 = r13.strategy
            com.google.common.hash.BloomFilter$Strategy r1 = r14.strategy
            boolean r0 = r0.equals(r1)
            java.lang.String r1 = "BloomFilters must have equal strategies (%s != %s)"
            com.google.common.hash.BloomFilter$Strategy r2 = r13.strategy
            com.google.common.hash.BloomFilter$Strategy r3 = r14.strategy
            com.google.common.base.Preconditions.checkArgument(r0, r1, r2, r3)
            com.google.common.hash.Funnel<? super T> r0 = r13.funnel
            com.google.common.hash.Funnel<? super T> r1 = r14.funnel
            boolean r0 = r0.equals(r1)
            java.lang.String r1 = "BloomFilters must have equal funnels (%s != %s)"
            com.google.common.hash.Funnel<? super T> r2 = r13.funnel
            com.google.common.hash.Funnel<? super T> r3 = r14.funnel
            com.google.common.base.Preconditions.checkArgument(r0, r1, r2, r3)
            com.google.common.hash.BloomFilterStrategies$LockFreeBitArray r0 = r13.bits
            com.google.common.hash.BloomFilterStrategies$LockFreeBitArray r14 = r14.bits
            r0.putAll(r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.hash.BloomFilter.putAll(com.google.common.hash.BloomFilter):void");
    }

    public boolean equals(@NullableDecl Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BloomFilter)) {
            return false;
        }
        BloomFilter bloomFilter = (BloomFilter) obj;
        if (this.numHashFunctions != bloomFilter.numHashFunctions || !this.funnel.equals(bloomFilter.funnel) || !this.bits.equals(bloomFilter.bits) || !this.strategy.equals(bloomFilter.strategy)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i, double d) {
        return create(funnel2, (long) i, d);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j, double d) {
        return create(funnel2, j, d, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    @VisibleForTesting
    static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j, double d, Strategy strategy2) {
        Preconditions.checkNotNull(funnel2);
        boolean z = true;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        Preconditions.checkArgument(i >= 0, "Expected insertions (%s) must be >= 0", j);
        Preconditions.checkArgument(d > 0.0d, "False positive probability (%s) must be > 0.0", (Object) Double.valueOf(d));
        if (d >= 1.0d) {
            z = false;
        }
        Preconditions.checkArgument(z, "False positive probability (%s) must be < 1.0", (Object) Double.valueOf(d));
        Preconditions.checkNotNull(strategy2);
        if (i == 0) {
            j = 1;
        }
        long optimalNumOfBits = optimalNumOfBits(j, d);
        try {
            return new BloomFilter<>(new LockFreeBitArray(optimalNumOfBits), optimalNumOfHashFunctions(j, optimalNumOfBits), funnel2, strategy2);
        } catch (IllegalArgumentException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not create BloomFilter of ");
            sb.append(optimalNumOfBits);
            sb.append(" bits");
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i) {
        return create(funnel2, (long) i);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j) {
        return create(funnel2, j, 0.03d);
    }

    @VisibleForTesting
    static int optimalNumOfHashFunctions(long j, long j2) {
        return Math.max(1, (int) Math.round((((double) j2) / ((double) j)) * Math.log(2.0d)));
    }

    @VisibleForTesting
    static long optimalNumOfBits(long j, double d) {
        if (d == 0.0d) {
            d = Double.MIN_VALUE;
        }
        return (long) ((((double) (-j)) * Math.log(d)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(SignedBytes.checkedCast((long) this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.checkedCast((long) this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.data.length());
        for (int i = 0; i < this.bits.data.length(); i++) {
            dataOutputStream.writeLong(this.bits.data.get(i));
        }
    }

    public static <T> BloomFilter<T> readFrom(InputStream inputStream, Funnel<? super T> funnel2) throws IOException {
        byte b;
        int i;
        Preconditions.checkNotNull(inputStream, "InputStream");
        Preconditions.checkNotNull(funnel2, "Funnel");
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            b = dataInputStream.readByte();
            try {
                i = UnsignedBytes.toInt(dataInputStream.readByte());
            } catch (RuntimeException e) {
                e = e;
                i = -1;
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ");
                sb.append(b);
                sb.append(" numHashFunctions: ");
                sb.append(i);
                sb.append(" dataLength: ");
                sb.append(-1);
                throw new IOException(sb.toString(), e);
            }
            try {
                int readInt = dataInputStream.readInt();
                BloomFilterStrategies bloomFilterStrategies = BloomFilterStrategies.values()[b];
                long[] jArr = new long[readInt];
                for (int i2 = 0; i2 < jArr.length; i2++) {
                    jArr[i2] = dataInputStream.readLong();
                }
                return new BloomFilter<>(new LockFreeBitArray(jArr), i, funnel2, bloomFilterStrategies);
            } catch (RuntimeException e2) {
                e = e2;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ");
                sb2.append(b);
                sb2.append(" numHashFunctions: ");
                sb2.append(i);
                sb2.append(" dataLength: ");
                sb2.append(-1);
                throw new IOException(sb2.toString(), e);
            }
        } catch (RuntimeException e3) {
            e = e3;
            b = -1;
            i = -1;
            StringBuilder sb22 = new StringBuilder();
            sb22.append("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ");
            sb22.append(b);
            sb22.append(" numHashFunctions: ");
            sb22.append(i);
            sb22.append(" dataLength: ");
            sb22.append(-1);
            throw new IOException(sb22.toString(), e);
        }
    }
}

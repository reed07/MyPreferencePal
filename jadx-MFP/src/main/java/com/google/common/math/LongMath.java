package com.google.common.math;

import com.google.android.exoplayer2.C;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedLongs;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.math.RoundingMode;

@GwtCompatible(emulated = true)
public final class LongMath {
    @VisibleForTesting
    static final long FLOOR_SQRT_MAX_LONG = 3037000499L;
    @VisibleForTesting
    static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;
    @VisibleForTesting
    static final long MAX_SIGNED_POWER_OF_TWO = 4611686018427387904L;
    private static final int SIEVE_30 = -545925251;
    static final int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, Dialogs.REMOVE_FRIEND_PROGRESS_DIALOG, 361, 265, RequestCodes.CONSENTS, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    @VisibleForTesting
    static final int[] biggestSimpleBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, 419, 287, 214, 169, PacketTypes.CheckFriendsByEmailResponse, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    static final long[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    @GwtIncompatible
    @VisibleForTesting
    static final long[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = {19, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, 17, 17, 17, Ascii.DLE, Ascii.DLE, Ascii.DLE, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.CR, Ascii.CR, Ascii.CR, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.VT, Ascii.VT, Ascii.VT, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
    private static final long[][] millerRabinBaseSets = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Long.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};
    @GwtIncompatible
    @VisibleForTesting
    static final long[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, C.NANOS_PER_SECOND, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};

    /* renamed from: com.google.common.math.LongMath$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                java.math.RoundingMode[] r0 = java.math.RoundingMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$math$RoundingMode = r0
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0014 }
                java.math.RoundingMode r1 = java.math.RoundingMode.UNNECESSARY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x001f }
                java.math.RoundingMode r1 = java.math.RoundingMode.DOWN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x002a }
                java.math.RoundingMode r1 = java.math.RoundingMode.FLOOR     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0035 }
                java.math.RoundingMode r1 = java.math.RoundingMode.UP     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0040 }
                java.math.RoundingMode r1 = java.math.RoundingMode.CEILING     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x004b }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_DOWN     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0056 }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_UP     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0062 }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_EVEN     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.LongMath.AnonymousClass1.<clinit>():void");
        }
    }

    private enum MillerRabinTester {
        SMALL {
            /* access modifiers changed from: 0000 */
            public long mulMod(long j, long j2, long j3) {
                return (j * j2) % j3;
            }

            /* access modifiers changed from: 0000 */
            public long squareMod(long j, long j2) {
                return (j * j) % j2;
            }
        },
        LARGE {
            private long plusMod(long j, long j2, long j3) {
                return j >= j3 - j2 ? (j + j2) - j3 : j + j2;
            }

            private long times2ToThe32Mod(long j, long j2) {
                int i = 32;
                do {
                    int min = Math.min(i, Long.numberOfLeadingZeros(j));
                    j = UnsignedLongs.remainder(j << min, j2);
                    i -= min;
                } while (i > 0);
                return j;
            }

            /* access modifiers changed from: 0000 */
            public long mulMod(long j, long j2, long j3) {
                long j4 = j3;
                long j5 = j >>> 32;
                long j6 = j2 >>> 32;
                long j7 = j & 4294967295L;
                long j8 = j2 & 4294967295L;
                long times2ToThe32Mod = times2ToThe32Mod(j5 * j6, j4) + (j5 * j8);
                if (times2ToThe32Mod < 0) {
                    times2ToThe32Mod = UnsignedLongs.remainder(times2ToThe32Mod, j4);
                }
                Long.signum(j7);
                return plusMod(times2ToThe32Mod(times2ToThe32Mod + (j6 * j7), j4), UnsignedLongs.remainder(j7 * j8, j4), j3);
            }

            /* access modifiers changed from: 0000 */
            public long squareMod(long j, long j2) {
                long j3 = j >>> 32;
                long j4 = j & 4294967295L;
                long times2ToThe32Mod = times2ToThe32Mod(j3 * j3, j2);
                long j5 = j3 * j4 * 2;
                if (j5 < 0) {
                    j5 = UnsignedLongs.remainder(j5, j2);
                }
                return plusMod(times2ToThe32Mod(times2ToThe32Mod + j5, j2), UnsignedLongs.remainder(j4 * j4, j2), j2);
            }
        };

        /* access modifiers changed from: 0000 */
        public abstract long mulMod(long j, long j2, long j3);

        /* access modifiers changed from: 0000 */
        public abstract long squareMod(long j, long j2);

        static boolean test(long j, long j2) {
            return (j2 <= LongMath.FLOOR_SQRT_MAX_LONG ? SMALL : LARGE).testWitness(j, j2);
        }

        private long powMod(long j, long j2, long j3) {
            long j4 = 1;
            while (j2 != 0) {
                if ((j2 & 1) != 0) {
                    j4 = mulMod(j4, j, j3);
                }
                j = squareMod(j, j3);
                j2 >>= 1;
            }
            return j4;
        }

        private boolean testWitness(long j, long j2) {
            long j3 = j2;
            long j4 = j3 - 1;
            int numberOfTrailingZeros = Long.numberOfTrailingZeros(j4);
            long j5 = j4 >> numberOfTrailingZeros;
            long j6 = j % j3;
            if (j6 == 0) {
                return true;
            }
            long powMod = powMod(j6, j5, j2);
            if (powMod == 1) {
                return true;
            }
            int i = 0;
            while (powMod != j4) {
                i++;
                if (i == numberOfTrailingZeros) {
                    return false;
                }
                powMod = squareMod(powMod, j3);
            }
            return true;
        }
    }

    static boolean fitsInInt(long j) {
        return ((long) ((int) j)) == j;
    }

    public static boolean isPowerOfTwo(long j) {
        boolean z = true;
        boolean z2 = j > 0;
        if ((j & (j - 1)) != 0) {
            z = false;
        }
        return z2 & z;
    }

    @VisibleForTesting
    static int lessThanBranchFree(long j, long j2) {
        return (int) ((~(~(j - j2))) >>> 63);
    }

    public static long mean(long j, long j2) {
        return (j & j2) + ((j ^ j2) >> 1);
    }

    @Beta
    public static long saturatedAdd(long j, long j2) {
        long j3 = j + j2;
        boolean z = true;
        boolean z2 = (j2 ^ j) < 0;
        if ((j ^ j3) < 0) {
            z = false;
        }
        return z2 | z ? j3 : ((j3 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    @Beta
    public static long saturatedSubtract(long j, long j2) {
        long j3 = j - j2;
        boolean z = true;
        boolean z2 = (j2 ^ j) >= 0;
        if ((j ^ j3) < 0) {
            z = false;
        }
        return z2 | z ? j3 : ((j3 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    @Beta
    public static long ceilingPowerOfTwo(long j) {
        MathPreconditions.checkPositive(AvidJSONUtil.KEY_X, j);
        if (j <= 4611686018427387904L) {
            return 1 << (-Long.numberOfLeadingZeros(j - 1));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ceilingPowerOfTwo(");
        sb.append(j);
        sb.append(") is not representable as a long");
        throw new ArithmeticException(sb.toString());
    }

    @Beta
    public static long floorPowerOfTwo(long j) {
        MathPreconditions.checkPositive(AvidJSONUtil.KEY_X, j);
        return 1 << (63 - Long.numberOfLeadingZeros(j));
    }

    public static int log2(long j, RoundingMode roundingMode) {
        MathPreconditions.checkPositive(AvidJSONUtil.KEY_X, j);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(j));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(j - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Long.numberOfLeadingZeros(j);
                return (63 - numberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> numberOfLeadingZeros, j);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(j);
    }

    @GwtIncompatible
    public static int log10(long j, RoundingMode roundingMode) {
        MathPreconditions.checkPositive(AvidJSONUtil.KEY_X, j);
        int log10Floor = log10Floor(j);
        long j2 = powersOf10[log10Floor];
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(j == j2);
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return log10Floor + lessThanBranchFree(j2, j);
            case 6:
            case 7:
            case 8:
                return log10Floor + lessThanBranchFree(halfPowersOf10[log10Floor], j);
            default:
                throw new AssertionError();
        }
        return log10Floor;
    }

    @GwtIncompatible
    static int log10Floor(long j) {
        byte b = maxLog10ForLeadingZeros[Long.numberOfLeadingZeros(j)];
        return b - lessThanBranchFree(j, powersOf10[b]);
    }

    @GwtIncompatible
    public static long pow(long j, int i) {
        MathPreconditions.checkNonNegative("exponent", i);
        long j2 = 1;
        if (-2 > j || j > 2) {
            long j3 = j;
            long j4 = 1;
            while (true) {
                switch (i) {
                    case 0:
                        return j4;
                    case 1:
                        return j4 * j3;
                    default:
                        j4 *= (i & 1) == 0 ? 1 : j3;
                        j3 *= j3;
                        i >>= 1;
                }
            }
        } else {
            long j5 = 0;
            switch ((int) j) {
                case -2:
                    if (i >= 64) {
                        return 0;
                    }
                    return (i & 1) == 0 ? 1 << i : -(1 << i);
                case -1:
                    if ((i & 1) != 0) {
                        j2 = -1;
                    }
                    return j2;
                case 0:
                    if (i != 0) {
                        j2 = 0;
                    }
                    return j2;
                case 1:
                    return 1;
                case 2:
                    if (i < 64) {
                        j5 = 1 << i;
                    }
                    return j5;
                default:
                    throw new AssertionError();
            }
        }
    }

    @GwtIncompatible
    public static long sqrt(long j, RoundingMode roundingMode) {
        MathPreconditions.checkNonNegative(AvidJSONUtil.KEY_X, j);
        if (fitsInInt(j)) {
            return (long) IntMath.sqrt((int) j, roundingMode);
        }
        long sqrt = (long) Math.sqrt((double) j);
        long j2 = sqrt * sqrt;
        boolean z = true;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                if (j2 != j) {
                    z = false;
                }
                MathPreconditions.checkRoundingUnnecessary(z);
                return sqrt;
            case 2:
            case 3:
                return j < j2 ? sqrt - 1 : sqrt;
            case 4:
            case 5:
                return j > j2 ? sqrt + 1 : sqrt;
            case 6:
            case 7:
            case 8:
                if (j >= j2) {
                    z = false;
                }
                long j3 = sqrt - (z ? 1 : 0);
                return j3 + ((long) lessThanBranchFree((j3 * j3) + j3, j));
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static long divide(long j, long j2, RoundingMode roundingMode) {
        Preconditions.checkNotNull(roundingMode);
        long j3 = j / j2;
        long j4 = j - (j2 * j3);
        int i = (j4 > 0 ? 1 : (j4 == 0 ? 0 : -1));
        if (i == 0) {
            return j3;
        }
        int i2 = (int) ((j ^ j2) >> 63);
        boolean z = true;
        int i3 = i2 | 1;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                if (i != 0) {
                    z = false;
                }
                MathPreconditions.checkRoundingUnnecessary(z);
                break;
            case 2:
                break;
            case 3:
                if (i3 >= 0) {
                    z = false;
                    break;
                }
                break;
            case 4:
                break;
            case 5:
                if (i3 <= 0) {
                    z = false;
                    break;
                }
                break;
            case 6:
            case 7:
            case 8:
                long abs = Math.abs(j4);
                int i4 = ((abs - (Math.abs(j2) - abs)) > 0 ? 1 : ((abs - (Math.abs(j2) - abs)) == 0 ? 0 : -1));
                if (i4 != 0) {
                    if (i4 <= 0) {
                        z = false;
                        break;
                    }
                } else {
                    boolean z2 = roundingMode == RoundingMode.HALF_UP;
                    boolean z3 = roundingMode == RoundingMode.HALF_EVEN;
                    if ((1 & j3) == 0) {
                        z = false;
                    }
                    z = (z & z3) | z2;
                    break;
                }
                break;
            default:
                throw new AssertionError();
        }
        z = false;
        if (z) {
            j3 += (long) i3;
        }
        return j3;
    }

    @GwtIncompatible
    public static int mod(long j, int i) {
        return (int) mod(j, (long) i);
    }

    @GwtIncompatible
    public static long mod(long j, long j2) {
        if (j2 > 0) {
            long j3 = j % j2;
            return j3 >= 0 ? j3 : j3 + j2;
        }
        throw new ArithmeticException("Modulus must be positive");
    }

    public static long gcd(long j, long j2) {
        MathPreconditions.checkNonNegative("a", j);
        MathPreconditions.checkNonNegative("b", j2);
        if (j == 0) {
            return j2;
        }
        if (j2 == 0) {
            return j;
        }
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j);
        long j3 = j >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j2);
        long j4 = j2 >> numberOfTrailingZeros2;
        while (j3 != j4) {
            long j5 = j3 - j4;
            long j6 = (j5 >> 63) & j5;
            long j7 = (j5 - j6) - j6;
            j4 += j6;
            j3 = j7 >> Long.numberOfTrailingZeros(j7);
        }
        return j3 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    @GwtIncompatible
    public static long checkedAdd(long j, long j2) {
        long j3 = j + j2;
        boolean z = true;
        boolean z2 = (j ^ j2) < 0;
        if ((j ^ j3) < 0) {
            z = false;
        }
        MathPreconditions.checkNoOverflow(z2 | z, "checkedAdd", j, j2);
        return j3;
    }

    @GwtIncompatible
    public static long checkedSubtract(long j, long j2) {
        long j3 = j - j2;
        boolean z = true;
        boolean z2 = (j ^ j2) >= 0;
        if ((j ^ j3) < 0) {
            z = false;
        }
        MathPreconditions.checkNoOverflow(z2 | z, "checkedSubtract", j, j2);
        return j3;
    }

    public static long checkedMultiply(long j, long j2) {
        long j3 = j;
        long j4 = j2;
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(~j3) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j4);
        if (numberOfLeadingZeros > 65) {
            return j3 * j4;
        }
        MathPreconditions.checkNoOverflow(numberOfLeadingZeros >= 64, "checkedMultiply", j, j2);
        int i = (j3 > 0 ? 1 : (j3 == 0 ? 0 : -1));
        MathPreconditions.checkNoOverflow((i >= 0) | (j4 != Long.MIN_VALUE), "checkedMultiply", j, j2);
        long j5 = j3 * j4;
        MathPreconditions.checkNoOverflow(i == 0 || j5 / j3 == j4, "checkedMultiply", j, j2);
        return j5;
    }

    @GwtIncompatible
    public static long checkedPow(long j, int i) {
        MathPreconditions.checkNonNegative("exponent", i);
        long j2 = 1;
        if (!(j >= -2) || !(j <= 2)) {
            while (true) {
                switch (i) {
                    case 0:
                        return j2;
                    case 1:
                        return checkedMultiply(j2, j);
                    default:
                        if ((i & 1) != 0) {
                            j2 = checkedMultiply(j2, j);
                        }
                        i >>= 1;
                        if (i > 0) {
                            MathPreconditions.checkNoOverflow(-3037000499L <= j && j <= FLOOR_SQRT_MAX_LONG, "checkedPow", j, (long) i);
                            j *= j;
                        }
                        break;
                }
            }
        } else {
            switch ((int) j) {
                case -2:
                    MathPreconditions.checkNoOverflow(i < 64, "checkedPow", j, (long) i);
                    return (i & 1) == 0 ? 1 << i : -1 << i;
                case -1:
                    if ((i & 1) != 0) {
                        j2 = -1;
                    }
                    return j2;
                case 0:
                    if (i != 0) {
                        j2 = 0;
                    }
                    return j2;
                case 1:
                    return 1;
                case 2:
                    MathPreconditions.checkNoOverflow(i < 63, "checkedPow", j, (long) i);
                    return 1 << i;
                default:
                    throw new AssertionError();
            }
        }
    }

    @Beta
    public static long saturatedMultiply(long j, long j2) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j2);
        if (numberOfLeadingZeros > 65) {
            return j * j2;
        }
        long j3 = ((j ^ j2) >>> 63) + Long.MAX_VALUE;
        boolean z = true;
        boolean z2 = numberOfLeadingZeros < 64;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        boolean z3 = i < 0;
        if (j2 != Long.MIN_VALUE) {
            z = false;
        }
        if (z2 || (z & z3)) {
            return j3;
        }
        long j4 = j * j2;
        return (i == 0 || j4 / j == j2) ? j4 : j3;
    }

    @Beta
    public static long saturatedPow(long j, int i) {
        MathPreconditions.checkNonNegative("exponent", i);
        long j2 = 1;
        if ((j >= -2) && (j <= 2)) {
            switch ((int) j) {
                case -2:
                    if (i >= 64) {
                        return ((long) (i & 1)) + Long.MAX_VALUE;
                    }
                    return (i & 1) == 0 ? 1 << i : -1 << i;
                case -1:
                    if ((i & 1) != 0) {
                        j2 = -1;
                    }
                    return j2;
                case 0:
                    if (i != 0) {
                        j2 = 0;
                    }
                    return j2;
                case 1:
                    return 1;
                case 2:
                    if (i >= 63) {
                        return Long.MAX_VALUE;
                    }
                    return 1 << i;
                default:
                    throw new AssertionError();
            }
        } else {
            long j3 = ((j >>> 63) & ((long) (i & 1))) + Long.MAX_VALUE;
            while (true) {
                switch (i) {
                    case 0:
                        return j2;
                    case 1:
                        return saturatedMultiply(j2, j);
                    default:
                        if ((i & 1) != 0) {
                            j2 = saturatedMultiply(j2, j);
                        }
                        i >>= 1;
                        if (i > 0) {
                            if ((-3037000499L > j) || (j > FLOOR_SQRT_MAX_LONG)) {
                                return j3;
                            }
                            j *= j;
                        }
                }
            }
        }
    }

    @GwtIncompatible
    public static long factorial(int i) {
        MathPreconditions.checkNonNegative("n", i);
        long[] jArr = factorials;
        if (i < jArr.length) {
            return jArr[i];
        }
        return Long.MAX_VALUE;
    }

    public static long binomial(int i, int i2) {
        MathPreconditions.checkNonNegative("n", i);
        MathPreconditions.checkNonNegative("k", i2);
        Preconditions.checkArgument(i2 <= i, "k (%s) > n (%s)", i2, i);
        if (i2 > (i >> 1)) {
            i2 = i - i2;
        }
        switch (i2) {
            case 0:
                return 1;
            case 1:
                return (long) i;
            default:
                long[] jArr = factorials;
                if (i < jArr.length) {
                    return jArr[i] / (jArr[i2] * jArr[i - i2]);
                }
                int[] iArr = biggestBinomials;
                if (i2 >= iArr.length || i > iArr[i2]) {
                    return Long.MAX_VALUE;
                }
                int[] iArr2 = biggestSimpleBinomials;
                int i3 = 2;
                if (i2 >= iArr2.length || i > iArr2[i2]) {
                    long j = (long) i;
                    int log2 = log2(j, RoundingMode.CEILING);
                    long j2 = 1;
                    long j3 = 1;
                    long j4 = j;
                    int i4 = i - 1;
                    int i5 = log2;
                    while (i3 <= i2) {
                        i5 += log2;
                        if (i5 < 63) {
                            j4 *= (long) i4;
                            j3 *= (long) i3;
                        } else {
                            long multiplyFraction = multiplyFraction(j2, j4, j3);
                            i5 = log2;
                            j3 = (long) i3;
                            j4 = (long) i4;
                            j2 = multiplyFraction;
                        }
                        i3++;
                        i4--;
                    }
                    return multiplyFraction(j2, j4, j3);
                }
                int i6 = i - 1;
                long j5 = (long) i;
                while (i3 <= i2) {
                    j5 = (j5 * ((long) i6)) / ((long) i3);
                    i6--;
                    i3++;
                }
                return j5;
        }
    }

    static long multiplyFraction(long j, long j2, long j3) {
        if (j == 1) {
            return j2 / j3;
        }
        long gcd = gcd(j, j3);
        return (j / gcd) * (j2 / (j3 / gcd));
    }

    @GwtIncompatible
    @Beta
    public static boolean isPrime(long j) {
        long[][] jArr;
        int i = (j > 2 ? 1 : (j == 2 ? 0 : -1));
        if (i < 0) {
            MathPreconditions.checkNonNegative("n", j);
            return false;
        } else if (i == 0 || j == 3 || j == 5 || j == 7 || j == 11 || j == 13) {
            return true;
        } else {
            if ((SIEVE_30 & (1 << ((int) (j % 30)))) != 0 || j % 7 == 0 || j % 11 == 0 || j % 13 == 0) {
                return false;
            }
            if (j < 289) {
                return true;
            }
            for (long[] jArr2 : millerRabinBaseSets) {
                if (j <= jArr2[0]) {
                    for (int i2 = 1; i2 < jArr2.length; i2++) {
                        if (!MillerRabinTester.test(jArr2[i2], j)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            throw new AssertionError();
        }
    }

    private LongMath() {
    }
}

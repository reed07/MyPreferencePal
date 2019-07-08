package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.math.RoundingMode;

@GwtCompatible(emulated = true)
public final class IntMath {
    @VisibleForTesting
    static final int FLOOR_SQRT_MAX_INT = 46340;
    @VisibleForTesting
    static final int MAX_POWER_OF_SQRT2_UNSIGNED = -1257966797;
    @VisibleForTesting
    static final int MAX_SIGNED_POWER_OF_TWO = 1073741824;
    @VisibleForTesting
    static int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, 2345, 477, RequestCodes.CONFIRM_PHOTO_IMPORT, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};
    private static final int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
    @VisibleForTesting
    static final int[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = {9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
    @VisibleForTesting
    static final int[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    /* renamed from: com.google.common.math.IntMath$1 reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.IntMath.AnonymousClass1.<clinit>():void");
        }
    }

    public static boolean isPowerOfTwo(int i) {
        boolean z = false;
        boolean z2 = i > 0;
        if ((i & (i - 1)) == 0) {
            z = true;
        }
        return z2 & z;
    }

    @VisibleForTesting
    static int lessThanBranchFree(int i, int i2) {
        return (~(~(i - i2))) >>> 31;
    }

    public static int mean(int i, int i2) {
        return (i & i2) + ((i ^ i2) >> 1);
    }

    @Beta
    public static int ceilingPowerOfTwo(int i) {
        MathPreconditions.checkPositive(AvidJSONUtil.KEY_X, i);
        if (i <= 1073741824) {
            return 1 << (-Integer.numberOfLeadingZeros(i - 1));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ceilingPowerOfTwo(");
        sb.append(i);
        sb.append(") not representable as an int");
        throw new ArithmeticException(sb.toString());
    }

    @Beta
    public static int floorPowerOfTwo(int i) {
        MathPreconditions.checkPositive(AvidJSONUtil.KEY_X, i);
        return Integer.highestOneBit(i);
    }

    public static int log2(int i, RoundingMode roundingMode) {
        MathPreconditions.checkPositive(AvidJSONUtil.KEY_X, i);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(i));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(i - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i);
                return (31 - numberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> numberOfLeadingZeros, i);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(i);
    }

    @GwtIncompatible
    public static int log10(int i, RoundingMode roundingMode) {
        MathPreconditions.checkPositive(AvidJSONUtil.KEY_X, i);
        int log10Floor = log10Floor(i);
        int i2 = powersOf10[log10Floor];
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(i == i2);
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return log10Floor + lessThanBranchFree(i2, i);
            case 6:
            case 7:
            case 8:
                return log10Floor + lessThanBranchFree(halfPowersOf10[log10Floor], i);
            default:
                throw new AssertionError();
        }
        return log10Floor;
    }

    private static int log10Floor(int i) {
        byte b = maxLog10ForLeadingZeros[Integer.numberOfLeadingZeros(i)];
        return b - lessThanBranchFree(i, powersOf10[b]);
    }

    @GwtIncompatible
    public static int pow(int i, int i2) {
        MathPreconditions.checkNonNegative("exponent", i2);
        int i3 = 0;
        int i4 = 1;
        switch (i) {
            case -2:
                if (i2 >= 32) {
                    return 0;
                }
                return (i2 & 1) == 0 ? 1 << i2 : -(1 << i2);
            case -1:
                if ((i2 & 1) != 0) {
                    i4 = -1;
                }
                return i4;
            case 0:
                if (i2 == 0) {
                    i3 = 1;
                }
                return i3;
            case 1:
                return 1;
            case 2:
                if (i2 < 32) {
                    i3 = 1 << i2;
                }
                return i3;
            default:
                int i5 = i;
                int i6 = 1;
                while (true) {
                    switch (i2) {
                        case 0:
                            return i6;
                        case 1:
                            return i5 * i6;
                        default:
                            i6 *= (i2 & 1) == 0 ? 1 : i5;
                            i5 *= i5;
                            i2 >>= 1;
                    }
                }
        }
    }

    @GwtIncompatible
    public static int sqrt(int i, RoundingMode roundingMode) {
        MathPreconditions.checkNonNegative(AvidJSONUtil.KEY_X, i);
        int sqrtFloor = sqrtFloor(i);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(sqrtFloor * sqrtFloor == i);
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return sqrtFloor + lessThanBranchFree(sqrtFloor * sqrtFloor, i);
            case 6:
            case 7:
            case 8:
                return sqrtFloor + lessThanBranchFree((sqrtFloor * sqrtFloor) + sqrtFloor, i);
            default:
                throw new AssertionError();
        }
        return sqrtFloor;
    }

    private static int sqrtFloor(int i) {
        return (int) Math.sqrt((double) i);
    }

    public static int divide(int i, int i2, RoundingMode roundingMode) {
        Preconditions.checkNotNull(roundingMode);
        if (i2 != 0) {
            int i3 = i / i2;
            int i4 = i - (i2 * i3);
            if (i4 == 0) {
                return i3;
            }
            boolean z = true;
            int i5 = ((i ^ i2) >> 31) | 1;
            switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
                case 1:
                    if (i4 != 0) {
                        z = false;
                    }
                    MathPreconditions.checkRoundingUnnecessary(z);
                    break;
                case 2:
                    break;
                case 3:
                    if (i5 >= 0) {
                        z = false;
                        break;
                    }
                    break;
                case 4:
                    break;
                case 5:
                    if (i5 <= 0) {
                        z = false;
                        break;
                    }
                    break;
                case 6:
                case 7:
                case 8:
                    int abs = Math.abs(i4);
                    int abs2 = abs - (Math.abs(i2) - abs);
                    if (abs2 != 0) {
                        if (abs2 <= 0) {
                            z = false;
                            break;
                        }
                    } else if (roundingMode != RoundingMode.HALF_UP) {
                        if (!(roundingMode == RoundingMode.HALF_EVEN) || !((i3 & 1) != 0)) {
                            z = false;
                            break;
                        }
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            z = false;
            if (z) {
                i3 += i5;
            }
            return i3;
        }
        throw new ArithmeticException("/ by zero");
    }

    public static int mod(int i, int i2) {
        if (i2 > 0) {
            int i3 = i % i2;
            return i3 >= 0 ? i3 : i3 + i2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Modulus ");
        sb.append(i2);
        sb.append(" must be > 0");
        throw new ArithmeticException(sb.toString());
    }

    public static int gcd(int i, int i2) {
        MathPreconditions.checkNonNegative("a", i);
        MathPreconditions.checkNonNegative("b", i2);
        if (i == 0) {
            return i2;
        }
        if (i2 == 0) {
            return i;
        }
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
        int i3 = i >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Integer.numberOfTrailingZeros(i2);
        int i4 = i2 >> numberOfTrailingZeros2;
        while (i3 != i4) {
            int i5 = i3 - i4;
            int i6 = (i5 >> 31) & i5;
            int i7 = (i5 - i6) - i6;
            i4 += i6;
            i3 = i7 >> Integer.numberOfTrailingZeros(i7);
        }
        return i3 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static int checkedAdd(int i, int i2) {
        long j = ((long) i) + ((long) i2);
        int i3 = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) i3), "checkedAdd", i, i2);
        return i3;
    }

    public static int checkedSubtract(int i, int i2) {
        long j = ((long) i) - ((long) i2);
        int i3 = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) i3), "checkedSubtract", i, i2);
        return i3;
    }

    public static int checkedMultiply(int i, int i2) {
        long j = ((long) i) * ((long) i2);
        int i3 = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) i3), "checkedMultiply", i, i2);
        return i3;
    }

    public static int checkedPow(int i, int i2) {
        MathPreconditions.checkNonNegative("exponent", i2);
        int i3 = -1;
        boolean z = false;
        switch (i) {
            case -2:
                if (i2 < 32) {
                    z = true;
                }
                MathPreconditions.checkNoOverflow(z, "checkedPow", i, i2);
                return (i2 & 1) == 0 ? 1 << i2 : -1 << i2;
            case -1:
                if ((i2 & 1) == 0) {
                    i3 = 1;
                }
                return i3;
            case 0:
                if (i2 == 0) {
                    z = true;
                }
                return z ? 1 : 0;
            case 1:
                return 1;
            case 2:
                if (i2 < 31) {
                    z = true;
                }
                MathPreconditions.checkNoOverflow(z, "checkedPow", i, i2);
                return 1 << i2;
            default:
                int i4 = i;
                int i5 = 1;
                while (true) {
                    switch (i2) {
                        case 0:
                            return i5;
                        case 1:
                            return checkedMultiply(i5, i4);
                        default:
                            if ((i2 & 1) != 0) {
                                i5 = checkedMultiply(i5, i4);
                            }
                            i2 >>= 1;
                            if (i2 > 0) {
                                MathPreconditions.checkNoOverflow((-46340 <= i4) & (i4 <= FLOOR_SQRT_MAX_INT), "checkedPow", i4, i2);
                                i4 *= i4;
                            }
                    }
                }
        }
    }

    @Beta
    public static int saturatedAdd(int i, int i2) {
        return Ints.saturatedCast(((long) i) + ((long) i2));
    }

    @Beta
    public static int saturatedSubtract(int i, int i2) {
        return Ints.saturatedCast(((long) i) - ((long) i2));
    }

    @Beta
    public static int saturatedMultiply(int i, int i2) {
        return Ints.saturatedCast(((long) i) * ((long) i2));
    }

    @Beta
    public static int saturatedPow(int i, int i2) {
        MathPreconditions.checkNonNegative("exponent", i2);
        int i3 = -1;
        int i4 = 0;
        switch (i) {
            case -2:
                if (i2 >= 32) {
                    return (i2 & 1) + Integer.MAX_VALUE;
                }
                return (i2 & 1) == 0 ? 1 << i2 : -1 << i2;
            case -1:
                if ((i2 & 1) == 0) {
                    i3 = 1;
                }
                return i3;
            case 0:
                if (i2 == 0) {
                    i4 = 1;
                }
                return i4;
            case 1:
                return 1;
            case 2:
                if (i2 >= 31) {
                    return Integer.MAX_VALUE;
                }
                return 1 << i2;
            default:
                int i5 = ((i >>> 31) & i2 & 1) + Integer.MAX_VALUE;
                int i6 = i;
                int i7 = 1;
                while (true) {
                    switch (i2) {
                        case 0:
                            return i7;
                        case 1:
                            return saturatedMultiply(i7, i6);
                        default:
                            if ((i2 & 1) != 0) {
                                i7 = saturatedMultiply(i7, i6);
                            }
                            i2 >>= 1;
                            if (i2 > 0) {
                                if ((-46340 > i6) || (i6 > FLOOR_SQRT_MAX_INT)) {
                                    return i5;
                                }
                                i6 *= i6;
                            }
                    }
                }
        }
    }

    public static int factorial(int i) {
        MathPreconditions.checkNonNegative("n", i);
        int[] iArr = factorials;
        if (i < iArr.length) {
            return iArr[i];
        }
        return Integer.MAX_VALUE;
    }

    public static int binomial(int i, int i2) {
        MathPreconditions.checkNonNegative("n", i);
        MathPreconditions.checkNonNegative("k", i2);
        int i3 = 0;
        Preconditions.checkArgument(i2 <= i, "k (%s) > n (%s)", i2, i);
        if (i2 > (i >> 1)) {
            i2 = i - i2;
        }
        int[] iArr = biggestBinomials;
        if (i2 >= iArr.length || i > iArr[i2]) {
            return Integer.MAX_VALUE;
        }
        switch (i2) {
            case 0:
                return 1;
            case 1:
                return i;
            default:
                long j = 1;
                while (i3 < i2) {
                    i3++;
                    j = (j * ((long) (i - i3))) / ((long) i3);
                }
                return (int) j;
        }
    }

    @GwtIncompatible
    @Beta
    public static boolean isPrime(int i) {
        return LongMath.isPrime((long) i);
    }

    private IntMath() {
    }
}

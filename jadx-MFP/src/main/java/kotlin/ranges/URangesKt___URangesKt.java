package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandomKt;
import kotlin.ranges.UIntProgression.Companion;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\n\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\nø\u0001\u0000¢\u0006\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0007H\nø\u0001\u0000¢\u0006\u0002\b\b\u001a\u001f\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0004ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u001f\u0010\t\u001a\u00020\n*\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0004ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001f\u0010\t\u001a\u00020\u0011*\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0004ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001f\u0010\t\u001a\u00020\n*\u00020\u00142\u0006\u0010\f\u001a\u00020\u0014H\u0004ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0015\u0010\u0017\u001a\u00020\u0004*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a\u001c\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0015\u0010\u0017\u001a\u00020\u0007*\u00020\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a\u001c\u0010\u0017\u001a\u00020\u0007*\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\f\u0010\u001d\u001a\u00020\n*\u00020\nH\u0007\u001a\f\u0010\u001d\u001a\u00020\u0011*\u00020\u0011H\u0007\u001a\u0015\u0010\u001e\u001a\u00020\n*\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001fH\u0004\u001a\u0015\u0010\u001e\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u001e\u001a\u00020 H\u0004\u001a\u001f\u0010!\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0004ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a\u001f\u0010!\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0004ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a\u001f\u0010!\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0004ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a\u001f\u0010!\u001a\u00020\u0002*\u00020\u00142\u0006\u0010\f\u001a\u00020\u0014H\u0004ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"contains", "", "Lkotlin/ranges/UIntRange;", "element", "Lkotlin/UInt;", "contains-biwQdVI", "Lkotlin/ranges/ULongRange;", "Lkotlin/ULong;", "contains-GYNo2lE", "downTo", "Lkotlin/ranges/UIntProgression;", "Lkotlin/UByte;", "to", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "Lkotlin/UShort;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;)J", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "reversed", "step", "", "", "until", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/ranges/URangesKt")
/* compiled from: _URanges.kt */
class URangesKt___URangesKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int random(@NotNull UIntRange uIntRange) {
        return URangesKt.random(uIntRange, (Random) Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long random(@NotNull ULongRange uLongRange) {
        return URangesKt.random(uLongRange, (Random) Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int random(@NotNull UIntRange uIntRange, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        try {
            return URandomKt.nextUInt(random, uIntRange);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long random(@NotNull ULongRange uLongRange, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        try {
            return URandomKt.nextULong(random, uLongRange);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: contains-biwQdVI reason: not valid java name */
    private static final boolean m359containsbiwQdVI(@NotNull UIntRange uIntRange, UInt uInt) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "receiver$0");
        return uInt != null && uIntRange.m355containsWZ4Q5Ns(uInt.m130unboximpl());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: contains-GYNo2lE reason: not valid java name */
    private static final boolean m358containsGYNo2lE(@NotNull ULongRange uLongRange, ULong uLong) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "receiver$0");
        return uLong != null && uLongRange.m357containsVKZWuLQ(uLong.m197unboximpl());
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-Kr8caGY reason: not valid java name */
    public static final UIntProgression m362downToKr8caGY(byte b, byte b2) {
        return UIntProgression.Companion.m354fromClosedRangeNkh28Cs(UInt.m89constructorimpl(b & 255), UInt.m89constructorimpl(b2 & 255), -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-J1ME1BU reason: not valid java name */
    public static final UIntProgression m361downToJ1ME1BU(int i, int i2) {
        return UIntProgression.Companion.m354fromClosedRangeNkh28Cs(i, i2, -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-eb3DHEI reason: not valid java name */
    public static final ULongProgression m363downToeb3DHEI(long j, long j2) {
        return ULongProgression.Companion.m356fromClosedRange7ftBX0g(j, j2, -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-5PvTz6A reason: not valid java name */
    public static final UIntProgression m360downTo5PvTz6A(short s, short s2) {
        return UIntProgression.Companion.m354fromClosedRangeNkh28Cs(UInt.m89constructorimpl(s & UShort.MAX_VALUE), UInt.m89constructorimpl(s2 & UShort.MAX_VALUE), -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression reversed(@NotNull UIntProgression uIntProgression) {
        Intrinsics.checkParameterIsNotNull(uIntProgression, "receiver$0");
        return UIntProgression.Companion.m354fromClosedRangeNkh28Cs(uIntProgression.getLast(), uIntProgression.getFirst(), -uIntProgression.getStep());
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULongProgression reversed(@NotNull ULongProgression uLongProgression) {
        Intrinsics.checkParameterIsNotNull(uLongProgression, "receiver$0");
        return ULongProgression.Companion.m356fromClosedRange7ftBX0g(uLongProgression.getLast(), uLongProgression.getFirst(), -uLongProgression.getStep());
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression step(@NotNull UIntProgression uIntProgression, int i) {
        Intrinsics.checkParameterIsNotNull(uIntProgression, "receiver$0");
        RangesKt.checkStepIsPositive(i > 0, Integer.valueOf(i));
        Companion companion = UIntProgression.Companion;
        int first = uIntProgression.getFirst();
        int last = uIntProgression.getLast();
        if (uIntProgression.getStep() <= 0) {
            i = -i;
        }
        return companion.m354fromClosedRangeNkh28Cs(first, last, i);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULongProgression step(@NotNull ULongProgression uLongProgression, long j) {
        Intrinsics.checkParameterIsNotNull(uLongProgression, "receiver$0");
        RangesKt.checkStepIsPositive(j > 0, Long.valueOf(j));
        ULongProgression.Companion companion = ULongProgression.Companion;
        long first = uLongProgression.getFirst();
        long last = uLongProgression.getLast();
        if (uLongProgression.getStep() <= 0) {
            j = -j;
        }
        return companion.m356fromClosedRange7ftBX0g(first, last, j);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-Kr8caGY reason: not valid java name */
    public static final UIntRange m366untilKr8caGY(byte b, byte b2) {
        return new UIntRange(UInt.m89constructorimpl(b & 255), UInt.m89constructorimpl(UInt.m89constructorimpl(b2 & 255) - 1), null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-J1ME1BU reason: not valid java name */
    public static final UIntRange m365untilJ1ME1BU(int i, int i2) {
        if (UnsignedKt.uintCompare(i2, 0) <= 0) {
            return UIntRange.Companion.getEMPTY();
        }
        return new UIntRange(i, UInt.m89constructorimpl(i2 - 1), null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-eb3DHEI reason: not valid java name */
    public static final ULongRange m367untileb3DHEI(long j, long j2) {
        if (UnsignedKt.ulongCompare(j2, 0) <= 0) {
            return ULongRange.Companion.getEMPTY();
        }
        ULongRange uLongRange = new ULongRange(j, ULong.m156constructorimpl(j2 - ULong.m156constructorimpl(((long) 1) & 4294967295L)), null);
        return uLongRange;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-5PvTz6A reason: not valid java name */
    public static final UIntRange m364until5PvTz6A(short s, short s2) {
        return new UIntRange(UInt.m89constructorimpl(s & UShort.MAX_VALUE), UInt.m89constructorimpl(UInt.m89constructorimpl(s2 & UShort.MAX_VALUE) - 1), null);
    }
}

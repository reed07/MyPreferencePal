package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 ^2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001^B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\rHÖ\u0001J\u0013\u0010%\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u0013\u0010'\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u000fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0012J\u001b\u0010)\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0012J\u001b\u00100\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u000fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0012J\u001b\u00109\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0012J\u001b\u0010>\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020\u0003H\b¢\u0006\u0004\bD\u0010\u0005J\u0010\u0010E\u001a\u00020\rH\b¢\u0006\u0004\bF\u0010GJ\u0010\u0010H\u001a\u00020IH\b¢\u0006\u0004\bJ\u0010KJ\u0010\u0010L\u001a\u00020MH\b¢\u0006\u0004\bN\u0010OJ\u000f\u0010P\u001a\u00020QH\u0016¢\u0006\u0004\bR\u0010SJ\u0013\u0010T\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\bU\u0010\u0005J\u0013\u0010V\u001a\u00020\u0010H\bø\u0001\u0000¢\u0006\u0004\bW\u0010GJ\u0013\u0010X\u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\bY\u0010KJ\u0013\u0010Z\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\b[\u0010OJ\u001b\u0010\\\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b]\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006_"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "data$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: UByte.kt */
public final class UByte implements Comparable<UByte> {
    public static final Companion Companion = new Companion(null);
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: UByte.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    /* renamed from: box-impl reason: not valid java name */
    public static final /* synthetic */ UByte m18boximpl(byte b) {
        return new UByte(b);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU reason: not valid java name */
    private int m19compareTo7apg3OU(byte b) {
        return m20compareTo7apg3OU(this.data, b);
    }

    @PublishedApi
    /* renamed from: constructor-impl reason: not valid java name */
    public static byte m24constructorimpl(byte b) {
        return b;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl reason: not valid java name */
    public static boolean m30equalsimpl(byte b, @Nullable Object obj) {
        if (obj instanceof UByte) {
            if (b == ((UByte) obj).m63unboximpl()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: equals-impl0 reason: not valid java name */
    public static final boolean m31equalsimpl0(byte b, byte b2) {
        throw null;
    }

    /* renamed from: hashCode-impl reason: not valid java name */
    public static int m32hashCodeimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* renamed from: toByte-impl reason: not valid java name */
    private static final byte m53toByteimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* renamed from: toInt-impl reason: not valid java name */
    private static final int m54toIntimpl(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* renamed from: toLong-impl reason: not valid java name */
    private static final long m55toLongimpl(byte b) {
        return ((long) b) & 255;
    }

    @InlineOnly
    /* renamed from: toShort-impl reason: not valid java name */
    private static final short m56toShortimpl(byte b) {
        return (short) (((short) b) & 255);
    }

    @InlineOnly
    /* renamed from: toUByte-impl reason: not valid java name */
    private static final byte m58toUByteimpl(byte b) {
        return b;
    }

    public boolean equals(Object obj) {
        return m30equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m32hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m57toStringimpl(this.data);
    }

    /* renamed from: unbox-impl reason: not valid java name */
    public final /* synthetic */ byte m63unboximpl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m19compareTo7apg3OU(((UByte) obj).m63unboximpl());
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU reason: not valid java name */
    private static int m20compareTo7apg3OU(byte b, byte b2) {
        return Intrinsics.compare((int) b & 255, (int) b2 & 255);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw reason: not valid java name */
    private static final int m23compareToxj2QHRw(byte b, short s) {
        return Intrinsics.compare((int) b & 255, (int) s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns reason: not valid java name */
    private static final int m22compareToWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.uintCompare(UInt.m89constructorimpl(b & 255), i);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ reason: not valid java name */
    private static final int m21compareToVKZWuLQ(byte b, long j) {
        return UnsignedKt.ulongCompare(ULong.m156constructorimpl(((long) b) & 255), j);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU reason: not valid java name */
    private static final int m40plus7apg3OU(byte b, byte b2) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(b & 255) + UInt.m89constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw reason: not valid java name */
    private static final int m43plusxj2QHRw(byte b, short s) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(b & 255) + UInt.m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns reason: not valid java name */
    private static final int m42plusWZ4Q5Ns(byte b, int i) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(b & 255) + i);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ reason: not valid java name */
    private static final long m41plusVKZWuLQ(byte b, long j) {
        return ULong.m156constructorimpl(ULong.m156constructorimpl(((long) b) & 255) + j);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU reason: not valid java name */
    private static final int m35minus7apg3OU(byte b, byte b2) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(b & 255) - UInt.m89constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw reason: not valid java name */
    private static final int m38minusxj2QHRw(byte b, short s) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(b & 255) - UInt.m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns reason: not valid java name */
    private static final int m37minusWZ4Q5Ns(byte b, int i) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(b & 255) - i);
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ reason: not valid java name */
    private static final long m36minusVKZWuLQ(byte b, long j) {
        return ULong.m156constructorimpl(ULong.m156constructorimpl(((long) b) & 255) - j);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU reason: not valid java name */
    private static final int m49times7apg3OU(byte b, byte b2) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(b & 255) * UInt.m89constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw reason: not valid java name */
    private static final int m52timesxj2QHRw(byte b, short s) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(b & 255) * UInt.m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns reason: not valid java name */
    private static final int m51timesWZ4Q5Ns(byte b, int i) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(b & 255) * i);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ reason: not valid java name */
    private static final long m50timesVKZWuLQ(byte b, long j) {
        return ULong.m156constructorimpl(ULong.m156constructorimpl(((long) b) & 255) * j);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU reason: not valid java name */
    private static final int m26div7apg3OU(byte b, byte b2) {
        return UnsignedKt.m281uintDivideJ1ME1BU(UInt.m89constructorimpl(b & 255), UInt.m89constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw reason: not valid java name */
    private static final int m29divxj2QHRw(byte b, short s) {
        return UnsignedKt.m281uintDivideJ1ME1BU(UInt.m89constructorimpl(b & 255), UInt.m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns reason: not valid java name */
    private static final int m28divWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m281uintDivideJ1ME1BU(UInt.m89constructorimpl(b & 255), i);
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ reason: not valid java name */
    private static final long m27divVKZWuLQ(byte b, long j) {
        return UnsignedKt.m283ulongDivideeb3DHEI(ULong.m156constructorimpl(((long) b) & 255), j);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU reason: not valid java name */
    private static final int m45rem7apg3OU(byte b, byte b2) {
        return UnsignedKt.m282uintRemainderJ1ME1BU(UInt.m89constructorimpl(b & 255), UInt.m89constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw reason: not valid java name */
    private static final int m48remxj2QHRw(byte b, short s) {
        return UnsignedKt.m282uintRemainderJ1ME1BU(UInt.m89constructorimpl(b & 255), UInt.m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns reason: not valid java name */
    private static final int m47remWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m282uintRemainderJ1ME1BU(UInt.m89constructorimpl(b & 255), i);
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ reason: not valid java name */
    private static final long m46remVKZWuLQ(byte b, long j) {
        return UnsignedKt.m284ulongRemaindereb3DHEI(ULong.m156constructorimpl(((long) b) & 255), j);
    }

    @InlineOnly
    /* renamed from: inc-impl reason: not valid java name */
    private static final byte m33incimpl(byte b) {
        return m24constructorimpl((byte) (b + 1));
    }

    @InlineOnly
    /* renamed from: dec-impl reason: not valid java name */
    private static final byte m25decimpl(byte b) {
        return m24constructorimpl((byte) (b - 1));
    }

    @InlineOnly
    /* renamed from: rangeTo-7apg3OU reason: not valid java name */
    private static final UIntRange m44rangeTo7apg3OU(byte b, byte b2) {
        return new UIntRange(UInt.m89constructorimpl(b & 255), UInt.m89constructorimpl(b2 & 255), null);
    }

    @InlineOnly
    /* renamed from: and-7apg3OU reason: not valid java name */
    private static final byte m17and7apg3OU(byte b, byte b2) {
        return m24constructorimpl((byte) (b & b2));
    }

    @InlineOnly
    /* renamed from: or-7apg3OU reason: not valid java name */
    private static final byte m39or7apg3OU(byte b, byte b2) {
        return m24constructorimpl((byte) (b | b2));
    }

    @InlineOnly
    /* renamed from: xor-7apg3OU reason: not valid java name */
    private static final byte m62xor7apg3OU(byte b, byte b2) {
        return m24constructorimpl((byte) (b ^ b2));
    }

    @InlineOnly
    /* renamed from: inv-impl reason: not valid java name */
    private static final byte m34invimpl(byte b) {
        return m24constructorimpl((byte) (~b));
    }

    @InlineOnly
    /* renamed from: toUShort-impl reason: not valid java name */
    private static final short m61toUShortimpl(byte b) {
        return UShort.m223constructorimpl((short) (((short) b) & 255));
    }

    @InlineOnly
    /* renamed from: toUInt-impl reason: not valid java name */
    private static final int m59toUIntimpl(byte b) {
        return UInt.m89constructorimpl(b & 255);
    }

    @InlineOnly
    /* renamed from: toULong-impl reason: not valid java name */
    private static final long m60toULongimpl(byte b) {
        return ULong.m156constructorimpl(((long) b) & 255);
    }

    @NotNull
    /* renamed from: toString-impl reason: not valid java name */
    public static String m57toStringimpl(byte b) {
        return String.valueOf(b & 255);
    }
}

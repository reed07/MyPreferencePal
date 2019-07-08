package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 ^2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001^B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0010J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0013J\u001b\u0010\u001b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\rHÖ\u0001J\u0013\u0010%\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u0013\u0010'\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u0010J\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0013J\u001b\u0010)\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u0010J\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0013J\u001b\u00100\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u0010J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0013J\u001b\u00109\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u0010J\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0013J\u001b\u0010>\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020DH\b¢\u0006\u0004\bE\u0010FJ\u0010\u0010G\u001a\u00020\rH\b¢\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u00020KH\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020\u0003H\b¢\u0006\u0004\bO\u0010\u0005J\u000f\u0010P\u001a\u00020QH\u0016¢\u0006\u0004\bR\u0010SJ\u0013\u0010T\u001a\u00020\u000eH\bø\u0001\u0000¢\u0006\u0004\bU\u0010FJ\u0013\u0010V\u001a\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\bW\u0010IJ\u0013\u0010X\u001a\u00020\u0014H\bø\u0001\u0000¢\u0006\u0004\bY\u0010MJ\u0013\u0010Z\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b[\u0010\u0005J\u001b\u0010\\\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b]\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006_"}, d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "data$annotations", "()V", "and", "other", "and-xj2QHRw", "(SS)S", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "compareTo-xj2QHRw", "(SS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(SJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-xj2QHRw", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(S)B", "toInt", "toInt-impl", "(S)I", "toLong", "", "toLong-impl", "(S)J", "toShort", "toShort-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-xj2QHRw", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: UShort.kt */
public final class UShort implements Comparable<UShort> {
    public static final Companion Companion = new Companion(null);
    public static final short MAX_VALUE = -1;
    public static final short MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    private final short data;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/UShort$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UShort;", "S", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: UShort.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    /* renamed from: box-impl reason: not valid java name */
    public static final /* synthetic */ UShort m217boximpl(short s) {
        return new UShort(s);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw reason: not valid java name */
    private int m221compareToxj2QHRw(short s) {
        return m222compareToxj2QHRw(this.data, s);
    }

    @PublishedApi
    /* renamed from: constructor-impl reason: not valid java name */
    public static short m223constructorimpl(short s) {
        return s;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl reason: not valid java name */
    public static boolean m229equalsimpl(short s, @Nullable Object obj) {
        if (obj instanceof UShort) {
            if (s == ((UShort) obj).m262unboximpl()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: equals-impl0 reason: not valid java name */
    public static final boolean m230equalsimpl0(short s, short s2) {
        throw null;
    }

    /* renamed from: hashCode-impl reason: not valid java name */
    public static int m231hashCodeimpl(short s) {
        return s;
    }

    @InlineOnly
    /* renamed from: toByte-impl reason: not valid java name */
    private static final byte m252toByteimpl(short s) {
        return (byte) s;
    }

    @InlineOnly
    /* renamed from: toInt-impl reason: not valid java name */
    private static final int m253toIntimpl(short s) {
        return s & MAX_VALUE;
    }

    @InlineOnly
    /* renamed from: toLong-impl reason: not valid java name */
    private static final long m254toLongimpl(short s) {
        return ((long) s) & 65535;
    }

    @InlineOnly
    /* renamed from: toShort-impl reason: not valid java name */
    private static final short m255toShortimpl(short s) {
        return s;
    }

    @InlineOnly
    /* renamed from: toUShort-impl reason: not valid java name */
    private static final short m260toUShortimpl(short s) {
        return s;
    }

    public boolean equals(Object obj) {
        return m229equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m231hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m256toStringimpl(this.data);
    }

    /* renamed from: unbox-impl reason: not valid java name */
    public final /* synthetic */ short m262unboximpl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ UShort(short s) {
        this.data = s;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m221compareToxj2QHRw(((UShort) obj).m262unboximpl());
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU reason: not valid java name */
    private static final int m218compareTo7apg3OU(short s, byte b) {
        return Intrinsics.compare((int) s & MAX_VALUE, (int) b & 255);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw reason: not valid java name */
    private static int m222compareToxj2QHRw(short s, short s2) {
        return Intrinsics.compare((int) s & MAX_VALUE, (int) s2 & MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns reason: not valid java name */
    private static final int m220compareToWZ4Q5Ns(short s, int i) {
        return UnsignedKt.uintCompare(UInt.m89constructorimpl(s & MAX_VALUE), i);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ reason: not valid java name */
    private static final int m219compareToVKZWuLQ(short s, long j) {
        return UnsignedKt.ulongCompare(ULong.m156constructorimpl(((long) s) & 65535), j);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU reason: not valid java name */
    private static final int m239plus7apg3OU(short s, byte b) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(s & MAX_VALUE) + UInt.m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw reason: not valid java name */
    private static final int m242plusxj2QHRw(short s, short s2) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(s & MAX_VALUE) + UInt.m89constructorimpl(s2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns reason: not valid java name */
    private static final int m241plusWZ4Q5Ns(short s, int i) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(s & MAX_VALUE) + i);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ reason: not valid java name */
    private static final long m240plusVKZWuLQ(short s, long j) {
        return ULong.m156constructorimpl(ULong.m156constructorimpl(((long) s) & 65535) + j);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU reason: not valid java name */
    private static final int m234minus7apg3OU(short s, byte b) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(s & MAX_VALUE) - UInt.m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw reason: not valid java name */
    private static final int m237minusxj2QHRw(short s, short s2) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(s & MAX_VALUE) - UInt.m89constructorimpl(s2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns reason: not valid java name */
    private static final int m236minusWZ4Q5Ns(short s, int i) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(s & MAX_VALUE) - i);
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ reason: not valid java name */
    private static final long m235minusVKZWuLQ(short s, long j) {
        return ULong.m156constructorimpl(ULong.m156constructorimpl(((long) s) & 65535) - j);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU reason: not valid java name */
    private static final int m248times7apg3OU(short s, byte b) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(s & MAX_VALUE) * UInt.m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw reason: not valid java name */
    private static final int m251timesxj2QHRw(short s, short s2) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(s & MAX_VALUE) * UInt.m89constructorimpl(s2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns reason: not valid java name */
    private static final int m250timesWZ4Q5Ns(short s, int i) {
        return UInt.m89constructorimpl(UInt.m89constructorimpl(s & MAX_VALUE) * i);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ reason: not valid java name */
    private static final long m249timesVKZWuLQ(short s, long j) {
        return ULong.m156constructorimpl(ULong.m156constructorimpl(((long) s) & 65535) * j);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU reason: not valid java name */
    private static final int m225div7apg3OU(short s, byte b) {
        return UnsignedKt.m281uintDivideJ1ME1BU(UInt.m89constructorimpl(s & MAX_VALUE), UInt.m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw reason: not valid java name */
    private static final int m228divxj2QHRw(short s, short s2) {
        return UnsignedKt.m281uintDivideJ1ME1BU(UInt.m89constructorimpl(s & MAX_VALUE), UInt.m89constructorimpl(s2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns reason: not valid java name */
    private static final int m227divWZ4Q5Ns(short s, int i) {
        return UnsignedKt.m281uintDivideJ1ME1BU(UInt.m89constructorimpl(s & MAX_VALUE), i);
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ reason: not valid java name */
    private static final long m226divVKZWuLQ(short s, long j) {
        return UnsignedKt.m283ulongDivideeb3DHEI(ULong.m156constructorimpl(((long) s) & 65535), j);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU reason: not valid java name */
    private static final int m244rem7apg3OU(short s, byte b) {
        return UnsignedKt.m282uintRemainderJ1ME1BU(UInt.m89constructorimpl(s & MAX_VALUE), UInt.m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw reason: not valid java name */
    private static final int m247remxj2QHRw(short s, short s2) {
        return UnsignedKt.m282uintRemainderJ1ME1BU(UInt.m89constructorimpl(s & MAX_VALUE), UInt.m89constructorimpl(s2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns reason: not valid java name */
    private static final int m246remWZ4Q5Ns(short s, int i) {
        return UnsignedKt.m282uintRemainderJ1ME1BU(UInt.m89constructorimpl(s & MAX_VALUE), i);
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ reason: not valid java name */
    private static final long m245remVKZWuLQ(short s, long j) {
        return UnsignedKt.m284ulongRemaindereb3DHEI(ULong.m156constructorimpl(((long) s) & 65535), j);
    }

    @InlineOnly
    /* renamed from: inc-impl reason: not valid java name */
    private static final short m232incimpl(short s) {
        return m223constructorimpl((short) (s + 1));
    }

    @InlineOnly
    /* renamed from: dec-impl reason: not valid java name */
    private static final short m224decimpl(short s) {
        return m223constructorimpl((short) (s - 1));
    }

    @InlineOnly
    /* renamed from: rangeTo-xj2QHRw reason: not valid java name */
    private static final UIntRange m243rangeToxj2QHRw(short s, short s2) {
        return new UIntRange(UInt.m89constructorimpl(s & MAX_VALUE), UInt.m89constructorimpl(s2 & MAX_VALUE), null);
    }

    @InlineOnly
    /* renamed from: and-xj2QHRw reason: not valid java name */
    private static final short m216andxj2QHRw(short s, short s2) {
        return m223constructorimpl((short) (s & s2));
    }

    @InlineOnly
    /* renamed from: or-xj2QHRw reason: not valid java name */
    private static final short m238orxj2QHRw(short s, short s2) {
        return m223constructorimpl((short) (s | s2));
    }

    @InlineOnly
    /* renamed from: xor-xj2QHRw reason: not valid java name */
    private static final short m261xorxj2QHRw(short s, short s2) {
        return m223constructorimpl((short) (s ^ s2));
    }

    @InlineOnly
    /* renamed from: inv-impl reason: not valid java name */
    private static final short m233invimpl(short s) {
        return m223constructorimpl((short) (~s));
    }

    @InlineOnly
    /* renamed from: toUByte-impl reason: not valid java name */
    private static final byte m257toUByteimpl(short s) {
        return UByte.m24constructorimpl((byte) s);
    }

    @InlineOnly
    /* renamed from: toUInt-impl reason: not valid java name */
    private static final int m258toUIntimpl(short s) {
        return UInt.m89constructorimpl(s & MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: toULong-impl reason: not valid java name */
    private static final long m259toULongimpl(short s) {
        return ULong.m156constructorimpl(((long) s) & 65535);
    }

    @NotNull
    /* renamed from: toString-impl reason: not valid java name */
    public static String m256toStringimpl(short s) {
        return String.valueOf(s & MAX_VALUE);
    }
}

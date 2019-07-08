package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001bB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u0013\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\u0013\u0010#\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b$\u0010\u0005J\u0013\u0010%\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b(\u0010\u000fJ\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b)\u0010\u000bJ\u001b\u0010'\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u001dJ\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0016J\u001b\u0010,\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b-\u0010\u000bJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b/\u0010\u000fJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u000bJ\u001b\u0010.\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u001dJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0016J\u001b\u00103\u001a\u0002042\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b8\u0010\u000fJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b9\u0010\u000bJ\u001b\u00107\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u001dJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0016J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0003H\fø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0003H\fø\u0001\u0000¢\u0006\u0004\b@\u0010\u000bJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u000fJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u000bJ\u001b\u0010A\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u001dJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u0016J\u0010\u0010F\u001a\u00020GH\b¢\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u00020\u0003H\b¢\u0006\u0004\bK\u0010\u0005J\u0010\u0010L\u001a\u00020MH\b¢\u0006\u0004\bN\u0010OJ\u0010\u0010P\u001a\u00020QH\b¢\u0006\u0004\bR\u0010SJ\u000f\u0010T\u001a\u00020UH\u0016¢\u0006\u0004\bV\u0010WJ\u0013\u0010X\u001a\u00020\rH\bø\u0001\u0000¢\u0006\u0004\bY\u0010IJ\u0013\u0010Z\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b[\u0010\u0005J\u0013\u0010\\\u001a\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\b]\u0010OJ\u0013\u0010^\u001a\u00020\u0014H\bø\u0001\u0000¢\u0006\u0004\b_\u0010SJ\u001b\u0010`\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\ba\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006c"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "data$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-impl", "shr", "shr-impl", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: UInt.kt */
public final class UInt implements Comparable<UInt> {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/UInt$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UInt;", "I", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: UInt.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    /* renamed from: box-impl reason: not valid java name */
    public static final /* synthetic */ UInt m83boximpl(int i) {
        return new UInt(i);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns reason: not valid java name */
    private int m86compareToWZ4Q5Ns(int i) {
        return m87compareToWZ4Q5Ns(this.data, i);
    }

    @PublishedApi
    /* renamed from: constructor-impl reason: not valid java name */
    public static int m89constructorimpl(int i) {
        return i;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl reason: not valid java name */
    public static boolean m95equalsimpl(int i, @Nullable Object obj) {
        if (obj instanceof UInt) {
            if (i == ((UInt) obj).m130unboximpl()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: equals-impl0 reason: not valid java name */
    public static final boolean m96equalsimpl0(int i, int i2) {
        throw null;
    }

    /* renamed from: hashCode-impl reason: not valid java name */
    public static int m97hashCodeimpl(int i) {
        return i;
    }

    @InlineOnly
    /* renamed from: toByte-impl reason: not valid java name */
    private static final byte m120toByteimpl(int i) {
        return (byte) i;
    }

    @InlineOnly
    /* renamed from: toInt-impl reason: not valid java name */
    private static final int m121toIntimpl(int i) {
        return i;
    }

    @InlineOnly
    /* renamed from: toLong-impl reason: not valid java name */
    private static final long m122toLongimpl(int i) {
        return ((long) i) & 4294967295L;
    }

    @InlineOnly
    /* renamed from: toShort-impl reason: not valid java name */
    private static final short m123toShortimpl(int i) {
        return (short) i;
    }

    @InlineOnly
    /* renamed from: toUInt-impl reason: not valid java name */
    private static final int m126toUIntimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m95equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m97hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m124toStringimpl(this.data);
    }

    /* renamed from: unbox-impl reason: not valid java name */
    public final /* synthetic */ int m130unboximpl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ UInt(int i) {
        this.data = i;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m86compareToWZ4Q5Ns(((UInt) obj).m130unboximpl());
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU reason: not valid java name */
    private static final int m84compareTo7apg3OU(int i, byte b) {
        return UnsignedKt.uintCompare(i, m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw reason: not valid java name */
    private static final int m88compareToxj2QHRw(int i, short s) {
        return UnsignedKt.uintCompare(i, m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns reason: not valid java name */
    private static int m87compareToWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ reason: not valid java name */
    private static final int m85compareToVKZWuLQ(int i, long j) {
        return UnsignedKt.ulongCompare(ULong.m156constructorimpl(((long) i) & 4294967295L), j);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU reason: not valid java name */
    private static final int m105plus7apg3OU(int i, byte b) {
        return m89constructorimpl(i + m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw reason: not valid java name */
    private static final int m108plusxj2QHRw(int i, short s) {
        return m89constructorimpl(i + m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns reason: not valid java name */
    private static final int m107plusWZ4Q5Ns(int i, int i2) {
        return m89constructorimpl(i + i2);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ reason: not valid java name */
    private static final long m106plusVKZWuLQ(int i, long j) {
        return ULong.m156constructorimpl(ULong.m156constructorimpl(((long) i) & 4294967295L) + j);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU reason: not valid java name */
    private static final int m100minus7apg3OU(int i, byte b) {
        return m89constructorimpl(i - m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw reason: not valid java name */
    private static final int m103minusxj2QHRw(int i, short s) {
        return m89constructorimpl(i - m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns reason: not valid java name */
    private static final int m102minusWZ4Q5Ns(int i, int i2) {
        return m89constructorimpl(i - i2);
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ reason: not valid java name */
    private static final long m101minusVKZWuLQ(int i, long j) {
        return ULong.m156constructorimpl(ULong.m156constructorimpl(((long) i) & 4294967295L) - j);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU reason: not valid java name */
    private static final int m116times7apg3OU(int i, byte b) {
        return m89constructorimpl(i * m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw reason: not valid java name */
    private static final int m119timesxj2QHRw(int i, short s) {
        return m89constructorimpl(i * m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns reason: not valid java name */
    private static final int m118timesWZ4Q5Ns(int i, int i2) {
        return m89constructorimpl(i * i2);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ reason: not valid java name */
    private static final long m117timesVKZWuLQ(int i, long j) {
        return ULong.m156constructorimpl(ULong.m156constructorimpl(((long) i) & 4294967295L) * j);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU reason: not valid java name */
    private static final int m91div7apg3OU(int i, byte b) {
        return UnsignedKt.m281uintDivideJ1ME1BU(i, m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw reason: not valid java name */
    private static final int m94divxj2QHRw(int i, short s) {
        return UnsignedKt.m281uintDivideJ1ME1BU(i, m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns reason: not valid java name */
    private static final int m93divWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m281uintDivideJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ reason: not valid java name */
    private static final long m92divVKZWuLQ(int i, long j) {
        return UnsignedKt.m283ulongDivideeb3DHEI(ULong.m156constructorimpl(((long) i) & 4294967295L), j);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU reason: not valid java name */
    private static final int m110rem7apg3OU(int i, byte b) {
        return UnsignedKt.m282uintRemainderJ1ME1BU(i, m89constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw reason: not valid java name */
    private static final int m113remxj2QHRw(int i, short s) {
        return UnsignedKt.m282uintRemainderJ1ME1BU(i, m89constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns reason: not valid java name */
    private static final int m112remWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m282uintRemainderJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ reason: not valid java name */
    private static final long m111remVKZWuLQ(int i, long j) {
        return UnsignedKt.m284ulongRemaindereb3DHEI(ULong.m156constructorimpl(((long) i) & 4294967295L), j);
    }

    @InlineOnly
    /* renamed from: inc-impl reason: not valid java name */
    private static final int m98incimpl(int i) {
        return m89constructorimpl(i + 1);
    }

    @InlineOnly
    /* renamed from: dec-impl reason: not valid java name */
    private static final int m90decimpl(int i) {
        return m89constructorimpl(i - 1);
    }

    @InlineOnly
    /* renamed from: rangeTo-WZ4Q5Ns reason: not valid java name */
    private static final UIntRange m109rangeToWZ4Q5Ns(int i, int i2) {
        return new UIntRange(i, i2, null);
    }

    @InlineOnly
    /* renamed from: shl-impl reason: not valid java name */
    private static final int m114shlimpl(int i, int i2) {
        return m89constructorimpl(i << i2);
    }

    @InlineOnly
    /* renamed from: shr-impl reason: not valid java name */
    private static final int m115shrimpl(int i, int i2) {
        return m89constructorimpl(i >>> i2);
    }

    @InlineOnly
    /* renamed from: and-WZ4Q5Ns reason: not valid java name */
    private static final int m82andWZ4Q5Ns(int i, int i2) {
        return m89constructorimpl(i & i2);
    }

    @InlineOnly
    /* renamed from: or-WZ4Q5Ns reason: not valid java name */
    private static final int m104orWZ4Q5Ns(int i, int i2) {
        return m89constructorimpl(i | i2);
    }

    @InlineOnly
    /* renamed from: xor-WZ4Q5Ns reason: not valid java name */
    private static final int m129xorWZ4Q5Ns(int i, int i2) {
        return m89constructorimpl(i ^ i2);
    }

    @InlineOnly
    /* renamed from: inv-impl reason: not valid java name */
    private static final int m99invimpl(int i) {
        return m89constructorimpl(~i);
    }

    @InlineOnly
    /* renamed from: toUByte-impl reason: not valid java name */
    private static final byte m125toUByteimpl(int i) {
        return UByte.m24constructorimpl((byte) i);
    }

    @InlineOnly
    /* renamed from: toUShort-impl reason: not valid java name */
    private static final short m128toUShortimpl(int i) {
        return UShort.m223constructorimpl((short) i);
    }

    @InlineOnly
    /* renamed from: toULong-impl reason: not valid java name */
    private static final long m127toULongimpl(int i) {
        return ULong.m156constructorimpl(((long) i) & 4294967295L);
    }

    @NotNull
    /* renamed from: toString-impl reason: not valid java name */
    public static String m124toStringimpl(int i) {
        return String.valueOf(((long) i) & 4294967295L);
    }
}

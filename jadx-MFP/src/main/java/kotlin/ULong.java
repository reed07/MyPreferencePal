package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001eB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\rHÖ\u0001J\u0013\u0010'\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u0013\u0010)\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0005J\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001dJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u001fJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b.\u0010\u000bJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b/\u0010\"J\u001b\u00100\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001dJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u001fJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b6\u0010\"J\u001b\u00107\u001a\u0002082\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001dJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u001fJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b?\u0010\"J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\fø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010C\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\fø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\bF\u0010\u001dJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bI\u0010\"J\u0010\u0010J\u001a\u00020KH\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020\rH\b¢\u0006\u0004\bO\u0010PJ\u0010\u0010Q\u001a\u00020\u0003H\b¢\u0006\u0004\bR\u0010\u0005J\u0010\u0010S\u001a\u00020TH\b¢\u0006\u0004\bU\u0010VJ\u000f\u0010W\u001a\u00020XH\u0016¢\u0006\u0004\bY\u0010ZJ\u0013\u0010[\u001a\u00020\u000eH\bø\u0001\u0000¢\u0006\u0004\b\\\u0010MJ\u0013\u0010]\u001a\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\b^\u0010PJ\u0013\u0010_\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b`\u0010\u0005J\u0013\u0010a\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\bb\u0010VJ\u001b\u0010c\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\bd\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006f"}, d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "data$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-impl", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-impl", "shr", "shr-impl", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toInt", "toInt-impl", "(J)I", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: ULong.kt */
public final class ULong implements Comparable<ULong> {
    public static final Companion Companion = new Companion(null);
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/ULong$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/ULong;", "J", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ULong.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    /* renamed from: box-impl reason: not valid java name */
    public static final /* synthetic */ ULong m150boximpl(long j) {
        return new ULong(j);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ reason: not valid java name */
    private int m152compareToVKZWuLQ(long j) {
        return m153compareToVKZWuLQ(this.data, j);
    }

    @PublishedApi
    /* renamed from: constructor-impl reason: not valid java name */
    public static long m156constructorimpl(long j) {
        return j;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl reason: not valid java name */
    public static boolean m162equalsimpl(long j, @Nullable Object obj) {
        if (obj instanceof ULong) {
            if (j == ((ULong) obj).m197unboximpl()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: equals-impl0 reason: not valid java name */
    public static final boolean m163equalsimpl0(long j, long j2) {
        throw null;
    }

    /* renamed from: hashCode-impl reason: not valid java name */
    public static int m164hashCodeimpl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    @InlineOnly
    /* renamed from: toByte-impl reason: not valid java name */
    private static final byte m187toByteimpl(long j) {
        return (byte) ((int) j);
    }

    @InlineOnly
    /* renamed from: toInt-impl reason: not valid java name */
    private static final int m188toIntimpl(long j) {
        return (int) j;
    }

    @InlineOnly
    /* renamed from: toLong-impl reason: not valid java name */
    private static final long m189toLongimpl(long j) {
        return j;
    }

    @InlineOnly
    /* renamed from: toShort-impl reason: not valid java name */
    private static final short m190toShortimpl(long j) {
        return (short) ((int) j);
    }

    @InlineOnly
    /* renamed from: toULong-impl reason: not valid java name */
    private static final long m194toULongimpl(long j) {
        return j;
    }

    public boolean equals(Object obj) {
        return m162equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m164hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m191toStringimpl(this.data);
    }

    /* renamed from: unbox-impl reason: not valid java name */
    public final /* synthetic */ long m197unboximpl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ ULong(long j) {
        this.data = j;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m152compareToVKZWuLQ(((ULong) obj).m197unboximpl());
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU reason: not valid java name */
    private static final int m151compareTo7apg3OU(long j, byte b) {
        return UnsignedKt.ulongCompare(j, m156constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw reason: not valid java name */
    private static final int m155compareToxj2QHRw(long j, short s) {
        return UnsignedKt.ulongCompare(j, m156constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns reason: not valid java name */
    private static final int m154compareToWZ4Q5Ns(long j, int i) {
        return UnsignedKt.ulongCompare(j, m156constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ reason: not valid java name */
    private static int m153compareToVKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU reason: not valid java name */
    private static final long m172plus7apg3OU(long j, byte b) {
        return m156constructorimpl(j + m156constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw reason: not valid java name */
    private static final long m175plusxj2QHRw(long j, short s) {
        return m156constructorimpl(j + m156constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns reason: not valid java name */
    private static final long m174plusWZ4Q5Ns(long j, int i) {
        return m156constructorimpl(j + m156constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ reason: not valid java name */
    private static final long m173plusVKZWuLQ(long j, long j2) {
        return m156constructorimpl(j + j2);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU reason: not valid java name */
    private static final long m167minus7apg3OU(long j, byte b) {
        return m156constructorimpl(j - m156constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw reason: not valid java name */
    private static final long m170minusxj2QHRw(long j, short s) {
        return m156constructorimpl(j - m156constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns reason: not valid java name */
    private static final long m169minusWZ4Q5Ns(long j, int i) {
        return m156constructorimpl(j - m156constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ reason: not valid java name */
    private static final long m168minusVKZWuLQ(long j, long j2) {
        return m156constructorimpl(j - j2);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU reason: not valid java name */
    private static final long m183times7apg3OU(long j, byte b) {
        return m156constructorimpl(j * m156constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw reason: not valid java name */
    private static final long m186timesxj2QHRw(long j, short s) {
        return m156constructorimpl(j * m156constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns reason: not valid java name */
    private static final long m185timesWZ4Q5Ns(long j, int i) {
        return m156constructorimpl(j * m156constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ reason: not valid java name */
    private static final long m184timesVKZWuLQ(long j, long j2) {
        return m156constructorimpl(j * j2);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU reason: not valid java name */
    private static final long m158div7apg3OU(long j, byte b) {
        return UnsignedKt.m283ulongDivideeb3DHEI(j, m156constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw reason: not valid java name */
    private static final long m161divxj2QHRw(long j, short s) {
        return UnsignedKt.m283ulongDivideeb3DHEI(j, m156constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns reason: not valid java name */
    private static final long m160divWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m283ulongDivideeb3DHEI(j, m156constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ reason: not valid java name */
    private static final long m159divVKZWuLQ(long j, long j2) {
        return UnsignedKt.m283ulongDivideeb3DHEI(j, j2);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU reason: not valid java name */
    private static final long m177rem7apg3OU(long j, byte b) {
        return UnsignedKt.m284ulongRemaindereb3DHEI(j, m156constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw reason: not valid java name */
    private static final long m180remxj2QHRw(long j, short s) {
        return UnsignedKt.m284ulongRemaindereb3DHEI(j, m156constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns reason: not valid java name */
    private static final long m179remWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m284ulongRemaindereb3DHEI(j, m156constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ reason: not valid java name */
    private static final long m178remVKZWuLQ(long j, long j2) {
        return UnsignedKt.m284ulongRemaindereb3DHEI(j, j2);
    }

    @InlineOnly
    /* renamed from: inc-impl reason: not valid java name */
    private static final long m165incimpl(long j) {
        return m156constructorimpl(j + 1);
    }

    @InlineOnly
    /* renamed from: dec-impl reason: not valid java name */
    private static final long m157decimpl(long j) {
        return m156constructorimpl(j - 1);
    }

    @InlineOnly
    /* renamed from: rangeTo-VKZWuLQ reason: not valid java name */
    private static final ULongRange m176rangeToVKZWuLQ(long j, long j2) {
        ULongRange uLongRange = new ULongRange(j, j2, null);
        return uLongRange;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=long, for r2v0, types: [long, int] */
    @kotlin.internal.InlineOnly
    /* renamed from: shl-impl reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long m181shlimpl(long r0, long r2) {
        /*
            long r0 = r0 << r2
            long r0 = m156constructorimpl(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ULong.m181shlimpl(long, int):long");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=long, for r2v0, types: [long, int] */
    @kotlin.internal.InlineOnly
    /* renamed from: shr-impl reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long m182shrimpl(long r0, long r2) {
        /*
            long r0 = r0 >>> r2
            long r0 = m156constructorimpl(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ULong.m182shrimpl(long, int):long");
    }

    @InlineOnly
    /* renamed from: and-VKZWuLQ reason: not valid java name */
    private static final long m149andVKZWuLQ(long j, long j2) {
        return m156constructorimpl(j & j2);
    }

    @InlineOnly
    /* renamed from: or-VKZWuLQ reason: not valid java name */
    private static final long m171orVKZWuLQ(long j, long j2) {
        return m156constructorimpl(j | j2);
    }

    @InlineOnly
    /* renamed from: xor-VKZWuLQ reason: not valid java name */
    private static final long m196xorVKZWuLQ(long j, long j2) {
        return m156constructorimpl(j ^ j2);
    }

    @InlineOnly
    /* renamed from: inv-impl reason: not valid java name */
    private static final long m166invimpl(long j) {
        return m156constructorimpl(~j);
    }

    @InlineOnly
    /* renamed from: toUByte-impl reason: not valid java name */
    private static final byte m192toUByteimpl(long j) {
        return UByte.m24constructorimpl((byte) ((int) j));
    }

    @InlineOnly
    /* renamed from: toUShort-impl reason: not valid java name */
    private static final short m195toUShortimpl(long j) {
        return UShort.m223constructorimpl((short) ((int) j));
    }

    @InlineOnly
    /* renamed from: toUInt-impl reason: not valid java name */
    private static final int m193toUIntimpl(long j) {
        return UInt.m89constructorimpl((int) j);
    }

    @NotNull
    /* renamed from: toString-impl reason: not valid java name */
    public static String m191toStringimpl(long j) {
        return UnsignedKt.ulongToString(j);
    }
}

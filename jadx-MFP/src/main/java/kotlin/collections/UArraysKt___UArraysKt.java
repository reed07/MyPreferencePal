package kotlin.collections;

import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b/\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\r\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\n\u001a\u00020\u000b*\u00020\fH\bø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\u000f\u001a\u00020\u0010*\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0015\u0010\u0014\u001a\u00020\u0002*\u00020\u0001H\bø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\u0015\u0010\u0015\u001a\u00020\u0007*\u00020\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u0015\u0010\u0016\u001a\u00020\f*\u00020\u000bH\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010\u0017\u001a\u00020\u0011*\u00020\u0010H\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a\u001f\u0010\u0018\u001a\u00020\u0019*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0004ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001f\u0010\u0018\u001a\u00020\u0019*\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H\u0004ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001f\u0010\u0018\u001a\u00020\u0019*\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0004ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a\u001f\u0010\u0018\u001a\u00020\u0019*\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0011H\u0004ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\u0016\u0010#\u001a\u00020$*\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u0016\u0010#\u001a\u00020$*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u0016\u0010#\u001a\u00020$*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u0016\u0010#\u001a\u00020$*\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u0016\u0010-\u001a\u00020.*\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a\u0016\u0010-\u001a\u00020.*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001a\u0016\u0010-\u001a\u00020.*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b3\u00104\u001a\u0016\u0010-\u001a\u00020.*\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a=\u00107\u001a\u00020\u0002*\u00020\u00022\u0006\u00108\u001a\u00020\u00022\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a=\u00107\u001a\u00020\u0007*\u00020\u00072\u0006\u00108\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\b>\u0010?\u001a=\u00107\u001a\u00020\f*\u00020\f2\u0006\u00108\u001a\u00020\f2\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a=\u00107\u001a\u00020\u0011*\u00020\u00112\u0006\u00108\u001a\u00020\u00112\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001a\u0017\u0010D\u001a\u00020\u0002*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0004\bE\u0010\u0004\u001a\u001f\u0010D\u001a\u00020\u0002*\u00020\u00022\u0006\u0010F\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bG\u0010H\u001a\u0017\u0010D\u001a\u00020\u0007*\u00020\u0007H\bø\u0001\u0000¢\u0006\u0004\bI\u0010\t\u001a\u001f\u0010D\u001a\u00020\u0007*\u00020\u00072\u0006\u0010F\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bJ\u0010K\u001a\u0017\u0010D\u001a\u00020\f*\u00020\fH\bø\u0001\u0000¢\u0006\u0004\bL\u0010\u000e\u001a\u001f\u0010D\u001a\u00020\f*\u00020\f2\u0006\u0010F\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a\u0017\u0010D\u001a\u00020\u0011*\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\bO\u0010\u0013\u001a\u001f\u0010D\u001a\u00020\u0011*\u00020\u00112\u0006\u0010F\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bP\u0010Q\u001a'\u0010R\u001a\u00020\u0002*\u00020\u00022\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bU\u0010V\u001a'\u0010R\u001a\u00020\u0007*\u00020\u00072\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bW\u0010X\u001a'\u0010R\u001a\u00020\f*\u00020\f2\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bY\u0010Z\u001a'\u0010R\u001a\u00020\u0011*\u00020\u00112\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\b[\u0010\\\u001a\u0017\u0010]\u001a\u00020^*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0004\b_\u0010`\u001a\u001e\u0010]\u001a\u00020^*\u00020\u00022\u0006\u0010]\u001a\u00020aH\u0007ø\u0001\u0000¢\u0006\u0004\bb\u0010c\u001a\u0017\u0010]\u001a\u00020d*\u00020\u0007H\bø\u0001\u0000¢\u0006\u0004\be\u0010(\u001a\u001e\u0010]\u001a\u00020d*\u00020\u00072\u0006\u0010]\u001a\u00020aH\u0007ø\u0001\u0000¢\u0006\u0004\bf\u0010g\u001a\u0017\u0010]\u001a\u00020h*\u00020\fH\bø\u0001\u0000¢\u0006\u0004\bi\u0010j\u001a\u001e\u0010]\u001a\u00020h*\u00020\f2\u0006\u0010]\u001a\u00020aH\u0007ø\u0001\u0000¢\u0006\u0004\bk\u0010l\u001a\u0017\u0010]\u001a\u00020m*\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\bn\u0010o\u001a\u001e\u0010]\u001a\u00020m*\u00020\u00112\u0006\u0010]\u001a\u00020aH\u0007ø\u0001\u0000¢\u0006\u0004\bp\u0010q\u001a\u0017\u0010r\u001a\u00020\u0001*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0004\bs\u0010\u0004\u001a\u0017\u0010t\u001a\u00020\u0006*\u00020\u0007H\bø\u0001\u0000¢\u0006\u0004\bu\u0010\t\u001a\u0017\u0010v\u001a\u00020\u000b*\u00020\fH\bø\u0001\u0000¢\u0006\u0004\bw\u0010\u000e\u001a\u0017\u0010x\u001a\u00020\u0010*\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\by\u0010\u0013\u001a\u001c\u0010z\u001a\b\u0012\u0004\u0012\u00020^0{*\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b|\u0010}\u001a\u001c\u0010z\u001a\b\u0012\u0004\u0012\u00020d0{*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b~\u0010\u001a\u001e\u0010z\u001a\b\u0012\u0004\u0012\u00020h0{*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001\u001a\u001e\u0010z\u001a\b\u0012\u0004\u0012\u00020m0{*\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001\u001a\u0016\u0010\u0001\u001a\u00020\u0002*\u00020\u0001H\bø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\u0016\u0010\u0001\u001a\u00020\u0007*\u00020\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u0016\u0010\u0001\u001a\u00020\f*\u00020\u000bH\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a\u0016\u0010\u0001\u001a\u00020\u0011*\u00020\u0010H\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0002\u0004\n\u0002\b\u0019¨\u0006\u0001"}, d2 = {"asByteArray", "", "Lkotlin/UByteArray;", "asByteArray-GBYM_sE", "([B)[B", "asIntArray", "", "Lkotlin/UIntArray;", "asIntArray--ajY-9A", "([I)[I", "asLongArray", "", "Lkotlin/ULongArray;", "asLongArray-QwZRm1k", "([J)[J", "asShortArray", "", "Lkotlin/UShortArray;", "asShortArray-rL5Bavg", "([S)[S", "asUByteArray", "asUIntArray", "asULongArray", "asUShortArray", "contentEquals", "", "other", "contentEquals-kdPth3s", "([B[B)Z", "contentEquals-ctEhBpI", "([I[I)Z", "contentEquals-us8wMrg", "([J[J)Z", "contentEquals-mazbYpA", "([S[S)Z", "contentHashCode", "", "contentHashCode-GBYM_sE", "([B)I", "contentHashCode--ajY-9A", "([I)I", "contentHashCode-QwZRm1k", "([J)I", "contentHashCode-rL5Bavg", "([S)I", "contentToString", "", "contentToString-GBYM_sE", "([B)Ljava/lang/String;", "contentToString--ajY-9A", "([I)Ljava/lang/String;", "contentToString-QwZRm1k", "([J)Ljava/lang/String;", "contentToString-rL5Bavg", "([S)Ljava/lang/String;", "copyInto", "destination", "destinationOffset", "startIndex", "endIndex", "copyInto-FUQE5sA", "([B[BIII)[B", "copyInto-sIZ3KeM", "([I[IIII)[I", "copyInto--B0-L2c", "([J[JIII)[J", "copyInto-9-ak10g", "([S[SIII)[S", "copyOf", "copyOf-GBYM_sE", "newSize", "copyOf-PpDY95g", "([BI)[B", "copyOf--ajY-9A", "copyOf-qFRl0hI", "([II)[I", "copyOf-QwZRm1k", "copyOf-r7IrZao", "([JI)[J", "copyOf-rL5Bavg", "copyOf-nggk6HY", "([SI)[S", "copyOfRange", "fromIndex", "toIndex", "copyOfRange-4UcCI2c", "([BII)[B", "copyOfRange-oBK06Vg", "([III)[I", "copyOfRange--nroSd4", "([JII)[J", "copyOfRange-Aa5vz7o", "([SII)[S", "random", "Lkotlin/UByte;", "random-GBYM_sE", "([B)B", "Lkotlin/random/Random;", "random-oSF2wD8", "([BLkotlin/random/Random;)B", "Lkotlin/UInt;", "random--ajY-9A", "random-2D5oskM", "([ILkotlin/random/Random;)I", "Lkotlin/ULong;", "random-QwZRm1k", "([J)J", "random-JzugnMA", "([JLkotlin/random/Random;)J", "Lkotlin/UShort;", "random-rL5Bavg", "([S)S", "random-s5X_as8", "([SLkotlin/random/Random;)S", "toByteArray", "toByteArray-GBYM_sE", "toIntArray", "toIntArray--ajY-9A", "toLongArray", "toLongArray-QwZRm1k", "toShortArray", "toShortArray-rL5Bavg", "toTypedArray", "", "toTypedArray-GBYM_sE", "([B)[Lkotlin/UByte;", "toTypedArray--ajY-9A", "([I)[Lkotlin/UInt;", "toTypedArray-QwZRm1k", "([J)[Lkotlin/ULong;", "toTypedArray-rL5Bavg", "([S)[Lkotlin/UShort;", "toUByteArray", "toUIntArray", "toULongArray", "toUShortArray", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/collections/UArraysKt")
/* compiled from: _UArrays.kt */
class UArraysKt___UArraysKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: asByteArray-GBYM_sE reason: not valid java name */
    private static final byte[] m289asByteArrayGBYM_sE(@NotNull byte[] bArr) {
        return bArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: asIntArray--ajY-9A reason: not valid java name */
    private static final int[] m290asIntArrayajY9A(@NotNull int[] iArr) {
        return iArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: asLongArray-QwZRm1k reason: not valid java name */
    private static final long[] m291asLongArrayQwZRm1k(@NotNull long[] jArr) {
        return jArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: asShortArray-rL5Bavg reason: not valid java name */
    private static final short[] m292asShortArrayrL5Bavg(@NotNull short[] sArr) {
        return sArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: random--ajY-9A reason: not valid java name */
    private static final int m325randomajY9A(@NotNull int[] iArr) {
        return UArraysKt.m326random2D5oskM(iArr, Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: random-QwZRm1k reason: not valid java name */
    private static final long m329randomQwZRm1k(@NotNull long[] jArr) {
        return UArraysKt.m328randomJzugnMA(jArr, Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: random-GBYM_sE reason: not valid java name */
    private static final byte m327randomGBYM_sE(@NotNull byte[] bArr) {
        return UArraysKt.m330randomoSF2wD8(bArr, Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: random-rL5Bavg reason: not valid java name */
    private static final short m331randomrL5Bavg(@NotNull short[] sArr) {
        return UArraysKt.m332randoms5X_as8(sArr, Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: random-2D5oskM reason: not valid java name */
    public static final int m326random2D5oskM(@NotNull int[] iArr, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (!UIntArray.m141isEmptyimpl(iArr)) {
            return UIntArray.m138getimpl(iArr, random.nextInt(UIntArray.m139getSizeimpl(iArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: random-JzugnMA reason: not valid java name */
    public static final long m328randomJzugnMA(@NotNull long[] jArr, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (!ULongArray.m208isEmptyimpl(jArr)) {
            return ULongArray.m205getimpl(jArr, random.nextInt(ULongArray.m206getSizeimpl(jArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: random-oSF2wD8 reason: not valid java name */
    public static final byte m330randomoSF2wD8(@NotNull byte[] bArr, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (!UByteArray.m74isEmptyimpl(bArr)) {
            return UByteArray.m71getimpl(bArr, random.nextInt(UByteArray.m72getSizeimpl(bArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: random-s5X_as8 reason: not valid java name */
    public static final short m332randoms5X_as8(@NotNull short[] sArr, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (!UShortArray.m273isEmptyimpl(sArr)) {
            return UShortArray.m270getimpl(sArr, random.nextInt(UShortArray.m271getSizeimpl(sArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte[] asUByteArray(@NotNull byte[] bArr) {
        return UByteArray.m66constructorimpl(bArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int[] asUIntArray(@NotNull int[] iArr) {
        return UIntArray.m133constructorimpl(iArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long[] asULongArray(@NotNull long[] jArr) {
        return ULongArray.m200constructorimpl(jArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short[] asUShortArray(@NotNull short[] sArr) {
        return UShortArray.m265constructorimpl(sArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contentEquals-ctEhBpI reason: not valid java name */
    public static final boolean m293contentEqualsctEhBpI(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(iArr2, "other");
        return Arrays.equals(iArr, iArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contentEquals-us8wMrg reason: not valid java name */
    public static final boolean m296contentEqualsus8wMrg(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(jArr2, "other");
        return Arrays.equals(jArr, jArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contentEquals-kdPth3s reason: not valid java name */
    public static final boolean m294contentEqualskdPth3s(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(bArr2, "other");
        return Arrays.equals(bArr, bArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contentEquals-mazbYpA reason: not valid java name */
    public static final boolean m295contentEqualsmazbYpA(@NotNull short[] sArr, @NotNull short[] sArr2) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(sArr2, "other");
        return Arrays.equals(sArr, sArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contentHashCode--ajY-9A reason: not valid java name */
    public static final int m297contentHashCodeajY9A(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        return Arrays.hashCode(iArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contentHashCode-QwZRm1k reason: not valid java name */
    public static final int m299contentHashCodeQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        return Arrays.hashCode(jArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contentHashCode-GBYM_sE reason: not valid java name */
    public static final int m298contentHashCodeGBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        return Arrays.hashCode(bArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contentHashCode-rL5Bavg reason: not valid java name */
    public static final int m300contentHashCoderL5Bavg(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        return Arrays.hashCode(sArr);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: contentToString--ajY-9A reason: not valid java name */
    public static final String m301contentToStringajY9A(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        return CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(iArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: contentToString-QwZRm1k reason: not valid java name */
    public static final String m303contentToStringQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        return CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(jArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: contentToString-GBYM_sE reason: not valid java name */
    public static final String m302contentToStringGBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        return CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(bArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: contentToString-rL5Bavg reason: not valid java name */
    public static final String m304contentToStringrL5Bavg(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        return CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(sArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyInto-sIZ3KeM$default reason: not valid java name */
    static /* synthetic */ int[] m312copyIntosIZ3KeM$default(int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UIntArray.m139getSizeimpl(iArr);
        }
        ArraysKt.copyInto(iArr, iArr2, i, i2, i3);
        return iArr2;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyInto-sIZ3KeM reason: not valid java name */
    private static final int[] m311copyIntosIZ3KeM(@NotNull int[] iArr, int[] iArr2, int i, int i2, int i3) {
        ArraysKt.copyInto(iArr, iArr2, i, i2, i3);
        return iArr2;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyInto--B0-L2c$default reason: not valid java name */
    static /* synthetic */ long[] m306copyIntoB0L2c$default(long[] jArr, long[] jArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = ULongArray.m206getSizeimpl(jArr);
        }
        ArraysKt.copyInto(jArr, jArr2, i, i2, i3);
        return jArr2;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyInto--B0-L2c reason: not valid java name */
    private static final long[] m305copyIntoB0L2c(@NotNull long[] jArr, long[] jArr2, int i, int i2, int i3) {
        ArraysKt.copyInto(jArr, jArr2, i, i2, i3);
        return jArr2;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyInto-FUQE5sA$default reason: not valid java name */
    static /* synthetic */ byte[] m310copyIntoFUQE5sA$default(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UByteArray.m72getSizeimpl(bArr);
        }
        ArraysKt.copyInto(bArr, bArr2, i, i2, i3);
        return bArr2;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyInto-FUQE5sA reason: not valid java name */
    private static final byte[] m309copyIntoFUQE5sA(@NotNull byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        ArraysKt.copyInto(bArr, bArr2, i, i2, i3);
        return bArr2;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyInto-9-ak10g$default reason: not valid java name */
    static /* synthetic */ short[] m308copyInto9ak10g$default(short[] sArr, short[] sArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UShortArray.m271getSizeimpl(sArr);
        }
        ArraysKt.copyInto(sArr, sArr2, i, i2, i3);
        return sArr2;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyInto-9-ak10g reason: not valid java name */
    private static final short[] m307copyInto9ak10g(@NotNull short[] sArr, short[] sArr2, int i, int i2, int i3) {
        ArraysKt.copyInto(sArr, sArr2, i, i2, i3);
        return sArr2;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOf--ajY-9A reason: not valid java name */
    private static final int[] m313copyOfajY9A(@NotNull int[] iArr) {
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return UIntArray.m133constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOf-QwZRm1k reason: not valid java name */
    private static final long[] m316copyOfQwZRm1k(@NotNull long[] jArr) {
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return ULongArray.m200constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOf-GBYM_sE reason: not valid java name */
    private static final byte[] m314copyOfGBYM_sE(@NotNull byte[] bArr) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return UByteArray.m66constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOf-rL5Bavg reason: not valid java name */
    private static final short[] m320copyOfrL5Bavg(@NotNull short[] sArr) {
        short[] copyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return UShortArray.m265constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOf-qFRl0hI reason: not valid java name */
    private static final int[] m318copyOfqFRl0hI(@NotNull int[] iArr, int i) {
        int[] copyOf = Arrays.copyOf(iArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return UIntArray.m133constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOf-r7IrZao reason: not valid java name */
    private static final long[] m319copyOfr7IrZao(@NotNull long[] jArr, int i) {
        long[] copyOf = Arrays.copyOf(jArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return ULongArray.m200constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOf-PpDY95g reason: not valid java name */
    private static final byte[] m315copyOfPpDY95g(@NotNull byte[] bArr, int i) {
        byte[] copyOf = Arrays.copyOf(bArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return UByteArray.m66constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOf-nggk6HY reason: not valid java name */
    private static final short[] m317copyOfnggk6HY(@NotNull short[] sArr, int i) {
        short[] copyOf = Arrays.copyOf(sArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return UShortArray.m265constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOfRange-oBK06Vg reason: not valid java name */
    private static final int[] m324copyOfRangeoBK06Vg(@NotNull int[] iArr, int i, int i2) {
        int[] iArr2;
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            iArr2 = ArraysKt.copyOfRange(iArr, i, i2);
        } else if (i2 <= iArr.length) {
            iArr2 = Arrays.copyOfRange(iArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(iArr2, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("toIndex: ");
            sb.append(i2);
            sb.append(", size: ");
            sb.append(iArr.length);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        return UIntArray.m133constructorimpl(iArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOfRange--nroSd4 reason: not valid java name */
    private static final long[] m321copyOfRangenroSd4(@NotNull long[] jArr, int i, int i2) {
        long[] jArr2;
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            jArr2 = ArraysKt.copyOfRange(jArr, i, i2);
        } else if (i2 <= jArr.length) {
            jArr2 = Arrays.copyOfRange(jArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(jArr2, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("toIndex: ");
            sb.append(i2);
            sb.append(", size: ");
            sb.append(jArr.length);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        return ULongArray.m200constructorimpl(jArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOfRange-4UcCI2c reason: not valid java name */
    private static final byte[] m322copyOfRange4UcCI2c(@NotNull byte[] bArr, int i, int i2) {
        byte[] bArr2;
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            bArr2 = ArraysKt.copyOfRange(bArr, i, i2);
        } else if (i2 <= bArr.length) {
            bArr2 = Arrays.copyOfRange(bArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(bArr2, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("toIndex: ");
            sb.append(i2);
            sb.append(", size: ");
            sb.append(bArr.length);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        return UByteArray.m66constructorimpl(bArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: copyOfRange-Aa5vz7o reason: not valid java name */
    private static final short[] m323copyOfRangeAa5vz7o(@NotNull short[] sArr, int i, int i2) {
        short[] sArr2;
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            sArr2 = ArraysKt.copyOfRange(sArr, i, i2);
        } else if (i2 <= sArr.length) {
            sArr2 = Arrays.copyOfRange(sArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(sArr2, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("toIndex: ");
            sb.append(i2);
            sb.append(", size: ");
            sb.append(sArr.length);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        return UShortArray.m265constructorimpl(sArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: toByteArray-GBYM_sE reason: not valid java name */
    private static final byte[] m333toByteArrayGBYM_sE(@NotNull byte[] bArr) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: toIntArray--ajY-9A reason: not valid java name */
    private static final int[] m334toIntArrayajY9A(@NotNull int[] iArr) {
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: toLongArray-QwZRm1k reason: not valid java name */
    private static final long[] m335toLongArrayQwZRm1k(@NotNull long[] jArr) {
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: toShortArray-rL5Bavg reason: not valid java name */
    private static final short[] m336toShortArrayrL5Bavg(@NotNull short[] sArr) {
        short[] copyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: toTypedArray--ajY-9A reason: not valid java name */
    public static final UInt[] m337toTypedArrayajY9A(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        UInt[] uIntArr = new UInt[UIntArray.m139getSizeimpl(iArr)];
        int length = uIntArr.length;
        for (int i = 0; i < length; i++) {
            uIntArr[i] = UInt.m83boximpl(UIntArray.m138getimpl(iArr, i));
        }
        return uIntArr;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: toTypedArray-QwZRm1k reason: not valid java name */
    public static final ULong[] m339toTypedArrayQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        ULong[] uLongArr = new ULong[ULongArray.m206getSizeimpl(jArr)];
        int length = uLongArr.length;
        for (int i = 0; i < length; i++) {
            uLongArr[i] = ULong.m150boximpl(ULongArray.m205getimpl(jArr, i));
        }
        return uLongArr;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: toTypedArray-GBYM_sE reason: not valid java name */
    public static final UByte[] m338toTypedArrayGBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        UByte[] uByteArr = new UByte[UByteArray.m72getSizeimpl(bArr)];
        int length = uByteArr.length;
        for (int i = 0; i < length; i++) {
            uByteArr[i] = UByte.m18boximpl(UByteArray.m71getimpl(bArr, i));
        }
        return uByteArr;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: toTypedArray-rL5Bavg reason: not valid java name */
    public static final UShort[] m340toTypedArrayrL5Bavg(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        UShort[] uShortArr = new UShort[UShortArray.m271getSizeimpl(sArr)];
        int length = uShortArr.length;
        for (int i = 0; i < length; i++) {
            uShortArr[i] = UShort.m217boximpl(UShortArray.m270getimpl(sArr, i));
        }
        return uShortArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte[] toUByteArray(@NotNull byte[] bArr) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return UByteArray.m66constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int[] toUIntArray(@NotNull int[] iArr) {
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return UIntArray.m133constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long[] toULongArray(@NotNull long[] jArr) {
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return ULongArray.m200constructorimpl(copyOf);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short[] toUShortArray(@NotNull short[] sArr) {
        short[] copyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return UShortArray.m265constructorimpl(copyOf);
    }
}

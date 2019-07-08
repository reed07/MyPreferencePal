package kotlin.streams.jdk8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001*\u00020\u0005H\u0007\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u0007H\u0007\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\tH\u0007\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\t\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u0001H\u0007\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f*\u00020\u0003H\u0007\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f*\u00020\u0005H\u0007\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f*\u00020\u0007H\u0007\u001a\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\f\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\tH\u0007¨\u0006\r"}, d2 = {"asSequence", "Lkotlin/sequences/Sequence;", "", "Ljava/util/stream/DoubleStream;", "", "Ljava/util/stream/IntStream;", "", "Ljava/util/stream/LongStream;", "T", "Ljava/util/stream/Stream;", "asStream", "toList", "", "kotlin-stdlib-jdk8"}, k = 2, mv = {1, 1, 13}, pn = "kotlin.streams")
@JvmName(name = "StreamsKt")
/* compiled from: Streams.kt */
public final class StreamsKt {
    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <T> Sequence<T> asSequence(@NotNull Stream<T> stream) {
        Intrinsics.checkParameterIsNotNull(stream, "receiver$0");
        return new StreamsKt$asSequence$$inlined$Sequence$1<>(stream);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final Sequence<Integer> asSequence(@NotNull IntStream intStream) {
        Intrinsics.checkParameterIsNotNull(intStream, "receiver$0");
        return new StreamsKt$asSequence$$inlined$Sequence$2<>(intStream);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final Sequence<Long> asSequence(@NotNull LongStream longStream) {
        Intrinsics.checkParameterIsNotNull(longStream, "receiver$0");
        return new StreamsKt$asSequence$$inlined$Sequence$3<>(longStream);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final Sequence<Double> asSequence(@NotNull DoubleStream doubleStream) {
        Intrinsics.checkParameterIsNotNull(doubleStream, "receiver$0");
        return new StreamsKt$asSequence$$inlined$Sequence$4<>(doubleStream);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <T> Stream<T> asStream(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkParameterIsNotNull(sequence, "receiver$0");
        Stream<T> stream = StreamSupport.stream(new StreamsKt$asStream$1(sequence), 16, false);
        Intrinsics.checkExpressionValueIsNotNull(stream, "StreamSupport.stream({ S…literator.ORDERED, false)");
        return stream;
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <T> List<T> toList(@NotNull Stream<T> stream) {
        Intrinsics.checkParameterIsNotNull(stream, "receiver$0");
        Object collect = stream.collect(Collectors.toList());
        Intrinsics.checkExpressionValueIsNotNull(collect, "collect(Collectors.toList<T>())");
        return (List) collect;
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final List<Integer> toList(@NotNull IntStream intStream) {
        Intrinsics.checkParameterIsNotNull(intStream, "receiver$0");
        int[] array = intStream.toArray();
        Intrinsics.checkExpressionValueIsNotNull(array, "toArray()");
        return ArraysKt.asList(array);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final List<Long> toList(@NotNull LongStream longStream) {
        Intrinsics.checkParameterIsNotNull(longStream, "receiver$0");
        long[] array = longStream.toArray();
        Intrinsics.checkExpressionValueIsNotNull(array, "toArray()");
        return ArraysKt.asList(array);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final List<Double> toList(@NotNull DoubleStream doubleStream) {
        Intrinsics.checkParameterIsNotNull(doubleStream, "receiver$0");
        double[] array = doubleStream.toArray();
        Intrinsics.checkExpressionValueIsNotNull(array, "toArray()");
        return ArraysKt.asList(array);
    }
}

package kotlinx.coroutines.channels;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"kotlinx/coroutines/channels/ChannelsKt__ChannelsKt", "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt"}, k = 4, mv = {1, 1, 13})
public final class ChannelsKt {
    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object all(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.all(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object any(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.any(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V> Object associate(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends Pair<? extends K, ? extends V>> function1, @NotNull Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt__Channels_commonKt.associate(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K> Object associateBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super Map<K, ? extends E>> continuation) {
        return ChannelsKt__Channels_commonKt.associateBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V> Object associateBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt__Channels_commonKt.associateBy(receiveChannel, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, M extends Map<? super K, ? super E>> Object associateByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.associateByTo(receiveChannel, m, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V, M extends Map<? super K, ? super V>> Object associateByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.associateByTo(receiveChannel, m, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V, M extends Map<? super K, ? super V>> Object associateTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m, @NotNull Function1<? super E, ? extends Pair<? extends K, ? extends V>> function1, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.associateTo(receiveChannel, m, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object consumeEach(@NotNull BroadcastChannel<E> broadcastChannel, @NotNull Function1<? super E, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEach(broadcastChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object consumeEach(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEach(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object consumeEachIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super IndexedValue<? extends E>, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEachIndexed(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object count(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.count(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object elementAtOrElse(@NotNull ReceiveChannel<? extends E> receiveChannel, int i, @NotNull Function1<? super Integer, ? extends E> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.elementAtOrElse(receiveChannel, i, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends Collection<? super E>> Object filterIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, Boolean> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterIndexedTo(receiveChannel, c, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends SendChannel<? super E>> Object filterIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, Boolean> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterIndexedTo(receiveChannel, c, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends Collection<? super E>> Object filterNotTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterNotTo(receiveChannel, c, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends SendChannel<? super E>> Object filterNotTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterNotTo(receiveChannel, c, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends Collection<? super E>> Object filterTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterTo(receiveChannel, c, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends SendChannel<? super E>> Object filterTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterTo(receiveChannel, c, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object find(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.find(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object findLast(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.findLast(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object first(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.first(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object firstOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.firstOrNull(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R> Object fold(@NotNull ReceiveChannel<? extends E> receiveChannel, R r, @NotNull Function2<? super R, ? super E, ? extends R> function2, @NotNull Continuation<? super R> continuation) {
        return ChannelsKt__Channels_commonKt.fold(receiveChannel, r, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R> Object foldIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, R r, @NotNull Function3<? super Integer, ? super R, ? super E, ? extends R> function3, @NotNull Continuation<? super R> continuation) {
        return ChannelsKt__Channels_commonKt.foldIndexed(receiveChannel, r, function3, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K> Object groupBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super Map<K, ? extends List<? extends E>>> continuation) {
        return ChannelsKt__Channels_commonKt.groupBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V> Object groupBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super Map<K, ? extends List<? extends V>>> continuation) {
        return ChannelsKt__Channels_commonKt.groupBy(receiveChannel, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, M extends Map<? super K, List<E>>> Object groupByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.groupByTo(receiveChannel, m, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V, M extends Map<? super K, List<V>>> Object groupByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.groupByTo(receiveChannel, m, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object indexOfFirst(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.indexOfFirst(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object indexOfLast(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.indexOfLast(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object last(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.last(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object lastOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.lastOrNull(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends Collection<? super R>> Object mapIndexedNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(receiveChannel, c, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends SendChannel<? super R>> Object mapIndexedNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(receiveChannel, c, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends Collection<? super R>> Object mapIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedTo(receiveChannel, c, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends SendChannel<? super R>> Object mapIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedTo(receiveChannel, c, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends Collection<? super R>> Object mapNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapNotNullTo(receiveChannel, c, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends SendChannel<? super R>> Object mapNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapNotNullTo(receiveChannel, c, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends Collection<? super R>> Object mapTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapTo(receiveChannel, c, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends SendChannel<? super R>> Object mapTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapTo(receiveChannel, c, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R extends Comparable<? super R>> Object maxBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.maxBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R extends Comparable<? super R>> Object minBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.minBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object none(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.none(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object partition(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Pair<? extends List<? extends E>, ? extends List<? extends E>>> continuation) {
        return ChannelsKt__Channels_commonKt.partition(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <S, E extends S> Object reduce(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function2<? super S, ? super E, ? extends S> function2, @NotNull Continuation<? super S> continuation) {
        return ChannelsKt__Channels_commonKt.reduce(receiveChannel, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <S, E extends S> Object reduceIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function3<? super Integer, ? super S, ? super E, ? extends S> function3, @NotNull Continuation<? super S> continuation) {
        return ChannelsKt__Channels_commonKt.reduceIndexed(receiveChannel, function3, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object single(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.single(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object singleOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.singleOrNull(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object sumBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Integer> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.sumBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object sumByDouble(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Double> function1, @NotNull Continuation<? super Double> continuation) {
        return ChannelsKt__Channels_commonKt.sumByDouble(receiveChannel, function1, continuation);
    }
}

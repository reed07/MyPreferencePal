package kotlin.reflect.jvm.internal.impl.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: collections.kt */
public final class CollectionsKt {
    @NotNull
    public static final <K> Map<K, Integer> mapToIndex(@NotNull Iterable<? extends K> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "receiver$0");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (Object put : iterable) {
            linkedHashMap.put(put, Integer.valueOf(i));
            i++;
        }
        return linkedHashMap;
    }

    public static final <T> void addIfNotNull(@NotNull Collection<T> collection, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(collection, "receiver$0");
        if (t != null) {
            collection.add(t);
        }
    }

    @NotNull
    public static final <K, V> HashMap<K, V> newHashMapWithExpectedSize(int i) {
        return new HashMap<>(capacity(i));
    }

    @NotNull
    public static final <E> HashSet<E> newHashSetWithExpectedSize(int i) {
        return new HashSet<>(capacity(i));
    }

    @NotNull
    public static final <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int i) {
        return new LinkedHashSet<>(capacity(i));
    }

    private static final int capacity(int i) {
        if (i < 3) {
            return 3;
        }
        return i + (i / 3) + 1;
    }

    @NotNull
    public static final <T> List<T> compact(@NotNull ArrayList<T> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "receiver$0");
        switch (arrayList.size()) {
            case 0:
                return kotlin.collections.CollectionsKt.emptyList();
            case 1:
                return kotlin.collections.CollectionsKt.listOf(kotlin.collections.CollectionsKt.first((List<? extends T>) arrayList));
            default:
                arrayList.trimToSize();
                return arrayList;
        }
    }
}

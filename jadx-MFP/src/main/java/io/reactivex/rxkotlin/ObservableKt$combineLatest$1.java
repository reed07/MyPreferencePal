package io.reactivex.rxkotlin;

import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0001*\u00020\u00032*\u0010\u0004\u001a&\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00030\u0003 \u0006*\u0012\u0012\u000e\b\u0001\u0012\n \u0006*\u0004\u0018\u00010\u00030\u00030\u00050\u0005H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "R", "T", "", "it", "", "kotlin.jvm.PlatformType", "apply", "([Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 11})
/* compiled from: observable.kt */
public final class ObservableKt$combineLatest$1<T, R> implements Function<Object[], R> {
    final /* synthetic */ Function1 $combineFunction;

    @NotNull
    public final R apply(@NotNull Object[] objArr) {
        Intrinsics.checkParameterIsNotNull(objArr, "it");
        Function1 function1 = this.$combineFunction;
        Iterable asList = ArraysKt.asList(objArr);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(asList, 10));
        for (Object next : asList) {
            if (next != null) {
                arrayList.add(next);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
        }
        return function1.invoke((List) arrayList);
    }
}

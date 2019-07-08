package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: KClasses.kt */
final class KClasses$sam$org_jetbrains_kotlin_utils_DFS_Neighbors$0 implements Neighbors {
    private final /* synthetic */ Function1 function;

    KClasses$sam$org_jetbrains_kotlin_utils_DFS_Neighbors$0(Function1 function1) {
        this.function = function1;
    }

    @NotNull
    public final /* synthetic */ Iterable getNeighbors(Object obj) {
        return (Iterable) this.function.invoke(obj);
    }
}

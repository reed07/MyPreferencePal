package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: overridingUtils.kt */
final class OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1 extends Lambda implements Function1<D, D> {
    public static final OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1 INSTANCE = new OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1();

    OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1() {
        super(1);
    }

    @NotNull
    public final D invoke(@NotNull D d) {
        Intrinsics.checkParameterIsNotNull(d, "receiver$0");
        return d;
    }
}

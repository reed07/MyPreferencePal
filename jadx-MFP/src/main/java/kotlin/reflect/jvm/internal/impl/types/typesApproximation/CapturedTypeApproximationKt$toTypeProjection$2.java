package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

/* compiled from: CapturedTypeApproximation.kt */
final class CapturedTypeApproximationKt$toTypeProjection$2 extends Lambda implements Function1<Variance, Variance> {
    final /* synthetic */ TypeArgument $this_toTypeProjection;

    CapturedTypeApproximationKt$toTypeProjection$2(TypeArgument typeArgument) {
        this.$this_toTypeProjection = typeArgument;
        super(1);
    }

    @NotNull
    public final Variance invoke(@NotNull Variance variance) {
        Intrinsics.checkParameterIsNotNull(variance, "variance");
        return variance == this.$this_toTypeProjection.getTypeParameter().getVariance() ? Variance.INVARIANT : variance;
    }
}

package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;

/* compiled from: CapturedTypeApproximation.kt */
final class CapturedTypeApproximationKt$approximateCapturedTypes$1 extends Lambda implements Function1<KotlinType, KotlinType> {
    final /* synthetic */ KotlinType $type;

    CapturedTypeApproximationKt$approximateCapturedTypes$1(KotlinType kotlinType) {
        this.$type = kotlinType;
        super(1);
    }

    @NotNull
    public final KotlinType invoke(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        KotlinType makeNullableIfNeeded = TypeUtils.makeNullableIfNeeded(kotlinType, this.$type.isMarkedNullable());
        Intrinsics.checkExpressionValueIsNotNull(makeNullableIfNeeded, "TypeUtils.makeNullableIf…s, type.isMarkedNullable)");
        return makeNullableIfNeeded;
    }
}

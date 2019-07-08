package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;

/* compiled from: CapturedTypeConstructor.kt */
final class CapturedTypeConstructorKt$createCapturedIfNeeded$1 extends Lambda implements Function0<KotlinType> {
    final /* synthetic */ TypeProjection $this_createCapturedIfNeeded;

    CapturedTypeConstructorKt$createCapturedIfNeeded$1(TypeProjection typeProjection) {
        this.$this_createCapturedIfNeeded = typeProjection;
        super(0);
    }

    @NotNull
    public final KotlinType invoke() {
        KotlinType type = this.$this_createCapturedIfNeeded.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "this@createCapturedIfNeeded.type");
        return type;
    }
}

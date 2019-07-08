package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinTypeFactory.kt */
final class NullableSimpleType extends DelegatingSimpleTypeImpl {
    public boolean isMarkedNullable() {
        return true;
    }

    public NullableSimpleType(@NotNull SimpleType simpleType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "delegate");
        super(simpleType);
    }
}

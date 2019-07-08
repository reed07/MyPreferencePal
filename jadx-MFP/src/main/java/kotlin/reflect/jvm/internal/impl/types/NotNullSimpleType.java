package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinTypeFactory.kt */
final class NotNullSimpleType extends DelegatingSimpleTypeImpl {
    public boolean isMarkedNullable() {
        return false;
    }

    public NotNullSimpleType(@NotNull SimpleType simpleType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "delegate");
        super(simpleType);
    }
}

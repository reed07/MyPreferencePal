package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: flexibleTypes.kt */
public final class FlexibleTypesKt {
    public static final boolean isFlexible(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        return kotlinType.unwrap() instanceof FlexibleType;
    }

    @NotNull
    public static final FlexibleType asFlexibleType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (unwrap != null) {
            return (FlexibleType) unwrap;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.FlexibleType");
    }

    @NotNull
    public static final SimpleType lowerIfFlexible(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (unwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrap).getLowerBound();
        }
        if (unwrap instanceof SimpleType) {
            return (SimpleType) unwrap;
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public static final SimpleType upperIfFlexible(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (unwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrap).getUpperBound();
        }
        if (unwrap instanceof SimpleType) {
            return (SimpleType) unwrap;
        }
        throw new NoWhenBranchMatchedException();
    }
}

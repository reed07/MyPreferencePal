package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;

class TypeCheckerProcedureCallbacksImpl implements TypeCheckingProcedureCallbacks {
    public boolean capture(@NotNull KotlinType kotlinType, @NotNull TypeProjection typeProjection) {
        return false;
    }

    public boolean noCorrespondingSupertype(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        return false;
    }

    TypeCheckerProcedureCallbacksImpl() {
    }

    public boolean assertEqualTypes(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2, @NotNull TypeCheckingProcedure typeCheckingProcedure) {
        return typeCheckingProcedure.equalTypes(kotlinType, kotlinType2);
    }

    public boolean assertEqualTypeConstructors(@NotNull TypeConstructor typeConstructor, @NotNull TypeConstructor typeConstructor2) {
        return typeConstructor.equals(typeConstructor2);
    }

    public boolean assertSubtype(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2, @NotNull TypeCheckingProcedure typeCheckingProcedure) {
        return typeCheckingProcedure.isSubtypeOf(kotlinType, kotlinType2);
    }
}

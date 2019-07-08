package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;

public interface TypeCheckingProcedureCallbacks {
    boolean assertEqualTypeConstructors(@NotNull TypeConstructor typeConstructor, @NotNull TypeConstructor typeConstructor2);

    boolean assertEqualTypes(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2, @NotNull TypeCheckingProcedure typeCheckingProcedure);

    boolean assertSubtype(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2, @NotNull TypeCheckingProcedure typeCheckingProcedure);

    boolean capture(@NotNull KotlinType kotlinType, @NotNull TypeProjection typeProjection);

    boolean noCorrespondingSupertype(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2);
}

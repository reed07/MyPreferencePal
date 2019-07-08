package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality;
import org.jetbrains.annotations.NotNull;

public class KotlinTypeCheckerImpl implements KotlinTypeChecker {
    private final TypeCheckingProcedure procedure;

    @NotNull
    public static KotlinTypeChecker withAxioms(@NotNull final TypeConstructorEquality typeConstructorEquality) {
        return new KotlinTypeCheckerImpl(new TypeCheckingProcedure(new TypeCheckerProcedureCallbacksImpl() {
            public boolean assertEqualTypeConstructors(@NotNull TypeConstructor typeConstructor, @NotNull TypeConstructor typeConstructor2) {
                return typeConstructor.equals(typeConstructor2) || typeConstructorEquality.equals(typeConstructor, typeConstructor2);
            }
        }));
    }

    protected KotlinTypeCheckerImpl(@NotNull TypeCheckingProcedure typeCheckingProcedure) {
        this.procedure = typeCheckingProcedure;
    }

    public boolean isSubtypeOf(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        return this.procedure.isSubtypeOf(kotlinType, kotlinType2);
    }

    public boolean equalTypes(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        return this.procedure.equalTypes(kotlinType, kotlinType2);
    }
}

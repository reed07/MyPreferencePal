package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.Set;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeCheckerContext.kt */
public class TypeCheckerContext {
    private final boolean allowedTypeVariable;
    /* access modifiers changed from: private */
    public int argumentsDepth;
    private final boolean errorTypeEqualsToAnything;
    /* access modifiers changed from: private */
    public ArrayDeque<SimpleType> supertypesDeque;
    private boolean supertypesLocked;
    /* access modifiers changed from: private */
    public Set<SimpleType> supertypesSet;

    /* compiled from: TypeCheckerContext.kt */
    public enum LowerCapturedTypePolicy {
        CHECK_ONLY_LOWER,
        CHECK_SUBTYPE_AND_LOWER,
        SKIP_LOWER
    }

    /* compiled from: TypeCheckerContext.kt */
    public enum SeveralSupertypesWithSameConstructorPolicy {
        TAKE_FIRST_FOR_SUBTYPING,
        FORCE_NOT_SUBTYPE,
        CHECK_ANY_OF_THEM,
        INTERSECT_ARGUMENTS_AND_CHECK_AGAIN
    }

    /* compiled from: TypeCheckerContext.kt */
    public static abstract class SupertypesPolicy {

        /* compiled from: TypeCheckerContext.kt */
        public static final class LowerIfFlexible extends SupertypesPolicy {
            public static final LowerIfFlexible INSTANCE = new LowerIfFlexible();

            private LowerIfFlexible() {
                super(null);
            }

            @NotNull
            public SimpleType transformType(@NotNull KotlinType kotlinType) {
                Intrinsics.checkParameterIsNotNull(kotlinType, "type");
                return FlexibleTypesKt.lowerIfFlexible(kotlinType);
            }
        }

        /* compiled from: TypeCheckerContext.kt */
        public static final class LowerIfFlexibleWithCustomSubstitutor extends SupertypesPolicy {
            @NotNull
            private final TypeSubstitutor substitutor;

            public LowerIfFlexibleWithCustomSubstitutor(@NotNull TypeSubstitutor typeSubstitutor) {
                Intrinsics.checkParameterIsNotNull(typeSubstitutor, "substitutor");
                super(null);
                this.substitutor = typeSubstitutor;
            }

            @NotNull
            public SimpleType transformType(@NotNull KotlinType kotlinType) {
                Intrinsics.checkParameterIsNotNull(kotlinType, "type");
                KotlinType safeSubstitute = this.substitutor.safeSubstitute(FlexibleTypesKt.lowerIfFlexible(kotlinType), Variance.INVARIANT);
                Intrinsics.checkExpressionValueIsNotNull(safeSubstitute, "substitutor.safeSubstitu…le(), Variance.INVARIANT)");
                return TypeSubstitutionKt.asSimpleType(safeSubstitute);
            }
        }

        /* compiled from: TypeCheckerContext.kt */
        public static final class None extends SupertypesPolicy {
            public static final None INSTANCE = new None();

            private None() {
                super(null);
            }

            @NotNull
            public Void transformType(@NotNull KotlinType kotlinType) {
                Intrinsics.checkParameterIsNotNull(kotlinType, "type");
                throw new UnsupportedOperationException("Should not be called");
            }
        }

        /* compiled from: TypeCheckerContext.kt */
        public static final class UpperIfFlexible extends SupertypesPolicy {
            public static final UpperIfFlexible INSTANCE = new UpperIfFlexible();

            private UpperIfFlexible() {
                super(null);
            }

            @NotNull
            public SimpleType transformType(@NotNull KotlinType kotlinType) {
                Intrinsics.checkParameterIsNotNull(kotlinType, "type");
                return FlexibleTypesKt.upperIfFlexible(kotlinType);
            }
        }

        @NotNull
        public abstract SimpleType transformType(@NotNull KotlinType kotlinType);

        private SupertypesPolicy() {
        }

        public /* synthetic */ SupertypesPolicy(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Nullable
    public Boolean addSubtypeConstraint(@NotNull UnwrappedType unwrappedType, @NotNull UnwrappedType unwrappedType2) {
        Intrinsics.checkParameterIsNotNull(unwrappedType, "subType");
        Intrinsics.checkParameterIsNotNull(unwrappedType2, "superType");
        return null;
    }

    public TypeCheckerContext(boolean z, boolean z2) {
        this.errorTypeEqualsToAnything = z;
        this.allowedTypeVariable = z2;
    }

    public /* synthetic */ TypeCheckerContext(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        this(z, z2);
    }

    public final boolean getErrorTypeEqualsToAnything() {
        return this.errorTypeEqualsToAnything;
    }

    public boolean areEqualTypeConstructors(@NotNull TypeConstructor typeConstructor, @NotNull TypeConstructor typeConstructor2) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "a");
        Intrinsics.checkParameterIsNotNull(typeConstructor2, "b");
        return Intrinsics.areEqual((Object) typeConstructor, (Object) typeConstructor2);
    }

    @NotNull
    public LowerCapturedTypePolicy getLowerCapturedTypePolicy(@NotNull SimpleType simpleType, @NotNull NewCapturedType newCapturedType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "subType");
        Intrinsics.checkParameterIsNotNull(newCapturedType, "superType");
        return LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER;
    }

    @NotNull
    public SeveralSupertypesWithSameConstructorPolicy getSameConstructorPolicy() {
        return SeveralSupertypesWithSameConstructorPolicy.INTERSECT_ARGUMENTS_AND_CHECK_AGAIN;
    }

    /* access modifiers changed from: private */
    public final void initialize() {
        boolean z = !this.supertypesLocked;
        if (!_Assertions.ENABLED || z) {
            this.supertypesLocked = true;
            if (this.supertypesDeque == null) {
                this.supertypesDeque = new ArrayDeque<>(4);
            }
            if (this.supertypesSet == null) {
                this.supertypesSet = SmartSet.Companion.create();
                return;
            }
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    /* access modifiers changed from: private */
    public final void clear() {
        ArrayDeque<SimpleType> arrayDeque = this.supertypesDeque;
        if (arrayDeque == null) {
            Intrinsics.throwNpe();
        }
        arrayDeque.clear();
        Set<SimpleType> set = this.supertypesSet;
        if (set == null) {
            Intrinsics.throwNpe();
        }
        set.clear();
        this.supertypesLocked = false;
    }

    public final boolean isAllowedTypeVariable(@NotNull UnwrappedType unwrappedType) {
        Intrinsics.checkParameterIsNotNull(unwrappedType, "receiver$0");
        return this.allowedTypeVariable && (unwrappedType.getConstructor() instanceof NewTypeVariableConstructor);
    }
}

package kotlin.reflect.jvm.internal.impl.types;

import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.NullabilityChecker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SpecialTypes.kt */
public final class DefinitelyNotNullType extends DelegatingSimpleType implements CustomTypeVariable {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SimpleType original;

    /* compiled from: SpecialTypes.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final DefinitelyNotNullType makeDefinitelyNotNull$descriptors(@NotNull UnwrappedType unwrappedType) {
            Intrinsics.checkParameterIsNotNull(unwrappedType, "type");
            if (unwrappedType instanceof DefinitelyNotNullType) {
                return (DefinitelyNotNullType) unwrappedType;
            }
            if (!makesSenseToBeDefinitelyNotNull(unwrappedType)) {
                return null;
            }
            if (unwrappedType instanceof FlexibleType) {
                FlexibleType flexibleType = (FlexibleType) unwrappedType;
                boolean areEqual = Intrinsics.areEqual((Object) flexibleType.getLowerBound().getConstructor(), (Object) flexibleType.getUpperBound().getConstructor());
                if (_Assertions.ENABLED && !areEqual) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("DefinitelyNotNullType for flexible type (");
                    sb.append(unwrappedType);
                    sb.append(") can be created only from type variable with the same constructor for bounds");
                    throw new AssertionError(sb.toString());
                }
            }
            return new DefinitelyNotNullType(FlexibleTypesKt.lowerIfFlexible(unwrappedType), null);
        }

        public final boolean makesSenseToBeDefinitelyNotNull(@NotNull UnwrappedType unwrappedType) {
            Intrinsics.checkParameterIsNotNull(unwrappedType, "type");
            return TypeUtilsKt.canHaveUndefinedNullability(unwrappedType) && !NullabilityChecker.INSTANCE.isSubtypeOfAny(unwrappedType);
        }
    }

    public boolean isMarkedNullable() {
        return false;
    }

    private DefinitelyNotNullType(SimpleType simpleType) {
        this.original = simpleType;
    }

    public /* synthetic */ DefinitelyNotNullType(@NotNull SimpleType simpleType, DefaultConstructorMarker defaultConstructorMarker) {
        this(simpleType);
    }

    @NotNull
    public final SimpleType getOriginal() {
        return this.original;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public SimpleType getDelegate() {
        return this.original;
    }

    public boolean isTypeVariable() {
        return (getDelegate().getConstructor() instanceof NewTypeVariableConstructor) || (getDelegate().getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor);
    }

    @NotNull
    public KotlinType substitutionResult(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "replacement");
        return SpecialTypesKt.makeDefinitelyNotNullOrNotNull(kotlinType.unwrap());
    }

    @NotNull
    public DefinitelyNotNullType replaceAnnotations(@NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        return new DefinitelyNotNullType(getDelegate().replaceAnnotations(annotations));
    }

    @NotNull
    public SimpleType makeNullableAsSpecified(boolean z) {
        return z ? getDelegate().makeNullableAsSpecified(z) : this;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDelegate());
        sb.append("!!");
        return sb.toString();
    }
}

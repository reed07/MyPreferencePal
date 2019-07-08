package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TypeUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final SimpleType CANT_INFER_FUNCTION_PARAM_TYPE = ErrorUtils.createErrorType("Cannot be inferred");
    public static final SimpleType DONT_CARE = ErrorUtils.createErrorTypeWithCustomDebugName("DONT_CARE");
    @NotNull
    public static final SimpleType NO_EXPECTED_TYPE = new SpecialType("NO_EXPECTED_TYPE");
    public static final SimpleType UNIT_EXPECTED_TYPE = new SpecialType("UNIT_EXPECTED_TYPE");

    public static class SpecialType extends DelegatingSimpleType {
        private final String name;

        public SpecialType(String str) {
            this.name = str;
        }

        /* access modifiers changed from: protected */
        @NotNull
        public SimpleType getDelegate() {
            throw new IllegalStateException(this.name);
        }

        @NotNull
        public SimpleType replaceAnnotations(@NotNull Annotations annotations) {
            throw new IllegalStateException(this.name);
        }

        @NotNull
        public SimpleType makeNullableAsSpecified(boolean z) {
            throw new IllegalStateException(this.name);
        }

        @NotNull
        public String toString() {
            return this.name;
        }
    }

    public static boolean noExpectedType(@NotNull KotlinType kotlinType) {
        return kotlinType == NO_EXPECTED_TYPE || kotlinType == UNIT_EXPECTED_TYPE;
    }

    public static boolean isDontCarePlaceholder(@Nullable KotlinType kotlinType) {
        return kotlinType != null && kotlinType.getConstructor() == DONT_CARE.getConstructor();
    }

    @NotNull
    public static KotlinType makeNullable(@NotNull KotlinType kotlinType) {
        return makeNullableAsSpecified(kotlinType, true);
    }

    @NotNull
    public static KotlinType makeNotNullable(@NotNull KotlinType kotlinType) {
        return makeNullableAsSpecified(kotlinType, false);
    }

    @NotNull
    public static KotlinType makeNullableAsSpecified(@NotNull KotlinType kotlinType, boolean z) {
        return kotlinType.unwrap().makeNullableAsSpecified(z);
    }

    @NotNull
    public static KotlinType makeNullableIfNeeded(@NotNull KotlinType kotlinType, boolean z) {
        return z ? makeNullable(kotlinType) : kotlinType;
    }

    @NotNull
    public static SimpleType makeUnsubstitutedType(ClassifierDescriptor classifierDescriptor, MemberScope memberScope) {
        if (ErrorUtils.isError(classifierDescriptor)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsubstituted type for ");
            sb.append(classifierDescriptor);
            return ErrorUtils.createErrorType(sb.toString());
        }
        TypeConstructor typeConstructor = classifierDescriptor.getTypeConstructor();
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(Annotations.Companion.getEMPTY(), typeConstructor, getDefaultTypeProjections(typeConstructor.getParameters()), false, memberScope);
    }

    @NotNull
    public static List<TypeProjection> getDefaultTypeProjections(@NotNull List<TypeParameterDescriptor> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (TypeParameterDescriptor defaultType : list) {
            arrayList.add(new TypeProjectionImpl(defaultType.getDefaultType()));
        }
        return CollectionsKt.toList(arrayList);
    }

    @NotNull
    public static List<KotlinType> getImmediateSupertypes(@NotNull KotlinType kotlinType) {
        TypeSubstitutor create = TypeSubstitutor.create(kotlinType);
        Collection<KotlinType> supertypes = kotlinType.getConstructor().getSupertypes();
        ArrayList arrayList = new ArrayList(supertypes.size());
        for (KotlinType createSubstitutedSupertype : supertypes) {
            KotlinType createSubstitutedSupertype2 = createSubstitutedSupertype(kotlinType, createSubstitutedSupertype, create);
            if (createSubstitutedSupertype2 != null) {
                arrayList.add(createSubstitutedSupertype2);
            }
        }
        return arrayList;
    }

    @Nullable
    public static KotlinType createSubstitutedSupertype(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2, @NotNull TypeSubstitutor typeSubstitutor) {
        KotlinType substitute = typeSubstitutor.substitute(kotlinType2, Variance.INVARIANT);
        if (substitute != null) {
            return makeNullableIfNeeded(substitute, kotlinType.isMarkedNullable());
        }
        return null;
    }

    public static boolean isNullableType(@NotNull KotlinType kotlinType) {
        if (kotlinType.isMarkedNullable()) {
            return true;
        }
        if (FlexibleTypesKt.isFlexible(kotlinType) && isNullableType(FlexibleTypesKt.asFlexibleType(kotlinType).getUpperBound())) {
            return true;
        }
        if (isTypeParameter(kotlinType)) {
            return hasNullableSuperType(kotlinType);
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            for (KotlinType isNullableType : constructor.getSupertypes()) {
                if (isNullableType(isNullableType)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean acceptsNullable(@NotNull KotlinType kotlinType) {
        if (kotlinType.isMarkedNullable()) {
            return true;
        }
        if (!FlexibleTypesKt.isFlexible(kotlinType) || !acceptsNullable(FlexibleTypesKt.asFlexibleType(kotlinType).getUpperBound())) {
            return false;
        }
        return true;
    }

    public static boolean hasNullableSuperType(@NotNull KotlinType kotlinType) {
        if (kotlinType.getConstructor().getDeclarationDescriptor() instanceof ClassDescriptor) {
            return false;
        }
        for (KotlinType isNullableType : getImmediateSupertypes(kotlinType)) {
            if (isNullableType(isNullableType)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static ClassDescriptor getClassDescriptor(@NotNull KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor instanceof ClassDescriptor) {
            return (ClassDescriptor) declarationDescriptor;
        }
        return null;
    }

    public static boolean contains(@Nullable KotlinType kotlinType, @NotNull Function1<UnwrappedType, Boolean> function1) {
        if (kotlinType == null) {
            return false;
        }
        UnwrappedType unwrap = kotlinType.unwrap();
        if (((Boolean) function1.invoke(unwrap)).booleanValue()) {
            return true;
        }
        FlexibleType flexibleType = unwrap instanceof FlexibleType ? (FlexibleType) unwrap : null;
        if (flexibleType != null && (contains(flexibleType.getLowerBound(), function1) || contains(flexibleType.getUpperBound(), function1))) {
            return true;
        }
        if ((unwrap instanceof DefinitelyNotNullType) && contains(((DefinitelyNotNullType) unwrap).getOriginal(), function1)) {
            return true;
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            for (KotlinType contains : ((IntersectionTypeConstructor) constructor).getSupertypes()) {
                if (contains(contains, function1)) {
                    return true;
                }
            }
            return false;
        }
        for (TypeProjection typeProjection : kotlinType.getArguments()) {
            if (!typeProjection.isStarProjection()) {
                if (contains(typeProjection.getType(), function1)) {
                    return true;
                }
            }
        }
        return false;
    }

    @NotNull
    public static TypeProjection makeStarProjection(@NotNull TypeParameterDescriptor typeParameterDescriptor) {
        return new StarProjectionImpl(typeParameterDescriptor);
    }

    public static boolean isTypeParameter(@NotNull KotlinType kotlinType) {
        return getTypeParameterDescriptorOrNull(kotlinType) != null || (kotlinType.getConstructor() instanceof NewTypeVariableConstructor);
    }

    @Nullable
    public static TypeParameterDescriptor getTypeParameterDescriptorOrNull(@NotNull KotlinType kotlinType) {
        if (kotlinType.getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor) {
            return (TypeParameterDescriptor) kotlinType.getConstructor().getDeclarationDescriptor();
        }
        return null;
    }
}

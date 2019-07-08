package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeUtils.kt */
public final class TypeUtilsKt {
    @NotNull
    public static final KotlinBuiltIns getBuiltIns(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        KotlinBuiltIns builtIns = kotlinType.getConstructor().getBuiltIns();
        Intrinsics.checkExpressionValueIsNotNull(builtIns, "constructor.builtIns");
        return builtIns;
    }

    @NotNull
    public static final KotlinType makeNullable(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        KotlinType makeNullable = TypeUtils.makeNullable(kotlinType);
        Intrinsics.checkExpressionValueIsNotNull(makeNullable, "TypeUtils.makeNullable(this)");
        return makeNullable;
    }

    @NotNull
    public static final KotlinType makeNotNullable(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        KotlinType makeNotNullable = TypeUtils.makeNotNullable(kotlinType);
        Intrinsics.checkExpressionValueIsNotNull(makeNotNullable, "TypeUtils.makeNotNullable(this)");
        return makeNotNullable;
    }

    public static final boolean isAnyOrNullableAny(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        return KotlinBuiltIns.isAnyOrNullableAny(kotlinType);
    }

    public static final boolean isTypeParameter(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        return TypeUtils.isTypeParameter(kotlinType);
    }

    public static final boolean isSubtypeOf(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "superType");
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(kotlinType, kotlinType2);
    }

    @NotNull
    public static final KotlinType replaceAnnotations(@NotNull KotlinType kotlinType, @NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        if (!kotlinType.getAnnotations().isEmpty() || !annotations.isEmpty()) {
            return kotlinType.unwrap().replaceAnnotations(annotations);
        }
        return kotlinType;
    }

    @NotNull
    public static final TypeProjection createProjection(@NotNull KotlinType kotlinType, @NotNull Variance variance, @Nullable TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        Intrinsics.checkParameterIsNotNull(variance, "projectionKind");
        if ((typeParameterDescriptor != null ? typeParameterDescriptor.getVariance() : null) == variance) {
            variance = Variance.INVARIANT;
        }
        return new TypeProjectionImpl(variance, kotlinType);
    }

    @NotNull
    public static final TypeProjection asTypeProjection(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        return new TypeProjectionImpl(kotlinType);
    }

    public static final boolean contains(@NotNull KotlinType kotlinType, @NotNull Function1<? super UnwrappedType, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        return TypeUtils.contains(kotlinType, function1);
    }

    public static final boolean canHaveUndefinedNullability(@NotNull UnwrappedType unwrappedType) {
        Intrinsics.checkParameterIsNotNull(unwrappedType, "receiver$0");
        return (unwrappedType.getConstructor() instanceof NewTypeVariableConstructor) || (unwrappedType.getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor) || (unwrappedType instanceof NewCapturedType);
    }

    @NotNull
    public static final KotlinType replaceArgumentsWithStarProjections(@NotNull KotlinType kotlinType) {
        UnwrappedType unwrappedType;
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (unwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrap;
            SimpleType lowerBound = flexibleType.getLowerBound();
            if (!lowerBound.getConstructor().getParameters().isEmpty() && lowerBound.getConstructor().getDeclarationDescriptor() != null) {
                List parameters = lowerBound.getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters, "constructor.parameters");
                Iterable<TypeParameterDescriptor> iterable = parameters;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (TypeParameterDescriptor starProjectionImpl : iterable) {
                    arrayList.add(new StarProjectionImpl(starProjectionImpl));
                }
                lowerBound = TypeSubstitutionKt.replace$default(lowerBound, (List) arrayList, (Annotations) null, 2, (Object) null);
            }
            SimpleType upperBound = flexibleType.getUpperBound();
            if (!upperBound.getConstructor().getParameters().isEmpty() && upperBound.getConstructor().getDeclarationDescriptor() != null) {
                List parameters2 = upperBound.getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters2, "constructor.parameters");
                Iterable<TypeParameterDescriptor> iterable2 = parameters2;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (TypeParameterDescriptor starProjectionImpl2 : iterable2) {
                    arrayList2.add(new StarProjectionImpl(starProjectionImpl2));
                }
                upperBound = TypeSubstitutionKt.replace$default(upperBound, (List) arrayList2, (Annotations) null, 2, (Object) null);
            }
            unwrappedType = KotlinTypeFactory.flexibleType(lowerBound, upperBound);
        } else if (unwrap instanceof SimpleType) {
            SimpleType simpleType = (SimpleType) unwrap;
            if (!simpleType.getConstructor().getParameters().isEmpty() && simpleType.getConstructor().getDeclarationDescriptor() != null) {
                List parameters3 = simpleType.getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters3, "constructor.parameters");
                Iterable<TypeParameterDescriptor> iterable3 = parameters3;
                Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                for (TypeParameterDescriptor starProjectionImpl3 : iterable3) {
                    arrayList3.add(new StarProjectionImpl(starProjectionImpl3));
                }
                simpleType = TypeSubstitutionKt.replace$default(simpleType, (List) arrayList3, (Annotations) null, 2, (Object) null);
            }
            unwrappedType = simpleType;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(unwrappedType, unwrap);
    }
}

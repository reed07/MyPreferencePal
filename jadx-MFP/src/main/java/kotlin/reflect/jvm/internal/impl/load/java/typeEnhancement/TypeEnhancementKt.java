package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeEnhancement.kt */
public final class TypeEnhancementKt {
    private static final EnhancedTypeAnnotations ENHANCED_MUTABILITY_ANNOTATIONS;
    private static final EnhancedTypeAnnotations ENHANCED_NULLABILITY_ANNOTATIONS;

    @Nullable
    public static final KotlinType enhance(@NotNull KotlinType kotlinType, @NotNull Function1<? super Integer, JavaTypeQualifiers> function1) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "qualifiers");
        return enhancePossiblyFlexible(kotlinType.unwrap(), function1, 0).getTypeIfChanged();
    }

    public static final boolean hasEnhancedNullability(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        Annotations annotations = kotlinType.getAnnotations();
        FqName fqName = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION");
        return annotations.findAnnotation(fqName) != null;
    }

    private static final Result enhancePossiblyFlexible(@NotNull UnwrappedType unwrappedType, Function1<? super Integer, JavaTypeQualifiers> function1, int i) {
        Result result;
        UnwrappedType unwrappedType2;
        KotlinType kotlinType = unwrappedType;
        boolean z = false;
        if (KotlinTypeKt.isError(kotlinType)) {
            return new Result(kotlinType, 1, false);
        }
        if (unwrappedType instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedType;
            SimpleResult enhanceInflexible = enhanceInflexible(flexibleType.getLowerBound(), function1, i, TypeComponentPosition.FLEXIBLE_LOWER);
            SimpleResult enhanceInflexible2 = enhanceInflexible(flexibleType.getUpperBound(), function1, i, TypeComponentPosition.FLEXIBLE_UPPER);
            boolean z2 = enhanceInflexible.getSubtreeSize() == enhanceInflexible2.getSubtreeSize();
            if (!_Assertions.ENABLED || z2) {
                if (enhanceInflexible.getWereChanges() || enhanceInflexible2.getWereChanges()) {
                    z = true;
                }
                KotlinType enhancement = TypeWithEnhancementKt.getEnhancement(enhanceInflexible.getType());
                if (enhancement == null) {
                    enhancement = TypeWithEnhancementKt.getEnhancement(enhanceInflexible2.getType());
                }
                if (z) {
                    if (unwrappedType instanceof RawTypeImpl) {
                        unwrappedType2 = new RawTypeImpl(enhanceInflexible.getType(), enhanceInflexible2.getType());
                    } else {
                        unwrappedType2 = KotlinTypeFactory.flexibleType(enhanceInflexible.getType(), enhanceInflexible2.getType());
                    }
                    unwrappedType = TypeWithEnhancementKt.wrapEnhancement(unwrappedType2, enhancement);
                }
                result = new Result(unwrappedType, enhanceInflexible.getSubtreeSize(), z);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Different tree sizes of bounds: ");
                sb.append("lower = (");
                sb.append(flexibleType.getLowerBound());
                sb.append(", ");
                sb.append(enhanceInflexible.getSubtreeSize());
                sb.append("), ");
                sb.append("upper = (");
                sb.append(flexibleType.getUpperBound());
                sb.append(", ");
                sb.append(enhanceInflexible2.getSubtreeSize());
                sb.append(')');
                throw new AssertionError(sb.toString());
            }
        } else if (unwrappedType instanceof SimpleType) {
            result = enhanceInflexible((SimpleType) unwrappedType, function1, i, TypeComponentPosition.INFLEXIBLE);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return result;
    }

    private static final SimpleResult enhanceInflexible(@NotNull SimpleType simpleType, Function1<? super Integer, JavaTypeQualifiers> function1, int i, TypeComponentPosition typeComponentPosition) {
        TypeProjection typeProjection;
        SimpleType simpleType2 = simpleType;
        Function1<? super Integer, JavaTypeQualifiers> function12 = function1;
        TypeComponentPosition typeComponentPosition2 = typeComponentPosition;
        if (!shouldEnhance(typeComponentPosition) && simpleType.getArguments().isEmpty()) {
            return new SimpleResult(simpleType2, 1, false);
        }
        ClassifierDescriptor declarationDescriptor = simpleType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            return new SimpleResult(simpleType2, 1, false);
        }
        Intrinsics.checkExpressionValueIsNotNull(declarationDescriptor, "constructor.declarationD…pleResult(this, 1, false)");
        JavaTypeQualifiers javaTypeQualifiers = (JavaTypeQualifiers) function12.invoke(Integer.valueOf(i));
        EnhancementResult enhanceMutability = enhanceMutability(declarationDescriptor, javaTypeQualifiers, typeComponentPosition2);
        ClassifierDescriptor classifierDescriptor = (ClassifierDescriptor) enhanceMutability.component1();
        Annotations component2 = enhanceMutability.component2();
        TypeConstructor typeConstructor = classifierDescriptor.getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "enhancedClassifier.typeConstructor");
        int i2 = i + 1;
        boolean z = component2 != null;
        Iterable arguments = simpleType.getArguments();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        int i3 = i2;
        int i4 = 0;
        for (Object next : arguments) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeProjection typeProjection2 = (TypeProjection) next;
            if (typeProjection2.isStarProjection()) {
                i3++;
                TypeConstructor typeConstructor2 = classifierDescriptor.getTypeConstructor();
                Intrinsics.checkExpressionValueIsNotNull(typeConstructor2, "enhancedClassifier.typeConstructor");
                typeProjection = TypeUtils.makeStarProjection((TypeParameterDescriptor) typeConstructor2.getParameters().get(i4));
            } else {
                Result enhancePossiblyFlexible = enhancePossiblyFlexible(typeProjection2.getType().unwrap(), function12, i3);
                z = z || enhancePossiblyFlexible.getWereChanges();
                i3 += enhancePossiblyFlexible.getSubtreeSize();
                KotlinType type = enhancePossiblyFlexible.getType();
                Variance projectionKind = typeProjection2.getProjectionKind();
                Intrinsics.checkExpressionValueIsNotNull(projectionKind, "arg.projectionKind");
                typeProjection = TypeUtilsKt.createProjection(type, projectionKind, (TypeParameterDescriptor) typeConstructor.getParameters().get(i4));
            }
            arrayList.add(typeProjection);
            i4 = i5;
        }
        List list = (List) arrayList;
        EnhancementResult enhancedNullability = getEnhancedNullability(simpleType2, javaTypeQualifiers, typeComponentPosition2);
        boolean booleanValue = ((Boolean) enhancedNullability.component1()).booleanValue();
        Annotations component22 = enhancedNullability.component2();
        int i6 = i3 - i;
        if (!(z || component22 != null)) {
            return new SimpleResult(simpleType2, i6, false);
        }
        SimpleType simpleType3 = KotlinTypeFactory.simpleType(compositeAnnotationsOrSingle(CollectionsKt.filterNotNull(CollectionsKt.listOf(simpleType.getAnnotations(), component2, component22))), typeConstructor, list, booleanValue);
        if (javaTypeQualifiers.isNotNullTypeParameter$descriptors_jvm()) {
            simpleType3 = new NotNullTypeParameter(simpleType3);
        }
        UnwrappedType wrapEnhancement = component22 != null && javaTypeQualifiers.isNullabilityQualifierForWarning$descriptors_jvm() ? TypeWithEnhancementKt.wrapEnhancement(simpleType2, simpleType3) : simpleType3;
        if (wrapEnhancement != null) {
            return new SimpleResult((SimpleType) wrapEnhancement, i6, true);
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }

    private static final Annotations compositeAnnotationsOrSingle(@NotNull List<? extends Annotations> list) {
        switch (list.size()) {
            case 0:
                throw new IllegalStateException("At least one Annotations object expected".toString());
            case 1:
                return (Annotations) CollectionsKt.single(list);
            default:
                return new CompositeAnnotations(CollectionsKt.toList(list));
        }
    }

    private static final boolean shouldEnhance(@NotNull TypeComponentPosition typeComponentPosition) {
        return typeComponentPosition != TypeComponentPosition.INFLEXIBLE;
    }

    private static final <T> EnhancementResult<T> noChange(T t) {
        return new EnhancementResult<>(t, null);
    }

    private static final <T> EnhancementResult<T> enhancedNullability(T t) {
        return new EnhancementResult<>(t, ENHANCED_NULLABILITY_ANNOTATIONS);
    }

    private static final <T> EnhancementResult<T> enhancedMutability(T t) {
        return new EnhancementResult<>(t, ENHANCED_MUTABILITY_ANNOTATIONS);
    }

    private static final EnhancementResult<ClassifierDescriptor> enhanceMutability(@NotNull ClassifierDescriptor classifierDescriptor, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        if (!shouldEnhance(typeComponentPosition)) {
            return noChange(classifierDescriptor);
        }
        if (!(classifierDescriptor instanceof ClassDescriptor)) {
            return noChange(classifierDescriptor);
        }
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        MutabilityQualifier mutability = javaTypeQualifiers.getMutability();
        if (mutability != null) {
            switch (mutability) {
                case READ_ONLY:
                    if (typeComponentPosition == TypeComponentPosition.FLEXIBLE_LOWER) {
                        ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptor;
                        if (javaToKotlinClassMap.isMutable(classDescriptor)) {
                            return enhancedMutability(javaToKotlinClassMap.convertMutableToReadOnly(classDescriptor));
                        }
                    }
                    break;
                case MUTABLE:
                    if (typeComponentPosition == TypeComponentPosition.FLEXIBLE_UPPER) {
                        ClassDescriptor classDescriptor2 = (ClassDescriptor) classifierDescriptor;
                        if (javaToKotlinClassMap.isReadOnly(classDescriptor2)) {
                            return enhancedMutability(javaToKotlinClassMap.convertReadOnlyToMutable(classDescriptor2));
                        }
                    }
                    break;
            }
        }
        return noChange(classifierDescriptor);
    }

    private static final EnhancementResult<Boolean> getEnhancedNullability(@NotNull KotlinType kotlinType, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        EnhancementResult<Boolean> enhancementResult;
        if (!shouldEnhance(typeComponentPosition)) {
            return noChange(Boolean.valueOf(kotlinType.isMarkedNullable()));
        }
        NullabilityQualifier nullability = javaTypeQualifiers.getNullability();
        if (nullability != null) {
            switch (nullability) {
                case NULLABLE:
                    enhancementResult = enhancedNullability(Boolean.valueOf(true));
                    break;
                case NOT_NULL:
                    enhancementResult = enhancedNullability(Boolean.valueOf(false));
                    break;
            }
        }
        enhancementResult = noChange(Boolean.valueOf(kotlinType.isMarkedNullable()));
        return enhancementResult;
    }

    static {
        FqName fqName = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION");
        ENHANCED_NULLABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(fqName);
        FqName fqName2 = JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION");
        ENHANCED_MUTABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(fqName2);
    }
}

package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModalityKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StubType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SeveralSupertypesWithSameConstructorPolicy;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.LowerIfFlexible;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.LowerIfFlexibleWithCustomSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.None;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NewKotlinTypeChecker.kt */
public final class NewKotlinTypeChecker implements KotlinTypeChecker {
    public static final NewKotlinTypeChecker INSTANCE = new NewKotlinTypeChecker();

    private NewKotlinTypeChecker() {
    }

    public boolean isSubtypeOf(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "subtype");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "supertype");
        return isSubtypeOf(new TypeCheckerContext(true, false, 2, null), kotlinType.unwrap(), kotlinType2.unwrap());
    }

    public boolean equalTypes(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "a");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "b");
        return equalTypes(new TypeCheckerContext(false, false, 2, null), kotlinType.unwrap(), kotlinType2.unwrap());
    }

    public final boolean equalTypes(@NotNull TypeCheckerContext typeCheckerContext, @NotNull UnwrappedType unwrappedType, @NotNull UnwrappedType unwrappedType2) {
        Intrinsics.checkParameterIsNotNull(typeCheckerContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(unwrappedType, "a");
        Intrinsics.checkParameterIsNotNull(unwrappedType2, "b");
        boolean z = true;
        if (unwrappedType == unwrappedType2) {
            return true;
        }
        KotlinType kotlinType = unwrappedType;
        if (isCommonDenotableType(kotlinType)) {
            KotlinType kotlinType2 = unwrappedType2;
            if (isCommonDenotableType(kotlinType2)) {
                if (!typeCheckerContext.areEqualTypeConstructors(unwrappedType.getConstructor(), unwrappedType2.getConstructor())) {
                    return false;
                }
                if (unwrappedType.getArguments().isEmpty()) {
                    if (hasFlexibleNullability(kotlinType) || hasFlexibleNullability(kotlinType2)) {
                        return true;
                    }
                    if (unwrappedType.isMarkedNullable() != unwrappedType2.isMarkedNullable()) {
                        z = false;
                    }
                    return z;
                }
            }
        }
        if (!isSubtypeOf(typeCheckerContext, unwrappedType, unwrappedType2) || !isSubtypeOf(typeCheckerContext, unwrappedType2, unwrappedType)) {
            z = false;
        }
        return z;
    }

    private final boolean hasFlexibleNullability(@NotNull KotlinType kotlinType) {
        return FlexibleTypesKt.lowerIfFlexible(kotlinType).isMarkedNullable() != FlexibleTypesKt.upperIfFlexible(kotlinType).isMarkedNullable();
    }

    private final boolean isCommonDenotableType(KotlinType kotlinType) {
        return kotlinType.getConstructor().isDenotable() && !DynamicTypesKt.isDynamic(kotlinType) && !SpecialTypesKt.isDefinitelyNotNullType(kotlinType) && Intrinsics.areEqual((Object) FlexibleTypesKt.lowerIfFlexible(kotlinType).getConstructor(), (Object) FlexibleTypesKt.upperIfFlexible(kotlinType).getConstructor());
    }

    public final boolean isSubtypeOf(@NotNull TypeCheckerContext typeCheckerContext, @NotNull UnwrappedType unwrappedType, @NotNull UnwrappedType unwrappedType2) {
        Intrinsics.checkParameterIsNotNull(typeCheckerContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(unwrappedType, "subType");
        Intrinsics.checkParameterIsNotNull(unwrappedType2, "superType");
        if (unwrappedType == unwrappedType2) {
            return true;
        }
        UnwrappedType transformToNewType = transformToNewType(unwrappedType);
        UnwrappedType transformToNewType2 = transformToNewType(unwrappedType2);
        KotlinType kotlinType = transformToNewType;
        KotlinType kotlinType2 = transformToNewType2;
        Boolean checkSubtypeForSpecialCases = checkSubtypeForSpecialCases(typeCheckerContext, FlexibleTypesKt.lowerIfFlexible(kotlinType), FlexibleTypesKt.upperIfFlexible(kotlinType2));
        if (checkSubtypeForSpecialCases != null) {
            boolean booleanValue = checkSubtypeForSpecialCases.booleanValue();
            typeCheckerContext.addSubtypeConstraint(transformToNewType, transformToNewType2);
            return booleanValue;
        }
        Boolean addSubtypeConstraint = typeCheckerContext.addSubtypeConstraint(transformToNewType, transformToNewType2);
        if (addSubtypeConstraint != null) {
            return addSubtypeConstraint.booleanValue();
        }
        return isSubtypeOfForSingleClassifierType(typeCheckerContext, FlexibleTypesKt.lowerIfFlexible(kotlinType), FlexibleTypesKt.upperIfFlexible(kotlinType2));
    }

    @NotNull
    public final SimpleType transformToNewType(@NotNull SimpleType simpleType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "type");
        TypeConstructor constructor = simpleType.getConstructor();
        boolean z = false;
        if (constructor instanceof CapturedTypeConstructor) {
            CapturedTypeConstructor capturedTypeConstructor = (CapturedTypeConstructor) constructor;
            TypeProjection typeProjection = capturedTypeConstructor.getTypeProjection();
            if (typeProjection.getProjectionKind() == Variance.IN_VARIANCE) {
                z = true;
            }
            UnwrappedType unwrappedType = null;
            if (!z) {
                typeProjection = null;
            }
            if (typeProjection != null) {
                KotlinType type = typeProjection.getType();
                if (type != null) {
                    unwrappedType = type.unwrap();
                }
            }
            UnwrappedType unwrappedType2 = unwrappedType;
            if (capturedTypeConstructor.getNewTypeConstructor() == null) {
                TypeProjection typeProjection2 = capturedTypeConstructor.getTypeProjection();
                Iterable<KotlinType> supertypes = capturedTypeConstructor.getSupertypes();
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(supertypes, 10));
                for (KotlinType unwrap : supertypes) {
                    arrayList.add(unwrap.unwrap());
                }
                capturedTypeConstructor.setNewTypeConstructor(new NewCapturedTypeConstructor(typeProjection2, (List) arrayList));
            }
            CaptureStatus captureStatus = CaptureStatus.FOR_SUBTYPING;
            NewCapturedTypeConstructor newTypeConstructor = capturedTypeConstructor.getNewTypeConstructor();
            if (newTypeConstructor == null) {
                Intrinsics.throwNpe();
            }
            NewCapturedType newCapturedType = new NewCapturedType(captureStatus, newTypeConstructor, unwrappedType2, simpleType.getAnnotations(), simpleType.isMarkedNullable());
            return newCapturedType;
        } else if (constructor instanceof IntegerValueTypeConstructor) {
            Iterable<KotlinType> supertypes2 = ((IntegerValueTypeConstructor) constructor).getSupertypes();
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(supertypes2, 10));
            for (KotlinType makeNullableAsSpecified : supertypes2) {
                arrayList2.add(TypeUtils.makeNullableAsSpecified(makeNullableAsSpecified, simpleType.isMarkedNullable()));
            }
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(simpleType.getAnnotations(), new IntersectionTypeConstructor((List) arrayList2), CollectionsKt.emptyList(), false, simpleType.getMemberScope());
        } else if (!(constructor instanceof IntersectionTypeConstructor) || !simpleType.isMarkedNullable()) {
            return simpleType;
        } else {
            Collection supertypes3 = ((IntersectionTypeConstructor) constructor).getSupertypes();
            Intrinsics.checkExpressionValueIsNotNull(supertypes3, "constructor.supertypes");
            Iterable<KotlinType> iterable = supertypes3;
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (KotlinType kotlinType : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(kotlinType, "it");
                arrayList3.add(TypeUtilsKt.makeNullable(kotlinType));
            }
            IntersectionTypeConstructor intersectionTypeConstructor = new IntersectionTypeConstructor((List) arrayList3);
            Annotations annotations = simpleType.getAnnotations();
            TypeConstructor typeConstructor = intersectionTypeConstructor;
            List emptyList = CollectionsKt.emptyList();
            MemberScope createScopeForKotlinType = intersectionTypeConstructor.createScopeForKotlinType();
            Intrinsics.checkExpressionValueIsNotNull(createScopeForKotlinType, "newConstructor.createScopeForKotlinType()");
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, typeConstructor, emptyList, false, createScopeForKotlinType);
        }
    }

    @NotNull
    public final UnwrappedType transformToNewType(@NotNull UnwrappedType unwrappedType) {
        UnwrappedType unwrappedType2;
        Intrinsics.checkParameterIsNotNull(unwrappedType, "type");
        if (unwrappedType instanceof SimpleType) {
            unwrappedType2 = transformToNewType((SimpleType) unwrappedType);
        } else if (unwrappedType instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedType;
            SimpleType transformToNewType = transformToNewType(flexibleType.getLowerBound());
            SimpleType transformToNewType2 = transformToNewType(flexibleType.getUpperBound());
            if (transformToNewType == flexibleType.getLowerBound() && transformToNewType2 == flexibleType.getUpperBound()) {
                unwrappedType2 = unwrappedType;
            } else {
                unwrappedType2 = KotlinTypeFactory.flexibleType(transformToNewType, transformToNewType2);
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(unwrappedType2, unwrappedType);
    }

    private final Boolean checkSubtypeForSpecialCases(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType, SimpleType simpleType2) {
        boolean z = true;
        if (KotlinTypeKt.isError(simpleType) || KotlinTypeKt.isError(simpleType2)) {
            if (typeCheckerContext.getErrorTypeEqualsToAnything()) {
                return Boolean.valueOf(true);
            }
            if (!simpleType.isMarkedNullable() || simpleType2.isMarkedNullable()) {
                return Boolean.valueOf(StrictEqualityTypeChecker.INSTANCE.strictEqualTypes(simpleType.makeNullableAsSpecified(false), simpleType2.makeNullableAsSpecified(false)));
            }
            return Boolean.valueOf(false);
        } else if ((simpleType instanceof StubType) || (simpleType2 instanceof StubType)) {
            return Boolean.valueOf(true);
        } else {
            if (simpleType2 instanceof NewCapturedType) {
                NewCapturedType newCapturedType = (NewCapturedType) simpleType2;
                if (newCapturedType.getLowerType() != null) {
                    switch (typeCheckerContext.getLowerCapturedTypePolicy(simpleType, newCapturedType)) {
                        case CHECK_ONLY_LOWER:
                            return Boolean.valueOf(isSubtypeOf(typeCheckerContext, simpleType, newCapturedType.getLowerType()));
                        case CHECK_SUBTYPE_AND_LOWER:
                            if (isSubtypeOf(typeCheckerContext, simpleType, newCapturedType.getLowerType())) {
                                return Boolean.valueOf(true);
                            }
                            break;
                    }
                }
            }
            TypeConstructor constructor = simpleType2.getConstructor();
            if (!(constructor instanceof IntersectionTypeConstructor)) {
                constructor = null;
            }
            IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) constructor;
            if (intersectionTypeConstructor == null) {
                return null;
            }
            boolean z2 = !simpleType2.isMarkedNullable();
            if (!_Assertions.ENABLED || z2) {
                Collection supertypes = intersectionTypeConstructor.getSupertypes();
                Intrinsics.checkExpressionValueIsNotNull(supertypes, "it.supertypes");
                Iterable iterable = supertypes;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    Iterator it = iterable.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!INSTANCE.isSubtypeOf(typeCheckerContext, simpleType, ((KotlinType) it.next()).unwrap())) {
                                z = false;
                            }
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Intersection type should not be marked nullable!: ");
            sb.append(simpleType2);
            throw new AssertionError(sb.toString());
        }
    }

    private final boolean isSubtypeOfForSingleClassifierType(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType, SimpleType simpleType2) {
        boolean z;
        TypeCheckerContext typeCheckerContext2 = typeCheckerContext;
        SimpleType simpleType3 = simpleType;
        SimpleType simpleType4 = simpleType2;
        boolean z2 = NewKotlinTypeCheckerKt.isSingleClassifierType(simpleType) || NewKotlinTypeCheckerKt.isIntersectionType(simpleType) || typeCheckerContext2.isAllowedTypeVariable(simpleType3);
        if (!_Assertions.ENABLED || z2) {
            boolean z3 = NewKotlinTypeCheckerKt.isSingleClassifierType(simpleType2) || typeCheckerContext2.isAllowedTypeVariable(simpleType4);
            if (_Assertions.ENABLED && !z3) {
                StringBuilder sb = new StringBuilder();
                sb.append("Not singleClassifierType superType: ");
                sb.append(simpleType4);
                throw new AssertionError(sb.toString());
            } else if (!NullabilityChecker.INSTANCE.isPossibleSubtype(typeCheckerContext2, simpleType3, simpleType4)) {
                return false;
            } else {
                TypeConstructor constructor = simpleType2.getConstructor();
                if ((Intrinsics.areEqual((Object) simpleType.getConstructor(), (Object) constructor) && constructor.getParameters().isEmpty()) || TypeUtilsKt.isAnyOrNullableAny(simpleType4)) {
                    return true;
                }
                List findCorrespondingSupertypes = findCorrespondingSupertypes(typeCheckerContext2, simpleType3, constructor);
                switch (findCorrespondingSupertypes.size()) {
                    case 0:
                        return hasNothingSupertype(typeCheckerContext, simpleType);
                    case 1:
                        return isSubtypeForSameConstructor(typeCheckerContext2, ((SimpleType) CollectionsKt.first(findCorrespondingSupertypes)).getArguments(), simpleType4);
                    default:
                        switch (typeCheckerContext.getSameConstructorPolicy()) {
                            case FORCE_NOT_SUBTYPE:
                                return false;
                            case TAKE_FIRST_FOR_SUBTYPING:
                                return isSubtypeForSameConstructor(typeCheckerContext2, ((SimpleType) CollectionsKt.first(findCorrespondingSupertypes)).getArguments(), simpleType4);
                            case CHECK_ANY_OF_THEM:
                            case INTERSECT_ARGUMENTS_AND_CHECK_AGAIN:
                                Iterable iterable = findCorrespondingSupertypes;
                                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                                    Iterator it = iterable.iterator();
                                    while (true) {
                                        if (it.hasNext()) {
                                            if (INSTANCE.isSubtypeForSameConstructor(typeCheckerContext2, ((SimpleType) it.next()).getArguments(), simpleType4)) {
                                                z = true;
                                            }
                                        } else {
                                            z = false;
                                        }
                                    }
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    return true;
                                }
                                break;
                        }
                        if (typeCheckerContext.getSameConstructorPolicy() != SeveralSupertypesWithSameConstructorPolicy.INTERSECT_ARGUMENTS_AND_CHECK_AGAIN) {
                            return false;
                        }
                        List parameters = constructor.getParameters();
                        Intrinsics.checkExpressionValueIsNotNull(parameters, "superConstructor.parameters");
                        Iterable iterable2 = parameters;
                        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                        int i = 0;
                        for (Object next : iterable2) {
                            int i2 = i + 1;
                            if (i < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) next;
                            Iterable<SimpleType> iterable3 = findCorrespondingSupertypes;
                            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                            for (SimpleType simpleType5 : iterable3) {
                                TypeProjection typeProjection = (TypeProjection) CollectionsKt.getOrNull(simpleType5.getArguments(), i);
                                if (typeProjection != null) {
                                    if (!(typeProjection.getProjectionKind() == Variance.INVARIANT)) {
                                        typeProjection = null;
                                    }
                                    if (typeProjection != null) {
                                        KotlinType type = typeProjection.getType();
                                        if (type != null) {
                                            UnwrappedType unwrap = type.unwrap();
                                            if (unwrap != null) {
                                                arrayList2.add(unwrap);
                                            }
                                        }
                                    }
                                }
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Incorrect type: ");
                                sb2.append(simpleType5);
                                sb2.append(", subType: ");
                                sb2.append(simpleType3);
                                sb2.append(", superType: ");
                                sb2.append(simpleType4);
                                throw new IllegalStateException(sb2.toString().toString());
                            }
                            arrayList.add(TypeUtilsKt.asTypeProjection(IntersectionTypeKt.intersectTypes((List) arrayList2)));
                            i = i2;
                        }
                        return isSubtypeForSameConstructor(typeCheckerContext2, (List) arrayList, simpleType4);
                }
            }
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Not singleClassifierType and not intersection subType: ");
            sb3.append(simpleType3);
            throw new AssertionError(sb3.toString());
        }
    }

    private final List<SimpleType> collectAndFilter(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType, TypeConstructor typeConstructor) {
        return selectOnlyPureKotlinSupertypes(collectAllSupertypesWithGivenTypeConstructor(typeCheckerContext, simpleType, typeConstructor));
    }

    @NotNull
    public final List<SimpleType> findCorrespondingSupertypes(@NotNull TypeCheckerContext typeCheckerContext, @NotNull SimpleType simpleType, @NotNull TypeConstructor typeConstructor) {
        SupertypesPolicy supertypesPolicy;
        Intrinsics.checkParameterIsNotNull(typeCheckerContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(simpleType, "baseType");
        Intrinsics.checkParameterIsNotNull(typeConstructor, "constructor");
        if (NewKotlinTypeCheckerKt.isClassType(simpleType)) {
            return collectAndFilter(typeCheckerContext, simpleType, typeConstructor);
        }
        if (!(typeConstructor.getDeclarationDescriptor() instanceof ClassDescriptor)) {
            return collectAllSupertypesWithGivenTypeConstructor(typeCheckerContext, simpleType, typeConstructor);
        }
        SmartList smartList = new SmartList();
        typeCheckerContext.initialize();
        ArrayDeque access$getSupertypesDeque$p = typeCheckerContext.supertypesDeque;
        if (access$getSupertypesDeque$p == null) {
            Intrinsics.throwNpe();
        }
        Set access$getSupertypesSet$p = typeCheckerContext.supertypesSet;
        if (access$getSupertypesSet$p == null) {
            Intrinsics.throwNpe();
        }
        access$getSupertypesDeque$p.push(simpleType);
        while (!access$getSupertypesDeque$p.isEmpty()) {
            if (access$getSupertypesSet$p.size() <= 1000) {
                SimpleType simpleType2 = (SimpleType) access$getSupertypesDeque$p.pop();
                Intrinsics.checkExpressionValueIsNotNull(simpleType2, "current");
                if (access$getSupertypesSet$p.add(simpleType2)) {
                    if (NewKotlinTypeCheckerKt.isClassType(simpleType2)) {
                        smartList.add(simpleType2);
                        supertypesPolicy = None.INSTANCE;
                    } else {
                        supertypesPolicy = LowerIfFlexible.INSTANCE;
                    }
                    if (!(!Intrinsics.areEqual((Object) supertypesPolicy, (Object) None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinType kotlinType : simpleType2.getConstructor().getSupertypes()) {
                            Intrinsics.checkExpressionValueIsNotNull(kotlinType, "supertype");
                            access$getSupertypesDeque$p.add(supertypesPolicy.transformType(kotlinType));
                        }
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Too many supertypes for type: ");
                sb.append(simpleType);
                sb.append(". Supertypes = ");
                sb.append(CollectionsKt.joinToString$default(access$getSupertypesSet$p, null, null, null, 0, null, null, 63, null));
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        typeCheckerContext.clear();
        Iterable<SimpleType> iterable = smartList;
        Collection arrayList = new ArrayList();
        for (SimpleType simpleType3 : iterable) {
            NewKotlinTypeChecker newKotlinTypeChecker = INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(simpleType3, "it");
            CollectionsKt.addAll(arrayList, (Iterable<? extends T>) newKotlinTypeChecker.collectAndFilter(typeCheckerContext, simpleType3, typeConstructor));
        }
        return (List) arrayList;
    }

    private final List<SimpleType> collectAllSupertypesWithGivenTypeConstructor(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType, TypeConstructor typeConstructor) {
        SupertypesPolicy supertypesPolicy;
        List<SimpleType> list;
        TypeCheckerContext typeCheckerContext2 = typeCheckerContext;
        SimpleType simpleType2 = simpleType;
        TypeConstructor typeConstructor2 = typeConstructor;
        ClassifierDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            declarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
        if (classDescriptor != null) {
            if (isCommonFinalClass(classDescriptor)) {
                if (typeCheckerContext2.areEqualTypeConstructors(simpleType.getConstructor(), typeConstructor2)) {
                    SimpleType captureFromArguments$default = NewCapturedTypeKt.captureFromArguments$default(simpleType2, CaptureStatus.FOR_SUBTYPING, null, 4, null);
                    if (captureFromArguments$default == null) {
                        captureFromArguments$default = simpleType2;
                    }
                    list = CollectionsKt.listOf(captureFromArguments$default);
                } else {
                    list = CollectionsKt.emptyList();
                }
                return list;
            }
        }
        List<SimpleType> smartList = new SmartList<>();
        typeCheckerContext.initialize();
        ArrayDeque access$getSupertypesDeque$p = typeCheckerContext.supertypesDeque;
        if (access$getSupertypesDeque$p == null) {
            Intrinsics.throwNpe();
        }
        Set access$getSupertypesSet$p = typeCheckerContext.supertypesSet;
        if (access$getSupertypesSet$p == null) {
            Intrinsics.throwNpe();
        }
        access$getSupertypesDeque$p.push(simpleType2);
        while (!access$getSupertypesDeque$p.isEmpty()) {
            if (access$getSupertypesSet$p.size() <= 1000) {
                SimpleType simpleType3 = (SimpleType) access$getSupertypesDeque$p.pop();
                Intrinsics.checkExpressionValueIsNotNull(simpleType3, "current");
                if (access$getSupertypesSet$p.add(simpleType3)) {
                    SimpleType captureFromArguments$default2 = NewCapturedTypeKt.captureFromArguments$default(simpleType3, CaptureStatus.FOR_SUBTYPING, null, 4, null);
                    if (captureFromArguments$default2 == null) {
                        captureFromArguments$default2 = simpleType3;
                    }
                    if (typeCheckerContext2.areEqualTypeConstructors(captureFromArguments$default2.getConstructor(), typeConstructor2)) {
                        smartList.add(captureFromArguments$default2);
                        supertypesPolicy = None.INSTANCE;
                    } else if (captureFromArguments$default2.getArguments().isEmpty()) {
                        supertypesPolicy = LowerIfFlexible.INSTANCE;
                    } else {
                        supertypesPolicy = new LowerIfFlexibleWithCustomSubstitutor(TypeConstructorSubstitution.Companion.create(captureFromArguments$default2).buildSubstitutor());
                    }
                    if (!(!Intrinsics.areEqual((Object) supertypesPolicy, (Object) None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinType kotlinType : simpleType3.getConstructor().getSupertypes()) {
                            Intrinsics.checkExpressionValueIsNotNull(kotlinType, "supertype");
                            access$getSupertypesDeque$p.add(supertypesPolicy.transformType(kotlinType));
                        }
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Too many supertypes for type: ");
                sb.append(simpleType2);
                sb.append(". Supertypes = ");
                sb.append(CollectionsKt.joinToString$default(access$getSupertypesSet$p, null, null, null, 0, null, null, 63, null));
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        typeCheckerContext.clear();
        return smartList;
    }

    private final boolean isCommonFinalClass(@NotNull ClassDescriptor classDescriptor) {
        return (!ModalityKt.isFinalClass(classDescriptor) || classDescriptor.getKind() == ClassKind.ENUM_ENTRY || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) ? false : true;
    }

    private final List<SimpleType> selectOnlyPureKotlinSupertypes(List<? extends SimpleType> list) {
        if (list.size() < 2) {
            return list;
        }
        Iterable iterable = list;
        Collection arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            Iterable arguments = ((SimpleType) next).getArguments();
            if (!(arguments instanceof Collection) || !((Collection) arguments).isEmpty()) {
                Iterator it2 = arguments.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    KotlinType type = ((TypeProjection) it2.next()).getType();
                    Intrinsics.checkExpressionValueIsNotNull(type, "it.type");
                    if (!(!FlexibleTypesKt.isFlexible(type))) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                arrayList.add(next);
            }
        }
        List<? extends SimpleType> list2 = (List) arrayList;
        if (!list2.isEmpty()) {
            list = list2;
        }
        return list;
    }

    @Nullable
    public final Variance effectiveVariance(@NotNull Variance variance, @NotNull Variance variance2) {
        Intrinsics.checkParameterIsNotNull(variance, "declared");
        Intrinsics.checkParameterIsNotNull(variance2, "useSite");
        if (variance == Variance.INVARIANT) {
            return variance2;
        }
        if (variance2 == Variance.INVARIANT || variance == variance2) {
            return variance;
        }
        return null;
    }

    private final boolean isSubtypeForSameConstructor(@NotNull TypeCheckerContext typeCheckerContext, List<? extends TypeProjection> list, SimpleType simpleType) {
        boolean z;
        if (list == simpleType.getArguments()) {
            return true;
        }
        List parameters = simpleType.getConstructor().getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "superType.constructor.parameters");
        int size = parameters.size();
        for (int i = 0; i < size; i++) {
            TypeProjection typeProjection = (TypeProjection) simpleType.getArguments().get(i);
            if (!typeProjection.isStarProjection()) {
                UnwrappedType unwrap = typeProjection.getType().unwrap();
                TypeProjection typeProjection2 = (TypeProjection) list.get(i);
                boolean z2 = typeProjection2.getProjectionKind() == Variance.INVARIANT;
                if (!_Assertions.ENABLED || z2) {
                    UnwrappedType unwrap2 = typeProjection2.getType().unwrap();
                    Object obj = parameters.get(i);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "parameters[index]");
                    Variance variance = ((TypeParameterDescriptor) obj).getVariance();
                    Intrinsics.checkExpressionValueIsNotNull(variance, "parameters[index].variance");
                    Variance projectionKind = typeProjection.getProjectionKind();
                    Intrinsics.checkExpressionValueIsNotNull(projectionKind, "superProjection.projectionKind");
                    Variance effectiveVariance = effectiveVariance(variance, projectionKind);
                    if (effectiveVariance == null) {
                        return typeCheckerContext.getErrorTypeEqualsToAnything();
                    }
                    if (typeCheckerContext.argumentsDepth <= 100) {
                        typeCheckerContext.argumentsDepth = typeCheckerContext.argumentsDepth + 1;
                        switch (effectiveVariance) {
                            case INVARIANT:
                                z = INSTANCE.equalTypes(typeCheckerContext, unwrap2, unwrap);
                                break;
                            case OUT_VARIANCE:
                                z = INSTANCE.isSubtypeOf(typeCheckerContext, unwrap2, unwrap);
                                break;
                            case IN_VARIANCE:
                                z = INSTANCE.isSubtypeOf(typeCheckerContext, unwrap, unwrap2);
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                        typeCheckerContext.argumentsDepth = typeCheckerContext.argumentsDepth - 1;
                        if (!z) {
                            return false;
                        }
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Arguments depth is too high. Some related argument: ");
                        sb.append(unwrap2);
                        throw new IllegalStateException(sb.toString().toString());
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Incorrect sub argument: ");
                    sb2.append(typeProjection2);
                    throw new AssertionError(sb2.toString());
                }
            }
        }
        return true;
    }

    private final boolean hasNothingSupertype(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType) {
        SupertypesPolicy supertypesPolicy;
        if (KotlinBuiltIns.isNothingOrNullableNothing(simpleType)) {
            return true;
        }
        typeCheckerContext.initialize();
        ArrayDeque access$getSupertypesDeque$p = typeCheckerContext.supertypesDeque;
        if (access$getSupertypesDeque$p == null) {
            Intrinsics.throwNpe();
        }
        Set access$getSupertypesSet$p = typeCheckerContext.supertypesSet;
        if (access$getSupertypesSet$p == null) {
            Intrinsics.throwNpe();
        }
        access$getSupertypesDeque$p.push(simpleType);
        while (!access$getSupertypesDeque$p.isEmpty()) {
            if (access$getSupertypesSet$p.size() <= 1000) {
                SimpleType simpleType2 = (SimpleType) access$getSupertypesDeque$p.pop();
                Intrinsics.checkExpressionValueIsNotNull(simpleType2, "current");
                if (access$getSupertypesSet$p.add(simpleType2)) {
                    if (NewKotlinTypeCheckerKt.isClassType(simpleType2)) {
                        supertypesPolicy = None.INSTANCE;
                    } else {
                        supertypesPolicy = LowerIfFlexible.INSTANCE;
                    }
                    if (!(!Intrinsics.areEqual((Object) supertypesPolicy, (Object) None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinType kotlinType : simpleType2.getConstructor().getSupertypes()) {
                            Intrinsics.checkExpressionValueIsNotNull(kotlinType, "supertype");
                            SimpleType transformType = supertypesPolicy.transformType(kotlinType);
                            if (KotlinBuiltIns.isNothingOrNullableNothing(transformType)) {
                                typeCheckerContext.clear();
                                return true;
                            }
                            access$getSupertypesDeque$p.add(transformType);
                        }
                        continue;
                    } else {
                        continue;
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Too many supertypes for type: ");
                sb.append(simpleType);
                sb.append(". Supertypes = ");
                sb.append(CollectionsKt.joinToString$default(access$getSupertypesSet$p, null, null, null, 0, null, null, 63, null));
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        typeCheckerContext.clear();
        return false;
    }
}

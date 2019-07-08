package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.LowerIfFlexible;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.None;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.UpperIfFlexible;
import org.jetbrains.annotations.NotNull;

/* compiled from: NewKotlinTypeChecker.kt */
public final class NullabilityChecker {
    public static final NullabilityChecker INSTANCE = new NullabilityChecker();

    private NullabilityChecker() {
    }

    public final boolean isPossibleSubtype(@NotNull TypeCheckerContext typeCheckerContext, @NotNull SimpleType simpleType, @NotNull SimpleType simpleType2) {
        Intrinsics.checkParameterIsNotNull(typeCheckerContext, "context");
        Intrinsics.checkParameterIsNotNull(simpleType, "subType");
        Intrinsics.checkParameterIsNotNull(simpleType2, "superType");
        return runIsPossibleSubtype(typeCheckerContext, simpleType, simpleType2);
    }

    public final boolean isSubtypeOfAny(@NotNull UnwrappedType unwrappedType) {
        Intrinsics.checkParameterIsNotNull(unwrappedType, "type");
        return hasNotNullSupertype(new TypeCheckerContext(false, false, 2, null), FlexibleTypesKt.lowerIfFlexible(unwrappedType), LowerIfFlexible.INSTANCE);
    }

    private final boolean runIsPossibleSubtype(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType, SimpleType simpleType2) {
        boolean z = NewKotlinTypeCheckerKt.isIntersectionType(simpleType) || NewKotlinTypeCheckerKt.isSingleClassifierType(simpleType) || typeCheckerContext.isAllowedTypeVariable(simpleType);
        if (!_Assertions.ENABLED || z) {
            boolean z2 = NewKotlinTypeCheckerKt.isSingleClassifierType(simpleType2) || typeCheckerContext.isAllowedTypeVariable(simpleType2);
            if (_Assertions.ENABLED && !z2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Not singleClassifierType superType: ");
                sb.append(simpleType2);
                throw new AssertionError(sb.toString());
            } else if (simpleType2.isMarkedNullable() || SpecialTypesKt.isDefinitelyNotNullType(simpleType) || hasNotNullSupertype(typeCheckerContext, simpleType, LowerIfFlexible.INSTANCE)) {
                return true;
            } else {
                if (!SpecialTypesKt.isDefinitelyNotNullType(simpleType2) && !hasNotNullSupertype(typeCheckerContext, simpleType2, UpperIfFlexible.INSTANCE) && !NewKotlinTypeCheckerKt.isClassType(simpleType)) {
                    return hasPathByNotMarkedNullableNodes(typeCheckerContext, simpleType, simpleType2.getConstructor());
                }
                return false;
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Not singleClassifierType superType: ");
            sb2.append(simpleType2);
            throw new AssertionError(sb2.toString());
        }
    }

    private final boolean hasNotNullSupertype(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType, SupertypesPolicy supertypesPolicy) {
        SimpleType simpleType2 = simpleType;
        if ((NewKotlinTypeCheckerKt.isClassType(simpleType) && !simpleType.isMarkedNullable()) || SpecialTypesKt.isDefinitelyNotNullType(simpleType2)) {
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
        access$getSupertypesDeque$p.push(simpleType2);
        while (!access$getSupertypesDeque$p.isEmpty()) {
            if (access$getSupertypesSet$p.size() <= 1000) {
                SimpleType simpleType3 = (SimpleType) access$getSupertypesDeque$p.pop();
                Intrinsics.checkExpressionValueIsNotNull(simpleType3, "current");
                if (access$getSupertypesSet$p.add(simpleType3)) {
                    SupertypesPolicy supertypesPolicy2 = simpleType3.isMarkedNullable() ? None.INSTANCE : supertypesPolicy;
                    if (!(!Intrinsics.areEqual((Object) supertypesPolicy2, (Object) None.INSTANCE))) {
                        supertypesPolicy2 = null;
                    }
                    if (supertypesPolicy2 != null) {
                        for (KotlinType kotlinType : simpleType3.getConstructor().getSupertypes()) {
                            Intrinsics.checkExpressionValueIsNotNull(kotlinType, "supertype");
                            SimpleType transformType = supertypesPolicy2.transformType(kotlinType);
                            if ((NewKotlinTypeCheckerKt.isClassType(transformType) && !transformType.isMarkedNullable()) || SpecialTypesKt.isDefinitelyNotNullType(transformType)) {
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
                sb.append(simpleType2);
                sb.append(". Supertypes = ");
                sb.append(CollectionsKt.joinToString$default(access$getSupertypesSet$p, null, null, null, 0, null, null, 63, null));
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        typeCheckerContext.clear();
        return false;
    }

    private final boolean hasPathByNotMarkedNullableNodes(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType, TypeConstructor typeConstructor) {
        SimpleType simpleType2 = simpleType;
        TypeConstructor typeConstructor2 = typeConstructor;
        if (!simpleType.isMarkedNullable() && Intrinsics.areEqual((Object) simpleType.getConstructor(), (Object) typeConstructor2)) {
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
        access$getSupertypesDeque$p.push(simpleType2);
        while (!access$getSupertypesDeque$p.isEmpty()) {
            if (access$getSupertypesSet$p.size() <= 1000) {
                SimpleType simpleType3 = (SimpleType) access$getSupertypesDeque$p.pop();
                Intrinsics.checkExpressionValueIsNotNull(simpleType3, "current");
                if (access$getSupertypesSet$p.add(simpleType3)) {
                    SupertypesPolicy supertypesPolicy = simpleType3.isMarkedNullable() ? None.INSTANCE : LowerIfFlexible.INSTANCE;
                    if (!(!Intrinsics.areEqual((Object) supertypesPolicy, (Object) None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinType kotlinType : simpleType3.getConstructor().getSupertypes()) {
                            Intrinsics.checkExpressionValueIsNotNull(kotlinType, "supertype");
                            SimpleType transformType = supertypesPolicy.transformType(kotlinType);
                            if (!transformType.isMarkedNullable() && Intrinsics.areEqual((Object) transformType.getConstructor(), (Object) typeConstructor2)) {
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
                sb.append(simpleType2);
                sb.append(". Supertypes = ");
                sb.append(CollectionsKt.joinToString$default(access$getSupertypesSet$p, null, null, null, 0, null, null, 63, null));
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        typeCheckerContext.clear();
        return false;
    }
}

package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: NewKotlinTypeChecker.kt */
public final class NewKotlinTypeCheckerKt {
    public static final boolean isClassType(@NotNull SimpleType simpleType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "receiver$0");
        return simpleType.getConstructor().getDeclarationDescriptor() instanceof ClassDescriptor;
    }

    public static final boolean isSingleClassifierType(@NotNull SimpleType simpleType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "receiver$0");
        return !KotlinTypeKt.isError(simpleType) && !(simpleType.getConstructor().getDeclarationDescriptor() instanceof TypeAliasDescriptor) && (simpleType.getConstructor().getDeclarationDescriptor() != null || (simpleType instanceof CapturedType) || (simpleType instanceof NewCapturedType) || (simpleType instanceof DefinitelyNotNullType));
    }

    public static final boolean isIntersectionType(@NotNull SimpleType simpleType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "receiver$0");
        return simpleType.getConstructor() instanceof IntersectionTypeConstructor;
    }
}

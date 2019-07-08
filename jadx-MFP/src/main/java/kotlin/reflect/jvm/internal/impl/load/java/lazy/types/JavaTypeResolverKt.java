package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaTypeResolver.kt */
public final class JavaTypeResolverKt {
    /* access modifiers changed from: private */
    public static final FqName JAVA_LANG_CLASS_FQ_NAME = new FqName("java.lang.Class");

    @NotNull
    public static final TypeProjection makeStarProjection(@NotNull TypeParameterDescriptor typeParameterDescriptor, @NotNull JavaTypeAttributes javaTypeAttributes) {
        Intrinsics.checkParameterIsNotNull(typeParameterDescriptor, "typeParameter");
        Intrinsics.checkParameterIsNotNull(javaTypeAttributes, "attr");
        if (javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) {
            return new TypeProjectionImpl(StarProjectionImplKt.starProjectionType(typeParameterDescriptor));
        }
        return new StarProjectionImpl(typeParameterDescriptor);
    }

    @NotNull
    public static /* synthetic */ JavaTypeAttributes toAttributes$default(TypeUsage typeUsage, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            typeParameterDescriptor = null;
        }
        return toAttributes(typeUsage, z, typeParameterDescriptor);
    }

    @NotNull
    public static final JavaTypeAttributes toAttributes(@NotNull TypeUsage typeUsage, boolean z, @Nullable TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(typeUsage, "receiver$0");
        JavaTypeAttributes javaTypeAttributes = new JavaTypeAttributes(typeUsage, null, z, typeParameterDescriptor, 2, null);
        return javaTypeAttributes;
    }

    @NotNull
    public static /* synthetic */ KotlinType getErasedUpperBound$default(TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            typeParameterDescriptor2 = null;
        }
        if ((i & 2) != 0) {
            function0 = new JavaTypeResolverKt$getErasedUpperBound$1(typeParameterDescriptor);
        }
        return getErasedUpperBound(typeParameterDescriptor, typeParameterDescriptor2, function0);
    }

    @NotNull
    public static final KotlinType getErasedUpperBound(@NotNull TypeParameterDescriptor typeParameterDescriptor, @Nullable TypeParameterDescriptor typeParameterDescriptor2, @NotNull Function0<? extends KotlinType> function0) {
        Intrinsics.checkParameterIsNotNull(typeParameterDescriptor, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "defaultValue");
        if (typeParameterDescriptor == typeParameterDescriptor2) {
            return (KotlinType) function0.invoke();
        }
        List upperBounds = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds, "upperBounds");
        KotlinType kotlinType = (KotlinType) CollectionsKt.first(upperBounds);
        if (kotlinType.getConstructor().getDeclarationDescriptor() instanceof ClassDescriptor) {
            Intrinsics.checkExpressionValueIsNotNull(kotlinType, "firstUpperBound");
            return TypeUtilsKt.replaceArgumentsWithStarProjections(kotlinType);
        }
        if (typeParameterDescriptor2 != null) {
            typeParameterDescriptor = typeParameterDescriptor2;
        }
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor != null) {
            while (true) {
                TypeParameterDescriptor typeParameterDescriptor3 = (TypeParameterDescriptor) declarationDescriptor;
                if (!(!Intrinsics.areEqual((Object) typeParameterDescriptor3, (Object) typeParameterDescriptor))) {
                    return (KotlinType) function0.invoke();
                }
                List upperBounds2 = typeParameterDescriptor3.getUpperBounds();
                Intrinsics.checkExpressionValueIsNotNull(upperBounds2, "current.upperBounds");
                KotlinType kotlinType2 = (KotlinType) CollectionsKt.first(upperBounds2);
                if (kotlinType2.getConstructor().getDeclarationDescriptor() instanceof ClassDescriptor) {
                    Intrinsics.checkExpressionValueIsNotNull(kotlinType2, "nextUpperBound");
                    return TypeUtilsKt.replaceArgumentsWithStarProjections(kotlinType2);
                }
                declarationDescriptor = kotlinType2.getConstructor().getDeclarationDescriptor();
                if (declarationDescriptor == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
                }
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
        }
    }
}

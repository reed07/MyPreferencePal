package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: inlineClassesUtils.kt */
public final class InlineClassesUtilsKt {
    @Nullable
    public static final ValueParameterDescriptor underlyingRepresentation(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "receiver$0");
        ValueParameterDescriptor valueParameterDescriptor = null;
        if (!classDescriptor.isInline()) {
            return null;
        }
        ClassConstructorDescriptor unsubstitutedPrimaryConstructor = classDescriptor.getUnsubstitutedPrimaryConstructor();
        if (unsubstitutedPrimaryConstructor != null) {
            List valueParameters = unsubstitutedPrimaryConstructor.getValueParameters();
            if (valueParameters != null) {
                valueParameterDescriptor = (ValueParameterDescriptor) CollectionsKt.singleOrNull(valueParameters);
            }
        }
        return valueParameterDescriptor;
    }

    public static final boolean isInlineClass(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "receiver$0");
        return (declarationDescriptor instanceof ClassDescriptor) && ((ClassDescriptor) declarationDescriptor).isInline();
    }

    @Nullable
    public static final ValueParameterDescriptor unsubstitutedUnderlyingParameter(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            declarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
        if (classDescriptor != null) {
            return underlyingRepresentation(classDescriptor);
        }
        return null;
    }

    public static final boolean isInlineClassType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor != null) {
            return isInlineClass(declarationDescriptor);
        }
        return false;
    }

    @Nullable
    public static final KotlinType substitutedUnderlyingType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        ValueParameterDescriptor unsubstitutedUnderlyingParameter = unsubstitutedUnderlyingParameter(kotlinType);
        KotlinType kotlinType2 = null;
        if (unsubstitutedUnderlyingParameter == null) {
            return null;
        }
        MemberScope memberScope = kotlinType.getMemberScope();
        Name name = unsubstitutedUnderlyingParameter.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "parameter.name");
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) CollectionsKt.singleOrNull((Iterable<? extends T>) memberScope.getContributedVariables(name, NoLookupLocation.FOR_ALREADY_TRACKED));
        if (propertyDescriptor != null) {
            kotlinType2 = propertyDescriptor.getType();
        }
        return kotlinType2;
    }
}

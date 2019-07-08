package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypes;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypes.Companion;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.util.Check.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: modifierChecks.kt */
final class IsKPropertyCheck implements Check {
    public static final IsKPropertyCheck INSTANCE = new IsKPropertyCheck();
    @NotNull
    private static final String description = description;

    private IsKPropertyCheck() {
    }

    @Nullable
    public String invoke(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        return DefaultImpls.invoke(this, functionDescriptor);
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public boolean check(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) functionDescriptor.getValueParameters().get(1);
        Companion companion = ReflectionTypes.Companion;
        Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "secondParameter");
        KotlinType createKPropertyStarType = companion.createKPropertyStarType(DescriptorUtilsKt.getModule(valueParameterDescriptor));
        if (createKPropertyStarType == null) {
            return false;
        }
        KotlinType type = valueParameterDescriptor.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "secondParameter.type");
        return TypeUtilsKt.isSubtypeOf(createKPropertyStarType, TypeUtilsKt.makeNotNullable(type));
    }
}

package kotlin.reflect.jvm.internal.impl.util;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.util.Check.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: modifierChecks.kt */
final class NoDefaultAndVarargsCheck implements Check {
    public static final NoDefaultAndVarargsCheck INSTANCE = new NoDefaultAndVarargsCheck();
    @NotNull
    private static final String description = description;

    private NoDefaultAndVarargsCheck() {
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
        boolean z;
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        List valueParameters = functionDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "functionDescriptor.valueParameters");
        Iterable<ValueParameterDescriptor> iterable = valueParameters;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        for (ValueParameterDescriptor valueParameterDescriptor : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "it");
            if (DescriptorUtilsKt.declaresOrInheritsDefaultValue(valueParameterDescriptor) || valueParameterDescriptor.getVarargElementType() != null) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }
}

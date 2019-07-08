package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.JavaDescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Contract;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Result;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FieldOverridabilityCondition.kt */
public final class FieldOverridabilityCondition implements ExternalOverridabilityCondition {
    @NotNull
    public Result isOverridable(@NotNull CallableDescriptor callableDescriptor, @NotNull CallableDescriptor callableDescriptor2, @Nullable ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "superDescriptor");
        Intrinsics.checkParameterIsNotNull(callableDescriptor2, "subDescriptor");
        if (!(callableDescriptor2 instanceof PropertyDescriptor) || !(callableDescriptor instanceof PropertyDescriptor)) {
            return Result.UNKNOWN;
        }
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) callableDescriptor2;
        PropertyDescriptor propertyDescriptor2 = (PropertyDescriptor) callableDescriptor;
        if (!Intrinsics.areEqual((Object) propertyDescriptor.getName(), (Object) propertyDescriptor2.getName())) {
            return Result.UNKNOWN;
        }
        if (JavaDescriptorUtilKt.isJavaField(propertyDescriptor) && JavaDescriptorUtilKt.isJavaField(propertyDescriptor2)) {
            return Result.OVERRIDABLE;
        }
        if (JavaDescriptorUtilKt.isJavaField(propertyDescriptor) || JavaDescriptorUtilKt.isJavaField(propertyDescriptor2)) {
            return Result.INCOMPATIBLE;
        }
        return Result.UNKNOWN;
    }

    @NotNull
    public Contract getContract() {
        return Contract.BOTH;
    }
}

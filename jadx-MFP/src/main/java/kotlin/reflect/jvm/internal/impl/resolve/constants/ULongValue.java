package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: constantValues.kt */
public final class ULongValue extends UnsignedValueConstant<Long> {
    public ULongValue(long j) {
        super(Long.valueOf(j));
    }

    @NotNull
    public KotlinType getType(@NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        ClassId classId = KotlinBuiltIns.FQ_NAMES.uLong;
        Intrinsics.checkExpressionValueIsNotNull(classId, "KotlinBuiltIns.FQ_NAMES.uLong");
        ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor, classId);
        if (findClassAcrossModuleDependencies != null) {
            SimpleType defaultType = findClassAcrossModuleDependencies.getDefaultType();
            if (defaultType != null) {
                return defaultType;
            }
        }
        SimpleType createErrorType = ErrorUtils.createErrorType("Unsigned type ULong not found");
        Intrinsics.checkExpressionValueIsNotNull(createErrorType, "ErrorUtils.createErrorTy…ed type ULong not found\")");
        return createErrorType;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(((Number) getValue()).longValue());
        sb.append(".toULong()");
        return sb.toString();
    }
}

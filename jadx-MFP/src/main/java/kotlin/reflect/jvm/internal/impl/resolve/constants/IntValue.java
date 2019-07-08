package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: constantValues.kt */
public final class IntValue extends IntegerValueConstant<Integer> {
    public IntValue(int i) {
        super(Integer.valueOf(i));
    }

    @NotNull
    public SimpleType getType(@NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        SimpleType intType = moduleDescriptor.getBuiltIns().getIntType();
        Intrinsics.checkExpressionValueIsNotNull(intType, "module.builtIns.intType");
        return intType;
    }
}

package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;

/* compiled from: constantValues.kt */
public final class StringValue extends ConstantValue<String> {
    public StringValue(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        super(str);
    }

    @NotNull
    public SimpleType getType(@NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        SimpleType stringType = moduleDescriptor.getBuiltIns().getStringType();
        Intrinsics.checkExpressionValueIsNotNull(stringType, "module.builtIns.stringType");
        return stringType;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.quote);
        sb.append((String) getValue());
        sb.append(Typography.quote);
        return sb.toString();
    }
}

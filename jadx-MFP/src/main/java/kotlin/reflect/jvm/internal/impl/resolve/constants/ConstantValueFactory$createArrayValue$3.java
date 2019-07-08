package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: ConstantValueFactory.kt */
final class ConstantValueFactory$createArrayValue$3 extends Lambda implements Function1<ModuleDescriptor, SimpleType> {
    final /* synthetic */ PrimitiveType $componentType;

    ConstantValueFactory$createArrayValue$3(PrimitiveType primitiveType) {
        this.$componentType = primitiveType;
        super(1);
    }

    @NotNull
    public final SimpleType invoke(@NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        SimpleType primitiveArrayKotlinType = moduleDescriptor.getBuiltIns().getPrimitiveArrayKotlinType(this.$componentType);
        Intrinsics.checkExpressionValueIsNotNull(primitiveArrayKotlinType, "module.builtIns.getPrimi…KotlinType(componentType)");
        return primitiveArrayKotlinType;
    }
}

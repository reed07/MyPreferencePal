package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin._Assertions;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

/* compiled from: constantValues.kt */
public final class ArrayValue extends ConstantValue<List<? extends ConstantValue<?>>> {
    private final Function1<ModuleDescriptor, KotlinType> computeType;

    public ArrayValue(@NotNull List<? extends ConstantValue<?>> list, @NotNull Function1<? super ModuleDescriptor, ? extends KotlinType> function1) {
        Intrinsics.checkParameterIsNotNull(list, "value");
        Intrinsics.checkParameterIsNotNull(function1, "computeType");
        super(list);
        this.computeType = function1;
    }

    @NotNull
    public KotlinType getType(@NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        KotlinType kotlinType = (KotlinType) this.computeType.invoke(moduleDescriptor);
        boolean z = KotlinBuiltIns.isArray(kotlinType) || KotlinBuiltIns.isPrimitiveArray(kotlinType);
        if (!_Assertions.ENABLED || z) {
            return kotlinType;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Type should be an array, but was ");
        sb.append(kotlinType);
        sb.append(": ");
        sb.append((List) getValue());
        throw new AssertionError(sb.toString());
    }
}

package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import org.jetbrains.annotations.NotNull;

public interface SimpleFunctionDescriptor extends FunctionDescriptor {
    @NotNull
    CopyBuilder<? extends SimpleFunctionDescriptor> newCopyBuilder();
}

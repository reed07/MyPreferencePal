package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtensionReceiver extends AbstractReceiverValue implements ImplicitReceiver {
    private final CallableDescriptor descriptor;

    public ExtensionReceiver(@NotNull CallableDescriptor callableDescriptor, @NotNull KotlinType kotlinType, @Nullable ReceiverValue receiverValue) {
        super(kotlinType, receiverValue);
        this.descriptor = callableDescriptor;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getType());
        sb.append(": Ext {");
        sb.append(this.descriptor);
        sb.append("}");
        return sb.toString();
    }
}

package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ImplicitClassReceiver;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import org.jetbrains.annotations.NotNull;

public class LazyClassReceiverParameterDescriptor extends AbstractReceiverParameterDescriptor {
    private final ClassDescriptor descriptor;
    private final ImplicitClassReceiver receiverValue;

    public LazyClassReceiverParameterDescriptor(@NotNull ClassDescriptor classDescriptor) {
        super(Annotations.Companion.getEMPTY());
        this.descriptor = classDescriptor;
        this.receiverValue = new ImplicitClassReceiver(classDescriptor, null);
    }

    @NotNull
    public ReceiverValue getValue() {
        return this.receiverValue;
    }

    @NotNull
    public DeclarationDescriptor getContainingDeclaration() {
        return this.descriptor;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ");
        sb.append(this.descriptor.getName());
        sb.append("::this");
        return sb.toString();
    }
}

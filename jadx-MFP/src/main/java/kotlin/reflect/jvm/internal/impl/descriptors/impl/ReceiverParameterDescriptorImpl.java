package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import org.jetbrains.annotations.NotNull;

public class ReceiverParameterDescriptorImpl extends AbstractReceiverParameterDescriptor {
    private final DeclarationDescriptor containingDeclaration;
    private final ReceiverValue value;

    public ReceiverParameterDescriptorImpl(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull ReceiverValue receiverValue, @NotNull Annotations annotations) {
        super(annotations);
        this.containingDeclaration = declarationDescriptor;
        this.value = receiverValue;
    }

    @NotNull
    public ReceiverValue getValue() {
        return this.value;
    }

    @NotNull
    public DeclarationDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }
}

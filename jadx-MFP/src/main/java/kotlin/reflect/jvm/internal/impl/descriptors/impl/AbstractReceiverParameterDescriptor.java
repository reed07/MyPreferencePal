package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.ParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.TransientReceiver;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractReceiverParameterDescriptor extends DeclarationDescriptorImpl implements ReceiverParameterDescriptor {
    private static final Name RECEIVER_PARAMETER_NAME = Name.special("<this>");

    @Nullable
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return null;
    }

    @Nullable
    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return null;
    }

    @NotNull
    public ParameterDescriptor getOriginal() {
        return this;
    }

    @Nullable
    public <V> V getUserData(UserDataKey<V> userDataKey) {
        return null;
    }

    public boolean hasSynthesizedParameterNames() {
        return false;
    }

    public AbstractReceiverParameterDescriptor(@NotNull Annotations annotations) {
        super(annotations, RECEIVER_PARAMETER_NAME);
    }

    @Nullable
    public ReceiverParameterDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor) {
        KotlinType kotlinType;
        if (typeSubstitutor.isEmpty()) {
            return this;
        }
        if (getContainingDeclaration() instanceof ClassDescriptor) {
            kotlinType = typeSubstitutor.substitute(getType(), Variance.OUT_VARIANCE);
        } else {
            kotlinType = typeSubstitutor.substitute(getType(), Variance.INVARIANT);
        }
        if (kotlinType == null) {
            return null;
        }
        if (kotlinType == getType()) {
            return this;
        }
        return new ReceiverParameterDescriptorImpl(getContainingDeclaration(), new TransientReceiver(kotlinType), getAnnotations());
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitReceiverParameterDescriptor(this, d);
    }

    @NotNull
    public List<TypeParameterDescriptor> getTypeParameters() {
        return Collections.emptyList();
    }

    @Nullable
    public KotlinType getReturnType() {
        return getType();
    }

    @NotNull
    public KotlinType getType() {
        return getValue().getType();
    }

    @NotNull
    public List<ValueParameterDescriptor> getValueParameters() {
        return Collections.emptyList();
    }

    @NotNull
    public Collection<? extends CallableDescriptor> getOverriddenDescriptors() {
        return Collections.emptySet();
    }

    @NotNull
    public Visibility getVisibility() {
        return Visibilities.LOCAL;
    }

    @NotNull
    public SourceElement getSource() {
        return SourceElement.NO_SOURCE;
    }
}

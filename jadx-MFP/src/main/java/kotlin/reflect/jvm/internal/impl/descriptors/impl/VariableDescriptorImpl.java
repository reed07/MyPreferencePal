package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class VariableDescriptorImpl extends DeclarationDescriptorNonRootImpl implements VariableDescriptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected KotlinType outType;

    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return null;
    }

    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return null;
    }

    @Nullable
    public <V> V getUserData(UserDataKey<V> userDataKey) {
        return null;
    }

    public boolean hasSynthesizedParameterNames() {
        return false;
    }

    public boolean isConst() {
        return false;
    }

    public VariableDescriptorImpl(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, @NotNull Name name, @Nullable KotlinType kotlinType, @NotNull SourceElement sourceElement) {
        super(declarationDescriptor, annotations, name, sourceElement);
        this.outType = kotlinType;
    }

    @NotNull
    public KotlinType getType() {
        return this.outType;
    }

    public void setOutType(KotlinType kotlinType) {
        this.outType = kotlinType;
    }

    @NotNull
    public VariableDescriptor getOriginal() {
        return (VariableDescriptor) super.getOriginal();
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
    public List<TypeParameterDescriptor> getTypeParameters() {
        return Collections.emptyList();
    }

    @NotNull
    public KotlinType getReturnType() {
        return getType();
    }
}

package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleFunctionDescriptorImpl extends FunctionDescriptorImpl implements SimpleFunctionDescriptor {
    protected SimpleFunctionDescriptorImpl(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable SimpleFunctionDescriptor simpleFunctionDescriptor, @NotNull Annotations annotations, @NotNull Name name, @NotNull Kind kind, @NotNull SourceElement sourceElement) {
        super(declarationDescriptor, simpleFunctionDescriptor, annotations, name, kind, sourceElement);
    }

    @NotNull
    public static SimpleFunctionDescriptorImpl create(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, @NotNull Name name, @NotNull Kind kind, @NotNull SourceElement sourceElement) {
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImpl = new SimpleFunctionDescriptorImpl(declarationDescriptor, null, annotations, name, kind, sourceElement);
        return simpleFunctionDescriptorImpl;
    }

    @NotNull
    public SimpleFunctionDescriptorImpl initialize(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor, @Nullable ReceiverParameterDescriptor receiverParameterDescriptor2, @NotNull List<? extends TypeParameterDescriptor> list, @NotNull List<ValueParameterDescriptor> list2, @Nullable KotlinType kotlinType, @Nullable Modality modality, @NotNull Visibility visibility) {
        return initialize(receiverParameterDescriptor, receiverParameterDescriptor2, list, list2, kotlinType, modality, visibility, null);
    }

    @NotNull
    public SimpleFunctionDescriptorImpl initialize(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor, @Nullable ReceiverParameterDescriptor receiverParameterDescriptor2, @NotNull List<? extends TypeParameterDescriptor> list, @NotNull List<ValueParameterDescriptor> list2, @Nullable KotlinType kotlinType, @Nullable Modality modality, @NotNull Visibility visibility, @Nullable Map<? extends UserDataKey<?>, ?> map) {
        super.initialize(receiverParameterDescriptor, receiverParameterDescriptor2, list, list2, kotlinType, modality, visibility);
        if (map != null && !map.isEmpty()) {
            this.userDataMap = new LinkedHashMap(map);
        }
        return this;
    }

    @NotNull
    public SimpleFunctionDescriptor getOriginal() {
        return (SimpleFunctionDescriptor) super.getOriginal();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public FunctionDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement sourceElement) {
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) functionDescriptor;
        if (name == null) {
            name = getName();
        }
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImpl = new SimpleFunctionDescriptorImpl(declarationDescriptor, simpleFunctionDescriptor, annotations, name, kind, sourceElement);
        return simpleFunctionDescriptorImpl;
    }

    @NotNull
    public SimpleFunctionDescriptor copy(DeclarationDescriptor declarationDescriptor, Modality modality, Visibility visibility, Kind kind, boolean z) {
        return (SimpleFunctionDescriptor) super.copy(declarationDescriptor, modality, visibility, kind, z);
    }

    @NotNull
    public CopyBuilder<? extends SimpleFunctionDescriptor> newCopyBuilder() {
        return super.newCopyBuilder();
    }
}

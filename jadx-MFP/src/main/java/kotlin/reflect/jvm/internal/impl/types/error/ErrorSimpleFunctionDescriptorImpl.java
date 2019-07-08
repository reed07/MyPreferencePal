package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
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
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils.ErrorScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ErrorSimpleFunctionDescriptorImpl extends SimpleFunctionDescriptorImpl {
    private final ErrorScope ownerScope;

    @NotNull
    public SimpleFunctionDescriptor copy(DeclarationDescriptor declarationDescriptor, Modality modality, Visibility visibility, Kind kind, boolean z) {
        return this;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public FunctionDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement sourceElement) {
        return this;
    }

    public <V> V getUserData(UserDataKey<V> userDataKey) {
        return null;
    }

    public boolean isSuspend() {
        return false;
    }

    public void setOverriddenDescriptors(@NotNull Collection<? extends CallableMemberDescriptor> collection) {
    }

    public ErrorSimpleFunctionDescriptorImpl(@NotNull ClassDescriptor classDescriptor, @NotNull ErrorScope errorScope) {
        super(classDescriptor, null, Annotations.Companion.getEMPTY(), Name.special("<ERROR FUNCTION>"), Kind.DECLARATION, SourceElement.NO_SOURCE);
        this.ownerScope = errorScope;
    }

    @NotNull
    public CopyBuilder<? extends SimpleFunctionDescriptor> newCopyBuilder() {
        return new CopyBuilder<SimpleFunctionDescriptor>() {
            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setAdditionalAnnotations(@NotNull Annotations annotations) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setCopyOverrides(boolean z) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setDispatchReceiverParameter(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setDropOriginalInContainingParts() {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setExtensionReceiverParameter(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setHiddenForResolutionEverywhereBesideSupercalls() {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setHiddenToOvercomeSignatureClash() {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setKind(@NotNull Kind kind) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setModality(@NotNull Modality modality) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setName(@NotNull Name name) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setOwner(@NotNull DeclarationDescriptor declarationDescriptor) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setPreserveSourceElement() {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setReturnType(@NotNull KotlinType kotlinType) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setSignatureChange() {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setSubstitution(@NotNull TypeSubstitution typeSubstitution) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setTypeParameters(@NotNull List<TypeParameterDescriptor> list) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setValueParameters(@NotNull List<ValueParameterDescriptor> list) {
                return this;
            }

            @NotNull
            public CopyBuilder<SimpleFunctionDescriptor> setVisibility(@NotNull Visibility visibility) {
                return this;
            }

            @Nullable
            public SimpleFunctionDescriptor build() {
                return ErrorSimpleFunctionDescriptorImpl.this;
            }
        };
    }
}

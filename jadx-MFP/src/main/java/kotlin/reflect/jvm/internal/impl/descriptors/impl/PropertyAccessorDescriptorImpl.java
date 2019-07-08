package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PropertyAccessorDescriptorImpl extends DeclarationDescriptorNonRootImpl implements PropertyAccessorDescriptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final PropertyDescriptor correspondingProperty;
    @Nullable
    private FunctionDescriptor initialSignatureDescriptor = null;
    private boolean isDefault;
    private final boolean isExternal;
    private final boolean isInline;
    private final Kind kind;
    private final Modality modality;
    private Visibility visibility;

    @NotNull
    public abstract PropertyAccessorDescriptor getOriginal();

    @Nullable
    public <V> V getUserData(UserDataKey<V> userDataKey) {
        return null;
    }

    public boolean hasSynthesizedParameterNames() {
        return false;
    }

    public boolean isActual() {
        return false;
    }

    public boolean isExpect() {
        return false;
    }

    public boolean isHiddenForResolutionEverywhereBesideSupercalls() {
        return false;
    }

    public boolean isHiddenToOvercomeSignatureClash() {
        return false;
    }

    public boolean isInfix() {
        return false;
    }

    public boolean isOperator() {
        return false;
    }

    public boolean isSuspend() {
        return false;
    }

    public boolean isTailrec() {
        return false;
    }

    public void setOverriddenDescriptors(@NotNull Collection<? extends CallableMemberDescriptor> collection) {
    }

    public PropertyAccessorDescriptorImpl(@NotNull Modality modality2, @NotNull Visibility visibility2, @NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, @NotNull Name name, boolean z, boolean z2, boolean z3, Kind kind2, @NotNull SourceElement sourceElement) {
        super(propertyDescriptor.getContainingDeclaration(), annotations, name, sourceElement);
        this.modality = modality2;
        this.visibility = visibility2;
        this.correspondingProperty = propertyDescriptor;
        this.isDefault = z;
        this.isExternal = z2;
        this.isInline = z3;
        this.kind = kind2;
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    public void setDefault(boolean z) {
        this.isDefault = z;
    }

    @NotNull
    public Kind getKind() {
        return this.kind;
    }

    public boolean isExternal() {
        return this.isExternal;
    }

    public boolean isInline() {
        return this.isInline;
    }

    @NotNull
    public FunctionDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    public List<TypeParameterDescriptor> getTypeParameters() {
        return Collections.emptyList();
    }

    @NotNull
    public Modality getModality() {
        return this.modality;
    }

    @NotNull
    public Visibility getVisibility() {
        return this.visibility;
    }

    public void setVisibility(Visibility visibility2) {
        this.visibility = visibility2;
    }

    @NotNull
    public PropertyDescriptor getCorrespondingProperty() {
        return this.correspondingProperty;
    }

    @Nullable
    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return getCorrespondingProperty().getExtensionReceiverParameter();
    }

    @Nullable
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return getCorrespondingProperty().getDispatchReceiverParameter();
    }

    @NotNull
    public CopyBuilder<? extends FunctionDescriptor> newCopyBuilder() {
        throw new UnsupportedOperationException("Accessors must be copied by the corresponding property");
    }

    @NotNull
    public PropertyAccessorDescriptor copy(DeclarationDescriptor declarationDescriptor, Modality modality2, Visibility visibility2, Kind kind2, boolean z) {
        throw new UnsupportedOperationException("Accessors must be copied by the corresponding property");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Collection<PropertyAccessorDescriptor> getOverriddenDescriptors(boolean z) {
        ArrayList arrayList = new ArrayList(0);
        for (PropertyDescriptor propertyDescriptor : getCorrespondingProperty().getOverriddenDescriptors()) {
            Object getter = z ? propertyDescriptor.getGetter() : propertyDescriptor.getSetter();
            if (getter != null) {
                arrayList.add(getter);
            }
        }
        return arrayList;
    }

    @Nullable
    public FunctionDescriptor getInitialSignatureDescriptor() {
        return this.initialSignatureDescriptor;
    }

    public void setInitialSignatureDescriptor(@Nullable FunctionDescriptor functionDescriptor) {
        this.initialSignatureDescriptor = functionDescriptor;
    }
}

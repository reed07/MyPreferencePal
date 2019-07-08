package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PropertyDescriptorImpl extends VariableDescriptorWithInitializerImpl implements PropertyDescriptor {
    private FieldDescriptor backingField;
    private FieldDescriptor delegateField;
    /* access modifiers changed from: private */
    public ReceiverParameterDescriptor dispatchReceiverParameter;
    private ReceiverParameterDescriptor extensionReceiverParameter;
    private PropertyGetterDescriptorImpl getter;
    private final boolean isActual;
    private final boolean isConst;
    private final boolean isDelegated;
    private final boolean isExpect;
    private final boolean isExternal;
    private final Kind kind;
    private final boolean lateInit;
    private final Modality modality;
    private final PropertyDescriptor original;
    private Collection<? extends PropertyDescriptor> overriddenProperties = null;
    private PropertySetterDescriptor setter;
    private boolean setterProjectedOut;
    private List<TypeParameterDescriptor> typeParameters;
    private Visibility visibility;

    public class CopyConfiguration {
        /* access modifiers changed from: private */
        public boolean copyOverrides = true;
        /* access modifiers changed from: private */
        public ReceiverParameterDescriptor dispatchReceiverParameter = PropertyDescriptorImpl.this.dispatchReceiverParameter;
        /* access modifiers changed from: private */
        public Kind kind = PropertyDescriptorImpl.this.getKind();
        /* access modifiers changed from: private */
        public Modality modality = PropertyDescriptorImpl.this.getModality();
        /* access modifiers changed from: private */
        public Name name = PropertyDescriptorImpl.this.getName();
        /* access modifiers changed from: private */
        public List<TypeParameterDescriptor> newTypeParameters = null;
        /* access modifiers changed from: private */
        public PropertyDescriptor original = null;
        /* access modifiers changed from: private */
        public DeclarationDescriptor owner = PropertyDescriptorImpl.this.getContainingDeclaration();
        /* access modifiers changed from: private */
        public TypeSubstitution substitution = TypeSubstitution.EMPTY;
        /* access modifiers changed from: private */
        public Visibility visibility = PropertyDescriptorImpl.this.getVisibility();

        public CopyConfiguration() {
        }

        @NotNull
        public CopyConfiguration setOwner(@NotNull DeclarationDescriptor declarationDescriptor) {
            this.owner = declarationDescriptor;
            return this;
        }

        @NotNull
        public CopyConfiguration setOriginal(@Nullable CallableMemberDescriptor callableMemberDescriptor) {
            this.original = (PropertyDescriptor) callableMemberDescriptor;
            return this;
        }

        @NotNull
        public CopyConfiguration setModality(@NotNull Modality modality2) {
            this.modality = modality2;
            return this;
        }

        @NotNull
        public CopyConfiguration setVisibility(@NotNull Visibility visibility2) {
            this.visibility = visibility2;
            return this;
        }

        @NotNull
        public CopyConfiguration setKind(@NotNull Kind kind2) {
            this.kind = kind2;
            return this;
        }

        @NotNull
        public CopyConfiguration setSubstitution(@NotNull TypeSubstitution typeSubstitution) {
            this.substitution = typeSubstitution;
            return this;
        }

        @NotNull
        public CopyConfiguration setCopyOverrides(boolean z) {
            this.copyOverrides = z;
            return this;
        }

        @Nullable
        public PropertyDescriptor build() {
            return PropertyDescriptorImpl.this.doSubstitute(this);
        }
    }

    @Nullable
    public <V> V getUserData(UserDataKey<V> userDataKey) {
        return null;
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor] */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected PropertyDescriptorImpl(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r9, @org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r10, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r11, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.Modality r12, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.Visibility r13, boolean r14, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.name.Name r15, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind r16, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r17, boolean r18, boolean r19, boolean r20, boolean r21, boolean r22, boolean r23) {
        /*
            r8 = this;
            r7 = r8
            r4 = 0
            r0 = r8
            r1 = r9
            r2 = r11
            r3 = r15
            r5 = r14
            r6 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r0 = 0
            r7.overriddenProperties = r0
            r0 = r12
            r7.modality = r0
            r0 = r13
            r7.visibility = r0
            if (r10 != 0) goto L_0x0019
            r0 = r7
            goto L_0x001a
        L_0x0019:
            r0 = r10
        L_0x001a:
            r7.original = r0
            r0 = r16
            r7.kind = r0
            r0 = r18
            r7.lateInit = r0
            r0 = r19
            r7.isConst = r0
            r0 = r20
            r7.isExpect = r0
            r0 = r21
            r7.isActual = r0
            r0 = r22
            r7.isExternal = r0
            r0 = r23
            r7.isDelegated = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl.<init>(kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations, kotlin.reflect.jvm.internal.impl.descriptors.Modality, kotlin.reflect.jvm.internal.impl.descriptors.Visibility, boolean, kotlin.reflect.jvm.internal.impl.name.Name, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind, kotlin.reflect.jvm.internal.impl.descriptors.SourceElement, boolean, boolean, boolean, boolean, boolean, boolean):void");
    }

    @NotNull
    public static PropertyDescriptorImpl create(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, @NotNull Modality modality2, @NotNull Visibility visibility2, boolean z, @NotNull Name name, @NotNull Kind kind2, @NotNull SourceElement sourceElement, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        PropertyDescriptorImpl propertyDescriptorImpl = new PropertyDescriptorImpl(declarationDescriptor, null, annotations, modality2, visibility2, z, name, kind2, sourceElement, z2, z3, z4, z5, z6, z7);
        return propertyDescriptorImpl;
    }

    public void setType(@NotNull KotlinType kotlinType, @NotNull List<? extends TypeParameterDescriptor> list, @Nullable ReceiverParameterDescriptor receiverParameterDescriptor, @Nullable ReceiverParameterDescriptor receiverParameterDescriptor2) {
        setOutType(kotlinType);
        this.typeParameters = new ArrayList(list);
        this.extensionReceiverParameter = receiverParameterDescriptor2;
        this.dispatchReceiverParameter = receiverParameterDescriptor;
    }

    public void initialize(@Nullable PropertyGetterDescriptorImpl propertyGetterDescriptorImpl, @Nullable PropertySetterDescriptor propertySetterDescriptor) {
        initialize(propertyGetterDescriptorImpl, propertySetterDescriptor, null, null);
    }

    public void initialize(@Nullable PropertyGetterDescriptorImpl propertyGetterDescriptorImpl, @Nullable PropertySetterDescriptor propertySetterDescriptor, @Nullable FieldDescriptor fieldDescriptor, @Nullable FieldDescriptor fieldDescriptor2) {
        this.getter = propertyGetterDescriptorImpl;
        this.setter = propertySetterDescriptor;
        this.backingField = fieldDescriptor;
        this.delegateField = fieldDescriptor2;
    }

    public void setSetterProjectedOut(boolean z) {
        this.setterProjectedOut = z;
    }

    public void setVisibility(@NotNull Visibility visibility2) {
        this.visibility = visibility2;
    }

    @NotNull
    public List<TypeParameterDescriptor> getTypeParameters() {
        return this.typeParameters;
    }

    @Nullable
    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return this.extensionReceiverParameter;
    }

    @Nullable
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return this.dispatchReceiverParameter;
    }

    @NotNull
    public KotlinType getReturnType() {
        return getType();
    }

    @NotNull
    public Modality getModality() {
        return this.modality;
    }

    @NotNull
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Nullable
    public PropertyGetterDescriptorImpl getGetter() {
        return this.getter;
    }

    @Nullable
    public PropertySetterDescriptor getSetter() {
        return this.setter;
    }

    public boolean isSetterProjectedOut() {
        return this.setterProjectedOut;
    }

    public boolean isLateInit() {
        return this.lateInit;
    }

    public boolean isConst() {
        return this.isConst;
    }

    public boolean isExternal() {
        return this.isExternal;
    }

    public boolean isDelegated() {
        return this.isDelegated;
    }

    @NotNull
    public List<PropertyAccessorDescriptor> getAccessors() {
        ArrayList arrayList = new ArrayList(2);
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl = this.getter;
        if (propertyGetterDescriptorImpl != null) {
            arrayList.add(propertyGetterDescriptorImpl);
        }
        PropertySetterDescriptor propertySetterDescriptor = this.setter;
        if (propertySetterDescriptor != null) {
            arrayList.add(propertySetterDescriptor);
        }
        return arrayList;
    }

    public PropertyDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor) {
        if (typeSubstitutor.isEmpty()) {
            return this;
        }
        return newCopyBuilder().setSubstitution(typeSubstitutor.getSubstitution()).setOriginal(getOriginal()).build();
    }

    @NotNull
    public CopyConfiguration newCopyBuilder() {
        return new CopyConfiguration();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public PropertyDescriptor doSubstitute(@NotNull CopyConfiguration copyConfiguration) {
        ReceiverParameterDescriptor receiverParameterDescriptor;
        ReceiverParameterDescriptor receiverParameterDescriptor2;
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl;
        PropertySetterDescriptorImpl propertySetterDescriptorImpl;
        PropertyDescriptorImpl createSubstitutedCopy = createSubstitutedCopy(copyConfiguration.owner, copyConfiguration.modality, copyConfiguration.visibility, copyConfiguration.original, copyConfiguration.kind, copyConfiguration.name);
        List typeParameters2 = copyConfiguration.newTypeParameters == null ? getTypeParameters() : copyConfiguration.newTypeParameters;
        ArrayList arrayList = new ArrayList(typeParameters2.size());
        TypeSubstitutor substituteTypeParameters = DescriptorSubstitutor.substituteTypeParameters(typeParameters2, copyConfiguration.substitution, createSubstitutedCopy, arrayList);
        KotlinType substitute = substituteTypeParameters.substitute(getType(), Variance.OUT_VARIANCE);
        FieldDescriptorImpl fieldDescriptorImpl = null;
        if (substitute == null) {
            return null;
        }
        ReceiverParameterDescriptor access$900 = copyConfiguration.dispatchReceiverParameter;
        if (access$900 != null) {
            receiverParameterDescriptor = access$900.substitute(substituteTypeParameters);
            if (receiverParameterDescriptor == null) {
                return null;
            }
        } else {
            receiverParameterDescriptor = null;
        }
        ReceiverParameterDescriptor receiverParameterDescriptor3 = this.extensionReceiverParameter;
        if (receiverParameterDescriptor3 != null) {
            KotlinType substitute2 = substituteTypeParameters.substitute(receiverParameterDescriptor3.getType(), Variance.IN_VARIANCE);
            if (substitute2 == null) {
                return null;
            }
            receiverParameterDescriptor2 = new ReceiverParameterDescriptorImpl(createSubstitutedCopy, new ExtensionReceiver(createSubstitutedCopy, substitute2, this.extensionReceiverParameter.getValue()), this.extensionReceiverParameter.getAnnotations());
        } else {
            receiverParameterDescriptor2 = null;
        }
        createSubstitutedCopy.setType(substitute, arrayList, receiverParameterDescriptor, receiverParameterDescriptor2);
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl2 = this.getter;
        if (propertyGetterDescriptorImpl2 == null) {
            propertyGetterDescriptorImpl = null;
        } else {
            propertyGetterDescriptorImpl = new PropertyGetterDescriptorImpl(createSubstitutedCopy, propertyGetterDescriptorImpl2.getAnnotations(), copyConfiguration.modality, normalizeVisibility(this.getter.getVisibility(), copyConfiguration.kind), this.getter.isDefault(), this.getter.isExternal(), this.getter.isInline(), copyConfiguration.kind, copyConfiguration.original == null ? null : copyConfiguration.original.getGetter(), SourceElement.NO_SOURCE);
        }
        if (propertyGetterDescriptorImpl != null) {
            KotlinType returnType = this.getter.getReturnType();
            propertyGetterDescriptorImpl.setInitialSignatureDescriptor(getSubstitutedInitialSignatureDescriptor(substituteTypeParameters, this.getter));
            propertyGetterDescriptorImpl.initialize(returnType != null ? substituteTypeParameters.substitute(returnType, Variance.OUT_VARIANCE) : null);
        }
        PropertySetterDescriptor propertySetterDescriptor = this.setter;
        if (propertySetterDescriptor == null) {
            propertySetterDescriptorImpl = null;
        } else {
            propertySetterDescriptorImpl = new PropertySetterDescriptorImpl(createSubstitutedCopy, propertySetterDescriptor.getAnnotations(), copyConfiguration.modality, normalizeVisibility(this.setter.getVisibility(), copyConfiguration.kind), this.setter.isDefault(), this.setter.isExternal(), this.setter.isInline(), copyConfiguration.kind, copyConfiguration.original == null ? null : copyConfiguration.original.getSetter(), SourceElement.NO_SOURCE);
        }
        if (propertySetterDescriptorImpl != null) {
            List substitutedValueParameters = FunctionDescriptorImpl.getSubstitutedValueParameters(propertySetterDescriptorImpl, this.setter.getValueParameters(), substituteTypeParameters, false, false, null);
            if (substitutedValueParameters == null) {
                createSubstitutedCopy.setSetterProjectedOut(true);
                substitutedValueParameters = Collections.singletonList(PropertySetterDescriptorImpl.createSetterParameter(propertySetterDescriptorImpl, DescriptorUtilsKt.getBuiltIns(copyConfiguration.owner).getNothingType(), ((ValueParameterDescriptor) this.setter.getValueParameters().get(0)).getAnnotations()));
            }
            if (substitutedValueParameters.size() == 1) {
                propertySetterDescriptorImpl.setInitialSignatureDescriptor(getSubstitutedInitialSignatureDescriptor(substituteTypeParameters, this.setter));
                propertySetterDescriptorImpl.initialize((ValueParameterDescriptor) substitutedValueParameters.get(0));
            } else {
                throw new IllegalStateException();
            }
        }
        FieldDescriptor fieldDescriptor = this.backingField;
        FieldDescriptor fieldDescriptorImpl2 = fieldDescriptor == null ? null : new FieldDescriptorImpl(fieldDescriptor.getAnnotations(), createSubstitutedCopy);
        FieldDescriptor fieldDescriptor2 = this.delegateField;
        if (fieldDescriptor2 != null) {
            fieldDescriptorImpl = new FieldDescriptorImpl(fieldDescriptor2.getAnnotations(), createSubstitutedCopy);
        }
        createSubstitutedCopy.initialize(propertyGetterDescriptorImpl, propertySetterDescriptorImpl, fieldDescriptorImpl2, fieldDescriptorImpl);
        if (copyConfiguration.copyOverrides) {
            SmartSet create = SmartSet.create();
            for (PropertyDescriptor substitute3 : getOverriddenDescriptors()) {
                create.add(substitute3.substitute(substituteTypeParameters));
            }
            createSubstitutedCopy.setOverriddenDescriptors(create);
        }
        if (isConst() && this.compileTimeInitializer != null) {
            createSubstitutedCopy.setCompileTimeInitializer(this.compileTimeInitializer);
        }
        return createSubstitutedCopy;
    }

    private static Visibility normalizeVisibility(Visibility visibility2, Kind kind2) {
        return (kind2 != Kind.FAKE_OVERRIDE || !Visibilities.isPrivate(visibility2.normalize())) ? visibility2 : Visibilities.INVISIBLE_FAKE;
    }

    private static FunctionDescriptor getSubstitutedInitialSignatureDescriptor(@NotNull TypeSubstitutor typeSubstitutor, @NotNull PropertyAccessorDescriptor propertyAccessorDescriptor) {
        if (propertyAccessorDescriptor.getInitialSignatureDescriptor() != null) {
            return propertyAccessorDescriptor.getInitialSignatureDescriptor().substitute(typeSubstitutor);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public PropertyDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Modality modality2, @NotNull Visibility visibility2, @Nullable PropertyDescriptor propertyDescriptor, @NotNull Kind kind2, @NotNull Name name) {
        PropertyDescriptorImpl propertyDescriptorImpl = new PropertyDescriptorImpl(declarationDescriptor, propertyDescriptor, getAnnotations(), modality2, visibility2, isVar(), name, kind2, SourceElement.NO_SOURCE, isLateInit(), isConst(), isExpect(), isActual(), isExternal(), isDelegated());
        return propertyDescriptorImpl;
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitPropertyDescriptor(this, d);
    }

    @NotNull
    public PropertyDescriptor getOriginal() {
        PropertyDescriptor propertyDescriptor = this.original;
        return propertyDescriptor == this ? this : propertyDescriptor.getOriginal();
    }

    @NotNull
    public Kind getKind() {
        return this.kind;
    }

    public boolean isExpect() {
        return this.isExpect;
    }

    public boolean isActual() {
        return this.isActual;
    }

    @Nullable
    public FieldDescriptor getBackingField() {
        return this.backingField;
    }

    @Nullable
    public FieldDescriptor getDelegateField() {
        return this.delegateField;
    }

    public void setOverriddenDescriptors(@NotNull Collection<? extends CallableMemberDescriptor> collection) {
        this.overriddenProperties = collection;
    }

    @NotNull
    public Collection<? extends PropertyDescriptor> getOverriddenDescriptors() {
        Collection<? extends PropertyDescriptor> collection = this.overriddenProperties;
        return collection != null ? collection : Collections.emptyList();
    }

    @NotNull
    public PropertyDescriptor copy(DeclarationDescriptor declarationDescriptor, Modality modality2, Visibility visibility2, Kind kind2, boolean z) {
        return newCopyBuilder().setOwner(declarationDescriptor).setOriginal(null).setModality(modality2).setVisibility(visibility2).setKind(kind2).setCopyOverrides(z).build();
    }
}

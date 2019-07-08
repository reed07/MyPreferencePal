package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class FunctionDescriptorImpl extends DeclarationDescriptorNonRootImpl implements FunctionDescriptor {
    /* access modifiers changed from: private */
    public ReceiverParameterDescriptor dispatchReceiverParameter;
    private ReceiverParameterDescriptor extensionReceiverParameter;
    private boolean hasStableParameterNames = true;
    private boolean hasSynthesizedParameterNames = false;
    @Nullable
    private FunctionDescriptor initialSignatureDescriptor = null;
    private boolean isActual = false;
    private boolean isExpect = false;
    private boolean isExternal = false;
    private boolean isHiddenForResolutionEverywhereBesideSupercalls = false;
    private boolean isHiddenToOvercomeSignatureClash = false;
    private boolean isInfix = false;
    private boolean isInline = false;
    private boolean isOperator = false;
    private boolean isSuspend = false;
    private boolean isTailrec = false;
    private final Kind kind;
    private volatile Function0<Collection<FunctionDescriptor>> lazyOverriddenFunctionsTask = null;
    private Modality modality;
    private final FunctionDescriptor original;
    private Collection<? extends FunctionDescriptor> overriddenFunctions = null;
    private List<TypeParameterDescriptor> typeParameters;
    private KotlinType unsubstitutedReturnType;
    private List<ValueParameterDescriptor> unsubstitutedValueParameters;
    protected Map<UserDataKey<?>, Object> userDataMap = null;
    private Visibility visibility = Visibilities.UNKNOWN;

    public class CopyConfiguration implements CopyBuilder<FunctionDescriptor> {
        /* access modifiers changed from: private */
        public Annotations additionalAnnotations = null;
        protected boolean copyOverrides = true;
        @Nullable
        protected ReceiverParameterDescriptor dispatchReceiverParameter = FunctionDescriptorImpl.this.dispatchReceiverParameter;
        protected boolean dropOriginalInContainingParts = false;
        /* access modifiers changed from: private */
        public boolean isHiddenForResolutionEverywhereBesideSupercalls = FunctionDescriptorImpl.this.isHiddenForResolutionEverywhereBesideSupercalls();
        /* access modifiers changed from: private */
        public boolean isHiddenToOvercomeSignatureClash = FunctionDescriptorImpl.this.isHiddenToOvercomeSignatureClash();
        protected boolean justForTypeSubstitution = false;
        @NotNull
        protected Kind kind;
        @Nullable
        protected Name name;
        @Nullable
        protected ReceiverParameterDescriptor newExtensionReceiverParameter;
        /* access modifiers changed from: private */
        public Boolean newHasSynthesizedParameterNames = null;
        @NotNull
        protected Modality newModality;
        @NotNull
        protected DeclarationDescriptor newOwner;
        @NotNull
        protected KotlinType newReturnType;
        /* access modifiers changed from: private */
        public List<TypeParameterDescriptor> newTypeParameters = null;
        @NotNull
        protected List<ValueParameterDescriptor> newValueParameterDescriptors;
        @NotNull
        protected Visibility newVisibility;
        @Nullable
        protected FunctionDescriptor original = null;
        protected boolean preserveSourceElement = false;
        protected boolean signatureChange = false;
        @NotNull
        protected TypeSubstitution substitution;
        /* access modifiers changed from: private */
        public Map<UserDataKey<?>, Object> userDataMap = new LinkedHashMap();

        public CopyConfiguration(TypeSubstitution typeSubstitution, @NotNull DeclarationDescriptor declarationDescriptor, @NotNull Modality modality, @NotNull Visibility visibility, @NotNull Kind kind2, @NotNull List<ValueParameterDescriptor> list, @NotNull ReceiverParameterDescriptor receiverParameterDescriptor, @Nullable KotlinType kotlinType, @NotNull Name name2) {
            this.substitution = typeSubstitution;
            this.newOwner = declarationDescriptor;
            this.newModality = modality;
            this.newVisibility = visibility;
            this.kind = kind2;
            this.newValueParameterDescriptors = list;
            this.newExtensionReceiverParameter = receiverParameterDescriptor;
            this.newReturnType = kotlinType;
            this.name = name2;
        }

        @NotNull
        public CopyConfiguration setOwner(@NotNull DeclarationDescriptor declarationDescriptor) {
            this.newOwner = declarationDescriptor;
            return this;
        }

        @NotNull
        public CopyConfiguration setModality(@NotNull Modality modality) {
            this.newModality = modality;
            return this;
        }

        @NotNull
        public CopyConfiguration setVisibility(@NotNull Visibility visibility) {
            this.newVisibility = visibility;
            return this;
        }

        @NotNull
        public CopyConfiguration setKind(@NotNull Kind kind2) {
            this.kind = kind2;
            return this;
        }

        @NotNull
        public CopyConfiguration setCopyOverrides(boolean z) {
            this.copyOverrides = z;
            return this;
        }

        @NotNull
        public CopyConfiguration setName(@NotNull Name name2) {
            this.name = name2;
            return this;
        }

        @NotNull
        public CopyConfiguration setValueParameters(@NotNull List<ValueParameterDescriptor> list) {
            this.newValueParameterDescriptors = list;
            return this;
        }

        @NotNull
        public CopyConfiguration setTypeParameters(@NotNull List<TypeParameterDescriptor> list) {
            this.newTypeParameters = list;
            return this;
        }

        @NotNull
        public CopyConfiguration setReturnType(@NotNull KotlinType kotlinType) {
            this.newReturnType = kotlinType;
            return this;
        }

        @NotNull
        public CopyConfiguration setExtensionReceiverParameter(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor) {
            this.newExtensionReceiverParameter = receiverParameterDescriptor;
            return this;
        }

        @NotNull
        public CopyConfiguration setDispatchReceiverParameter(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor) {
            this.dispatchReceiverParameter = receiverParameterDescriptor;
            return this;
        }

        @NotNull
        public CopyConfiguration setOriginal(@Nullable CallableMemberDescriptor callableMemberDescriptor) {
            this.original = (FunctionDescriptor) callableMemberDescriptor;
            return this;
        }

        @NotNull
        public CopyConfiguration setSignatureChange() {
            this.signatureChange = true;
            return this;
        }

        @NotNull
        public CopyConfiguration setPreserveSourceElement() {
            this.preserveSourceElement = true;
            return this;
        }

        @NotNull
        public CopyConfiguration setDropOriginalInContainingParts() {
            this.dropOriginalInContainingParts = true;
            return this;
        }

        @NotNull
        public CopyConfiguration setHiddenToOvercomeSignatureClash() {
            this.isHiddenToOvercomeSignatureClash = true;
            return this;
        }

        @NotNull
        public CopyConfiguration setHiddenForResolutionEverywhereBesideSupercalls() {
            this.isHiddenForResolutionEverywhereBesideSupercalls = true;
            return this;
        }

        @NotNull
        public CopyConfiguration setAdditionalAnnotations(@NotNull Annotations annotations) {
            this.additionalAnnotations = annotations;
            return this;
        }

        public CopyConfiguration setHasSynthesizedParameterNames(boolean z) {
            this.newHasSynthesizedParameterNames = Boolean.valueOf(z);
            return this;
        }

        @NotNull
        public CopyConfiguration setSubstitution(@NotNull TypeSubstitution typeSubstitution) {
            this.substitution = typeSubstitution;
            return this;
        }

        @Nullable
        public FunctionDescriptor build() {
            return FunctionDescriptorImpl.this.doSubstitute(this);
        }

        @NotNull
        public CopyConfiguration setJustForTypeSubstitution(boolean z) {
            this.justForTypeSubstitution = z;
            return this;
        }
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract FunctionDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull Kind kind2, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement sourceElement);

    protected FunctionDescriptorImpl(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull Annotations annotations, @NotNull Name name, @NotNull Kind kind2, @NotNull SourceElement sourceElement) {
        super(declarationDescriptor, annotations, name, sourceElement);
        if (functionDescriptor == 0) {
            functionDescriptor = this;
        }
        this.original = functionDescriptor;
        this.kind = kind2;
    }

    @NotNull
    public FunctionDescriptorImpl initialize(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor, @Nullable ReceiverParameterDescriptor receiverParameterDescriptor2, @NotNull List<? extends TypeParameterDescriptor> list, @NotNull List<ValueParameterDescriptor> list2, @Nullable KotlinType kotlinType, @Nullable Modality modality2, @NotNull Visibility visibility2) {
        this.typeParameters = CollectionsKt.toList(list);
        this.unsubstitutedValueParameters = CollectionsKt.toList(list2);
        this.unsubstitutedReturnType = kotlinType;
        this.modality = modality2;
        this.visibility = visibility2;
        this.extensionReceiverParameter = receiverParameterDescriptor;
        this.dispatchReceiverParameter = receiverParameterDescriptor2;
        int i = 0;
        int i2 = 0;
        while (i2 < list.size()) {
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) list.get(i2);
            if (typeParameterDescriptor.getIndex() == i2) {
                i2++;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(typeParameterDescriptor);
                sb.append(" index is ");
                sb.append(typeParameterDescriptor.getIndex());
                sb.append(" but position is ");
                sb.append(i2);
                throw new IllegalStateException(sb.toString());
            }
        }
        while (i < list2.size()) {
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) list2.get(i);
            if (valueParameterDescriptor.getIndex() == i + 0) {
                i++;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(valueParameterDescriptor);
                sb2.append("index is ");
                sb2.append(valueParameterDescriptor.getIndex());
                sb2.append(" but position is ");
                sb2.append(i);
                throw new IllegalStateException(sb2.toString());
            }
        }
        return this;
    }

    public void setVisibility(@NotNull Visibility visibility2) {
        this.visibility = visibility2;
    }

    public void setOperator(boolean z) {
        this.isOperator = z;
    }

    public void setInfix(boolean z) {
        this.isInfix = z;
    }

    public void setExternal(boolean z) {
        this.isExternal = z;
    }

    public void setInline(boolean z) {
        this.isInline = z;
    }

    public void setTailrec(boolean z) {
        this.isTailrec = z;
    }

    public void setExpect(boolean z) {
        this.isExpect = z;
    }

    public void setActual(boolean z) {
        this.isActual = z;
    }

    private void setHiddenToOvercomeSignatureClash(boolean z) {
        this.isHiddenToOvercomeSignatureClash = z;
    }

    private void setHiddenForResolutionEverywhereBesideSupercalls(boolean z) {
        this.isHiddenForResolutionEverywhereBesideSupercalls = z;
    }

    public void setSuspend(boolean z) {
        this.isSuspend = z;
    }

    public void setReturnType(@NotNull KotlinType kotlinType) {
        KotlinType kotlinType2 = this.unsubstitutedReturnType;
        this.unsubstitutedReturnType = kotlinType;
    }

    public void setHasStableParameterNames(boolean z) {
        this.hasStableParameterNames = z;
    }

    public void setHasSynthesizedParameterNames(boolean z) {
        this.hasSynthesizedParameterNames = z;
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
    public Collection<? extends FunctionDescriptor> getOverriddenDescriptors() {
        performOverriddenLazyCalculationIfNeeded();
        Collection<? extends FunctionDescriptor> collection = this.overriddenFunctions;
        return collection != null ? collection : Collections.emptyList();
    }

    private void performOverriddenLazyCalculationIfNeeded() {
        Function0<Collection<FunctionDescriptor>> function0 = this.lazyOverriddenFunctionsTask;
        if (function0 != null) {
            this.overriddenFunctions = (Collection) function0.invoke();
            this.lazyOverriddenFunctionsTask = null;
        }
    }

    @NotNull
    public Modality getModality() {
        return this.modality;
    }

    @NotNull
    public Visibility getVisibility() {
        return this.visibility;
    }

    public boolean isOperator() {
        if (this.isOperator) {
            return true;
        }
        for (FunctionDescriptor isOperator2 : getOriginal().getOverriddenDescriptors()) {
            if (isOperator2.isOperator()) {
                return true;
            }
        }
        return false;
    }

    public boolean isInfix() {
        if (this.isInfix) {
            return true;
        }
        for (FunctionDescriptor isInfix2 : getOriginal().getOverriddenDescriptors()) {
            if (isInfix2.isInfix()) {
                return true;
            }
        }
        return false;
    }

    public boolean isExternal() {
        return this.isExternal;
    }

    public boolean isInline() {
        return this.isInline;
    }

    public boolean isTailrec() {
        return this.isTailrec;
    }

    public boolean isSuspend() {
        return this.isSuspend;
    }

    public boolean isExpect() {
        return this.isExpect;
    }

    public boolean isActual() {
        return this.isActual;
    }

    public <V> V getUserData(UserDataKey<V> userDataKey) {
        Map<UserDataKey<?>, Object> map = this.userDataMap;
        if (map == null) {
            return null;
        }
        return map.get(userDataKey);
    }

    public boolean isHiddenToOvercomeSignatureClash() {
        return this.isHiddenToOvercomeSignatureClash;
    }

    public void setOverriddenDescriptors(@NotNull Collection<? extends CallableMemberDescriptor> collection) {
        this.overriddenFunctions = collection;
        for (FunctionDescriptor isHiddenForResolutionEverywhereBesideSupercalls2 : this.overriddenFunctions) {
            if (isHiddenForResolutionEverywhereBesideSupercalls2.isHiddenForResolutionEverywhereBesideSupercalls()) {
                this.isHiddenForResolutionEverywhereBesideSupercalls = true;
                return;
            }
        }
    }

    @NotNull
    public List<TypeParameterDescriptor> getTypeParameters() {
        return this.typeParameters;
    }

    @NotNull
    public List<ValueParameterDescriptor> getValueParameters() {
        return this.unsubstitutedValueParameters;
    }

    public boolean hasStableParameterNames() {
        return this.hasStableParameterNames;
    }

    public boolean hasSynthesizedParameterNames() {
        return this.hasSynthesizedParameterNames;
    }

    public KotlinType getReturnType() {
        return this.unsubstitutedReturnType;
    }

    @NotNull
    public FunctionDescriptor getOriginal() {
        FunctionDescriptor functionDescriptor = this.original;
        return functionDescriptor == this ? this : functionDescriptor.getOriginal();
    }

    @NotNull
    public Kind getKind() {
        return this.kind;
    }

    public FunctionDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor) {
        if (typeSubstitutor.isEmpty()) {
            return this;
        }
        return newCopyBuilder(typeSubstitutor).setOriginal(getOriginal()).setJustForTypeSubstitution(true).build();
    }

    public boolean isHiddenForResolutionEverywhereBesideSupercalls() {
        return this.isHiddenForResolutionEverywhereBesideSupercalls;
    }

    @NotNull
    public CopyBuilder<? extends FunctionDescriptor> newCopyBuilder() {
        return newCopyBuilder(TypeSubstitutor.EMPTY);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public CopyConfiguration newCopyBuilder(@NotNull TypeSubstitutor typeSubstitutor) {
        CopyConfiguration copyConfiguration = new CopyConfiguration(typeSubstitutor.getSubstitution(), getContainingDeclaration(), getModality(), getVisibility(), getKind(), getValueParameters(), getExtensionReceiverParameter(), getReturnType(), null);
        return copyConfiguration;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public FunctionDescriptor doSubstitute(@NotNull CopyConfiguration copyConfiguration) {
        ReceiverParameterDescriptor receiverParameterDescriptor;
        ReceiverParameterDescriptor receiverParameterDescriptor2;
        CopyConfiguration copyConfiguration2 = copyConfiguration;
        boolean[] zArr = new boolean[1];
        FunctionDescriptorImpl createSubstitutedCopy = createSubstitutedCopy(copyConfiguration2.newOwner, copyConfiguration2.original, copyConfiguration2.kind, copyConfiguration2.name, copyConfiguration.additionalAnnotations != null ? AnnotationsKt.composeAnnotations(getAnnotations(), copyConfiguration.additionalAnnotations) : getAnnotations(), getSourceToUseForCopy(copyConfiguration2.preserveSourceElement, copyConfiguration2.original));
        List typeParameters2 = copyConfiguration.newTypeParameters == null ? getTypeParameters() : copyConfiguration.newTypeParameters;
        zArr[0] = zArr[0] | (!typeParameters2.isEmpty());
        ArrayList arrayList = new ArrayList(typeParameters2.size());
        TypeSubstitutor substituteTypeParameters = DescriptorSubstitutor.substituteTypeParameters(typeParameters2, copyConfiguration2.substitution, createSubstitutedCopy, arrayList, zArr);
        if (substituteTypeParameters == null) {
            return null;
        }
        if (copyConfiguration2.newExtensionReceiverParameter != null) {
            KotlinType substitute = substituteTypeParameters.substitute(copyConfiguration2.newExtensionReceiverParameter.getType(), Variance.IN_VARIANCE);
            if (substitute == null) {
                return null;
            }
            ReceiverParameterDescriptorImpl receiverParameterDescriptorImpl = new ReceiverParameterDescriptorImpl(createSubstitutedCopy, new ExtensionReceiver(createSubstitutedCopy, substitute, copyConfiguration2.newExtensionReceiverParameter.getValue()), copyConfiguration2.newExtensionReceiverParameter.getAnnotations());
            zArr[0] = (substitute != copyConfiguration2.newExtensionReceiverParameter.getType()) | zArr[0];
            receiverParameterDescriptor = receiverParameterDescriptorImpl;
        } else {
            receiverParameterDescriptor = null;
        }
        if (copyConfiguration2.dispatchReceiverParameter != null) {
            ReceiverParameterDescriptor substitute2 = copyConfiguration2.dispatchReceiverParameter.substitute(substituteTypeParameters);
            if (substitute2 == null) {
                return null;
            }
            zArr[0] = zArr[0] | (substitute2 != copyConfiguration2.dispatchReceiverParameter);
            receiverParameterDescriptor2 = substitute2;
        } else {
            receiverParameterDescriptor2 = null;
        }
        List substitutedValueParameters = getSubstitutedValueParameters(createSubstitutedCopy, copyConfiguration2.newValueParameterDescriptors, substituteTypeParameters, copyConfiguration2.dropOriginalInContainingParts, copyConfiguration2.preserveSourceElement, zArr);
        if (substitutedValueParameters == null) {
            return null;
        }
        KotlinType substitute3 = substituteTypeParameters.substitute(copyConfiguration2.newReturnType, Variance.OUT_VARIANCE);
        if (substitute3 == null) {
            return null;
        }
        zArr[0] = zArr[0] | (substitute3 != copyConfiguration2.newReturnType);
        if (!zArr[0] && copyConfiguration2.justForTypeSubstitution) {
            return this;
        }
        final TypeSubstitutor typeSubstitutor = substituteTypeParameters;
        createSubstitutedCopy.initialize(receiverParameterDescriptor, receiverParameterDescriptor2, arrayList, substitutedValueParameters, substitute3, copyConfiguration2.newModality, copyConfiguration2.newVisibility);
        createSubstitutedCopy.setOperator(this.isOperator);
        createSubstitutedCopy.setInfix(this.isInfix);
        createSubstitutedCopy.setExternal(this.isExternal);
        createSubstitutedCopy.setInline(this.isInline);
        createSubstitutedCopy.setTailrec(this.isTailrec);
        createSubstitutedCopy.setSuspend(this.isSuspend);
        createSubstitutedCopy.setExpect(this.isExpect);
        createSubstitutedCopy.setActual(this.isActual);
        createSubstitutedCopy.setHasStableParameterNames(this.hasStableParameterNames);
        createSubstitutedCopy.setHiddenToOvercomeSignatureClash(copyConfiguration.isHiddenToOvercomeSignatureClash);
        createSubstitutedCopy.setHiddenForResolutionEverywhereBesideSupercalls(copyConfiguration.isHiddenForResolutionEverywhereBesideSupercalls);
        createSubstitutedCopy.setHasSynthesizedParameterNames(copyConfiguration.newHasSynthesizedParameterNames != null ? copyConfiguration.newHasSynthesizedParameterNames.booleanValue() : this.hasSynthesizedParameterNames);
        if (!copyConfiguration.userDataMap.isEmpty() || this.userDataMap != null) {
            Map<UserDataKey<?>, Object> access$600 = copyConfiguration.userDataMap;
            Map<UserDataKey<?>, Object> map = this.userDataMap;
            if (map != null) {
                for (Entry entry : map.entrySet()) {
                    if (!access$600.containsKey(entry.getKey())) {
                        access$600.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            if (access$600.size() == 1) {
                createSubstitutedCopy.userDataMap = Collections.singletonMap(access$600.keySet().iterator().next(), access$600.values().iterator().next());
            } else {
                createSubstitutedCopy.userDataMap = access$600;
            }
        }
        if (copyConfiguration2.signatureChange || getInitialSignatureDescriptor() != null) {
            createSubstitutedCopy.setInitialSignatureDescriptor((getInitialSignatureDescriptor() != null ? getInitialSignatureDescriptor() : this).substitute(typeSubstitutor));
        }
        if (copyConfiguration2.copyOverrides && !getOriginal().getOverriddenDescriptors().isEmpty()) {
            if (copyConfiguration2.substitution.isEmpty()) {
                Function0<Collection<FunctionDescriptor>> function0 = this.lazyOverriddenFunctionsTask;
                if (function0 != null) {
                    createSubstitutedCopy.lazyOverriddenFunctionsTask = function0;
                } else {
                    createSubstitutedCopy.setOverriddenDescriptors(getOverriddenDescriptors());
                }
            } else {
                createSubstitutedCopy.lazyOverriddenFunctionsTask = new Function0<Collection<FunctionDescriptor>>() {
                    public Collection<FunctionDescriptor> invoke() {
                        SmartList smartList = new SmartList();
                        for (FunctionDescriptor substitute : FunctionDescriptorImpl.this.getOverriddenDescriptors()) {
                            smartList.add(substitute.substitute(typeSubstitutor));
                        }
                        return smartList;
                    }
                };
            }
        }
        return createSubstitutedCopy;
    }

    @NotNull
    public FunctionDescriptor copy(DeclarationDescriptor declarationDescriptor, Modality modality2, Visibility visibility2, Kind kind2, boolean z) {
        return newCopyBuilder().setOwner(declarationDescriptor).setModality(modality2).setVisibility(visibility2).setKind(kind2).setCopyOverrides(z).build();
    }

    @NotNull
    private SourceElement getSourceToUseForCopy(boolean z, @Nullable FunctionDescriptor functionDescriptor) {
        if (!z) {
            return SourceElement.NO_SOURCE;
        }
        if (functionDescriptor == null) {
            functionDescriptor = getOriginal();
        }
        return functionDescriptor.getSource();
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitFunctionDescriptor(this, d);
    }

    @Nullable
    public static List<ValueParameterDescriptor> getSubstitutedValueParameters(FunctionDescriptor functionDescriptor, @NotNull List<ValueParameterDescriptor> list, @NotNull TypeSubstitutor typeSubstitutor) {
        return getSubstitutedValueParameters(functionDescriptor, list, typeSubstitutor, false, false, null);
    }

    @Nullable
    public static List<ValueParameterDescriptor> getSubstitutedValueParameters(FunctionDescriptor functionDescriptor, @NotNull List<ValueParameterDescriptor> list, @NotNull TypeSubstitutor typeSubstitutor, boolean z, boolean z2, @Nullable boolean[] zArr) {
        KotlinType kotlinType;
        TypeSubstitutor typeSubstitutor2 = typeSubstitutor;
        ArrayList arrayList = new ArrayList(list.size());
        for (ValueParameterDescriptor valueParameterDescriptor : list) {
            KotlinType substitute = typeSubstitutor2.substitute(valueParameterDescriptor.getType(), Variance.IN_VARIANCE);
            KotlinType varargElementType = valueParameterDescriptor.getVarargElementType();
            if (varargElementType == null) {
                kotlinType = null;
            } else {
                kotlinType = typeSubstitutor2.substitute(varargElementType, Variance.IN_VARIANCE);
            }
            if (substitute == null) {
                return null;
            }
            if (!((substitute == valueParameterDescriptor.getType() && varargElementType == kotlinType) || zArr == null)) {
                zArr[0] = true;
            }
            ValueParameterDescriptorImpl valueParameterDescriptorImpl = new ValueParameterDescriptorImpl(functionDescriptor, z ? null : valueParameterDescriptor, valueParameterDescriptor.getIndex(), valueParameterDescriptor.getAnnotations(), valueParameterDescriptor.getName(), substitute, valueParameterDescriptor.declaresDefaultValue(), valueParameterDescriptor.isCrossinline(), valueParameterDescriptor.isNoinline(), kotlinType, z2 ? valueParameterDescriptor.getSource() : SourceElement.NO_SOURCE);
            arrayList.add(valueParameterDescriptorImpl);
        }
        return arrayList;
    }

    @Nullable
    public FunctionDescriptor getInitialSignatureDescriptor() {
        return this.initialSignatureDescriptor;
    }

    private void setInitialSignatureDescriptor(@Nullable FunctionDescriptor functionDescriptor) {
        this.initialSignatureDescriptor = functionDescriptor;
    }

    public <V> void putInUserDataMap(UserDataKey<V> userDataKey, Object obj) {
        if (this.userDataMap == null) {
            this.userDataMap = new LinkedHashMap();
        }
        this.userDataMap.put(userDataKey, obj);
    }
}

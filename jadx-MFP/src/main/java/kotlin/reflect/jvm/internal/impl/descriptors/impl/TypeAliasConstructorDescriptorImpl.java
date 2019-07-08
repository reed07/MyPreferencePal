package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeAliasConstructorDescriptor.kt */
public final class TypeAliasConstructorDescriptorImpl extends FunctionDescriptorImpl implements TypeAliasConstructorDescriptor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TypeAliasConstructorDescriptorImpl.class), "withDispatchReceiver", "getWithDispatchReceiver()Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;"))};
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final StorageManager storageManager;
    @NotNull
    private final TypeAliasDescriptor typeAliasDescriptor;
    @NotNull
    private ClassConstructorDescriptor underlyingConstructorDescriptor;
    @Nullable
    private final NullableLazyValue withDispatchReceiver$delegate;

    /* compiled from: TypeAliasConstructorDescriptor.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final TypeSubstitutor getTypeSubstitutorForUnderlyingClass(@NotNull TypeAliasDescriptor typeAliasDescriptor) {
            if (typeAliasDescriptor.getClassDescriptor() == null) {
                return null;
            }
            return TypeSubstitutor.create((KotlinType) typeAliasDescriptor.getExpandedType());
        }

        @Nullable
        public final TypeAliasConstructorDescriptor createIfAvailable(@NotNull StorageManager storageManager, @NotNull TypeAliasDescriptor typeAliasDescriptor, @NotNull ClassConstructorDescriptor classConstructorDescriptor) {
            TypeAliasDescriptor typeAliasDescriptor2 = typeAliasDescriptor;
            ClassConstructorDescriptor classConstructorDescriptor2 = classConstructorDescriptor;
            Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
            Intrinsics.checkParameterIsNotNull(typeAliasDescriptor2, "typeAliasDescriptor");
            Intrinsics.checkParameterIsNotNull(classConstructorDescriptor2, "constructor");
            TypeSubstitutor typeSubstitutorForUnderlyingClass = getTypeSubstitutorForUnderlyingClass(typeAliasDescriptor2);
            ReceiverParameterDescriptor receiverParameterDescriptor = null;
            if (typeSubstitutorForUnderlyingClass == null) {
                return null;
            }
            ClassConstructorDescriptor substitute = classConstructorDescriptor2.substitute(typeSubstitutorForUnderlyingClass);
            if (substitute == null) {
                return null;
            }
            Annotations annotations = classConstructorDescriptor.getAnnotations();
            Kind kind = classConstructorDescriptor.getKind();
            Intrinsics.checkExpressionValueIsNotNull(kind, "constructor.kind");
            SourceElement source = typeAliasDescriptor.getSource();
            Intrinsics.checkExpressionValueIsNotNull(source, "typeAliasDescriptor.source");
            TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl = new TypeAliasConstructorDescriptorImpl(storageManager, typeAliasDescriptor, substitute, null, annotations, kind, source, null);
            List substitutedValueParameters = FunctionDescriptorImpl.getSubstitutedValueParameters(typeAliasConstructorDescriptorImpl, classConstructorDescriptor.getValueParameters(), typeSubstitutorForUnderlyingClass);
            if (substitutedValueParameters == null) {
                return null;
            }
            Intrinsics.checkExpressionValueIsNotNull(substitutedValueParameters, "FunctionDescriptorImpl.g…         ) ?: return null");
            SimpleType lowerIfFlexible = FlexibleTypesKt.lowerIfFlexible(substitute.getReturnType().unwrap());
            SimpleType defaultType = typeAliasDescriptor.getDefaultType();
            Intrinsics.checkExpressionValueIsNotNull(defaultType, "typeAliasDescriptor.defaultType");
            SimpleType withAbbreviation = SpecialTypesKt.withAbbreviation(lowerIfFlexible, defaultType);
            ReceiverParameterDescriptor dispatchReceiverParameter = classConstructorDescriptor.getDispatchReceiverParameter();
            if (dispatchReceiverParameter != null) {
                CallableDescriptor callableDescriptor = typeAliasConstructorDescriptorImpl;
                Intrinsics.checkExpressionValueIsNotNull(dispatchReceiverParameter, "it");
                receiverParameterDescriptor = DescriptorFactory.createExtensionReceiverParameterForCallable(callableDescriptor, typeSubstitutorForUnderlyingClass.safeSubstitute(dispatchReceiverParameter.getType(), Variance.INVARIANT), Annotations.Companion.getEMPTY());
            }
            typeAliasConstructorDescriptorImpl.initialize(receiverParameterDescriptor, null, typeAliasDescriptor.getDeclaredTypeParameters(), substitutedValueParameters, withAbbreviation, Modality.FINAL, typeAliasDescriptor.getVisibility());
            return typeAliasConstructorDescriptorImpl;
        }
    }

    public /* synthetic */ TypeAliasConstructorDescriptorImpl(@NotNull StorageManager storageManager2, @NotNull TypeAliasDescriptor typeAliasDescriptor2, @NotNull ClassConstructorDescriptor classConstructorDescriptor, @Nullable TypeAliasConstructorDescriptor typeAliasConstructorDescriptor, @NotNull Annotations annotations, @NotNull Kind kind, @NotNull SourceElement sourceElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(storageManager2, typeAliasDescriptor2, classConstructorDescriptor, typeAliasConstructorDescriptor, annotations, kind, sourceElement);
    }

    @NotNull
    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    @NotNull
    public TypeAliasDescriptor getTypeAliasDescriptor() {
        return this.typeAliasDescriptor;
    }

    private TypeAliasConstructorDescriptorImpl(StorageManager storageManager2, TypeAliasDescriptor typeAliasDescriptor2, ClassConstructorDescriptor classConstructorDescriptor, TypeAliasConstructorDescriptor typeAliasConstructorDescriptor, Annotations annotations, Kind kind, SourceElement sourceElement) {
        super(typeAliasDescriptor2, typeAliasConstructorDescriptor, annotations, Name.special("<init>"), kind, sourceElement);
        this.storageManager = storageManager2;
        this.typeAliasDescriptor = typeAliasDescriptor2;
        setActual(getTypeAliasDescriptor().isActual());
        this.withDispatchReceiver$delegate = this.storageManager.createNullableLazyValue(new TypeAliasConstructorDescriptorImpl$withDispatchReceiver$2(this, classConstructorDescriptor));
        this.underlyingConstructorDescriptor = classConstructorDescriptor;
    }

    private void setUnderlyingConstructorDescriptor(ClassConstructorDescriptor classConstructorDescriptor) {
        this.underlyingConstructorDescriptor = classConstructorDescriptor;
    }

    @NotNull
    public ClassConstructorDescriptor getUnderlyingConstructorDescriptor() {
        return this.underlyingConstructorDescriptor;
    }

    public boolean isPrimary() {
        return getUnderlyingConstructorDescriptor().isPrimary();
    }

    @NotNull
    public TypeAliasDescriptor getContainingDeclaration() {
        return getTypeAliasDescriptor();
    }

    @NotNull
    public KotlinType getReturnType() {
        KotlinType returnType = super.getReturnType();
        if (returnType == null) {
            Intrinsics.throwNpe();
        }
        return returnType;
    }

    @NotNull
    public TypeAliasConstructorDescriptor getOriginal() {
        FunctionDescriptor original = super.getOriginal();
        if (original != null) {
            return (TypeAliasConstructorDescriptor) original;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
    }

    @Nullable
    public TypeAliasConstructorDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor) {
        Intrinsics.checkParameterIsNotNull(typeSubstitutor, "substitutor");
        FunctionDescriptor substitute = super.substitute(typeSubstitutor);
        if (substitute != null) {
            TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl = (TypeAliasConstructorDescriptorImpl) substitute;
            TypeSubstitutor create = TypeSubstitutor.create(typeAliasConstructorDescriptorImpl.getReturnType());
            Intrinsics.checkExpressionValueIsNotNull(create, "TypeSubstitutor.create(s…asConstructor.returnType)");
            ClassConstructorDescriptor substitute2 = getUnderlyingConstructorDescriptor().getOriginal().substitute(create);
            if (substitute2 == null) {
                return null;
            }
            typeAliasConstructorDescriptorImpl.setUnderlyingConstructorDescriptor(substitute2);
            return typeAliasConstructorDescriptorImpl;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptorImpl");
    }

    @NotNull
    public TypeAliasConstructorDescriptor copy(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Modality modality, @NotNull Visibility visibility, @NotNull Kind kind, boolean z) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "newOwner");
        Intrinsics.checkParameterIsNotNull(modality, "modality");
        Intrinsics.checkParameterIsNotNull(visibility, "visibility");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        FunctionDescriptor build = newCopyBuilder().setOwner(declarationDescriptor).setModality(modality).setVisibility(visibility).setKind(kind).setCopyOverrides(z).build();
        if (build != null) {
            return (TypeAliasConstructorDescriptor) build;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public TypeAliasConstructorDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement sourceElement) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "newOwner");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(sourceElement, "source");
        boolean z = false;
        boolean z2 = kind == Kind.DECLARATION || kind == Kind.SYNTHESIZED;
        if (!_Assertions.ENABLED || z2) {
            if (name == null) {
                z = true;
            }
            if (!_Assertions.ENABLED || z) {
                TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl = new TypeAliasConstructorDescriptorImpl(this.storageManager, getTypeAliasDescriptor(), getUnderlyingConstructorDescriptor(), this, annotations, Kind.DECLARATION, sourceElement);
                return typeAliasConstructorDescriptorImpl;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Renaming type alias constructor: ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Creating a type alias constructor that is not a declaration: \ncopy from: ");
        sb2.append(this);
        sb2.append("\nnewOwner: ");
        sb2.append(declarationDescriptor);
        sb2.append("\nkind: ");
        sb2.append(kind);
        throw new AssertionError(sb2.toString());
    }
}

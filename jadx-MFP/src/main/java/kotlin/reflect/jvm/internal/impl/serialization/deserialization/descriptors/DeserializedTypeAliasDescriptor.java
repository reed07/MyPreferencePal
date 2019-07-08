package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor.CoroutinesCompatibilityMode;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedMemberDescriptor.kt */
public final class DeserializedTypeAliasDescriptor extends AbstractTypeAliasDescriptor implements DeserializedMemberDescriptor {
    @NotNull
    private Collection<? extends TypeAliasConstructorDescriptor> constructors;
    @Nullable
    private final DeserializedContainerSource containerSource;
    @NotNull
    private CoroutinesCompatibilityMode coroutinesExperimentalCompatibilityMode = CoroutinesCompatibilityMode.COMPATIBLE;
    private SimpleType defaultTypeImpl;
    @NotNull
    private SimpleType expandedType;
    @NotNull
    private final NameResolver nameResolver;
    @NotNull
    private final TypeAlias proto;
    @NotNull
    private final StorageManager storageManager;
    private List<? extends TypeParameterDescriptor> typeConstructorParameters;
    @NotNull
    private final TypeTable typeTable;
    @NotNull
    private SimpleType underlyingType;
    @NotNull
    private final VersionRequirementTable versionRequirementTable;

    @NotNull
    public List<VersionRequirement> getVersionRequirements() {
        return DefaultImpls.getVersionRequirements(this);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public StorageManager getStorageManager() {
        return this.storageManager;
    }

    @NotNull
    public TypeAlias getProto() {
        return this.proto;
    }

    @NotNull
    public NameResolver getNameResolver() {
        return this.nameResolver;
    }

    @NotNull
    public TypeTable getTypeTable() {
        return this.typeTable;
    }

    @NotNull
    public VersionRequirementTable getVersionRequirementTable() {
        return this.versionRequirementTable;
    }

    @Nullable
    public DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    public DeserializedTypeAliasDescriptor(@NotNull StorageManager storageManager2, @NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, @NotNull Name name, @NotNull Visibility visibility, @NotNull TypeAlias typeAlias, @NotNull NameResolver nameResolver2, @NotNull TypeTable typeTable2, @NotNull VersionRequirementTable versionRequirementTable2, @Nullable DeserializedContainerSource deserializedContainerSource) {
        StorageManager storageManager3 = storageManager2;
        TypeAlias typeAlias2 = typeAlias;
        NameResolver nameResolver3 = nameResolver2;
        TypeTable typeTable3 = typeTable2;
        VersionRequirementTable versionRequirementTable3 = versionRequirementTable2;
        Intrinsics.checkParameterIsNotNull(storageManager2, "storageManager");
        DeclarationDescriptor declarationDescriptor2 = declarationDescriptor;
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "containingDeclaration");
        Annotations annotations2 = annotations;
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Name name2 = name;
        Intrinsics.checkParameterIsNotNull(name2, "name");
        Visibility visibility2 = visibility;
        Intrinsics.checkParameterIsNotNull(visibility2, "visibility");
        Intrinsics.checkParameterIsNotNull(typeAlias2, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver3, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable3, "typeTable");
        Intrinsics.checkParameterIsNotNull(versionRequirementTable3, "versionRequirementTable");
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
        super(declarationDescriptor2, annotations2, name2, sourceElement, visibility2);
        this.storageManager = storageManager3;
        this.proto = typeAlias2;
        this.nameResolver = nameResolver3;
        this.typeTable = typeTable3;
        this.versionRequirementTable = versionRequirementTable3;
        this.containerSource = deserializedContainerSource;
    }

    private void setConstructors(Collection<? extends TypeAliasConstructorDescriptor> collection) {
        this.constructors = collection;
    }

    private void setUnderlyingType(SimpleType simpleType) {
        this.underlyingType = simpleType;
    }

    @NotNull
    public SimpleType getUnderlyingType() {
        SimpleType simpleType = this.underlyingType;
        if (simpleType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("underlyingType");
        }
        return simpleType;
    }

    private void setExpandedType(SimpleType simpleType) {
        this.expandedType = simpleType;
    }

    @NotNull
    public SimpleType getExpandedType() {
        SimpleType simpleType = this.expandedType;
        if (simpleType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("expandedType");
        }
        return simpleType;
    }

    private void setCoroutinesExperimentalCompatibilityMode(CoroutinesCompatibilityMode coroutinesCompatibilityMode) {
        this.coroutinesExperimentalCompatibilityMode = coroutinesCompatibilityMode;
    }

    @NotNull
    public CoroutinesCompatibilityMode getCoroutinesExperimentalCompatibilityMode() {
        return this.coroutinesExperimentalCompatibilityMode;
    }

    public final void initialize(@NotNull List<? extends TypeParameterDescriptor> list, @NotNull SimpleType simpleType, @NotNull SimpleType simpleType2, @NotNull CoroutinesCompatibilityMode coroutinesCompatibilityMode) {
        Intrinsics.checkParameterIsNotNull(list, "declaredTypeParameters");
        Intrinsics.checkParameterIsNotNull(simpleType, "underlyingType");
        Intrinsics.checkParameterIsNotNull(simpleType2, "expandedType");
        Intrinsics.checkParameterIsNotNull(coroutinesCompatibilityMode, "isExperimentalCoroutineInReleaseEnvironment");
        initialize(list);
        setUnderlyingType(simpleType);
        setExpandedType(simpleType2);
        this.typeConstructorParameters = TypeParameterUtilsKt.computeConstructorTypeParameters(this);
        this.defaultTypeImpl = computeDefaultType();
        setConstructors(getTypeAliasConstructors());
        setCoroutinesExperimentalCompatibilityMode(coroutinesCompatibilityMode);
    }

    @Nullable
    public ClassDescriptor getClassDescriptor() {
        if (KotlinTypeKt.isError(getExpandedType())) {
            return null;
        }
        ClassifierDescriptor declarationDescriptor = getExpandedType().getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            declarationDescriptor = null;
        }
        return (ClassDescriptor) declarationDescriptor;
    }

    @NotNull
    public SimpleType getDefaultType() {
        SimpleType simpleType = this.defaultTypeImpl;
        if (simpleType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("defaultTypeImpl");
        }
        return simpleType;
    }

    @NotNull
    public TypeAliasDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor) {
        Intrinsics.checkParameterIsNotNull(typeSubstitutor, "substitutor");
        if (typeSubstitutor.isEmpty()) {
            return this;
        }
        StorageManager storageManager2 = getStorageManager();
        DeclarationDescriptor containingDeclaration = getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "containingDeclaration");
        Annotations annotations = getAnnotations();
        Intrinsics.checkExpressionValueIsNotNull(annotations, "annotations");
        Name name = getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        DeserializedTypeAliasDescriptor deserializedTypeAliasDescriptor = new DeserializedTypeAliasDescriptor(storageManager2, containingDeclaration, annotations, name, getVisibility(), getProto(), getNameResolver(), getTypeTable(), getVersionRequirementTable(), getContainerSource());
        List declaredTypeParameters = getDeclaredTypeParameters();
        KotlinType safeSubstitute = typeSubstitutor.safeSubstitute(getUnderlyingType(), Variance.INVARIANT);
        Intrinsics.checkExpressionValueIsNotNull(safeSubstitute, "substitutor.safeSubstitu…Type, Variance.INVARIANT)");
        SimpleType asSimpleType = TypeSubstitutionKt.asSimpleType(safeSubstitute);
        KotlinType safeSubstitute2 = typeSubstitutor.safeSubstitute(getExpandedType(), Variance.INVARIANT);
        Intrinsics.checkExpressionValueIsNotNull(safeSubstitute2, "substitutor.safeSubstitu…Type, Variance.INVARIANT)");
        deserializedTypeAliasDescriptor.initialize(declaredTypeParameters, asSimpleType, TypeSubstitutionKt.asSimpleType(safeSubstitute2), getCoroutinesExperimentalCompatibilityMode());
        return deserializedTypeAliasDescriptor;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public List<TypeParameterDescriptor> getTypeConstructorTypeParameters() {
        List<? extends TypeParameterDescriptor> list = this.typeConstructorParameters;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeConstructorParameters");
        }
        return list;
    }
}

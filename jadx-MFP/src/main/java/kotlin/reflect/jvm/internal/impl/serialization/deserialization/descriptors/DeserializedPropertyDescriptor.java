package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor.CoroutinesCompatibilityMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedMemberDescriptor.kt */
public final class DeserializedPropertyDescriptor extends PropertyDescriptorImpl implements DeserializedCallableMemberDescriptor {
    @Nullable
    private final DeserializedContainerSource containerSource;
    @NotNull
    private CoroutinesCompatibilityMode coroutinesExperimentalCompatibilityMode = CoroutinesCompatibilityMode.COMPATIBLE;
    @NotNull
    private final NameResolver nameResolver;
    @NotNull
    private final Property proto;
    @NotNull
    private final TypeTable typeTable;
    @NotNull
    private final VersionRequirementTable versionRequirementTable;

    @NotNull
    public List<VersionRequirement> getVersionRequirements() {
        return DefaultImpls.getVersionRequirements(this);
    }

    @NotNull
    public Property getProto() {
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

    public DeserializedPropertyDescriptor(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, @NotNull Modality modality, @NotNull Visibility visibility, boolean z, @NotNull Name name, @NotNull Kind kind, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, @NotNull Property property, @NotNull NameResolver nameResolver2, @NotNull TypeTable typeTable2, @NotNull VersionRequirementTable versionRequirementTable2, @Nullable DeserializedContainerSource deserializedContainerSource) {
        Property property2 = property;
        NameResolver nameResolver3 = nameResolver2;
        TypeTable typeTable3 = typeTable2;
        VersionRequirementTable versionRequirementTable3 = versionRequirementTable2;
        DeclarationDescriptor declarationDescriptor2 = declarationDescriptor;
        Intrinsics.checkParameterIsNotNull(declarationDescriptor2, "containingDeclaration");
        Annotations annotations2 = annotations;
        Intrinsics.checkParameterIsNotNull(annotations2, "annotations");
        Modality modality2 = modality;
        Intrinsics.checkParameterIsNotNull(modality2, "modality");
        Visibility visibility2 = visibility;
        Intrinsics.checkParameterIsNotNull(visibility2, "visibility");
        Name name2 = name;
        Intrinsics.checkParameterIsNotNull(name2, "name");
        Kind kind2 = kind;
        Intrinsics.checkParameterIsNotNull(kind2, "kind");
        Intrinsics.checkParameterIsNotNull(property2, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver3, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable3, "typeTable");
        Intrinsics.checkParameterIsNotNull(versionRequirementTable3, "versionRequirementTable");
        super(declarationDescriptor2, propertyDescriptor, annotations2, modality2, visibility2, z, name2, kind2, SourceElement.NO_SOURCE, z2, z3, z6, false, z4, z5);
        this.proto = property;
        this.nameResolver = nameResolver2;
        this.typeTable = typeTable2;
        this.versionRequirementTable = versionRequirementTable2;
        this.containerSource = deserializedContainerSource;
    }

    private void setCoroutinesExperimentalCompatibilityMode(CoroutinesCompatibilityMode coroutinesCompatibilityMode) {
        this.coroutinesExperimentalCompatibilityMode = coroutinesCompatibilityMode;
    }

    public final void initialize(@Nullable PropertyGetterDescriptorImpl propertyGetterDescriptorImpl, @Nullable PropertySetterDescriptor propertySetterDescriptor, @Nullable FieldDescriptor fieldDescriptor, @Nullable FieldDescriptor fieldDescriptor2, @NotNull CoroutinesCompatibilityMode coroutinesCompatibilityMode) {
        Intrinsics.checkParameterIsNotNull(coroutinesCompatibilityMode, "isExperimentalCoroutineInReleaseEnvironment");
        super.initialize(propertyGetterDescriptorImpl, propertySetterDescriptor, fieldDescriptor, fieldDescriptor2);
        Unit unit = Unit.INSTANCE;
        setCoroutinesExperimentalCompatibilityMode(coroutinesCompatibilityMode);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public PropertyDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Modality modality, @NotNull Visibility visibility, @Nullable PropertyDescriptor propertyDescriptor, @NotNull Kind kind, @NotNull Name name) {
        DeclarationDescriptor declarationDescriptor2 = declarationDescriptor;
        Modality modality2 = modality;
        Visibility visibility2 = visibility;
        PropertyDescriptor propertyDescriptor2 = propertyDescriptor;
        Kind kind2 = kind;
        Name name2 = name;
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "newOwner");
        Intrinsics.checkParameterIsNotNull(modality, "newModality");
        Intrinsics.checkParameterIsNotNull(visibility, "newVisibility");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(name, "newName");
        DeserializedPropertyDescriptor deserializedPropertyDescriptor = new DeserializedPropertyDescriptor(declarationDescriptor2, propertyDescriptor2, getAnnotations(), modality2, visibility2, isVar(), name2, kind2, isLateInit(), isConst(), isExternal(), isDelegated(), isExpect(), getProto(), getNameResolver(), getTypeTable(), getVersionRequirementTable(), getContainerSource());
        return deserializedPropertyDescriptor;
    }

    public boolean isExternal() {
        Boolean bool = Flags.IS_EXTERNAL_PROPERTY.get(getProto().getFlags());
        Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.IS_EXTERNAL_PROPERTY.get(proto.flags)");
        return bool.booleanValue();
    }
}

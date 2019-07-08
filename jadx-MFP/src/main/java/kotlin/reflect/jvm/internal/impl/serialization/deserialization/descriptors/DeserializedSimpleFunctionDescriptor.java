package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
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
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor.CoroutinesCompatibilityMode;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedMemberDescriptor.kt */
public final class DeserializedSimpleFunctionDescriptor extends SimpleFunctionDescriptorImpl implements DeserializedCallableMemberDescriptor {
    @Nullable
    private final DeserializedContainerSource containerSource;
    @NotNull
    private CoroutinesCompatibilityMode coroutinesExperimentalCompatibilityMode;
    @NotNull
    private final NameResolver nameResolver;
    @NotNull
    private final Function proto;
    @NotNull
    private final TypeTable typeTable;
    @NotNull
    private final VersionRequirementTable versionRequirementTable;

    @NotNull
    public List<VersionRequirement> getVersionRequirements() {
        return DefaultImpls.getVersionRequirements(this);
    }

    @NotNull
    public Function getProto() {
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

    public /* synthetic */ DeserializedSimpleFunctionDescriptor(DeclarationDescriptor declarationDescriptor, SimpleFunctionDescriptor simpleFunctionDescriptor, Annotations annotations, Name name, Kind kind, Function function, NameResolver nameResolver2, TypeTable typeTable2, VersionRequirementTable versionRequirementTable2, DeserializedContainerSource deserializedContainerSource, SourceElement sourceElement, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(declarationDescriptor, simpleFunctionDescriptor, annotations, name, kind, function, nameResolver2, typeTable2, versionRequirementTable2, deserializedContainerSource, (i & 1024) != 0 ? null : sourceElement);
    }

    public DeserializedSimpleFunctionDescriptor(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable SimpleFunctionDescriptor simpleFunctionDescriptor, @NotNull Annotations annotations, @NotNull Name name, @NotNull Kind kind, @NotNull Function function, @NotNull NameResolver nameResolver2, @NotNull TypeTable typeTable2, @NotNull VersionRequirementTable versionRequirementTable2, @Nullable DeserializedContainerSource deserializedContainerSource, @Nullable SourceElement sourceElement) {
        Function function2 = function;
        NameResolver nameResolver3 = nameResolver2;
        TypeTable typeTable3 = typeTable2;
        VersionRequirementTable versionRequirementTable3 = versionRequirementTable2;
        DeclarationDescriptor declarationDescriptor2 = declarationDescriptor;
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "containingDeclaration");
        Annotations annotations2 = annotations;
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(function2, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver3, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable3, "typeTable");
        Intrinsics.checkParameterIsNotNull(versionRequirementTable3, "versionRequirementTable");
        super(declarationDescriptor, simpleFunctionDescriptor, annotations, name, kind, sourceElement != null ? sourceElement : SourceElement.NO_SOURCE);
        this.proto = function2;
        this.nameResolver = nameResolver3;
        this.typeTable = typeTable3;
        this.versionRequirementTable = versionRequirementTable3;
        this.containerSource = deserializedContainerSource;
        this.coroutinesExperimentalCompatibilityMode = CoroutinesCompatibilityMode.COMPATIBLE;
    }

    private void setCoroutinesExperimentalCompatibilityMode(CoroutinesCompatibilityMode coroutinesCompatibilityMode) {
        this.coroutinesExperimentalCompatibilityMode = coroutinesCompatibilityMode;
    }

    @NotNull
    public CoroutinesCompatibilityMode getCoroutinesExperimentalCompatibilityMode() {
        return this.coroutinesExperimentalCompatibilityMode;
    }

    @NotNull
    public final SimpleFunctionDescriptorImpl initialize(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor, @Nullable ReceiverParameterDescriptor receiverParameterDescriptor2, @NotNull List<? extends TypeParameterDescriptor> list, @NotNull List<? extends ValueParameterDescriptor> list2, @Nullable KotlinType kotlinType, @Nullable Modality modality, @NotNull Visibility visibility, @NotNull Map<? extends UserDataKey<?>, ?> map, @NotNull CoroutinesCompatibilityMode coroutinesCompatibilityMode) {
        Intrinsics.checkParameterIsNotNull(list, "typeParameters");
        Intrinsics.checkParameterIsNotNull(list2, "unsubstitutedValueParameters");
        Intrinsics.checkParameterIsNotNull(visibility, "visibility");
        Intrinsics.checkParameterIsNotNull(map, "userDataMap");
        Intrinsics.checkParameterIsNotNull(coroutinesCompatibilityMode, "isExperimentalCoroutineInReleaseEnvironment");
        SimpleFunctionDescriptorImpl initialize = super.initialize(receiverParameterDescriptor, receiverParameterDescriptor2, list, list2, kotlinType, modality, visibility, map);
        setCoroutinesExperimentalCompatibilityMode(coroutinesCompatibilityMode);
        Intrinsics.checkExpressionValueIsNotNull(initialize, "super.initialize(\n      …easeEnvironment\n        }");
        return initialize;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public FunctionDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement sourceElement) {
        Name name2;
        DeclarationDescriptor declarationDescriptor2 = declarationDescriptor;
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "newOwner");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(sourceElement, "source");
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) functionDescriptor;
        if (name != null) {
            name2 = name;
        } else {
            Name name3 = getName();
            Intrinsics.checkExpressionValueIsNotNull(name3, "name");
            name2 = name3;
        }
        DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor = new DeserializedSimpleFunctionDescriptor(declarationDescriptor, simpleFunctionDescriptor, annotations, name2, kind, getProto(), getNameResolver(), getTypeTable(), getVersionRequirementTable(), getContainerSource(), sourceElement);
        deserializedSimpleFunctionDescriptor.setCoroutinesExperimentalCompatibilityMode(getCoroutinesExperimentalCompatibilityMode());
        return deserializedSimpleFunctionDescriptor;
    }
}

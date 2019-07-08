package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionSpecificBehaviorKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: context.kt */
public final class DeserializationContext {
    @NotNull
    private final DeserializationComponents components;
    @Nullable
    private final DeserializedContainerSource containerSource;
    @NotNull
    private final DeclarationDescriptor containingDeclaration;
    @NotNull
    private final MemberDeserializer memberDeserializer = new MemberDeserializer(this);
    @NotNull
    private final BinaryVersion metadataVersion;
    @NotNull
    private final NameResolver nameResolver;
    @NotNull
    private final TypeDeserializer typeDeserializer;
    @NotNull
    private final TypeTable typeTable;
    @NotNull
    private final VersionRequirementTable versionRequirementTable;

    public DeserializationContext(@NotNull DeserializationComponents deserializationComponents, @NotNull NameResolver nameResolver2, @NotNull DeclarationDescriptor declarationDescriptor, @NotNull TypeTable typeTable2, @NotNull VersionRequirementTable versionRequirementTable2, @NotNull BinaryVersion binaryVersion, @Nullable DeserializedContainerSource deserializedContainerSource, @Nullable TypeDeserializer typeDeserializer2, @NotNull List<TypeParameter> list) {
        DeserializationComponents deserializationComponents2 = deserializationComponents;
        NameResolver nameResolver3 = nameResolver2;
        DeclarationDescriptor declarationDescriptor2 = declarationDescriptor;
        TypeTable typeTable3 = typeTable2;
        VersionRequirementTable versionRequirementTable3 = versionRequirementTable2;
        BinaryVersion binaryVersion2 = binaryVersion;
        Intrinsics.checkParameterIsNotNull(deserializationComponents, "components");
        Intrinsics.checkParameterIsNotNull(nameResolver2, "nameResolver");
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(typeTable2, "typeTable");
        Intrinsics.checkParameterIsNotNull(versionRequirementTable3, "versionRequirementTable");
        Intrinsics.checkParameterIsNotNull(binaryVersion2, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(list, "typeParameters");
        this.components = deserializationComponents2;
        this.nameResolver = nameResolver3;
        this.containingDeclaration = declarationDescriptor2;
        this.typeTable = typeTable3;
        this.versionRequirementTable = versionRequirementTable3;
        this.metadataVersion = binaryVersion2;
        this.containerSource = deserializedContainerSource;
        StringBuilder sb = new StringBuilder();
        sb.append("Deserializer for ");
        sb.append(this.containingDeclaration.getName());
        TypeDeserializer typeDeserializer3 = new TypeDeserializer(this, typeDeserializer2, list, sb.toString(), false, 16, null);
        this.typeDeserializer = typeDeserializer3;
    }

    @NotNull
    public final DeserializationComponents getComponents() {
        return this.components;
    }

    @NotNull
    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    @NotNull
    public final DeclarationDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @NotNull
    public final TypeTable getTypeTable() {
        return this.typeTable;
    }

    @NotNull
    public final VersionRequirementTable getVersionRequirementTable() {
        return this.versionRequirementTable;
    }

    @Nullable
    public final DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    @NotNull
    public final TypeDeserializer getTypeDeserializer() {
        return this.typeDeserializer;
    }

    @NotNull
    public final MemberDeserializer getMemberDeserializer() {
        return this.memberDeserializer;
    }

    @NotNull
    public final StorageManager getStorageManager() {
        return this.components.getStorageManager();
    }

    @NotNull
    public static /* synthetic */ DeserializationContext childContext$default(DeserializationContext deserializationContext, DeclarationDescriptor declarationDescriptor, List list, NameResolver nameResolver2, TypeTable typeTable2, VersionRequirementTable versionRequirementTable2, BinaryVersion binaryVersion, int i, Object obj) {
        return deserializationContext.childContext(declarationDescriptor, list, (i & 4) != 0 ? deserializationContext.nameResolver : nameResolver2, (i & 8) != 0 ? deserializationContext.typeTable : typeTable2, (i & 16) != 0 ? deserializationContext.versionRequirementTable : versionRequirementTable2, (i & 32) != 0 ? deserializationContext.metadataVersion : binaryVersion);
    }

    @NotNull
    public final DeserializationContext childContext(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull List<TypeParameter> list, @NotNull NameResolver nameResolver2, @NotNull TypeTable typeTable2, @NotNull VersionRequirementTable versionRequirementTable2, @NotNull BinaryVersion binaryVersion) {
        DeclarationDescriptor declarationDescriptor2 = declarationDescriptor;
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "descriptor");
        List<TypeParameter> list2 = list;
        Intrinsics.checkParameterIsNotNull(list, "typeParameterProtos");
        NameResolver nameResolver3 = nameResolver2;
        Intrinsics.checkParameterIsNotNull(nameResolver2, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable2, "typeTable");
        VersionRequirementTable versionRequirementTable3 = versionRequirementTable2;
        Intrinsics.checkParameterIsNotNull(versionRequirementTable3, "versionRequirementTable");
        Intrinsics.checkParameterIsNotNull(binaryVersion, "metadataVersion");
        DeserializationComponents deserializationComponents = this.components;
        if (!VersionSpecificBehaviorKt.isVersionRequirementTableWrittenCorrectly(binaryVersion)) {
            versionRequirementTable3 = this.versionRequirementTable;
        }
        DeserializationContext deserializationContext = new DeserializationContext(deserializationComponents, nameResolver2, declarationDescriptor, typeTable2, versionRequirementTable3, binaryVersion, this.containerSource, this.typeDeserializer, list);
        return deserializationContext;
    }
}

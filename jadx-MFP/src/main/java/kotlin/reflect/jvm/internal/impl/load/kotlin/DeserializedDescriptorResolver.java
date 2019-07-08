package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Set;
import kotlin.Pair;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedDescriptorResolver.kt */
public final class DeserializedDescriptorResolver {
    public static final Companion Companion = new Companion(null);
    private static final JvmMetadataVersion KOTLIN_1_1_EAP_METADATA_VERSION = new JvmMetadataVersion(1, 1, 2);
    private static final JvmMetadataVersion KOTLIN_1_3_M1_METADATA_VERSION = new JvmMetadataVersion(1, 1, 11);
    /* access modifiers changed from: private */
    @NotNull
    public static final JvmMetadataVersion KOTLIN_1_3_RC_METADATA_VERSION = new JvmMetadataVersion(1, 1, 13);
    @NotNull
    private static final Set<Kind> KOTLIN_CLASS = SetsKt.setOf(Kind.CLASS);
    private static final Set<Kind> KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART = SetsKt.setOf(Kind.FILE_FACADE, Kind.MULTIFILE_CLASS_PART);
    @NotNull
    public DeserializationComponents components;

    /* compiled from: DeserializedDescriptorResolver.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final JvmMetadataVersion getKOTLIN_1_3_RC_METADATA_VERSION$descriptors_jvm() {
            return DeserializedDescriptorResolver.KOTLIN_1_3_RC_METADATA_VERSION;
        }
    }

    @NotNull
    public final DeserializationComponents getComponents() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents;
    }

    public final void setComponents(@NotNull DeserializationComponentsForJava deserializationComponentsForJava) {
        Intrinsics.checkParameterIsNotNull(deserializationComponentsForJava, "components");
        this.components = deserializationComponentsForJava.getComponents();
    }

    /* access modifiers changed from: private */
    public final boolean getSkipMetadataVersionCheck() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents.getConfiguration().getSkipMetadataVersionCheck();
    }

    @Nullable
    public final ClassDescriptor resolveClass(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        Intrinsics.checkParameterIsNotNull(kotlinJvmBinaryClass, "kotlinClass");
        ClassData readClassData$descriptors_jvm = readClassData$descriptors_jvm(kotlinJvmBinaryClass);
        if (readClassData$descriptors_jvm == null) {
            return null;
        }
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents.getClassDeserializer().deserializeClass(kotlinJvmBinaryClass.getClassId(), readClassData$descriptors_jvm);
    }

    @Nullable
    public final ClassData readClassData$descriptors_jvm(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        Pair pair;
        Intrinsics.checkParameterIsNotNull(kotlinJvmBinaryClass, "kotlinClass");
        String[] readData$descriptors_jvm = readData$descriptors_jvm(kotlinJvmBinaryClass, KOTLIN_CLASS);
        if (readData$descriptors_jvm == null) {
            return null;
        }
        String[] strings = kotlinJvmBinaryClass.getClassHeader().getStrings();
        if (strings == null) {
            return null;
        }
        try {
            pair = JvmProtoBufUtil.readClassDataFrom(readData$descriptors_jvm, strings);
        } catch (InvalidProtocolBufferException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not read data from ");
            sb.append(kotlinJvmBinaryClass.getLocation());
            throw new IllegalStateException(sb.toString(), e);
        } catch (Throwable th) {
            if (getSkipMetadataVersionCheck() || kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible()) {
                throw th;
            }
            pair = null;
        }
        if (pair == null) {
            return null;
        }
        return new ClassData((JvmNameResolver) pair.component1(), (Class) pair.component2(), kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), new KotlinJvmBinarySourceElement(kotlinJvmBinaryClass, getIncompatibility(kotlinJvmBinaryClass), isPreReleaseInvisible(kotlinJvmBinaryClass)));
    }

    @Nullable
    public final MemberScope createKotlinPackagePartScope(@NotNull PackageFragmentDescriptor packageFragmentDescriptor, @NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        Pair pair;
        Intrinsics.checkParameterIsNotNull(packageFragmentDescriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(kotlinJvmBinaryClass, "kotlinClass");
        String[] readData$descriptors_jvm = readData$descriptors_jvm(kotlinJvmBinaryClass, KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART);
        if (readData$descriptors_jvm == null) {
            return null;
        }
        String[] strings = kotlinJvmBinaryClass.getClassHeader().getStrings();
        if (strings == null) {
            return null;
        }
        try {
            pair = JvmProtoBufUtil.readPackageDataFrom(readData$descriptors_jvm, strings);
        } catch (InvalidProtocolBufferException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not read data from ");
            sb.append(kotlinJvmBinaryClass.getLocation());
            throw new IllegalStateException(sb.toString(), e);
        } catch (Throwable th) {
            if (getSkipMetadataVersionCheck() || kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible()) {
                throw th;
            }
            pair = null;
        }
        if (pair == null) {
            return null;
        }
        JvmNameResolver jvmNameResolver = (JvmNameResolver) pair.component1();
        Package packageR = (Package) pair.component2();
        NameResolver nameResolver = jvmNameResolver;
        JvmPackagePartSource jvmPackagePartSource = new JvmPackagePartSource(kotlinJvmBinaryClass, packageR, nameResolver, getIncompatibility(kotlinJvmBinaryClass), isPreReleaseInvisible(kotlinJvmBinaryClass));
        BinaryVersion metadataVersion = kotlinJvmBinaryClass.getClassHeader().getMetadataVersion();
        DeserializedContainerSource deserializedContainerSource = jvmPackagePartSource;
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        DeserializedPackageMemberScope deserializedPackageMemberScope = new DeserializedPackageMemberScope(packageFragmentDescriptor, packageR, nameResolver, metadataVersion, deserializedContainerSource, deserializationComponents, DeserializedDescriptorResolver$createKotlinPackagePartScope$2.INSTANCE);
        return deserializedPackageMemberScope;
    }

    private final IncompatibleVersionErrorData<JvmMetadataVersion> getIncompatibility(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        if (getSkipMetadataVersionCheck() || kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible()) {
            return null;
        }
        return new IncompatibleVersionErrorData<>(kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), JvmMetadataVersion.INSTANCE, kotlinJvmBinaryClass.getLocation(), kotlinJvmBinaryClass.getClassId());
    }

    private final boolean isPreReleaseInvisible(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return (deserializationComponents.getConfiguration().getReportErrorsOnPreReleaseDependencies() && (kotlinJvmBinaryClass.getClassHeader().isPreRelease() || Intrinsics.areEqual((Object) kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), (Object) KOTLIN_1_1_EAP_METADATA_VERSION))) || isCompiledWith13M1(kotlinJvmBinaryClass);
    }

    private final boolean isCompiledWith13M1(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return !deserializationComponents.getConfiguration().getSkipMetadataVersionCheck() && kotlinJvmBinaryClass.getClassHeader().isPreRelease() && Intrinsics.areEqual((Object) kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), (Object) KOTLIN_1_3_M1_METADATA_VERSION);
    }

    @Nullable
    public final String[] readData$descriptors_jvm(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass, @NotNull Set<? extends Kind> set) {
        Intrinsics.checkParameterIsNotNull(kotlinJvmBinaryClass, "kotlinClass");
        Intrinsics.checkParameterIsNotNull(set, "expectedKinds");
        KotlinClassHeader classHeader = kotlinJvmBinaryClass.getClassHeader();
        String[] data = classHeader.getData();
        if (data == null) {
            data = classHeader.getIncompatibleData();
        }
        if (data == null || !set.contains(classHeader.getKind())) {
            return null;
        }
        return data;
    }
}

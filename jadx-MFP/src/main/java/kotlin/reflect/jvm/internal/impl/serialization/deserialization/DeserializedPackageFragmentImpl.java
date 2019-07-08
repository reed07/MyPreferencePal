package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolverImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedPackageFragmentImpl.kt */
public abstract class DeserializedPackageFragmentImpl extends DeserializedPackageFragment {
    private MemberScope _memberScope;
    private PackageFragment _proto;
    @NotNull
    private final ProtoBasedClassDataFinder classDataFinder;
    /* access modifiers changed from: private */
    public final DeserializedContainerSource containerSource;
    private final BinaryVersion metadataVersion;
    @NotNull
    private final NameResolverImpl nameResolver;

    public DeserializedPackageFragmentImpl(@NotNull FqName fqName, @NotNull StorageManager storageManager, @NotNull ModuleDescriptor moduleDescriptor, @NotNull PackageFragment packageFragment, @NotNull BinaryVersion binaryVersion, @Nullable DeserializedContainerSource deserializedContainerSource) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        Intrinsics.checkParameterIsNotNull(packageFragment, "proto");
        Intrinsics.checkParameterIsNotNull(binaryVersion, "metadataVersion");
        super(fqName, storageManager, moduleDescriptor);
        this.metadataVersion = binaryVersion;
        this.containerSource = deserializedContainerSource;
        StringTable strings = packageFragment.getStrings();
        Intrinsics.checkExpressionValueIsNotNull(strings, "proto.strings");
        QualifiedNameTable qualifiedNames = packageFragment.getQualifiedNames();
        Intrinsics.checkExpressionValueIsNotNull(qualifiedNames, "proto.qualifiedNames");
        this.nameResolver = new NameResolverImpl(strings, qualifiedNames);
        this.classDataFinder = new ProtoBasedClassDataFinder(packageFragment, this.nameResolver, this.metadataVersion, new DeserializedPackageFragmentImpl$classDataFinder$1(this));
        this._proto = packageFragment;
    }

    @NotNull
    public ProtoBasedClassDataFinder getClassDataFinder() {
        return this.classDataFinder;
    }

    public void initialize(@NotNull DeserializationComponents deserializationComponents) {
        Intrinsics.checkParameterIsNotNull(deserializationComponents, "components");
        PackageFragment packageFragment = this._proto;
        if (packageFragment != null) {
            this._proto = null;
            PackageFragmentDescriptor packageFragmentDescriptor = this;
            Package packageR = packageFragment.getPackage();
            Intrinsics.checkExpressionValueIsNotNull(packageR, "proto.`package`");
            DeserializedPackageMemberScope deserializedPackageMemberScope = new DeserializedPackageMemberScope(packageFragmentDescriptor, packageR, this.nameResolver, this.metadataVersion, this.containerSource, deserializationComponents, new DeserializedPackageFragmentImpl$initialize$1(this));
            this._memberScope = deserializedPackageMemberScope;
            return;
        }
        throw new IllegalStateException("Repeated call to DeserializedPackageFragmentImpl::initialize".toString());
    }

    @NotNull
    public MemberScope getMemberScope() {
        MemberScope memberScope = this._memberScope;
        if (memberScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_memberScope");
        }
        return memberScope;
    }
}

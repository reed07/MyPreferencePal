package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedPackageFragment.kt */
public abstract class DeserializedPackageFragment extends PackageFragmentDescriptorImpl {
    @NotNull
    private final StorageManager storageManager;

    @NotNull
    public abstract ClassDataFinder getClassDataFinder();

    public DeserializedPackageFragment(@NotNull FqName fqName, @NotNull StorageManager storageManager2, @NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(storageManager2, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        super(moduleDescriptor, fqName);
        this.storageManager = storageManager2;
    }

    public boolean hasTopLevelClass(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        MemberScope memberScope = getMemberScope();
        return (memberScope instanceof DeserializedMemberScope) && ((DeserializedMemberScope) memberScope).getClassNames$deserialization().contains(name);
    }
}

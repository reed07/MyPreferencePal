package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import org.jetbrains.annotations.NotNull;

/* compiled from: EmptyPackageFragmentDesciptor.kt */
public final class EmptyPackageFragmentDescriptor extends PackageFragmentDescriptorImpl {
    public EmptyPackageFragmentDescriptor(@NotNull ModuleDescriptor moduleDescriptor, @NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        super(moduleDescriptor, fqName);
    }

    @NotNull
    public Empty getMemberScope() {
        return Empty.INSTANCE;
    }
}

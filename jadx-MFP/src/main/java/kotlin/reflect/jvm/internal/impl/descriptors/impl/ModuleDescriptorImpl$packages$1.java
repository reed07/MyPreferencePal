package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

/* compiled from: ModuleDescriptorImpl.kt */
final class ModuleDescriptorImpl$packages$1 extends Lambda implements Function1<FqName, LazyPackageViewDescriptorImpl> {
    final /* synthetic */ ModuleDescriptorImpl this$0;

    ModuleDescriptorImpl$packages$1(ModuleDescriptorImpl moduleDescriptorImpl) {
        this.this$0 = moduleDescriptorImpl;
        super(1);
    }

    @NotNull
    public final LazyPackageViewDescriptorImpl invoke(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        ModuleDescriptorImpl moduleDescriptorImpl = this.this$0;
        return new LazyPackageViewDescriptorImpl(moduleDescriptorImpl, fqName, moduleDescriptorImpl.storageManager);
    }
}

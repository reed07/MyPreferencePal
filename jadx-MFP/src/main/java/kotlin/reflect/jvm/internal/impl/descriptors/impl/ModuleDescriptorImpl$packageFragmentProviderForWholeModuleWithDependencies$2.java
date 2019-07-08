package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import org.jetbrains.annotations.NotNull;

/* compiled from: ModuleDescriptorImpl.kt */
final class ModuleDescriptorImpl$packageFragmentProviderForWholeModuleWithDependencies$2 extends Lambda implements Function0<CompositePackageFragmentProvider> {
    final /* synthetic */ ModuleDescriptorImpl this$0;

    ModuleDescriptorImpl$packageFragmentProviderForWholeModuleWithDependencies$2(ModuleDescriptorImpl moduleDescriptorImpl) {
        this.this$0 = moduleDescriptorImpl;
        super(0);
    }

    @NotNull
    public final CompositePackageFragmentProvider invoke() {
        ModuleDependencies access$getDependencies$p = this.this$0.dependencies;
        if (access$getDependencies$p != null) {
            List allDependencies = access$getDependencies$p.getAllDependencies();
            boolean contains = allDependencies.contains(this.this$0);
            if (!_Assertions.ENABLED || contains) {
                Iterable<ModuleDescriptorImpl> iterable = allDependencies;
                for (ModuleDescriptorImpl moduleDescriptorImpl : iterable) {
                    boolean access$isInitialized$p = moduleDescriptorImpl.isInitialized();
                    if (_Assertions.ENABLED && !access$isInitialized$p) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Dependency module ");
                        sb.append(moduleDescriptorImpl.getId());
                        sb.append(" was not initialized by the time contents of dependent module ");
                        sb.append(this.this$0.getId());
                        sb.append(" were queried");
                        throw new AssertionError(sb.toString());
                    }
                }
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (ModuleDescriptorImpl access$getPackageFragmentProviderForModuleContent$p : iterable) {
                    PackageFragmentProvider access$getPackageFragmentProviderForModuleContent$p2 = access$getPackageFragmentProviderForModuleContent$p.packageFragmentProviderForModuleContent;
                    if (access$getPackageFragmentProviderForModuleContent$p2 == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList.add(access$getPackageFragmentProviderForModuleContent$p2);
                }
                return new CompositePackageFragmentProvider((List) arrayList);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Module ");
            sb2.append(this.this$0.getId());
            sb2.append(" is not contained in his own dependencies, this is probably a misconfiguration");
            throw new AssertionError(sb2.toString());
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Dependencies of module ");
        sb3.append(this.this$0.getId());
        sb3.append(" were not set before querying module content");
        throw new AssertionError(sb3.toString());
    }
}

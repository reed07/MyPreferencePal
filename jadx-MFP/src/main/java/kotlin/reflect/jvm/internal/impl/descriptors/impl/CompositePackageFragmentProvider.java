package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: CompositePackageFragmentProvider.kt */
public final class CompositePackageFragmentProvider implements PackageFragmentProvider {
    private final List<PackageFragmentProvider> providers;

    public CompositePackageFragmentProvider(@NotNull List<? extends PackageFragmentProvider> list) {
        Intrinsics.checkParameterIsNotNull(list, "providers");
        this.providers = list;
    }

    @NotNull
    public List<PackageFragmentDescriptor> getPackageFragments(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        ArrayList arrayList = new ArrayList();
        for (PackageFragmentProvider packageFragments : this.providers) {
            arrayList.addAll(packageFragments.getPackageFragments(fqName));
        }
        return CollectionsKt.toList(arrayList);
    }

    @NotNull
    public Collection<FqName> getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        HashSet hashSet = new HashSet();
        for (PackageFragmentProvider subPackagesOf : this.providers) {
            hashSet.addAll(subPackagesOf.getSubPackagesOf(fqName, function1));
        }
        return hashSet;
    }
}

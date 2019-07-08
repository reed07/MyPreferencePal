package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaPackageFragment.kt */
final class LazyJavaPackageFragment$subPackages$1 extends Lambda implements Function0<List<? extends FqName>> {
    final /* synthetic */ LazyJavaPackageFragment this$0;

    LazyJavaPackageFragment$subPackages$1(LazyJavaPackageFragment lazyJavaPackageFragment) {
        this.this$0 = lazyJavaPackageFragment;
        super(0);
    }

    @NotNull
    public final List<FqName> invoke() {
        Iterable<JavaPackage> subPackages = this.this$0.jPackage.getSubPackages();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subPackages, 10));
        for (JavaPackage fqName : subPackages) {
            arrayList.add(fqName.getFqName());
        }
        return (List) arrayList;
    }
}

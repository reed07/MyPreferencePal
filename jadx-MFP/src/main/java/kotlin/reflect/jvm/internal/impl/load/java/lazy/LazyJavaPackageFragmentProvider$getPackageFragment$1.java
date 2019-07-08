package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaPackageFragmentProvider.kt */
final class LazyJavaPackageFragmentProvider$getPackageFragment$1 extends Lambda implements Function0<LazyJavaPackageFragment> {
    final /* synthetic */ JavaPackage $jPackage;
    final /* synthetic */ LazyJavaPackageFragmentProvider this$0;

    LazyJavaPackageFragmentProvider$getPackageFragment$1(LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider, JavaPackage javaPackage) {
        this.this$0 = lazyJavaPackageFragmentProvider;
        this.$jPackage = javaPackage;
        super(0);
    }

    @NotNull
    public final LazyJavaPackageFragment invoke() {
        return new LazyJavaPackageFragment(this.this$0.c, this.$jPackage);
    }
}

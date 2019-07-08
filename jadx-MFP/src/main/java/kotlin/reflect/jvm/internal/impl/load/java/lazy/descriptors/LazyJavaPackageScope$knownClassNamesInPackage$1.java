package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaPackageScope.kt */
final class LazyJavaPackageScope$knownClassNamesInPackage$1 extends Lambda implements Function0<Set<? extends String>> {
    final /* synthetic */ LazyJavaResolverContext $c;
    final /* synthetic */ LazyJavaPackageScope this$0;

    LazyJavaPackageScope$knownClassNamesInPackage$1(LazyJavaPackageScope lazyJavaPackageScope, LazyJavaResolverContext lazyJavaResolverContext) {
        this.this$0 = lazyJavaPackageScope;
        this.$c = lazyJavaResolverContext;
        super(0);
    }

    @Nullable
    public final Set<String> invoke() {
        return this.$c.getComponents().getFinder().knownClassNamesInPackage(this.this$0.getOwnerDescriptor().getFqName());
    }
}

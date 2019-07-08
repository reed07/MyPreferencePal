package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaScope.kt */
final class LazyJavaScope$functionNamesLazy$2 extends Lambda implements Function0<Set<? extends Name>> {
    final /* synthetic */ LazyJavaScope this$0;

    LazyJavaScope$functionNamesLazy$2(LazyJavaScope lazyJavaScope) {
        this.this$0 = lazyJavaScope;
        super(0);
    }

    @NotNull
    public final Set<Name> invoke() {
        return this.this$0.computeFunctionNames(DescriptorKindFilter.FUNCTIONS, null);
    }
}

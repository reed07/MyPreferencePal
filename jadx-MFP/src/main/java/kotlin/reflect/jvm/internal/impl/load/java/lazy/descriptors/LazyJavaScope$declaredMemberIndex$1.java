package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaScope.kt */
final class LazyJavaScope$declaredMemberIndex$1 extends Lambda implements Function0<DeclaredMemberIndex> {
    final /* synthetic */ LazyJavaScope this$0;

    LazyJavaScope$declaredMemberIndex$1(LazyJavaScope lazyJavaScope) {
        this.this$0 = lazyJavaScope;
        super(0);
    }

    @NotNull
    public final DeclaredMemberIndex invoke() {
        return this.this$0.computeMemberIndex();
    }
}

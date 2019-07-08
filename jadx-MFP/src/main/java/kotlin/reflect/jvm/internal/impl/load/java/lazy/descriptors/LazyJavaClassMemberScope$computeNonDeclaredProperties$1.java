package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaClassMemberScope.kt */
final class LazyJavaClassMemberScope$computeNonDeclaredProperties$1 extends Lambda implements Function1<Name, Collection<? extends SimpleFunctionDescriptor>> {
    final /* synthetic */ LazyJavaClassMemberScope this$0;

    LazyJavaClassMemberScope$computeNonDeclaredProperties$1(LazyJavaClassMemberScope lazyJavaClassMemberScope) {
        this.this$0 = lazyJavaClassMemberScope;
        super(1);
    }

    @NotNull
    public final Collection<SimpleFunctionDescriptor> invoke(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "it");
        return this.this$0.searchMethodsByNameWithoutBuiltinMagic(name);
    }
}

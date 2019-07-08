package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaClassMemberScope.kt */
final class LazyJavaClassMemberScope$isVisibleAsFunctionInCurrentClass$$inlined$any$lambda$1 extends Lambda implements Function1<Name, Collection<? extends SimpleFunctionDescriptor>> {
    final /* synthetic */ SimpleFunctionDescriptor $function$inlined;
    final /* synthetic */ LazyJavaClassMemberScope this$0;

    LazyJavaClassMemberScope$isVisibleAsFunctionInCurrentClass$$inlined$any$lambda$1(LazyJavaClassMemberScope lazyJavaClassMemberScope, SimpleFunctionDescriptor simpleFunctionDescriptor) {
        this.this$0 = lazyJavaClassMemberScope;
        this.$function$inlined = simpleFunctionDescriptor;
        super(1);
    }

    @NotNull
    public final Collection<SimpleFunctionDescriptor> invoke(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "accessorName");
        if (Intrinsics.areEqual((Object) this.$function$inlined.getName(), (Object) name)) {
            return CollectionsKt.listOf(this.$function$inlined);
        }
        return CollectionsKt.plus(this.this$0.searchMethodsByNameWithoutBuiltinMagic(name), (Iterable<? extends T>) this.this$0.searchMethodsInSupertypesWithoutBuiltinMagic(name));
    }
}

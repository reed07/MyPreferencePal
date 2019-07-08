package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: GivenFunctionsMemberScope.kt */
final class GivenFunctionsMemberScope$allDescriptors$2 extends Lambda implements Function0<List<? extends DeclarationDescriptor>> {
    final /* synthetic */ GivenFunctionsMemberScope this$0;

    GivenFunctionsMemberScope$allDescriptors$2(GivenFunctionsMemberScope givenFunctionsMemberScope) {
        this.this$0 = givenFunctionsMemberScope;
        super(0);
    }

    @NotNull
    public final List<DeclarationDescriptor> invoke() {
        List computeDeclaredFunctions = this.this$0.computeDeclaredFunctions();
        return CollectionsKt.plus((Collection<? extends T>) computeDeclaredFunctions, (Iterable<? extends T>) this.this$0.createFakeOverrides(computeDeclaredFunctions));
    }
}

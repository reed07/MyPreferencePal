package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls;
import org.jetbrains.annotations.NotNull;

/* compiled from: SubstitutingScope.kt */
final class SubstitutingScope$_allDescriptors$2 extends Lambda implements Function0<Collection<? extends DeclarationDescriptor>> {
    final /* synthetic */ SubstitutingScope this$0;

    SubstitutingScope$_allDescriptors$2(SubstitutingScope substitutingScope) {
        this.this$0 = substitutingScope;
        super(0);
    }

    @NotNull
    public final Collection<DeclarationDescriptor> invoke() {
        SubstitutingScope substitutingScope = this.this$0;
        return substitutingScope.substitute(DefaultImpls.getContributedDescriptors$default(substitutingScope.workerScope, null, null, 3, null));
    }
}

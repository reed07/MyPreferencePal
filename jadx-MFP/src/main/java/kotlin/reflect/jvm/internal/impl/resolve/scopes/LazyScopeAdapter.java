package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyScopeAdapter.kt */
public final class LazyScopeAdapter extends AbstractScopeAdapter {
    private final NotNullLazyValue<MemberScope> scope;

    public LazyScopeAdapter(@NotNull NotNullLazyValue<? extends MemberScope> notNullLazyValue) {
        Intrinsics.checkParameterIsNotNull(notNullLazyValue, "scope");
        this.scope = notNullLazyValue;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public MemberScope getWorkerScope() {
        return (MemberScope) this.scope.invoke();
    }
}

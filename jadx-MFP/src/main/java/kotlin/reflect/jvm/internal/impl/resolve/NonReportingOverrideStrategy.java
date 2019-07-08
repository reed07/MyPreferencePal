package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: OverridingStrategy.kt */
public abstract class NonReportingOverrideStrategy extends OverridingStrategy {
    /* access modifiers changed from: protected */
    public abstract void conflict(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull CallableMemberDescriptor callableMemberDescriptor2);

    public void overrideConflict(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull CallableMemberDescriptor callableMemberDescriptor2) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "fromSuper");
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor2, "fromCurrent");
        conflict(callableMemberDescriptor, callableMemberDescriptor2);
    }

    public void inheritanceConflict(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull CallableMemberDescriptor callableMemberDescriptor2) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "first");
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor2, "second");
        conflict(callableMemberDescriptor, callableMemberDescriptor2);
    }
}

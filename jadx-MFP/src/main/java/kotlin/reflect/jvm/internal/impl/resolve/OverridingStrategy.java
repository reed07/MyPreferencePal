package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: OverridingStrategy.kt */
public abstract class OverridingStrategy {
    public abstract void addFakeOverride(@NotNull CallableMemberDescriptor callableMemberDescriptor);

    public abstract void inheritanceConflict(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull CallableMemberDescriptor callableMemberDescriptor2);

    public abstract void overrideConflict(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull CallableMemberDescriptor callableMemberDescriptor2);

    public void setOverriddenDescriptors(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull Collection<? extends CallableMemberDescriptor> collection) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "member");
        Intrinsics.checkParameterIsNotNull(collection, "overridden");
        callableMemberDescriptor.setOverriddenDescriptors(collection);
    }
}

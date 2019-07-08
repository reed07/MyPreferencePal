package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DescriptorUtils.kt */
public final class DescriptorUtilsKt$firstOverridden$2 extends AbstractNodeHandler<CallableMemberDescriptor, CallableMemberDescriptor> {
    final /* synthetic */ Function1 $predicate;
    final /* synthetic */ ObjectRef $result;

    DescriptorUtilsKt$firstOverridden$2(ObjectRef objectRef, Function1 function1) {
        this.$result = objectRef;
        this.$predicate = function1;
    }

    public boolean beforeChildren(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "current");
        return ((CallableMemberDescriptor) this.$result.element) == null;
    }

    public void afterChildren(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "current");
        if (((CallableMemberDescriptor) this.$result.element) == null && ((Boolean) this.$predicate.invoke(callableMemberDescriptor)).booleanValue()) {
            this.$result.element = callableMemberDescriptor;
        }
    }

    @Nullable
    public CallableMemberDescriptor result() {
        return (CallableMemberDescriptor) this.$result.element;
    }
}

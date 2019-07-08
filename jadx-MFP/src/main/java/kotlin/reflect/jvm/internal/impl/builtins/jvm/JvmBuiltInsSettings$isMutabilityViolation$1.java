package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
final class JvmBuiltInsSettings$isMutabilityViolation$1<N> implements Neighbors<N> {
    public static final JvmBuiltInsSettings$isMutabilityViolation$1 INSTANCE = new JvmBuiltInsSettings$isMutabilityViolation$1();

    JvmBuiltInsSettings$isMutabilityViolation$1() {
    }

    @NotNull
    public final Collection<? extends CallableMemberDescriptor> getNeighbors(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkExpressionValueIsNotNull(callableMemberDescriptor, "it");
        CallableMemberDescriptor original = callableMemberDescriptor.getOriginal();
        Intrinsics.checkExpressionValueIsNotNull(original, "it.original");
        return original.getOverriddenDescriptors();
    }
}

package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import org.jetbrains.annotations.NotNull;

/* compiled from: VisibilityUtil.kt */
public final class VisibilityUtilKt {
    @NotNull
    public static final CallableMemberDescriptor findMemberWithMaxVisibility(@NotNull Collection<? extends CallableMemberDescriptor> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "descriptors");
        boolean z = !collection.isEmpty();
        if (!_Assertions.ENABLED || z) {
            CallableMemberDescriptor callableMemberDescriptor = null;
            for (CallableMemberDescriptor callableMemberDescriptor2 : collection) {
                if (callableMemberDescriptor != null) {
                    Integer compare = Visibilities.compare(callableMemberDescriptor.getVisibility(), callableMemberDescriptor2.getVisibility());
                    if (compare != null) {
                        if (compare.intValue() >= 0) {
                        }
                    }
                }
                callableMemberDescriptor = callableMemberDescriptor2;
            }
            if (callableMemberDescriptor == null) {
                Intrinsics.throwNpe();
            }
            return callableMemberDescriptor;
        }
        throw new AssertionError("Assertion failed");
    }
}

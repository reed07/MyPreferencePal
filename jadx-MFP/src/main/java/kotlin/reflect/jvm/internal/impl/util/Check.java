package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: modifierChecks.kt */
public interface Check {

    /* compiled from: modifierChecks.kt */
    public static final class DefaultImpls {
        @Nullable
        public static String invoke(Check check, @NotNull FunctionDescriptor functionDescriptor) {
            Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
            if (!check.check(functionDescriptor)) {
                return check.getDescription();
            }
            return null;
        }
    }

    boolean check(@NotNull FunctionDescriptor functionDescriptor);

    @NotNull
    String getDescription();

    @Nullable
    String invoke(@NotNull FunctionDescriptor functionDescriptor);
}

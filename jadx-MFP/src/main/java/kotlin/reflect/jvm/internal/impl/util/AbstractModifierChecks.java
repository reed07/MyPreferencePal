package kotlin.reflect.jvm.internal.impl.util;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.util.CheckResult.IllegalFunctionName;
import org.jetbrains.annotations.NotNull;

/* compiled from: modifierChecks.kt */
public abstract class AbstractModifierChecks {
    @NotNull
    public abstract List<Checks> getChecks$descriptors();

    @NotNull
    public final CheckResult check(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        for (Checks checks : getChecks$descriptors()) {
            if (checks.isApplicable(functionDescriptor)) {
                return checks.checkAll(functionDescriptor);
            }
        }
        return IllegalFunctionName.INSTANCE;
    }
}

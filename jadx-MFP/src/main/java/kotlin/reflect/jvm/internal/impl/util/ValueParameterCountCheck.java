package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.util.Check.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: modifierChecks.kt */
public abstract class ValueParameterCountCheck implements Check {
    @NotNull
    private final String description;

    /* compiled from: modifierChecks.kt */
    public static final class AtLeast extends ValueParameterCountCheck {
        private final int n;

        public AtLeast(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append("must have at least ");
            sb.append(i);
            sb.append(" value parameter");
            sb.append(i > 1 ? "s" : "");
            super(sb.toString(), null);
            this.n = i;
        }

        public boolean check(@NotNull FunctionDescriptor functionDescriptor) {
            Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
            return functionDescriptor.getValueParameters().size() >= this.n;
        }
    }

    /* compiled from: modifierChecks.kt */
    public static final class Equals extends ValueParameterCountCheck {
        private final int n;

        public Equals(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append("must have exactly ");
            sb.append(i);
            sb.append(" value parameters");
            super(sb.toString(), null);
            this.n = i;
        }

        public boolean check(@NotNull FunctionDescriptor functionDescriptor) {
            Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
            return functionDescriptor.getValueParameters().size() == this.n;
        }
    }

    /* compiled from: modifierChecks.kt */
    public static final class NoValueParameters extends ValueParameterCountCheck {
        public static final NoValueParameters INSTANCE = new NoValueParameters();

        private NoValueParameters() {
            super("must have no value parameters", null);
        }

        public boolean check(@NotNull FunctionDescriptor functionDescriptor) {
            Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
            return functionDescriptor.getValueParameters().isEmpty();
        }
    }

    /* compiled from: modifierChecks.kt */
    public static final class SingleValueParameter extends ValueParameterCountCheck {
        public static final SingleValueParameter INSTANCE = new SingleValueParameter();

        private SingleValueParameter() {
            super("must have a single value parameter", null);
        }

        public boolean check(@NotNull FunctionDescriptor functionDescriptor) {
            Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
            return functionDescriptor.getValueParameters().size() == 1;
        }
    }

    private ValueParameterCountCheck(String str) {
        this.description = str;
    }

    public /* synthetic */ ValueParameterCountCheck(@NotNull String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @NotNull
    public String getDescription() {
        return this.description;
    }

    @Nullable
    public String invoke(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        return DefaultImpls.invoke(this, functionDescriptor);
    }
}

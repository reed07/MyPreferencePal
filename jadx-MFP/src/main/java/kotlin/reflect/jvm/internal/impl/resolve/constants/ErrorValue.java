package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: constantValues.kt */
public abstract class ErrorValue extends ConstantValue<Unit> {
    public static final Companion Companion = new Companion(null);

    /* compiled from: constantValues.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ErrorValue create(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "message");
            return new ErrorValueWithMessage(str);
        }
    }

    /* compiled from: constantValues.kt */
    public static final class ErrorValueWithMessage extends ErrorValue {
        @NotNull
        private final String message;

        public ErrorValueWithMessage(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "message");
            this.message = str;
        }

        @NotNull
        public SimpleType getType(@NotNull ModuleDescriptor moduleDescriptor) {
            Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
            SimpleType createErrorType = ErrorUtils.createErrorType(this.message);
            Intrinsics.checkExpressionValueIsNotNull(createErrorType, "ErrorUtils.createErrorType(message)");
            return createErrorType;
        }

        @NotNull
        public String toString() {
            return this.message;
        }
    }

    public ErrorValue() {
        super(Unit.INSTANCE);
    }

    @NotNull
    public Unit getValue() {
        throw new UnsupportedOperationException();
    }
}

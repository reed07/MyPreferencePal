package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import org.jetbrains.annotations.NotNull;

/* compiled from: deprecation.kt */
public final class DeprecationKt {
    @NotNull
    private static final UserDataKey<Object> DEPRECATED_FUNCTION_KEY = new DeprecationKt$DEPRECATED_FUNCTION_KEY$1();

    @NotNull
    public static final UserDataKey<Object> getDEPRECATED_FUNCTION_KEY() {
        return DEPRECATED_FUNCTION_KEY;
    }
}

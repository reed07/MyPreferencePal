package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

/* compiled from: ReflectionTypes.kt */
public final class ReflectionTypesKt {
    @NotNull
    private static final FqName KOTLIN_REFLECT_FQ_NAME = new FqName("kotlin.reflect");
    @NotNull
    private static final String K_FUNCTION_PREFIX = K_FUNCTION_PREFIX;
    @NotNull
    private static final String K_SUSPEND_FUNCTION_PREFIX = K_SUSPEND_FUNCTION_PREFIX;

    @NotNull
    public static final FqName getKOTLIN_REFLECT_FQ_NAME() {
        return KOTLIN_REFLECT_FQ_NAME;
    }
}

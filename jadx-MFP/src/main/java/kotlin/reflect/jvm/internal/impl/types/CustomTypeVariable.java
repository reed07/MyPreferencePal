package kotlin.reflect.jvm.internal.impl.types;

import org.jetbrains.annotations.NotNull;

/* compiled from: TypeCapabilities.kt */
public interface CustomTypeVariable {
    boolean isTypeVariable();

    @NotNull
    KotlinType substitutionResult(@NotNull KotlinType kotlinType);
}

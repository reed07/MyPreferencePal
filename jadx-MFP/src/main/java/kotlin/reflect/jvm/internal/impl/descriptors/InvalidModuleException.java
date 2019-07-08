package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: InvalidModuleException.kt */
public final class InvalidModuleException extends IllegalStateException {
    public InvalidModuleException(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        super(str);
    }
}

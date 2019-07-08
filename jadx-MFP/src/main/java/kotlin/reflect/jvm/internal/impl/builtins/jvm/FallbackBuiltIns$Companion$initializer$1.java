package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
final class FallbackBuiltIns$Companion$initializer$1 extends Lambda implements Function0<FallbackBuiltIns> {
    public static final FallbackBuiltIns$Companion$initializer$1 INSTANCE = new FallbackBuiltIns$Companion$initializer$1();

    FallbackBuiltIns$Companion$initializer$1() {
        super(0);
    }

    @NotNull
    public final FallbackBuiltIns invoke() {
        return new FallbackBuiltIns(null);
    }
}

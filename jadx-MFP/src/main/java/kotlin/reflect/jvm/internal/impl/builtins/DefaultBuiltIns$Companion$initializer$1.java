package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: DefaultBuiltIns.kt */
final class DefaultBuiltIns$Companion$initializer$1 extends Lambda implements Function0<DefaultBuiltIns> {
    public static final DefaultBuiltIns$Companion$initializer$1 INSTANCE = new DefaultBuiltIns$Companion$initializer$1();

    DefaultBuiltIns$Companion$initializer$1() {
        super(0);
    }

    @NotNull
    public final DefaultBuiltIns invoke() {
        return new DefaultBuiltIns(null);
    }
}

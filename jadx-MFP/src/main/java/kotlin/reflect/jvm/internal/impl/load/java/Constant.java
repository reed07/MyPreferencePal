package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: utils.kt */
public final class Constant extends JavaDefaultValue {
    @NotNull
    private final Object value;

    public Constant(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "value");
        super(null);
        this.value = obj;
    }
}

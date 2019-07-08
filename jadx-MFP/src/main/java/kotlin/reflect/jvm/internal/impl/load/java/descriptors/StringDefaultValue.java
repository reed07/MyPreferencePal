package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: util.kt */
public final class StringDefaultValue extends AnnotationDefaultValue {
    @NotNull
    private final String value;

    public StringDefaultValue(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        super(null);
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}

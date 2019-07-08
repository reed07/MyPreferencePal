package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: typeEnhancement.kt */
final class SimpleResult extends Result {
    @NotNull
    private final SimpleType type;

    public SimpleResult(@NotNull SimpleType simpleType, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(simpleType, "type");
        super(simpleType, i, z);
        this.type = simpleType;
    }

    @NotNull
    public SimpleType getType() {
        return this.type;
    }
}

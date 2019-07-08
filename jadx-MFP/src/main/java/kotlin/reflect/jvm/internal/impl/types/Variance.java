package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Variance.kt */
public enum Variance {
    INVARIANT("", true, true, 0),
    IN_VARIANCE("in", true, false, -1),
    OUT_VARIANCE("out", false, true, 1);
    
    private final boolean allowsInPosition;
    private final boolean allowsOutPosition;
    @NotNull
    private final String label;
    private final int superpositionFactor;

    protected Variance(String str, boolean z, @NotNull boolean z2, int i) {
        Intrinsics.checkParameterIsNotNull(str, "label");
        this.label = str;
        this.allowsInPosition = z;
        this.allowsOutPosition = z2;
        this.superpositionFactor = i;
    }

    @NotNull
    public final String getLabel() {
        return this.label;
    }

    public final boolean getAllowsOutPosition() {
        return this.allowsOutPosition;
    }

    @NotNull
    public String toString() {
        return this.label;
    }
}

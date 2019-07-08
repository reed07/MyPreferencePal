package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeEnhancement.kt */
class Result {
    private final int subtreeSize;
    @NotNull
    private final KotlinType type;
    private final boolean wereChanges;

    public Result(@NotNull KotlinType kotlinType, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        this.type = kotlinType;
        this.subtreeSize = i;
        this.wereChanges = z;
    }

    public final int getSubtreeSize() {
        return this.subtreeSize;
    }

    @NotNull
    public KotlinType getType() {
        return this.type;
    }

    public final boolean getWereChanges() {
        return this.wereChanges;
    }

    @Nullable
    public final KotlinType getTypeIfChanged() {
        KotlinType type2 = getType();
        if (this.wereChanges) {
            return type2;
        }
        return null;
    }
}

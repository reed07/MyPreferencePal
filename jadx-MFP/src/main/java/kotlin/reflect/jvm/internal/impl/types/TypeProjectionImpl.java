package kotlin.reflect.jvm.internal.impl.types;

import org.jetbrains.annotations.NotNull;

public class TypeProjectionImpl extends TypeProjectionBase {
    private final Variance projection;
    private final KotlinType type;

    public boolean isStarProjection() {
        return false;
    }

    public TypeProjectionImpl(@NotNull Variance variance, @NotNull KotlinType kotlinType) {
        this.projection = variance;
        this.type = kotlinType;
    }

    public TypeProjectionImpl(@NotNull KotlinType kotlinType) {
        this(Variance.INVARIANT, kotlinType);
    }

    @NotNull
    public Variance getProjectionKind() {
        return this.projection;
    }

    @NotNull
    public KotlinType getType() {
        return this.type;
    }
}

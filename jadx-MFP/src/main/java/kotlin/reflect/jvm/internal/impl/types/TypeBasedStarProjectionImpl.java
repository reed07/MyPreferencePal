package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: StarProjectionImpl.kt */
public final class TypeBasedStarProjectionImpl extends TypeProjectionBase {
    private final KotlinType _type;

    public boolean isStarProjection() {
        return true;
    }

    public TypeBasedStarProjectionImpl(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "_type");
        this._type = kotlinType;
    }

    @NotNull
    public Variance getProjectionKind() {
        return Variance.OUT_VARIANCE;
    }

    @NotNull
    public KotlinType getType() {
        return this._type;
    }
}

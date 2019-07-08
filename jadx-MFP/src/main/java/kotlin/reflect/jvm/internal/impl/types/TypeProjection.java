package kotlin.reflect.jvm.internal.impl.types;

import org.jetbrains.annotations.NotNull;

public interface TypeProjection {
    @NotNull
    Variance getProjectionKind();

    @NotNull
    KotlinType getType();

    boolean isStarProjection();
}

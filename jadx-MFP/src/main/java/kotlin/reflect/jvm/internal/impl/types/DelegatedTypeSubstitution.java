package kotlin.reflect.jvm.internal.impl.types;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeSubstitution.kt */
public class DelegatedTypeSubstitution extends TypeSubstitution {
    @NotNull
    private final TypeSubstitution substitution;

    public DelegatedTypeSubstitution(@NotNull TypeSubstitution typeSubstitution) {
        Intrinsics.checkParameterIsNotNull(typeSubstitution, "substitution");
        this.substitution = typeSubstitution;
    }

    @Nullable
    public TypeProjection get(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, IpcUtil.KEY_CODE);
        return this.substitution.get(kotlinType);
    }

    @NotNull
    public KotlinType prepareTopLevelType(@NotNull KotlinType kotlinType, @NotNull Variance variance) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "topLevelType");
        Intrinsics.checkParameterIsNotNull(variance, "position");
        return this.substitution.prepareTopLevelType(kotlinType, variance);
    }

    public boolean isEmpty() {
        return this.substitution.isEmpty();
    }

    public boolean approximateCapturedTypes() {
        return this.substitution.approximateCapturedTypes();
    }

    public boolean approximateContravariantCapturedTypes() {
        return this.substitution.approximateContravariantCapturedTypes();
    }

    @NotNull
    public Annotations filterAnnotations(@NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        return this.substitution.filterAnnotations(annotations);
    }
}

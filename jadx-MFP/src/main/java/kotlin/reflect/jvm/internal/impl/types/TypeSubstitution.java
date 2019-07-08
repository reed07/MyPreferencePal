package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeSubstitution.kt */
public abstract class TypeSubstitution {
    public static final Companion Companion = new Companion(null);
    @NotNull
    @JvmField
    public static final TypeSubstitution EMPTY = new TypeSubstitution$Companion$EMPTY$1();

    /* compiled from: TypeSubstitution.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public boolean approximateCapturedTypes() {
        return false;
    }

    public boolean approximateContravariantCapturedTypes() {
        return false;
    }

    @NotNull
    public Annotations filterAnnotations(@NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        return annotations;
    }

    @Nullable
    public abstract TypeProjection get(@NotNull KotlinType kotlinType);

    public boolean isEmpty() {
        return false;
    }

    @NotNull
    public KotlinType prepareTopLevelType(@NotNull KotlinType kotlinType, @NotNull Variance variance) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "topLevelType");
        Intrinsics.checkParameterIsNotNull(variance, "position");
        return kotlinType;
    }

    @NotNull
    public final TypeSubstitutor buildSubstitutor() {
        TypeSubstitutor create = TypeSubstitutor.create(this);
        Intrinsics.checkExpressionValueIsNotNull(create, "TypeSubstitutor.create(this)");
        return create;
    }
}

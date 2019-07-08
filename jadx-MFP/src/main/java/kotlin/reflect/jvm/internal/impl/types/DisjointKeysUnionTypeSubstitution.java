package kotlin.reflect.jvm.internal.impl.types;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DisjointKeysUnionTypeSubstitution.kt */
public final class DisjointKeysUnionTypeSubstitution extends TypeSubstitution {
    public static final Companion Companion = new Companion(null);
    private final TypeSubstitution first;
    private final TypeSubstitution second;

    /* compiled from: DisjointKeysUnionTypeSubstitution.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final TypeSubstitution create(@NotNull TypeSubstitution typeSubstitution, @NotNull TypeSubstitution typeSubstitution2) {
            Intrinsics.checkParameterIsNotNull(typeSubstitution, "first");
            Intrinsics.checkParameterIsNotNull(typeSubstitution2, "second");
            if (typeSubstitution.isEmpty()) {
                return typeSubstitution2;
            }
            if (typeSubstitution2.isEmpty()) {
                return typeSubstitution;
            }
            return new DisjointKeysUnionTypeSubstitution(typeSubstitution, typeSubstitution2, null);
        }
    }

    @JvmStatic
    @NotNull
    public static final TypeSubstitution create(@NotNull TypeSubstitution typeSubstitution, @NotNull TypeSubstitution typeSubstitution2) {
        return Companion.create(typeSubstitution, typeSubstitution2);
    }

    public boolean isEmpty() {
        return false;
    }

    public /* synthetic */ DisjointKeysUnionTypeSubstitution(@NotNull TypeSubstitution typeSubstitution, @NotNull TypeSubstitution typeSubstitution2, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeSubstitution, typeSubstitution2);
    }

    private DisjointKeysUnionTypeSubstitution(TypeSubstitution typeSubstitution, TypeSubstitution typeSubstitution2) {
        this.first = typeSubstitution;
        this.second = typeSubstitution2;
    }

    @Nullable
    public TypeProjection get(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, IpcUtil.KEY_CODE);
        TypeProjection typeProjection = this.first.get(kotlinType);
        return typeProjection != null ? typeProjection : this.second.get(kotlinType);
    }

    @NotNull
    public KotlinType prepareTopLevelType(@NotNull KotlinType kotlinType, @NotNull Variance variance) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "topLevelType");
        Intrinsics.checkParameterIsNotNull(variance, "position");
        return this.second.prepareTopLevelType(this.first.prepareTopLevelType(kotlinType, variance), variance);
    }

    public boolean approximateCapturedTypes() {
        return this.first.approximateCapturedTypes() || this.second.approximateCapturedTypes();
    }

    public boolean approximateContravariantCapturedTypes() {
        return this.first.approximateContravariantCapturedTypes() || this.second.approximateContravariantCapturedTypes();
    }

    @NotNull
    public Annotations filterAnnotations(@NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        return this.second.filterAnnotations(this.first.filterAnnotations(annotations));
    }
}

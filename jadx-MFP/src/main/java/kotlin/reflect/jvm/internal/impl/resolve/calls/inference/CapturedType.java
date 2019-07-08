package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: CapturedTypeConstructor.kt */
public final class CapturedType extends SimpleType implements SubtypingRepresentatives {
    @NotNull
    private final Annotations annotations;
    @NotNull
    private final CapturedTypeConstructor constructor;
    private final boolean isMarkedNullable;
    @NotNull
    private final TypeProjection typeProjection;

    public /* synthetic */ CapturedType(TypeProjection typeProjection2, CapturedTypeConstructor capturedTypeConstructor, boolean z, Annotations annotations2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            capturedTypeConstructor = new CapturedTypeConstructor(typeProjection2);
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            annotations2 = Annotations.Companion.getEMPTY();
        }
        this(typeProjection2, capturedTypeConstructor, z, annotations2);
    }

    @NotNull
    public CapturedTypeConstructor getConstructor() {
        return this.constructor;
    }

    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    @NotNull
    public Annotations getAnnotations() {
        return this.annotations;
    }

    public CapturedType(@NotNull TypeProjection typeProjection2, @NotNull CapturedTypeConstructor capturedTypeConstructor, boolean z, @NotNull Annotations annotations2) {
        Intrinsics.checkParameterIsNotNull(typeProjection2, "typeProjection");
        Intrinsics.checkParameterIsNotNull(capturedTypeConstructor, "constructor");
        Intrinsics.checkParameterIsNotNull(annotations2, "annotations");
        this.typeProjection = typeProjection2;
        this.constructor = capturedTypeConstructor;
        this.isMarkedNullable = z;
        this.annotations = annotations2;
    }

    @NotNull
    public List<TypeProjection> getArguments() {
        return CollectionsKt.emptyList();
    }

    @NotNull
    public MemberScope getMemberScope() {
        MemberScope createErrorScope = ErrorUtils.createErrorScope("No member resolution should be done on captured type, it used only during constraint system resolution", true);
        Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…system resolution\", true)");
        return createErrorScope;
    }

    @NotNull
    public KotlinType getSubTypeRepresentative() {
        Variance variance = Variance.OUT_VARIANCE;
        SimpleType nullableAnyType = TypeUtilsKt.getBuiltIns(this).getNullableAnyType();
        Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "builtIns.nullableAnyType");
        return representative(variance, nullableAnyType);
    }

    @NotNull
    public KotlinType getSuperTypeRepresentative() {
        Variance variance = Variance.IN_VARIANCE;
        SimpleType nothingType = TypeUtilsKt.getBuiltIns(this).getNothingType();
        Intrinsics.checkExpressionValueIsNotNull(nothingType, "builtIns.nothingType");
        return representative(variance, nothingType);
    }

    private final KotlinType representative(Variance variance, KotlinType kotlinType) {
        if (this.typeProjection.getProjectionKind() == variance) {
            kotlinType = this.typeProjection.getType();
        }
        Intrinsics.checkExpressionValueIsNotNull(kotlinType, "if (typeProjection.proje…jection.type else default");
        return kotlinType;
    }

    public boolean sameTypeConstructor(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        return getConstructor() == kotlinType.getConstructor();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Captured(");
        sb.append(this.typeProjection);
        sb.append(')');
        sb.append(isMarkedNullable() ? "?" : "");
        return sb.toString();
    }

    @NotNull
    public CapturedType makeNullableAsSpecified(boolean z) {
        if (z == isMarkedNullable()) {
            return this;
        }
        return new CapturedType(this.typeProjection, getConstructor(), z, getAnnotations());
    }

    @NotNull
    public CapturedType replaceAnnotations(@NotNull Annotations annotations2) {
        Intrinsics.checkParameterIsNotNull(annotations2, "newAnnotations");
        return new CapturedType(this.typeProjection, getConstructor(), isMarkedNullable(), annotations2);
    }
}

package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import java.util.Collection;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CapturedTypeConstructor.kt */
public final class CapturedTypeConstructor implements TypeConstructor {
    @Nullable
    private NewCapturedTypeConstructor newTypeConstructor;
    @NotNull
    private final TypeProjection typeProjection;

    @Nullable
    public Void getDeclarationDescriptor() {
        return null;
    }

    public boolean isDenotable() {
        return false;
    }

    public CapturedTypeConstructor(@NotNull TypeProjection typeProjection2) {
        Intrinsics.checkParameterIsNotNull(typeProjection2, "typeProjection");
        this.typeProjection = typeProjection2;
        boolean z = this.typeProjection.getProjectionKind() != Variance.INVARIANT;
        if (_Assertions.ENABLED && !z) {
            StringBuilder sb = new StringBuilder();
            sb.append("Only nontrivial projections can be captured, not: ");
            sb.append(this.typeProjection);
            throw new AssertionError(sb.toString());
        }
    }

    @NotNull
    public final TypeProjection getTypeProjection() {
        return this.typeProjection;
    }

    @Nullable
    public final NewCapturedTypeConstructor getNewTypeConstructor() {
        return this.newTypeConstructor;
    }

    public final void setNewTypeConstructor(@Nullable NewCapturedTypeConstructor newCapturedTypeConstructor) {
        this.newTypeConstructor = newCapturedTypeConstructor;
    }

    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        return CollectionsKt.emptyList();
    }

    @NotNull
    public Collection<KotlinType> getSupertypes() {
        KotlinType kotlinType;
        if (this.typeProjection.getProjectionKind() == Variance.OUT_VARIANCE) {
            kotlinType = this.typeProjection.getType();
        } else {
            kotlinType = getBuiltIns().getNullableAnyType();
        }
        Intrinsics.checkExpressionValueIsNotNull(kotlinType, "if (typeProjection.proje… builtIns.nullableAnyType");
        return CollectionsKt.listOf(kotlinType);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CapturedTypeConstructor(");
        sb.append(this.typeProjection);
        sb.append(')');
        return sb.toString();
    }

    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        KotlinBuiltIns builtIns = this.typeProjection.getType().getConstructor().getBuiltIns();
        Intrinsics.checkExpressionValueIsNotNull(builtIns, "typeProjection.type.constructor.builtIns");
        return builtIns;
    }
}

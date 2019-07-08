package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CapturedTypeConstructor.kt */
public final class CapturedTypeConstructorKt$wrapWithCapturingSubstitution$2 extends DelegatedTypeSubstitution {
    final /* synthetic */ boolean $needApproximation;
    final /* synthetic */ TypeSubstitution $this_wrapWithCapturingSubstitution;

    CapturedTypeConstructorKt$wrapWithCapturingSubstitution$2(TypeSubstitution typeSubstitution, boolean z, TypeSubstitution typeSubstitution2) {
        this.$this_wrapWithCapturingSubstitution = typeSubstitution;
        this.$needApproximation = z;
        super(typeSubstitution2);
    }

    public boolean approximateContravariantCapturedTypes() {
        return this.$needApproximation;
    }

    @Nullable
    public TypeProjection get(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, IpcUtil.KEY_CODE);
        TypeProjection typeProjection = super.get(kotlinType);
        if (typeProjection == null) {
            return null;
        }
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof TypeParameterDescriptor)) {
            declarationDescriptor = null;
        }
        return CapturedTypeConstructorKt.createCapturedIfNeeded(typeProjection, (TypeParameterDescriptor) declarationDescriptor);
    }
}

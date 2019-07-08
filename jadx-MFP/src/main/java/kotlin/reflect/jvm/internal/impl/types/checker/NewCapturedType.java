package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NewCapturedType.kt */
public final class NewCapturedType extends SimpleType {
    @NotNull
    private final Annotations annotations;
    @NotNull
    private final CaptureStatus captureStatus;
    @NotNull
    private final NewCapturedTypeConstructor constructor;
    private final boolean isMarkedNullable;
    @Nullable
    private final UnwrappedType lowerType;

    @NotNull
    public NewCapturedTypeConstructor getConstructor() {
        return this.constructor;
    }

    @Nullable
    public final UnwrappedType getLowerType() {
        return this.lowerType;
    }

    public /* synthetic */ NewCapturedType(CaptureStatus captureStatus2, NewCapturedTypeConstructor newCapturedTypeConstructor, UnwrappedType unwrappedType, Annotations annotations2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(captureStatus2, newCapturedTypeConstructor, unwrappedType, (i & 8) != 0 ? Annotations.Companion.getEMPTY() : annotations2, (i & 16) != 0 ? false : z);
    }

    @NotNull
    public Annotations getAnnotations() {
        return this.annotations;
    }

    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    public NewCapturedType(@NotNull CaptureStatus captureStatus2, @NotNull NewCapturedTypeConstructor newCapturedTypeConstructor, @Nullable UnwrappedType unwrappedType, @NotNull Annotations annotations2, boolean z) {
        Intrinsics.checkParameterIsNotNull(captureStatus2, "captureStatus");
        Intrinsics.checkParameterIsNotNull(newCapturedTypeConstructor, "constructor");
        Intrinsics.checkParameterIsNotNull(annotations2, "annotations");
        this.captureStatus = captureStatus2;
        this.constructor = newCapturedTypeConstructor;
        this.lowerType = unwrappedType;
        this.annotations = annotations2;
        this.isMarkedNullable = z;
    }

    public NewCapturedType(@NotNull CaptureStatus captureStatus2, @Nullable UnwrappedType unwrappedType, @NotNull TypeProjection typeProjection) {
        Intrinsics.checkParameterIsNotNull(captureStatus2, "captureStatus");
        Intrinsics.checkParameterIsNotNull(typeProjection, "projection");
        this(captureStatus2, new NewCapturedTypeConstructor(typeProjection, null, 2, null), unwrappedType, null, false, 24, null);
    }

    @NotNull
    public List<TypeProjection> getArguments() {
        return CollectionsKt.emptyList();
    }

    @NotNull
    public MemberScope getMemberScope() {
        MemberScope createErrorScope = ErrorUtils.createErrorScope("No member resolution should be done on captured type!", true);
        Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…on captured type!\", true)");
        return createErrorScope;
    }

    @NotNull
    public NewCapturedType replaceAnnotations(@NotNull Annotations annotations2) {
        Intrinsics.checkParameterIsNotNull(annotations2, "newAnnotations");
        NewCapturedType newCapturedType = new NewCapturedType(this.captureStatus, getConstructor(), this.lowerType, annotations2, isMarkedNullable());
        return newCapturedType;
    }

    @NotNull
    public NewCapturedType makeNullableAsSpecified(boolean z) {
        NewCapturedType newCapturedType = new NewCapturedType(this.captureStatus, getConstructor(), this.lowerType, getAnnotations(), z);
        return newCapturedType;
    }
}

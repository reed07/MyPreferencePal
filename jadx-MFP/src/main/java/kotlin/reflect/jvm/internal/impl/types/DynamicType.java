package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: dynamicTypes.kt */
public final class DynamicType extends FlexibleType {
    @NotNull
    private final Annotations annotations;

    public boolean isMarkedNullable() {
        return false;
    }

    @NotNull
    public DynamicType makeNullableAsSpecified(boolean z) {
        return this;
    }

    @NotNull
    public String render(@NotNull DescriptorRenderer descriptorRenderer, @NotNull DescriptorRendererOptions descriptorRendererOptions) {
        Intrinsics.checkParameterIsNotNull(descriptorRenderer, "renderer");
        Intrinsics.checkParameterIsNotNull(descriptorRendererOptions, "options");
        return "dynamic";
    }

    public DynamicType(@NotNull KotlinBuiltIns kotlinBuiltIns, @NotNull Annotations annotations2) {
        Intrinsics.checkParameterIsNotNull(kotlinBuiltIns, "builtIns");
        Intrinsics.checkParameterIsNotNull(annotations2, "annotations");
        SimpleType nothingType = kotlinBuiltIns.getNothingType();
        Intrinsics.checkExpressionValueIsNotNull(nothingType, "builtIns.nothingType");
        SimpleType nullableAnyType = kotlinBuiltIns.getNullableAnyType();
        Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "builtIns.nullableAnyType");
        super(nothingType, nullableAnyType);
        this.annotations = annotations2;
    }

    @NotNull
    public Annotations getAnnotations() {
        return this.annotations;
    }

    @NotNull
    public SimpleType getDelegate() {
        return getUpperBound();
    }

    @NotNull
    public DynamicType replaceAnnotations(@NotNull Annotations annotations2) {
        Intrinsics.checkParameterIsNotNull(annotations2, "newAnnotations");
        return new DynamicType(TypeUtilsKt.getBuiltIns(getDelegate()), annotations2);
    }
}

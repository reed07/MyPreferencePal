package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy.FULLY_QUALIFIED;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import org.jetbrains.annotations.NotNull;

/* compiled from: CapturedTypeApproximation.kt */
final class CapturedTypeApproximationKt$toTypeProjection$1$descriptorRenderer$1 extends Lambda implements Function1<DescriptorRendererOptions, Unit> {
    public static final CapturedTypeApproximationKt$toTypeProjection$1$descriptorRenderer$1 INSTANCE = new CapturedTypeApproximationKt$toTypeProjection$1$descriptorRenderer$1();

    CapturedTypeApproximationKt$toTypeProjection$1$descriptorRenderer$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DescriptorRendererOptions) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DescriptorRendererOptions descriptorRendererOptions) {
        Intrinsics.checkParameterIsNotNull(descriptorRendererOptions, "receiver$0");
        descriptorRendererOptions.setClassifierNamePolicy(FULLY_QUALIFIED.INSTANCE);
    }
}

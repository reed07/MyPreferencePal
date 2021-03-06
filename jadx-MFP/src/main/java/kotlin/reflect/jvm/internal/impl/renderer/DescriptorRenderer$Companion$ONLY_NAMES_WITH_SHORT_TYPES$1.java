package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy.SHORT;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorRenderer.kt */
final class DescriptorRenderer$Companion$ONLY_NAMES_WITH_SHORT_TYPES$1 extends Lambda implements Function1<DescriptorRendererOptions, Unit> {
    public static final DescriptorRenderer$Companion$ONLY_NAMES_WITH_SHORT_TYPES$1 INSTANCE = new DescriptorRenderer$Companion$ONLY_NAMES_WITH_SHORT_TYPES$1();

    DescriptorRenderer$Companion$ONLY_NAMES_WITH_SHORT_TYPES$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DescriptorRendererOptions) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DescriptorRendererOptions descriptorRendererOptions) {
        Intrinsics.checkParameterIsNotNull(descriptorRendererOptions, "receiver$0");
        descriptorRendererOptions.setWithDefinedIn(false);
        descriptorRendererOptions.setModifiers(SetsKt.emptySet());
        descriptorRendererOptions.setClassifierNamePolicy(SHORT.INSTANCE);
        descriptorRendererOptions.setWithoutTypeParameters(true);
        descriptorRendererOptions.setParameterNameRenderingPolicy(ParameterNameRenderingPolicy.NONE);
        descriptorRendererOptions.setReceiverAfterName(true);
        descriptorRendererOptions.setRenderCompanionObjectName(true);
        descriptorRendererOptions.setWithoutSuperTypes(true);
        descriptorRendererOptions.setStartFromName(true);
    }
}

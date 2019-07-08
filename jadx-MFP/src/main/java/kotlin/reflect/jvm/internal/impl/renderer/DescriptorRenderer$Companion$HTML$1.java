package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorRenderer.kt */
final class DescriptorRenderer$Companion$HTML$1 extends Lambda implements Function1<DescriptorRendererOptions, Unit> {
    public static final DescriptorRenderer$Companion$HTML$1 INSTANCE = new DescriptorRenderer$Companion$HTML$1();

    DescriptorRenderer$Companion$HTML$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DescriptorRendererOptions) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DescriptorRendererOptions descriptorRendererOptions) {
        Intrinsics.checkParameterIsNotNull(descriptorRendererOptions, "receiver$0");
        descriptorRendererOptions.setTextFormat(RenderingFormat.HTML);
        descriptorRendererOptions.setModifiers(DescriptorRendererModifier.ALL);
    }
}

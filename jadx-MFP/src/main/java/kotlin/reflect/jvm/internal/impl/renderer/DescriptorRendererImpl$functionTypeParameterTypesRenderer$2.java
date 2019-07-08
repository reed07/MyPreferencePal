package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorRendererImpl.kt */
final class DescriptorRendererImpl$functionTypeParameterTypesRenderer$2 extends Lambda implements Function0<DescriptorRenderer> {
    final /* synthetic */ DescriptorRendererImpl this$0;

    DescriptorRendererImpl$functionTypeParameterTypesRenderer$2(DescriptorRendererImpl descriptorRendererImpl) {
        this.this$0 = descriptorRendererImpl;
        super(0);
    }

    @NotNull
    public final DescriptorRenderer invoke() {
        return this.this$0.withOptions(AnonymousClass1.INSTANCE);
    }
}

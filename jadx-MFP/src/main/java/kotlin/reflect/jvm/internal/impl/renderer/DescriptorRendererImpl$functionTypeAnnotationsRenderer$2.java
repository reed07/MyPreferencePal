package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorRendererImpl.kt */
final class DescriptorRendererImpl$functionTypeAnnotationsRenderer$2 extends Lambda implements Function0<DescriptorRendererImpl> {
    final /* synthetic */ DescriptorRendererImpl this$0;

    DescriptorRendererImpl$functionTypeAnnotationsRenderer$2(DescriptorRendererImpl descriptorRendererImpl) {
        this.this$0 = descriptorRendererImpl;
        super(0);
    }

    @NotNull
    public final DescriptorRendererImpl invoke() {
        DescriptorRenderer withOptions = this.this$0.withOptions(AnonymousClass1.INSTANCE);
        if (withOptions != null) {
            return (DescriptorRendererImpl) withOptions;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.renderer.DescriptorRendererImpl");
    }
}

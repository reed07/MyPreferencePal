package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorRendererImpl.kt */
final class DescriptorRendererImpl$renderSuperTypes$1 extends Lambda implements Function1<KotlinType, String> {
    final /* synthetic */ DescriptorRendererImpl this$0;

    DescriptorRendererImpl$renderSuperTypes$1(DescriptorRendererImpl descriptorRendererImpl) {
        this.this$0 = descriptorRendererImpl;
        super(1);
    }

    @NotNull
    public final String invoke(KotlinType kotlinType) {
        DescriptorRendererImpl descriptorRendererImpl = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(kotlinType, "it");
        return descriptorRendererImpl.renderType(kotlinType);
    }
}

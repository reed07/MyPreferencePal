package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorRendererImpl.kt */
final class DescriptorRendererImpl$renderConstant$1 extends Lambda implements Function1<ConstantValue<?>, String> {
    final /* synthetic */ DescriptorRendererImpl this$0;

    DescriptorRendererImpl$renderConstant$1(DescriptorRendererImpl descriptorRendererImpl) {
        this.this$0 = descriptorRendererImpl;
        super(1);
    }

    @NotNull
    public final String invoke(@NotNull ConstantValue<?> constantValue) {
        Intrinsics.checkParameterIsNotNull(constantValue, "it");
        return this.this$0.renderConstant(constantValue);
    }
}

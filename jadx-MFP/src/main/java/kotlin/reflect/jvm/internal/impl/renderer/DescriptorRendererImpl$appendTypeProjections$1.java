package kotlin.reflect.jvm.internal.impl.renderer;

import com.brightcove.player.event.EventType;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorRendererImpl.kt */
final class DescriptorRendererImpl$appendTypeProjections$1 extends Lambda implements Function1<TypeProjection, CharSequence> {
    final /* synthetic */ DescriptorRendererImpl this$0;

    DescriptorRendererImpl$appendTypeProjections$1(DescriptorRendererImpl descriptorRendererImpl) {
        this.this$0 = descriptorRendererImpl;
        super(1);
    }

    @NotNull
    public final CharSequence invoke(@NotNull TypeProjection typeProjection) {
        Intrinsics.checkParameterIsNotNull(typeProjection, "it");
        if (typeProjection.isStarProjection()) {
            return EventType.ANY;
        }
        DescriptorRendererImpl descriptorRendererImpl = this.this$0;
        KotlinType type = typeProjection.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "it.type");
        String renderType = descriptorRendererImpl.renderType(type);
        if (typeProjection.getProjectionKind() != Variance.INVARIANT) {
            StringBuilder sb = new StringBuilder();
            sb.append(typeProjection.getProjectionKind());
            sb.append(' ');
            sb.append(renderType);
            renderType = sb.toString();
        }
        return renderType;
    }
}

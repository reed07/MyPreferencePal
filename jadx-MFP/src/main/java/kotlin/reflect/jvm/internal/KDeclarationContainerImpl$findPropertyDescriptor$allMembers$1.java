package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KDeclarationContainerImpl.kt */
final class KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1 extends Lambda implements Function1<PropertyDescriptor, String> {
    public static final KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1 INSTANCE = new KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1();

    KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1() {
        super(1);
    }

    @NotNull
    public final String invoke(@NotNull PropertyDescriptor propertyDescriptor) {
        Intrinsics.checkParameterIsNotNull(propertyDescriptor, "descriptor");
        StringBuilder sb = new StringBuilder();
        sb.append(DescriptorRenderer.DEBUG_TEXT.render(propertyDescriptor));
        sb.append(" | ");
        sb.append(RuntimeTypeMapper.INSTANCE.mapPropertySignature(propertyDescriptor));
        return sb.toString();
    }
}

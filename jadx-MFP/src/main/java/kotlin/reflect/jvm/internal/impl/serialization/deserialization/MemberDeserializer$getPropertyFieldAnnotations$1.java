package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import org.jetbrains.annotations.NotNull;

/* compiled from: MemberDeserializer.kt */
final class MemberDeserializer$getPropertyFieldAnnotations$1 extends Lambda implements Function0<List<? extends AnnotationDescriptor>> {
    final /* synthetic */ boolean $isDelegate;
    final /* synthetic */ Property $proto;
    final /* synthetic */ MemberDeserializer this$0;

    MemberDeserializer$getPropertyFieldAnnotations$1(MemberDeserializer memberDeserializer, boolean z, Property property) {
        this.this$0 = memberDeserializer;
        this.$isDelegate = z;
        this.$proto = property;
        super(0);
    }

    @NotNull
    public final List<AnnotationDescriptor> invoke() {
        MemberDeserializer memberDeserializer = this.this$0;
        ProtoContainer access$asProtoContainer = memberDeserializer.asProtoContainer(memberDeserializer.c.getContainingDeclaration());
        List<AnnotationDescriptor> list = access$asProtoContainer != null ? this.$isDelegate ? CollectionsKt.toList(this.this$0.c.getComponents().getAnnotationAndConstantLoader().loadPropertyDelegateFieldAnnotations(access$asProtoContainer, this.$proto)) : CollectionsKt.toList(this.this$0.c.getComponents().getAnnotationAndConstantLoader().loadPropertyBackingFieldAnnotations(access$asProtoContainer, this.$proto)) : null;
        return list != null ? list : CollectionsKt.emptyList();
    }
}

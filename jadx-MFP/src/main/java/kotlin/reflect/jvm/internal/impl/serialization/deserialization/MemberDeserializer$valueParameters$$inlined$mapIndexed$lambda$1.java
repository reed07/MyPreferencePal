package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.jetbrains.annotations.NotNull;

/* compiled from: MemberDeserializer.kt */
final class MemberDeserializer$valueParameters$$inlined$mapIndexed$lambda$1 extends Lambda implements Function0<List<? extends AnnotationDescriptor>> {
    final /* synthetic */ MessageLite $callable$inlined;
    final /* synthetic */ CallableDescriptor $callableDescriptor$inlined;
    final /* synthetic */ ProtoContainer $containerOfCallable$inlined;
    final /* synthetic */ int $i;
    final /* synthetic */ AnnotatedCallableKind $kind$inlined;
    final /* synthetic */ ValueParameter $proto;
    final /* synthetic */ MemberDeserializer this$0;

    MemberDeserializer$valueParameters$$inlined$mapIndexed$lambda$1(int i, ValueParameter valueParameter, MemberDeserializer memberDeserializer, ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind, CallableDescriptor callableDescriptor) {
        this.$i = i;
        this.$proto = valueParameter;
        this.this$0 = memberDeserializer;
        this.$containerOfCallable$inlined = protoContainer;
        this.$callable$inlined = messageLite;
        this.$kind$inlined = annotatedCallableKind;
        this.$callableDescriptor$inlined = callableDescriptor;
        super(0);
    }

    @NotNull
    public final List<AnnotationDescriptor> invoke() {
        return CollectionsKt.toList(this.this$0.c.getComponents().getAnnotationAndConstantLoader().loadValueParameterAnnotations(this.$containerOfCallable$inlined, this.$callable$inlined, this.$kind$inlined, this.$i, this.$proto));
    }
}

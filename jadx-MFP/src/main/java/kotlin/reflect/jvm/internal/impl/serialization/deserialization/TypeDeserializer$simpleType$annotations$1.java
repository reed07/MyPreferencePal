package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeDeserializer.kt */
final class TypeDeserializer$simpleType$annotations$1 extends Lambda implements Function0<List<? extends AnnotationDescriptor>> {
    final /* synthetic */ Type $proto;
    final /* synthetic */ TypeDeserializer this$0;

    TypeDeserializer$simpleType$annotations$1(TypeDeserializer typeDeserializer, Type type) {
        this.this$0 = typeDeserializer;
        this.$proto = type;
        super(0);
    }

    @NotNull
    public final List<AnnotationDescriptor> invoke() {
        return this.this$0.c.getComponents().getAnnotationAndConstantLoader().loadTypeAnnotations(this.$proto, this.this$0.c.getNameResolver());
    }
}

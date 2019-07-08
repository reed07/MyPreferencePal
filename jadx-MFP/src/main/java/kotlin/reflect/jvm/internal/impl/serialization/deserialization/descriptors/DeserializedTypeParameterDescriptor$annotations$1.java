package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedTypeParameterDescriptor.kt */
final class DeserializedTypeParameterDescriptor$annotations$1 extends Lambda implements Function0<List<? extends AnnotationDescriptor>> {
    final /* synthetic */ DeserializedTypeParameterDescriptor this$0;

    DeserializedTypeParameterDescriptor$annotations$1(DeserializedTypeParameterDescriptor deserializedTypeParameterDescriptor) {
        this.this$0 = deserializedTypeParameterDescriptor;
        super(0);
    }

    @NotNull
    public final List<AnnotationDescriptor> invoke() {
        return CollectionsKt.toList(this.this$0.c.getComponents().getAnnotationAndConstantLoader().loadTypeParameterAnnotations(this.this$0.proto, this.this$0.c.getNameResolver()));
    }
}

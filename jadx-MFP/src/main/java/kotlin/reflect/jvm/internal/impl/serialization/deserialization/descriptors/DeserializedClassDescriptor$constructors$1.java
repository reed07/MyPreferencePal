package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedClassDescriptor.kt */
final class DeserializedClassDescriptor$constructors$1 extends Lambda implements Function0<Collection<? extends ClassConstructorDescriptor>> {
    final /* synthetic */ DeserializedClassDescriptor this$0;

    DeserializedClassDescriptor$constructors$1(DeserializedClassDescriptor deserializedClassDescriptor) {
        this.this$0 = deserializedClassDescriptor;
        super(0);
    }

    @NotNull
    public final Collection<ClassConstructorDescriptor> invoke() {
        return this.this$0.computeConstructors();
    }
}

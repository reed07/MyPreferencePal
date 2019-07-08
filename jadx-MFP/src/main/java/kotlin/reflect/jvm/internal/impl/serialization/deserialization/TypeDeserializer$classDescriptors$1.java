package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeDeserializer.kt */
final class TypeDeserializer$classDescriptors$1 extends Lambda implements Function1<Integer, ClassDescriptor> {
    final /* synthetic */ TypeDeserializer this$0;

    TypeDeserializer$classDescriptors$1(TypeDeserializer typeDeserializer) {
        this.this$0 = typeDeserializer;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    @Nullable
    public final ClassDescriptor invoke(int i) {
        return this.this$0.computeClassDescriptor(i);
    }
}

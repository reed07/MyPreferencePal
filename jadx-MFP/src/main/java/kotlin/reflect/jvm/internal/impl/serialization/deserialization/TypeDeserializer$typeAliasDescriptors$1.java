package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeDeserializer.kt */
final class TypeDeserializer$typeAliasDescriptors$1 extends Lambda implements Function1<Integer, ClassifierDescriptor> {
    final /* synthetic */ TypeDeserializer this$0;

    TypeDeserializer$typeAliasDescriptors$1(TypeDeserializer typeDeserializer) {
        this.this$0 = typeDeserializer;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    @Nullable
    public final ClassifierDescriptor invoke(int i) {
        return this.this$0.computeTypeAliasDescriptor(i);
    }
}

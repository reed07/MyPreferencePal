package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedClassDescriptor.kt */
final class DeserializedClassDescriptor$DeserializedClassTypeConstructor$parameters$1 extends Lambda implements Function0<List<? extends TypeParameterDescriptor>> {
    final /* synthetic */ DeserializedClassTypeConstructor this$0;

    DeserializedClassDescriptor$DeserializedClassTypeConstructor$parameters$1(DeserializedClassTypeConstructor deserializedClassTypeConstructor) {
        this.this$0 = deserializedClassTypeConstructor;
        super(0);
    }

    @NotNull
    public final List<TypeParameterDescriptor> invoke() {
        return TypeParameterUtilsKt.computeConstructorTypeParameters(DeserializedClassDescriptor.this);
    }
}

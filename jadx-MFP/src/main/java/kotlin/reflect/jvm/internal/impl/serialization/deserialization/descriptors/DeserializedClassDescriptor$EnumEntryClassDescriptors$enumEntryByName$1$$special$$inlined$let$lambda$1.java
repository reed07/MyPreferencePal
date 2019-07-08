package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedClassDescriptor.kt */
final class DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1$$special$$inlined$let$lambda$1 extends Lambda implements Function0<List<? extends AnnotationDescriptor>> {
    final /* synthetic */ Name $name$inlined;
    final /* synthetic */ EnumEntry $proto;
    final /* synthetic */ DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1 this$0;

    DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1$$special$$inlined$let$lambda$1(EnumEntry enumEntry, DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1 deserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1, Name name) {
        this.$proto = enumEntry;
        this.this$0 = deserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1;
        this.$name$inlined = name;
        super(0);
    }

    @NotNull
    public final List<AnnotationDescriptor> invoke() {
        return CollectionsKt.toList(DeserializedClassDescriptor.this.getC().getComponents().getAnnotationAndConstantLoader().loadEnumEntryAnnotations(DeserializedClassDescriptor.this.getThisAsProtoContainer$deserialization(), this.$proto));
    }
}

package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedClassDescriptor.kt */
final class DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1 extends Lambda implements Function1<Name, EnumEntrySyntheticClassDescriptor> {
    final /* synthetic */ EnumEntryClassDescriptors this$0;

    DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1(EnumEntryClassDescriptors enumEntryClassDescriptors) {
        this.this$0 = enumEntryClassDescriptors;
        super(1);
    }

    @Nullable
    public final EnumEntrySyntheticClassDescriptor invoke(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        EnumEntry enumEntry = (EnumEntry) this.this$0.enumEntryProtos.get(name);
        if (enumEntry == null) {
            return null;
        }
        return EnumEntrySyntheticClassDescriptor.create(DeserializedClassDescriptor.this.getC().getStorageManager(), DeserializedClassDescriptor.this, name, this.this$0.enumMemberNames, new DeserializedAnnotations(DeserializedClassDescriptor.this.getC().getStorageManager(), new DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1$$special$$inlined$let$lambda$1(enumEntry, this, name)), SourceElement.NO_SOURCE);
    }
}

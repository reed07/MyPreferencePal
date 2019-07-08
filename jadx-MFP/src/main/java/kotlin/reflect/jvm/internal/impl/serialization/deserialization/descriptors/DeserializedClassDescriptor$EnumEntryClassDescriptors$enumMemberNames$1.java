package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedClassDescriptor.kt */
final class DeserializedClassDescriptor$EnumEntryClassDescriptors$enumMemberNames$1 extends Lambda implements Function0<Set<? extends Name>> {
    final /* synthetic */ EnumEntryClassDescriptors this$0;

    DeserializedClassDescriptor$EnumEntryClassDescriptors$enumMemberNames$1(EnumEntryClassDescriptors enumEntryClassDescriptors) {
        this.this$0 = enumEntryClassDescriptors;
        super(0);
    }

    @NotNull
    public final Set<Name> invoke() {
        return this.this$0.computeEnumMemberNames();
    }
}

package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedClassDescriptor.kt */
final class DeserializedClassDescriptor$DeserializedClassMemberScope$2$1 extends Lambda implements Function0<List<? extends Name>> {
    final /* synthetic */ List $it;

    DeserializedClassDescriptor$DeserializedClassMemberScope$2$1(List list) {
        this.$it = list;
        super(0);
    }

    @NotNull
    public final List<Name> invoke() {
        return this.$it;
    }
}

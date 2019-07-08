package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedMemberScope.kt */
final class DeserializedMemberScope$classNames$2 extends Lambda implements Function0<Set<? extends Name>> {
    final /* synthetic */ Function0 $classNames;

    DeserializedMemberScope$classNames$2(Function0 function0) {
        this.$classNames = function0;
        super(0);
    }

    @NotNull
    public final Set<Name> invoke() {
        return CollectionsKt.toSet((Iterable) this.$classNames.invoke());
    }
}

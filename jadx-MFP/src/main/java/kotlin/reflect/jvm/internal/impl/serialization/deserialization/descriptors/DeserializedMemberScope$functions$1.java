package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedMemberScope.kt */
final class DeserializedMemberScope$functions$1 extends Lambda implements Function1<Name, Collection<? extends SimpleFunctionDescriptor>> {
    final /* synthetic */ DeserializedMemberScope this$0;

    DeserializedMemberScope$functions$1(DeserializedMemberScope deserializedMemberScope) {
        this.this$0 = deserializedMemberScope;
        super(1);
    }

    @NotNull
    public final Collection<SimpleFunctionDescriptor> invoke(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "it");
        return this.this$0.computeFunctions(name);
    }
}

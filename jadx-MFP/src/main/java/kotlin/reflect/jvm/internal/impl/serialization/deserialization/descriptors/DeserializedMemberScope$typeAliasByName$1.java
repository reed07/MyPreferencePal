package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedMemberScope.kt */
final class DeserializedMemberScope$typeAliasByName$1 extends Lambda implements Function1<Name, TypeAliasDescriptor> {
    final /* synthetic */ DeserializedMemberScope this$0;

    DeserializedMemberScope$typeAliasByName$1(DeserializedMemberScope deserializedMemberScope) {
        this.this$0 = deserializedMemberScope;
        super(1);
    }

    @Nullable
    public final TypeAliasDescriptor invoke(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "it");
        return this.this$0.createTypeAlias(name);
    }
}

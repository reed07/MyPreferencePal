package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeDeserializer.kt */
final class TypeDeserializer$simpleType$1 extends Lambda implements Function1<Type, List<? extends Argument>> {
    final /* synthetic */ TypeDeserializer this$0;

    TypeDeserializer$simpleType$1(TypeDeserializer typeDeserializer) {
        this.this$0 = typeDeserializer;
        super(1);
    }

    @NotNull
    public final List<Argument> invoke(@NotNull Type type) {
        Intrinsics.checkParameterIsNotNull(type, "receiver$0");
        List argumentList = type.getArgumentList();
        Intrinsics.checkExpressionValueIsNotNull(argumentList, "argumentList");
        Collection collection = argumentList;
        Type outerType = ProtoTypeTableUtilKt.outerType(type, this.this$0.c.getTypeTable());
        List invoke = outerType != null ? invoke(outerType) : null;
        if (invoke == null) {
            invoke = CollectionsKt.emptyList();
        }
        return CollectionsKt.plus(collection, (Iterable<? extends T>) invoke);
    }
}

package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeDeserializer.kt */
final class TypeDeserializer$typeConstructor$1$typeParametersCount$2 extends Lambda implements Function1<Type, Integer> {
    public static final TypeDeserializer$typeConstructor$1$typeParametersCount$2 INSTANCE = new TypeDeserializer$typeConstructor$1$typeParametersCount$2();

    TypeDeserializer$typeConstructor$1$typeParametersCount$2() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Integer.valueOf(invoke((Type) obj));
    }

    public final int invoke(@NotNull Type type) {
        Intrinsics.checkParameterIsNotNull(type, "it");
        return type.getArgumentCount();
    }
}

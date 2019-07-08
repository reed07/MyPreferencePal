package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ContractDeserializer.kt */
public final class ContractDeserializer$Companion$DEFAULT$1 implements ContractDeserializer {
    @Nullable
    public Pair deserializeContractFromFunction(@NotNull Function function, @NotNull FunctionDescriptor functionDescriptor, @NotNull TypeTable typeTable, @NotNull TypeDeserializer typeDeserializer) {
        Intrinsics.checkParameterIsNotNull(function, "proto");
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "ownerFunction");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        Intrinsics.checkParameterIsNotNull(typeDeserializer, "typeDeserializer");
        return null;
    }

    ContractDeserializer$Companion$DEFAULT$1() {
    }
}

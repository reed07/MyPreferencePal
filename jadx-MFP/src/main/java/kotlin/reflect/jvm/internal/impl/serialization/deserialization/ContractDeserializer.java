package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.Pair;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ContractDeserializer.kt */
public interface ContractDeserializer {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: ContractDeserializer.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        private static final ContractDeserializer DEFAULT = new ContractDeserializer$Companion$DEFAULT$1();

        private Companion() {
        }

        @NotNull
        public final ContractDeserializer getDEFAULT() {
            return DEFAULT;
        }
    }

    @Nullable
    Pair<UserDataKey<?>, Object> deserializeContractFromFunction(@NotNull Function function, @NotNull FunctionDescriptor functionDescriptor, @NotNull TypeTable typeTable, @NotNull TypeDeserializer typeDeserializer);
}

package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TransientReceiver extends AbstractReceiverValue {
    public TransientReceiver(@NotNull KotlinType kotlinType) {
        this(kotlinType, null);
    }

    private TransientReceiver(@NotNull KotlinType kotlinType, @Nullable ReceiverValue receiverValue) {
        super(kotlinType, receiverValue);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{Transient} : ");
        sb.append(getType());
        return sb.toString();
    }
}

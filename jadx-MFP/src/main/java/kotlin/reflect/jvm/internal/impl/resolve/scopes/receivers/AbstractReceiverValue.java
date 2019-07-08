package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractReceiverValue implements ReceiverValue {
    private final ReceiverValue original;
    protected final KotlinType receiverType;

    public AbstractReceiverValue(@NotNull KotlinType kotlinType, @Nullable ReceiverValue receiverValue) {
        this.receiverType = kotlinType;
        if (receiverValue == 0) {
            receiverValue = this;
        }
        this.original = receiverValue;
    }

    @NotNull
    public KotlinType getType() {
        return this.receiverType;
    }
}

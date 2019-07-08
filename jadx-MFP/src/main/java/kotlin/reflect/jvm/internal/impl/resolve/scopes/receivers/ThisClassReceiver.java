package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: ImplicitClassReceiver.kt */
public interface ThisClassReceiver extends ReceiverValue {
    @NotNull
    ClassDescriptor getClassDescriptor();
}

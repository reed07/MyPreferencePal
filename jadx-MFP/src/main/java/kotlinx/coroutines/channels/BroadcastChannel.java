package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannel;", "E", "Lkotlinx/coroutines/channels/SendChannel;", "cancel", "", "cause", "", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
@ExperimentalCoroutinesApi
/* compiled from: BroadcastChannel.kt */
public interface BroadcastChannel<E> extends SendChannel<E> {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: BroadcastChannel.kt */
    public static final class DefaultImpls {
    }

    @NotNull
    ReceiveChannel<E> openSubscription();
}

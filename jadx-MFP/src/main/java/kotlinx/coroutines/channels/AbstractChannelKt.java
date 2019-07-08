package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003\"\u0016\u0010\u0006\u001a\u00020\u00018\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0003\"\u0016\u0010\b\u001a\u00020\u00018\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0003\"\u0016\u0010\n\u001a\u00020\u00018\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0003\"\u0016\u0010\f\u001a\u00020\u00018\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0003\"\u0016\u0010\u000e\u001a\u00020\u00018\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0003\"\u0016\u0010\u0010\u001a\u00020\u00018\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0003\"\u0016\u0010\u0012\u001a\u00020\u00018\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0003*(\b\u0000\u0010\u0014\"\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0004\u0012\u00020\u00170\u00152\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0004\u0012\u00020\u00170\u0015¨\u0006\u0018"}, d2 = {"CLOSE_RESUMED", "", "CLOSE_RESUMED$annotations", "()V", "ENQUEUE_FAILED", "ENQUEUE_FAILED$annotations", "HANDLER_INVOKED", "HANDLER_INVOKED$annotations", "NULL_VALUE", "NULL_VALUE$annotations", "OFFER_FAILED", "OFFER_FAILED$annotations", "OFFER_SUCCESS", "OFFER_SUCCESS$annotations", "POLL_FAILED", "POLL_FAILED$annotations", "SELECT_STARTED", "SELECT_STARTED$annotations", "SEND_RESUMED", "SEND_RESUMED$annotations", "Handler", "Lkotlin/Function1;", "", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: AbstractChannel.kt */
public final class AbstractChannelKt {
    @NotNull
    @JvmField
    public static final Object CLOSE_RESUMED = new Symbol("CLOSE_RESUMED");
    @NotNull
    @JvmField
    public static final Object ENQUEUE_FAILED = new Symbol("ENQUEUE_FAILED");
    @NotNull
    @JvmField
    public static final Object HANDLER_INVOKED = new Symbol("ON_CLOSE_HANDLER_INVOKED");
    @NotNull
    @JvmField
    public static final Object NULL_VALUE = new Symbol("NULL_VALUE");
    @NotNull
    @JvmField
    public static final Object OFFER_FAILED = new Symbol("OFFER_FAILED");
    @NotNull
    @JvmField
    public static final Object OFFER_SUCCESS = new Symbol("OFFER_SUCCESS");
    @NotNull
    @JvmField
    public static final Object POLL_FAILED = new Symbol("POLL_FAILED");
    @NotNull
    @JvmField
    public static final Object SELECT_STARTED = new Symbol("SELECT_STARTED");
    @NotNull
    @JvmField
    public static final Object SEND_RESUMED = new Symbol("SEND_RESUMED");
}

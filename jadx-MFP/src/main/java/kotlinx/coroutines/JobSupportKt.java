package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u0004\u0018\u00010\u0012H\u0002\u001a\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0012*\u0004\u0018\u00010\u0012H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0016\u0010\u0005\u001a\u00020\u00068\u0002X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\b\"\u0016\u0010\t\u001a\u00020\u00068\u0002X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\b\"\u000e\u0010\u000b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0016\u0010\r\u001a\u00020\u000e8\u0002X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\b\"\u000e\u0010\u0010\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"COMPLETING_ALREADY_COMPLETING", "", "COMPLETING_COMPLETED", "COMPLETING_RETRY", "COMPLETING_WAITING_CHILDREN", "EMPTY_ACTIVE", "Lkotlinx/coroutines/Empty;", "EMPTY_ACTIVE$annotations", "()V", "EMPTY_NEW", "EMPTY_NEW$annotations", "FALSE", "RETRY", "SEALED", "Lkotlinx/coroutines/internal/Symbol;", "SEALED$annotations", "TRUE", "boxIncomplete", "", "unboxState", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: JobSupport.kt */
public final class JobSupportKt {
    /* access modifiers changed from: private */
    public static final Empty EMPTY_ACTIVE = new Empty(true);
    /* access modifiers changed from: private */
    public static final Empty EMPTY_NEW = new Empty(false);
    /* access modifiers changed from: private */
    public static final Symbol SEALED = new Symbol("SEALED");

    /* access modifiers changed from: private */
    public static final Object boxIncomplete(@Nullable Object obj) {
        return obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj;
    }

    @Nullable
    public static final Object unboxState(@Nullable Object obj) {
        IncompleteStateBox incompleteStateBox = (IncompleteStateBox) (!(obj instanceof IncompleteStateBox) ? null : obj);
        if (incompleteStateBox == null) {
            return obj;
        }
        Incomplete incomplete = incompleteStateBox.state;
        return incomplete != null ? incomplete : obj;
    }
}

package androidx.core.util;

import android.util.Range;
import kotlin.Metadata;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.ClosedRange.DefaultImpls;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\n \u0004*\u0004\u0018\u00018\u00008\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00018\u00008\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"androidx/core/util/RangeKt$toClosedRange$1", "Lkotlin/ranges/ClosedRange;", "(Landroid/util/Range;)V", "endInclusive", "kotlin.jvm.PlatformType", "getEndInclusive", "()Ljava/lang/Comparable;", "start", "getStart", "core-ktx_release"}, k = 1, mv = {1, 1, 9})
/* compiled from: Range.kt */
public final class RangeKt$toClosedRange$1 implements ClosedRange<T> {
    final /* synthetic */ Range receiver$0;

    public boolean contains(@NotNull T t) {
        return DefaultImpls.contains(this, t);
    }

    public boolean isEmpty() {
        return DefaultImpls.isEmpty(this);
    }

    public T getEndInclusive() {
        return this.receiver$0.getUpper();
    }

    public T getStart() {
        return this.receiver$0.getLower();
    }
}

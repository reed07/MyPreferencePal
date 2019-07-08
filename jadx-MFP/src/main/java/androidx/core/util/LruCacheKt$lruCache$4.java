package androidx.core.util;

import android.util.LruCache;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0004\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0005J/\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u00012\b\u0010\u000b\u001a\u0004\u0018\u00018\u0001H\u0014¢\u0006\u0002\u0010\fJ\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0001H\u0014¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"androidx/core/util/LruCacheKt$lruCache$4", "Landroid/util/LruCache;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;II)V", "create", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "entryRemoved", "", "evicted", "", "oldValue", "newValue", "(ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "sizeOf", "", "value", "(Ljava/lang/Object;Ljava/lang/Object;)I", "core-ktx_release"}, k = 1, mv = {1, 1, 9})
/* compiled from: LruCache.kt */
public final class LruCacheKt$lruCache$4 extends LruCache<K, V> {
    final /* synthetic */ Function1 $create;
    final /* synthetic */ Function4 $onEntryRemoved;
    final /* synthetic */ Function2 $sizeOf;

    /* access modifiers changed from: protected */
    public int sizeOf(@NotNull K k, @NotNull V v) {
        return ((Number) this.$sizeOf.invoke(k, v)).intValue();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public V create(@NotNull K k) {
        return this.$create.invoke(k);
    }

    /* access modifiers changed from: protected */
    public void entryRemoved(boolean z, @NotNull K k, @NotNull V v, @Nullable V v2) {
        this.$onEntryRemoved.invoke(Boolean.valueOf(z), k, v, v2);
    }
}

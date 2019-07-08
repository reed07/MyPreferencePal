package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010)\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"androidx/core/view/ViewGroupKt$children$1", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "(Landroid/view/ViewGroup;)V", "iterator", "", "core-ktx_release"}, k = 1, mv = {1, 1, 9})
/* compiled from: ViewGroup.kt */
public final class ViewGroupKt$children$1 implements Sequence<View> {
    final /* synthetic */ ViewGroup receiver$0;

    @NotNull
    public Iterator<View> iterator() {
        return ViewGroupKt.iterator(this.receiver$0);
    }
}

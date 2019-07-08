package androidx.core.view;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"androidx/core/view/ViewKt$doOnPreDraw$1", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;Landroid/view/ViewTreeObserver;)V", "onPreDraw", "", "core-ktx_release"}, k = 1, mv = {1, 1, 9})
/* compiled from: View.kt */
public final class ViewKt$doOnPreDraw$1 implements OnPreDrawListener {
    final /* synthetic */ Function1 $action;
    final /* synthetic */ ViewTreeObserver $vto;
    final /* synthetic */ View receiver$0;

    public boolean onPreDraw() {
        this.$action.invoke(this.receiver$0);
        ViewTreeObserver viewTreeObserver = this.$vto;
        Intrinsics.checkExpressionValueIsNotNull(viewTreeObserver, "vto");
        if (viewTreeObserver.isAlive()) {
            this.$vto.removeOnPreDrawListener(this);
        } else {
            this.receiver$0.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        return true;
    }
}

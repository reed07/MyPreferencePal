package androidx.core.transition;

import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u000b"}, d2 = {"androidx/core/transition/TransitionKt$addListener$1", "Landroid/transition/Transition$TransitionListener;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onTransitionCancel", "", "transition", "Landroid/transition/Transition;", "onTransitionEnd", "onTransitionPause", "onTransitionResume", "onTransitionStart", "core-ktx_release"}, k = 1, mv = {1, 1, 9})
/* compiled from: Transition.kt */
public final class TransitionKt$addListener$1 implements TransitionListener {
    final /* synthetic */ Function1 $onCancel;
    final /* synthetic */ Function1 $onEnd;
    final /* synthetic */ Function1 $onPause;
    final /* synthetic */ Function1 $onResume;
    final /* synthetic */ Function1 $onStart;

    public void onTransitionEnd(@NotNull Transition transition) {
        Function1 function1 = this.$onEnd;
        if (function1 != null) {
            Unit unit = (Unit) function1.invoke(transition);
        }
    }

    public void onTransitionResume(@NotNull Transition transition) {
        Function1 function1 = this.$onResume;
        if (function1 != null) {
            Unit unit = (Unit) function1.invoke(transition);
        }
    }

    public void onTransitionPause(@NotNull Transition transition) {
        Function1 function1 = this.$onPause;
        if (function1 != null) {
            Unit unit = (Unit) function1.invoke(transition);
        }
    }

    public void onTransitionCancel(@NotNull Transition transition) {
        Function1 function1 = this.$onCancel;
        if (function1 != null) {
            Unit unit = (Unit) function1.invoke(transition);
        }
    }

    public void onTransitionStart(@NotNull Transition transition) {
        Function1 function1 = this.$onStart;
        if (function1 != null) {
            Unit unit = (Unit) function1.invoke(transition);
        }
    }
}

package com.myfitnesspal.shared.ui.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.Behavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J@\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0011H\u0016JH\u0010\u0016\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J*\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u0011H\u0002¨\u0006\u001c"}, d2 = {"Lcom/myfitnesspal/shared/ui/behavior/FixAppBarLayoutBehavior;", "Landroid/support/design/widget/AppBarLayout$Behavior;", "()V", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "onNestedPreScroll", "", "coordinatorLayout", "Landroid/support/design/widget/CoordinatorLayout;", "child", "Landroid/support/design/widget/AppBarLayout;", "target", "Landroid/view/View;", "dx", "", "dy", "consumed", "", "type", "onNestedScroll", "dxConsumed", "dyConsumed", "dxUnconsumed", "dyUnconsumed", "stopNestedScrollIfNeeded", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FixAppBarLayoutBehavior.kt */
public final class FixAppBarLayoutBehavior extends Behavior {
    public FixAppBarLayoutBehavior() {
    }

    public FixAppBarLayoutBehavior(@NotNull Context context, @NotNull AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        super(context, attributeSet);
    }

    public void onNestedScroll(@NotNull CoordinatorLayout coordinatorLayout, @NotNull AppBarLayout appBarLayout, @NotNull View view, int i, int i2, int i3, int i4, int i5) {
        Intrinsics.checkParameterIsNotNull(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkParameterIsNotNull(appBarLayout, "child");
        Intrinsics.checkParameterIsNotNull(view, "target");
        super.onNestedScroll(coordinatorLayout, appBarLayout, view, i, i2, i3, i4, i5);
        stopNestedScrollIfNeeded(i4, appBarLayout, view, i5);
    }

    public void onNestedPreScroll(@NotNull CoordinatorLayout coordinatorLayout, @NotNull AppBarLayout appBarLayout, @NotNull View view, int i, int i2, @NotNull int[] iArr, int i3) {
        Intrinsics.checkParameterIsNotNull(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkParameterIsNotNull(appBarLayout, "child");
        Intrinsics.checkParameterIsNotNull(view, "target");
        Intrinsics.checkParameterIsNotNull(iArr, "consumed");
        super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i, i2, iArr, i3);
        stopNestedScrollIfNeeded(i2, appBarLayout, view, i3);
    }

    private final void stopNestedScrollIfNeeded(int i, AppBarLayout appBarLayout, View view, int i2) {
        if (i2 == 1) {
            int topAndBottomOffset = getTopAndBottomOffset();
            if ((i < 0 && topAndBottomOffset == 0) || (i > 0 && topAndBottomOffset == (-appBarLayout.getTotalScrollRange()))) {
                if (view == null) {
                    Intrinsics.throwNpe();
                }
                ViewCompat.stopNestedScroll(view, 1);
            }
        }
    }
}

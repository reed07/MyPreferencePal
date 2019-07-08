package com.myfitnesspal.shared.ui.behavior;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onScrollChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: NestedScrollFABBehavior.kt */
final class NestedScrollFABBehavior$onDependentViewChanged$1 implements OnScrollChangedListener {
    final /* synthetic */ FloatingActionButton $child;
    final /* synthetic */ NestedScrollView $nestedScrollView;

    NestedScrollFABBehavior$onDependentViewChanged$1(NestedScrollView nestedScrollView, FloatingActionButton floatingActionButton) {
        this.$nestedScrollView = nestedScrollView;
        this.$child = floatingActionButton;
    }

    public final void onScrollChanged() {
        NestedScrollView nestedScrollView = this.$nestedScrollView;
        View childAt = nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
        Intrinsics.checkExpressionValueIsNotNull(childAt, "lastItem");
        if (childAt.getBottom() - (this.$nestedScrollView.getHeight() + this.$nestedScrollView.getScrollY()) == 0) {
            FloatingActionButton floatingActionButton = this.$child;
            if (floatingActionButton != null) {
                floatingActionButton.hide();
                return;
            }
            return;
        }
        FloatingActionButton floatingActionButton2 = this.$child;
        if (floatingActionButton2 != null) {
            floatingActionButton2.show();
        }
    }
}

package com.myfitnesspal.shared.ui.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.FloatingActionButton.Behavior;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J \u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0011"}, d2 = {"Lcom/myfitnesspal/shared/ui/behavior/NestedScrollFABBehavior;", "Landroid/support/design/widget/FloatingActionButton$Behavior;", "()V", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "layoutDependsOn", "", "parent", "Landroid/support/design/widget/CoordinatorLayout;", "child", "Landroid/support/design/widget/FloatingActionButton;", "dependency", "Landroid/view/View;", "onDependentViewChanged", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: NestedScrollFABBehavior.kt */
public final class NestedScrollFABBehavior extends Behavior {
    public NestedScrollFABBehavior() {
    }

    public NestedScrollFABBehavior(@NotNull Context context, @NotNull AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        super(context, attributeSet);
    }

    public boolean layoutDependsOn(@NotNull CoordinatorLayout coordinatorLayout, @NotNull FloatingActionButton floatingActionButton, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(coordinatorLayout, "parent");
        Intrinsics.checkParameterIsNotNull(floatingActionButton, "child");
        Intrinsics.checkParameterIsNotNull(view, "dependency");
        return view instanceof NestedScrollView;
    }

    public boolean onDependentViewChanged(@NotNull CoordinatorLayout coordinatorLayout, @NotNull FloatingActionButton floatingActionButton, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(coordinatorLayout, "parent");
        Intrinsics.checkParameterIsNotNull(floatingActionButton, "child");
        Intrinsics.checkParameterIsNotNull(view, "dependency");
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new NestedScrollFABBehavior$onDependentViewChanged$1(nestedScrollView, floatingActionButton));
        return false;
    }
}

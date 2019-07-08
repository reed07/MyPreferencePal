package com.myfitnesspal.feature.premium.util;

import android.support.annotation.IdRes;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0010\u001a\u00020\u000fH\u0002J+\u0010\u0011\u001a\u00020\u000f2#\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\nR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R-\u0010\t\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/myfitnesspal/feature/premium/util/MenuItemLookup;", "", "menuItemId", "", "root", "Landroid/view/ViewGroup;", "(ILandroid/view/ViewGroup;)V", "menuItemView", "Landroid/support/v7/view/menu/ActionMenuItemView;", "observer", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "", "notifyObserver", "observe", "callback", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumCrownUtil.kt */
public final class MenuItemLookup {
    /* access modifiers changed from: private */
    public ActionMenuItemView menuItemView;
    private Function1<? super View, Unit> observer;

    public MenuItemLookup(@IdRes final int i, @NotNull ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "root");
        viewGroup.setOnHierarchyChangeListener(new OnHierarchyChangeListener(this) {
            final /* synthetic */ MenuItemLookup this$0;

            public void onChildViewRemoved(@Nullable View view, @Nullable View view2) {
            }

            {
                this.this$0 = r1;
            }

            public void onChildViewAdded(@Nullable View view, @Nullable View view2) {
                if (view2 instanceof ActionMenuView) {
                    ((ActionMenuView) view2).setOnHierarchyChangeListener(new MenuItemLookup$1$onChildViewAdded$1(this));
                }
            }
        });
    }

    public final void observe(@NotNull Function1<? super View, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        this.observer = function1;
        ActionMenuItemView actionMenuItemView = this.menuItemView;
        if (actionMenuItemView != null) {
            actionMenuItemView.post(new MenuItemLookup$observe$1(this));
        }
    }

    /* access modifiers changed from: private */
    public final void notifyObserver() {
        ActionMenuItemView actionMenuItemView = this.menuItemView;
        if (actionMenuItemView != null) {
            Function1<? super View, Unit> function1 = this.observer;
            if (function1 != null) {
                Unit unit = (Unit) function1.invoke(actionMenuItemView);
            }
        }
    }
}

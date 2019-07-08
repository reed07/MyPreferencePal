package com.myfitnesspal.feature.premium.util;

import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.view.ViewGroup.OnHierarchyChangeListener;
import com.myfitnesspal.feature.premium.util.MenuItemLookup.AnonymousClass1;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, d2 = {"com/myfitnesspal/feature/premium/util/MenuItemLookup$1$onChildViewAdded$1", "Landroid/view/ViewGroup$OnHierarchyChangeListener;", "onChildViewAdded", "", "parent", "Landroid/view/View;", "menuChild", "onChildViewRemoved", "child", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumCrownUtil.kt */
public final class MenuItemLookup$1$onChildViewAdded$1 implements OnHierarchyChangeListener {
    final /* synthetic */ AnonymousClass1 this$0;

    public void onChildViewRemoved(@Nullable View view, @Nullable View view2) {
    }

    MenuItemLookup$1$onChildViewAdded$1(AnonymousClass1 r1) {
        this.this$0 = r1;
    }

    public void onChildViewAdded(@Nullable View view, @Nullable View view2) {
        if (view2 instanceof ActionMenuItemView) {
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) view2;
            if (r2 == actionMenuItemView.getId()) {
                this.this$0.this$0.menuItemView = actionMenuItemView;
                this.this$0.this$0.notifyObserver();
            }
        }
    }
}

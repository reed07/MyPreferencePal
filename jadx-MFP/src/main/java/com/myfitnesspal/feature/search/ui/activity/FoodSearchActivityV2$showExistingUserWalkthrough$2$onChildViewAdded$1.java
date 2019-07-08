package com.myfitnesspal.feature.search.ui.activity;

import android.support.v7.widget.ActionMenuView;
import android.view.View;
import android.view.ViewGroup.OnHierarchyChangeListener;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\b"}, d2 = {"com/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$showExistingUserWalkthrough$2$onChildViewAdded$1", "Landroid/view/ViewGroup$OnHierarchyChangeListener;", "onChildViewAdded", "", "menuParent", "Landroid/view/View;", "menuChild", "onChildViewRemoved", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
public final class FoodSearchActivityV2$showExistingUserWalkthrough$2$onChildViewAdded$1 implements OnHierarchyChangeListener {
    final /* synthetic */ FoodSearchActivityV2$showExistingUserWalkthrough$2 this$0;

    public void onChildViewRemoved(@Nullable View view, @Nullable View view2) {
    }

    FoodSearchActivityV2$showExistingUserWalkthrough$2$onChildViewAdded$1(FoodSearchActivityV2$showExistingUserWalkthrough$2 foodSearchActivityV2$showExistingUserWalkthrough$2) {
        this.this$0 = foodSearchActivityV2$showExistingUserWalkthrough$2;
    }

    public void onChildViewAdded(@Nullable View view, @Nullable View view2) {
        if (!(view instanceof ActionMenuView)) {
            view = null;
        }
        ActionMenuView actionMenuView = (ActionMenuView) view;
        if (actionMenuView != null && actionMenuView.getChildCount() - 1 == 1 && view2 != null) {
            this.this$0.$createHighlightSequence$1.invoke(view2).start();
            FoodSearchActivityV2.access$getViewModel$p(this.this$0.this$0).setShouldShowWalkthrough(false);
        }
    }
}

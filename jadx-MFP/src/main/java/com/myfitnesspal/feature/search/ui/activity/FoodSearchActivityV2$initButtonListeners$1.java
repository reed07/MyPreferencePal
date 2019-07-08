package com.myfitnesspal.feature.search.ui.activity;

import android.content.Context;
import android.support.v7.view.ActionMode;
import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivityV2.Extras;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import java.util.Date;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
final class FoodSearchActivityV2$initButtonListeners$1 implements OnClickListener {
    final /* synthetic */ FoodSearchActivityV2 this$0;

    FoodSearchActivityV2$initButtonListeners$1(FoodSearchActivityV2 foodSearchActivityV2) {
        this.this$0 = foodSearchActivityV2;
    }

    public final void onClick(View view) {
        ActionMode access$getMultiAddActionMode$p = this.this$0.multiAddActionMode;
        if (access$getMultiAddActionMode$p != null) {
            access$getMultiAddActionMode$p.finish();
        }
        NavigationHelper navigationHelper = this.this$0.getNavigationHelper();
        FoodSearchActivityV2 foodSearchActivityV2 = this.this$0;
        Context context = foodSearchActivityV2;
        String mealName = FoodSearchActivityV2.access$getViewModel$p(foodSearchActivityV2).getMealName();
        Date currentActiveDate = FoodSearchActivityV2.access$getViewModel$p(this.this$0).getCurrentActiveDate();
        Extras extras = FoodSearchActivityV2.access$getViewModel$p(this.this$0).getExtras();
        navigationHelper.withIntent(VenuesActivity.newStartIntent(context, mealName, currentActiveDate, extras != null ? extras.isInMealFoodCreationFlow() : false)).startActivity(1001);
    }
}

package com.myfitnesspal.feature.foodfeedback.ui.activity;

import android.view.MenuItem;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackViewModel;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/MenuItem;", "kotlin.jvm.PlatformType", "onMenuItemClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackActivity.kt */
final class FoodFeedbackActivity$setupServingSize$1 implements OnMenuItemClickListener {
    final /* synthetic */ MfpFood $mfpFood;
    final /* synthetic */ FoodFeedbackActivity this$0;

    FoodFeedbackActivity$setupServingSize$1(FoodFeedbackActivity foodFeedbackActivity, MfpFood mfpFood) {
        this.this$0 = foodFeedbackActivity;
        this.$mfpFood = mfpFood;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        FoodFeedbackViewModel viewModel = this.this$0.getViewModel();
        Intrinsics.checkExpressionValueIsNotNull(menuItem, "it");
        viewModel.setSelectedServingId(menuItem.getItemId());
        MfpServingSize mfpServingSize = (MfpServingSize) this.$mfpFood.getServingSizes().get(menuItem.getItemId());
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.serving_size_values);
        Intrinsics.checkExpressionValueIsNotNull(textView, "serving_size_values");
        textView.setText(mfpServingSize.descriptionWithAmount());
        FoodFeedbackActivity foodFeedbackActivity = this.this$0;
        MfpNutritionalContents nutritionalContents = this.$mfpFood.getNutritionalContents();
        Intrinsics.checkExpressionValueIsNotNull(nutritionalContents, "mfpFood.nutritionalContents");
        Intrinsics.checkExpressionValueIsNotNull(mfpServingSize, "selectedFoodPortion");
        foodFeedbackActivity.setupNutritionValues(nutritionalContents, (float) mfpServingSize.getNutritionMultiplier().doubleValue());
        return true;
    }
}

package com.myfitnesspal.feature.foodfeedback.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import com.myfitnesspal.shared.model.v1.Food;
import com.uacf.core.util.Ln;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "newFood", "Lcom/myfitnesspal/shared/model/v1/Food;", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackActivity.kt */
final class FoodFeedbackActivity$saveToMyFoods$1<T> implements Observer<Food> {
    final /* synthetic */ FoodFeedbackActivity this$0;

    FoodFeedbackActivity$saveToMyFoods$1(FoodFeedbackActivity foodFeedbackActivity) {
        this.this$0 = foodFeedbackActivity;
    }

    public final void onChanged(@Nullable Food food) {
        this.this$0.hideProgressDialog();
        if (food != null) {
            this.this$0.scheduleSync();
            FoodFeedbackActivity foodFeedbackActivity = this.this$0;
            Intent intent = new Intent();
            intent.putExtra("extra_new_food", food);
            foodFeedbackActivity.setResult(-1, intent);
            this.this$0.finish();
            return;
        }
        Ln.e("FoodFeedback: Something went wrong when creating private food", new Object[0]);
    }
}

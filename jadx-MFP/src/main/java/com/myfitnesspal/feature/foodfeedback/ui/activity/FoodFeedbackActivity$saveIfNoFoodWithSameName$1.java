package com.myfitnesspal.feature.foodfeedback.ui.activity;

import android.arch.lifecycle.Observer;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelperImpl;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.uacf.core.util.Ln;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "onChanged", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackActivity.kt */
final class FoodFeedbackActivity$saveIfNoFoodWithSameName$1<T> implements Observer<Boolean> {
    final /* synthetic */ FoodFeedbackActivity this$0;

    FoodFeedbackActivity$saveIfNoFoodWithSameName$1(FoodFeedbackActivity foodFeedbackActivity) {
        this.this$0 = foodFeedbackActivity;
    }

    public final void onChanged(@Nullable Boolean bool) {
        Unit unit;
        this.this$0.hideProgressDialog();
        if (bool != null) {
            Intrinsics.checkExpressionValueIsNotNull(bool, "it");
            if (bool.booleanValue()) {
                this.this$0.showSnackBar(R.string.nameExistsMsg);
                MfpFood mfpFood = this.this$0.getViewModel().getMfpFood();
                if (mfpFood != null) {
                    FoodFeedbackAnalyticsHelper foodFeedbackAnalyticsHelper = (FoodFeedbackAnalyticsHelper) this.this$0.getFoodFeedbackAnalyticsHelper().get();
                    String id = mfpFood.getId();
                    Intrinsics.checkExpressionValueIsNotNull(id, "it.id");
                    String version = mfpFood.getVersion();
                    Intrinsics.checkExpressionValueIsNotNull(version, "it.version");
                    foodFeedbackAnalyticsHelper.reportFoodCorrectionFailed(id, version, FoodFeedbackAnalyticsHelperImpl.ERROR_FOOD_SAME_NAME);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
            } else {
                this.this$0.saveToMyFoods();
                unit = Unit.INSTANCE;
            }
            if (unit != null) {
                return;
            }
        }
        Integer.valueOf(Ln.e("FoodFeedback: Something went wrong when checking if a private food with the same name exists", new Object[0]));
    }
}

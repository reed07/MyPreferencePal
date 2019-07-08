package com.myfitnesspal.feature.foodfeedback.mixin;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.foodfeedback.ui.activity.FoodFeedbackActivity;
import com.myfitnesspal.feature.foodfeedback.ui.dialog.FeedbackOptionsDialog;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/myfitnesspal/feature/foodfeedback/mixin/FoodFeedbackOptionsMixin$showReportFood$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackOptionsMixin.kt */
final class FoodFeedbackOptionsMixin$showReportFood$$inlined$let$lambda$1 implements OnClickListener {
    final /* synthetic */ MfpFood $food;
    final /* synthetic */ boolean $shouldShowFoodFeedback$inlined;
    final /* synthetic */ FoodFeedbackOptionsMixin this$0;

    FoodFeedbackOptionsMixin$showReportFood$$inlined$let$lambda$1(MfpFood mfpFood, FoodFeedbackOptionsMixin foodFeedbackOptionsMixin, boolean z) {
        this.$food = mfpFood;
        this.this$0 = foodFeedbackOptionsMixin;
        this.$shouldShowFoodFeedback$inlined = z;
    }

    public final void onClick(View view) {
        FoodFeedbackAnalyticsHelper foodFeedbackAnalyticsHelper = (FoodFeedbackAnalyticsHelper) this.this$0.getFoodFeedbackAnalyticsHelper().get();
        String id = this.$food.getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "food.id");
        String version = this.$food.getVersion();
        Intrinsics.checkExpressionValueIsNotNull(version, "food.version");
        foodFeedbackAnalyticsHelper.reportFeedbackButtonTapped(id, version);
        if (ConnectivityUtil.isOnline()) {
            FeedbackOptionsDialog newInstance = FeedbackOptionsDialog.Companion.newInstance();
            newInstance.show(this.this$0.activity.getSupportFragmentManager(), FeedbackOptionsDialog.TAG);
            newInstance.setOnFeedbackSubmitClick(new Function1<ArrayList<String>, Unit>(this) {
                final /* synthetic */ FoodFeedbackOptionsMixin$showReportFood$$inlined$let$lambda$1 this$0;

                {
                    this.this$0 = r1;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((ArrayList) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull ArrayList<String> arrayList) {
                    Intrinsics.checkParameterIsNotNull(arrayList, "selectedFeedback");
                    FoodFeedbackAnalyticsHelper foodFeedbackAnalyticsHelper = (FoodFeedbackAnalyticsHelper) this.this$0.this$0.getFoodFeedbackAnalyticsHelper().get();
                    String id = this.this$0.$food.getId();
                    Intrinsics.checkExpressionValueIsNotNull(id, "food.id");
                    String version = this.this$0.$food.getVersion();
                    Intrinsics.checkExpressionValueIsNotNull(version, "food.version");
                    foodFeedbackAnalyticsHelper.reportFeedbackReasonsTapped(id, version, arrayList);
                    this.this$0.this$0.activity.getNavigationHelper().withIntent(FoodFeedbackActivity.Companion.newStartIntent(this.this$0.this$0.activity, this.this$0.$food, arrayList)).startActivity(RequestCodes.SUBMIT_CORRECTION);
                }
            });
            return;
        }
        new SnackbarBuilder(this.this$0.rootView).setMessage((int) R.string.feedback_offline).setDuration(0).show();
    }
}

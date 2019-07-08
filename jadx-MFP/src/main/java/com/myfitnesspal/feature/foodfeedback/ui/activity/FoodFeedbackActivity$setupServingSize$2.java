package com.myfitnesspal.feature.foodfeedback.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackActivity.kt */
final class FoodFeedbackActivity$setupServingSize$2 implements OnClickListener {
    final /* synthetic */ MfpFood $mfpFood;
    final /* synthetic */ FoodFeedbackActivity this$0;

    FoodFeedbackActivity$setupServingSize$2(FoodFeedbackActivity foodFeedbackActivity, MfpFood mfpFood) {
        this.this$0 = foodFeedbackActivity;
        this.$mfpFood = mfpFood;
    }

    public final void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this.this$0, view);
        int size = this.$mfpFood.getServingSizes().size();
        for (int i = 0; i < size; i++) {
            popupMenu.getMenu().add(0, i, i, ((MfpServingSize) this.$mfpFood.getServingSizes().get(i)).descriptionWithAmount());
        }
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(this.this$0.servingSizeMenuClickListener);
    }
}

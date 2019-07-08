package com.myfitnesspal.feature.search.ui.adapter;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.myfitnesspal.shared.model.v2.MfpFood;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchAdapter.kt */
final class OnlineFoodSearchAdapter$FoodViewHolder$setData$1 implements OnCheckedChangeListener {
    final /* synthetic */ MfpFood $food;
    final /* synthetic */ FoodViewHolder this$0;

    OnlineFoodSearchAdapter$FoodViewHolder$setData$1(FoodViewHolder foodViewHolder, MfpFood mfpFood) {
        this.this$0 = foodViewHolder;
        this.$food = mfpFood;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        OnlineFoodSearchAdapter.this.getOnCheckedChange().invoke(this.$food, Integer.valueOf(this.this$0.getAdapterPosition()), Boolean.valueOf(z));
    }
}

package com.myfitnesspal.feature.search.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.search.model.SponsoredFood;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchAdapter.kt */
final class OnlineFoodSearchAdapter$SponsoredFoodViewHolder$setData$2 implements OnClickListener {
    final /* synthetic */ int $position;
    final /* synthetic */ SponsoredFood $sponsoredFood;
    final /* synthetic */ SponsoredFoodViewHolder this$0;

    OnlineFoodSearchAdapter$SponsoredFoodViewHolder$setData$2(SponsoredFoodViewHolder sponsoredFoodViewHolder, SponsoredFood sponsoredFood, int i) {
        this.this$0 = sponsoredFoodViewHolder;
        this.$sponsoredFood = sponsoredFood;
        this.$position = i;
    }

    public final void onClick(View view) {
        OnlineFoodSearchAdapter.this.getOnSponsoredFoodClick().invoke(this.$sponsoredFood, Integer.valueOf(this.$position));
    }
}

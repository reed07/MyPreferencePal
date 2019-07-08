package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.model.SponsoredFoodBannerAd;
import com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: SponsoredFoodMixin.kt */
final class SponsoredFoodMixin$bindAdToView$1 implements OnClickListener {
    final /* synthetic */ SponsoredFoodBannerAd $ad;
    final /* synthetic */ String $foodId;
    final /* synthetic */ String $foodVersion;
    final /* synthetic */ SponsoredFoodMixin this$0;

    SponsoredFoodMixin$bindAdToView$1(SponsoredFoodMixin sponsoredFoodMixin, String str, String str2, SponsoredFoodBannerAd sponsoredFoodBannerAd) {
        this.this$0 = sponsoredFoodMixin;
        this.$foodId = str;
        this.$foodVersion = str2;
        this.$ad = sponsoredFoodBannerAd;
    }

    public final void onClick(View view) {
        this.this$0.viewModel.reportBannerClicked();
        FoodEditorAnalytics analytics = this.this$0.getAnalytics();
        FoodEditorExtras foodEditorExtras = this.this$0.foodEditorExtras;
        Intrinsics.checkExpressionValueIsNotNull(foodEditorExtras, "foodEditorExtras");
        analytics.reportSponsoredFoodBannerClicked(foodEditorExtras.getFlowId(), this.$foodId, this.$foodVersion, SearchSource.ONLINE.getTitle());
        Activity access$getActivity = this.this$0.getActivity();
        Context access$getActivity2 = this.this$0.getActivity();
        SponsoredFoodSearchAd access$getSponsoredInfo$p = this.this$0.sponsoredInfo;
        access$getActivity.startActivity(FullScreenWebView.newStartIntentForSponsoredFood(access$getActivity2, access$getSponsoredInfo$p != null ? access$getSponsoredInfo$p.getProductName() : null, this.$ad.getClickUrl()));
    }
}

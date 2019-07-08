package com.myfitnesspal.feature.foodeditor.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import com.google.android.gms.ads.AdListener;
import com.myfitnesspal.feature.registration.model.Resource.Error;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/myfitnesspal/feature/foodeditor/viewmodel/SponsoredFoodEditorViewModel$fetchSponsoredBannerAd$3", "Lcom/google/android/gms/ads/AdListener;", "onAdFailedToLoad", "", "errorCode", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SponsoredFoodEditorViewModel.kt */
public final class SponsoredFoodEditorViewModel$fetchSponsoredBannerAd$3 extends AdListener {
    final /* synthetic */ SponsoredFoodEditorViewModel this$0;

    SponsoredFoodEditorViewModel$fetchSponsoredBannerAd$3(SponsoredFoodEditorViewModel sponsoredFoodEditorViewModel) {
        this.this$0 = sponsoredFoodEditorViewModel;
    }

    public void onAdFailedToLoad(int i) {
        MutableLiveData sponsoredBannerAd = this.this$0.getSponsoredBannerAd();
        StringBuilder sb = new StringBuilder();
        sb.append("DFP ad load failed, code: ");
        sb.append(i);
        sponsoredBannerAd.setValue(new Error(new IllegalArgumentException(sb.toString())));
    }
}

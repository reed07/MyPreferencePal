package com.myfitnesspal.feature.foodeditor.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.myfitnesspal.feature.registration.model.Resource.Success;
import com.myfitnesspal.feature.search.model.SponsoredFoodBannerAd;
import com.myfitnesspal.feature.search.model.SponsoredFoodBannerAd.Companion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "ad", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "kotlin.jvm.PlatformType", "onCustomTemplateAdLoaded"}, k = 3, mv = {1, 1, 13})
/* compiled from: SponsoredFoodEditorViewModel.kt */
final class SponsoredFoodEditorViewModel$fetchSponsoredBannerAd$1 implements OnCustomTemplateAdLoadedListener {
    final /* synthetic */ SponsoredFoodEditorViewModel this$0;

    SponsoredFoodEditorViewModel$fetchSponsoredBannerAd$1(SponsoredFoodEditorViewModel sponsoredFoodEditorViewModel) {
        this.this$0 = sponsoredFoodEditorViewModel;
    }

    public final void onCustomTemplateAdLoaded(NativeCustomTemplateAd nativeCustomTemplateAd) {
        this.this$0.dfpAd = nativeCustomTemplateAd;
        MutableLiveData sponsoredBannerAd = this.this$0.getSponsoredBannerAd();
        Companion companion = SponsoredFoodBannerAd.Companion;
        Intrinsics.checkExpressionValueIsNotNull(nativeCustomTemplateAd, "ad");
        sponsoredBannerAd.setValue(new Success(companion.fromCustomDfpAd(nativeCustomTemplateAd)));
        nativeCustomTemplateAd.recordImpression();
    }
}

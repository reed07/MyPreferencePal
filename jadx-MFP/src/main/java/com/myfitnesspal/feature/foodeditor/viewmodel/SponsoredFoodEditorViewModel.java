package com.myfitnesspal.feature.foodeditor.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import com.google.android.gms.ads.AdLoader.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.myfitnesspal.feature.registration.model.Resource;
import com.myfitnesspal.feature.registration.model.Resource.Loading;
import com.myfitnesspal.feature.search.model.SponsoredFoodBannerAd;
import com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\u0006\u0010\u0012\u001a\u00020\u0010R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/myfitnesspal/feature/foodeditor/viewmodel/SponsoredFoodEditorViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "sponsoredFoodSearchAd", "Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;", "(Landroid/app/Application;Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;)V", "dfpAd", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "sponsoredBannerAd", "Landroid/arch/lifecycle/MutableLiveData;", "Lcom/myfitnesspal/feature/registration/model/Resource;", "Lcom/myfitnesspal/feature/search/model/SponsoredFoodBannerAd;", "getSponsoredBannerAd", "()Landroid/arch/lifecycle/MutableLiveData;", "fetchSponsoredBannerAd", "", "onCleared", "reportBannerClicked", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SponsoredFoodEditorViewModel.kt */
public final class SponsoredFoodEditorViewModel extends AndroidViewModel {
    public static final Companion Companion = new Companion(null);
    private static final String DFP_CUSTOM_KEY_FOOD_ID = "food_id";
    private static final String DFP_PATH_BANNER_AD = "/17729925/UACF_M/MFP/AddEntry/Hero_Android";
    private static final String DFP_TEMPLATE_BANNER_AD = "11760169";
    /* access modifiers changed from: private */
    public NativeCustomTemplateAd dfpAd;
    @NotNull
    private final MutableLiveData<Resource<SponsoredFoodBannerAd>> sponsoredBannerAd = new MutableLiveData<>();
    private final SponsoredFoodSearchAd sponsoredFoodSearchAd;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/foodeditor/viewmodel/SponsoredFoodEditorViewModel$Companion;", "", "()V", "DFP_CUSTOM_KEY_FOOD_ID", "", "DFP_PATH_BANNER_AD", "DFP_TEMPLATE_BANNER_AD", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: SponsoredFoodEditorViewModel.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SponsoredFoodEditorViewModel(@NotNull Application application, @NotNull SponsoredFoodSearchAd sponsoredFoodSearchAd2) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        Intrinsics.checkParameterIsNotNull(sponsoredFoodSearchAd2, "sponsoredFoodSearchAd");
        super(application);
        this.sponsoredFoodSearchAd = sponsoredFoodSearchAd2;
        fetchSponsoredBannerAd();
    }

    @NotNull
    public final MutableLiveData<Resource<SponsoredFoodBannerAd>> getSponsoredBannerAd() {
        return this.sponsoredBannerAd;
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        NativeCustomTemplateAd nativeCustomTemplateAd = this.dfpAd;
        if (nativeCustomTemplateAd != null) {
            nativeCustomTemplateAd.destroy();
        }
    }

    public final void reportBannerClicked() {
        NativeCustomTemplateAd nativeCustomTemplateAd = this.dfpAd;
        if (nativeCustomTemplateAd != null) {
            nativeCustomTemplateAd.performClick("");
        }
    }

    private final void fetchSponsoredBannerAd() {
        this.sponsoredBannerAd.setValue(new Loading());
        new Builder((Context) getApplication(), DFP_PATH_BANNER_AD).forCustomTemplateAd(DFP_TEMPLATE_BANNER_AD, new SponsoredFoodEditorViewModel$fetchSponsoredBannerAd$1(this), SponsoredFoodEditorViewModel$fetchSponsoredBannerAd$2.INSTANCE).withAdListener(new SponsoredFoodEditorViewModel$fetchSponsoredBannerAd$3(this)).build().loadAd(new PublisherAdRequest.Builder().addCustomTargeting("food_id", this.sponsoredFoodSearchAd.getFoodId()).build());
    }
}

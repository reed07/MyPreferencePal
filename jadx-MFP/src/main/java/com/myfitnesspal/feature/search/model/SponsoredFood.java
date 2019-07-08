package com.myfitnesspal.feature.search.model;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpFood;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "sponsoredFoodAd", "Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;", "adCategory", "", "dfpAd", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "(Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;Ljava/lang/String;Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;)V", "getAdCategory", "()Ljava/lang/String;", "setAdCategory", "(Ljava/lang/String;)V", "getDfpAd", "()Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "setDfpAd", "(Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;)V", "getSponsoredFoodAd", "()Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;", "setSponsoredFoodAd", "(Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;)V", "API_RESPONSE_MAPPER", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SponsoredFood.kt */
public final class SponsoredFood extends MfpFood {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final SponsoredFood EMPTY;
    @Nullable
    private String adCategory;
    @Nullable
    private NativeCustomTemplateAd dfpAd;
    @Nullable
    private SponsoredFoodSearchAd sponsoredFoodAd;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SponsoredFood$API_RESPONSE_MAPPER;", "Lcom/myfitnesspal/shared/api/ApiResponse;", "Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "()V", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: SponsoredFood.kt */
    public static final class API_RESPONSE_MAPPER extends ApiResponse<SponsoredFood> {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SponsoredFood$Companion;", "", "()V", "EMPTY", "Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "getEMPTY", "()Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: SponsoredFood.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SponsoredFood getEMPTY() {
            return SponsoredFood.EMPTY;
        }
    }

    public SponsoredFood() {
        this(null, null, null, 7, null);
    }

    public /* synthetic */ SponsoredFood(SponsoredFoodSearchAd sponsoredFoodSearchAd, String str, NativeCustomTemplateAd nativeCustomTemplateAd, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            sponsoredFoodSearchAd = null;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            nativeCustomTemplateAd = null;
        }
        this(sponsoredFoodSearchAd, str, nativeCustomTemplateAd);
    }

    @Nullable
    public final SponsoredFoodSearchAd getSponsoredFoodAd() {
        return this.sponsoredFoodAd;
    }

    public final void setSponsoredFoodAd(@Nullable SponsoredFoodSearchAd sponsoredFoodSearchAd) {
        this.sponsoredFoodAd = sponsoredFoodSearchAd;
    }

    @Nullable
    public final String getAdCategory() {
        return this.adCategory;
    }

    public final void setAdCategory(@Nullable String str) {
        this.adCategory = str;
    }

    @Nullable
    public final NativeCustomTemplateAd getDfpAd() {
        return this.dfpAd;
    }

    public final void setDfpAd(@Nullable NativeCustomTemplateAd nativeCustomTemplateAd) {
        this.dfpAd = nativeCustomTemplateAd;
    }

    public SponsoredFood(@Nullable SponsoredFoodSearchAd sponsoredFoodSearchAd, @Nullable String str, @Nullable NativeCustomTemplateAd nativeCustomTemplateAd) {
        this.sponsoredFoodAd = sponsoredFoodSearchAd;
        this.adCategory = str;
        this.dfpAd = nativeCustomTemplateAd;
    }

    static {
        SponsoredFood sponsoredFood = new SponsoredFood(null, null, null, 7, null);
        EMPTY = sponsoredFood;
    }
}

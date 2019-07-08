package com.myfitnesspal.feature.search.model;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SponsoredFoodBannerAd;", "", "imageUrl", "", "clickUrl", "(Ljava/lang/String;Ljava/lang/String;)V", "getClickUrl", "()Ljava/lang/String;", "getImageUrl", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SponsoredFoodBannerAd.kt */
public final class SponsoredFoodBannerAd {
    private static final String ATTR_CLICK_URL = "ClickthruURL";
    private static final String ATTR_IMAGE_URL = "BannerImagePath";
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final String clickUrl;
    @Nullable
    private final String imageUrl;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SponsoredFoodBannerAd$Companion;", "", "()V", "ATTR_CLICK_URL", "", "ATTR_IMAGE_URL", "fromCustomDfpAd", "Lcom/myfitnesspal/feature/search/model/SponsoredFoodBannerAd;", "customAd", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: SponsoredFoodBannerAd.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SponsoredFoodBannerAd fromCustomDfpAd(@NotNull NativeCustomTemplateAd nativeCustomTemplateAd) {
            Intrinsics.checkParameterIsNotNull(nativeCustomTemplateAd, "customAd");
            return new SponsoredFoodBannerAd((String) nativeCustomTemplateAd.getText(SponsoredFoodBannerAd.ATTR_IMAGE_URL), (String) nativeCustomTemplateAd.getText(SponsoredFoodBannerAd.ATTR_CLICK_URL));
        }
    }

    public SponsoredFoodBannerAd(@Nullable String str, @Nullable String str2) {
        this.imageUrl = str;
        this.clickUrl = str2;
    }

    @Nullable
    public final String getImageUrl() {
        return this.imageUrl;
    }

    @Nullable
    public final String getClickUrl() {
        return this.clickUrl;
    }
}

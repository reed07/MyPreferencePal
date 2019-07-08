package com.myfitnesspal.feature.home.ui.adapter.factory;

import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardRenderer;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.home.ui.adapter.DfpNativeAdsAdapter;
import com.myfitnesspal.feature.home.ui.adapter.NativeAdsAdapter;
import com.myfitnesspal.feature.home.ui.adapter.NewsFeedAdapter;
import com.myfitnesspal.feature.home.ui.fragment.HomeFragment;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B¿\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0007\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0007\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0007\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0007\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0007\u0012\u0006\u0010\u001f\u001a\u00020 ¢\u0006\u0002\u0010!J\n\u0010-\u001a\u0006\u0012\u0002\b\u00030.R\u001b\u0010\"\u001a\u00020#8BX\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b$\u0010%R\u001b\u0010(\u001a\u00020)8BX\u0002¢\u0006\f\n\u0004\b,\u0010'\u001a\u0004\b*\u0010+R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/myfitnesspal/feature/home/ui/adapter/factory/NativeAdsAdapterFactory;", "", "homeFragment", "Lcom/myfitnesspal/feature/home/ui/fragment/HomeFragment;", "newsFeedItemActionListener", "Lcom/myfitnesspal/feature/home/listener/NewsFeedItemActionListener;", "premiumService", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/premium/service/PremiumService;", "imageService", "Lcom/myfitnesspal/feature/images/service/ImageService;", "nutrientDashboardRenderer", "Lcom/myfitnesspal/feature/dashboard/ui/view/NutrientDashboardRenderer;", "userApplicationSettingsService", "Lcom/myfitnesspal/feature/userapplicationsettings/service/UserApplicationSettingsService;", "flowId", "", "newsFeedAnalyticsHelper", "Lcom/myfitnesspal/feature/home/service/NewsFeedAnalyticsHelper;", "configService", "Lcom/myfitnesspal/shared/service/config/ConfigService;", "adUnitService", "Lcom/myfitnesspal/shared/service/ads/AdUnitService;", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "newsFeedService", "Lcom/myfitnesspal/shared/service/newsfeed/NewsFeedService;", "adsSettings", "Lcom/myfitnesspal/feature/settings/model/AdsSettings;", "locationService", "Lcom/myfitnesspal/shared/service/location/LocationService;", "isCountryUS", "", "(Lcom/myfitnesspal/feature/home/ui/fragment/HomeFragment;Lcom/myfitnesspal/feature/home/listener/NewsFeedItemActionListener;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ljava/lang/String;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Z)V", "dfpNativeAdsAdapter", "Lcom/myfitnesspal/feature/home/ui/adapter/DfpNativeAdsAdapter;", "getDfpNativeAdsAdapter", "()Lcom/myfitnesspal/feature/home/ui/adapter/DfpNativeAdsAdapter;", "dfpNativeAdsAdapter$delegate", "Lkotlin/Lazy;", "newsFeedAdapter", "Lcom/myfitnesspal/feature/home/ui/adapter/NewsFeedAdapter;", "getNewsFeedAdapter", "()Lcom/myfitnesspal/feature/home/ui/adapter/NewsFeedAdapter;", "newsFeedAdapter$delegate", "get", "Lcom/myfitnesspal/feature/home/ui/adapter/NativeAdsAdapter;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: NativeAdsAdapterFactory.kt */
public final class NativeAdsAdapterFactory {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NativeAdsAdapterFactory.class), "newsFeedAdapter", "getNewsFeedAdapter()Lcom/myfitnesspal/feature/home/ui/adapter/NewsFeedAdapter;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NativeAdsAdapterFactory.class), "dfpNativeAdsAdapter", "getDfpNativeAdsAdapter()Lcom/myfitnesspal/feature/home/ui/adapter/DfpNativeAdsAdapter;"))};
    private final Lazy dfpNativeAdsAdapter$delegate;
    private final Lazy newsFeedAdapter$delegate;
    /* access modifiers changed from: private */
    public final dagger.Lazy<PremiumService> premiumService;

    private final DfpNativeAdsAdapter getDfpNativeAdsAdapter() {
        Lazy lazy = this.dfpNativeAdsAdapter$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (DfpNativeAdsAdapter) lazy.getValue();
    }

    /* access modifiers changed from: private */
    public final NewsFeedAdapter getNewsFeedAdapter() {
        Lazy lazy = this.newsFeedAdapter$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (NewsFeedAdapter) lazy.getValue();
    }

    public NativeAdsAdapterFactory(@NotNull HomeFragment homeFragment, @NotNull NewsFeedItemActionListener newsFeedItemActionListener, @NotNull dagger.Lazy<PremiumService> lazy, @NotNull dagger.Lazy<ImageService> lazy2, @NotNull dagger.Lazy<NutrientDashboardRenderer> lazy3, @NotNull dagger.Lazy<UserApplicationSettingsService> lazy4, @NotNull String str, @NotNull dagger.Lazy<NewsFeedAnalyticsHelper> lazy5, @NotNull dagger.Lazy<ConfigService> lazy6, @NotNull dagger.Lazy<AdUnitService> lazy7, @NotNull dagger.Lazy<LocalSettingsService> lazy8, @NotNull dagger.Lazy<NewsFeedService> lazy9, @NotNull dagger.Lazy<AdsSettings> lazy10, @NotNull dagger.Lazy<LocationService> lazy11, boolean z) {
        dagger.Lazy<PremiumService> lazy12 = lazy;
        HomeFragment homeFragment2 = homeFragment;
        Intrinsics.checkParameterIsNotNull(homeFragment2, "homeFragment");
        NewsFeedItemActionListener newsFeedItemActionListener2 = newsFeedItemActionListener;
        Intrinsics.checkParameterIsNotNull(newsFeedItemActionListener2, "newsFeedItemActionListener");
        Intrinsics.checkParameterIsNotNull(lazy12, "premiumService");
        dagger.Lazy<ImageService> lazy13 = lazy2;
        Intrinsics.checkParameterIsNotNull(lazy13, "imageService");
        dagger.Lazy<NutrientDashboardRenderer> lazy14 = lazy3;
        Intrinsics.checkParameterIsNotNull(lazy14, "nutrientDashboardRenderer");
        Intrinsics.checkParameterIsNotNull(lazy4, "userApplicationSettingsService");
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str2, "flowId");
        Intrinsics.checkParameterIsNotNull(lazy5, "newsFeedAnalyticsHelper");
        Intrinsics.checkParameterIsNotNull(lazy6, "configService");
        Intrinsics.checkParameterIsNotNull(lazy7, "adUnitService");
        Intrinsics.checkParameterIsNotNull(lazy8, "localSettingsService");
        Intrinsics.checkParameterIsNotNull(lazy9, "newsFeedService");
        Intrinsics.checkParameterIsNotNull(lazy10, "adsSettings");
        Intrinsics.checkParameterIsNotNull(lazy11, "locationService");
        this.premiumService = lazy12;
        NativeAdsAdapterFactory$newsFeedAdapter$2 nativeAdsAdapterFactory$newsFeedAdapter$2 = new NativeAdsAdapterFactory$newsFeedAdapter$2(this, homeFragment2, lazy13, newsFeedItemActionListener2, lazy5, lazy6, lazy14, lazy4, str2, lazy9);
        this.newsFeedAdapter$delegate = LazyKt.lazy(nativeAdsAdapterFactory$newsFeedAdapter$2);
        NativeAdsAdapterFactory$dfpNativeAdsAdapter$2 nativeAdsAdapterFactory$dfpNativeAdsAdapter$2 = new NativeAdsAdapterFactory$dfpNativeAdsAdapter$2(this, lazy6, lazy4, lazy8, lazy11, lazy10, lazy5, lazy7, z);
        this.dfpNativeAdsAdapter$delegate = LazyKt.lazy(nativeAdsAdapterFactory$dfpNativeAdsAdapter$2);
    }

    @NotNull
    public final NativeAdsAdapter<?> get() {
        return !((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.AdFree) ? getDfpNativeAdsAdapter() : getNewsFeedAdapter();
    }
}

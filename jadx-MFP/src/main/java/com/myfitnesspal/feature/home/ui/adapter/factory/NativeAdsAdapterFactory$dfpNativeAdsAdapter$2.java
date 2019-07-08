package com.myfitnesspal.feature.home.ui.adapter.factory;

import com.myfitnesspal.feature.home.ui.adapter.DfpNativeAdsAdapter;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/feature/home/ui/adapter/DfpNativeAdsAdapter;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: NativeAdsAdapterFactory.kt */
final class NativeAdsAdapterFactory$dfpNativeAdsAdapter$2 extends Lambda implements Function0<DfpNativeAdsAdapter> {
    final /* synthetic */ Lazy $adUnitService;
    final /* synthetic */ Lazy $adsSettings;
    final /* synthetic */ Lazy $configService;
    final /* synthetic */ boolean $isCountryUS;
    final /* synthetic */ Lazy $localSettingsService;
    final /* synthetic */ Lazy $locationService;
    final /* synthetic */ Lazy $newsFeedAnalyticsHelper;
    final /* synthetic */ Lazy $userApplicationSettingsService;
    final /* synthetic */ NativeAdsAdapterFactory this$0;

    NativeAdsAdapterFactory$dfpNativeAdsAdapter$2(NativeAdsAdapterFactory nativeAdsAdapterFactory, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, boolean z) {
        this.this$0 = nativeAdsAdapterFactory;
        this.$configService = lazy;
        this.$userApplicationSettingsService = lazy2;
        this.$localSettingsService = lazy3;
        this.$locationService = lazy4;
        this.$adsSettings = lazy5;
        this.$newsFeedAnalyticsHelper = lazy6;
        this.$adUnitService = lazy7;
        this.$isCountryUS = z;
        super(0);
    }

    @NotNull
    public final DfpNativeAdsAdapter invoke() {
        DfpNativeAdsAdapter dfpNativeAdsAdapter = new DfpNativeAdsAdapter(this.this$0.premiumService, this.this$0.getNewsFeedAdapter(), this.$configService, this.$userApplicationSettingsService, this.$localSettingsService, this.$locationService, this.$adsSettings, this.$newsFeedAnalyticsHelper, this.$adUnitService, Boolean.valueOf(this.$isCountryUS));
        return dfpNativeAdsAdapter;
    }
}

package com.myfitnesspal.feature.home.ui.adapter.factory;

import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.DailySummaryHeader;
import com.myfitnesspal.feature.home.model.NewsFeedDisplayActivityName;
import com.myfitnesspal.feature.home.ui.adapter.NewsFeedAdapter;
import com.myfitnesspal.feature.home.ui.fragment.HomeFragment;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/feature/home/ui/adapter/NewsFeedAdapter;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: NativeAdsAdapterFactory.kt */
final class NativeAdsAdapterFactory$newsFeedAdapter$2 extends Lambda implements Function0<NewsFeedAdapter> {
    final /* synthetic */ Lazy $configService;
    final /* synthetic */ String $flowId;
    final /* synthetic */ HomeFragment $homeFragment;
    final /* synthetic */ Lazy $imageService;
    final /* synthetic */ Lazy $newsFeedAnalyticsHelper;
    final /* synthetic */ NewsFeedItemActionListener $newsFeedItemActionListener;
    final /* synthetic */ Lazy $newsFeedService;
    final /* synthetic */ Lazy $nutrientDashboardRenderer;
    final /* synthetic */ Lazy $userApplicationSettingsService;
    final /* synthetic */ NativeAdsAdapterFactory this$0;

    NativeAdsAdapterFactory$newsFeedAdapter$2(NativeAdsAdapterFactory nativeAdsAdapterFactory, HomeFragment homeFragment, Lazy lazy, NewsFeedItemActionListener newsFeedItemActionListener, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, String str, Lazy lazy6) {
        this.this$0 = nativeAdsAdapterFactory;
        this.$homeFragment = homeFragment;
        this.$imageService = lazy;
        this.$newsFeedItemActionListener = newsFeedItemActionListener;
        this.$newsFeedAnalyticsHelper = lazy2;
        this.$configService = lazy3;
        this.$nutrientDashboardRenderer = lazy4;
        this.$userApplicationSettingsService = lazy5;
        this.$flowId = str;
        this.$newsFeedService = lazy6;
        super(0);
    }

    @NotNull
    public final NewsFeedAdapter invoke() {
        NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(this.$homeFragment.getNavigationHelper(), this.this$0.premiumService, this.$imageService, this.$newsFeedItemActionListener, this.$newsFeedAnalyticsHelper, this.$configService, this.$homeFragment.getSession(), NewsFeedDisplayActivityName.Home, this.$nutrientDashboardRenderer, this.$userApplicationSettingsService, this.$homeFragment.getSession().getUser().getUsername(), this.$flowId, this.$newsFeedService);
        newsFeedAdapter.addItem(new DailySummaryHeader());
        return newsFeedAdapter;
    }
}

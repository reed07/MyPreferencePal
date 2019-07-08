package com.myfitnesspal.feature.search.ui.viewmodel;

import com.myfitnesspal.feature.registration.model.Resource.Success;
import com.myfitnesspal.feature.search.model.OnlineSearchResult;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.model.SponsoredFood;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import io.reactivex.functions.Consumer;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "searchResult", "Lcom/myfitnesspal/feature/search/model/OnlineSearchResult;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchViewModel.kt */
final class OnlineFoodSearchViewModel$search$2<T> implements Consumer<OnlineSearchResult> {
    final /* synthetic */ String $query;
    final /* synthetic */ OnlineFoodSearchViewModel this$0;

    OnlineFoodSearchViewModel$search$2(OnlineFoodSearchViewModel onlineFoodSearchViewModel, String str) {
        this.this$0 = onlineFoodSearchViewModel;
        this.$query = str;
    }

    public final void accept(OnlineSearchResult onlineSearchResult) {
        this.this$0.searchRequestId = onlineSearchResult.getRequestId();
        List items = onlineSearchResult.getItems();
        ((ActionTrackingService) this.this$0.actionTrackingService.get()).deleteEvent(Events.ONLINE_SEARCH_SUMMARY);
        if (!(!Intrinsics.areEqual((Object) onlineSearchResult.getSponsoredFood(), (Object) SponsoredFood.Companion.getEMPTY())) || items.isEmpty()) {
            this.this$0.containsSponsoredFood = false;
            ((ActionTrackingService) this.this$0.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.CONTAINS_FOOD_AD, Strings.toString(Boolean.valueOf(false)));
        } else {
            MfpFoodSearchResult mfpFoodSearchResult = new MfpFoodSearchResult();
            mfpFoodSearchResult.setSearchResultItem(onlineSearchResult.getSponsoredFood());
            items.add(0, mfpFoodSearchResult);
            this.this$0.containsSponsoredFood = true;
            OnlineFoodSearchViewModel onlineFoodSearchViewModel = this.this$0;
            SponsoredFood sponsoredFood = onlineSearchResult.getSponsoredFood();
            onlineFoodSearchViewModel.dfpAd = sponsoredFood != null ? sponsoredFood.getDfpAd() : null;
            SponsoredFood sponsoredFood2 = onlineSearchResult.getSponsoredFood();
            if (sponsoredFood2 != null) {
                FoodSearchAnalyticsHelper access$getFoodSearchAnalyticsHelper$p = this.this$0.foodSearchAnalyticsHelper;
                String flowId = this.this$0.getFlowId();
                String id = sponsoredFood2.getId();
                Intrinsics.checkExpressionValueIsNotNull(id, "it.id");
                String version = sponsoredFood2.getVersion();
                Intrinsics.checkExpressionValueIsNotNull(version, "it.version");
                access$getFoodSearchAnalyticsHelper$p.reportFoodSearchAdDisplayed(flowId, id, version, this.$query, this.this$0.getSearchRequestId(), 0, SearchSource.ONLINE);
            }
            ((ActionTrackingService) this.this$0.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.CONTAINS_FOOD_AD, Strings.toString(Boolean.valueOf(true)));
        }
        this.this$0.nextPageLink = onlineSearchResult.getNextPageLink();
        this.this$0.getSearchResults().setValue(new Success(items));
        if (CollectionUtils.notEmpty((Collection<?>) items)) {
            this.this$0.logSearchResultSize(items.size(), this.this$0.doesListContainVenueSearchResult(items));
        } else {
            ((AnalyticsService) this.this$0.analyticsService.get()).reportEvent(Events.FOODSEARCH_ZERO_RESULTS_FOUND);
        }
        this.this$0.appendCommonAnltAttrsToOnlineFoodSummary();
    }
}

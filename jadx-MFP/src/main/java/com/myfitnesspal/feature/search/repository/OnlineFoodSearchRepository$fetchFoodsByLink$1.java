package com.myfitnesspal.feature.search.repository;

import com.myfitnesspal.feature.search.model.OnlineSearchResult;
import com.myfitnesspal.shared.api.ApiResponse;
import com.uacf.core.util.Tuple2;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/feature/search/model/OnlineSearchResult;", "call"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchRepository.kt */
final class OnlineFoodSearchRepository$fetchFoodsByLink$1<V> implements Callable<T> {
    final /* synthetic */ String $flowId;
    final /* synthetic */ String $link;
    final /* synthetic */ OnlineFoodSearchRepository this$0;

    OnlineFoodSearchRepository$fetchFoodsByLink$1(OnlineFoodSearchRepository onlineFoodSearchRepository, String str, String str2) {
        this.this$0 = onlineFoodSearchRepository;
        this.$link = str;
        this.$flowId = str2;
    }

    @NotNull
    public final OnlineSearchResult call() {
        Tuple2 moreResultsV2 = this.this$0.searchService.getMoreResultsV2(this.$link, this.$flowId);
        Intrinsics.checkExpressionValueIsNotNull(moreResultsV2, "tuple");
        Object item1 = moreResultsV2.getItem1();
        Intrinsics.checkExpressionValueIsNotNull(item1, "tuple.item1");
        if (((ApiResponse) item1).getItems() != null) {
            Object item12 = moreResultsV2.getItem1();
            Intrinsics.checkExpressionValueIsNotNull(item12, "tuple.item1");
            List items = ((ApiResponse) item12).getItems();
            Intrinsics.checkExpressionValueIsNotNull(items, "tuple.item1.items");
            OnlineSearchResult onlineSearchResult = new OnlineSearchResult(items, null, (String) moreResultsV2.getItem2(), null, 10, null);
            return onlineSearchResult;
        }
        throw new IllegalArgumentException("Items list is null");
    }
}

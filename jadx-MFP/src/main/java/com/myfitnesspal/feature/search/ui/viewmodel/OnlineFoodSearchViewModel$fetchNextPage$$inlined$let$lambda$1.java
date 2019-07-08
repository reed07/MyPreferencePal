package com.myfitnesspal.feature.search.ui.viewmodel;

import com.myfitnesspal.feature.registration.model.Resource.Success;
import com.myfitnesspal.feature.search.model.OnlineSearchResult;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "result", "Lcom/myfitnesspal/feature/search/model/OnlineSearchResult;", "kotlin.jvm.PlatformType", "accept", "com/myfitnesspal/feature/search/ui/viewmodel/OnlineFoodSearchViewModel$fetchNextPage$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchViewModel.kt */
final class OnlineFoodSearchViewModel$fetchNextPage$$inlined$let$lambda$1<T> implements Consumer<OnlineSearchResult> {
    final /* synthetic */ OnlineFoodSearchViewModel this$0;

    OnlineFoodSearchViewModel$fetchNextPage$$inlined$let$lambda$1(OnlineFoodSearchViewModel onlineFoodSearchViewModel) {
        this.this$0 = onlineFoodSearchViewModel;
    }

    public final void accept(OnlineSearchResult onlineSearchResult) {
        this.this$0.nextPageLink = onlineSearchResult.getNextPageLink();
        if (this.this$0.getSearchResults().getValue() instanceof Success) {
            Object value = this.this$0.getSearchResults().getValue();
            if (!(value instanceof Success)) {
                value = null;
            }
            Success success = (Success) value;
            if (success != null) {
                List list = (List) success.getData();
                if (list != null) {
                    list.addAll(onlineSearchResult.getItems());
                }
            }
            this.this$0.getNextPage().setValue(new Success(null));
        }
    }
}

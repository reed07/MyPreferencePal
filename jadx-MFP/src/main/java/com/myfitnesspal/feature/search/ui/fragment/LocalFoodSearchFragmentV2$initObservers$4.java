package com.myfitnesspal.feature.search.ui.fragment;

import android.arch.lifecycle.Observer;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.widget.Button;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.model.SortOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "sortOrder", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initObservers$4<T> implements Observer<SortOrder> {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initObservers$4(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
    }

    public final void onChanged(@Nullable SortOrder sortOrder) {
        RecyclerView recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        int i = 0;
        if (layoutManager != null) {
            layoutManager.scrollToPosition(0);
        }
        this.this$0.expandAppBarIfPresent();
        Button button = (Button) this.this$0._$_findCachedViewById(R.id.sortOrderButton);
        if (sortOrder != null) {
            switch (sortOrder) {
                case ALPHABETICAL_ASCENDING:
                    i = R.string.alphabetical_ascending;
                    break;
                case ALPHABETICAL_DESCENDING:
                    i = R.string.alphabetical_descending;
                    break;
                case RECENTLY_USED:
                    i = R.string.sort_recent;
                    break;
                case DATE_DESCENDING:
                    i = R.string.date_created;
                    break;
                case FREQUENTLY_USED:
                    i = R.string.most_frequent;
                    break;
            }
        }
        button.setText(i);
    }
}

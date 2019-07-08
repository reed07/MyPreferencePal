package com.myfitnesspal.feature.search.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0016¨\u0006\u000b¸\u0006\u0000"}, d2 = {"com/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2$initViews$2$2", "Landroid/support/v7/widget/RecyclerView$OnScrollListener;", "onScrollStateChanged", "", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "newState", "", "onScrolled", "dx", "dy", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
public final class LocalFoodSearchFragmentV2$initViews$$inlined$apply$lambda$2 extends OnScrollListener {
    final /* synthetic */ LinearLayoutManager $layoutManager;
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initViews$$inlined$apply$lambda$2(LinearLayoutManager linearLayoutManager, LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.$layoutManager = linearLayoutManager;
        this.this$0 = localFoodSearchFragmentV2;
    }

    public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int i) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        this.this$0.getImmHelper().hideSoftInput();
    }

    public void onScrolled(@NotNull RecyclerView recyclerView, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        super.onScrolled(recyclerView, i, i2);
        int childCount = this.$layoutManager.getChildCount();
        int itemCount = this.$layoutManager.getItemCount();
        int findFirstVisibleItemPosition = this.$layoutManager.findFirstVisibleItemPosition();
        if (childCount + findFirstVisibleItemPosition >= itemCount && findFirstVisibleItemPosition >= 0) {
            LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).loadNextPage();
        }
    }
}

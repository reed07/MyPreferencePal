package com.myfitnesspal.feature.search.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import com.google.android.gms.analytics.ecommerce.Promotion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b¸\u0006\u0000"}, d2 = {"com/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2$initViews$2$1", "Landroid/support/v7/widget/RecyclerView$OnScrollListener;", "onScrollStateChanged", "", "view", "Landroid/support/v7/widget/RecyclerView;", "scrollState", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
public final class LocalFoodSearchFragmentV2$initViews$$inlined$apply$lambda$1 extends OnScrollListener {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initViews$$inlined$apply$lambda$1(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
    }

    public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int i) {
        Intrinsics.checkParameterIsNotNull(recyclerView, Promotion.ACTION_VIEW);
        this.this$0.getImmHelper().hideSoftInput();
    }
}

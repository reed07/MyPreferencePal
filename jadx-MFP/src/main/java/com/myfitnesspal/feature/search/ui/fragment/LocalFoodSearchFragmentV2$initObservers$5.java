package com.myfitnesspal.feature.search.ui.fragment;

import android.arch.lifecycle.Observer;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import com.myfitnesspal.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "onChanged", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initObservers$5<T> implements Observer<Boolean> {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initObservers$5(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
    }

    public final void onChanged(@Nullable Boolean bool) {
        RecyclerView recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            layoutManager.scrollToPosition(0);
        }
        this.this$0.expandAppBarIfPresent();
    }
}

package com.myfitnesspal.feature.search.ui.fragment;

import android.arch.lifecycle.Observer;
import android.support.constraint.Group;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "items", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initObservers$1<T> implements Observer<List<? extends DiaryEntryCellModel>> {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initObservers$1(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
    }

    public final void onChanged(@Nullable List<? extends DiaryEntryCellModel> list) {
        if (this.this$0.shouldShowEmptyState(list)) {
            this.this$0.showEmptyState();
        } else if (list != null) {
            Group group = (Group) this.this$0._$_findCachedViewById(R.id.localSearchResultGroup);
            Intrinsics.checkExpressionValueIsNotNull(group, "localSearchResultGroup");
            group.setVisibility(0);
            Group group2 = (Group) this.this$0._$_findCachedViewById(R.id.emptyResultsGroup);
            Intrinsics.checkExpressionValueIsNotNull(group2, "emptyResultsGroup");
            group2.setVisibility(8);
            LocalFoodSearchFragmentV2.access$getFoodAdapter$p(this.this$0).setItems(list);
            LocalFoodSearchFragmentV2.access$getFoodAdapter$p(this.this$0).notifyDataSetChanged();
        }
    }
}

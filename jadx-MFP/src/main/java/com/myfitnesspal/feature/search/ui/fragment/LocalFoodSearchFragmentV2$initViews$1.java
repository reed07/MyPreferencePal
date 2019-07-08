package com.myfitnesspal.feature.search.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.ui.dialog.SortAndFilterBottomSheet;
import com.myfitnesspal.feature.search.ui.dialog.SortOrderBottomSheet;
import com.myfitnesspal.shared.ui.fragment.MfpBottomSheetFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initViews$1 implements OnClickListener {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initViews$1(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
    }

    public final void onClick(View view) {
        MfpBottomSheetFragment mfpBottomSheetFragment;
        if (LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).getShouldShowSortAndFilter()) {
            SortAndFilterBottomSheet newInstance = SortAndFilterBottomSheet.Companion.newInstance((SortOrder) LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).getSortOrder().getValue(), LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).getMealName(), Intrinsics.areEqual((Object) (Boolean) LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).getShouldFilterAllMeals().getValue(), (Object) Boolean.valueOf(true)));
            newInstance.setOnSortAndFilterSelected(new LocalFoodSearchFragmentV2$initViews$1$$special$$inlined$apply$lambda$1(this));
            mfpBottomSheetFragment = newInstance;
        } else {
            SortOrderBottomSheet newInstance2 = SortOrderBottomSheet.Companion.newInstance((SortOrder) LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).getSortOrder().getValue());
            newInstance2.setOnSortOrderSelected(new LocalFoodSearchFragmentV2$initViews$1$$special$$inlined$apply$lambda$2(this));
            mfpBottomSheetFragment = newInstance2;
        }
        this.this$0.showDialogFragment(mfpBottomSheetFragment, "search_result_sort_order_fragment");
        LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).reportSortAndFilterOptionsClicked();
    }
}

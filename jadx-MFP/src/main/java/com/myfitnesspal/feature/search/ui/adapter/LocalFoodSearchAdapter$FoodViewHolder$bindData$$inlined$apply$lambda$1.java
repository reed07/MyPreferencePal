package com.myfitnesspal.feature.search.ui.adapter;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter.FoodViewHolder;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged", "com/myfitnesspal/feature/search/ui/adapter/LocalFoodSearchAdapter$FoodViewHolder$bindData$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchAdapter.kt */
final class LocalFoodSearchAdapter$FoodViewHolder$bindData$$inlined$apply$lambda$1 implements OnCheckedChangeListener {
    final /* synthetic */ boolean $isChecked$inlined;
    final /* synthetic */ DiaryEntryCellModel $item$inlined;
    final /* synthetic */ FoodViewHolder this$0;

    LocalFoodSearchAdapter$FoodViewHolder$bindData$$inlined$apply$lambda$1(FoodViewHolder foodViewHolder, boolean z, DiaryEntryCellModel diaryEntryCellModel) {
        this.this$0 = foodViewHolder;
        this.$isChecked$inlined = z;
        this.$item$inlined = diaryEntryCellModel;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.this$0.this$0.getOnItemCheckedChange().invoke(this.$item$inlined, Integer.valueOf(this.this$0.getAdapterPosition()), Boolean.valueOf(z));
    }
}

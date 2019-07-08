package com.myfitnesspal.feature.search.ui.activity;

import android.arch.lifecycle.Observer;
import android.support.v7.view.ActionMode;
import com.myfitnesspal.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "itemsCount", "", "onChanged", "(Ljava/lang/Integer;)V"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
final class FoodSearchActivityV2$onCreate$2<T> implements Observer<Integer> {
    final /* synthetic */ FoodSearchActivityV2 this$0;

    FoodSearchActivityV2$onCreate$2(FoodSearchActivityV2 foodSearchActivityV2) {
        this.this$0 = foodSearchActivityV2;
    }

    public final void onChanged(@Nullable Integer num) {
        if (num != null) {
            ActionMode access$getMultiAddActionMode$p = this.this$0.multiAddActionMode;
            if (access$getMultiAddActionMode$p != null) {
                access$getMultiAddActionMode$p.setTitle((CharSequence) this.this$0.getString(Intrinsics.compare(num.intValue(), 0) > 0 ? R.string.number_selected : R.string.select_items, new Object[]{num}));
                access$getMultiAddActionMode$p.invalidate();
            }
        }
    }
}

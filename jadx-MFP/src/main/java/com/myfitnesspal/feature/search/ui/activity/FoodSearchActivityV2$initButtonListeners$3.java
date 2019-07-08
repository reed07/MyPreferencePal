package com.myfitnesspal.feature.search.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.ClearableEditText;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
final class FoodSearchActivityV2$initButtonListeners$3 implements OnClickListener {
    final /* synthetic */ FoodSearchActivityV2 this$0;

    FoodSearchActivityV2$initButtonListeners$3(FoodSearchActivityV2 foodSearchActivityV2) {
        this.this$0 = foodSearchActivityV2;
    }

    public final void onClick(View view) {
        ((ClearableEditText) this.this$0._$_findCachedViewById(R.id.searchEditText)).setText("");
        ((ClearableEditText) this.this$0._$_findCachedViewById(R.id.searchEditText)).clearFocus();
        this.this$0.getImmHelper().hideSoftInput();
    }
}

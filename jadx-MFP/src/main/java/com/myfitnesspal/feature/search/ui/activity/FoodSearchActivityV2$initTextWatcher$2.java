package com.myfitnesspal.feature.search.ui.activity;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Trigger;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\b0\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "actionId", "", "event", "Landroid/view/KeyEvent;", "onEditorAction"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
final class FoodSearchActivityV2$initTextWatcher$2 implements OnEditorActionListener {
    final /* synthetic */ FoodSearchActivityV2 this$0;

    FoodSearchActivityV2$initTextWatcher$2(FoodSearchActivityV2 foodSearchActivityV2) {
        this.this$0 = foodSearchActivityV2;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if ((i != 0 && i != 3) || keyEvent == null || keyEvent.getAction() != 0) {
            return false;
        }
        this.this$0.showOnlineSearch(Trigger.MANUAL);
        return true;
    }
}

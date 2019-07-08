package com.myfitnesspal.feature.search.ui.activity;

import android.arch.lifecycle.Observer;
import android.support.v7.view.ActionMode;
import com.myfitnesspal.shared.util.MaterialUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "isEnabled", "", "onChanged", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
final class FoodSearchActivityV2$onCreate$1<T> implements Observer<Boolean> {
    final /* synthetic */ FoodSearchActivityV2 this$0;

    FoodSearchActivityV2$onCreate$1(FoodSearchActivityV2 foodSearchActivityV2) {
        this.this$0 = foodSearchActivityV2;
    }

    public final void onChanged(@Nullable Boolean bool) {
        if (Intrinsics.areEqual((Object) bool, (Object) Boolean.valueOf(true)) && this.this$0.multiAddActionMode == null) {
            FoodSearchActivityV2 foodSearchActivityV2 = this.this$0;
            foodSearchActivityV2.startSupportActionMode(new MultiAddActionMode());
            MaterialUtils.cleanActionModeDoneText(this.this$0);
        } else if (Intrinsics.areEqual((Object) bool, (Object) Boolean.valueOf(false)) && this.this$0.multiAddActionMode != null) {
            ActionMode access$getMultiAddActionMode$p = this.this$0.multiAddActionMode;
            if (access$getMultiAddActionMode$p != null) {
                access$getMultiAddActionMode$p.finish();
            }
        }
    }
}

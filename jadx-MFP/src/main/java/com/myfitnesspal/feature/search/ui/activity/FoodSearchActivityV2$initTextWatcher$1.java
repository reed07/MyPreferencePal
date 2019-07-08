package com.myfitnesspal.feature.search.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$initTextWatcher$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
public final class FoodSearchActivityV2$initTextWatcher$1 implements TextWatcher {
    final /* synthetic */ FoodSearchActivityV2 this$0;

    public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
    }

    FoodSearchActivityV2$initTextWatcher$1(FoodSearchActivityV2 foodSearchActivityV2) {
        this.this$0 = foodSearchActivityV2;
    }

    public void afterTextChanged(@NotNull Editable editable) {
        Intrinsics.checkParameterIsNotNull(editable, "s");
        this.this$0.setShowingOnlineSearch(false);
        String obj = editable.toString();
        this.this$0.updateSearchElements(obj);
        this.this$0.updateLocalFragmentQueryString(obj);
    }
}
